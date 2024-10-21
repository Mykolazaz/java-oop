public class WordPlay {

    public Boolean isVowel(char ch){
        char[] vowels = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'U'};
        for (char c: vowels){
            if (c == ch){
                return true;
            }
        }
        return false;
    }


    public String replaceVowels(String phrase, char ch) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < phrase.length(); i++) {
            char currentChar = phrase.charAt(i);
            if (isVowel(currentChar)) {
                result.append(ch);
            } else {
                result.append(currentChar);
            }
        }
        return result.toString();
    }


    public String emphasize(String phrase, char ch) {
        StringBuilder result = new StringBuilder();
        char lowerChar = Character.toLowerCase(ch);
        char upperChar = Character.toUpperCase(ch);

        for (int i = 0; i < phrase.length(); i++) {
            char currentChar = phrase.charAt(i);
            if (currentChar == lowerChar || currentChar == upperChar) {
                if (i % 2 == 0) {
                    result.append('*');
                } else {
                    result.append('+');
                }
            } else {
                result.append(currentChar);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        WordPlay tester = new WordPlay();
//        System.out.println(tester.isVowel('C'));
//        System.out.println(tester.replaceVowels("Hello World", '*'));;
        System.out.println(tester.emphasize("Mary Bella Abracadabra", 'a'));

    }
}
