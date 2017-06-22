package harrypotter.model.world;

import harrypotter.model.magic.Mystery;

public class MysteryCell extends Cell {
	private Mystery mystery;
	
	public MysteryCell(Mystery mystery)
	{
		super(true);
		this.mystery = mystery;
	}

	public Mystery getMystery() {
		return mystery;
	}

}
