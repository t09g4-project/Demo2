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
  *receive the Linkedlist<SnakeBody>, make sure that the food will not appear on snakebody
  *(we find this bug on demo1)
  *
  *@param snakeBody
  *       this is the Linkedlist<SnakeBody>
  *@return Returning the instantiated object itself
  */


    private boolean checkSame(LinkedList<SnakePosition> snakeBody) {
        for (SnakePosition sp : snakeBody)
            if (sp.row == this.row && sp.col == this.col)
                return true;
        // return true if it does
        return false;
    }

    // get the data of snake body to confirm food does not overlap the snake body
    public Food getSnake(LinkedList<SnakePosition> snakeBody) {
        while (checkSame(snakeBody))
            randomPos();
        //if it does, relocate the food randomly
        return this;
        // return the object itself
    }
    
    /**
  *check that is there a block in lnkedlist<SnakeBody> have the same position as Food
  *
  *@param snakeBody
  *
  *@return if they have same position,return true
  */


    // randomly set the location(row and column)
    private void randomPos() {
        this.row = (int) (Math.random() * Row);
        this.col = (int) (Math.random() * Column);
    }
}
