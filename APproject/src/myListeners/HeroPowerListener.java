package myListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JEditorPane;

import Cardspackage.Card;
import grapic.CardShow;
import grapic.HeroPowerShow;
import grapic.PlayPanel;
import hero.heroPower.HeroPower;
import interfaces.HeroPowerVisitor;
import interfaces.Visitor;
import playModel.Mapper;
import playModel.Player;

public class HeroPowerListener implements MouseListener,MouseMotionListener{
	private PlayPanel panel;
	private HeroPower heropower;
	private HeroPowerShow x;
	private Player me;
	private Player enemy;
	private HeroPowerVisitor v;

	public HeroPowerListener(PlayPanel panel, HeroPower heroPower,HeroPowerShow shpw , Player me, Player enemy, HeroPowerVisitor v) {
		super();
		this.panel = panel;
		this.heropower = heroPower;
		this.x = shpw;
		this.me = me;
		this.enemy = enemy;
		this.v = v;
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
		if(heropower.isUsed()==false && PlayPanel.getRoundGame()%2==me.getTurn()) {
			if(me.getCurrentgem()>=heropower.getMana())
			try {
				if(Mapper.getinsist().handleHeroPower(me, enemy, v, x.getX(), x.getY(), heropower)) {
					String 	ss=me.getName()+"     played    hero power\n";
					heropower.setUsed(true);
					panel.getTextArea().append(ss);	
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		panel.updatePanel();	
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		if(panel.getRoundGame()%2==me.getTurn()) {
			int newX = e.getX() + x.getX();
			int newY = e.getY() + x.getY();
			x.setBounds(newX, newY, 100	, 150);	
		}		
	}
	@Override
	public void mouseMoved(MouseEvent e) {}

}
