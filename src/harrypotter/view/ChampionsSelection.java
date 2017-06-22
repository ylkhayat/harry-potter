package harrypotter.view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.NoRouteToHostException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.SynchronousQueue;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.tools.Tool;

import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.character.SlytherinWizard;

public class ChampionsSelection extends JFrame implements NewChampionListener
{
	JPanel up;
	LinkedList<JButton> chosenBtn;
	JButton firstChosen;
	JButton secondChosen;
	JButton thirdChosen;
	JButton fourthChosen;
	LinkedList<JPanel> chosen;
	JPanel down;
	JButton addChamp;
	JPanel house1;
	JLabel h1Label;
	JPanel h1Down;
	JPanel h1Panel;
//	JPanel h1ChampPanel;
	JPanel house2;
	JLabel h2Label;
	JPanel h2Down;
	JPanel h2Panel;
//	JPanel h2ChampPanel;
	JPanel house3;
	JLabel h3Label;
	JPanel h3Down;
	JPanel h3Panel;
//	JPanel h3ChampPanel;
	JPanel house4;
	JLabel h4Label;
	JPanel h4Down;
	JPanel h4Panel;
//	JPanel h4ChampPanel;
	JButton chooseSpells;
	JPanel firstChosenChamp;
	JPanel secondChosenChamp;
	JPanel thirdChosenChamp;
	JPanel fourthChosenChamp;
	JButton refresh = new JButton("Refresh");
	JButton refresh1 = new JButton("Refresh");
	JButton refresh2 = new JButton("Refresh");
	JButton refresh3 = new JButton("Refresh");
	int checks = 0;
	SpellsSelection view;
	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	ChampionSelectionListener listener;
	public ChampionsSelection() throws IOException
	{
		super();
		
		screen.height = screen.height - 62;
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		firstChosenChamp = new JPanel(new GridLayout(2, 0));
		secondChosenChamp = new JPanel(new GridLayout(2, 0));
		thirdChosenChamp = new JPanel(new GridLayout(2, 0));
		fourthChosenChamp = new JPanel(new GridLayout(2, 0));
		chosen = new LinkedList<JPanel>();
		chosen.add(firstChosenChamp);
		chosen.add(secondChosenChamp);
		chosen.add(thirdChosenChamp);
		chosen.add(fourthChosenChamp);
		chosenBtn = new LinkedList<JButton>();
		firstChosen = new JButton();
		secondChosen = new JButton();
		thirdChosen = new JButton();
		fourthChosen = new JButton();
		chosenBtn.add(firstChosen);
		chosenBtn.add(secondChosen);
		chosenBtn.add(thirdChosen);
		chosenBtn.add(fourthChosen);
		view = new SpellsSelection();
		this.setLayout(null);
		up = new JPanel(new GridLayout(0, 4));
		up.setBounds(0, 0, screen.width, screen.height/4);
//		up.setSize(1366, 200);
		this.add(up);
		down = new JPanel(new GridLayout(2,2,15,15));
		down.setBounds(10, screen.height/4, screen.width-20, screen.height/2);
		this.add(down);
		refresh.setBackground(Color.lightGray);
		refresh.addActionListener(this);
		refresh1.setBackground(Color.lightGray);
		refresh1.addActionListener(this);
		refresh2.setBackground(Color.lightGray);
		refresh2.addActionListener(this);
		refresh3.setBackground(Color.lightGray);
		refresh3.addActionListener(this);
		house1 = new JPanel(new GridLayout(2,0,5,5));
		h1Label = new JLabel("Gryffindor Wizards",SwingConstants.CENTER);
		house1.add(h1Label);
		h1Panel = new JPanel(new GridLayout(0, 2, 10, 10));
//		h1Label.setFont(new Font("SignPainter",Font.BOLD,45));
		house1.add(h1Panel);
		JButton gWizard1 = new JButton("Harry Potter");
//		gWizard1.setFont(new Font("SignPainter",Font.BOLD,30));
		gWizard1.addActionListener(this);
		JButton gWizard2 = new JButton("Hermione Granger");
//		gWizard2.setFont(new Font("SignPainter",Font.BOLD,30));
		gWizard2.addActionListener(this);
		JButton gWizard3 = new JButton("Ron Weasley");
//		gWizard3.setFont(new Font("SignPainter",Font.BOLD,30));
		gWizard3.addActionListener(this);
		h1Panel.add(gWizard1);
		h1Panel.add(gWizard2);
		h1Panel.add(gWizard3);
		down.add(house1);
		house2 = new JPanel(new GridLayout(2,0,5,5));
		h2Label = new JLabel("Hufflepuff Wizards",SwingConstants.CENTER);
//		h2Label.setFont(new Font("SignPainter",Font.BOLD,45));
		house2.add(h2Label);
		h2Panel = new JPanel(new GridLayout(0,2,10,10));
		house2.add(h2Panel);
		JButton hWizard1 = new JButton("Cedric Diggory");
//		hWizard1.setFont(new Font("SignPainter",Font.BOLD,30));
		hWizard1.addActionListener(this);
		JButton hWizard2 = new JButton("Pomona Sprout");
//		hWizard2.setFont(new Font("SignPainter",Font.BOLD,30));
		hWizard2.addActionListener(this);
		h2Panel.add(hWizard1);
		h2Panel.add(hWizard2);
		down.add(house2);
		house3 = new JPanel(new GridLayout(2,0,5,5));
		h3Label = new JLabel("Ravenclaw Wizards",SwingConstants.CENTER);
//		h3Label.setFont(new Font("SignPainter",Font.BOLD,45));
		house3.add(h3Label);
		h3Panel = new JPanel(new GridLayout(0,2,10,10));
		house3.add(h3Panel);
		JButton rWizard1 = new JButton("Cho Chang");
//		rWizard1.setFont(new Font("SignPainter",Font.BOLD,30));
		rWizard1.addActionListener(this);
		JButton rWizard2 = new JButton("Luna Lovegood");
//		rWizard2.setFont(new Font("SignPainter",Font.BOLD,30));
		rWizard2.addActionListener(this);
		h3Panel.add(rWizard1);
		h3Panel.add(rWizard2);
		down.add(house3);
		house4 = new JPanel(new GridLayout(2,0,5,5));
		h4Label = new JLabel("Slytherin Wizards",SwingConstants.CENTER);
//		h4Label.setFont(new Font("SignPainter",Font.BOLD,45));
		house4.add(h4Label);
		h4Panel = new JPanel(new GridLayout(0,2,10,10));
		house4.add(h4Panel);
		JButton sWizard1 = new JButton("Draco Malfoy");
//		sWizard1.setFont(new Font("SignPainter",Font.BOLD,30));
		sWizard1.addActionListener(this);
		JButton sWizard2 = new JButton("Severus Snape");
//		sWizard2.setFont(new Font("SignPainter",Font.BOLD,30));
		sWizard2.addActionListener(this);
		JButton sWizard3 = new JButton("Slim Abdelnaher");
//		sWizard3.setFont(new Font("SignPainter",Font.BOLD,30));
		sWizard3.addActionListener(this);
		h4Panel.add(sWizard1);
		h4Panel.add(sWizard2);
		h4Panel.add(sWizard3);
		down.add(house4);
		addChamp = new JButton("Add your champion");
//		addChamp.setFont(new Font("SignPainter",Font.BOLD,30));
		addChamp.setBounds(screen.width*2/9, screen.height*4/5, screen.width/5, screen.height/6+1);
		addChamp.addActionListener(this);
		add(addChamp);
		chooseSpells = new JButton("Chosen Ones");
//		chooseSpells.setFont(new Font("SignPainter",Font.BOLD,30));
		chooseSpells.setBounds(screen.width*5/9, screen.height*4/5-1, screen.width/5, screen.height/6+1);
		chooseSpells.addActionListener(this);
		add(chooseSpells);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void onAddingNewChampion(NewChampion tmp)
	{
		Profession prof = tmp.getProf();
		JButton tmpo = new JButton(tmp.getName());
		tmpo.setBackground(Color.CYAN);
		switch(prof)
		{
		case GRYFFINDOR : 
			h1Panel.add(tmpo);
			tmpo.addActionListener(this);
			break;
		case HUFFLEPUFF : 
			h2Panel.add(tmpo);
			tmpo.addActionListener(this);
			break;
		case RAVENCLAW : 
			h3Panel.add(tmpo);
			tmpo.addActionListener(this);
			break;
		case SLYTHERIN : 
			h4Panel.add(tmpo);
			tmpo.addActionListener(this);
			break;
		}
	}
	public void actionPerformed(ActionEvent e) {
		JButton tmp = (JButton) e.getSource();
		if(e.getSource().equals(addChamp))
		{
			AddChampionView tmpAdd = new AddChampionView();
			tmpAdd.setListener(this);
	    }
		else
			if(e.getSource().equals(chooseSpells))
			{
			}
			else
				if(e.getSource().equals(refresh))
				{
					int ind = chosen.indexOf(refresh.getParent());
					JButton selectedChamp = chosenBtn.get(ind);
					selectedChamp.setEnabled(true);
					chosenBtn.addLast(chosenBtn.remove(ind));
					JPanel current = chosen.get(ind);
					current.setFont(new Font("SignPainter",Font.BOLD,30));
					chosen.addLast(chosen.remove(ind));
					up.remove(current);
					current.removeAll();
					if(listener != null)
						listener.removeChampion(selectedChamp.getText());
					checks--;
					up.updateUI();
				}
				else
					if(e.getSource().equals(refresh1))
					{
						int ind = chosen.indexOf(refresh1.getParent());
						JButton selectedChamp = chosenBtn.get(ind);
						selectedChamp.setEnabled(true);
						chosenBtn.addLast(chosenBtn.remove(ind));
						JPanel current = chosen.get(ind);
						current.setFont(new Font("SignPainter",Font.BOLD,30));
						chosen.addLast(chosen.remove(ind));
						up.remove(current);
						current.removeAll();
						if(listener != null)
							listener.removeChampion(selectedChamp.getText());
						checks--;
						up.updateUI();
					}
					else
						if(e.getSource().equals(refresh2))
						{
							int ind = chosen.indexOf(refresh2.getParent());
							JButton selectedChamp = chosenBtn.get(ind);
							selectedChamp.setEnabled(true);
							chosenBtn.addLast(chosenBtn.remove(ind));
							JPanel current = chosen.get(ind);
							current.setFont(new Font("SignPainter",Font.BOLD,30));
							chosen.addLast(chosen.remove(ind));
							up.remove(current);
							current.removeAll();
							if(listener != null)
								listener.removeChampion(selectedChamp.getText());
							checks--;
							up.updateUI();
						}
						else
							if(e.getSource().equals(refresh3))
							{
								int ind = chosen.indexOf(refresh3.getParent());
								JButton selectedChamp = chosenBtn.get(ind);
								selectedChamp.setEnabled(true);
								chosenBtn.addLast(chosenBtn.remove(ind));
								JPanel current = chosen.get(ind);
								current.setFont(new Font("SignPainter",Font.BOLD,30));
								chosen.addLast(chosen.remove(ind));
								up.remove(current);
								current.removeAll();
								if(listener != null)
									listener.removeChampion(selectedChamp.getText());
								checks--;
								up.updateUI();
							}
							else
								if(checks < 4)
								{
									if(checks == 0)
									{
										((Component) e.getSource()).setEnabled(false);
										JPanel current = chosen.get(0);
										current.setFont(new Font("SignPainter",Font.BOLD,30));
										current.add(new JLabel(tmp.getText(), SwingConstants.CENTER));
										addChosen(current);
										chosenBtn.remove(0);
										chosenBtn.add(0,(JButton) e.getSource());
//										addProp(tmp,view.getFirstChampStats());
										addHouse(tmp);
//										view.getFirstChampStats().setName(tmp.getText());
										up.add(current);
										checks++;
									}
									else
										if(checks == 1)
										{
											((Component) e.getSource()).setEnabled(false);
											JPanel current = chosen.get(checks);
											current.setFont(new Font("SignPainter",Font.BOLD,30));
											current.add(new JLabel(tmp.getText(), SwingConstants.CENTER));
											addChosen(current);
											chosenBtn.remove(checks);
											chosenBtn.add(checks,(JButton) e.getSource());
//											addProp(tmp,view.getSecondChampStats());
											addHouse(tmp);
//											view.getSecondChampStats().setName(tmp.getText());
											up.add(current);
											checks++;
										}
										else
											if(checks == 2)
											{
												((Component) e.getSource()).setEnabled(false);
												JPanel current = chosen.get(checks);
												current.setFont(new Font("SignPainter",Font.BOLD,30));
												current.add(new JLabel(tmp.getText(), SwingConstants.CENTER));
												addChosen(current);
												chosenBtn.remove(checks);
												chosenBtn.add(checks,(JButton) e.getSource());
//												addProp(tmp,view.getThirdChampStats());
												addHouse(tmp);
//												view.getThirdChampStats().setName(tmp.getText());
												up.add(current);
												checks++;
											}
											else
												if(checks == 3)
												{
													((Component) e.getSource()).setEnabled(false);
													JPanel current = chosen.get(checks);
													current.setFont(new Font("SignPainter",Font.BOLD,30));
													secondChosenChamp.setFont(new Font("SignPainter",Font.BOLD,30));
													thirdChosenChamp.setFont(new Font("SignPainter",Font.BOLD,30));
													fourthChosenChamp.setFont(new Font("SignPainter",Font.BOLD,30));
													current.add(new JLabel(tmp.getText(), SwingConstants.CENTER));
													addChosen(current);
													chosenBtn.remove(checks);
													chosenBtn.add(checks,(JButton) e.getSource());
//													addProp(tmp,view.getFourthChampStats());
													addHouse(tmp);
//													view.getFourthChampStats().setName(tmp.getText());
													up.add(current);
													checks++;
												}
								}
			revalidate();
	}
	private void addHouse(JButton s) {
		JPanel source = (JPanel) s.getParent();
		if(source.equals(h1Panel) && listener != null)
		{
			listener.addChampion(new GryffindorWizard(s.getText()));
		}
		else
			if(source.equals(h2Panel)&& listener != null)
			{
				listener.addChampion(new HufflepuffWizard(s.getText()));
			}
			else
				if(source.equals(h3Panel) && listener != null)
				{
					listener.addChampion(new RavenclawWizard(s.getText()));
				}
				else
				{
					listener.addChampion(new SlytherinWizard(s.getText()));
				}
	}
	public void setPanelEnabled(JPanel panel, Boolean isEnabled) {
	    panel.setEnabled(isEnabled);

	    Component[] components = panel.getComponents();

	    for(int i = 0; i < components.length; i++) {
	        if(components[i].getClass().getName() == "javax.swing.JPanel") {
	            setPanelEnabled((JPanel) components[i], isEnabled);
	        }

	        components[i].setEnabled(isEnabled);
	    }
	}
	public void addNewChampion()
	{
		AddChampionView tmp = new AddChampionView();
		tmp.setListener(this);
	}
	public void addChosen(JPanel p)
	{
		switch(chosen.indexOf(p))
		{
		case 0 : p.add(refresh); break;
		case 1 : p.add(refresh1); break;
		case 2 : p.add(refresh2); break;
		case 3 : p.add(refresh3);
		}
		p.revalidate();
	}
	public ChampionSelectionListener getListener() {
		return listener;
	}
	public void setListener(ChampionSelectionListener listener) {
		this.listener = listener;
	}
	public LinkedList<JButton> getChosenBtn() {
		return chosenBtn;
	}
	public LinkedList<JPanel> getChosen() {
		return chosen;
	}
	public int getChecks() {
		return checks;
	}
	public SpellsSelection getView() {
		return view;
	}
	public JButton getChooseSpells() {
		return chooseSpells;
	}
	public JButton getRefresh() {
		return refresh;
	}
	public JButton getRefresh1() {
		return refresh1;
	}
	public JButton getRefresh2() {
		return refresh2;
	}
	public JButton getRefresh3() {
		return refresh3;
	}

}
