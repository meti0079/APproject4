package server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import client.model.Card;
import gameModel.requestAndREsponse.GameNeed;
import server.gameModel.AbstractAdapter;
import server.gameModel.Logger;
import server.hero.Heros;
import server.hero.heroPower.HeroPower;
import server.passives.Passive;
import server.playModel.Mapper;
import server.playModel.PlayerModel;

public class Game {
	User user1;
	User user2;
	PlayerModel me;
	PlayerModel enemy;
	Mapper mapper;
	int round=60;
	private Logger log;
	String text;
	Gson gson;
	boolean showPassive=true;
	Clock clock;
	ArrayList<User > watcher=new ArrayList<>();
	public Game(User user) {
		try {

			initialGson();
			log=Logger.getinsist();
			user1=user;
			user2=user;
			mapper=new Mapper(user1.getGameState(),user,user);
			me=mapper.readMe(user.getGameState(),user);
			enemy=mapper.readEnemy(user.getGameState(), user);
			clock=new Clock(this);
			clock.start();
			addToHand(1);
			addToHand(1);
			addToHand(1);
			addToHand(0);
			addToHand(0);
			addToHand(0);
			sendGameNeed();
			String messString="CHANGEPANEL>>PLAYEPANEL#";
			ServerMain.WriteMessage(messString, user1.getAddress());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Game(User user1, User user2, Mapper mapper) {
		try {
			initialGson();
			log=Logger.getinsist();
			this.user1=user1;
			this.user2=user2;
//			new OneShot(user1.getGameState(), user1, user2);
//			new Mapper(user1.getGameState(), user1, user2);
			this.mapper=mapper;	
			me=mapper.readMe(user1.getGameState(),user1);
			enemy=mapper.readEnemy(user2.getGameState(), user2);
			clock=new Clock(this);
			clock.start();
			addToHand(1);
			addToHand(1);
			addToHand(1);
			addToHand(0);
			addToHand(0);
			addToHand(0);
			String messString="CHANGEPANEL>>PLAYEPANEL#";
			ServerMain.WriteMessage(messString, user1.getAddress());
			ServerMain.WriteMessage(messString, user2.getAddress());
			sendGameNeed();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void chekConnection(int i) {
		if(i==0) {
			clock.setUser1LastConnection(System.nanoTime());
		}else {
			clock.setUser2LastConnection(System.nanoTime());
		}
	}

	public void attack(int i, String cardName, int x, int y) {
		chekConnection(i);
		if(user1.getGameState().equals("training")) {
			try {
				if(round%2==0) {
					if(mapper.handleAttack(me, enemy,x,y,findCard(cardName, me.getBattleGroundCard())) ||  mapper.handleAttack(me, enemy,x,y,me.getWeapon())) {
						String 	ss=me.getName()+"     played   "+cardName+"\n";
						Logger.getinsist().log(me.getName(), "", ss);
						if(me.getWeapon()!=null && cardName.equalsIgnoreCase(me.getWeapon().get_Name())) {
							me.getWeapon().setUsedToAttack(true);
						}else {
							if(findCard(cardName, me.getBattleGroundCard())!=null)
								findCard(cardName, me.getBattleGroundCard()).setUsedToAttack(true);
						}
						text+=ss;
						sendGameNeed();
					}else {
						String message= "ATTACKERROR>> cant attack!!!!!!!! #";
						ServerMain.WriteMessage(message, user1.getAddress());
					}
				}else {
					if(mapper.handleAttack(enemy, me,x,y,findCard(cardName, enemy.getBattleGroundCard()))|| mapper.handleAttack(enemy, me,x,y,enemy.getWeapon())) {
						String 	ss=enemy.getName()+"     played   "+cardName+"\n";
						Logger.getinsist().log(enemy.getName(), "", ss);
						if(enemy.getWeapon()!=null && cardName.equalsIgnoreCase(enemy.getWeapon().get_Name())) {
							enemy.getWeapon().setUsedToAttack(true);
						}else{
							if(findCard(cardName, enemy.getBattleGroundCard())!=null)
								findCard(cardName, enemy.getBattleGroundCard()).setUsedToAttack(true);
						}
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
		}else
			if(round%2==i) {
				try {
					if(i==0) {
						if(mapper.handleAttack(me, enemy,x,y,findCard(cardName,me.getBattleGroundCard())) || mapper.handleAttack(me, enemy,x,y,me.getWeapon())) {
							String 	ss=me.getName()+"     played   "+cardName+"\n";
							Logger.getinsist().log(me.getName(), "", ss);
							if(me.getWeapon()!=null && cardName.equalsIgnoreCase(me.getWeapon().get_Name())) {
								me.getWeapon().setUsedToAttack(true);
							}else {
								if(findCard(cardName, me.getBattleGroundCard())!=null)
									findCard(cardName, me.getBattleGroundCard()).setUsedToAttack(true);
							}
							text+=ss;
							sendGameNeed();
						}else {
							String message= "ATTACKERROR>> cant attack!!!!!!!! #";
							ServerMain.WriteMessage(message, user1.getAddress());
						}
					}else {
						if(mapper.handleAttack(enemy, me,x,y,findCard(cardName, enemy.getBattleGroundCard())) || mapper.handleAttack(enemy, me,x,y,enemy.getWeapon())) {
							String 	ss=enemy.getName()+"     played   "+cardName+"\n";
							Logger.getinsist().log(enemy.getName(), "", ss);
							if(enemy.getWeapon()!=null && cardName.equalsIgnoreCase(enemy.getWeapon().get_Name())) {
								enemy.getWeapon().setUsedToAttack(true);
							}else {
								if(findCard(cardName, enemy.getBattleGroundCard())!=null)
									findCard(cardName, enemy.getBattleGroundCard()).setUsedToAttack(true);
							}
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
		sendGameNeed();
	}

	private void sendGameNeed() {
		ArrayList<String> pas=passives();
		mapper.checkQuest(enemy);
		if(me.getWeapon()!=null && me.getWeapon().getDurability()==0)
			me.setWeapon(null);
		if(enemy.getWeapon()!=null && enemy.getWeapon().getDurability()==0)
			enemy.setWeapon(null);
		if(user1.getGameState().equals("online") || user1.getGameState().contains("deckreader")) {
			GameNeed gameNeed1=new GameNeed(me.getDecksize(), enemy.getDecksize(),makeClientCards( me.getHand()),new ArrayList<>(),
					makeClientCards(me.getBattleGroundCard()), makeClientCards(enemy.getBattleGroundCard()),
					mekeACard(me.getWeapon())
					,mekeACard(enemy.getWeapon()),
					me.getHero(),
					enemy.getHero(), enemy.getHand().size(), round, me.getCurrentgem(), enemy.getCurrentgem(),
					text, pas, enemy.getName(),me.getTurn(),	mapper.checkQuest(me),	mapper.checkQuest(me)?me.getQuest().getHave():0,	mapper.checkQuest(me)?me.getQuest().getMission():0,mapper.checkQuest(enemy),mapper.checkQuest(enemy)?enemy.getQuest().getHave():0,mapper.checkQuest(enemy)?enemy.getQuest().getMission():0, user1.getBackBattleGround(),user1.getBackCard());

			GameNeed gameNeed2=new GameNeed(enemy.getDecksize(), me.getDecksize(),makeClientCards( enemy.getHand()),new ArrayList<>(),
					makeClientCards(enemy.getBattleGroundCard()), makeClientCards(me.getBattleGroundCard()),
					mekeACard(enemy.getWeapon()),
					mekeACard(me.getWeapon())
					, enemy.getHero(),
					me.getHero(), me.getHand().size(), round, enemy.getCurrentgem(), me.getCurrentgem(),
					text, pas, me.getName(),enemy.getTurn(),mapper.checkQuest(enemy),mapper.checkQuest(enemy)?enemy.getQuest().getHave():0,mapper.checkQuest(enemy)?enemy.getQuest().getMission():0
							,mapper.checkQuest(me),	mapper.checkQuest(me)?me.getQuest().getHave():0,mapper.checkQuest(me)?me.getQuest().getMission():0,user2.getBackBattleGround(),user2.getBackCard());
			String message1="SETGAMENEED>>"+gson.toJson(gameNeed1)+"#";
			String message2="SETGAMENEED>>"+gson.toJson(gameNeed2)+"#";
			try {
				ServerMain.WriteMessage(message1, user1.getAddress());
				ServerMain.WriteMessage(message2, user2.getAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			try {
				if(round%2==0) {
					GameNeed gameNeed=new GameNeed(me.getDecksize(), enemy.getDecksize(),makeClientCards( me.getHand()),new ArrayList<>(),
							makeClientCards(me.getBattleGroundCard()), makeClientCards(enemy.getBattleGroundCard()),
							mekeACard(me.getWeapon())
							,mekeACard(enemy.getWeapon()), me.getHero(),	enemy.getHero(), enemy.getHand().size(), round, me.getCurrentgem(), enemy.getCurrentgem(),
							text, passives(), enemy.getName(),me.getTurn(),mapper.checkQuest(me),	mapper.checkQuest(me)?me.getQuest().getHave():0,mapper.checkQuest(me)?me.getQuest().getMission():0,
									mapper.checkQuest(enemy),mapper.checkQuest(enemy)?enemy.getQuest().getHave():0,mapper.checkQuest(enemy)?enemy.getQuest().getMission():0,user1.getBackBattleGround(),user1.getBackCard());
					String message1="SETGAMENEED>>"+gson.toJson(gameNeed)+"#";				
					ServerMain.WriteMessage(message1, user1.getAddress());
				}else {
					GameNeed gameNeed=new GameNeed(enemy.getDecksize(), me.getDecksize(),makeClientCards( enemy.getHand()),new ArrayList<>(),
							makeClientCards(enemy.getBattleGroundCard()), makeClientCards(me.getBattleGroundCard()),
							mekeACard(enemy.getWeapon())
							,mekeACard(me.getWeapon()), enemy.getHero(),	me.getHero(), me.getHand().size(), round, enemy.getCurrentgem(), me.getCurrentgem(),
							text, passives(), me.getName(),enemy.getTurn(),mapper.checkQuest(enemy),mapper.checkQuest(enemy)?enemy.getQuest().getHave():0,mapper.checkQuest(enemy)?enemy.getQuest().getMission():0
									,mapper.checkQuest(me),	mapper.checkQuest(me)?me.getQuest().getHave():0,mapper.checkQuest(me)?me.getQuest().getMission():0,user1.getBackBattleGround(),user1.getBackCard());
					String message1="SETGAMENEED>>"+gson.toJson(gameNeed)+"#";				
					ServerMain.WriteMessage(message1, user1.getAddress());	
				}
			} catch (IOException e) {	e.printStackTrace();
			}
		}
		for (User user : watcher) {
			GameNeed gameNeed=new GameNeed(me.getDecksize(), enemy.getDecksize(),makeClientCards( me.getHand()),makeClientCards(enemy.getHand()),
					makeClientCards(me.getBattleGroundCard()), makeClientCards(enemy.getBattleGroundCard()),
					mekeACard(me.getWeapon())
					,mekeACard(enemy.getWeapon()), me.getHero(),	enemy.getHero(), enemy.getHand().size(), round, me.getCurrentgem(), enemy.getCurrentgem(),
					text, passives(), "&&&&",me.getTurn(),mapper.checkQuest(me),	mapper.checkQuest(me)?me.getQuest().getHave():0,mapper.checkQuest(me)?me.getQuest().getMission():0,
							mapper.checkQuest(enemy),mapper.checkQuest(enemy)?enemy.getQuest().getHave():0,mapper.checkQuest(enemy)?enemy.getQuest().getMission():0,user1.getBackBattleGround(),user1.getBackCard());
			String message1="SETGAMENEED>>"+gson.toJson(gameNeed)+"#";				
			try {
				ServerMain.WriteMessage(message1, user.getAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	private ArrayList<String> passives(){
		ArrayList<String> passives=new ArrayList<>(); 
		ArrayList<Integer > a=new ArrayList<>();
		if(round==60 &&showPassive)
			while (a.size()<3) {
				int x=(new Random().nextInt(9));
				if(!a.contains(x)&&x>=0&&x<=9) {
					passives.add(mapper.getAllPassives().get(x).getName());
					a.add(x);					
				}
			}
		showPassive=false;
		return passives;
	}
	protected void nextTurn(int turn) throws Exception {
		chekConnection(turn);
		clock.setTime(0);
		if(!user1.getGameState().equals("training")) {
			if(round%2==turn) {
				if(round==60) {
					firstRound();
					return;
				}
				try {
					mapper.isFinished(me, enemy,round,text);
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
		}else {
			if(round==60) {
				firstRound();
				return;
			}
			try {
				mapper.isFinished(me, enemy,round,text);
			} catch (Exception e) {}
			mapper.readDeck(me, enemy,user1.getGameState(),user1,user2);
			if(round%2==1) {
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

	private void addToHand(int turn) {
		chekConnection(turn);
		try {
			mapper.readDeck(me, enemy,user1.getGameState(),user1,user2);
			if(turn==0) {
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

	public void addToBattleground(int i, String cardName, int x, int y) {
		chekConnection(i);
		try {
			if(user1.getGameState().equals("training")) {
				if(round%2==0) {
					if(mapper.addTobattleground(findCard(cardName, me.getHand()), x, y, me, enemy,text)) {
						log.log(me.getName(), me.getName(), "summon card : "+cardName);
						mapper.addUse(cardName,user1.getGameState(),user1);
						sendGameNeed();
					}else {
						String message= "ATTACKERROR>> cant summon this card!!!!!!!! #";
						ServerMain.WriteMessage(message, user1.getAddress());
					}
				}else {
					if (mapper.addTobattleground(findCard(cardName, enemy.getHand()), x, y, enemy, me,text)) { 
						mapper.addUse(cardName,user1.getGameState(),user2);
						log.log(enemy.getName(), enemy.getName(), "summon card : "+cardName);
						sendGameNeed();
					}else {			
						String message= "ATTACKERROR>> cant summon this card!!!!!!!! #";
						ServerMain.WriteMessage(message, user2.getAddress());						
					}
				}
			}else	
				if(round%2==i) {
					if(i==0) {
						if(mapper.addTobattleground(findCard(cardName, me.getHand()), x, y, me, enemy,text)) {
							log.log(me.getName(), me.getName(), "summon card : "+cardName);
							mapper.addUse(cardName,user1.getGameState(),user1);
							sendGameNeed();
						}else {
							String message= "ATTACKERROR>> cant summon this card!!!!!!!! #";
							ServerMain.WriteMessage(message, user1.getAddress());
						}
					}else {
						if (mapper.addTobattleground(findCard(cardName, enemy.getHand()), x, y, enemy, me,text)) { 
							mapper.addUse(cardName,user1.getGameState(),user2);
							log.log(enemy.getName(), enemy.getName(), "summon card : "+cardName);
							sendGameNeed();
						}else {			
							String message= "ATTACKERROR>> cant summon this card!!!!!!!! #";
							ServerMain.WriteMessage(message, user2.getAddress());						
						}
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void handleHeroPower(int i, int x, int y) {
		chekConnection(i);
		if(user1.getGameState().equals("training")) {
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
			sendGameNeed();	
		}else		
			if(round%2==i) {
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
				sendGameNeed();
			}
	}

	public void setPassive(int i, String passiveName) {
		chekConnection(i);
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
	public void changCard(int i, String cardName) {
		chekConnection(i);
		if (i==0 && me.getChanges()<3) {
			mapper.changeCartAtFirst(me, findCard(cardName, me.getHand()));
			sendGameNeed();
		}else if(i==1&& enemy.getChanges()<3){
			mapper.changeCartAtFirst(enemy, findCard(cardName, enemy.getHand()));
			sendGameNeed();
		}
	}

	private ArrayList<Card> makeClientCards(ArrayList<server.cardspackage.Card> cards){
		ArrayList<Card> c=new ArrayList<>();
		for (server.cardspackage.Card card : cards) {
			c.add(new Card(card.get_Mana(),card.get_Name(),card.get_Rarity(),card.get_Class(),card.getDescription(),card.getType(),card.getAttack(), card.getHp(),card.isRush(),card.getUsedToAttack()));
		}
		return c;
	}
	private Card mekeACard(server.cardspackage.Card card) {
		if(card ==null) {
			return null;
		}else {
			return new Card(card.get_Mana(),card.get_Name(),card.get_Rarity(),card.get_Class(),card.getDescription(),card.getType(),card.getAttack(), card.getHp(),card.isRush(),card.getUsedToAttack());

		}
	}
	private LinkedList<Card> makeClientCards(LinkedList<server.cardspackage.Card> cards){
		LinkedList<Card> c=new LinkedList<>();
		for (int i=0;i<7;i++) {
			if(cards.get(i)==null) {
				c.add(null);
			}else
				c.add(new Card(cards.get(i).get_Mana(),cards.get(i).get_Name(),cards.get(i).get_Rarity(),cards.get(i).get_Class(),cards.get(i).getDescription(),cards.get(i).getType(),cards.get(i).getAttack(), cards.get(i).getHp(),cards.get(i).isRush(),cards.get(i).getUsedToAttack()));
		}
		return c;
	}
	private server.cardspackage.Card findCard(String name, ArrayList<server.cardspackage.Card> cards) {
		for (server.cardspackage.Card card : cards) {
			if(card.get_Name().equals(name))
				return card;
		}
		return null;
	}
	private server.cardspackage.Card findCard(String name, LinkedList<server.cardspackage.Card> cards) {
		for (server.cardspackage.Card card : cards) {
			if(card!=null)
				if(card.get_Name().equals(name))
					return card;
		}
		return null;
	}
	private void firstRound() {
		round--;
		mapper.manaSet(round,me, enemy);
		sendGameNeed();
	}
	private void player1Turn() {
		mapper.nextTurn(me, enemy);
		addToHand(0);
	}	
	private void player2Turn() {
		mapper.nextTurn(enemy, me);
		addToHand(1);
	}
	private void initialGson() {
		GsonBuilder builder=new	GsonBuilder().registerTypeAdapter(Heros.class, new AbstractAdapter<Heros>());
		builder.serializeSpecialFloatingPointValues();
		builder.registerTypeAdapter(HeroPower.class, new AbstractAdapter<HeroPower>());
		gson= builder.create();
	}
	public User getUser1() {
		return user1;
	}
	public User getUser2() {
		return user2;
	}
	public void exit(int i) {
		if(i==0) {
			setUserWiner(user1, user2);
		}else {
			setUserWiner(user2, user1);			
		}
		user1.getPlayer().addPlays();				
		user2.getPlayer().addPlays();							
		try {
			String message1="SETPLAYER>>"+new Gson().toJson(new client.model.User(user1.getPlayer().get_name(), user1.getPlayer().getTocken(), user1.getPlayer().gem, user1.getPlayer().getCup()))+"#";
			ServerMain.WriteMessage(message1, user1.getAddress());
			String message2="SETPLAYER>>"+new Gson().toJson(new client.model.User(user2.getPlayer().get_name(), user2.getPlayer().getTocken(), user2.getPlayer().gem, user2.getPlayer().getCup()))+"#";
			ServerMain.WriteMessage(message2, user2.getAddress());
		} catch (IOException e) {e.printStackTrace();}	
	}
	void setUserWiner(User user1, User user2) {
		if(user1.getGameState().equalsIgnoreCase("online")) {
			user2.getPlayer().getMyDeck().addWin();
			user2.getPlayer().setCup(user2.getPlayer().getCup()+30);
			user1.getPlayer().setCup(user1.getPlayer().getCup()-30);
			user2.getPlayer().getMyDeck().setCup(1);
			user1.getPlayer().getMyDeck().setCup(-1);
			user1.getPlayer().getMyDeck().addUsethisDeck();
			user2.getPlayer().getMyDeck().addUsethisDeck();
		}else if(user1.getGameState().contains("deckreader")) {
			user2.getPlayer().setCup(user2.getPlayer().getCup()+30);
			user1.getPlayer().setCup(user1.getPlayer().getCup()-30);						
		}
		String message1="PLAYERROR>>"+me.getName()+" left !!!! #";
		try {
			String message3="CHANGEPANEL>>MENU#";
			ServerMain.WriteMessage(message3, user1.getAddress());
			ServerMain.WriteMessage(message1, user2.getAddress());
			Logger.getinsist().log(user1.getPlayer().get_name(), user1.getPlayer().get_name()+"  left the match", "");
		} catch (IOException e) {e.printStackTrace();}
	}
	public void addWatcher(User x) {
		watcher.add(x);
		sendWatcher();
		try {
			String messString="CHANGEPANEL>>PLAYEPANEL#";
			ServerMain.WriteMessage(messString, x.getAddress());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void sendWatcher() {
		ArrayList<String> ss=new ArrayList<>();
		for (User user : watcher) {
			ss.add(user.getPlayer().get_name());
		}
		try {
			String message="SETWATCHER>>"+gson.toJson(ss)+"#";
			ServerMain.WriteMessage(message, user1.getAddress());
			ServerMain.WriteMessage(message, user2.getAddress());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<User> getWatcher() {
		return watcher;
	}

}
