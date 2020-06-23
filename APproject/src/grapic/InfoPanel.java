package grapic;

import java.awt.BorderLayout;
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

import GAME.Gamestate;
import GAME.Logger;

public class InfoPanel extends JPanel{
	private Gamestate game;
	public static InfoPanel inf;
	private Logger log;
	private InfoPanel(MainFrame f) throws Exception {
		setPreferredSize(new Dimension(1800, 100));
		game=Gamestate.getinsist();
		setLayout(null);
		log=Logger.getinsist();
		JButton b= new JButton();
		b.setBounds(1600, 0, 100, 100);
		b.setIcon(new ImageIcon("menubut.png") );
		b.setContentAreaFilled(false);
		b.setBorder(BorderFactory.createEmptyBorder());
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					log.log(game.getPlayer().get_name(), "clicked  go menu button ", "");
					f.setContentPane(new MenuPanel((MainFrame)f));
					f.pack();
					f.repaint();
					f.revalidate();
				} catch (Exception e1) {
					System.out.println("adas");
					e1.printStackTrace();
				}

			}
		});

		JButton b1= new JButton();
		b1.setBounds(1700, 15, 70, 70);
		b1.setIcon(new ImageIcon("src\\button image\\exit.jpg") );
		b1.setContentAreaFilled(false);
		b1.setBorder(BorderFactory.createEmptyBorder());
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					log.log(game.getPlayer().get_name(), "clicked exit button ", "");
				} catch (IOException e2) {
					System.out.println("exit button");
					e2.printStackTrace();
				}
				int j=JOptionPane.showConfirmDialog(null, "Do you want realy Exit", "Confirm", JOptionPane.OK_CANCEL_OPTION);
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
		add(b1);
		add(b);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(new ImageIcon("4.png").getImage(), 0, 0, null);
		g.setFont(new Font("Tahoma", Font.BOLD, 40));
		g.setColor(Color.WHITE);
		g.drawString("Name : "+game.getPlayer().get_name(), 50, 60);
		g.drawString("Gem       : "+game.getPlayer().gem, 600, 60);
		g.drawImage(new ImageIcon("src\\button image\\gems.png").getImage(), 700, 20, null	);
	}
	public static InfoPanel getinsist(MainFrame f) throws Exception {
		if(inf==null)
			inf=new InfoPanel(f);
		return inf;
	}
}