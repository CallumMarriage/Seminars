package model;

import Exceptions.BadDataFormatException;

import java.util.*;
import java.io.*;

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
	 * Constructor - build Conference object containing list of seminars
	 */
	public Conference(){
		talks = new ArrayList<List<Seminar>>();
	}

	/**
	 * read seminar data from file to create the
	 * contents of the conference
	 *
	 * @param  file
	 * @throws IOException
	 * @throws BadDataFormatException
	 */
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
			if(attributes.length == 7 || attributes.length == 5  ){
                List<Seminar> seminarParts = new ArrayList<Seminar>();

                String title = attributes[0];
				String content = attributes[1];
				String partOneName = attributes[2];
                int type = Integer.parseInt(attributes[3]);

				if(attributes[4].equals("2")) {
					String partTwoName = attributes[5];
					int type2 = Integer.parseInt(attributes[6]);
					String[] contentInHalves = getContentSplitIntoHalves(content);
					seminarParts.add(new Seminar(title, contentInHalves[0], partOneName, type));
					seminarParts.add(new Seminar(title, contentInHalves[1], partTwoName, type2));
				} else{
					seminarParts.add(new Seminar(title, content, partOneName, type));
				}

				talks.add(seminarParts);
			} else{
				throw new BadDataFormatException("Some elements are missing, you have " + attributes.length + " attributes when you need 4!");

			}
		}
		in.close();
	}


	/**
	 * This takes the content and splits it when there are more than one lecturer.
	 * It will split it so they have a roughly even number of sentences to say.
	 *
	 * @param content
	 * @return an array containing the first and second halves of the content
	 */
	private String[] getContentSplitIntoHalves(String content){
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

		firstHalfContent.append("\n");
		contentSplitIntoHalves[0] = firstHalfContent.toString();
		contentSplitIntoHalves[1] = secondHalfConetnt.toString();

		return contentSplitIntoHalves;
	}


	/**
	 * Let a specified seminar in the conference proceed.
	 * @param index	The seminar index
	 * @return what is said in the seminar
	 */
	private String getSeminar(int index){
		List<Seminar> seminarParts = talks.get(index);
		StringBuilder wholeSeminar = new StringBuilder();
		for(Seminar seminar : seminarParts){
			wholeSeminar.append(seminar.proceed());

		}
		return wholeSeminar.toString();
	}
	
	/**
	 * Get the next seminar
	 * @return content
	 */
	public String getNextSeminar(){
		if(nextSeminar < numOfSeminar()){
				String content = getSeminar(nextSeminar);
				nextSeminar++;
				return content;
			}
		else {
            throw new ArrayIndexOutOfBoundsException("No more seminars.\n");
        }
	}
	
	/**
	 * Reveal the number of seminars in this conference.
	 * @return number of seminars in this conference
	 */
	public int numOfSeminar(){
		return talks.size();
	}
}
