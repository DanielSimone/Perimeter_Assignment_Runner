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
        // Start with zero points
        int numPoints = 0;
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()){
            // Increment numPoints until there are none left
            numPoints++;
        }
        // numPoints is the answer
        return numPoints;
    }

    public double getAverageLength(Shape s) {
        // Start with the number of sides
        int numSides = getNumPoints(s);
        // Start with the perimeter of shape s
        double totalPerim = getPerimeter(s);
        // Divide the perimeter by the number of sides
        double AverageLength = totalPerim / numSides;
        // AverageLength is the answer
        return AverageLength;
    }

    public double getLargestSide(Shape s) {
            // Start with prevPt
            Point prevPt = s.getLastPoint();
            // Start with LargestSide = 0.0
            double LargestSide = 0.0;
            // For each point currPt in the shape,
        for (Point currPt : s.getPoints()){
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // If the distance is larger than the largest side
            if (currDist > LargestSide) {
                // Make the new largest side the currDist, so the for loop iterates until it finds the largest currDist
                LargestSide = currDist;
            }
            // Move the currPt to prevPt to restart the for loop
            prevPt = currPt;
        }
        // LargestSide is the answer
        return LargestSide;
    }

    public double getLargestX(Shape s) {
        // Start with largestX = 0
        double LargestX = 0;
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()){
            // Find the X-value of the point,
            double currPtX = currPt.getX();
            // If the X-value is greater than the previously largest value,
            if (currPtX > LargestX) {
                // Set the largest value to the new X-value
                LargestX = currPtX;
            }
            
        }
        // LargestX is the answer
        return LargestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Start with the DirectoryResource
        DirectoryResource dr = new DirectoryResource();
        // Start with no FileResource
        FileResource fr = null;
        // Start with largestperimetermultiple = 0.0
        double largestPerimeter = 0.0;
        // For each file f in the directory,
        for (File f : dr.selectedFiles()) {
            FileResource newfr = new FileResource(f);
            // Create a new shape from the file,
            Shape s = new Shape(newfr);
            // Get the new shape's perimeter
            double newPerimeter = getPerimeter(s);
            // If the new shape's perimeter is greater than the previously largest perimeter,
            if (newPerimeter > largestPerimeter) {
                // Set the largest perimeter to the new perimeter
                largestPerimeter = newPerimeter;
            }
        }
        // largestPerimeter is the answer
        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        // Start with the DirectoryResource
        DirectoryResource dr = new DirectoryResource();
        // Start with no File
        File largestFile = null;
        // Start with the largestPerimeter = 0.0
        double largestPerimeter = 0.0;
        // For each file fileName selected in directory dr,
        for (File fileName : dr.selectedFiles()) {
            // Create a new object using the file,
            FileResource newFile = new FileResource(fileName);
            // Create a new shape with the data from the file,
            Shape newShape = new Shape(newFile);
            // Acquire the perimeter of the shape,
            double perimeter = getPerimeter(newShape);
            // If the perimeter is greater than the previously largest perimeter,
            if (perimeter > largestPerimeter) {
                // Set the largest perimeter to the new perimeter.
                largestPerimeter = perimeter;
                // And set the largest file to the file from which the new perimeter originated
                largestFile = fileName;
            }
        }
        // largestFile's name is the answer
        return largestFile.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int numPoints = getNumPoints(s);
        double AverageLength = getAverageLength(s);
        double LargestSide = getLargestSide(s);
        double LargestX = getLargestX(s);
        // Print the perimeter with perimeter
        System.out.println("perimeter = " + length);
        // Print the number of points with numPoints
        System.out.println("number of points = " + numPoints);
        // Print the average length with AverageLength
        System.out.println("average length = " + AverageLength);
        // Print the largest side with LargestSide
        System.out.println("largest side = " + LargestSide);
        // Print the largest x-coordinate with LargestX
        System.out.println("largest x-value = " + LargestX);
    }
    
    public void testPerimeterMultipleFiles() {
        // Start with what the largest perimeter is
        double largestPerimeter = getLargestPerimeterMultipleFiles();
        // Find the name of the file which contains the largest perimeter
        String fileName = getFileWithLargestPerimeter();
        // Print the largest perimeter and the file from which it originates
        System.out.println("largest perimeter = " + largestPerimeter + " from the shape " + fileName);
    }

    public void testFileWithLargestPerimeter() {
        // Find the name of the file which contains the largest perimeter
        String fileName = getFileWithLargestPerimeter();
        // Print the file from which it originates
        System.out.println("the largest perimeter comes from the file " + fileName);
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
        System.out.println("perimeter = "+peri);
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
    }
}
