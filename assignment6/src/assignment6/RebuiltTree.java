package assignment6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;

public class RebuiltTree
{
	final private String INDENT = "     ";
	final private int MAX_LEVEL = 10;
	private DefaultMutableTreeNode tree;
	private ArrayList<String> text;
	private int[] levels;
	public RebuiltTree(String path)
	{
		text = new ArrayList<String>();
		openFile(path);
		levels = new int[text.size()];
		tree = new DefaultMutableTreeNode();
		generateTree();
	}

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
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void generateTree()
	{
		ArrayList<Integer> sameLevels = null;
		calculateLevelCounts();
		//int size = text.size();
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
	
	private String createIndent(int count)
	{
		String s = "";
		for(int i = 1; i <= count; i++)
			s += INDENT;
		return s;
	}
	
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
	
	public DefaultMutableTreeNode getTree()
	{
		return tree;
	}
	
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
