/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordsearch;

/**
 *
 * @author the-r
 */
public class WordLocation {
    public WordLocation(String word, Coordinate coordinate, WordDirection direction) {
        _word = word;
        _coordinate = coordinate;
        _direction = direction;
    }    
    
    private Coordinate _coordinate;

    /**
     * @return the _coordinate
     */
    public Coordinate getCoordinate() {
        return _coordinate;
    }

    private String _word;
    
    /**
     * @return the _word
     */
    public String getWord() {
        return _word;
    }
    
    private WordDirection _direction;

    /**
     * @return the _direction
     */
    public WordDirection getDirection() {
        return _direction;
    }
}
