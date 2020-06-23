package myListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import Cardspackage.Cards;
import GAME.Gamestate;
import GAME.Logger;
import grapic.PlayPanel;

public class MyHandCardListener implements MouseListener {
	private Cards card;
	private PlayPanel panel;
	public MyHandCardListener(PlayPanel panel,Cards card) {
		this.card=card;
		this.panel=panel;
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
		if(!card.getUsedToAttack())
			if(panel.getRoundGame()==0&&panel.getChanges()<3) {
				panel.getDeck().add(card);
				panel.getHand().remove(card);
				panel.getHand().add(panel.getDeck().get(0));
				panel.getHand().get(panel.getHand().size()-1).setUsedToAttack(true);
				panel.getDeck().remove(0);
				panel.updatePanel();
				panel.setChanges(panel.getChanges()+1);
			}else {
				panel.addUse(card);
				panel.addTobattleground(card);
				panel.updatePanel();
				try {
					Logger.getinsist().log(Gamestate.getinsist().getPlayer().get_name(), Gamestate.getinsist().getPlayer().get_name(), card.get_Name());
				} catch (IOException e1) {
					e1.printStackTrace();
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
