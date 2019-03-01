/*
	Originally written by Bruce A. Maxwell a long time ago.
	Updated by Brian Eastwood and Stephanie Taylor more recently

	Creates a window using the JFrame class.

	Creates a drawable area in the window using the JPanel class.

	The JPanel calls the Landscape's draw method to fill in content, so the
	Landscape class needs a draw method.
    
    Updated by Emmett Burns in May 2018 for the Hunt The Wumpus Project

 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

//from basic
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.Point;


import javax.swing.JLabel;
import javax.swing.event.MouseInputAdapter;

import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Displays a Board graphically using Swing.  The Board can be
 * displayed at any scale factor.  The only change from project 2
 * is the use of the Board class instead of the Landscape class.
 * @author bseastwo
 */
public class LandscapeDisplay extends JFrame
{
    protected Landscape scape; 
    private LandscapePanel canvas;
    //Hunt the Wumpus field
    private HuntTheWumpus htw;
	//field to allow buttons 
	private JPanel panel;

    /**
     * Initializes a display window for a Landscape.
     * @param scape the Landscape to display
     * @param scale controls the relative size of the display
     */
    public LandscapeDisplay(Landscape scape, HuntTheWumpus wump)
    {
        // setup the window
        super("Hunt the Wumpus");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.scape = scape;
        this.htw = wump;
        // create a panel in which to display the Landscape
        this.canvas = new LandscapePanel( this.scape.getWidth(),
																					this.scape.getHeight() );

        // add the panel to the window, layout, and display
        this.add(this.canvas, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);

        //for interpreting keyboard inputs
        Control control = new Control();
		this.addKeyListener(control);
		this.setFocusable(true);
		this.requestFocus();
		
		//constructs buttons
		this.panel = new JPanel();
		JButton l1 = new JButton("Level 1");
		JButton l2 = new JButton("Level 2");
		JButton restart = new JButton("Restart");
		JButton quit = new JButton("Quit");
		
		panel.add( l1 );
		panel.add( l2 );
		panel.add( restart );
		panel.add( quit );
		
		this.add( panel, BorderLayout.SOUTH);
		this.pack();
		
		l1.addActionListener( control );
		l2.addActionListener( control );
		restart.addActionListener( control );
		quit.addActionListener( control );
    }

    /**
     * Saves an image of the display contents to a file.  The supplied
     * filename should have an extension supported by javax.imageio, e.g.
     * "png" or "jpg".
     *
     * @param filename  the name of the file to save
     */
    public void saveImage(String filename)
    {
        // get the file extension from the filename
        String ext = filename.substring(filename.lastIndexOf('.') + 1, filename.length());

        // create an image buffer to save this component
        Component tosave = this.getRootPane();
        BufferedImage image = new BufferedImage(tosave.getWidth(), tosave.getHeight(), 
                                                BufferedImage.TYPE_INT_RGB);

        // paint the component to the image buffer
        Graphics g = image.createGraphics();
        tosave.paint(g);
        g.dispose();

        // save the image
        try
                {
                        ImageIO.write(image, ext, new File(filename));
                }
        catch (IOException ioe)
                {
                        System.out.println(ioe.getMessage());
                }
    }

    /**
     * This inner class provides the panel on which Landscape elements
     * are drawn.
     */
    private class LandscapePanel extends JPanel
    {
        /**
         * Creates the panel.
         * @param width     the width of the panel in pixels
         * @param height        the height of the panel in pixels
         */
        public LandscapePanel(int width, int height)
        {
                super();
                this.setPreferredSize(new Dimension(width, height));
                this.setBackground(Color.white);
        }

        /**
         * Method overridden from JComponent that is responsible for
         * drawing components on the screen.  The supplied Graphics
         * object is used to draw.
         * 
         * @param g     the Graphics object used for drawing
         */
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            scape.draw( g );
        } // end paintComponent
        
    } // end LandscapePanel

    public void update() {
        Graphics g = canvas.getGraphics();
        this.requestFocus();
        canvas.paintComponent( g );
    }

    //from basic: interprets keyboard inputs
    private class Control extends KeyAdapter implements ActionListener {

        public void keyTyped(KeyEvent e) {
			if(htw.inProgress()){
				if( ("" + e.getKeyChar()).equalsIgnoreCase("w") ) {
					System.out.println("up");
					htw.update("w");
				}
				else if( ("" + e.getKeyChar()).equalsIgnoreCase("s") ) {
					System.out.println("down");
					htw.update("s");
				}
				else if( ("" + e.getKeyChar()).equalsIgnoreCase("d") ) {
					System.out.println("right");
					htw.update("d");
				}
				else if( ("" + e.getKeyChar()).equalsIgnoreCase("a") ) {
					System.out.println("left");
					htw.update("a");
				}
				else if( ("" + e.getKeyChar()).equalsIgnoreCase(" ") ) {
					System.out.println("arm/disarm");
					htw.update(" ");
				}
            }
        }

        public void actionPerformed(ActionEvent event) {
        	if( event.getActionCommand().equalsIgnoreCase("Restart") ) {
                //sets all Vertexes to not visible 
				System.out.println("restart");
                htw.restart();
            }
            else if( event.getActionCommand().equalsIgnoreCase("Quit") ) {
				htw.quit();
            }
			else if( event.getActionCommand().equalsIgnoreCase("Level 1") ) {
				System.out.println("Level 1");
				htw.levelOne();
			}
			else if( event.getActionCommand().equalsIgnoreCase("Level 2") ) {
				System.out.println("Level 2");
				htw.levelTwo();
			}
        }
	}


		// test function that creates a new LandscapeDisplay and populates it with 200 agents.
    public static void main(String[] args) throws InterruptedException {
    	Landscape scape = new Landscape();
    	HuntTheWumpus wump = new HuntTheWumpus();
    	LandscapeDisplay display = new LandscapeDisplay(scape, wump);
    	while(true){
    		display.repaint();
    		Thread.sleep(100);
    	}
    }
}
