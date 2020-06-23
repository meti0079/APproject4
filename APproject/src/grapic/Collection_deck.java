package grapic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import GAME.Decks;
import GAME.Gamestate;
import GAME.Logger;

public class Collection_deck extends JPanel{

	private static final long serialVersionUID = 1L;
	private Gamestate game;
	private Logger log;
	private ArrayList< JButton> allBut;
	
	public Collection_deck(Collection_herospanel x,CollectionPanel y) throws Exception {
		setLayout(null);
		allBut=new ArrayList<>();
		log =Logger.getinsist();
		game=Gamestate.getinsist();
		setPreferredSize(new Dimension(300, 900));
		setBackground(new Color(162, 82,45));


		JButton b= new JButton(new ImageIcon("src\\\\button image\\\\add.png"));

		b.setBounds(25,780, 200, 50);
		b.setContentAreaFilled(false);
		b.setBorder(BorderFactory.createEmptyBorder());
		b.setFont(new Font("Tahoma", Font.BOLD, 15));
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					log.log(game.getPlayer().get_name(), "clicked add deck ", "");
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				if(game.getPlayer().getalldeck().size()>16) {
					JOptionPane.showConfirmDialog(null, "you have maximum deck!!! delet or edit some", "error", JOptionPane.YES_OPTION);
					return;
				}	
				Decks s=null;
				try {
					s = new Decks();
				} catch (Exception e1) {
					System.out.println("cant make deck , collection");
					e1.printStackTrace();
				}
				while(true ) {
					Boolean flag=false;
					String name=JOptionPane.showInputDialog("enter your deck name")+"";
					for(Decks a : game.getPlayer().getalldeck()) {
						if(a.getName().equalsIgnoreCase(name)) {
							flag=true;
						}
					}
					if(flag==false) {
						s.setName(name);
						break;
					}
				}
				String [] myhero=new String[game.getPlayer().get_myheros().size()] ;
				for(int i=0 ;i< game.getPlayer().get_myheros().size();i++)
					myhero[i]=game.getPlayer().get_myheros().get(i).getname();
				String n="";
				n = (String)JOptionPane.showInputDialog(null, "select deck hero ",
						"select", JOptionPane.QUESTION_MESSAGE, null,myhero, myhero[0]);
				try {
					s.setHeroDeck(n);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				game.getPlayer().setMyDeck(s);
				game.getPlayer().getalldeck().add(s);
				try {
					log.log(game.getPlayer().get_name(), "add deck ", s.getName());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				updateBut(x,y);
				repaint();
				revalidate();
			}
		});
		add(b);

		JButton edit=new JButton(new ImageIcon("src\\button image\\edit2.png"));
		edit.setContentAreaFilled(false);
		edit.setBorder(BorderFactory.createEmptyBorder());
		edit.setBounds(155, 830, 100, 50);
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					log.log(game.getPlayer().get_name(), "clicked editName deck  button ", "");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				while(true ) {
					Boolean flag=false;
					String name=JOptionPane.showInputDialog("enter your deck name")+"";
					for(Decks a : game.getPlayer().getalldeck()) {
						if(a.getName().equalsIgnoreCase(name)) {
							flag=true;
						}
					}
					if(flag==false) {
						game.getPlayer().getMyDeck().setName(name);
						updateBut(x,y);
						repaint();
						revalidate();
						try {
							log.log(game.getPlayer().get_name(), "deck name edited ","new name of deck : "+name );
						} catch (IOException e) {
							e.printStackTrace();
						}
						break;
					}
				}
			}
		});
		add(edit);

		JButton edit1=new JButton(new ImageIcon("src\\button image\\edit.png"));
		edit1.setContentAreaFilled(false);
		edit1.setBorder(BorderFactory.createEmptyBorder());
		edit1.setBounds(25, 830, 100, 50);
		edit1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					log.log(game.getPlayer().get_name(), "clicked chang hero button ", "");
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				String [] myhero=new String[game.getPlayer().get_myheros().size()] ;
				for(int i=0 ;i< game.getPlayer().get_myheros().size();i++)
					myhero[i]=game.getPlayer().get_myheros().get(i).getname();
				String n="";
				n = (String)JOptionPane.showInputDialog(null, "select deck hero ",
						"select", JOptionPane.QUESTION_MESSAGE, null,myhero, myhero[0]);
				try {
					if(n.equalsIgnoreCase("")) {
					}else {
						game.getPlayer().getMyDeck().addUsethisDeck(0);
						game.getPlayer().getMyDeck().addWin(0);
						game.getPlayer().getMyDeck().setHeroDeck(n);
						log.log(game.getPlayer().get_name(), "change hero of deck  ", "to : " +n);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		add(edit1);
		updateBut(x,y);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon icon =new ImageIcon("src\\button image\\2.png");
		g.drawImage(icon.getImage(), 5, 0, null);
		g.drawRoundRect(10, 60, 280, 40, 5, 5);
		g.drawRoundRect(10, 105, 280, 40, 5, 5);
		g.drawRoundRect(10, 150, 280, 40, 5, 5);
		g.drawRoundRect(10, 195, 280, 40, 5, 5);
		g.drawRoundRect(10, 240, 280, 40, 5, 5);
		g.drawRoundRect(10, 285, 280, 40, 5, 5);
		g.drawRoundRect(10, 330, 280, 40, 5, 5);
		g.drawRoundRect(10, 375, 280, 40, 5, 5);
		g.drawRoundRect(10, 420, 280, 40, 5, 5);
		g.drawRoundRect(10, 465, 280, 40, 5, 5);
		g.drawRoundRect(10, 510, 280, 40, 5, 5);
		g.drawRoundRect(10, 555, 280, 40, 5, 5);
		g.drawRoundRect(10, 600, 280, 40, 5, 5);
		g.drawRoundRect(10, 645, 280, 40, 5, 5);
		g.drawRoundRect(10, 690, 280, 40, 5, 5);
		g.drawRoundRect(10, 735, 280, 40, 5, 5);
	}
	public void updateBut(Collection_herospanel x, CollectionPanel y) {
		for (JButton jButton : allBut) {
			remove(jButton);
		}
		allBut.removeAll(allBut);
		int i=0;
		for(Decks s: game.getPlayer().getalldeck()) {
			JButton b= new JButton(s.getName()+"       "+s.getHeroDeck().getname()+ "   size :"+ s.getDeck().size());
			b.setFont(new Font("tahoma", Font.BOLD, 15));
			b.setBackground(new Color(165, 42, 42));
			b.setBounds(10, 45*i+60, 280, 40);
			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						log.log(game.getPlayer().get_name(), "change deck", "to " +s.getName());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					game.getPlayer().setMyDeck(s);
					y.setdeck();
					x.revalidate();
					x.repaint();
				}
			});
			add(b);
			allBut.add(b);
			i++;
		}
	}
	public void makeDeck(Decks s , String name) {
		JButton b= new JButton(name);
		b.setBackground(new Color(165, 42, 42));
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					log.log(game.getPlayer().get_name(), "change deck", " to  "+s.getName());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				game.getPlayer().setMyDeck(s);
			}
		});
		add(b);
	}
}
