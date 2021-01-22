import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GreatGatsby {
	private HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>> ();
	private String filename;
	private BufferedReader reader;
	private int chainlength;
	private String mostCommon;
	
	public GreatGatsby (int chainLength, String filename) throws IOException{
			mostCommon = "";		
		 	reader = new BufferedReader(new FileReader(filename));
			String key = "";
			for (int i = 0; i < chainLength; i++) {
				char charthing = (char)reader.read();
				key += ""+ charthing;
			}
			String value = "" +reader.read();
			ArrayList<String> values = new ArrayList<String>();

			if (map.containsKey(key)) {
				map.get(key).add(value);				
			}
			else {
				map.put(key,values);
				map.get(key).add(value);				
			}
			int longest = map.get(key).size();
			mostCommon = key;
						
			while (reader.ready()) {
				key = key.substring(1, chainLength-1) + value;
				value = ""+reader.read();
				if (map.containsKey(key)) {
					map.get(key).add(value);
				}
				else {
					map.put(key,values);
					map.get(key).add(value);
				}
				if (map.get(key).size() > longest) {
					longest = map.get(key).size();
					mostCommon = key;
				}
			}
		reader.close();	
	}

	public String write(int length) {
		String newDoc = "";		
		String pointer = mostCommon;
		newDoc += pointer;
		for (int i = 0;i<length; i ++) {
			int random = (int)(Math.random() * map.get(pointer).size());
			newDoc += map.get(pointer).get(random)+"" ;
			pointer = pointer.substring(1,chainlength -1) + map.get(pointer).get(random);
		}
		return newDoc;
	}
}
