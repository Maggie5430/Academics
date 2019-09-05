package lab3;

/**
 * Lab3 CSSKL 143B, Winter 2018 1/25/18
 *
 * This class contains my work from Lab 3: LineSegment
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class LineSegment {

    private Point2D startPoint;
    private Point2D endPoint;

    /**
     * Initializes a new instance of the LineSegment class.
     */
    public LineSegment() {

    }

    /**
     * Initializes a new instance of the LineSegment class.
     * 
     * @param start
     * @param end
     */
    public LineSegment(Point2D start, Point2D end) {
        this.startPoint = new Point2D(start);
        this.endPoint = new Point2D(end);
    }

    /**
     * Initializes a new instance of the LineSegment class.
     *
     * @param newLineSegment
     */
    public LineSegment(LineSegment newLineSegment) {
        this.startPoint = new Point2D(newLineSegment.startPoint);
        this.endPoint = new Point2D(newLineSegment.endPoint);
    }

    /**
     * Preconditions: None
     * Postconditions: Returns distance between start and end point
     * 
     * @return
     */
    public double distance() {
        //distance formula
        return Math.sqrt(
                (Math.pow((this.endPoint.getX() - this.startPoint.getX()), 2) 
                        + Math.pow((this.endPoint.getY() 
                                - this.startPoint.getY()), 2)));
    }

    /**
     * Preconditions: None
     * Postconditions: Returns starting point
     * 
     * @return
     */
    public Point2D getStartPoint() {
        return new Point2D(startPoint);
    }

    /**
     * Preconditions: None
     * Postconditions: Sets a new starting point
     * @param start
     */
    public void setStartPoint(Point2D start) {
        this.startPoint = start;
    }

    /**
     * Preconditions: None
     * Postconditions: return end point
     * 
     * @return
     */
    public Point2D getEndPoint() {
        return new Point2D(endPoint);
    }

    /**
     * Preconditions: None
     * Postconditions: Sets new end point
     * 
     * @param end
     */
    public void setEndPoint(Point2D end) {
        this.endPoint = end;
    }

    /**
     * Precondition: None 
     * Postcondition: Returns String of line segment
     * 
     * @return String
     */
    @Override
    public String toString() {
        return ("LineSegment[startPoint = Point2D" + this.startPoint 
                + ", endPoint = Point2D " + this.endPoint);
    }

    /**
     * Precondition: None 
     * Postcondition: Returns true if two LineSegments are equal
     * 
     * @return boolean
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof LineSegment)) {
            return false;
        } else if (this.endPoint.equals(((LineSegment) other).endPoint)
                && this.startPoint.equals(((LineSegment) other).startPoint)) {
            return true;
        }
        return false;
    }
}
