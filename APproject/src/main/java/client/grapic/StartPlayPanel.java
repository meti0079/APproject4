package client.grapic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import com.google.gson.Gson;
import client.Client;
import client.Controller;
import gameModel.requestAndREsponse.StartMatchRequest;

public class StartPlayPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private JButton go;
	String state;
	private void initial() throws Exception {
		setLayout(null);
		setPreferredSize(new Dimension(1800, 900));
	}
	public StartPlayPanel() throws Exception {
		initial();
		setGoButton();
		setEnemyBut();
		setDeckReaderBut();
		setComputerBut();
	}
	@Override
	protected void paintComponent(Graphics g) {
		draWBackGround(g);
		g.setFont(new Font("Tahoma", Font.BOLD, 50));
		g.setColor(Color.WHITE);
		g.drawString("Choose a play type !!	", 600, 100);
	}
	private void draWBackGround(Graphics g) {
		ImageIcon image = new ImageIcon("src\\passiva image\\passiva.png"); 
		g.drawImage(image.getImage(),0,0,null);
	}
	private void setGoButton() {
		go=new JButton("go");
		go.setBounds(1350, 700, 200, 150);
		go.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{
					String message="STARTMATCH>>"+new Gson().toJson(new StartMatchRequest(Controller.getInsist().getUser().getTocken(),state))+"#";
					Client.WriteMessage(message);
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
		});
		go.setEnabled(false);
		add(go);	
	}

	private void setEnemyBut() {
		JButton b= new JButton("Play with enemy");
		b.setBounds(280, 200, 300, 100);
		b.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				state="training";
				go.setEnabled(true);	
			}
		});
		add(b);
	}
	private void setDeckReaderBut() {
		JButton b= new JButton("Deck reader");
		b.setBounds(490, 400, 300,100);
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				state="deckreader";
				go.setEnabled(true);
			}
		});
		add(b);
	}
	private void setComputerBut() {
		JButton b= new JButton("Play with computer");
		b.setBounds(700, 600, 300,100);
		b.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				state="computer";
				go.setEnabled(true);			
			}
		});
		add(b);
	}
}