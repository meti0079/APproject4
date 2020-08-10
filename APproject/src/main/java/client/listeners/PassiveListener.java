package client.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.google.gson.Gson;

import client.Client;
import client.Controller;
import client.grapic.PassivePanel;
import gameModel.requestAndREsponse.SetPassiveRequest;


public class PassiveListener implements MouseListener{
	private String name;
	PassivePanel pas;
	int tocken;
	public PassiveListener(PassivePanel pas, String name, int tocken) {
		this.tocken=tocken;
		this.name=name;
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
			String message="SETPASSIVE>>"+new Gson().toJson(new SetPassiveRequest(name, tocken))+"#";
			Client.WriteMessage(message);
		} catch (Exception e1) {e1.printStackTrace();}
		pas.setVisible(false);
	}
	@Override
	public void mouseReleased(MouseEvent e) {}

}
