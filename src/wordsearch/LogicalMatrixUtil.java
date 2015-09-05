/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordsearch;
import java.security.InvalidParameterException;
import java.util.*;
/**
 *
 * @author Gio
 */
public class LogicalMatrixUtil {
    public static Map<Character, Integer[]> findAlphabetCharIndices(String chars){
        Map<Character, Integer[]> locations = new Hashtable<Character, Integer[]>();
        
        for (Character c = 'A'; c<='Z'; c++) {
            int charIndex = -1;
            
            Vector<Integer> indices = new Vector<Integer>();
            do {
                charIndex = chars.indexOf(c, ++charIndex);
                indices.add(charIndex);
            } while (charIndex >- -1);
            
            if (indices.size() > 1) {
                indices.remove(-1);
                locations.put(c,(Integer[])indices.toArray());
            }            
        }
        
        return locations;
    }
    
    /**
     * 
     * @param index The zero-based index of the character in the array
     * @param matrixWidth The logical width of the matrix represented by the array
     * @return The (m,n) coordinate of the index in the logical matrix
     */
    public static Coordinate getMatrixCoordinate(int index, int matrixWidth) {        
        if (index < 0)
            throw new InvalidParameterException("index cannot be negative");
        
        if (matrixWidth < 0)
            throw new InvalidParameterException("matrixWidth cannot be negative");
        
        Coordinate coordinate = new Coordinate();
        
        coordinate.m = index / matrixWidth;
        coordinate.n = index % matrixWidth;
        
        return coordinate;             
    }

}
