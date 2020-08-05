package client.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.google.gson.Gson;

import client.Client;
import client.Controller;
import client.grapic.InfoPanel;
import client.grapic.Shop;
import client.grapic.StorePanel;
import client.model.Card;
import gameModel.requestAndREsponse.SellAndBuy;


public class SellCardListener implements MouseListener {

	private Card card;

	public SellCardListener(Card card) {
		this.card = card;
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {	}

	@Override
	public void mousePressed(MouseEvent e) {
		int j=JOptionPane.showConfirmDialog(null, "Do you want sell this card??"
				+ "\n you give "+card.gemCost()+" gem t. \n "
				+ "this card for "+card.getClass()+" class.\n"
				+"and rarity is : "+card.getRarity(), "Confirm", JOptionPane.OK_CANCEL_OPTION);
		if(j==JOptionPane.OK_OPTION) {
			try {
				String message="SELL>>"+new Gson().toJson(new SellAndBuy(card.getName(), Controller.getInsist().getUser().getTocken()))+"#";
				Client.WriteMessage(message);
			} catch (Exception e1) {e1.printStackTrace();}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

}
