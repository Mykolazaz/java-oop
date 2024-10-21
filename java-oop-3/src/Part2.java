public class Part2 {
    public int howMany(String stringa, String stringb) {
        int count = 0;
        int startIndex = 0;

        // Loop through stringb, searching for stringa
        while (true) {
            // Find the first occurrence of stringa in stringb starting from startIndex
            int index = stringb.indexOf(stringa, startIndex);

            // If stringa is not found, break the loop
            if (index == -1) {
                break;
            }

            // Increment the count for each occurrence found
            count++;

            // Move the startIndex forward, just after the found stringa, to avoid overlap
            startIndex = index + stringa.length();
        }

        return count;
    }

    public void testHowMany() {
        // Test case 1: Multiple non-overlapping occurrences of stringa
        String stringa1 = "GAA";
        String stringb1 = "ATGAACGAATTGAATC";
        System.out.println("Test case 1 - Occurrences of '" + stringa1 + "' in '" + stringb1 + "': " + howMany(stringa1, stringb1)); // Should return 3

        // Test case 2: Overlapping occurrences (should only count non-overlapping)
        String stringa2 = "AA";
        String stringb2 = "ATAAAA";
        System.out.println("Test case 2 - Occurrences of '" + stringa2 + "' in '" + stringb2 + "': " + howMany(stringa2, stringb2)); // Should return 2

        // Test case 3: No occurrences of stringa in stringb
        String stringa3 = "GCC";
        String stringb3 = "ATGAACGAATTGAATC";
        System.out.println("Test case 3 - Occurrences of '" + stringa3 + "' in '" + stringb3 + "': " + howMany(stringa3, stringb3)); // Should return 0

        // Test case 4: stringa is the entire stringb
        String stringa4 = "ATG";
        String stringb4 = "ATG";
        System.out.println("Test case 4 - Occurrences of '" + stringa4 + "' in '" + stringb4 + "': " + howMany(stringa4, stringb4)); // Should return 1
    }

    public static void main (String[] args) {
        Part2 testFinder = new Part2();
        testFinder.testHowMany();
    }


}
