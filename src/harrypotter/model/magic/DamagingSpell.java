package harrypotter.model.magic;

public class DamagingSpell extends Spell
{
	private int damageAmount;
	
	public DamagingSpell(String s, int c, int dc, int da)
	{
		super(s, c, dc);
		damageAmount = da;
	}
	public int getDamageAmount()
	{
		return damageAmount;
	}
}
