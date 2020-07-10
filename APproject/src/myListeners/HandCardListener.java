package myListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import Cardspackage.Cards;
import GAME.Gamestate;
import GAME.Logger;
import GAME.Players;
import grapic.CardShow;
import grapic.PlayPanel;
import playModel.Mapper;
import playModel.Player;

public class HandCardListener implements MouseListener,MouseMotionListener {
	private Cards card;
	private PlayPanel panel;
	private CardShow x;
	private Player me;
	public HandCardListener(PlayPanel panel,Cards card, CardShow x, Player p) {
		this.card=card;
		this.panel=panel;
		this.x=x;
		this.me=p;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(PlayPanel.getRoundGame()%2==me.getTurn()) {	
			if(!card.getUsedToAttack()){
				try {
				if(panel.addTobattleground(card,x.getX(), x.getY())) {
					Mapper.getinsist().addUse(card);
					panel.updatePanel();
						Logger.getinsist().log(Gamestate.getinsist().getPlayer().get_name(), Gamestate.getinsist().getPlayer().get_name(), card.get_Name());
				}else {
					panel.updatePanel();
				}
				} catch (Exception e1) {}						
			}else {
				panel.updatePanel();
			}
		}					
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if(panel.getRoundGame()==60-me.getTurn() && me.getChanges()<3) {
			try {
				Mapper.getinsist().changeCartAtFirst(me, card);
			} catch (Exception e1) {e1.printStackTrace();}
			panel.updatePanel();
		}
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