package gameModel.requestAndREsponse;

import java.util.ArrayList;
import java.util.LinkedList;

import client.model.Card;
import hero.Heros;
import playModel.Quest;

public class GameNeed {
	int myDeck;
	int enemDeck;
	ArrayList<Card> myHand;
	ArrayList<Card> enemyHand;
	LinkedList<Card> myBattlrground;
	LinkedList<Card> enemyBattleground;
	Card myWeapon;
	Card enemyWeapon;
	Heros myHero;
	Heros enemyHero;
	int enemyHandsize;
	int turnremind;
	int myMana;
	int enemyMana;
	String text;
	ArrayList<String > passive;
	String enemyName;
	int myturn;
	Quest myquest;
	Quest enemyQuest;
	String battleground;
	String backCard;
	public GameNeed(int myDeck, int enemDeck, ArrayList<Card> myHand, ArrayList<Card> enemyHand,
			LinkedList<Card> myBattlrground, LinkedList<Card> enemyBattleground, Card myWeapon, Card enemyWeapon,
			Heros myHero, Heros enemyHero, int enemyHandsize, int turnremind, int myMana, int enemyMana, String text,
			ArrayList<String> passive, String enemyName, int myturn,Quest my,Quest enem, String battleground, String backCard) {
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
	}
	public Quest getMyquest() {
		return myquest;
	}
	public void setMyquest(Quest myquest) {
		this.myquest = myquest;
	}
	public String getBattleground() {
		return battleground;
	}
	public void setBattleground(String battleground) {
		this.battleground = battleground;
	}
	public String getBackCard() {
		return backCard;
	}
	public void setBackCard(String backCard) {
		this.backCard = backCard;
	}
	public Quest getEnemyQuest() {
		return enemyQuest;
	}
	public void setEnemyQuest(Quest enemyQuest) {
		this.enemyQuest = enemyQuest;
	}
	public int getMyturn() {
		return myturn;
	}
	public void setMyturn(int myturn) {
		this.myturn = myturn;
	}

	public int getMyDeck() {
		return myDeck;
	}
	public void setMyDeck(int myDeck) {
		this.myDeck = myDeck;
	}
	public int getEnemDeck() {
		return enemDeck;
	}
	public void setEnemDeck(int enemDeck) {
		this.enemDeck = enemDeck;
	}
	public ArrayList<Card> getMyHand() {
		return myHand;
	}
	public void setMyHand(ArrayList<Card> myHand) {
		this.myHand = myHand;
	}
	public ArrayList<Card> getEnemyHand() {
		return enemyHand;
	}
	public void setEnemyHand(ArrayList<Card> enemyHand) {
		this.enemyHand = enemyHand;
	}
	public LinkedList<Card> getMyBattlrground() {
		return myBattlrground;
	}
	public void setMyBattlrground(LinkedList<Card> myBattlrground) {
		this.myBattlrground = myBattlrground;
	}
	public LinkedList<Card> getEnemyBattleground() {
		return enemyBattleground;
	}
	public void setEnemyBattleground(LinkedList<Card> enemyBattleground) {
		this.enemyBattleground = enemyBattleground;
	}

	public Card getMyWeapon() {
		return myWeapon;
	}
	public void setMyWeapon(Card myWeapon) {
		this.myWeapon = myWeapon;
	}
	public Card getEnemyWeapon() {
		return enemyWeapon;
	}
	public void setEnemyWeapon(Card enemyWeapon) {
		this.enemyWeapon = enemyWeapon;
	}
	public Heros getMyHero() {
		return myHero;
	}
	public void setMyHero(Heros myHero) {
		this.myHero = myHero;
	}
	public Heros getEnemyHero() {
		return enemyHero;
	}
	public void setEnemyHero(Heros enemyHero) {
		this.enemyHero = enemyHero;
	}
	public int getEnemyHandsize() {
		return enemyHandsize;
	}
	public void setEnemyHandsize(int enemyHandsize) {
		this.enemyHandsize = enemyHandsize;
	}
	public int getTurnremind() {
		return turnremind;
	}
	public void setTurnremind(int turnremind) {
		this.turnremind = turnremind;
	}
	public int getMyMana() {
		return myMana;
	}
	public void setMyMana(int myMana) {
		this.myMana = myMana;
	}
	public int getEnemyMana() {
		return enemyMana;
	}
	public void setEnemyMana(int enemyMana) {
		this.enemyMana = enemyMana;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public ArrayList<String> getPassive() {
		return passive;
	}
	public void setPassive(ArrayList<String> passive) {
		this.passive = passive;
	}
	public String getEnemyName() {
		return enemyName;
	}
	public void setEnemyName(String enemyName) {
		this.enemyName = enemyName;
	}
}