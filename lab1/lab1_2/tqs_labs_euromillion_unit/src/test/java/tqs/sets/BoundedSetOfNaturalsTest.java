/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tqs.sets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.function.Executable;
import tqs.sets.BoundedSetOfNaturals;

/**
 * @author ico0
 */
class BoundedSetOfNaturalsTest {
    private BoundedSetOfNaturals setA;
    private BoundedSetOfNaturals setB;
    private BoundedSetOfNaturals setC;


    @BeforeEach
    public void setUp() {
        setA = new BoundedSetOfNaturals(5);
        setB = BoundedSetOfNaturals.fromArray(new int[]{10, 20, 30, 40, 50, 60});
        setC = BoundedSetOfNaturals.fromArray(new int[]{50, 60});
    }

    @AfterEach
    public void tearDown() {
        setA = setB = setC = null;
    }

    @DisplayName("\n === Add and Size === \n")
    @Test
    public void testAddElement() {

        setA.add(5);
        System.out.println(setA.size());
        assertTrue(setA.contains(5), "add: added element not found in set.");
        assertEquals(1, setA.size(), "add: elements count not as expected.");

        assertThrows(IllegalArgumentException.class, () -> setA.add(5), "add: adding element should have throw an IllegalArgumentException.");
        assertEquals(1, setA.size(), "add: elements count not as expected.");

        assertThrows(IllegalArgumentException.class, () -> setA.add(-5), "add: adding element should have throw an IllegalArgumentException.");
        assertEquals(1, setA.size(), "add: elements count not as expected.");


    }

    //@Disabled("TODO revise to test the construction from invalid arrays")
    @DisplayName("\n === Test the construction from invalid arrays === \n")
    @Test
    public void testAddFromBadArray() {
        int[] set1 = new int[]{10, -20, -30}, set2 = new int[]{10, 20, 20};

        // must fail with exception

        Assertions.assertAll("test the construction from invalid arrays",
                (Executable) () -> assertThrows(IllegalArgumentException.class, () -> setA.add(set1)) ,
                (Executable) () -> assertThrows(IllegalArgumentException.class, () -> setA.add(set2))
        );

    }

    /*
    * From : Rafael Kauati
    *  simple tests for 2c
    *       |
    *       |
    *       v
     */
    @Test
    @DisplayName("\n === Test  for empty sets === \n")
    public void testSetIsEmpty() {
        assertTrue(setA.isEmpty());
        assertFalse(setB.isEmpty());
    }



}
