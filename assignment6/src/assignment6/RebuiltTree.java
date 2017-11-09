package assignment6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * A class dedicated to rebuilding a
 * tree stored in a text file.
 * 
 * @author Nick Rummel
 * @author Alex Medellin
 *
 */
public class RebuiltTree
{
	final private String INDENT = "     ";
	final private int MAX_LEVEL = 10;
	private DefaultMutableTreeNode tree;
	private ArrayList<String> text;
	private int[] levels;
	/**
	 * Constructor of class.
	 * Opens text file and rebuilds
	 * the tree.
	 * @param path the path to the file
	 */
	public RebuiltTree(String path)
	{
		text = new ArrayList<String>();
		openFile(path);
		levels = new int[text.size()];
		tree = new DefaultMutableTreeNode();
		generateTree();
	}

	/**
	 * Opens a file at a particular path
	 * and reads in the text, storing each
	 * line as a String in an ArrayList
	 * @param path
	 */
	private void openFile(String path)
	{
		File tf = new File(path);
		BufferedReader br = null;
		try
		{
			br = new BufferedReader(new FileReader(tf));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		String buf;
		try
		{
			while((buf = br.readLine()) != null && buf.length() > 0)
			{
				text.add(buf);
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates the tree and stores it
	 * as an instance variable
	 */
	private void generateTree()
	{
		ArrayList<Integer> sameLevels = null;
		calculateLevelCounts();
		tree.setUserObject(text.get(0));
		DefaultMutableTreeNode temp = tree;
		for(int i = 1; i <= MAX_LEVEL; i++)
		{
			sameLevels = checkForSameLevel(i);
			if(sameLevels.size() == 0)
				break;
			else
			{
				for(int j = 0; j < sameLevels.size(); j++)
				{
					temp.add(new DefaultMutableTreeNode(text.get(sameLevels.get(j)).trim()));
				}
			}
			if(checkIfDeadEndComponent(temp))
				temp = temp.getNextNode();
			
			temp = temp.getNextNode();
				
			
		}
	}
	
	/**
	 * Loops through the String ArrayList
	 * to determine what level of the tree
	 * each line is located on.
	 */
	private void calculateLevelCounts()
	{
		int size = text.size();
		for(int i = 0; i < size; i++)
		{
			String val = text.get(i);
			if(!val.startsWith(INDENT))
			{
				levels[i] = 0;
			}
			else
			{
				for(int j = MAX_LEVEL; j >= 1; j--)
				{
					if(val.startsWith(createIndent(j)))
					{
						levels[i] = j;
						break;
					}
				}
			}
		}
	}
	
	/**
	 * Concatenates whitespace to determine
	 * what level each node is on in the tree
	 * @param count the number of indents to add
	 * @return the whitespace as a String
	 */
	private String createIndent(int count)
	{
		String s = "";
		for(int i = 1; i <= count; i++)
			s += INDENT;
		return s;
	}
	
	/**
	 * Determines if any nodes are on the same level
	 * @param level the level to be searched for
	 * @return an ArrayList of nodes on the same level
	 */
	private ArrayList<Integer> checkForSameLevel(int level)
	{
		ArrayList<Integer> sameLevel = new ArrayList<Integer>();
		for(int i = 0; i < levels.length; i++)
		{
			if(levels[i] == level)
			{
				sameLevel.add(i);
			}
		}
		return sameLevel;
	}
	
	/**
	 * Retrieves tree instance variable
	 * @return the tree
	 */
	public DefaultMutableTreeNode getTree()
	{
		return tree;
	}
	
	/**
	 * Determines if the child node is a leaf (meaning that
	 * this command does not have any children because it is a
	 * turn, backup, or forward command).
	 * @param node the parent of the node to be checked
	 * @return True if child is "dead end", else false
	 */
	private boolean checkIfDeadEndComponent(DefaultMutableTreeNode node)
	{
		if(((String)node.getNextNode().getUserObject()).contains("Forward"))
			return true;
		if(((String)node.getNextNode().getUserObject()).contains("Backup"))
			return true;
		if(((String)node.getNextNode().getUserObject()).contains("Turn"))
			return true;
		else
			return false;
		
	}
}
