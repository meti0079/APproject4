package myListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import Cardspackage.Card;
import GAME.Gamestate;
import GAME.Logger;
import grapic.CollectionPanel;
import grapic.MainFrame;
import grapic.Shop;

public class LockCardListener implements MouseListener {
	private MainFrame f;
	private Card card;
	public LockCardListener(MainFrame f, Card card) {
		super();
		this.f = f;
		this.card = card;
	}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {	}
	@Override
	public void mousePressed(MouseEvent e) {
		int x=JOptionPane.showConfirmDialog(null, "you dont have this card!! do yo want to buy this??",
				"confirm", JOptionPane.OK_CANCEL_OPTION);
		if(x==JOptionPane.OK_OPTION) {
			try {
				Logger.getinsist().log(Gamestate.getinsist().getPlayer().get_name(), "go to shop to buy card", "card is : "+card.get_Name());
				Shop sh=new Shop((MainFrame)f);	
				f.setContentPane(sh);

			} catch (Exception e1) {
				e1.printStackTrace();
			}
			f.Update();
			f.setLocationRelativeTo(null);
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {}

}
