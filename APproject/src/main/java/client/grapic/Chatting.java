package client.grapic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.google.gson.Gson;

import client.Client;
import gameModel.requestAndREsponse.ChatRequest;

public class Chatting  extends JPanel{
	private JTextField textField;
	private JButton send;
	private int tocken;
	JTextArea textArea;
	public Chatting(int tocken) {
		this.tocken=tocken;
		textField=new JTextField();
		send=new JButton("SEND");
		initial();
	initialButten();
	}
	private void initial() {
		setPreferredSize(new Dimension(300	, 300));
		setSize(new Dimension(300, 300));
		setLayout(null);
		textArea=new JTextArea();
		textArea.setBackground(Color.GRAY);
		textArea.setBounds(0, 0, 300, 150);
		add(textArea);
	}
	private void initialButten() {
		send.setBounds(220, 150, 80, 50);
		add(send);
		textField.setBounds(0, 150, 220, 50);
		add(textField);
		send.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String message="SENDMESSAGE>>"+new Gson().toJson(new ChatRequest(textField.getText(),tocken ))+"#";
				try {
					Client.WriteMessage(message);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});	
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	
}
