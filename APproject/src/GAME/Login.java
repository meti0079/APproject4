package GAME;

import java.io.File;
import java.util.Random;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import Cardspackage.Cards;
import Cardspackage.Minion;
import Cardspackage.Spell;
import Cardspackage.Weapon;
import hero.Heros;
import hero.Hunter;
import hero.Mage;
import hero.Priest;
import hero.Rouge;
import hero.Warlock;

public class Login  {
	public Login(Players player) throws Exception {
		Random ran=new Random();
		Heros first=new Mage();
		Heros s1=new Rouge();
		Heros s2=new Warlock();
		Heros s3=new Hunter();
		Heros s4=new Priest();
		player.getMyStore().getBuyHero().add(s1);
		player.getMyStore().getBuyHero().add(s4);
		player.getMyStore().getBuyHero().add(s3);
		player.getMyStore().getBuyHero().add(s2);
		player.addHero(first);
		Decks dec=new Decks();
		dec.setName("first deck");
		dec.setHeroDeck(first.getname());
		player.adddeck(dec);
		player.setMyDeck(dec);
		Decks enemy=new Decks();
		enemy.setHeroDeck("mage");
		enemy.setName("enemy deck");
		player.setEnemyDeck(enemy);
		
		
		
		Gson j=new GsonBuilder().setPrettyPrinting().create();
		File fe=new File(System.getProperty("user.dir")+"\\src\\CArds\\spells");

		File[] dir=fe.listFiles();
		if(dir!=null) {
			int i=0;
			for(File ch:dir) {
				Scanner sca=new Scanner(ch);
				String t1="";
				while(sca.hasNext()) {
					t1+=sca.nextLine();
				}
				Spell m=j.fromJson(t1, Spell.class);
				if(m.get_Class().equals("Mage")  && i<=1) {
					player.get_mydeck().add(m);
					player.add_card(m);	
					i++;

				}else {
					player.getMyStore().getBuyCard().add(m);
				}
			}

		}



		File fa=new File(System.getProperty("user.dir")+"\\src\\CArds\\weapons");

		File[] dirr=fa.listFiles();
		if(dirr!=null) {
			for(File ch:dirr) {
				Scanner sca=new Scanner(ch);
				String t1="";
				while(sca.hasNext()) {
					t1+=sca.nextLine();
				}
				Weapon m=j.fromJson(t1, Weapon.class);
				if(m.get_Class().equals("Mage") ) {
					player.get_mydeck().add(m);
					player.add_card(m);
				}else {
					player.getMyStore().getBuyCard().add(m);

				}
			}
		}

		File f2=new File(System.getProperty("user.dir")+"\\src\\CArds\\minions");
		File[] dirr2=f2.listFiles();
		if(dirr2!=null) {
			for(File ch:dirr2) {
				Scanner sca=new Scanner(ch);
				String t1="";
				while(sca.hasNext()) {
					t1+=sca.nextLine();
				}
				Minion m=j.fromJson(t1, Minion.class);
				player.getMyStore().getBuyCard().add(m);

			}




			while(player.get_mydeck().size()<15) {	
				int shans=ran.nextInt(player.getMyStore().getBuyCard().size()-1);
				Cards m=player.getMyStore().getBuyCard().get(shans);
				if(m.get_Class().equalsIgnoreCase("Mage") && player.get_mydeck().size()<=15) {
					player.get_mydeck().add(m);
					player.add_card(m);
					player.getMyStore().getBuyCard().remove(shans);
					continue;
				}
				if(!m.get_Class().equals("Mage")
						&&  !m.get_Class().equals("Rouge")
						&&  !m.get_Class().equals("Warlock") 
						&&  !m.get_Class().equals("Hunter") 
						&&  !m.get_Class().equals("Priest") 
						&&  player.get_mydeck().size()<=15){
					player.get_mydeck().add(m);
					player.add_card(m);
					player.getMyStore().getBuyCard().remove(shans);
				}
			}
		}






	}



}