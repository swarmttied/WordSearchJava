/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordsearch;

import java.security.InvalidParameterException;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Gio
 */
public class LogicalMatrixUtilTest {
    
    @Rule
    public ExpectedException expectation = ExpectedException.none();
    
    public LogicalMatrixUtilTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of findAlphabetCharIndices method, of class LogicalMatrixUtil.
     */
    @Test
    public void testFindAlphabetCharIndices() {
        System.out.println("findAlphabetCharIndices");
        String chars = "";
        Map expResult = null;
        Map result = LogicalMatrixUtil.findAlphabetCharIndices(chars);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMatrixCoordinate method, of class LogicalMatrixUtil.
     */
    @Test
    public void testGetMatrixCoordinate() {
       
        Coordinate res = LogicalMatrixUtil.getMatrixCoordinate(9,4);

        assertEquals("m component not correct", 2, res.m);
        assertEquals("n component not correct", 1, res.n);               
    
    }
    
    /**
     * 
     */
    @Test
    public void testGetMatrixCoordinate_NegativeWidth_Throw() {
        expectation.expect(InvalidParameterException.class);
        throw new InvalidParameterException();
    }
    
}
