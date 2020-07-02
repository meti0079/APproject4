package grapic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import GAME.Gamestate;

public class SettingPanel extends JPanel{

	
	private static final long serialVersionUID = 1L;
	private Gamestate game;
	private JButton deletAcount;
	private JLabel background1;
	private JLabel background2;
	private JLabel background3;
	private JLabel background4;
	private InfoPanel inf;
	public SettingPanel(MainFrame f) throws Exception {
		inf=InfoPanel.getinsist(f);
		setPreferredSize(new Dimension(1800, 1000));
		setLayout(null);
		inf.setBounds(0, 0, 1800, 100);
		add(inf);
	game=Gamestate.getinsist();
	
	deletAcount=new JButton("Delete Acount");
	deletAcount.setFont(new Font("Tahoma", Font.BOLD, 30));
	deletAcount.setForeground(Color.BLACK);
	deletAcount.setBackground(Color.orange);
	deletAcount.setBounds(50, 200, 300, 60);
	add(deletAcount);
	background1=new JLabel(new ImageIcon("src\\play image\\nattle11.jpg"));
	background1.setBounds(840,260, 226, 158);
	add(background1);
	background2=new JLabel(new ImageIcon("src\\play image\\nattle21.jpg"));
	background2.setBounds(1100, 260, 226,158);
	add(background2);
	background3=new JLabel(new ImageIcon("src\\play image\\nattle31.jpg"));
	background3.setBounds(840, 460, 226,158);
	add(background3);
	background4=new JLabel(new ImageIcon("src\\play image\\nattle41.jpg"));
	background4.setBounds(1100,460, 226,158);
	add(background4);
	
	
	
	
//	/////// set listener
	deletAcount.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			int x=JOptionPane.showConfirmDialog(null, "do you really want to delete account","confirm", JOptionPane.OK_CANCEL_OPTION);
			if(x==JOptionPane.OK_OPTION)
			try {
				game.DeletPlayer();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	});
	
	background1.addMouseListener(new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mousePressed(MouseEvent arg0) {
			setBattlebackGround(1);
			JOptionPane.showMessageDialog(null, "choosed");
		}
		
		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	});
	background2.addMouseListener(new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			setBattlebackGround(2);
			JOptionPane.showMessageDialog(null, "choosed");

		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	});
	background3.addMouseListener(new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			setBattlebackGround(3);
			JOptionPane.showMessageDialog(null, "choosed");
			
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	});
	background4.addMouseListener(new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			setBattlebackGround(4);
			JOptionPane.showMessageDialog(null, "choosed");
			
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	});
	
	}
	
	public void setBackCard(int a) {
		String s="ca"+a+".png";
		game.setBackBattleground(s);
	}
	public void setBattlebackGround(int a) {
		String s="nattle"+a+".jpg";
		game.setBackBattleground(s);
	}
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(new ImageIcon("setting.jpg").getImage(), 0, 0, null);
		g.setFont(new Font("Tahoma", Font.BOLD, 40));
		g.drawString("choose a battleground", 870, 200);
	}
}
