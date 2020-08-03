package client.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import Cardspackage.Card;
import Cardspackage.Spell;
import client.grapic.CardShow;
import client.grapic.PlayPanel;
import client.grapic.ShowCardBigger;
import game.Gamestate;
import game.Logger;
import game.Player;
import interfaces.Visitor;
import playModel.Mapper;
import playModel.PlayerModel;

public class HandCardListener implements MouseListener,MouseMotionListener {
	private Card card;
	private PlayPanel panel;
	private CardShow x;
	private PlayerModel me;
	ShowCardBigger sho;

	public HandCardListener(PlayPanel panel,Card card, CardShow x, PlayerModel p) {
		this.card=card;
		this.panel=panel;
		this.x=x;
		this.me=p;
		sho =new ShowCardBigger(card);
		sho.setBounds(650, 350, 200, 300);
		panel.add(sho);		
		sho.setVisible(false);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		sho.setVisible(false);
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
		sho.setVisible(false);
		try {
		if(panel.getRoundGame()==60-me.getTurn() && me.getChanges()<3) {
				Mapper.getinsist().changeCartAtFirst(me, card);
			panel.updatePanel();
		}
		} catch (Exception e1) {e1.printStackTrace();}
		}
	@Override
	public void mouseExited(MouseEvent e) {
		sho.setVisible(false);	
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	sho.setVisible(true);
	}
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