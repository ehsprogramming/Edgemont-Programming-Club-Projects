package engine.util;

import java.util.HashMap;

public class Logger {

	static HashMap<String, Integer> frequency = new HashMap<>();
	
	public static void warn(String s) {
		System.err.println(s);
	}
	
	/** @param num Total number of times allowed to display this message */
	public static void warn(String s, int num) {
		frequency.putIfAbsent(s, 0);
		if(num > frequency.get(s)) {
			System.err.println(s);
			frequency.put(s, frequency.get(s) + 1);
		}
	}
	
}
