package myListeners;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Cardspackage.Cards;
import GAME.Gamestate;
import grapic.CardShow;
import grapic.PlayPanel;
import playModel.Player;

public class EnemyBattlegrounCardListener implements MouseListener,MouseMotionListener{

	private PlayPanel panel;
	private Cards card;
	private CardShow x;
	public  EnemyBattlegrounCardListener(PlayPanel panel,Cards card, CardShow x) {
		this.panel=panel;
		this.card=card;
		this.x=x;
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if(panel.getRoundGame()%2==1) {
			if(card.isBattlecry()||!card.getUsedToAttack()) {		
				card.setBattlecry(false);
				card.setUsedToAttack(true);
				String ss="";
				try {
					ss="enemy     played   "+card.get_Name()+"\n";
				} catch (Exception e1) {}
				panel.getTextArea().append(ss);
				panel.updatePanel();	
			}
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {
		panel.updatePanel();
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		if(panel.getRoundGame()%2==1) {
			int newX = e.getX() + x.getX();
			int newY = e.getY() + x.getY();
			x.setBounds(newX, newY, 100	, 150);	
		}		
	}
	@Override
	public void mouseMoved(MouseEvent e) {}
}
