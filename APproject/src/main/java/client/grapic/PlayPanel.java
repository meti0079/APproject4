package client.grapic;

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
import com.google.gson.Gson;
import client.Client;
import client.Controller;
import client.listeners.BattlegrounCardListener;
import client.listeners.HandCardListener;
import client.listeners.HeroPowerListener;
import client.model.Card;
import gameModel.requestAndREsponse.NextTurnRequest;


public class PlayPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	public static int i=0;
	private JButton manabut;	
	private JLabel next;
	private TextArea textArea;
	private JLabel turnPlayed;
	private JLabel player2DeckRemind;
	private JLabel player1DeckRemind;
	private JLabel player1HandRemind;
	private JLabel player2HandRemind;
	private ArrayList<CardShow> CurrentBattleground=new ArrayList<>();
	private ArrayList<CardShow> CurrentHand=new ArrayList<>();
	private ArrayList<CardShow> weapons=new ArrayList<>();
	private ArrayList<HeroShow> heros=new ArrayList<>();
	ArrayList<JLabel> enemyHand=new ArrayList<>();
	private JProgressBar[] progres=new JProgressBar[2];
	private ArrayList<HeroPowerShow> heroPowers=new ArrayList<>();
	private Gson gson;
	private int tocken;
	Controller controller;
	public PlayPanel( TextArea t, Controller controller) throws Exception {
		tocken=controller.getUser().getTocken();
		this.controller=controller;
		initial();
		gson=new Gson();
		textArea=t;
		initialNextTurnBtutton();
		initialLables();
	}
	//	private void startGame() throws Exception {
	//		JProgressBar jp=new JProgressBar(0, 60);
	//		jp.setBounds(10, 440, 150, 40);
	//		jp.setValue(0);
	//		jp.setString(0+"");
	//		jp.setStringPainted(true);
	//		ComputerPlayer computerPlayer=new ComputerPlayer(me, enemy, visitor, textArea);
	//		Clock timer =new Clock(jp, this,computerPlayer);
	//		add(jp);
	//		timer.start();		
	//	}

	private void  initialPassive() throws Exception {
		//		if(controller.get.getState().equalsIgnoreCase("computer")) {
		//			if(getRoundGame()%2==1) {
		//				pp.setPassive(map.getAllPassives().get(0));
		//				return ;
		//			}
		//		}
		if(controller.getGameNeed().getTurnremind()==60) {
			PassivePanel p=new PassivePanel(controller);
			p.setBounds(200, 300, 1000, 500);
			this.add(p);			
		}

	}
	private void setCard() {
		setQuest();
//		removeHeroPowers();
		drawHeroPower();
		removeLables();
//		removeHeros();
		addPlayerWeapon();
		setMyHandCard();
		setMyBattleGroundCard();
		setenemyBattleGroundCard();
		drawHero();
		setenemyHandCard();
		player1HandRemind.setText(controller.getGameNeed().getMyHand().size()+"");
		player2HandRemind.setText(controller.getGameNeed().getEnemyHandsize()+"");
	}

	private void drawHeroPower() {
		for (int i = 0; i < heroPowers.size(); i++) {
			remove(heroPowers.get(i));
		}
		heroPowers.removeAll(heroPowers);
		HeroPowerShow x= new HeroPowerShow(controller.getGameNeed().getMyHero());
		x.setBounds(828, 700, 150, 150);
		x.addMouseListener(new HeroPowerListener (controller.getGameNeed().getMyHero().getHero_power(),controller.getGameNeed().getTurnremind() ,controller.getGameNeed().getMyturn(),x, controller.getUser().getTocken()));
		x.addMouseMotionListener(new HeroPowerListener(controller.getGameNeed().getMyHero().getHero_power(),controller.getGameNeed().getTurnremind() ,controller.getGameNeed().getMyturn(),x, controller.getUser().getTocken()));
		add(x);
		heroPowers.add(x);
		HeroPowerShow x1= new HeroPowerShow(controller.getGameNeed().getEnemyHero());
		x1.addMouseListener(new HeroPowerListener(controller.getGameNeed().getEnemyHero().getHero_power(),controller.getGameNeed().getTurnremind(),controller.getGameNeed().getMyturn(),x, controller.getUser().getTocken()));
		x1.addMouseMotionListener(new HeroPowerListener(controller.getGameNeed().getEnemyHero().getHero_power(),controller.getGameNeed().getTurnremind(),controller.getGameNeed().getMyturn(),x, controller.getUser().getTocken()));
		x1.setBounds(828, 140, 150, 150);
		add(x1);
		heroPowers.add(x1);
	}
	private void drawHero() {
		for (int i = 0; i < heros.size(); i++) {
			remove(heros.get(i));
		}
		heros.removeAll(heros);
		HeroShow x1= new HeroShow(controller.getGameNeed().getMyHero());
		x1.setBounds(658, 660, 190, 200);
		add(x1);
		heros.add(x1);
		HeroShow x2= new HeroShow(controller.getGameNeed().getEnemyHero());
		x2.setBounds(658, 100, 190, 200);
		add(x2);
		heros.add(x2);
	}
//	private void removeHeroPowers() {
//		for(int i=0;i<2;i++)
//			remove(heroPowers.get(i));
//		heroPowers.removeAll(heroPowers);
//	}
//	private void removeHeros() {
//		for(int i=0;i<2;i++)
//			remove(heros.get(i));
//		heros.removeAll(heros);
//	}

	public void updatePanel() {
		try {
			initialPassive();
		} catch (Exception e) {
			e.printStackTrace();
		}
		setCard();
		repaint();
		revalidate();
	}
	public TextArea getTextArea() {
		return textArea;
	}
	public void drawGem(Graphics g) {
		if(controller.getGameNeed().getTurnremind()%2==controller.getGameNeed().getMyturn()) {
			g.setColor(Color.RED);
			g.setFont(new Font("Tahoma", Font.BOLD, 20));
			g.drawString(controller.getGameNeed().getMyMana()+"", 1030, 933);
			for(int i=0;i<controller.getGameNeed().getMyMana();i++) {
				g.drawImage(new ImageIcon("src\\main\\java\\button image\\gem"+(i+1)+".png").getImage(), 1090+33*i, 910, null);
			}
		}else {
			g.setColor(Color.RED);
			g.setFont(new Font("Tahoma", Font.BOLD, 20));
			g.drawString(controller.getGameNeed().getEnemyMana()+"", 995, 70);
			for(int i=0;i<controller.getGameNeed().getEnemyMana();i++) {
				g.drawImage(new ImageIcon("src\\main\\java\\button image\\gem"+(i+1)+".png").getImage(), 1070+33*i, 40, null);
			}			
		}
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(new ImageIcon("src\\main\\java\\play image\\"+controller.getGameNeed().getBattleground()).getImage(), 0, 0, null);
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
	private void initialLables() {
		player1DeckRemind = new JLabel("");
		player2DeckRemind=new JLabel("");
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
	private void initialNextTurnBtutton() {
		manabut= new JButton();
		manabut.setBounds(1280, 400, 120, 80);
		manabut.setBorder(BorderFactory.createEmptyBorder());
		manabut.setContentAreaFilled(false);
		manabut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String message="NEXTTURN>>"+gson.toJson(new NextTurnRequest(tocken));
					Client.WriteMessage(message);
				} catch (Exception e) {e.printStackTrace();	}
			}
		});
		add(manabut);
	}
	private void setQuest() {
		if(controller.getGameNeed().getMyquest()!=null) {
			if(progres[0]==null) {
				JProgressBar jp=new JProgressBar(0, controller.getGameNeed().getMyquest().getMission());
				jp.setBounds(10, 240+1*400, 150, 40);
				jp.setValue(0);
				add(jp);
				progres[0]=jp;			
			}else {
				progres[0].setValue(controller.getGameNeed().getMyquest().getHave());
			}		
		}else {
			progres[0]=null;						
		}
		if(controller.getGameNeed().getEnemyQuest()!=null) {
			if(progres[1]==null) {
				JProgressBar jp=new JProgressBar(0, controller.getGameNeed().getEnemyQuest().getMission());
				jp.setBounds(10, 240, 150, 40);
				jp.setValue(0);
				add(jp);
				progres[1]=jp;			
			}else {
				progres[1].setValue(controller.getGameNeed().getEnemyQuest().getHave());
			}		
		}else {
			progres[1]=null;
		}
	}
	private void initial() throws Exception {
		setPreferredSize(new Dimension(1800, 1000));
		setLayout(null);
	}
	private void addPlayerWeapon() {
		if(controller.getGameNeed().getMyWeapon()!=null) {
			CardShow x=new CardShow(controller.getGameNeed().getMyWeapon());
			x.setBounds(560, 690, 100, 150);
			x.addMouseListener(new  BattlegrounCardListener(controller.getGameNeed().getMyWeapon(), x,controller.getGameNeed().getTurnremind(),controller.getGameNeed().getMyturn(), controller.getUser().getTocken()));
			x.addMouseMotionListener(new  BattlegrounCardListener(controller.getGameNeed().getMyWeapon(), x,controller.getGameNeed().getTurnremind(),controller.getGameNeed().getMyturn(), controller.getUser().getTocken()));
			add(x);
			weapons.add(x);				
		}
		if(controller.getGameNeed().getEnemyWeapon()!=null) {
			CardShow x=new CardShow(controller.getGameNeed().getEnemyWeapon());
			x.setBounds(560, 690-520, 100, 150);
			if(!(controller.getState().equals("online") ||controller.getState().equals("deckreader"))) {
				x.addMouseListener(new  BattlegrounCardListener(controller.getGameNeed().getEnemyWeapon(), x,controller.getGameNeed().getTurnremind(),controller.getGameNeed().getMyturn()+1, controller.getUser().getTocken()));
				x.addMouseMotionListener(new  BattlegrounCardListener(controller.getGameNeed().getEnemyWeapon(), x,controller.getGameNeed().getTurnremind(),controller.getGameNeed().getMyturn()+1, controller.getUser().getTocken()));				
			}
			add(x);
			weapons.add(x);	
		}
	}
	public void setMyBattleGroundCard() {
		for(int i=0;i<7;i++) {
			if(controller.getGameNeed().getMyBattlrground().get(i) !=null) {
				CardShow x=new CardShow(controller.getGameNeed().getMyBattlrground().get(i));
				x.setBounds(200+160*i,500, 100, 150);
				x.addMouseListener(new  BattlegrounCardListener(controller.getGameNeed().getMyWeapon(), x,controller.getGameNeed().getTurnremind(),controller.getGameNeed().getMyturn(), controller.getUser().getTocken()));
				x.addMouseMotionListener(new  BattlegrounCardListener(controller.getGameNeed().getMyWeapon(), x,controller.getGameNeed().getTurnremind(),controller.getGameNeed().getMyturn(), controller.getUser().getTocken()));
				CurrentBattleground.add(x);
				add(x);
			}
		}
	}
	public void setenemyBattleGroundCard() {
		for(int i=0;i<7;i++) {
			if(controller.getGameNeed().getEnemyBattleground().get(i) !=null) {
				CardShow x=new CardShow(controller.getGameNeed().getEnemyBattleground().get(i));
				x.setBounds(200+160*i,300, 100, 150);
				if(!(controller.getState().equals("online") ||controller.getState().equals("deckreader"))) {
					x.addMouseListener(new  BattlegrounCardListener(controller.getGameNeed().getEnemyBattleground().get(i), x,controller.getGameNeed().getTurnremind(),controller.getGameNeed().getMyturn()+1, controller.getUser().getTocken()));
					x.addMouseMotionListener(new  BattlegrounCardListener(controller.getGameNeed().getEnemyBattleground().get(i), x,controller.getGameNeed().getTurnremind(),controller.getGameNeed().getMyturn()+1, controller.getUser().getTocken()));				
				}
				CurrentBattleground.add(x);
				add(x);
			}
		}
	}
	private void setMyHandCard() {
		int	j=-1;
		for(Card s :controller.getGameNeed().getMyHand()) {
			final CardShow x=new CardShow(s);
			x.addMouseListener(new HandCardListener(this, s, x, controller.getGameNeed().getTurnremind(),controller.getGameNeed().getMyturn(), controller.getUser().getTocken())); 
			x.addMouseMotionListener(new HandCardListener(this, s, x, controller.getGameNeed().getTurnremind(),controller.getGameNeed().getMyturn(), controller.getUser().getTocken()));
			CurrentHand.add(x);
			x.setBounds(1000+(j*100), 850, 100, 150);
			add(x);
			j--;
		}
	}
	private void setenemyHandCard() {
		if(controller.getState().equals("training")) {
		int	j1=-1;
		for(Card s :controller.getGameNeed().getEnemyHand()) {
			final CardShow x=new CardShow(s);
				x.addMouseListener(new HandCardListener(this, s, x, controller.getGameNeed().getTurnremind(),controller.getGameNeed().getMyturn()+1, controller.getUser().getTocken()));
				x.addMouseMotionListener(new HandCardListener(this, s, x, controller.getGameNeed().getTurnremind(),controller.getGameNeed().getMyturn()+1, controller.getUser().getTocken()));				
			CurrentHand.add(x);
			x.setBounds(1000+(j1*100), 5, 100, 150);
			add(x);
			j1--;
		}
		}else {
			int	j1=-1;
			for (int i = 0; i < controller.getGameNeed().getEnemyHandsize(); i++) {
				JLabel x=new JLabel("src\\main\\java\\play image\\"+controller.getGameNeed().getBackCard());
				x.setBounds(1000+(j1*100), 5, 100, 150);
				add(x);
				enemyHand.add(x);
				j1--;
			}
		}
	}
}