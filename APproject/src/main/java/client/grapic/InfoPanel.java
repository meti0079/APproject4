package client.grapic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.google.gson.Gson;

import client.Client;
import client.Controller;
import game.Gamestate;
import game.Logger;
import gameModel.requestAndREsponse.SaveAndExitRequest;

public class InfoPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	public static InfoPanel inf;
	private InfoPanel() throws Exception {
		initial();
		setBackButton();
		setExitButton();
	}
	public static InfoPanel getinsist() throws Exception {
		if(inf==null)
			inf=new InfoPanel();
		return inf;
	}	
	private void setExitButton() {
		JButton b1= new JButton();
		b1.setBounds(1700, 15, 70, 70);
		b1.setIcon(new ImageIcon("src\\button image\\exit.jpg") );
		b1.setContentAreaFilled(false);
		b1.setBorder(BorderFactory.createEmptyBorder());
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				try {
					int j=JOptionPane.showConfirmDialog(null, "Do you want realy Exit", "Confirm", JOptionPane.OK_CANCEL_OPTION);
					if(j==JOptionPane.OK_OPTION) {
					String 	message= "EXIT>>"+new Gson().toJson(new SaveAndExitRequest(Controller.getInsist().getUser().getTocken()))+"#";
					Client.WriteMessage(message);
					System.exit(0);
					}
				} catch (IOException e1) {
					System.out.println("exit button");
					e1.printStackTrace();
				}	
			}
		});
		add(b1);
	}
	private void setBackButton() {
		JButton b= new JButton();
		b.setBounds(1600, 0, 100, 100);
		b.setIcon(new ImageIcon("menubut.png") );
		b.setContentAreaFilled(false);
		b.setBorder(BorderFactory.createEmptyBorder());
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String 	message= "GOMENU>>"+new Gson().toJson(new SaveAndExitRequest(Controller.getInsist().getUser().getTocken()))+"#";
					Client.WriteMessage(message);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		add(b);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawBackGround(g);
		drawInfoInPanel(g);
	}
	private void drawBackGround(Graphics g) {
		g.drawImage(new ImageIcon("4.png").getImage(), 0, 0, null);
	}
	private void drawInfoInPanel(Graphics g) {
		g.setFont(new Font("Tahoma", Font.BOLD, 40));
		g.setColor(Color.WHITE);
		g.drawString("Name : "+Controller.getInsist().getUser().getName(), 50, 60);
		g.drawString("Gem       : "+Controller.getInsist().getUser().getGem(), 400, 60);
		g.drawString("Cup       : "+Controller.getInsist().getUser().getCup(), 600, 60);
		g.drawImage(new ImageIcon("src\\button image\\gems.png").getImage(), 700, 20, null	);
	}
	private void initial() throws Exception {
		setPreferredSize(new Dimension(1800, 100));
		setLayout(null);
	}
}