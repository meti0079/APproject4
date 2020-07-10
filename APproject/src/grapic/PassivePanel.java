package grapic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import com.google.gson.Gson;
import GAME.Gamestate;
import GAME.Logger;
import GAME.Passive;
import myListeners.PassiveListener;

public class PassivePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private Gamestate game;
	private JButton go;
	private Logger log;
	private Random random=new Random();
	private void initial() throws Exception {
		setLayout(null);
		setPreferredSize(new Dimension(1800, 900));
		game=Gamestate.getinsist();
		log=Logger.getinsist();	
	}
	public PassivePanel(MainFrame f) throws Exception {
		initial();
		setPassives();
		setGoButton(f);
		setEnemyBut();
		setDeckReaderBut();
		setComputerBut();
	}
	@Override
	protected void paintComponent(Graphics g) {
		draWBackGround(g);
		g.setFont(new Font("Tahoma", Font.BOLD, 50));
		g.setColor(Color.WHITE);
		g.drawString("Choose a treasure !!	", 600, 100);
	}
	private void draWBackGround(Graphics g) {
		ImageIcon image = new ImageIcon("src\\passiva image\\passiva.png"); 
		g.drawImage(image.getImage(),0,0,null);
	}
	public void setPassives() throws FileNotFoundException{
		readPassiveFile();
		setPassiveLables();
	}
	private void setGoButton(MainFrame f) {
		go=new JButton("go");
		go.setBounds(1450, 800, 50, 50);
		go.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(game.getPlayPassive()==null) {
					JOptionPane.showConfirmDialog(null, "you have to choose a passive", "dont select ", JOptionPane.ERROR_MESSAGE);
				}else
					try {
						log.log(game.getPlayer().get_name(), "go to play game", "");
						PlayShow p= new PlayShow((MainFrame)f);
						f.ChangePanel(p);
					} catch (Exception e) {
						e.printStackTrace();
					}	
			}
		});
		go.setEnabled(false);
		add(go);	
	}
	private void readPassiveFile() throws FileNotFoundException {
		Gson j= new Gson();
		File f3=new File(System.getProperty("user.dir")+"\\src\\passive");
		File[] dirr3=f3.listFiles();
		if(dirr3!=null) {
			for(File ch:dirr3) {
				Scanner sca=new Scanner(ch);
				String t1="";
				while(sca.hasNext()) {
					t1+=sca.nextLine();
				}
				Passive s= j.fromJson(t1, Passive.class);
				game.addPassives(s);
				sca.close();
			}
		}
	}
	private void setPassiveLables() {
		ArrayList<Integer > a=new ArrayList<>();
		while (a.size()!=3) {
			int x=(random.nextInt(7));
			if(!a.contains(x)&&x>=0&&x<=7)
				a.add(x);
		}
		for(int i=0 ;i<3 ; i++) {
			int index=i;
			final JLabel lp1 =new JLabel(new ImageIcon( System.getProperty("user.dir")+"\\src\\passiva image\\"+game.getPassives().get(a.get(i)).getName()+".png"));
			lp1.setBounds(500+i*300, 200, 300, 400);
			lp1.addMouseListener(new PassiveListener(a.get(index)));
			add(lp1);
		}
	}
	private void setEnemyBut() {
		JButton b= new JButton("Play with enemy");
		b.setBounds(280, 700, 200, 60);
		b.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				game.setState("enemy");
				go.setEnabled(true);
			}
		});
		add(b);
	}
	private void setDeckReaderBut() {
		JButton b= new JButton("Deck reader");
		b.setBounds(490, 700, 200, 60);
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.setState("Deck");
				go.setEnabled(true);	
			}
		});
		add(b);
	}
	private void setComputerBut() {
		JButton b= new JButton("Play with computer");
		b.setBounds(700, 700, 200, 60);
		b.setEnabled(false);
		b.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				game.setState("computer");
				go.setVisible(true);
			}
		});
		add(b);
	}

}