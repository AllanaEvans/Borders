//Dylan Evans
//2015-05-07
//Borders

import javax.swing.*;

import java.awt.event.*;
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
		BorPan p = new BorPan();
		setContentPane(p);
		setVisible(true);
		// set values (resizable, visible, etc.)
	}
}

class BorPan extends JPanel
{
	private static boolean[][] bor;
	// 2-d boolean array for bordering countries
	private static int[][] loc;
	// 2-d array for locations of countries (1st row x, 2nd row y)
	Image[] igs;
	// 1-d image array for country images
	private static String[] name;
	// 1-d string array for country names
	// private static File location = new File("Locations.txt");
	private static File border = new File("country borders2.csv");
	private static File names = new File("country list.csv");
	private static CardLayout cl;
	private static JPanel p;
	private static JPanel card1;
	private static SelectionFrame card2;
	private static GamePanel card3;
	private static Drag card4;
	private static JPanel card5;
	int setCountry;
	int qCountry;

	public BorPan()
	{
		bor = new boolean[155][155];
		name = new String[155];
		setCountry = -1;
		cl = new CardLayout();
		p = new JPanel();
		p.setLayout(cl);
		card1 = new JPanel();// ///////////////////////////////////////////////////////////////////////////////////////
		card2 = new SelectionFrame();
		card3 = new GamePanel();
		card4 = new Drag();
		card5 = new JPanel();// ///////////////////////////////////////////////////////////////////////////////////////
		p.add(card1, "1");
		p.add(card2, "2");
		p.add(card3, "3");
		add(p);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	}

	// CORRECT? Dummy for class
	class Drag extends JPanel
	{
		public Drag()
		{
		}
	}

	class SelectionFrame extends JPanel implements ActionListener
	{
		JMenuItem america, europe, asia, africa, random;// 5 global JMenus
		JMenuItem[] countryItems;
		JButton bb;// global JButton
		boolean pressed;// boolean pressed
		boolean americaselected;// 4 selected booleans
		boolean europeselected;
		boolean asiaselected;
		boolean africaselected;
		JPanel p;
		JPanel newcardstack = new JPanel();
		JPanel newcard1 = new JPanel();
		JPanel newcard2 = new JPanel();
		JPanel newcard3 = new JPanel();
		JPanel newcard4 = new JPanel();
		CardLayout newcl;
		JLabel newlabel1 = new JLabel("America");
		JLabel newlabel2 = new JLabel("Europe");
		JLabel newlabel3 = new JLabel("Africa");
		JLabel newlabel4 = new JLabel("Asia");

		public SelectionFrame()
		{
			setArr();
			// run setArr to add values to arrays
			setBackground(Color.YELLOW);
			setLayout(new GridLayout(1, 2)); // setlayout Grid
			p = new JPanel();
			p.setLayout(new GridLayout(2, 1));
			bb = new JButton("PLAY");// instastiate the new button called PLAY
										// gl
			bb.addActionListener(this);// add ActionListener
			p.add(bb);// add the button
			MyCanvas canvas = new MyCanvas();
			setContentPane(canvas);
			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			JMenu CountryMenu = new JMenu("SELECT A COUNTRY");// make the JMenu
																// labeled
																// Select gl
			countryItems = new JMenuItem[155];

			america = new JMenuItem("America");// For the JMenuItems have all
												// the continents except
												// Antartica and Austrailia
			europe = new JMenuItem("Europe");
			asia = new JMenuItem("Asia");
			africa = new JMenuItem("Africa");
			random = new JMenuItem("Random");
			CountryMenu.add(america);
			america.addActionListener(canvas);
			CountryMenu.addSeparator();

			CountryMenu.add(europe);
			europe.addActionListener(canvas);
			CountryMenu.addSeparator();

			CountryMenu.add(asia);
			asia.addActionListener(canvas);
			CountryMenu.addSeparator();

			CountryMenu.add(africa);
			africa.addActionListener(canvas);
			CountryMenu.addSeparator();

			CountryMenu.add(random);
			random.addActionListener(canvas);
			CountryMenu.addSeparator();

			menuBar.add(CountryMenu);

			JMenu asiaMenu = new JMenu("Choose a country in Asia");
			int w = 0;
			while (w < 41)
			{
				countryItems[w] = new JMenuItem(name[w]);
				countryItems[w].addActionListner(this);
				asiaMenu.add(countryItems[w]);
				asiaMenu.addSeparator();
			}
			asiaMenu.addActionListener(this);
			menuBar.add(asiaMenu);

			JMenu europeMenu = new JMenu("Choose a country in Europe");
			while (w < 83)
			{
				countryItems[w] = new JMenuItem(name[w]);
				countryItems[w].addActionListner(this);
				europeMenu.add(countryItems[w]);
				europeMenu.addSeparator();
			}
			europeMenu.addActionListener(this);
			menuBar.add(europeMenu);

			JMenu americaMenu = new JMenu("Choose a country in the Americas");
			while (w < 107)
			{
				countryItems[w] = new JMenuItem(name[w]);
				countryItems[w].addActionListner(this);
				americaMenu.add(countryItems[w]);
				americaMenu.addSeparator();
			}
			americaMenu.addActionListener(this);
			menuBar.add(americaMenu);

			JMenu africaMenu = new JMenu("Choose a country in the Americas");
			while (w < 155)
			{
				countryItems[w] = new JMenuItem(name[w]);
				countryItems[w].addActionListner(this);
				africaMenu.add(countryItems[w]);
				africaMenu.addSeparator();
			}
			africaMenu.addActionListener(this);
			menuBar.add(africaMenu);
			// //////

			newcl = new CardLayout();

			newcardstack.setBackground(Color.YELLOW);
			newcard1.setBackground(Color.YELLOW);
			newcard2.setBackground(Color.YELLOW);
			newcard3.setBackground(Color.YELLOW);
			newcard4.setBackground(Color.YELLOW);
			newcardstack.setLayout(newcl);
			newcardstack.add(newcard1, "America");
			newcard1.add(newlabel1);
			newcard1.add(AmericaMenu);
			newcardstack.add(newcard2, "Europe");
			newcard2.add(newlabel2);
			newcard2.add(EuropeMenu);
			newcardstack.add(newcard3, "Africa");
			newcard3.add(newlabel3);
			newcard3.add(AfricaMenu);
			newcardstack.add(newcard4, "Asia");
			newcard4.add(newlabel4);
			newcard4.add(AsiaMenu);
			add(newcardstack);// there is a cardlayout on the right or bottom
								// box of the gridlayout so the Jmenu for the
								// continent the user selects will pop up
			// Depending on what continent the user selects
		}// Selection Frame

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
			/*
			 * int locr = 0; int locc = 0; String locw = ""; while(s.hasNext())
			 * { locw = s.next(); if(locw.equals("/")) { locr++; locc = 0; }
			 * else { loc[locr][locc] = locw; locc++; } }
			 */
			// add each word (next) to each index of the location array
			// switch rows when a slash appears in the file

			int borr = 0;
			String borw = "";
			while (t.hasNextLine())
			{
				borw = t.nextLine();
				int out = 0;
				int in = 0;
				while (in < borw.length())
				{
					if (borw.charAt(in) == '1')
					{
						bor[borr][out] = true;
					} else if (borw.charAt(in) == ',')
					{
						out++;
					}
					in++;
				}
				borr++;
			}
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
			for (int w = 0; w < name.length; w++)
			{
				igs[w] = Toolkit.getDefaultToolkit().getImage(name[w] + ".gif");
			}
			// use these name values to decide which image to get for each
			// corresponding index in the image array.
		}// SetArr

		class MyCanvas extends JPanel implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				// ///////
				if (e.getSource() == america)
				{
					newcl.show(newcard1, "America");// each of the 4 boolean
													// selected's correspond to
													// a specific country
				} else if (e.getSource() == europe)
				{
					newcl.show(newcard2, "Europe");
				} else if (e.getSource() == africa)
				{
					newcl.show(newcard3, "Africa");
				} else if (e.getSource() == asia)
				{
					newcl.show(newcard4, "Asia");
				} else if (e.getSource() == random)
				{
					newcl.show(new JPanel());
					// take to the main game page
				} else if (e.getSource() != bb)
					setCountry = countryItems.indexOf(e.getSource());
				// ///////

				if (e.getSource() == bb)
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
		}// MyCanvass
	}// Selection Frame class

	// indexs 1 - 41 Asia
	// indexs 42 - 83 Europe
	// indexs 84 - 107 'Muricas
	// indexs 108 - 155 Africa
	class GamePanel extends JPanel
	// class GamePanel
	{
		int life;
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
			Gicon i = new Gicon();
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
				setLayout(new GridLayout(1, 3));
				// set grid layout(3 columns)
				Life lf = new Life();
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
			{
			}

			// constructor
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
					g.fillArc(w + 4, 2, 10, 12, 0, 180);
					g.fillArc(w + 14, 2, 10, 12, 0, 180);
					heartTrix[0] = w + 4;
					heartTriy[0] = 7;
					heartTrix[1] = w + 24;
					heartTriy[1] = 7;
					heartTrix[2] = w + 14;
					heartTriy[2] = 17;
					g.fillPolygon(heartTrix, heartTriy, 3);
					w /= 29;

				}
				// use loop (counter is variable life) to draw hearts
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
				setCountry = 104; // (USA)
				imgbool = true;
				txtbool = true;
			}

			public void paintComponent(Graphics g)
			// paintcomponent
			{
				setBackground(Color.WHITE);
				super.paintComponent(g); // Changed 300s to 100s since only 3rd
											// of screen !!!!

				if (imgbool)
					// ADD IMAGES!!!!
					// g.drawImage(igs[setCountry],
					// (300 - (50 / igs[setCountry].getHeight())
					// * igs[setCountry].getWidth() / 2), 0,
					// (50 / igs[setCountry].getHeight())
					// * igs[setCountry].getWidth(), 50, this);

					if (txtbool)
						g.drawString(name[setCountry],
								100 - name[setCountry].length() * 5, 50);
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
				mpiteml = new JMenuItem("LMB: Yes, it borders.");
				mpitemr = new JMenuItem("RMB: No, they don't.");
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

		class Gicon extends JPanel implements MouseListener
		// Gicon panel class
		{
			private Color c;
			private boolean done;
			private boolean resp;

			public Gicon()
			// constructor
			{
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
				qCountry = 87; // (Canada)
			}

			public void paintComponent(Graphics g)
			// paintcomponent
			{
				setBackground(c);
				// set background to c
				g.setFont(new Font("Calibri", Font.PLAIN, 25));
				qCountry = (int) (Math.random() * 155);
				if (imgbool)
					// ADD IMAGES!!!!
					// g.drawImage(
					// igs[qCountry],
					// 300 - 145 / igs[qCountry].getHeight()
					// * igs[qCountry].getWidth(),
					// 10,
					// 290 / igs[qCountry].getHeight()
					// * igs[qCountry].getWidth(), 290, this);
					if (txtbool)
						g.drawString(
								name[qCountry],
								300 - (int) (12.5 / 2 * name[qCountry].length()),
								312);
				// use qCountry variable (int) index in image and/or name arrays
				// and draw them
				// set timer to difficulty level variable
			}

			public void mouseClicked(MouseEvent e)
			// mouse clicked event
			{
				if (e.getX() > 150 && e.getX() < 450 && e.getY() > 100
						&& e.getY() < 225)
				// if within a certain center square...
				{
					if (e.getButton() == 1)
						resp = true;
					// if left click, set value resp to true
					if (e.getButton() == 3)
						resp = false;
					// if right click, set value resp to false
					if (resp == bor[setCountry][qCountry])
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
						done = true;
					}
					if (bor[setCountry][qCountry])
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
			}

			public void mouseEntered(MouseEvent e)
			{
			}

			public void mouseExited(MouseEvent e)
			{
			}

			public void mouseReleased(MouseEvent e)
			{
			}
			// timer task sets color c to red and decrements # of lives, sets
			// done to true
		}
	}
}
