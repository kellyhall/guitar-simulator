import java.util.Random;

public class GuitarString {
	
	private int tics;
	private static final int SAMPLING_RATE = 44100;
	private static final double ENERGY_DECAY = 0.994;
	private Random r = new Random();
	private int N;
	private RingBuffer rb;
	
	// constructs a Guitar String by making a Ring Buffer with capacity N and initializes it to a string at rest
	// by enqueuing zeros to fill the array
	public GuitarString (double frequency) {
		N = (int) Math.round(SAMPLING_RATE / frequency);
		rb = new RingBuffer(N);
		for(int i = 0; i < N; i++) {
			rb.enqueue(0);
		}
	}
	
	// constructs a guitar string by making a ring buffer with the capacity of the given array
	public GuitarString (double[] init) {
		N = init.length;
		rb = new RingBuffer(N);
		for(int i = 0; i < init.length; i++) {
			rb.enqueue(init[i]);
		}
	}
	
	// plucks the string by enqueuing a value between -0.5 and 0.5
	public void pluck() {
		for(int j = 0; j < N; j++) {
			rb.dequeue();
		}
		for(int i = 0; i < N; i++) {
			rb.enqueue(r.nextInt(2) - 0.5);
		}
	}
	
	// advances the simulation one time step and applies Karplus-Strong algorithm to update the queue
	public void tic() {
		tics++;
		double value1 = rb.dequeue();
		double value2 = rb.peek();
		double valueAdded = ENERGY_DECAY * ((value1 + value2) / 2);
		rb.enqueue(valueAdded);
	}
	
	// returns the value at the front of the ring buffer
	public double sample() {
		return rb.peek();
	}
	
	// returns the number of times tic() was called
	public int time() {
		return tics;
	}
}
