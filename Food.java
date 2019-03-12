/**
 * This class controls and functions for the food object. 
 */

import java.util.LinkedList;

public class Food extends SnakePosition {

    public int row;
    public int col;
    // row and column(represent the location of the food)

    public static final int Row = Configure.ROW;
    public static final int Column = Configure.COL;
    // get the game data from the configuration.java

    Food() {
        randomPos();
        // randomly set the location of the food
    }



   /**
     * Method will be used to check if the food block will be on the same position as the snake's body when spawned.
     * @param snakeBody (Type - SnakePosition class)
     * @return boolean. True if the food is touching the snake's body, False otherwise.
     */
    private boolean checkSame(LinkedList<SnakePosition> snakeBody) {
        for (SnakePosition sp : snakeBody)
            if (sp.row == this.row && sp.col == this.col)
                return true;
        // return true if it does
        return false;
    }
    
    

        /**
     *receive the Linkedlist<SnakeBody>, make sure that the food will not overlap on snakebody
     *(we find this bug on demo1)
     *
     *@param snakeBody
     *       this is the Linkedlist<SnakeBody>
     *@return Returning the instantiated object itself
     */
    // get the data of snake body to confirm food does not overlap the snake body
    public Food getSnake(LinkedList<SnakePosition> snakeBody) {
        while (checkSame(snakeBody))
            randomPos();
        //if it does, relocate the food randomly
        return this;
        // return the object itself
    }
    

    /**
     * Method randomly sets the location of the food spawning position. 
     */
    // randomly set the location(row and column)
    private void randomPos() {
        this.row = (int) (Math.random() * Row);
        this.col = (int) (Math.random() * Column);
    }
}
