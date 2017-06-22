
package harrypotter.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BrokenBarrierException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import harrypotter.model.magic.HealingSpell;

public class JPanelSpell extends JPanel 
//implements ActionListener
{
	JButtonSpell btnSpell;
	JButton refresh;
	JLabel label;
	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	public JPanelSpell(JButtonSpell spell)
	{
		super();
		btnSpell = spell;
		label = new JLabel(spell.getSpell().getName());
		setLayout(new GridLayout(0,1));	
		add(btnSpell);
		refresh = new JButton("Refresh");
		refresh.setBackground(Color.GRAY);
		label.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
//		btnSpell.setSize(new Dimension(112,158));
	}
	public JButtonSpell getBtnSpell() {
		return btnSpell;
	}
	public void setBtnSpell(JButtonSpell btnSpell) {
		this.btnSpell = btnSpell;
	}
	public JLabel getLabel() {
		return label;
	}
	public void setLabel(JLabel label) {
		this.label = label;
	}
	public JButton getRefresh() {
		return refresh;
	}
	public void setRefresh(JButton refresh) {
		this.refresh = refresh;
	}
}