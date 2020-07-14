package myListeners;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;

import Cardspackage.Card;
import Cardspackage.Minion;
import GAME.Gamestate;
import grapic.CardShow;
import grapic.PlayPanel;
import grapic.ShowCardBigger;
import interfaces.Visitor;
import playModel.Mapper;
import playModel.PlayerModel;

public class BattlegrounCardListener implements MouseListener,MouseMotionListener{

	private PlayPanel panel;
	private Card card;
	private CardShow x;
	private PlayerModel me;
	private PlayerModel enemy;
	private Visitor v;
	ShowCardBigger sho;
	public  BattlegrounCardListener(PlayPanel panel,Card card, CardShow x, PlayerModel me, PlayerModel enemy, Visitor v) {
		this.panel=panel;
		this.card=card;
		this.x=x;
		this.me=me;
		this.enemy=enemy;
		this.v=v;
		sho =new ShowCardBigger(card);
		sho.setBounds(650, 350, 200, 300);
		panel.add(sho);
		sho.setVisible(false);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		sho.setVisible(true);
	}
	@Override
	public void mouseExited(MouseEvent e) {
		sho.setVisible(false);
	}
	@Override
	public void mousePressed(MouseEvent e) {
		sho.setVisible(false);
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		sho.setVisible(false);
		if(card.getUsedToAttack() ==false || (card).isRush()) {
		try {
			if(Mapper.getinsist().handleAttack(me, enemy, v, x.getX(), x.getY(), card)) {
				String 	ss=me.getName()+"     played   "+card.get_Name()+"\n";
				card.setUsedToAttack(true);
				panel.getTextArea().append(ss);	
			}else
				JOptionPane.showMessageDialog(panel, "target is not valid");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		}else {
			JOptionPane.showMessageDialog(panel, "cant attack with this card");
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
