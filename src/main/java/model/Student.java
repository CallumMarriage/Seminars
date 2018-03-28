package model;

import static Constants.SpeakerConstants.NAME_INTRO;
import static Constants.SpeakerConstants.THANKS;

/**
 * Created by callummarriage on 23/03/2018.
 */
public class Student implements Speaker {
    private String name;

    public Student(String name){
        this.name = name;
    }

    public String introduceOneself() {
        return speak(NAME_INTRO + name + ". " + THANKS );
    }

    /**
     * Converts the students speech to include mannerism (Saying errrmm. at the beginning of every sentence
     *
     * @param speech the content of the speech that is to be made
     * @return speach altered
     */
    public String speak(String speech) {


        String[] speechAsArray = speech.split("\\. ");

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < speechAsArray.length; i++){

            String[] lineAsArray = speechAsArray[i].split(" ");

            String string = "";

            if(i != 0){
                string = " ";
            }

            lineAsArray[0] = string + "Errrmm, " + lineAsArray[0];

            for(int e = 0; e < lineAsArray.length; e++){
                sb.append(lineAsArray[e]);
                if(e != lineAsArray.length -1){
                    sb.append(" ");
                }
            }

            if(speechAsArray.length != 1) {
                sb.append(".");
            }
        }
        return sb.toString();
    }
}
