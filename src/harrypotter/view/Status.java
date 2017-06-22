package harrypotter.view;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import harrypotter.controller.TriwizardTournamentGUI;
import harrypotter.model.character.Champion;
import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.character.SlytherinWizard;
import harrypotter.model.magic.Potion;

public class Status extends JPanel{
	private Champion champ;
	//private TriwizardTournamentGUI ttGUI;
	private JLabel name;
	private Profession prof;
	private JPanel icon;
	private JLabel champHP;
	private JLabel champIP;
	private JPanel champSpells;
	private JPanel champInventory;
	private JLabel champCooldown;
	private JButton useTrait;
	private ArrayList<JButton> clicked;
	public Status() {
		super(new GridLayout(0, 1));
		clicked = new ArrayList<JButton>();
		icon = new JPanel();
		icon.setLayout(new GridLayout(1, 2));
		add(icon);
		name = new JLabel();
		add(name);
		name.setFont(new Font("SignPainter",Font.BOLD,25));
		useTrait = new JButton("Use my Trait");
		//useTrait.addActionListener(this);
		add(useTrait);
		getUseTrait().createToolTip();
		//traitDescription();
		champHP = new JLabel();
		add(champHP);
		champIP = new JLabel();
		add(champIP);
		champSpells = new JPanel(new GridLayout(0, 3));
		add(champSpells);
		champInventory = new JPanel(new GridLayout(0, 2));
		add(champInventory);
		champCooldown = new JLabel();
		add(champCooldown);
		revalidate();
	}
	public void traitDescription(Champion champ)
	{
		if (champ instanceof GryffindorWizard)
			getUseTrait().setToolTipText("By activating your trait you get the chance to make 2 moves instead of one" + ", CoolDown:" + champ.getTraitCooldown());
		if (champ instanceof SlytherinWizard)
			getUseTrait().setToolTipText("By activating your trait you get the chance to jump over a cell in your preferred direction"  + ", CoolDown:" + champ.getTraitCooldown());
		if (champ instanceof HufflepuffWizard)
			getUseTrait().setToolTipText("By activating your trait you get the chance not to be fired by the dragon during this turn"  + ", CoolDown:" + champ.getTraitCooldown());
		if (champ instanceof RavenclawWizard)
			getUseTrait().setToolTipText("By activating your trait you get the chance to make get the location of the marked cells"  + ", CoolDown:" + champ.getTraitCooldown());
	
	revalidate();
	repaint();
	}
	
	
/*
	public void setIcon(ImageIcon jl) {
		icon = new JLabel(jl);
		
		
	}*/
/*	public void setIcon(ImageIcon icon) {
		this.icon.setIcon(icon);
	}*/
	public JPanel getIcon() {
		return icon;
	}
	
	public void addIcon(JLabel h){
		this.icon.add(h);
		
	}
	public void setName(String name) {
		this.name.setText("       " + name);
	}

	public void setChampHP(String champHP, String dchampHP) {
		this.champHP.setText("   HP: "+champHP + "/" + dchampHP);
	}

	public void setChampIP(String champIP, String dchampIP) {
		this.champIP.setText("   IP: " +champIP + "/" + dchampIP);
	}
	public void setChampHP(String champHP) {
		this.champHP.setText(champHP);
		this.champHP.setFont(new Font("SignPainter",Font.BOLD,25));
	}

	public void setChampIP(String champIP) {
		this.champIP.setText(champIP);
		this.champIP.setFont(new Font("SignPainter",Font.BOLD,25));
	}
	public void setChampInventory(JPanel champInventory) {
		this.champInventory = champInventory;
		this.champInventory.setFont(new Font("SignPainter",Font.BOLD,25));
	}

	public String getName() {
		return name.getText();
	}
	public String getChampHP() {
		return champHP.getText();
	}
	public String getChampIP() {
		return champIP.getText();
	}
	public JLabel getChampHPLabel() {
		return champHP;
	}
	public JLabel getChampIPLabel() {
		return champIP;
	}
	public JPanel getChampInventory() {
		return champInventory;
	}
	public JLabel getChampCooldown() {
		return champCooldown;
	}
	public void setChampCooldownValue(String jLabel) {
		this.champCooldown.setText(jLabel);
		this.champCooldown.setFont(new Font("SignPainter",Font.BOLD,25));
	}
	public void setChampCooldown(String jLabel) {
		this.champCooldown.setText(jLabel+ " turn(s)");
		this.champCooldown.setFont(new Font("SignPainter",Font.BOLD,25));
	}
	public Profession getProf() {
		return prof;
	}
	public void setProf(Profession prof) {
		this.prof = prof;
	}
	public JPanel getChampSpells() {
		return champSpells;
	}
	public void setChampSpells(JPanel updateSpells) {
		this.champSpells = updateSpells;
		this.champSpells.setFont(new Font("SignPainter",Font.BOLD,25));
	}
	public Champion getChamp() {
		return champ;
	}
	public void setChamp(Champion champ) {
		this.champ = champ;
	}
	public JButton getUseTrait() {
		return useTrait;
	}
	public void setChampHPLabel(JLabel champHPLabel) {
		this.champHP = champHPLabel;
		this.champHP.setFont(new Font("SignPainter",Font.BOLD,25));
		
	}
	public void setChampIPLabel(JLabel champIPLabel) {
		this.champIP = champIPLabel;
		this.champIP.setFont(new Font("SignPainter",Font.BOLD,25));
		
	}
	public ArrayList<JButton> getClicked() {
		return clicked;
	}
	public void setClicked(ArrayList<JButton> clicked) {
		this.clicked = clicked;
	}
	
}