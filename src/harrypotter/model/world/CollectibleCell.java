package harrypotter.model.world;

import harrypotter.model.magic.Collectible;

public class CollectibleCell extends Cell
{
	private Collectible collectible;
	
	public CollectibleCell(Collectible c)
	{
		super(true);
		collectible = c;
	}
	public Collectible getCollectible() {
		return collectible;
	}
}
