package server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import com.google.gson.Gson;
import client.model.Card;
import game.Gamestate;
import game.Logger;
import gameModel.requestAndREsponse.gameNeed;
import passives.Passive;
import playModel.Mapper;
import playModel.PlayerModel;

public class Game {
	User user1;
	User user2;
	PlayerModel me;
	PlayerModel enemy;
	Mapper mapper;
	int round=60;
	private Logger log;
	String text;
	public Game(User user) {
		try {
			log=Logger.getinsist();
			mapper=new Mapper(user1.getGameState(),user,user);
			user1=user;
			user2=user;
			me=mapper.readMe(user.getGameState(),user);
			enemy=mapper.readEnemy(user.getGameState(), user);
			addToHand(1);
			addToHand(1);
			addToHand(1);
			addToHand(2);
			addToHand(2);
			addToHand(2);
			sendGameNeed();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Game(User user1, User user2) {
		try {
			log=Logger.getinsist();
			mapper=new Mapper(user1.getGameState(),user1,user2);
			this.user1=user1;
			this.user2=user2;
			me=mapper.readMe(user1.getGameState(),user1);
			enemy=mapper.readEnemy(user2.getGameState(), user2);
			addToHand(1);
			addToHand(1);
			addToHand(1);
			addToHand(2);
			addToHand(2);
			addToHand(2);
			sendGameNeed();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void attack(int i, String cardName, int x, int y) {
		if(round%2==i) {
			try {
				if(i==0) {
					if(mapper.handleAttack(me, enemy,x,y,findCard(cardName, me.getBattleGroundCard()) )) {
						String 	ss=me.getName()+"     played   "+findCard(cardName, me.getBattleGroundCard()).get_Name()+"\n";
						Logger.getinsist().log(me.getName(), "", ss);
						findCard(cardName, me.getBattleGroundCard()).setUsedToAttack(true);
						text+=ss;
						sendGameNeed();
					}else {
						String message= "PLAYERROR>> cant attack!!!!!!!! #";
						ServerMain.WriteMessage(message, user1.getAddress());
					}
				}else {
					if(mapper.handleAttack(enemy, me,x,y,findCard(cardName, enemy.getBattleGroundCard()) )) {
						String 	ss=enemy.getName()+"     played   "+findCard(cardName, enemy.getBattleGroundCard()).get_Name()+"\n";
						Logger.getinsist().log(enemy.getName(), "", ss);
						findCard(cardName, enemy.getBattleGroundCard()).setUsedToAttack(true);
						text+=ss;
						sendGameNeed();
					}else {
						String message= "ATTACKERROR>> cant attack!!!!!!!! #";
						ServerMain.WriteMessage(message, user2.getAddress());
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}









	private void sendGameNeed() {
		mapper.checkQuest(me);
		mapper.checkQuest(enemy);
		if(me.getWeapon()!=null && me.getWeapon().getDurability()==0)
			me.setWeapon(null);
		if(enemy.getWeapon()!=null && enemy.getWeapon().getDurability()==0)
			enemy.setWeapon(null);
		if(user1.getGameState().equals("online") || user1.getGameState().equals("deckreader")) {
			gameNeed gameNeed1=new gameNeed(me.getDecksize(), enemy.getDecksize(),makeClientCards( me.getHand()),new ArrayList<>(),
					makeClientCards(me.getBattleGroundCard()), makeClientCards(enemy.getBattleGroundCard()),
					new Card(me.getWeapon().get_Mana(), me.getWeapon().get_Name(), me.getWeapon().get_Rarity(), me.getWeapon().get_Class(), me.getWeapon().getDescription(), me.getWeapon().getType(), me.getWeapon().getAttack(), me.getWeapon().getHp(), me.getWeapon().isRush(), me.getWeapon().getUsedToAttack())
					, new Card(enemy.getWeapon().get_Mana(), enemy.getWeapon().get_Name(), enemy.getWeapon().get_Rarity(), enemy.getWeapon().get_Class(), enemy.getWeapon().getDescription(), enemy.getWeapon().getType(), enemy.getWeapon().getAttack(), enemy.getWeapon().getHp(), enemy.getWeapon().isRush(), enemy.getWeapon().getUsedToAttack())
					, me.getHero(),
					enemy.getHero(), enemy.getHand().size(), round, me.getCurrentgem(), enemy.getCurrentgem(),
					text, passives(), enemy.getName(),me.getTurn(),me.getQuest(),enemy.getQuest(), user1.getBackBattleGround(),user1.getBackCard());

			gameNeed gameNeed2=new gameNeed(enemy.getDecksize(), me.getDecksize(),makeClientCards( enemy.getHand()),new ArrayList<>(),
					makeClientCards(enemy.getBattleGroundCard()), makeClientCards(me.getBattleGroundCard()),
					new Card(enemy.getWeapon().get_Mana(), enemy.getWeapon().get_Name(), enemy.getWeapon().get_Rarity(), enemy.getWeapon().get_Class(), enemy.getWeapon().getDescription(), enemy.getWeapon().getType(), enemy.getWeapon().getAttack(), enemy.getWeapon().getHp(), enemy.getWeapon().isRush(), enemy.getWeapon().getUsedToAttack())
					, new Card(me.getWeapon().get_Mana(), me.getWeapon().get_Name(), me.getWeapon().get_Rarity(), me.getWeapon().get_Class(),me.getWeapon().getDescription(), me.getWeapon().getType(), me.getWeapon().getAttack(), me.getWeapon().getHp(), me.getWeapon().isRush(), me.getWeapon().getUsedToAttack())
					, enemy.getHero(),
					me.getHero(), me.getHand().size(), round, enemy.getCurrentgem(), me.getCurrentgem(),
					text, passives(), me.getName(),enemy.getTurn(),enemy.getQuest(),me.getQuest(),user2.getBackBattleGround(),user2.getBackCard());

			String message1="SETGAMENEED>>"+new Gson().toJson(gameNeed1)+"#";
			String message2="SETGAMENEED>>"+new Gson().toJson(gameNeed2)+"#";
			try {
				ServerMain.WriteMessage(message1, user1.getAddress());
				ServerMain.WriteMessage(message2, user1.getAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}

		}else {
			gameNeed gameNeed=new gameNeed(me.getDecksize(), enemy.getDecksize(),makeClientCards( me.getHand()),makeClientCards( enemy.getHand()),
					makeClientCards(me.getBattleGroundCard()), makeClientCards(enemy.getBattleGroundCard()),
					new Card(me.getWeapon().get_Mana(), me.getWeapon().get_Name(), me.getWeapon().get_Rarity(), me.getWeapon().get_Class(), me.getWeapon().getDescription(), me.getWeapon().getType(), me.getWeapon().getAttack(), me.getWeapon().getHp(), me.getWeapon().isRush(), me.getWeapon().getUsedToAttack())
					, new Card(enemy.getWeapon().get_Mana(), enemy.getWeapon().get_Name(), enemy.getWeapon().get_Rarity(), enemy.getWeapon().get_Class(), enemy.getWeapon().getDescription(), enemy.getWeapon().getType(), enemy.getWeapon().getAttack(), enemy.getWeapon().getHp(), enemy.getWeapon().isRush(), enemy.getWeapon().getUsedToAttack())
					, me.getHero(),	enemy.getHero(), enemy.getHand().size(), round, me.getCurrentgem(), enemy.getCurrentgem(),
					text, passives(), enemy.getName(),me.getTurn(),me.getQuest(),enemy.getQuest(),user1.getBackBattleGround(),user1.getBackCard());
			String message1="SETGAMENEED>>"+new Gson().toJson(gameNeed)+"#";
			try {
				ServerMain.WriteMessage(message1, user1.getAddress());
			} catch (IOException e) {	e.printStackTrace();
			}
		}
	}
	private ArrayList<String> passives(){
		ArrayList<String> passives=new ArrayList<>(); 
		ArrayList<Integer > a=new ArrayList<>();
		if(round==60  || round==59)
			while (a.size()!=3) {
				int x=(new Random().nextInt(9));
				if(!a.contains(x)&&x>=0&&x<=9)
					passives.add(mapper.getAllPassives().get(x).getName());
				a.add(x);
			}
		return passives;
	}
	protected void nextTurn(int turn) throws Exception {
		if(round%2==turn) {
			if(round==60) {
				firstRound();
				return;
			}
			try {
				mapper.isFinished(me, enemy,turn,text);
			} catch (Exception e) {}
			mapper.readDeck(me, enemy,user1.getGameState(),user1,user2);
			if(turn==1) {
				log.log(user1.getPlayer().get_name(), "clicked end turn ", "");
				player1Turn();
			}else {
				log.log(user2.getPlayer().get_name(), "clicked end turn ", "");
				player2Turn();
			}
			round--;
			mapper.manaSet(round,me, enemy);
			sendGameNeed();
		}
	}
	private void firstRound() {
		round--;
		mapper.manaSet(round,me, enemy);
	}
	private void player1Turn() {
		mapper.nextTurn(me, enemy);
		addToHand(me.getTurn()+1);
	}	
	private void player2Turn() {
		mapper.nextTurn(enemy, me);
		addToHand(enemy.getTurn()+1);
	}
	private void addToHand(int turn) {	
		if(round%2==turn) {
			try {
				mapper.readDeck(me, enemy,user1.getGameState(),user1,user2);
				if(turn==1) {
					mapper.addToHand(me, enemy,user1.getGameState());
					log.log( me.getName(), me.getName(), "summon card : "+me.getHand().get(me.getHand().size()-1).get_Name());
				}else {
					mapper.addToHand(enemy, me,user1.getGameState());
					log.log(enemy.getName(), enemy.getName(), "sdraw card : "+enemy.getHand().get(enemy.getHand().size()-1).get_Name());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}










































	public void handleHeroPower(int i, int x, int y) {
		if(i==me.getTurn()) {
			if(me.getCurrentgem()>=me.getHero().getHero_power().getMana())
				if(mapper.handleHeroPower(me, enemy, x, y, me.getHero().getHero_power())) {
					text+=me.getName()+"     played    hero power\n";
				}					
		}else {
			if(enemy.getCurrentgem()>=enemy.getHero().getHero_power().getMana())
				if(mapper.handleHeroPower(enemy, me, x, y, enemy.getHero().getHero_power())) {
					text+=enemy.getName()+"     played    hero power\n";
				}								
		}
	}
	public User getUser1() {
		return user1;
	}
	public void setUser1(User user1) {
		this.user1 = user1;
	}
	public User getUser2() {
		return user2;
	}
	public void setUser2(User user2) {
		this.user2 = user2;
	}
	public PlayerModel getMe() {
		return me;
	}
	public void setMe(PlayerModel me) {
		this.me = me;
	}
	public PlayerModel getEnemy() {
		return enemy;
	}
	public void setEnemy(PlayerModel enemy) {
		this.enemy = enemy;
	}
	public Mapper getMapper() {
		return mapper;
	}
	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	private ArrayList<Card> makeClientCards(ArrayList<Cardspackage.Card> cards){
		ArrayList<Card> c=new ArrayList<>();
		for (Cardspackage.Card card : cards) {
			c.add(new Card(card.get_Mana(),card.get_Name(),card.get_Rarity(),card.get_Class(),card.getDescription(),card.getType(),card.getAttack(), card.getHp(),card.isRush(),card.getUsedToAttack()));
		}
		return c;
	}
	private LinkedList<Card> makeClientCards(LinkedList<Cardspackage.Card> cards){
		LinkedList<Card> c=new LinkedList<>();
		for (int i=0;i<7;i++) {
			if(cards.get(i)==null) {
				c.add(null);
			}else
				c.add(new Card(cards.get(i).get_Mana(),cards.get(i).get_Name(),cards.get(i).get_Rarity(),cards.get(i).get_Class(),cards.get(i).getDescription(),cards.get(i).getType(),cards.get(i).getAttack(), cards.get(i).getHp(),cards.get(i).isRush(),cards.get(i).getUsedToAttack()));
		}
		return c;
	}
	public void setPassive(int i, String passiveName) {
		if(round%2==i) {

			if(i==0) {
				for (Passive passive : mapper.getAllPassives()) {
					if(passive.getName().equalsIgnoreCase(passiveName))
						me.setPassive(passive);
				}
			}else if(i==1) {
				for (Passive passive : mapper.getAllPassives()) {
					if(passive.getName().equalsIgnoreCase(passiveName))
						enemy.setPassive(passive);
				}			
			}
			sendGameNeed();
		}
	}
	private Cardspackage.Card findCard(String name, ArrayList<Cardspackage.Card> cards) {
		for (Cardspackage.Card card : cards) {
			if(card.get_Name().equals(name))
				return card;
		}
		return null;
	}
	private Cardspackage.Card findCard(String name, LinkedList<Cardspackage.Card> cards) {
		for (Cardspackage.Card card : cards) {
			if(card!=null)
				if(card.get_Name().equals(name))
					return card;
		}
		return null;
	}
	public void changCard(int i, String cardName) {
		if(round%2==i) {

			if (i==0 && me.getChanges()<3) {
				mapper.changeCartAtFirst(me, findCard(cardName, me.getHand()));
				sendGameNeed();
			}else if(i==1&& enemy.getChanges()<3){
				mapper.changeCartAtFirst(enemy, findCard(cardName, enemy.getHand()));
				sendGameNeed();
			}
		}
	}
	public void addToBattleground(int i, String cardName, int x, int y) {
		try {
			if(round%2==i) {
				if(i==0) {
					if(mapper.addTobattleground(findCard(cardName, me.getHand()), x, y, me, enemy,text)) {
						log.log(me.getName(), me.getName(), "summon card : "+cardName);
						mapper.addUse(findCard(cardName, me.getBattleGroundCard()),user1.getGameState(),user1);
						sendGameNeed();
					}else {
						String message= "ATTACKERROR>> cant summon this card!!!!!!!! #";
						ServerMain.WriteMessage(message, user1.getAddress());
					}
				}else {
					if (mapper.addTobattleground(findCard(cardName, enemy.getHand()), x, y, enemy, me,text)) { 
						mapper.addUse(findCard(cardName, enemy.getBattleGroundCard()),user1.getGameState(),user2);
						log.log(enemy.getName(), enemy.getName(), "summon card : "+cardName);
						sendGameNeed();
					}			
					String message= "ATTACKERROR>> cant summon this card!!!!!!!! #";
					ServerMain.WriteMessage(message, user2.getAddress());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
