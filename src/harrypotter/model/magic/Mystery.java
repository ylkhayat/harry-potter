package harrypotter.model.magic;

public abstract class Mystery 
{
	private String name;
	public Mystery(String name)
	{
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
