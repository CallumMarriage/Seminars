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

	public void readFile(String file) throws BadDataFormatException {
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader (fileInputStream));
			String line;
			while((line = bufferedReader.readLine()) != null){
				processFile(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * read seminar data from file to create the
	 * contents of the conference
	 *
	 * @param  line each line containing the attributes that define a lecture and its lecturers
	 * @throws BadDataFormatException Thrown when file does not contain the correct attributes
	 */
	private void processFile(String line)throws BadDataFormatException {

	    String[] attributes = line.split("\t");

        //check if all of the data is included
        if(attributes.length % 2 != 0 ){
            Map<String, Integer> lecturers = new HashMap<String, Integer>();
            List<Seminar> seminarParts = new ArrayList<Seminar>();

            int numberOfLecturers = Integer.parseInt(attributes[2]);
            int positionOfCurrentLecturersName = 3;

            for(int lecturer = 1; lecturer <= numberOfLecturers; lecturer++){
                lecturers.put(attributes[positionOfCurrentLecturersName], Integer.parseInt(attributes[positionOfCurrentLecturersName +1]));
                //next lecturer's name is 2 positions after the previous lecturer's name
                positionOfCurrentLecturersName += 2;
            }

            int i = 0;
            for(String name : lecturers.keySet()){
                seminarParts.add(new Seminar(attributes[0], getContentSplitIntoSections(attributes[1],numberOfLecturers)[i], name, lecturers.get(name)));
                i++;
            }

            talks.add(seminarParts);
        } else{
            throw new BadDataFormatException("You have too few/many Attributes");

        }
	}


	/**
	 * This takes the content and splits it when there are more than one lecturer.
	 * It will split it so they have a roughly even number of sentences to say.
	 *
	 * @param content the entire content of lecture
	 * @param numberOfLectures number of lecturers that the content will be split between
	 * @return an array containing the first and second halves of the content
	 */

	// I want to go through each sentence in a paragraph, whilst value is
	public String[] getContentSplitIntoSections(String content, int numberOfLectures){
        String[] contentAsList = content.split("\\.");
        String[] lectureSplitByLecturer = new String[numberOfLectures];

        int line = 0;
        int lecturer = 0;
        int contentLength = contentAsList.length;

        //remove every lecturer would not have anything to say as the number of lines available is smaller than the number of lecturers
        if(contentLength < numberOfLectures){
            //all lecturers that are in a position above the number of lines must be given a line to show that they can not say anything
           for(int i = contentLength; i <=  numberOfLectures-1; i++) {
			   lectureSplitByLecturer[i] = "I have nothing to say!";
               //make the number of lecturers equal to the content length as e
           }
            //make the number of lecturers equal to the content length as e
           numberOfLectures = contentLength;
        }

        //if the number of lines is one add the whole content and break as we have no content for the next itteration
        if(contentLength == 1){
            lectureSplitByLecturer[lecturer] = content;
            return lectureSplitByLecturer;
        }

        //go through every sentence in the file
        while(line < contentLength){
            //I split the file down into lecturers. So lecturer one will cover x amount of the lines
            while (lecturer < numberOfLectures) {

                StringBuilder section = new StringBuilder();

                for (int sectionNumber = 0; sectionNumber < (contentLength / numberOfLectures); sectionNumber++) {
                    //add every line within the section that the current lecturer covers.
                    //so if there are 3 lecturers and 9 lines, each lecturer would cover a section containing 3 lines
                    section.append(contentAsList[line]);
                    section.append(".");
                    line++;
                }
                //add the section to element of the array that the lecturer is at
				lectureSplitByLecturer[lecturer] = section.toString();
                lecturer++;
            }
        }
        //return the array with all of the lines in the contents split into sections, each section associated with a lecturer
        return lectureSplitByLecturer;
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
