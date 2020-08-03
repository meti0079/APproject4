package client.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Cardspackage.Card;
import client.grapic.InfoPanel;
import client.grapic.Shop;
import client.grapic.StorePanel;
import game.Gamestate;
import game.Logger;

public class SellCardListener implements MouseListener {

	private StorePanel store;
	private JPanel x;
	private JLabel cardLable;
	private InfoPanel inf;
	private Card card;
	private Logger log;
	private Shop shop;


	public SellCardListener(StorePanel store, JPanel x, JLabel cardLable, InfoPanel inf, Card card, Logger log,
			Shop shop) {
		super();
		this.store = store;
		this.x = x;
		this.cardLable = cardLable;
		this.inf = inf;
		this.card = card;
		this.log = log;
		this.shop = shop;
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
				+ "this card for "+card.get_Class()+" class.\n"
				+"and rarity is : "+card.get_Rarity(), "Confirm", JOptionPane.OK_CANCEL_OPTION);
		if(j==JOptionPane.OK_OPTION) {
			try {
				if(Gamestate.getinsist().getPlayer().sellaCard(card)) {			
					Gamestate.getinsist().getPlayer().gem+=card.gemCost();
					shop.setCard(x, store);
					inf.repaint();
					x.remove(cardLable);
					x.repaint();	
					x.revalidate();
					store.update();
					log.log(Gamestate.getinsist().getPlayer().get_name(), "sell card", card.get_Name());	
				}else {
					log.log(Gamestate.getinsist().getPlayer().get_name(), "error", "cant sell card : "+card.get_Name());
				}
			} catch (Exception e1) {e1.printStackTrace();}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

}
