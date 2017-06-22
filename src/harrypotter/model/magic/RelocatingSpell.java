package harrypotter.model.magic;

public class RelocatingSpell extends Spell
{
	private int range;
	
	public RelocatingSpell(String s, int c, int dc, int r)
	{
		super(s, c, dc);
		range = r;
	}

	public int getRange()
	{
		return range;
	}
	
}
