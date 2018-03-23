import model.GUIConference;

/**
 * Created by callummarriage on 20/03/2018.
 */
public class Application {

    /**
     * The main for starting up this application.
     * This application expect only one command line input argument
     * which specifies the name of the data file. This data file stores
     * seminar records.
     * @param args	input argument(s)
     */
    public static void main(String[] args) {
        try{
            //new model.GUIConference(args[0]);
            new GUIConference("./src/main/resources/seminars.txt");
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Missing input argument");
        }
    }
}
