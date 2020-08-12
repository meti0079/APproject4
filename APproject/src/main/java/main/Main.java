
package main;

import javax.swing.SwingUtilities;

import server.cardspackage.Card;
import server.cardspackage.Minions.Locust;
import server.cardspackage.Minions.MechanicalYeti;
import server.cardspackage.Minions.Sheep;
import server.cardspackage.Minions.SleepyDragon;
import server.cardspackage.Minions.WaxElemental;
import server.gameModel.AbstractAdapter;
import server.passives.ManaJump;
import server.passives.OffCards;
import server.passives.Passive;

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

import client.grapic.MainFrame;

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
//		gsonBilder.registerTypeAdapter(Passive.class, new AbstractAdapter<Passive>());
//		gsonBilder.setPrettyPrinting();
//		Gson gson=gsonBilder.create();
////		Card x= new MechanicalYeti();
//////		
//		for (Card  cards : all) {
//Passive x= new ManaJump();
//Passive y=new OffCards();
//		FileWriter fileWriter = new FileWriter("E:\\2\\"+x.getName()+".json");
//			gson.toJson(x, new TypeToken<Passive>(){}.getType(), fileWriter);
//			fileWriter.flush();
//			FileWriter fileWriter1 = new FileWriter("E:\\2\\"+y.getName()+".json");
//			gson.toJson(y, new TypeToken<Passive>(){}.getType(), fileWriter1);
//			fileWriter1.flush();
//////			
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
