package server.gameModel;

import java.io.FileNotFoundException;
import java.security.SecureRandom;
import java.util.Random;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import server.cardspackage.Card;
import server.cardspackage.Minion;
import server.cardspackage.Spell;
import server.cardspackage.Weapon;
import server.hero.Heros;
import server.hero.Mage;
import server.hero.heroPower.HeroPower;

public class Login  {
	Gson gson;
	DataReader dataReader;
	public Login(Player player, Enemy enem) throws Exception {
		initialGson();
		dataReader=DataReader.initial();
		makeHero(player);
		makeDeck(player, player.get_myheros().get(0));
		makeEnemyDeck(enem);
		makeSpells(player);
		makeMinion(player);
		makeWeapon(player);
		player.setTocken(new SecureRandom().nextInt());
	}
	private void initialGson() {
		GsonBuilder gsonBilder=new GsonBuilder();
		gsonBilder.registerTypeAdapter(Card.class, new AbstractAdapter<Card>());
		gsonBilder.registerTypeAdapter(Heros.class, new AbstractAdapter<Heros>());
		gsonBilder.registerTypeAdapter(HeroPower.class, new AbstractAdapter<HeroPower>());
		gsonBilder.setPrettyPrinting();
		gson=gsonBilder.create();
	}
	private void makeWeapon(Player player) throws FileNotFoundException {
//		GsonBuilder gsonBilder=new GsonBuilder();
//		gsonBilder.registerTypeAdapter(Passive.class, new AbstractAdapter<Passive>());
//		gsonBilder.setPrettyPrinting();
//		Gson gson=gsonBilder.create();		
//		File f3=new File(System.getProperty("user.dir")+"\\src\\main\\java\\passives\\passiveFiles");
//		File[] dirr3=f3.listFiles();
//		if(dirr3!=null) {
//			for(File ch:dirr3) {
//				Scanner sca=new Scanner(ch);
//				String t1="";
//				while(sca.hasNext()) {
//					t1+=sca.nextLine();
//				}
//				Passive s= gson.fromJson(t1, Passive.class);
//				dataReader.save(s);
//				sca.close();
//			}
//		}
//		
//		File fa=new File(System.getProperty("user.dir")+"\\src\\main\\java\\CArds\\weapons");
//		File[] dirr=fa.listFiles();
//		if(dirr!=null) {
//			for(File ch:dirr) {
//				System.out.println(ch.getName());
//				Card m=gson.fromJson(readFileString(ch), Weapon.class);
//				if(m.get_Class().equals("Mage") ) {
//					addToDeck(m, player);
//				}else {
//					player.getMyStore().getBuyCard().add(m);
//				}
//			}
//		}	
		for (Card card : dataReader.loudAll(Weapon.class)) {
			
			if(card.get_Class().equals("Mage") ) {
				addToDeck(card, player);
			}else {
				player.getMyStore().getBuyCard().add(card);
			}
		}
//		Weapon m=new BattleAxe();
//		Weapon w=new HeavyAxe();
//		Weapon z=new BloodFury();
//		
//		
//		dataReader.save(w);
//		dataReader.save(z);
//				dataReader.save(m);
	}

	private void makeMinion(Player player) throws FileNotFoundException {
		Random ran=new Random();
//		File f2=new File(System.getProperty("user.dir")+"\\src\\main\\java\\CArds\\minions");
//		File[] dirr2=f2.listFiles();
//		if(dirr2!=null) {
//			for(File ch:dirr2) {
//				Card m=gson.fromJson(readFileString(ch), Card.class);
//				dataReader.save(m);
//				player.getMyStore().getBuyCard().add(m);
//			}
		for (Card card : dataReader.loudAll(Minion.class)) {
				player.getMyStore().getBuyCard().add(card);
			
		}
			while(player.get_mydeck().size()<14) {	
				int shans=ran.nextInt(player.getMyStore().getBuyCard().size()-1);
				Card m=player.getMyStore().getBuyCard().get(shans);
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
	

	private void makeHero(Player player) {
//		Heros first=new Mage();
//		Heros s1=new Rouge();
//		Heros s2=new Warlock();
//		Heros s3=new Hunter();
//		Heros s4=new Priest();
//		dataReader.save(first);
//		dataReader.save(s1);
//		dataReader.save(s2);
//		dataReader.save(s3);
//		dataReader.save(s4);
		for (Heros hero : dataReader.loudAll(Heros.class)) {
			if(hero.getName().equals("Mage")) {
				player.addHero(hero);				
			}else {
				player.getMyStore().getBuyHero().add(hero);				
			}
		}
//		player.getMyStore().getBuyHero().add(s4);
//		player.getMyStore().getBuyHero().add(s3);
//		player.getMyStore().getBuyHero().add(s2);
//		player.addHero(first);
	}
	private void makeSpells(Player player) throws FileNotFoundException {	
//		File fe=new File(System.getProperty("user.dir")+"\\src\\main\\java\\CArds\\spells");
//		File[] dir=fe.listFiles();
//		if(dir!=null) {
//			int i=0;
//			for(File ch:dir) {
//				Card m=gson.fromJson(readFileString(ch), Card.class);
//				dataReader.save(m);
//				if(m.get_Class().equals("Mage")  && i<=1) {
//					addToDeck(m, player);
//					i++;	
//				}else {
//					player.getMyStore().getBuyCard().add(m);
//				}
//			}
//		}
		int i=0;
		for (Card  m : dataReader.loudAll(Spell.class)) {
			if(m.get_Class().equals("Mage")  && i<=1) {
				addToDeck(m, player);
				i++;	
			}else {
				player.getMyStore().getBuyCard().add(m);
			}
		}	
	}
	private void makeDeck(Player player, Heros first) throws Exception {
		Deck dec=new Deck();
		dec.setName("first deck");
		dec.setHeroDeck(player,first.getname());
		player.adddeck(dec);
		player.setMyDeck(0);
	}
	private void makeEnemyDeck(Enemy en) throws Exception {
		Deck enemy=new Deck();
		enemy.setHeroDeck(new Mage());
		enemy.setName("enemy deck");
		en.setEnemyDeck(enemy);
	}
	private void addToDeck(Card m, Player player) {
		player.get_mydeck().add(m);
		player.add_card(m);	
	}
//	private String readFileString(File f) throws FileNotFoundException {
//		Scanner sca=new Scanner(f);
//		String t1="";
//		while(sca.hasNext()) {
//			t1+=sca.nextLine();
//		}
//		sca.close();
//		return t1;
//	}
}