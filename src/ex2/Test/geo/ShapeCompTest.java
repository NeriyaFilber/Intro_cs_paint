package ex2.Test.geo;

import ex2.ex2.Ex2_Const;
import ex2.geo.Circle_2D;
import ex2.geo.Point_2D;
import ex2.geo.Rect_2D;
import ex2.geo.ShapeComp;
import ex2.gui.GUIShape;
import ex2.gui.GUI_Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ShapeCompTest {
    private Point_2D _p1 = new Point_2D(0,0);
    private Point_2D _p2 = new Point_2D(2,2);
    private double _radius = 2;
    private Circle_2D _circle = new Circle_2D(_p1,_radius);
    private Rect_2D _rect = new Rect_2D(_p1,_p2);
    GUI_Shape gs1 = new GUIShape(_rect, true, Color.black, 1);
    GUI_Shape gs2 = new GUIShape(_circle, true, Color.black, 2);
    @Test
    void compare() {
        ShapeComp comp = new ShapeComp(Ex2_Const.Sort_By_Area);
        assertTrue(comp.compare(gs1, gs2) < 0);

        ShapeComp comp1 = new ShapeComp(Ex2_Const.Sort_By_Perimeter);
        assertTrue(comp1.compare(gs1, gs2) < 0);

        ShapeComp comp2 = new ShapeComp(Ex2_Const.Sort_By_toString);
        assertTrue(comp2.compare(gs1, gs2) < 0);


        ShapeComp comp3 = new ShapeComp(Ex2_Const.Sort_By_Tag);
        assertTrue(comp3.compare(gs1, gs2) < 0);


    }
}