package myListeners;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import Cardspackage.Cards;
import GAME.Gamestate;
import GAME.Logger;
import grapic.CardShow;
import grapic.PlayPanel;
import playModel.Player;

public class EnemyHandCardListener implements MouseListener,MouseMotionListener{

	private Cards card;
	private PlayPanel panel;
	private Player enemy;
	private CardShow x;
	public EnemyHandCardListener(PlayPanel panel,Cards card, Player enemy, CardShow x) {
		this.card=card;
		this.panel=panel;
		this.enemy=enemy;
		this.x=x;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(panel.getRoundGame()%2==1) {	
			if(!card.getUsedToAttack()){
				if(panel.addTobattleground(card,x.getX(), x.getY())) {
					try {
						Logger.getinsist().log(Gamestate.getinsist().getPlayer().get_name(), "enemy", card.get_Name());
					} catch (Exception e1) {}						
				}	panel.updatePanel();
			}
		}					
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(panel.getRoundGame()==59 && enemy.getChanges()<3) {
			enemy.getDeck().add(card);
			enemy.getHand().remove(card);
			enemy.getHand().add(enemy.getDeck().get(0));
			enemy.getHand().get(enemy.getHand().size()-1).setUsedToAttack(true);
			enemy.getDeck().remove(0);
			panel.updatePanel();
			enemy.setChanges(enemy.getChanges()+1);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseMoved(MouseEvent e) {}
	@Override
	public void mouseDragged(MouseEvent e) {
		if(panel.getRoundGame()%2==1) {
			int newX = e.getX() + x.getX();
			int newY = e.getY() + x.getY();
			x.setBounds(newX, newY, 100	, 150);	
		}					
	}
}
