package grapic;

import java.awt.Color;
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
import GAME.Gamestate;
import GAME.Logger;

public class MenuPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Gamestate game;
	private Logger log;
	private JButton play;
	private JButton collection;
	private JButton status;
	private JButton store;
	private JButton exit;
	private JButton save;
	public MenuPanel(JFrame frame) throws Exception {
		super();
		setPreferredSize(new    Dimension(1800, 1000));
		setLayout(null);
		game=Gamestate.getinsist();
		log=Logger.getinsist();
		//////set buttons
		play=new JButton("play");
		play.setFont(new Font("Tahoma", Font.BOLD, 30));
		play.setForeground(Color.BLACK);
		play.setBackground(Color.orange);
		play.setBounds(795, 300, 210, 60);
		add(play);
		collection=new JButton("Collection");
		collection.setFont(new Font("Tahoma", Font.BOLD, 30));
		collection.setForeground(Color.BLACK);
		collection.setBackground(Color.orange);
		collection.setBounds(765, 365, 270, 60);
		add(collection);
		store=new JButton("Shop");
		store.setFont(new Font("Tahoma", Font.BOLD, 30));
		store.setForeground(Color.BLACK);
		store.setBackground(Color.orange);
		store.setBounds(735, 430, 330, 60);
		add(store);
		save=new JButton("Save");
		save.setFont(new Font("Tahoma", Font.BOLD, 30));
		save.setForeground(Color.BLACK);
		save.setBackground(Color.orange);
		save.setBounds(735, 495, 330, 60);
		add(save);
		status=new JButton("Status");
		status.setFont(new Font("Tahoma", Font.BOLD, 30));
		status.setForeground(Color.BLACK);
		status.setBackground(Color.orange);
		status.setBounds(765, 560, 270, 60);
		add(status);
		exit=new JButton("Exit");
		exit.setFont(new Font("Tahoma", Font.BOLD, 30));
		exit.setForeground(Color.BLACK);
		exit.setBackground(Color.orange);
		exit.setBounds(795, 625, 210, 60);
		add(exit);
		//////set buttons finish
		/////set actin listener
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					log.log(game.getPlayer().get_name(), "clicked save button ", "");
				} catch (IOException e2) {
					System.out.println("save button");
					e2.printStackTrace();
				}
				int j=JOptionPane.showConfirmDialog(MenuPanel.this, "Do you want save game", "Confirm", JOptionPane.OK_CANCEL_OPTION);
				if(j==JOptionPane.OK_OPTION) {
					try {
						game.makeProfile();
						game.writeName(game.getPlayer().get_name(), game.getPlayer().get_pass());
						log.log(game.getPlayer().get_name(), "save game", "");
						game.makeProfile();
					} catch (IOException e1) {
						System.out.println("save button");
						e1.printStackTrace();
					}
				}
			}
		});	

		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					log.log(game.getPlayer().get_name(), "clicked exit button ", "");
				} catch (IOException e2) {
					System.out.println("exit button");
					e2.printStackTrace();
				}
				int j=JOptionPane.showConfirmDialog(MenuPanel.this, "Do you want realy Exit", "Confirm", JOptionPane.OK_CANCEL_OPTION);
				if(j==JOptionPane.OK_OPTION) {
					try {
						log.log(game.getPlayer().get_name(), "exit game", "");
					} catch (IOException e1) {
						System.out.println("exit button");
						e1.printStackTrace();
					}	
					System.exit(0);
				}
			}
		});

		store.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					log.log(game.getPlayer().get_name(), "clicked Shop button ", "");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				setVisible(false);
				try {
					Shop sh=new Shop((MainFrame)frame);
					frame.remove(MenuPanel.this);	
					frame.setContentPane(sh);
					frame.repaint();
					frame.revalidate();
				} catch (Exception e) {
					e.printStackTrace();
				}
				frame.pack();
				frame.setLocationRelativeTo(null);
				try {
					log.log(game.getPlayer().get_name(), "go to shop ", "");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		collection.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					log.log(game.getPlayer().get_name(), "clicked collection button ", "");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				try {
					frame.remove(MenuPanel.this);
					CollectionPanel c=new CollectionPanel((MainFrame)frame);
					frame.setContentPane(c);
					frame.revalidate();
					frame.repaint();
					frame.pack();
					frame.setLocationRelativeTo(null);
				} catch (Exception e1) {
					System.out.println("ha");
					e1.printStackTrace();
				}
				try {
					log.log(game.getPlayer().get_name(), "go to collection ", "");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		play.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(game.getPlayer().getMyDeck().getDeck().size()==15) {
					try {
						log.log(game.getPlayer().get_name(), "go to play", "");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					frame.remove(MenuPanel.this);
					PassiveShow p;
					try {
						p = new PassiveShow((MainFrame)frame);
						p.setPreferredSize(new Dimension(1800,1000));
						frame.setContentPane(p);
						frame.revalidate();
						frame.repaint();
						frame.pack();
						frame.setLocationRelativeTo(null);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}else {
					int j=JOptionPane.showConfirmDialog(null, "you dont have a good deck!!! edit or change this.", "deck not full", JOptionPane.ERROR_MESSAGE);
					try {
						log.log(game.getPlayer().get_name(), "error", "deck has problem");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					if(j==JOptionPane.OK_OPTION) {
						try {
							frame.remove(MenuPanel.this);
							CollectionPanel c=new CollectionPanel((MainFrame)frame);
							frame.setContentPane(c);
							frame.revalidate();
							frame.repaint();
							frame.pack();
							frame.setLocationRelativeTo(null);
						} catch (Exception e1) {
							System.out.println("ha");
							e1.printStackTrace();
						}
					}
				}
			}
		});

		status.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					log.log(game.getPlayer().get_name(), "go to Status", "");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Statos s=null;
				try {
					s = new Statos((MainFrame)frame);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				frame.remove(MenuPanel.this);
				frame.setContentPane(s);
				frame.revalidate();
				frame.repaint();
				frame.pack();
				frame.setLocationRelativeTo(null);
			}
		});
	}
	@Override
	protected void paintComponent(Graphics g) {
		ImageIcon image = new ImageIcon("6.jpg"); 
		g.drawImage(image.getImage(),0,0,null);
	}
}
