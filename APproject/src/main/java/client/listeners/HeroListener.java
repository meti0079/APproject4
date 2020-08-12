package client.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import client.grapic.PlayPanel;
import server.cardspackage.Spell;
import server.gameModel.FileReader;
import server.interfaces.Visitor;
import server.playModel.PlayerModel;

public class HeroListener implements MouseListener{
private PlayerModel me;
private PlayerModel enemy;
private Visitor v;

	
	
	
	
	public HeroListener(PlayerModel me, PlayerModel enemy, Visitor v) {
	this.me = me;
	this.enemy = enemy;
	this.v = v;
}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {

//		if (PlayPanel.getRoundGame()==me.getTurn()) {
//			if(Gamestate.getinsist().getAttacker() instanceof Spell) {
//				if(((Spell)Gamestate.getinsist().getAttacker()).isNeedTarget())
//			}
//		}
		
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
