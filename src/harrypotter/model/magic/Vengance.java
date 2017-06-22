package harrypotter.model.magic;

public class Vengance extends Mystery
{
	private int damage;

	public Vengance(String name, int damage)
	{
		super(name);
		this.damage = damage;
	}

	public int getDamage() {
		return damage;
	}

}
