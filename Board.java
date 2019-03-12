import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Class responsible for the animation of the game. 
 */
public class Board {

 /**
  * 
*@param args
*/


	public static void main(String[] args) {

		//Open a thread, all the swing components must be configured by the event Thread
		//the thread transfers the mouse click and button control to the user interface component
		EventQueue.invokeLater(new Runnable() {
			// inner class that is an instance of the Runnable interface that implements the run method.

			public void run() {

				JFrame frame = new BoardFrame();
				// create the Frame class object defined to make it easy to call the constructor and method.


				frame.setTitle("Snake");
				// set the title.



				frame.setVisible(true);
				//make the window visible, it will be easy when we want to add more things in it.

			}
		});
	}

}

class BoardFrame extends JFrame {

	private Snake S_snake;
	// create a snake object in the drawing area.


	public static final int INTERVAL = Configure.INTERVAL;
	// it decides the movement speed of the snake
	// we can get the intercal from the Configure.java



	public BoardFrame() {

		S_snake = new Snake();

		S_snake.setFood(new Food().getSnake(S_snake.getSnakeBody()));
		//create a food object,call the getSnake method to make the food that will
		//not be appear on snake's body.
		//getSnake method returns type Food, which we can call it directly.


		final KeyboardFocusManager manager = KeyboardFocusManager
				.getCurrentKeyboardFocusManager();
		//create a Keyboard listener
		//Because we want to open the thread below, the thread can only get the final
		//modified local variable, so this variable is immutable


		new Thread(new Runnable() {
			//open the thread to redraw the snake
			//And there is another reason for using the Multithreading
			//Because it will be easy when we create a double player mode in Demo 3


			public void run() {

				while (true) {
					BoardComponent bc = new BoardComponent();
					bc.setSnake(S_snake);
					add(bc);
					//Create JComponent instance and load the snake object which created above.


					MyKeyEventPostProcessor mkepp = new MyKeyEventPostProcessor();
					mkepp.setS_snake(S_snake);
					manager.addKeyEventPostProcessor(mkepp);
					//create Keyboard Listener instance, and load the snake object.



					try {
						Thread.sleep(INTERVAL);
						//Each movement need a short pause in interval,
						//so use sleeop moethod to make a short pause.

					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					S_snake.snakeMove();
					// call movment method

					pack();
					// draw the default size window.

				}
			}
		}).start();

	}

}

class MyKeyEventPostProcessor implements KeyEventPostProcessor {

	private Snake S_snake;

	public boolean postProcessKeyEvent(KeyEvent e) {

		Direction S_Direction = null;
		//Create a Direction enumeration class

		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			S_Direction = Direction.UP;
			break;
		case KeyEvent.VK_DOWN:
			S_Direction = Direction.DOWN;
			break;
		case KeyEvent.VK_LEFT:
			S_Direction = Direction.LEFT;
			break;
		case KeyEvent.VK_RIGHT:
			S_Direction = Direction.RIGHT;
			break;
		}
		// according to the different direction key, save the value in dir.


		if (S_Direction != null)
			S_snake.setMove_Direction(S_Direction);
		//if the value that receive above is one of the direction keys,
		//save dir to moveDir in Snake Class

		return true;
	}

	public void setS_snake(Snake S_snake) {
		this.S_snake = S_snake;
	}

}

class BoardComponent extends JComponent {

	public static final int Width = Configure.WIDTH;
	public static final int Height = Configure.HEIGHT;
	public static final int TileWidth = Configure.TILE_WIDTH;
	public static final int TileHeight = Configure.TILE_HEIGHT;
	public static final int Row = Configure.ROW;
	public static final int Column = Configure.COL;
	private static final int XOffset = (Width - Column * TileWidth) / 2;
	private static final int YOffset = (Height - Row * TileHeight) / 2;
	// load Game date from Configure.java

	private Snake S_snake;

	public void setSnake(Snake S_snake) {
		this.S_snake = S_snake;
	}

	/**
*
*
*@param g
*/


	public void paintComponent(Graphics g) {
		drawDecoration(g);
		drawFill(g);
	}

	/**
*drawing the Snake body and food
*
*@param g
*/


	public void drawFill(Graphics g) {

		g.setColor(Color.GREEN);

		for (SnakePosition sp : S_snake.getSnakeBody())
			g.fillRect(sp.col * TileWidth + XOffset, sp.row * TileHeight
					+ YOffset, TileWidth, TileHeight);
		//make the color on Snake's body
		Food fd = S_snake.getFood();


		g.setColor(Color.BLUE);

		//make color to the food.

		g.fillRect(fd.col * TileWidth + XOffset, fd.row * TileHeight + YOffset,
				TileWidth, TileHeight);

	}

	/**
*drawing the red wall for the Game
*
*@param g
*/


	public void drawDecoration(Graphics g) {
		g.setColor(Color.RED);
		g.drawRect(XOffset, YOffset, Column * TileWidth, Row * TileHeight);
	}

	/**
*We override this method to indicate the size of the components of this class.
*
*@return return the size of the components that it should be.
*/


	public Dimension getPreferredSize() {
		return new Dimension(Width, Height);
		// return a dimension object to show the size of the component.

	}
}
