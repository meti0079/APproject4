package myListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import Cardspackage.Cards;
import GAME.Gamestate;
import GAME.Logger;
import grapic.CollectionPanel;
import grapic.MainFrame;
import grapic.Shop;

public class LockCardListener implements MouseListener {
private MainFrame f;
private Cards card;
private CollectionPanel panel;
public LockCardListener(MainFrame f, Cards card, CollectionPanel panel) {
	super();
	this.panel=panel;
	this.f = f;
	this.card = card;
}
@Override
public void mouseClicked(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void mousePressed(MouseEvent e) {
	int x=JOptionPane.showConfirmDialog(null, "you dont have this card!! do yo want to buy this??",
			"confirm", JOptionPane.OK_CANCEL_OPTION);
	if(x==JOptionPane.OK_OPTION) {
		try {
			Logger.getinsist().log(Gamestate.getinsist().getPlayer().get_name(), "go to shop to buy card", "card is : "+card.get_Name());
			Shop sh=new Shop((MainFrame)f);
			f.remove(panel);	
			f.setContentPane(sh);
			f.repaint();
			f.revalidate();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		f.pack();
		f.setLocationRelativeTo(null);
	}
}
@Override
public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

}
