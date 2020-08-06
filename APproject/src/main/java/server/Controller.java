package server;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.JOptionPane;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import client.model.Card;
import client.model.DeckInfo;
import game.Deck;
import game.Enemy;
import game.Gamestate;
import game.Logger;
import game.Login;
import game.Player;
import game.Store;
import gameModel.requestAndREsponse.AddCardToDeck;
import gameModel.requestAndREsponse.ChangInDeckResponse;
import gameModel.requestAndREsponse.ChangeBattlegroundThem;
import gameModel.requestAndREsponse.CollectionNeed;
import gameModel.requestAndREsponse.EditDeckRequest;
import gameModel.requestAndREsponse.LoginAndSingUpRequest;
import gameModel.requestAndREsponse.NewDeck;
import gameModel.requestAndREsponse.SaveAndExitRequest;
import gameModel.requestAndREsponse.SearchRequest;
import gameModel.requestAndREsponse.SellAndBuy;
import gameModel.requestAndREsponse.ShopNeeds;
import gameModel.requestAndREsponse.StatosNeeds;
import hero.Heros;

public class Controller {
	public static ArrayList<User> clients= new ArrayList<User>();
	public static HashMap<Integer, User> online=new HashMap<>();
	public static ArrayList<User> waiting= new ArrayList<User>();
	//	public static ArrayList<Game> games=new  ArrayList<>();






	private Logger log;
	Gson gson;
	private Gamestate game;




	public Controller() {
		try {
			log= Logger.getinsist();
			game=Gamestate.getinsist();
			gson= new GsonBuilder().setLenient().create();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void whatWant(String message, DatagramPacket packet) {
		String what="";
		for(int i=0 ;i<message.length();i++) {
			if(message.charAt(i)=='>') {
				what=message.substring(0, i);
				message=message.substring(i+2,message.length());
				break;
			}
		}
		for(int i=0;i<message.length();i++)
			if(message.charAt(i)=='#') {
				message=message.substring(0,i+1);
			}
		switch (what) {
		case "LOGIN":
			login(message, packet);
			break;
		case "SINGUP":
			singup(message, packet);
			break;
		case "SAVE":
			save(message, packet);
			break;
		case "EXIT":
			exit(message, packet);
			break;
		case "GOSHOP":
			goShop(message, packet);
			break;
		case "SELL":
			sellCard(message, packet);
			break;
		case "BUYHERO":
			buyHero(message, packet);
			break;
		case "BUYCARD":
			buyCard(message, packet);
			break;
		case "GOSTATOS":
			statos(message, packet);
			break;
		case "GOMENU":
			goMenu(message, packet);
			break;
		case "GOSETTING":
			setting(message, packet);
			break;			
		case "DELETACCOUNT":
			deleteAccount(message,packet);
			break;
		case "CHANGEBATTLEGROUND":
			setBattlebackGround(message,packet);
			break;
		case "GOCOLLECTION":
			collection(message, packet);
			break;
		case "ADDTOENEMYDECK":
			addCardToEnemyDeck(message, packet);
			break;
		case "ADDTOMYDECK":
			addCardToMyDeck(message, packet);
			break;
		case "REMOVEFROMDECK":
			removeCardFromMyDeck(message, packet);
			break;
		case "REMOVEFROMENEMYDECK":
			removeCardFromEnemyDeck(message, packet);
			break;
		case "NEWDECK":
			makeNewDeck(message, packet);
			break;
		case "EDITHERODECK":
			editHeroDeck(message,packet);
			break;
		case "EDITNAMEDECK":
			editNameDeck(message,packet);
			break;
		case "CHANGEDECK":
			changeDeck(message,packet);
			break;
		case "SEARCH":
			serch(message, packet);
			break;








		default:
			break;
		}
	}








	private void goMenu(String message, DatagramPacket packet) {
		SaveAndExitRequest request=gson.fromJson(message, SaveAndExitRequest.class);
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

	private void serch(String message, DatagramPacket packet) {
		SearchRequest request =gson.fromJson(message, SearchRequest.class);
		User x =online.get(request.getTocken());
		try {
			if(x!=null) {
				log.log(x.getPlayer().get_name(), "",request.getMessage());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}




	}

	private void changeDeck(String message, DatagramPacket packet) {
		EditDeckRequest request = gson.fromJson(message, EditDeckRequest.class);
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

	private void editNameDeck(String message, DatagramPacket packet) {
		EditDeckRequest request = gson.fromJson(message, EditDeckRequest.class);
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

	private void editHeroDeck(String message, DatagramPacket packet) {
		EditDeckRequest request = gson.fromJson(message, EditDeckRequest.class);
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

	private void makeNewDeck(String message, DatagramPacket packet){
		NewDeck newDeck=gson.fromJson(message, NewDeck.class);
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
					JOptionPane.showConfirmDialog(null, "you have maximum deck!!! delet or edit some", "error", JOptionPane.YES_OPTION);			
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}











	private void removeCardFromEnemyDeck(String message, DatagramPacket packet) {
		AddCardToDeck request=gson.fromJson(message, AddCardToDeck.class);
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

	private void removeCardFromMyDeck(String message, DatagramPacket packet) {
		AddCardToDeck request=gson.fromJson(message, AddCardToDeck.class);
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
	private void addCardToMyDeck(String message, DatagramPacket packet) {
		AddCardToDeck request=gson.fromJson(message, AddCardToDeck.class);
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

	private void addCardToEnemyDeck(String message, DatagramPacket packet) {
		AddCardToDeck request=gson.fromJson(message, AddCardToDeck.class);
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

	private void collection(String message, DatagramPacket packet) {
		SaveAndExitRequest request=gson.fromJson(message, SaveAndExitRequest.class);
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
		ChangeBattlegroundThem them=gson.fromJson(message, ChangeBattlegroundThem.class);
		try {
			User x=online.get(them.getTocken());
			if(x!=null) {
				log.log(x.getPlayer().get_name(), "Change  Battleground  Them", "");
				x.setBackBattleGround(them.getName());
			}
		} catch (IOException e) {e.printStackTrace();}	
	}
	private void deleteAccount(String message, DatagramPacket packet) {
		SaveAndExitRequest request=gson.fromJson(message, SaveAndExitRequest.class);
		try {
			User x=online.get(request.getTocken());
			if(x!=null) {
				log.log(x.getPlayer().get_name(), "deleted account", "");
				game.DeletPlayer(x.getPlayer());
				exit(message, packet);	
			}
		} catch (IOException e) {e.printStackTrace();}	
	}

	private void setting(String message, DatagramPacket packet) {		
		SaveAndExitRequest request=gson.fromJson(message, SaveAndExitRequest.class);
		try {
			User x=online.get(request.getTocken());
			if(x!=null) {
				log.log(x.getPlayer().get_name(), "go to Setting", "");
				String message1="CHANGEPANEL>>SETTING#";
				ServerMain.WriteMessage(message1, packet.getSocketAddress());
			}
		} catch (IOException e) {e.printStackTrace();}
	}

	private void statos(String message, DatagramPacket packet) {
		SaveAndExitRequest request=gson.fromJson(message, SaveAndExitRequest.class);
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
				decks.add(new DeckInfo(deck.getName(),((float)deck.getWin())/((float)player.getPlays()), deck.getWin()	, deck.getUsethisDeck(), deck.GetAverage(), deck.getHeroDeck().getname(), deck.bestCard().get_Name(),deck.getDeck().size()));
			}else {
				decks.add(new DeckInfo(deck.getName(),((float)deck.getWin())/((float)player.getPlays()), deck.getWin()	, deck.getUsethisDeck(), deck.GetAverage(), deck.getHeroDeck().getname(), "",deck.getDeck().size()));				
			}
		}
		return decks;
	}
	private void buyCard(String message, DatagramPacket packet) {
		SellAndBuy sellAndBuy=gson.fromJson(message, SellAndBuy.class);
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
						String message2="SETPLAYER>>"+gson.toJson(new client.model.User(user.getPlayer().get_name(), user.getPlayer().getTocken(), user.getPlayer().gem, user.getPlayer().getCup()))+"#";
						String message3="UPDATE>>SHOP#";
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

	private void buyHero(String message, DatagramPacket packet) {
		SellAndBuy sellAndBuy=gson.fromJson(message, SellAndBuy.class);
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
						String message1="SETSHOPNEED>>"+gson.toJson(new ShopNeeds(makeClientCards(user.getPlayer().get_myCards()), user.getPlayer().getMyStore().getBuyHero(), makeClientCards(user.getPlayer().getMyStore().getBuyCard())))+"#";
						String message3="UPDATE>>SHOP#";
						String message2="SETPLAYER>>"+gson.toJson(new client.model.User(user.getPlayer().get_name(), user.getPlayer().getTocken(), user.getPlayer().gem, user.getPlayer().getCup()))+"#";
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

	private void sellCard(String message, DatagramPacket packet) {
		SellAndBuy sellAndBuy=gson.fromJson(message, SellAndBuy.class);
		User user=online.get(sellAndBuy.getTocken());
		try {
			if(user!=null) {
				Cardspackage.Card card=findCard(sellAndBuy.getName(), user.getPlayer().get_myCards());
				if(card!=null)
					if(user.getPlayer().sellaCard(card)) {			
						user.getPlayer().gem+=card.gemCost();
						String message1="SETSHOPNEED>>"+gson.toJson(new ShopNeeds(makeClientCards(user.getPlayer().get_myCards()), user.getPlayer().getMyStore().getBuyHero(), makeClientCards(user.getPlayer().getMyStore().getBuyCard())))+"#";
						String message3="UPDATE>>SHOP#";
						String message2="SETPLAYER>>"+gson.toJson(new client.model.User(user.getPlayer().get_name(), user.getPlayer().getTocken(), user.getPlayer().gem, user.getPlayer().getCup()))+"#";
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

	private void goShop(String message, DatagramPacket packet) {
		SaveAndExitRequest request=gson.fromJson(message, SaveAndExitRequest.class);
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
			c.add(new Card(card.get_Mana(),card.get_Name(),card.get_Rarity(),card.get_Class(),card.getDescription(),card.getType(),card.getAttack(), card.getHp()));
		}
		return c;
	}

	private void exit(String message, DatagramPacket packet) {
		SaveAndExitRequest request=gson.fromJson(message, SaveAndExitRequest.class);
		try {
			log.log(online.get(request.getTocken()).getPlayer().get_name(), "exit game", "");
			clients.remove(online.get(request.getTocken()));
			waiting.remove(online.get(request.getTocken()));
			online.remove(request.getTocken());
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	private void save(String message, DatagramPacket packet) {
		SaveAndExitRequest request=gson.fromJson(message, SaveAndExitRequest.class);
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
	private void singup(String message, DatagramPacket packet) {	
		LoginAndSingUpRequest re=gson.fromJson(message, LoginAndSingUpRequest.class);
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
				String message3="CHANGEPANEL>>MENU#";
				ServerMain.WriteMessage(message1, user.getAddress());
				ServerMain.WriteMessage(message3, user.getAddress());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}		
	}

	private void login(String message, DatagramPacket packet) {
		try {
			LoginAndSingUpRequest re=gson.fromJson(message, LoginAndSingUpRequest.class);
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

	public void recivePacket(DatagramPacket packet) {
		ByteArrayInputStream arrayInputStream=new ByteArrayInputStream(packet.getData());
		whatWant(read(arrayInputStream), packet);
	}
	private String read(ByteArrayInputStream arrayInputStream) {
		Scanner scan = new Scanner(arrayInputStream);
		String message="";
		while (scan.hasNext()) {
			message+=scan.next();
		}
		scan.close();
		return message;
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




}