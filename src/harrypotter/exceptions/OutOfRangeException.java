package harrypotter.exceptions;

public class OutOfRangeException extends InvalidActionException
{
	private int allowedRange;
	
	public OutOfRangeException(int allowRange)
	{
		super("Invalid designated cell, Allowed range : " + allowRange +".");
		allowedRange = allowRange;
	}

	public int getAllowedRange() {
		return allowedRange;
	}
}
