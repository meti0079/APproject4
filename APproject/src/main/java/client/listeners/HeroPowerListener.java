package client.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JEditorPane;

import Cardspackage.Card;
import client.grapic.CardShow;
import client.grapic.HeroPowerShow;
import client.grapic.PlayPanel;
import hero.heroPower.HeroPower;
import interfaces.HeroPowerVisitor;
import interfaces.Visitor;
import playModel.Mapper;
import playModel.PlayerModel;

public class HeroPowerListener implements MouseListener,MouseMotionListener{
	private PlayPanel panel;
	private HeroPower heropower;
	private HeroPowerShow x;
	private PlayerModel me;
	private PlayerModel enemy;
	private HeroPowerVisitor v;
	private Visitor cardVisitor;
	public HeroPowerListener(PlayPanel panel, HeroPower heroPower,HeroPowerShow shpw , PlayerModel me, PlayerModel enemy, HeroPowerVisitor v, Visitor cv) {
		super();
		this.panel = panel;
		this.heropower = heroPower;
		this.x = shpw;
		this.me = me;
		this.enemy = enemy;
		this.v = v;
		this.cardVisitor=cv;
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
				if(Mapper.getinsist().handleHeroPower(me, enemy, v, x.getX(), x.getY(), heropower,cardVisitor)) {
					String 	ss=me.getName()+"     played    hero power\n";
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
