package harrypotter.model.character;

import harrypotter.model.magic.Collectible;
import harrypotter.model.magic.Spell;

import java.awt.Point;
import java.util.ArrayList;

abstract public class Wizard implements Champion {
	private String name;
	private int defaultHp;
	private int defaultIp;
	private int hp;
	private int ip;
	private ArrayList<Spell> spells;
	private ArrayList<Collectible> inventory;
	private int traitCooldown;
	private Point location;
	private WizardListener listener;
	
	public Wizard(String n)
	{
		name = n;
		traitCooldown = 0;
		spells = new ArrayList<Spell>();
		inventory = new ArrayList<Collectible>();
	}
	public abstract void useTrait() throws Exception;
	public Wizard (String n, int hp , int ip)
	{
		this(n);
		defaultHp = hp;
		defaultIp = ip;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDefaultHp() {
		return defaultHp;
	}
	public final void setDefaultHp(int defaultHp) {
		this.defaultHp = defaultHp;
	}
	public final int getDefaultIp() {
		return defaultIp;
	}
	public void setDefaultIp(int defaultIp) {
		this.defaultIp = defaultIp;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getIp() {
		return ip;
	}
	public WizardListener getListener() {
		return listener;
	}

	public void setListener(WizardListener listener) {
		this.listener = listener;
	}

	public void setIp(int ip) {
		this.ip = ip;
	}
	public int getTraitCooldown() {
		return traitCooldown;
	}
	public void setTraitCooldown(int traitCooldown) {
		this.traitCooldown = traitCooldown;
	}
	public Point getLocation() {
		return location;
	}
	public void setLocation(Point location) {
		this.location = location;
	}
	public ArrayList<Spell> getSpells() {
		return spells;
	}
	public ArrayList<Collectible> getInventory() {
		return inventory;
	}
}
