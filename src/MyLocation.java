/**
 * The MyLocation class is class creates a new data type called MyLocation.
 * A MyLocation stores a row and a column. It contains the methods
 * equals and compareTo.
 * 
 * @author Aashish Jain
 *          With Assistance from Richard Page
 * @version December 5, 2014          
 */
public class MyLocation implements Comparable
{
    private int row; //An integer that stores the row
    private int col; //An integer that stores the column
    
    /**
     * Creates a myLocation object which has a row and column.
     * 
     * @param r The value that the row is set to.
     * @param c To value that the column is set to.
     */
    public MyLocation(int r, int c)
    {
        row = r;
        col = c;
    }
    
    /**
     * Gets the row instance variable.
     * 
     * @return The row of this object
     */
    public int row()
    {
        return row;
    }
    
    /**
     * Gets the col instance variable.
     * 
     * @return The col of this object. 
     */
    public int col()
    {
        return col;
    }
    
    /**
     * Checks if another object is equal to this object. Does so
     * by comparing the row of the other object and the col of the other object.
     * If they are the same as this object, it returns true. 
     * 
     * @return True if the instance variables are the same,
     *           false if they are different.
     */
    public boolean equals(Object other)
    {
        if (!(other instanceof MyLocation))
            return false;
        MyLocation loc = (MyLocation)other;
        return row == loc.row() && col == loc.col();
    }
    
    /**
     * Converts this Location to a string. Does so by printing the row
     * and then the column.
     */
    public String toString()
    {
        return "(" + row + ", " + col + ")";
    }
    
    /**
     * Compares two myLocation objects. If the rows are equal,
     * compares the two myLocation objects by comparing their cols.
     * If the rows are not equal, it compares the rows.
     */
    public int compareTo(Object x)
    {
        MyLocation y = (MyLocation) x;
        if (y.row() == this.row()) return this.col() - y.col();
        else return this.row() - y.row();
    }
}