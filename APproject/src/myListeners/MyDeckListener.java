package myListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Cardspackage.Cards;
import grapic.CollectionPanel;

public class MyDeckListener implements MouseListener{
	Cards s;
	JLabel lp;
	private CollectionPanel p;
	public MyDeckListener(Cards s, JLabel lp, CollectionPanel p) {
		super();
		this.s = s;
		this.lp = lp;
		this.p = p;
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
		int x=JOptionPane.showConfirmDialog(null, "do you want to remove this from your deck",
				"remove from deck", JOptionPane.OK_CANCEL_OPTION);
		if(x==JOptionPane.OK_OPTION) {
			p.makeChangeInDeck(s, lp);
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseClicked(MouseEvent e) {}
}
