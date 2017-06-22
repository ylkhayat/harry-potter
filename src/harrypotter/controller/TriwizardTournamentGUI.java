package harrypotter.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

import javax.annotation.Generated;
import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.OverlayLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import harrypotter.model.character.Champion;
import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.character.SlytherinWizard;
import harrypotter.model.magic.Collectible;
import harrypotter.model.magic.DamagingSpell;
import harrypotter.model.magic.HealingSpell;
import harrypotter.model.magic.Potion;
import harrypotter.model.magic.RelocatingSpell;
import harrypotter.model.magic.Spell;
import harrypotter.model.tournament.FirstTask;
import harrypotter.model.tournament.SecondTask;
import harrypotter.model.tournament.Task;
import harrypotter.model.tournament.ThirdTask;
import harrypotter.model.tournament.Tournament;
import harrypotter.model.world.Cell;
import harrypotter.model.world.ChampionCell;
import harrypotter.model.world.CollectibleCell;
import harrypotter.model.world.CupCell;
import harrypotter.model.world.Direction;
import harrypotter.model.world.EmptyCell;
import harrypotter.model.world.ObstacleCell;
import harrypotter.model.world.TreasureCell;
import harrypotter.model.world.WallCell;
import harrypotter.view.ChampionsSelection;
import harrypotter.view.FirstTaskView;
import harrypotter.view.GameOver;
import harrypotter.view.JButtonSpell;
import harrypotter.view.JPanelSpell;
import harrypotter.view.SecondTaskView;
import harrypotter.view.SpellsSelection;
import harrypotter.view.Status;
import harrypotter.view.TaskView;
import harrypotter.view.ThirdTaskView;
import harrypotter.view.TriwizardTournamentView;
import harrypotter.view.Winner;

public class TriwizardTournamentGUI implements ActionListener, KeyListener, MouseListener {
	private TriwizardTournamentView ttournamentView;
	private ChampionsSelection champSelectView;
	private SpellsSelection spellsSelectView;
	private FirstTaskView firstTaskView;
	private FirstTask firstTask;
	private SecondTaskView secondTaskView;
	private SecondTask secondTask;
	private ThirdTaskView thirdTaskView;
	private ThirdTask thirdTask;
	private Tournament tournament;
	private GameOver gameOver;
	private Color defaultColor;
	private Champion currentChamp;
	private Task currentTask;
	private TaskView currentView;
	private int currentChampRow;
	private int currentChampCol;
	private Spell spell;
	private boolean relocatingSpellFlagLocat;
	private boolean relocatingSpellFlagDest;
	private Direction firstDirection;
	private Direction secondDirection;
	private boolean flagSlytherin;
	private int range;
	private boolean damagingSpellFlag;
	private int IndOfDmgSpell;
	private JPanel champsPanel;
	private Status firstChampStats;
	private Status secondChampStats;
	private Status thirdChampStats;
	private Status fourthChampStats;
	private LinkedList<Status> champStats;
	private JButton start;
	private boolean onFirstTask;
	private boolean onSecondTask;
	private boolean onThirdTask;
	private boolean isHufflepuff;
	private Winner winner;
	int indexOfSpell;
	Boolean clickedOnRS = false;
	boolean hasclicked1 = false;
	JPanel click1Panel = null;
	JPanel click2Panel = null;
	String click1Panelname;
	String click2Panelname;
	String CellNo;
	int rangeOfSpell;
	int champPosX;
	int champPosY;
	int posOfTarget;
	int CellOfChamp;
	int finalRange;
	Direction d;
	Direction t;
	int diffChampD; // difference between champ and direction d
	String CellOfD;
	int diffChampT;
	int diffOfRanges;
	Color defaultSpellColor = Color.WHITE;
	Stack<JButtonSpell> castedSpells;
	Sound sound;

	public TriwizardTournamentGUI() throws Exception {
		tournament = new Tournament();
		firstChampStats = new Status();
		secondChampStats = new Status();
		thirdChampStats = new Status();
		fourthChampStats = new Status();
		champsPanel = new JPanel(new GridLayout(0, 1));
		champStats = new LinkedList<Status>();
		champStats.add(firstChampStats);
		champStats.add(secondChampStats);
		champStats.add(thirdChampStats);
		champStats.add(fourthChampStats);
		ttournamentView = new TriwizardTournamentView();
		champSelectView = ttournamentView.getView();
		champSelectView.setListener(tournament);
		champSelectView.getChooseSpells().addActionListener(this);
		castedSpells = new Stack<JButtonSpell>();
	}

	public void actionPerformed(ActionEvent e) {
		Champion c = null;
		if (currentTask != null)
			c = currentTask.getCurrentChamp();
		if (e.getSource().equals((champSelectView).getChooseSpells())) {
			try {
				tournament.beginTournament();
				firstTask = tournament.getFirstTask();
				onFirstTask = true;
				// System.out.println(tournament.getFirstTask().getChampions().toString());
				// firstTask.getCurrentChamp().setHp(6456545);
				// firstTask.getChampions().get(1).setHp(6456465);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			ArrayList<Champion> champs = tournament.getFirstTask().getChampions();
			ArrayList<Spell> spells = tournament.getSpells();
			spellsSelectView = champSelectView.getView();
			constructStats();
			champSelectView.dispose();
			spellsSelectView.requestFocus();
			spellsSelectView.getStart().addActionListener(this);
			initStats(getChampStats());
			sortSpells(spells);
			spellsSelectView.setCurrentStat(getChampStats().get(0));
			spellsSelectView.getCurrentStat().setBackground(Color.LIGHT_GRAY);
			spellsSelectView.setChampions(champs);
			spellsSelectView.revalidate();
			spellsSelectView = champSelectView.getView();
			currentTask = firstTask;
			currentChamp = firstTask.getCurrentChamp();
			// System.out.println(currentChamp.toString());
			currentChampRow = currentChamp.getLocation().x;
			currentChampCol = currentChamp.getLocation().y;
		} else if (spellsSelectView.isFocusable() && ((Component) e.getSource()).getParent().getParent()
				.equals(spellsSelectView.getCurrentStat().getChampSpells())) {
			removeSpell(spellsSelectView.getCurrentStat(), tournament.getChampions().get(spellsSelectView.turns),
					(JPanelSpell) (((Component) e.getSource()).getParent()));
			spellsSelectView.revalidate();
			spellsSelectView.repaint();
		} else if (e.getSource() instanceof JButtonSpell && spellsSelectView.isFocusable()
				&& spellsSelectView.turns < spellsSelectView.getChampions().size()) {

			addSpell(spellsSelectView.getCurrentStat(), tournament.getChampions().get(spellsSelectView.turns),
					(JButtonSpell) e.getSource());
			spellsSelectView.actionPerformed(e);
			spellsSelectView.getStart().addActionListener(this); // zebala
			setStart(spellsSelectView.getStart());
		} else if (spellsSelectView.getStart() != null && e.getSource().equals(getStart())) {
			spellsSelectView.getStartFrame().dispose();
			firstTaskView = new FirstTaskView();
			setCurrentView(firstTaskView);
			getCurrentView().getExit().addActionListener(this);
			getCurrentView().getHelp().addActionListener(this);
			getCurrentView().getSound().addActionListener(this);
			getCurrentView().getSkipTurn().addActionListener(this);
			getCurrentView().initializeStats(spellsSelectView.getChampsPanel(), this);

			try {
				loadMap();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			getCurrentView().addKeyListener(this);
			defaultColor = getCurrentView().getChampStats().get(0).getBackground();
			getCurrentView().setCurrentStat(getCurrentView().getChampStats().getFirst());
			getCurrentView().setCurrentChamp(currentTask.getCurrentChamp());
			getCurrentView().getCurrentStat().setBackground(Color.LIGHT_GRAY);
			getCurrentView().setVisible(true);
			getCurrentView().revalidate();
			getCurrentView().requestFocus();
			spellsSelectView.dispose();
			JLabel label;
			try {
				sound = new Sound("src/harrypotter.wav");
				getCurrentView().getSound().removeAll();
				label = new JLabel("ON");
				label.setBounds(15, 0, getCurrentView().getSound().getWidth(), getCurrentView().getSound().getHeight());
				label.setFont(new Font("SignPainter", Font.BOLD, 25));
				getCurrentView().getSound().add(label);
			} catch (InvocationTargetException | LineUnavailableException | UnsupportedAudioFileException | IOException
					| InterruptedException e2) {
				e2.printStackTrace();
			}
		} else if (getCurrentView() != null && e.getSource().equals(getCurrentView().getCurrentStat().getUseTrait())) {

			if (c != null && c instanceof SlytherinWizard) {
				flagSlytherin = true;
				getCurrentView().requestFocus();
			} else if (c != null && c instanceof RavenclawWizard)

				try {
					if (getCurrentTask() instanceof FirstTask) {
						c.useTrait();

						ArrayList<Point> marked = firstTask.getMarkedCells();
						Point p1 = null;
						Point p2 = null;
						JLabel label1 = new JLabel();
						JLabel label2 = new JLabel();
						Icon icon = new ImageIcon("src/harrypotter/view/baguette.gif");
						label1.setIcon(icon);
						label2.setIcon(new ImageIcon("src/harrypotter/view/baguette.gif"));

						if (marked.size() > 0)
							p1 = marked.get(0);
						if (marked.size() > 1)
							p2 = marked.get(1);
						if (marked.size() > 0) {
							// ((JPanel)
							// firstTaskView.getGameMap().getComponent(p1.x * 10
							// + p1.y)).removeAll();
							((JPanel) firstTaskView.getGameMap().getComponent(p1.x * 10 + p1.y)).add(label1, 0);
							// ((JComponent)
							// firstTaskView.getGameMap().getComponent(p1.x * 10
							// + p1.y))
							// .setBorder(BorderFactory.createLineBorder(new
							// Color(44, 36, 84), 5));
							// (Color.YELLOW);
						}
						if (marked.size() > 1) {
							// ((JPanel)
							// firstTaskView.getGameMap().getComponent(p2.x * 10
							// + p2.y)).removeAll();
							((JPanel) firstTaskView.getGameMap().getComponent(p2.x * 10 + p2.y)).add(label2, 0);
							// ((JComponent)
							// firstTaskView.getGameMap().getComponent(p2.x * 10
							// + p2.y))
							// .setBorder(BorderFactory.createLineBorder(new
							// Color(44, 36, 84), 5));}
							firstTaskView.revalidate();
							firstTaskView.repaint();

							// TimeUnit.SECONDS.sleep(1);
							// refreshMap();

							getCurrentView().requestFocus();
						}
					}
					if (getCurrentTask() instanceof SecondTask || getCurrentTask() instanceof ThirdTask) {
						c.useTrait();
						ArrayList<Direction> hint = new ArrayList<>();
						if (getCurrentTask() instanceof SecondTask)
							hint = (ArrayList<Direction>) ((SecondTask) getCurrentTask()).onRavenclawTrait();
						else
							hint = ((ThirdTask) getCurrentTask())
									.getCupCell(((ThirdTask) getCurrentTask()).getCupPoint());
						int i = getCurrentTask().getCurrentChamp().getLocation().x;
						int j = getCurrentTask().getCurrentChamp().getLocation().y;
						int d1 = 0;
						int d2 = 0;
						int counter;
						int reps;
						JLabel label = new JLabel();

						JPanel panel = null;
						if (hint.contains(Direction.RIGHT)) {
							System.out.println("dakhlalt right");
							counter = i * 10 + j + 1;
							reps = 10 - counter % 10;
							while (reps > 0) {
								// System.out.println(counter);
								label = new JLabel();
								label.setIcon(new ImageIcon("src/harrypotter/view/baguette.gif"));

								panel = (JPanel) getCurrentView().getGameMap().getComponent(counter);
								panel.setLayout(new OverlayLayout(panel));
								panel.add(label, 0);
								// panel.setBorder(BorderFactory.createLineBorder(new
								// Color(44, 36, 84), 5));
								counter++;
								reps--;
							}

						}
						if (hint.contains(Direction.LEFT)) {
							// System.out.println("dakahlt left");
							counter = i * 10 + j - 1;
							reps = counter % 10;
							while (reps >= 0) {
								label = new JLabel();
								label.setIcon(new ImageIcon("src/harrypotter/view/baguette.gif"));
								panel = (JPanel) getCurrentView().getGameMap().getComponent(counter);
								panel.setLayout(new OverlayLayout(panel));
								panel.add(label, 0);
								// panel.setBorder(BorderFactory.createLineBorder(new
								// Color(44, 36, 84), 5));
								counter--;
								reps--;
							}
						}
						if (hint.contains(Direction.BACKWARD)) {
							counter = i * 10 + j;
							// System.out.println("dakhalt backward");
							while (counter < 100) {
								label = new JLabel();
								label.setIcon(new ImageIcon("src/harrypotter/view/baguette.gif"));

								panel = (JPanel) getCurrentView().getGameMap().getComponent(counter);
								panel.setLayout(new OverlayLayout(panel));
								panel.add(label, 0);
								// panel.setBorder(BorderFactory.createLineBorder(new
								// Color(44, 36, 84), 5));
								counter = counter + 10;
							}
						}
						if (hint.contains(Direction.FORWARD)) {
							counter = i * 10 + j - 10;
							System.out.println("dakhalt forward");
							// System.out.println(counter);
							while (counter >= 0) {

								label = new JLabel();
								label.setIcon(new ImageIcon("src/harrypotter/view/baguette.gif"));
								panel = (JPanel) getCurrentView().getGameMap().getComponent(counter);
								panel.setLayout(new OverlayLayout(panel));
								panel.add(label, 0);
								// panel.setBorder(BorderFactory.createLineBorder(new
								// Color(44, 36, 84), 5));
								counter = counter - 10;
							}
						}
						getCurrentView().revalidate();
						/*
						 * d1=1; else d1=-1;
						 * if(hint.contains(Direction.BACKWARD)) d2= 10; else
						 * d2=-10;
						 */

					}
				} catch (Exception e1) {
					getCurrentView().getConsole().append("\n" + e1.getMessage());
				}

			else
				try {
					c.useTrait();
					getCurrentView().getConsole().append(c.getName() + "   You have used your Trait");
					if (c instanceof HufflepuffWizard)
						isHufflepuff = true;
				} catch (Exception e1) {
					currentView.getConsole().append(e1.getMessage());
				}

		} else

		if (getCurrentView() != null && ((Component) e.getSource()).getParent()
				.equals(getCurrentView().getCurrentStat().getChampInventory())) {
			int ind = getCurrentView().getCurrentStat().getChampInventory()
					.getComponentZOrder((Component) e.getSource());
			currentTask.usePotion((Potion) currentTask.getCurrentChamp().getInventory().get(ind));
			// System.out.println(getCurrentView().getCurrentStat().getChampInventory().getComponentCount());
			getCurrentView().getCurrentStat().getChampInventory().remove(ind);
			updateInventory(getCurrentView().getCurrentStat(), currentChamp);
			// System.out.println(getCurrentView().getCurrentStat().getChampInventory().getComponentCount());
			updateStat(getCurrentView().getCurrentStat(), getCurrentView().getChampStats(), c);
			getCurrentView().getCurrentStat().updateUI();
			getCurrentView().requestFocus();
		} else if (getCurrentView().getCurrentStat() != null && ((Component) e.getSource()).getParent().getParent()
				.equals(getCurrentView().getCurrentStat().getChampSpells())) {
			int ind = getCurrentView().getCurrentStat().getChampSpells()
					.getComponentZOrder(((Component) e.getSource()).getParent());
			// int r = ((JPanelSpell)
			// getCurrentView().getCurrentStat().getChampSpells().getComponent(ind)).getRange()
			// .getComponentZOrder((Component) e.getSource());
			// range = r;
			// System.out.println(range);
		} else if (e.getSource().equals(getCurrentView().getSkipTurn())) {
			try {
				currentTask.finalizeAction();
				updateStat(getCurrentView().getCurrentStat(), getCurrentView().getChampStats(), c);
				getCurrentView().updateStat(currentTask.isTraitActivated());
				getCurrentView().setCurrentChamp(currentTask.getCurrentChamp());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource().equals(getCurrentView().getExit()))
			getCurrentView().dispose();
		else if (e.getSource().equals(getCurrentView().getSound())) {
			JLabel label;
			if (sound == null) {
				try {
					sound = new Sound("src/harrypotter.wav");
					getCurrentView().getSound().removeAll();
					label = new JLabel("ON");
					label.setBounds(15, 0, getCurrentView().getSound().getWidth(),
							getCurrentView().getSound().getHeight());
					label.setFont(new Font("SignPainter", Font.BOLD, 25));
					getCurrentView().getSound().add(label);
				} catch (InvocationTargetException | LineUnavailableException | UnsupportedAudioFileException
						| IOException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				getCurrentView().getSound().removeAll();
				label = new JLabel("OFF");
				label.setBounds(15, 0, getCurrentView().getSound().getWidth(), getCurrentView().getSound().getHeight());
				label.setFont(new Font("SignPainter", Font.BOLD, 25));
				getCurrentView().getSound().add(label);
				sound.stop();
				sound = null;
			}
		} else if (e.getSource().equals(getCurrentView().getHelp())) {
		}

		if (onFirstTask && tournament.getSecondTask() != null) {
			onFirstTask = false;
			onSecondTask = true;
			setCurrentTask(tournament.getSecondTask());
			secondTask = tournament.getSecondTask();
			secondTaskView = new SecondTaskView();
			secondTaskView.requestFocus();
			secondTaskView.revalidate();
			
			getCurrentView().getConsole().append("The Winners of " + getCurrentTask().getClass() + " are :" );
			for (int i = 0; i < getCurrentTask().getChampions().size(); i++) {
				getCurrentView().getConsole().append( "\n" + getCurrentTask().getChampions().get(i).getName()); }
			
			getCurrentView().dispose();
			setCurrentView(secondTaskView);
			getCurrentView().getExit().addActionListener(this);
			getCurrentView().getHelp().addActionListener(this);
			getCurrentView().getSound().addActionListener(this);
			getCurrentView().getSkipTurn().addActionListener(this);
			getCurrentView().initializeStats(currentTask.getChampions(), this);
			currentChamp = currentTask.getCurrentChamp();
			// System.out.println(currentChamp.toString());
			currentChampRow = currentChamp.getLocation().x;
			currentChampCol = currentChamp.getLocation().y;
			loadMap();
			/*
			 * for (Cell[] tmpArray : tournament.getSecondTask().getMap()) { for
			 * (Cell tmp : tmpArray) { JPanel btn = new JPanel(); if (tmp
			 * instanceof ObstacleCell) btn.setBackground(Color.BLACK); else if
			 * (tmp instanceof ChampionCell) { btn.add(new
			 * JLabel(((ChampionCell) tmp).getChamp().getName()),
			 * SwingConstants.CENTER); btn.setBackground(Color.BLUE); } else if
			 * (tmp instanceof EmptyCell) btn.setBackground(Color.WHITE); else
			 * if (tmp instanceof CollectibleCell)
			 * btn.setBackground(Color.GREEN); getCurrentView().addCell(btn); }
			 * }
			 */
			getCurrentView().addKeyListener(this);
			getCurrentView().addMouseListener(this);
			defaultColor = getCurrentView().getChampStats().get(0).getBackground();
			getCurrentView().setCurrentStat(getCurrentView().getChampStats().getFirst());
			getCurrentView().setCurrentChamp(currentTask.getCurrentChamp());
			getCurrentView().getCurrentStat().setBackground(Color.LIGHT_GRAY);
			;
			getCurrentView().revalidate();
			getCurrentView().requestFocus();
		} else if (!onFirstTask && onSecondTask && tournament.getThirdTask() != null) {
			onSecondTask = false;
			onThirdTask = true;
			setCurrentTask(tournament.getThirdTask());
			thirdTaskView = new ThirdTaskView();
			getCurrentView().getConsole().append("The Winners of " + getCurrentTask().getClass() + " are :" );
			for (int i = 0; i < getCurrentTask().getChampions().size(); i++) {
				getCurrentView().getConsole().append( "\n" + getCurrentTask().getChampions().get(i).getName()); }
			thirdTaskView.requestFocus();

			// secondTaskView.removeFocusListener((FocusListener) this);
			// secondTaskView.removeContainerListener((ContainerListener) this);
			thirdTaskView.addKeyListener(this);
			getCurrentView().dispose();
			setCurrentView(thirdTaskView);
			currentChamp = currentTask.getCurrentChamp();
			setCurrentView(thirdTaskView);
			getCurrentView().getExit().addActionListener(this);
			getCurrentView().getHelp().addActionListener(this);
			getCurrentView().getSound().addActionListener(this);
			getCurrentView().getSkipTurn().addActionListener(this);
			getCurrentView().initializeStats(currentTask.getChampions(), this);
			currentChamp = currentTask.getCurrentChamp();
			// System.out.println(currentChamp.toString());
			currentChampRow = currentChamp.getLocation().x;
			currentChampCol = currentChamp.getLocation().y;
			try {
				loadMap();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			getCurrentView().addKeyListener(this);
			getCurrentView().addMouseListener(this);
			defaultColor = getCurrentView().getChampStats().get(0).getBackground();
			getCurrentView().setCurrentStat(getCurrentView().getChampStats().getFirst());
			getCurrentView().setCurrentChamp(currentTask.getCurrentChamp());
			getCurrentView().getCurrentStat().setBackground(Color.LIGHT_GRAY);
			;
			getCurrentView().revalidate();
			getCurrentView().requestFocus();
		} else if (gameOver != null && e.getSource().equals(gameOver.getExit()))
			gameOver.dispose();
		else if (winner != null && e.getSource().equals(winner.getRestart())) {
			winner.dispose();
			try {
				TriwizardTournamentView ttv = new TriwizardTournamentView();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if (winner != null && e.getSource().equals(winner.getExit())) {
			winner.dispose();
		}
		if (getCurrentView() != null) {
			currentChamp = currentTask.getCurrentChamp();
			getCurrentView().requestFocus();
		}
	}

	public void initStats(LinkedList<Status> champsStatList) {
		int i = 0;
		Status current;
		for (i = 0; i < champsStatList.size(); i++) {
			current = champsStatList.get(i);
			current.remove(current.getUseTrait());
			current.remove(current.getChampCooldown());
			current.remove(current.getChampInventory());
			spellsSelectView.getChampsPanel().add(current);
		}
		spellsSelectView.setCurrentStat(champsStatList.get(0));
	}

	public void constructStats() {
		int i = 0;
		Status stat;
		for (Champion champ : tournament.getChampions()) {
			stat = getChampStats().get(i);
			if (champ instanceof GryffindorWizard) {

				stat.getIcon().setBackground(new Color(139, 0, 0));

				stat.setName(champ.getName() + "(G)");
				stat.setChampHP(champ.getHp() + "", champ.getHp() + "");
				stat.setChampIP(champ.getIp() + "", champ.getIp() + "");
				stat.setChampCooldownValue(champ.getTraitCooldown() + "");

				ImageIcon bg = new ImageIcon("src/harrypotter/view/gryf.jpg");
				JLabel b = new JLabel();
				b.setIcon(bg);

				if (champ.getName().equals("Harry Potter")) {
					ImageIcon ic = new ImageIcon("src/harrypotter/view/harry3adi.jpg");

					JLabel jl = new JLabel();
					jl.setIcon(ic);
					stat.getIcon().add(jl);

				}

				if (champ.getName().equals("Hermione Granger")) {
					ImageIcon ic = new ImageIcon("src/harrypotter/view/herm3adi.jpg");

					JLabel jl = new JLabel();
					jl.setIcon(ic);
					stat.getIcon().add(jl);

				}
				if (champ.getName().equals("Ron Weasley")) {
					ImageIcon ic = new ImageIcon("src/harrypotter/view/ron3adi.jpg");
					JLabel jl = new JLabel();
					jl.setIcon(ic);
					stat.getIcon().add(jl);
				}
				stat.getIcon().add(b);

			} else if (champ instanceof HufflepuffWizard) {
				stat.getIcon().setBackground(new Color(255, 219, 88));

				ImageIcon bg = new ImageIcon("src/harrypotter/view/huff.jpg");
				JLabel b = new JLabel();
				b.setIcon(bg);

				stat.setName(champ.getName() + "(H)");
				stat.setChampHP(champ.getHp() + "", champ.getHp() + "");
				stat.setChampIP(champ.getIp() + "", champ.getIp() + "");
				stat.setChampCooldownValue(champ.getTraitCooldown() + "");

				if (champ.getName().equals("Cedric Diggory")) {

					JLabel c = new JLabel();
					c.setIcon(new ImageIcon("src/harrypotter/view/cedric3adi.jpg"));
					stat.getIcon().add(c);

				}
				if(champ.getName().equals("Pomona Sprout")){
					JLabel c = new JLabel();
					c.setIcon(new ImageIcon("src/harrypotter/view/pom3adi.jpg"));
					stat.getIcon().add(c);
				}

				stat.getIcon().add(b);
			} else if (champ instanceof RavenclawWizard) {

				stat.getIcon().setBackground(new Color(41, 38, 78));

				ImageIcon bg = new ImageIcon("src/harrypotter/view/raven.jpg");
				JLabel b = new JLabel();
				b.setIcon(bg);

				stat.setName(champ.getName() + "(R)");
				stat.setChampHP(champ.getHp() + "", champ.getHp() + "");
				stat.setChampIP(champ.getIp() + "", champ.getIp() + "");
				stat.setChampCooldownValue(champ.getTraitCooldown() + "");

				if (champ.getName().equals("Cho Chang")) {
					JLabel c = new JLabel();
					c.setIcon(new ImageIcon("src/harrypotter/view/cho3adi.jpg"));
					stat.getIcon().add(c);
				}
				if( champ.getName().equals("Luna Lovegood")){
					JLabel c = new JLabel();
					c.setIcon(new ImageIcon("src/harrypotter/view/luna3adi.jpg"));
					stat.getIcon().add(c);
				}

				stat.getIcon().add(b);
			} else {

				stat.getIcon().setBackground(new Color(1, 50, 32));

				ImageIcon bg = new ImageIcon("src/harrypotter/view/sly.jpg");
				JLabel b = new JLabel();
				b.setIcon(bg);

				stat.setName(champ.getName() + "(S)");
				stat.setChampHP(champ.getHp() + "", champ.getHp() + "");
				stat.setChampIP(champ.getIp() + "", champ.getIp() + "");
				stat.setChampCooldownValue(champ.getTraitCooldown() + "");

				if (champ.getName().equals("Draco Malfoy")) {
					JLabel c = new JLabel();
					c.setIcon(new ImageIcon("src/harrypotter/view/draco3adi.jpg"));
					stat.getIcon().add(c);
				}
				if (champ.getName().equals("Slim Abdelnaher")) {
					JLabel co = new JLabel();
					co.setIcon(new ImageIcon("src/harrypotter/view/slim3adi.jpg"));
					stat.getIcon().add(co);
				}
				if (champ.getName().equals("Severus Snape")) {
					JLabel co = new JLabel();
					co.setIcon(new ImageIcon("src/harrypotter/view/snap3adi.jpg"));
					stat.getIcon().add(co);
				}

				stat.getIcon().add(b);
			}
			stat.setChamp(champ);
			stat.getUseTrait().addKeyListener(this);
			stat.getUseTrait().addActionListener(this);
			i++;
		}
		for (; i < getChampStats().size();) {
			getChampStats().remove(i);
		}
		spellsSelectView.setChamps(getChampStats());
	}

	public void keyPressed(KeyEvent e) {
		int ind = e.getKeyCode();
		Champion c = currentTask.getCurrentChamp();
		ArrayList<Point> markedCells = new ArrayList<Point>();
		getCurrentView().setCurrentChamp(currentTask.getCurrentChamp());
		// markedCells = ((FirstTask) currentTask).getMarkedCells();
		int row = currentChampRow;
		int col = currentChampCol;
		int nrow = currentChampRow;
		int ncol = currentChampCol;
		boolean change = false;
		// System.out.println(currentChamp.getName());
		switch (ind) {
		case 37:
			try {
				ArrayList<Point> marked = new ArrayList<>();
				if (firstTask.getMarkedCells().get(0) != null)
					marked.add(firstTask.getMarkedCells().get(0));
				if (firstTask.getMarkedCells().get(1) != null)
					marked.add(firstTask.getMarkedCells().get(1));
				if (flagSlytherin) {
					flagSlytherin = false;
					((SlytherinWizard) currentTask.getCurrentChamp()).setTraitDirection(Direction.LEFT);
					try {
						currentTask.getCurrentChamp().useTrait();
						change = true;
						if (getCurrentView() instanceof FirstTaskView)
							getCurrentView().fire(markedCells);
						ncol = currentChampCol - 2;
						refreshMap(marked);
					} catch (Exception i) {
						flagSlytherin = false;
						change = false;
						getCurrentView().getConsole()
								.append("\n" + i.getMessage() + "\nYour last action was eliminated.");
					}
				} else {
					if (getCurrentView() instanceof FirstTaskView)
						getCurrentView().fire(markedCells);
					moveLeft();
					refreshMap(marked);
					change = true;
					ncol = currentChampCol - 1;
				}
				if (change) {
					updateStat(getCurrentView().getCurrentStat(), getCurrentView().getChampStats(), c);
					getCurrentView().updateStat(currentTask.isTraitActivated());
					getCurrentView().setCurrentChamp(currentTask.getCurrentChamp());
				}
				setCurrentChampCol(currentChamp.getLocation().y);
				setCurrentChampRow(currentChamp.getLocation().x);
				getCurrentView().revalidate();
			} catch (Exception e1) {
				getCurrentView().getConsole().append("\n" + e1.getMessage());
			}
			break;
		case 38:
			try {
				ArrayList<Point> marked = new ArrayList<>();
				if (firstTask.getMarkedCells().get(0) != null)
					marked.add(firstTask.getMarkedCells().get(0));
				if (firstTask.getMarkedCells().get(1) != null)
					marked.add(firstTask.getMarkedCells().get(1));
				if (flagSlytherin) {
					flagSlytherin = false;
					((SlytherinWizard) currentTask.getCurrentChamp()).setTraitDirection(Direction.FORWARD);
					try {
						currentTask.getCurrentChamp().useTrait();
						change = true;
						if (getCurrentView() instanceof FirstTaskView)
							getCurrentView().fire(markedCells);
						nrow = currentChampRow - 2;
						refreshMap(marked);
					} catch (Exception i) {
						flagSlytherin = false;
						change = false;
						getCurrentView().getConsole()
								.append("\n" + i.getMessage() + "\nYour last action was eliminated.");
					}
				} else {
					/*
					 * if (relocatingSpellFlagDest) { secondDirection =
					 * Direction.FORWARD;
					 * currentTask.castRelocatingSpell((RelocatingSpell) spell,
					 * firstDirection, secondDirection, range); change = true;
					 * updateRelocating(firstDirection, secondDirection, range);
					 * relocatingSpellFlagDest = false; } else {
					 */
					// if (getCurrentView() instanceof FirstTaskView)
					// getCurrentView().fire(markedCells);
					moveForward();
					refreshMap(marked);
					change = true;
					nrow = currentChampRow - 1;

				}
				if (change) {
					updateStat(getCurrentView().getCurrentStat(), getCurrentView().getChampStats(), c);
					getCurrentView().updateStat(currentTask.isTraitActivated());
					getCurrentView().setCurrentChamp(currentTask.getCurrentChamp());
				}
				setCurrentChampCol(currentChamp.getLocation().y);
				setCurrentChampRow(currentChamp.getLocation().x);
				getCurrentView().revalidate();
			} catch (Exception e1) {
				getCurrentView().getConsole().append("\n" + e1.getMessage());
			}
			break;
		case 39:
			try {
				ArrayList<Point> marked = new ArrayList<>();
				if (firstTask.getMarkedCells().get(0) != null)
					marked.add(firstTask.getMarkedCells().get(0));
				if (firstTask.getMarkedCells().get(1) != null)
					marked.add(firstTask.getMarkedCells().get(1));

				if (flagSlytherin) {
					flagSlytherin = false;
					((SlytherinWizard) getCurrentTask().getCurrentChamp()).setTraitDirection(Direction.RIGHT);
					try {
						getCurrentTask().getCurrentChamp().useTrait();
						change = true;
						// if (getCurrentView() instanceof FirstTaskView)
						// getCurrentView().fire(markedCells);
						ncol = currentChampCol + 2;
						refreshMap(marked);
					} catch (Exception i) {
						change = false;
						flagSlytherin = false;
						getCurrentView().getConsole()
								.append("\n" + i.getMessage() + "\nYour last action was eliminated.");
					}
				} else {
					// if (getCurrentView() instanceof FirstTaskView)
					// getCurrentView().fire(markedCells);
					moveRight();
					refreshMap(marked);
					change = true;
					ncol = currentChampCol + 1;
				}

				if (change) {
					updateStat(getCurrentView().getCurrentStat(), getCurrentView().getChampStats(), c);
					getCurrentView().updateStat(currentTask.isTraitActivated());
					getCurrentView().setCurrentChamp(currentTask.getCurrentChamp());
				}
				setCurrentChampCol(currentChamp.getLocation().y);
				setCurrentChampRow(currentChamp.getLocation().x);
				getCurrentView().revalidate();
			} catch (Exception e1) {
				getCurrentView().getConsole().append("\n" + e1.getMessage());
			}
			break;
		case 40:
			try {
				ArrayList<Point> marked = new ArrayList<>();
				if (firstTask.getMarkedCells().get(0) != null)
					marked.add(firstTask.getMarkedCells().get(0));
				if (firstTask.getMarkedCells().get(1) != null)
					marked.add(firstTask.getMarkedCells().get(1));
				if (flagSlytherin) {
					flagSlytherin = false;
					((SlytherinWizard) currentTask.getCurrentChamp()).setTraitDirection(Direction.BACKWARD);
					try {
						currentTask.getCurrentChamp().useTrait();
						change = true;
						if (getCurrentView() instanceof FirstTaskView)
							getCurrentView().fire(markedCells);
						nrow = currentChampRow + 2;
						refreshMap(marked);
					} catch (Exception i) {
						change = false;
						flagSlytherin = false;
						getCurrentView().getConsole()
								.append("\n" + i.getMessage() + "\nYour last action was eliminated.");
					}

				} /*
					 * else { if (relocatingSpellFlagDest) { secondDirection =
					 * Direction.BACKWARD;
					 * currentTask.castRelocatingSpell((RelocatingSpell) spell,
					 * firstDirection, secondDirection, range);
					 * updateRelocating(firstDirection, secondDirection, range);
					 * change = true; relocatingSpellFlagDest = false; } else if
					 * (relocatingSpellFlagLocat) { firstDirection =
					 * Direction.BACKWARD; relocatingSpellFlagDest = true;
					 * relocatingSpellFlagLocat = false;
					 * getCurrentView().getConsole().
					 * append("\nPress Targeted Direction"); }
					 */else {
					moveBackward();
					refreshMap(marked);
					change = true;
					nrow = currentChampRow + 1;
				}

				if (change) {
					updateStat(getCurrentView().getCurrentStat(), getCurrentView().getChampStats(), c);
					getCurrentView().updateStat(currentTask.isTraitActivated());
					getCurrentView().setCurrentChamp(currentTask.getCurrentChamp());
				}
				setCurrentChampCol(currentChamp.getLocation().y);
				setCurrentChampRow(currentChamp.getLocation().x);
				getCurrentView().revalidate();
			} catch (Exception e1) {
				getCurrentView().getConsole().append("\n" + e1.getMessage());
			}
			break;
		case 49: {
			ArrayList<Point> marked = new ArrayList<>();
			if (firstTask.getMarkedCells().get(0) != null)
				marked.add(firstTask.getMarkedCells().get(0));
			if (firstTask.getMarkedCells().get(1) != null)
				marked.add(firstTask.getMarkedCells().get(1));
			int spellRange = 0;
			if (currentTask.getCurrentChamp().getSpells().size() > 0) {
				indexOfSpell = 0;
				spell = currentTask.getCurrentChamp().getSpells().get(0);
				if (!castedSpells.isEmpty()) {
					getCurrentView().getConsole().append(
							"\nNothing happened, you were already casting a spell. \nYou may now choose your next action.");
					damagingSpellFlag = false;
					relocatingSpellFlagDest = false;
					relocatingSpellFlagLocat = false;
					while (!castedSpells.isEmpty()) {
						castedSpells.pop().setBackground(defaultSpellColor);
					}
					return;
				}
			}
			if (spell instanceof RelocatingSpell) {
				castedSpells.push((JButtonSpell) getCurrentView().getCurrentStat().getChampSpells().getComponent(0));
				spellRange = ((RelocatingSpell) spell).getRange();
				relocatingSpellFlagLocat = true;
				getCurrentView().requestFocus();
				getCurrentView().getCurrentStat().getChampSpells().getComponent(0).setBackground(Color.GREEN);
				getCurrentView().getCurrentStat().getChampSpells().getComponent(0).revalidate();
			} else if (spell instanceof HealingSpell) {
				try {
					currentTask.castHealingSpell((HealingSpell) spell);
					updateStat(getCurrentView().getCurrentStat(), getCurrentView().getChampStats(), c);
					getCurrentView().getCurrentStat().updateUI();
					getCurrentView().revalidate();
					getCurrentView().updateStat(currentTask.isTraitActivated());
					getCurrentView().setCurrentChamp(currentTask.getCurrentChamp());
					setCurrentChampCol(currentChamp.getLocation().y);
					setCurrentChampRow(currentChamp.getLocation().x);
					getCurrentView().revalidate();
					// getCurrentView().getCurrentStat().getChampSpells().getComponent(0).setBackground(Color.ORANGE);
				} catch (Exception e1) {
					getCurrentView().getConsole().append("\n" + e1.getMessage() + "\nYour last action was eliminated.");
				}
			} else if (spell instanceof DamagingSpell) {
				castedSpells.push((JButtonSpell) getCurrentView().getCurrentStat().getChampSpells().getComponent(0));
				damagingSpellFlag = true;
				IndOfDmgSpell = 0;
				getCurrentView().requestFocus();
				getCurrentView().getCurrentStat().getChampSpells().getComponent(0).setBackground(Color.RED);
				getCurrentView().getCurrentStat().getChampSpells().getComponent(0).revalidate();
				// getCurrentView().onDamagingSpell(currentChamp.getLocation());
				getCurrentView().getConsole().append("\nChoose Damage Spell Cell.");

			}
			break;
		}
		case 50: {
			ArrayList<Point> marked = new ArrayList<>();
			if (firstTask.getMarkedCells().get(0) != null)
				marked.add(firstTask.getMarkedCells().get(0));
			if (firstTask.getMarkedCells().get(1) != null)
				marked.add(firstTask.getMarkedCells().get(1));
			if (currentTask.getCurrentChamp().getSpells().size() >= 2) {
				indexOfSpell = 1;
				spell = currentTask.getCurrentChamp().getSpells().get(1);
				if (!castedSpells.isEmpty()) {
					getCurrentView().getConsole().append(
							"\nNothing happened, you were already casting a spell. \nYou may now choose your next action.");
					damagingSpellFlag = false;
					relocatingSpellFlagDest = false;
					relocatingSpellFlagLocat = false;
					while (!castedSpells.isEmpty()) {
						castedSpells.pop().setBackground(defaultSpellColor);
					}
					return;
				}
				if (spell instanceof RelocatingSpell) {
					rangeOfSpell = ((RelocatingSpell) spell).getRange();
					castedSpells
							.push((JButtonSpell) getCurrentView().getCurrentStat().getChampSpells().getComponent(1));
					relocatingSpellFlagLocat = true;
					getCurrentView().requestFocus();
					getCurrentView().getCurrentStat().getChampSpells().getComponent(1).setBackground(Color.GREEN);
					getCurrentView().getCurrentStat().getChampSpells().getComponent(1).revalidate();
				} else if (spell instanceof HealingSpell)
					try {
						currentTask.castHealingSpell((HealingSpell) spell);
						updateStat(getCurrentView().getCurrentStat(), getCurrentView().getChampStats(), c);
						getCurrentView().getCurrentStat().updateUI();
						getCurrentView().revalidate();
						getCurrentView().updateStat(currentTask.isTraitActivated());
						// getCurrentView().getCurrentStat().getChampSpells().getComponent(1).setBackground(Color.ORANGE);
						refreshMap(marked);
						getCurrentView().setCurrentChamp(currentTask.getCurrentChamp());
						setCurrentChampCol(currentChamp.getLocation().y);
						setCurrentChampRow(currentChamp.getLocation().x);
						getCurrentView().revalidate();
					} catch (Exception e1) {
						getCurrentView().getConsole().append("\n" + e1.getMessage());
						;
					}
				else if (spell instanceof DamagingSpell) {
					castedSpells
							.push((JButtonSpell) getCurrentView().getCurrentStat().getChampSpells().getComponent(1));
					damagingSpellFlag = true;
					IndOfDmgSpell = 1;
					getCurrentView().getCurrentStat().getChampSpells().getComponent(1).setBackground(Color.RED);
					getCurrentView().getCurrentStat().getChampSpells().getComponent(1).revalidate();
					getCurrentView().requestFocus();
					getCurrentView().getConsole().append("\nChoose Damage Spell Cell.");
					getCurrentView().getCurrentStat().repaint();
				}
			}
			break;
		}
		case 51: {
			ArrayList<Point> marked = new ArrayList<>();
			if (firstTask.getMarkedCells().get(0) != null)
				marked.add(firstTask.getMarkedCells().get(0));
			if (firstTask.getMarkedCells().get(1) != null)
				marked.add(firstTask.getMarkedCells().get(1));
			if (currentTask.getCurrentChamp().getSpells().size() >= 3) {
				indexOfSpell = 2;
				spell = currentTask.getCurrentChamp().getSpells().get(2);
				if (!castedSpells.isEmpty()) {
					getCurrentView().getConsole().append(
							"\nNothing happened, you were already casting a spell. \nYou may now choose your next action.");
					damagingSpellFlag = false;
					relocatingSpellFlagDest = false;
					relocatingSpellFlagLocat = false;
					while (!castedSpells.isEmpty()) {
						castedSpells.pop().setBackground(defaultSpellColor);
					}
					return;
				}
				if (spell instanceof RelocatingSpell) {
					rangeOfSpell = ((RelocatingSpell) spell).getRange();
					castedSpells
							.push((JButtonSpell) getCurrentView().getCurrentStat().getChampSpells().getComponent(2));
					relocatingSpellFlagLocat = true;
					getCurrentView().requestFocus();
					getCurrentView().getCurrentStat().getChampSpells().getComponent(2).setBackground(Color.GREEN);
					getCurrentView().getCurrentStat().getChampSpells().getComponent(2).revalidate();
				} else if (spell instanceof HealingSpell)
					try {
						currentTask.castHealingSpell((HealingSpell) spell);
						updateStat(getCurrentView().getCurrentStat(), getCurrentView().getChampStats(), c);
						getCurrentView().getCurrentStat().updateUI();
						getCurrentView().revalidate();
						getCurrentView().setCurrentChamp(currentTask.getCurrentChamp());
						setCurrentChampCol(currentChamp.getLocation().y);
						setCurrentChampRow(currentChamp.getLocation().x);
						refreshMap(marked);
						getCurrentView().revalidate();
						// getCurrentView().getCurrentStat().getChampSpells().getComponent(2).setBackground(Color.ORANGE);
					} catch (Exception e1) {
						getCurrentView().getConsole().append("\n" + e1.getMessage());
					}
				else if (spell instanceof DamagingSpell) {
					castedSpells
							.push((JButtonSpell) getCurrentView().getCurrentStat().getChampSpells().getComponent(2));
					damagingSpellFlag = true;
					IndOfDmgSpell = 2;
					getCurrentView().requestFocus();
					getCurrentView().getCurrentStat().getChampSpells().getComponent(2).setBackground(Color.RED);
					getCurrentView().getCurrentStat().getChampSpells().getComponent(2).revalidate();
					getCurrentView().getConsole().append("\nChoose Damage Spell Cell.");
					getCurrentView().requestFocus();
				}
			}
		}
			break;
		}
		if (onFirstTask && tournament.getSecondTask() != null) {
			onFirstTask = false;
			onSecondTask = true;
			setCurrentTask(tournament.getSecondTask());
			secondTask = tournament.getSecondTask();
			// secondTask.getCurrentChamp().setHp(6456545);
			secondTaskView = new SecondTaskView();
			secondTaskView.requestFocus();
			secondTaskView.revalidate();
		

			getCurrentView().dispose();
			setCurrentView(secondTaskView);
			getCurrentView().getConsole().append("The Winners of " + getCurrentTask().getClass() + " are :" );
			for (int i = 0; i < getCurrentTask().getChampions().size(); i++) {
				getCurrentView().getConsole().append( "\n" + getCurrentTask().getChampions().get(i).getName());
			}
			getCurrentView().getExit().addActionListener(this);
			getCurrentView().getHelp().addActionListener(this);
			getCurrentView().getSound().addActionListener(this);
			getCurrentView().getSkipTurn().addActionListener(this);
			getCurrentView().initializeStats(currentTask.getChampions(), this);
			currentChamp = currentTask.getCurrentChamp();
			currentChampRow = currentChamp.getLocation().x;
			currentChampCol = currentChamp.getLocation().y;
			try {
				loadMap();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			getCurrentView().addKeyListener(this);
			defaultColor = getCurrentView().getChampStats().get(0).getBackground();
			getCurrentView().setCurrentStat(getCurrentView().getChampStats().getFirst());
			getCurrentView().setCurrentChamp(currentTask.getCurrentChamp());
			getCurrentView().getCurrentStat().setBackground(Color.LIGHT_GRAY);
			;
			getCurrentView().revalidate();
			getCurrentView().requestFocus();
		} else if (onSecondTask && tournament.getThirdTask() != null) {
			onSecondTask = false;
			onThirdTask = true;
			setCurrentTask(tournament.getThirdTask());
			thirdTaskView = new ThirdTaskView();
			thirdTaskView.requestFocus();
			thirdTaskView.revalidate();
			getCurrentView().getConsole().append("The Winners of " + getCurrentTask().getClass() + " are :" );
			for (int i = 0; i < getCurrentTask().getChampions().size(); i++) {
				getCurrentView().getConsole().append( "\n" + getCurrentTask().getChampions().get(i).getName()); }

			secondTaskView.addKeyListener(null);
			secondTaskView.addMouseListener(this);
			getCurrentView().dispose();
			setCurrentView(thirdTaskView);
			getCurrentView().getExit().addActionListener(this);
			getCurrentView().getHelp().addActionListener(this);
			getCurrentView().getSound().addActionListener(this);
			getCurrentView().getSkipTurn().addActionListener(this);
			currentChamp = currentTask.getCurrentChamp();
			getCurrentView().initializeStats(currentTask.getChampions(), this);
			currentChamp = currentTask.getCurrentChamp();
			// System.out.println(currentChamp.toString());
			currentChampRow = currentChamp.getLocation().x;
			currentChampCol = currentChamp.getLocation().y;
			loadMap();
			getCurrentView().addKeyListener(this);
			defaultColor = getCurrentView().getChampStats().get(0).getBackground();
			getCurrentView().setCurrentStat(getCurrentView().getChampStats().getFirst());
			getCurrentView().setCurrentChamp(currentTask.getCurrentChamp());
			getCurrentView().getCurrentStat().setBackground(Color.LIGHT_GRAY);
			getCurrentView().revalidate();
			getCurrentView().requestFocus();
		} else if (tournament.getWinner() == null && currentTask.getChampions().isEmpty()) {
			getCurrentView().dispose();
			gameOver = new GameOver();
			gameOver.getExit().addActionListener(this);
		} else if (onThirdTask && tournament.getWinner() != null) {
			getCurrentView().dispose();
			winner = new Winner(tournament.getWinner());
			winner.getExit().addActionListener(this);
			winner.getRestart().addActionListener(this);
		}
		// if (onThirdTask) {
		// getCurrentView().dispose();
		// gameOver = new GameOver();
		// gameOver.setVisible(true);
		// }
		if (currentTask != null) {
			currentChamp = currentTask.getCurrentChamp();
			getCurrentView().requestFocus();
		}
	}

	public void updateRelocating(Direction first, Direction second, int range) throws InterruptedException {
		int beforeX = getCurrentChamp().getLocation().x;
		int nextX = getCurrentChamp().getLocation().x;
		int beforeY = getCurrentChamp().getLocation().y;
		int nextY = getCurrentChamp().getLocation().y;
		switch (first) {
		case FORWARD:
			beforeX = beforeX - 1;
			break;
		case BACKWARD:
			beforeX = beforeX + 1;
			break;
		case RIGHT:
			beforeY = beforeY + 1;
			break;
		case LEFT:
			beforeY = beforeY - 1;
		}
		switch (second) {
		case FORWARD:
			nextX = nextX - range;
			break;
		case BACKWARD:
			nextX = nextX + range;
			break;
		case RIGHT:
			nextY = nextY + range;
			break;
		case LEFT:
			nextY = nextY - range;
		}
		getCurrentView().updateStat(beforeX, nextX, beforeY, nextY, currentTask.isTraitActivated());
	}

	public void keyReleased(KeyEvent arg0) {
	}

	public void keyTyped(KeyEvent arg0) {
	}

	public void updateStat(Status stat, LinkedList<Status> stats, Champion c) {
		stat.setChampHP(c.getHp() + "", c.getDefaultHp() + "");
		stat.setChampIP(c.getIp() + "", c.getDefaultIp() + "");
		stat.setChampCooldown(c.getTraitCooldown() + "");
		updateInventory(stat, c);
		updateSpells(stat, c);
		updateIcon(stat, c);
		// getCurrentView().setCurrentStat(champStats.get((champStats.indexOf(getCurrentView().getCurrentStat())
		// + 1)%getChampStats().size()));
		// getCurrentView().getCurrentStat().setBackground(Color.LIGHT_GRAY);
		if (c.getHp() == 0) {
			stat.setEnabled(false);
			stats.remove(stat);
		}
		c = firstTask.getCurrentChamp();
		stat.updateUI();
	}

	public void updateInventory(Status s, Champion c) {
		int inventorySize = s.getChampInventory().getComponentCount();
		ArrayList<Collectible> inventoryChamp = c.getInventory();
		if (inventorySize < inventoryChamp.size()) {
			JButton tmp = new JButton(inventoryChamp.get(inventoryChamp.size() - 1).getName());
			tmp.setToolTipText("IP:" + "\n" + ((Potion) inventoryChamp.get(inventoryChamp.size() - 1)).getAmount());
			tmp.addActionListener(this);
			s.getChampInventory().add(tmp);
		}
		s.updateUI();
	}

	public void updateIcon(Status s, Champion c) {
		s.getIcon().removeAll();
		if (c instanceof GryffindorWizard) {
			s.getIcon().removeAll();
			s.getIcon().setBackground(new Color(139, 0, 0));

			s.setName(c.getName() + "(G)");
			s.setChampHP(c.getHp() + "", c.getHp() + "");
			s.setChampIP(c.getIp() + "", c.getIp() + "");
			s.setChampCooldownValue(c.getTraitCooldown() + "");

			ImageIcon bg = new ImageIcon("src/harrypotter/view/gryf.jpg");
			JLabel b = new JLabel();
			b.setIcon(bg);

			if (c.getName().equals("Harry Potter")) {
				ImageIcon ic = new ImageIcon("src/harrypotter/view/harry3adi.jpg");

				JLabel jl = new JLabel();
				jl.setIcon(ic);
				s.getIcon().add(jl);

			}

			else {
				if (c.getName().equals("Hermione Granger")) {
					ImageIcon ic = new ImageIcon("src/harrypotter/view/herm3adi.jpg");

					JLabel jl = new JLabel();
					jl.setIcon(ic);
					s.getIcon().add(jl);
				} else {
					if (c.getName().equals("Ron Weasley")) {
						ImageIcon ic = new ImageIcon("src/harrypotter/view/ron3adi.jpg");
						JLabel jl = new JLabel();
						jl.setIcon(ic);
						s.getIcon().add(jl);
					}
					
				}
			}
			s.getIcon().add(b);
		} else if (c instanceof HufflepuffWizard) {
			s.getIcon().setBackground(new Color(255, 219, 88));

			ImageIcon bg = new ImageIcon("src/harrypotter/view/huff.jpg");
			JLabel b = new JLabel();
			b.setIcon(bg);

			s.setName(c.getName() + "(H)");
			s.setChampHP(c.getHp() + "", c.getHp() + "");
			s.setChampIP(c.getIp() + "", c.getIp() + "");
			s.setChampCooldownValue(c.getTraitCooldown() + "");

			if (c.getName().equals("Cedric Diggory")) {

				JLabel co = new JLabel();
				co.setIcon(new ImageIcon("src/harrypotter/view/cedric3adi.jpg"));
				s.getIcon().add(co);

			}
			if(c.getName().equals("Pomona Sprout")){
				JLabel co = new JLabel();
				co.setIcon(new ImageIcon("src/harrypotter/view/pom3adi.jpg"));
				s.getIcon().add(co);
			}

			s.getIcon().add(b);
		} else if (c instanceof RavenclawWizard) {

			s.getIcon().setBackground(new Color(41, 38, 78));

			ImageIcon bg = new ImageIcon("src/harrypotter/view/raven.jpg");
			JLabel b = new JLabel();
			b.setIcon(bg);

			s.setName(c.getName() + "(R)");
			s.setChampHP(c.getHp() + "", c.getHp() + "");
			s.setChampIP(c.getIp() + "", c.getIp() + "");
			s.setChampCooldownValue(c.getTraitCooldown() + "");

			if (c.getName().equals("Cho Chang")) {
				JLabel co = new JLabel();
				co.setIcon(new ImageIcon("src/harrypotter/view/cho3adi.jpg"));
				s.getIcon().add(co);
			}
			if( c.getName().equals("Luna Lovegood")){
				JLabel co = new JLabel();
				co.setIcon(new ImageIcon("src/harrypotter/view/luna3adi.jpg"));
				s.getIcon().add(co);
			}

			s.getIcon().add(b);
		} else {
			s.getIcon().removeAll();

			s.getIcon().setBackground(new Color(1, 50, 32));

			ImageIcon bg = new ImageIcon("src/harrypotter/view/sly.jpg");
			JLabel b = new JLabel();
			b.setIcon(bg);

			s.setName(c.getName() + "(S)");
			s.setChampHP(c.getHp() + "", c.getHp() + "");
			s.setChampIP(c.getIp() + "", c.getIp() + "");
			s.setChampCooldownValue(c.getTraitCooldown() + "");

			if (c.getName().equals("Draco Malfoy")) {
				JLabel co = new JLabel();
				co.setIcon(new ImageIcon("src/harrypotter/view/draco3adi.jpg"));
				s.getIcon().add(co);
			}
			if (c.getName().equals("Slim Abdelnaher")) {
				JLabel co = new JLabel();
				co.setIcon(new ImageIcon("src/harrypotter/view/slim3adi.jpg"));
				s.getIcon().add(co);
			}
			if (c.getName().equals("Severus Snape")) {
				JLabel co = new JLabel();
				co.setIcon(new ImageIcon("src/harrypotter/view/snap3adi.jpg"));
				s.getIcon().add(co);
			}

			s.getIcon().add(b);
		}
	}

	public void updateSpells(Status s, Champion c) {
		Spell spell;
		JButtonSpell btn;
		for (int i = 0; i < c.getSpells().size(); i++) {
			spell = c.getSpells().get(i);
			btn = (JButtonSpell) s.getChampSpells().getComponent(i);
			if (spell instanceof RelocatingSpell) {
				btn.setToolTipText("Relocating Spell" + ", Cost:" + spell.getCost() + "\nDefault Cooldown:"
						+ spell.getDefaultCooldown() + ", CoolDown:" + spell.getCoolDown() + "\nRange:"
						+ ((RelocatingSpell) spell).getRange() + ".");
			}
			if (spell instanceof HealingSpell) {
				btn.setToolTipText("Healing Spell" + ", Cost:" + spell.getCost() + ", Default Cooldown:"
						+ spell.getDefaultCooldown() + ", CoolDown:" + spell.getCoolDown() + ", Healing Amount:"
						+ ((HealingSpell) spell).getHealingAmount() + ".");
			}
			if (spell instanceof DamagingSpell) {
				btn.setToolTipText("Damaging Spell" + ", Cost:" + spell.getCost() + "\nDefault Cooldown:"
						+ spell.getDefaultCooldown() + "\nCoolDown:" + spell.getCoolDown() + "\n Damage Amount:"
						+ ((DamagingSpell) spell).getDamageAmount() + ".");
			}
			// spells.add(btn);
			btn.updateUI();
		}
		// return spells;
		// s.setChampSpells(spells);
	}

	public void addSpell(Status stat, Champion c, JButtonSpell btn) {

		String type;
		stat.getClicked().add(btn);
		JButtonSpell champSpellBtn = new JButtonSpell(btn.getSpell());
		JPanelSpell champSpellPnl = new JPanelSpell(champSpellBtn);
		if (btn.getSpell() instanceof RelocatingSpell) {
			champSpellBtn.setToolTipText("Relocating Spell" + ", Cost:" + btn.getSpell().getCost()
					+ ", Default Cooldown:" + btn.getSpell().getDefaultCooldown() + ", CoolDown:"
					+ btn.getSpell().getCoolDown() + ", Range: " + ((RelocatingSpell) btn.getSpell()).getRange() + ".");
		}
		if (btn.getSpell() instanceof HealingSpell) {
			champSpellBtn.setToolTipText("Healing Spell" + ", Cost:" + btn.getSpell().getCost() + ", Default Cooldown:"
					+ btn.getSpell().getDefaultCooldown() + ", CoolDown:" + btn.getSpell().getCoolDown()
					+ ", Healing Amount: " + ((HealingSpell) btn.getSpell()).getHealingAmount() + ".");
		}
		if (btn.getSpell() instanceof DamagingSpell) {
			champSpellBtn.setToolTipText("Damaging Spell" + ", Cost:" + btn.getSpell().getCost() + ", Default Cooldown:"
					+ btn.getSpell().getDefaultCooldown() + ", CoolDown:" + btn.getSpell().getCoolDown()
					+ ", Damage Amount: " + ((DamagingSpell) btn.getSpell()).getDamageAmount() + ".");
		}
		champSpellBtn.addActionListener(this);
		champSpellBtn.setText(btn.getText());
		champSpellBtn.setBackground(btn.getBackground());
		stat.getChampSpells().add(champSpellPnl);
		champSpellPnl.add(champSpellPnl.getRefresh());
		champSpellPnl.getRefresh().addActionListener(this);
		champSpellPnl.updateUI();
		stat.updateUI();
		Spell spell = champSpellBtn.getSpell();
		Spell toAdd = null;
		if (spell instanceof HealingSpell) {
			toAdd = new HealingSpell(spell.getName(), spell.getCost(), spell.getDefaultCooldown(),
					((HealingSpell) spell).getHealingAmount());
		} else {
			if (spell instanceof DamagingSpell) {
				toAdd = new DamagingSpell(spell.getName(), spell.getCost(), spell.getDefaultCooldown(),
						((DamagingSpell) spell).getDamageAmount());
			} else if (spell instanceof RelocatingSpell) {
				toAdd = new RelocatingSpell(spell.getName(), spell.getCost(), spell.getDefaultCooldown(),
						((RelocatingSpell) spell).getRange());

			}
		}
		if (toAdd != null) {
			toAdd.setCoolDown(0);
			c.getSpells().add(toAdd);
		}
	}

	public void removeSpell(Status s, Champion c, JPanelSpell pnl) {
		spellsSelectView.spellCountEach--;
		int ind = s.getChampSpells().getComponentZOrder(pnl);
		s.getClicked().get(ind).setEnabled(true);
		s.getClicked().remove(ind);
		s.getChampSpells().remove(pnl);
		c.getSpells().remove(pnl.getBtnSpell().getSpell());
	}

	public void sortSpells(ArrayList<Spell> spells) {
		Spell spell;
		for (int i = 0; i < spells.size(); i++) {
			spell = spells.get(i);
			JButton b = new JButtonSpell(spell);
			b.setText(spell.getName());
			b.addActionListener(this);
			b.setForeground(Color.BLACK);
			b.setOpaque(false);
			b.setContentAreaFilled(false);
			if (spell instanceof DamagingSpell) {
				spellsSelectView.addDmg(b);
			} else if (spell instanceof HealingSpell)
				spellsSelectView.addHs(b);
			else if (spell instanceof RelocatingSpell)
				spellsSelectView.addRs(b);
		}
		spellsSelectView.setVisible(true);
	}

	/*
	 * public void loadMap() throws IOException { Cell[][] map =
	 * getCurrentTask().getMap(); JPanel gameMap = firstTaskView.getGameMap();
	 * 
	 * //BufferedImage myPicture = ImageIO.read(new
	 * File("C:\\Users\\User\\Desktop\\map.jpg")); int heightOfPanel;// =
	 * btn.getHeight(); int widthOfPanel;// = btn.getWidth(); //JLabel picLabel
	 * = new JLabel(new ImageIcon(myPicture)); btn.setBackground(Color.black);
	 * picLabel.setSize(widthOfPanel, heightOfPanel); btn.add(picLabel);
	 * 
	 * for (int i = 0; i < 10; i++) {
	 * 
	 * for (int j = 0; j < 10; j++) { JPanel btn = new JPanel(); if (map[i][j]
	 * instanceof ObstacleCell){ myPicture = ImageIO.read(new
	 * File("C:\\Users\\User\\Desktop\\map.jpg")); heightOfPanel =
	 * btn.getHeight(); myPicture = ImageIO.read(new
	 * File("C:\\Users\\User\\Desktop\\map.jpg")); heightOfPanel =
	 * btn.getHeight(); widthOfPanel = btn.getWidth(); picLabel = new JLabel(new
	 * ImageIcon(myPicture)); btn.setBackground(Color.black);
	 * picLabel.setSize(widthOfPanel, heightOfPanel); btn.add(picLabel); //=
	 * btn.getWidth(); picLabel = new JLabel(new ImageIcon(myPicture));
	 * btn.setBackground(Color.black); picLabel.setSize(widthOfPanel,
	 * heightOfPanel); btn.add(picLabel);} btn.setBackground(Color.BLACK); if
	 * (map[i][j] instanceof CollectibleCell) { myPicture = ImageIO.read(new
	 * File("C:\\Users\\User\\Desktop\\map.jpg")); heightOfPanel =
	 * btn.getHeight(); widthOfPanel = btn.getWidth(); picLabel = new JLabel(new
	 * ImageIcon(myPicture)); btn.setBackground(Color.black);
	 * picLabel.setSize(widthOfPanel, heightOfPanel); btn.add(picLabel);}
	 * btn.setBackground(Color.GREEN); if (map[i][j] instanceof ChampionCell) {
	 * myPicture = ImageIO.read(new File("C:\\Users\\User\\Desktop\\map.jpg"));
	 * heightOfPanel = btn.getHeight(); widthOfPanel = btn.getWidth(); picLabel
	 * = new JLabel(new ImageIcon(myPicture)); btn.setBackground(Color.black);
	 * picLabel.setSize(widthOfPanel, heightOfPanel); btn.add(picLabel);}
	 * btn.setBackground(Color.BLUE); if (map[i][j] instanceof WallCell){
	 * myPicture = ImageIO.read(new File("C:\\Users\\User\\Desktop\\map.jpg"));
	 * heightOfPanel = btn.getHeight(); widthOfPanel = btn.getWidth(); picLabel
	 * = new JLabel(new ImageIcon(myPicture)); btn.setBackground(Color.black);
	 * picLabel.setSize(widthOfPanel, heightOfPanel); btn.add(picLabel);}
	 * btn.setBackground(Color.ORANGE); if (map[i][j] instanceof EmptyCell) {
	 * btn.setBackground(Color.WHITE); myPicture = ImageIO.read(new
	 * File("C:\\Users\\User\\Desktop\\map.jpg")); heightOfPanel =
	 * btn.getHeight(); widthOfPanel = btn.getWidth(); picLabel = new JLabel(new
	 * ImageIcon(myPicture)); btn.setBackground(Color.black);
	 * picLabel.setSize(widthOfPanel, heightOfPanel); btn.add(picLabel); }
	 * 
	 * firstTaskView.getGameMap().add(btn); btn.addMouseListener(this); //
	 * System.out.println(btn.getMouseListeners()); btn.setName("Cell" + i + ""
	 * + j);
	 * 
	 * System.out.println(btn.getName()); } }
	 * 
	 * }}}}}
	 */

	public void loadMap() {
		Cell[][] map = getCurrentTask().getMap();
		JPanel gameMap = currentView.getGameMap();
		JLabel merp;

		for (int i = 0; i < 10; i++) {

			for (int j = 0; j < 10; j++) {
				JPanel btn = new JPanel();
				btn.setLayout(new OverlayLayout(btn));
				if (map[i][j] instanceof ObstacleCell) {
					if (getCurrentTask() instanceof SecondTask) {
						merp = new JLabel();
						merp.setIcon(new ImageIcon("src/harrypotter/view/Unknown-2 (1).jpeg"));
						btn.setBackground(new Color(8, 74, 109));
						btn.add(merp, BorderLayout.CENTER);
					}

					else {
						if (getCurrentTask() instanceof FirstTask) {
							btn.removeAll();
							// btn.setBackground(Color.BLACK);
							JLabel obs = new JLabel();
							obs.setIcon(new ImageIcon("src/harrypotter/view/obstacle (1).jpg"));
							btn.setBackground(new Color(139, 113, 101));
							btn.add(obs);
						} else {
							if (getCurrentTask() instanceof ThirdTask) {
								JLabel obs = new JLabel();
								obs.setIcon(new ImageIcon("src/rock3.jpg"));
								btn.setBackground(new Color(46, 51, 28));
								btn.add(obs);
							}
						}
					}
					// ((JComponent)
					// gameMap.getComponent(i*10+j)).setToolTipText("HP: " +
					// ((ObstacleCell) map[i][j]).getObstacle().getHp());
					btn.setToolTipText("HP: " + ((ObstacleCell) map[i][j]).getObstacle().getHp());
					// btn.setBackground(Color.BLACK);
				}
				if (map[i][j] instanceof WallCell) {
					int count = (i * 10) + j;
					JLabel wall = new JLabel();
					wall.setIcon(new ImageIcon("src/f.jpg"));
					btn.setBackground(new Color(46, 51, 28));
					wall.setBounds(0, 0, wall.getWidth(), wall.getHeight());
					// cell.add(wall);

					switch (count) {
					case 3:
					case 27:
						JLabel w = new JLabel();
						w.setIcon(new ImageIcon("src/wall2.jpg"));
						// cell = (JPanel) gameMap.getComponent(count);
						btn.add(w);
						break;

					case 21:
					case 31:
					case 13:
					case 15:
					case 25:
					case 35:
					case 37:
					case 47:
					case 57:
					case 59:
					case 69:
					case 75:
					case 79:
					case 76:
					case 71:
						JLabel w2 = new JLabel();
						w2.setIcon(new ImageIcon("src/wall4.jpg"));
						btn.add(w2);
						break;
					case 42:
					case 61:
					case 62:
					case 43:
					case 63:
					case 64:
					case 65:
					case 66:
					case 4:
					case 44:
					case 46:
						JLabel w1 = new JLabel();
						w1.setIcon(new ImageIcon("src/wall6.jpg"));
						btn.add(w1);
						break;

					case 5:
						JLabel w3 = new JLabel();
						w3.setIcon(new ImageIcon("src/wall7.jpg"));
						btn.add(w3);
						break;
					case 11:
					case 83:
					case 87:
					case 49:
					case 18:
						JLabel w4 = new JLabel();
						w4.setIcon(new ImageIcon("src/wall1.jpg"));
						btn.add(w4);
						break;
					case 81:
					case 93:
					case 85:
					case 97:
					case 89:
					case 23:
						JLabel w5 = new JLabel();
						w5.setIcon(new ImageIcon("src/wall5.jpg"));
						btn.add(w5);
						break;
					case 41:
						JLabel w6 = new JLabel();
						w6.setIcon(new ImageIcon("src/wall41.jpg"));
						btn.add(w6);
						break;
					case 60:
						JLabel w17 = new JLabel();
						w17.setIcon(new ImageIcon("src/wall701.png"));
						btn.add(w17);
						break;
					case 45:
					case 67:
					case 28:
						JLabel w8 = new JLabel();
						w8.setIcon(new ImageIcon("src/wall10.jpg"));
						btn.add(w8);
						break;

					}
				}

				// if (map[i][j] instanceof CollectibleCell)
				// btn.setBackground(Color.GREEN);
				if (map[i][j] instanceof ChampionCell) {
					if (getCurrentTask() instanceof FirstTask) {
						if (((ChampionCell) map[i][j]).getChamp().getName().equals("Harry Potter")) {
							JLabel c = new JLabel();
							btn.setBackground(new Color(130, 113, 101));
							c.setIcon(new ImageIcon("src/harrypotter/view/harry.jpg"));
							c.setBounds(0, 0, c.getWidth(), c.getHeight());
							btn.add(c);
						}
						if (((ChampionCell) map[i][j]).getChamp().getName().equals("Draco Malfoy")) {
							JLabel c = new JLabel();
							btn.setBackground(new Color(130, 113, 101));
							c.setIcon(new ImageIcon("src/harrypotter/view/g.jpg"));
							c.setBounds(0, 0, c.getWidth(), c.getHeight());
							btn.add(c);
						}
						if (((ChampionCell) map[i][j]).getChamp().getName().equals("Cedric Diggory")) {
							JLabel c = new JLabel();
							btn.setBackground(new Color(130, 113, 101));
							c.setIcon(new ImageIcon("src/harrypotter/view/sd.jpg"));
							c.setBounds(0, 0, c.getWidth(), c.getHeight());
							btn.add(c);
						}
						if (((ChampionCell) map[i][j]).getChamp().getName().equals("Hermione Granger")) {
							JLabel c = new JLabel();
							btn.setBackground(new Color(130, 113, 101));
							c.setIcon(new ImageIcon("src/harrypotter/view/herm.jpg"));
							c.setBounds(0, 0, c.getWidth(), c.getHeight());
							btn.add(c);
						}
						if (((ChampionCell) map[i][j]).getChamp().getName().equals("Luna Lovegood")) {
							JLabel c = new JLabel();
							btn.setBackground(new Color(130, 113, 101));
							c.setIcon(new ImageIcon("src/ASSX.jpg"));
							c.setBounds(0, 0, c.getWidth(), c.getHeight());
							btn.add(c);
						}
						if (((ChampionCell) map[i][j]).getChamp().getName().equals("Severus Snape")) {
							JLabel c = new JLabel();
							btn.setBackground(new Color(130, 113, 101));
							c.setIcon(new ImageIcon("src/snap1.jpg"));
							c.setBounds(0, 0, c.getWidth(), c.getHeight());
							btn.add(c);
						}
						if (((ChampionCell) map[i][j]).getChamp().getName().equals("Cho Chang")) {
							JLabel c = new JLabel();
							btn.setBackground(new Color(130, 113, 101));
							c.setIcon(new ImageIcon("src/cho1.jpg"));
							c.setBounds(0, 0, c.getWidth(), c.getHeight());
							btn.add(c);
						}
						if (((ChampionCell) map[i][j]).getChamp().getName().equals("Slim Abdelnaher")) {
							JLabel c = new JLabel();
							btn.setBackground(new Color(130, 113, 101));
							c.setIcon(new ImageIcon("src/slim 3.jpg"));
							c.setBounds(0, 0, c.getWidth(), c.getHeight());
							btn.add(c);
						}
						if (((ChampionCell) map[i][j]).getChamp().getName().equals("Ron Weasley")) {
							JLabel c = new JLabel();
							btn.setBackground(new Color(130, 113, 101));
							c.setIcon(new ImageIcon("src/ron1.jpg"));
							c.setBounds(0, 0, c.getWidth(), c.getHeight());
							btn.add(c);
						}
						if (((ChampionCell) map[i][j]).getChamp().getName().equals("Pomona Sprout")) {
							JLabel c = new JLabel();
							btn.setBackground(new Color(130, 113, 101));
							c.setIcon(new ImageIcon("src/pom1.jpg"));
							c.setBounds(0, 0, c.getWidth(), c.getHeight());
							btn.add(c);
						}

					}

					else {
						if (getCurrentTask() instanceof SecondTask) {
							if (((ChampionCell) map[i][j]).getChamp().getName().equals("Harry Potter")) {
								JLabel c = new JLabel();
								btn.setBackground(new Color(8, 74, 109));
								c.setIcon(new ImageIcon("src/harrypotter/view/h maya.jpg"));
								c.setBounds(0, 0, c.getWidth(), c.getHeight());
								btn.add(c);
							}
							if (((ChampionCell) map[i][j]).getChamp().getName().equals("Hermione Granger")) {
								JLabel c = new JLabel();
								btn.setBackground(new Color(8, 74, 109));
								c.setIcon(new ImageIcon("src/harrypotter/view/15.jpg"));
								c.setBounds(0, 0, c.getWidth(), c.getHeight());
								btn.add(c);

							}
							if (((ChampionCell) map[i][j]).getChamp().getName().equals("Cedric Diggory")) {
								JLabel c = new JLabel();
								btn.setBackground(new Color(8, 74, 109));
								c.setIcon(new ImageIcon("src/harrypotter/view/12.jpg"));
								c.setBounds(0, 0, c.getWidth(), c.getHeight());
								btn.add(c);

							}
							if (((ChampionCell) map[i][j]).getChamp().getName().equals("Draco Malfoy")) {
								JLabel c = new JLabel();
								btn.setBackground(new Color(8, 74, 109));
								c.setIcon(new ImageIcon("src/harrypotter/view/14 (1).jpg"));
								c.setBounds(0, 0, c.getWidth(), c.getHeight());
								btn.add(c);

							}
							if (((ChampionCell) map[i][j]).getChamp().getName().equals("Luna Lovegood")) {
								JLabel c = new JLabel();
								btn.setBackground(new Color(8, 74, 109));
								c.setIcon(new ImageIcon("src/d.jpg"));
								c.setBounds(0, 0, c.getWidth(), c.getHeight());
								btn.add(c);

							}
							if (((ChampionCell) map[i][j]).getChamp().getName().equals("Severus Snape")) {
								JLabel c = new JLabel();
								btn.setBackground(new Color(8, 74, 109));
								c.setIcon(new ImageIcon("src/snap2.jpg"));
								c.setBounds(0, 0, c.getWidth(), c.getHeight());
								btn.add(c);

							}
							if (((ChampionCell) map[i][j]).getChamp().getName().equals("Cho Chang")) {
								JLabel c = new JLabel();
								btn.setBackground(new Color(8, 74, 109));
								c.setIcon(new ImageIcon("src/cho2.jpg"));
								c.setBounds(0, 0, c.getWidth(), c.getHeight());
								btn.add(c);

							}
							if (((ChampionCell) map[i][j]).getChamp().getName().equals("Slim Abdelnaher")) {
								JLabel c = new JLabel();
								btn.setBackground(new Color(8, 74, 109));
								c.setIcon(new ImageIcon("src/slim 1.jpg"));
								c.setBounds(0, 0, c.getWidth(), c.getHeight());
								btn.add(c);
							}
							if (((ChampionCell) map[i][j]).getChamp().getName().equals("Ron Weasley")) {
								JLabel c = new JLabel();
								btn.setBackground(new Color(8, 74, 109));
								c.setIcon(new ImageIcon("src/ron2.jpg"));
								c.setBounds(0, 0, c.getWidth(), c.getHeight());
								btn.add(c);
							}
							if (((ChampionCell) map[i][j]).getChamp().getName().equals("Pomona Sprout")) {
								JLabel c = new JLabel();
								btn.setBackground(new Color(8, 74, 109));
								c.setIcon(new ImageIcon("src/pom2.jpg"));
								c.setBounds(0, 0, c.getWidth(), c.getHeight());
								btn.add(c);
							}
						} else {
							if (((ChampionCell) map[i][j]).getChamp().getName().equals("Luna Lovegood")) {
								btn.removeAll();
								JLabel c = new JLabel();
								btn.setBackground(new Color(46, 51, 28));
								c.setIcon(new ImageIcon("src/luna3.jpg"));
								c.setBounds(0, 0, c.getWidth(), c.getHeight());
								btn.add(c);

							}
							if (((ChampionCell) map[i][j]).getChamp().getName().equals("Hermione Granger")) {
								btn.removeAll();
								JLabel c = new JLabel();
								btn.setBackground(new Color(46, 51, 28));
								c.setIcon(new ImageIcon("src/herm3.jpg"));
								c.setBounds(0, 0, c.getWidth(), c.getHeight());
								btn.add(c);

							}
							if (((ChampionCell) map[i][j]).getChamp().getName().equals("Draco Malfoy")) {
								btn.removeAll();
								JLabel c = new JLabel();
								btn.setBackground(new Color(46, 51, 28));
								c.setIcon(new ImageIcon("src/draco3.jpg"));
								c.setBounds(0, 0, c.getWidth(), c.getHeight());
								btn.add(c);

							}
							if (((ChampionCell) map[i][j]).getChamp().getName().equals("Harry Potter")) {
								btn.removeAll();
								JLabel c = new JLabel();
								btn.setBackground(new Color(46, 51, 28));
								c.setIcon(new ImageIcon("src/harry3.jpg"));
								c.setBounds(0, 0, c.getWidth(), c.getHeight());
								btn.add(c);
							}
							if (((ChampionCell) map[i][j]).getChamp().getName().equals("Cedric Diggory")) {
								btn.removeAll();
								JLabel c = new JLabel();
								btn.setBackground(new Color(46, 51, 28));
								c.setIcon(new ImageIcon("src/cedric3.jpg"));
								c.setBounds(0, 0, c.getWidth(), c.getHeight());
								btn.add(c);

							}
							if (((ChampionCell) map[i][j]).getChamp().getName().equals("Severus Snape")) {
								JLabel c = new JLabel();
								btn.setBackground(new Color(46, 51, 28));
								c.setIcon(new ImageIcon("src/snap3.jpg"));
								c.setBounds(0, 0, c.getWidth(), c.getHeight());
								btn.add(c);

							}
							if (((ChampionCell) map[i][j]).getChamp().getName().equals("Cho Chang")) {
								JLabel c = new JLabel();
								btn.setBackground(new Color(46, 51, 28));
								c.setIcon(new ImageIcon("src/cho3.jpg"));
								c.setBounds(0, 0, c.getWidth(), c.getHeight());
								btn.add(c);

							}
							if (((ChampionCell) map[i][j]).getChamp().getName().equals("Slim Abdelnaher")) {
								JLabel c = new JLabel();
								btn.setBackground(new Color(46, 51, 28));
								c.setIcon(new ImageIcon("src/slim 2.jpg"));
								c.setBounds(0, 0, c.getWidth(), c.getHeight());
								btn.add(c);
							}
							if (((ChampionCell) map[i][j]).getChamp().getName().equals("Ron Weasley")) {
								JLabel c = new JLabel();
								btn.setBackground(new Color(46, 51, 28));
								c.setIcon(new ImageIcon("src/ron3.jpg"));
								c.setBounds(0, 0, c.getWidth(), c.getHeight());
								btn.add(c);
							}
							if (((ChampionCell) map[i][j]).getChamp().getName().equals("Pomona Sprout")) {
								JLabel c = new JLabel();
								btn.setBackground(new Color(46, 51, 28));
								c.setIcon(new ImageIcon("src/pom3.jpg"));
								c.setBounds(0, 0, c.getWidth(), c.getHeight());
								btn.add(c);
							}
						}
					}

					// btn.setBackground(Color.BLUE);
				}
				if ((map[i][j] instanceof EmptyCell) || (map[i][j] instanceof CollectibleCell)
						|| (map[i][j] instanceof TreasureCell) || (map[i][j] instanceof CupCell)) {
					if ((getCurrentTask() instanceof FirstTask) && (i != 4 && j != 4)) {
						btn.setBackground(new Color(139, 113, 101));
						JLabel emp = new JLabel();
						emp.setIcon(new ImageIcon("src/shadow-rock-texture (1).jpg"));

						btn.add(emp);
					}
					if (getCurrentTask() instanceof SecondTask) {
						JLabel wa = new JLabel();
						wa.setIcon(new ImageIcon("src/wa.jpg"));
						btn.setBackground(new Color(8, 74, 109));
						btn.add(wa);
					}
					if (getCurrentTask() instanceof ThirdTask) {
						if (getCurrentTask() instanceof ThirdTask) {
							JLabel empty3 = new JLabel();
							empty3.setIcon(new ImageIcon("src/harrypotter/view/fe.jpg"));
							btn.setBackground(new Color(46, 51, 28));
							btn.add(empty3);
						}
					}
				}

				// btn.setBackground(Color.WHITE);

				getCurrentView().addCell(btn);
				btn.addMouseListener(this);

				// System.out.println(btn.getMouseListeners());
				btn.setName("Cell" + i + "" + j);
				// System.out.println(btn.getName());
			}
		}
		JPanel cell;
		JLabel egg = new JLabel();
		ImageIcon eggPic = new ImageIcon("src/harrypotter/view/1 (3).jpg");
		egg.setIcon(eggPic);
		// egg.setBackground(new Color(139, 113, 101));
		cell = (JPanel) gameMap.getComponent(44);
		if (getCurrentTask() instanceof FirstTask) {
			// cell.setOpaque(false);
			cell.add(egg);
			cell.setBackground(new Color(139, 113, 101));
		}

	}

	public void updateCurrentChampion(Champion c) {
		if (!(c.equals(firstTask.getCurrentChamp()))) {
			firstTaskView.getCurrentStat().setBackground(defaultColor);
			firstTaskView.setCurrentStat(
					champStats.get((champStats.indexOf(firstTaskView.getCurrentStat()) + 1) % getChampStats().size()));
			firstTaskView.getCurrentStat().setBackground(Color.LIGHT_GRAY);
		}

	}

	public void firing(JPanel cell) // throws InterruptedException
	{
		// ArrayList<Point> marked = firstTask.getMarkedCells();
		/*
		 * Point p1 = null; Point p2 = null; JLabel label1 = new JLabel();
		 * JLabel label2 = new JLabel();
		 */
		// JLabel label2 = new JLabel();
		// Icon icon = new ImageIcon(
		// "src/harrypotter/view/ezgif.com-resize-4.gif");
		JLabel label = new JLabel();

		label.setIcon(new ImageIcon("src/harrypotter/view/fire.gif"));
		// label2.setIcon(new ImageIcon(
		// "src/harrypotter/view/fire.gif"));
		// JPanel panel1 = null;
		// JPanel panel2 = null;
		if (getCurrentTask() instanceof FirstTask && !isHufflepuff) {
			cell.add(label);
			/*
			 * if (marked.size() > 0) { p1 = marked.get(0); panel1 = (JPanel)
			 * firstTaskView.getGameMap().getComponent(p1.x * 10 + p1.y);
			 * panel1.add(label1); } // } if (marked.size() > 1) {
			 * 
			 * p2 = marked.get(1); panel2 = (JPanel)
			 * firstTaskView.getGameMap().getComponent(p2.x * 10 + p2.y);
			 * panel2.add(label2);
			 */
		}
		firstTaskView.getGameMap().updateUI();
		try {
			firstTaskView.revalidate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		isHufflepuff = false;

	}

	public Champion getCurrentChamp() {
		return currentChamp;
	}

	public int getCurrentChampRow() {
		return currentChampRow;
	}

	public int getCurrentChampCol() {
		return currentChampCol;
	}

	public void moveLeft() throws Exception {
		currentTask.moveLeft();
	}

	public void moveForward() throws Exception {
		currentTask.moveForward();
	}

	public void moveRight() throws Exception {
		currentTask.moveRight();
	}

	public void moveBackward() throws Exception {
		currentTask.moveBackward();
	}

	public void setCurrentChampRow(int currentChampRow) {
		this.currentChampRow = currentChampRow;
	}

	public void setCurrentChampCol(int currentChampCol) {
		this.currentChampCol = currentChampCol;
	}

	public TaskView getCurrentView() {
		return currentView;
	}

	public void setCurrentView(TaskView currentView) {
		this.currentView = currentView;
	}

	public LinkedList<Status> getChampStats() {
		return champStats;
	}

	public JButton getStart() {
		return start;
	}

	public void setStart(JButton start) {
		this.start = start;
	}

	public void setCurrentTask(Task currentTask) {
		this.currentTask = currentTask;
	}

	public Task getCurrentTask() {
		return currentTask;
	}

//	public void mouseClicked(MouseEvent m) {
//		ArrayList<Point> marked = new ArrayList<>();
//		if (firstTask.getMarkedCells().get(0) != null)
//			marked.add(firstTask.getMarkedCells().get(0));
//		if (firstTask.getMarkedCells().get(1) != null)
//			marked.add(firstTask.getMarkedCells().get(1));
//		String indexDmgSt = null;
//		JPanel clickedDmg = null;
//		int indexDmg;
//		int currentIndex;
//		int diff;
//		if (damagingSpellFlag) {
//			clickedDmg = (JPanel) m.getSource();
//			indexDmgSt = clickedDmg.getName();
//			indexDmgSt = indexDmgSt.substring(4);
//			indexDmg = Integer.parseInt(indexDmgSt);
//			currentIndex = getCurrentTask().getCurrentChamp().getLocation().x * 10
//					+ getCurrentTask().getCurrentChamp().getLocation().y;
//			diff = currentIndex - indexDmg;
//			DamagingSpell DmgSpell = (DamagingSpell) getCurrentTask().getCurrentChamp().getSpells().get(IndOfDmgSpell);
//			try {
//				switch (diff) {
//				case 1:
//					getCurrentTask().castDamagingSpell(DmgSpell, Direction.LEFT);
//					if (castedSpells.peek() != null)
//						castedSpells.pop().setBackground(defaultSpellColor);
//					refreshMap(marked);
//					damagingSpellFlag = false;
//					break;
//				case -1:
//					getCurrentTask().castDamagingSpell(DmgSpell, Direction.RIGHT);
//					if (castedSpells.peek() != null)
//						castedSpells.pop().setBackground(defaultSpellColor);
//					refreshMap(marked);
//					damagingSpellFlag = false;
//					break;
//				case 10:
//					getCurrentTask().castDamagingSpell(DmgSpell, Direction.FORWARD);
//					if (castedSpells.peek() != null)
//						castedSpells.pop().setBackground(defaultSpellColor);
//					refreshMap(marked);
//					damagingSpellFlag = false;
//					break;
//				case -10:
//					getCurrentTask().castDamagingSpell(DmgSpell, Direction.BACKWARD);
//					refreshMap(marked);
//					if (castedSpells.peek() != null)
//						castedSpells.pop().setBackground(defaultSpellColor);
//					damagingSpellFlag = false;
//					break;
//				}
//				getCurrentView().updateStat(currentTask.isTraitActivated());
//			} catch (Exception e) {
//				getCurrentView().getConsole().append("\n" + e.getMessage() + "\nYour last action was refreshed.");
//				damagingSpellFlag = false;
//				while (!castedSpells.isEmpty()) {
//					castedSpells.pop().setBackground(defaultSpellColor);
//				}
//			}
//			// a mettre dans switch
//			getCurrentView().requestFocus();
//
//		} else if (relocatingSpellFlagLocat) {
//			getCurrentView().requestFocus();
//			spell = currentTask.getCurrentChamp().getSpells().get(indexOfSpell);
//			if (spell instanceof RelocatingSpell) {
//				rangeOfSpell = ((RelocatingSpell) spell).getRange();
//			} else
//				return;
//
//			if (!hasclicked1) { // clicked first panel
//				getCurrentView().requestFocus();
//				hasclicked1 = true;
//				click1Panel = (JPanel) m.getSource();
//				click1Panel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 3));
//				click1Panelname = click1Panel.getName();
//				CellOfD = click1Panelname.substring(4);
//				getCurrentView().requestFocus();
//
//			} else { // clicked second panel
//				getCurrentView().requestFocus();
//				hasclicked1 = false;
//				click2Panel = (JPanel) m.getSource();
//				click2Panel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
//				click2Panelname = click2Panel.getName();
//
//				champPosX = currentTask.getCurrentChamp().getLocation().x;
//				champPosY = currentTask.getCurrentChamp().getLocation().y;
//				CellOfChamp = champPosX * 10 + champPosY;
//				CellNo = click2Panelname.substring(4);
//				posOfTarget = Integer.parseInt(CellNo);
//				if (posOfTarget > CellOfChamp)
//					diffOfRanges = posOfTarget - CellOfChamp;
//
//				else
//					diffOfRanges = CellOfChamp - posOfTarget;
//				if (diffOfRanges >= 10)
//					finalRange = diffOfRanges / 10;
//				else
//					finalRange = diffOfRanges;
//
//				diffChampD = CellOfChamp - Integer.parseInt(CellOfD);
//
//				switch (diffChampD) {
//				case -1:
//					d = Direction.RIGHT;
//					break;
//				case 1:
//					d = Direction.LEFT;
//					break;
//				case 10:
//					d = Direction.FORWARD;
//					break;
//				case -10:
//					d = Direction.BACKWARD;
//					break;
//				}
//
//				diffChampT = CellOfChamp - posOfTarget;
//
//				if (0 < diffChampT && diffChampT < 10)
//					t = Direction.LEFT;
//				else {
//					if (0 > diffChampT && diffChampT > -10)
//						t = Direction.RIGHT;
//					else {
//						if (diffChampT >= 10)
//							t = Direction.FORWARD;
//						else {
//							if (diffChampT <= -10)
//								t = Direction.BACKWARD;
//						}
//
//					}
//				}
//				try {
//					getCurrentTask().castRelocatingSpell((RelocatingSpell) spell, d, t, finalRange);
//					relocatingSpellFlagLocat = false;
//					click1Panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
//					click2Panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
//					if (castedSpells.peek() != null)
//						castedSpells.pop().setBackground(defaultSpellColor);
//					currentView.getCurrentStat().updateUI();
//					getCurrentView().updateStat(currentTask.isTraitActivated());
//					refreshMap(marked);
//				} catch (Exception e) {
//					click1Panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
//					click2Panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
//					relocatingSpellFlagLocat = false;
//					relocatingSpellFlagDest = false;
//					while (!castedSpells.isEmpty()) {
//						castedSpells.pop().setBackground(defaultSpellColor);
//					}
//					getCurrentView().getConsole().append("\n" + e.getMessage() + "\nYour last action was eliminated.");
//				}
//				try {
//					refreshMap(marked);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				getCurrentView().requestFocus();
//			}
//
//		}
//	}

	public void mouseClicked(MouseEvent m) {
		ArrayList<Point> marked = new ArrayList<>();
		if (firstTask.getMarkedCells().get(0) != null)
			marked.add(firstTask.getMarkedCells().get(0));
		if (firstTask.getMarkedCells().get(1) != null)
			marked.add(firstTask.getMarkedCells().get(1));
		String indexDmgSt = null;
		JPanel clickedDmg = null;
		int indexDmg;
		int currentIndex;
		int diff;
		if (damagingSpellFlag) {
			clickedDmg = (JPanel) m.getSource();
			indexDmgSt = clickedDmg.getName();
			indexDmgSt = indexDmgSt.substring(4);
			indexDmg = Integer.parseInt(indexDmgSt);
			currentIndex = getCurrentTask().getCurrentChamp().getLocation().x * 10
					+ getCurrentTask().getCurrentChamp().getLocation().y;
			diff = currentIndex - indexDmg;
			DamagingSpell DmgSpell = (DamagingSpell) getCurrentTask().getCurrentChamp().getSpells().get(IndOfDmgSpell);
			try {
				switch (diff) {
				case 1:
					getCurrentTask().castDamagingSpell(DmgSpell, Direction.LEFT);
					if (castedSpells.peek() != null)
						castedSpells.pop().setBackground(defaultSpellColor);
					refreshMap(marked);
					damagingSpellFlag = false;
					break;
				case -1:
					getCurrentTask().castDamagingSpell(DmgSpell, Direction.RIGHT);
					if (castedSpells.peek() != null)
						castedSpells.pop().setBackground(defaultSpellColor);
					refreshMap(marked);
					damagingSpellFlag = false;
					break;
				case 10:
					getCurrentTask().castDamagingSpell(DmgSpell, Direction.FORWARD);
					if (castedSpells.peek() != null)
						castedSpells.pop().setBackground(defaultSpellColor);
					refreshMap(marked);
					damagingSpellFlag = false;
					break;
				case -10:
					getCurrentTask().castDamagingSpell(DmgSpell, Direction.BACKWARD);
					refreshMap(marked);
					if (castedSpells.peek() != null)
						castedSpells.pop().setBackground(defaultSpellColor);
					damagingSpellFlag = false;
					break;
				}
				getCurrentView().updateStat(currentTask.isTraitActivated());
			} catch (Exception e) {
				getCurrentView().getConsole().append("\n" + e.getMessage() + "\nYour last action was refreshed.");
				damagingSpellFlag = false;
				while (!castedSpells.isEmpty()) {
					castedSpells.pop().setBackground(defaultSpellColor);
				}
			}
			// a mettre dans switch
			getCurrentView().requestFocus();

		} else if (relocatingSpellFlagLocat) {
			getCurrentView().requestFocus();
			spell = currentTask.getCurrentChamp().getSpells().get(indexOfSpell);
			if (spell instanceof RelocatingSpell) {
				rangeOfSpell = ((RelocatingSpell) spell).getRange();
			} else
				return;

			if (!hasclicked1) { // clicked first panel
				getCurrentView().requestFocus();
				hasclicked1 = true;
				click1Panel = (JPanel) m.getSource();
				click1Panel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 3));
				click1Panelname = click1Panel.getName();
				CellOfD = click1Panelname.substring(4);
				getCurrentView().requestFocus();

			} else { // clicked second panel
				getCurrentView().requestFocus();
				hasclicked1 = false;
				click2Panel = (JPanel) m.getSource();
				click2Panel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
				click2Panelname = click2Panel.getName();

				champPosX = currentTask.getCurrentChamp().getLocation().x;
				champPosY = currentTask.getCurrentChamp().getLocation().y;
				CellOfChamp = champPosX * 10 + champPosY;
				CellNo = click2Panelname.substring(4);
				posOfTarget = Integer.parseInt(CellNo);
				if (posOfTarget > CellOfChamp)
					diffOfRanges = posOfTarget - CellOfChamp;

				else
					diffOfRanges = CellOfChamp - posOfTarget;
				if (diffOfRanges >= 10)
					finalRange = diffOfRanges / 10;
				else
					finalRange = diffOfRanges;

				diffChampD = CellOfChamp - Integer.parseInt(CellOfD);

				switch (diffChampD) {
				case -1:
					d = Direction.RIGHT;
					break;
				case 1:
					d = Direction.LEFT;
					break;
				case 10:
					d = Direction.FORWARD;
					break;
				case -10:
					d = Direction.BACKWARD;
					break;
				}

				diffChampT = CellOfChamp - posOfTarget;

				if (0 < diffChampT && diffChampT < 10)
					t = Direction.LEFT;
				else {
					if (0 > diffChampT && diffChampT > -10)
						t = Direction.RIGHT;
					else {
						if (diffChampT >= 10)
							t = Direction.FORWARD;
						else {
							if (diffChampT <= -10)
								t = Direction.BACKWARD;
						}

					}
				}
				try {
					getCurrentTask().castRelocatingSpell((RelocatingSpell) spell, d, t, finalRange);
					relocatingSpellFlagLocat = false;
					click1Panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
					click2Panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
					if (castedSpells.peek() != null)
						castedSpells.pop().setBackground(defaultSpellColor);
					currentView.getCurrentStat().updateUI();
					getCurrentView().updateStat(currentTask.isTraitActivated());
					refreshMap(marked);
				} catch (Exception e) {
					click1Panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
					click2Panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
					relocatingSpellFlagLocat = false;
					relocatingSpellFlagDest = false;
					while (!castedSpells.isEmpty()) {
						castedSpells.pop().setBackground(defaultSpellColor);
					}
					getCurrentView().getConsole().append("\n" + e.getMessage() + "\nYour last action was eliminated.");
				}
				try {
					refreshMap(marked);
				} catch (IOException e) {
					e.printStackTrace();
				}
				getCurrentView().requestFocus();
			}

		}
	}

	public void mouseEntered(MouseEvent e)
	{
		Point c = currentTask.getCurrentChamp().getLocation();
		if (!hasclicked1 && !(e.getSource().equals(click1Panel)) && !(e.getSource().equals(click2Panel))
				&& relocatingSpellFlagLocat) {
			int ind = getCurrentView().getGameMap().getComponentZOrder((Component) e.getSource());
			double indd = currentTask.getCurrentChamp().getLocation().distance(new Point(ind / 10, ind % 10));
			if (indd <= 1 && (((int) ind / 10) == c.x || ((int) ind % 10) == c.y))
				((JPanel) e.getSource()).setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
		} else if (!(e.getSource().equals(click1Panel)) && !(e.getSource().equals(click2Panel))
				&& relocatingSpellFlagLocat) {
			int ind = getCurrentView().getGameMap().getComponentZOrder((Component) e.getSource());
			double indd = currentTask.getCurrentChamp().getLocation().distance(new Point(ind / 10, ind % 10));
			if (indd <= rangeOfSpell
					&& (((int) ind / 10) == c.x || ((int) ind % 10) == c.y))
				((JPanel) e.getSource()).setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
		} else if (damagingSpellFlag) {
			int ind = getCurrentView().getGameMap().getComponentZOrder((Component) e.getSource());
			double indd = currentTask.getCurrentChamp().getLocation().distance(new Point(ind / 10, ind % 10));
			if (indd <= 1)
				((JPanel) e.getSource()).setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		}
	}

	public void mouseExited(MouseEvent e)
	{
		Point c = getCurrentTask().getCurrentChamp().getLocation();
		if (!hasclicked1 && !(e.getSource().equals(click1Panel)) && !(e.getSource().equals(click2Panel))
				&& relocatingSpellFlagLocat) {
			int ind = getCurrentView().getGameMap().getComponentZOrder((Component) e.getSource());
			double indd = currentTask.getCurrentChamp().getLocation().distance(new Point(ind / 10, ind % 10));
			if (indd <= 1 && (((int) ind / 10) == c.x || ((int) ind % 10) == c.y))
				((JPanel) e.getSource()).setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		} else if (!(e.getSource().equals(click1Panel)) && !(e.getSource().equals(click2Panel))
				&& relocatingSpellFlagLocat) {
			int ind = getCurrentView().getGameMap().getComponentZOrder((Component) e.getSource());
			double indd = currentTask.getCurrentChamp().getLocation().distance(new Point(ind / 10, ind % 10));
			if (indd <= rangeOfSpell && (((int) ind / 10) == c.x || ((int) ind % 10) == c.y))
				((JPanel) e.getSource()).setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		} else if (damagingSpellFlag) {
			int ind = getCurrentView().getGameMap().getComponentZOrder((Component) e.getSource());
			double indd = currentTask.getCurrentChamp().getLocation().distance(new Point(ind / 10, ind % 10));
			if (indd <= 1)
				((JPanel) e.getSource()).setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		}
	}

	public void refreshMap(ArrayList<Point> marked) throws IOException {
		Cell[][] map = getCurrentTask().getMap();
		JPanel gameMap = getCurrentView().getGameMap();
		BufferedImage myPicture;
		Point p = null;

		JPanel cell;

		JLabel merp = new JLabel();
		JLabel empty1 = new JLabel();
		JLabel empty2 = new JLabel();
		JLabel empty3 = new JLabel();
		JLabel egg = new JLabel();
		JLabel obstacle1 = new JLabel();

		ImageIcon eggPic = new ImageIcon("src/harrypotter/view/1 (3).jpg");
		egg.setIcon(eggPic);
		cell = (JPanel) gameMap.getComponent(44);
		if (getCurrentTask() instanceof FirstTask) {
			egg.setBounds(0, 0, eggPic.getIconWidth(), eggPic.getIconHeight());
			cell.add(egg);
		}

		int count = 1234;
		int m1 = 1234;
		int m2 = 1234;
		JLabel w;
		if (marked.get(0) != null)
			m1 = marked.get(0).x * 10 + marked.get(0).y;
		if (marked.get(1) != null)
			m2 = marked.get(1).x * 10 + marked.get(1).y;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				count = i * 10 + j;
				p = new Point(i, j);
				cell = (JPanel) gameMap.getComponent(count);

				cell.setBorder(null);
				cell.removeAll();
				if (map[i][j] instanceof WallCell) {
					JLabel wall = new JLabel();
					wall.setIcon(new ImageIcon("src/f.jpg"));
					cell.setBackground(new Color(46, 51, 28));
					wall.setBounds(0, 0, wall.getWidth(), wall.getHeight());
					// cell.add(wall);

					switch (count) {
					case 3:
					case 27:
						w = new JLabel();
						w.setIcon(new ImageIcon("src/wall2.jpg"));
						// cell = (JPanel) gameMap.getComponent(count);
						cell.add(w);
						break;

					case 21:
					case 31:
					case 13:
					case 15:
					case 25:
					case 35:
					case 37:
					case 47:
					case 57:
					case 59:
					case 69:
					case 75:
					case 79:
					case 76:
					case 71:
						JLabel w2 = new JLabel();
						w2.setIcon(new ImageIcon("src/wall4.jpg"));
						cell.add(w2);
						break;
					case 42:
					case 61:
					case 62:
					case 43:
					case 63:
					case 64:
					case 65:
					case 66:
					case 4:
					case 44:
					case 46:
						JLabel w1 = new JLabel();
						w1.setIcon(new ImageIcon("src/wall6.jpg"));
						cell.add(w1);
						break;

					case 5:
						JLabel w3 = new JLabel();
						w3.setIcon(new ImageIcon("src/wall7.jpg"));
						cell.add(w3);
						break;
					case 11:
					case 83:
					case 87:
					case 49:
					case 18:
						JLabel w4 = new JLabel();
						w4.setIcon(new ImageIcon("src/wall1.jpg"));
						cell.add(w4);
						break;
					case 81:
					case 93:
					case 85:
					case 97:
					case 89:
					case 23:
						JLabel w5 = new JLabel();
						w5.setIcon(new ImageIcon("src/wall5.jpg"));
						cell.add(w5);
						break;
					case 41:
						JLabel w6 = new JLabel();
						w6.setIcon(new ImageIcon("src/wall41.jpg"));
						cell.add(w6);
						break;
					case 60:
						JLabel w17 = new JLabel();
						w17.setIcon(new ImageIcon("src/wall701.png"));
						cell.add(w17);
						break;
					case 45:
					case 67:
					case 28:
						JLabel w8 = new JLabel();
						w8.setIcon(new ImageIcon("src/wall10.jpg"));
						cell.add(w8);
						break;

					}
				}
				/*
				 * if (map[i][j] instanceof TreasureCell) { cell.removeAll();
				 * cell.setBackground(Color.LIGHT_GRAY);
				 * 
				 * }
				 */
				if (map[i][j] instanceof ObstacleCell) {
					if (getCurrentTask() instanceof SecondTask) {
						merp = new JLabel();
						merp.setIcon(new ImageIcon("src/harrypotter/view/Unknown-2 (1).jpeg"));
						cell.setBackground(new Color(8, 74, 109));
						merp.setBounds(0, 0, merp.getWidth(), merp.getHeight());
						cell.add(merp);
					}

					else {
						if (getCurrentTask() instanceof FirstTask) {
							if (m1 == count || m2 == count) {
								firing(cell);
								cell.setLayout(new OverlayLayout(cell));
							}
							obstacle1 = new JLabel();
							cell.setBackground(new Color(139, 113, 101));
							obstacle1.setIcon(new ImageIcon("src/harrypotter/view/obstacle (1).jpg"));
							obstacle1.setBounds(0, 0, obstacle1.getWidth(), obstacle1.getHeight());
							cell.add(obstacle1);
						}

						else {
							if (getCurrentTask() instanceof SecondTask) {
								obstacle1 = new JLabel();
								cell.setBackground(new Color(8, 74, 109));
								obstacle1.setIcon(new ImageIcon("src/rock (2).jpg"));
								obstacle1.setBounds(0, 0, obstacle1.getWidth(), obstacle1.getHeight());
								cell.add(obstacle1);
							} else {
								if (getCurrentTask() instanceof ThirdTask) {
									obstacle1 = new JLabel();
									cell.setBackground(new Color(46, 51, 28));
									obstacle1.setIcon(new ImageIcon("src/rock3.jpg"));
									obstacle1.setBounds(0, 0, obstacle1.getWidth(), obstacle1.getHeight());
									cell.add(obstacle1);
								}
							}

						}
						((JComponent) gameMap.getComponent(i * 10 + j))
								.setToolTipText("HP: " + ((ObstacleCell) map[i][j]).getObstacle().getHp());
					}
				}
				/*
				 * if (map[i][j] instanceof CollectibleCell) {
				 * cell.setBackground(Color.GREEN); }
				 */

				if (map[i][j] instanceof ChampionCell) {
					if (getCurrentTask() instanceof FirstTask) {

						if (m1 == count || m2 == count) {
							firing(cell);
							cell.setLayout(new OverlayLayout(cell));
						}

						if (((ChampionCell) map[i][j]).getChamp().getName().equals("Harry Potter")) {
							JLabel c = new JLabel();
							cell.setBackground(new Color(130, 113, 101));
							c.setIcon(new ImageIcon("src/harrypotter/view/harry.jpg"));
							c.setBounds(0, 0, c.getWidth(), c.getHeight());
							cell.add(c);
						}
						if (((ChampionCell) map[i][j]).getChamp().getName().equals("Draco Malfoy")) {
							JLabel c = new JLabel();
							cell.setBackground(new Color(130, 113, 101));
							c.setIcon(new ImageIcon("src/harrypotter/view/g.jpg"));
							c.setBounds(0, 0, c.getWidth(), c.getHeight());
							cell.add(c);
						}
						if (((ChampionCell) map[i][j]).getChamp().getName().equals("Cedric Diggory")) {
							JLabel c = new JLabel();
							cell.setBackground(new Color(130, 113, 101));
							c.setIcon(new ImageIcon("src/harrypotter/view/sd.jpg"));
							c.setBounds(0, 0, c.getWidth(), c.getHeight());
							cell.add(c);
						}
						if (((ChampionCell) map[i][j]).getChamp().getName().equals("Hermione Granger")) {
							JLabel c = new JLabel();
							cell.setBackground(new Color(130, 113, 101));
							c.setIcon(new ImageIcon("src/harrypotter/view/herm.jpg"));
							c.setBounds(0, 0, c.getWidth(), c.getHeight());
							cell.add(c);
						}
						if (((ChampionCell) map[i][j]).getChamp().getName().equals("Luna Lovegood")) {
							JLabel c = new JLabel();
							cell.setBackground(new Color(130, 113, 101));
							c.setIcon(new ImageIcon("src/ASSX.jpg"));
							c.setBounds(0, 0, c.getWidth(), c.getHeight());
							cell.add(c);
						}
						if (((ChampionCell) map[i][j]).getChamp().getName().equals("Severus Snape")) {
							JLabel c = new JLabel();
							cell.setBackground(new Color(130, 113, 101));
							c.setIcon(new ImageIcon("src/snap1.jpg"));
							c.setBounds(0, 0, c.getWidth(), c.getHeight());
							cell.add(c);
						}
						if (((ChampionCell) map[i][j]).getChamp().getName().equals("Cho Chang")) {
							JLabel c = new JLabel();
							cell.setBackground(new Color(130, 113, 101));
							c.setIcon(new ImageIcon("src/cho1.jpg"));
							c.setBounds(0, 0, c.getWidth(), c.getHeight());
							cell.add(c);
						}
						if (((ChampionCell) map[i][j]).getChamp().getName().equals("Slim Abdelnaher")) {
							JLabel c = new JLabel();
							cell.setBackground(new Color(130, 113, 101));
							c.setIcon(new ImageIcon("src/slim 3.jpg"));
							c.setBounds(0, 0, c.getWidth(), c.getHeight());
							cell.add(c);
						}
						if (((ChampionCell) map[i][j]).getChamp().getName().equals("Ron Weasley")) {
							JLabel c = new JLabel();
							cell.setBackground(new Color(130, 113, 101));
							c.setIcon(new ImageIcon("src/ron1.jpg"));
							c.setBounds(0, 0, c.getWidth(), c.getHeight());
							cell.add(c);
						}
						if (((ChampionCell) map[i][j]).getChamp().getName().equals("Pomona Sprout")) {
							JLabel c = new JLabel();
							cell.setBackground(new Color(130, 113, 101));
							c.setIcon(new ImageIcon("src/pom1.jpg"));
							c.setBounds(0, 0, c.getWidth(), c.getHeight());
							cell.add(c);
						}
						// obstacle1 = new JLabel(((ChampionCell)
						// map[i][j]).getChamp().getName());
						// cell.add(obstacle1);
						// cell.setBackground(Color.BLUE);
					} else {
						if (getCurrentTask() instanceof SecondTask) {
							if (((ChampionCell) map[i][j]).getChamp().getName().equals("Harry Potter")) {
								JLabel c = new JLabel();
								cell.setBackground(new Color(8, 74, 109));
								c.setIcon(new ImageIcon("src/harrypotter/view/h maya.jpg"));
								c.setBounds(0, 0, c.getWidth(), c.getHeight());
								cell.add(c);
							}
							if (((ChampionCell) map[i][j]).getChamp().getName().equals("Hermione Granger")) {
								JLabel c = new JLabel();
								cell.setBackground(new Color(8, 74, 109));
								c.setIcon(new ImageIcon("src/harrypotter/view/15.jpg"));
								c.setBounds(0, 0, c.getWidth(), c.getHeight());
								cell.add(c);

							}
							if (((ChampionCell) map[i][j]).getChamp().getName().equals("Cedric Diggory")) {
								JLabel c = new JLabel();
								cell.setBackground(new Color(8, 74, 109));
								c.setIcon(new ImageIcon("src/harrypotter/view/12.jpg"));
								c.setBounds(0, 0, c.getWidth(), c.getHeight());
								cell.add(c);

							}
							if (((ChampionCell) map[i][j]).getChamp().getName().equals("Draco Malfoy")) {
								JLabel c = new JLabel();
								cell.setBackground(new Color(8, 74, 109));
								c.setIcon(new ImageIcon("src/harrypotter/view/14 (1).jpg"));
								c.setBounds(0, 0, c.getWidth(), c.getHeight());
								cell.add(c);

							}
							if (((ChampionCell) map[i][j]).getChamp().getName().equals("Luna Lovegood")) {
								cell.removeAll();
								JLabel c = new JLabel();
								cell.setBackground(new Color(8, 74, 109));
								c.setIcon(new ImageIcon("src/d.jpg"));
								c.setBounds(0, 0, c.getWidth(), c.getHeight());
								cell.add(c);

							}
							if (((ChampionCell) map[i][j]).getChamp().getName().equals("Severus Snape")) {
								JLabel c = new JLabel();
								cell.setBackground(new Color(8, 74, 109));
								c.setIcon(new ImageIcon("src/snap2.jpg"));
								c.setBounds(0, 0, c.getWidth(), c.getHeight());
								cell.add(c);

							}
							if (((ChampionCell) map[i][j]).getChamp().getName().equals("Cho Chang")) {
								JLabel c = new JLabel();
								cell.setBackground(new Color(8, 74, 109));
								c.setIcon(new ImageIcon("src/cho2.jpg"));
								c.setBounds(0, 0, c.getWidth(), c.getHeight());
								cell.add(c);

							}
							if (((ChampionCell) map[i][j]).getChamp().getName().equals("Slim Abdelnaher")) {
								JLabel c = new JLabel();
								cell.setBackground(new Color(8, 74, 109));
								c.setIcon(new ImageIcon("src/slim 1.jpg"));
								c.setBounds(0, 0, c.getWidth(), c.getHeight());
								cell.add(c);
							}

							if (((ChampionCell) map[i][j]).getChamp().getName().equals("Ron Weasley")) {
								JLabel c = new JLabel();
								cell.setBackground(new Color(8, 74, 109));
								c.setIcon(new ImageIcon("src/ron2.jpg"));
								c.setBounds(0, 0, c.getWidth(), c.getHeight());
								cell.add(c);
							}
							if (((ChampionCell) map[i][j]).getChamp().getName().equals("Pomona Sprout")) {
								JLabel c = new JLabel();
								cell.setBackground(new Color(8, 74, 109));
								c.setIcon(new ImageIcon("src/pom2.jpg"));
								c.setBounds(0, 0, c.getWidth(), c.getHeight());
								cell.add(c);
							}

						} else {
							if (((ChampionCell) map[i][j]).getChamp().getName().equals("Luna Lovegood")) {
								cell.removeAll();
								JLabel c = new JLabel();
								cell.setBackground(new Color(46, 51, 28));
								c.setIcon(new ImageIcon("src/luna3.jpg"));
								c.setBounds(0, 0, c.getWidth(), c.getHeight());
								cell.add(c);

							}
							if (((ChampionCell) map[i][j]).getChamp().getName().equals("Hermione Granger")) {
								cell.removeAll();
								JLabel c = new JLabel();
								cell.setBackground(new Color(46, 51, 28));
								c.setIcon(new ImageIcon("src/herm3.jpg"));
								c.setBounds(0, 0, c.getWidth(), c.getHeight());
								cell.add(c);

							}
							if (((ChampionCell) map[i][j]).getChamp().getName().equals("Draco Malfoy")) {
								cell.removeAll();
								JLabel c = new JLabel();
								cell.setBackground(new Color(46, 51, 28));
								c.setIcon(new ImageIcon("src/draco3.jpg"));
								c.setBounds(0, 0, c.getWidth(), c.getHeight());
								cell.add(c);

							}
							if (((ChampionCell) map[i][j]).getChamp().getName().equals("Harry Potter")) {
								cell.removeAll();
								JLabel c = new JLabel();
								cell.setBackground(new Color(46, 51, 28));
								c.setIcon(new ImageIcon("src/harry3.jpg"));
								c.setBounds(0, 0, c.getWidth(), c.getHeight());
								cell.add(c);
							}
							if (((ChampionCell) map[i][j]).getChamp().getName().equals("Cedric Diggory")) {
								cell.removeAll();
								JLabel c = new JLabel();
								cell.setBackground(new Color(46, 51, 28));
								c.setIcon(new ImageIcon("src/cedric3.jpg"));
								c.setBounds(0, 0, c.getWidth(), c.getHeight());
								cell.add(c);

							}
							if (((ChampionCell) map[i][j]).getChamp().getName().equals("Severus Snape")) {
								JLabel c = new JLabel();
								cell.setBackground(new Color(46, 51, 28));
								c.setIcon(new ImageIcon("src/snap3.jpg"));
								c.setBounds(0, 0, c.getWidth(), c.getHeight());
								cell.add(c);

							}
							if (((ChampionCell) map[i][j]).getChamp().getName().equals("Cho Chang")) {
								JLabel c = new JLabel();
								cell.setBackground(new Color(46, 51, 28));
								c.setIcon(new ImageIcon("src/cho3.jpg"));
								c.setBounds(0, 0, c.getWidth(), c.getHeight());
								cell.add(c);

							}
							if (((ChampionCell) map[i][j]).getChamp().getName().equals("Slim Abdelnaher")) {
								JLabel c = new JLabel();
								cell.setBackground(new Color(46, 51, 28));
								c.setIcon(new ImageIcon("src/slim 2.jpg"));
								c.setBounds(0, 0, c.getWidth(), c.getHeight());
								cell.add(c);
							}
							if (((ChampionCell) map[i][j]).getChamp().getName().equals("Ron Weasley")) {
								JLabel c = new JLabel();
								cell.setBackground(new Color(46, 51, 28));
								c.setIcon(new ImageIcon("src/ron3.jpg"));
								c.setBounds(0, 0, c.getWidth(), c.getHeight());
								cell.add(c);
							}
							if (((ChampionCell) map[i][j]).getChamp().getName().equals("Pomona Sprout")) {
								JLabel c = new JLabel();
								cell.setBackground(new Color(46, 51, 28));
								c.setIcon(new ImageIcon("src/pom3.jpg"));
								c.setBounds(0, 0, c.getWidth(), c.getHeight());
								cell.add(c);
							}

						}
					}
				}

				if (map[i][j] instanceof EmptyCell || map[i][j] instanceof CollectibleCell
						|| map[i][j] instanceof CupCell || map[i][j] instanceof TreasureCell) {
					// myPicture = new BufferedImage(0, 0, 5);
					// ImageIcon image = new
					// ImageIcon("C:\\Users\\User\\Desktop\\map.jpg");
					// JLabel picLabel = new JLabel("", image,
					// JLabel.CENTER);
					// int heightOfPanel = cell.getHeight();
					// int widthOfPanel = cell.getWidth();
					// JLabel picLabel = new JLabel(new
					// ImageIcon(myPicture));
					// cell.setBackground(Color.BLACK);
					// picLabel.setSize(widthOfPanel, heightOfPanel);
					// cell.setBackground(Color.WHITE);
					// cell.add(picLabel, BorderLayout.CENTER);
					if (getCurrentTask() instanceof FirstTask && ((i != 4) && (j != 4))) {

						if (m1 == count || m2 == count) {
							firing(cell);
							cell.setLayout(new OverlayLayout(cell));
						}

						cell.setBackground(new Color(130, 113, 101));
						empty1 = new JLabel();
						empty1.setIcon(new ImageIcon("src/shadow-rock-texture (1).jpg"));
						cell.add(empty1);
					}
					if (getCurrentTask() instanceof SecondTask) {
						cell.setBackground(new Color(8, 74, 109));
						empty2 = new JLabel();
						empty2.setIcon(new ImageIcon("src/wa.jpg"));
						cell.add(empty2);
					}
					if (getCurrentTask() instanceof ThirdTask) {
						empty3 = new JLabel();
						empty3.setIcon(new ImageIcon("src/harrypotter/view/fe.jpg"));
						cell.setBackground(new Color(46, 51, 28));
						cell.add(empty3);
					}

				}
				if (getCurrentTask() instanceof FirstTask) {

					if (m1 == count || m2 == count) {
						firing(cell);
						cell.setLayout(new OverlayLayout(cell));
					}
					((JPanel) gameMap.getComponent(44)).add(egg);
					cell.setBackground(new Color(130, 113, 101));
					// ((JPanel)
					// gameMap.getComponent(44)).setBackground(Color.WHITE);
				}

			}
			// for(int i =0; i<)
			// firing(marked);
			getCurrentView().revalidate();
			getCurrentView().repaint();
			getCurrentView().requestFocus();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) throws Exception {
		TriwizardTournamentGUI tmp = new TriwizardTournamentGUI();
	}
}
