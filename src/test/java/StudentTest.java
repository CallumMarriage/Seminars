import model.Student;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Created by callummarriage on 23/03/2018.
 */
public class StudentTest {
    @Test
    public void testStudentSpeach(){
        Student student = new Student("Steve");
        assertEquals("Errrmm, My name is dog.", student.speak("My name is dog.") );
    }
}
