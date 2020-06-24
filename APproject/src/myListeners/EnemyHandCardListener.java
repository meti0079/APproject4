package myListeners;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import Cardspackage.Cards;
import GAME.Gamestate;
import GAME.Logger;
import grapic.PlayPanel;

public class EnemyHandCardListener implements MouseListener{

	private Cards card;
	private PlayPanel panel;
	public EnemyHandCardListener(PlayPanel panel,Cards card) {
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
		if(panel.getRoundGame()%2==1) {
			if(!card.getUsedToAttack())
				if(panel.getRoundGame()==59&&panel.getChanges1()<3) {
					panel.getDeck2().add(card);
					panel.getHand2().remove(card);
					panel.getHand2().add(panel.getDeck2().get(0));
					panel.getHand2().get(panel.getHand2().size()-1).setUsedToAttack(true);
					panel.getDeck2().remove(0);
					panel.updatePanel();
					panel.setChanges1(panel.getChanges1()+1);
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
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
