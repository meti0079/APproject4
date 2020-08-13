
package main;

import server.cardspackage.Card;
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
import server.cardspackage.Minions.MechanicalYeti;
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
import server.gameModel.AbstractAdapter;
import server.passives.ManaJump;
import server.passives.OffCards;
import server.passives.Passive;
import java.io.FileWriter;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class Main {
	public static void main(String[] args) throws Exception {
		ArrayList<Card> all=new ArrayList<>();
		Card a=new ArcaneShot(); 
		all.add(a);
		Card cards=new AstralRift();
		all.add(cards);
		Backstab b=new Backstab();
		all.add(b);
		BattleAxe ba=new BattleAxe();
		all.add(ba);
		BigGameHunter bi=new BigGameHunter();
		all.add(bi);
		BloodFury bl=new BloodFury();
		all.add(bl);
		BluegillWarrior b1=new BluegillWarrior();
		all.add(b1);
		BookofSpecters b2=new BookofSpecters();
		all.add(b2);
		ChillwindYeti c=new ChillwindYeti();
		all.add(c);
		CurioCollector cu=new CurioCollector();
		all.add(cu);
		Dreadscale d=new Dreadscale();
		all.add(d);
		FriendlySmith f=new FriendlySmith();
		all.add(f);
		gift g=new gift(); 
		all.add(g);
		Gruul gu=new Gruul();
		all.add(gu);
		HeavyAxe h=new HeavyAxe();
		all.add(h);
		HighPriestAmet h1=new HighPriestAmet();
		all.add(h1);
		HolySmite ho=new HolySmite();
		all.add(ho);
		KronxDragonhoof k=new KronxDragonhoof();
		all.add(k);
		LearnDraconic l=new LearnDraconic();
		all.add(l);
		LeperGnome lp=new LeperGnome();
		all.add(lp);
		MurlocRaider m=new MurlocRaider();
		all.add(m);
		MurlocWarleader mw=new MurlocWarleader();
		all.add(mw);
		OasisSnapjaw o=new OasisSnapjaw();
		all.add(o);
		PharaohBlessing p=new PharaohBlessing();
		all.add(p);
		Polymorph pp=new Polymorph();
		all.add(pp);
		Sandbinder s=new Sandbinder();
		all.add(s);
		Sathrovarr sa=new Sathrovarr();
		all.add(sa);
		SeaGiant se=new SeaGiant();
		all.add(se);
		SecurityRover sc=new SecurityRover();
		all.add(sc);
		Shieldbearer sh=new Shieldbearer();
		all.add(sh);
		Sprint sp=new Sprint();
		all.add(sp);
		StrengthinNumbers st=new StrengthinNumbers();
		all.add(st);
		SwampKingDred sw=new SwampKingDred();
		all.add(sw);
		Swarmoflocusts sq=new Swarmoflocusts();
		all.add(sq);
		TheBlackKnight th=new TheBlackKnight();
		all.add(th);
		Card to= new TombWarden();
		all.add(to);
		Card x= new MechanicalYeti();
		all.add(x);
//		
		GsonBuilder gsonBilder=new GsonBuilder();
		gsonBilder.registerTypeAdapter(Card.class, new AbstractAdapter<Card>());
		gsonBilder.setPrettyPrinting();
		Gson gson=gsonBilder.create();
for (Card qqq : all) {
	System.out.println(qqq.get_Name());
	String nn=gson.toJson(cards);
	FileWriter f2=new FileWriter("E:\\2\\"+qqq.get_Name()+".json");
	f2.write(nn);
	f2.close();
}
//			FileWriter fileWriter1 = new FileWriter("E:\\2\\"+y.getName()+".json");
//			gson.toJson(y, new TypeToken<Passive>(){}.getType(), fileWriter1);
//			fileWriter1.flush();
////			
		
//		Passive x= new ManaJump();
//		Passive y=new OffCards();
//		Passive p=new AlchemistsStone();
//		Passive s=new BandofScarabs();
//		Passive q=new Caltrops();
//		Passive w=new CapturedFlag();
//		Passive t=new Entrenchment();
//		Passive f=new GlyphofWarding();
//		Passive g=new GrommashsArmguards();
//		Passive c=new Stargazing();
//		
//		ArrayList<Passive > al=new ArrayList<>();
//		al.add(c);
//		al.add(g);
//		al.add(q);
//		al.add(s);
//		al.add(p);
//		al.add(f);
//		al.add(t);
//		al.add(w);
			


//CLI s=new CLI();

	}

}
