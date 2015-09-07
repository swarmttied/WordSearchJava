/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordsearch;

import java.util.Dictionary;
import java.util.EnumSet;
import java.util.Objects;

/**
 *
 * @author gio
 */
public class CharMatrix {
        
    // NOTE: Nested single-member interface is Java's equivalent to 
    //      C# delegate
    
    interface IndexOffsetter {
        int move(int index);        
    }
    
    interface WidthIndexOffsetter {
        int move(int index, int width);
    }    
    
    IndexOffsetter _toRight = (int index) -> ++index;
    IndexOffsetter _toLeft = (int index) -> --index;    
    IndexOffsetter _fixN = (int index) -> index;
    WidthIndexOffsetter _toDown = (int index, int width) -> index + width;
    WidthIndexOffsetter _toUp = (int index, int width) -> index - width;
    WidthIndexOffsetter _fixM = (int index, int width) -> index;   
    
    
    // The physical representation of the MxN char matrix
    char[] _flattenedMatrix = null;
        
    // Contains the indices of the alphabet chars in the
    // physical char array. This will be used as starting point for the search
    Dictionary<Character, Integer[]> _firstCharIndices = null;
    
    public CharMatrix(char[] chars, int width) {
        Objects.requireNonNull(chars);
      
        if (chars.length == 0)
            throw new IllegalArgumentException("chars cannot be empty");
                
        if (width <= 0)
            throw new IllegalArgumentException("width must be greater than zero");
        
        if (chars.length % width > 0)
            throw new IllegalArgumentException("Char array is not implied as matrix. Its length should be divisible by the width arguement.");
        
        _flattenedMatrix = new String(chars).toUpperCase().toCharArray();
        setWidth(width);
        setHeight(chars.length/width);
        
        _firstCharIndices = LogicalMatrixUtil.findAlphabetCharIndices(new String(_flattenedMatrix));           
    }
    
    private int _width;
    
    private int _height;

    /**
     * @return the Width
     */
    public int getWidth() {
        return _width;
    }

    /**
     * @param width the _width to set
     */
    final void setWidth(int width) {
        this._width = width;
    }

    /**
     * @return the Height
     */
    public int getHeight() {
        return _height;
    }

    /**
     * @param height the _height to set
     */
    final void setHeight(int height) {
        this._height = height;
    }
    
    public WordLocation findWord(String word) {
        
        String noSpaceWord = word.toUpperCase().replace(" ", "");
        int wordLen = noSpaceWord.length();
        
        // Get indices of first char
        char startChar = noSpaceWord.charAt(0);
        if (_firstCharIndices.get(startChar)==null)
            return null;
        Integer[] indices = _firstCharIndices.get(startChar);
        
        for(int index: indices) {
            Coordinate coordinate = LogicalMatrixUtil.getMatrixCoordinate(index, getWidth());
            EnumSet<Direction> directions = LogicalMatrixUtil.getPossibleDirections
                            (coordinate, wordLen, getWidth(), getHeight());
            
            // Right?
            if (directions.contains(Direction.RIGHT)) {                
                if (wordExists(noSpaceWord, index, getWidth(), _fixM, _toRight)) {
                    return new WordLocation(word, coordinate, WordDirection.LR);
                }
                // DownRight?
                if (directions.contains(Direction.DOWN)) {
                    if (wordExists(noSpaceWord, index, getWidth(), _toDown, _toRight)) {
                        return new WordLocation(word, coordinate, WordDirection.DDR);
                    }
                }
                // ToUpRight?
                if (directions.contains(Direction.UP)) {
                    if (wordExists(noSpaceWord, index, getWidth(), _toUp, _toRight)) {
                        return new WordLocation(word, coordinate, WordDirection.DUR);
                    }
                }
            }
            // Left?
            if (directions.contains(Direction.LEFT)) {
                if (wordExists(noSpaceWord, index, getWidth(), _fixM, _toLeft)) {
                    return new WordLocation(word, coordinate, WordDirection.RL);
                }
                // DownLeft?
                if (directions.contains(Direction.DOWN)) {
                    if (wordExists(noSpaceWord, index, getWidth(), _toDown, _toLeft)) {
                        return new WordLocation(word, coordinate, WordDirection.DDL);
                    }
                }
                // ToUpLeft?
                if (directions.contains(Direction.UP)) {
                    if (wordExists(noSpaceWord, index, getWidth(), _toUp, _toLeft)) {
                        return new WordLocation(word, coordinate, WordDirection.DUL);
                    }
                }
            }
            // Up?
            if (directions.contains(Direction.UP)) {
                 if (wordExists(noSpaceWord, index, getWidth(), _toUp, _fixN))
                    return new WordLocation(word, coordinate, WordDirection.U);
            }
            // Down?
            if (directions.contains(Direction.DOWN)) {
                if (wordExists(noSpaceWord, index, getWidth(), _toDown, _fixN))
                    return new WordLocation(word, coordinate, WordDirection.D);
            }            
        }
        
        return null;        
    }
    
    boolean wordExists(String wordToSearch, int index, int matrixWidth,
            WidthIndexOffsetter mOffsetter, IndexOffsetter nOffsetter) {
        
        int lenWord = wordToSearch.length();
        
        for (int i=1; i< lenWord; i++) {
            index = mOffsetter.move(index, matrixWidth);
            index = nOffsetter.move(index);
            
            if (_flattenedMatrix[index] != wordToSearch.charAt(i))
                return false;
        }
        
        return true;        
    }
    
}
