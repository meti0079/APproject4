package client.grapic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import com.google.gson.Gson;
import client.ClientMain;
import client.Controller;
import client.listeners.BattlegrounCardListener;
import client.listeners.HandCardListener;
import client.listeners.HeroPowerListener;
import client.model.Card;
import gameModel.requestAndREsponse.Kickrequest;
import gameModel.requestAndREsponse.NextTurnRequest;
import gameModel.requestAndREsponse.SaveAndExitRequest;


public class PlayPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	JProgressBar jp;
	private JButton manabut;	
	private JLabel next;
	private JButton exitGame;
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
	private JLabel enemyName;
	private ArrayList<String> watcher=new ArrayList<>();  
	private ArrayList<JButton> watcherButtons=new ArrayList<>();  
	public PlayPanel( TextArea t, Controller controller) throws Exception {
		tocken=controller.getUser().getTocken();
		this.controller=controller;
		initial();
		gson=new Gson();
		textArea=t;
		initialLables();
		initialBtutton();
		startGame();
	}
	private void startGame(){
		jp=new JProgressBar(0, 60);
		jp.setBounds(10, 440, 150, 40);
		jp.setValue(0);
		jp.setString(0+"");
		jp.setStringPainted(true);
		add(jp);
	}

	private void  initialPassive() throws Exception {
		if(controller.getGameNeed().getTurnremind()==60) {
			PassivePanel p=new PassivePanel(controller);
			p.setBounds(200, 300, 1000, 500);
			this.add(p);			
		}

	}
	private void setCard() {
		setWatcher();
		setQuest();
		drawHeroPower();
		removeLables();
		addPlayerWeapon();
		setMyHandCard();
		setMyBattleGroundCard();
		setenemyBattleGroundCard();
		drawHero();
		setenemyHandCard();
		player1HandRemind.setText(controller.getGameNeed().getMyHand().size()+"");
		player2HandRemind.setText(controller.getGameNeed().getEnemyHandsize()+"");
		player1DeckRemind.setText(controller.getGameNeed().getMyDeck()+"");
		player2DeckRemind.setText(controller.getGameNeed().getEnemDeck()+"");
		turnPlayed.setText(""+(controller.getGameNeed().getTurnremind()/2));
		enemyName.setText(controller.getGameNeed().getEnemyName());
	}

	private void drawHeroPower() {
		for (int i = 0; i < heroPowers.size(); i++) {
			remove(heroPowers.get(i));
		}
		heroPowers.removeAll(heroPowers);
		HeroPowerShow x= new HeroPowerShow(controller.getGameNeed().getMyHero());
		x.setBounds(828, 700, 150, 150);
		if(!controller.getGameNeed().getEnemyName().equals("&&&&")) {					
			x.addMouseListener(new HeroPowerListener (controller.getGameNeed().getMyHero().getHero_power(),controller.getGameNeed().getTurnremind() ,controller.getGameNeed().getMyturn(),x, controller.getUser().getTocken(),this));
			x.addMouseMotionListener(new HeroPowerListener(controller.getGameNeed().getMyHero().getHero_power(),controller.getGameNeed().getTurnremind() ,controller.getGameNeed().getMyturn(),x, controller.getUser().getTocken(),this));
		}
		add(x);
		heroPowers.add(x);
		HeroPowerShow x1= new HeroPowerShow(controller.getGameNeed().getEnemyHero());
		x1.setBounds(828, 140, 150, 150);
		add(x1);
		heroPowers.add(x1);
	}
	public void setWatcher() {
		for (JButton jButton : watcherButtons) {
			remove(jButton);
		}
		watcherButtons.removeAll(watcherButtons);
		int x=1;
		for (String string : watcher) {
			System.out.println(string);
			JButton button=new JButton("KICK :"+string);
			button.addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
					String message="KICK>>"+gson.toJson(new Kickrequest(string, tocken));
					try {
						ClientMain.WriteMessage(message);
					} catch (IOException e1) {e1.printStackTrace();}
				}
			});
			watcherButtons.add(button);
			button.setBounds(10, x*200, 150, 50);
			add(button);
			x++;
		}	
	}
	public void setWatcher(ArrayList<String> watcher) {
		this.watcher = watcher;
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
	public void updatePanel() {
		if(controller.getGameNeed().getTurnremind()%2!=controller.getGameNeed().getMyturn()) {
			manabut.setEnabled(false);				
		}else {
			manabut.setEnabled(true);
		}
		if(controller.getGameNeed().getEnemyName().equals("&&&&")) {
			manabut.setEnabled(false);							
		}
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
		if(controller.getGameNeed()==null) {
			g.drawImage(new ImageIcon("src\\main\\java\\play image\\nattle1.jpg").getImage(), 0, 0, null);
		}else {
			g.drawImage(new ImageIcon("src\\main\\java\\play image\\"+controller.getGameNeed().getBattleground()).getImage(), 0, 0, null);
			drawGem(g);			
		}
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
		enemyName=new JLabel();
		enemyName.setBounds(1050, 150, 100, 100);
		add(enemyName);
		enemyName.setFont(new Font("Tahoma", Font.BOLD, 20));
		enemyName.setForeground(Color.RED);
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
	private void initialBtutton() {
		manabut= new JButton();
		manabut.setBounds(1280, 400, 120, 80);
		manabut.setContentAreaFilled(false);
		manabut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String message="NEXTTURN>>"+gson.toJson(new NextTurnRequest(tocken));
					ClientMain.WriteMessage(message);
				} catch (Exception e) {e.printStackTrace();	}
			}
		});
		add(manabut);
		exitGame =new JButton("EXIT");
		exitGame.setBackground(Color.BLACK);
		exitGame.setBounds(1300, 0, 100, 80);
		add(exitGame);
		exitGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
				if(!controller.getGameNeed().getEnemyName().equals("&&&&")) {
					String message="EXITGAME>>"+gson.toJson(new SaveAndExitRequest(tocken));
					ClientMain.WriteMessage(message);					
				}else {
					String message="EXITMATCH>>"+gson.toJson(new SaveAndExitRequest(tocken));
					ClientMain.WriteMessage(message);					
				}
				} catch (IOException e) {e.printStackTrace();}
			}
		});	
	}
	private void setQuest() {
		if(controller.getGameNeed().isMyquest()) {
			if(progres[0]==null) {
				JProgressBar jp=new JProgressBar(0, controller.getGameNeed().getMyquestmission());
				jp.setBounds(10, 240+1*400, 150, 40);
				jp.setValue(0);
				add(jp);
				progres[0]=jp;			
			}else {
				progres[0].setValue(controller.getGameNeed().getMyquesthave());
			}		
		}else {
			progres[0]=null;						
		}
		if(controller.getGameNeed().isEnemyQuest()) {
			if(progres[1]==null) {
				JProgressBar jp=new JProgressBar(0, controller.getGameNeed().getEnemyquestmission());
				jp.setBounds(10, 240, 150, 40);
				jp.setValue(0);
				add(jp);
				progres[1]=jp;			
			}else {
				progres[1].setValue(controller.getGameNeed().getEnemyquestmission());
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
			if(!controller.getGameNeed().getEnemyName().equals("&&&&")) {				
				x.addMouseListener(new  BattlegrounCardListener(controller.getGameNeed().getMyWeapon(), x,controller.getGameNeed().getTurnremind(),controller.getGameNeed().getMyturn(), controller.getUser().getTocken(),this));
				x.addMouseMotionListener(new  BattlegrounCardListener(controller.getGameNeed().getMyWeapon(), x,controller.getGameNeed().getTurnremind(),controller.getGameNeed().getMyturn(), controller.getUser().getTocken(),this));
			}
			x.setBounds(560, 690, 100, 150);
			add(x);
			weapons.add(x);				
		}
		if(controller.getGameNeed().getEnemyWeapon()!=null) {
			CardShow x=new CardShow(controller.getGameNeed().getEnemyWeapon());
			x.setBounds(560, 690-520, 100, 150);
			add(x);
			weapons.add(x);	
		}
	}
	public void setMyBattleGroundCard() {
		for(int i=0;i<7;i++) {
			if(controller.getGameNeed().getMyBattlrground().get(i) !=null) {
				CardShow x=new CardShow(controller.getGameNeed().getMyBattlrground().get(i));
				x.setBounds(200+160*i,500, 100, 150);
				if(!controller.getGameNeed().getEnemyName().equals("&&&&")) {
					x.addMouseListener(new  BattlegrounCardListener(controller.getGameNeed().getMyBattlrground().get(i), x,controller.getGameNeed().getTurnremind(),controller.getGameNeed().getMyturn(), controller.getUser().getTocken(),this));
					x.addMouseMotionListener(new  BattlegrounCardListener(controller.getGameNeed().getMyBattlrground().get(i), x,controller.getGameNeed().getTurnremind(),controller.getGameNeed().getMyturn(), controller.getUser().getTocken(),this));					
				}
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
				CurrentBattleground.add(x);
				add(x);
			}
		}
	}
	private void setMyHandCard() {
		int	j=-1;
		for(Card s :controller.getGameNeed().getMyHand()) {
			final CardShow x=new CardShow(s);
			if(!controller.getGameNeed().getEnemyName().equals("&&&&")) {
				x.addMouseListener(new HandCardListener(this, s, x, controller.getGameNeed().getTurnremind(),controller.getGameNeed().getMyturn(), controller.getUser().getTocken())); 
				x.addMouseMotionListener(new HandCardListener(this, s, x, controller.getGameNeed().getTurnremind(),controller.getGameNeed().getMyturn(), controller.getUser().getTocken()));
			}
			CurrentHand.add(x);
			x.setBounds(1000+(j*100), 850, 100, 150);
			add(x);
			j--;
		}
	}
	private void setenemyHandCard() {
		if(controller.getGameNeed().getEnemyName().equals("&&&&")) {
			int	j=-1;
			for(Card s :controller.getGameNeed().getEnemyHand()) {
				final CardShow sh=new CardShow(s);
				CurrentHand.add(sh);
				sh.setBounds(1000+(j*100), 5, 100, 150);
				add(sh);
				j--;
			}	
		}else {	
			int	j1=-1;
			for (int i = 0; i < controller.getGameNeed().getEnemyHandsize(); i++) {
				JLabel x=new JLabel(new ImageIcon("src\\main\\java\\play image\\"+controller.getGameNeed().getBackCard()));
				x.setBounds(1000+(j1*100), 5, 100, 150);
				add(x);
				enemyHand.add(x);
				j1--;
			}
		}
	}
	public JProgressBar getJp() {
		return jp;
	}
	public void setJp(JProgressBar jp) {
		this.jp = jp;
	}
}