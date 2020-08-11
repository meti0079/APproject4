package server;

import java.io.ByteArrayInputStream;
import java.net.DatagramPacket;
import java.util.Scanner;

public class MessageHandler {
	Controller controller;
	public MessageHandler() {
		controller=new Controller();
	}
	public void recivePacket(DatagramPacket packet) {
		ByteArrayInputStream arrayInputStream=new ByteArrayInputStream(packet.getData());
		whatWant(read(arrayInputStream), packet);
	}

	private String read(ByteArrayInputStream arrayInputStream) {
		Scanner scan = new Scanner(arrayInputStream);
		String message="";
		while (scan.hasNext()) {
			message+=scan.next();
		}
		scan.close();
		return message;
	}
	public void whatWant(String message, DatagramPacket packet) {
		String what="";
		for(int i=0 ;i<message.length();i++) {
			if(message.charAt(i)=='>') {
				what=message.substring(0, i);
				message=message.substring(i+2,message.length());
				break;
			}
		}
		for(int i=0;i<message.length();i++)
			if(message.charAt(i)=='#') {
				message=message.substring(0,i+1);
			}
		switch (what) {
		case "LOGIN":
			controller.login(message, packet);
			break;
		case "SINGUP":
			controller.singup(message, packet);
			break;
		case "SAVE":
			controller.save(message, packet);
			break;
		case "EXIT":
			controller.exit(message, packet);
			break;
		case "GOSHOP":
			controller.goShop(message, packet);
			break;
		case "SELL":
			controller.sellCard(message, packet);
			break;
		case "BUYHERO":
			controller.buyHero(message, packet);
			break;
		case "BUYCARD":
			controller.buyCard(message, packet);
			break;
		case "GOSTATOS":
			controller.statos(message, packet);
			break;
		case "GOMENU":
			controller.goMenu(message, packet);
			break;
		case "GOSETTING":
			controller.setting(message, packet);
			break;			
		case "DELETACCOUNT":
			controller.deleteAccount(message,packet);
			break;
		case "CHANGEBATTLEGROUND":
			controller.setBattlebackGround(message,packet);
			break;
		case "GOCOLLECTION":
			controller.collection(message, packet);
			break;
		case "ADDTOENEMYDECK":
			controller.addCardToEnemyDeck(message, packet);
			break;
		case "ADDTOMYDECK":
			controller.addCardToMyDeck(message, packet);
			break;
		case "REMOVEFROMDECK":
			controller.removeCardFromMyDeck(message, packet);
			break;
		case "REMOVEFROMENEMYDECK":
			controller.removeCardFromEnemyDeck(message, packet);
			break;
		case "NEWDECK":
			controller.makeNewDeck(message, packet);
			break;
		case "EDITHERODECK":
			controller.editHeroDeck(message,packet);
			break;
		case "EDITNAMEDECK":
			controller.editNameDeck(message,packet);
			break;
		case "CHANGEDECK":
			controller.changeDeck(message,packet);
			break;
		case "SEARCH":
			controller.serch(message, packet);
			break;
		case "GOPLAY":
			controller.startPlay(message , packet);
			break;
		case "STARTMATCH":
			controller.startmatch(message , packet);
			break;
		case "NEXTTURN":
			controller.nextTurn(message , packet);
			break;
		case "HEROPOWER":
			controller.heroPower(message , packet);
			break;
		case "SETPASSIVE":
			controller.setPassive(message , packet);
			break;
		case "ATTACK":
			controller.attack(message , packet);
			break;
		case "CHANGCARD":
			controller.changCard(message , packet);
			break;
		case "ADDTOBATTLEGROUND":
			controller.addToBattleground(message , packet);
			break;
		case "EXITGAME":
			controller.exitMatch(message,packet);
			break;
		case "IAM":
			controller.isAlive(message,packet);
			break;
		case "SENDMESSAGE":
			controller.sendMessage(message,packet);
			break;
		case "SEEMATCH":
			controller.seeMatch(message,packet);
			break;
		default:
			break;
		}
	}
}
