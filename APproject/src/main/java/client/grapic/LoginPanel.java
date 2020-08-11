package client.grapic;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetSocketAddress;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import com.google.gson.Gson;
import client.Client;
import gameModel.requestAndREsponse.LoginAndSingUpRequest;

public class LoginPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField namefield;
	private JTextField namefield2;
	private JTextField serverPort;
	private JTextField serverIp;
	private JPasswordField passfield;
	private JPasswordField passfield2;
	private JButton loginButton;
	private JButton newAccountButton;
	private JLabel error;
	private JLabel error1;

	public LoginPanel() throws Exception  {
		initial();
		setErrorLables();
		setSignUpButtons();
		setMakeNewAccount();
		layoutComponenet();
	}
	private void initial() throws Exception {
		Dimension dim=new Dimension(500, 1000);
		setPreferredSize(dim);
		this.namefield=new JTextField(15);
		this.passfield=new JPasswordField(15);
		this.namefield2=new JTextField(15);
		this.passfield2=new JPasswordField(15);
		serverIp=new JTextField("localhost");
		serverPort=new JTextField("8000");
	}
	private void setErrorLables(){
		error=new JLabel("username or password is incoreect!!!!  try again");
		error.setFont(new Font("Tahoma", Font.BOLD, 15));
		error.setForeground(Color.RED);
		error.setVisible(false);
		error1=new JLabel("username  has already used!!!!");
		error1.setFont(new Font("Tahoma", Font.BOLD, 15));
		error1.setForeground(Color.RED);
		error1.setVisible(false);
	}
	private void setSignUpButtons() {
		this.loginButton=new JButton("Sign in");
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Client.serverIP=serverIp.getText();
				Client.serverport=Integer.parseInt(serverPort.getText());
				Client.address=new InetSocketAddress(serverIp.getText(),Integer.parseInt(serverPort.getText()));
				String name =namefield.getText();
				String pass=passfield.getText();
				if(namefield.getText().equals("") || passfield.getText().equals("")) {
					error.setVisible(true);
				}else {
					try {
						LoginAndSingUpRequest loginRequest=new LoginAndSingUpRequest(name, pass);
						String message="LOGIN>>"+new Gson().toJson(loginRequest)+"#";
						Client.WriteMessage(message);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}
	private void setMakeNewAccount() {
		this.newAccountButton =new JButton("Create new");
		newAccountButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Client.serverIP=serverIp.getText();
				Client.serverport=Integer.parseInt(serverPort.getText());
				Client.address=new InetSocketAddress(serverIp.getText(),Integer.parseInt(serverPort.getText()));
				String name=namefield2.getText();
				String pass=passfield2.getText();
				LoginAndSingUpRequest loginRequest=new LoginAndSingUpRequest(name, pass);
				String message="SINGUP>>"+new Gson().toJson(loginRequest)+"#";
				try {
					Client.WriteMessage(message);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawBackGrounfd(g);
		drawCenterLine(g);
		drawCadr(g);
		writeFieldName(g);
	}
	private void drawCenterLine(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawLine(20, 550, 200, 550);
		g.drawLine(300, 550, 475, 550);
		g.setFont(new Font("Tahoma", Font.BOLD, 20));
		g.drawString("OR", 240, 555);	
	}
	private void writeFieldName(Graphics g) {
		g.setFont(new Font("Tahoma", Font.BOLD, 20));
		g.drawString("server Port :", 30	, 130);
		g.drawString("server IP :", 30, 180);
		g.drawString("WellCome", 10	, 30);
		g.drawString("Sign in", 30, 280);
		g.drawString("Username :", 50, 402);
		g.drawString("Password :", 50, 448);
		g.drawString("create account", 30, 700);
		g.drawString("Username :", 50, 759);
		g.drawString("Password :", 50, 809);
	}
	private void drawBackGrounfd(Graphics g) {
		ImageIcon image = new ImageIcon("loginBackground.png"); 
		g.drawImage(image.getImage(),0,0,null);
	}
	private void drawCadr(Graphics g) {
		g.drawLine(15,35, 15, 985);
		g.drawLine(120, 25, 485, 25);
		g.drawLine(485, 25, 485, 985);
		g.drawLine(15, 985, 485, 985);
	}

	public void layoutComponenet() {
		setLayout(null);
		serverPort.setBounds(180, 115, 200, 20);
		add(serverPort);
		serverIp.setBounds(180, 165, 200, 20);
		add(serverIp);
		namefield.setBounds(180, 385, 200, 20);
		add(namefield);
		passfield.setBounds(180, 425, 200, 20);
		add(passfield);
		loginButton.setBounds(300, 480, 150, 60);
		add(loginButton);
		namefield2.setBounds(180, 745, 200, 20);
		add(namefield2);
		passfield2.setBounds(180, 795, 200, 20);
		add(passfield2);
		newAccountButton.setBounds(300, 850, 150, 60);
		add(newAccountButton);		


	}
}