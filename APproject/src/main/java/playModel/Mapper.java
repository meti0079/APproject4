package playModel;

import java.awt.TextArea;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import Cardspackage.Card;
import Cardspackage.Weapon;
import client.grapic.PlayPanel;
import game.AbstractAdapter;
import game.Deck;
import game.DeckReader;
import game.ExportHeroPower;
import game.ExportHeroVisitor;
import game.ExportPassives;
import game.ExportVisitor;
import game.Logger;
import hero.Mage;
import hero.heroPower.HeroPower;
import passives.Passive;
import server.ServerMain;
import server.User;

public class Mapper {

	private ExportPassives pas=new ExportPassives();
	private ArrayList<Passive> allPassives;
	private DeckReader deckReader;
	private ExportHeroVisitor heroV=new ExportHeroVisitor();
	private ExportVisitor visitor;
	private ExportHeroPower heroVisitor=new ExportHeroPower();
	private User user1;
	private User user2;
	
	
	
	public Mapper(String state, User user1, User user2) throws Exception {
		this.user1=user1;
		this.user2=user2;
		visitor=new ExportVisitor(this,state,user1, user2);
		allPassives=new ArrayList<>();
		readFromFile();
		readPassiveFile();
	}
	public void setBackGroundCard(PlayerModel p) {
		for (int i=0;i<7;i++) { 
			if(p.getBattleGroundCard().get(i) != null) {
				p.getBattleGroundCard().get(i).setUsedToAttack(false);
			}
		}			
	}
	public void nextTurn(PlayerModel p, PlayerModel enemy) {
		handleHeroSpecialPower(p, enemy, 0, 0, 1);
		checkPassive( p, enemy, null);
		setBackGroundCard(p);
		p.checkCard(enemy, visitor, this);
		enemy.checkCard(p, visitor, this);
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
				handleHeroSpecialPower(p, enemy, 0, 0, 2);
				p.getBattleGroundCard().get(x+1).setUsedToAttack(true);
				String se=p.getName()+"  summon  "+ s.get_Name()+"\n";
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
				handleHeroSpecialPower(p, enemy, 0, 0, 2);
				p.getBattleGroundCard().get(x).setUsedToAttack(true);
				String se=p.getName()+"  summon  "+ s.get_Name()+"\n";
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
		try {
			Logger.getinsist().log(p.getName(), p.getName(), "change card"+card.get_Name()+"---> "+p.getHand().get(p.getHand().size()-1));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void checkPassive(PlayerModel me , PlayerModel enemy, Card x) {
		if(me.getPassive()!= null)
			me.getPassive().accept(pas, me, enemy, x);
	}
	public boolean addTobattleground(Card s,int x,int y, PlayerModel me, PlayerModel enemy, TextArea textArea) throws Exception {

		if(s.get_Mana()<=me.getCurrentgem()) {
			if(s.getType().equalsIgnoreCase("minion")) {
				if(y<700-(220*me.getTurn()) && y>480-(220*me.getTurn())) {
					s.accept(visitor,findTarget(x, y, me, enemy), me, enemy, this);
					if(me.getBattleGroundCard().get((x-200)/160) == null ) {
						calleBattleAccept(me, enemy, s);
						me.getBattleGroundCard().remove((x-200)/160);
						me.getBattleGroundCard().add((x-200)/160,s.copy());
						checkPassive( me, enemy, me.getBattleGroundCard().get((x-200)/160));
						checkPassive( enemy, me, me.getBattleGroundCard().get((x-200)/160));
						checkQuestDone(me.getBattleGroundCard().get((x-200)/160), me);
						handleHeroSpecialPower(me, enemy, x, y, 2);
						me.getHand().remove(s);
						me.getBattleGroundCard().get((x-200)/160).setUsedToAttack(true);
						String se=me.getName()+"  summon  "+ s.get_Name()+"\n";
						textArea.append(se);
						me.setCurrentgem(me.getCurrentgem()-s.get_Mana());
						me.checkCard(enemy, visitor, this);
						return true;
					}else if(betweencards(me,((x-200)/160) , s, textArea, enemy)) {
						me.checkCard(enemy, visitor, this);
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
				if(s.accept(visitor, findTarget(x, y, me, enemy), me, enemy, this)) {
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
				Logger.getinsist().log(p.getName(), "" , p.getName()+"  played  "+ weapon.get_Name());
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
	private void calleBattleAccept(PlayerModel p , PlayerModel enemy, Card x) {
		for (int i = 0; i < 7; i++) {
			if(p.getBattleGroundCard().get(i) != null)
				p.getBattleGroundCard().get(i).accept(visitor, x, p, enemy, this);
		}
		for (int i = 0; i < 7; i++) {
			if(enemy.getBattleGroundCard().get(i) != null)
				enemy.getBattleGroundCard().get(i).accept(visitor, x, p, enemy, this);
		}
	}
	private void calleHandAccept(PlayerModel p , PlayerModel enemy) {
		for (int i = 0; i < 7; i++) {
			if(p.getBattleGroundCard().get(i) != null)
				p.getBattleGroundCard().get(i).accept(visitor, null, p, enemy, this);
		}
		for (int i = 0; i < 7; i++) {
			if(enemy.getBattleGroundCard().get(i) != null)
				enemy.getBattleGroundCard().get(i).accept(visitor, null, p, enemy, this);
		}
	}
	public void addToHand(PlayerModel p, PlayerModel enemy,String state) {
		int x=0;
		calleHandAccept(p, enemy);
		if(state.equalsIgnoreCase("enemy") || state.equalsIgnoreCase("computer")  ) {			
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
				handleHeroSpecialPower(p, enemy,  0, 0, 1);
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



	public void readDeck(PlayerModel me,PlayerModel enemy,String state, User user1,User user2) {
		if (state.equalsIgnoreCase("enemy")) {
			if(me.getDecksize()==0 ) {
				me.setDeck((ArrayList<Card>) user1.getPlayer().get_mydeck().clone());
				me.setDecksize(me.getDeck().size());
				JOptionPane.showMessageDialog(null, "player1 : Deck update");	
			}
			if(enemy.getDecksize()==0 ) {
				enemy.setDeck((ArrayList<Card>) user1.getEnemy().getEnemyDeck().getDeck().clone());
				enemy.setDecksize(enemy.getDeck().size());
				JOptionPane.showMessageDialog(null, "player2 : Deck update");	
			}
		}else if(state.equalsIgnoreCase("deckreader")){
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
		}else if(state.equalsIgnoreCase("online")) {
			if(me.getDecksize()==0 ) {
				me.setDeck((ArrayList<Card>) user1.getPlayer().get_mydeck().clone());
				me.setDecksize(me.getDeck().size());
				JOptionPane.showMessageDialog(null, "player1 : Deck update");	
			}
			if(enemy.getDecksize()==0 ) {
				enemy.setDeck((ArrayList<Card>) user2.getPlayer().get_mydeck().clone());
				enemy.setDecksize(enemy.getDeck().size());
				JOptionPane.showMessageDialog(null, "player2 : Deck update");	
			}
		}
	}
	public PlayerModel readMe(String state, User user) throws Exception {
		if(state.equals("training") ||state.equals("computer")) {
			PlayerModel x=new PlayerModel(user.getPlayer().getMyDeck(), 0, user.getPlayer().get_name());
			return x;
		}else if(state.equals("deckreader")) {
			Deck s=new  Deck();
			s.setHeroDeck(new Mage());
			s.setDeck(deckReader.cardFactory("friend"));
			PlayerModel x= new PlayerModel(s, 0,user.getPlayer().get_name());
			return x;
		}else {
			return null;
		}
	}
	private void readFromFile() throws FileNotFoundException {
		File f1=new File("C:\\Users\\MohammadMehdi\\git\\ApProject3\\ApProject3\\APproject\\src\\main\\deckreader.json");
		Scanner s=new Scanner(f1);
		String se="";
		while(s.hasNext()) {
			se+=s.nextLine(); 
		}
		s.close();
		Gson gson=new GsonBuilder().setPrettyPrinting().create();
		deckReader=gson.fromJson(se, DeckReader.class);	
	}
	public PlayerModel readEnemy(String state, User user) throws Exception {
		if(state.equals("training")||state.equals("computer")) {
			PlayerModel x=new PlayerModel(user.getEnemy().getEnemyDeck(), 1,"enemy");
			x.setPreviosgem(0);
			return x;
		}else if(state.equals("deckreader")) {
			Deck s=new  Deck();
			s.setHeroDeck(new Mage());
			s.setDeck(deckReader.cardFactory("enemy"));
			PlayerModel x= new PlayerModel(s, 1, "enemy");
			x.setPreviosgem(0);
			return x;
		}else {
			return null;
		}	
	}
	public void addUse(Card s, String state, User user) {
		if(state.equalsIgnoreCase("deckreader") ||state.equalsIgnoreCase("online") )
			user.getPlayer().SpecialCard(s.get_Name()).addUse();
	}
	public boolean isFinished(PlayerModel me, PlayerModel enemy,int turn, String text) {
		try {
			if(enemy.getHero().get_HP()<=0  || me.getHero().get_HP()<=0||turn==1) {
				if(enemy.getHero().get_HP()> me.getHero().get_HP()) {
					text+=enemy.getName()+" win the game !!!!!!";
					String message1="PLAYERROR>>"+enemy.getName()+" WON !!!! #";
					ServerMain.WriteMessage(message1, user2.getAddress());
					ServerMain.WriteMessage(message1, user1.getAddress());
					Logger.getinsist().log(user2.getPlayer().get_name(), enemy.getName()+" won the match", "");
				}else {
					text+=me.getName()+"  win the game !!!!!!";
					String message1="PLAYERROR>>"+me.getName()+" WON !!!! #";
					ServerMain.WriteMessage(message1, user2.getAddress());
					ServerMain.WriteMessage(message1, user1.getAddress());
					Logger.getinsist().log(user1.getPlayer().get_name(), user1.getPlayer().get_name()+"  won the match", "");
					if(user1.getGameState().equalsIgnoreCase("online")) {
						user1.getPlayer().getMyDeck().addWin();
						user2.getPlayer().getMyDeck().addWin();						
						user1.getPlayer().getMyDeck().addUsethisDeck();
						user2.getPlayer().getMyDeck().addUsethisDeck();
					}
				}
				if(user1.getGameState().equalsIgnoreCase("online") || user1.getGameState().equalsIgnoreCase("deckreader")) {
					user1.getPlayer().addPlays();				
					user2.getPlayer().addPlays();				
				}
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
	public boolean handleAttack(PlayerModel me , PlayerModel enemy, int x, int y, Card card) {
		return card.accept(visitor, findTarget(x, y, me, enemy), me, enemy, this);
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
	public boolean handleHeroPower(PlayerModel me, PlayerModel enemy, int x, int y, HeroPower heropower) {
		if(heropower.accept(heroVisitor, findTarget(x, y, me, enemy), me, enemy,this)) {
			heropower.setUse(heropower.getUse()+1);			
			if(heropower.getMaxUse()==heropower.getUse()) {
				heropower.setUsed(true);				
			}
			me.setCurrentgem(me.getCurrentgem()-heropower.getMana());
			return true;
		}
		return false;
	}
	public boolean handleHeroSpecialPower(PlayerModel me, PlayerModel enemy, int x, int y, int d) {
			me.getHero().accept(heroV, me, d, (Card) findTarget(x, y, me, enemy));
			return true;
	}
}