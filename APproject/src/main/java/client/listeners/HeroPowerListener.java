package client.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import com.google.gson.Gson;
import client.ClientMain;
import client.grapic.HeroPowerShow;
import client.grapic.PlayPanel;
import gameModel.requestAndREsponse.HeroPowerRequest;
import server.hero.heroPower.HeroPower;

public class HeroPowerListener implements MouseListener,MouseMotionListener{
	private HeroPower heropower;
	private int round ;
	private int turn;
	int tocken;
	private HeroPowerShow x;
	PlayPanel panel;
	public HeroPowerListener( HeroPower heroPower,int round, int turn, HeroPowerShow x, int tocken, PlayPanel panel) {
		this.tocken=tocken;
		this.round=round;
		this.turn=turn;
		this.heropower = heroPower;
		this.x=x;
		this.panel=panel;
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
		if(!heropower.isUsed()&& round%2==turn) {
			try {
				String message="HEROPOWER>>"+new Gson().toJson(new HeroPowerRequest(tocken, x.getX(), x.getY()))+"#";
				ClientMain.WriteMessage(message);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		panel.updatePanel();
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
