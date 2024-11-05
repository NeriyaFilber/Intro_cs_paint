package ex2.Test.gui;


import ex2.ex2.Ex2_Const;
import ex2.ex2.GUI_Shape_Collection;
import ex2.geo.*;
import ex2.gui.Ex2;
import ex2.gui.GUIShape;
import ex2.gui.GUI_Shape;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class TestEx2 {
    private static Point_2D p1 = null;
    private static Point_2D p2 = null;
    private static Point_2D p3 = null;
    private static Point_2D p4 = null;
    private static Point_2D p5 = null;
    private static Point_2D p6 = null;
    private static Circle_2D c1 = null;
    private static Circle_2D c2 = null;
    private static Circle_2D c3 = null;
    private static Rect_2D r1 = null;
    private static Triangle_2D t1 = null;
    private static Segment_2D s1 = null;
    private static Polygon_2D po1 = null;
    private static GUI_Shape gs1 = null;
    private static GUI_Shape gs2 = null;
    private static GUI_Shape gs3 = null;
    private static GUI_Shape gs4 = null;
    private static GUI_Shape gs5 = null;
    private static GUI_Shape gs6 = null;
    private static GUI_Shape gs7 = null;

    private static Ex2 ex2;
    private static GUI_Shape_Collection arrayList;

    @BeforeEach
    void setUp() {
        ex2 = Ex2.getInstance();
        arrayList = ex2.getShape_Collection();
        p1 = new Point_2D(1, 1);
        p2 = new Point_2D(2, 2);
        p3 = new Point_2D(0, 0);
        p4 = new Point_2D(1, 0);
        p5 = new Point_2D(1, 0);
        p6 = new Point_2D(3, 4);
        c1 = new Circle_2D(p2, 2);
        c2 = new Circle_2D(p1, 2);
        r1 = new Rect_2D(p1, p3);
        t1 = new Triangle_2D(p1, p2, p3);
        s1 = new Segment_2D(p4, p1);
        po1 = new Polygon_2D(new Point_2D[]{p1, p2, p3, p4, p5});
        c3 = new Circle_2D(p6, 2);

        gs1 = new GUIShape(c1, false, Color.black, 4);
        gs2 = new GUIShape(c2, false, Color.yellow, 1);
        gs3 = new GUIShape(r1, true, Color.red, 2);
        gs4 = new GUIShape(t1, true, Color.green, 0);
        gs5 = new GUIShape(s1, false, Color.gray, 7);
        gs6 = new GUIShape(po1, true, Color.blue, 3);
        gs7 = new GUIShape(c3, true, Color.green, 0);

        arrayList.removeAll();
        arrayList.add(gs1);
        arrayList.add(gs2);
        arrayList.add(gs3);
        arrayList.add(gs4);
        arrayList.add(gs5);
        arrayList.add(gs6);
        arrayList.add(gs7);

        ex2.init(arrayList);


    }

    @Test
    void getInfo() {
        System.out.println(ex2.getInfo());
        String s = "GUIShape,0,false,4,Circle_2D,2.0,2.0, 2.0\n" +
                "GUIShape,16776960,false,1,Circle_2D,1.0,1.0, 2.0\n" +
                "GUIShape,16711680,true,2,Rect_2D,1.0,1.0,1.0,0.0,0.0,0.0,0.0,1.0\n" +
                "GUIShape,65280,true,0,Triangle_2D,1.0,1.0,2.0,2.0,0.0,0.0\n" +
                "GUIShape,8421504,false,7,Segment_2D,1.0,0.0,1.0,1.0\n" +
                "GUIShape,255,true,3,Polygon_2D,1.0,1.0,2.0,2.0,0.0,0.0,1.0,0.0,1.0,0.0\n" +
                "GUIShape,65280,true,0,Circle_2D,3.0,4.0, 2.0";
        String s1 = ex2.getInfo().trim();

        assertEquals(s, s1);


    }

    @Test
    void All() {
        System.out.println("Before operations:");
        System.out.println(ex2.getInfo());
        for (int i = 0; i < ex2.getShape_Collection().size(); i++) {
            GUI_Shape s = ex2.getShape_Collection().get(i);
            s.getShape().translate(new Point_2D(1, 1));
        }
        for (int i = 0; i < 2; i = i + 1) {
            GUI_Shape s = ex2.getShape_Collection().get(i);
            s.setFilled(true);
        }

        for (int i = 0; i < ex2.getShape_Collection().size(); i++) {
            GUI_Shape s = ex2.getShape_Collection().get(i);
            s.getShape().rotate(new Point_2D(0, 0), 180);
            s.getShape().scale(new Point_2D(0, 0), 2);

        }

        System.out.println("After operations:");
        ex2.actionPerformed("Info");

    }

    @Test
    void copy() {
        GUI_Shape_Collection copy = ex2.getShape_Collection().copy();
        Assertions.assertNotSame(copy, ex2.getShape_Collection());
    }

    @Test
    void testInfo() {
        String s1 = ex2.getInfo();
        String s2 = ex2.getShape_Collection().toString();
        assertEquals(s1, s2);

    }

    @Test
    void testNone() {
        ex2.actionPerformed("All");
        for (int i = 0; i < ex2.getShape_Collection().size(); i++) {
            GUI_Shape s = ex2.getShape_Collection().get(i);
            Assertions.assertTrue(s.isSelected());
        }

        ex2.actionPerformed("None");
        for (int i = 0; i < ex2.getShape_Collection().size(); i++) {
            GUI_Shape s = ex2.getShape_Collection().get(i);
            Assertions.assertFalse(s.isSelected());
        }
        ex2.actionPerformed("Point");
        ex2.mouseClicked(new Point_2D(1, 1));
        Assertions.assertTrue(ex2.getShape_Collection().get(0).isSelected());
        Assertions.assertTrue(ex2.getShape_Collection().get(1).isSelected());
        Assertions.assertTrue(ex2.getShape_Collection().get(2).isSelected());
        Assertions.assertTrue(ex2.getShape_Collection().get(3).isSelected());
        Assertions.assertTrue(ex2.getShape_Collection().get(4).isSelected());

        ex2.actionPerformed("Anti");
        for (int i = 0; i < ex2.getShape_Collection().size() - 1; i++) {
            GUI_Shape s = ex2.getShape_Collection().get(i);
            Assertions.assertFalse(s.isSelected());
        }

    }

    @Test
    void testTags() {
        for (int i = 0; i < ex2.getShape_Collection().size(); i++) {
            ex2.getShape_Collection().get(i).setTag(i);
            assertEquals(i, ex2.getShape_Collection().get(i).getTag());
        }
    }

    @Test
    void testColor() {
        Color co1 = Color.blue;
        for (int i = 0; i < ex2.getShape_Collection().size(); i++) {
            ex2.getShape_Collection().get(i).setColor(Color.blue);
        }
        for (int i = 0; i < ex2.getShape_Collection().size(); i++) {
            Assertions.assertSame(co1, ex2.getShape_Collection().get(i).getColor());
        }
    }

    @Test
    void testRemove() {
        GUI_Shape_Collection a1 = ex2.getShape_Collection();
        a1.removeElementAt(1);
        ex2.getShape_Collection().get(1).setSelected(true);
        ex2.actionPerformed("Remove");
        assertEquals(a1, ex2.getShape_Collection());
    }

    @Test
    void testScale90() {
        ex2.actionPerformed("Point");
        ex2.mouseClicked(new Point_2D(1, 1));
        double a = Math.PI * Math.pow(1.8, 2);
        ex2.actionPerformed("Scale_90%");
        ex2.mouseClicked(new Point_2D(1, 1));
        double a2 = ex2.getShape_Collection().get(1).getShape().area();
        assertEquals(a, a2, Ex2_Const.EPS);

    }

    @Test
    void testScale110() {
        ex2.actionPerformed("Point");
        ex2.mouseClicked(new Point_2D(1, 1));
        double a = Math.PI * Math.pow(2.2, 2);
        ex2.actionPerformed("Scale_110%");
        ex2.mouseClicked(new Point_2D(1, 1));
        double a2 = ex2.getShape_Collection().get(1).getShape().area();
        assertEquals(a, a2, Ex2_Const.EPS);
    }

    @Test
    void movesAndRotate() {

        ex2.actionPerformed("Point");
        ex2.mouseClicked(new Point_2D(2, 4));
        ex2.actionPerformed("Move");
        ex2.mouseClicked(new Point_2D(3, 4));
        ex2.mouseClicked(new Point_2D(2, 2));
        Circle_2D c3 = new Circle_2D(new Point_2D(2, 2), 2);
        Assertions.assertEquals(c3, ex2.getShape_Collection().get(6).getShape());
        ex2.actionPerformed("Rotate");
        ex2.mouseClicked(new Point_2D(0, 0));
        ex2.mouseClicked(new Point_2D(0, 2));
        c3 = new Circle_2D(new Point_2D(-2, 2), 2);
        Circle_2D c4 = new Circle_2D(String.valueOf(ex2.getShape_Collection().get(6).getShape()));
        Assertions.assertTrue(c3.equals(c4));
    }

    @Test
    void sortByArea() {
        ex2.actionPerformed("ByArea");
        for (int i = 1; i < ex2.getShape_Collection().size(); i++) {
            double a = ex2.getShape_Collection().get(i - 1).getShape().area();
            double b = ex2.getShape_Collection().get(i).getShape().area();
            Assertions.assertTrue(a <= b);
        }
    }

    @Test
    void sortByAntiArea() {
        ex2.actionPerformed("ByAntiArea");
        for (int i = 1; i < ex2.getShape_Collection().size(); i++) {
            double a = ex2.getShape_Collection().get(i - 1).getShape().area();
            double b = ex2.getShape_Collection().get(i).getShape().area();
            Assertions.assertTrue(a >= b);
        }
    }

    @Test
    void sortByTag() {
        ex2.actionPerformed("ByTag");
        for (int i = 1; i < ex2.getShape_Collection().size(); i++) {
            double a = ex2.getShape_Collection().get(i - 1).getTag();
            double b = ex2.getShape_Collection().get(i).getTag();
            Assertions.assertTrue(a <= b);
        }
    }

    @Test
    void sortByAntiTag() {
        ex2.actionPerformed("ByAntiTag");
        for (int i = 1; i < ex2.getShape_Collection().size(); i++) {
            double a = ex2.getShape_Collection().get(i - 1).getTag();
            double b = ex2.getShape_Collection().get(i).getTag();
            Assertions.assertTrue(a >= b);
        }
    }

    @Test
    void sortByPerimeter() {
        ex2.actionPerformed("ByPerimeter");
        for (int i = 1; i < ex2.getShape_Collection().size(); i++) {
            double a = ex2.getShape_Collection().get(i - 1).getShape().perimeter();
            double b = ex2.getShape_Collection().get(i).getShape().perimeter();
            Assertions.assertTrue(a <= b);
        }
    }

    @Test
    void sortByAntiPerimeter() {
        ex2.actionPerformed("ByAntiPerimeter");
        for (int i = 1; i < ex2.getShape_Collection().size(); i++) {
            double a = ex2.getShape_Collection().get(i - 1).getShape().perimeter();
            double b = ex2.getShape_Collection().get(i).getShape().perimeter();
            Assertions.assertTrue(a >= b);
        }
    }

    @Test
    void sortByToString() {
        ex2.actionPerformed("ByToString");
        for (int i = 1; i < ex2.getShape_Collection().size(); i++) {
            String a = ex2.getShape_Collection().get(i - 1).toString();
            String b = ex2.getShape_Collection().get(i).toString();
            Assertions.assertTrue(0 >= a.compareTo(b));
        }
    }

    @Test
    void sortByAntiToString() {
        ex2.actionPerformed("ByAntiToString");
        for (int i = 1; i < ex2.getShape_Collection().size(); i++) {
            String a = ex2.getShape_Collection().get(i - 1).toString();
            String b = ex2.getShape_Collection().get(i).toString();
            Assertions.assertTrue(0 <= a.compareTo(b));
        }
    }

    @Test
    void createPolygon() {
        assertEquals(7, arrayList.size());
        Point_2D[] polygonPoints = {new Point_2D(0, 0), new Point_2D(3, 0), new Point_2D(3, 4)};
        Polygon_2D newPolygon = new Polygon_2D(polygonPoints);

        GUI_Shape newPolygonShape = new GUIShape(newPolygon, true, Color.orange, 8);
        arrayList.add(newPolygonShape);

        ex2.init(arrayList);
        assertEquals(8, arrayList.size());
        assertEquals(newPolygonShape, arrayList.get(7));

    }

    @Test
    void createSegment() {
        assertEquals(7, arrayList.size());
        Segment_2D newSegment = new Segment_2D(new Point_2D(5, 6), new Point_2D(4, 3));
        GUI_Shape newSegmentShape = new GUIShape(newSegment, true, Color.orange, 8);
        arrayList.add(newSegmentShape);

        ex2.init(arrayList);
        assertEquals(8, arrayList.size());
        assertEquals(newSegmentShape, arrayList.get(7));
    }

    @Test
    void createCircle() {
        assertEquals(7, arrayList.size());
        Circle_2D newCircle = new Circle_2D(new Point_2D(5, 6), 4);
        GUI_Shape newCircleShape = new GUIShape(newCircle, true, Color.orange, 8);
        arrayList.addAt(newCircleShape, 4);

        ex2.init(arrayList);
        assertEquals(8, arrayList.size());
        assertEquals(newCircleShape, arrayList.get(4));

    }

    @Test
    void createRect() {
        assertEquals(7, arrayList.size());
        Rect_2D newRect = new Rect_2D(new Point_2D(5, 6), new Point_2D(4, 3));
        GUI_Shape newRectShape = new GUIShape(newRect, true, Color.orange, 8);
        arrayList.addAt(newRectShape, 0);
        arrayList.removeElementAt(2);

        ex2.init(arrayList);
        assertEquals(7, arrayList.size());
        assertEquals(newRectShape, arrayList.get(0));
    }

    @Test
    void createTriangle() {
        assertEquals(7, arrayList.size());
        Triangle_2D newTriangle = new Triangle_2D(new Point_2D(5, 6), new Point_2D(4, 3), new Point_2D(4, 3));
        GUI_Shape newTriangleShape = new GUIShape(newTriangle, true, Color.orange, 8);
        arrayList.addAt(newTriangleShape, 2);

        ex2.init(arrayList);
        assertEquals(8, arrayList.size());
        assertEquals(newTriangleShape, arrayList.get(2));

    }

    @Test
    void setColor() {
        Color[] co1 = {Color.black, Color.red, Color.blue, Color.green, Color.white, Color.yellow, Color.CYAN};
        Ex2 ex2_1 = Ex2.getInstance();
        GUI_Shape_Collection shapes1 = ex2.getShape_Collection().copy();
        for (int i = 0; i < ex2.getShape_Collection().size(); i++) {
            ex2_1.getShape_Collection().get(i).setColor(co1[i]);
            System.out.println(ex2_1.getShape_Collection().get(i));
        }
        ex2_1.init(shapes1);
    }
}