import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class Main {

    public CSVRecord coldestHourInFile(CSVParser parser){
        double coldestTemp = Double.MAX_VALUE;
        CSVRecord recordMinTemp = null;

        for (CSVRecord record : parser){
            String temperatureStr = record.get("TemperatureF");
            double temperature = Double.parseDouble(temperatureStr);
            if(temperature < coldestTemp && temperature != -9999){
                coldestTemp = temperature;
                recordMinTemp = record;
            }
        }
        return(recordMinTemp);
    }

    public String fileWithColdestTemperature() {
        CSVRecord coldestSoFar = null;
        String filename = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            if(coldestSoFar == null) {
                coldestSoFar = currentRow;
                filename = f.getName();
            } else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if(currentTemp < coldestTemp) {
                    coldestSoFar = currentRow;
                    filename = f.getName();
                }
            }

        }
        return filename;
    }

    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestHumiditySoFar = null;
        for(CSVRecord currentRow : parser) {
            if(lowestHumiditySoFar == null) {
                lowestHumiditySoFar = currentRow;
            } else {
                String currentHumidityStr = currentRow.get("Humidity");
                String humidityStr = lowestHumiditySoFar.get("Humidity");
                if(!currentHumidityStr.equalsIgnoreCase("N/A") && !humidityStr.equalsIgnoreCase("N/A")) {
                    double currentHumidity = Double.parseDouble(currentHumidityStr);
                    double humidity = Double.parseDouble(humidityStr);
                    if(currentHumidity < humidity) {
                        lowestHumiditySoFar = currentRow;
                    }
                }
            }
        }
        return lowestHumiditySoFar;
    }

    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowestHumiditySoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            if(lowestHumiditySoFar == null) {
                lowestHumiditySoFar = currentRow;
            } else {
                String currentHumidityStr = currentRow.get("Humidity");
                String humidityStr = lowestHumiditySoFar.get("Humidity");
                if(!currentHumidityStr.equalsIgnoreCase("N/A") && !humidityStr.equalsIgnoreCase("N/A")) {
                    double currentHumidity = Double.parseDouble(currentHumidityStr);
                    double humidity = Double.parseDouble(humidityStr);
                    if(currentHumidity < humidity) {
                        lowestHumiditySoFar = currentRow;
                    }
                }
            }
        }
        return lowestHumiditySoFar;
    }

    public double averageTemperatureInFile(CSVParser parser) {
        double sum = 0.0;
        double average = 0.0;
        for(CSVRecord currentRow : parser) {
            double temp = Double.parseDouble(currentRow.get("TemperatureF"));
            sum += temp;
            average = sum / (parser.getCurrentLineNumber() - 1);
        }
        return average;
    }

    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double sum = 0.0;
        double average = 0.0;
        int counter = 0;
        for(CSVRecord currentRow : parser) {
            String humidityStr = currentRow.get("Humidity");
            if(!humidityStr.equalsIgnoreCase("N/A")) {
                double humidity = Double.parseDouble(humidityStr);

                if(humidity >= value) {
                    double temp = Double.parseDouble(currentRow.get("TemperatureF"));
                    sum += temp;
                    counter ++;
                    average = sum / counter;
                }
            }
        }
        return average;
    }

    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(coldestHourInFile(parser).get("TemperatureF"));
    }

    public void testFileWithColdestTemperature(){
        System.out.println(fileWithColdestTemperature());
    }

    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }

    public void testLowestHumidityInManyFiles() {
        CSVRecord lowest = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + lowest.get("Humidity") +
                " at " + lowest.get("DateUTC"));
    }

    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + avg);
    }

    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        int value = 80;
        double avg = averageTemperatureWithHighHumidityInFile(parser, 80);
        if(avg <= 0.0) {
            System.out.println("No temperatures with that humidity");
        } else {
            System.out.println("Average Temp when high Humidity is " + avg);
        }
    }

    public static void main(String[] args) {
        Main tester = new Main();
//        tester.testColdestHourInFile();
//        tester.testFileWithColdestTemperature();
//        tester.testLowestHumidityInFile();
//        tester.testLowestHumidityInManyFiles();
//        tester.testAverageTemperatureInFile();
        tester.testAverageTemperatureWithHighHumidityInFile();

    }
}
