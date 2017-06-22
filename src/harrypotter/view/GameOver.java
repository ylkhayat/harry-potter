package harrypotter.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameOver extends JFrame
{

	private JButton exit;
	public GameOver()
	{
		super();
		setSize(getMaximumSize());
		setLayout(null);
		setVisible(true);
		getContentPane().setBackground(Color.DARK_GRAY);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		Icon icon = new ImageIcon("src/harrypotter/view/PotterLadies.gif");
		JLabel label = new JLabel();
		label.setIcon(icon);
		int w =(int) getToolkit().getScreenSize().getWidth();
		int h = (int) getToolkit().getScreenSize().getHeight();
		label.setBounds(w/2-icon.getIconWidth()/2,0, icon.getIconWidth(),icon.getIconHeight());
		exit = new JButton("Yalla BYYYYYYYYYEEEEEEEEEE");
		exit.setBounds(w*4/5-200, h*3/5+65, w/6, h/6);
		exit.setOpaque(false);
		exit.setBorderPainted(false);
		exit.setFont(new Font("SignPainter",Font.BOLD,70));
				
		add(exit);
		add(label);
//		label.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	

	public JButton getExit() {
		return exit;
	}


	public static void main(String[] args) 
	{
		GameOver gameOver = new GameOver();
	}
}
