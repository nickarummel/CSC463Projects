package assignment6;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Scanner;

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
		while(true)
		{
			Scanner kbreader = new Scanner(System.in);
			System.out.print("Options:"
					+ "\n1 - Generate new tree"
					+ "\n2 - Rebuild tree and replace random node"
					+ "\n3 - Rebuild two trees and replace tree 1's node with tree 2's node"
					+ "\n4 - Terminate program"
					+ "\nWhat would you like to do? ");
			int choice = kbreader.nextInt();
			if(choice == 1)
			{
				DefaultMutableTreeNode root = chooseComponent(generateRandomSensor(),generateRandomComponent(),THRESHOLD);
				System.out.println("Tree");
				printTree(root);
				
				exportTree(root);
				File f = new File("output_tree.txt");

				System.out.println("Tree saved at: " + f.getAbsolutePath() + "\n");
			}
			else if(choice == 2)
			{
				kbreader.nextLine();
				System.out.print("\nEnter path of tree file:  ");
				String path = kbreader.nextLine();
				RebuiltTree rt = new RebuiltTree(path);
				DefaultMutableTreeNode newTree = rt.getTree();				
				newTree = changeOneNode(newTree, selectRandomNode(newTree));
				
				System.out.println("Tree");
				printTree(newTree);
				
				exportTree(newTree);
				File f = new File("output_tree.txt");

				System.out.println("Tree saved at: " + f.getAbsolutePath() + "\n");
			}
			else if(choice == 3)
			{
				kbreader.nextLine();
				System.out.print("\nEnter path of the first tree's file:  ");
				String path = kbreader.nextLine();
				RebuiltTree rt = new RebuiltTree(path);
				DefaultMutableTreeNode tree1 = rt.getTree();
				
				System.out.print("\nEnter path of the second tree's file:  ");
				path = kbreader.nextLine().trim();
				rt = new RebuiltTree(path);
				DefaultMutableTreeNode tree2 = rt.getTree();
				
				
				System.out.println("\nMerged Tree");
				DefaultMutableTreeNode root = makeNewTreeFromTwoTrees(tree1, tree2);
				printTree(tree1);
				
				exportTree(root);
				File f = new File("output_tree.txt");

				System.out.println("Tree saved at: " + f.getAbsolutePath() + "\n");
				
			}
			else
			{
				break;
			}
		
		}
	}

	/**
	 * Code to generate all parts of assignment
	 * as a test
	 */
	public static void testProject()
	{
		DefaultMutableTreeNode root = chooseComponent(generateRandomSensor(),generateRandomComponent(),THRESHOLD);
		System.out.println("Part 1 - First Tree");
		printTree(root);
		
		exportTree(root);
		
		System.out.println("\nRebuilt Tree");
		
		RebuiltTree rt = new RebuiltTree("output_tree.txt");
		DefaultMutableTreeNode newTree = rt.getTree();
		printTree(newTree);
		
		System.out.println("\nPart 2 - Swap 1 Node with Random Components");
		
		newTree = changeOneNode(newTree, selectRandomNode(newTree));
		
		printTree(newTree);
		
		System.out.println("\nPart 3 - Replace Random Node from Tree 1 with Random Node from Tree 2");
		System.out.println("Tree #2");
		DefaultMutableTreeNode tree2 = chooseComponent(generateRandomSensor(),generateRandomComponent(),THRESHOLD);
		printTree(tree2);
		
		System.out.println("\nMerged Tree");
		root = makeNewTreeFromTwoTrees(root, tree2);
		printTree(root);
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
	
	/**
	 * Creates a new tree by randomly selecting a node in the first
	 * tree and replacing it with a node from a second tree.
	 * @param tree1 the tree to receive a new node/sub-tree
	 * @param tree2 the tree supplying the node/sub-tree
	 * @return the merged tree's root
	 */
	public static DefaultMutableTreeNode makeNewTreeFromTwoTrees(DefaultMutableTreeNode tree1, DefaultMutableTreeNode tree2)
	{
		int tree1node = selectRandomNode(tree1);
		int tree2node = selectRandomNode(tree2);
		
		for(int i = 0; i < tree1node; i++)
			tree1 = tree1.getNextNode();
		for(int i = 0; i < tree2node; i++)
			tree2 = tree2.getNextNode();
		
		tree1.removeAllChildren();
		if(tree1.getSiblingCount() > 0 && !(tree1.isRoot()))
		{
			DefaultMutableTreeNode test = (DefaultMutableTreeNode) tree1.getParent().getChildAt(0);
			if(test.equals(tree1))
			{
				tree1 = (DefaultMutableTreeNode) tree1.getParent();
				tree1.remove(0);
			}
			else
			{
				tree1 = (DefaultMutableTreeNode) tree1.getParent();
				tree1.remove(1);
			}
			tree1.add(tree2);
		}
		else if(tree1.getSiblingCount() == 0 && !(tree1.isRoot()))
		{
			tree1 = (DefaultMutableTreeNode) tree1.getParent();
			tree1.removeAllChildren();
			tree1.add(tree2);
		}
		else 
		{
			tree1 = tree2;
		}
		return (DefaultMutableTreeNode) tree1.getRoot();
	}

}
