package harrypotter.model.magic;

public class HealingSpell extends Spell
{
	private int healingAmount;
	
	public HealingSpell(String s, int c, int dc, int ha)
	{
		super(s, c, dc);
		healingAmount = ha;
	}
	public String toString()
	{
		return "Name: "+ getName() + " , Cost: " + getCost() + " , HealingAmount: " + getHealingAmount() + " ,CoolDown: "+ getCoolDown();  
	}
	public int getHealingAmount()
	{
		return healingAmount;
	}
}
