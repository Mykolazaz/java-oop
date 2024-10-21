import edu.duke.FileResource;

public class CaesarCipher {

    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);

        // Create the alphabet strings for both uppercase and lowercase letters
        String alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLower = "abcdefghijklmnopqrstuvwxyz";

        // Create the shifted alphabet strings for both cases
        String shiftedUpper = alphabetUpper.substring(key) + alphabetUpper.substring(0, key);
        String shiftedLower = alphabetLower.substring(key) + alphabetLower.substring(0, key);

        // Loop through each character in the input string
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);

            // Check if the character is an uppercase letter
            int idxUpper = alphabetUpper.indexOf(currChar);
            if (idxUpper != -1) {
                // Replace with shifted uppercase letter
                char newChar = shiftedUpper.charAt(idxUpper);
                encrypted.setCharAt(i, newChar);
            } else {
                // Check if the character is a lowercase letter
                int idxLower = alphabetLower.indexOf(currChar);
                if (idxLower != -1) {
                    // Replace with shifted lowercase letter
                    char newChar = shiftedLower.charAt(idxLower);
                    encrypted.setCharAt(i, newChar);
                }
            }
        }

        return encrypted.toString();
    }

    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder(input);

        for (int i = 0; i < input.length(); i++) {
            char currChar = input.charAt(i);

            if (i % 2 == 0) {
                encrypted.setCharAt(i, encrypt(Character.toString(currChar), key1).charAt(0));
            }
            else {
                encrypted.setCharAt(i, encrypt(Character.toString(currChar), key2).charAt(0));
            }
        }

        // Return the encrypted string
        return encrypted.toString();
    }

    // Test method for encryptTwoKeys
    public void testEncryptTwoKeys() {
        // Test case 1: key1 = 23, key2 = 17
        System.out.println("Encrypted with keys 23 and 17: " + encryptTwoKeys("First Legion", 23, 17)); // Expected: "Czojq Ivdzle"
    }


    public void testCaesar(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key = 23;
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }

    public static void main(String[] args) {
        CaesarCipher tester = new CaesarCipher();
//        System.out.println(tester.encrypt("FIRST LEGION ATTACK EAST FLANK!", 23));
//        tester.testCaesar();
        tester.testEncryptTwoKeys(); // Test for two-key encryption
    }
}
