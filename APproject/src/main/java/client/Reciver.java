package client;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;

public class Reciver extends Thread{
	DatagramSocket data;
	public Reciver(DatagramSocket daa) {
		data=daa;
	}
	@Override
	public void run() {
		MessageHandler handler=new MessageHandler();
		while (true) {
			try {
				DatagramPacket packet= Client.readPacket(data);
				ByteArrayInputStream arrayInputStream=new ByteArrayInputStream(packet.getData());				
				handler.readMessage(read(arrayInputStream));
			} catch (IOException e) {	e.printStackTrace();	}
		}
	}
	
	private String read(ByteArrayInputStream arrayInputStream) {
		Scanner scan = new Scanner(arrayInputStream);
		String message="";
		while (scan.hasNextLine()) {
			message+=scan.nextLine();
		}
		scan.close();
		return message;
	}

}
