package grapic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import Cardspackage.Cards;
import Cardspackage.Minions.BigGameHunter;
import Cardspackage.Minions.BluegillWarrior;
import Cardspackage.Minions.ChillwindYeti;
import Cardspackage.Minions.CurioCollector;
import Cardspackage.Minions.Dreadscale;
import Cardspackage.Minions.Gruul;
import Cardspackage.Minions.HighPriestAmet;
import Cardspackage.Minions.KronxDragonhoof;
import Cardspackage.Minions.LeperGnome;
import Cardspackage.Minions.MurlocRaider;
import Cardspackage.Minions.MurlocWarleader;
import Cardspackage.Minions.OasisSnapjaw;
import Cardspackage.Minions.Sandbinder;
import Cardspackage.Minions.Sathrovarr;
import Cardspackage.Minions.SeaGiant;
import Cardspackage.Minions.SecurityRover;
import Cardspackage.Minions.Shieldbearer;
import Cardspackage.Minions.SwampKingDred;
import Cardspackage.Minions.TheBlackKnight;
import Cardspackage.Minions.ThrallmarFarseer;
import Cardspackage.Minions.TombWarden;
import Cardspackage.Spells.ArcaneShot;
import Cardspackage.Spells.AstralRift;
import Cardspackage.Spells.Backstab;
import Cardspackage.Spells.BookofSpecters;
import Cardspackage.Spells.FriendlySmith;
import Cardspackage.Spells.HolySmite;
import Cardspackage.Spells.LearnDraconic;
import Cardspackage.Spells.PharaohBlessing;
import Cardspackage.Spells.Polymorph;
import Cardspackage.Spells.Sprint;
import Cardspackage.Spells.StrengthinNumbers;
import Cardspackage.Spells.Swarmoflocusts;
import Cardspackage.Spells.gift;
import Cardspackage.Weapons.BattleAxe;
import Cardspackage.Weapons.BloodFury;
import Cardspackage.Weapons.HeavyAxe;
import GAME.Gamestate;
import GAME.Logger;
import interfaces.Acceptable;
import interfaces.Visitor;
import myListeners.EnemyBattlegrounCardListener;
import myListeners.EnemyWeaponListener;
import myListeners.MyBattlegroundCardListener;
import myListeners.HandCardListener;
import playModel.Mapper;
import playModel.Player;


public class PlayPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	public static int i=0;
	private MainFrame f;	
	private JButton manabut;	
	private JLabel next;
	private Gamestate game;	
	private TextArea textArea;
	private Logger log;
	private JLabel turnPlayed;
	private static int roundGame=60;
	private Player me;
	private Player enemy;
	private Mapper map=Mapper.getinsist();
	private JLabel player2DeckRemind;
	private JLabel player1DeckRemind;
	private JLabel player1HandRemind;
	private JLabel player2HandRemind;
	private ArrayList<CardShow> CurrentBattleground=new ArrayList<>();
	private ArrayList<CardShow> CurrentHand=new ArrayList<>();
	private ArrayList<CardShow> weapons=new ArrayList<>();

	public PlayPanel(MainFrame f, TextArea t) throws Exception {
		initialPlayers();
		textArea=t;
		this.f=f;
		initial();
		initialNextTurnBtutton();
		initialLables();
		startGame();
	}
	private void initialPlayers() throws Exception {
		me=map.readMe();
		enemy=map.readEnemy();
	}
	private void startGame() throws Exception {
		addToHand(1);
		addToHand(1);
		addToHand(1);
		addToHand(2);
		addToHand(2);
		addToHand(2);
		setCard();
		JProgressBar jp=new JProgressBar(0, 60);
		jp.setBounds(10, 440, 150, 40);
		jp.setValue(0);
		jp.setString(0+"");
		jp.setStringPainted(true);
		Clock timer =new Clock(jp, this);
		add(jp);
		timer.start();		
	}
	private void initialNextTurnBtutton() {
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
	}
	private void initial() throws Exception {
		
		log=Logger.getinsist();
		game=Gamestate.getinsist();
		setPreferredSize(new Dimension(1800, 1000));
		setLayout(null);
	}
	private void initialLables() {
		player1DeckRemind = new JLabel(me.getDecksize()+"");
		player2DeckRemind=new JLabel(enemy.getDecksize()+"");
		player2HandRemind=new JLabel("3");
		player1HandRemind=new JLabel("3");
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
	}
	private void setWeaons() {
		if(me.getWeapon()!=null) {
			if(me.getWeapon().getDurability()==0) {
				me.setWeapon(null);
			}else {  addPlayerWeapon(me);	}
		}
		if(enemy.getWeapon()!=null) {
			if(enemy.getWeapon().getDurability()==0) {
				enemy.setWeapon(null);
			}else {		addPlayerWeapon(enemy);		}
		}
	}	
	private void setMyBattleGroundCard() {
		for(int i=0;i<7;i++) {
			if(me.getBattleGroundCard().get(i) !=null) {
				CardShow x=new CardShow(me.getBattleGroundCard().get(i));
				x.setBounds(200+160*i,500, 100, 150);
				x.addMouseListener(new MyBattlegroundCardListener(this, me.getBattleGroundCard().get(i), x));
				x.addMouseMotionListener(new MyBattlegroundCardListener(this, me.getBattleGroundCard().get(i), x));
				CurrentBattleground.add(x);
				add(x);
			}
		}
	}
	private void setMyHandCard() {
		int	j=-1;
		for(Cards s : me.getHand()) {
			final CardShow x=new CardShow(s);
			x.addMouseListener(new HandCardListener(this, s, x, me)); 
			x.addMouseMotionListener(new HandCardListener(this, s, x, me));
			CurrentHand.add(x);
			x.setBounds(1000+(j*100), 850, 100, 150);
			add(x);
			j--;
		}
	}
	private void setenemyHandCard() {
		int	j1=-1;
		for(Cards s : enemy.getHand()) {
			final CardShow x=new CardShow(s);
			x.addMouseListener(new HandCardListener(this, s, x, enemy));
			x.addMouseMotionListener(new HandCardListener(this, s, x, enemy));
			CurrentHand.add(x);
			x.setBounds(1000+(j1*100), 5, 100, 150);
			add(x);
			j1--;
		}
	}
	private void setenemyBattleGroundCard() {
		for(int i=0;i<7;i++) {
			if(enemy.getBattleGroundCard().get(i) !=null) {
				CardShow x=new CardShow(enemy.getBattleGroundCard().get(i));
				x.setBounds(200+160*i,300, 100, 150);
				x.addMouseListener(new EnemyBattlegrounCardListener(this, enemy.getBattleGroundCard().get(i), x));
				x.addMouseMotionListener(new EnemyBattlegrounCardListener(this, enemy.getBattleGroundCard().get(i), x));
				CurrentBattleground.add(x);
				add(x);
			}
		}
	}
	private void setCard() {
		removeLables();
		setWeaons();
		setMyHandCard();
		setMyBattleGroundCard();
		setenemyBattleGroundCard();
		setenemyHandCard();
		player1HandRemind.setText(me.getHand().size()+"");
		player2HandRemind.setText(enemy.getHand().size()+"");
	}
	private void drawHero() {
		HeroShow x1= new HeroShow(me.getHero());
		x1.setBounds(658, 660, 190, 200);
		add(x1);
		HeroShow x2= new HeroShow(enemy.getHero());
		x2.setBounds(658, 100, 190, 200);
		add(x2);
	}
	private  void finish(MainFrame f) throws Exception {
		if(map.isFinished(me, enemy, textArea)) {
			MenuPanel m=new MenuPanel(f);
			f.ChangePanel(m);			
		}
	}
	public void updatePanel() {
		setCard();
		repaint();
		revalidate();
	}
	public  static int getRoundGame() {
		return roundGame;
	}
	public static void setRoundGame(int rondGame) {
		roundGame = rondGame;
	}
	public TextArea getTextArea() {
		return textArea;
	}
	public void drawGem(Graphics g) {
		if(roundGame%2==me.getTurn()) {
			g.setColor(Color.RED);
			g.setFont(new Font("Tahoma", Font.BOLD, 20));
			g.drawString(map.getPreviousGem() +"/"+me.getCurrentgem(), 1030, 933);
			for(int i=0;i<me.getCurrentgem();i++) {
				g.drawImage(new ImageIcon("src\\button image\\gem"+(i+1)+".png").getImage(), 1090+33*i, 910, null);
			}
		}else {
			g.setColor(Color.RED);
			g.setFont(new Font("Tahoma", Font.BOLD, 20));
			g.drawString(map.getPreviousGem()+"/"+enemy.getCurrentgem(), 995, 70);
			for(int i=0;i<enemy.getCurrentgem();i++) {
				g.drawImage(new ImageIcon("src\\button image\\gem"+(i+1)+".png").getImage(), 1070+33*i, 40, null);
			}			
		}
	}
	private void firstRound() {
		roundGame--;
		manaSet();
		repaint();
	}
	protected void nextTurn() {
		if(roundGame==60) {
			firstRound();
			return;
		}
		try {
			log.log(game.getPlayer().get_name(), "clicked end turn ", "");
			finish(f);
		} catch (Exception e) {}
		map.readDeck(me, enemy);
		if(roundGame%2==1) {
			player1Turn();
		}else {
			player2Turn();
		}
		roundGame--;
		manaSet();
		if(next!=null)
			next.setVisible(false);
		repaint();
	}
	private void player1Turn() {
		turnPlayed.setText(((roundGame/2))+"");
		map.nextTurn(me);
		addToHand(me.getTurn()+1);
		setCard();
	}	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(new ImageIcon("src\\play image\\"+game.getBackBattleground()).getImage(), 0, 0, null);
		drawGem(g);
		drawHero();
	}
	private void addPlayerWeapon(Player p) {
		CardShow x=new CardShow(p.getWeapon());
		x.setBounds(560, 690-(520*p.getTurn()), 100, 150);
		x.addMouseListener(new  EnemyWeaponListener(this, p.getWeapon(), p));
		add(x);
		weapons.add(x);
	}
	private void removeLables() {
		for(int i=CurrentBattleground.size()-1;i>=0;i--) {
			remove(CurrentBattleground.get(i));
			CurrentBattleground.remove(CurrentBattleground.get(i));
		}
		for(int i=CurrentHand.size()-1;i>=0;i--) {
			remove(CurrentHand.get(i));
			CurrentHand.remove(CurrentHand.get(i));
		}
		for (CardShow cardShow : weapons) {
			remove(cardShow);
		}	
	}
	private void player2Turn() {
		map.nextTurn(enemy);
		turnPlayed.setText(roundGame/2+"");			
		addToHand(enemy.getTurn()+1);
		setCard();
	}
	private void addToHand(int turn) {	
		map.readDeck(me, enemy);
		if(turn==1) {
			map.addToHand(me);
			player1DeckRemind.setText(me.getDecksize()+"");
		}else {
			map.addToHand(enemy);
			player2DeckRemind.setText(enemy.getDecksize()+"");	
		}
	}
	public boolean addTobattleground(Cards s,int x,int y) throws Exception {
		if(roundGame%2==0) {
			if(map.addTobattleground(s, x, y, me, textArea)) {
				return true;
			}else {
				next.setVisible(true);			
				return false;
			}
		}else {
			if (map.addTobattleground(s, x, y, enemy, textArea)) { 
				return true;
			}else {
				next.setVisible(true);			
				return false;
			}			
		}
	}
	public void manaSet() {
		map.manaSet(me, enemy);
	}
}