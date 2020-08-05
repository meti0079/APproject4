package client.grapic;

import javax.swing.JPanel;

public class StartPlayShow extends JPanel{
	private static final long serialVersionUID = 1L;
	private InfoPanel inf ;
	public StartPlayShow() throws Exception {
		inf=InfoPanel.getinsist();
		StartPlayPanel pp=new StartPlayPanel();
		add(inf);
		add(pp);
	}
}
