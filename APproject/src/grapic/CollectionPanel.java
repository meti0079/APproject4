package grapic;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import Cardspackage.Cards;
import GAME.Gamestate;
import myListeners.CollectionCardListener;
import myListeners.DeckCardListener;
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
	private Collection_herospanel deckPanel;
	private Collection_deck deckbord;
	private ArrayList<JLabel > current = new ArrayList<>();
	private Collection_search serchPanel;

	public CollectionPanel(MainFrame f) throws Exception {
		game=Gamestate.getinsist();
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

		deckPanel=new Collection_herospanel("deck");
		setdeck();
		deckPanel.setPreferredSize(new Dimension(1500,2500));
		JScrollPane de=new JScrollPane(deckPanel);

		deckbord =new Collection_deck(deckPanel,this);

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
		tp.setPreferredSize(new Dimension(1500, 790));

		setpanel(f);
	}

	private void setcard(String name,JPanel p,MainFrame f) {
		for(Cards s : game.getPlayer().get_myCards()) {
			if(s.get_Class().equalsIgnoreCase(name)) {
				final JLabel lp =new JLabel(new ImageIcon( System.getProperty("user.dir")+"\\src\\card image\\"+s.get_Name()+".png"));
				lp.addMouseListener(new CollectionCardListener(s, deckPanel, this, deckbord));
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
			deckPanel.remove(current.get(i));
			current.remove(i);
			deckPanel.repaint();
			deckPanel.revalidate();
		}
		for(Cards s : game.getPlayer().get_mydeck()) {			
			final JLabel lp =new JLabel(new ImageIcon( System.getProperty("user.dir")+"\\src\\card image\\"+s.get_Name()+".png"));
			lp.addMouseListener(new DeckCardListener(deckPanel, s, lp));
			deckPanel.add(lp);
			current.add(lp);
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