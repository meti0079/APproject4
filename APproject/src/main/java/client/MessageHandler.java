package client;

public class MessageHandler {
	Controller controller;
	public MessageHandler() {
	controller=Controller.getInsist();
	}

	
	public void readMessage(String message) {
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
				message=message.substring(0,i);
			}
		switch (what) {
		case "LOGINERROR":
			controller.loginError(message);
			break;
		case "SELLERROR":
			controller.sellError(message);
			break;
		case "SETPLAYER":
			controller.setPlayer(message);
			break;
		case "CHANGEPANEL":
			controller.changPanel(message);
			break;
		case "SETSHOPNEED":
			controller.shopNeed(message);
			break;
		case "UPDATE":
			controller.updatePanels(message);
			break;
			
			
			
			
			
			
		default:
			break;
		}
		
	}
	
	
	
	
}