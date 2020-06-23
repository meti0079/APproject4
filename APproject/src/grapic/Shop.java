package grapic;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import Cardspackage.Cards;
import GAME.Gamestate;
import GAME.Logger;
import hero.Heros;


public class Shop extends JPanel{
	private Gamestate game;
	private InfoPanel inf;
	private Logger log;
	private ArrayList<JLabel> current=new ArrayList<>();
	public Shop(MainFrame f) throws Exception {
		setLayout(null);
		log=Logger.getinsist();
		inf = InfoPanel.getinsist(f);
		game=Gamestate.getinsist();
		setPreferredSize(new Dimension(1800, 1000));
		inf.setBounds(0, 0, 1800, 100);
		add(inf);
		Collection_herospanel sell= new Collection_herospanel("sell");
		sell.setPreferredSize(new Dimension(1800, 2000));
		JScrollPane sellPanel=new JScrollPane(sell);

		StorePanel storePanel=new StorePanel(f, this,sell);
		storePanel.setBounds(0, 100, 1800, 900);

		JScrollPane buyPanel=new JScrollPane(storePanel);

		JTabbedPane tp =new JTabbedPane();


		Collection_herospanel hero=new Collection_herospanel("deck1");
		setHero(hero);



		tp.add(buyPanel,"buy");
		tp.add(sellPanel, "sell");
		tp.add(hero,"hero");
		setCard(sell, storePanel);
		tp.setBounds(0, 100, 1800, 900);
		add(tp);
	}
	public void setCard(JPanel x, StorePanel y) {
		int sum= current.size();
		for (int i = sum-1; i >=0; i--) {
			x.remove(current.get(i));
			current.remove(i);
			x.repaint();
			x.revalidate();
		}
		for(Cards s:game.getPlayer().get_myCards()) {
			ImageIcon icon=new ImageIcon(System.getProperty("user.dir")+"\\src\\card image\\"+s.get_Name()+".png");
			final	JLabel lb1=new JLabel(icon);
			lb1.addMouseListener(new MouseListener() {
				@Override
				public void mouseReleased(MouseEvent arg0) {
				}
				@Override
				public void mousePressed(MouseEvent arg0) {
					int j=JOptionPane.showConfirmDialog(null, "Do you want sell this card??"
							+ "\n you give "+gemCost(s)+" gem t. \n "
							+ "this card for "+s.get_Class()+" class.\n"
							+"and rarity is : "+s.get_Rarity(), "Confirm", JOptionPane.OK_CANCEL_OPTION);
					if(j==JOptionPane.OK_OPTION) {
						if(game.getPlayer().sellaCard(s)) {			
							game.getPlayer().gem+=gemCost(s);
							x.remove(lb1);
							setCard(x, y);
							x.repaint();	
							inf.repaint();
							y.update();
							x.revalidate();
							y.revalidate();
							y.repaint();
							try {
								log.log(game.getPlayer().get_name(), "sell card", s.get_Name());
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}else {
							try {
								log.log(game.getPlayer().get_name(), "error", "cant sell card : "+s.get_Name());
							} catch (IOException e1) {						
								e1.printStackTrace();
							}
						}
					}
				}
				@Override
				public void mouseExited(MouseEvent arg0) {
				}
				@Override
				public void mouseEntered(MouseEvent arg0) {
				}
				@Override
				public void mouseClicked(MouseEvent arg0) {
				}
			});			 
			x.add(lb1);	
			current.add(lb1);
		}
	}

	public void setHero(Collection_herospanel x) {
		x.removeAll();
		for(Heros s:game.getPlayer().getMyStore().getBuyHero()) {
			ImageIcon icon=new ImageIcon(System.getProperty("user.dir")+"\\src\\play image\\"+s.getname()+".png");
			final	JLabel lb1=new JLabel(icon);
			lb1.addMouseListener(new MouseListener() {
				@Override
				public void mouseReleased(MouseEvent arg0) {
				}
				@Override
				public void mousePressed(MouseEvent arg0) {
					int j=JOptionPane.showConfirmDialog(null, "Do you want buy this hero??\n"+
							"name : "+s.getname()+"\n"
							+ "you give 25 gem t. \n "+" hero power : "+s.get_SpecialPower() +"\n"
							+"HeroPowerMana : "+s.getHeroPowerMana()+"\n"
							+"SpecialPower : "+s.get_SpecialPower()+"\n"
							, "Confirm", JOptionPane.OK_CANCEL_OPTION);
					if(j==JOptionPane.OK_OPTION) {	
						if(game.getPlayer().gem>=25) {
							x.remove(lb1);
							game.getPlayer().gem-=25;
							game.getPlayer().get_myheros().add(s);
							game.getPlayer().getMyStore().getBuyHero().remove(s);
							x.revalidate();
							x.repaint();
							inf.repaint();
							try {
								log.log(game.getPlayer().get_name(), "buy hero ", s.getname());
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}else {
							try {
								log.log(game.getPlayer().get_name(), "error", "cant buy hero : "+s.getname());
							} catch (IOException e1) {						
								e1.printStackTrace();
							}
						}
					}
				}
				@Override
				public void mouseExited(MouseEvent arg0) {	
				}
				@Override
				public void mouseEntered(MouseEvent arg0) {
				}
				@Override
				public void mouseClicked(MouseEvent arg0) {
				}
			});		
			x.add(lb1);
		}
	}
	private int gemCost(Cards a) {
		if(a.get_Rarity().equalsIgnoreCase("rare"))
			return 2;
		if(a.get_Rarity().equalsIgnoreCase("epic"))
			return 3;
		if(a.get_Rarity().equalsIgnoreCase("legendary"))
			return 4;
		if(a.get_Rarity().equalsIgnoreCase("common"))
			return 1;
		return 1;
	}
}