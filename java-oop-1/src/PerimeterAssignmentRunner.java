import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int totalPoints = 0;
        for (Point currPt : s.getPoints()) {
            totalPoints += 1;
        }
        return 0;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        int numPoints = getNumPoints(s);
        if (numPoints == 0) {
            return 0.0;
        }
        return getPerimeter(s) / numPoints;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double totalPerim = 0.0;
        double maxDist = 0.0;

        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);

            if (currDist > maxDist){
                maxDist = currDist;
            }

        }
        return maxDist;
    }

    public double getLargestX(Shape s) {
        // Put code here
        double maxX = -Double.MAX_VALUE;
        for (Point currPt : s.getPoints()) {
            if (currPt.getX() > maxX) {
                maxX = currPt.getX();
            }
        }
        return maxX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        double max_perim = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double curr_perim = getPerimeter(s);
            if (curr_perim > max_perim){
                max_perim = curr_perim;
            }
        }
        return max_perim;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        double max_perim = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double curr_perim = getPerimeter(s);
            if (curr_perim > max_perim){
                max_perim = curr_perim;
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("Perimeter = " + length);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double max_perim = getLargestPerimeterMultipleFiles();
        System.out.println("Largest perimeter = " + max_perim);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String file = getFileWithLargestPerimeter();
        System.out.println("File with largest perimeter = " + file);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = " + peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        pr.testFileWithLargestPerimeter();
    }
}