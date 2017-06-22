package harrypotter.model.magic;

abstract public class Spell
{
	private String name;
	private int cost;
	private int defaultCooldown;
	private int coolDown;
	
	
	public Spell(String s, int c, int dc)
	{
		name = s;
		cost = c;
		defaultCooldown = dc;
	}

	public int getCoolDown()
	{
		return coolDown;
	}

	public void setCoolDown(int cooldown)
	{
		this.coolDown = cooldown;
	}

	public String getName()
	{
		return name;
	}

	public int getCost()
	{
		return cost;
	}

	public int getDefaultCooldown()
	{
		return defaultCooldown;
	}
}
