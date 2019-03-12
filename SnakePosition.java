/**
 * Class contains the information of the snake's position/location. 
 */
public class SnakePosition {

    public int col;
    public int row;

     /**
     * Constructor to set up the snake's position.
     * @param row (type int - The row position)
     * @param col (type int - The column position)
     */
    SnakePosition(int row, int col) {
        this.col = col;
        this.row = row;
    }

    /**
     * Default constructor that sets the row and column of the snake's position to 0.
     */
    SnakePosition() {
        col = 0;
        row = 0;
    }

}
