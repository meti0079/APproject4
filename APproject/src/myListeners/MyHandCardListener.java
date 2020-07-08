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

public class MyHandCardListener implements MouseListener,MouseMotionListener {
	private Cards card;
	private PlayPanel panel;
	private CardShow x;
	private Player me;
	public MyHandCardListener(PlayPanel panel,Cards card, CardShow x, Player p) {
		this.card=card;
		this.panel=panel;
		this.x=x;
		this.me=p;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(PlayPanel.getRoundGame()%2==0) {	
			if(!card.getUsedToAttack()){
				if(panel.addTobattleground(card,x.getX(), x.getY())) {
					try {
					Mapper.getinsist().addUse(card);
					panel.updatePanel();
						Logger.getinsist().log(Gamestate.getinsist().getPlayer().get_name(), Gamestate.getinsist().getPlayer().get_name(), card.get_Name());
					} catch (Exception e1) {}						
				}else {
					panel.updatePanel();
				}
			}else {
				panel.updatePanel();
			}
		}					
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if(panel.getRoundGame()==60 && me.getChanges()<3) {
			me.getDeck().add(card);
			me.getHand().remove(card);
			me.getHand().add(me.getDeck().get(0));
			me.getHand().get(me.getHand().size()-1).setUsedToAttack(true);
			me.getDeck().remove(0);
			panel.updatePanel();
			me.setChanges(me.getChanges()+1);
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
		if(panel.getRoundGame()%2==0) {
			int newX = e.getX() + x.getX();
			int newY = e.getY() + x.getY();
			x.setBounds(newX, newY, 100	, 150);	
		}					
	}

}