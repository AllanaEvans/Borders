//Dylan Evans
//2015-04-22
//DragRun runs Drag panel of the game

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.awt.Image;
import java.io.File;
import java.util.Scanner;

public class DragRun extends JFrame
	//class DragRun (JFrame)
{
	public static void main(String [] args)
		//main
	{
		DragRun e = new DragRun();
	}
	public DragRun
		//constructor
	{
		setSize(600, 450);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(0, 0);
		setResizable(false);
		Drag p = new Drag();
		setContentPane(p);
		setVisible(true);		
			//set values (resizable, visible, etc.)
	}
}
class Drag extends JPanel
	//panel Drag
{
	
		//location array (these arrays will be global in the final program)
		//image array
	private static boolean pressed;
	private static int dragx;
	private static int dragy;
	private static boolean start;
	private static int [][] rect;
	
	public Drag()
		//constructor
	{
		pressed = false;
		start = true;
			//set pressed to false
		dragx = 0;
		dragy = 0;
		rect = new int[2][14];
			//set dragx and dragy to 0
	}
	public void paintComponent(Graphics g)
		//paintcomponent
	{
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawImage(igs[setCountry], 
			(300 - (50/igs[setCountry].getHeight())*igs[setCountry].getWidth()/2), 0, 
			(50/igs[setCountry].getHeight())*igs[setCountry].getWidth(), 50, this);
			//display set country in middle of screen
		for(int w = 0; w < scw; w++)
		{
			if(scn[w] != -1)
			{
			//	if(125/(igs[scn[w]].getWidth()/600*igs[scn[w]].getHeight() + 10) >= 2)
			//		for(
				if(start)
					//if start
				{
					rect[0][w] = 150;
					rect[1][w] = 325;
				}
				g.drawRect(rect[0], rect[1]);
			}
				//set rectangle value array to default values
		}
			//use loops to display each bordering country and rectangles where the user can drag using rectangle value array
			//save the values of the rectangles in arrays
			//use location array to display rectangles around the set country where the borders are
	}
	public void mousePressed(MouseEvent e){}
		//mouse pressed
	public void mouseReleased(MouseEvent e)
		//mouse released
	{
		if(rect[0] >= 
			//if a rectangle's array values are in one of the borders' rectangles
				//see if the rectangles match
				//if they match, make the dragged rectangle value equal to the other one
				//if not, reset the dragged rectangle's value, decrement # of lives
	}
	public void mouseDragged(MouseEvent e)
		//mouse dragged
	{
		dragx = e.getX();
		dragy = e.getY();
			//set dragx to getx and dragy to gety
		while(!(dragx <= (rect[0][v] + rect[2][v]) && dragx >= rect[0][v] 
				&& dragy <= (rect[1][v] + rect[3][v]) && dragy >= rect[1][v])
			v++;
			//if dragx and dragy are in a rectangle's values
		rect[0][v] = dragx;
		rect[1][v] = dragy;
			//change those rectangles' values
		repaint();
			//repaint
	}
	public void mouseMoved(MouseEvent e){}
		//mouse moved 
}