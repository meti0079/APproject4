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
import client.grapic.PlayShow;
import client.grapic.SettingPanel;
import client.grapic.Shop;
import client.grapic.StartPlayShow;
import client.grapic.Statos;
import client.model.User;
import gameModel.requestAndREsponse.ChangInDeckResponse;
import gameModel.requestAndREsponse.CollectionNeed;
import gameModel.requestAndREsponse.SaveAndExitRequest;
import gameModel.requestAndREsponse.ShopNeeds;
import gameModel.requestAndREsponse.StatosNeeds;
import gameModel.requestAndREsponse.gameNeed;
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
	private gameNeed gameNeed;
	private String state;





	public Controller() {
		try {
			gson=new GsonBuilder().setLenient().create();
			frame=new MainFrame();
			loginPanel=new LoginPanel();
			frame.ChangePanel(loginPanel);
			
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
		case "PLAYSTART":
			frame.ChangePanel(playShow);
			break;

			
			
			
			
		default:
			break;
		}	
	}





























	public User getUser() {
		return user;
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
		try {
			menuPanel=new MenuPanel(this);
		shop=new Shop(this);
		statos=new Statos(this);
		settingPanel=new SettingPanel(this);
		collectionPanel=new CollectionPanel(this);
		playShow=new StartPlayShow(this);
		playPanel=new PlayShow(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void shopNeed(String message) {
		StringReader reader=new StringReader(message);
		System.out.println(message);
		ShopNeeds sh=gson.fromJson(new JsonReader(reader), ShopNeeds.class);
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
		collectionPanel.updatePanel(this);
	}
	public void deckChange(String message) {
		ChangInDeckResponse response=gson.fromJson(message, ChangInDeckResponse.class);
		collectionPanel.setDeck(response.getDeck());
		collectionPanel.setDeckinfo(response.getDeckInfo());
		collectionPanel.setEnemyDeck(response.getEnemydeck());
		collectionPanel.updatePanel(this);
	}
	public void collectioError(String message) {
		JOptionPane.showMessageDialog(collectionPanel, message);
	}
	public void playError(String message) {
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
		gameNeed=gson.fromJson(message, gameNeed.getClass());
		playPanel.update(gameNeed.getText());
	}
	public void attackError(String message) {
		JOptionPane.showMessageDialog(playPanel, message);	
	}
	public gameNeed getGameNeed() {
		return gameNeed;
	}	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

}