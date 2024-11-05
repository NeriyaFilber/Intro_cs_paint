package ex2.Test.geo;

import ex2.ex2.Ex2_Const;
import ex2.geo.GeoShape;
import ex2.geo.Point_2D;
import ex2.geo.Triangle_2D;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Triangle_2DTest {
    private Triangle_2D t1 = null;
    private Triangle_2D t2 = null;

    @BeforeEach
    void setUp(){
        t1 = new Triangle_2D(Point_2D.ORIGIN, new Point_2D(0, 5), new Point_2D(5,0));
        t2 = new Triangle_2D(new Point_2D(1,1), new Point_2D(2,2), new Point_2D(1,3));
    }

    @Test
    public void testGetAllPoints() {
        Point_2D[] allPoint = t1.getAllPoints();
        Point_2D[] tri1 = {Point_2D.ORIGIN, new Point_2D(0, 5), new Point_2D(5,0)};
        Assert.assertEquals(tri1[0].x(), allPoint[0].x(), Ex2_Const.EPS);
        Assert.assertEquals(tri1[0].y(), allPoint[0].y(), Ex2_Const.EPS);
        Assert.assertEquals(tri1[1].x(), allPoint[1].x(), Ex2_Const.EPS);
        Assert.assertEquals(tri1[1].y(), allPoint[1].y(), Ex2_Const.EPS);
        Assert.assertEquals(tri1[2].x(), allPoint[2].x(), Ex2_Const.EPS);
        Assert.assertEquals(tri1[2].y(), allPoint[2].y(), Ex2_Const.EPS);
    }

    @Test
    public void testContains() {
        Assert.assertTrue(t1.contains(new Point_2D(2,2)));
        Assert.assertFalse(t1.contains(new Point_2D(7,4)));
    }

    @Test
    public void testArea() {
        assertEquals(5.0*5.0/2.0, t1.area(), Ex2_Const.EPS);
    }

    @Test
    void perimeter() {
        double a = Math.sqrt(2);
        Assert.assertEquals(a * 2 + 2, t2.perimeter(), Ex2_Const.EPS);
    }

    @Test
    public void testTranslate() {
        t2.translate(new Point_2D(1,1));
        Point_2D[] allPoint = t2.getAllPoints();
        Assert.assertEquals(2.0, allPoint[0].x(), Ex2_Const.EPS);
        Assert.assertEquals(2.0, allPoint[0].y(), Ex2_Const.EPS);
        Assert.assertEquals(3.0, allPoint[1].x(), Ex2_Const.EPS);
        Assert.assertEquals(3.0, allPoint[1].y(), Ex2_Const.EPS);
        Assert.assertEquals(2.0, allPoint[2].x(), Ex2_Const.EPS);
        Assert.assertEquals(4.0, allPoint[2].y(), Ex2_Const.EPS);
    }

    @Test
    public void testCopy() {
        GeoShape t3 = t1.copy();
        Triangle_2D t4 = (Triangle_2D) t3;
        Point_2D[] allPoint = t1.getAllPoints();
        Point_2D[] t4AllPoint = t4.getAllPoints();
        Assert.assertEquals(t4AllPoint[0].x(), allPoint[0].x(), Ex2_Const.EPS);
        Assert.assertEquals(t4AllPoint[0].y(), allPoint[0].y(), Ex2_Const.EPS);
        Assert.assertEquals(t4AllPoint[1].x(), allPoint[1].x(), Ex2_Const.EPS);
        Assert.assertEquals(t4AllPoint[1].y(), allPoint[1].y(), Ex2_Const.EPS);
        Assert.assertEquals(t4AllPoint[2].x(), allPoint[2].x(), Ex2_Const.EPS);
        Assert.assertEquals(t4AllPoint[2].y(), allPoint[2].y(), Ex2_Const.EPS);
    }

    @Test
    void scale() {
        t1.scale(Point_2D.ORIGIN, 0.5);
        Triangle_2D t3 = new Triangle_2D(Point_2D.ORIGIN, new Point_2D(2.5,0), new Point_2D(0,2.5));
        assertEquals(t1,t3);
    }



    @Test
    void rotate() {
        t1.rotate(Point_2D.ORIGIN, 90);
        Point_2D[] allPoint = t1.getAllPoints();
        Assert.assertEquals(0, allPoint[0].x(), Ex2_Const.EPS);
        Assert.assertEquals(0, allPoint[0].y(), Ex2_Const.EPS);
        Assert.assertEquals(-5, allPoint[1].x(), Ex2_Const.EPS);
        Assert.assertEquals(0, allPoint[1].y(), Ex2_Const.EPS);
        Assert.assertEquals(0, allPoint[2].x(), Ex2_Const.EPS);
        Assert.assertEquals(5, allPoint[2].y(), Ex2_Const.EPS);

    }


    @Test
    public void testEquals(){
        Triangle_2D t3 = new Triangle_2D( new Point_2D(0, 5) ,Point_2D.ORIGIN, new Point_2D(5,0));
        assertTrue(t1.equals(t3));
        assertFalse(t2.equals(t3));
    }

    @Test
    public void testToString(){
        Triangle_2D t3 = new Triangle_2D("0,0,0,5,5,0");
        assertEquals(t1,t3);
        String s1 = t3.toString();
        Triangle_2D t4 = new Triangle_2D(s1);
        assertEquals(t4,t3);
    }
}