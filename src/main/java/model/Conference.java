package model;

import Exceptions.BadDataFormatException;

import java.util.*;
import java.io.*;
import java.util.regex.Pattern;

/**
 * A model.Conference object keeps a record of all seminar records that
 * are in the conference.
 * 
 * @author Sylvia Wong
 * @version 25/09/06
 */
public class Conference {
	// the seminars in this conference
	private ArrayList<Seminar> talks;
	// the index of the next seminar that is scheduled to happen 
	private int nextSeminar = 0;
	
	/**
	 * Constructor - read seminar data from file to create the
	 * contents of the conference
	 */
	public Conference(){
		talks = new ArrayList<Seminar>();
	}

	public void readFile(String file) throws IOException, BadDataFormatException {
		File actualFile = new File(file);

		if(!actualFile.exists()){
			throw new IOException("The file does not exist");
		}

		// read seminar records from data file
		Scanner in = new Scanner(actualFile);
		// read records one by one
		while(in.hasNextLine()){
			String record = in.nextLine();
			String[] attributes = record.split("\t");

			//check if all of the data is included
			if(attributes.length!= 4){
				throw new BadDataFormatException("Some elements are missing, you have " + attributes.length + " attributes when you need 4!");
			}
			String title = attributes[0];
			String content = attributes[1];
			String name = attributes[2];

			if(Pattern.matches(".*\\d+.*", name)){
				throw new BadDataFormatException("Names can not contain numbers");
			}

			int type = 0;
			try {
				//check if you can parse the 3rd attribute as an integer
				type = Integer.parseInt(attributes[3]);
			} catch(Exception e){
				throw new NumberFormatException("The type: " + type + " is not a number.");
			}

			talks.add(new Seminar(title, content, name, type));
		}
		in.close();
	}

	/**
	 * Let a specified seminar in the conference proceed.
	 * @param index	The seminar index
	 * @return what is said in the seminar
	 */
	public String getSeminar(int index){
		Seminar seminar = (Seminar) talks.get(index);
		return seminar.proceed();
	}
	
	/**
	 * Get the next seminar
	 * @return
	 */
	public String getNextSeminar(){
		if(nextSeminar < numOfSeminar()){
				Seminar seminar = (Seminar) talks.get(nextSeminar);
				nextSeminar++;
				return seminar.proceed();
			}
		else
			throw new ArrayIndexOutOfBoundsException("No more seminars.\n");
	}
	
	/**
	 * Reveal the number of seminars in this conference.
	 * @return number of seminars in this conference
	 */
	public int numOfSeminar(){
		return talks.size();
	}
}
