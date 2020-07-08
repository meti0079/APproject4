package playModel;

import java.awt.Panel;
import java.awt.TextArea;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Cardspackage.Cards;
import Cardspackage.Minion;
import Cardspackage.Weapon;
import GAME.DeckReader;
import GAME.Decks;
import GAME.Gamestate;
import GAME.Logger;
import grapic.PlayPanel;

public class Mapper {



	private int previousGem=0;
	private DeckReader deckReader;
	private static Mapper map;
	private Gamestate game;
	private Mapper() throws Exception {
		game=Gamestate.getinsist();
		readFromFile();
	}
	public static Mapper getinsist() throws Exception {
		if (map==null) {
			map=new Mapper();
		}
		return map;
	}

	public void nextTurn(Player p) {
		for (Cards cards : p.getBattleGroundCard()) { 
			if(cards != null)
				cards.setUsedToAttack(false);
		}
		for (Cards cards : p.getHand()) 
			cards.setUsedToAttack(false);
		if(p.getWeapon()!=null)
			p.getWeapon().setUsedToAttack(false);
		PlayPanel.i=0;
	}
	public boolean betweencards(Player p,int x, Cards s, TextArea textArea) {
		for(int i=x+1;i<7;i++) {
			if(p.getBattleGroundCard().get(i)==null) {
				p.getBattleGroundCard().remove(i);
				p.getHand().remove(s);
				p.getBattleGroundCard().add(x+1,copy(s));
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
				p.getBattleGroundCard().add(x,copy(s));
				p.getBattleGroundCard().get(x).setUsedToAttack(true);
				String se=game.getPlayer().get_name()+"  summon  "+ s.get_Name()+"\n";
				textArea.append(se);
				p.setCurrentgem(p.getCurrentgem()-s.get_Mana());
				return true;
			}
		}
		return false;
	}




	public boolean addTobattleground(Cards s,int x,int y, Player p, TextArea textArea) {
		if(s.get_Mana()<=p.getCurrentgem()) {
			if(s.getType().equalsIgnoreCase("minion")) {
				if(y<700-(220*p.getTurn()) && y>480-(220*p.getTurn())) {
					if(p.getBattleGroundCard().get((x-200)/160) == null ) {
						p.getHand().remove(s);
						p.getBattleGroundCard().remove((x-200)/160);
						p.getBattleGroundCard().add((x-200)/160,copy(s));
						p.getBattleGroundCard().get((x-200)/160).setUsedToAttack(true);
						String se=p.getName()+"  summon  "+ s.get_Name()+"\n";
						textArea.append(se);
						p.setCurrentgem(p.getCurrentgem()-s.get_Mana());
						return true;
					}else if(betweencards(p,((x-200)/160) , s, textArea)) {
						return true;
					}else {						
						JOptionPane.showConfirmDialog(null, "your battleground if full play a card or click next turn","cant plan",JOptionPane.CLOSED_OPTION);
						return false;							
					}
				}else {
					return false;
				}
			}else if(s.getType().equalsIgnoreCase("Spell")) {
				String se=p.getName()+"  played  "+ s.get_Name()+"\n";
				textArea.append(se);
				p.getHand().remove(s);
			}else {
				String se=p.getName()+"  Summon  "+ s.get_Name()+"\n";
				textArea.append(se);
				p.setWeapon((Weapon) copyWeapon((Weapon) s));
				p.getWeapon().setUsedToAttack(true);
				p.getHand().remove(s);
			}
			p.setCurrentgem(p.getCurrentgem()-s.get_Mana());;
			return true;
		}else {
			return false;
		}
	}
	public void manaSet(Player me, Player enemy) {
		if(PlayPanel.getRoundGame()%2==me.getTurn()) {
			if(previousGem==10) {
				me.setCurrentgem(10);
				return;
			}
			previousGem++;
			me.setCurrentgem(previousGem);;
		}else {
			enemy.setCurrentgem(previousGem);;			
		}
	}

	protected Cards copy(Cards card) {
		Minion x=(Minion)card;
		Minion s=new Minion();
		s.setAttack(x.getAttack());
		s.Set_Class(x.getClass()+"");
		s.setHp(x.getHp());
		s.Set_Mana(card.get_Mana());
		s.Set_Name(card.get_Name());
		s.Set_Rarity(card.get_Rarity());
		s.setBattlecry(card.isBattlecry());
		s.setDeathrattle(card.isDeathrattle());
		s.setDescription(card.getDescription());
		s.setDivineShield(card.isDivineShield());
		s.setQuest(card.isQuest());
		s.setRush(card.isRush());
		s.setTaunt(card.isTaunt());
		s.setWindfury(card.isWindfury());
		return s;
	}
	protected Cards copyWeapon(Weapon card) {
		Weapon s=new Weapon();
		s.setAttack(card.getAttack());
		s.Set_Class(card.getClass()+"");
		s.Set_Mana(card.get_Mana());
		s.Set_Name(card.get_Name());
		s.Set_Rarity(card.get_Rarity());
		s.setBattlecry(card.isBattlecry());
		s.setDeathrattle(card.isDeathrattle());
		s.setDescription(card.getDescription());
		s.setDivineShield(card.isDivineShield());
		s.setQuest(card.isQuest());
		s.setRush(card.isRush());
		s.setTaunt(card.isTaunt());
		s.setWindfury(card.isWindfury());
		s.setDurability(card.getDurability());
		return s;
	}

	public void addToHand(Player p) {
		int x=0;	
		if(game.getState().equalsIgnoreCase("enemy") ||game.getState().equalsIgnoreCase("computer")  ) {
			if(p.getDecksize()==0) {
				x=0;
			}else {
				x=new Random().nextInt(p.getDeck().size());						
			}
			p.setDecksize(p.getDecksize()-1);
			if(p.getHand().size()<10) {
				p.AddToHand(p.getDeck().get(Math.abs(x)));
				p.getDeck().remove(Math.abs(x));
			}else {
				p.getDeck().remove(Math.abs(x));	
			}
		}else {
			x=0;
			p.setDecksize(p.getDecksize()-1);
			if(p.getHand().size()<10) {
				p.AddToHand(p.getDeck().get(x));
				p.getDeck().remove(x);
			}else {
				p.getDeck().remove(x);	
			}
		}
	}



	public void readDeck(Player me,Player enemy) {
		if (game.getState().equalsIgnoreCase("enemy")) {
			if(me.getDecksize()==0 ) {
				me.setDeck((ArrayList<Cards>) game.getPlayer().get_mydeck().clone());
				me.setDecksize(me.getDeck().size());
				JOptionPane.showMessageDialog(null, "player1 : Deck update");	
			}
			if(enemy.getDecksize()==0 ) {
				enemy.setDeck((ArrayList<Cards>) game.getEnemy().getEnemyDeck().getDeck().clone());
				enemy.setDecksize(enemy.getDeck().size());
				JOptionPane.showMessageDialog(null, "player2 : Deck update");	
			}
		}else if(game.getState().equalsIgnoreCase("Deck")){
			if(me.getDecksize()==0 ) {
				me.setDeck((ArrayList<Cards>) deckReader.cardFactory("friend").clone());
				me.setDecksize(me.getDeck().size());
				JOptionPane.showMessageDialog(null, "player1 : Deck update");	
			}
			if(enemy.getDecksize()==0 ) {
				enemy.setDeck((ArrayList<Cards>) deckReader.cardFactory("enemy").clone());
				enemy.setDecksize(enemy.getDeck().size());
				JOptionPane.showMessageDialog(null, "player2 : Deck update");	
			}
		}else {
		}
	}

	public int getPreviousGem() {
		return previousGem;
	}

	public void setPreviousGem(int previousGem) {
		this.previousGem = previousGem;
	}
	public Player readMe() throws Exception {
		if(game.getState().equals("enemy")) {
			Player x=new Player(game.getPlayer().getMyDeck(), 0, game.getPlayer().get_name());
			return x;
		}else if(game.getState().equals("Deck")) {
			Decks s=new  Decks();
			s.setHeroDeck("mage");
			s.setDeck(deckReader.cardFactory("friend"));
			Player x= new Player(s, 0,game.getPlayer().get_name());
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
	public Player readEnemy() throws Exception {
		if(game.getState().equals("enemy")) {
			Player x=new Player(game.getEnemy().getEnemyDeck(), 1,"enemy");
			return x;
		}else if(game.getState().equals("Deck")) {
			Decks s=new  Decks();
			s.setHeroDeck("mage");
			s.setDeck(deckReader.cardFactory("enemy"));
			Player x= new Player(s, 1, "enemy");
			return x;
		}else {
			return null;
		}	
	}
	public void addUse(Cards s) {
		if(game.getState().equalsIgnoreCase("enemy"))
			game.getPlayer().SpecialCard(s.get_Name()).addUse();
	}
	public boolean isFinished(Player me, Player enemy, TextArea textArea) {
		try {
			if(enemy.getHero().get_HP()==0  || me.getHero().get_HP()==0||PlayPanel.getRoundGame()==1) {
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
					game.setPlayPassive(null);				
				}
				return true;
			}
		}catch (Exception e) {}
		return false;
	}
}
