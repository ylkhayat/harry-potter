package harrypotter.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import harrypotter.model.character.Champion;

public class Winner extends JFrame
{
	private JLabel winner;
	private JButton restart;
	private JButton exit;
	private Champion winnerChamp;
	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	public Winner(Champion c)
	{
		super();
		winnerChamp = c;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLayout(null);
		//setUndecorated(true);
		Icon icon = new ImageIcon("src/harrypotter/view/PotterLadies.gif");
		JLabel label = new JLabel();
		label.setIcon(icon);
		label.setBounds((int) (screen.getWidth()/2-icon.getIconWidth()/2),0, icon.getIconWidth(),icon.getIconHeight());
		screen.height = screen.height - 62;
		winner = new JLabel("MABROUKKKKKK  " + winnerChamp.getName(),SwingConstants.CENTER);
		winner.setForeground(Color.WHITE);
		winner.setOpaque(false);
		winner.setFont(new Font("SignPainter",Font.BOLD,70));
		restart = new JButton("Restart Tournament");
		//restart.setBounds(screen.width/6, , width, height);
		restart.setFont(new Font("SignPainter",Font.BOLD,50));
		restart.setBorderPainted(false);
		restart.setOpaque(false);
		exit = new JButton("Habibi Bye");
		exit.setFont(new Font("SignPainter",Font.BOLD,70));
		exit.setBorderPainted(false);
		exit.setOpaque(false);
		winner.setBounds(70, 70, screen.width, screen.height*3/5);
		
		restart.setBounds(screen.width/8, screen.height*4/5, screen.width/2, screen.height/5);
		exit.setBounds(screen.width*5/8, screen.height*4/5, screen.width/4, screen.height/5);
		add(winner);
		add(restart);
		add(exit);
		add(label);
		setVisible(true);
		revalidate();
	}
	public JLabel getWinner()
	{
		return winner;
	}
	public JButton getRestart()
	{
		return restart;
	}
	public JButton getExit()
	{
		return exit;
	}
/*	public static void main(String[] args) {
		Winner x = new Winner();
	}*/
}
