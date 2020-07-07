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
	private JButton enemyBut;
	private Collection_herospanel x;
	private CollectionPanel y;
	public Collection_deck(Collection_herospanel x,CollectionPanel y) throws Exception {
		initial();
		this.x=x;
		this.y=y;
		allBut=new ArrayList<>();
		log =Logger.getinsist();
		game=Gamestate.getinsist();
		initialAddDeckButton();
		initialChangeNameButton();
		initialSelectHeroButton();
		initialEnemyButton();
	}
	private void initialEnemyButton() {
		enemyBut=new JButton("enemy deck   "+ game.getEnemy().getEnemyDeck().getHeroDeck().getname()+"    size : "+game.getEnemy().getEnemyDeck().getDeck().size());
		enemyBut.setBounds(10, 60, 280, 40);
		enemyBut.setFont(new Font("tahoma", Font.BOLD, 15));
		enemyBut.setBackground(new Color(165, 62, 22));
		add(enemyBut);
	}
	private void initialAddDeckButton() {
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
					if(game.getPlayer().getalldeck().size()>16) {
						JOptionPane.showConfirmDialog(null, "you have maximum deck!!! delet or edit some", "error", JOptionPane.YES_OPTION);
						return;
					}	
					makeNewDeck();
					update();
				} catch (Exception e1) {e1.printStackTrace();}
			}
		});
		add(b);
	}
	private void makeNewDeck() throws Exception {
		Decks s = new Decks();
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
		s.setHeroDeck(n);
		game.getPlayer().getalldeck().add(s);
		game.getPlayer().setMyDeck(game.getPlayer().getalldeck().size()-1);
		log.log(game.getPlayer().get_name(), "add deck ", s.getName());
		update();
	}
	private void initialSelectHeroButton() {
		JButton edit1=new JButton(new ImageIcon("src\\button image\\edit.png"));
		edit1.setContentAreaFilled(false);
		edit1.setBorder(BorderFactory.createEmptyBorder());
		edit1.setBounds(25, 830, 100, 50);
		edit1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					log.log(game.getPlayer().get_name(), "clicked chang hero button ", "");
					String [] myhero=new String[game.getPlayer().get_myheros().size()] ;
					for(int i=0 ;i< game.getPlayer().get_myheros().size();i++)
						myhero[i]=game.getPlayer().get_myheros().get(i).getname();
					String n="";
					n = (String)JOptionPane.showInputDialog(null, "select deck hero ",
							"select", JOptionPane.QUESTION_MESSAGE, null,myhero, myhero[0]);
					if(n.equalsIgnoreCase("")) {
					}else {
						game.getPlayer().getMyDeck().addUsethisDeck(0);
						game.getPlayer().getMyDeck().addWin(0);
						game.getPlayer().getMyDeck().setHeroDeck(n);
						log.log(game.getPlayer().get_name(), "change hero of deck  ", "to : " +n);
					}
				} catch (Exception e1) {e1.printStackTrace();}
			}
		});
		add(edit1);
		updateBut(x,y);
	}
	private void initialChangeNameButton() {
		JButton edit=new JButton(new ImageIcon("src\\button image\\edit2.png"));
		edit.setContentAreaFilled(false);
		edit.setBorder(BorderFactory.createEmptyBorder());
		edit.setBounds(155, 830, 100, 50);
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					log.log(game.getPlayer().get_name(), "clicked editName deck  button ", "");
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
							update();
							log.log(game.getPlayer().get_name(), "deck name edited ","new name of deck : "+name );
							break;
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		add(edit);
	}
	public JButton getEnemyBut() {
		return enemyBut;
	}
	public void update() {
		enemyBut.setText("enemy deck  "+ game.getEnemy().getEnemyDeck().getHeroDeck().getname()
				+"   size : "+game.getEnemy().getEnemyDeck().getDeck().size());
		this.updateBut(x, y);
		this.repaint();
		this.revalidate();
	}
	@Override	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon icon =new ImageIcon("src\\button image\\2.png");
		g.drawImage(icon.getImage(), 5, 0, null);
		drawDeckPlace(g);
	}
	private void drawDeckPlace(Graphics g) { 
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
		int i=1;
		for(Decks s: game.getPlayer().getalldeck()) {
			makeDeck(s, i);
			i++;
		}
	}
	public void makeDeck(Decks s , int i) {
		JButton b= new JButton(s.getName()+"       "+s.getHeroDeck().getname()+ "   size :"+ s.getDeck().size());
		b.setFont(new Font("tahoma", Font.BOLD, 15));
		b.setBackground(new Color(165, 42, 42));
		b.setBounds(10, 45*i+60, 280, 40);
		int tt=i-1;
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					log.log(game.getPlayer().get_name(), "change deck", "to " +s.getName());
				} catch (IOException e1) {e1.printStackTrace();}
				game.getPlayer().setMyDeck(tt);
				y.setdeck();
				x.update();}
		});
		add(b);
		allBut.add(b);
	}
	private void initial() {
		setLayout(null);
		setPreferredSize(new Dimension(300, 900));
		setBackground(new Color(162, 82,45));
	}
}
