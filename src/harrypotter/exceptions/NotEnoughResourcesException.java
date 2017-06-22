package harrypotter.exceptions;

public abstract class NotEnoughResourcesException extends InvalidActionException
{
	public NotEnoughResourcesException()
	{
		super();
	}
	public NotEnoughResourcesException(String string) {
		super(string);
	}
}
