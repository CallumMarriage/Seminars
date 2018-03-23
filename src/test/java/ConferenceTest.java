import Exceptions.BadDataFormatException;
import model.Conference;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by callummarriage on 20/03/2018.
 */
public class ConferenceTest {

    @Test(expected = BadDataFormatException.class)
    public void constructorBadFormatTest() throws IOException, BadDataFormatException {
        Conference conferenceBadFormat = new Conference();
        conferenceBadFormat.readFile("./src/test/resources/Bad-Format-Seminar.txt");
    }

    @Test(expected = BadDataFormatException.class)
    public void constructorBadNameTest() throws IOException, BadDataFormatException {
        Conference conferenceBadName = new Conference();
        conferenceBadName.readFile("./src/test/resources/Bad-Name-Seminar.txt");

    }

    @Test
    public void constructorGoodFileTest() throws IOException, BadDataFormatException {
        Conference conferenceGoodFile = new Conference();
        conferenceGoodFile.readFile("./src/test/resources/Good-File-Seminar.txt");
    }

}
