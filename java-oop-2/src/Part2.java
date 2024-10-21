import java.lang.String;

public class Part2 {
    public String findSimpleGene(String dna) {
    // Convert the input string to uppercase for uniform processing
        String originalDna = dna;
        dna = dna.toUpperCase();

        // Find the index of "ATG"
        int startCodon = dna.indexOf("ATG");
        if (startCodon == -1) {
            // "ATG" not found, return empty string
            return "";
        }

        // Find the index of "TAA" starting from the position after "ATG"
        int stopCodon = dna.indexOf("TAA", startCodon + 3);
        if (stopCodon == -1) {
            // "TAA" not found, return empty string
            return "";
        }

        // Check if the length between "ATG" and "TAA" is a multiple of 3
        if ((stopCodon - startCodon) % 3 == 0) {
            String outputDna = originalDna.substring(startCodon, stopCodon + 3);

            // Return based on the original case of the string
            if (originalDna.equals(originalDna.toUpperCase())) {
                return outputDna.toUpperCase();  // Return gene in uppercase
            } else {
                return outputDna.toLowerCase();  // Return gene in lowercase
            }
        } else {
            // Gene is not a multiple of 3, return empty string
            return "";
        }
    }


    public void testFindSimpleGene(){
        // not a gene
        System.out.println();
        String dna = "AATGCGTAATATGGT";
        System.out.println("DNA strand is " + dna);
        String gene = findSimpleGene(dna);
        if (gene == "") System.out.println("There is no gene.");
        else System.out.println("Gene is " + gene + ".");

        // is a gene
        dna = "ATGTAA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        if (gene == "") System.out.println("There is no gene.");
        else System.out.println("Gene is " + gene + ".");

        // no "ATG"
        dna = "TGTAA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        if (gene == "") System.out.println("There is no gene.");
        else System.out.println("Gene is " + gene + ".");

        // no "TAA"
        dna = "ATGTA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        if (gene == "") System.out.println("There is no gene.");
        else System.out.println("Gene is " + gene + ".");

        // not a gene
        dna = "AATGCGTAATTAATGGT";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        if (gene == "") System.out.println("There is no gene.");
        else System.out.println("Gene is " + gene + ".");

        // is a gene
        dna = "atgctataa";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        if (gene == "") System.out.println("There is no gene.");
        else System.out.println("Gene is " + gene + ".");

        // is a gene
        dna = "atgCTAtaa";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        if (gene == "") System.out.println("There is no gene.");
        else System.out.println("Gene is " + gene + ".");
    }

    public static void main (String[] args) {
        Part2 dnaFinder = new Part2();
        dnaFinder.testFindSimpleGene();
    }
}
