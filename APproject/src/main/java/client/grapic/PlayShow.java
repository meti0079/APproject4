package client.grapic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextArea;
import javax.swing.JPanel;

import client.Controller;

public class PlayShow extends JPanel{

	private static final long serialVersionUID = 1L;
	TextArea text;
	PlayPanel p;
	Controller controller;
	Chatting chatting;
	public PlayShow(Controller controller) throws Exception {
	initial();
	this.controller=controller;
	setTextArea();
	setPlayPanel();
	}
	private void initial() {
		setLayout(null);
		setPreferredSize(new Dimension(1800, 1000));
	}
	private void setTextArea() {
		text=new TextArea();
		text.setPreferredSize(new Dimension(300, 800));
		text.setBackground(new Color(27, 0, 53));
		text.setForeground(Color.WHITE);
		text.setFont(new Font("Tahoma", Font.BOLD, 15));
		text.setBounds(0, 0, 300, 800);
		add(text);
	}
	private void setPlayPanel() throws Exception {
		 p=new PlayPanel( text, controller);
		 p.setBounds(300, 0, 1500, 1000);
		 add(p);
		chatting=new Chatting(controller.getUser().getTocken());
		chatting.setBackground(Color.GRAY);
		chatting.setBounds(0, 800, 300, 200);
		add(chatting);
	}
	public void update(String text1) {
		text.setText(text1);
		p.updatePanel();
	}
	public Chatting getChat() {
		return chatting;
	}
	public PlayPanel getP() {
		return p;
	}

}
