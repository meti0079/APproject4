package client;

import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import java.lang.reflect.Type;
import com.google.common.reflect.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import Cardspackage.Card;
import client.grapic.CollectionPanel;
import client.grapic.LoginPanel;
import client.grapic.MainFrame;
import client.grapic.MenuPanel;
import client.grapic.PlayShow;
import client.grapic.RankPanel;
import client.grapic.SettingPanel;
import client.grapic.Shop;
import client.grapic.StartPlayShow;
import client.grapic.Statos;
import client.model.User;
import game.AbstractAdapter;
import gameModel.requestAndREsponse.ChangInDeckResponse;
import gameModel.requestAndREsponse.CollectionNeed;
import gameModel.requestAndREsponse.SaveAndExitRequest;
import gameModel.requestAndREsponse.ShopNeeds;
import gameModel.requestAndREsponse.StatosNeeds;
import gameModel.requestAndREsponse.GameNeed;
import gameModel.requestAndREsponse.NextTurnRequest;
import gameModel.requestAndREsponse.RankNeed;
import hero.Heros;
import hero.heroPower.HeroPower;
public class Controller {

	private MainFrame frame;
	private LoginPanel loginPanel;
	private MenuPanel menuPanel;
	private User user;
	private Gson gson;
	private Shop shop;
	private Statos statos;
	private SettingPanel settingPanel;
	private CollectionPanel collectionPanel;
	private StartPlayShow playShow;
	private PlayShow playPanel;
	private RankPanel rankPanel;
	private GameNeed gameNeed;
	private String state;
	Clock clock;
	public Controller() {
		try {

			GsonBuilder builder=new	GsonBuilder().registerTypeAdapter(Heros.class, new AbstractAdapter<Heros>());
			builder.registerTypeAdapter(HeroPower.class, new AbstractAdapter<HeroPower>());
			builder.registerTypeAdapter(Card.class, new AbstractAdapter<Card>());
			builder.setLenient();
			gson=builder.create();
			frame=new MainFrame();
			loginPanel=new LoginPanel();
			frame.ChangePanel(loginPanel);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void changPanel(String x) {
		clock.setConnection(System.nanoTime());
		switch (x) {
		case "MENU":
			frame.ChangePanel(menuPanel);
			break;
		case "SHOP":
			frame.ChangePanel(shop);
			break;
		case "STATOS":
			frame.ChangePanel(statos);
			break;
		case "SETTING":
			frame.ChangePanel(settingPanel);
			break;
		case "COLLECTION":
			frame.ChangePanel(collectionPanel);
			break;
		case "PLAYSTART":
			frame.ChangePanel(playShow);
			break;
		case "PLAYEPANEL":
			frame.ChangePanel(playPanel);
			break;
		case "RANK":
			frame.ChangePanel(rankPanel);
			break;
		default:
			break;
		}	
	}
	public User getUser() {
		return user;
	}

	public void loginError(String message) {	
		JOptionPane.showMessageDialog(loginPanel, message);
	}
	public void setPlayer(String message) {
		StringReader stringReader=new StringReader(message+" ");
		user=gson.fromJson(new JsonReader(stringReader), User.class);
		clock=new Clock(user.getTocken());
		clock.setConnection(System.nanoTime());
		clock.start();
		try {
			rankPanel=new RankPanel(this);
			menuPanel=new MenuPanel(this);
			shop=new Shop(this);
			statos=new Statos(this);
			settingPanel=new SettingPanel(this);
			collectionPanel=new CollectionPanel(this);
			playShow=new StartPlayShow(this);
			playPanel=new PlayShow(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void shopNeed(String message) {
		clock.setConnection(System.nanoTime());
		StringReader reader=new StringReader(message);
		ShopNeeds sh=gson.fromJson(new JsonReader(reader), ShopNeeds.class);
		shop.update(sh.getCards(),sh.getBuyHeros(),sh.getHavenot());
		frame.repaint();
		frame.revalidate();
	}

	public void sellError(String message) {	
		clock.setConnection(System.nanoTime());
		JOptionPane.showMessageDialog(shop, message);
	}
	public void statosNeed(String message) {	
		clock.setConnection(System.nanoTime());
		StatosNeeds needs=gson.fromJson(message, StatosNeeds.class);
		statos.setDecks(needs.getDeck());	
	}
	public void collectionNeed(String message) {
		clock.setConnection(System.nanoTime());
		CollectionNeed need=gson.fromJson(message, CollectionNeed.class);
		collectionPanel.setHave(need.getHave());
		collectionPanel.setDeck(need.getDeck());
		collectionPanel.setDontHave(need.getDontHave());
		collectionPanel.setDeckinfo(need.getDeckInfo());
		collectionPanel.setEnemyDeck(need.getEnemydeck());
		collectionPanel.setEnemyHero(need.getEnemyHero());
		collectionPanel.setHeros(need.getHero());
		collectionPanel.updatePanel(this);
	}
	public void deckChange(String message) {	
		clock.setConnection(System.nanoTime());
		ChangInDeckResponse response=gson.fromJson(message, ChangInDeckResponse.class);
		collectionPanel.setDeck(response.getDeck());
		collectionPanel.setDeckinfo(response.getDeckInfo());
		collectionPanel.setEnemyDeck(response.getEnemydeck());
		collectionPanel.updatePanel(this);
	}
	public void collectioError(String message) {	
		clock.setConnection(System.nanoTime());
		JOptionPane.showMessageDialog(collectionPanel, message);
	}
	public void playError(String message) {		
		clock.setConnection(System.nanoTime());
		int x=	JOptionPane.showConfirmDialog(menuPanel, message);
		if(x==JOptionPane.OK_OPTION) {
			try {
				String 	message1= "GOCOLLECTION>>"+new Gson().toJson(new SaveAndExitRequest(getUser().getTocken()))+"#";
				Client.WriteMessage(message1);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	public void setGameNeed(String message) {
		clock.setConnection(System.nanoTime());
		gameNeed=gson.fromJson(message, GameNeed.class);
		playPanel.update(gameNeed.getText());
	}
	public void attackError(String message) {	
		clock.setConnection(System.nanoTime());
		JOptionPane.showMessageDialog(playPanel, message);	
	}
	public GameNeed getGameNeed() {
		return gameNeed;
	}	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	public void setTime(String message) {
		clock.setConnection(System.nanoTime());
		playPanel.getP().getJp().setString(message);
		playPanel.getP().getJp().setValue(Integer.parseInt(message));
		if(message.equals("40")||message.equals("41") ||message.equals("42")||message.equals("43")||message.equals("44")) {
			playPanel.getP().getJp().setString("HURRY UP");
		}
		if(	message.equals("59") && gameNeed.getTurnremind()%2==gameNeed.getMyturn()) {		
			try {
				String message1="NEXTTURN>>"+gson.toJson(new NextTurnRequest(user.getTocken()));
				Client.WriteMessage(message1);
			} catch (Exception e) {e.printStackTrace();
			}
		}
	}
	public void yes(String message) {
		clock.setConnection(System.nanoTime());	
	}
	public void message(String message) {
		clock.setConnection(System.nanoTime());	
		playPanel.getChat().getTextArea().setText(message);
	}

	public void setWatcher(String message) {
		clock.setConnection(System.nanoTime());	
		Type listOfMyClassObject = new TypeToken<ArrayList<String>>() {}.getType();
		StringReader reader=new StringReader(message);
		ArrayList<String> ss=gson.fromJson(new JsonReader(reader), listOfMyClassObject);
		playPanel.getP().setWatcher(ss);
		playPanel.getP().setWatcher();
	}

	public void setRankNeed(String message) {
		StringReader reader=new StringReader(message);
		RankNeed need=gson.fromJson(new JsonReader(reader), RankNeed.class);
		rankPanel.setTop(need.getTop());
		rankPanel.setMe(need.getMe());
		rankPanel.repaint();
		rankPanel.revalidate();
	}

}