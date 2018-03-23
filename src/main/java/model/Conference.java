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
	private ArrayList<List<Seminar>> talks;
	// the index of the next seminar that is scheduled to happen 
	private int nextSeminar = 0;
	
	/**
	 * Constructor - read seminar data from file to create the
	 * contents of the conference
	 */
	public Conference(){
		talks = new ArrayList<List<Seminar>>();
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
			if(attributes.length!= 6){
				throw new BadDataFormatException("Some elements are missing, you have " + attributes.length + " attributes when you need 4!");
			}
			String title = attributes[0];
			String content = attributes[1];
			String partOneName = attributes[2];
			String partTwoName = attributes[4];

			int type;
			int type2;
			try {
				//check if you can parse the 3rd attribute as an integer
				type = Integer.parseInt(attributes[3]);
				type2 = Integer.parseInt(attributes[5]);
			} catch(Exception e){
				throw new NumberFormatException("The type: " + attributes[3] + " or " + attributes[5] + " is not a number.");
			}

			List<Seminar> seminarParts = new ArrayList<Seminar>();

			String[] contentInHalves = getContentSplitIntoHalves(content);

			seminarParts.add(new Seminar(title, contentInHalves[0], partOneName, type));
			seminarParts.add(new Seminar(title, contentInHalves[1], partTwoName, type2));

			talks.add(seminarParts);
		}
		in.close();
	}


	public String[] getContentSplitIntoHalves(String content){
		StringBuilder firstHalfContent = new StringBuilder();
		StringBuilder secondHalfConetnt = new StringBuilder();
		String[] contentSplitIntoHalves = new String[2];
		String[] contentAsArray = content.split("\\.");

		for(int i = 0; i < contentAsArray.length; i++){
			if(i < contentAsArray.length / 2){
				firstHalfContent.append(contentAsArray[i]);
			} else{
				secondHalfConetnt.append(contentAsArray[i]);
			}
		}
		contentSplitIntoHalves[0] = firstHalfContent.toString();
		contentSplitIntoHalves[1] = secondHalfConetnt.toString();

		return contentSplitIntoHalves;
	}


	/**
	 * Let a specified seminar in the conference proceed.
	 * @param index	The seminar index
	 * @return what is said in the seminar
	 */
	public String getSeminar(int index){
		List<Seminar> seminarParts = talks.get(index);
		StringBuilder wholeSeminer = new StringBuilder();
		for(Seminar seminar : seminarParts){
			wholeSeminer.append(seminar.proceed());

		}
		return wholeSeminer.toString();
	}
	
	/**
	 * Get the next seminar
	 * @return
	 */
	public String getNextSeminar(){
		if(nextSeminar < numOfSeminar()){
				String content = getSeminar(nextSeminar);
				nextSeminar++;
				return content;
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
