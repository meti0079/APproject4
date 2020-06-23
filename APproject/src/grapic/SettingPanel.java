package grapic;

import javax.swing.JPanel;

import GAME.Gamestate;

public class SettingPanel extends JPanel{

	
	
	Gamestate game;
	public SettingPanel() throws Exception {
	game=Gamestate.getinsist();
	}
	
	public void setBackCard(int a) {
		String s="ca"+a+".png";
		game.setBackBattleground(s);
	}
	public void setBattlebackGround(int a) {
		String s="nattle"+a+".jpg";
		game.setBackBattleground(s);
	}
}
