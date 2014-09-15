package Backend;

public class HashTable {
	Hash<String, String> artists;
	Hash<String, String> songs;

	
	public void remove(String category, String name){
		if(category.equals("artist")){
			artists.remove(name);
		}
		
	}
	
	
	
	
	public void add(String name, int option){
		if(option==0){
			//input is an artist
			artists.put(name, name);//TODO ? correct?
			
		}
		else if(option == 1){
			//input is a song
			songs.put(name, name);
		}
		else{
			System.out.println("Warning: this option is not available.");
		}
	}

	/**
	 * 
	 * @param s
	 * @param M size of hash
	 * @return
	 */
	public long sfold(String s, int M) {
		int intLength = s.length() / 4;
		long sum = 0;
		for (int j = 0; j < intLength; j++) {
			char c[] = s.substring(j * 4, (j * 4) + 4).toCharArray();
			long mult = 1;
			for (int k = 0; k < c.length; k++) {
				sum += c[k] * mult;
				mult *= 256;
			}
		}

		char c[] = s.substring(intLength * 4).toCharArray();
		long mult = 1;
		for (int k = 0; k < c.length; k++) {
			sum += c[k] * mult;
			mult *= 256;
		}

		return (Math.abs(sum) % M);
	}

}
