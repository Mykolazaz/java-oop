import java.lang.String;

public class Part1 {
    public String findSimpleGene(String dna){

        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            // "ATG" not found, return empty string
            return "";
        }

        // Find the index of "TAA" starting from the position after "ATG"
        int stopIndex = dna.indexOf("TAA", startIndex + 3);
        if (stopIndex == -1) {
            // "TAA" not found, return empty string
            return "";
        }

        // Check if the length between "ATG" and "TAA" is a multiple of 3
        if ((stopIndex - startIndex) % 3 == 0) {
            // Return the gene sequence from "ATG" to "TAA" (inclusive)
            return dna.substring(startIndex, stopIndex + 3);
        } else {
            // Gene is not a multiple of 3, return empty string
            return "";
        }
    }



    public void testFindSimpleGene(){
        System.out.println();
        String dna = "AAATGCCCTAACTAGATTAAGAAACC";
        System.out.println("DNA strand is " + dna);
        String gene = findSimpleGene(dna);
        if (gene == "") System.out.println("There is no Gene.");
        else System.out.println("Gene is " + gene + ".");

        dna = "ATGTAA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        if (gene == "") System.out.println("There is no Gene.");
        else System.out.println("Gene is " + gene + ".");
        // no "ATG"
        dna = "TGTAA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        if (gene == "") System.out.println("There is no Gene.");
        else System.out.println("Gene is " + gene + ".");
        // no "TAA"
        dna = "ATGTA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        if (gene == "") System.out.println("There is no Gene.");
        else System.out.println("Gene is " + gene + ".");

        dna = "AATGCGTAATTAATGGT";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        if (gene == "") System.out.println("There is no Gene.");
        else System.out.println("Gene is " + gene + ".");
    }

    public static void main (String[] args) {
        Part1 dnaFinder = new Part1();
        dnaFinder.testFindSimpleGene();
    }
}
