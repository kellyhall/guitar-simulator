import java.util.NoSuchElementException;

public class RingBuffer {
	
	private int capacity;
	private int items;
	private int first;
	private int last;
	private double[] buffer;
	private double value;
	
	// constructs a ring buffer with a given capacity by creating an array of doubles
	// with that capacity
	public RingBuffer (int capacity) {
		this.capacity = capacity;
		buffer = new double[capacity];
	}
	
	// returns the number of items in the buffer
	public int size() {
		return items;
	}
	
	// returns whether the buffer has no items (if it's empty)
	public boolean isEmpty() {
		return items == 0;
	}
	
	// returns whether the buffer is full 
	public boolean isFull() {
		return items == capacity;
	}
	
	// if the buffer is full, throws an exception
	// otherwise, it adds the given value to the end of the queue in the buffer
	// and increments last in order to move it to the next open spot
	public void enqueue(double x) {
		if(isFull()) {
			throw new IllegalStateException("Ring Buffer is full.");
		}
		
		buffer[last] = x;
		items++;
		last++;
		if (last == capacity) {
			last = 0;
		}		
	}
	
	// if the buffer is empty, throws an exception
	// otherwise, it returns and deletes the value that has been in the queue the longest
	// and increments first in order to move it to the value that's been in the queue
	// the next-longest
	public double dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException("Ring Buffer is empty.");
		}
		items--;
		value = buffer[first];
		buffer[first] = 0.0;
		first++;
		if (first == capacity) {
			first = 0;	
		}
		return value;
		
	}
	
	// if the buffer is empty, throws an exception
	// otherwise, returns the value that has been in the queue the longest
	public double peek() {
		if (isEmpty()) {
			throw new NoSuchElementException("Ring Buffer is empty.");
		}	
		return buffer[first];
	}
	
	// prints out the buffer as an array of doubles to the console
	public String toString() {
		int counter = first;
		String result = "[";
		if(!isEmpty()) {
			result += buffer[counter];
			counter++;
		}
		for(int i = 0; i < items - 1; i++) {
			if (counter == capacity) {
				counter = 0;
			}
			result += ", ";
			result += buffer[counter];
			counter++;
		}
		result += "]";
		return result;
	}
	
}
