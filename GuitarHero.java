public class GuitarHero {
	
	 public static void main(String[] args) {
		 String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
		 GuitarString[] strings = new GuitarString[keyboard.length()];
		 for (int i = 0; i < keyboard.length(); i++) {
			 double frequency = 440 * Math.pow(1.05956, i - 24);
			 strings[i] = new GuitarString(frequency);
		 }
		 
		 final double TEXT_POS_X = .5;
	     final double TEXT_POS_Y = .7;
		 
		 StdDraw.text(TEXT_POS_X, TEXT_POS_Y, "Type one of the following characters in order to play a note: ");
		 StdDraw.text(TEXT_POS_X, TEXT_POS_Y - .1, "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ");
		 
		 play(strings, keyboard);
	 }
	 
	 private static void play(GuitarString[] strings, String keyboard) {
		 while (true) {
	            
	            // check if the user has typed a key, and, if so, process it
	            if (StdDraw.hasNextKeyTyped()) {
	 
	                // turns the character typed by the user into a string, and then 
	            	// finds its position in the keyboard string (and thus in the array of GuitarStrings)
	                char key = StdDraw.nextKeyTyped();
	                String keyString = key + "";
	                int position = keyboard.indexOf(key);
	                

	                // pluck the corresponding string assuming the character corresponds to a key
	                if(keyboard.contains(keyString)) {
	                	GuitarString string = strings[position];
	                	string.pluck();
	                }
	            }

	            // compute the superposition of the samples by adding the sample 
	            // obtained from each string together
	            double sample = 0.0;
	            for(int i = 0; i < strings.length; i++) {
	            	GuitarString string = strings[i];
	            	sample += string.sample();
	            }

	            // send the result to standard audio
	            StdAudio.play(sample);

	            // advance the simulation of each guitar string by one step
	            for(int i = 0; i < strings.length; i++) {
	            	GuitarString string = strings[i];
	            	string.tic();
	            }
	            
	        }
	 }
	 
	 

}
