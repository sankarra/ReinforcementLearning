
public class Action {
	
	private double [] probability;
	private int [] location;
	
	Action(double [] probability, int [] location)
	{
		probability = new double[4];
		
		//Up, Left, Down, Right Intentions
		for (int i = 0; i < 4; ++i)
		{
			probability[i] = 0.0;
		}
	}
	Action() {
		probability = new double[4];
	}
	
	public void setP(int i, double prob)
	{
		probability[i]= prob; 
	}
	
	public double getP(int i)
	{
		return probability[i];
	} 
	
	public void setL(int i, int loc)
	{
		location[i] = loc;
	}
	
	public int getL(int i)
	{
		return location[i];
	}

}
