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
		g.drawString("WellCome", 10	, 30);
		g.drawString("Sign in", 30, 80);
		g.drawString("Username :", 50, 202);
		g.drawString("Password :", 50, 248);
		g.drawString("create account", 30, 600);
		g.drawString("Username :", 50, 659);
		g.drawString("Password :", 50, 709);
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
		setLayout(new GridBagLayout());
		GridBagConstraints g= new GridBagConstraints();
		g.weightx=0;
		g.weighty=0.1;
		g.gridx=0;
		g.gridy=0;
		g.fill=GridBagConstraints.NONE;
		g.anchor=GridBagConstraints.LINE_END;
		g.insets=new Insets(100,150,0, 150);
		add(namefield,g);
		////////////////  second row////////////////	
		g.gridx=0;
		g.insets=new Insets(190, 180, 0, 150);
		g.anchor=GridBagConstraints.LINE_START;
		add(passfield,g);
		////////////////next     
		g.gridy++;	
		g.gridx=0;
		g.insets=new Insets(0, 150, 600,220);
		g.anchor=GridBagConstraints.FIRST_LINE_END;
		add(loginButton,g);
		////////////////third row////////////////
		g.gridx=0;
		g.insets=new Insets(350,180, 0, 0);
		g.anchor=GridBagConstraints.FIRST_LINE_START;
		add(namefield2,g);
		/////  box
		g.gridx=0;
		g.insets=new Insets(400,180, 0, 0);
		g.anchor=GridBagConstraints.FIRST_LINE_START;
		add(passfield2,g);
		////////////////next     
		g.gridx=0;
		g.insets=new Insets(450,150, 0,180);
		g.anchor=GridBagConstraints.FIRST_LINE_END;
		add(newAccountButton,g);
		g.gridx=0;
		g.insets=new Insets(582,100, 0,140);
		g.anchor=GridBagConstraints.FIRST_LINE_END;
		add(error1,g);
		g.gridx=0;
		g.insets=new Insets(82,40, 0,80);
		g.anchor=GridBagConstraints.FIRST_LINE_END;
		add(error,g);
	}
}