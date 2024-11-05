package ex2.Test.geo;

import ex2.ex2.Ex2_Const;
import ex2.geo.Circle_2D;
import ex2.geo.GeoShape;
import ex2.geo.Point_2D;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Circle_2DTest {
    private Circle_2D c1 = null;
    private Circle_2D c2 = null;
    private Point_2D p1 = new Point_2D(0.5,0.5);
    private Point_2D p2 = new Point_2D(1.1, 0.5);

    @BeforeEach
    void setUp(){
        c1 = new Circle_2D(Point_2D.ORIGIN, 1);
        c2 = new Circle_2D(new Point_2D(2,2), 1);
    }

    @Test
    public void testGetRadius() {
        double r1 = c1.getRadius();
        Assert.assertEquals(r1, 1, Ex2_Const.EPS);
        Assert.assertFalse(r1 == 1.1);

    }

    @Test
    public void testGetCenter() {
        Point_2D cen1 = c1.getCenter();
        Point_2D cen2 = c2.getCenter();
        assertEquals(new Point_2D(0,0), cen1);
        assertEquals(new Point_2D(2,2), cen2);
        assertFalse(cen1 == new Point_2D(2,5));
    }

    @Test
    void testToString() {
        Circle_2D c2 = new Circle_2D("4,3, 5");
        String s1 = c1.toString();
        String s2 = c2.toString();
        Circle_2D c3 = new Circle_2D(s1);
        Circle_2D c4 = new Circle_2D(s2);
        assertEquals(c1,c3);
        assertEquals(c2,c4);
    }

    @Test
    public void testContains() {
        boolean ans = c1.contains(p1);
        Assert.assertTrue(ans);
        ans = c1.contains(p2);
        Assert.assertFalse(ans);
    }

    @Test
    public void testArea() {
        double ans = c1.area(); // PI
        Assert.assertEquals(ans, Math.PI, Ex2_Const.EPS);
        Assert.assertFalse(ans == 4 * Math.PI);
    }

    @Test
    public void testPerimeter() {
        double per = c2.perimeter();
        Assert.assertEquals(per, 2 * Math.PI, Ex2_Const.EPS);
        Assert.assertFalse(per == 4 * Math.PI);
    }

    @Test
    public void testTranslate() {
        c1.translate(new Point_2D(1,1));
        assertEquals(new Point_2D(1,1), c1.getCenter());
        c1.translate(new Point_2D(1,1));
        assertFalse(new Point_2D(1,1) == c1.getCenter());
    }

    @Test
    public void testCopy() {
        GeoShape c3 = c1.copy();
        Circle_2D c4 = (Circle_2D) c3;
        Point_2D p4 = c4.getCenter();
        assertTrue(c1.getRadius() == c4.getRadius());
        assertEquals(p4, new Point_2D(0, 0));
        assertEquals(c1.getCenter() , new Point_2D(0,0));
    }

    @Test
    public void testScale() {
        c1.scale(c1.getCenter(), 2.0);
        assertEquals(2.0, c1.getRadius(), Ex2_Const.EPS);
    }

    @Test
    public void testRotate() {
        c2.rotate(Point_2D.ORIGIN, 90);
        Circle_2D c3 = new Circle_2D(new Point_2D(-2,2),1);
        Point_2D cen1 = c2.getCenter();
        assertTrue(c2.getRadius() == 1);
        assertEquals(c2.getCenter().x(), -2, Ex2_Const.EPS);
        assertEquals(c2.getCenter().y(), 2, Ex2_Const.EPS);
        assertTrue(c2.equals(c3));
    }

    @Test
    void testEquals(){
        Circle_2D c3 = new Circle_2D(Point_2D.ORIGIN, 1);
        assertFalse(c1.equals(c2));
        assertTrue(c1.equals(c3));
    }
}