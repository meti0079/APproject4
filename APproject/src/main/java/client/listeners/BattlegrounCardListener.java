package client.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JOptionPane;

import com.google.gson.Gson;

import Cardspackage.Minion;
import client.Client;
import client.Controller;
import client.grapic.CardShow;
import client.grapic.PlayPanel;
import client.grapic.ShowCardBigger;
import client.model.Card;
import gameModel.requestAndREsponse.AttackRequest;
import interfaces.Visitor;
import playModel.Mapper;
import playModel.PlayerModel;

public class BattlegrounCardListener implements MouseListener,MouseMotionListener{
	private Card card;
	private CardShow x;
	ShowCardBigger sho;
	int round;
	int turn;
	int tocken;
	public  BattlegrounCardListener(Card card, CardShow x, int round, int turn,int tocken) {
		this.tocken=tocken;
		this.card=card;
		this.x=x;
		this.round=round;
		this.turn=turn;
		sho =new ShowCardBigger(card);
		sho.setBounds(650, 350, 200, 300);
		sho.setVisible(false);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		sho.setVisible(true);
	}
	@Override
	public void mouseExited(MouseEvent e) {
		sho.setVisible(false);
	}
	@Override
	public void mousePressed(MouseEvent e) {
		sho.setVisible(false);
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		sho.setVisible(false);
		if(card.isUsedToAttack() ==false || (card).isRush()) {
			try {
			String message="ATTACK>>"+new Gson().toJson(new AttackRequest(tocken, x.getX(), x.getY(),card.getName()))+"#";
			Client.WriteMessage(message);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		if(round%2==turn) {
			int newX = e.getX() + x.getX();
			int newY = e.getY() + x.getY();
			x.setBounds(newX, newY, 100	, 150);	
		}		
	}
	@Override
	public void mouseMoved(MouseEvent e) {}
}
