package myListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import Cardspackage.Cards;
import GAME.Gamestate;
import grapic.Collection_herospanel;
import grapic.InfoPanel;
import grapic.Shop;
import grapic.StorePanel;

public class BuyCardListener implements MouseListener {
	private Shop shop;
	private StorePanel store;
	private JLabel cardLabl;
	private Cards card;
	private InfoPanel inf;
	private Collection_herospanel sellPanel;
	
	public BuyCardListener(Shop shop, StorePanel store, JLabel cardLabl, Cards card,InfoPanel inf, Collection_herospanel sellpanel) {
		super();
		this.sellPanel=sellpanel;
		this.inf=inf;
		this.shop = shop;
		this.store = store;
		this.cardLabl = cardLabl;
		this.card = card;
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
		int j=JOptionPane.showConfirmDialog(null, "Do you want buy this card??"
				+ "\nyou have to spend "+card.gemCost()+" gem to buy this card.\n "
				+ "this card for "+card.get_Class()+" class.\n"
				+"and rarity is : "+card.get_Rarity(), "Confirm", JOptionPane.OK_CANCEL_OPTION);
		if(j==JOptionPane.OK_OPTION) {
			try {
				if(Gamestate.getinsist().getPlayer().gem>=card.gemCost()) {
					Gamestate.getinsist().getPlayer().buyaCard(card);
					Gamestate.getinsist().getPlayer().gem-=card.gemCost();
					store.remove(cardLabl);
					store.repaint();	
					inf.repaint();
					shop.setCard(sellPanel,store);
					shop.repaint();
					shop.revalidate();
					try {
						GAME.Logger.getinsist().log(Gamestate.getinsist().getPlayer().get_name(), "buy card", card.get_Name());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}else {
				
						GAME.Logger.getinsist().log(Gamestate.getinsist().getPlayer().get_name(), "error ", "dont have enogh gem to buy : "+card.get_Name());
				
					JOptionPane.showMessageDialog(null, "you dont have enough gem", "Erorr", JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
