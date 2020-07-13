
package main;

import javax.swing.SwingUtilities;
import grapic.MainFrame;
//import passives.AlchemistsStone;
//import passives.BandofScarabs;
//import passives.Caltrops;
//import passives.CapturedFlag;
//import passives.Entrenchment;
//import passives.GlyphofWarding;
//import passives.GrommashsArmguards;
//import passives.Passive;
//import passives.Stargazing;
//
import java.io.FileWriter;
//import java.security.spec.PSSParameterSpec;
//import java.util.ArrayList;
//
//import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
//
import Cardspackage.Card;
//import Cardspackage.Minions.Locust;
////
//import Cardspackage.Minions.BigGameHunter;
//import Cardspackage.Minions.BluegillWarrior;
//import Cardspackage.Minions.ChillwindYeti;
//import Cardspackage.Minions.CurioCollector;
//import Cardspackage.Minions.Dreadscale;
//import Cardspackage.Minions.Gruul;
//import Cardspackage.Minions.HighPriestAmet;
//import Cardspackage.Minions.KronxDragonhoof;
//import Cardspackage.Minions.LeperGnome;
//import Cardspackage.Minions.MurlocRaider;
//import Cardspackage.Minions.MurlocWarleader;
//import Cardspackage.Minions.OasisSnapjaw;
//import Cardspackage.Minions.Sandbinder;
//import Cardspackage.Minions.Sathrovarr;
//import Cardspackage.Minions.SeaGiant;
//import Cardspackage.Minions.SecurityRover;
//import Cardspackage.Minions.Shieldbearer;
//import Cardspackage.Minions.SwampKingDred;
//import Cardspackage.Minions.TheBlackKnight;
//import Cardspackage.Minions.ThrallmarFarseer;
//import Cardspackage.Minions.TombWarden;
//import Cardspackage.Spells.ArcaneShot;
//import Cardspackage.Spells.Backstab;
//import Cardspackage.Spells.BookofSpecters;
//import Cardspackage.Spells.FriendlySmith;
//import Cardspackage.Spells.HolySmite;
//import Cardspackage.Spells.LearnDraconic;
//import Cardspackage.Spells.PharaohBlessing;
//import Cardspackage.Spells.Polymorph;
//import Cardspackage.Spells.Sprint;
//import Cardspackage.Spells.StrengthinNumbers;
//import Cardspackage.Spells.Swarmoflocusts;
//import Cardspackage.Spells.gift;
//import Cardspackage.Weapons.BattleAxe;
//import Cardspackage.Weapons.BloodFury;
//import Cardspackage.Weapons.HeavyAxe;
//import GAME.AbstractAdapter;
//
//import Cardspackage.Card;
//import Cardspackage.Spells.AstralRift;
import Cardspackage.Minions.Locust;
import Cardspackage.Minions.Sheep;
import Cardspackage.Minions.SleepyDragon;
import Cardspackage.Minions.WaxElemental;
import GAME.AbstractAdapter;

public class Main {
	public static void main(String[] args) throws Exception {
//		ArrayList<Card> all=new ArrayList<>();
//		Card a=new ArcaneShot(); 
//		all.add(a);
//		ArcaneShot ar=new ArcaneShot();
//		all.add(ar);
//		Backstab b=new Backstab();
//		all.add(b);
//		BattleAxe ba=new BattleAxe();
//		all.add(ba);
//		BigGameHunter bi=new BigGameHunter();
//		all.add(bi);
//		BloodFury bl=new BloodFury();
//		all.add(bl);
//		BluegillWarrior b1=new BluegillWarrior();
//		all.add(b1);
//		BookofSpecters b2=new BookofSpecters();
//		all.add(b2);
//		ChillwindYeti c=new ChillwindYeti();
//		all.add(c);
//		CurioCollector cu=new CurioCollector();
//		all.add(cu);
//		Dreadscale d=new Dreadscale();
//		all.add(d);
//		FriendlySmith f=new FriendlySmith();
//		all.add(f);
//		gift g=new gift(); 
//		all.add(g);
//		Gruul gu=new Gruul();
//		all.add(gu);
//		HeavyAxe h=new HeavyAxe();
//		all.add(h);
//		HighPriestAmet h1=new HighPriestAmet();
//		all.add(h1);
//		HolySmite ho=new HolySmite();
//		all.add(ho);
//		KronxDragonhoof k=new KronxDragonhoof();
//		all.add(k);
//		LearnDraconic l=new LearnDraconic();
//		all.add(l);
//		LeperGnome lp=new LeperGnome();
//		all.add(lp);
//		MurlocRaider m=new MurlocRaider();
//		all.add(m);
//		MurlocWarleader mw=new MurlocWarleader();
//		all.add(mw);
//		OasisSnapjaw o=new OasisSnapjaw();
//		all.add(o);
//		PharaohBlessing p=new PharaohBlessing();
//		all.add(p);
//		Polymorph pp=new Polymorph();
//		all.add(pp);
//		Sandbinder s=new Sandbinder();
//		all.add(s);
//		Sathrovarr sa=new Sathrovarr();
//		all.add(sa);
//		SeaGiant se=new SeaGiant();
//		all.add(se);
//		SecurityRover sc=new SecurityRover();
//		all.add(sc);
//		Shieldbearer sh=new Shieldbearer();
//		all.add(sh);
//		Sprint sp=new Sprint();
//		all.add(sp);
//		StrengthinNumbers st=new StrengthinNumbers();
//		all.add(st);
//		SwampKingDred sw=new SwampKingDred();
//		all.add(sw);
//		Swarmoflocusts sq=new Swarmoflocusts();
//		all.add(sq);
//		TheBlackKnight th=new TheBlackKnight();
//		all.add(th);
//		ThrallmarFarseer t=new ThrallmarFarseer();
//		all.add(t);
//		Card to= new TombWarden();
//		all.add(to);
////		
//		GsonBuilder gsonBilder=new GsonBuilder();
//		gsonBilder.registerTypeAdapter(Card.class, new AbstractAdapter<Card>());
//		gsonBilder.setPrettyPrinting();
//		Gson gson=gsonBilder.create();
//		Card x= new SleepyDragon();
////		
//		for (Card  cards : all) {
//			
//			FileWriter fileWriter = new FileWriter("E:\\2\\"+x.get_Name()+".json");
//			gson.toJson(x, new TypeToken<Card>(){}.getType(), fileWriter);
//			fileWriter.flush();
////			
//		}
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
//			
		//	Cards cards=new AstralRift();
//		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new MainFrame();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});



//CLI s=new CLI();

	}

}
