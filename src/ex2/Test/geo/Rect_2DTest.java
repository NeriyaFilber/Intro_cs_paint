package ex2.Test.geo;

import ex2.ex2.Ex2_Const;
import ex2.geo.GeoShape;
import ex2.geo.Point_2D;
import ex2.geo.Rect_2D;
import ex2.geo.Triangle_2D;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Rect_2DTest {
    private Point_2D p1 = new Point_2D(2, 2);
    private Point_2D p2 = new Point_2D(5, 3);
    private Rect_2D r1;

    @BeforeEach
    void setUp(){
        r1 = new Rect_2D(Point_2D.ORIGIN, new Point_2D(4, 3));
    }

    @Test
    public void testGetAllPoints() {
        Point_2D[] myArr = r1.getAllPoints();
        assertEquals(0, myArr[0].x(), Ex2_Const.EPS);
        assertEquals(0, myArr[0].y(), Ex2_Const.EPS);
        assertEquals(0, myArr[1].x(), Ex2_Const.EPS);
        assertEquals(3, myArr[1].y(), Ex2_Const.EPS);
        assertEquals(4, myArr[2].x(), Ex2_Const.EPS);
        assertEquals(3, myArr[2].y(), Ex2_Const.EPS);
        assertEquals(4, myArr[3].x(), Ex2_Const.EPS);
        assertEquals(0, myArr[3].y(), Ex2_Const.EPS);
    }



    @Test
    public void testContains() {
        assertTrue(r1.contains(p1));
        assertFalse(r1.contains(p2));
    }

    @Test
    public void testArea() {
        assertEquals(4 * 3, r1.area());
        assertNotEquals(15, r1.area());
        assertNotEquals(11.99999999, r1.area());
    }

    @Test
    public void testPerimeter() {
        assertEquals(14, r1.perimeter());
        assertNotEquals(13.999999, r1.perimeter());
    }

    @Test
    void translate() {
        Rect_2D r2 = new Rect_2D(p1, p2);
        r2.translate(new Point_2D(1,1));
        Point_2D[] myArr = r2.getAllPoints();
        assertEquals(3, myArr[0].x(), Ex2_Const.EPS);
        assertEquals(3, myArr[0].y(), Ex2_Const.EPS);
        assertEquals(3, myArr[1].x(), Ex2_Const.EPS);
        assertEquals(4, myArr[1].y(), Ex2_Const.EPS);
        assertEquals(6, myArr[2].x(), Ex2_Const.EPS);
        assertEquals(4, myArr[2].y(), Ex2_Const.EPS);
        assertEquals(6, myArr[3].x(), Ex2_Const.EPS);
        assertEquals(3, myArr[3].y(), Ex2_Const.EPS);
    }

    @Test
    void copy() {
        Point_2D[] myArr = r1.getAllPoints();
        Rect_2D r2 = (Rect_2D) r1.copy();
        GeoShape r3 = r1.copy();
        assertEquals(0, myArr[0].x(), Ex2_Const.EPS);
        assertEquals(0, myArr[0].y(), Ex2_Const.EPS);
        assertEquals(0, myArr[1].x(), Ex2_Const.EPS);
        assertEquals(3, myArr[1].y(), Ex2_Const.EPS);
        assertEquals(4, myArr[2].x(), Ex2_Const.EPS);
        assertEquals(3, myArr[2].y(), Ex2_Const.EPS);
        assertEquals(4, myArr[3].x(), Ex2_Const.EPS);
        assertEquals(0, myArr[3].y(), Ex2_Const.EPS);
        assertNotSame(r1, r3);
        assertFalse(r2 == r1);
        assertNotSame(r2,r3);
    }

    @Test
    public void testScale() {
        r1.scale(Point_2D.ORIGIN, 0.5);
        Point_2D[] myArr = r1.getAllPoints();
        assertEquals(0, myArr[0].x(), Ex2_Const.EPS);
        assertEquals(0, myArr[0].y(), Ex2_Const.EPS);
        assertEquals(0, myArr[1].x(), Ex2_Const.EPS);
        assertEquals(1.5, myArr[1].y(), Ex2_Const.EPS);
        assertEquals(2, myArr[2].x(), Ex2_Const.EPS);
        assertEquals(1.5, myArr[2].y(), Ex2_Const.EPS);
        assertEquals(2, myArr[3].x(), Ex2_Const.EPS);
        assertEquals(0, myArr[3].y(), Ex2_Const.EPS);
    }

    @Test
    void rotate() {
        Rect_2D r2 = new Rect_2D(Point_2D.ORIGIN, new Point_2D(3,3));
        r2.rotate(Point_2D.ORIGIN, 90);
        Point_2D[] myArr = r2.getAllPoints();
        assertEquals(0, myArr[0].x(), Ex2_Const.EPS);
        assertEquals(0, myArr[0].y(), Ex2_Const.EPS);
        assertEquals(-3, myArr[1].x(), Ex2_Const.EPS);
        assertEquals(0, myArr[1].y(), Ex2_Const.EPS);
        assertEquals(-3, myArr[2].x(), Ex2_Const.EPS);
        assertEquals(3, myArr[2].y(), Ex2_Const.EPS);
        assertEquals(0, myArr[3].x(), Ex2_Const.EPS);
        assertEquals(3, myArr[3].y(), Ex2_Const.EPS);
    }

    @Test
    public void testEquals(){
        Rect_2D r2 = new Rect_2D(Point_2D.ORIGIN, new Point_2D(4,3));
        Rect_2D r3 = new Rect_2D(new Point_2D(0,1), new Point_2D(4,5));
        assertEquals(r1,r2);
        assertNotEquals(r1, r3);
    }

    @Test
    void testToString(){
        Rect_2D r2 = new Rect_2D("0,0,0,3,4,3,4,0");
        assertEquals(r1,r2);
        String s1 = r1.toString();
        String s2 = r2.toString();
        Rect_2D r3 = new Rect_2D(s1);
        Rect_2D r4 = new Rect_2D(s2);
        assertEquals(r3,r4);
    }
}