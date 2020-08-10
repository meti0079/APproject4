package client.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JEditorPane;

import com.google.gson.Gson;

import Cardspackage.Card;
import client.Client;
import client.Controller;
import client.grapic.CardShow;
import client.grapic.HeroPowerShow;
import client.grapic.PlayPanel;
import gameModel.requestAndREsponse.HeroPowerRequest;
import gameModel.requestAndREsponse.gameNeed;
import hero.heroPower.HeroPower;
import interfaces.HeroPowerVisitor;
import interfaces.Visitor;
import playModel.Mapper;
import playModel.PlayerModel;

public class HeroPowerListener implements MouseListener,MouseMotionListener{
	private HeroPower heropower;
	private int round ;
	private int turn;
	int tocken;
	private HeroPowerShow x;
	public HeroPowerListener( HeroPower heroPower,int round, int turn, HeroPowerShow x, int tocken) {
		this.tocken=tocken;
		this.round=round;
		this.turn=turn;
		this.heropower = heroPower;
		this.x=x;

	}
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if(heropower.isUsed()==false && round%2==turn) {
			try {
				String message="HeroPower>>"+new Gson().toJson(new HeroPowerRequest(tocken, x.getX(), x.getY()))+"#";
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
