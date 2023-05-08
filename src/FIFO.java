/*
 * First In, First Out class.
 * Implements the FIFO page replacement algorithm.
 * Uses a queue to know which page number was first entered 
 * and which page number should be replaced in memory.
 */

import java.util.LinkedList;
import java.util.Queue;

public class FIFO {
	
	private int faults = 0;
	
	public FIFO(String pagesString, int frames) {
		
		int headIndex = 0;
		int index = 0;
		int memory[] = new int[frames];
		
		// queue to keep track of which page number to remove from memory
		Queue<Integer> queue = new LinkedList<>();
		
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
			
			// search if page number is in memory
			boolean found = false;
			for(int j = 0; j < memory.length; j++) {
				if(memory[j] == pagesArray[i]) {
					found = true;
					break;
				}
			}
			
			// page number was not in memory
			if(!found) {
				
				// fill up memory and queue
				if(queue.size() < memory.length) {
					memory[index] = pagesArray[i];
					index++;
					queue.add(pagesArray[i]);
					
				}else { // queue and memory is full: replace page numbers in memory
					
					// remove head of queue and find it's index in the memory array
					int head = queue.poll();
					for(int k = 0; k < memory.length; k++) {
						if(memory[k] == head) {
							headIndex = k;
						}
					}
					
					// replace the value matching the queue head with the current page number
					memory[headIndex] = pagesArray[i];
					
					// add the current page number to the back of the queue
					queue.add(pagesArray[i]);
				}
				faults++;
			}
		}
	}
	
	// method to retrieve the number of page faults for FIFO
	public int getFaults() {
		return faults;
	}
	
}
