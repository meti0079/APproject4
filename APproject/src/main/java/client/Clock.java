package client;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import gameModel.requestAndREsponse.SaveAndExitRequest;

public class Clock  extends Thread{
	int timeLeft=30;
	int deedLine=40;
	long connection;
	int tocken;
	public Clock(int tocken) {
	this.tocken=tocken;
	}
	@Override
	public  void run() {
		while(true) {
			try {	
				long s=System.nanoTime();
				if((s-connection)/1000000000>timeLeft) {
					String mess="IAM>>"+new Gson().toJson(new SaveAndExitRequest(tocken))+"#";
					ClientMain.WriteMessage(mess);
				}
				if((s-connection)/1000000000>deedLine) {
					JOptionPane.showConfirmDialog(null,"your connection to server has been lost" );
					System.exit(0);					
				}
				this.sleep(1000);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	public long getConnection() {
		return connection;
	}

	public void setConnection(long connection) {
		this.connection = connection;
	}

}
