package client.grapic;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import client.Controller;
import client.listeners.PassiveListener;

public class PassivePanel extends JPanel {


	private static final long serialVersionUID = 1L;
	public PassivePanel(Controller controller) throws Exception {
		Initial();
		drawLables(controller);
	}
	private void Initial(){
		setPreferredSize(new Dimension(1000, 500));
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
	}
	private void drawLables(Controller controller) throws Exception {
		System.out.println(controller.getGameNeed().getPassive());
		for(int i=0 ;i<controller.getGameNeed().getPassive().size() ; i++) {
			final JLabel lp1 =new JLabel(new ImageIcon( System.getProperty("user.dir")+"\\src\\main\\java\\passiva image\\"+controller.getGameNeed().getPassive().get(i)+".png"));
			lp1.setBounds(i*300, 0, 300, 400);
			lp1.addMouseListener(new PassiveListener(this,controller.getGameNeed().getPassive().get(i),controller.getUser().getTocken()));
			add(lp1);
		}
	}
}
