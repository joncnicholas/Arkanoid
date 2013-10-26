
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.image.*;
import java.lang.*;
   /**
	* This is my User interface class that runs the whole game and creates certain aspects of the game.
	* <p>
	* I chose to make my game in a JPanel. Therefore, the user interface class extends the JPanel 
	* interface. Also, this class has to deal with threads. Therefore, it has to implement the interface runnable. 
	* A new thread is created to manage the movement of the ball and therefore, smooths out gameplay.
	* <p>
	* I also make instances of the interface Frame to set bounds for the games objects to be kept within. Also, 
	* this class contains a paint method in which i draw my bricks; set background colour and draw my bat(which 
	* gets the required parameters from my Bat class). I create my bricks within this method and my start method controls the 
	* flow of the game which is called from my main.
	* <p>
	* Textfields are intialized to create text boxes for my lives and score board. Along side this, i create instances of my
	* ball and bat class; Graphics classes and the buffered image class.   
	*/
public class ArkUI extends JPanel implements Runnable{

	//variable declarations
	ArrayList<Rectangle> zBrick;
    Ball xBall;
    Bat xBat = new Bat();
//	Rectangle bricks = new Rectangle(25,15,25,15); 
	TextField txtField = new TextField(6);
	TextField livesField = new TextField(6); 
    int numBlocks;          // number of blocks horizontally
    int numRows;            // number of rows - 1 least difficult,
    int m;
    public Graphics graph;
    BufferedImage image;
    BufferedImage pict;
    int lives = 5;
    boolean winner = false;
    boolean dead = false;
    
    	
		int iFramex;
    	int iFramey;
    	int iFrameh;
    	int iFramew;
  	
  	Thread ballMotion = null;
  	/**
	* This is the ArkUI's Null Constructor
	* Contains the frame intiallisations.    
	*/
	public ArkUI(){
		iFramex=0;
    	iFramey=0;
    	iFrameh=500;
    	iFramew=850;
	}//Null constructor

	/**
	* This is my main method in which i declare an instance of teh Frame class in order
	* to set boundaries within my game known as: iFramex, iFramey, iFrameh, and iFramew.
	* Also, it contains a window listener so as if i decide to close the game via the 'x' button, it will
	* close once pressed. 
	* <p>
	* My main creates an instance of the user Interface class and
	* sets the boundaries for the game frame as well as calls my start method in which
	* the game is controlled.  
	*/
	public static void main(String[] args) {
    Frame f = new Frame();
    
 		f.addWindowListener(new java.awt.event.WindowAdapter() {
       	public void windowClosing(java.awt.event.WindowEvent e) {
       	System.exit(0);
       	};
     	});

  		ArkUI rk = new ArkUI();
  		f.add(rk);
  		f.pack();
  		rk.start();
  		f.setBounds(rk.iFramex,rk.iFramey,rk.iFramew,rk.iFrameh);
  		f.setVisible(true);
    
    	}//method	
   /**
	* This method is one of the most important as it controls what goes on within the game. I first start by creating
	* 2 new panels for my text boxes which are lives and scoreboard. 
	* I then initialize my number of rows of blocks and the amount of blocks within each row. 
	* Then i create an instance of my thread and then call my startBrick, StartBall and StartBat methods
	* which takes care of creating and drawing my bat, ball and bricks into the game. 
	* Once completed my thread begins for the ball to run in, 
	* then i create an instance of my bat class to add my MouseMotionListener so as my mouse will move the bat. 
	* Once complete i make an instance of a buffered image class and then call a few more methods and set visible to true.
	*/
	public void start(){
		
		Panel p = new Panel();
        	p.setLayout(new FlowLayout());
        	txtField= new TextField(6);
        //Initially the text field is not editable
       		txtField.setEditable(false);
        	p.add(new Label("SCORE"));
        	p.add(txtField);
     		add("North", p);
  
   			Panel q = new Panel();
   				p.setLayout(new FlowLayout());
        		livesField= new TextField(6);
        //Initially the text field is not editable
        		livesField.setEditable(false);
        		p.add(new Label("LIVES"));
        		p.add(livesField);
        		String displaystring = Integer.toString(lives);
        		livesField.setText(displaystring);
     			add("North", p);
       			numBlocks = 10;
        		numRows = 4;
        //create the instance of the thread which will be taking care of the balls motion
        		ballMotion = new Thread(this);
        			startBrick();
        			startBat();
        			startBall();
        //start the execution of this thread now
        			
        			Bat mouseBat = new Bat(this);
      					this.addMouseListener(mouseBat);
       					this.addMouseMotionListener(mouseBat);
        				image = new BufferedImage(iFramew,iFrameh,BufferedImage.TYPE_INT_RGB);
        				//pict = new BufferedImage(iFramew,iFrameh,BufferedImage.TYPE_INT_RGB);
        				GameOver();
						this.setVisible(true);
		}//method
	/**
	 *This is my collision checking method. This method checks whether there are collisions between the ball and the
	 * bricks and whether there are collisions between the ball and the bat. Also, it controls the removal of
	 * bricks when the ball hits the brick and when it does the scoreboard updates. This method
	 * uses the intersects() class method to detect collisions between objects within the game
	 *
	 */
	public void collisionCheck(){
		
        Rectangle ballColl = new Rectangle( xBall.x, xBall.y, xBall.size, xBall.size);
        Rectangle xBatCrash = new Rectangle( xBat.getLeft(), xBat.getTop(), xBat.getWidth(), xBat.getHeight());
        for( int i=0; i<zBrick.size( ); i++ ){
            Rectangle r = (Rectangle)zBrick.get(i);
            if (r.intersects(ballColl)){
                zBrick.remove(r);
                m = m + 5;
                txtField.setText(""+m);
                
                getGraphics( ).clearRect(r.x, r.y, r.width, r.height);
               
                xBall.dirY = -1 * xBall.dirY;
                
            }//if statement
            
        }//for statement
        
        // check for bat collision
       
        if (ballColl.intersects(xBatCrash)){
           		xBall.dirY = -1;
  
        }//if statement
		
    }//method
    /**
     * This is the run method which is an essential if using threads within the program.
     * This method calls the ball movement method which is located within the ball class and 
     * sets the frame boundaries as parameters within the method call.
     * <p>
     * The method also calls the collision check method to detect collisions whilst the ballMotion
     * thread is running. Once it has done this is repaints and includes a thread.sleep() class method so determine how 
     * fast the ball should move. This is placed within a try and catch block.
     */
	public void run(){
		
		while(true){
				        
        	xBall.move(iFramew,iFrameh);
        
        	GameOver();
        	if(dead == true){
        		repaint();
        		break;
        	}else{
        	
        	//System.out.println("Movbedobne");
            collisionCheck();
            repaint();
            try {

                Thread.sleep(8);
              
            }//try block
            
            catch(Exception ex){ 
            
            }//catch block
            }//else
   	
            
        }//end of while
    
    }//method	
    /**
     * This is the user interface paint method which takes a parameter as a graphics object. 
     * This method fills the background colour as white and paints the ball by calling the ball paint method.
     * Also, it paints the bricks using the arraylist zBrick and setting the colour as red. It aslo, draws the bat filling the colour
     * as black and getting the coordinates from the bat class.
     * <p>
     * If dead is true the lives declines and to avoid using an unsafe method to stop the 
     * thread, i paint everything white and draw a string to say game over.
     *
     *@ param Graphics g is the graphics object that draw the image within the paint method
     */
    public void paint(Graphics g){
    	if(winner == true){
    		g.setColor(Color.WHITE); 
        	g.fillRect(0,0,iFramew,iFrameh);
        	g.setColor(Color.BLACK);
        	g.drawString("Congratulations! You have won!",415,200);
    	}else{
    	
    	if(dead == true){

    		g.setColor(Color.WHITE); 
        	g.fillRect(0,0,iFramew,iFrameh);
        	g.setColor(Color.BLACK);
        	g.drawString("Game Over, Try Again!",415,200);
     
        	
    	}else{ 
    	
    	
    	Graphics b = image.getGraphics();
    	
    	b.setColor(Color.WHITE); 
        b.fillRect(0,0,iFramew,iFrameh);
        
		b.setColor(Color.BLACK);
        b.drawString("Welcome to Not Arkanoid!",415,200);
        
       	xBall.paint(b);
    	
  		b.setColor(Color.RED);
  		for (int i=0; i<zBrick.size(); i++ ){
  			
            Rectangle rect = (Rectangle)zBrick.get(i);
            b.fillRect(rect.x, rect.y, rect.width, rect.height);
            
        }//for statement
        b.setColor( Color.black );
       	b.fillRect(xBat.getLeft(),xBat.getTop(),xBat.getWidth(),xBat.getHeight());
       	g.drawImage(image,0,0,this);
        	}//else
    	}//else
  	}//method
  	/**
  	 * This is the GameOver method that detects whether or not the ball is out of play and
  	 * whether you have lost a life or whether the bricks have all gone.
  	 * <p>
  	 * It makes an if statment and gets the balls Position and if it is lower
  	 * than 476 (beneath the bat), dead which is a global variable becomes true which is 
  	 * then called in paint and the run method for the thread.
  	 * It contains a unit test to test were the ball is so as it terminates the game once the ball has dropped
  	 * lower than 476 (below the bat). Also, it has a second test to detect whether or not
  	 * the ball has died or gone out of play at the end of the if statement.
  	 * <p>
  	 * Winner is a boolean variable declared globally which is then used in the below if statement. 
  	 * It checks whether the array list is empty and if so then all the bricks have been hit. Therefore,
  	 * the player has won the game.
  	 *
  	 */
  	public void GameOver(){
  		if(zBrick.isEmpty() == true){
					winner =true;
				}else{
				
  				System.out.println("*"+ xBall.getHeight());
  					if(xBall.getHeight() > 476){
  						dead =true;
  						lives--;
  						String displaystring = Integer.toString(lives);
  						livesField.setText(displaystring);
  						System.out.println("Ball died");
  					}
				
  				}//else
  			
  	}//method
  	
  	
  	/**
  	 * This is the startBrick method which controls were the bricks are painted and how many are painted and
  	 * in what order. The method contains calculations to detrmine block size and states block height. 
  	 * It contains 2 for loops to detemine the order and placement of the bricks and this is then added to the zrick array list.
  	 * This method is called from my start method at the top of this class.
  	 */
	public void startBrick(){
	zBrick = new ArrayList<Rectangle>(); 
	int blockSize = iFramew / numBlocks;
    int blockHeight = 15;	
        for(int rows=0; rows<iFramew; rows += blockSize){
            for(int cols=0; cols<numRows*blockHeight; cols += blockHeight){
                Rectangle r = new Rectangle(rows,80+cols, blockSize-2, blockHeight-2);
                zBrick.add(r);
                
            }//for  
            
      	}//for statement      
                 
	}//method
	/**
	 * This is the startBat method is called from the start method and defines the x, y, height and width of the bat.
	 * This method contains a unit test to test whether or not the bat is painted at the correct coordinates aswell as 
	 * to check if the program correctly calls this method so as the bat object appears within the game.
	 *
	 */
	public void startBat(){
		System.out.println("testing bat paint");
		xBat.setTop(400);
        xBat.setLeft(200);
        xBat.setHeight(20);
        xBat.setWidth(75);
       	
	}//method
	/**
	 * This is the startBall method. This calls the ball method and repaints it so as it appears in the correct place within 
	 * the program. THis method is called from the start method at the top of the user interface class.
	 */
	public void startBall(){
		repaint();
		xBall = new Ball();
		
	}//method

    	  			
}//end of class	

