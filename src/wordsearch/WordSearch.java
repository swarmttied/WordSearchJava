/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordsearch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.System.out;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author Gio
 */
public class WordSearch {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        if (args.length < 2)
        {
            out.println(_hintMessage);
            return;          
        }
        
        Date startTime = new Date();
        
        String puzzlePath = args[0];
        String listPath = args[1];
        
        File puzzleFile = new File(puzzlePath);
        if (!puzzleFile.exists() || puzzleFile.isDirectory()) {
            out.format("ERROR! Puzzle file %s is not found.", puzzlePath);
            return;
        }
        File listFile = new File(listPath);
        if (!listFile.exists() || listFile.isDirectory()) {
            out.format("ERROR! List file %s is not found.", listPath);
            return;
        }
        
        try {
            Tuple2<String, Integer> input = getChars(puzzlePath);
            String[] list = getWordList(listPath);
            
            CharMatrix puzzle = new CharMatrix(input.a.toCharArray(), input.b);
            
            for(String word: list) {
                WordLocation res = puzzle.findWord(word);
                if (res != null) {
                    out.println(res);
                }
            }
            
        }
        catch (Exception ex) {
            long elapsed = (new Date()).getTime() - startTime.getTime();
            out.format("\n\nRunning time: %d ms.\n", startTime);
        }     
        long elapsed = (new Date()).getTime() - startTime.getTime();
        out.format ("\n\nRunning time: %d ms.\n", elapsed);
    }
    
    static Tuple2<String,Integer> getChars(String source) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(source));               
        String line = reader.readLine();     
        int matrixWidth = line.length();
        String nextLine = null;
        while ((nextLine = reader.readLine())!= null){
            line += nextLine;
        }

         Tuple2<String, Integer> result = new Tuple2<>();
         result.a = line.replace("\"", "");
         result.b = matrixWidth;
         
         return result;      
    }

    static String[] getWordList(String source) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(source));               
        Vector<String> list = new Vector<>();
        String line = null;
        while ((line = reader.readLine()) != null)
        {
            list.add(line);
        }

        return list.toArray(new String[list.size()]);        
    }
    
    static final String _hintMessage = "Usage: wordpuzzle <puzzle path> <list path>\n" +
                                        "Where:\n" +
                                        "           <puzzle path> is a text file containing the mxn chars puzzle\n" +
                                        "           <list path>   is a text file with list of words to find\n" +
                                        "\n"+

                                        "Examples:    wordpuzzle source.txt list.txt\n" +
                                        "             wordpuzzle c:\\puzzle.txt c:\\search.txt";
    
}
