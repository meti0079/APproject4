package grapic;

import javax.swing.JPanel;

public class PassiveShow extends JPanel{
private InfoPanel inf ;
	public PassiveShow(MainFrame f) throws Exception {
		inf=InfoPanel.getinsist(f);
		PassivePanel pp=new PassivePanel(f);
		add(inf);
		add(pp);
	}
}
