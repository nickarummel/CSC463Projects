package assignment6;
import java.util.Map;
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
	public static TreeMap<String,String> tree = new TreeMap<>();
	static char i = '`';
	private final static int THRESHOLD = 235;
	public static void main(String[] args)
	{
		chooseComponent(generateRandomSensor(),generateRandomComponent(),THRESHOLD);
		printTree();
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
	 */
	public static void chooseComponent(int sensor, int component, int threshold)
	{
		i++;
		String s = "";
		
		// 1 - Are We Close To Wall
		if(component == 1)
		{
			s = "Close to Wall: Sensor " + sensor + ", threshold " + threshold;
			tree.put("" + i,s);
			chooseComponent(generateRandomSensor(), generateRandomComponent(), THRESHOLD);
			return;
		}
		// 2 - While Too Far From Wall
		else if(component == 2)
		{
			s = "Far from Wall: Sensor " + sensor + ", threshold " + threshold;
			tree.put("" + i,s);
			chooseComponent(generateRandomSensor(), generateRandomComponent(), THRESHOLD);
			return;
		}
		// 3 - Do 2 Far From Wall
		else if(component == 3)
		{
			s = "Do Two Far Far: Sensor " + sensor + ", threshold " + threshold;
			tree.put("" + i,s);
			chooseComponent(generateRandomSensor(), generateRandomComponent(), THRESHOLD);
			chooseComponent(generateRandomSensor(), generateRandomComponent(), THRESHOLD);
			return;
		}
		// 4 - Do 2 Close To Wall
		else if(component == 4)
		{
			s = "Do Two Close Close: Sensor " + sensor + ", threshold " + threshold;
			tree.put("" + i,s);
			chooseComponent(generateRandomSensor(), generateRandomComponent(), THRESHOLD);
			chooseComponent(generateRandomSensor(), generateRandomComponent(), THRESHOLD);
			return;
		}
		// 5 - Do 2 Far Close
		else if(component == 5)
		{
			s = "Do Two Far Close: Sensor " + sensor + ", threshold " + threshold;
			tree.put("" + i,s);
			chooseComponent(generateRandomSensor(), generateRandomComponent(), THRESHOLD);
			chooseComponent(generateRandomSensor(), generateRandomComponent(), THRESHOLD);
			return;
		}
		// 6 - Do 2 Close Far
		else if(component == 6)
		{
			s = "Do Two Close Far: Sensor " + sensor + ", threshold " + threshold;
			tree.put("" + i,s);
			chooseComponent(generateRandomSensor(), generateRandomComponent(), THRESHOLD);
			chooseComponent(generateRandomSensor(), generateRandomComponent(), THRESHOLD);
			return;
		}
		// 7 - Go Forward
		else if(component == 7)
		{
			s = "Go Forward";
			tree.put("" + i,s);
			return;
		}
		// 8 - Turn Right
		else if(component == 8)
		{
			s = "Turn Right";
			tree.put("" + i,s);
			return;
		}
		// 9 - Turn Left
		else if(component == 9)
		{
			s = "Turn Left";
			tree.put("" + i,s);
			return;
		}
		// 10 - Backup
		else if(component == 10)
		{
			s = "Backup";
			tree.put("" + i,s);
			return;
		}
		// 11 - Turn Parallel to Position
		else if(component == 11)
		{
			s = "Turn Parallel To Wall";
			tree.put("" + i,s);
			return;
		}
		// 12 - Turn Square with Wall
		else if(component == 12)
		{
			s = "Turn Square with Wall";
			tree.put("" + i,s);
			return;
		}
		
	}
	
	/**
	 * Prints out the tree
	 */
	public static void printTree()
	{
		for(Map.Entry<String, String> entry : tree.entrySet())
		{
			System.out.println(entry.getKey() + ". " + entry.getValue() + "\n" );
		}
	}

}
