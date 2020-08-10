package client.grapic;

import javax.swing.JPanel;

import client.Controller;

public class StartPlayShow extends JPanel{
	private static final long serialVersionUID = 1L;
	private InfoPanel inf ;
	Controller controller;
	public StartPlayShow(Controller controller) throws Exception {
		inf=new InfoPanel(controller); 
		this.controller=controller;
		StartPlayPanel pp=new StartPlayPanel(controller);
		add(inf);
		add(pp);
	}
}
