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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

/** Tutorial 1 creating a simple model
 */

public class las extends Object {
    // some definitions
    static String personURI    = "http://somewhere/JohnSmith";
    static String fullName     = "John Smith";

    public static void debug (String msg) {
    	System.out.println (msg);
    }

      public static void main (String args[]) {
        // create an empty model
        Model model = ModelFactory.createDefaultModel();

        /*
        String tagged_filtered_file = "E:/Dropbox/RA works/1301869834-tagged_filtered";
        HashMap<Integer, String> line_string = new HashMap<Integer, String>();
        int lineno = 0;
        ArrayList <String> sentences = new ArrayList<String>();

        try {
            BufferedReader in = new BufferedReader(new FileReader(tagged_filtered_file));
            String str;
            while ((str = in.readLine()) != null) {
                line_string.put(new Integer (lineno++), str);
                sentences.add(str);
            }
            in.close();
        } catch (IOException e) {
        	e.printStackTrace();
        	//System.out.println ("smth worng with reading file");
        }
		// remove this segment
        for (Integer key : line_string.keySet()){
        	lineno = key.intValue();
        	String v = line_string.get(key);

        	debug (Integer.toString(lineno));
        	debug (v);
        }

        Iterator<String> itr = sentences.iterator();
        while (itr.hasNext()){
        	String s = itr.next();
        }
        */

       // create experiment properties
        /*
        <rdf:Description rdf:about="http://somewhere/exp1/">
        <pharma:EXPR>Study of bioliomycin</pharma:EXPR>
        <pharma:EQUIP>x-ray</pharma:EQUIP>
        <pharma:API>bioliomycin</pharma:API>
        <pharma:OPCON>50C</pharma:OPCON>
      </rdf:Description>
        */

        Property p = model.createProperty("http://shit/ns", "#FN");
        Resource johnSmith = model.createResource(personURI);

      // add the property
      johnSmith.addProperty(VCARD.FN, fullName);

      Resource sentence = model.createResource("s1");
      sentence.addProperty(p, arg1)

      }
}
