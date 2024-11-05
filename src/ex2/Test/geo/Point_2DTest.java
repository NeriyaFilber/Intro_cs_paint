package ex2.Test.geo;

import ex2.ex2.Ex2_Const;
import ex2.geo.Point_2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Point_2DTest {
    private static Point_2D p1;
    private static Point_2D p2;
    private static Point_2D p3;

    @BeforeEach
    void setUp() {
        p1 = new Point_2D(5.5,3.5);
        p2 = new Point_2D(1, 1);
        p3 = Point_2D.ORIGIN;
    }

    @Test
    void x() {
        assertEquals(5.5,p1.x());
        assertEquals(1,p2.x());
        assertEquals(0,p3.x());
    }

    @Test
    void y() {
        assertEquals(3.5,p1.y());
        assertEquals(1,p2.y());
        assertEquals(0,p3.y());
    }

    @Test
    void ix() {
        assertEquals(5,p1.ix());
    }

    @Test
    void iy() {
        assertEquals(3, p1.iy());
    }

    @Test
    void add() {
        p3 = p3.add(p2);
        assertEquals(p2,p3);
    }

    @Test
    void testToString() {
        String s1 = p1.toString();
        Point_2D p4 = new Point_2D(s1);
        assertEquals(p1,p4);
    }

    @Test
    void distance() {
        Point_2D p4 = new Point_2D(4,3);
        assertEquals(5, p3.distance(p4));
    }

    @Test
    void testDistance() {
        Point_2D p4 = new Point_2D(5,4);
        assertEquals(5, p2.distance(p4));
    }

    @Test
    void testEquals() {
        Point_2D p4 = new Point_2D(5.5,3.5);
        assertTrue(p4.equals(p1));
        assertFalse(p4.equals(p2));
    }

    @Test
    void close2equals() {
        assertTrue(p2.close2equals(new Point_2D(0.9999999,0.9999999), Ex2_Const.EPS));
        assertFalse(p1.close2equals(new Point_2D(0.9999999,0.9999999), Ex2_Const.EPS));
    }

    @Test
    void vector() {
        Point_2D p4 = new Point_2D(2,2);
        assertEquals(p2, p2.vector(p4));
    }

    @Test
    void move() {
        Point_2D p4 = new Point_2D(2,2);
        p2.move(p2);
        assertEquals(p4 , p2);
    }

    @Test
    void scale() {
        Point_2D p4 = new Point_2D(2,2);
        p4.scale(p3, 0.5);
        assertEquals(p4, p2);
    }

    @Test
    void rotate() {
        Point_2D p4 = new Point_2D(0,2);
        Point_2D p5 = new Point_2D(2,0);
        p4.rotate(p3, 90);
        assertTrue(p4.close2equals(p4, Ex2_Const.EPS));
    }
}