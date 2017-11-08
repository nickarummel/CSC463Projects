package assignment6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

public class RebuiltTree
{
	final private String INDENT = "     ";
	final private int MAX_LEVEL = 10;
	protected DefaultMutableTreeNode tree;
	protected ArrayList<String> text;
	protected int[] levels;
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
		calculateLevelCounts();
		int size = text.size();
		tree.setUserObject(text.get(0));
		int prev_level = 0;
		for(int i = 1; i < size; i++)
		{
			String val = text.get(i);
			if(levels[i] > prev_level)
			{
				tree.add(new DefaultMutableTreeNode(val));
			}
			else if(levels[i] == prev_level)
			{
				
			}
			else
			{
				
			}
			
			
			prev_level = levels[i];
			
			
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
					}
				}
			}
		}
	}
	
	private String createIndent(int count)
	{
		String s = "";
		for(int i = 1; i <= count; i++)
			s.replace("\0", INDENT);
		return s;
	}
}
