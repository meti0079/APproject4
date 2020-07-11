package myListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import Cardspackage.Card;
import Cardspackage.Spell;
import GAME.Gamestate;
import GAME.Logger;
import GAME.Players;
import grapic.CardShow;
import grapic.PlayPanel;
import interfaces.Visitor;
import playModel.Mapper;
import playModel.Player;

public class HandCardListener implements MouseListener,MouseMotionListener {
	private Card card;
	private PlayPanel panel;
	private CardShow x;
	private Player me;
	private Player enemy;
	private Visitor v;
	public HandCardListener(PlayPanel panel,Card card, CardShow x, Player p, Player enemy, Visitor v) {
		this.card=card;
		this.panel=panel;
		this.x=x;
		this.me=p;
		this.enemy=enemy;
		this.v=v;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if(PlayPanel.getRoundGame()%2==me.getTurn()) {	
				try {
				if(panel.addTobattleground(card,x.getX(), x.getY())) {
					Mapper.getinsist().addUse(card);
					panel.updatePanel();
					Logger.getinsist().log(Gamestate.getinsist().getPlayer().get_name(), Gamestate.getinsist().getPlayer().get_name(), card.get_Name());
				}else {
					panel.updatePanel();
				}
				} catch (Exception e1) {}						
			
		}					
	}
	@Override
	public void mousePressed(MouseEvent e) {
		try {
		if(panel.getRoundGame()==60-me.getTurn() && me.getChanges()<3) {
				Mapper.getinsist().changeCartAtFirst(me, card);
			panel.updatePanel();
		}
		} catch (Exception e1) {e1.printStackTrace();}
		}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseMoved(MouseEvent e) {}		
	@Override
	public void mouseDragged(MouseEvent e) {
		if(PlayPanel.getRoundGame()%2==me.getTurn()) {
			int newX = e.getX() + x.getX();
			int newY = e.getY() + x.getY();
			x.setBounds(newX, newY, 100	, 150);	
		}					
	}

}