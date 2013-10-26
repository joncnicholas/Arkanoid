import javax.swing.*;
import java.util.*;
import java.awt.image.*;
import java.awt.*;
import java.awt.event.*;
   /**
	* This is the Ball class that creates the parameters required to make a ball object such as: 
	* the speed, direction, calculations for the coordinates of the ball
	* and is also used to paint the ball.
	* <p> 
	* This class uses graphics to set the colour, shape, and size of the ball.  
	* <p>
	* This class is called upon within my User Interface class (ArkUI) 
	* in the paint section and other sections such as collision checking.
	*/
public class Ball{
	int h,w;
    int x, y, size, speed;
    int dirX, dirY;
   
   /**
	* This is the Null constructor for the ball, that initializes the global variables of: 
	* the balls coordinates (x and y); the size of the ball; speed and direction of the ball.
	* These variables are specific to the ball class and are only used for purposes of the ball. They
	* are instance variables as they are required also by other classes.	
	*/
    public Ball(){
    	
    	x =590;
        y = 370;
        size = 15;
        speed = 3;
        dirX = 1;
        dirY = 1;
       
       
        
    }//Null constructor
   /**
	* This is the ball constructor that initializes the global variables of: 
	* the balls coordinates (x and y); the size of the ball; speed and direction of the ball.
	* These variables are specific to the ball class and are only used for purposes of the ball. They
	* are instance variables as they are required also by other classes.
	*
	* @param int _x is the x coordinate.
	* @param int _y is the y coordinate.
	* @param _size is the size of the ball.
	* @param _speed is the speed of the ball.	
	*/
	
    public Ball(int _x, int _y, int _size,  int _speed){
        x = _x;
        y = _y;
        size = _size;
        speed = _speed;
        dirX = 1;
        dirY = 1;
        
        
    }//constructor
   /**
	* This is the balls paint method which sets the colour of the ball and makes the ball
	* an oval with particular size and its coordinates.  
	*
	*@param Graphics g
	*
	*/
    public void paint(Graphics g){
        g.setColor(Color.BLUE);
        g.fillOval(x, y, size, size);
        
    }//method
    /**
	* This is the balls move method. Within this method the: direction, speed and (x,y) coordinates are
	* taken into consideration and used to calculate the balls trajectory when released.
	* 
	* @param int Wdt  
	* @param int Hgt    
	*/
    public void move(int Wdt, int Hgt){
		
		 
		
        x = x + speed * dirX; 
        y = y + speed * dirY; 
        
        
        if ( x < 0 )
        
            dirX = 1;
        else if ( x > Wdt )
            dirX = -1;
        if ( y < 0 )
            dirY = 1;
        else if ( y > Hgt )

           x = x;
           y = y;
        

    }//method
    
   /*
    * This is a get method to return the y coordinate
    *
    *@return returns a y coordinate used in another class
    *
    */
   public int getHeight(){
   	return y;
   }//method
   /*
    * This is a get method to return the x coordinate
    *
    *@return returns a x coordinate used in another class
    *
    */
   public int getWidth(){
   	return x;
   }//method
   
}//end of class
