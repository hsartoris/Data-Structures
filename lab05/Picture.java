import java.util.*;
import java.awt.*;
import java.awt.event.* ;

/*
 * Class to draw on a canvas in a window.  Provides a quit button.
 */   
public class Picture extends Frame implements ActionListener   {
    // maximum width of window
    public static final int MAX_WIDTH = 600 ;
    // maximum height of window
    public static final int MAX_HEIGHT = 900 ;

    private Button quit ;
    private MyCanvas canvas;
    private ArrayList<Line> lines; // lines to be drawn

    /**
     * Default constructor to lay out the window.
     */
    public Picture() {

	lines = new ArrayList<Line>();

        setTitle("Picture Window") ;
        setSize(MAX_WIDTH, MAX_HEIGHT + 50) ;

        canvas = new MyCanvas(this) ;
        add("Center", canvas) ;  

        Panel p = new Panel() ;
        p.setLayout(new FlowLayout()) ;

	// Add a quit button
	quit = new Button("Quit") ;
	p.add(quit) ;
	quit.addActionListener(this) ;
	add("South", p) ;  
    } // end of constructor method
  
    /**
       @return ArrayList of lines.
    */
    public ArrayList<Line> getLines() {
	return lines;
    }

    /**
     * handles button depression events for quitButton.
     */
    public void actionPerformed(ActionEvent event)    {
        dispose() ;
        System.exit(0) ;
    }
      
    /**
     * draws the window on the screen with the specified shapes
     */
    public void draw() {
        setVisible(true) ;
    } 

    /**
       Adds the lines that constitute the tree to lines.
       @param YOUR CHOICE
    */
    public void tree(int x, int y, double heading, double variance) {
    	double repeat = Math.random();
    	if (repeat < .25) repeat = 1;
    	else if (repeat > .9) repeat = 3;
    	else repeat = 2;
    	
    	for (int i = 0; i < repeat; i++) {
    		double length =  (y / (4 + ((Math.random() * 2) - 1))) + 20; // stack overflow due to logarithmic approach of 0 y without + 20
    		//double length = (y / 3) + 20;
    		double newHeading = (Math.random() * variance) - (variance / 2) + heading;
    		int newX = (int) (x + (length * Math.sin(newHeading)));
    		int newY = (int) (y - (length * Math.cos(newHeading)));
    		if (newX < 0 || newX > MAX_WIDTH || newY > MAX_HEIGHT || newY < 0) continue;
    		lines.add(new Line(x, y, newX , newY));
    		tree(newX, newY, newHeading, variance); //changing the way the tree passes the variance will change the structure
    	}
    	
    	
    }

    public static void main(String[] args) {
	Picture pic = new Picture();
	pic.tree(MAX_WIDTH/2, MAX_HEIGHT, 0, .55); // stay around or below this variance level, weird root bushes result otherwise
	pic.draw();
    }

} // end of class Picture


class MyCanvas extends Canvas {
    private Picture parent;

    public MyCanvas(Picture p) {
	parent = p;
    }

    /**
       Called implicitly whenever the canvas needs
       to be redrawn.
       @param g the current graphics context 
    */
    public void paint(Graphics g) {
	ArrayList<Line> lines = parent.getLines();
	Graphics2D g2d = (Graphics2D)g;

	g2d.setColor(Color.white);
	g2d.fillRect(0,0,parent.MAX_WIDTH,parent.MAX_HEIGHT);
	g2d.setColor(new Color(70,40,10)); // brown

	// Draw all lines in the arraylist of lines.
	for (int i = 0; i < lines.size(); i++) {
	    Line l = lines.get(i);
	    g2d.setStroke(new BasicStroke(l.width,
					  BasicStroke.CAP_ROUND,
					  BasicStroke.JOIN_ROUND));

	    g2d.drawLine(l.x1,l.y1,l.x2,l.y2);
	}
    }

} // end of MyCanvas class

// Package class to bundles points for a line segment.
class Line {
    int x1, x2, y1, y2;
    float width;

    public Line(int x1, int y1, int x2, int y2,float w) {
	this.x1 = x1;
	this.y1 = y1;
	this.x2 = x2;
	this.y2 = y2;
	this.width = w;
    }
    
    public Line(int x1, int y1, int x2, int y2) {
    	this.x1 = x1;
    	this.y1 = y1;
    	this.x2 = x2;
    	this.y2 = y2;
    	this.width = (float) (25 * Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)) / Picture.MAX_HEIGHT);
    }
}  // end of Line class
