package assignment6;
import java.util.TreeMap;
/**
 * Main class to generate the tree and code for
 * the genetic algorithm in Interactive C.
 * 
 * @author Nick Rummel
 * @author Alex Medellin
 *
 */


public class Runner
{
	public TreeMap<String,String> tree = new TreeMap<>();
	public static void main(String[] args)
	{
		int sensor = 0;
		int component = 0;

		
		

	}
	
	/**
	 * Generates a random number between 1 and 4,
	 * inclusively. Each number corresponds to a
	 * sensor.
	 * 
	 * @return the randomly generated integer
	 */
	public static int generateRandomSensor()
	{
		return (int) Math.floor(((Math.random()*4+1)));
	}
	
	/**
	 * Generates a random number between 1 and 12,
	 * inclusively. Each number corresponds to a
	 * component.
	 * 
	 * @return the randomly generated integer
	 */
	public static int generateRandomComponent()
	{
		return (int) Math.floor(((Math.random()*12+1)));
	}
	
	/**
	 * A recursive method that will generate the nodes for the tree
	 * @param sensor The sensor number to be used
	 * @param component The component to be used
	 * @param threshold The threshold to be used
	 * @return The tree as a String
	 */
	public String chooseComponent(int sensor, int component, int threshold)
	{
		// 1 - Are We Close To Wall
		if(component == 1)
		{
			
			return "";
		}
		// 2 - While Too Far From Wall
		else if(component == 2)
		{
			return "";
		}
		// 3 - Do 2 Far From Wall
		else if(component == 3)
		{
			return "";
		}
		// 4 - Do 2 Close To Wall
		else if(component == 4)
		{
			return "";
		}
		// 5 - Do 2 Far Close
		else if(component == 5)
		{
			return "";
		}
		// 6 - Do 2 Close Far
		else if(component == 6)
		{
			return "";
		}
		// 7 - Go Forward
		else if(component == 7)
		{
			return "";
		}
		// 8 - Turn Right
		else if(component == 8)
		{
			return "";
		}
		// 9 - Turn Left
		else if(component == 9)
		{
			return "";
		}
		// 10 - Backup
		else if(component == 10)
		{
			return "";
		}
		// 11 - Turn Parallel to Position
		else if(component == 11)
		{
			return "";
		}
		// 12 - Turn Parallel to Position
		else
		{
			return "";
		}
		
	}

}
