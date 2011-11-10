package jena.examples.rdf;

import java.util.*;
import java.util.regex.*;

public class testSt {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> patterns = new ArrayList<String> ();
		// add full string to do "is Substring searc"
		patterns.add("api");
		patterns.add("equipment");
		patterns.add("opcon");
		patterns.add("property");
		patterns.add("mp");

		String word = "b-api";
		if (word.indexOf("api") != -1){
			System.out.println ("pattern matceed");
		}

		String ee = "b-api";
		System.out.println ("len = " + ee.substring(ee.indexOf('-')+1));

//		HashMap <String, String> we_map = new HashMap<String, String>();
//		we_map.put("biolio", "api");
//		we_map.put("mphos", "api");
//
//		int len = new Set (we_map.values()).size();
//		System.out.println ("len = " + len);

		String l = "Boiliomycin//b-api Lilly//b-equipment in-license//b-equipment agreement//i-equipment San//name Luis//name Obispo//name Pharmaceuticals//name";
		StringTokenizer st1 = new StringTokenizer (l, " ");
		while (st1.hasMoreTokens()){
			String s = st1.nextToken();
			int p = s.indexOf("//");
			String w = s.substring(0, p);
			String e = s.substring(p+2);

			for (String pat : patterns){
				Pattern pp = Pattern.compile (pat);
				Matcher matcher = pp.matcher(e);
				if (matcher.matches()){
					System.out.println ("word " + w + " is a " + pp.pattern());
				}
			}
			System.out.println ("w =[" + w + "] e =[" + e + "]");
		}
	}
}
