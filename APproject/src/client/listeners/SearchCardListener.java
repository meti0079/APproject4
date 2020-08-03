package client.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import Cardspackage.Card;
import game.Gamestate;
import game.Logger;

public class SearchCardListener implements MouseListener {
	private Card s;
	 public SearchCardListener(Card card) {
		s=card;
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		try {
			int x=JOptionPane.showConfirmDialog(null, "do you want to add this to yor deck",
					"add card to deck", JOptionPane.OK_CANCEL_OPTION);
			if(x==JOptionPane.OK_OPTION) {
				if(Gamestate.getinsist().getPlayer().getMyDeck().addCardToDeck(s)){	
					Logger.getinsist().log(Gamestate.getinsist().getPlayer().get_name(), "add card to deck", "card is : "+s.get_Name());
				}else {
					Logger.getinsist().log(Gamestate.getinsist().getPlayer().get_name(), "cant add card to deck", "card is : "+s.get_Name());
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
	}
}
