package client.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import client.Controller;
import client.grapic.BiggerCard;
import client.grapic.CollectionPanel;
import client.model.Card;

public class UnlockListener implements MouseListener{
	private Card s;
	private CollectionPanel p;
	Controller controller;
	public UnlockListener(Card s, CollectionPanel p, Controller controller) {
		this.s = s;
		this.controller=controller;
		this.p = p;
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent arg0) {
		BiggerCard x=new BiggerCard(s,controller);
		p.getParent().add(x);
	}		
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseClicked(MouseEvent arg0) {}
}
