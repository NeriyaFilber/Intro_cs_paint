package ex2.Test.geo;

import ex2.ex2.Ex2_Const;
import ex2.geo.GeoShape;
import ex2.geo.Point_2D;
import ex2.geo.Segment_2D;
import ex2.gui.Ex2;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Segment_2DTest {
    private Segment_2D s1 = null;
    private Point_2D p1 = null;
    private Point_2D p2 = null;
    private Segment_2D s2 = null;

    @BeforeEach
    void setUp(){
        s1 = new Segment_2D(Point_2D.ORIGIN, new Point_2D(0,1));
        p1 = new Point_2D(1,0);
        p2 = new Point_2D(5,3);
        s2 = new Segment_2D(p1 , p2);
    }

    @Test
    public void testGet_p1() {
        assertEquals(p1.x(), s2.get_p1().x());
        assertEquals(p1.y(), s2.get_p1().y());
        assertFalse(p1 == s2.get_p1());
    }

    @Test
    public void testGet_p2() {
        assertEquals(p2.x(), s2.get_p2().x());
        assertEquals(p2.y(), s2.get_p2().y());
        assertFalse(p2 == s2.get_p2());
    }

    @Test
    public void testContains() {
        Point_2D q0 = new Point_2D(0,0.4);
        boolean b1 = s1.contains(q0);
        Assert.assertTrue(b1);

        q0 = new Point_2D(0,1.3);
        b1 = s1.contains(q0);
        Assert.assertFalse(b1);

        q0 = new Point_2D(3,1.5);
        b1 = s2.contains(q0);
        Assert.assertTrue(b1);

        q0 = new Point_2D(2.8,1.5);
        b1 = s2.contains(q0);
        Assert.assertFalse(b1);
    }

    @Test
    public void testArea() {
        assertEquals(0,s2.area());
        assertNotEquals(1,s2.area());
    }

    @Test
    void testPerimeter() {
        double p = s1.perimeter(); // 2
        Assert.assertEquals(p, 2, Ex2_Const.EPS);
        p = s2.perimeter(); // 10
        Assert.assertEquals(p, 10, Ex2_Const.EPS);
    }

    @Test
    public void testTranslate() {
        s1.translate(new Point_2D(1,1));
        assertEquals(1, s1.get_p1().x());
        assertEquals(1, s1.get_p1().y());
        assertEquals(1, s1.get_p2().x());
        assertEquals(2, s1.get_p2().y());
    }

    @Test
    public void testCopy() {
        GeoShape s3 =  s1.copy();
        Segment_2D s4 = (Segment_2D) s3;
        assertEquals(0, s4.get_p1().x());
        assertEquals(0, s4.get_p1().y());
        assertEquals(0, s4.get_p2().x());
        assertEquals(1, s4.get_p2().y());
        assertFalse(s4.get_p1() == s1.get_p1());
        assertFalse(s4.get_p2() == s1.get_p2());
        assertFalse(s4 == s1);

    }

    @Test
    public void testScale() {
        s1.scale(new Point_2D(0,4), 0.5);
        assertEquals(0, s1.get_p1().x());
        assertEquals(2, s1.get_p1().y());
        assertEquals(0, s1.get_p2().x());
        assertEquals(2.5, s1.get_p2().y());
    }

    @Test
    public void testRotate() {
        s1.rotate(new Point_2D(0,0.5), 180);
        s1.rotate(new Point_2D(0,0.5), 180);
        Assert.assertEquals(0, s1.get_p2().x(), Ex2_Const.EPS);
        Assert.assertEquals(1, s1.get_p2().y(), Ex2_Const.EPS);

    }

    @Test
    public void testToString(){
        String st1 = s1.toString();
        String st2 = s2.toString();
        Segment_2D s11 = new Segment_2D(st1);
        Segment_2D s12 = new Segment_2D(st2);
        assertTrue(s1.equals(s11));
        assertTrue(s2.equals(s12));
    }
}