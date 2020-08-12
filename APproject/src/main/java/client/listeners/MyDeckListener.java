package client.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.JOptionPane;
import com.google.gson.Gson;
import client.ClientMain;
import client.model.Card;
import gameModel.requestAndREsponse.AddCardToDeck;

public class MyDeckListener implements MouseListener{
	Card s;
	int tocken;
	public MyDeckListener(Card s, int tocken) {
		this.tocken=tocken;
		this.s = s;
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
		int x=JOptionPane.showConfirmDialog(null, "do you want to remove this from your deck",
				"remove from deck", JOptionPane.OK_CANCEL_OPTION);
		if(x==JOptionPane.OK_OPTION) {
			try {
				String 	message= "REMOVEFROMDECK>>"+new Gson().toJson(new AddCardToDeck(tocken,s.getName()))+"#";
				ClientMain.WriteMessage(message);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseClicked(MouseEvent e) {}
}
