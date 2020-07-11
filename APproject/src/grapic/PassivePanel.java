package grapic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import myListeners.PassiveListener;
import playModel.Mapper;
import playModel.Player;


public class PassivePanel extends JPanel {


	private static final long serialVersionUID = 1L;
	private Player p;
	public PassivePanel(Player p) throws Exception {
		this.p=p;
		Initial();
		drawLables();
	}
	private void Initial(){
		setPreferredSize(new Dimension(1000, 500));
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
	}
	private void drawLables() throws Exception {
		ArrayList<Integer > a=new ArrayList<>();
		while (a.size()!=3) {
			int x=(new Random().nextInt(7));
			if(!a.contains(x)&&x>=0&&x<=7)
				a.add(x);
		}
		for(int i=0 ;i<3 ; i++) {
			final JLabel lp1 =new JLabel(new ImageIcon( System.getProperty("user.dir")+"\\src\\passiva image\\"+Mapper.getinsist().getAllPassives().get(a.get(i)).getName()+".png"));
			lp1.setBounds(i*300, 0, 300, 400);
			lp1.addMouseListener(new PassiveListener(Mapper.getinsist().getAllPassives().get(a.get(i)),p,this));
			add(lp1);
		}
	}
}
