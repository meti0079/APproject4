package client.grapic;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import client.Client;
import client.Controller;
import game.Gamestate;
import game.Logger;
import gameModel.requestAndREsponse.SaveAndExitRequest;

public class MenuPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton play;
	private JButton collection;
	private JButton status;
	private JButton store;
	private JButton exit;
	private JButton save;
	private JButton setting;
	private Controller controller;
	private void initial() throws Exception {
		controller=Controller.getInsist();
		setPreferredSize(new  Dimension(1800, 1000));
		setLayout(null);
	}
	public MenuPanel() throws Exception {
		super();
		initial();
		//////set buttons
		setPlayButton();
		setCollectionButton();
		setSettingButton();
		setStatusButton();
		setStoreButton();
		setExitButton();
		setSaveButton();
	}
	@Override
	protected void paintComponent(Graphics g) {
		serBckGround(g);
	}
	private void serBckGround(Graphics g) {
		ImageIcon image = new ImageIcon("6.jpg"); 
		g.drawImage(image.getImage(),0,0,null);
	}
	
	private void setSaveButton() {
		save=new JButton("Save");
		save.setFont(new Font("Tahoma", Font.BOLD, 30));
		save.setForeground(Color.BLACK);
		save.setBackground(Color.orange);
		save.setBounds(735, 530, 330, 60);
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int j=JOptionPane.showConfirmDialog(MenuPanel.this, "Do you want save game", "Confirm", JOptionPane.OK_CANCEL_OPTION);
					if(j==JOptionPane.OK_OPTION) {
						String 	message= "SAVE>>"+new SaveAndExitRequest(controller.getUser().getTocken())+"#";
						Client.WriteMessage(message);	
					}
				} catch (IOException e1) {
					System.out.println("save button");
					e1.printStackTrace();
				}
			}
		});	
		add(save);
	}

	private void setExitButton() {
		exit=new JButton("Exit");
		exit.setFont(new Font("Tahoma", Font.BOLD, 30));
		exit.setForeground(Color.BLACK);
		exit.setBackground(Color.orange);
		exit.setBounds(795, 660, 210, 60);
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int j=JOptionPane.showConfirmDialog(MenuPanel.this, "Do you want realy Exit", "Confirm", JOptionPane.OK_CANCEL_OPTION);
					if(j==JOptionPane.OK_OPTION) {
					String 	message= "EXIT>>"+new SaveAndExitRequest(controller.getUser().getTocken())+"#";
					Client.WriteMessage(message);
					System.exit(0);
					}
				} catch (IOException e1) {
					System.out.println("exit button");
					e1.printStackTrace();
				}	
			}
		});
		add(exit);
	}
	private void setStoreButton() {
		store=new JButton("Shop");
		store.setFont(new Font("Tahoma", Font.BOLD, 30));
		store.setForeground(Color.BLACK);
		store.setBackground(Color.orange);
		store.setBounds(735, 400, 330, 60);
		store.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String 	message= "GOSHOP>>"+new SaveAndExitRequest(controller.getUser().getTocken())+"#";
					Client.WriteMessage(message);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		add(store);
	}
	private void setStatusButton() {
		status=new JButton("Status");
		status.setFont(new Font("Tahoma", Font.BOLD, 30));
		status.setForeground(Color.BLACK);
		status.setBackground(Color.orange);
		status.setBounds(705, 465, 390, 60);
		status.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String 	message= "GOSTATOS>>"+new SaveAndExitRequest(controller.getUser().getTocken())+"#";
					Client.WriteMessage(message);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		add(status);
	}
	private void setSettingButton() {
		setting=new JButton("setting");
		setting.setFont(new Font("Tahoma", Font.BOLD, 30));
		setting.setForeground(Color.BLACK);
		setting.setBackground(Color.orange);
		setting.setBounds(765, 595, 270, 60);
		setting.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String 	message= "GOSETTING>>"+new SaveAndExitRequest(controller.getUser().getTocken())+"#";
					Client.WriteMessage(message);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		add(setting);
	}
	private void setPlayButton() {
		play=new JButton("play");
		play.setFont(new Font("Tahoma", Font.BOLD, 30));
		play.setForeground(Color.BLACK);
		play.setBackground(Color.orange);
		play.setBounds(795, 270, 210, 60);
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {				
					if(game.getPlayer().getMyDeck().getDeck().size()==15) {
						log.log(game.getPlayer().get_name(), "go to play", "");
//						StartPlayShow p = new StartPlayShow((MainFrame)frame);
//						p.setPreferredSize(new Dimension(1800,1000));
//						frame.ChangePanel(p);
					}else {
						int j=JOptionPane.showConfirmDialog(null, "you dont have a good deck!!! edit or change this.", "deck not full", JOptionPane.ERROR_MESSAGE);
						log.log(game.getPlayer().get_name(), "error", "deck has problem");
						if(j==JOptionPane.OK_OPTION) {
//							CollectionPanel c=new CollectionPanel((MainFrame)frame);
//							frame.ChangePanel(c);
						}
					}
				} catch (Exception e1) {
					System.out.println("cant go play");
					e1.printStackTrace();
				}
			}
		});
		add(play);
	}
	private void setCollectionButton() {
		collection=new JButton("Collection");
		collection.setFont(new Font("Tahoma", Font.BOLD, 30));
		collection.setForeground(Color.BLACK);
		collection.setBackground(Color.orange);
		collection.setBounds(765, 335, 270, 60);
		collection.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					log.log(game.getPlayer().get_name(), "clicked collection button ", "");				
//					CollectionPanel c=new CollectionPanel((MainFrame)frame);
//					frame.ChangePanel(c);
					log.log(game.getPlayer().get_name(), "go to collection ", "");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		add(collection);
	}
}
