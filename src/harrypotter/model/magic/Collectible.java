package harrypotter.model.magic;

abstract public class Collectible
{
	private String name;
	
	public Collectible(String s)
	{
		name = s;
	}
	public String getName()
	{
		return name;
	}
}
