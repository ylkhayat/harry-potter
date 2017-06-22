package harrypotter.model.world;

abstract public class Obstacle
{
	private int hp;
	
	public Obstacle(int h)
	{
		hp = h;
	}

	public int getHp()
	{
		return hp;
	}

	public void setHp(int hp) 
	{
		this.hp = hp;
	}
	
}
