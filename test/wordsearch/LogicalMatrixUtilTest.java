/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordsearch;

import java.security.InvalidParameterException;
import java.util.Dictionary;
import java.util.EnumSet;
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
    public ExpectedException _expectation = ExpectedException.none();
    
    String _chars = "ABAD";
    
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
    public void findAlphabetCharIndices() {
       Dictionary<Character, Integer[]> result = LogicalMatrixUtil.findAlphabetCharIndices(_chars);
       
       assertEquals("Result count incorrect", 3, result.size());
       assertEquals("Count for 'A' not correct.", 2, result.get('A').length);
       assertEquals("Wrong index for 'A'", 0, (int)result.get('A')[0]);
       assertEquals("Wrong index for 'A'", 2, (int)result.get('A')[1]);
       assertEquals("Count for 'B' not correct.", 1, result.get('B').length);
       assertEquals("Wrong index for 'B'", 1, (int)result.get('B')[0]);
       assertEquals("Count for 'D' not correct.", 1, result.get('D').length);
       assertEquals("Wrong index for 'D'", 3, (int)result.get('D')[0]);
       assertTrue("'C' index was found when it should not be.", result.get('C') == null);     
    }

    /**
     * Test of getMatrixCoordinate method, of class LogicalMatrixUtil.
     */
    @Test
    public void getMatrixCoordinate() {
       
        Coordinate res = LogicalMatrixUtil.getMatrixCoordinate(9,4);

        assertEquals("m component not correct", 2, res.m);
        assertEquals("n component not correct", 1, res.n);               
    
    }
    
    /**
     * 
     */
    @Test
    public void getMatrixCoordinate_NegativeWidth_Throws() {
        _expectation.expect(InvalidParameterException.class);
        LogicalMatrixUtil.getMatrixCoordinate(0, -1);
    }
    
    @Test
    public void getMatrixCoordinate_NegativeIndex_Throws() {
        _expectation.expect(InvalidParameterException.class);
        LogicalMatrixUtil.getMatrixCoordinate(-1, 3);
    }
    
    @Test
    public void getIndexFromMxN() {
        Coordinate mn = new Coordinate();
        mn.m = 1;
        mn.n = 2;
        
        int res = LogicalMatrixUtil.getIndexFromMxN(mn, 3);
        
        
        // We assume a mx3 matrix where m >= 2;
        // (1,2) should pertain to element 6;
        assertEquals(5, res);        
    }
    
    @Test
    public void getPossibleDirections_RightDownDiagonal() {
        Coordinate coordinate = new Coordinate();
        EnumSet<Direction> result = LogicalMatrixUtil.getPossibleDirections
                                                    (coordinate, 4, 6, 6);
        
        assertTrue(result.contains(Direction.RIGHT));
        assertTrue(result.contains(Direction.DOWN));
        assertFalse(result.contains(Direction.UP));
        assertFalse(result.contains(Direction.LEFT));       
        
    }
    
    @Test
    public void getPossibleDirections_Center() {
        Coordinate coor = new Coordinate();
        coor.m = 2; coor.n = 2;
        EnumSet<Direction> result = LogicalMatrixUtil.getPossibleDirections(coor, 
                                                    3, 6, 6);
        
        assertTrue(result.contains(Direction.RIGHT));
        assertTrue(result.contains(Direction.DOWN));
        assertTrue(result.contains(Direction.UP));
        assertTrue(result.contains(Direction.LEFT));
    }
    
    @Test 
    public void getPossibleDirections_NoRight_NoDown() {
        Coordinate coor = new Coordinate();
        coor.m = 4; coor.n = 4;
        EnumSet<Direction> result = LogicalMatrixUtil.getPossibleDirections(coor, 
                                                    3, 6, 6);
        
        assertFalse(result.contains(Direction.RIGHT));
        assertFalse(result.contains(Direction.DOWN));
        assertTrue(result.contains(Direction.UP));
        assertTrue(result.contains(Direction.LEFT));
    }
    
    @Test 
    public void getPossibleDirections_NoDown() {
        Coordinate coor = new Coordinate();
        coor.m = 4; coor.n = 2;
        EnumSet<Direction> result = LogicalMatrixUtil.getPossibleDirections(coor, 
                                                    3, 6, 6);
        
        assertTrue(result.contains(Direction.RIGHT));
        assertFalse(result.contains(Direction.DOWN));
        assertTrue(result.contains(Direction.UP));
        assertTrue(result.contains(Direction.LEFT));
    }
    
    @Test
    public void getPossibleDirections_Mone_TooLong() {
        Coordinate coor = new Coordinate();
        coor.m = 4; coor.n = 2;
        EnumSet<Direction> result = LogicalMatrixUtil.getPossibleDirections(coor, 
                                                    6, 6, 6);
        
        assertFalse(result.contains(Direction.RIGHT));
        assertFalse(result.contains(Direction.DOWN));
        assertFalse(result.contains(Direction.UP));
        assertFalse(result.contains(Direction.LEFT));
    } 
    
    @Test
    public void ctorCharMatrix_NullChars_ThrowsNullPointerEx() {
        _expectation.expect(NullPointerException.class);
        new CharMatrix(null,4);
    }
    
    @Test
    public void ctorCharMatrix_EmptyChars_ThrowsIllegalArgs() {
        _expectation.expect(IllegalArgumentException.class);
        new CharMatrix(new char[]{}, 4);
    }
    
    @Test
    public void ctorCharMatrix_WidthZeroOrLess_ThrowsIllegalArgs() {
        _expectation.expect(IllegalArgumentException.class);
        new CharMatrix(new char[]{'C'}, 0);
    }
    
    @Test
    public void ctorCharMatrix_NotImpliedAsMatrix_ThrowsIllegalArgs() {
         _expectation.expect(IllegalArgumentException.class);
        new CharMatrix(new char[]{'a', 'b', 'c', 'd' }, 3);
    }
    
    @Test
    public void FindWord_Gio_Right()
    {
        char[] chars = new char[] { 'g', 'I', 'o', 'A', 'G', 'O', 'b', 'C', 'D' };

        CharMatrix target = new CharMatrix(chars, 3);
        WordLocation res = target.findWord("GIO");

        assertEquals("getWidth",3, target.getWidth());
        assertEquals("getHeight",3, target.getHeight());
        assertNotNull("FindWord result is null.", res);
        assertEquals(res.getWord(), "GIO");
        assertEquals(res.getCoordinate().m, 0);
        assertEquals(res.getCoordinate().n, 0);
        assertEquals(res.getDirection(), WordDirection.LR);
    }
    
}