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
    public static Dictionary<Character, Integer[]> findAlphabetCharIndices(String chars){
        Dictionary<Character, Integer[]> locations = new Hashtable<Character, Integer[]>();
        
        for (Character c = 'A'; c<='Z'; c++) {
            int charIndex = -1;
            
            Vector<Integer> indices = new Vector<Integer>();            
            do {
                charIndex = chars.indexOf(c, ++charIndex);
                indices.add(charIndex);
            } while (charIndex > -1);
            
            if (indices.size() > 1) {
                indices.removeElement(-1);
                locations.put(c,indices.toArray(new Integer[indices.size()]));
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
    
    public static int getIndexFromMxN(Coordinate pos, int matrixWidth) {
        return (int)(pos.m * matrixWidth) + pos.n;
    }
    
    public static EnumSet<Direction> getPossibleDirections(Coordinate start,
            int elementsLength, int matrixWidth, int matrixHeight) {
        
        EnumSet<Direction> directions = EnumSet.noneOf(Direction.class);
        
        elementsLength--; // Remove the start char
        
        if (start.n + elementsLength <= matrixWidth-1)
            directions.add(Direction.RIGHT);
        if (start.n - elementsLength >= 0)
            directions.add(Direction.LEFT);
        if (start.m + elementsLength <= matrixHeight-1)
            directions.add(Direction.DOWN);
        if (start.m - elementsLength >= 0)
            directions.add(Direction.UP);
        
        return directions;
        
    }

}
