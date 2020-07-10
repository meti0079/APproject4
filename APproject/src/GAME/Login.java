package GAME;

import java.io.File;
import java.io.FileNotFoundException;
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
	Gson gson;

	public Login(Players player, Enemy enem) throws Exception {
		initialGson();
		makeHero(player);
		makeDeck(player, player.get_myheros().get(0));
		makeEnemyDeck(enem);
		makeSpells(player);
		makeMinion(player);
		makeWeapon(player);
	}
	private void initialGson() {
		GsonBuilder gsonBilder=new GsonBuilder();
		gsonBilder.registerTypeAdapter(Cards.class, new AbstractAdapter<Cards>());
		gsonBilder.setPrettyPrinting();
		gson=gsonBilder.create();
	}
	private void makeWeapon(Players player) throws FileNotFoundException {
		File fa=new File(System.getProperty("user.dir")+"\\src\\CArds\\weapons");
		File[] dirr=fa.listFiles();
		if(dirr!=null) {
			for(File ch:dirr) {
				Cards m=gson.fromJson(readFileString(ch), Cards.class);
				if(m.get_Class().equals("Mage") ) {
					addToDeck(m, player);
				}else {
					player.getMyStore().getBuyCard().add(m);
				}
			}
		}		
	}

	private void makeMinion(Players player) throws FileNotFoundException {
		Random ran=new Random();
		File f2=new File(System.getProperty("user.dir")+"\\src\\CArds\\minions");
		File[] dirr2=f2.listFiles();
		if(dirr2!=null) {
			for(File ch:dirr2) {
				Cards m=gson.fromJson(readFileString(ch), Cards.class);
				player.getMyStore().getBuyCard().add(m);
			}
			while(player.get_mydeck().size()<14) {	
				int shans=ran.nextInt(player.getMyStore().getBuyCard().size()-1);
				Cards m=player.getMyStore().getBuyCard().get(shans);
				if(m.get_Class().equalsIgnoreCase("Mage") && player.get_mydeck().size()<=15) {
					addToDeck(m, player);
					player.getMyStore().getBuyCard().remove(shans);
					continue;
				}
				if(!m.get_Class().equals("Mage")
						&&  !m.get_Class().equals("Rouge")
						&&  !m.get_Class().equals("Warlock") 
						&&  !m.get_Class().equals("Hunter") 
						&&  !m.get_Class().equals("Priest") 
						&&  player.get_mydeck().size()<=15){
					addToDeck(m, player);
					player.getMyStore().getBuyCard().remove(shans);
				}
			}
		}		
	}

	private void makeHero(Players player) {
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
	}
	private void makeSpells(Players player) throws FileNotFoundException {	
		File fe=new File(System.getProperty("user.dir")+"\\src\\CArds\\spells");
		File[] dir=fe.listFiles();
		if(dir!=null) {
			int i=0;
			for(File ch:dir) {
				Cards m=gson.fromJson(readFileString(ch), Cards.class);
				if(m.get_Class().equals("Mage")  && i<=1) {
					addToDeck(m, player);
					i++;	
				}else {
					player.getMyStore().getBuyCard().add(m);
				}
			}
		}
	}
	private void makeDeck(Players player, Heros first) throws Exception {
		Decks dec=new Decks();
		dec.setName("first deck");
		dec.setHeroDeck(first.getname());
		player.adddeck(dec);
		player.setMyDeck(0);
	}
	private void makeEnemyDeck(Enemy en) throws Exception {
		Decks enemy=new Decks();
		enemy.setHeroDeck("mage");
		enemy.setName("enemy deck");
		en.setEnemyDeck(enemy);
	}
	private void addToDeck(Cards m, Players player) {
		player.get_mydeck().add(m);
		player.add_card(m);	
	}
	private String readFileString(File f) throws FileNotFoundException {
		Scanner sca=new Scanner(f);
		String t1="";
		while(sca.hasNext()) {
			t1+=sca.nextLine();
		}
		return t1;
	}
}