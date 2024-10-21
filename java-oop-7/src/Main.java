import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Main {

    public void totalBirths(FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int girlsNames = 0;
        int boysNames = 0;
        for(CSVRecord record : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(record.get(2));
            String name = record.get(0);
            totalBirths += numBorn;
            if(record.get(1).equalsIgnoreCase("M")) {
                totalBoys += numBorn;
                boysNames = countNames(name, boysNames);
            } else {
                totalGirls += numBorn;
                girlsNames = countNames(name, girlsNames);
            }
        }
        System.out.println("Total Births = " + totalBirths);
        System.out.println("Total Girls = " + totalGirls);
        System.out.println("Total Boys = " + totalBoys);
        System.out.println("Number of Girls Names = " + girlsNames);
        System.out.println("Number of Boys Names = " + boysNames);
        System.out.println("Total Names = " + (boysNames + girlsNames));
    }

    public int countNames(String name, int count) {
        String temp = null;
        String temp1 = null;
        if(temp == null) {
            temp = name;
        }
        if(temp1 != temp) {
            temp1 = temp;
            count++;
        }
        return count;
    }

    public int getRank(int year, String name, String gender) {
        String filename = "/Users/mykolasmotiejunas/Documents/java-oop-7/us_babynames/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(filename);
        int rank = 1;
        int boysNames = 0;
        for(CSVRecord record : fr.getCSVParser(false)) {
            String nameRow = record.get(0);
            String genderRow = record.get(1);
            if(genderRow.equalsIgnoreCase("M")) {
                boysNames = countNames(name, boysNames);
                rank = boysNames;
            }
            if(nameRow.equalsIgnoreCase(name) && genderRow.equalsIgnoreCase(gender)) {
                return rank;
            }
            rank++;
        }
        return -1;
    }

    public String getName(int year, int rank, String gender) {
        String filename = "/Users/mykolasmotiejunas/Documents/java-oop-7/us_babynames/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser(false);
        String name = null;
        int girlsNames = 0;
        int maleRank = rank;
        int lineNumber = 0;
        for(CSVRecord record : parser) {
            String genderRow = record.get(1);
            lineNumber++;
            if(genderRow.equalsIgnoreCase("F")) {
                girlsNames = countNames(record.get(0), girlsNames);
            }

            if(genderRow.equalsIgnoreCase("M") && gender.equalsIgnoreCase("M")) {
                rank = maleRank + girlsNames;
            }
            if(genderRow.equals(gender) && rank == lineNumber) {
                return name = record.get(0);
            }
        }
        return "NO NAME";
    }

    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        if(gender.equalsIgnoreCase("M")) {
            System.out.println(name + " born in " + year + " would be " + newName +
                    " if he was born in " + newYear);
        } else {
            System.out.println(name + " born in " + year + " would be " + newName +
                    " if she was born in " + newYear);
        }
    }

    public int yearOfHighestRank(String name, String gender) {
        int year = -1;
        int tempYear = 0;
        int previousRank = 0;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            String filename = f.getName();
            String getYear = filename.substring(filename.indexOf("yob") + 3, filename.indexOf(".csv"));
            tempYear = Integer.parseInt(getYear);
            int currentRank = getRank(tempYear, name, gender);
            if((currentRank != -1) && (previousRank == 0 || currentRank < previousRank)) {
                previousRank = currentRank;
                year = tempYear;
            }
        }
        return year;
    }

    public double getAverageRank(String name, String gender) {
        double average = -1.0;
        int sum = 0;
        int count = 0;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            String filename = f.getName();
            String getYear = filename.substring(filename.indexOf("yob") + 3, filename.indexOf(".csv"));
            int year = Integer.parseInt(getYear);
            int rank = getRank(year, name, gender);
            sum += rank;
            count++;
            average = (double)sum / count;
        }
        return average;
    }

    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        String filename = "/Users/mykolasmotiejunas/Documents/java-oop-7/us_babynames/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser(false);
        int totalBirths = 0;
        int count = 0;
        int boysCount = 0;
        int rank = getRank(year, name, gender);
        for(CSVRecord record : parser) {
            int numBorn = Integer.parseInt(record.get(2));
            if(record.get(1).equals("M") && gender.equals("M")) {
                boysCount++;
                if(boysCount < rank) {
                    totalBirths += numBorn;
                }
            } else if(record.get(1).equals("F") && gender.equals("F")){
                count++;
                if (count < rank) {
                    totalBirths += numBorn;
                }
            }
        }
        return totalBirths;
    }

    public void testTotalBirths() {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }

    public void testGetRank() {
        System.out.println("Mason 2012: " + getRank(2012, "Mason", "M"));
    }

    public void testGetName() {
        System.out.println(getName(2010, 10, "M")); //Anthony
        System.out.println(getName(2010, 1, "M")); //Jacob
    }

    public void testWhatIsNameInYear() {
        whatIsNameInYear("Noah", 2014, 2012, "M"); //Jacob
        whatIsNameInYear("Owen", 1974, 2014, "M"); // Leonel
    }

    public void testYearOfHighestRank() {
        System.out.println(yearOfHighestRank("Mason", "M"));
        System.out.println(yearOfHighestRank("John", "M"));
    }

    public void testGetAverageRank() {
        System.out.println(getAverageRank("Mason", "M"));
        System.out.println(getAverageRank("Jacob", "M"));
    }

    public void testGetTotalBirthsRankedHigher() {
        System.out.println(getTotalBirthsRankedHigher(2013, "Steven", "M"));
        System.out.println(getTotalBirthsRankedHigher(2012, "Ava", "F"));
    }

    public static void main(String[] args) {
        Main tester = new Main();
//        tester.testTotalBirths();
//        tester.testGetRank();
//        tester.testGetName();
//        tester.testWhatIsNameInYear();
//        tester.testYearOfHighestRank();
//        tester.testGetAverageRank();
        tester.testGetTotalBirthsRankedHigher();
    }
}