package myListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Cardspackage.Card;
import grapic.CollectionPanel;

public class EnemyDeckListener implements MouseListener {
	Card s;
	JLabel lp;
	private CollectionPanel p;
	public EnemyDeckListener(Card s, JLabel lp,CollectionPanel p) {
	this.p=p;
	this.s=s;
	this.lp=lp;		
	}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {
		int x=JOptionPane.showConfirmDialog(null, "do you want to remove this from enemy deck",
				"remove from deck", JOptionPane.OK_CANCEL_OPTION);
		if(x==JOptionPane.OK_OPTION) {
			p.makeChangeInEnemyDeck(s, lp);
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseClicked(MouseEvent e) {}
}
