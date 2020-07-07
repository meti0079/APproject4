package grapic;

import javax.swing.JPanel;

public class PassiveShow extends JPanel{
	private static final long serialVersionUID = 1L;
	private InfoPanel inf ;
	public PassiveShow(MainFrame f) throws Exception {
		inf=InfoPanel.getinsist(f);
		PassivePanel pp=new PassivePanel(f);
		add(inf);
		add(pp);
	}
}
