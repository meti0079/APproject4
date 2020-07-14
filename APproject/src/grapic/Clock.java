package grapic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

import GAME.Gamestate;
import playModel.ComputerPlayer;

public class Clock  extends Thread{

	private  final long Frametime=1000/50;
	private float movepessecond;
	private long lastupdate;
	private int CycleUsed;
	private float Cyclehave;
	private JProgressBar jp;
	private PlayPanel playPanel;
	private JLabel alarm;
	ComputerPlayer computer;
	public Clock(JProgressBar jp, PlayPanel playPanel, ComputerPlayer pla) throws InterruptedException {
		movepessecond=1.0f;
		this.computer=pla;
		reset();
		this.jp=jp;
		this.playPanel=playPanel;
		makeAlarmLable();
	}
	private void makeAlarmLable() {
		alarm=new JLabel("Hurry up");
		alarm.setForeground(Color.RED);
		alarm.setFont(new Font("Tahoma", Font.BOLD, 35));
		alarm.setBounds(650	,440,200,50	);
		playPanel.add(alarm);
		alarm.setVisible(false);
	}
	public void reset() {
		this.CycleUsed=0;
		this.Cyclehave=0.0f;
		this.lastupdate=getCurrenttime();
	}
	private long getCurrenttime() {
		return System.nanoTime()/1000000;
	}
	public void update() {
		long cur=getCurrenttime();
		float a=(float)(cur-lastupdate)+Cyclehave;
		this.CycleUsed += (int)Math.floor(a / movepessecond);
		this.Cyclehave = a % movepessecond;
		this.lastupdate=cur;
	}
	public boolean hascycle() {
		if(CycleUsed>0) {
			this.CycleUsed--;
			return true;
		}
		return false;
	}
	@Override
	public  void run() {
		while(true) {
			try {
			if(Gamestate.getinsist().getState().equalsIgnoreCase("computer")) {
				if(playPanel.getRoundGame()%2==1) {
					if(playPanel.i==5) {
						computer.addToBattlefield();
						playPanel.updatePanel();
					}
					if(playPanel.i==20) {
						computer.attack();
						playPanel.updatePanel();
					}
					if(playPanel.i==30) {
						playPanel.nextTurn();
					}	
				}		
			}			
			long s=System.nanoTime();
			update();
			if(hascycle()) {
				PlayPanel.i++;
			}
			jp.setString(PlayPanel.i+"");
			jp.setValue(PlayPanel.i);
			long e = ( System.nanoTime()-s)/1000000;
			if(playPanel.i==40) {
				alarm.setVisible(true);
			}else if(playPanel.i==42)
				alarm.setVisible(false);
			if(e<Frametime) {
					this.sleep(1000);
			}
			if (PlayPanel.i==60) {
				alarm.setVisible(false);
				PlayPanel.i=0;
				try {
					playPanel.nextTurn();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			} catch (Exception e1) {
				System.out.println(255);			
				e1.printStackTrace();
			}
		}
	}
}
