import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
   Displays binary search trees.  
   @author: S. Anderson
*/

public class BTDisplay extends JFrame {
    // Frame dimensions
    private int WIDTH = 500; 
    private int HEIGHT = 300;
    private LinkedBinaryTree tree; // tree to be drawn
    private int dx, dy; // grid size
    private int x0, y0; // init pt of tree
    private int sx, sy; // size of nodes
    private Color nodeColor; // color of nodes
    private Color lineColor; // color of lines
    private Color textColor; // color of text
    // constants (see related variable in lower case for interpretation)
    private final int X0 = 50;
    private final int Y0 = 50;
    private final int SX = 30;
    private final int SY = 30;
    private final int DX = 30;
    private final int DY = 50;


    public BTDisplay(LinkedBinaryTree t) {
	tree = t;

	nodeColor = new Color(255,200,200); // pink
        textColor = new Color(50,50,50);  // gray
	lineColor = new Color(30,30,240); // blue

	//  starting position for tree
	x0 = X0; y0 = Y0;
	// distance between grid points
	dx = DX; dy = DY;
	// size of nodes
	sx = SX; sy = SY;

	setTitle("Binary Search Tree Demo");
	setSize(WIDTH,HEIGHT);
	show();
	// to exit display
	this.addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent e) {
		    System.exit(0);
		}
	    });
    }

    /**
       Make display invisible.
     */
    public void close() {
	setVisible(false);
    }

    /**
       Quit the application
     */
    public void quit() {
	System.exit(0);
    }

    /**
       Paint called implicitly to update graphics.
    */
    public void paint(Graphics g) {
	Object[][] map = tree.getMap();
	int i, j;
	//	tree.computeNodeHeights();
	g.setColor(Color.white);
	g.fillRect(0,0,this.getWidth(),this.getHeight()); // erase frame

	tree.updatePositions(); // fix up x,y coords in the tree
	drawTree(tree.root,null,g); // draw it!
    }

    /**
       Recursively draw tree rooted at r with parent p.
       g must be the current graphics context.
    */
    private void drawTree(BTNode r,BTNode p, Graphics g) {

	if (r == null || r.element == null) return;

	// set coordinate of node r (including offset)
	int x = x0 + r.x * dx;
	int y = y0 + r.y * dy;
	
	// draw node as circle
	g.setColor(nodeColor);
	g.fillOval(x,y,sx,sy);	    

	// put node key inside center
	g.setColor(textColor);
	g.drawString(((r.element)).toString()+":" + r.height,x+sx/5,y+2*sy/3);

	// if parent exists, draw line to parent.  We actually draw line
	// from top center of this node to bottom center of parent
	// node.
	if (p != null) {
	    int px = x0 + (p.x) * dx + sx/2;
	    int py = y0 + (p.y) * dy + sy;
	    g.setColor(lineColor);
	    g.drawLine(x+sx/2,y,px,py);
	}
	// draw subtrees
	drawTree(r.left,r,g);
	drawTree(r.right,r,g);

    }
}
