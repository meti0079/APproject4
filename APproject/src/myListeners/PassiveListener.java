package myListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import GAME.Gamestate;

public class PassiveListener implements MouseListener{
private int index;

	public PassiveListener(int index) {
	this.index = index;
}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {	}

	@Override
	public void mousePressed(MouseEvent e) {
		try {
			Gamestate.getinsist().setPlayPassive(Gamestate.getinsist().getPassives().get(index));
			JOptionPane.showMessageDialog(null, "passive chosed");
		} catch (Exception e1) {e1.printStackTrace();}
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

}
