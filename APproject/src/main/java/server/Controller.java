package server;

import static java.awt.Container.log;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
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
import gameModel.requestAndREsponse.ChangeBattlegroundThem;
import gameModel.requestAndREsponse.LoginAndSingUpRequest;
import gameModel.requestAndREsponse.SaveAndExitRequest;
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
			











		default:
			break;
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void collection(String message, DatagramPacket packet) {
		SaveAndExitRequest request=gson.fromJson(message, SaveAndExitRequest.class);
		User x=online.get(request.getTocken());
		try {
		if(x!=null) {
			log.log(x.getPlayer().get_name(), "go to collection ", "");
			
		}
		

		
		
		
		
		
		
		String message1="CHANGEPANEL>>COLLECTION#";
			ServerMain.WriteMessage(message1, packet.getSocketAddress());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
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
				decks.add(new DeckInfo(deck.getName(),((float)deck.getWin())/((float)player.getPlays()), deck.getWin()	, deck.getUsethisDeck(), deck.GetAverage(), deck.getHeroDeck().getname(), deck.bestCard().get_Name()));
			}else {
				decks.add(new DeckInfo(deck.getName(),((float)deck.getWin())/((float)player.getPlays()), deck.getWin()	, deck.getUsethisDeck(), deck.GetAverage(), deck.getHeroDeck().getname(), ""));				
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
			c.add(new Card(card.get_Mana(),card.get_Name(),card.get_Rarity(),card.get_Class(),card.getDescription(),card.getType()));
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
				Login log=new Login(user.getPlayer(),user.getEnemy());
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