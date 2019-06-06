package trainingsearch;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Columns {

    private List<List<String>> lines = new ArrayList<>();
    private List<Integer> maxLengths = new ArrayList<>();
    private int numColumns = -1;

    public Columns addLine(String... line) {

        if (numColumns == -1){
            numColumns = line.length;
            for(int column = 0; column < numColumns; column++) {
                maxLengths.add(0);
            }
        }

        if (numColumns != line.length) {
            throw new IllegalArgumentException();
        }

        for(int column = 0; column < numColumns; column++) {
            int length = Math
                    .max(
                            maxLengths.get(column),
                            line[column].length()
                    )
                    ;
            maxLengths.set( column, length );
        }

        lines.add( Arrays.asList(line) );

        return this;
    }

    public void print(){
        System.out.println( toString() );
    }

    public String toString(){
        StringBuilder result = new StringBuilder();
        for(List<String> line : lines) {
            for(int i = 0; i < numColumns; i++) {
                result.append(pad( line.get(i), maxLengths.get(i) + 1 ));
            }
            result.append(System.lineSeparator());
        }
        return result.toString();
    }

    @Contract(pure = true)
    private String pad(@NotNull String word, int newLength){
        StringBuilder wordB = new StringBuilder();
        wordB.append(word);

        while (wordB.length() < newLength) {
            wordB.append(" ");
        }
        return wordB.toString();
    }
}

