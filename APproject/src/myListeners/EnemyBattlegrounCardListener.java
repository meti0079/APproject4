package myListeners;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Cardspackage.Cards;
import GAME.Gamestate;
import grapic.PlayPanel;

public class EnemyBattlegrounCardListener implements MouseListener{

	private PlayPanel panel;
	private Cards card;

	public  EnemyBattlegrounCardListener(PlayPanel panel,Cards card) {
		this.panel=panel;
		this.card=card;
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if(panel.getRoundGame()%2==1) {
		if(card.isBattlecry()||!card.getUsedToAttack()) {		
			card.setBattlecry(false);
			card.setUsedToAttack(true);
			String s="";
			try {
				 s="enemy    played   "+card.get_Name()+"\n";
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			panel.getTextArea().append(s);
			panel.updatePanel();	
		}
	}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
