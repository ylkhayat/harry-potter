package harrypotter.exceptions;

public class InCooldownException extends NotEnoughResourcesException
{
	private int remainingTurns;
	
	public int getRemainingTurns() {
		return remainingTurns;
	}

	public InCooldownException(int remTurns)
	{
		super("You are exhausted, " + remTurns + " turns remaining.");
		remainingTurns = remTurns;
	}
}
