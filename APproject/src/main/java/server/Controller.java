package server;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import client.model.Card;
import client.model.DeckInfo;
import game.AbstractAdapter;
import game.Deck;
import game.Enemy;
import game.FileReader;
import game.Logger;
import game.Login;
import game.Player;
import game.Rank;
import game.Store;
import gameModel.requestAndREsponse.AddCardToDeck;
import gameModel.requestAndREsponse.AttackRequest;
import gameModel.requestAndREsponse.ChangInDeckResponse;
import gameModel.requestAndREsponse.ChangeBattlegroundThem;
import gameModel.requestAndREsponse.ChatRequest;
import gameModel.requestAndREsponse.CollectionNeed;
import gameModel.requestAndREsponse.EditDeckRequest;
import gameModel.requestAndREsponse.HeroPowerRequest;
import gameModel.requestAndREsponse.Kickrequest;
import gameModel.requestAndREsponse.LoginAndSingUpRequest;
import gameModel.requestAndREsponse.NewDeck;
import gameModel.requestAndREsponse.NextTurnRequest;
import gameModel.requestAndREsponse.SaveAndExitRequest;
import gameModel.requestAndREsponse.SearchRequest;
import gameModel.requestAndREsponse.SellAndBuy;
import gameModel.requestAndREsponse.SetPassiveRequest;
import gameModel.requestAndREsponse.ShopNeeds;
import gameModel.requestAndREsponse.StartMatchRequest;
import gameModel.requestAndREsponse.StatosNeeds;
import gameModel.requestAndREsponse.changeCardRequest;
import hero.Heros;
import hero.heroPower.HeroPower;


public class Controller {
	public static ArrayList<User> clients= new ArrayList<User>();
	public static HashMap<Integer, User> online=new HashMap<>();
	public static ArrayList<User> deckReaderWaiting= new ArrayList<User>();
	public static ArrayList<User> onlineWaiting= new ArrayList<User>();
	public static ArrayList<Game> games=new  ArrayList<>();
	private Logger log;
	private Gson  gson;
	private FileReader game;
	Rank rank;
	public Controller() {
		try {
			rank= new Rank();
			log= Logger.getinsist();
			game=new FileReader();
			GsonBuilder builder=new	GsonBuilder().registerTypeAdapter(Heros.class, new AbstractAdapter<Heros>());
			builder.serializeSpecialFloatingPointValues();
			builder.registerTypeAdapter(HeroPower.class, new AbstractAdapter<HeroPower>());
			gson= builder.create();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	void addToBattleground(String message, DatagramPacket packet) {
		StringReader reader=new StringReader(message);
		AttackRequest request=gson.fromJson(new JsonReader(reader), AttackRequest.class);
		User x=online.get(request.getTocken());
		if(x!=null) {
			try {
				for (Game game : games) {
					if(game.getUser1()==x) {
						game.addToBattleground(0,request.getCardName(),request.getX(),request.getY());
					}else if(game.getUser2()==x) {
						game.addToBattleground(1,request.getCardName(),request.getX(),request.getY());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
	}
	void changCard(String message, DatagramPacket packet) {
		StringReader reader=new StringReader(message);
		changeCardRequest request=gson.fromJson(new JsonReader(reader), changeCardRequest.class);
		User x=online.get(request.getTocken());
		if(x!=null) {
			try {
				for (Game game : games) {
					if(game.getUser1()==x) {
						game.changCard(0,request.getCardName());
					}else if(game.getUser2()==x) {
						game.changCard(1,request.getCardName());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
	}
	void attack(String message, DatagramPacket packet) {
		StringReader reader=new StringReader(message);
		AttackRequest request=gson.fromJson(new JsonReader(reader), AttackRequest.class);
		User x=online.get(request.getTocken());
		if(x!=null) {
			try {
				for (Game game : games) {
					if(game.getUser1()==x) {
						game.attack(0,request.getCardName(),request.getX(),request.getY());
					}else if(game.getUser2()==x) {
						game.attack(1,request.getCardName(),request.getX(),request.getY());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
	void sendRank(String message, DatagramPacket  packet) {
		StringReader reader=new StringReader(message);
		SaveAndExitRequest request=gson.fromJson(new JsonReader(reader), SaveAndExitRequest.class);
		try {
			User x=online.get(request.getTocken());
			if(x!=null) {
			String mes="SETRANKNEED>>"+gson.toJson(rank.setRankNeed(x.getPlayer()))+"#";
			String mse1="CHANGEPANEL>>RANK#";
			ServerMain.WriteMessage(mes, x.getAddress());
			ServerMain.WriteMessage(mse1, x.getAddress());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	void setPassive(String message, DatagramPacket packet) {
		StringReader reader=new StringReader(message);
		SetPassiveRequest request=gson.fromJson(new JsonReader(reader), SetPassiveRequest.class);
		User x=online.get(request.getTocken());
		if(x!=null) {
			try {
				for (Game game : games) {
					if(game.getUser1()==x) {
						game.setPassive(0,request.getPassiveName());
					}else if(game.getUser2()==x) {
						game.setPassive(1,request.getPassiveName());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	void heroPower(String message, DatagramPacket packet) {
		StringReader reader=new StringReader(message);
		HeroPowerRequest request=gson.fromJson(new JsonReader(reader), HeroPowerRequest.class);
		User x=online.get(request.getTocken());
		if(x!=null) {
			try {
				for (Game game : games) {
					if(game.getUser1()==x) {
						game.handleHeroPower(0,request.getX(), request.getY());
					}else if(game.getUser2()==x) {
						game.handleHeroPower(1,request.getX(), request.getY());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
	}
	void nextTurn(String message, DatagramPacket packet) {
		StringReader reader=new StringReader(message);
		NextTurnRequest request=gson.fromJson(new JsonReader(reader), NextTurnRequest.class);
		User x=online.get(request.getTocken());
		if(x!=null) {
			try {
				for (Game game : games) {
					if(game.getUser1()==x) {
						game.nextTurn(0);
					}else if(game.getUser2()==x) {
						game.nextTurn(1);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	void startmatch(String message, DatagramPacket packet) {
		StringReader reader=new StringReader(message);
		StartMatchRequest request=gson.fromJson(new JsonReader(reader), StartMatchRequest.class);
		User x=online.get(request.getTocken());
		if(x!=null) {
			x.setGameState(request.getState());
			try {
				if(request.getState().equals("training")) {
					games.add(new Game(x));
				}else if(request.getState().equals("deckreader")) {
					deckReaderWaiting.add(x);
					if(deckReaderWaiting.size()>=2) {
						games.add(new Game(deckReaderWaiting.get(0), deckReaderWaiting.get(1)));
						deckReaderWaiting.remove(0);
						deckReaderWaiting.remove(0);
					}
				}else if(request.getState().equals("online")) {
					onlineWaiting.add(x);
					if(onlineWaiting.size()>=2) {
						games.add(new Game(onlineWaiting.get(0), onlineWaiting.get(1)));
						onlineWaiting.remove(0);
						onlineWaiting.remove(0);
					}					
				}else {
					System.out.println("not valid state");
				}
				log.log(x.getPlayer().get_name(), "want to play game", request.getState());
			} catch (IOException e) {	e.printStackTrace();}
		}
	}
	void startPlay(String message, DatagramPacket packet) {
		StringReader reader=new StringReader(message);
		SaveAndExitRequest request=gson.fromJson(new JsonReader(reader), SaveAndExitRequest.class);
		try {
			User x=online.get(request.getTocken());
			if(x!=null) {
				if(canStart(x)){
					log.log(x.getPlayer().get_name(), "go to  start a match", "");
					String message1="CHANGEPANEL>>PLAYSTART#";
					ServerMain.WriteMessage(message1, x.getAddress());
				}else {
					String message1="PLAYERROR>> you have to make a good deck for yourself and enemy!!! edit or change them"+"#";					
					ServerMain.WriteMessage(message1, x.getAddress());			
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private boolean canStart(User x) {
		if(x.getPlayer().get_mydeck().size()==15 && x.getEnemy().getEnemyDeck().getDeck().size()==15) {
			return true;
		}else
			return false;		
	}
	void goMenu(String message, DatagramPacket packet) {
		StringReader reader=new StringReader(message);
		SaveAndExitRequest request=gson.fromJson(new JsonReader(reader), SaveAndExitRequest.class);
		try {
			User x=online.get(request.getTocken());
			if(x!=null) {
				log.log(x.getPlayer().get_name(), "go to menu", "");
				String message1="SETPLAYER>>"+gson.toJson(new client.model.User(x.getPlayer().get_name(), x.getPlayer().getTocken(), x.getPlayer().gem, x.getPlayer().getCup()))+"#";
				String message3="CHANGEPANEL>>MENU#";
				ServerMain.WriteMessage(message1, x.getAddress());
				ServerMain.WriteMessage(message3, x.getAddress());			
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	void serch(String message, DatagramPacket packet) {
		StringReader reader=new StringReader(message);
		SearchRequest request =gson.fromJson(new JsonReader(reader), SearchRequest.class);
		User x =online.get(request.getTocken());
		try {
			if(x!=null) {
				log.log(x.getPlayer().get_name(), "",request.getMessage());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	void changeDeck(String message, DatagramPacket packet) {
		StringReader reader=new StringReader(message);
		EditDeckRequest request = gson.fromJson(new JsonReader(reader), EditDeckRequest.class);
		User x =online.get(request.getTocken());
		try {
			if(x!=null) {
				log.log(x.getPlayer().get_name(), "change deck", "to " +request.getDeckName());
				for (int i = 0; i < x.getPlayer().getalldeck().size(); i++) {
					if(x.getPlayer().getalldeck().get(i).getName().equals(request.getDeckName())  && x.getPlayer().getalldeck().get(i).getHeroDeck().getname().equals(request.getHeroName())) {
						x.getPlayer().setMyDeck(i);
						String message1="DECKCHANGE>>"+gson.toJson(new ChangInDeckResponse(makeClientCards(x.getPlayer().getMyDeck().getDeck()),makeClientCards(x.getEnemy().getEnemyDeck().getDeck()), findDeckInfo(x.getPlayer())))+"#";
						ServerMain.WriteMessage(message1, packet.getSocketAddress());
						break;						
					}		
				}		
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	void editNameDeck(String message, DatagramPacket packet) {
		StringReader reader=new StringReader(message);
		EditDeckRequest request = gson.fromJson(new JsonReader(reader), EditDeckRequest.class);
		User x =online.get(request.getTocken());
		try {
			if(x!=null) {
				x.getPlayer().getMyDeck().setName(request.getDeckName());
				log.log(x.getPlayer().get_name(), "deck name edited ","new name of deck : "+request.getDeckName() );
				String message1="DECKCHANGE>>"+gson.toJson(new ChangInDeckResponse(makeClientCards(x.getPlayer().getMyDeck().getDeck()),makeClientCards(x.getEnemy().getEnemyDeck().getDeck()), findDeckInfo(x.getPlayer())))+"#";
				ServerMain.WriteMessage(message1, packet.getSocketAddress());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	void editHeroDeck(String message, DatagramPacket packet) {
		StringReader reader=new StringReader(message);
		EditDeckRequest request = gson.fromJson(new JsonReader(reader), EditDeckRequest.class);
		User x =online.get(request.getTocken());
		try {
			if(x!=null) {
				x.getPlayer().getMyDeck().addUsethisDeck(0);
				x.getPlayer().getMyDeck().addWin(0);
				x.getPlayer().getMyDeck().setHeroDeck(findHero(request.getHeroName(), x.getPlayer().get_myheros()));
				log.log(x.getPlayer().get_name(), "change hero of deck  ", "to : " +request.getHeroName());
				String message1="DECKCHANGE>>"+gson.toJson(new ChangInDeckResponse(makeClientCards(x.getPlayer().getMyDeck().getDeck()),makeClientCards(x.getEnemy().getEnemyDeck().getDeck()), findDeckInfo(x.getPlayer())))+"#";
				ServerMain.WriteMessage(message1, packet.getSocketAddress());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	void makeNewDeck(String message, DatagramPacket packet){
		StringReader reader=new StringReader(message);
		NewDeck newDeck=gson.fromJson(new JsonReader(reader), NewDeck.class);
		User x=online.get(newDeck.getTocken());
		try {
			if(x!=null) {
				if(x.getPlayer().getalldeck().size()<16) {
					Deck s= new Deck();
					s.setName(newDeck.getDeckInfo().getName());
					s.setHeroDeck(x.getPlayer(), newDeck.getDeckInfo().getHeroName());
					x.getPlayer().getalldeck().add(s);
					x.getPlayer().setMyDeck(x.getPlayer().getalldeck().size()-1);
					log.log(x.getPlayer().get_name(), "add deck ", s.getName());
					String message1="DECKCHANGE>>"+gson.toJson(new ChangInDeckResponse(makeClientCards(s.getDeck()),makeClientCards(x.getEnemy().getEnemyDeck().getDeck()), findDeckInfo(x.getPlayer())))+"#";
					ServerMain.WriteMessage(message1, packet.getSocketAddress());
				}else {
					String message4="COLLECTIONERROR>>you have maximum deck!!! delet or edit some!!!#";
					ServerMain.WriteMessage(message4, packet.getSocketAddress());
				}
			}
		} catch (Exception e) {e.printStackTrace();}
	}
	void removeCardFromEnemyDeck(String message, DatagramPacket packet) {
		StringReader reader=new StringReader(message);
		AddCardToDeck request=gson.fromJson(new JsonReader(reader), AddCardToDeck.class);
		User x=online.get(request.getTocken());
		try {
			if(x!=null) {
				x.getEnemy().getEnemyDeck().getDeck().remove(findCard(request.getCardName(), x.getEnemy().getEnemyDeck().getDeck()));
				log.log(x.getPlayer().get_name(), "remove card from enemy deck", request.getCardName());
				String message1="DECKCHANGE>>"+gson.toJson(new ChangInDeckResponse(makeClientCards(x.getPlayer().get_mydeck()),
						makeClientCards(x.getEnemy().getEnemyDeck().getDeck()),
						findDeckInfo(x.getPlayer())))+"#";
				ServerMain.WriteMessage(message1, packet.getSocketAddress());
			}
		} catch (Exception e) {e.printStackTrace();}		
	}
	void removeCardFromMyDeck(String message, DatagramPacket packet) {
		StringReader reader=new StringReader(message);
		AddCardToDeck request=gson.fromJson(new JsonReader(reader), AddCardToDeck.class);
		User x=online.get(request.getTocken());
		try {
			if(x!=null) {
				x.getPlayer().getMyDeck().getDeck().remove(findCard(request.getCardName(), x.getPlayer().get_mydeck()));
				haveChangeInDeck(x.getPlayer());
				log.log(x.getPlayer().get_name(), "remove card from deck", request.getCardName());					
				String message1="DECKCHANGE>>"+gson.toJson(new ChangInDeckResponse(makeClientCards(x.getPlayer().get_mydeck()),
						makeClientCards(x.getEnemy().getEnemyDeck().getDeck()),
						findDeckInfo(x.getPlayer())))+"#";
				ServerMain.WriteMessage(message1, packet.getSocketAddress());
			}
		} catch (Exception e) {e.printStackTrace();}		
	}
	private void haveChangeInDeck(Player player) throws Exception {
		player.getMyDeck().addUsethisDeck(0);
		player.getMyDeck().addWin(0);
	}
	void addCardToMyDeck(String message, DatagramPacket packet) {
		StringReader reader=new StringReader(message);
		AddCardToDeck request=gson.fromJson(new JsonReader(reader), AddCardToDeck.class);
		User x=online.get(request.getTocken());
		try {
			if(x!=null) {
				if(x.getPlayer().getMyDeck().addCardToDeck(findCard(request.getCardName(), x.getPlayer().get_myCards()))) {
					haveChangeInDeck(x.getPlayer());
					log.log(x.getPlayer().get_name(), "add card to deck", request.getCardName());					
					String message1="DECKCHANGE>>"+gson.toJson(new ChangInDeckResponse(makeClientCards(x.getPlayer().get_mydeck()),
							makeClientCards(x.getEnemy().getEnemyDeck().getDeck()),
							findDeckInfo(x.getPlayer())))+"#";
					ServerMain.WriteMessage(message1, packet.getSocketAddress());
				}else {
					String message4="COLLECTIONERROR>>Cant add because this deck is full or \n "
							+ "this card is for a diffrent hero \n"
							+ "you have this in your deck !!!#";
					ServerMain.WriteMessage(message4, packet.getSocketAddress());
				}
			}
		} catch (Exception e) {e.printStackTrace();}		
	}
	void addCardToEnemyDeck(String message, DatagramPacket packet) {
		StringReader reader=new StringReader(message);
		AddCardToDeck request=gson.fromJson(new JsonReader(reader), AddCardToDeck.class);
		User x=online.get(request.getTocken());
		try {
			if(x!=null) {
				if(x.getEnemy().getEnemyDeck().addCardToDeck(findCard(request.getCardName(), x.getPlayer().get_myCards()))) {
					log.log(x.getPlayer().get_name(), "add card to deck", findCard(request.getCardName(), x.getPlayer().get_myCards()).get_Name());					
					String message1="DECKCHANGE>>"+gson.toJson(new ChangInDeckResponse(makeClientCards(x.getPlayer().get_mydeck()),
							makeClientCards(x.getEnemy().getEnemyDeck().getDeck()),
							findDeckInfo(x.getPlayer())))+"#";
					ServerMain.WriteMessage(message1, packet.getSocketAddress());
				}else {
					String message4="COLLECTIONERROR>>Cant add because this deck is full or \n "
							+ "this card is for a diffrent hero \n"
							+ "you have this in your deck !!!#";
					ServerMain.WriteMessage(message4, packet.getSocketAddress());
				}

			}
		} catch (Exception e) {e.printStackTrace();}
	}
	void collection(String message, DatagramPacket packet) {
		StringReader reader=new StringReader(message);
		SaveAndExitRequest request=gson.fromJson(new JsonReader(reader), SaveAndExitRequest.class);
		User x=online.get(request.getTocken());
		try {
			if(x!=null) {
				log.log(x.getPlayer().get_name(), "go to collection ", "");
				String message1="SETCOLLECTIONNEED>>"+gson.toJson(new CollectionNeed(makeClientCards(x.getPlayer().get_myCards()),
						makeClientCards(x.getPlayer().getMyStore().getBuyCard()), 
						makeClientCards(x.getPlayer().get_mydeck()),
						makeClientCards(x.getEnemy().getEnemyDeck().getDeck()),
						findDeckInfo(x.getPlayer()),x.getPlayer().get_myheros(),x.getEnemy().getEnemyDeck().getHeroDeck().getname()))+"#";
				String message2="CHANGEPANEL>>COLLECTION#";
				ServerMain.WriteMessage(message1, packet.getSocketAddress());
				ServerMain.WriteMessage(message2, packet.getSocketAddress());	
			}
		} catch (Exception e) {e.printStackTrace();}
	}
	public void setBattlebackGround(String message, DatagramPacket  packet){
		StringReader reader=new StringReader(message);
		ChangeBattlegroundThem them=gson.fromJson(new JsonReader(reader), ChangeBattlegroundThem.class);
		try {
			User x=online.get(them.getTocken());
			if(x!=null) {
				log.log(x.getPlayer().get_name(), "Change  Battleground  Them", "");
				x.setBackBattleGround(them.getName());
			}
		} catch (IOException e) {e.printStackTrace();}	
	}
	void deleteAccount(String message, DatagramPacket packet) {
		StringReader reader=new StringReader(message);
		SaveAndExitRequest request=gson.fromJson(new JsonReader(reader), SaveAndExitRequest.class);
		try {
			User x=online.get(request.getTocken());
			if(x!=null) {
				log.log(x.getPlayer().get_name(), "deleted account", "");
				game.DeletPlayer(x.getPlayer());
				exit(message, packet);	
			}
		} catch (IOException e) {e.printStackTrace();}	
	}
	void setting(String message, DatagramPacket packet) {		
		StringReader reader=new StringReader(message);
		SaveAndExitRequest request=gson.fromJson(new JsonReader(reader), SaveAndExitRequest.class);
		try {
			User x=online.get(request.getTocken());
			if(x!=null) {
				log.log(x.getPlayer().get_name(), "go to Setting", "");
				String message1="CHANGEPANEL>>SETTING#";
				ServerMain.WriteMessage(message1, packet.getSocketAddress());
			}
		} catch (IOException e) {e.printStackTrace();}
	}
	void statos(String message, DatagramPacket packet) {
		StringReader reader=new StringReader(message);
		SaveAndExitRequest request=gson.fromJson(new JsonReader(reader), SaveAndExitRequest.class);
		try {
			User x=online.get(request.getTocken());
			if(x!=null) {
				log.log(x.getPlayer().get_name(), "go to Status", "");
				String message1="SETSTATOSNEED>>"+gson.toJson(new StatosNeeds(findDeckInfo(x.getPlayer())))+"#";
				String message2="CHANGEPANEL>>STATOS#";
				ServerMain.WriteMessage(message1, packet.getSocketAddress());
				ServerMain.WriteMessage(message2, packet.getSocketAddress());
			}
		} catch (IOException e) {e.printStackTrace();}
	}
	private ArrayList<DeckInfo> findDeckInfo(Player player){
		ArrayList<DeckInfo> decks=new ArrayList<>();
		for (Deck deck : player.sortDecks()) {
			if(deck.bestCard()!=null) {
				decks.add(new DeckInfo(deck.getName(),((float)deck.getWin())/((float)player.getPlays()), deck.getWin()	, deck.getUsethisDeck(), deck.GetAverage(), deck.getHeroDeck().getname(), deck.bestCard().get_Name(),deck.getDeck().size(),deck.getCup()));
			}else {
				decks.add(new DeckInfo(deck.getName(),((float)deck.getWin())/((float)player.getPlays()), deck.getWin()	, deck.getUsethisDeck(), deck.GetAverage(), deck.getHeroDeck().getname(), "",deck.getDeck().size(),deck.getCup()));				
			}
		}
		return decks;
	}
	void buyCard(String message, DatagramPacket packet) {
		StringReader reader=new StringReader(message);
		SellAndBuy sellAndBuy=gson.fromJson(new JsonReader(reader), SellAndBuy.class);
		User user=online.get(sellAndBuy.getTocken());
		try {
			if(user!=null) {
				Cardspackage.Card card=findCard(sellAndBuy.getName(), user.getPlayer().getMyStore().getBuyCard());
				if(card!=null) {					
					if(user.getPlayer().gem>=card.gemCost()) {
						user.getPlayer().gem-=card.gemCost();
						user.getPlayer().buyaCard(card);
						log.log(user.getPlayer().get_name(), "buy card ", card.get_Name());
						String message1="SETSHOPNEED>>"+gson.toJson(new ShopNeeds(makeClientCards(user.getPlayer().get_myCards()), user.getPlayer().getMyStore().getBuyHero(), makeClientCards(user.getPlayer().getMyStore().getBuyCard())))+"#";
						String message3="SETPLAYER>>"+gson.toJson(new client.model.User(user.getPlayer().get_name(), user.getPlayer().getTocken(), user.getPlayer().gem, user.getPlayer().getCup()))+"#";
						String message2="CHANGEPANEL>>SHOP#";
						ServerMain.WriteMessage(message1, packet.getSocketAddress());
						ServerMain.WriteMessage(message2, packet.getSocketAddress());
						ServerMain.WriteMessage(message3, packet.getSocketAddress());	
					}else {
						ServerMain.WriteMessage("SELLERROR>> cant buy hero : "+card.get_Name()+" dont have enogh gem!!!!#", packet.getSocketAddress());
						log.log(user.getPlayer().get_name(), "error", "cant buy card : "+card.get_Name()+"  dont have enogh gem!!!!");	
					}
				}	
			}	
		} catch (Exception e) {}
	}
	void buyHero(String message, DatagramPacket packet) {
		StringReader reader=new StringReader(message);
		SellAndBuy sellAndBuy=gson.fromJson(new JsonReader(reader), SellAndBuy.class);
		User user=online.get(sellAndBuy.getTocken());
		try {
			if(user!=null) {
				Heros x=findHero(sellAndBuy.getName(), user.getPlayer().getMyStore().getBuyHero());
				if(user.getPlayer().gem>=25) {
					if(x!=null) {					
						user.getPlayer().gem-=25;
						user.getPlayer().get_myheros().add(x);
						user.getPlayer().getMyStore().getBuyHero().remove(x);
						log.log(user.getPlayer().get_name(), "buy hero ", x.getname());
						String message1="SETPLAYER>>"+gson.toJson(new client.model.User(user.getPlayer().get_name(), user.getPlayer().getTocken(), user.getPlayer().gem, user.getPlayer().getCup()))+"#";
						String message2="SETSHOPNEED>>"+gson.toJson(new ShopNeeds(makeClientCards(user.getPlayer().get_myCards()), user.getPlayer().getMyStore().getBuyHero(), makeClientCards(user.getPlayer().getMyStore().getBuyCard())))+"#";
						String message3="CHANGEPANEL>>SHOP#";
						ServerMain.WriteMessage(message1, packet.getSocketAddress());
						ServerMain.WriteMessage(message2, packet.getSocketAddress());
						ServerMain.WriteMessage(message3, packet.getSocketAddress());	
					}	
				}else {
					ServerMain.WriteMessage("SELLERROR>> cant buy hero : "+x.getname()+" dont have enogh gem!!!!#", packet.getSocketAddress());
					log.log(user.getPlayer().get_name(), "error", "cant buy hero : "+x.getname()+"  dont have enogh gem!!!!");	
				}
			}	
		} catch (Exception e) {}
	}
	void sellCard(String message, DatagramPacket packet) {
		StringReader reader=new StringReader(message);
		SellAndBuy sellAndBuy=gson.fromJson(new JsonReader(reader), SellAndBuy.class);
		User user=online.get(sellAndBuy.getTocken());
		try {
			if(user!=null) {
				Cardspackage.Card card=findCard(sellAndBuy.getName(), user.getPlayer().get_myCards());
				if(card!=null)
					if(user.getPlayer().sellaCard(card)) {			
						user.getPlayer().gem+=card.gemCost();
						String message2="SETSHOPNEED>>"+gson.toJson(new ShopNeeds(makeClientCards(user.getPlayer().get_myCards()), user.getPlayer().getMyStore().getBuyHero(), makeClientCards(user.getPlayer().getMyStore().getBuyCard())))+"#";
						String message3="CHANGEPANEL>>SHOP#";
						String message1="SETPLAYER>>"+gson.toJson(new client.model.User(user.getPlayer().get_name(), user.getPlayer().getTocken(), user.getPlayer().gem, user.getPlayer().getCup()))+"#";
						ServerMain.WriteMessage(message1, packet.getSocketAddress());
						ServerMain.WriteMessage(message2, packet.getSocketAddress());
						ServerMain.WriteMessage(message3, packet.getSocketAddress());
						log.log(user.getPlayer().get_name(), "sell card", card.get_Name());
					}else {
						ServerMain.WriteMessage("SELLERROR>> cant sell this card!!!!!#", packet.getSocketAddress());
						log.log(user.getPlayer().get_name(), "error", "cant sell card : "+card.get_Name());		
					}
			}
		} catch (Exception e) {}
	}
	void goShop(String message, DatagramPacket packet) {
		StringReader reader=new StringReader(message);
		SaveAndExitRequest request=gson.fromJson(new JsonReader(reader), SaveAndExitRequest.class);
		try {
			User x=online.get(request.getTocken());
			if(x!=null) {	
				log.log(x.getPlayer().get_name(), "go shop", "");
				String message1="SETSHOPNEED>>"+gson.toJson(new ShopNeeds(makeClientCards(x.getPlayer().get_myCards()), x.getPlayer().getMyStore().getBuyHero(), makeClientCards(x.getPlayer().getMyStore().getBuyCard())))+"#";
				String message2="CHANGEPANEL>>SHOP#";
				ServerMain.WriteMessage(message1, packet.getSocketAddress());
				ServerMain.WriteMessage(message2, packet.getSocketAddress());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private ArrayList<Card> makeClientCards(ArrayList<Cardspackage.Card> cards){
		ArrayList<Card> c=new ArrayList<>();
		for (Cardspackage.Card card : cards) {
			c.add(new Card(card.get_Mana(),card.get_Name(),card.get_Rarity(),card.get_Class(),card.getDescription(),card.getType(),card.getAttack(), card.getHp(),card.isRush(),card.getUsedToAttack()));
		}
		return c;
	}
	void exit(String message, DatagramPacket packet) {
		StringReader reader=new StringReader(message);
		SaveAndExitRequest request=gson.fromJson(new JsonReader(reader), SaveAndExitRequest.class);
		try {
			log.log(online.get(request.getTocken()).getPlayer().get_name(), "exit game", "");
			clients.remove(online.get(request.getTocken()));
			deckReaderWaiting.remove(online.get(request.getTocken()));
			onlineWaiting.remove(online.get(request.getTocken()));
			online.remove(request.getTocken());
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	void save(String message, DatagramPacket packet) {
		StringReader reader=new StringReader(message);
		SaveAndExitRequest request=gson.fromJson(new JsonReader(reader), SaveAndExitRequest.class);
		try {
			User x=online.get(request.getTocken());
			if(x!=null) {			
				game.makeProfile(x.getPlayer(), x.getEnemy());
				game.writeName(x.getPlayer().get_name(), x.getPlayer().get_pass());
				log.log(x.getPlayer().get_name(), "save game", "");		
				game.makeProfile(x.getPlayer(), x.getEnemy());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	void singup(String message, DatagramPacket packet) {	
		StringReader reader=new StringReader(message);
		LoginAndSingUpRequest re=gson.fromJson(new JsonReader(reader), LoginAndSingUpRequest.class);
		try {
			for (User client : clients) {
				if(re.getName().equals(client.getPlayer().get_name())) {
					ServerMain.WriteMessage("LOGINERROR>> user alredy online!!!!!!#", packet.getSocketAddress());
					return;
				}
			}
			if(game.checkValid(re.getName())) {
				ServerMain.WriteMessage("LOGINERROR>> username alredy used!!!!!!#", packet.getSocketAddress());
			}else {
				File loog=new File(System.getProperty("user.dir")+"\\src\\LOGFILE\\"+re.getName());
				loog.getParentFile().mkdir();
				loog.createNewFile();
				User user= new User(new Player(re.getName(), re.getPassword(), 50), new Enemy(), packet.getPort(), packet.getSocketAddress());
				log.makeLog(user.getPlayer().get_name(), user.getPlayer().get_pass());
				log.log(user.getPlayer().get_name(), "sign up ", " ");
				user.getPlayer().setMyStore(new Store());
				new Login(user.getPlayer(),user.getEnemy());
				online.put(user.getPlayer().getTocken(), user);
				clients.add(user);
				String message1="SETPLAYER>>"+gson.toJson(new client.model.User(user.getPlayer().get_name(), user.getPlayer().getTocken(), user.getPlayer().gem, user.getPlayer().getCup()))+"#";
				ServerMain.WriteMessage(message1, user.getAddress());
				String message3="CHANGEPANEL>>MENU#";
				ServerMain.WriteMessage(message3, user.getAddress());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}		
	}
	void login(String message, DatagramPacket packet) {
		try {
			StringReader reader=new StringReader(message);
			LoginAndSingUpRequest re=gson.fromJson(new JsonReader(reader), LoginAndSingUpRequest.class);
			for (User client : clients) {
				if(re.getName().equals(client.getPlayer().get_name())) {
					ServerMain.WriteMessage("LOGINERROR>> user alredy online!!!!!!#", packet.getSocketAddress());
					return;
				}
			}
			if(game.checkName(re.getName(), re.getPassword())) {
				File fw=new File("src\\LOGFILE\\"+re.getName());
				boolean del=false;
				Scanner see=new Scanner(fw);
				while (see.hasNextLine()) {
					if(see.nextLine().startsWith("DELETED")) {
						ServerMain.WriteMessage("LOGINERROR>>Account deleted!!!!!!#", packet.getSocketAddress());
						del=true;
					}
				}
				see.close();
				if(!del) {
					User user =new User(game.readPlayer(re.getName()),  game.readEnemy(re.getName()), packet.getPort(), packet.getSocketAddress());
					online.put(user.getPlayer().getTocken(), user);
					clients.add(user);
					log.log(user.getPlayer().get_name(), "login at :  ", "");
					String message1="SETPLAYER>>"+gson.toJson(new client.model.User(user.getPlayer().get_name(), user.getPlayer().getTocken(), user.getPlayer().gem, user.getPlayer().getCup()))+"#";
					String message3="CHANGEPANEL>>MENU#";
					ServerMain.WriteMessage(message1, user.getAddress());
					ServerMain.WriteMessage(message3, user.getAddress());
				}
			}else {
				ServerMain.WriteMessage("LOGINERROR>> Name or password incorrect!!!!!#", packet.getSocketAddress());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	

	private Cardspackage.Card findCard(String name, ArrayList<Cardspackage.Card> cards) {
		for (Cardspackage.Card card : cards) {
			if(card.get_Name().equals(name))
				return card;
		}
		return null;
	}
	private Heros findHero(String name, ArrayList<Heros> heros) {
		for (Heros heros2 : heros) {
			if(heros2.getname().equals(name))
				return heros2;
		}
		return null;
	}
	public void exitMatch(String message, DatagramPacket packet) {
		StringReader reader=new StringReader(message);
		SaveAndExitRequest request=gson.fromJson(new JsonReader(reader), SaveAndExitRequest.class);
		User x=online.get(request.getTocken());
		if(x!=null) {
			try {
				for (Game game : games) {
					if(game.getUser1()==x) {
						game.exit(0);
					}else if(game.getUser2()==x) {
						game.exit(1);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void isAlive(String message, DatagramPacket packet) {
		StringReader reader=new StringReader(message);
		SaveAndExitRequest request=gson.fromJson(new JsonReader(reader), SaveAndExitRequest.class);
		User x=online.get(request.getTocken());
		if(x!=null) {
			String messString="YES#";
			try {
				ServerMain.WriteMessage(messString, x.getAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void sendMessage(String message, DatagramPacket packet) {
		StringReader reader=new StringReader(message);
		ChatRequest request=gson.fromJson(new JsonReader(reader), ChatRequest.class);
		User x=online.get(request.getTocken());
		if(x!=null) {
			try {
				for (Game game : games) {
					if(game.getUser1()==x) {
						ServerMain.WriteMessage("MESSAGE>>"+request.getMessag()+"#", game.getUser2().getAddress());
						for (User user : clients) {								
							ServerMain.WriteMessage("MESSAGE>>"+request.getMessag()+"#", user.getAddress());						
						}
					}else if(game.getUser2()==x) {
						for (User user : clients) {								
							ServerMain.WriteMessage("MESSAGE>>"+request.getMessag()+"#", user.getAddress());						
						}
						ServerMain.WriteMessage("MESSAGE>>"+request.getMessag()+"#", game.getUser1().getAddress());						
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void seeMatch(String message, DatagramPacket packet) {
		StringReader reader=new StringReader(message);
		ChatRequest request=gson.fromJson(new JsonReader(reader), ChatRequest.class);
		User x=online.get(request.getTocken());
		if(x!=null) {
			try {
				for (Game game : games) {
					if(game.getUser1().getPlayer().get_name().equalsIgnoreCase(request.getMessag()) ||game.getUser2().getPlayer().get_name().equalsIgnoreCase(request.getMessag()) ) {
						if(!game.getUser1().getGameState().equals("training"))
							game.addWatcher(x);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void kick(String message, DatagramPacket packet) {
		StringReader reader=new StringReader(message);
		Kickrequest request=gson.fromJson(new JsonReader(reader),Kickrequest.class);
		User x=online.get(request.getTocken());
		if(x!=null) {
			try {
				for (Game game : games) {
					if(game.getUser1()==x || game.getUser2()==x) {
						for (int i = 0; i < game.getWatcher().size(); i++) {
							if(game.getWatcher().get(i).getPlayer().get_name().equals(request.getName())) {
								String message2="CHANGEPANEL>>MENU#";
								ServerMain.WriteMessage(message2, game.getWatcher().get(i).getAddress());
								game.getWatcher().remove(i);
								game.sendWatcher();
							}

						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void exitwatching(String message, DatagramPacket packet) {
		StringReader reader=new StringReader(message);
		SaveAndExitRequest request=gson.fromJson(new JsonReader(reader),SaveAndExitRequest.class);
		User x=online.get(request.getTocken());
		if(x!=null) {
			try {
				for (Game game : games) {
					for (int i = 0; i < game.getWatcher().size(); i++) {
						if(game.getWatcher().get(i)==x) {
							String message2="CHANGEPANEL>>MENU#";
							ServerMain.WriteMessage(message2, x.getAddress());
							game.getWatcher().remove(i);
							game.sendWatcher();
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}