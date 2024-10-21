public class Part3 {
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

    public int countGenes(String dna) {
        int count = 0;
        int startIndex = 0;

        // Loop through the DNA string, searching for genes
        while (true) {
            // Find the next gene in the DNA string starting from startIndex
            String gene = findGene(dna.substring(startIndex));

            // If no gene is found, break the loop
            if (gene.isEmpty()) {
                break;
            }

            // Increment the count for each gene found
            count++;

            // Move the startIndex forward, just after the found gene
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }

        return count;
    }

    public void testCountGenes() {
        // Test case 1: DNA with no genes
        String dna1 = "ATCGTACGTTAG";
        System.out.println("Test case 1 - Number of genes in '" + dna1 + "': " + countGenes(dna1)); // Should return 0

        // Test case 2: DNA with one gene
        String dna2 = "ATGCGTAATAG";
        System.out.println("Test case 2 - Number of genes in '" + dna2 + "': " + countGenes(dna2)); // Should return 1

        // Test case 3: DNA with multiple genes
        String dna3 = "ATGTAAGATGCCCTAGT";
        System.out.println("Test case 3 - Number of genes in '" + dna3 + "': " + countGenes(dna3)); // Should return 2

        // Test case 4: DNA with overlapping gene patterns (should not count overlaps)
        String dna4 = "ATGAAATAAATGAAATAG";
        System.out.println("Test case 4 - Number of genes in '" + dna4 + "': " + countGenes(dna4)); // Should return 2

        // Test case 5: Edge case - Empty DNA string
        String dna5 = "";
        System.out.println("Test case 5 - Number of genes in an empty DNA string: " + countGenes(dna5)); // Should return 0

        // Test case 6: DNA with no start or stop codon
        String dna6 = "CGTACGGTACGTAG";
        System.out.println("Test case 6 - Number of genes in '" + dna6 + "': " + countGenes(dna6)); // Should return 0
    }

    public static void main (String[] args) {
        Part3 testFinder = new Part3();
        testFinder.testCountGenes();
    }

}
