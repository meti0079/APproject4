package  grapic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Cardspackage.Card;
import GAME.Decks;
import GAME.Gamestate;

public class Statos extends JPanel{

	private static final long serialVersionUID = 1L;
	private Gamestate game=Gamestate.getinsist();
	private  int size=game.getPlayer().getalldeck().size();
	public Statos(MainFrame f) throws Exception {
		InfoPanel p=InfoPanel.getinsist(f);
		add(p);
		initial();
	}
	private void initial() {
		setPreferredSize(new Dimension(1800, 1000));
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);	
		setBackGround(g);
		writeTopic(g);
		drawRoundRect(g);
		drawDeckDetails(g);
	}
	private void setBackGround(Graphics g) {
		g.drawImage(new ImageIcon("src\\passiva image\\astatos.jpg").getImage(), 0, 0, null);		
	}
	private void writeTopic(Graphics g) {
		g.setFont(new Font("Tahoma", Font.BOLD, 50));
		g.setColor(Color.RED);
		g.drawString("Status", 820	, 150);
	}
	private void drawRoundRect(Graphics g) {
		for(int i=0;i<5;i++)
			g.drawRoundRect(10, 165+i*170, 880, 160,20,20);
		for(int i=0;i<5;i++)
			g.drawRoundRect(900, 165+i*170, 880, 160,20,20);
	}
	private void drawDeckDetails(Graphics g) {
		int co=40;
		int x=20;
		int first=190;
		g.setFont(new Font("Tahoma", Font.BOLD, 20));
		g.setColor(Color.WHITE);
		int sum=0;
		for(Decks s : game.getPlayer().sortDecks()) {
			if(sum<10) {
				g.drawString("deck name :"+s.getName(), co, first);
				g.drawString((sum+1)+"", co+800, first);
				first+=x;
				if(game.getPlayer().getPlays()==0) {
					g.drawString("won / paly  : no match ", co,first );					
				}else {
					float av=((float)s.getWin())/((float)game.getPlayer().getPlays());
					g.drawString("won / paly  : "+av , co,first );						
				}
				first+=x;
				g.drawString( "won "+ s.getWin(), co, first);
				first+=x;
				g.drawString("played with : "+s.getUsethisDeck(), co, first);
				first+=x;
				g.drawString("avarage : " +s.GetAverage(), co, first);
				first+=x;
				g.drawString("hero :" +s.getHeroDeck().getname(), co, first);
				first+=x;
				if(s.bestCard()!=null)
					g.drawString("best card " +s.bestCard().get_Name(), co, first);
				first+=50;
				if(first>980) {
					first=190;
					co=910;
				}
				sum++;
			}else {
				break;
			}
		}
	}

}