package client.grapic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import client.Controller;


public class RankPanel extends JPanel {
	ArrayList<String > top=new ArrayList<>();
	ArrayList<String> me=new ArrayList<>();
	Font font=new Font("Tahoma", Font.ITALIC, 15);
	Font font1=new Font("Tahoma", Font.BOLD, 25);

	public RankPanel(Controller controller) throws Exception {
		InfoPanel p=new InfoPanel(controller);
		add(p);
		initial();
	}
	private void initial() {
		setPreferredSize(new Dimension(1800, 1000));
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);	
		setBackGround(g);
		writeTopic(g);
		drawDetails(g);
	}
	private void setBackGround(Graphics g) {
		g.drawImage(new ImageIcon("src\\main\\java\\passiva image\\astatos.jpg").getImage(), 0, 0, null);		
	}
	private void writeTopic(Graphics g) {
		g.setFont(new Font("Tahoma", Font.BOLD, 50));
		g.setColor(Color.RED);
		g.drawString("RANK", 80	, 140);
		g.drawString("MAME", 820	, 140);
		g.drawString("CUP", 1600	, 140);
	}
	private void drawDetails(Graphics g) {
		if(me.size()>0 || top.size()>0) {
			int i=0;
			for (String string : top) {
				g.setColor(Color.RED);
				g.setFont(font);
				g.drawRoundRect(20, 125+i*65, 1760, 60,20,20);
				g.setColor(Color.DARK_GRAY);
				g.setFont(font1);
				g.drawString(string, 30, 165+i*60);
				i++;
			}
			int j=10;
			for (String string : me) {
				g.setColor(Color.RED);
				g.setFont(font);
				g.drawRoundRect(20, 125+j*65, 1760, 60,20,20);
				g.setColor(Color.darkGray);
				g.setFont(font1);
				if(j==15) {
					g.setColor(Color.CYAN);
					g.drawString(string, 30, 165+j*60);

					j++;
				}
			}
		}
	}
	public void setTop(ArrayList<String> top) {
		this.top = top;
	}

	public void setMe(ArrayList<String> me) {
		this.me = me;
	}
}
