package ex2.geo;

import ex2.ex2.Ex2_Const;


/**
 * This class represents a 2D circle in the plane. 
 * It implements the GeoShape interface, providing methods to interact with and manipulate the circle.
 *
 * @author Neriya Filber
 * I.D: 211377700
 */

public class Circle_2D implements GeoShape{
	/**
	 * The center point of the circle.
	 */
	private Point_2D _center;

	/**
	 * The radius of the circle.
	 */
	private double _radius;

	/**
	 * Constructs a Circle_2D with a given center and radius.
	 *
	 * @param cen The center point of the circle.
	 * @param rad The radius of the circle.
	 */
	public Circle_2D(Point_2D cen, double rad) {
		this._center = new Point_2D(cen);
		this._radius = rad;
	}

	/**
	 * Constructs a Circle_2D from a string representation.
	 *
	 * @param s The string representation of the circle in the format "x,y, radius".
	 */
	public Circle_2D(String s){
		String[] a = s.split(",");
		String s1 = a[0] + "," + a[1];
		_center = new Point_2D(s1);
		a[2] = a[2].replace(" ", "");
		_radius = Double.parseDouble(a[2]);
	}

	/**
	 * Copy constructor for Circle_2D.
	 *
	 * @param c The Circle_2D to copy.
	 */
	public Circle_2D(Circle_2D c) {this(c.getCenter(), c.getRadius());}

	/**
	 * Gets the radius of the circle.
	 *
	 * @return The radius of the circle.
	 */
	public double getRadius() {return this._radius;}

	/**
	 * Gets a copy of the center point of the circle.
	 *
	 * @return A copy of the center point.
	 */
	public Point_2D getCenter(){return new Point_2D(_center);}

	/**
	 * Returns a string representation of the Circle_2D.
	 *
	 * @return A string representation in the format "center, radius".
	 */
	 @Override
	 public String toString(){
			return _center.toString()+", "+_radius;
		}

	/**
	 * Checks if the circle contains a given point.
	 *
	 * @param ot The point to check for containment.
	 * @return True if the point is inside the circle, false otherwise.
	 */
	@Override
	public boolean contains(Point_2D ot) {
		double dist = _center.distance(ot);
        return dist < _radius + Ex2_Const.EPS;
	}

	/**
	 * Calculates the area of the circle.
	 *
	 * @return The area of the circle.
	 */
	@Override
	public double area() {
        return Math.PI * Math.pow(_radius, 2);
	}

	/**
	 * Calculates the perimeter (circumference) of the circle.
	 *
	 * @return The perimeter of the circle.
	 */
	@Override
	public double perimeter() {
        return 2 * Math.PI * _radius;
	}

	/**
	 * Translates the circle by a given vector.
	 *
	 * @param vec The translation vector.
	 */
	@Override
	public void translate(Point_2D vec) {
		_center.move(vec);
	}

	/**
	 * Creates a deep copy of the Circle_2D.
	 *
	 * @return A deep copy of the Circle_2D.
	 */
	@Override
	public GeoShape copy() {return new Circle_2D(this);}

	/**
	 * Scales the circle with respect to a given center and ratio.
	 *
	 * @param center The center point for scaling.
	 * @param ratio  The scaling ratio.
	 */
	@Override
	public void scale(Point_2D center, double ratio) {
		_center.scale(center, ratio);
		_radius = _radius * ratio;
	}

	/**
	 * Rotates the circle around a given center by a specified angle in degrees.
	 *
	 * @param center         The center point for rotation.
	 * @param angleDegrees   The angle of rotation in degrees.
	 */
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		_center.rotate(center, angleDegrees);
	}

	/**
	 * Checks if two Circle_2D objects are equal.
	 *
	 * @param o The object to compare with the Circle_2D.
	 * @return True if the objects are equal, false otherwise.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Circle_2D circle2D = (Circle_2D) o;
		if (Double.compare(_radius, circle2D._radius) == 0 && _center.close2equals(circle2D._center,Ex2_Const.EPS)){return true;}
		return false;
	}
}
