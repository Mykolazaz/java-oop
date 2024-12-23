import java.util.ArrayList;

import edu.duke.FileResource;

public class CharactersInPlay {
    private ArrayList<String> charNames = new ArrayList<String>();
    private ArrayList<Integer> charCount = new ArrayList<Integer>();

    public void update(String person){
        if(!charNames.contains(person)){
            charNames.add(person);
            charCount.add(0);
        } else {
            int idxPerson = charNames.indexOf(person);
            int currValue = charCount.get(idxPerson);
            charCount.set(idxPerson, currValue + 1);
        }
    }

    public void findAllCharacters(){
        FileResource fr = new FileResource("/Users/mykolasmotiejunas/Documents/vs-git-repositories/java-oop/java-oop-9/data/macbeth.txt");
        int lineNumber = 0;

        for (String line : fr.lines()) {
            if(lineNumber >= 52){
                if (line.matches("^ {2}\\w+\\..*")) {
                    int dotIndex = line.indexOf(".");
                    String personName = line.substring(2, dotIndex);
                    update(personName);
                }
            }
            lineNumber++;
        }
    }

    public void charactersWithNumParts(int num1, int num2){
        System.out.println("These characters have between " + num1 + " and " + num2 + " parts:");
        for (String person : charNames){
            int idxPerson = charNames.indexOf(person);
            int partCount = charCount.get(idxPerson);
            if (partCount > num1 && partCount < num2){
                System.out.println(person);
            }
        }
    }


    public void tester(){
        findAllCharacters();
        int positionInArray = 0;
        for (String character : charNames){
            int speakingParts = charCount.get(positionInArray);
            positionInArray += 1;
            System.out.println(character + " has " + speakingParts + " speaking parts");
        }

        charactersWithNumParts(2, 6);
    }

    public static void main(String[] args) {
        CharactersInPlay test = new CharactersInPlay();
        test.tester();
    }
}