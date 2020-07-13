package myListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import GAME.Gamestate;
import grapic.PassivePanel;
import passives.Passive;
import playModel.Player;

public class PassiveListener implements MouseListener{
	private Passive index;
	private Player p;
	PassivePanel pas;
	public PassiveListener(Passive index, Player p,PassivePanel pas) {
		this.index = index;
		this.p=p;
		this.pas=pas;
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
			p.setPassive(index);
			JOptionPane.showMessageDialog(null,  p.getName()+"  passive chosed");
		} catch (Exception e1) {e1.printStackTrace();}
		pas.setVisible(false);
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

}
