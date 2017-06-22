package harrypotter.model.world;

abstract public class Cell
{
	boolean walkThrough;

	public Cell(boolean wt)
	{
		walkThrough = wt;
	}
	public boolean isWalkThrough() {
		return walkThrough;
	}
	public void setWalkThrough(boolean walkThrough) {
		this.walkThrough = walkThrough;
	}
}
