package harrypotter.view;

public class NewChampion
{
	private String name;
	private Profession prof;
	
	public NewChampion(String n, Profession prof)
	{
		name = n;
		this.prof = prof;
	}
	public String getName() {
		return name;
	}
	public Profession getProf() {
		return prof;
	}
}
