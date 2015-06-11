//Dylan Evans
//2015-05-07
//Borders

import javax.swing.*;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.*;

import javax.swing.*;
import java.awt.Image;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Borders extends JFrame
// class Borders (JFrame)
{
	public static void main(String[] args)
	// main
	{
		Borders e = new Borders();
	}

	public Borders()
	// constructor
	{
		setSize(600, 450);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(0, 0);
		setResizable(false);
		BordersPanel p = new BordersPanel();
		setContentPane(p);
		setVisible(true);
		// set values (resizable, visible, etc.)
	}
}

class BordersPanel extends JPanel
{	// indexs 0 - 40 Asia
	// indexs 41 - 82 Europe
	// indexs 83 - 106 Americas
	// indexs 107 - 154 Africa
    final int FIRST_ASIA_COUNTRY = 0;
	final int NUM_ASIA_COUNTRIES = 41;
	final int LAST_ASIA_COUNTRY = 40;
	
	final int FIRST_EUROPE_COUNTRY = 41;
	final int NUM_EUROPE_COUNTRIES = 42;
	final int LAST_EUROPE_COUNTRY = 82;
	
	final int FIRST_AMERICA_COUNTRY = 83;
	final int NUM_AMERICA_COUNTRIES = 24;
	final int LAST_AMERICA_COUNTRY = 106;

	final int FIRST_AFRICA_COUNTRY = 107;
	final int NUM_AFRICA_COUNTRIES = 48;
	final int LAST_AFRICA_COUNTRY = 154;

	final int MAX_COUNTRIES = 155;
	
    boolean[][] borders;
	// 2-d boolean array for bordering countries
	int[][] loc;
	Image [] images;
	Image [] imagesSmall;
	// 1-d image array for country images
	String[] name;
	// 1-d string array for country names
	
	// private static File location = new File("Locations.txt");
	private static File border = new File("country borders2.csv");
	private static File names = new File("country list.csv");
	private static CardLayout cl;
	private static JPanel p;
	
	private static TitleFrame card1;
	private static SelectionFrame card2;
	private static GamePanel card3;
	private static DragPanel card4;
	private static JPanel card5;
	
	private static int setCountry; //test country
	public int qCountry;  //question country

	public BordersPanel()
	{
		borders = new boolean[MAX_COUNTRIES][MAX_COUNTRIES];
		name = new String[MAX_COUNTRIES];
		images = new Image[MAX_COUNTRIES];
		imagesSmall = new Image[MAX_COUNTRIES];
		setCountry = -1;
		cl = new CardLayout();
		p = new JPanel();
		p.setLayout(cl);
		card1 = new TitleFrame();
		card2 = new SelectionFrame();
		card3 = new GamePanel();
		card4 = new DragPanel();
		card5 = new JPanel();// Placeholder
		p.add(card1, "titleCard");
		p.add(card2, "selectionCard");
		p.add(card3, "gameCard");
		p.add(card4, "dragCard");
		p.add(card5, "spareCard");
		add(p);
		cl.show(p, "titleCard");
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	}

	class TitleFrame extends JPanel implements ActionListener
	{
		private JButton startButton;//global JButton
		private boolean pressedstart;
		
		public TitleFrame()
		{
			setLayout(null);//null Layout 3 rows 1 column
			pressedstart = false;
			startButton = new JButton("START");
			startButton.addActionListener(this);
			//startButton.setBounds(0,330,600,100);
			startButton.setBounds(30,300,520,100);
			add(startButton);
			//in the second box make a grid layout 2 rows 2 columns
			//upper-left print Choose Difficulty
			//upper-right make the JSlider with each of the 3 global variables being a difficulty level
			//bottom-left print Choose Display
			//bottom-right have 2 JCheckBostartButton's one labeled Show Image the other labeled Show Name
			//In the third box 2 rows 1 column
			// top box of the third write the directions
			//bottom of the third box have a JButton labeled START that will take you to the main game
	    }
		public void actionPerformed(ActionEvent e)
		{
			if(pressedstart = true)//if start button pressed it takes to the game screen
			{
			     System.out.println("Start Button Pressed");
			     cl.show(p, "selectionCard");
			}
		}
		/*public void stateChanged(ChangeEvent q)
		{
			//if moved time increased or decreased************
		}*/
		public void paintComponent(Graphics g)
		{
			setBackground(Color.YELLOW);// will paint all the words in the correct place
			super.paintComponent(g);
			g.setFont(new Font("Times",Font.BOLD,50));//in the first box set font to plain and font size to 400 and write BORDERS;
			g.drawString("Borders",200,50);
			g.setFont(new Font("Times",Font.PLAIN,20));
			//g.drawString("Use the Slider to adjust difficulty,",690,600);
			//g.drawString("and select the checkbox's for your visual settings",690,610)
			g.drawString("Welcome to Borders, a game about countries and their neighbors",40,80);
			g.drawString("Instructions:",230,120);
			g.setFont(new Font("Times",Font.PLAIN,15));
		    g.drawString("You will be given a country at the top. Below that country you will given another one.",5,140);
			g.drawString("To say the lower country does border the upper country, click the left mouse button.",5,160);
			g.drawString("To say the lower country does NOT border the upper country, click the right mouse button.",5,180);
			g.drawString("If you get an answer wrong, you will lose a life.",5,200);
			g.drawString("You keep playing until you lose all your lives.",5,220);
			g.setFont(new Font("Times",Font.PLAIN,20));
			g.drawString("Good Luck",240,260);
		}
	} //TitleFrame
		
	class SelectionFrame extends JPanel implements ActionListener
	{
		JMenuItem america, europe, asia, africa, random;// 5 global JMenuItems
		JMenuItem[] countryItems;
		
		JButton playButton;// global JButton
		boolean pressed;// boolean pressed
		
		boolean americaselected;// 4 selected booleans
		boolean europeselected;
		boolean asiaselected;
		boolean africaselected;
		
		JPanel p2;
		JPanel newcardstack = new JPanel();
		JPanel newcard1 = new MyPanel();
		JPanel newcard2 = new MyPanel();
		JPanel newcard3 = new MyPanel();
		JPanel newcard4 = new MyPanel();
		CardLayout newcl;
		
		class MyPanel extends JPanel implements ActionListener
		{
			public MyPanel()
			{
				super();
			}
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
			}
			public void actionPerformed(ActionEvent e){}
		}	

		public SelectionFrame()
		{
			setArr();
			// run setArr to add values to arrays
			
			setLayout(new GridLayout(1, 2)); // setlayout Grid
			System.out.println("set grid layout #1");
			p2 = new JPanel();
			p2.setLayout(new GridLayout(2, 1));
			System.out.println("set grid layout #2");
			
			playButton = new JButton("PLAY");// instantiate the new button called PLAY
			//playButton.setBounds(30,300,520,100);							// gl
			playButton.addActionListener(this);// add ActionListener
			p2.add(playButton);// add the button
			add(p2);
			
			JMenuBar menuBar = new JMenuBar();
			JMenu continentMenu = new JMenu("SELECT A CONTINENT");// make the JMenu
																// labeled
																// Select gl
			countryItems = new MyJMI[MAX_COUNTRIES];
			System.out.println("Made the JMI array");

			america = new JMenuItem("America");// For the JMenuItems have all  											
			europe = new JMenuItem("Europe");  // the continents except       
			asia = new JMenuItem("Asia");      // Antartica and Austrailia    
			africa = new JMenuItem("Africa");                                  
			random = new JMenuItem("Random");
			menuBar.add(continentMenu);
			
			continentMenu.add(america);
			america.addActionListener(this);
			continentMenu.addSeparator();

			continentMenu.add(europe);
			europe.addActionListener(this);
			continentMenu.addSeparator();

			continentMenu.add(asia);
			asia.addActionListener(this);
			continentMenu.addSeparator();

			continentMenu.add(africa);
			africa.addActionListener(this);
			continentMenu.addSeparator();

			continentMenu.add(random);
			random.addActionListener(this);
			continentMenu.addSeparator();
			
			JMenu asiaMenu = new JMenu("Choose a country in Asia");
			int w = 0;System.out.println("made it to before the while loop");
			while (w <= LAST_ASIA_COUNTRY)
			{
				countryItems[w] = new MyJMI(name[w], w);
				countryItems[w].addActionListener(this);
				asiaMenu.add(countryItems[w]);				
				w++;
			}
			asiaMenu.addActionListener(this);
			
			JMenu europeMenu = new JMenu("Choose a country in Europe");
			while (w <= LAST_EUROPE_COUNTRY)
			{
				countryItems[w] = new MyJMI(name[w], w);
				countryItems[w].addActionListener(this);
				europeMenu.add(countryItems[w]);
				w++;
			}
			europeMenu.addActionListener(this);

			JMenu americaMenu = new JMenu("Choose a country in the Americas");
			while (w <= LAST_AMERICA_COUNTRY)
			{
				countryItems[w] = new MyJMI(name[w], w);
				countryItems[w].addActionListener(this);
				americaMenu.add(countryItems[w]);
				w++;
			}
			americaMenu.addActionListener(this);

			JMenu africaMenu = new JMenu("Choose a country in Africa");
			while (w <= LAST_AFRICA_COUNTRY)
			{
				countryItems[w] = new MyJMI(name[w], w);
				countryItems[w].addActionListener(this);
				africaMenu.add(countryItems[w]);
				w++;
			}
			africaMenu.addActionListener(this);
			menuBar.add(continentMenu);
			p2.add(menuBar);
			// //////
			System.out.println("added continent menus");

			newcl = new CardLayout();
 
			newcardstack.setBackground(Color.YELLOW);
			newcard1.setBackground(Color.YELLOW);
			newcard2.setBackground(Color.YELLOW);
			newcard3.setBackground(Color.YELLOW);
			newcard4.setBackground(Color.YELLOW);
			newcardstack.setLayout(newcl);
			newcardstack.add(newcard1, "America");
			newcard1.add(americaMenu);
			newcardstack.add(newcard2, "Europe");
			newcard2.add(europeMenu);
			newcardstack.add(newcard3, "Africa");
			newcard3.add(africaMenu);
			newcardstack.add(newcard4, "Asia");
			newcard4.add(asiaMenu);
			add(newcardstack);// there is a cardlayout on the right or bottom
								// box of the gridlayout so the Jmenu for the
								// continent the user selects will pop up
			// Depending on what continent the user selects
			
			System.out.println("Got through the SelectionFrame constructor");
		}// Selection Frame
		
		public void paintComponent(Graphics g)
		{
			setBackground(Color.YELLOW);
			super.paintComponent(g);
		}
		
		public void setArr()
		// setArr method
		{
			/*
			 * Scanner s = null; try { s = new Scanner(location);
			 * }catch(FileNotFoundException f) {
			 * System.out.println("Cannot find the locations"); System.exit(1);
			 * }
			 */
			// new scanner for locations file
			Scanner t = null;
			try
			{
				t = new Scanner(border);
			} catch (FileNotFoundException f)
			{
				System.out.println("Cannot find the borders");
				System.exit(1);
			}
			// new scanner for boolean file (1s and 0s)
			Scanner u = null;
			try
			{
				u = new Scanner(names);
			} catch (FileNotFoundException f)
			{
				System.out.println("Cannot find the names");
				System.exit(1);
			}
			// new scanner for country names file
			
		    // Not yet implemented
			/*
			 * int locr = 0; int locc = 0; String locw = ""; while(s.hasNext())
			 * { locw = s.next(); if(locw.equals("/")) { locr++; locc = 0; }
			 * else { loc[locr][locc] = locw; locc++; } }
			 */
			// add each word (next) to each index of the location array
			// switch rows when a slash appears in the file

			for (int w = 0; w < MAX_COUNTRIES; w++)
				for (int v = 0; v < MAX_COUNTRIES; v++)
					borders[w][v] = false;
			
			int bordersRow = 0;
			String bordersLine = "";
			while (t.hasNextLine())
			{
				bordersLine = t.nextLine();
				int out = 0;
				int in = 0;
				while (in < bordersLine.length())
				{
					if (bordersLine.charAt(in) == '1')
					{
						borders[bordersRow][out] = true;
					} else if (bordersLine.charAt(in) == ',')
					{
						out++;
					}
					in++;
				}
				bordersRow++;
			}
			System.out.println("setted borders");			
			// parse the csv(comma separated values) border data file
			// get each number (or /) from boolean file as a string
			// if the string is 1, then set value in array to true
			// if the string is 0, then set value in array to false
			// if the string is /, then go to next row in array.
			
			
			int namc = 0;
			while (u.hasNextLine())
			{
				name[namc] = u.nextLine();
				namc++;
			}
			// add values from the country name file
			System.out.println("setted name");			
			
			String wname = "";
			for (int w = 0; w < MAX_COUNTRIES; w++)
			{
					wname = name[w] + ".png";
					images[w] = Toolkit.getDefaultToolkit().getImage(wname);
					imagesSmall[w] = Toolkit.getDefaultToolkit().getImage("smallMaps/" +wname);
			}
			// use these name values to decide which image to get for each
			// corresponding index in the image array.
			System.out.println("setted images");
			
		}// SetArr
		
		public void actionPerformed(ActionEvent e)
		{ 
			// ///////
			if (e.getSource() == america)
			{ 
				
				System.out.println("America");
				newcl.show(newcardstack, "America");// each of the 4 boolean
												// selected's correspond to												// a specific country
			} 
			else if (e.getSource() == europe)
			{
				System.out.println("europe");
				newcl.show(newcardstack, "Europe");
			} 
			else if (e.getSource() == africa)
			{	
				newcl.show(newcardstack, "Africa");
				
			} 
			else if (e.getSource() == asia)
			{
				newcl.show(newcardstack, "Asia");
			} 
			else if (e.getSource() == random)
			{
				// take to the main game page
			} 
			else if (e.getSource() != playButton) 
			{
				
				System.out.println("Country");
				setCountry = ((MyJMI) e.getSource()).index();
		    }
			// ///////

			if (e.getSource() == playButton)
			{
				if (setCountry != -1)
					cl.next(p);
				// if pressed == true take to the main game page
			}
			// ////////

			// for()
			// {//create a loop for if-else statements

			// bunch of if-else statements depending on the continent
			// selectesd in the JMenu

			// then a nother JMenu comes up with the option of either having
			// a random countryor the *user selects a country*
			// the user clicks the play button and it takes them to the main
			// game screen
			// }
		}
		
		class MyJMI extends JMenuItem
		{
			final int itemIndex;
			public MyJMI(String name, int index)
			{
				super(name);
				itemIndex = index;
			}
			public void addActionListener(ActionListener l)
			{
				super.addActionListener(l);
			}
			public int index()
			{
				return itemIndex;
			}
			
		}
	}// Selection Frame class

	class GamePanel extends JPanel
	// class GamePanel
	{
		final int MAX_LIVES = 7;
		int life;
		Life lf;
		// variable life for number of lives (int)
		
		int scw;
		// integer variable for country's order alphabetically (starts at 0)
		
		private boolean imgbool;
		private boolean txtbool;

		public GamePanel()
		// constructor
		{
			setLayout(new BorderLayout());
			// set border layout
			Header h = new Header();
			add(h, BorderLayout.NORTH);
			// north is Header panel
			GraphicsIcon i = new GraphicsIcon();
			add(i, BorderLayout.CENTER);
			// center is Icon panel
		}

		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
		}

		// paint component
		class Header extends JPanel
		// Header panel class
		{
			public Header()
			// constructor
			{
				life = MAX_LIVES;
				setLayout(new GridLayout(1, 3));
				// set grid layout(3 columns)
				lf = new Life();
				add(lf);
				// first panel is Life
				HeaderIcon hi = new HeaderIcon();
				add(hi);
				// second panel is HeaderIcon
				MenuPanel mp = new MenuPanel();
				add(mp);
				// third panel is MenuPanel
			}

			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
			}
			// paint component
		}

		class Life extends JPanel
		// Life panel class
		{
			public Life()
			// constructor
			{
			}
			
			public void paintComponent(Graphics g)
			// paintcomponent
			{
				g.setColor(Color.RED);
				super.paintComponent(g);
				int[] heartTrix = new int[3];
				int[] heartTriy = new int[3];
				
				for (int w = 0; w < life; w++)
				{
					
					w *= 29;
					g.fillArc(w , 2, 10, 12, 0, 180);
					g.fillArc(w + 10, 2, 10, 12, 0, 180);
					heartTrix[0] = w;
					heartTriy[0] = 7;
					heartTrix[1] = w + 20;
					heartTriy[1] = 7;
					heartTrix[2] = w + 10;
					heartTriy[2] = 17;
					g.fillPolygon(heartTrix, heartTriy, 3);
					w /= 29;

				}
				// use loop (counter is variable life) to draw hearts
				// use coordinates of heart that was designed on graph paper
			}
		}

		class HeaderIcon extends JPanel
		// HeaderIcon class
		{
			public HeaderIcon()
			// constructor
			{
				// ///////////////////
				// only for test //
				// ///////////////////
//				setCountry = 104; // (USA)
				imgbool = true;
				txtbool = true;
			}

			public void paintComponent(Graphics g)
			// paintcomponent
			{
				setBackground(Color.WHITE);
				super.paintComponent(g); 

				if (imgbool)
					g.drawImage(imagesSmall[setCountry],50,10,100,100, this);

				if (txtbool)
					g.drawString(name[setCountry],
							95 - name[setCountry].length() * 4, 15);
				
				// use setCountry variable (int) index in image and/or name
				// arrays and draw them
			}
		}// HeaderIcon

		class MenuPanel extends JPanel
		// MenuPanel class
		{
			private JMenu mpm;
			private JMenuBar mpbar;
			private JMenuItem mpiteml, mpitemr;

			public MenuPanel()
			// constructor
			{
				setPreferredSize(new Dimension(100, 100));
				mpbar = new JMenuBar();
				mpm = new JMenu("Help");
				mpiteml = new JMenuItem("Left Click: Yes, it borders.");
				mpitemr = new JMenuItem("Right Click: No, it doesn't.");
				// initialize menu bar and 2 items

				mpbar.add(mpm);
				mpm.add(mpiteml);
				mpm.add(mpitemr);

				add(mpbar);

				// frame.setJMenuBar(mpbar);
				// add components and action listener
			}

			public void paintComponent(Graphics g)
			// paintcomponent
			{
				super.paintComponent(g);
			}
		}

		class GraphicsIcon extends JPanel implements MouseListener
		// GraphicsIcon panel class
		{
			int cnt = 0;
			private Color c;
			private boolean done;
			private boolean resp;

			public GraphicsIcon()
			// constructor
			{
				setPreferredSize(new Dimension(320, 320));
			    done = false;
				resp = true;
				// boolean done at false
				c = Color.WHITE;
				// set Color c to white
				addMouseListener(this);
				scw = 0;

				// ///////////////
				// for test //
				// ///////////////
				//qCountry = 87; // (Canada) 
			}

			public void paintComponent(Graphics g)
			// paintcomponent
			{	
				//Countries on border of Europe and Asia
				final int AZERBAIJAN    = 2;
				final int CHINA         = 7;
				final int GEORGIA       = 8;
				final int KAZAKHSTAN    = 15;
				final int MONOGOLIA     = 21;
				final int NORTH_KOREA   = 24;
				final int TURKEY        = 35;
				final int BULGARIA      = 47;
				final int GREECE        = 55;
				final int RUSSIA        = 72;
				
				//Countries on border of Africa and Asia
				final int ISRAEL        = 13;
				final int EGYPT         = 119;
				
				super.paintComponent(g); 
				setBackground(c);
				// set background to c
				g.setFont(new Font("Calibri", Font.PLAIN, 25));
				
				if(setCountry == cnt)
				{	
					cnt++;
					if (cnt >= MAX_COUNTRIES)
						cnt = 0;
				}	
				else if(borders[setCountry][cnt])
					qCountry = cnt;
				else
				{
					qCountry = setCountry;
					while (qCountry == setCountry)	
					{
						//Limit countries available to be qCountry by continent
						if(setCountry == AZERBAIJAN  		
								|| setCountry == CHINA  
								|| setCountry == GEORGIA  
								|| setCountry == KAZAKHSTAN 
								|| setCountry == MONOGOLIA 
								|| setCountry == NORTH_KOREA 
								|| setCountry == TURKEY 
								|| setCountry == BULGARIA 
								|| setCountry == GREECE 
								|| setCountry == RUSSIA)
							qCountry = (int)(Math.random()*(NUM_ASIA_COUNTRIES + NUM_EUROPE_COUNTRIES));
																					//Europe or Asia
						else if(setCountry == ISRAEL     
								|| setCountry == EGYPT)
							qCountry = (int)(Math.random()*(NUM_ASIA_COUNTRIES + NUM_AFRICA_COUNTRIES)
																	+ FIRST_AFRICA_COUNTRY)%MAX_COUNTRIES;
																					 //Asia or Africa
						else if(setCountry <= LAST_ASIA_COUNTRY)
							qCountry = (int)(Math.random()*NUM_ASIA_COUNTRIES);			     //Asia
						else if(setCountry <= LAST_EUROPE_COUNTRY)
							qCountry = (int)(Math.random()*NUM_EUROPE_COUNTRIES
																	+ FIRST_EUROPE_COUNTRY); //Europe
						else if(setCountry <= LAST_AMERICA_COUNTRY)
							qCountry = (int)(Math.random()*NUM_AMERICA_COUNTRIES 
																	+ FIRST_AMERICA_COUNTRY);//Americas
						else if(setCountry <= LAST_AFRICA_COUNTRY)
							qCountry = (int)(Math.random()*NUM_AFRICA_COUNTRIES
																	+ FIRST_AFRICA_COUNTRY);  //Africa
					}	
				}	
				cnt++;
				if (cnt >= MAX_COUNTRIES)
					cnt = 0;
				
				if (imgbool)
				   g.drawImage(images[qCountry], 155,  0,  270,  270,  this);
				
				if (txtbool)	
					g.drawString(name[qCountry],
								300 - (int) (12.5 / 2 * name[qCountry].length()),
								290);

				
				// use qCountry variable (int) index in image and/or name arrays
				// and draw them
				// set timer to difficulty level variable
			}

			public void mouseClicked(MouseEvent e)
			// mouse clicked event
			{
				final int LEFT_CLICK = 1;
				final int RIGHT_CLICK = 3;
				
				//System.out.print("GraphicsIcon mouse event");
					if (e.getX() > 0 && e.getX() < 650 && e.getY() > 0
							&& e.getY() < 400)
				// if within a certain center square...
				{
					if (e.getButton() == LEFT_CLICK)
						resp = true;
					// if left click, set value resp to true
					if (e.getButton() == RIGHT_CLICK)
						resp = false;
					// if right click, set value resp to false
					if (resp == borders[setCountry][qCountry])
					{
						c = Color.GREEN;
						done = true;
					}
					// if resp is equal to index for setCountry and qCountry's
					// border, set c to green, done is true
					else
					{
						c = Color.RED;
						life--;
						if (life == 0)
						{
							cl.show(p, "selectionCard");
							life = MAX_LIVES;
						}
						lf.repaint();
						done = true;
					}
					if (borders[setCountry][qCountry])
						scw++;
					// if resp is not equal, set color c to red, decrement # of
					// lives, done is true
				}
				// cancel timer
				repaint();
				// repaint
			}

			public void mousePressed(MouseEvent e)
			{
				//System.out.print("mousePressed");
			}

			public void mouseEntered(MouseEvent e)
			{
				//System.out.print("mouseEntered");
		    }

			public void mouseExited(MouseEvent e)
			{
				//System.out.print("mouseExited");
		    }

			public void mouseReleased(MouseEvent e)
			{
				//System.out.print("mouseReleased");
		    }
			
			// timer task sets color c to red and decrements # of lives, sets
			// done to true
		}
	}
	
	//  Not yet implemented
	class DragPanel extends JPanel
	{
		public DragPanel()
		{
		}
	}


}