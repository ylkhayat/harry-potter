package harrypotter.model.world;

import harrypotter.model.character.Champion;

public class ChampionCell extends Cell
{
	private Champion champ;

	public ChampionCell(Champion c)
	{
		super(false);
		champ = c;
	}
	public Champion getChamp()
	{
		return champ;
	}
}
