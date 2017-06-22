package harrypotter.exceptions;

public class OutOfBordersException extends InvalidActionException
{
	public OutOfBordersException()
	{
		super("Out of borders attempt.");
	}
	public OutOfBordersException(String message)
	{
		super(message);
	}
}
