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
	public PlayShow(Controller controller) throws Exception {
	initial();
	this.controller=controller;
	setTextArea();
	setPlayPanel();
	}
	private void initial() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(1800, 1000));
	}
	private void setTextArea() {
		text=new TextArea();
		text.setPreferredSize(new Dimension(300, 1000));
		text.setBackground(new Color(27, 0, 53));
		text.setForeground(Color.WHITE);
		text.setFont(new Font("Tahoma", Font.BOLD, 15));
		add(text,BorderLayout.WEST);
	}
	private void setPlayPanel() throws Exception {
		 p=new PlayPanel( text, controller);
		add(p,BorderLayout.CENTER);
	}
	public void update(String text1) {
		text.setText(text1);
		p.updatePanel();
	}
}
