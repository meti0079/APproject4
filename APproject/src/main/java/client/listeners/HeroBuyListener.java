package client.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.JOptionPane;
import com.google.gson.Gson;
import client.ClientMain;
import gameModel.requestAndREsponse.SellAndBuy;
import server.hero.Heros;

public class HeroBuyListener implements MouseListener {
	private Heros s;
	int tocken;

	public HeroBuyListener(Heros s, int tocken) {
		this.s = s;
		this.tocken=tocken;
	}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {
		int j=JOptionPane.showConfirmDialog(null, "Do you want buy this hero??\n"+
				"name : "+s.getname()+"\n"
				+ "you give 25 gem t. \n "+" hero power : "+s.get_SpecialPower() +"\n"
				+"HeroPowerMana : "+s.getHero_power().getMana()+"\n"
				+"SpecialPower : "+s.get_SpecialPower()+"\n"
				, "Confirm", JOptionPane.OK_CANCEL_OPTION);
		if(j==JOptionPane.OK_OPTION) {	
			String message="BUYHERO>>"+new Gson().toJson(new SellAndBuy(s.getname(), tocken))+"#";
			try {
				ClientMain.WriteMessage(message);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {}

}
