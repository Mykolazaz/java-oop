import edu.duke.*;

public class Part4 {
    public void checkUrl(){
        URLResource url = new URLResource("https://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String uWord : url.words()){
            String word = uWord.toLowerCase();
            if (word.contains("youtube.com")){
                String link = word.substring(word.indexOf("\""), word.lastIndexOf("\"") + 1);
                System.out.println(link);
            }
        }
    }

    public static void main (String[] args) {
        Part4 testURL = new Part4();
        testURL.checkUrl();
    }
}
