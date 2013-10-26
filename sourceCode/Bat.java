import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.image.*;
import java.lang.*;
   /**
	* This is the Bat class that creates the parameters required for a rectangular bat.
	* The Bat class contains attributes specific to any rectangular object such as:
	* width and height. Also, it contains and creates parameters to place the bat inside 
	* the JPanel in ArkUI such as: set/getTop and set/getLeft, indicating x and y coordinates.
	* <p> 
	* To access these attributes get and set methods are 
	* located within this class.   
	* <p>
	* This class implements the MouseMotionListener and MouseListener becuase to be able to move the bat
	* the mouse is required to do this. Therefore, there are specific methods relating to this interface located within
	* the class.  		
	*/
public class Bat implements MouseMotionListener,MouseListener{

	 //Some attributes of Bat
    private int top=0;
    private static int left;
    private int height=0;
    private int width=0;
    ArkUI thisUI;	
	public Bat(){
		
	}//null
	/**
	 * This is the Bat constructor that takes a parameter of a user interface class
	 * thisUI is used within this class to start the ball thread on mouse click. 
	 *
	 *@param ArkUI u 
	 *
	 */
	public Bat(ArkUI u){
		thisUI = u;
	}//con
	/**
	 * This method is required by the interface MouseMotionListener and MouseListener.
	 * Its used to detect a mouse event e and it uses the global variable left to be used inconjunction with the bat.
	 * To test if the detection of mouse movement works i have
	 * made a unit test for this so as it will print all the x coordinates of the mouse within the JPanel.
	 *
	 *@param MouseEvent e is a mouse event 
	 */
	
	public void mouseMoved(MouseEvent e){
		left=e.getX();
		//Testing the mouse of position and when ball hits bat
		System.out.println("x: "+e.getX()+ " "+left);
		
		
	}//method
	
	/**
	 * This method is required by the interface MouseMotionListener and MouseListener.
	 * Its used to detect a mouse event e and is inconjunction with the bat to check if the mouse is dragged.
	 *
	 *@param MouseEvent e is a mouse event 
	 */
	public void mouseDragged(MouseEvent e){
		
		
	}//method
	/**
	 * This method is required by the interface MouseMotionListener and MouseListener.
	 * Its used to detect a mouse event e and is inconjunction with the bat.
	 *
	 *@param MouseEvent e is a mouse event 
	 */
	public void mouseExited(MouseEvent e){
		
	}//method
	/**
	 * This method is required by the interface MouseMotionListener and MouseListener.
	 * Its used to detect a mouse event e and is inconjunction with the bat.
	 *
	 *@param MouseEvent e is a mouse event 
	 */
	public void mouseEntered(MouseEvent e){
		
	}//method
	/**
	 * This method is required by the interface MouseMotionListener and MouseListener.
	 * Its used to detect a mouse event e and is inconjunction with the bat.
	 *
	 *@param MouseEvent e is a mouse event 
	 */
	public void mouseReleased(MouseEvent e){
		
	}//method
	/**
	 * This method is required by the interface MouseMotionListener and MouseListener.
	 * Its used to detect a mouse event e and is inconjunction with the bat.
	 *
	 *@param MouseEvent e is a mouse event 
	 */
	public void mousePressed(MouseEvent e){
		
	}//method
	/**
	 * This method is required by the interface MouseMotionListener and MouseListener.
	 * Its used to detect a mouse event e and is inconjunction with the bat. 
	 * It contains a unit test to check if mouse clicks were detected in game.
	 *
	 *@param MouseEvent e is a mouse event 
	 */
	public void mouseClicked(MouseEvent e){
		thisUI.ballMotion.start();
	
		System.out.println("Mouse clicked");
	}//method

   /**
	* To access a class attribute, i have created Get and Set methods for the
	* particular attribute. 
	* <p>
	* The getTop method is used to access the attribute top.
	*
	* @return Returns the top attribute when the method is called upon.   		
	*/
    public int getTop()
    {
        return top;
    }//method
   /**
	* To access a class attribute, i have created Get and Set methods for the
	* particular attribute. 
	* <p>
	* The getLeft method is used to access the attribute left.
	*
	* @return Returns the left attribute when the method is called upon.   		
	*/
    public int getLeft()
    {
        return left;
    }//method
   /**
	* To access a class attribute, i have created Get and Set methods for the
	* particular attribute. 
	* <p>
	* The getHeight method is used to access the attribute height.
	*
	* @return Returns the height attribute when the method is called upon.   		
	*/
    public int getHeight()
    {
        return height;
    }//method
   /**
	* To access a class attribute, i have created Get and Set methods for the
	* particular attribute. 
	* <p>
	* The getWidth method is used to access the attribute width.
	*
	* @return Returns the width attribute when the method is called upon.   		
	*/
    public int getWidth()
    {
        return width;
    }//method 
   /**
	* To access a class attribute, i have created Get and Set methods for the
	* particular attribute. 
	* <p>
	* The setTop method is used to set the attribute top to t.
	*
	* @param  int t is passed to this method and t is set to top.   		
	*/
    public void setTop(int t)
    {
        top=t;
    }//method
   /**
	* To access a class attribute, i have created Get and Set methods for the
	* particular attribute. 
	* <p>
	* The setLeft method is used to set the attribute left to l.
	*
	* @param  int l is passed to this method and l is set to left.   		
	*/
    public void setLeft(int l)
    {
        left=l;
    }//method
   /**
	* To access a class attribute, i have created Get and Set methods for the
	* particular attribute. 
	* <p>
	* The setHeight method is used to set the attribute height to h.
	*
	* @param  int h is passed to this method and h is set to height.   		
	*/
    public void setHeight(int h)
    {
        height=h;
    }//method
   /**
	* To access a class attribute, i have created Get and Set methods for the
	* particular attribute. 
	* <p>
	* The setWidth method is used to set the attribute width to w.
	*
	* @param  int is passed to this method and w is set to width.   		
	*/
    public void setWidth(int w)
    {
        width=w;
    }//method
	
}//end of class
