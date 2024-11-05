package ex2.Test.gui;

import ex2.geo.*;
import ex2.gui.GUIShape;
import ex2.gui.GUI_Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class GUIShapeTest {
    public static Point_2D p1;
    public static Point_2D p2;
    public static Point_2D p3;
    public static Point_2D p4;
    public static Point_2D p5;
    Circle_2D c1;
    Circle_2D c2;
    Segment_2D s1;
    Triangle_2D t1;
    Rect_2D r1;
    Polygon_2D po1;
    GUI_Shape gs1;
    GUI_Shape gs2;
    GUI_Shape gs3;
    GUI_Shape gs4;
    GUI_Shape gs5;
    GUI_Shape gs6;

    @BeforeEach
    void setUp() {
        p1 = new Point_2D(3,4);
        p2 = new Point_2D(6,8);
        c1 = new Circle_2D(p1,2);
        c2 = new Circle_2D(p2,3.4);
        gs1 = new GUIShape(c1, true, Color.black, 1);
        gs2 = new GUIShape(c2, true, Color.yellow, 2);
        p3 = new Point_2D(2, 8);
        p4 = new Point_2D(5,7);
        p5 = new Point_2D( 2,4);
        s1 = new Segment_2D(p3, p4);
        t1 = new Triangle_2D(p3, p4, p5);
        r1 = new Rect_2D(p1,p2);
        gs3 = new GUIShape(s1, false, Color.black, 3);
        gs4 = new GUIShape(t1, true, Color.cyan, 4);
        gs5 = new GUIShape(r1, true, Color.magenta, 5);
        po1 = new Polygon_2D();
        po1.add(p1);
        po1.add(p2);
        po1.add(p3);
        po1.add(p4);
        po1.add(p5);
        gs6 = new GUIShape(po1, true, Color.green, 6);
    }

    @Test
    void getShape() {
        assertEquals(c1, gs1.getShape());
        assertNotEquals(c2,gs1.getShape());
    }

    @Test
    void setShape() {
        gs1.setShape(c2);
        assertEquals(c2, gs1.getShape());
    }

    @Test
    void isFilled() {
        assertTrue(gs1.isFilled());
        assertFalse(gs3.isFilled());
    }

    @Test
    void setFilled() {
        gs3.setFilled(true);
        assertTrue(gs3.isFilled());
    }

    @Test
    void getColor() {
        assertEquals(new Color(255,255,0) ,gs2.getColor());
    }

    @Test
    void setColor() {
        gs2.setColor(new Color(0,0,0));
        assertEquals(new Color(0,0,0),gs2.getColor());
    }

    @Test
    void getTag() {
        assertEquals(1, gs1.getTag());
    }

    @Test
    void setTag() {
        gs1.setTag(5);
        assertEquals(5,gs1.getTag());
    }

    @Test
    void copy() {
        GUI_Shape gs7 = gs1.copy();
        assertTrue(gs1.equals(gs7));
        assertFalse(gs1 == gs7);
        assertFalse(gs1.getShape() == gs7.getShape());
    }

    @Test
    void testToString() {
        String s1 = gs6.toString();
        GUI_Shape gs7 = new GUIShape(s1);
        assertEquals(gs6, gs7);
    }

    @Test
    void colorEncoding() {
        Color co1 = new Color(255,255,0);
        int co1Int = GUIShape.colorEncoding(co1);
        assertEquals(16776960, co1Int);
    }

    @Test
    void decodeColor() {
        Color co1 = new Color(255,255,0);
        int co1Int = GUIShape.colorEncoding(co1);
        Color co2 = GUIShape.decodeColor(co1Int);
        assertEquals(co1,co2);
    }

    @Test
    void isSelected() {
        assertFalse(gs1.isSelected());
    }

    @Test
    void setSelected() {
        gs1.setSelected(true);
        assertTrue(gs1.isSelected());
    }
}