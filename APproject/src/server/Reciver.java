package server;

import java.io.IOException;
import java.net.DatagramPacket;

public class Reciver extends Thread{

	@Override
	public void run() {
		///////////////////Handler handler= new Handler();
		while (true) {
			try {
				DatagramPacket datagramPacket= ServerMain.readPacket();
			///////////////////////////	handler.recivePacket(datagramPacket);
			} catch (IOException e) {	e.printStackTrace();}
		}
	}

}
