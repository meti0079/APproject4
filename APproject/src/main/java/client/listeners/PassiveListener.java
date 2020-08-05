package client.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import client.grapic.PassivePanel;
import client.grapic.PlayPanel;
import game.Gamestate;
import game.Logger;
import passives.Passive;
import playModel.PlayerModel;

public class PassiveListener implements MouseListener{
	private Passive index;
	private PlayerModel p;
	PassivePanel pas;
	public PassiveListener(Passive index, PlayerModel p,PassivePanel pas) {
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
			Logger.getinsist().log(Gamestate.getinsist().getPlayer().get_name(), p.getName(), "choosed Passive"+p.getPassive().getName());
			JOptionPane.showMessageDialog(null,  p.getName()+"  passive chosed");
		} catch (Exception e1) {e1.printStackTrace();}
		pas.setVisible(false);
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

}
