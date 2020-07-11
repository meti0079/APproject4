package GAME;

import Cardspackage.Minion;
import Cardspackage.Minions.BigGameHunter;
import Cardspackage.Minions.BluegillWarrior;
import Cardspackage.Minions.ChillwindYeti;
import Cardspackage.Minions.CurioCollector;
import Cardspackage.Minions.Dreadscale;
import Cardspackage.Minions.Gruul;
import Cardspackage.Minions.HighPriestAmet;
import Cardspackage.Minions.KronxDragonhoof;
import Cardspackage.Minions.LeperGnome;
import Cardspackage.Minions.MurlocRaider;
import Cardspackage.Minions.MurlocWarleader;
import Cardspackage.Minions.OasisSnapjaw;
import Cardspackage.Minions.Sandbinder;
import Cardspackage.Minions.Sathrovarr;
import Cardspackage.Minions.SeaGiant;
import Cardspackage.Minions.SecurityRover;
import Cardspackage.Minions.Shieldbearer;
import Cardspackage.Minions.SwampKingDred;
import Cardspackage.Minions.TheBlackKnight;
import Cardspackage.Minions.ThrallmarFarseer;
import Cardspackage.Minions.TombWarden;
import Cardspackage.Spells.ArcaneShot;
import Cardspackage.Spells.AstralRift;
import Cardspackage.Spells.Backstab;
import Cardspackage.Spells.BookofSpecters;
import Cardspackage.Spells.FriendlySmith;
import Cardspackage.Spells.HolySmite;
import Cardspackage.Spells.LearnDraconic;
import Cardspackage.Spells.PharaohBlessing;
import Cardspackage.Spells.Polymorph;
import Cardspackage.Spells.Sprint;
import Cardspackage.Spells.StrengthinNumbers;
import Cardspackage.Spells.Swarmoflocusts;
import Cardspackage.Spells.gift;
import Cardspackage.Weapons.BattleAxe;
import Cardspackage.Weapons.BloodFury;
import Cardspackage.Weapons.HeavyAxe;
import hero.Heros;
import interfaces.Visitor;
import playModel.Player;

public class ExportVisitor implements Visitor{

	@Override
	public void visitArcaneShot(ArcaneShot m, Object taeget, Player attackerP, Player targetP) {
		System.out.println("arcane");
	}

	@Override
	public void visitAstralRift(AstralRift m, Object taeget, Player attackerP, Player targetP) {
System.out.println("astrial");		
	}

	@Override
	public void visitBackstab(Backstab m, Object taeget, Player attackerP, Player targetP) {
System.out.println("back");	
}

	@Override
	public void visitBattleAxe(BattleAxe m, Object taeget, Player attackerP, Player targetP) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitBigGameHunter(BigGameHunter m, Object taeget, Player attackerP, Player targetP) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitBloodFury(BloodFury m, Object taeget, Player attackerP, Player targetP) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitBluegillWarrior(BluegillWarrior m, Object taeget, Player attackerP, Player targetP) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitBookofSpecters(BookofSpecters m, Object taeget, Player attackerP, Player targetP) {
		System.out.println( "book");
	}

	@Override
	public void visitChillwindYeti(ChillwindYeti m, Object taeget, Player attackerP, Player targetP) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitCurioCollector(CurioCollector m, Object taeget, Player attackerP, Player targetP) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitDreadscale(Dreadscale m, Object taeget, Player attackerP, Player targetP) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitFriendlySmith(FriendlySmith m, Object taeget, Player attackerP, Player targetP) {	
		System.out.println("fri");
	}

	@Override
	public void visitgift(gift m, Object taeget, Player attackerP, Player targetP) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitGruul(Gruul m, Object taeget, Player attackerP, Player targetP) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitHeavyAxe(HeavyAxe m, Object taeget, Player attackerP, Player targetP) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitHighPriestAmet(HighPriestAmet m, Object taeget, Player attackerP, Player targetP) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitHolySmite(HolySmite m, Object taeget, Player attackerP, Player targetP) {
		System.out.println("holy");
	}

	@Override
	public void visitKronxDragonhoof(KronxDragonhoof m, Object taeget, Player attackerP, Player targetP) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitLearnDraconic(LearnDraconic m, Object taeget, Player attackerP, Player targetP) {
		System.out.println("learn");
	}

	@Override
	public void visitLeperGnome(LeperGnome m, Object taeget, Player attackerP, Player targetP) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitMurlocWarleader(MurlocWarleader m, Object taeget, Player attackerP, Player targetP) {
	
	}

	@Override
	public void visitMurlocRaider(MurlocRaider m, Object taeget, Player attackerP, Player targetP) {
	}

	@Override
	public void visitOasisSnapjaw(OasisSnapjaw m, Object taeget, Player attackerP, Player targetP) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitPharaohBlessing(PharaohBlessing m, Object taeget, Player attackerP, Player targetP) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitPolymorph(Polymorph m, Object taeget, Player attackerP, Player targetP) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitSandbinder(Sandbinder m, Object taeget, Player attackerP, Player targetP) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitSathrovarr(Sathrovarr m, Object taeget, Player attackerP, Player targetP) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitSeaGiant(SeaGiant m, Object taeget, Player attackerP, Player targetP) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitSecurityRover(SecurityRover m, Object taeget, Player attackerP, Player targetP) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitShieldbearer(Shieldbearer m, Object taeget, Player attackerP, Player targetP) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitSprint(Sprint m, Object taeget, Player attackerP, Player targetP) {
		System.out.println("sprint");
		}

	@Override
	public void visitStrengthinNumbers(StrengthinNumbers m, Object taeget, Player attackerP, Player targetP) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitSwampKingDred(SwampKingDred m, Object taeget, Player attackerP, Player targetP) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitSwarmoflocusts(Swarmoflocusts m, Object taeget, Player attackerP, Player targetP) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitTheBlackKnight(TheBlackKnight m, Object taeget, Player attackerP, Player targetP) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitThrallmarFarseer(ThrallmarFarseer m, Object taeget, Player attackerP, Player targetP) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitTombWarden(TombWarden m, Object taeget, Player attackerP, Player targetP) {
		// TODO Auto-generated method stub
		
	}

}
