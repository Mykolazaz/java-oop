import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Main {
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //System.out.println(countryInfo(parser, "Afghanistan"));
        //listExportersTwoProducts(parser, "gold", "diamonds");
        //System.out.println(numberOfExporters(parser, "gold"));
        bigExporters(parser, "$999,999,999");
    }

    public String countryInfo(CSVParser parser, String country){
        for (CSVRecord record : parser){
            if(record.get("Country").contains(country)){
                return (country + ": " + record.get("Exports") + ": " + record.get("Value (dollars)"));
            }
        }
        return ("NOT FOUND");
    }

    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for (CSVRecord record : parser){
            String exports = record.get("Exports");
            if(exports.contains(exportItem1) && exports.contains(exportItem2)){
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }

    public int numberOfExporters(CSVParser parser, String exportItem){
        int exporterCount = 0;
        for (CSVRecord record : parser){
            String exports = record.get("Exports");
            if(exports.contains(exportItem)){
                exporterCount++;
            }
        }
        return(exporterCount);
    }

    public void bigExporters(CSVParser parser, String amount) {
        for(CSVRecord record : parser) {
            String value = record.get("Value (dollars)");
            if(value.length() > amount.length()) {
                String country = record.get("Country");
                System.out.println(country + " " + value);
            }
        }
    }

    public static void main(String[] args) {
        Main test1 = new Main();
        test1.tester();
    }
}
