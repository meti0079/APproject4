package client.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import game.Gamestate;

public class SettingBackGrounListener implements MouseListener {
	private int s;
	public SettingBackGrounListener(int s) {
		this.s = s;
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent arg0) {
		try {
			setBattlebackGround(1);
		} catch (Exception e) {e.printStackTrace();}
		JOptionPane.showMessageDialog(null, "choosed");
	}
	@Override
	public void mouseExited(MouseEvent arg0) {	}
	@Override
	public void mouseEntered(MouseEvent arg0) {	}
	@Override
	public void mouseClicked(MouseEvent arg0) {}
	public void setBattlebackGround(int a) throws Exception {
		String s="nattle"+a+".jpg";
		Gamestate.getinsist().setBackBattleground(s);
	}
}
