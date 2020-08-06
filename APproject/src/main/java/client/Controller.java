package client;

import java.io.StringReader;
import javax.swing.JOptionPane;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import client.grapic.CollectionPanel;
import client.grapic.LoginPanel;
import client.grapic.MainFrame;
import client.grapic.MenuPanel;
import client.grapic.SettingPanel;
import client.grapic.Shop;
import client.grapic.Statos;
import client.model.User;
import gameModel.requestAndREsponse.ChangInDeckResponse;
import gameModel.requestAndREsponse.CollectionNeed;
import gameModel.requestAndREsponse.ShopNeeds;
import gameModel.requestAndREsponse.StatosNeeds;
public class Controller {

	private static Controller controller;
	private MainFrame frame;
	private LoginPanel loginPanel;
	private MenuPanel menuPanel;
	private User user;
	private Gson gson;
	private Shop shop;
	private Statos statos;
	private SettingPanel settingPanel;
	private CollectionPanel collectionPanel;


	private Controller() {
		try {
			gson=new GsonBuilder().setLenient().create();
			frame=new MainFrame();
			loginPanel=new LoginPanel();
			frame.ChangePanel(loginPanel);
			menuPanel=new MenuPanel();
			shop=new Shop();
			statos=new Statos();
			settingPanel=new SettingPanel();
			collectionPanel=new CollectionPanel();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void changPanel(String x) {
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

		default:
			break;
		}	
	}





























	public User getUser() {
		return user;
	}
	public static Controller getInsist() {
		if(controller==null)
			controller=new  Controller();
		return controller;
	}
	public void loginError(String message) {
		for(int i=0;i<message.length();i++)
			if(message.charAt(i)=='}') {
				message=message.substring(0,i+1);
			}
		JOptionPane.showMessageDialog(loginPanel, message);
	}
	public void setPlayer(String message) {
		StringReader stringReader=new StringReader(message+" ");
		user=gson.fromJson(new JsonReader(stringReader), User.class);
	}

	public void shopNeed(String message) {
		ShopNeeds sh=gson.fromJson(message, ShopNeeds.class);
		shop.setMyCards(sh.getCards());
		shop.setHeros(sh.getBuyHeros());
		shop.getStorePanel().setBuyCard(sh.getHavenot());
	}

	public void updatePanels(String message) {
		switch (message) {
		case "SHOP":
			shop.update();
			break;
		case "":

			break;

		default:
			break;
		}


	}

	public void sellError(String message) {
		JOptionPane.showMessageDialog(shop, message);
	}

	public void statosNeed(String message) {
		StatosNeeds needs=gson.fromJson(message, StatosNeeds.class);
		statos.setDecks(needs.getDeck());	
	}

	public void collectionNeed(String message) {
		CollectionNeed need=gson.fromJson(message, CollectionNeed.class);
		collectionPanel.setHave(need.getHave());
		collectionPanel.setDeck(need.getDeck());
		collectionPanel.setDontHave(need.getDontHave());
		collectionPanel.setDeckinfo(need.getDeckInfo());
		collectionPanel.setEnemyDeck(need.getEnemydeck());
		collectionPanel.setEnemyHero(need.getEnemyHero());
		collectionPanel.setHeros(need.getHero());
		collectionPanel.updatePanel();
	}

	public void deckChange(String message) {
		ChangInDeckResponse response=gson.fromJson(message, ChangInDeckResponse.class);
		collectionPanel.setDeck(response.getDeck());
		collectionPanel.setDeckinfo(response.getDeckInfo());
		collectionPanel.setEnemyDeck(response.getEnemydeck());
		collectionPanel.updatePanel();
	}

	public void collectioError(String message) {
		JOptionPane.showMessageDialog(collectionPanel, message);

	}
}