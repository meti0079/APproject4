package playModel;

import java.awt.TextArea;
import java.io.File;
import java.io.FileNotFoundException;
import java.security.spec.PSSParameterSpec;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Cardspackage.Card;
import Cardspackage.Minion;
import Cardspackage.Spell;
import Cardspackage.Weapon;
import GAME.AbstractAdapter;
import GAME.DeckReader;
import GAME.Deck;
import GAME.ExportHeroVisitor;
import GAME.ExportPassives;
import GAME.Gamestate;
import GAME.Logger;
import grapic.HeroShow;
import grapic.PlayPanel;
import hero.Heros;
import hero.Hunter;
import hero.Mage;
import hero.Priest;
import hero.Rouge;
import hero.Warlock;
import hero.heroPower.HeroPower;
import hero.heroPower.HunterPower;
import hero.heroPower.MagePower;
import hero.heroPower.PriestPower;
import hero.heroPower.RougePower;
import hero.heroPower.WarlockPower;
import interfaces.HeroPowerVisitor;
import interfaces.HeroVisitor;
import interfaces.PassiveVisitor;
import interfaces.Visitor;
import myListeners.PassiveListener;
import passives.Passive;

public class Mapper {

	private ExportPassives pas=new ExportPassives();
	private ArrayList<Passive> allPassives;
	private DeckReader deckReader;
	private static Mapper map;
	private Gamestate game;
	private ExportHeroVisitor heroV=new ExportHeroVisitor();
	private Mapper() throws Exception {
		game=Gamestate.getinsist();
		allPassives=new ArrayList<>();
		readFromFile();
		readPassiveFile();
	}
	public static Mapper getinsist() throws Exception {
		if (map==null) {
			map=new Mapper();
		}
		return map;
	}


	public void setBackGroundCard(PlayerModel p) {
		for (int i=0;i<7;i++) { 
			if(p.getBattleGroundCard().get(i) != null) {
				p.getBattleGroundCard().get(i).setUsedToAttack(false);
			}
		}			
	}
	public void nextTurn(PlayerModel p, PlayerModel enemy, Visitor v) {
		handleHeroSpecialPower(p, enemy, heroV, 0, 0, 1);
		checkPassive( p, enemy, null);
		setBackGroundCard(p);
		p.checkCard(enemy, v);
		enemy.checkCard(p, v);
		for (Card cards : p.getHand()) 
			cards.setUsedToAttack(false);
		p.getHero().getHero_power().setUsed(false);
		if(p.getWeapon()!=null)
			p.getWeapon().setUsedToAttack(false);
		PlayPanel.i=0;
	}
	public boolean betweencards(PlayerModel p,int x, Card s, TextArea textArea , PlayerModel enemy) throws Exception {
		for(int i=x+1;i<7;i++) {
			if(p.getBattleGroundCard().get(i)==null) {
				p.getBattleGroundCard().remove(i);
				p.getHand().remove(s);
				p.getBattleGroundCard().add(x+1,s.copy());
				checkPassive(p, enemy, p.getBattleGroundCard().get(x+1));
				checkPassive( enemy, p,p.getBattleGroundCard().get(x+1));
				handleHeroSpecialPower(p, enemy, heroV, 0, 0, 2);
				p.getBattleGroundCard().get(x+1).setUsedToAttack(true);
				String se=game.getPlayer().get_name()+"  summon  "+ s.get_Name()+"\n";
				textArea.append(se);
				p.setCurrentgem(p.getCurrentgem()-s.get_Mana());
				return true;
			}
		}
		for(int i=x-1;i>=0;i--) {
			if(p.getBattleGroundCard().get(i)==null) {
				p.getBattleGroundCard().remove(i);
				p.getHand().remove(s);
				p.getBattleGroundCard().add(x,s.copy());
				checkPassive(p, enemy, p.getBattleGroundCard().get(x));
				checkPassive( enemy, p,p.getBattleGroundCard().get(x));
				handleHeroSpecialPower(p, enemy, heroV, 0, 0, 2);
				p.getBattleGroundCard().get(x).setUsedToAttack(true);
				String se=game.getPlayer().get_name()+"  summon  "+ s.get_Name()+"\n";
				textArea.append(se);
				p.setCurrentgem(p.getCurrentgem()-s.get_Mana());
				return true;
			}
		}
		return false;
	}

	public void changeCartAtFirst(PlayerModel p, Card card) {
		if(card.getUsedToAttack())
			return ;
		p.getDeck().add(card);
		p.getHand().remove(card);
		p.getHand().add(p.getDeck().get(0));
		p.getHand().get(p.getHand().size()-1).setUsedToAttack(true);
		p.getDeck().remove(0);
		p.setChanges(p.getChanges()+1);
	}
	private void checkPassive(PlayerModel me , PlayerModel enemy, Card x) {
		if(me.getPassive()!= null)
			me.getPassive().accept(pas, me, enemy, x);
	}
	public boolean addTobattleground(Card s,int x,int y, PlayerModel me, PlayerModel enemy, Visitor v, TextArea textArea) throws Exception {

		if(s.get_Mana()<=me.getCurrentgem()) {
			if(s.getType().equalsIgnoreCase("minion")) {
				if(y<700-(220*me.getTurn()) && y>480-(220*me.getTurn())) {
					s.accept(v,findTarget(x, y, me, enemy), me, enemy);
					if(me.getBattleGroundCard().get((x-200)/160) == null ) {
						calleBattleAccept(me, enemy, v, s);
						me.getBattleGroundCard().remove((x-200)/160);
						me.getBattleGroundCard().add((x-200)/160,s.copy());
						checkPassive( me, enemy, me.getBattleGroundCard().get((x-200)/160));
						checkPassive( enemy, me, me.getBattleGroundCard().get((x-200)/160));
						checkQuestDone(me.getBattleGroundCard().get((x-200)/160), me);
						handleHeroSpecialPower(me, enemy, heroV, x, y, 2);
						me.getHand().remove(s);
						me.getBattleGroundCard().get((x-200)/160).setUsedToAttack(true);
						String se=me.getName()+"  summon  "+ s.get_Name()+"\n";
						textArea.append(se);
						me.setCurrentgem(me.getCurrentgem()-s.get_Mana());
						me.checkCard(enemy, v);
						return true;
					}else if(betweencards(me,((x-200)/160) , s, textArea, enemy)) {
						me.checkCard(enemy, v);
						checkQuestDone(s, me);
						return true;
					}else {						
						JOptionPane.showConfirmDialog(null, "your battleground if full play a card or click next turn","cant plan",JOptionPane.CLOSED_OPTION);
						return false;							
					}
				}else {
					return false;
				}
			}else if(s.getType().equalsIgnoreCase("Spell")) {
				if(s.accept(v, findTarget(x, y, me, enemy), me, enemy)) {
					checkQuestDone(s, me);
					String se=me.getName()+"  played  "+ s.get_Name()+"\n";
					textArea.append(se);
					me.getHand().remove(s);	
					me.setCurrentgem(me.getCurrentgem()-s.get_Mana());;
				}
			}else {
				checkQuestDone(s, me);
				String se=me.getName()+"  Summon  "+ s.get_Name()+"\n";
				textArea.append(se);
				me.setWeapon( (Weapon) s.copy());
				me.getWeapon().setUsedToAttack(true);
				me.getHand().remove(s);
				me.setCurrentgem(me.getCurrentgem()-s.get_Mana());;
			}
			return true;
		}else {
			return false;
		}
	}
	private void checkQuestDone(Card x, PlayerModel p) {

		if(p.getQuest()!= null) {
			if(p.getQuest().getType().equalsIgnoreCase(x.getType())) {
				p.getQuest().setHave(p.getQuest().getHave()+x.get_Mana());
			}		
		}
	}

	private Object findTarget(int x, int y, PlayerModel me, PlayerModel enemy) {

		if(y<700-(220*me.getTurn()) && y>480-(220*me.getTurn())) {
			return me.getBattleGroundCard().get((x-200)/160);
		}else if(y<700-(220*enemy.getTurn()) && y>480-(220*enemy.getTurn())) {
			return enemy.getBattleGroundCard().get((x-200)/160);
		}else if(658 <x && x<850  && y>660-(560*me.getTurn()) &&y<860-(560*me.getTurn())) {
			return me.getHero();
		}else if(658 <x && x<850  && y>100+(560*me.getTurn()) &&y<300+(560*me.getTurn())) {
			return enemy.getHero();
		}
		return null;		
	}

	public void manaSet(PlayerModel me, PlayerModel enemy) {
		if(PlayPanel.getRoundGame()%2==me.getTurn()) {
			if(me.getPreviosgem()==10) {
				me.setCurrentgem(10);
				return;
			}
			me.setPreviosgem(me.getPreviosgem()+1);
			me.setCurrentgem(me.getPreviosgem());;
		}else {
			if(enemy.getPreviosgem()==10) {
				enemy.setCurrentgem(10);
				return;
			}
			enemy.setPreviosgem(enemy.getPreviosgem()+1);
			enemy.setCurrentgem(enemy.getPreviosgem());
		}
	}
	public boolean useWeapon(Weapon weapon, PlayerModel p) {
		if(!weapon.getUsedToAttack() || weapon.isBattlecry()) {
			weapon.setDurability(weapon.getDurability()-1);
			try {
				Logger.getinsist().log(Gamestate.getinsist().getPlayer().get_name(), "" , p.getName()+"  played  "+ weapon.get_Name());
			} catch (Exception e) {	e.printStackTrace();	}
			if(weapon.getDurability()!=0) {					
				weapon.setBattlecry(false);
				weapon.setUsedToAttack(true);
			}else {
				weapon=null;
			}
			return true;
		}
		return false;
	}
	private void calleBattleAccept(PlayerModel p , PlayerModel enemy, Visitor v, Card x) {
		for (int i = 0; i < 7; i++) {
			if(p.getBattleGroundCard().get(i) != null)
				p.getBattleGroundCard().get(i).accept(v, x, p, enemy);
		}
		for (int i = 0; i < 7; i++) {
			if(enemy.getBattleGroundCard().get(i) != null)
				enemy.getBattleGroundCard().get(i).accept(v, x, p, enemy);
		}
	}
	private void calleHandAccept(PlayerModel p , PlayerModel enemy, Visitor v) {
		for (int i = 0; i < 7; i++) {
			if(p.getBattleGroundCard().get(i) != null)
				p.getBattleGroundCard().get(i).accept(v, null, p, enemy);
		}
		for (int i = 0; i < 7; i++) {
			if(enemy.getBattleGroundCard().get(i) != null)
				enemy.getBattleGroundCard().get(i).accept(v, null, p, enemy);
		}
	}
	public void addToHand(PlayerModel p, PlayerModel enemy, Visitor v) {
		int x=0;
		calleHandAccept(p, enemy, v);
		if(game.getState().equalsIgnoreCase("enemy") ||game.getState().equalsIgnoreCase("computer")  ) {			
			if(p.getDecksize()<=0) {
				x=0;
			}else {
				x=new Random().nextInt(p.getDeck().size());						
			}
			p.setDecksize(p.getDecksize()-1);
			if(p.getHand().size()<10) {
				p.AddToHand(p.getDeck().get(Math.abs(x)));
				checkPassive( p, enemy, p.getDeck().get(Math.abs(x)));
				checkPassive(enemy, p, p.getDeck().get(Math.abs(x)));
				handleHeroSpecialPower(p, enemy, heroV,  0, 0, 1);
				p.getDeck().remove(Math.abs(x));
			}else {
				p.getDeck().remove(Math.abs(x));	
			}
		}else {
			x=0;
			p.setDecksize(p.getDecksize()-1);
			if(p.getHand().size()<10) {
				p.AddToHand(p.getDeck().get(x));
				checkPassive( enemy,p, p.getDeck().get(Math.abs(x)));
				checkPassive( enemy,p, p.getDeck().get(Math.abs(x)));
				p.getDeck().remove(x);
			}else {
				p.getDeck().remove(x);	
			}
		}
	}



	public void readDeck(PlayerModel me,PlayerModel enemy) {
		if (game.getState().equalsIgnoreCase("enemy")) {
			if(me.getDecksize()==0 ) {
				me.setDeck((ArrayList<Card>) game.getPlayer().get_mydeck().clone());
				me.setDecksize(me.getDeck().size());
				JOptionPane.showMessageDialog(null, "player1 : Deck update");	
			}
			if(enemy.getDecksize()==0 ) {
				enemy.setDeck((ArrayList<Card>) game.getEnemy().getEnemyDeck().getDeck().clone());
				enemy.setDecksize(enemy.getDeck().size());
				JOptionPane.showMessageDialog(null, "player2 : Deck update");	
			}
		}else if(game.getState().equalsIgnoreCase("Deck")){
			if(me.getDecksize()==0 ) {
				me.setDeck((ArrayList<Card>) deckReader.cardFactory("friend").clone());
				me.setDecksize(me.getDeck().size());
				JOptionPane.showMessageDialog(null, "player1 : Deck update");	
			}
			if(enemy.getDecksize()==0 ) {
				enemy.setDeck((ArrayList<Card>) deckReader.cardFactory("enemy").clone());
				enemy.setDecksize(enemy.getDeck().size());
				JOptionPane.showMessageDialog(null, "player2 : Deck update");	
			}
		}else {
		}
	}
	public PlayerModel readMe() throws Exception {
		if(game.getState().equals("enemy") ||game.getState().equals("computer")) {
			PlayerModel x=new PlayerModel(game.getPlayer().getMyDeck(), 0, game.getPlayer().get_name());
			return x;
		}else if(game.getState().equals("Deck")) {
			Deck s=new  Deck();
			s.setHeroDeck("mage");
			s.setDeck(deckReader.cardFactory("friend"));
			PlayerModel x= new PlayerModel(s, 0,game.getPlayer().get_name());
			return x;
		}else {
			return null;
		}
	}
	private void readFromFile() throws FileNotFoundException {
		File f1=new File("C:\\Users\\MohammadMehdi\\git\\repository2\\APproject\\src\\main\\deckreader.json");
		Scanner s=new Scanner(f1);
		String se="";
		while(s.hasNext()) {
			se+=s.nextLine(); 
		}
		Gson gson=new GsonBuilder().setPrettyPrinting().create();
		deckReader=gson.fromJson(se, DeckReader.class);	
	}
	public PlayerModel readEnemy() throws Exception {
		if(game.getState().equals("enemy")||game.getState().equals("computer")) {
			PlayerModel x=new PlayerModel(game.getEnemy().getEnemyDeck(), 1,"enemy");
			x.setPreviosgem(0);
			return x;
		}else if(game.getState().equals("Deck")) {
			Deck s=new  Deck();
			s.setHeroDeck("mage");
			s.setDeck(deckReader.cardFactory("enemy"));
			PlayerModel x= new PlayerModel(s, 1, "enemy");
			x.setPreviosgem(0);
			return x;
		}else {
			return null;
		}	
	}
	public void addUse(Card s) {
		if(game.getState().equalsIgnoreCase("enemy"))
			game.getPlayer().SpecialCard(s.get_Name()).addUse();
	}
	public boolean isFinished(PlayerModel me, PlayerModel enemy, TextArea textArea) {
		try {
			if(enemy.getHero().get_HP()<=0  || me.getHero().get_HP()<=0||PlayPanel.getRoundGame()==1) {
				if(enemy.getHero().get_HP()> me.getHero().get_HP()) {
					String se="Enemy  win the game !!!!!!";
					textArea.append(se);
					JOptionPane.showConfirmDialog(null, "ENEMY WON !!!!", "game finished", JOptionPane.OK_CANCEL_OPTION);
					Logger.getinsist().log(game.getPlayer().get_name(), "enemy  won the match", "");
				}else {
					String se=me.getName()+" win the game !!!!!!";
					textArea.append(se);
					JOptionPane.showConfirmDialog(null, "YOU WON !!!!", "game finished", JOptionPane.OK_CANCEL_OPTION);
					Logger.getinsist().log(game.getPlayer().get_name(), game.getPlayer().get_name()+"  won the match", "");
					if(game.getState().equalsIgnoreCase("enemy"))
						game.getPlayer().getMyDeck().addWin();
				}
				if(game.getState().equalsIgnoreCase("enemy")) {
					game.getPlayer().addPlays();				
					game.getPlayer().getMyDeck().addUsethisDeck();
				}
				enemy.getHero().setHP(30);
				return true;
			}
		}catch (Exception e) {}
		return false;
	}
	private void readPassiveFile() throws FileNotFoundException {
		GsonBuilder gsonBilder=new GsonBuilder();
		gsonBilder.registerTypeAdapter(Passive.class, new AbstractAdapter<Passive>());
		gsonBilder.setPrettyPrinting();
		Gson gson=gsonBilder.create();		
		File f3=new File(System.getProperty("user.dir")+"\\src\\passives\\passiveFiles");
		File[] dirr3=f3.listFiles();
		if(dirr3!=null) {
			for(File ch:dirr3) {
				Scanner sca=new Scanner(ch);
				String t1="";
				while(sca.hasNext()) {
					t1+=sca.nextLine();
				}
				Passive s= gson.fromJson(t1, Passive.class);
				allPassives.add(s);
				sca.close();
			}
		}
	}

	public ArrayList<Passive> getAllPassives() {
		return allPassives;
	}

	public void setAllPassives(ArrayList<Passive> allPassives) {
		this.allPassives = allPassives;
	}
	public boolean handleAttack(PlayerModel me , PlayerModel enemy, Visitor v, int x, int y, Card card) {
		return card.accept(v, findTarget(x, y, me, enemy), me, enemy);
	}
	public boolean checkTount(PlayerModel targetP) {
		for(Card card : targetP.getBattleGroundCard()) {
			if(card !=null)
				if(card.isTaunt())
					return true;
		}
		return false;
	}
	public boolean validCard(PlayerModel targetP , Card ca) {
		for(Card card : targetP.getBattleGroundCard()) {
			if(card !=null)
				if(card.equals(ca))
					return true;
		}
		return false;
	}
	public boolean checkQuest(PlayerModel p) {
		if(p.getQuest()==null) {
			return false;
		}else if(p.getQuest().getHave()>=p.getQuest().getMission()) {
			p.getHand().add(p.getQuest().getReward());
			p.setQuest(null); 
			return false;	
		}else {
			return true;
		}
	}
	public boolean handleHeroPower(PlayerModel me, PlayerModel enemy, HeroPowerVisitor v, int x, int y, HeroPower heropower, Visitor visitor) {
		if(heropower.accept(v, findTarget(x, y, me, enemy), me, enemy)) {
			heropower.setUse(heropower.getUse()+1);			
			if(heropower.getMaxUse()==heropower.getUse()) {
				heropower.setUsed(true);				
			}
			me.setCurrentgem(me.getCurrentgem()-heropower.getMana());
			me.checkCard(enemy, visitor);
			return true;
		}
		return false;
	}
	public boolean handleHeroSpecialPower(PlayerModel me, PlayerModel enemy, ExportHeroVisitor v, int x, int y, int d) {
			me.getHero().accept(v, me, d, (Card) findTarget(x, y, me, enemy));
			return true;
	}
}

