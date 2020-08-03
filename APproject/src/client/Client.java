package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;


public class Client {
	public static SocketAddress address;
	public static DatagramSocket  datagramSocket;
	public static String serverIP="localhost";
	public static int serverport=8000;
	public static int maxLenght=100000;

	public static DatagramPacket readPacket(DatagramSocket datagramSocket) throws IOException {
		DatagramPacket datagramPacket = new DatagramPacket(new byte[maxLenght]	,maxLenght);
		datagramSocket.receive(datagramPacket);
		return datagramPacket;
	}
	public static void WriteMessage( String message) throws IOException {
		byte[] data=message.getBytes();
		DatagramPacket datagramPacket= new DatagramPacket(data, data.length, address);
		datagramSocket.send(datagramPacket);
	}
	public static void main(String[] args) throws IOException {
		datagramSocket= new DatagramSocket();
		
		address=new InetSocketAddress(serverIP, serverport);
//	/////////	MessageHandler.getInsisit();
		Reciver re= new Reciver(datagramSocket);
		re.start();
	}
}
