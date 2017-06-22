package harrypotter.model.world;

import harrypotter.model.character.Champion;

public class TreasureCell extends Cell
{
	private Champion owner;
	
	public TreasureCell(Champion o)
	{
		super(true);
		owner = o;
	}
	public Champion getOwner() {
		return owner;
	}
}
