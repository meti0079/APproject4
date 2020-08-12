package client.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import com.google.gson.Gson;
import client.ClientMain;
import gameModel.requestAndREsponse.ChangeBattlegroundThem;

public class SettingBackGrounListener implements MouseListener {
	private int s;
	int tocken;

	public SettingBackGrounListener(int s, int tocken) {
		this.tocken=tocken;
		this.s = s;
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent arg0) {
		try {
			String message="CHANGEBATTLEGROUND>>"+new Gson().toJson(new ChangeBattlegroundThem(tocken, "nattle"+s+".jpg"))+"#";
			ClientMain.WriteMessage(message);
		} catch (Exception e) {e.printStackTrace();}
		JOptionPane.showMessageDialog(null, "choosed");
	}
	@Override
	public void mouseExited(MouseEvent arg0) {	}
	@Override
	public void mouseEntered(MouseEvent arg0) {	}
	@Override
	public void mouseClicked(MouseEvent arg0) {}
}
