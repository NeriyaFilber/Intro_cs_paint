package ex2.Test.geo;

import ex2.ex2.Ex2_Const;
import ex2.geo.*;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Polygon_2DTest {
    private static Point_2D p1 = new Point_2D(1,0);
    private static Point_2D p2 = new Point_2D(0,3);
    private static Point_2D p3 = new Point_2D(3,5);
    private static Point_2D p4 = new Point_2D(4,3);
    private static Point_2D p5 = new Point_2D(2,3);
    private static Point_2D p6 = new Point_2D(2,1);
    private static Point_2D p7 = new Point_2D(4,1);
    private static Point_2D p9 = new Point_2D(4,0);

    public static Polygon_2D po1 = new Polygon_2D();
    Point_2D[] pArr = null;

    @BeforeEach
    void setUp(){
        pArr = new Point_2D[]{p1, p2, p3, p4, p5, p6, p7};
        for (int i = 0; i < pArr.length; i++) {
            po1.add(pArr[i]);
        }

    }

    @Test
    public void testGetAllPoints() {
        Point_2D[] po1Arr = po1.getAllPoints();
        for (Point_2D point2D : po1Arr) {
            assertEquals(point2D.x(), point2D.x(), Ex2_Const.EPS);
            assertEquals(point2D.y(), point2D.y(), Ex2_Const.EPS);
        }
    }

    @Test
    public void testAdd() {
        po1.add(p1);
        Point_2D[] po1Arr = po1.getAllPoints();
        assertEquals(p1.x(), po1Arr[0].x(), Ex2_Const.EPS);
        assertEquals(p1.y(), po1Arr[0].y(), Ex2_Const.EPS);
        assertFalse(p1 == po1Arr[0]);

    }

    @Test
    void testToString() {
        String a = po1.toString();
        System.out.println(a);
        Polygon_2D po2 = new Polygon_2D(a);
        assertEquals(po1,po2);
    }

    @Test
    public void testContains() {
        assertTrue(po1.contains(new Point_2D(1,3)));
        assertTrue(po1.contains(new Point_2D(2,2)));
        assertFalse(po1.contains(new Point_2D(3,2)));
        assertTrue(po1.contains(new Point_2D(4,3)));
        assertTrue(po1.contains(new Point_2D(2.9,4.9)));
        assertTrue(po1.contains(new Point_2D(2,2)));
        assertTrue(po1.contains(new Point_2D(3.9999999, 2.9999999)));
    }

    @Test
    public void TestArea() {
        Triangle_2D t1 = new Triangle_2D(p1,p2,p5);
        Triangle_2D t2 = new Triangle_2D(p2,p3,p4);
        Triangle_2D t3 = new Triangle_2D(p5,p6,p1);
        Triangle_2D t4 = new Triangle_2D(p6,p7,p1);
        double t1Ar = t1.area();
        double t2Ar = t2.area();
        double t3Ar = t3.area();
        double t4Ar = t4.area();
        Assert.assertEquals(t1Ar + t2Ar + t3Ar + t4Ar, po1.area(), Ex2_Const.EPS);
        Assert.assertNotEquals(1 + t2Ar + t3Ar + t4Ar, po1.area(), Ex2_Const.EPS);
    }

    @Test
    public void testPerimeter() {
        Polygon_2D po2 = new Polygon_2D();
        po2.add(new Point_2D(0,0));
        po2.add(new Point_2D(0,4));
        po2.add(new Point_2D(4,4));
        po2.add(new Point_2D(4,0));
        Assert.assertEquals(16, po2.perimeter(), Ex2_Const.EPS);
        Assert.assertNotEquals(15.99999, po2.perimeter(), Ex2_Const.EPS);
    }

    @Test
    public void testTranslate() {
        for (int i = 0; i < pArr.length; i++) {
            po1.add(pArr[i]);
        }
        po1.translate(new Point_2D(1,1));
        Point_2D[] po1Arr = po1.getAllPoints();
        for (int i = 0; i < pArr.length; i++) {
            Assert.assertEquals(pArr[i].x() + 1, po1Arr[i].x(), Ex2_Const.EPS);
            Assert.assertEquals(pArr[i].y() + 1, po1Arr[i].y(), Ex2_Const.EPS);
        }
    }

    @Test
    void copy() {
        Point_2D[] po1Arr = po1.getAllPoints();
        GeoShape po2 = po1.copy();
        pArr = ((Polygon_2D) po2).getAllPoints();
        for (int i = 0; i < pArr.length; i++) {
            assertFalse(pArr[i] == po1Arr [i]);
            assertEquals(pArr[i].x(), po1Arr[i].x(), Ex2_Const.EPS);
            assertEquals(pArr[i].y(), po1Arr[i].y(), Ex2_Const.EPS);
        }
        assertFalse(po1 == po2);
    }

    @Test
    public void testScale() {
       Polygon_2D po2 = new Polygon_2D();
       po2.add(Point_2D.ORIGIN);
       po2.add(p2);
       po2.add(p4);
       po2.add(p9);
       po2.scale(Point_2D.ORIGIN, 0.5);
       Point_2D[] myArr = po2.getAllPoints();
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
        Polygon_2D po2 = new Polygon_2D();
        po2.add(Point_2D.ORIGIN);
        po2.add(p2);
        po2.add(p4);
        po2.add(p9);
        po2.rotate(Point_2D.ORIGIN,90);
        Point_2D[] myArr = po2.getAllPoints();
        assertEquals(0, myArr[0].x(), Ex2_Const.EPS);
        assertEquals(0, myArr[0].y(), Ex2_Const.EPS);
        assertEquals(-3, myArr[1].x(), Ex2_Const.EPS);
        assertEquals(0, myArr[1].y(), Ex2_Const.EPS);
        assertEquals(-3, myArr[2].x(), Ex2_Const.EPS);
        assertEquals(4, myArr[2].y(), Ex2_Const.EPS);
        assertEquals(0, myArr[3].x(), Ex2_Const.EPS);
        assertEquals(4, myArr[3].y(), Ex2_Const.EPS);
    }

    @Test
    public void testEquals(){
        for (int i = 0; i < pArr.length; i++) {
            po1.add(pArr[i]);
        }
        Polygon_2D po2 = new Polygon_2D();
        Point_2D[] pArr1 = {p2,p6,p3,p1,p4,p5,p7};
        for (int i = 0; i < pArr1.length; i++) {
            po2.add(pArr1[i]);
        }
        Polygon_2D po3 = new Polygon_2D();
        Point_2D[] pArr3 = {p2,p3,p4,p5,p6,p7,p1};
        for (int i = 0; i < pArr3.length; i++) {
            po3.add(pArr3[i]);
        }
        Polygon_2D po4 = new Polygon_2D();
        Point_2D[] pArr4 = {p7,p6,p5,p4,p3,p2,p1};
        for (int i = 0; i < pArr4.length; i++) {
            po4.add(pArr4[i]);
        }
        assertFalse(po1.equals(po2));
        assertTrue(po1.equals(po3));
        assertTrue(po1.equals(po1));
        assertTrue(po1.equals(po4));
    }

}