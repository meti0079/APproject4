package  grapic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Cardspackage.Cards;
import GAME.Decks;
import GAME.Gamestate;

public class Statos extends JPanel{

	private static final long serialVersionUID = 1L;
	private Gamestate game=Gamestate.getinsist();
	private  int size=game.getPlayer().getalldeck().size();
	Decks [] allDeck=new Decks[size];
	public Statos(MainFrame f) throws Exception {
		InfoPanel p=InfoPanel.getinsist(f);
		add(p);
		setPreferredSize(new Dimension(1800, 1000));
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);	
		g.drawImage(new ImageIcon("src\\passiva image\\astatos.jpg").getImage(), 0, 0, null);
		g.setFont(new Font("Tahoma", Font.BOLD, 50));
		g.setColor(Color.RED);
		g.drawString("Status", 820	, 150);
		for(int i=0;i<5;i++)
			g.drawRoundRect(10, 165+i*170, 880, 160,20,20);
		for(int i=0;i<5;i++)
			g.drawRoundRect(900, 165+i*170, 880, 160,20,20);
		sort();
		int co=40;
		int x=20;
		int first=190;
		g.setFont(new Font("Tahoma", Font.BOLD, 20));
		g.setColor(Color.WHITE);
		int sum=0;
		for(Decks s : allDeck) {
			if(sum<10) {
				g.drawString("deck name :"+s.getName(), co, first);
				g.drawString((sum+1)+"", co+800, first);
				first+=x;
				if(game.getPlayer().getPlays()==0) {
					g.drawString("won / paly  : no match ", co,first );					
				}else {
					float av=((float)s.getWin())/((float)game.getPlayer().getPlays());
					System.out.println(av);
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
				if(bestCard(s)!=null)
					g.drawString("best card " +bestCard(s).get_Name(), co, first);
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
	private Cards bestCard(Decks s) {
		if(s.getDeck().size()<2)
			return null;
		int x=0;
		Cards[] ee=new Cards[s.getDeck().size()];
		for(Cards  b: s.getDeck()) {
			ee[x]=b;
			x++;
		}
		for(int i=0;i<s.getDeck().size();i++) {
			for(int j=0;j<s.getDeck().size()-1-i;j++) {
				if(ee[j].getUse()>=ee[j+1].getUse()) {	
					Cards y=ee[j+1];
					ee[j+1]=ee[j];
					ee[j]=y;
				}
			}
		}
		if(ee[s.getDeck().size()-1].getUse()==ee[s.getDeck().size()-2].getUse()) {
			if(ee[s.getDeck().size()-1].get_Rarity().equalsIgnoreCase(ee[s.getDeck().size()-2].get_Rarity())){
				if(ee[s.getDeck().size()-1].get_Mana()==ee[s.getDeck().size()-2].get_Mana()) {
					if(ee[s.getDeck().size()-1].getType().equalsIgnoreCase(ee[s.getDeck().size()-2].getType()))
						return ee[s.getDeck().size()-1];
				}else {
					if(ee[s.getDeck().size()-1].get_Mana()  >  ee[s.getDeck().size()-2].get_Mana())
						return ee[s.getDeck().size()-1];
					return ee[s.getDeck().size()-2];
				}
			}else {
				if(ee[s.getDeck().size()-1].get_Rarity().equalsIgnoreCase("legandry"))
					return ee[s.getDeck().size()-1];
				if(ee[s.getDeck().size()-2].get_Rarity().equalsIgnoreCase("legandry"))
					return ee[s.getDeck().size()-2];
				if(ee[s.getDeck().size()-1].get_Rarity().equalsIgnoreCase("epic"))
					return ee[s.getDeck().size()-1];
				if(ee[s.getDeck().size()-2].get_Rarity().equalsIgnoreCase("epic"))
					return ee[s.getDeck().size()-2];

				if(ee[s.getDeck().size()-1].get_Rarity().equalsIgnoreCase("rare"))
					return ee[s.getDeck().size()-1];
				if(ee[s.getDeck().size()-2].get_Rarity().equalsIgnoreCase("rare"))
					return ee[s.getDeck().size()-2];
			}
		}else {
			if(ee[s.getDeck().size()-1].getUse()  >  ee[s.getDeck().size()-2].getUse())
				return ee[s.getDeck().size()-1];
			return ee[s.getDeck().size()-2];
		}
		return  null;
	}
	private void sort() {
		int x=0;
		for(Decks s : game.getPlayer().getalldeck()) {
			allDeck[x]=s;
			x++;
		}
		for(int i=0;i<size-1;i++) {
			for(int j=size-i-1;j>0;j--) {
				if(allDeck[j].getWin()>=allDeck[j-1].getWin()) {	
					Decks y=allDeck[j-1];
					allDeck[j-1]=allDeck[j];
					allDeck[j]=y;
				}
			}
		}
	}
}