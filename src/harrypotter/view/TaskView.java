package harrypotter.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
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

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import harrypotter.controller.TriwizardTournamentGUI;
import harrypotter.model.character.Champion;
import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.magic.Spell;

abstract public class TaskView extends JFrame implements KeyListener,ActionListener
{
	private Status firstChampStats;
	private Status secondChampStats;
	private Status thirdChampStats;
	private Status fourthChampStats;
	private LinkedList<Status> champStats;
	private JPanel gameMap;
	private Color defaultColor;
	private Status currentStat;
	private Champion currentChamp;
	private JTextArea console;
	private JTextArea console1;
	public static final int LINES = 4;  
    public static final int CHAR_PER_LINE = 40; 
    private JScrollPane scrollPane;
    private JPanel[][] btnMap;
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private JButton skipTurn;
    private JPanel options;
    private JButton sound;
    private JButton help;
    private JButton exit;
	public TaskView()
	{
		super();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		options = new JPanel(new GridLayout(0, 3));
		skipTurn = new JButton("Skip my Turn");
		sound = new JButton();
		help = new JButton("Help");
		exit = new JButton("Exit");
		screen.height = screen.height - 62;
		firstChampStats = new Status();
		secondChampStats = new Status();
		thirdChampStats = new Status();
		fourthChampStats = new Status();
		btnMap = new JPanel[10][10];
		skipTurn.setBounds(screen.width/36, screen.height*4/5+screen.height/9, screen.width/5-5, screen.height/14);
		options.setBounds(screen.width*28/36,( screen.height*4/5+screen.height/9), screen.width/5-5, screen.height/14);
		options.add(sound);
		options.add(help);
		options.add(exit);
		setConsole(new JTextArea("Welcome champions.",LINES,CHAR_PER_LINE));
		getConsole().setCaretPosition(getConsole().getText().length());
		getConsole().setEditable(false);
		console1 = new JTextArea();
		console1.setEditable(false);
		getConsole().setAutoscrolls(true);
		getConsole().setColumns (20);
		getConsole().setRows(5);
		scrollPane = new JScrollPane(getConsole());
		gameMap = new JPanel(new GridLayout(10,10));
		champStats = new LinkedList<Status>();
		champStats.addLast(firstChampStats);
		champStats.addLast(secondChampStats);
		champStats.addLast(thirdChampStats);
		champStats.addLast(fourthChampStats);
		defaultColor = getChampStats().get(0).getBackground();
		setLayout(null);
//		fourthChampStats.setBounds(0, 0, screen.width/4, screen.height*2/5);
		fourthChampStats.setBounds(0, 0, screen.width/4, screen.height/2);
		fourthChampStats.setSize(screen.width/4, screen.height*2/5);
//        thirdChampStats.setBounds(screen.width*3/4, 0, screen.width/4, screen.height*2/5);
        thirdChampStats.setBounds(screen.width*3/4, 0, screen.width/4, screen.height/2);
        thirdChampStats.setSize(screen.width/4, screen.height*2/5);
//    	secondChampStats.setBounds(screen.width*3/4, screen.height*2/5, screen.width/4, screen.height*2/5);
        secondChampStats.setBounds(screen.width*3/4, screen.height/2, screen.width/4, screen.height/2);
        secondChampStats.setSize(screen.width/4, screen.height*2/5);
//    	firstChampStats.setBounds(0, screen.height*2/5, screen.width/4, screen.height*2/5);
        firstChampStats.setBounds(0, screen.height/2, screen.width/4, screen.height/2);
    	firstChampStats.setSize(screen.width/4, screen.height*2/5);
    	add(firstChampStats);
    	add(secondChampStats);
    	add(thirdChampStats);
    	add(fourthChampStats);
    	add(options);
    	add(skipTurn);
    	gameMap.setBounds(screen.width/4, 0, screen.width/2, screen.height*4/5);
    	add(gameMap);
    	scrollPane.setBounds(screen.width/4, screen.height*4/5, screen.width/2, screen.height/5);
    	add(scrollPane);
    	scrollPane.setViewportView(getConsole());
    	pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public Status getFirstChampStats() {
		return firstChampStats;
	}
	public void setFirstChampStats(Status firstChampStats) {
		this.firstChampStats = firstChampStats;
	}
	public Status getSecondChampStats() {
		return secondChampStats;
	}
	public void setSecondChampStats(Status secondChampStats) {
		this.secondChampStats = secondChampStats;
	}
	public Status getThirdChampStats() {
		return thirdChampStats;
	}
	public void setThirdChampStats(Status thirdChampStats) {
		this.thirdChampStats = thirdChampStats;
	}
	public Status getFourthChampStats() {
		return fourthChampStats;
	}
	public void setFourthChampStats(Status fourthChampStats) {
		this.fourthChampStats = fourthChampStats;
	}
	public JPanel getGameMap() {
		return gameMap;
	}
	public void setGameMap(JPanel gameMap) {
		this.gameMap = gameMap;
	}
	public void addCell(JPanel btn)
	{
		int ind = gameMap.getComponentCount();
		gameMap.add(btn);
		btnMap[ind/10][ind%10] = btn;
		btn.addKeyListener(this);
	}
	public LinkedList<Status> getChampStats() {
		return champStats;
	}
	public void onDamagingSpell(Point p)
	{
		Border redBorder = BorderFactory.createLineBorder(Color.RED,5);
		if(gameMap.contains(p.x+1,p.y))
			((JPanel) gameMap.getComponentAt(new Point(p.x+1,p.y))).setBorder(redBorder);
		if(gameMap.contains(p.x-1,p.y))
			((JPanel) gameMap.getComponentAt(new Point(p.x-1,p.y))).setBorder(redBorder);
		if(gameMap.contains(p.x,p.y+1))
			((JPanel) gameMap.getComponentAt(new Point(p.x,p.y+1))).setBorder(redBorder);
		if(gameMap.contains(p.x,p.y-1))
			((JPanel) gameMap.getComponentAt(new Point(p.x,p.y-1))).setBorder(redBorder);
		revalidate();
	}
	public void updateStat(int row, int nrow, int col, int ncol, boolean trait) throws InterruptedException
	{
//		JPanel tmpBefore = (JPanel) gameMap.getComponent(row*10+col);
////		JPanel tmpAfter = (JPanel) gameMap.getComponent(nrow*10+ncol);
//		JPanel newtmpBefore = new JPanel();
//		newtmpBefore.setBackground(tmpBefore.getBackground());
//		JPanel newtmpAfter = new JPanel();
//		newtmpAfter.setBackground(Color.WHITE);
//		gameMap.remove(row*10+col);
//		gameMap.add(newtmpAfter, row*10+col);
//		gameMap.remove(nrow*10+ncol);
//		gameMap.add(newtmpBefore, nrow*10+ncol);
		if(trait == false)
		{
			getCurrentStat().setBackground(defaultColor);
			setCurrentStat(champStats.get((champStats.indexOf(getCurrentStat()) + 1)%getChampStats().size()));
			getCurrentStat().setBackground(Color.LIGHT_GRAY);
		}
		revalidate();
	}
	public void updateStat(boolean trait) throws InterruptedException
	{
//		JPanel tmpBefore = (JPanel) gameMap.getComponent(row*10+col);
////		JPanel tmpAfter = (JPanel) gameMap.getComponent(nrow*10+ncol);
//		JPanel newtmpBefore = new JPanel();
//		newtmpBefore.setBackground(tmpBefore.getBackground());
//		JPanel newtmpAfter = new JPanel();
//		newtmpAfter.setBackground(Color.WHITE);
//		gameMap.remove(row*10+col);
//		gameMap.add(newtmpAfter, row*10+col);
//		gameMap.remove(nrow*10+ncol);
//		gameMap.add(newtmpBefore, nrow*10+ncol);
		if(trait == false)
		{
			getCurrentStat().setBackground(defaultColor);
			setCurrentStat(champStats.get((champStats.indexOf(getCurrentStat()) + 1)%getChampStats().size()));
			getCurrentStat().setBackground(Color.LIGHT_GRAY);
		}
		revalidate();
	}
	public Status getCurrentStat() {
		return currentStat;
	}
	public void setCurrentStat(Status current) {
		this.currentStat = current;
	}
	public void keyPressed(KeyEvent e)
	{}
	public void keyReleased(KeyEvent arg0){}
	public void keyTyped(KeyEvent arg0) {}
	public JTextArea getConsole() {
		return console;
	}
	public void fire(ArrayList<Point> arrayList) throws InterruptedException
	{
//		Color firstMarkedCell = gameMap.getComponent(arrayList.get(0).x*10+arrayList.get(0).y).getBackground();
//		Color secondMarkedCell = gameMap.getComponent(arrayList.get(1).x*10+arrayList.get(1).y).getBackground();
//		gameMap.getComponent(arrayList.get(0).x*10+arrayList.get(0).y).setBackground(Color.RED);
//		gameMap.getComponent(arrayList.get(1).x*10+arrayList.get(1).y).setBackground(Color.RED);
//		revalidate();
//		stopWatch(0.5);
//		gameMap.getComponent(arrayList.get(0).x*10+arrayList.get(0).y).setBackground(firstMarkedCell);
//		gameMap.getComponent(arrayList.get(1).x*10+arrayList.get(1).y).setBackground(secondMarkedCell);
//		revalidate();
	}
	public void setConsole(JTextArea console) {
		this.console = console;
	}
	public Champion getCurrentChamp() {
		return currentChamp;
	}
	public void setCurrentChamp(Champion currentChamp) {
		this.currentChamp = currentChamp;
	}
	public void setChampStats(LinkedList<Status> champStats) {
		this.champStats = champStats;
	}
	public void initializeStats(JPanel jPanel, TriwizardTournamentGUI t)
	{
		int i = 0;
		Status currentChangingStat;
		JButtonSpell btnSpell;
		Status stat;
		for (;i < jPanel.getComponentCount();)
		{
			stat = (Status) jPanel.getComponent(i);
			currentChangingStat = getChampStats().get(i);
			currentChangingStat.setName(stat.getName());
			currentChangingStat.setChampHP(stat.getChampHP());
			currentChangingStat.setChampIP(stat.getChampIP());
			currentChangingStat.setChampCooldown(stat.getChampCooldown().getText());
			currentChangingStat.setChamp(stat.getChamp());
			for (int j = 0; j < stat.getChampSpells().getComponentCount();j++)
			{
				btnSpell = ((JPanelSpell)stat.getChampSpells().getComponent(j)).getBtnSpell();
				btnSpell.setText(btnSpell.getSpell().getName());
//				 + " | " + btnSpell.getSpell().getCoolDown()
				btnSpell.setEnabled(false);
				//btnSpell.setToolTipText(text);
				currentChangingStat.getChampSpells().add(btnSpell);
			}
			currentChangingStat.traitDescription(stat.getChamp());
			currentChangingStat.getUseTrait().addActionListener(t);
			i++;
			currentChangingStat.updateUI();
			
		}
		for(;i < getChampStats().size();)
		{
			currentChangingStat = getChampStats().get(i);
			getChampStats().remove(currentChangingStat);
			remove(currentChangingStat);
		}
		revalidate();
	}

	public void initializeStats(ArrayList<Champion> list, TriwizardTournamentGUI t)
	{
		int i = 0;
		Status currentChangingStat;
//		JPanelSpell pnlSpell;
		Status stat; 		
		for (Champion champ : list)
		{
			stat = getChampStats().get(i);
			if(champ instanceof GryffindorWizard)
			{
				stat.setName(champ.getName()+ "(G)");
				stat.setChampHP("HP: "+champ.getHp()+"",champ.getHp()+"");
				stat.setChampIP("IP: "+champ.getIp()+"",champ.getIp()+"");
				stat.setChampCooldownValue(champ.getTraitCooldown()+"");
				stat.traitDescription(champ);
			}
			else
				if(champ instanceof HufflepuffWizard)
				{
					stat.setName(champ.getName()+ "(H)");
					stat.setChampHP("HP: "+champ.getHp()+"",champ.getHp()+"");
					stat.setChampIP("IP: "+champ.getIp()+"",champ.getIp()+"");
					stat.setChampCooldownValue(champ.getTraitCooldown()+"");
					stat.traitDescription(champ);
				}
				else
					if(champ instanceof RavenclawWizard)
					{
						stat.setName(champ.getName()+ "(R)");
						stat.setChampHP("HP: "+champ.getHp()+"",champ.getHp()+"");
						stat.setChampIP("IP: "+champ.getIp()+"",champ.getIp()+"");
						stat.setChampCooldownValue(champ.getTraitCooldown()+"");
						stat.traitDescription(champ);
					}
					else
					{
						stat.setName(champ.getName()+ "(S)");
						stat.setChampHP("HP: "+champ.getHp()+"",champ.getHp()+"");
						stat.setChampIP("IP:" +champ.getIp()+"",champ.getIp()+"");
						stat.setChampCooldownValue(champ.getTraitCooldown()+"");
						stat.traitDescription(champ);
					}
			stat.setChamp(champ);
			currentChangingStat = getChampStats().get(i);
//			for(int j = 0;j < stat.getChampSpells().getComponentCount();j++)
//			{
//				currentChangingStat.getChampSpells().add(stat.getChampSpells().getComponent(j));
//			}
			for(Spell spell:champ.getSpells())
			{
				JButtonSpell btnSpell = new JButtonSpell(spell);
				btnSpell.setText(spell.getName());
				btnSpell.setEnabled(false);
				stat.getChampSpells().add(btnSpell);
			}
			stat.traitDescription(champ);
			stat.getUseTrait().addKeyListener(t);
			stat.getUseTrait().addActionListener(t);
			i++;
		}
		for(;i < getChampStats().size();)
		{
			currentChangingStat = getChampStats().get(i);
			getChampStats().remove(currentChangingStat);
			remove(currentChangingStat);
		}
		revalidate();
	}
	public JPanel getOptions() {
		return options;
	}
	public JButton getSound() {
		return sound;
	}
	public JButton getHelp() {
		return help;
	}
	public JButton getExit() {
		return exit;
	}
	public void stopWatch(double time)
	{
		long t = System.currentTimeMillis();
		while(System.currentTimeMillis() < t + time*1000);
	}
	
	public JButton getSkipTurn() {
		return skipTurn;
	}
//	public static void main(String[] args) throws IOException {
//		FirstTaskView t = new FirstTaskView();
//		BufferedImage myPicture = ImageIO.read(new File("C:\\Users\\User\\Desktop\\rock.jpg"));
//	//	myPicture.getScaledInstance(200, 400, 500);
//		int  heightOfPanel = t.getGameMap().getHeight();
//	 int widthOfPanel = t.getGameMap().getWidth();
//		JLabel picLabel = new JLabel();//(new ImageIcon(myPicture));
//	//	t.getGameMap().setBackground(Color.black);
//		t.getGameMap().setLayout(null);
//	//	picLabel.setBackground(Color.red);
//		picLabel.setBounds(0,0,widthOfPanel, heightOfPanel);
//		t.getGameMap().add(picLabel);
//		picLabel.setVisible(true);
//		System.out.println(t.getGameMap().getBounds());
//		t.revalidate();
//		t.setVisible(true);
//		t.getGameMap().setBackground(Color.black);
//		JLabel ha = new JLabel(new ImageIcon(myPicture));
//		ha.setBounds(0,-10 ,960, 820);
//		
//		t.getGameMap().add(ha);
//				
//				
//		
//	}
}
