package server;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.util.Scanner;

public class Reciver extends Thread{

	@Override
	public void run() {
MessageHandler handler=new MessageHandler();
		while (true) {
			try {
				DatagramPacket datagramPacket= ServerMain.readPacket();
				ByteArrayInputStream arrayInputStream=new ByteArrayInputStream(datagramPacket.getData());				
				handler.whatWant(read(arrayInputStream), datagramPacket);
			} catch (IOException e) {	e.printStackTrace();}
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