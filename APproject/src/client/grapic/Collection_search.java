package client.grapic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Cardspackage.Card;
import client.listeners.LockCardListener;
import client.listeners.UnlockListener;
import game.Gamestate;
import game.Logger;

public class Collection_search extends JPanel{

	private static final long serialVersionUID = 1L;
	private JTextField text;
	private JButton ok,g1,g2,g3,g4,g5,g6,g7,g8,g9,g10,g0,all,have,notHave;
	private ArrayList<JButton> but;
	private Gamestate game;
	private ArrayList<JLabel> current;
	private Logger log;
	private Collection_deck dec;
	private CollectionPanel p;


	public Collection_search(MainFrame f, Collection_deck dec, CollectionPanel p) throws Exception {
		this.p=p;
		this.dec=dec;
		initial();
		initialButtons();
		addButtonToList();
		for(int i=0;i<11;i++) {
			initialGemButton(i, but.get(i), f);
		}
		//////// button filter
		initialSearchButton(f, all, "all");
		initialSearchButton(f, notHave, "not");
		initialSearchButton(f, have, "sum");
		setOkButton(f);
	}
	private void removeLables() {
		for(int i=current.size()-1;i>=0;i--) {
			remove(current.get(i));
			current.remove(current.get(i));
		}
	}

	private void initialCardLable(Card s) {
		final JLabel lp =new JLabel(new ImageIcon( System.getProperty("user.dir")+"\\src\\card image\\"+s.get_Name()+".png"));
		lp.addMouseListener(new UnlockListener(s, p, dec));
		current.add(lp);
		add(lp);
	}
	private void initialLockCardLable(Card s, MainFrame f) {
		final JLabel lp =new JLabel(new ImageIcon( System.getProperty("user.dir")+"\\src\\card image\\"+s.get_Name()+"1.png"));
		lp.addMouseListener(new LockCardListener(f, s));
		current.add(lp);
		add(lp);
	}
	private void manaFilter(int x,MainFrame f) {
		for(int i=current.size()-1;i>=0;i--) {
			remove(current.get(i));
			current.remove(current.get(i));
		}
		for(Card s : game.getPlayer().findManaCard(x)) {
			initialCardLable(s);
		}
		for(Card s2 : game.getPlayer().getMyStore().findManaCard(x)) {
			initialLockCardLable(s2, f);		
		}

	}
	private void setCard(MainFrame f,String text) throws IOException {
		for(int i=current.size()-1;i>=0;i--) {
			remove(current.get(i));
			current.remove(current.get(i));
		}
		if(text.equalsIgnoreCase("all")) {
			log.log(game.getPlayer().get_name(), "clicked search button ", "show all cards");
			addHave();
			addnothave(f);
			this.setPreferredSize(new Dimension(1500, 3300));
		}else if(text.equalsIgnoreCase("sum")) {
			log.log(game.getPlayer().get_name(), "clicked search button ", "show cards that have");
			addHave();
		}else {
			log.log(game.getPlayer().get_name(), "clicked search button ", "show cards that dont have");
			addnothave(f);
		}	
	}
	private void addHave() {
		for(Card s : game.getPlayer().get_myCards()) {
			initialCardLable(s);
		}
	}
	private void addnothave(MainFrame f) {
		for(Card s2 : game.getPlayer().getMyStore().getBuyCard()) {
			initialLockCardLable(s2, f);
		}
	}
	private void nameFilter(String x,MainFrame f) {
		removeLables();
		for(Card s : game.getPlayer().findNameCard(x)) {
		initialCardLable(s);	
		}
		for(Card s2 : game.getPlayer().getMyStore().findNameCard(x)) {
		initialLockCardLable(s2, f);	
		}
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawBackGround(g);
	}
	private void drawBackGround(Graphics g) {
		ImageIcon icon =new ImageIcon("src\\backgrund image\\search.jpg");
		g.drawImage(icon.getImage(), 0, 0, null);
	}
	private void initialButtons() {
		ok=new JButton("Ok");
		g0=new JButton(new ImageIcon("src\\button image\\gem0.png"));	
		g1=new JButton(new ImageIcon("src\\button image\\gem1.png"));	
		g2=new JButton(new ImageIcon("src\\button image\\gem2.png"));	
		g3=new JButton(new ImageIcon("src\\button image\\gem3.png"));	
		g4=new JButton(new ImageIcon("src\\button image\\gem4.png"));	
		g5=new JButton(new ImageIcon("src\\button image\\gem5.png"));	
		g6=new JButton(new ImageIcon("src\\button image\\gem6.png"));	
		g7=new JButton(new ImageIcon("src\\button image\\gem7.png"));	
		g8=new JButton(new ImageIcon("src\\button image\\gem8.png"));	
		g9=new JButton(new ImageIcon("src\\button image\\gem9.png"));	
		g10=new JButton(new ImageIcon("src\\button image\\gem10.png"));	
		all=new JButton("All");	
		have=new JButton("i have");	
		notHave=new JButton("i  dont have");	
	}
	private void setOkButton(MainFrame f) {
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String su=text.getText();
				nameFilter(su, f);
				initialAfterFilter();
				try {
					log.log(game.getPlayer().get_name(), "clicked search button ", "want to show cards with name filter!! searched : "+su);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		add(ok);
	}
	private void addButtonToList() {
		but.add(g0);but.add(g1);but.add(g2);but.add(g3);but.add(g4);but.add(g5);
		but.add(g6);but.add(g7);but.add(g8);but.add(g9);but.add(g10);
	}
	private void initialSearchButton(MainFrame f, JButton b, String string) {
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initialAfterFilter();
				try {
					setCard(f, string);
				} catch (IOException e1) {e1.printStackTrace();}
			}
		});
		add(b);
	}
	private void initial() throws Exception{
		setPreferredSize(new Dimension(1500, 2000));
		game=Gamestate.getinsist();
		log=Logger.getinsist();
		text=new JTextField("search",50);
		but=new ArrayList<>();
		add(text);
		current=new ArrayList<>();
		setBackground(new Color(10, 10, 10));
	}
	private void initialGemButton(int gem,JButton b, MainFrame f) {
		b.setContentAreaFilled(false);
		b.setBorder(BorderFactory.createEmptyBorder());
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					log.log(game.getPlayer().get_name(), "clicked search button ", gem+" gem");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				manaFilter(gem, f);	
				initialAfterFilter();
			}
		});
		add(b);
	}
	private void initialAfterFilter() {
		setPreferredSize(new Dimension(1500, 2000));
		revalidate();	
		repaint();
	}
}
