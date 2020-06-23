package myListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Cardspackage.Weapon;
import GAME.Gamestate;
import GAME.Logger;
import grapic.PlayPanel;

public class MyWeaponListener implements MouseListener {
	private PlayPanel panel;
	private Weapon myWeapon;
	public MyWeaponListener(PlayPanel panel,Weapon weapon) {
		this.myWeapon=weapon;
		this.panel=panel;

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(!myWeapon.getUsedToAttack() || myWeapon.isBattlecry()) {
			myWeapon.setDurability(myWeapon.getDurability()-1);
			if(myWeapon.getDurability()==0) {
				myWeapon=null;
				try {
					Logger.getinsist().log(Gamestate.getinsist().getPlayer().get_name(), Gamestate.getinsist().getPlayer().get_name() ," played  "+ myWeapon.get_Name());
					String se=Gamestate.getinsist().getPlayer().get_name()+"  played  "+ myWeapon.get_Name();
					panel.getTextArea().append(se);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			myWeapon.setBattlecry(false);
			myWeapon.setUsedToAttack(true);
			panel.updatePanel();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
