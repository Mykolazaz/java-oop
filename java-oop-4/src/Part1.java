import java.lang.String;
import edu.duke.*;

public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currentIndex = dna.indexOf(stopCodon, startIndex + 3);

        // Loop to find the next stopCodon that is a multiple of 3 away
        while (currentIndex != -1) {
            if ((currentIndex - startIndex) % 3 == 0) {
                return currentIndex;
            }
            // Search for the next occurrence of the stop codon
            currentIndex = dna.indexOf(stopCodon, currentIndex + 1);
        }

        // If no valid stop codon is found, return the length of the DNA string
        return dna.length();
    }

    public String findGene(String dna) {
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

    public StorageResource getAllGenes(String dna) {
        StorageResource geneList = new StorageResource();
        int startIndex = 0;

        // Loop through the DNA string, searching for genes
        while (true) {
            // Find the next gene in the DNA string starting from startIndex
            String gene = findGene(dna.substring(startIndex));

            // If no gene is found, break the loop
            if (gene.isEmpty()) {
                break;
            }

            // Add the found gene to the StorageResource
            geneList.add(gene);

            // Move the startIndex forward, just after the found gene
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }

        return geneList;
    }

    public double cgRatio(String dna) {
        double count = 0.0;

        // Loop through the dna string
        for (int i = 0; i < dna.length(); i++) {
            char currentChar = dna.charAt(i);

            // Increment the count if the character is 'C' or 'G'
            if (currentChar == 'C' || currentChar == 'G') {
                count++;
            }
        }

        // Return the ratio of C's and G's to the total length of the DNA
        return count / dna.length();
    }

    public int countCTG(String dna) {
        int count = 0;
        int startIndex = 0;

        // Loop through stringb, searching for stringa
        while (true) {
            // Find the first occurrence of stringa in stringb starting from startIndex
            int index = dna.indexOf("CTG", startIndex);

            // If stringa is not found, break the loop
            if (index == -1) {
                break;
            }

            // Increment the count for each occurrence found
            count++;

            // Move the startIndex forward, just after the found stringa, to avoid overlap
            startIndex = index + 3;
        }

        return count;
    }

    public void processGenes(StorageResource sr) {
        int countLongerThan9 = 0;
        int countLongerThan60 = 0;
        int countHighCGRatio = 0;
        int maxLength = 0;

        for (String gene : sr.data()) {
            if (gene.length() > 9) {
                countLongerThan9++;
            }

            if (gene.length() > 60) {
                System.out.println("Gene longer than 60 characters: " + gene);
                countLongerThan60++;
            }

            double cgRatio = cgRatio(gene);
            if (cgRatio > 0.35) {
                System.out.println("Gene with CG ratio higher than 0.35: " + gene);
                countHighCGRatio++;
            }

            if (gene.length() > maxLength) {
                maxLength = gene.length();
            }
        }

        System.out.println("Number of genes longer than 9 characters: " + countLongerThan9);
        System.out.println("Number of genes longer than 60 characters: " + countLongerThan60);
        System.out.println("Number of genes with CG ratio higher than 0.35: " + countHighCGRatio);
        System.out.println("Length of the longest gene: " + maxLength);
        System.out.println("Total number of genes: " + sr.size());
    }


    public void testFindStopCodon() {
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

    public void testGetAllGenes() {
        // Test case 1: DNA with no genes
        String dna1 = "ATCGTACGTTAG";
        System.out.println("Test case 1 - Genes found in '" + dna1 + "':");
        StorageResource genes1 = getAllGenes(dna1);
        for (String gene : genes1.data()) {
            System.out.println(gene);
        }
        if (!genes1.data().iterator().hasNext()) {
            System.out.println("No genes found.");
        }

        // Test case 2: DNA with one gene
        String dna2 = "ATGCGTAATAG";
        System.out.println("Test case 2 - Genes found in '" + dna2 + "':");
        StorageResource genes2 = getAllGenes(dna2);
        for (String gene : genes2.data()) {
            System.out.println(gene);
        }

        // Test case 3: DNA with multiple genes
        String dna3 = "ATGTAAGATGCCCTAGT";
        System.out.println("Test case 3 - Genes found in '" + dna3 + "':");
        StorageResource genes3 = getAllGenes(dna3);
        for (String gene : genes3.data()) {
            System.out.println(gene);
        }

        // Test case 4: DNA with overlapping start/stop codons (should not overlap)
        String dna4 = "ATGAAATAAATGAAATAG";
        System.out.println("Test case 4 - Genes found in '" + dna4 + "':");
        StorageResource genes4 = getAllGenes(dna4);
        for (String gene : genes4.data()) {
            System.out.println(gene);
        }

        // Test case 5: Edge case - Empty DNA string
        String dna5 = "";
        System.out.println("Test case 5 - Genes found in an empty DNA string:");
        StorageResource genes5 = getAllGenes(dna5);
        for (String gene : genes5.data()) {
            System.out.println(gene);
        }
        if (!genes5.data().iterator().hasNext()) {
            System.out.println("No genes found.");
        }

        // Test case 6: DNA with no start or stop codon
        String dna6 = "CGTACGGTACGTAG";
        System.out.println("Test case 6 - Genes found in '" + dna6 + "':");
        StorageResource genes6 = getAllGenes(dna6);
        for (String gene : genes6.data()) {
            System.out.println(gene);
        }
        if (!genes6.data().iterator().hasNext()) {
            System.out.println("No genes found.");
        }
    }

    public void testProcessGenes() {
        // Test case 1: Some genes longer than 9 characters
        String dna1 = "ATGCGTAAATGCCCTAGATGCCCCCGGTAA";
        StorageResource genes1 = getAllGenes(dna1);
        System.out.println("Test case 1:");
        processGenes(genes1);

        // Test case 2: No genes longer than 9 characters
        String dna2 = "ATGTAATAGATGTAA";
        StorageResource genes2 = getAllGenes(dna2);
        System.out.println("\nTest case 2:");
        processGenes(genes2);

        // Test case 3: Genes with CG ratio higher than 0.35
        String dna3 = "ATGCCCGGGTAATAGATGCGCCGGTGA";
        StorageResource genes3 = getAllGenes(dna3);
        System.out.println("\nTest case 3:");
        processGenes(genes3);

        // Test case 4: Genes with CG ratio lower than 0.35
        String dna4 = "ATGAAATAAATGTGA";
        StorageResource genes4 = getAllGenes(dna4);
        System.out.println("\nTest case 4:");
        processGenes(genes4);

        // Test case 5: Mixed conditions
        String dna5 = "ATGCGATAGATGCCCCCGGTAAATGCGTAA";
        StorageResource genes5 = getAllGenes(dna5);
        System.out.println("\nTest case 5:");
        processGenes(genes5);
    }

    public void testProcessGenesFromFile() {
        // Load the DNA sequence from a file
        FileResource fr = new FileResource("./brca1line.fa");
        String dna = fr.asString().toUpperCase();  // Convert to uppercase to handle case insensitivity

        // Get all genes from the DNA sequence and store them in a StorageResource
        StorageResource genes = getAllGenes(dna);

        // Process the genes using processGenes
        processGenes(genes);
    }

    public static void main(String[] args) {
        Part1 testFinder = new Part1();
//        testFinder.testFindStopCodon();
//        testFinder.testFindGene();
//        testFinder.testGetAllGenes();
//        testFinder.testProcessGenes();
        testFinder.testProcessGenesFromFile();
    }

}