package myListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Cardspackage.Cards;
import GAME.Gamestate;
import grapic.CardShow;
import grapic.PlayPanel;

public class MyBattlegroundCardListener implements MouseListener,MouseMotionListener {

	private PlayPanel panel;
	private Cards card;
	private CardShow x;

	public  MyBattlegroundCardListener(PlayPanel panel,Cards card ,CardShow x) {
		this.panel=panel;
		this.card=card;
		this.x=x;
		this.x.addMouseMotionListener(this);
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if(panel.getRoundGame()%2==0) {
		if(card.isBattlecry()||!card.getUsedToAttack()) {		
			card.setBattlecry(false);
			card.setUsedToAttack(true);
			String s="";
			try {
				 s=Gamestate.getinsist().getPlayer().get_name()+"     played   "+card.get_Name()+"\n";
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
	
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		if(panel.getRoundGame()%2==0) {
			int newX = e.getX() + x.getX();
			int newY = e.getY() + x.getY();
			x.setBounds(newX, newY, 100	, 150);	
		}		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
