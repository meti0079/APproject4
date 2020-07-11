package grapic;

import javax.swing.JPanel;

public class StartPlayShow extends JPanel{
	private static final long serialVersionUID = 1L;
	private InfoPanel inf ;
	public StartPlayShow(MainFrame f) throws Exception {
		inf=InfoPanel.getinsist(f);
		StartPlayPanel pp=new StartPlayPanel(f);
		add(inf);
		add(pp);
	}
}
