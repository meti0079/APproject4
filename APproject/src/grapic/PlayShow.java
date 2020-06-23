package grapic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextArea;

import javax.swing.JPanel;

public class PlayShow extends JPanel{

	private static final long serialVersionUID = 1L;
TextArea text;
	public PlayShow(MainFrame f) throws Exception {
		setLayout(new BorderLayout());
		text=new TextArea();
		text.setPreferredSize(new Dimension(300, 1000));
		text.setBackground(new Color(27, 0, 53));
		text.setForeground(Color.WHITE);
		text.setFont(new Font("Tahoma", Font.BOLD, 15));
		add(text,BorderLayout.WEST);
		setPreferredSize(new Dimension(1800, 1000));
		PlayPanel p=new PlayPanel(f, text);
		add(p,BorderLayout.CENTER);
	}
}
