package gameModel.requestAndREsponse;

import java.util.ArrayList;
import java.util.LinkedList;
import client.model.Card;
import server.hero.Heros;

public class GameNeed {
	private int myDeck, enemDeck,enemyHandsize,turnremind,myMana,enemyMana,myquesthave,enemyquesthave,myquestmission,enemyquestmission,myturn;
	private ArrayList<Card> myHand;
	private ArrayList<Card> enemyHand;
	private LinkedList<Card> myBattlrground;
	private LinkedList<Card> enemyBattleground;
	private Card myWeapon,enemyWeapon;
	private Heros myHero,enemyHero;
	private String text;
	private ArrayList<String > passive;
	private boolean myquest,enemyQuest;
	private String battleground, backCard,enemyName;
	public GameNeed(int myDeck, int enemDeck, ArrayList<Card> myHand, ArrayList<Card> enemyHand,
			LinkedList<Card> myBattlrground, LinkedList<Card> enemyBattleground, Card myWeapon, Card enemyWeapon,
			Heros myHero, Heros enemyHero, int enemyHandsize, int turnremind, int myMana, int enemyMana, String text,
			ArrayList<String> passive, String enemyName, int myturn,boolean my,int h,int m,boolean enem,int he, int me, String battleground, String backCard) {
		this.myDeck = myDeck;
		this.myturn=myturn;
		this.backCard=backCard;
		this.battleground=battleground;
		this.enemDeck = enemDeck;
		this.myHand = myHand;
		this.enemyHand = enemyHand;
		this.myBattlrground = myBattlrground;
		this.enemyBattleground = enemyBattleground;
		this.myWeapon = myWeapon;
		this.enemyWeapon = enemyWeapon;
		this.myHero = myHero;
		this.enemyHero = enemyHero;
		this.enemyHandsize = enemyHandsize;
		this.turnremind = turnremind;
		this.myMana = myMana;
		this.enemyMana = enemyMana;
		this.text = text;
		this.passive = passive;
		this.enemyName = enemyName;
		this.myquest=my;
		this.enemyQuest=enem;
		this.myquestmission=m;
		this.myquesthave=h;
		this.enemyquesthave=he;
		this.enemyquestmission=me;	
	}
	public boolean isMyquest() {
		return myquest;
	}
	public boolean isEnemyQuest() {
		return enemyQuest;
	}
	public int getMyquesthave() {
		return myquesthave;
	}
	public int getEnemyquesthave() {
		return enemyquesthave;
	}
	public int getMyquestmission() {
		return myquestmission;
	}
	public int getEnemyquestmission() {
		return enemyquestmission;
	}
	public String getBattleground() {
		return battleground;
	}
	public String getBackCard() {
		return backCard;
	}
	public int getMyturn() {
		return myturn;
	}
	public int getMyDeck() {
		return myDeck;
	}
	public int getEnemDeck() {
		return enemDeck;
	}
	public ArrayList<Card> getMyHand() {
		return myHand;
	}
	public ArrayList<Card> getEnemyHand() {
		return enemyHand;
	}
	public LinkedList<Card> getMyBattlrground() {
		return myBattlrground;
	}
	public LinkedList<Card> getEnemyBattleground() {
		return enemyBattleground;
	}
	public Card getMyWeapon() {
		return myWeapon;
	}
	public Card getEnemyWeapon() {
		return enemyWeapon;
	}
	public Heros getMyHero() {
		return myHero;
	}
	public Heros getEnemyHero() {
		return enemyHero;
	}
	public int getEnemyHandsize() {
		return enemyHandsize;
	}
	public int getTurnremind() {
		return turnremind;
	}
	public int getMyMana() {
		return myMana;
	}
	public int getEnemyMana() {
		return enemyMana;
	}
	public String getText() {
		return text;
	}
	public ArrayList<String> getPassive() {
		return passive;
	}
	public String getEnemyName() {
		return enemyName;
	}

}