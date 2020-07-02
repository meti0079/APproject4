package grapic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Cardspackage.Cards;
import Cardspackage.Minion;
import Cardspackage.Weapon;
import GAME.DeckReader;
import GAME.Gamestate;
import GAME.Logger;
import GAME.Players;
import hero.Heros;
import hero.Mage;
import myListeners.EnemyBattlegrounCardListener;
import myListeners.EnemyHandCardListener;
import myListeners.EnemyWeaponListener;
import myListeners.MyBattlegroundCardListener;
import myListeners.MyHandCardListener;
import myListeners.MyWeaponListener;


public class PlayPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	public static int i=0;
	private MainFrame f;	
	private int previousgem=1;
	private JButton manabut;	
	private JLabel next;
	private Gamestate game;	
	private Random random=new Random();
	private TextArea textArea;
	private Logger log;
	private JLabel turnPlayed;
	private int roundGame=60;
	private DeckReader deckReader;
	/////player 1
	private Heros player1Hero;
	private int currentgemPlayer1=1;
	private int player1Changes=0;
	private Weapon player1Weapon;
	private CardShow player1WeaponCard;
	private ArrayList<Cards> player1Hand;
	private ArrayList<Cards> player1Deck=new ArrayList<>();
	private ArrayList<Cards> player1Battleground;
	private ArrayList<JComponent> player1CurrentBattleground=new ArrayList<>();
	private ArrayList<JComponent> player1CurrentHand=new ArrayList<>();
	private JLabel player1DeckRemind;
	private JLabel player1HandRemind;
	private int player1Decksize;
	private CardPlace[] player1BattleGroundPlace=new CardPlace[7];
	////////  player 2
	private Heros player2Hero;
	private int player2Changes=0;
	private int currentgemPlayer2=1;
	private Weapon player2Weapon;
	private CardShow player2WeaponCard;
	private ArrayList<Cards> player2Hand;
	private ArrayList<Cards> player2Deck=new ArrayList<>();
	private ArrayList<Cards> player2Battleground;
	private ArrayList<JComponent> player2CurrentBattleground=new ArrayList<>();
	private ArrayList<JComponent> player2CurrentHand=new ArrayList<>();
	private JLabel player2DeckRemind;
	private int player2Decksize;
	private JLabel player2HandRemind;
	private CardPlace[] player2BattleGroundPlace=new CardPlace[7];

	public PlayPanel(MainFrame f, TextArea t) throws Exception {
		textArea=t;
		this.f=f;
		log=Logger.getinsist();
		game=Gamestate.getinsist();
		if(game.getState().equalsIgnoreCase("enemy")) {
			player1Hero= game.getPlayer().getMyDeck().getHeroDeck().clone();
			player2Hero=game.getPlayer().getEnemyDeck().getHeroDeck().clone();

		}else if(game.getState().equalsIgnoreCase("deck")) {
			player1Hero= new Mage();
			player2Hero=new Mage();			
			File f1=new File("C:\\Users\\MohammadMehdi\\git\\repository2\\APproject\\src\\main\\deckreader.json");
			Scanner s=new Scanner(f1);
			String se="";
			while(s.hasNext()) {
				se+=s.nextLine(); 
			}
			Gson gson=new GsonBuilder().setPrettyPrinting().create();
			deckReader=gson.fromJson(se, DeckReader.class);	

		}
		readDeck();
		player1Battleground=new ArrayList<>();
		player1Hand=new ArrayList<>();
		player2Battleground=new ArrayList<>();
		player2Hand=new ArrayList<>();
		setPreferredSize(new Dimension(1800, 1000));
		setLayout(null);
		player1Decksize=player1Deck.size();
		player1DeckRemind=new JLabel(player1Decksize+"");
		player2Decksize=player2Deck.size();
		player2DeckRemind=new JLabel(player2Decksize+"");
		player2HandRemind=new JLabel("3");
		player1HandRemind=new JLabel("3");
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
		addToHand(1);
		addToHand(1);
		addToHand(1);
		addToHand(2);
		addToHand(2);
		addToHand(2);
		setCard();
		next=new JLabel("click end turn");
		next.setForeground(Color.RED);
		next .setBounds(1280, 540, 100, 10);
		next.setVisible(false);
		add(next);
		turnPlayed=new JLabel("30");
		turnPlayed.setForeground(Color.RED);
		turnPlayed.setBounds(1430, 430,70, 30);
		turnPlayed.setFont(new Font("Tahoma", Font.BOLD, 30));
		add(turnPlayed);
		player1DeckRemind.setForeground(Color.red);
		player1DeckRemind.setFont(new Font("Tohama", Font.BOLD, 30));
		player1DeckRemind.setBounds(1380, 690, 120, 30);

		player2DeckRemind.setForeground(Color.red);
		player2DeckRemind.setFont(new Font("Tohama", Font.BOLD, 30));
		player2DeckRemind.setBounds(1370, 190, 120, 30);

		player1HandRemind.setForeground(Color.red);
		player1HandRemind.setFont(new Font("Tahoma", Font.BOLD, 30));
		player1HandRemind.setBounds(1030, 950, 30, 30);

		player2HandRemind.setBounds(995, 10, 30, 30);
		player2HandRemind.setFont(new Font("Tahoma", Font.BOLD, 30));
		player2HandRemind.setForeground(Color.red);

		add(player1HandRemind);
		add(player2HandRemind);
		add(player1DeckRemind);
		add(player2DeckRemind);
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
		if(roundGame==60) {
			roundGame--;
			manaSet();
			repaint();
			return;
		}
		try {
			log.log(game.getPlayer().get_name(), "clicked end turn ", "");
			finish(f);
		} catch (Exception e) {	
			e.printStackTrace();
		}
		if(roundGame%2==1) {
			turnPlayed.setText(((roundGame/2))+"");
			addToHand(1);
			setCard();
		}else {
			turnPlayed.setText(roundGame/2+"");			
			addToHand(2);
			setCard();
		}
		roundGame--;
		manaSet();
		if(next!=null)
			next.setVisible(false);
		repaint();
		i=0;
		for (Cards cards : player1Battleground) {
			cards.setUsedToAttack(false);
		}
		for (Cards cards : player1Hand) {
			cards.setUsedToAttack(false);
		}
		if(player1Weapon!=null)
			player1Weapon.setUsedToAttack(false);
		for (Cards cards : player2Battleground) {
			cards.setUsedToAttack(false);
		}
		for (Cards cards : player2Hand) {
			cards.setUsedToAttack(false);
		}
		if(player2Weapon!=null)
			player2Weapon.setUsedToAttack(false);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(new ImageIcon("src\\play image\\"+game.getBackBattleground()).getImage(), 0, 0, null);
		g.drawImage(new ImageIcon("src\\play image\\ca.png").getImage(), 660, 10, null);
		g.drawImage(new ImageIcon("src\\play image\\ca.png").getImage(), 725, 10, null);
		g.drawImage(new ImageIcon("src\\play image\\ca.png").getImage(), 790, 10, null);
		drawGem(g);
		drawHero();
	}
	public void manaSet() {
		if(roundGame%2==0) {
			if(previousgem==10) {
				currentgemPlayer1=10;
				return;
			}
			previousgem++;
			currentgemPlayer1=previousgem;
		}else {
			currentgemPlayer2=previousgem;			
		}
	}
	public void drawGem(Graphics g) {
		if(roundGame%2==0) {
			g.setColor(Color.RED);
			g.setFont(new Font("Tahoma", Font.BOLD, 20));
			g.drawString(previousgem +"/"+currentgemPlayer1, 1030, 933);
			for(int i=0;i<currentgemPlayer1;i++) {
				g.drawImage(new ImageIcon("src\\button image\\gem"+(i+1)+".png").getImage(), 1090+33*i, 910, null);
			}
		}else {
			g.setColor(Color.RED);
			g.setFont(new Font("Tahoma", Font.BOLD, 20));
			g.drawString(previousgem +"/"+currentgemPlayer2, 995, 70);
			for(int i=0;i<currentgemPlayer2;i++) {
				g.drawImage(new ImageIcon("src\\button image\\gem"+(i+1)+".png").getImage(), 1070+33*i, 40, null);
			}			
		}
	}
	private void addToHand(int turn) {
		int x=0;	
		readDeck();
		if(game.getState().equalsIgnoreCase("enemy") ||game.getState().equalsIgnoreCase("computer")  ) {
			if(turn==1) {
				if(player1Deck.size()==0) {
					x=0;
				}else {
					x=random.nextInt(player1Deck.size());						
				}
				player1Decksize--;
				player1DeckRemind.setText(player1Decksize+"");
				if(player1Hand.size()<10) {
					player1Hand.add(player1Deck.get(Math.abs(x)));
					player1Deck.remove(Math.abs(x));
				}else {
					player1Deck.remove(Math.abs(x));	
				}
			}else {
				if(player2Deck.size()==0) {
					x=0;
				}else {
					x=random.nextInt(player2Deck.size());						
				}
				if(player2Hand.size()<10) {
					player2Hand.add(player2Deck.get(Math.abs(x)));
					player2Deck.remove(Math.abs(x));
				}else {
					player2Deck.remove(Math.abs(x));	
				}
				player2Decksize--;
				player2DeckRemind.setText(player2Decksize+"");	
			}
		}else {
			x=0;
			if(turn==1) {
				player1Decksize--;
				player1DeckRemind.setText(player1Decksize+"");
				if(player1Hand.size()<10) {
					player1Hand.add(player1Deck.get(x));
					player1Deck.remove(x);
				}else {
					player1Deck.remove(x);	
				}
			}else {
				if(player2Hand.size()<10) {
					player2Hand.add(player2Deck.get(x));
					player2Deck.remove(x);
				}else {
					player2Deck.remove(x);	
				}
				player2Decksize--;
				player2DeckRemind.setText(player2Decksize+"");	
			}
		}
	}
	public void addTobattleground(Cards s) {
		if(roundGame%2==0) {
			if(s.get_Mana()<=currentgemPlayer1) {
				if(s.getType().equalsIgnoreCase("minion")) {
					if(player1Battleground.size()<=6) {
						player1Hand.remove(s);
						player1Battleground.add(copy(s));
						player1Battleground.get(player1Battleground.size()-1).setUsedToAttack(true);
						String se=game.getPlayer().get_name()+"  summon  "+ s.get_Name()+"\n";
						textArea.append(se);
					}else {
						JOptionPane.showConfirmDialog(null, "your battleground if full play a card or click next turn","cant plan",JOptionPane.CLOSED_OPTION);
					}						
				}else if(s.getType().equalsIgnoreCase("Spell")) {
					String se=game.getPlayer().get_name()+"  played  "+ s.get_Name()+"\n";
					textArea.append(se);
					addUse(s);
					player1Hand.remove(s);			
				}else {
					String se=game.getPlayer().get_name()+"  Summon  "+ s.get_Name()+"\n";
					textArea.append(se);
					addUse(s);
					player1Weapon=(Weapon) copyWeapon((Weapon) s);
					player1Weapon.setUsedToAttack(true);
					player1Hand.remove(s);
				}
				currentgemPlayer1-=s.get_Mana();
			}else {
				next.setVisible(true);
			}
		}else {
			if(s.get_Mana()<=currentgemPlayer2) {
				if(s.getType().equalsIgnoreCase("minion")) {
					if(player2Battleground.size()<=6) {
						player2Hand.remove(s);
						player2Battleground.add(copy(s));
						player2Battleground.get(player2Battleground.size()-1).setUsedToAttack(true);
						String se="enemy  summon  "+ s.get_Name()+"\n";
						textArea.append(se);
					}else {
						JOptionPane.showConfirmDialog(null, "enemy battleground if full play a card or click next turn","cant plan",JOptionPane.CLOSED_OPTION);
					}						
				}else if(s.getType().equalsIgnoreCase("Spell")) {
					String se=" enemy  played  "+ s.get_Name()+"\n";
					textArea.append(se);
					player2Hand.remove(s);			
				}else {
					String se="enemy  Summon  "+ s.get_Name()+"\n";
					textArea.append(se);
					player2Weapon=(Weapon) copyWeapon((Weapon) s);
					player2Weapon.setUsedToAttack(true);
					player2Hand.remove(s);
				}
				currentgemPlayer2-=s.get_Mana();
			}else {
				next.setVisible(true);
			}
		}
	}
	private void addPlayer1Weapon() {
		player1WeaponCard=new CardShow(player1Weapon);
		player1WeaponCard.setBounds(560, 690, 100, 150);
		player1WeaponCard.addMouseListener(new MyWeaponListener(this, player1Weapon,game.getPlayer().get_name()));
		add(player1WeaponCard);
	}
	private void addPlayer2Weapon() {
		player2WeaponCard=new CardShow(player2Weapon);
		player2WeaponCard.setBounds(560, 150, 100, 150);
		player2WeaponCard.addMouseListener(new EnemyWeaponListener(this, player2Weapon));
		add(player2WeaponCard);
	}
	private void setCard() {

		for(int i=player1CurrentBattleground.size()-1;i>=0;i--) {
			remove(player1CurrentBattleground.get(i));
			player1CurrentBattleground.remove(player1CurrentBattleground.get(i));
		}
		for(int i=player1CurrentHand.size()-1;i>=0;i--) {
			remove(player1CurrentHand.get(i));
			player1CurrentHand.remove(player1CurrentHand.get(i));
		}
		if(player1WeaponCard!=null) {
			remove(player1WeaponCard);
		}
		if(player1Weapon!=null) {
			if(player1Weapon.getDurability()==0) {
				player1Weapon=null;
			}else {
				addPlayer1Weapon();				
			}
		}
		////////// player2
		for(int i=player2CurrentBattleground.size()-1;i>=0;i--) {
			remove(player2CurrentBattleground.get(i));
			player2CurrentBattleground.remove(player2CurrentBattleground.get(i));
		}
		for(int i=player2CurrentHand.size()-1;i>=0;i--) {
			remove(player2CurrentHand.get(i));
			player2CurrentHand.remove(player2CurrentHand.get(i));
		}
		if(player2WeaponCard!=null) {
			remove(player2WeaponCard);
		}
		if(player2Weapon!=null) {
			if(player2Weapon.getDurability()==0) {
				player2Weapon=null;
			}else {
				addPlayer2Weapon();				
			}

		}
		//////////player 1

		int[] g=new int[7];g[0]=0;g[1]=-1;g[2]=1;g[3]=-2;g[4]=2;g[5]=-3;g[6]=3;
		int y =0;
		for(Cards s : player1Battleground) {
			CardShow x=new CardShow(s);
			x.setBounds(700+(g[y]*100),500, 100, 150);
			x.addMouseListener(new MyBattlegroundCardListener(this, s));
			x.addMouseMotionListener(new MouseMotionListener() {

				@Override
				public void mouseMoved(MouseEvent e) {}

				@Override
				public void mouseDragged(MouseEvent e) {
					if(roundGame%2==0) {
						int newX = e.getX() + x.getX();
						int newY = e.getY() + x.getY();
						x.setBounds(newX, newY, 100	, 150);	
					}
				}
			});
			player1CurrentBattleground.add(x);
			add(x);
			y++;
		}
		int	j=-1;
		for(Cards s : player1Hand) {
			final CardShow x=new CardShow(s);
			x.addMouseListener(new MyHandCardListener(this, s));
			player1CurrentHand.add(x);
			x.setBounds(1000+(j*100), 850, 100, 150);
			add(x);
			j--;
		}

		//////player2 
		int[] g2=new int[7];g2[0]=0;g2[1]=-1;g2[2]=1;g2[3]=-2;g2[4]=2;g2[5]=-3;g2[6]=3;
		int y2 =0;
		for(Cards s : player2Battleground) {
			CardShow x=new CardShow(s);
			x.setBounds(700+(g2[y2]*100),300, 100, 150);
			x.addMouseMotionListener(new MouseMotionListener() {

				@Override
				public void mouseMoved(MouseEvent e) {}

				@Override
				public void mouseDragged(MouseEvent e) {
					if(roundGame%2==1) {
						int newX = e.getX() + x.getX();
						int newY = e.getY() + x.getY();
						x.setBounds(newX, newY, 100	, 150);	
					}
				}
			});
			x.addMouseListener(new EnemyBattlegrounCardListener(this, s));
			player2CurrentBattleground.add(x);
			add(x);
			y2++;
		}
		int	j2=-1;
		for(Cards s : player2Hand) {
			final CardShow x=new CardShow(s);
			x.addMouseListener(new EnemyHandCardListener(this, s));
			player2CurrentHand.add(x);
			x.setBounds(1000+(j2*100), 5, 100, 150);
			add(x);
			j2--;
		}
		player1HandRemind.setText(player1Hand.size()+"");
		player2HandRemind.setText(player2Hand.size()+"");
	}
	private void drawHero() {
		HeroShow x1= new HeroShow(player1Hero);
		x1.setBounds(658, 660, 190, 200);
		add(x1);
		HeroShow x2= new HeroShow(player2Hero);
		x2.setBounds(658, 100, 190, 200);
		add(x2);
	}
	private  void finish(MainFrame f) throws Exception {
		if(player2Hero.get_HP()==0  || player1Hero.get_HP()==0||roundGame==0) {
			if(player2Hero.get_HP()> player1Hero.get_HP()) {
				String se="Enemy  win the game !!!!!!";
				textArea.append(se);
				JOptionPane.showConfirmDialog(null, "ENEMY WON !!!!", "game finished", JOptionPane.OK_CANCEL_OPTION);
				log.log(game.getPlayer().get_name(), "enemy  won the match", "");
			}else {
				String se=game.getPlayer().get_name()+" win the game !!!!!!";
				textArea.append(se);
				JOptionPane.showConfirmDialog(null, "YOU WON !!!!", "game finished", JOptionPane.OK_CANCEL_OPTION);
				log.log(game.getPlayer().get_name(), game.getPlayer().get_name()+"  won the match", "");
				if(game.getState().equalsIgnoreCase("enemy"))
					game.getPlayer().getMyDeck().addWin();
			}
			if(game.getState().equalsIgnoreCase("enemy")) {
				game.getPlayer().addPlays();				
				game.getPlayer().getMyDeck().addUsethisDeck();
				game.setPlayPassive(null);				
			}
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
		if(game.getState().equalsIgnoreCase("enemy"))
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
		return player1Changes;
	}
	public int getChanges1() {
		return player2Changes;
	}
	public void setChanges(int changes) {
		this.player1Changes = changes;
	}
	public void setChanges1(int changes) {
		this.player2Changes = changes;
	}
	public ArrayList<Cards> getHand1() {
		return player1Hand;
	}
	public ArrayList<Cards> getDeck1() {
		return player1Deck;
	}
	public ArrayList<Cards> getHand2() {
		return player2Hand;
	}
	public ArrayList<Cards> getDeck2() {
		return player2Deck;
	}
	public TextArea getTextArea() {
		return textArea;
	}

	//	public boolean isInMyBoard(int x , int y) {
	//		if
	//	}
	//	public boolean isInEnemyBoard(int x , int y) {
	//		
	//	}
	private void readDeck() {
		if (game.getState().equalsIgnoreCase("enemy")) {
			if(player1Deck.size()==0 ) {
				player1Deck=(ArrayList<Cards>) game.getPlayer().get_mydeck().clone();
				player1Decksize=player1Deck.size();
				JOptionPane.showMessageDialog(null, "player1 : Deck update");	
			}
			if( player2Deck.size()==0) {
				player2Deck=(ArrayList<Cards>) game.getPlayer().getEnemyDeck().getDeck().clone();
				player2Decksize=player2Deck.size();
				JOptionPane.showMessageDialog(null, "player2 : Deck update");
			}

		}else if(game.getState().equalsIgnoreCase("Deck")){
			if(player1Deck.size()==0 ) {
				player1Deck=(ArrayList<Cards>) deckReader.cardFactory("friend").clone();
				player1Decksize=player1Deck.size();
				JOptionPane.showMessageDialog(null, "player1 : Deck update");	
			}
			if( player2Deck.size()==0) {
				player2Deck=(ArrayList<Cards>) deckReader.cardFactory("enemy").clone();
				player2Decksize=player2Deck.size();
				JOptionPane.showMessageDialog(null, "player2 : Deck update");
			}


		}else {

		}
	}


}