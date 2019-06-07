import org.jetbrains.annotations.NotNull;
import trainingsearch.searchengines.Google;


public class Main {

    public static void main(@NotNull String[] args) throws Exception {

//        HashMap for keyword args:

//        HashMap<String, String> params = convertToKeyValuePair(args);
//        params.forEach((k, v) -> System.out.println("Key : " + k + " Value : " + v));

        String help = "HELP: \n\n" +
                "" +
                "usage: [script] --help \n" +
                "\n" +
                "Arguments: \n" +
                "-h --help          this help message\n" +
                "-v --version       print version number\n";
        for (int i=0; i<args.length; i++){
            if (args[i].equals("--Google-csv")){
                System.out.println("GOOGLE CSV LOCATION: "+args[i]+" "+args[i+1]);
            }
        }

//        USAGE:

        Google google_properties = new Google("robot framework");
        google_properties.searchTrainings(10);
    }
}