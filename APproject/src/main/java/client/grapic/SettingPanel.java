package client.grapic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import client.Client;
import client.Controller;
import client.listeners.SettingBackGrounListener;
import gameModel.requestAndREsponse.SaveAndExitRequest;

public class SettingPanel extends JPanel{


	private static final long serialVersionUID = 1L;
	private JButton deletAcount;
	private JLabel background1;
	private JLabel background2;
	private JLabel background3;
	private JLabel background4;
	private InfoPanel inf;
	private Controller controller;
	public SettingPanel(Controller controller) throws Exception {
		initial();
		this.controller=controller;
		inf=new InfoPanel(controller);
		inf.setBounds(0, 0, 1800, 100);
		add(inf);
		setDeleteAccountButton();
		setBackGroundButtons();
	}
	private void setDeleteAccountButton() {
		deletAcount=new JButton("Delete Acount");
		deletAcount.setFont(new Font("Tahoma", Font.BOLD, 30));
		deletAcount.setForeground(Color.BLACK);
		deletAcount.setBackground(Color.orange);
		deletAcount.setBounds(50, 200, 300, 60);
		deletAcount.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int x=JOptionPane.showConfirmDialog(null, "do you really want to delete account","confirm", JOptionPane.OK_CANCEL_OPTION);
				if(x==JOptionPane.OK_OPTION)
					try {
						String 	message= "DELETACCOUNT>>"+new SaveAndExitRequest(controller.getUser().getTocken())+"#";
						Client.WriteMessage(message);
						System.exit(0);
					} catch (IOException e) {e.printStackTrace();}
			}
		});
		add(deletAcount);
	}
	private void setBackGroundButtons() {
		background1=new JLabel(new ImageIcon("src\\main\\java\\play image\\nattle11.jpg"));
		background1.setBounds(840,260, 226, 158);
		add(background1);
		background2=new JLabel(new ImageIcon("src\\main\\java\\play image\\nattle21.jpg"));
		background2.setBounds(1100, 260, 226,158);
		add(background2);
		background3=new JLabel(new ImageIcon("src\\main\\java\\play image\\nattle31.jpg"));
		background3.setBounds(840, 460, 226,158);
		add(background3);
		background4=new JLabel(new ImageIcon("src\\main\\java\\play image\\nattle41.jpg"));
		background4.setBounds(1100,460, 226,158);
		add(background4);
		background1.addMouseListener(new SettingBackGrounListener(1, controller.getUser().getTocken()));
		background2.addMouseListener(new SettingBackGrounListener(2, controller.getUser().getTocken()));
		background3.addMouseListener(new SettingBackGrounListener(3, controller.getUser().getTocken()));
		background4.addMouseListener(new SettingBackGrounListener(4, controller.getUser().getTocken()));
	}
	private void initial() {
		setPreferredSize(new Dimension(1800, 1000));
		setLayout(null);
	}
//	public void setBackCard(int a) {
//		String s="ca"+a+".png";
//		game.setBackBattleground(s);
//	}
	@Override
	protected void paintComponent(Graphics g) {
		setBackGround(g);
	}
	private void setBackGround(Graphics g) {
		g.drawImage(new ImageIcon("setting.jpg").getImage(), 0, 0, null);
		g.setFont(new Font("Tahoma", Font.BOLD, 40));
		g.drawString("choose a battleground", 870, 200);
	}
}
