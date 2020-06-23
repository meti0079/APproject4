package  grapic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import Cardspackage.Cards;
import GAME.Gamestate;
import GAME.Logger;

public class StorePanel  extends JPanel{
	private Gamestate game;
	private JFrame frame;
	private InfoPanel inf;
	private Shop shop;
	private Collection_herospanel sellPanel;
	private Logger log;
	private ArrayList<JLabel> current=new ArrayList<>();
	public StorePanel(JFrame f, Shop shop, Collection_herospanel sell) throws Exception {
		this.frame=f;
		log=Logger.getinsist();
		this.shop=shop;
		sellPanel=sell;
		inf=InfoPanel.getinsist((MainFrame)f);
		game=Gamestate.getinsist();
		setPreferredSize(new Dimension(1800, 2000));
		setBackground(Color.BLUE);
		update();
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(new ImageIcon("8.jpg").getImage(), 0, 0, null);
	}
	public void update() {
		int sum= current.size();
		for (int i = sum-1; i >=0; i--) {
			remove(current.get(i));
			current.remove(i);
			repaint();
			revalidate();
		}
		for(Cards s:game.getPlayer().getMyStore().getBuyCard()) {
			ImageIcon icon=new ImageIcon(System.getProperty("user.dir")+"\\src\\card image\\"+s.get_Name()+".png");
			final	JLabel lb1=new JLabel(icon);
			lb1.addMouseListener(new MouseListener() {
				@Override
				public void mouseReleased(MouseEvent arg0) {

				}
				@Override
				public void mousePressed(MouseEvent arg0) {
					int j=JOptionPane.showConfirmDialog(null, "Do you want buy this card??"
							+ "\nyou have to spend "+gemCost(s)+" gem to buy this card.\n "
							+ "this card for "+s.get_Class()+" class.\n"
							+"and rarity is : "+s.get_Rarity(), "Confirm", JOptionPane.OK_CANCEL_OPTION);
					if(j==JOptionPane.OK_OPTION) {
						if(game.getPlayer().gem>=gemCost(s)) {
							game.getPlayer().buyaCard(s);
							game.getPlayer().gem-=gemCost(s);
							remove(lb1);
							repaint();	
							inf.repaint();
							shop.setCard(sellPanel,StorePanel.this);
							shop.repaint();
							shop.revalidate();
							try {
								log.log(game.getPlayer().get_name(), "buy card", s.get_Name());
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}else {
							try {
								log.log(game.getPlayer().get_name(), "error ", "dont have enogh gem to buy : "+s.get_Name());
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							JOptionPane.showMessageDialog(null, "you dont have enough gem", "Erorr", JOptionPane.ERROR_MESSAGE);
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
			add(lb1);
			current.add(lb1);
		}
	}
	private int gemCost(Cards a) {
		if(a.get_Rarity().equalsIgnoreCase("rare"))
			return 3;
		if(a.get_Rarity().equalsIgnoreCase("epic"))
			return 4;
		if(a.get_Rarity().equalsIgnoreCase("legendary"))
			return 5;
		if(a.get_Rarity().equalsIgnoreCase("common"))
			return 2;
		return 1;
	}
}