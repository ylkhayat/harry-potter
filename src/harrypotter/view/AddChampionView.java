package harrypotter.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.NoRouteToHostException;

import javax.sql.rowset.WebRowSet;
import javax.swing.BoxLayout;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.plaf.basic.BasicComboBoxUI.ComboBoxLayoutManager;

public class AddChampionView extends JFrame
{
	private JTextField name;
	private Object prof;
	private JLabel label;
	private JPanel top;
	private JPanel topofTop;
	private JComboBox<Profession> profList;
	private Profession[] profs = {Profession.GRYFFINDOR, Profession.HUFFLEPUFF, Profession.RAVENCLAW, Profession.SLYTHERIN};
    private JButton add;
    private NewChampionListener listener;
  
	public AddChampionView()
	{
		super();
		this.setBounds(0, 0, 500, 200);
		top = new JPanel(new GridLayout(2,0,10,10));
		topofTop = new JPanel(new BorderLayout(5 , 5));
		label = new JLabel("  Champion Name ");
		setLayout(new GridLayout(2, 0, 10, 10));
		name = new JTextField();
//		label.setBounds(10 , 20, 50, 50);
//		name.setBounds(60, 20, 450, 50);
		topofTop.add(label, BorderLayout.WEST);
		topofTop.add(name, BorderLayout.CENTER);
		top.add(topofTop);
		profList = new JComboBox<Profession>();
//		profList.setBounds(,);
		for (Profession prof : profs)
		{
			profList.addItem(prof);
		}
		add = new JButton("Add Champion");
		add.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				if(getListener() != null)
				{
					listener.onAddingNewChampion(new NewChampion(name.getText(), (Profession)profList.getSelectedItem()));
					((ChampionsSelection)listener).revalidate();
				}
			}
		});
		top.add(profList);
		add(top);
		add(add);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	public static void main(String[] args) {
		AddChampionView tmp = new AddChampionView();
		tmp.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public NewChampionListener getListener() {
		return listener;
	}
	public void setListener(NewChampionListener listener) {
		this.listener = listener;
	}
}
