package harrypotter.exceptions;

public class InvalidTraitActionException extends InvalidActionException
{
	public InvalidTraitActionException()
	{
		super("Invalid attempt.");
	}
	public InvalidTraitActionException(String message)
	{
		super(message);
	}
}
