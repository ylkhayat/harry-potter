package harrypotter.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import harrypotter.model.character.Champion;
import harrypotter.model.magic.Spell;
import harrypotter.model.tournament.Tournament;

public class SpellsSelection extends JFrame implements ActionListener {
	
	
	FirstTaskView firstTaskView;
	LinkedList<Status> champs;
	JPanel champsPanel;
	Status firstChampStat;
	Status secondChampStat;
	Status thirdChampStat;
	Status fourthChampStat;
	JPanel spells;
	JLabel chooseSpells;
	JPanel chooses;
	JPanel DSpell;
	JLabel dSpell;
	JPanel HSpell;
	JLabel hSpell;
	JPanel RSpell;
	JLabel rSpell;
	Status currentStat;
	JButton start;
	FirstTaskView view;
	ArrayList<Champion> champions;
	public int turns = 0;
	public int spellCountEach = 0;
	private Color defaultColor;
	private JFrame startFrame;
	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	ArrayList<JButtonSpell> chosenSpells;
	JButtonSpell firstChosen;
	JButtonSpell secondChosen;
	JButtonSpell thirdChosen;
	public SpellsSelection() throws IOException{
		super();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		screen.height = screen.height - 62;
		firstChampStat = new Status();
		secondChampStat = new Status();
		thirdChampStat = new Status();
		fourthChampStat = new Status();
		champs = new LinkedList<Status>();
		chosenSpells = new ArrayList<JButtonSpell>();
		firstChosen = new JButtonSpell(null);
		secondChosen = new JButtonSpell(null);
		thirdChosen = new JButtonSpell(null);
		chosenSpells.add(firstChosen);
		chosenSpells.add(secondChosen);
		chosenSpells.add(thirdChosen);
		setLayout(null);	    
	    champsPanel = new JPanel(new GridLayout(0, 4));
	    champsPanel.setBounds(0, 0, screen.width, screen.height*5/6);
	    champs.add(firstChampStat);
	    champs.add(secondChampStat);
	    champs.add(thirdChampStat);
	    champs.add(fourthChampStat);
	    add(champsPanel);
		start = new JButton("Start Tournament");
		start.setFont(new Font( "AR BERKLEY" , Font.BOLD , 40));
		start.setBackground(Color.BLACK);
//		start.setBounds(500, screen.height*5/6, 500, 150);
		DSpell = new JPanel();
		DSpell.setLayout(new GridLayout(6, 2));
		DSpell.setBounds(0, screen.height*5/6, screen.width/3, screen.height/6);
		DSpell.setBackground(Color.RED);
		this.add(DSpell);
		HSpell = new JPanel();
		HSpell.setLayout(new GridLayout(6, 2));
		HSpell.setBounds(screen.width/3, screen.height*5/6, screen.width/3, screen.height/6);
		HSpell.setBackground(Color.ORANGE);
		this.add(HSpell);
		RSpell = new JPanel();
		RSpell.setLayout(new GridLayout(6, 2));
		RSpell.setBounds(screen.width*2/3, screen.height*5/6,  screen.width/3, screen.height/6);
		RSpell.setBackground(Color.GREEN);
		defaultColor = RSpell.getBackground();
		this.add(RSpell);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);		
	}
	public void addChamps( JPanel champ){
		
		champsPanel.add(champ);
		
	}
	public void addChampsStats(ArrayList<JPanel> stats){
	
		for ( int i = 0 ; i < stats.size(); i++)
			champsPanel.add(stats.get(i));	
	}
	public void addDmg( JButton tmp){
		DSpell.add(tmp);
	}

	public void addHs(JButton tmp)
	{
		HSpell.add(tmp);
	}
	
	public void addRs(JButton tmp){
		RSpell.add(tmp);
	}
	
	public FirstTaskView getView(){
		return firstTaskView;
	}
	
	
	public JButton getStart(){
		return start;
	}
	public JPanel getChampsPanel() {
		return champsPanel;
	}
	public void actionPerformed(ActionEvent e)
	{
//		System.out.println("Turns : " + turns);
//		System.out.println("Size : " + getChampions().size());
//		System.out.println("Spell : " + spellCountEach);
		if(turns == getChampions().size()-1 && spellCountEach == 2)
		{
			startFrame = new JFrame();
			startFrame.setLayout(null);
			start =  new JButton("Let The Tournament Begin");
			start.setOpaque(false);
			start.setBorderPainted(false);
			start.setForeground(Color.WHITE);
			//start.setVisible(false);
			start.setBounds(screen.width/3, screen.height/3, screen.width/3, screen.height/3);
			startFrame.add(start);
			startFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			startFrame.setVisible(true);
			start.addActionListener(this);
			JLabel label = new JLabel();
			Icon icon = new ImageIcon("src/harrypotter/view/ezgif.com-resize-3.gif");
			label.setIcon(icon);
			label.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
			startFrame.add(label);
			
			//start.setVisible(true);
			revalidate();
			startFrame.revalidate();
		}
		if(e.getSource() instanceof JButtonSpell && turns < champs.size())
		{
				if(spellCountEach == 2)
				{
					reset();
					spellCountEach = 0;
					turns++;
					getCurrentStat().setBackground(defaultColor);
					setCurrentStat(champs.get(turns%champs.size()));
					if(turns < champs.size())
						getCurrentStat().setBackground(Color.LIGHT_GRAY);		
				}
					else
					{	
						spellCountEach++;
						((JButtonSpell)e.getSource()).setEnabled(false);
						chosenSpells.add((JButtonSpell) e.getSource());
					}
				revalidate();
			}
	}
	public void reset()
	{
		for (int i = 0; i < chosenSpells.size(); i++) {
			chosenSpells.get(i).setEnabled(true);
		}
	}
	public void setChampsPanel(JPanel champsPanel) {
		this.champsPanel = champsPanel;
	}
	public LinkedList<Status> getChamps() {
		return champs;
	}
	public void setChamps(LinkedList<Status> champs) {
		this.champs = champs;
	}
	public Status getCurrentStat() {
		return currentStat;
	}
	public void setCurrentStat(Status currentStat) {
		this.currentStat = currentStat;
	}
	public ArrayList<Champion> getChampions() {
		return champions;
	}
	public void setChampions(ArrayList<Champion> champions) {
		this.champions = champions;
	}
	public JFrame getStartFrame() {
		return startFrame;
	}
}

