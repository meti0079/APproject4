package interfaces;

import java.util.ArrayList;

import Cardspackage.Card;
import Cardspackage.Minions.BigGameHunter;
import Cardspackage.Minions.BluegillWarrior;
import Cardspackage.Minions.ChillwindYeti;
import Cardspackage.Minions.CurioCollector;
import Cardspackage.Minions.Dreadscale;
import Cardspackage.Minions.Gruul;
import Cardspackage.Minions.HighPriestAmet;
import Cardspackage.Minions.KronxDragonhoof;
import Cardspackage.Minions.LeperGnome;
import Cardspackage.Minions.Locust;
import Cardspackage.Minions.MurlocRaider;
import Cardspackage.Minions.MurlocWarleader;
import Cardspackage.Minions.OasisSnapjaw;
import Cardspackage.Minions.Sandbinder;
import Cardspackage.Minions.Sathrovarr;
import Cardspackage.Minions.SeaGiant;
import Cardspackage.Minions.SecurityRover;
import Cardspackage.Minions.Sheep;
import Cardspackage.Minions.Shieldbearer;
import Cardspackage.Minions.SleepyDragon;
import Cardspackage.Minions.SwampKingDred;
import Cardspackage.Minions.TheBlackKnight;
import Cardspackage.Minions.TombWarden;
import Cardspackage.Minions.WaxElemental;
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
import playModel.PlayerModel;

public interface Visitor {

		public void visitArcaneShot	(ArcaneShot m, Object taeget, PlayerModel attackerP, PlayerModel targetP);
		public void visitAstralRift(AstralRift m, Object taeget, PlayerModel attackerP, PlayerModel targetP);
		public void visitBackstab(Backstab m, Object taeget, PlayerModel attackerP, PlayerModel targetP);
		public void visitBattleAxe(BattleAxe m, Object taeget, PlayerModel attackerP, PlayerModel targetP);
		public void visitBigGameHunter(BigGameHunter m, Object taeget, PlayerModel attackerP, PlayerModel targetP);
		public void visitBloodFury(BloodFury m, Object taeget, PlayerModel attackerP, PlayerModel targetP);
		public void visitBluegillWarrior(BluegillWarrior m, Object taeget, PlayerModel attackerP, PlayerModel targetP);
		public void visitBookofSpecters(BookofSpecters m, Object taeget, PlayerModel attackerP, PlayerModel targetP);
		public void visitChillwindYeti(ChillwindYeti m, Object taeget, PlayerModel attackerP, PlayerModel targetP);
		public void visitCurioCollector(CurioCollector m, Object taeget, PlayerModel attackerP, PlayerModel targetP);
		public void visitDreadscale(Dreadscale m, Object taeget, PlayerModel attackerP, PlayerModel targetP);
		public void visitFriendlySmith(FriendlySmith m, Object taeget, PlayerModel attackerP, PlayerModel targetP);
		public void visitgift(gift m, Object taeget, PlayerModel attackerP, PlayerModel targetP);
		public void visitGruul(Gruul m, Object taeget, PlayerModel attackerP, PlayerModel targetP);
		public void visitHeavyAxe(HeavyAxe m, Object taeget, PlayerModel attackerP, PlayerModel targetP);
		public void visitHighPriestAmet(HighPriestAmet m, Object taeget, PlayerModel attackerP, PlayerModel targetP);
		public void visitHolySmite(HolySmite m, Object taeget, PlayerModel attackerP, PlayerModel targetP);
		public void visitKronxDragonhoof(KronxDragonhoof m, Object taeget, PlayerModel attackerP, PlayerModel targetP);
		public void visitLearnDraconic(LearnDraconic m, Object taeget, PlayerModel attackerP, PlayerModel targetP);
		public void visitLeperGnome(LeperGnome m, Object taeget, PlayerModel attackerP, PlayerModel targetP);
		public void visitMurlocWarleader(MurlocWarleader m, Object taeget, PlayerModel attackerP, PlayerModel targetP);
		public void visitMurlocRaider(MurlocRaider m, Object taeget, PlayerModel attackerP, PlayerModel targetP);
		public void visitOasisSnapjaw(OasisSnapjaw m, Object taeget, PlayerModel attackerP, PlayerModel targetP);
		public void visitPharaohBlessing(PharaohBlessing m, Object taeget, PlayerModel attackerP, PlayerModel targetP);
		public void visitPolymorph(Polymorph m, Object taeget, PlayerModel attackerP, PlayerModel targetP);
		public void visitSandbinder(Sandbinder m, Object taeget, PlayerModel attackerP, PlayerModel targetP);
		public void visitSathrovarr(Sathrovarr m, Object taeget, PlayerModel attackerP, PlayerModel targetP);
		public void visitSeaGiant(SeaGiant m, Object taeget, PlayerModel attackerP, PlayerModel targetP);
		public void visitSecurityRover(SecurityRover m, Object taeget, PlayerModel attackerP, PlayerModel targetP);
		public void visitShieldbearer(Shieldbearer m, Object taeget, PlayerModel attackerP, PlayerModel targetP);
		public void visitSprint(Sprint m, Object taeget, PlayerModel attackerP, PlayerModel targetP);
		public void visitStrengthinNumbers(StrengthinNumbers m, Object taeget, PlayerModel attackerP, PlayerModel targetP);
		public void visitSwampKingDred(SwampKingDred m, Object taeget, PlayerModel attackerP, PlayerModel targetP);
		public void visitSwarmoflocusts(Swarmoflocusts m, Object taeget, PlayerModel attackerP, PlayerModel targetP);
		public void visitTheBlackKnight(TheBlackKnight m, Object taeget, PlayerModel attackerP, PlayerModel targetP);
		public void visitTombWarden(TombWarden m, Object taeget, PlayerModel attackerP, PlayerModel targetP);	
		public void visitLocust(Locust m, Object taeget, PlayerModel attackerP, PlayerModel targetP);	
		public void visitSheep(Sheep m, Object taeget, PlayerModel attackerP, PlayerModel targetP);	
		public void visitWaxElemental(WaxElemental m, Object taeget, PlayerModel attackerP, PlayerModel targetP);	
		public void visitSleepyDragon(SleepyDragon m, Object taeget, PlayerModel attackerP, PlayerModel targetP);
}
