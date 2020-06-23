package myListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Cardspackage.Cards;
import GAME.Gamestate;
import grapic.PlayPanel;

public class MyBattlegroundCardListener implements MouseListener {

	private PlayPanel panel;
	private Cards card;

	public  MyBattlegroundCardListener(PlayPanel panel,Cards card) {
		this.panel=panel;
		this.card=card;
	}


	@Override
	public void mouseClicked(MouseEvent e) {
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
