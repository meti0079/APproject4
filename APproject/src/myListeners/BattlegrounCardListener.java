package myListeners;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Cardspackage.Card;
import GAME.Gamestate;
import grapic.CardShow;
import grapic.PlayPanel;
import interfaces.Visitor;
import playModel.Mapper;
import playModel.Player;

public class BattlegrounCardListener implements MouseListener,MouseMotionListener{

	private PlayPanel panel;
	private Card card;
	private CardShow x;
	private Player me;
	private Player enemy;
	private Visitor v;
	public  BattlegrounCardListener(PlayPanel panel,Card card, CardShow x, Player me, Player enemy, Visitor v) {
		this.panel=panel;
		this.card=card;
		this.x=x;
		this.me=me;
		this.enemy=enemy;
		this.v=v;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if(card.getUsedToAttack() ==false) {
		try {
			if(Mapper.getinsist().handleAttack(me, enemy, v, x.getX(), x.getY(), card)) {
				String 	ss=me.getName()+"     played   "+card.get_Name()+"\n";
				card.setUsedToAttack(true);
				panel.getTextArea().append(ss);	
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		}
		panel.updatePanel();	
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		if(panel.getRoundGame()%2==me.getTurn()) {
			int newX = e.getX() + x.getX();
			int newY = e.getY() + x.getY();
			x.setBounds(newX, newY, 100	, 150);	
		}		
	}
	@Override
	public void mouseMoved(MouseEvent e) {}
}
