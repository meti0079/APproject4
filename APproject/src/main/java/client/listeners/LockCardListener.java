package client.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import com.google.gson.Gson;
import client.Client;
import client.Controller;
import gameModel.requestAndREsponse.SaveAndExitRequest;

public class LockCardListener implements MouseListener {
	public LockCardListener() {

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
				String 	message= "GOSHOP>>"+new Gson().toJson(new SaveAndExitRequest(Controller.getInsist().getUser().getTocken()))+"#";
				Client.WriteMessage(message);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {}

}
