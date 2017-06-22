package harrypotter.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TriwizardTournamentView extends JFrame
{
	JPanel btnPanel;
	ChampionsSelection view;
	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	public TriwizardTournamentView() throws IOException
	{
		super();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		screen.height = screen.height - 62;
		view = new ChampionsSelection();
		btnPanel = new JPanel();
		/*JLabel label = new JLabel();
		Icon icon = new ImageIcon("src/harrypotter/view/ezgif.com-resize-2.gif");
//		icon.
		label.setIcon(icon);*/
		//label.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
		//int border1 = (int) (screen.getWidth() - icon.getIconWidth())/2;
		//int border2 = border1 + icon.getIconWidth();
		//label.setBounds(border1, 0, icon.getIconWidth(), icon.getIconHeight());
		JLabel label = new JLabel();
		Icon icon = new ImageIcon("src/harrypotter/view/ezgif.com-resize-2.gif");
		label.setIcon(icon);
		label.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
		
		btnPanel.setLayout(new GridLayout(2,0,10,10));
		btnPanel.setSize(new Dimension(400, getHeight()));
		JButton start = new JButton("Start Tournament");
		start.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				start();
			}
		});
		start.setBounds(screen.width/5, screen.height*2/3, screen.width/4, screen.height/4);
		start.setOpaque(false);
		start.setBorderPainted(false);
		start.setForeground(Color.WHITE);
		start.setFont(new Font("SignPainter",Font.BOLD,35));
		//start.setFont(new Font(""));
		btnPanel.add(start);
		JButton exit = new JButton("Exit");
		exit.setOpaque(false);
		exit.setBorderPainted(false);
		exit.setForeground(Color.WHITE);
		//getContentPane().add(label);
		exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				exit();
			}
		});
		exit.setFont(new Font("SignPainter",Font.BOLD,35));
		exit.setBounds(screen.width*3/5, screen.height*2/3, screen.width/4, screen.height/4);
		btnPanel.add(exit);
//		setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("Castle.gif")))));
		this.setLayout(null);
		this.add(start);
		this.add(exit);
		this.add(label);
		this.getContentPane().setBackground(new Color(16, 24, 36));
//		Font myFont = new Font();
//		setUndecorated(true);
//		setLocationRelativeTo(null);
//		pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public void exit()
	{
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
	public void start()
	{
		view.setVisible(true);
		dispose();
	}
	public ChampionsSelection getView() {
		return view;
	}
	public static void main(String[] args) throws IOException {
		TriwizardTournamentView x = new TriwizardTournamentView();
	}
}
