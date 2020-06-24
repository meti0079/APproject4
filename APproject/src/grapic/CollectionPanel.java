package grapic;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import GAME.Logger;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import Cardspackage.Cards;
import GAME.Gamestate;
import myListeners.LockCardListener;

public class CollectionPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTabbedPane tp;
	private Collection_herospanel mage;
	private Collection_herospanel hunter;
	private Collection_herospanel priest;
	private Collection_herospanel rouge;
	private Collection_herospanel warlock;
	private Collection_herospanel netural;
	private Gamestate game;
	private Collection_herospanel myDeckPanel;
	private Collection_herospanel enemyDeckPanel;	
	private Collection_deck deckbord;
	private ArrayList<JLabel > current = new ArrayList<>();
	private ArrayList<JLabel > enemyCurrent = new ArrayList<>();
	private Collection_search serchPanel;
	private MainFrame frame;
	public CollectionPanel(MainFrame f) throws Exception {
		game=Gamestate.getinsist();
		frame=f;
		setPreferredSize(new Dimension(1800,990));
		tp=new JTabbedPane();
		mage=new Collection_herospanel("mage");
		setcard("Mage", mage,f);
		JScrollPane m=new JScrollPane(mage);
		mage.setPreferredSize(new Dimension(1500, 2000));
		hunter=new  Collection_herospanel("hunter");
		setcard("Hunter", hunter,f);
		JScrollPane hu=new JScrollPane(hunter);
		hunter.setPreferredSize(new Dimension(1500, 2000));
		priest=new Collection_herospanel("priest");
		setcard("Priest", priest,f);
		JScrollPane p=new JScrollPane(priest);
		priest.setPreferredSize(new  Dimension(1500, 2000));
		warlock=new Collection_herospanel("warlock");
		setcard("Warlock", warlock,f);
		JScrollPane w=new JScrollPane(warlock);
		warlock.setPreferredSize(new  Dimension(1500, 2000));
		rouge=new Collection_herospanel("rogue");
		setcard("Rouge", rouge,f);
		JScrollPane ru=new JScrollPane(rouge);
		rouge.setPreferredSize(new  Dimension(1500, 2000));

		netural=new Collection_herospanel("netural");
		setcard("neutral", netural,f);
		JScrollPane ne=new JScrollPane(netural);
		netural.setPreferredSize(new  Dimension(1500, 2500));

		myDeckPanel=new Collection_herospanel("deck");
		setdeck();
		myDeckPanel.setPreferredSize(new Dimension(1500,2500));
		JScrollPane de=new JScrollPane(myDeckPanel);

		enemyDeckPanel=new Collection_herospanel("enemy");
		setEnemyDeck();
		enemyDeckPanel.setPreferredSize(new Dimension(1500, 2500));
		JScrollPane ed=new JScrollPane(enemyDeckPanel);
		setEnemyDeck();
		deckbord =new Collection_deck(myDeckPanel,this);

		serchPanel=new Collection_search(f);
		serchPanel.setPreferredSize(new Dimension(1500, 2000));
		JScrollPane ser=new JScrollPane(serchPanel);
		/////add to tabpane
		tp.add(m,"Mage");
		tp.add(hu,"Hunter");
		tp.add(p,"Priest");
		tp.add(w,"Warlock");
		tp.add(ru,"Rouge");
		tp.add(ne,"Neutral");
		tp.add(ser,"search");
		tp.add(de,"My Deck");
		tp.add(ed, "enemy deck");
		tp.setPreferredSize(new Dimension(1500, 790));

		setpanel(f);
	}

	private void setcard(String name,JPanel p,MainFrame f) {
		for(Cards s : game.getPlayer().get_myCards()) {
			if(s.get_Class().equalsIgnoreCase(name)) {
				final JLabel lp =new JLabel(new ImageIcon( System.getProperty("user.dir")+"\\src\\card image\\"+s.get_Name()+".png"));
				lp.addMouseListener(new MouseListener() {
					@Override
					public void mouseReleased(MouseEvent arg0) {
					}

					@Override
					public void mousePressed(MouseEvent arg0) {
//						int x=JOptionPane.showConfirmDialog(null, "do you want to add this to your deck",
//								"add card to deck", JOptionPane.OK_CANCEL_OPTION);
//						if(x==JOptionPane.OK_OPTION) {
//							game.getPlayer().getMyDeck().addUsethisDeck(0);
//							game.getPlayer().getMyDeck().addWin(0);
//							if(game.getPlayer().getMyDeck().addCardToDeck(s)){
//								try {
//									Logger.getinsist().log(game.getPlayer().get_name(), "add card to deck", s.get_Name());
//								} catch (IOException e1) {
//									e1.printStackTrace();
//								}
								BiggerCard x=new BiggerCard(s, CollectionPanel.this, deckbord, myDeckPanel);
								CollectionPanel.this.getParent().add(x);					


//								deckbord.updateBut(myDeckPanel, CollectionPanel.this);
								deckbord.repaint();
								deckbord.revalidate();
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
				p.add(lp);
			}
		}
		for(Cards s2 : game.getPlayer().getMyStore().getBuyCard()) {
			if(s2.get_Class().equalsIgnoreCase(name)) {
				final JLabel lp1 =new JLabel(new ImageIcon( System.getProperty("user.dir")+"\\src\\card image\\"+s2.get_Name()+"1.png"));
				lp1.addMouseListener(new LockCardListener(f, s2, this));
				p.add(lp1);
			}
		}
	}

	public void setdeck() {
		int sum= current.size();
		for (int i = sum-1; i >=0; i--) {
			myDeckPanel.remove(current.get(i));
			current.remove(i);
			myDeckPanel.repaint();
			myDeckPanel.revalidate();
		}
		for(Cards s : game.getPlayer().get_mydeck()) {			
			final JLabel lp =new JLabel(new ImageIcon( System.getProperty("user.dir")+"\\src\\card image\\"+s.get_Name()+".png"));
			lp.addMouseListener( new MouseListener() {
								@Override
								public void mouseReleased(MouseEvent e) {
								}
								@Override
								public void mousePressed(MouseEvent e) {
									int x=JOptionPane.showConfirmDialog(null, "do you want to remove this from your deck",
											"remove from deck", JOptionPane.OK_CANCEL_OPTION);
									if(x==JOptionPane.OK_OPTION) {
										try {
											game.getPlayer().getMyDeck().addUsethisDeck(0);
											game.getPlayer().getMyDeck().addWin(0);
											Logger.getinsist().log(game.getPlayer().get_name(), "remove card from deck", s.get_Name());
										} catch (IOException e1) {
											e1.printStackTrace();
										}
										game.getPlayer().getMyDeck().getDeck().remove(s);
										
										myDeckPanel.remove(lp);
										myDeckPanel.repaint();
										myDeckPanel.revalidate();
										deckbord.updateBut(myDeckPanel, CollectionPanel.this);
										deckbord.repaint();
										deckbord.revalidate();
									}
								}
								@Override
								public void mouseExited(MouseEvent e) {
								}
								@Override
								public void mouseEntered(MouseEvent e) {
								}
								@Override
								public void mouseClicked(MouseEvent e) {
								}
							});
			myDeckPanel.add(lp);
			current.add(lp);
		}
	}
	
	public void setEnemyDeck(){
		int sum= enemyCurrent.size();
		for (int i = sum-1; i >=0; i--) {
			enemyDeckPanel.remove(enemyCurrent.get(i));
			enemyCurrent.remove(i);
			enemyDeckPanel.repaint();
			enemyDeckPanel.revalidate();
		}
		for(Cards s : game.getPlayer().getEnemyDeck().getDeck()) {			
			final JLabel lp =new JLabel(new ImageIcon( System.getProperty("user.dir")+"\\src\\card image\\"+s.get_Name()+".png"));
			lp.addMouseListener( new MouseListener() {
								@Override
								public void mouseReleased(MouseEvent e) {
								}
								@Override
								public void mousePressed(MouseEvent e) {
									int x=JOptionPane.showConfirmDialog(null, "do you want to remove this from enemy deck",
											"remove from deck", JOptionPane.OK_CANCEL_OPTION);
									if(x==JOptionPane.OK_OPTION) {
										try {

											Logger.getinsist().log(game.getPlayer().get_name(), "remove card from enemy deck", s.get_Name());
										} catch (IOException e1) {
											e1.printStackTrace();
										}
										game.getPlayer().getEnemyDeck().getDeck().remove(s);
										deckbord.getEnemyBut().setText("enemy deck  "+ game.getPlayer().getEnemyDeck().getHeroDeck().getname()+"   size : "+game.getPlayer().getEnemyDeck().getDeck().size());
										
										enemyDeckPanel.remove(lp);
										enemyDeckPanel.repaint();
										enemyDeckPanel.revalidate();
										deckbord.repaint();
										deckbord.revalidate();
									}
								}
								@Override
								public void mouseExited(MouseEvent e) {
								}
								@Override
								public void mouseEntered(MouseEvent e) {
								}
								@Override
								public void mouseClicked(MouseEvent e) {
								}
							});
			enemyDeckPanel.add(lp);
//			enemyDeckPanel.repaint();
//			enemyDeckPanel.revalidate();
			enemyCurrent.add(lp);
		}
	}
	
	
	
	private void setpanel(MainFrame f) throws Exception {
		setLayout(new BorderLayout());
		InfoPanel inf=InfoPanel.getinsist(f);
		add(inf,BorderLayout.NORTH);
		add(tp,BorderLayout.CENTER);
		add(deckbord,BorderLayout.EAST);
	}
}