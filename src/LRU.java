/*
 * Least Recently Used class.
 * Implements the LRU page replacement algorithm.
 * Uses a queue (instead of a stack) to track which page number was least 
 * recently used and replace it in memory.
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class LRU {
	
	private int faults = 0;
	
	public LRU(String pagesString, int frames) {
		
		int index = 0;
		
		// add to back of queue each page as it gets called.
		// used to track which page number was least recently used. 
		Queue<Integer> queue = new LinkedList<>();
		
		// HashMap to find index in memory of page to replace
		// from the value popped off the queue
		// key = memory index; value = page number stored in memory 
		HashMap<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
		
		int memory[] = new int[frames];
		
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
		
		// loop thru each page numbers passed
		for(int i = 0; i < pagesArray.length; i++) {
			
			// check if current page number is already in queue and remove it before
			// adding current page number to the back of the queue
			int queueSize = queue.size();
			for(int y = 0; y < queueSize; y++) {
				int temp = queue.remove();
				if(temp != pagesArray[i]) {
					queue.add(temp);
				}
			}
			
			// add current page number to back of the queue
			queue.add(pagesArray[i]);
			
			// search if page is in memory
			boolean found = false;
			for(int j = 0; j < memory.length; j++) {
				if(memory[j] == pagesArray[i]) {
					found = true;
					break;
				}
			}
			
			// page number was not in memory
			if(!found) {
				
				// HashMap and memory is not full, add to each 
				if(indexMap.size() < memory.length) {
					memory[index] = pagesArray[i];
					indexMap.put(index, pagesArray[i]);
					index++;
					
				}else { // HashMap and memory is full: replace page numbers in memory and HashMap
					
					// the page to be replace must be least recently used, not the lowest index.
					// use the HashMap to find the index from value taken from queue
					int removeValue = queue.remove();
					int removeIndex = 0;
					for(HashMap.Entry<Integer, Integer> pair : indexMap.entrySet() ) {
						if(pair.getValue() == removeValue) {
							removeIndex = pair.getKey();
							indexMap.remove(pair.getKey(), pair.getValue());
							break;
						}
					}
					
					// replace the value matching the queue head with the current page number
					memory[removeIndex] = pagesArray[i];
					
					// add the current page number to the HashMap
					indexMap.put(removeIndex, pagesArray[i]);
				}
				faults++;	
			}
		}
	}
	
	// method to retrieve the number of page faults for LRU
	public int getFaults() {
		return faults;
	}
}
