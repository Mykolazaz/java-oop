import java.lang.String;


public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
        if ((stopIndex - startIndex) % 3 == 0){
            return stopIndex;
        }
        return dna.length();
    }

    public String findGene(String dna){
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "";
        }
        int indexStopTAA = findStopCodon(dna, startIndex, "TAA");
        int indexStopTAG = findStopCodon(dna, startIndex, "TAG");
        int indexStopTGA = findStopCodon(dna, startIndex, "TGA");
        int closestStop = Math.min(indexStopTAA, Math.min(indexStopTAG, indexStopTGA));

        if (closestStop == dna.length()) {
            return "";
        }
        return dna.substring(startIndex, closestStop + 3);
    }

    public void printAllGenes(String dna) {
        int startIndex = 0;

        while (true) {
            // Find a gene starting from the current index
            String gene = findGene(dna.substring(startIndex));

            // If no gene is found, break the loop
            if (gene.isEmpty()) {
                break;
            }

            // Print the gene found
            System.out.println("Gene found: " + gene);

            // Move the start index to just after the found gene for the next search
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }
    }


    public void testFindStopCodon(){
        String dna1 = "CCATGCCCTAA";
        String dna2 = "CATGCCTAA";
        System.out.println(findStopCodon(dna1, 2, "TAA"));
        System.out.println(findStopCodon(dna2, 1, "TAA"));
    }

    public void testFindGene() {
        // Test case 1: DNA with no "ATG"
        String dna1 = "CCCGGGTTT"; // No "ATG"
        System.out.println("Test case 1: " + dna1);
        System.out.println("Gene: " + findGene(dna1)); // Should return ""

        // Test case 2: DNA with "ATG" and one valid stop codon
        String dna2 = "ATGCCCTAA"; // "ATG" followed by "TAA", valid gene "ATGCCCTAA"
        System.out.println("Test case 2: " + dna2);
        System.out.println("Gene: " + findGene(dna2)); // Should return "ATGCCCTAA"

        // Test case 3: DNA with "ATG" and multiple valid stop codons
        String dna3 = "ATGCCCTAGTAA"; // "ATG" with "TAG" and "TAA", gene should end at "TAG"
        System.out.println("Test case 3: " + dna3);
        System.out.println("Gene: " + findGene(dna3)); // Should return "ATGCCCTAG"

        // Test case 4: DNA with "ATG" and no valid stop codon
        String dna4 = "ATGCCCCTGGG"; // "ATG" but no valid stop codon
        System.out.println("Test case 4: " + dna4);
        System.out.println("Gene: " + findGene(dna4)); // Should return ""

        // Test case 5: DNA with "ATG" and stop codons not in frame
        String dna5 = "ATGCCCTGATAA"; // "ATG", but "TGA" is not in frame, so gene should end at "TAA"
        System.out.println("Test case 5: " + dna5);
        System.out.println("Gene: " + findGene(dna5)); // Should return "ATGCCCTGA"
    }


    public static void main (String[] args) {
        Part1 testFinder = new Part1();
        testFinder.testFindStopCodon();
        testFinder.testFindGene();
    }

}
