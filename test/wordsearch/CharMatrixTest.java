/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordsearch;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author the-r
 */
public class CharMatrixTest {
    
    public CharMatrixTest() {
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

     @Test
    public void Ctor_With_MxN_Matrix_Should_Set_Width_And_Height()
    {
        char[] chars = new char[] { 'a', 'b', 'c', 'd','e','f' };
        int width = 3;
        CharMatrix target = new CharMatrix(chars, width);

        assertEquals("getWidth()", width, target.getWidth());
        assertEquals("getHeight()", 2, target.getHeight());
    }

    @Test
    public void Ctor_With_1xN_Matrix()
    {
        char[] chars = new char[] { 'a', 'b', 'c', 'd', };
        int width = 4;
        CharMatrix target = new CharMatrix(chars, width);

        assertEquals("getWidth()",width, target.getWidth());
        assertEquals("getHeight()",1, target.getHeight());
    }

    @Test
    public void Ctor_With_Mx1_Matrix()
    {
        char[] chars = new char[] { 'a', 'b', 'c', 'd', };
        int width = 1;
        CharMatrix target = new CharMatrix(chars, width);

        assertEquals("getWidth()", width, target.getWidth());
        assertEquals("getHeight()", 4, target.getHeight());
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
    
    @Test
        public void FindGio_UpLeft()
        {
            char[] chars = new char[] { 'o', 'R', 'o', 'A', 'I', 'O', 'b', 'C', 'G' };

            CharMatrix target = new CharMatrix(chars, 3);
            WordLocation res = target.findWord("GIO");

            assertEquals("getWidth()", 3, target.getWidth());
            assertEquals("getHeight()", 3, target.getHeight());
            assertNotNull("FindWord result is null.", res);
            assertEquals(res.getWord(), "GIO");
            assertEquals(res.getCoordinate().m, 2);
            assertEquals(res.getCoordinate().n, 2);
            assertEquals(res.getDirection(), WordDirection.DUL);
        }

        @Test
        public void FindVan_NotFound()
        {
            char[] chars = new char[] { 'o', 'R', 'o', 'A', 'I', 'O', 'b', 'C', 'G' };

            CharMatrix target = new CharMatrix(chars, 3);
            WordLocation res = target.findWord("VAN");

            assertEquals("getWidth()", 3, target.getWidth());
            assertEquals("getHeight()",3, target.getHeight());
            assertNull("FindWord result",res);
        }

        @Test
        public void FindVan_FirstLetterPresent_NotFound()
        {
            char[] chars = new char[] { 'o', 'R', 'o', 'A', 'I', 'O', 'b', 'C', 'G' };

            CharMatrix target = new CharMatrix(chars, 3);
            WordLocation res = target.findWord("GAN");

            assertEquals("getWidth()", 3, target.getWidth());
            assertEquals("getHeight()", 3, target.getHeight());
            assertNull("FindWord result",res);
        }

        static char[] puzzle = new char[]{'Z','R','Q','K','t',
                                          'B','L','T','A','L',
                                          'O','L','K','Z','n',
                                          'K','L','A','B','c',
                                          'h','J','X','a','R',
                                          'z','o','1','l','K'};

        @Test
        public void FindKat_UpRight_Down()
        {
            CharMatrix target = new CharMatrix(puzzle, 5);

            WordLocation res = target.findWord("KAT");

            assertEquals(WordDirection.DUR, res.getDirection());
            assertEquals("(2,2)", res.getCoordinate().toString());
        }

        @Test
        public void Find_UpLeft()
        {
            CharMatrix target = new CharMatrix(puzzle, 5);

            WordLocation res = target.findWord("LXLO");

            assertEquals("(5,3)", res.getCoordinate().toString());
            assertEquals(WordDirection.DUL, res.getDirection());
            
        }

        @Test
        public void Find_DownLeft()
        {
            CharMatrix target = new CharMatrix(puzzle, 5);

            WordLocation res = target.findWord("nbxo");

            assertEquals("(2,4)", res.getCoordinate().toString());
            assertEquals(WordDirection.DDL, res.getDirection());

        }

        @Test
        public void Find_Left()
        {
            CharMatrix target = new CharMatrix(puzzle, 5);

            WordLocation res = target.findWord("cbal");

            assertEquals("(3,4)", res.getCoordinate().toString());
            assertEquals(WordDirection.RL, res.getDirection());

        }
    
}
