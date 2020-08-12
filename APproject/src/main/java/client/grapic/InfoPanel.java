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

import client.ClientMain;
import client.Controller;
import gameModel.requestAndREsponse.SaveAndExitRequest;
import server.gameModel.FileReader;
import server.gameModel.Logger;

public class InfoPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	Controller controller;
	public InfoPanel(Controller controller) throws Exception {
		initial();
		this.controller=controller;
		setBackButton();
		setExitButton();
	}
	private void setExitButton() {
		JButton b1= new JButton();
		b1.setBounds(1700, 15, 70, 70);
		b1.setIcon(new ImageIcon("src\\main\\java\\button image\\exit.jpg") );
		b1.setContentAreaFilled(false);
		b1.setBorder(BorderFactory.createEmptyBorder());
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				try {
					int j=JOptionPane.showConfirmDialog(null, "Do you want realy Exit", "Confirm", JOptionPane.OK_CANCEL_OPTION);
					if(j==JOptionPane.OK_OPTION) {
					String 	message= "EXIT>>"+new Gson().toJson(new SaveAndExitRequest(controller.getUser().getTocken()))+"#";
					ClientMain.WriteMessage(message);
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
					String 	message= "GOMENU>>"+new Gson().toJson(new SaveAndExitRequest(controller.getUser().getTocken()))+"#";
					ClientMain.WriteMessage(message);
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
		g.drawString("Name : "+  controller.getUser().getName(), 50, 60);
		g.drawString("Gem       : "+controller.getUser().getGem(), 400, 60);
		g.drawString("Cup       : "+controller.getUser().getCup(), 1000, 60);
		g.drawImage(new ImageIcon("src\\button image\\gems.png").getImage(), 700, 20, null	);
	}
	private void initial() throws Exception {
		setPreferredSize(new Dimension(1800, 100));
		setLayout(null);
	}
}