import edu.duke.*;

public class TestCaesarCipher {

    public int[] countLetters(String encrypt) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int i = 0; i < encrypt.length(); i++) {
            char ch = Character.toLowerCase(encrypt.charAt(i));
            int dex = alphabet.indexOf(ch);
            if(dex != - 1) {
                counts[dex] += 1;
            }
        }
        return counts;
    }
    
    public int maxIndex(int[] freqs) {
        int maxDex = 0;
        for(int i = 0; i < freqs.length; i++) {
            if(freqs[i] > freqs[maxDex]) {
                maxDex = i;
            }
        }
        return maxDex;
    }
    
    void breakCaesarCipher(String input) {
        int[] freqs = countLetters(input);
        int key = maxIndex(freqs) - 4;
        CaesarCipher cc = new CaesarCipher(key);
        String decrypted = cc.decrypt(input);
        System.out.println(decrypted);
    }
    
    void simpleTests() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCipher cc = new CaesarCipher(15);
        String encrypted = cc.encrypt(message);
        System.out.println(encrypted);
        //String decrypted = cc.decrypt(encrypted);
        //System.out.println(decrypted);
        
        breakCaesarCipher(encrypted);
    }

    public static void main(String[] args) {
        TestCaesarCipher tester = new TestCaesarCipher();
        int[] howManyLetters = tester.countLetters("Good morning");
        System.out.println(tester.maxIndex(howManyLetters));
        tester.breakCaesarCipher("zh zhuh hqhplhv exw wkhq zh ehfdph wkh ehvw iulhqgv hyhu");
    }
}