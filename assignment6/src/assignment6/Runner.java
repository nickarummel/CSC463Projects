package assignment6;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

import javax.swing.tree.*;
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
	private final static int THRESHOLD = 235;
	/**
	 * Main method for project
	 * @param args
	 */
	public static void main(String[] args)
	{
		DefaultMutableTreeNode root = chooseComponent(generateRandomSensor(),generateRandomComponent(),THRESHOLD);
		
		printTree(root);
		
		exportTree(root);
		
		System.out.println("");
		
		RebuiltTree rt = new RebuiltTree("output_tree.txt");
		DefaultMutableTreeNode newTree = rt.getTree();
		printTree(newTree);
		
		System.out.println("");
		
		newTree = changeOneNode(newTree, selectRandomNode(newTree));
		
		printTree(newTree);
	}

	/**
	 * Converts the entire tree into a formatted String
	 * @param root the start of the tree
	 * @return the generated String
	 */
	public static String toString(DefaultMutableTreeNode root)
	{
		Enumeration<?> en = root.preorderEnumeration();
		String buf = "";
		while(en.hasMoreElements())
		{
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) en.nextElement();
			String val = String.valueOf(node.getUserObject());
			String s = "";
			while (node.getParent() != null)
			{
				s += "     ";
				node = (DefaultMutableTreeNode) node.getParent();
			}
			buf = buf + s + val + "\n";
		}
		return buf;
	}
	
	/**
	 * Prints out the tree to the user
	 * @param root the start of the tree
	 */
	public static void printTree(DefaultMutableTreeNode root)
	{
			System.out.print(toString(root));
	}
	
	/**
	 * The tree is written to a designated text file in the project
	 * using the ISO-8859-1 (Latin-1) character set.
	 * @param root the start of the tree
	 */
	public static void exportTree(DefaultMutableTreeNode root)
	{
		String buf = toString(root);
		try
		{
			PrintWriter writer = new PrintWriter("output_tree.txt", "ISO-8859-1");
			writer.print(buf);
			writer.close();
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
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
	 * @return the root of tree
	 */
	public static DefaultMutableTreeNode chooseComponent(int sensor, int component, int threshold)
	{
		String s = "";
		DefaultMutableTreeNode node = new DefaultMutableTreeNode();
		
		// 1 - Are We Close To Wall
		if(component == 1)
		{
			s = "Close to Wall: Sensor " + sensor + ", threshold " + threshold;
			node.setUserObject(s);
			node.add(chooseComponent(generateRandomSensor(), generateRandomComponent(), THRESHOLD));
		}
		// 2 - While Too Far From Wall
		else if(component == 2)
		{
			s = "Far from Wall: Sensor " + sensor + ", threshold " + threshold;
			node.setUserObject(s);
			node.add(chooseComponent(generateRandomSensor(), generateRandomComponent(), THRESHOLD));
		}
		// 3 - Do 2 Far From Wall
		else if(component == 3)
		{
			s = "Do Two Far Far: Sensor " + sensor + ", threshold " + threshold;
			node.setUserObject(s);
			node.add(chooseComponent(generateRandomSensor(), generateRandomComponent(), THRESHOLD));
			node.add(chooseComponent(generateRandomSensor(), generateRandomComponent(), THRESHOLD));
		}
		// 4 - Do 2 Close To Wall
		else if(component == 4)
		{
			s = "Do Two Close Close: Sensor " + sensor + ", threshold " + threshold;
			node.setUserObject(s);
			node.add(chooseComponent(generateRandomSensor(), generateRandomComponent(), THRESHOLD));
			node.add(chooseComponent(generateRandomSensor(), generateRandomComponent(), THRESHOLD));
		}
		// 5 - Do 2 Far Close
		else if(component == 5)
		{
			s = "Do Two Far Close: Sensor " + sensor + ", threshold " + threshold;
			node.setUserObject(s);
			node.add(chooseComponent(generateRandomSensor(), generateRandomComponent(), THRESHOLD));
			node.add(chooseComponent(generateRandomSensor(), generateRandomComponent(), THRESHOLD));
		}
		// 6 - Do 2 Close Far
		else if(component == 6)
		{
			s = "Do Two Close Far: Sensor " + sensor + ", threshold " + threshold;
			node.setUserObject(s);
			node.add(chooseComponent(generateRandomSensor(), generateRandomComponent(), THRESHOLD));
			node.add(chooseComponent(generateRandomSensor(), generateRandomComponent(), THRESHOLD));
		}
		// 7 - Go Forward
		else if(component == 7)
		{
			s = "Go Forward";
			node.setUserObject(s);
		}
		// 8 - Turn Right
		else if(component == 8)
		{
			s = "Turn Right";
			node.setUserObject(s);
		}
		// 9 - Turn Left
		else if(component == 9)
		{
			s = "Turn Left";
			node.setUserObject(s);
		}
		// 10 - Backup
		else if(component == 10)
		{
			s = "Backup";
			node.setUserObject(s);
		}
		// 11 - Turn Parallel to Position
		else if(component == 11)
		{
			s = "Turn Parallel To Wall";
			node.setUserObject(s);
		}
		// 12 - Turn Square with Wall
		else if(component == 12)
		{
			s = "Turn Square with Wall";
			node.setUserObject(s);
		}
		return node;
		
	}
	
	/**
	 * Counts the number of nodes in the tree and 
	 * randomly selects one of the nodes from that
	 * count.
	 * @param tree the root of the tree
	 * @return the node number
	 */
	public static int selectRandomNode(DefaultMutableTreeNode tree)
	{
		Enumeration en = tree.preorderEnumeration();
		int count = 0;
		while(en.hasMoreElements())
		{
			en.nextElement();
			count++;
		}
		return (int) Math.floor(((Math.random()*count)));
	}
	
	/**
	 * Part 2 of Assignment 6 - Replaces the node at the
	 * designated location, removes children of node,
	 * then removes the node from the parent. The node is 
	 * then replaced, possibly generating a new sub-tree
	 * off that node.
	 * @param tree
	 * @param nodeNum Node index
	 * @return the root of the new tree
	 */
	public static DefaultMutableTreeNode changeOneNode(DefaultMutableTreeNode tree, int nodeNum)
	{
		DefaultMutableTreeNode temp = tree;
		for(int i = 0; i < nodeNum; i++)
			temp = temp.getNextNode();
		temp.removeAllChildren();
		if(temp.getSiblingCount() > 0 && !(temp.isRoot()))
		{
			DefaultMutableTreeNode test = (DefaultMutableTreeNode) temp.getParent().getChildAt(0);
			if(test.equals(temp))
			{
				temp = (DefaultMutableTreeNode) temp.getParent();
				temp.remove(0);
			}
			else
			{
				temp = (DefaultMutableTreeNode) temp.getParent();
				temp.remove(1);
			}
			temp.add(chooseComponent(generateRandomSensor(), generateRandomComponent(), THRESHOLD));
		}
		else if(temp.getSiblingCount() == 0 && !(temp.isRoot()))
		{
			temp = (DefaultMutableTreeNode) temp.getParent();
			temp.removeAllChildren();
			temp.add(chooseComponent(generateRandomSensor(), generateRandomComponent(), THRESHOLD));
		}
		else 
		{
			temp = chooseComponent(generateRandomSensor(),generateRandomComponent(),THRESHOLD);
		}
		return (DefaultMutableTreeNode) temp.getRoot();
	}

}
