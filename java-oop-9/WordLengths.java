import edu.duke.FileResource;

public class WordLengths {
	//TO-DO: OUTPUT EACH WORD OF EACH LENGTH
	
	public void countWords(FileResource resource, int[] counts){
		for(String word : resource.words()){
			word = word.toLowerCase();
			int wordLength = word.length();
			if (wordLength != -1) {
				counts[wordLength] += 1;
			}
		}

		for(int k=0; k < counts.length; k++){
			System.out.println(k + "\t" + counts[k]);
		}
	}

	public int indexOfMax(int[] values) {
		int largestValue = 0;
	    for (int k=0; k<values.length; k++) {
	        if (values[k] > largestValue) {
	               largestValue = values[k];
	           }
	       }
	    return largestValue;
	}

	public void testCountWordLengths(){
		WordLengths tester = new WordLengths();
		FileResource resource = new FileResource("data/caesar.txt");
		int[] numberOfLength = new int[31];
        tester.countWords(resource, numberOfLength);
		System.out.println(tester.indexOfMax(numberOfLength));
	}

    public static void main(String[] args) {
        WordLengths tester = new WordLengths();
		tester.testCountWordLengths();
		
    }
}