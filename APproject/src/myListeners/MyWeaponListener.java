package myListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Cardspackage.Weapon;
import GAME.Gamestate;
import GAME.Logger;
import grapic.PlayPanel;

public class MyWeaponListener implements MouseListener {
	private final PlayPanel panel;
	private  Weapon myWeapon;
	private final String name;
	public MyWeaponListener(PlayPanel panel,Weapon weapon, String name) {
		this.myWeapon=weapon;
		this.panel=panel;
		this.name=name;
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
		if(panel.getRoundGame()%2==0) {
		if(!myWeapon.getUsedToAttack() || myWeapon.isBattlecry()) {
			myWeapon.setDurability(myWeapon.getDurability()-1);
			try {
				Logger.getinsist().log(Gamestate.getinsist().getPlayer().get_name(), Gamestate.getinsist().getPlayer().get_name() ,name+ " played  "+ myWeapon.get_Name());
				String se=name+"  played  "+ myWeapon.get_Name()+"\n";
				panel.getTextArea().append(se);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(myWeapon.getDurability()!=0) {
				myWeapon.setBattlecry(false);
				myWeapon.setUsedToAttack(true);
				panel.updatePanel();	
			}else {
				myWeapon=null;
				panel.updatePanel();
			}
		}
	}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
