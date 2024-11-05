package ex2.geo;

import ex2.ex2.Ex2_Const;

/**
 * This class represents a 2D rectangle defined by two opposite corner points.
 * It implements the GeoShape interface, providing methods to interact with and manipulate the rectangle.
 *
 * @author Neriya Filber
 * I.D: 211377700
 */
public class Rect_2D implements GeoShape {

	/**
	 * Four corner points of the rectangle.
	 */
	private Point_2D _p1, _p2, _p3, _p4;

	/**
	 * Constructs a rectangle axis parallel given two opposite corner points.
	 *
	 * @param p1 The first corner point.
	 * @param p2 The second corner point.
	 */
	public Rect_2D(Point_2D p1, Point_2D p2) {
		this._p1 = p1;
		this._p3 = p2;
		this._p2 = new Point_2D(this._p1.x(), this._p3.y());
		this._p4 = new Point_2D(this._p3.x(), this._p1.y());
	}

	/**
	 * Constructs a rectangle given all four corner points.
	 *
	 * @param p1 The first corner point.
	 * @param p2 The second corner point.
	 * @param p3 The third corner point.
	 * @param p4 The fourth corner point.
	 */
	public Rect_2D(Point_2D p1, Point_2D p2, Point_2D p3, Point_2D p4){
		this._p1 = p1;
		this._p2 = p2;
		this._p3 = p3;
		this._p4 = p4;
	}

	/**
	 * Copy constructor for Rect_2D.
	 *
	 * @param t1 The Rect_2D to copy.
	 */
	public Rect_2D(Rect_2D t1) {
		Point_2D[] rect = t1.getAllPoints();
		this._p1 = rect[0];
		this._p2 = rect[1];
		this._p3 = rect[2];
		this._p4 = rect[3];
	}

	/**
	 * Constructs a rectangle from a string representation.
	 *
	 * @param s The string representation of the rectangle in the format "x1,y1,x2,y2,x3,y3,x4,y4".
	 */
	public Rect_2D(String s){
		String[] a = s.split(",");
		String s1 = a[0] + "," + a[1];
		String s2 = a[2] + "," + a[3];
		String s3 = a[4] + "," + a[5];
		String s4 = a[6] + "," + a[7];
		_p1 = new Point_2D(s1);
		_p2 = new Point_2D(s2);
		_p3 = new Point_2D(s3);
		_p4 = new Point_2D(s4);
	}

	/**
	 * Returns an array containing copies of all four corner points of the rectangle.
	 *
	 * @return An array of Point_2D representing the corner points of the rectangle.
	 */
	public Point_2D[] getAllPoints() {
		return new Point_2D[]{new Point_2D(this._p1), new Point_2D(this._p2), new Point_2D(this._p3), new Point_2D(this._p4)};
	}

	/**
	 * Checks if a given point is inside the rectangle using the sum of triangle areas approach.
	 *
	 * @param ot The point to check for containment.
	 * @return True if the point is inside the rectangle, false otherwise.
	 */
	@Override
	public boolean contains(Point_2D ot) {
		Triangle_2D t1 = new Triangle_2D(ot, _p1, _p2);
		Triangle_2D t2 = new Triangle_2D(ot, _p2, _p3);
		Triangle_2D t3 = new Triangle_2D(ot, _p3, _p4);
		Triangle_2D t4 = new Triangle_2D(ot, _p4, _p1);
		double arT1 = t1.area();
		double arT2 = t2.area();
		double arT3 = t3.area();
		double arT4 = t4.area();
		return arT1 + arT2 + arT3 + arT4 < this.area() + Ex2_Const.EPS;
	}

	/**
	 * Calculates the area of the rectangle.
	 *
	 * @return The area of the rectangle.
	 */
	@Override
	public double area() {
		double length = _p1.distance(_p2);
		double width = _p3.distance(_p2);
		return length * width;
	}

	/**
	 * Calculates the perimeter of the rectangle.
	 *
	 * @return The perimeter of the rectangle.
	 */
	@Override
	public double perimeter() {
		double length = _p1.distance(_p2);
		double width = _p3.distance(_p2);
		return (2 * length) + (2 * width);
	}

	/**
	 * Translates the rectangle by a given vector.
	 *
	 * @param vec The translation vector.
	 */
	@Override
	public void translate(Point_2D vec) {
		_p1.move(vec);
		_p2.move(vec);
		_p3.move(vec);
		_p4.move(vec);
	}

	/**
	 * Creates a deep copy of the rectangle.
	 *
	 * @return A deep copy of the rectangle.
	 */
	@Override
	public GeoShape copy() {
		return new Rect_2D(this);
	}

	/**
	 * Scales the rectangle with respect to a given center and ratio.
	 *
	 * @param center The center point for scaling.
	 * @param ratio  The scaling ratio.
	 */
	@Override
	public void scale(Point_2D center, double ratio) {
		_p1.scale(center, ratio);
		_p2.scale(center, ratio);
		_p3.scale(center, ratio);
		_p4.scale(center, ratio);
	}

	/**
	 * Rotates the rectangle around a given center by a specified angle in degrees.
	 *
	 * @param center        The center point for rotation.
	 * @param angleDegrees  The angle of rotation in degrees.
	 */
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		_p1.rotate(center, angleDegrees);
		_p2.rotate(center, angleDegrees);
		_p3.rotate(center, angleDegrees);
		_p4.rotate(center, angleDegrees);
	}

	/**
	 * Returns a string representation of the rectangle in the format "x1,y1,x2,y2,x3,y3,x4,y4".
	 *
	 * @return A string representation of the rectangle.
	 */
	@Override
	public String toString() {
		return  _p1.toString() +
				"," + _p2.toString() +
				"," + _p3.toString() +
				"," + _p4.toString();
	}

	/**
	 * Checks if two Rect_2D objects are equal.
	 * Two rectangles are considered equal if they have the same corner points (in any order),
	 * and their area and perimeter are equal.
	 *
	 * @param o The object to compare with the Rect_2D.
	 * @return True if the objects are equal, false otherwise.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {return true;}
		if (o == null || getClass() != o.getClass()) {return false;}
		Rect_2D rect2D = (Rect_2D) o;
		Point_2D[] myArr1 = rect2D.getAllPoints();
		Point_2D[] myArr2 = this.getAllPoints();
		for (int i = 0; i < myArr1.length; i++) {
			boolean insiderFlag = false;
			for (int j = 0; j < myArr2.length; j++) {
				if (myArr1[i].close2equals(myArr2[j], Point_2D.EPS)) {
					insiderFlag = true;
					break;
				}
			}
			if (insiderFlag == false) {return false;}
		}
		if (rect2D.area() == this.area() && rect2D.perimeter() == this.perimeter()) {return true;}
		return false;
	}
}
