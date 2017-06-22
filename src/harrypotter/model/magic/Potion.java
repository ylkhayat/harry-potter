package harrypotter.model.magic;

public class Potion extends Collectible
{
	private int amount;
	
	public int getAmount()
	{
		return amount;
	}
	public Potion(String s, int n)
	{
		super(s);
		amount = n;	
	}

}
