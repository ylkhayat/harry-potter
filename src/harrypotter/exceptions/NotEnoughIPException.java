package harrypotter.exceptions;

public class NotEnoughIPException extends NotEnoughResourcesException
{
	private int requiredIP;
	private int remainingIP;
	
	public NotEnoughIPException(int reqIP, int remIP)
	{
		super("Insufficient IntelligencePoint, required : " + reqIP +", needed amount :" + remIP +". Styubid.");
		requiredIP = reqIP;
		remainingIP = remIP;
	}

	public int getRequiredIP() {
		return requiredIP;
	}

	public int getRemainingIP() {
		return remainingIP;
	}
}