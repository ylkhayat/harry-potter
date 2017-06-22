package harrypotter.exceptions;

abstract public class InvalidActionException extends Exception
{
	public InvalidActionException()
	{
		super("Performing an Invalid Action.");
	}
	public InvalidActionException(String message)
	{
		super(message);
	}
}
