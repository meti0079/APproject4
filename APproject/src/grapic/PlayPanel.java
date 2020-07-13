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
import Cardspackage.Card;
import GAME.ExportHeroPower;
import GAME.ExportPassives;
import GAME.ExportVisitor;
import GAME.Gamestate;
import GAME.Logger;
import myListeners.BattlegrounCardListener;
import myListeners.HandCardListener;
import myListeners.HeroPowerListener;
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
	private Mapper map;
	private JLabel player2DeckRemind;
	private JLabel player1DeckRemind;
	private JLabel player1HandRemind;
	private JLabel player2HandRemind;
	private ArrayList<CardShow> CurrentBattleground=new ArrayList<>();
	private ArrayList<CardShow> CurrentHand=new ArrayList<>();
	private ArrayList<CardShow> weapons=new ArrayList<>();
	private ArrayList<HeroShow> heros=new ArrayList<>(); 
	private JProgressBar[] progres=new JProgressBar[2];
	private ExportVisitor visitor=new ExportVisitor();
	private ExportHeroPower heroVisitor=new ExportHeroPower();
	private ArrayList<HeroPowerShow> heroPowers=new ArrayList<>();
	
	
	public PlayPanel(MainFrame f, TextArea t) throws Exception {
		initial();
		initialPlayers();
		textArea=t;
		this.f=f;
		initialNextTurnBtutton();
		initialLables();
		drawHeroPower();
		startGame();
	}
	private void initialPlayers() throws Exception {
		me=map.readMe();
		enemy=map.readEnemy();
	}
	private void startGame() throws Exception {
		initialPassive(me);
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
	private void  initialPassive(Player pp) throws Exception {
		PassivePanel p=new PassivePanel(pp);
		p.setBounds(200, 300, 1000, 500);
		this.add(p);

	}
	private void initialNextTurnBtutton() {
		manabut= new JButton();
		manabut.setBounds(1280, 400, 120, 80);
		manabut.setBorder(BorderFactory.createEmptyBorder());
		manabut.setContentAreaFilled(false);
		manabut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					nextTurn();
				} catch (Exception e) {e.printStackTrace();	}
			}
		});
		add(manabut);
	}
	private void setQuest(Player p) {
		if(progres[p.getTurn()]==null) {
			JProgressBar jp=new JProgressBar(0, p.getQuest().getMission());
			jp.setBounds(10, 240+((p.getTurn()+1)%2)*400, 150, 40);
			jp.setValue(0);
			add(jp);
			progres[p.getTurn()]=jp;			
		}else {
			progres[p.getTurn()].setValue(p.getQuest().getHave());
		}
	}
	private void updateProgres(Player p) {
		if(map.checkQuest(p)) {
			setQuest(p);			
		}else {
			if(progres[p.getTurn()]!=null)
			remove(progres[p.getTurn()]);
			progres[p.getTurn()]=null;
		}
	}
	private void initial() throws Exception {
		map=Mapper.getinsist();
		log=Logger.getinsist();
		game=Gamestate.getinsist();
		setPreferredSize(new Dimension(1800, 1000));
		setLayout(null);
	}

	private void initialLables() {
		drawHero();
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
			}else {  addPlayerWeapon(me, enemy);	}
		}
		if(enemy.getWeapon()!=null) {
			if(enemy.getWeapon().getDurability()==0) {
				enemy.setWeapon(null);
			}else {		addPlayerWeapon(enemy, me);		}
		}
	}	
	public void setMyBattleGroundCard() {
		for(int i=0;i<7;i++) {
			if(me.getBattleGroundCard().get(i) !=null) {
				CardShow x=new CardShow(me.getBattleGroundCard().get(i));
				x.setBounds(200+160*i,500, 100, 150);
				x.addMouseListener(new BattlegrounCardListener(this, me.getBattleGroundCard().get(i), x, me, enemy, visitor));
				x.addMouseMotionListener(new BattlegrounCardListener(this, me.getBattleGroundCard().get(i), x, me , enemy, visitor));
				CurrentBattleground.add(x);
				add(x);
			}
		}
	}
	private void setMyHandCard() {
		int	j=-1;
		for(Card s : me.getHand()) {
			final CardShow x=new CardShow(s);
			x.addMouseListener(new HandCardListener(this, s, x, me, enemy, visitor)); 
			x.addMouseMotionListener(new HandCardListener(this, s, x, me, enemy, visitor));
			CurrentHand.add(x);
			x.setBounds(1000+(j*100), 850, 100, 150);
			add(x);
			j--;
		}
	}
	private void setenemyHandCard() {
		int	j1=-1;
		for(Card s : enemy.getHand()) {
			final CardShow x=new CardShow(s);
			x.addMouseListener(new HandCardListener(this, s, x, enemy, me, visitor));
			x.addMouseMotionListener(new HandCardListener(this, s, x, enemy, me, visitor));
			CurrentHand.add(x);
			x.setBounds(1000+(j1*100), 5, 100, 150);
			add(x);
			j1--;
		}
	}
	public void setenemyBattleGroundCard() {
		for(int i=0;i<7;i++) {
			if(enemy.getBattleGroundCard().get(i) !=null) {
				CardShow x=new CardShow(enemy.getBattleGroundCard().get(i));
				x.setBounds(200+160*i,300, 100, 150);
				x.addMouseListener(new BattlegrounCardListener(this, enemy.getBattleGroundCard().get(i), x, enemy, me,visitor));
				x.addMouseMotionListener(new BattlegrounCardListener(this, enemy.getBattleGroundCard().get(i), x, enemy, me,visitor));
				CurrentBattleground.add(x);
				add(x);
			}
		}
	}
	private void setCard() {
		updateProgres(me);
		updateProgres(enemy);
		removeHeroPowers();
		drawHeroPower();
		removeLables();
		removeHeros();
		setWeaons();
		setMyHandCard();
		setMyBattleGroundCard();
		setenemyBattleGroundCard();
		drawHero();
		setenemyHandCard();
		player1HandRemind.setText(me.getHand().size()+"");
		player2HandRemind.setText(enemy.getHand().size()+"");
	}
	
	private void drawHeroPower() {
		HeroPowerShow x= new HeroPowerShow(me.getHero());
		x.setBounds(828, 700, 150, 150);
		x.addMouseListener(new HeroPowerListener(this, me.getHero().getHero_power(), x, me, enemy, heroVisitor));
		x.addMouseMotionListener(new HeroPowerListener(this, me.getHero().getHero_power(), x, me, enemy, heroVisitor));
		add(x);
		heroPowers.add(x);
		HeroPowerShow x1= new HeroPowerShow(enemy.getHero());
		x1.addMouseListener(new HeroPowerListener(this, enemy.getHero().getHero_power(), x1, enemy, me, heroVisitor));
		x1.addMouseMotionListener(new HeroPowerListener(this, enemy.getHero().getHero_power(), x1, enemy, me, heroVisitor));
		x1.setBounds(828, 140, 150, 150);
		add(x1);
		heroPowers.add(x1);
	}
	private void drawHero() {
		HeroShow x1= new HeroShow(me.getHero());
		x1.setBounds(658, 660, 190, 200);
		add(x1);
		heros.add(x1);
		HeroShow x2= new HeroShow(enemy.getHero());
		x2.setBounds(658, 100, 190, 200);
		add(x2);
		heros.add(x2);
	}
	private void removeHeroPowers() {
		for(int i=0;i<2;i++)
			remove(heroPowers.get(i));
		heroPowers.removeAll(heroPowers);
	}
	private void removeHeros() {
		for(int i=0;i<2;i++)
			remove(heros.get(i));
		heros.removeAll(heros);
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
			g.drawString(me.getPreviosgem() +"/"+me.getCurrentgem(), 1030, 933);
			for(int i=0;i<me.getCurrentgem();i++) {
				g.drawImage(new ImageIcon("src\\button image\\gem"+(i+1)+".png").getImage(), 1090+33*i, 910, null);
			}
		}else {
			g.setColor(Color.RED);
			g.setFont(new Font("Tahoma", Font.BOLD, 20));
			g.drawString(enemy.getPreviosgem()+"/"+enemy.getCurrentgem(), 995, 70);
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
	protected void nextTurn() throws Exception {
		if(roundGame==60) {
			initialPassive(enemy);
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
		
		updatePanel();
	}
	private void player1Turn() {
		turnPlayed.setText(((roundGame/2))+"");
		map.nextTurn(me, enemy, visitor);
		addToHand(me.getTurn()+1);
		setCard();
	}	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(new ImageIcon("src\\play image\\"+game.getBackBattleground()).getImage(), 0, 0, null);
		drawGem(g);
		drawPlaces(g);
	}
	
	private void drawPlaces(Graphics g) {
		for(int i=0 ;i<8;i++)
			g.drawLine(200+i*160, 260, 200+i*160, 700);
	g.drawLine(200, 260, 1320, 260);
	g.drawLine(200, 480, 1320, 480);
	g.drawLine(200, 700, 1320, 700);
	}
	
	
	private void addPlayerWeapon(Player p , Player enemy) {
		CardShow x=new CardShow(p.getWeapon());
		x.setBounds(560, 690-(520*p.getTurn()), 100, 150);
		x.addMouseListener(new  BattlegrounCardListener(this, p.getWeapon(), x, p, enemy, visitor));
		x.addMouseMotionListener(new BattlegrounCardListener(this, p.getWeapon(), x, p, enemy, visitor));
		add(x);
		weapons.add(x);
	}
	public void removeBattlegroundCard() {
		for(int i=CurrentBattleground.size()-1;i>=0;i--) {
			remove(CurrentBattleground.get(i));
			CurrentBattleground.remove(CurrentBattleground.get(i));
		}		
	}
	private void removeLables() {
		removeBattlegroundCard();
		for(int i=CurrentHand.size()-1;i>=0;i--) {
			remove(CurrentHand.get(i));
			CurrentHand.remove(CurrentHand.get(i));
		}
		for (CardShow cardShow : weapons) {
			remove(cardShow);
		}	
	}
	private void player2Turn() {
		map.nextTurn(enemy, me, visitor);
		turnPlayed.setText(roundGame/2+"");			
		addToHand(enemy.getTurn()+1);
		setCard();
	}
	private void addToHand(int turn) {	
		map.readDeck(me, enemy);
		if(turn==1) {
			map.addToHand(me, enemy ,visitor);
			player1DeckRemind.setText(me.getDecksize()+"");
		}else {
			map.addToHand(enemy, me, visitor);
			player2DeckRemind.setText(enemy.getDecksize()+"");	
		}
	}
	public boolean addTobattleground(Card s,int x,int y) throws Exception {
		if(roundGame%2==0) {
			if(map.addTobattleground(s, x, y, me, enemy, visitor, textArea)) {
				return true;
			}else {
				next.setVisible(true);			
				return false;
			}
		}else {
			if (map.addTobattleground(s, x, y, enemy, me, visitor, textArea)) { 
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