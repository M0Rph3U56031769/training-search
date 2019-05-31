import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class Csvhandler {
    public Boolean csvhandler(ArrayList names, ArrayList links) throws IOException {
        String csvFile = "google_links.csv";
        Writer fileWriter = new FileWriter(csvFile);

        fileWriter.write("name,link\n");

        for (int i=0; i<names.size(); i++){
            fileWriter.write(names.get(i)+","+links.get(i)+"\n");
        }
        fileWriter.close();

        return Boolean.TRUE;
    }
}
