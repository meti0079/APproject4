package client.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import com.google.gson.Gson;
import client.Client;
import client.grapic.CardShow;
import client.grapic.PlayPanel;
import client.grapic.ShowCardBigger;
import client.model.Card;
import gameModel.requestAndREsponse.AttackRequest;
import gameModel.requestAndREsponse.changeCardRequest;

public class HandCardListener implements MouseListener,MouseMotionListener {
	private Card card;
	private CardShow x;
	ShowCardBigger sho;
	int round;
	int turn;
	PlayPanel panel;
	int tocken;

	public HandCardListener(PlayPanel panel,Card card, CardShow x,int round, int turn, int tocken) {
		this.round=round;
		this.tocken=tocken;
		this.card=card;
		this.x=x;
		sho =new ShowCardBigger(card);
		sho.setBounds(650, 350, 200, 300);
		panel.add(sho);		
		sho.setVisible(false);
		this.panel=panel;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		sho.setVisible(false);
		if(round!=60)
		if(round%2==turn) {	
			try {
				String message="ADDTOBATTLEGROUND>>"+new Gson().toJson(new AttackRequest(tocken, x.getX(), x.getY(), card.getName()))+"#";
				Client.WriteMessage(message);
			} catch (Exception e1) {}						
		}
		panel.updatePanel();
	}
	@Override
	public void mousePressed(MouseEvent e) {
		sho.setVisible(false);
		try {
			if(round==60) {
				String message="CHANGCARD>>"+new Gson().toJson(new changeCardRequest(tocken,card.getName()))+"#";
				Client.WriteMessage(message);
			}
		} catch (Exception e1) {e1.printStackTrace();}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		sho.setVisible(false);	
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		sho.setVisible(true);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		sho.setVisible(false);
	}
	@Override
	public void mouseMoved(MouseEvent e) {}		
	@Override
	public void mouseDragged(MouseEvent e) {
		if(round%2==turn) {
			int newX = e.getX() + x.getX();
			int newY = e.getY() + x.getY();
			x.setBounds(newX, newY, 100	, 150);	
		}					
	}

}