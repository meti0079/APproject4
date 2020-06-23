package myListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import Cardspackage.Cards;
import GAME.Gamestate;
import GAME.Logger;
import grapic.Collection_herospanel;

public class DeckCardListener implements MouseListener {

	private Collection_herospanel deckPanel;
	private Cards card;
	private JLabel cardLable;

	public DeckCardListener(Collection_herospanel deckPanel, Cards card,JLabel cardJLabel) {
		super();
		this.deckPanel = deckPanel;
		this.card = card;
		this.cardLable=cardJLabel;
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
		int x=JOptionPane.showConfirmDialog(null, "do you want to remove this from your deck",
				"remove from deck", JOptionPane.OK_CANCEL_OPTION);
		if(x==JOptionPane.OK_OPTION) {
			try {
				Gamestate.getinsist().getPlayer().getMyDeck().addUsethisDeck(0);
				Gamestate.getinsist().getPlayer().getMyDeck().addWin(0);
				Logger.getinsist().log(Gamestate.getinsist().getPlayer().get_name(), "remove card from deck", card.get_Name());
				Gamestate.getinsist().getPlayer().getMyDeck().getDeck().remove(card);
				deckPanel.remove(cardLable);
				deckPanel.repaint();
				deckPanel.revalidate();
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
