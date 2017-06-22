package harrypotter.exceptions;

public class InvalidTargetCellException extends InvalidActionException
{
	public InvalidTargetCellException()
	{
		super("WowInvalid target attempt.");
	}
	public InvalidTargetCellException(String message)
	{
		super(message);
	}
}
