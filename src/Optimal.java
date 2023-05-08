/*
 * Optimal class
 * Implements the Optimal page replacement algorithm.
 * Uses an array to hold when the page number stored in memory will be used
 * next. The largest value in the array determines which page number will be 
 * replaced.
 */

public class Optimal {
	
	private int faults = 0;
	
	public Optimal(String pagesString, int frames) {
		
		int memory[] = new int[frames];
		
		// array to hold the next pagesArray index 
		int nextIndex[] = new int[frames];
		
		// replace default zeros in array
		for(int i = 0; i < memory.length; i++) {
			memory[i] = -1;
		}
		
		// convert page reference String to an array of integers
		String pages = pagesString.replace(",","");
		int pagesArray[] = new int[pages.length()];
		for(int i = 0; i < pages.length(); i++) {
			pagesArray[i] = Integer.parseInt(pages.substring(i, i+1));
		}
				
		// loop thru the page numbers passed
		for(int i = 0; i < pagesArray.length; i++) {
			boolean found = false;
						
			// search if current page number is already in memory
			for(int j = 0; j < memory.length; j++) {
				if(memory[j] == pagesArray[i]) {
					found = true;
				}
			}
			
			// current page number is not in memory
			if(!found && i < pagesArray.length) {
				
				// loop thru each page number in memory
				for(int j = 0; j < memory.length; j++) {
					
					// get the next index in the pagesArray for page number
					nextIndex[j] = getIndex(pagesArray, i+1, memory[j]);
				}
				
				// get index of the highest value in the nextIndex array
				int highestIndex = 0;
				int highestValue = nextIndex[0];
				for(int j = 0; j < nextIndex.length; j++) {
					if(nextIndex[j] > highestValue) {
						highestValue = nextIndex[j];
						highestIndex = j;
					}
				}
				
				// place page number in memory and increment faults 
				memory[highestIndex] = pagesArray[i]; 
				faults++;
			}
		}
	}
	
	// function to return first index where pageArray entry matches value passed
	public static int getIndex(int[] pagesArray, int currentIndex, int page) {
		for(int i = currentIndex; i < pagesArray.length; i++) {
			if(pagesArray[i] == page) {
				return i;
			}
		}
		return 999;
	}
	
	// method to retrieve the number of page faults for Optimal
	public int getFaults() {
		return faults;
	}
	
}
