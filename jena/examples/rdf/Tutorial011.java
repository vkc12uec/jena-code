/*
 * (c) Copyright 2003, 2004, Hewlett-Packard Development Company, LP
 * All rights reserved.
 * [See end of file]
 * $Id: Tutorial01.java,v 1.3 2005/10/06 17:49:05 andy_seaborne Exp $
 */
package jena.examples.rdf ;

import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.vocabulary.*;
import java.io.*;
import java.util.*;

/** Tutorial 1 creating a simple model
 */

public class Tutorial011 extends Object {
	// some definitions
	static String personURI    = "http://somewhere/JohnSmith";
	static String tripleURI    = "http://somewhere/JohnSmith/triple";
	static String experimentURI1    = "http://somewhere/expr1";
	static String experimentURI2    = "http://somewhere/expr2";
	static String fullName     = "John Smith";

	public static ArrayList<String> getSentences (String f){
		ArrayList<String> al = new ArrayList<String>();
		int lines = 5;

		try {
			BufferedReader in = new BufferedReader(new FileReader(f));
			String str;
			while ((str = in.readLine()) != null) {
				al.add(str);
				/*lines--;
				if (lines == 0)
					break;*/
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return al;
	}

	public static void debug (String msg){
		System.out.println (msg);
	}

	public static void junk (){
		/*
    	   Resource ex1 = model.createResource(experimentURI1);
           ex1.addProperty(PHARMA.EXPR, "study of bioliomycin with wet roller compaction");
           ex1.addProperty(PHARMA.API, "bioliomycin");
           ex1.addProperty(PHARMA.EQUIP, "x-ray");
           ex1.addProperty(PHARMA.OPCON, "50C");
           ex1.addProperty(VCARD.FN, "myFN50C");

           Resource ex2 = model.createResource(experimentURI2);
           ex2.addProperty(PHARMA.EXPR, "tables compaction");
           ex2.addProperty(PHARMA.API, "phosphate");
           ex2.addProperty(PHARMA.EQUIP, "x-ray laser");
           ex2.addProperty(PHARMA.OPCON, "500C");
           ex2.addProperty(VCARD.FN, "myFN50C");
		 */

	}
	public static HashMap<String, String> getBroken (String line){
		StringTokenizer st = new StringTokenizer (line, " ");
		HashMap <String, String> we_map = new HashMap <String, String>();
		String word_entity;
		while (st.hasMoreTokens()){
			word_entity = st.nextToken();
			int p = word_entity.indexOf("//");
			String w = word_entity.substring(0,p);
			String e = word_entity.substring(p+2);
			we_map.put(w, e);
		}
		return we_map;
	}

	public static void main (String args[]) {
		String tagged_filename = "E:/Dropbox/RA works/filtered_tagged_files/1301869845-tagged_filtered";
		String fileCode = "1301869845";

		/*String FILTERED_FILES_DIR = "E:/Dropbox/RA works/filtered_tagged_files";

		// read files from DIR one at a time, and form the rdf
		File dir_object = new File (FILTERED_FILES_DIR);
		if (!dir_object.canRead()){
			System.out.println ("error : Directory not readable");
			return;
		}
		File []files = dir_object.listFiles();

		for (File f : files)
*/
		ArrayList<String> sentences = getSentences (tagged_filename);
		//System.out.println (sentences.get(0));
		//System.out.println (sentences.get(1));
		Model model = ModelFactory.createDefaultModel();
		Resource johnSmith  = model.createResource(personURI);
		ArrayList<String> patterns = new ArrayList<String> ();

		// add exact ENTITY class names as string , do "is Substring search"
		//TODO: is these all the ENTITY classes ?
		patterns.add("api");
		patterns.add("equipment");
		patterns.add("opcon");
		patterns.add("property");
		patterns.add("mp");

		// for each pattern, find the entity and make its node in 'resource' object
		// read each sentence and form the xml.
		Iterator <String> itr_sentences = sentences.iterator();
		int c = 0;

		while (itr_sentences.hasNext()){
			String line = itr_sentences.next();
			// line = Boiliomycin//b-api Lilly//b-equipment in-license//b-equipment agreement//i-equipment San//name Luis//name Obispo//name Pharmaceuticals//name
			//debug (line);
			Resource sent = model.createResource(tripleURI + "/s"+Integer.toString(c++));
			HashMap <String, String> we_map = getBroken (line);	// word_entity map

			// Make sure you have 2 diff. entity words in this line
			HashMap <String, Integer> diff_entity_counts = new HashMap <String, Integer>();
			String msg = "", allmsg = "";
			for (String entity : we_map.values()){
				//msg += " " + entity;
				if (entity.indexOf('-') != -1)
					msg = entity.substring(entity.indexOf('-')+1);	//diff_entity_counts.put(entity.substring(entity.indexOf('-')+1), 1);
				else
					msg = entity;
				diff_entity_counts.put(msg, 1);
			}
			//System.out.println (" +++ " + diff_entity_counts.toString());

			if (diff_entity_counts.size() < 3){
				//System.out.println("line =" + line);
				continue;
			}

			/*
			 * if the we_map doesnt has any 2 different entity, then they are un-related
			 * hence dont put them in the RDF graph
			 */

			Iterator <String> itr = we_map.keySet().iterator();
			while (itr.hasNext()){
				String w = itr.next();
				String e = we_map.get(w);

				// hardcode it ... improve later by switch etc.
				if (e.indexOf("api") != -1){
					sent.addProperty(PHARMA.API, w);
				}
				else if (e.indexOf("equipment") != -1){
					sent.addProperty(PHARMA.EQUIPMENT, w);
				}
				else if (e.indexOf("opcon") != -1){
					sent.addProperty(PHARMA.OPCON, w);
				}
				else if (e.indexOf("property") != -1){
					sent.addProperty(PHARMA.PROPERTY, w);
				}
				else if (e.indexOf("mp") != -1){
					sent.addProperty(PHARMA.MP, w);
				}
			}
			johnSmith.addProperty(PHARMA.SENT, sent);
		}
		model.setNsPrefix("pharma", PHARMA.uri);
		model.write(System.out);

		// add the property
		//johnSmith.addProperty(VCARD.FN, fullName);
	}
}
