package server.gameModel;

import java.util.ArrayList;
import java.util.Random;
import server.User;
import server.cardspackage.Card;
import server.cardspackage.Minion;
import server.cardspackage.Spell;
import server.cardspackage.Minions.BigGameHunter;
import server.cardspackage.Minions.BluegillWarrior;
import server.cardspackage.Minions.ChillwindYeti;
import server.cardspackage.Minions.CurioCollector;
import server.cardspackage.Minions.Dreadscale;
import server.cardspackage.Minions.Gruul;
import server.cardspackage.Minions.HighPriestAmet;
import server.cardspackage.Minions.KronxDragonhoof;
import server.cardspackage.Minions.LeperGnome;
import server.cardspackage.Minions.Locust;
import server.cardspackage.Minions.MurlocRaider;
import server.cardspackage.Minions.MurlocWarleader;
import server.cardspackage.Minions.OasisSnapjaw;
import server.cardspackage.Minions.Sandbinder;
import server.cardspackage.Minions.Sathrovarr;
import server.cardspackage.Minions.SeaGiant;
import server.cardspackage.Minions.SecurityRover;
import server.cardspackage.Minions.Sheep;
import server.cardspackage.Minions.Shieldbearer;
import server.cardspackage.Minions.SleepyDragon;
import server.cardspackage.Minions.SwampKingDred;
import server.cardspackage.Minions.TheBlackKnight;
import server.cardspackage.Minions.TombWarden;
import server.cardspackage.Minions.WaxElemental;
import server.cardspackage.Spells.ArcaneShot;
import server.cardspackage.Spells.AstralRift;
import server.cardspackage.Spells.Backstab;
import server.cardspackage.Spells.BookofSpecters;
import server.cardspackage.Spells.FriendlySmith;
import server.cardspackage.Spells.HolySmite;
import server.cardspackage.Spells.LearnDraconic;
import server.cardspackage.Spells.PharaohBlessing;
import server.cardspackage.Spells.Polymorph;
import server.cardspackage.Spells.Sprint;
import server.cardspackage.Spells.StrengthinNumbers;
import server.cardspackage.Spells.Swarmoflocusts;
import server.cardspackage.Spells.gift;
import server.cardspackage.Weapons.BattleAxe;
import server.cardspackage.Weapons.BloodFury;
import server.cardspackage.Weapons.HeavyAxe;
import server.hero.Heros;
import server.interfaces.Visitor;
import server.playModel.Mapper;
import server.playModel.PlayerModel;
import server.playModel.Quest;

public class ExportVisitor implements Visitor{
	private DeckReader d;
	private Mapper mapper;
	private String state;
	private User user1;
	private User user2;
	public ExportVisitor(Mapper mapper, String state, User user1, User user2) {
		d= new DeckReader();
		this.mapper=mapper;
		this.state=state;
		this.user1=user1;
		this.user2=user2;
	}
	///////////// spells
	@Override
	public void visitLearnDraconic(LearnDraconic m, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
		if(attackerP.getHand().contains(m)) {
			attackerP.setQuest(new Quest(new SleepyDragon(), 8, "spell"));			
		}
	}
	@Override
	public void visitStrengthinNumbers(StrengthinNumbers m, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
		try {
			if(attackerP.getHand().contains(m)) {
					for(int i=0;i<attackerP.getDecksize();i++) {
						if(attackerP.getDeck().get(i) instanceof Minion) {
							attackerP.setQuest(new Quest(attackerP.getDeck().get(i), 10, "minion"));						
							return;					
						}
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void visitPolymorph(Polymorph m, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
		for(int i=0 ;i<7;i++) {  
			if(targetP.getBattleGroundCard().get(i)!=null) {
				targetP.getBattleGroundCard().remove(i);
				targetP.getBattleGroundCard().add(i, d.find("Sheep"));
				return;
			}
		}	
	}
	@Override
	public void visitHolySmite(HolySmite m, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
		((Card)taeget).setHp(((Card)taeget).getHp()-3);
	}
	@Override
	public void visitgift(gift m, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
		attackerP.getHero().setHP(attackerP.getHero().get_HP()+2);
	}
	@Override
	public void visitFriendlySmith(FriendlySmith m, Object taeget, PlayerModel attackerP, PlayerModel targetP) {	
		ArrayList<String > name=new ArrayList<>();
		name.add("Battle Axe");	name.add("Heavy Axe");name.add("Blood Fury");
		int x= new Random().nextInt(3);
		Card xx=d.find(name.get(Math.abs(x)));
		xx.setHp(xx.getHp()+2);
		xx.setAttack(xx.getAttack()+2);
		attackerP.getDeck().add(xx);
	}
	@Override
	public void visitBookofSpecters(BookofSpecters m, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
		try {
			for(int i=0;i<3;i++) {
				handledeck(attackerP, targetP);
				mapper.addToHand(attackerP, targetP,state);
				if(attackerP.getHand().get(attackerP.getHand().size()-1) instanceof Spell)
					attackerP.getHand().remove(attackerP.getHand().size()-1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	@Override
	public void visitBackstab(Backstab m, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
		targetP.getHero().setHP(targetP.getHero().get_HP()-2);
	}
	@Override
	public void visitPharaohBlessing(PharaohBlessing m, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
		((Card )taeget).setTaunt(true);
		((Card )taeget).setDivineShield(true);
		((Card )taeget).setAttack(((Card )taeget).getAttack()+4);
		((Card )taeget).setHp(((Card )taeget).getHp()+4);
	}
	@Override
	public void visitSwarmoflocusts(Swarmoflocusts m, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
		for(int i =0 ;i<7 ;i++) {
			if(attackerP.getBattleGroundCard().get(i)== null) {
				attackerP.getBattleGroundCard().remove(i);
				attackerP.getBattleGroundCard().add(i, new Locust());
			}	
		}		
	}
	@Override
	public void visitSprint(Sprint m, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
		try {
			for (int i = 0; i < 4; i++) {
				handledeck(attackerP, targetP);
				mapper.addToHand(attackerP, targetP, state);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void handledeck(PlayerModel attackerP , PlayerModel targetP) throws Exception {
		if(attackerP.getDeck().size()==0) {
				if(attackerP.getTurn()==0) {
					mapper.readDeck(attackerP, targetP,state,user1,user2);
				}else {
					mapper.readDeck(targetP,attackerP,state, user1, user2);
				}
		}
	}
	@Override
	public void visitArcaneShot(ArcaneShot m, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
		if(targetP.getBattleGroundCard().contains(taeget)) {
			((Card)taeget).setHp(((Card) taeget).getHp()-2);
		}else if (targetP.getHero().equals(taeget)) {
			((Heros)taeget).setHP(((Heros)taeget).get_HP()-2);
		}
	}
	@Override
	public void visitAstralRift(AstralRift m, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
		try {
			int x=0;
			for(int i=0; attackerP.getDeck().size()>i;i++) {
				handledeck(attackerP, targetP);
				if(attackerP.getDeck().get(i) instanceof Minion && x<2) {
					attackerP.getHand().add(attackerP.getDeck().get(i));
					attackerP.getDeck().remove(i);
					i--;
					x++;
				}
			}
		} catch (Exception e) {	e.printStackTrace();}
	}

	//////////////////// minions
	@Override
	public void visitTheBlackKnight(TheBlackKnight m, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
		if(attackerP.getHand().contains(m))
			for (int i = 0; i < 7; i++) {
				if(targetP.getBattleGroundCard().get(i)!= null &&targetP.getBattleGroundCard().get(i).isTaunt()) {
					targetP.getBattleGroundCard().remove(i);
					targetP.getBattleGroundCard().add(i,null);
					return;
				}
			}
	}
	@Override
	public void visitSandbinder(Sandbinder m, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
		if(attackerP.getHand().contains(m))
			attackerP.getHand().add(new WaxElemental());	
	}	
	@Override
	public void visitLeperGnome(LeperGnome m, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
		if(taeget == null)
			if(m.getHp()<=0) {
				targetP.getHero().setHP(targetP.getHero().get_HP()-2);
			}
	}
	@Override
	public void visitMurlocWarleader(MurlocWarleader m, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
		try {
			if(taeget== null)
				if(mapper.validCard(attackerP, m)) {
					for(int i=0;i<7;i++) {
						if(attackerP.getBattleGroundCard().get(i) instanceof MurlocRaider)
							attackerP.getBattleGroundCard().get(i).setAttack(attackerP.getBattleGroundCard().get(i).getAttack()+2);
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void visitSwampKingDred(SwampKingDred m, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
		if(targetP.getBattleGroundCard().contains(m) && attackerP.getHand().contains(taeget)) {
			((Minion) taeget).setHp(((Minion) taeget).getHp()-m.getAttack());
			m.setHp(m.getHp()-((Minion) taeget).getAttack());	
			attackerP.checkCard(targetP,this,mapper);
			targetP.checkCard(attackerP, this, mapper);
		}
		if(attackerP.getHand().contains(m))
			m.setUsedToAttack(false);
	}
	@Override
	public void visitHighPriestAmet(HighPriestAmet m, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
		if(taeget!= null)
			if(attackerP.getBattleGroundCard().contains(m)) {
				if(attackerP.getHand().contains(taeget)) {
					((Card) taeget).setHp(m.getHp());
				}
			}		
	}
	@Override
	public void visitGruul(Gruul m, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
		if(taeget==null)
			if(m.getHp()>0)
				if(targetP.getBattleGroundCard().contains(m)) {
					m.setHp(m.getHp()+1);
					m.setAttack(m.getAttack()+1);
				}
	}
	@Override
	public void visitBluegillWarrior(BluegillWarrior m, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
		if(taeget!= null)
			if(taeget.equals(targetP.getHero()) || targetP.getBattleGroundCard().contains(taeget))
				m.setRush(false);
	}
	@Override
	public void visitDreadscale(Dreadscale m, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
		if(taeget ==null)
			if(m.getHp()>0)
				if(targetP.getBattleGroundCard().contains(m)) {
					for(int i =0 ;i<7;i++)
						if(attackerP.getBattleGroundCard().get(i)!=null)
							attackerP.getBattleGroundCard().get(i).setHp(attackerP.getBattleGroundCard().get(i).getHp()-1);
					for(int i =0 ;i<7;i++)
						if(targetP.getBattleGroundCard().get(i)!=null)
							targetP.getBattleGroundCard().get(i).setHp(targetP.getBattleGroundCard().get(i).getHp()-1);			
					attackerP.checkCard(targetP,this, mapper);
					targetP.checkCard(attackerP, this, mapper);
				}
	}
	@Override
	public void visitBigGameHunter(BigGameHunter m, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
		try {
			if(attackerP.getHand().contains(m) ) {
				for (int i = 0; i < 7; i++) {
					if(targetP.getBattleGroundCard().get(i)!=null ) {						
						if(targetP.getBattleGroundCard().get(i).getAttack()>=7) {	
							targetP.getBattleGroundCard().remove(i);
							targetP.getBattleGroundCard().add(i,null);
							break;							
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void visitLocust(Locust m, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
		if(taeget !=null)
			if(m.getHp()>0)
				m.setRush(false);
	}
	@Override
	public void visitTombWarden(TombWarden m, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
		if(taeget==null)   
			if(attackerP.getHand().contains(m)) {
				for (int i = 0; i < 7; i++) {
					if(attackerP.getBattleGroundCard().get(i)==null) {
						attackerP.getBattleGroundCard().remove(i);
						attackerP.getBattleGroundCard().add(i,m.copy());
						break;
					}
				}
			}	
	}
	@Override
	public void visitSathrovarr(Sathrovarr m, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
		if(taeget!=null)
			try {
				if(attackerP.getHand().contains(m)   && mapper.validCard(attackerP, (Card) taeget)) {
					attackerP.getDeck().add(((Card) taeget).copy());
					attackerP.getHand().add(((Card) taeget).copy());
					for (int i = 0; i < 7; i++) {
						if(attackerP.getBattleGroundCard().get(i)==null ) {						
							attackerP.getBattleGroundCard().remove(i);
							attackerP.getBattleGroundCard().add(i,((Card) taeget).copy());
							break;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	@Override
	public void visitCurioCollector(CurioCollector m, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
		if(attackerP.getBattleGroundCard().contains(m))
			if(m.getHp()>0)
				if(taeget==null) {
					m.setHp(m.getHp()+1);
					m.setAttack(m.getAttack()+1);
				}
	}
	@Override
	public void visitChillwindYeti(ChillwindYeti m, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
	}
	@Override
	public void visitMurlocRaider(MurlocRaider m, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
	}
	@Override
	public void visitKronxDragonhoof(KronxDragonhoof m, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
	}	
	@Override
	public void visitSheep(Sheep m, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
	}
	@Override
	public void visitWaxElemental(WaxElemental m, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
	}
	@Override
	public void visitSleepyDragon(SleepyDragon m, Object taeget, PlayerModel attackerP, PlayerModel targetP) {		
	}@Override
	public void visitOasisSnapjaw(OasisSnapjaw m, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
	}
	@Override
	public void visitSeaGiant(SeaGiant m, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
	}
	@Override
	public void visitSecurityRover(SecurityRover m, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
	}	
	@Override
	public void visitShieldbearer(Shieldbearer m, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
	}
	///////////////////////////weapons
	@Override
	public void visitHeavyAxe(HeavyAxe m, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
	}	@Override
	public void visitBattleAxe(BattleAxe m, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
	}@Override
	public void visitBloodFury(BloodFury m, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
	}
}
