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
			if(attributes.length % 2 != 0 ){
                List<Seminar> seminarParts = new ArrayList<Seminar>();

                String title = attributes[0];
				String content = attributes[1];
				String partOneName = attributes[2];
                int type = Integer.parseInt(attributes[3]);

                int numberOfLecturers = Integer.parseInt(attributes[4]);
                int lec = 4;

                Map<String, Integer> lecturers = new HashMap<String, Integer>();
                lecturers.put(partOneName, type);
                
                if(numberOfLecturers > 1){
                    for(int additionalLecturer = 2; additionalLecturer <= numberOfLecturers; additionalLecturer++){
                        int currentName = lec + 1;
                        String additionalLecturerName = attributes[currentName];
                        int type2 = Integer.parseInt(attributes[currentName +1]);
                        lec += 2;
                        lecturers.put(additionalLecturerName, type2);
                    }
                    String[] contentSplitIntoSections = getContentSplitIntoSections(content,numberOfLecturers);
                    int i = 0;
                    for(String name : lecturers.keySet()){
                       seminarParts.add(new Seminar(title,contentSplitIntoSections[i], name, lecturers.get(name)));
                       i++;
                    }
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

	// I want to go through each sentence in a paragraph, whilst value is
	public String[] getContentSplitIntoSections(String content, int numberOfLectures){
        String[] contentAsList = content.split("\\.");
        String[] split = new String[numberOfLectures];

        int line = 0;
        int lecturer = 0;
        int contentLength = contentAsList.length;

        //remove every lecturer would not have anything to say as the number of lines available is smaller than the number of lecturers
        if(contentLength < numberOfLectures){
            //all lecturers that are in a position above the number of lines must be given a line to show that they can not say anything
           for(int i = contentLength; i <=  numberOfLectures; i++) {
               split[i] = "I have nothing to say!";
               //make the number of lecturers equal to the content length as e
           }
            //make the number of lecturers equal to the content length as e
           numberOfLectures = contentLength;
        }
        //go through every sentence in the file
        while(line < contentLength){
            //I split the file down into lecturers. So lecturer one will cover x amount of the lines
            while (lecturer < numberOfLectures) {
                StringBuilder section = new StringBuilder();
                //if the number of lines is one add the whole content and break as we have no content for the next itteration
                if(contentLength == 1){
                    split[lecturer] = content;
                    line++;
                    lecturer++;
                    break;
                }                                       
                for (int sectionNumber = 0; sectionNumber < contentLength / numberOfLectures; sectionNumber++) {
                    //add every line within the section that the current lecturer covers.
                    //so if there are 3 lecturers and 9 lines, each lecturer would cover a section containing 3 lines
                    section.append(contentAsList[line]);
                    section.append(".");
                    line++;
                }
                //add the section to element of the array that the lecturer is at
                split[lecturer] = section.toString();
                lecturer++;
            }
        }
        //return the array with all of the lines in the contents split into sections, each section associated with a lecturer
        return split;
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
