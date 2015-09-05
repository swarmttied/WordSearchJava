/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordsearch;

/**
 *
 * @author Gio
 */
public enum WordDirection {
    LR("Left Right"),
    RL("Right Left"),
    U("Up"),
    DUL("Diagonal Up Left"),
    DUR("Diagonal Up Right"),
    DDL("Diagonal Down Left"),
    DDR("Diagonal Down Right");
    
    
    private String _fullname;
    WordDirection(String fullname){
        _fullname = fullname;
    } 

    /**
     * @return the full name of the enumeration value
     */
    public String getFullname() {
        return _fullname;
    }       
}
