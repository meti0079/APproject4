package client.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import Cardspackage.Card;
import client.grapic.CollectionPanel;
import client.grapic.MainFrame;
import client.grapic.Shop;
import game.Gamestate;
import game.Logger;

public class LockCardListener implements MouseListener {
	private Card card;
	public LockCardListener( Card card) {
		super();
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
//				Shop sh=new Shop((MainFrame)f);	
//				f.setContentPane(sh);

			} catch (Exception e1) {
				e1.printStackTrace();
			}
//			f.Update();
//			f.setLocationRelativeTo(null);
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {}

}
