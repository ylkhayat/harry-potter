package harrypotter.model.world;

public class Merperson extends Obstacle
{
	private int damage;
	
	public Merperson(int hp, int d)
	{
		super(hp);
		damage = d;
	}
	public int getDamage()
	{
		return damage;
	}
	
}
