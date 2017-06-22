package harrypotter.model.character;

import java.awt.Point;
import java.util.ArrayList;

import harrypotter.model.magic.Collectible;
import harrypotter.model.magic.Spell;

public interface Champion
{
	public void useTrait() throws Exception;
	public String getName();
	public void setName(String name);
	public int getDefaultHp();
	public void setDefaultHp(int defaultHp);
	public int getDefaultIp();
	public void setDefaultIp(int defaultIp);
	public int getHp();
	public void setHp(int hp);
	public int getIp();
	public void setIp(int ip);
	public int getTraitCooldown();
	public void setTraitCooldown(int traitCooldown);
	public Point getLocation();
	public void setLocation(Point location);
	public ArrayList<Spell> getSpells();
	public ArrayList<Collectible> getInventory();
	public void restore();
	public WizardListener getListener();
	public void setListener(WizardListener listener);
}