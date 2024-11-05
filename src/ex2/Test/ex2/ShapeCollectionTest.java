package ex2.Test.ex2;

import ex2.ex2.GUI_Shape_Collection;
import ex2.ex2.ShapeCollection;
import ex2.gui.Ex2;
import ex2.geo.*;
import ex2.gui.GUIShape;
import ex2.gui.GUI_Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ShapeCollectionTest {
    private ShapeCollection _shapes;
    private ShapeCollection _shapes1 = new ShapeCollection();
    private static Point_2D p1;
    private static Point_2D p2;
    private static Point_2D p3;
    private static Point_2D p4;
    private static Point_2D p5;
    private static Point_2D p6;
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
        _shapes = new ShapeCollection();
        p1 = new Point_2D(3,4);
        p2 = new Point_2D(6,8);
        p6 = Point_2D.ORIGIN;
        c1 = new Circle_2D(p6,5);
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
        _shapes.add(gs1);
        _shapes.add(gs2);
        _shapes.add(gs3);
        _shapes.add(gs4);
        _shapes.add(gs5);
        _shapes.add(gs6);
    }

    @Test
    void get() {
        assertEquals(_shapes.get(0).getShape(), new Circle_2D(Point_2D.ORIGIN, 5));

    }

    @Test
    void size() {
        assertTrue(_shapes.size() == 6);
        assertFalse(_shapes.size() == 5);
    }

    @Test
    void removeElementAt() {
        _shapes.removeElementAt(2);
        assertTrue(_shapes.size() == 5);
        System.out.println(_shapes.toString());
    }

    @Test
    void addAt() {
        Segment_2D s1 = new Segment_2D("4,6,7,8");
        GUI_Shape ss1 = new GUIShape(s1, true,Color.black, 3);
        _shapes.addAt(ss1, 1);
        assertEquals(ss1, _shapes.get(1));
        System.out.println(_shapes);
    }

    @Test
    void add() {
        Segment_2D s1 = new Segment_2D("2,8,5,7");
        GUI_Shape ss1 = new GUIShape(s1, false,Color.black, 3);
        _shapes.add(ss1);
        assertEquals(ss1, _shapes.get(2));
        System.out.println(_shapes);
    }

    @Test
    void copy() {
        GUI_Shape_Collection shape1 = _shapes.copy();
        assertNotEquals(new GUI_Shape_Collection[]{_shapes} , new GUI_Shape_Collection[]{shape1});
        assertEquals(shape1.toString(), _shapes.toString());
        for (int i = 0; i < _shapes.size(); i++) {
            assertFalse(_shapes.get(i) == shape1.get(i));
        }
    }

    @Test
    void sort() {
        double[] area = new double[6];
        for (int i = 0; i < _shapes.size(); i++) {
            area[i] = _shapes.get(i).getShape().area();
        }
        Arrays.sort(area);
        System.out.println(Arrays.toString(area));
    }

    @Test
    void removeAll() {
        _shapes.removeAll();
        assertTrue(_shapes.size() == 0);
    }

    @Test
    void saveAndLoad() {
        String s = "C:\\Users\\brhva\\IdeaProjects\\Ex2\\src\\ex2\\Test\\ex2.ex1.txt";
        _shapes.save(s);

        String t = "C:\\Users\\brhva\\IdeaProjects\\Ex2\\src\\ex2\\Test\\ex2.ex1.txt";
        _shapes1.load(t);
        assertEquals(_shapes,_shapes1);
    }

    @Test
    void testToString() {
        String a1 = _shapes.toString();
        String[] a2 = a1.split("\n");
    }

    @Test
    void testEquals() {
        GUI_Shape_Collection shape1 = _shapes.copy();
        assertEquals(_shapes,shape1);
    }
}