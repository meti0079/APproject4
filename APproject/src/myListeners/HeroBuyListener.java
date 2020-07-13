package myListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import GAME.Gamestate;
import GAME.Logger;
import grapic.Collection_herospanel;
import grapic.InfoPanel;
import hero.Heros;

public class HeroBuyListener implements MouseListener {
	private Collection_herospanel x;
	private Heros s;
	private JLabel heroLable;
	private InfoPanel inf;
	private Logger log;
	public HeroBuyListener(Collection_herospanel x, Heros s, JLabel heroLable, InfoPanel inf, Logger log) {
		super();
		this.x = x;
		this.s = s;
		this.heroLable = heroLable;
		this.inf = inf;
		this.log = log;
	}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {
		int j=JOptionPane.showConfirmDialog(null, "Do you want buy this hero??\n"+
				"name : "+s.getname()+"\n"
				+ "you give 25 gem t. \n "+" hero power : "+s.get_SpecialPower() +"\n"
				+"HeroPowerMana : "+s.getHero_power().getMana()+"\n"
				+"SpecialPower : "+s.get_SpecialPower()+"\n"
				, "Confirm", JOptionPane.OK_CANCEL_OPTION);
		if(j==JOptionPane.OK_OPTION) {	
			try {
				if(Gamestate.getinsist().getPlayer().gem>=25) {
					x.remove(heroLable);
					Gamestate.getinsist().getPlayer().gem-=25;
					Gamestate.getinsist().getPlayer().get_myheros().add(s);
					Gamestate.getinsist().getPlayer().getMyStore().getBuyHero().remove(s);
					x.update();
					inf.repaint();
					log.log(Gamestate.getinsist().getPlayer().get_name(), "buy hero ", s.getname());
				}else {
					log.log(Gamestate.getinsist().getPlayer().get_name(), "error", "cant buy hero : "+s.getname());	
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {}

}
