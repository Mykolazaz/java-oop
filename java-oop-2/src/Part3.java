public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb) {
        // Find the first occurrence of stringa in stringb
        int firstOccurrence = stringb.indexOf(stringa);

        if (firstOccurrence == -1) {
            // stringa does not appear in stringb
            return false;
        }

        // Find the second occurrence of stringa in stringb after the first occurrence
        int secondOccurrence = stringb.indexOf(stringa, firstOccurrence + stringa.length());

        // If the second occurrence is found, return true; otherwise, return false
        return secondOccurrence != -1;
    }

    public String lastPart(String stringa, String stringb){
        if (stringb.indexOf(stringa) != -1){
            int printFrom = stringb.indexOf(stringa) + stringa.length();
            stringb = stringb.substring(printFrom);
        }
        return stringb;
    }

    public void testTwoOccurences(){
        String a = "by";
        String b = "A story by Abby Long";
        boolean check = twoOccurrences(a, b);
        if (check) System.out.println("There are two occurences of " + a + " in " + b);
        else System.out.println("There aren't two occurences of " + a + " in " + b);

        a = "atg";
        b = "ctgtatgta";
        check = twoOccurrences(a, b);
        if (check) System.out.println("There are two occurences of " + a + " in " + b);
        else System.out.println("There aren't two occurences of " + a + " in " + b);
    }

    public void testLastPart(){
        String a = "an";
        String b = "banana";
        String lastPartCheck = lastPart(a, b);
        System.out.println("The part of the string after " + a + " in " + b + " is " + lastPartCheck);

        a = "zoo";
        b = "forest";
        lastPartCheck = lastPart(a, b);
        System.out.println("The part of the string after " + a + " in " + b + " is " + lastPartCheck);
    }

    public static void main (String[] args) {
        Part3 testFinder = new Part3();
        testFinder.testTwoOccurences();
        testFinder.testLastPart();
    }
}
