package myListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Cardspackage.Cards;
import GAME.Gamestate;
import GAME.Logger;
import grapic.InfoPanel;
import grapic.Shop;
import grapic.StorePanel;

public class SellCardListener implements MouseListener {

	private StorePanel store;
	private JPanel x;
	private JLabel cardLable;
	private InfoPanel inf;
	private Cards card;
	private Logger log;
	private Shop shop;


	public SellCardListener(StorePanel store, JPanel x, JLabel cardLable, InfoPanel inf, Cards card, Logger log,
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
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int j=JOptionPane.showConfirmDialog(null, "Do you want sell this card??"
				+ "\n you give "+shop.gemCost(card)+" gem t. \n "
				+ "this card for "+card.get_Class()+" class.\n"
				+"and rarity is : "+card.get_Rarity(), "Confirm", JOptionPane.OK_CANCEL_OPTION);
		if(j==JOptionPane.OK_OPTION) {
			try {
				if(Gamestate.getinsist().getPlayer().sellaCard(card)) {			
					Gamestate.getinsist().getPlayer().gem+=shop.gemCost(card);
					x.remove(cardLable);
					shop.setCard(x, store);
					x.repaint();	
					inf.repaint();
					store.update();
					x.revalidate();
					store.revalidate();
					store.repaint();
					log.log(Gamestate.getinsist().getPlayer().get_name(), "sell card", card.get_Name());	
				}else {
					log.log(Gamestate.getinsist().getPlayer().get_name(), "error", "cant sell card : "+card.get_Name());
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
