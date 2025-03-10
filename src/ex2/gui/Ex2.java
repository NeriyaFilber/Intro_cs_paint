package ex2.gui;

import ex2.ex2.Ex2_Const;
import ex2.ex2.GUI_Shape_Collection;
import ex2.ex2.ShapeCollection;
import ex2.geo.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;

/**
 * The Ex2 assignment is a basic paint application that empowers users to create, manipulate, and visualize
 * various 2D shapes within a coordinate plane. Offering features like interactive drawing of polygons, segments, circles,
 * triangles, and rectangles, this application goes beyond mere drawing, providing a basic set of functionalities:
 * movement, copying, filling / unfilling shapes with color, rotation, and scaling by 90% or 110%. Users can save and load
 * projects, ensuring a seamless and continuous artistic experience.
 * <p>
 * The application's structure is organized into three main packages:
 * <p>
 * 1) Geo Package:
 *    - GeoShape Interface: Defines fundamental methods for geometric shape representation.
 *        - `contains(Point_2D point)`: Checks if a shape contains a specific point.
 *        - `area()`: Calculates the area of the shape.
 *        - `perimeter()`: Calculates the perimeter of the shape.
 *        - `translate(Point_2D vector)`: Moves the shape by a specified vector.
 *        - `copy()`: Creates a deep copy of the shape.
 *        - `scale(Point_2D point, double ratio)`: Scales the size of the shape.
 *        - `rotate(Point_2D point, double angle)`: Rotates the shape around a specified point by a given angle.
 *        - `toString()`: Generates a textual representation of the shape.
 *    - ShapeComp Class: Facilitates shape comparison based on various criteria.
 *        - Sorting options include ByArea, ByAntiArea, ByPerimeter, ByAntiPerimeter, ByTag, ByAntiTag,
 *          ByToString, and ByAntiToString.
 *    - Noteworthy Classes: Circle_2D, Rect_2D, Triangle_2D, Point_2D, Polygon_2D, Segment_2D.
 * <p>
 * 2) Ex2 Package:
 *    - Ex2_Const: Contains essential constant data like the value of epsilon and sorted types.
 *    - ShapeCollection Class: Central class managing all data for a specific run of the application.
 *        - `add(GUI_Shape shape)`: Adds a shape to the collection.
 *        - `remove(GUI_Shape shape)`: Removes a shape from the collection.
 *        - `save(String filename)`: Saves the collection's data to a file.
 *        - `load(String filename)`: Loads data from a file into the collection.
 *        - `sort(Comparator<GUI_Shape> comparator)`: Sorts the shapes based on the provided comparator.
 *        - `removeAll()`: Removes all shapes from the collection.
 *    - Ex2_main Class: Main class providing methods to load files or start with a blank canvas.
 * <p>
 * 3) Gui Package:
 *    - Ex2 Class: Intermediary layer connecting StdDrew_Ex2 with the application's data. Manages mouse events, program modes, etc.
 *        - `init(GUI_Shape_Collection shapes)`: Initializes the application with a specified shape collection.
 *        - `show(double dimension)`: Displays the shapes within a specified dimension.
 *        - `getInstance()`: Retrieves the singleton instance of Ex2.
 *        - ... (Additional methods for handling user actions, shape manipulations, and visualization.)
 *    - StdDrew_Ex2 Class: Creates the visual representation of data from Ex2 in a 2D plane.
 *    - GuiShape Class: Represents data associated with a specific shape in the 2D plane.
 *        - `setColor(Color color)`: Sets the color of the shape.
 *        - `setFilled(boolean filled)`: Sets whether the shape is filled with color.
 *        - `getShape()`: Retrieves the geometric shape associated with this GuiShape.
 *        - ... (Additional methods for modifying shape attributes.)
 * <p>
 * Ex2 Class (current context):
 *    - `mouseClicked(Point_2D point)`: Handles mouse click events, facilitating shape creation and manipulation.
 *    - `mouseRightClicked(Point_2D point)`: Handles right-click events, finalizing polygon creation.
 *    - `mouseMoved(MouseEvent event)`: Responds to mouse movement events, updating the visualization dynamically.
 *    - `actionPerformed(String action)`: Responds to various user actions, such as changing color, filling shapes, removing,
 *        saving, loading, sorting, and more.
 *    - `getInfo()`: Retrieves textual information about the shapes in the collection.
 * <p>
 * This class, acting as an interlayer between StdDraw and the application's data, was initially designed for a Java course,
 * employing static functions to achieve a "Singleton-like" implementation.
 *
 * @author Neriya Filber
 * I.D: 211377700
 */

public class Ex2 implements Ex2_GUI {
    private GUI_Shape_Collection _shapes = new ShapeCollection();
    private GUI_Shape _gs;
    private Polygon_2D _pp;
    private Color _color = Color.blue;
    private boolean _fill = false;
    private String _mode = "";
    private Point_2D _p1;
    private static Ex2 _winEx2 = null;

    /**
     * Private constructor for singleton pattern.
     */
    private Ex2() {
        init(null);
    }


    /**
     * Initializes the Ex2 object with a given GUI_Shape_Collection.
     *
     * @param s The GUI_Shape_Collection to initialize with. If null, a new ShapeCollection will be created.
     */
    public void init(GUI_Shape_Collection s) {
        if (s == null) {
            _shapes = new ShapeCollection();
        } else {
            _shapes = s;
        }
        GUI_Shape _gs = null;
        Polygon_2D _pp = null;
        _color = Color.blue;
        _fill = false;
        _mode = "";
        Point_2D _p1 = null;
    }


    /**
     * Displays the shapes and sets the scale based on the given dimension.
     *
     * @param d The dimension size to set the scale.
     */
    public void show(double d) {
        StdDraw_Ex2.setScale(0, d);
        StdDraw_Ex2.show();
        drawShapes();
    }

    /**
     * Gets the instance of the Ex2 class following the Singleton pattern.
     *
     * @return The instance of the Ex2 class.
     */
    public static Ex2 getInstance() {
        if (_winEx2 == null) {
            _winEx2 = new Ex2();
        }
        return _winEx2;
    }

    /**
     * Draws a grid with the specified dimensions and grid spacing.
     *
     * @param x     The width of the grid.
     * @param y     The height of the grid.
     * @param delta The grid spacing.
     */
     private static void drawGrid(int x, int y, int delta) {
         for(int i=0;i<x;i+=delta) {
             StdDraw_Ex2.line(i, 0, i, y);
         }
         for(int i=0;i<y;i+=delta) {
             StdDraw_Ex2.line(0, i, x, i);
         }
    }

    /**
     * Draws all the shapes in the GUI_Shape_Collection.
     */
    public void drawShapes() {
        StdDraw_Ex2.clear();
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape sh = _shapes.get(i);

            drawShape(sh);
        }
        if (_gs != null) {
            drawShape(_gs);
        }
        StdDraw_Ex2.show();
    }

    /**
     * Draws a specific shape on the StdDraw canvas.
     *
     * @param g The GUI_Shape to be drawn.
     */
    private static void drawShape(GUI_Shape g) {
        StdDraw_Ex2.setPenColor(g.getColor());
        if (g.isSelected()) {
            StdDraw_Ex2.setPenColor(Color.gray);
        }
        GeoShape gs = g.getShape();
        boolean isFill = g.isFilled();
        if (gs instanceof Circle_2D) {
            Circle_2D c = (Circle_2D) gs;
            Point_2D cen = c.getCenter();
            double rad = c.getRadius();
            if (isFill) {
                StdDraw_Ex2.filledCircle(cen.x(), cen.y(), rad);
            } else {
                StdDraw_Ex2.circle(cen.x(), cen.y(), rad);
            }
        }
        if (gs instanceof Segment_2D) {
            Segment_2D c = (Segment_2D) gs;
            Point_2D m0 = c.get_p1();
            Point_2D m1 = c.get_p2();
            StdDraw_Ex2.line(m0.x(), m0.y(), m1.x(), m1.y());
        }
        Point_2D[] ps = null;
        if (gs instanceof Polygon_2D) {
            ps = ((Polygon_2D) gs).getAllPoints();
        }
        if (gs instanceof Triangle_2D) {
            ps = ((Triangle_2D) gs).getAllPoints();
        }
        if (gs instanceof Rect_2D) {
            ps = ((Rect_2D) gs).getAllPoints();
        }
        if (ps != null) {
            double[] xx = new double[ps.length];
            double[] yy = new double[ps.length];
            for (int i = 0; i < xx.length; i++) {
                xx[i] = ps[i].x();
                yy[i] = ps[i].y();
            }
            if (isFill) {
                StdDraw_Ex2.filledPolygon(xx, yy);
            } else {
                StdDraw_Ex2.polygon(xx, yy);
            }
        }
    }

    /**
     * Sets the color of selected shapes in the GUI_Shape_Collection.
     *
     * @param c The color to set.
     */
    private void setColor(Color c) {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            if (s.isSelected()) {
                s.setColor(c);
            }
        }
    }

    /**
     * Sets the fill property of selected shapes in the GUI_Shape_Collection.
     */
        private void setFill() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            if (s.isSelected()) {
                s.setFilled(_fill);
            }
        }
    }

    /**
     * Removes selected shapes from the GUI_Shape_Collection.
     */
    private void remove() {
        for (int i = _shapes.size() -1 ; i >= 0; i--) {
            GUI_Shape s = _shapes.get(i);
            if (s.isSelected()) {
                _shapes.removeElementAt(i);
            }
        }
    }

    /**
     * Handles actions based on the given command string.
     *
     * @param p The command string representing the action to be performed.
     */
    public void actionPerformed(String p) {
        _mode = p;
        if (p.equals("Blue")) {
            _color = Color.BLUE;
            setColor(_color);
        }
        if (p.equals("Red")) {
            _color = Color.RED;
            setColor(_color);
        }
        if (p.equals("Green")) {
            _color = Color.GREEN;
            setColor(_color);
        }
        if (p.equals("White")) {
            _color = Color.WHITE;
            setColor(_color);
        }
        if (p.equals("Black")) {
            _color = Color.BLACK;
            setColor(_color);
        }
        if (p.equals("Yellow")) {
            _color = Color.YELLOW;
            setColor(_color);
        }
        if (p.equals("Fill")) {
            _fill = true;
            setFill();
        }
        if (p.equals("Empty")) {
            _fill = false;
            setFill();
        }
        if (p.equals("Remove")) {
            remove();
        }
        if (p.equals("Save")) {
            save();
        }
        if (p.equals("Load")) {
            load();
        }
        if (p.equals("ByArea")) {
            _shapes.sort(ShapeComp.CompByArea);
        }
        if (p.equals("ByAntiArea")){
            _shapes.sort(ShapeComp.CompByAntiArea);
        }
        if (p.equals("ByPerimeter")){
            _shapes.sort(ShapeComp.CompByPerimeter);
        }
        if (p.equals("ByAntiPerimeter")){
            _shapes.sort(ShapeComp.CompByAntiPerimeter);
        }
        if (p.equals("ByToString")) {
            _shapes.sort(ShapeComp.CompByToString);
        }
        if (p.equals("ByAntiToString")){
            _shapes.sort(ShapeComp.CompByToAntiString);
        }
        if (p.equals("ByTag")){
            _shapes.sort(ShapeComp.CompByTag);
        }
        if(p.equals("ByAntiTag")){
            _shapes.sort(ShapeComp.CompByAntiTag);
        }
        // ByAntiPerimeter
        if (p.equals("Clear")) {
            _shapes.removeAll();
        }
        if (p.equals("None")) {
            selectNone();
        }
        if (p.equals("All")) {
            selectAll();
        }
        if (p.equals("Anti")) {
            selectAnti();
        }
        if (p.equals("Info")) {
            System.out.println(getInfo());
        }

        drawShapes();

    }

    /**
     * Saves the current GUI_Shape_Collection to a text file.
     */
    private void save() {
        FileDialog chooser = new FileDialog(StdDraw_Ex2.getFrame(), "Save to Text file", FileDialog.SAVE);
        chooser.setVisible(true);
        String filename = chooser.getFile();
        if (filename != null) {
            _shapes.save(chooser.getDirectory() + File.separator + chooser.getFile());
        }
    }

    /**
     * Loads shapes from a text file into the GUI_Shape_Collection.
     */
    private void load() {
        _shapes.removeAll();
        FileDialog chooser = new FileDialog(StdDraw_Ex2.getFrame(), "Load from Text file", FileDialog.LOAD);
        chooser.setVisible(true);
        String filename = chooser.getFile();
        if (filename != null) {
            _shapes.load(chooser.getDirectory() + File.separator + chooser.getFile());
        }
    }

    /**
     * Handles mouse click events on the canvas.
     *
     * @param p The Point_2D representing the location of the mouse click.
     */
    public void mouseClicked(Point_2D p) {
        System.out.println("Mode: " + _mode + "  " + p);
        if (_mode.equals("Rect") || _mode.equals("Circle") || _mode.equals("Segment")) {
            if (_gs == null) {
                _p1 = new Point_2D(p);
            } else {
                _gs.setColor(_color);
                _gs.setFilled(_fill);
                _shapes.add(_gs);
                _gs = null;
                _p1 = null;
            }
        }
        if (_mode.equals("Move")) {
            if (_p1 == null) {
                _p1 = new Point_2D(p);
            } else {
                _p1 = new Point_2D(p.x() - _p1.x(), p.y() - _p1.y());
                move();
                _p1 = null;
            }
        }
        if (_mode.equals("Copy")) {
            if (_p1 == null) {
                _p1 = new Point_2D(p);
            } else {
                _p1 = new Point_2D(p.x() - _p1.x(), p.y() - _p1.y());
                copy();
                _p1 = null;
            }
        }
        if (_mode.equals("Rotate")) {
            if (_p1 == null) {
                _p1 = new Point_2D(p);
            } else {
                rotate(p);
                _p1 = null;
            }
        }
        if (_mode.equals("Polygon")) {
            if (_pp == null) {
                _pp = new Polygon_2D();
            }
            _p1 = new Point_2D(p);
            _pp.add(p);
        }

        if (_mode.equals("Triangle")) {
            if (_pp == null) {
                _pp = new Polygon_2D();
            }
            _p1 = new Point_2D(p);
            _pp.add(p);
            if (_pp.getAllPoints().length == 3) {
                Point_2D[] pp = _pp.getAllPoints();
                Triangle_2D tt = new Triangle_2D(pp[0], pp[1], pp[2]);
                GUI_Shape s = new GUIShape(tt, _fill, _color, 0);
                _shapes.add(s);
                _pp = null;
                _p1 = null;
                _gs = null;
            }
        }
        if (_mode.equals("Point")) {
            select(p);
        }
        //
        if (_mode.equals("Scale_90%")) {
            scale(p, 0.9);
        }
        if (_mode.equals("Scale_110%")) {
            scale(p, 1.10);
        }
        drawShapes();
    }

    /**
     * Scales the selected shapes around a given point by a specified ratio.
     *
     * @param p     The Point_2D representing the center of scaling.
     * @param ratio The scaling ratio.
     */
    private void scale(Point_2D p, double ratio) {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            GeoShape g = s.getShape();
            if (s.isSelected() && g != null) {
                g.scale(p, ratio);
            }
        }
    }

    /**
     * Selects or deselects shapes based on a mouse click location.
     *
     * @param p The Point_2D representing the location of the mouse click.
     */
    private void select(Point_2D p) {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            GeoShape g = s.getShape();
            if (g != null && g.contains(p)) {
                s.setSelected(!s.isSelected());
            }
        }
    }

    /**
     * Moves the selected shapes based on the mouse drag movement.
     */
    private void move() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            GeoShape g = s.getShape();
            if (s.isSelected() && g != null) {
                g.translate(_p1);
            }
        }
    }

    /**
     * Copies and translates the selected shapes based on the mouse drag movement.
     */
    private void copy() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            if (s.isSelected()) {
                GUI_Shape s1 = s.copy();
                GeoShape g = s1.getShape();
                g.translate(_p1);
                _shapes.add(s1);
            }
        }
    }

    /**
     * Rotates the selected shapes based on the mouse movement.
     *
     * @param ang The Point_2D representing the location of the mouse movement.
     */
    private void rotate(Point_2D ang) {
        double dx = ang.x() - _p1.x();
        double dy = ang.y() - _p1.y();
        double da = Math.atan2(dy, dx);
        da = Math.toDegrees(da);
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            GeoShape g = s.getShape();
            if (s.isSelected()) {
                g.rotate(_p1, da);
            }
        }
    }

    /**
     * Selects all shapes in the GUI_Shape_Collection.
     */
    private void selectAll() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            s.setSelected(true);
        }
    }


    /**
     * Prints information about the selected shapes in the GUI_Shape_Collection.
     */
    private void printInfo() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            if (s.isSelected()) {
                System.out.println(i + ") " + s.toString());
            }
        }
    }

    /**
     * Deselects all shapes in the GUI_Shape_Collection.
     */
    private void selectNone() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            s.setSelected(false);
        }
    }

    /**
     * Selects or deselects all shapes in the GUI_Shape_Collection.
     */
    private void selectAnti() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            s.setSelected(!s.isSelected());
        }
    }

    /**
     * Handles the right-click event, finalizing the creation of a polygon (if in Polygon mode)
     * and updating the display accordingly.
     *
     * @param p The Point_2D representing the location of the right-click.
     */
    public void mouseRightClicked(Point_2D p) {
        if (_mode.equals("Polygon") && _pp != null) {
            GUIShape s = new GUIShape(_pp, _fill, _color, 0);
            _shapes.add(s);
        }
        _pp = null;
        _gs = null;
        _p1 = null;
        drawShapes();
    }

    /**
     * Handles the mouse movement event, updating the display based on the current mode.
     *
     * @param e The MouseEvent containing information about the mouse movement.
     */
    public void mouseMoved(MouseEvent e) {
        if (_p1 != null) {
            double x1 = StdDraw_Ex2.mouseX();
            double y1 = StdDraw_Ex2.mouseY();
            GeoShape gs = null;
            //	System.out.println("M: "+x1+","+y1);
            Point_2D p = new Point_2D(x1, y1);
            if (_mode.equals("Circle")) {
                double r = _p1.distance(p);
                gs = new Circle_2D(_p1, r);
            }
            if (_mode.equals("Rect")) {
                gs = new Rect_2D(_p1, p);
            }
            if (_mode.equals("Segment")) {
                gs = new Segment_2D(_p1, p);
            }

            if (_mode.equals("Polygon") || _mode.equals("Triangle")) {
                if (_pp == null) {
                    _pp = new Polygon_2D();
                    _pp.add(_p1);
                }
                Polygon_2D gg = new Polygon_2D(_pp);
                gg.add(p);
                gs = gg;
            }
            _gs = new GUIShape(gs, false, Color.pink, 0);
            drawShapes();
        }
    }

    /**
     * Gets the GUI_Shape_Collection associated with this instance of Ex2.
     *
     * @return The GUI_Shape_Collection containing all shapes.
     */
    @Override
    public GUI_Shape_Collection getShape_Collection() {
        return this._shapes;
    }

    /**
     * Displays the shapes with default dimension size.
     */
    @Override
    public void show() {
        show(Ex2_Const.DIM_SIZE);
    }

    /**
     * Gets information about all shapes in the GUI_Shape_Collection.
     *
     * @return A string containing information about all shapes.
     */
    @Override
    public String getInfo() {
        String ans = "";
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            ans += s.toString() + "\n";
        }
        return ans;
    }
}
