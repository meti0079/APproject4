package grapic;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class Clock  extends Thread{

	private  final long Frametime=1000/50;
	private float movepessecond;
	private long lastupdate;
	private int CycleUsed;
	private float Cyclehave;
	private JProgressBar jp;
	private PlayPanel playPanel;
	private JLabel alarm;
	public Clock(JProgressBar jp, PlayPanel playPanel) throws InterruptedException {
		movepessecond=1.0f;
		reset();
		this.jp=jp;
		this.playPanel=playPanel;
		alarm=new JLabel("Hurry up");
		alarm.setForeground(Color.RED);
		alarm.setFont(new Font("Tahoma", Font.BOLD, 35));
		alarm.setBounds(650	,420,200,50	);
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
				try {
					this.sleep(1000);
				} catch (InterruptedException e1) {
					System.out.println(255);			
					e1.printStackTrace();
				}

			}
			
			
			if (PlayPanel.i==60) {
				PlayPanel.i=0;
				playPanel.nextTurn();
			}
		}
	}

}
