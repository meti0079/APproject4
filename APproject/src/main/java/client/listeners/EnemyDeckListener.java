package client.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.JOptionPane;
import com.google.gson.Gson;
import client.ClientMain;
import client.model.Card;
import gameModel.requestAndREsponse.AddCardToDeck;

public class EnemyDeckListener implements MouseListener {
	Card s;
	int tocken;

	public EnemyDeckListener(Card s, int to) {
		this.s=s;
		this.tocken=to;

	}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {
		int x=JOptionPane.showConfirmDialog(null, "do you want to remove this from enemy deck",
				"remove from deck", JOptionPane.OK_CANCEL_OPTION);
		if(x==JOptionPane.OK_OPTION) {
			try {
				String 	message= "REMOVEFROMENEMYDECK>>"+new Gson().toJson(new AddCardToDeck(tocken,s.getName()))+"#";
				ClientMain.WriteMessage(message);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
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
