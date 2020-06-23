package grapic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import Cardspackage.Cards;
import Cardspackage.Minion;
import Cardspackage.Weapon;
import GAME.Gamestate;
import GAME.Logger;
import myListeners.MyBattlegroundCardListener;
import myListeners.MyHandCardListener;
import myListeners.MyWeaponListener;


public class PlayPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	public static int i=0;
	private int currentgem=1;
	private int previousgem=1;
	private JButton manabut;	
	private ArrayList<Cards> hand;
	private ArrayList<Cards> battleground;
	private ArrayList<Cards> deck;
	private Gamestate game;	
	private Random random=new Random();
	private JLabel next;
	private Weapon myWeapon;
	private ArrayList<JComponent> currentHand=new ArrayList<>();
	private ArrayList<JComponent> currentBattleground=new ArrayList<>();
	private int deckRound=0;
	private int roundGame=0;
	private Logger log;
	private TextArea textArea;
	private JLabel turndo;
	private int changes=0;
	private CardShow we;
	private MainFrame f;
	public PlayPanel(MainFrame f, TextArea t) throws Exception {
		textArea=t;
		this.f=f;
		log=Logger.getinsist();
		game=Gamestate.getinsist();
		deck=(ArrayList<Cards>) game.getPlayer().get_mydeck().clone();
		battleground=new ArrayList<>();
		hand=new ArrayList<>();
		setPreferredSize(new Dimension(1800, 1000));
		setLayout(null);
		manabut= new JButton();
		manabut.setBounds(1280, 400, 120, 80);
		manabut.setBorder(BorderFactory.createEmptyBorder());
		manabut.setContentAreaFilled(false);
		manabut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				nextTurn();
			}
		});
		add(manabut);
		addToHand();
		addToHand();
		addToHand();
		setCard();
		next=new JLabel("click end turn");
		next.setForeground(Color.RED);
		next .setBounds(1280, 540, 120, 10);
		next.setVisible(false);
		add(next);
		turndo=new JLabel();
		turndo.setForeground(Color.RED);
		turndo.setBounds(1380, 690, 120, 30);
		turndo.setFont(new Font("Tahoma", Font.BOLD, 30));


		add(turndo);
		JProgressBar jp=new JProgressBar(0, 60);
		jp.setBounds(10, 440, 150, 40);
		jp.setValue(0);
		jp.setString(0+"");
		jp.setStringPainted(true);
		Clock timer =new Clock(jp, this);
		add(jp);
		timer.start();
	}


	protected void nextTurn() {
		try {
			log.log(game.getPlayer().get_name(), "clicked end turn ", "");
			finish(f);
		} catch (Exception e) {	
			e.printStackTrace();
		}
		turndo.setText((29-roundGame)+"");
		roundGame++;
		addToHand();
		setCard();
		manaSet();
		if(next!=null)
			next.setVisible(false);
		repaint();
		i=0;

		for (Cards cards : battleground) {
			cards.setUsedToAttack(false);
		}
		for (Cards cards : hand) {
			cards.setUsedToAttack(false);
		}
		if(myWeapon!=null)
			myWeapon.setUsedToAttack(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(new ImageIcon("src\\play image\\nattle.jpg").getImage(), 0, 0, null);
		g.drawImage(new ImageIcon("src\\play image\\ca.png").getImage(), 660, 10, null);
		g.drawImage(new ImageIcon("src\\play image\\ca.png").getImage(), 725, 10, null);
		g.drawImage(new ImageIcon("src\\play image\\ca.png").getImage(), 790, 10, null);
		drawGem(g);
		drawHero(g,game.getPlayer().getMyDeck().getHeroDeck().getname());
	}

	public void manaSet() {
		if(previousgem==10) {
			currentgem=10;
			return;
		}
		previousgem++;
		currentgem=previousgem;

	}
	public void drawGem(Graphics g) {	
		g.setColor(Color.RED);
		g.setFont(new Font("Tahoma", Font.BOLD, 20));
		g.drawString(previousgem +"/"+currentgem, 1030, 933);
		for(int i=0;i<currentgem;i++) {
			g.drawImage(new ImageIcon("src\\button image\\gem"+(i+1)+".png").getImage(), 1090+33*i, 910, null);
		}
	}
	private void addToHand() {
		if(deck.size()==0 &&deckRound==0) {
			deck=(ArrayList<Cards>) game.getPlayer().get_mydeck().clone();
			deckRound=2;
		}
		if(hand.size()<10) {
			int x=0;
			if(deck.size()==0) {
				x=0;
			}else {
				x=random.nextInt(deck.size());						
			}
			hand.add(deck.get(Math.abs(x)));
			deck.remove(Math.abs(x));
		}
	}

	public void addTobattleground(Cards s) {
		if(s.get_Mana()<=currentgem) {
			if(s.getType().equalsIgnoreCase("minion")) {
				if(battleground.size()<=6) {
					hand.remove(s);
					battleground.add(copy(s));
					battleground.get(battleground.size()-1).setUsedToAttack(true);
					String se=game.getPlayer().get_name()+"  summon  "+ s.get_Name()+"\n";
					textArea.append(se);
				}else {
					JOptionPane.showConfirmDialog(null, "your battleground if full play a card or click next turn","cant plan",JOptionPane.CLOSED_OPTION);
				}						
			}else if(s.getType().equalsIgnoreCase("Spell")) {
				String se=game.getPlayer().get_name()+"  played  "+ s.get_Name()+"\n";
				textArea.append(se);
				addUse(s);
				hand.remove(s);			
			}else {
				String se=game.getPlayer().get_name()+"  Summon  "+ s.get_Name()+"\n";
				textArea.append(se);
				addUse(s);
				myWeapon=(Weapon) copyWeapon((Weapon) s);
				myWeapon.setUsedToAttack(true);
				hand.remove(s);
			}
			currentgem-=s.get_Mana();
		}else {
			next.setVisible(true);
		}
	}
	private void addWeapon() {
		we=new CardShow(myWeapon);
		we.setBounds(560, 690, 100, 150);
		we.addMouseListener(new MyWeaponListener(this, myWeapon));
		add(we);
	}


	private void setCard() {
		for(int i=currentBattleground.size()-1;i>=0;i--) {
			remove(currentBattleground.get(i));
			currentBattleground.remove(currentBattleground.get(i));
		}
		for(int i=currentHand.size()-1;i>=0;i--) {
			remove(currentHand.get(i));
			currentHand.remove(currentHand.get(i));
		}
		if(we!=null) {
			remove(we);
		}
		if(myWeapon!=null)
			addWeapon();

		int[] g=new int[7];g[0]=0;g[1]=-1;g[2]=1;g[3]=-2;g[4]=2;g[5]=-3;g[6]=3;
		int y =0;
		for(Cards s : battleground) {
			CardShow x=new CardShow(s);
			x.setBounds(700+(g[y]*100),500, 100, 150);
			x.addMouseListener(new MyBattlegroundCardListener(this, s));
			currentBattleground.add(x);
			add(x);
			y++;
		}
		int	j=-1;
		for(Cards s : hand) {
			final CardShow x=new CardShow(s);
			x.addMouseListener(new MyHandCardListener(this, s));
			currentHand.add(x);
			x.setBounds(1000+(j*100), 850, 100, 150);
			add(x);
			j--;
		}	
	}


	private void drawHero(Graphics g, String hero) {
		g.drawImage(new ImageIcon("src\\play image\\"+hero+".png").getImage(), 658,660, null);
	}
	private  void finish(MainFrame f) throws Exception {
		if(roundGame==30) {
			String se=game.getPlayer().get_name()+"  win the match!!!!!  \n";
			textArea.append(se);
			JOptionPane.showConfirmDialog(null, "YOU WON !!!!", "game finished", JOptionPane.OK_CANCEL_OPTION);
			log.log(game.getPlayer().get_name(), game.getPlayer().get_name()+"  won the match", "");
			game.setPlayPassive(null);
			game.getPlayer().addPlays();
			game.getPlayer().getMyDeck().addWin();
			game.getPlayer().getMyDeck().addUsethisDeck();
			MenuPanel m=new MenuPanel(f);
			f.remove(PlayPanel.this);
			f.setContentPane(m);
			f.revalidate();
			f.repaint();
			f.pack();
			f.setLocationRelativeTo(null);
		}
	}
	public void addUse(Cards s) {
		for(Cards q: game.getPlayer().getMyDeck().getDeck())
			if(s.get_Name().equals(s.get_Name()))
				q.addUse();
	}

	protected Cards copy(Cards card) {
		Minion x=(Minion)card;
		Minion s=new Minion();
		s.setAttack(x.getAttack());
		s.Set_Class(x.getClass()+"");
		s.setHp(x.getHp());
		s.Set_Mana(card.get_Mana());
		s.Set_Name(card.get_Name());
		s.Set_Rarity(card.get_Rarity());
		s.setBattlecry(card.isBattlecry());
		s.setDeathrattle(card.isDeathrattle());
		s.setDescription(card.getDescription());
		s.setDivineShield(card.isDivineShield());
		s.setQuest(card.isQuest());
		s.setRush(card.isRush());
		s.setTaunt(card.isTaunt());
		s.setWindfury(card.isWindfury());
		return s;
	}
	protected Cards copyWeapon(Weapon card) {
		Weapon s=new Weapon();
		s.setAttack(card.getAttack());
		s.Set_Class(card.getClass()+"");
		s.Set_Mana(card.get_Mana());
		s.Set_Name(card.get_Name());
		s.Set_Rarity(card.get_Rarity());
		s.setBattlecry(card.isBattlecry());
		s.setDeathrattle(card.isDeathrattle());
		s.setDescription(card.getDescription());
		s.setDivineShield(card.isDivineShield());
		s.setQuest(card.isQuest());
		s.setRush(card.isRush());
		s.setTaunt(card.isTaunt());
		s.setWindfury(card.isWindfury());
		s.setDurability(card.getDurability());
		return s;
	}
	public void updatePanel() {
		setCard();
		repaint();
		revalidate();
	}
	public int getRoundGame() {
		return roundGame;
	}
	public void setRoundGame(int roundGame) {
		this.roundGame = roundGame;
	}
	public int getChanges() {
		return changes;
	}
	public void setChanges(int changes) {
		this.changes = changes;
	}
	public ArrayList<Cards> getHand() {
		return hand;
	}
	public ArrayList<Cards> getDeck() {
		return deck;
	}

	public TextArea getTextArea() {
		return textArea;
	}

}