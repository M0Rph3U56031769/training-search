package trainingsearch.filehandling;

import org.jetbrains.annotations.NotNull;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class Csvhandler {
    public void csvhandler(@NotNull List names, List links, List timestamps) throws IOException {
        String csvFile = "google_links.csv";

        Writer fileWriter = new FileWriter(csvFile);
        fileWriter.write("NAME,LINK,DURATION\n");

        for (int i=0; i<names.size(); i++){
            fileWriter.write(names.get(i)+","+links.get(i)+","+timestamps.get(i)+"\n");
        }
        fileWriter.close();
    }
}
