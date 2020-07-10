package myListeners;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Cardspackage.Weapon;
import GAME.Gamestate;
import GAME.Logger;
import grapic.PlayPanel;
import playModel.Mapper;
import playModel.Player;

public class EnemyWeaponListener implements MouseListener {


	private final PlayPanel panel;
	private  Weapon myWeapon;
	Player enemy;
	public EnemyWeaponListener(PlayPanel panel,Weapon weapon, Player enemy) {
		this.myWeapon=weapon;
		this.panel=panel;
		this.enemy=enemy;
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
		if(panel.getRoundGame()%2==1) {
			try {
				if(Mapper.getinsist().useWeapon(myWeapon, enemy)) {
					panel.updatePanel();
					String se="enemy  played  "+ myWeapon.get_Name()+"\n";
					panel.getTextArea().append(se);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}


