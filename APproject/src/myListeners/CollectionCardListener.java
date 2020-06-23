package myListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import Cardspackage.Cards;
import GAME.Gamestate;
import GAME.Logger;
import grapic.CollectionPanel;
import grapic.Collection_deck;
import grapic.Collection_herospanel;

public class CollectionCardListener implements MouseListener {

	private Cards card;
	private Collection_herospanel deckPanel;
	private CollectionPanel panel;
	private Collection_deck deckbord;



	public CollectionCardListener(Cards card, Collection_herospanel deckPanel, CollectionPanel panel,
			Collection_deck deckbord) {
		super();
		this.card = card;
		this.deckPanel = deckPanel;
		this.panel = panel;
		this.deckbord = deckbord;
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
		int x=JOptionPane.showConfirmDialog(null, "do you want to add this to your deck",
				"add card to deck", JOptionPane.OK_CANCEL_OPTION);
		if(x==JOptionPane.OK_OPTION) {
			try {
				Gamestate.getinsist().getPlayer().getMyDeck().addUsethisDeck(0);
				Gamestate.getinsist().getPlayer().getMyDeck().addWin(0);
				if(Gamestate.getinsist().getPlayer().getMyDeck().addCardToDeck(card)){
					Logger.getinsist().log(Gamestate.getinsist().getPlayer().get_name(), "add card to deck", card.get_Name());

					panel.setdeck();
					deckPanel.repaint();
					deckPanel.revalidate();
					deckbord.updateBut(deckPanel, panel);
					deckbord.repaint();
					deckbord.revalidate();
				}
			}catch (Exception e1) {
			}

		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
