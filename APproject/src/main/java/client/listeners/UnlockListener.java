package client.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Cardspackage.Card;
import client.grapic.BiggerCard;
import client.grapic.CollectionPanel;
import client.grapic.Collection_deck;

public class UnlockListener implements MouseListener{
private Card s;
private CollectionPanel p;
private Collection_deck deckboard;
public UnlockListener(Card s, CollectionPanel p, Collection_deck deckboard) {
	this.s = s;
	this.p = p;
	this.deckboard = deckboard;
}
@Override
public void mouseReleased(MouseEvent arg0) {}
@Override
public void mousePressed(MouseEvent arg0) {
	BiggerCard x=new BiggerCard(s, p, deckboard);
	p.getParent().add(x);
}		
@Override
public void mouseExited(MouseEvent arg0) {}
@Override
public void mouseEntered(MouseEvent arg0) {}
@Override
public void mouseClicked(MouseEvent arg0) {}
}
