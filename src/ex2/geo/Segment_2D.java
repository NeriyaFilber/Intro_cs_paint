package ex2.geo;

import ex2.ex2.Ex2_Const;

import java.util.Objects;

/**
 * This class represents a 2D segment on the plane.
 * @author Neriya Filber
 * I.D: 211377700
 */
public class Segment_2D implements GeoShape{

	/**
	 * Two vertices points of the segment.
	 */
	private Point_2D _pointA, _pointB;

	/**
	 * Constructs a Segment_2D with two given points.
	 *
	 * @param a The first point of the segment.
	 * @param b The second point of the segment.
	 */
	public Segment_2D(Point_2D a, Point_2D b) {
		this._pointA = new Point_2D(a);
		this._pointB = new Point_2D(b);
	}

	/**
	 * Constructs a Segment_2D by copying another Segment_2D object.
	 *
	 * @param t1 The Segment_2D object to copy.
	 */
	public Segment_2D(Segment_2D t1) {
		_pointA = new Point_2D(t1._pointA);
		_pointB = new Point_2D(t1._pointB);
	}

	/**
	 * Constructs a Segment_2D from a string representation.
	 *
	 * @param t The string representation of the segment in the format "x1,y1,x2,y2".
	 */
	public Segment_2D(String t){
		String[] a = t.split(",");
		String s1 = a[0] + "," + a[1];
		String s2 = a[2] + "," + a[3];
		_pointA = new Point_2D(s1);
		_pointB = new Point_2D(s2);
	}

	/**
	 * Gets the first point of the segment.
	 *
	 * @return The first point of the segment.
	 */
	public Point_2D get_p1() {
		return new Point_2D(_pointA);
	}

	/**
	 * Gets the second point of the segment.
	 *
	 * @return The second point of the segment.
	 */
	public Point_2D get_p2() {
		return new Point_2D(_pointB);
	}

	/**
	 * Checks if the segment contains a given point.
	 * A point is considered to be contained if the sum of its distances to the two end points
	 * is less than the length of the segment plus a small epsilon value.
	 *
	 * @param ot The point to check for containment.
	 * @return True if the point is contained, false otherwise.
	 */
	@Override
	public boolean contains(Point_2D ot) {
		double dist = _pointA.distance(_pointB);
		double d1 = _pointA.distance(ot);
		double d2 = ot.distance(_pointB);
        return d1+d2 < dist + Ex2_Const.EPS;
	}

	/**
	 * Gets the area of the segment.
	 * Since a segment is a 1-dimensional object, its area is zero.
	 *
	 * @return The area of the segment (always zero).
	 */
	@Override
	public double area() {
		return 0;
	}

	/**
	 * Gets the perimeter of the segment.
	 * The perimeter is twice the length of the segment.
	 *
	 * @return The perimeter of the segment.
	 */
	@Override
	public double perimeter() {
		double d = _pointA.distance(_pointB);
		return 2 * d;
	}

	/**
	 * Translates the segment by a given vector.
	 *
	 * @param vec The vector by which to translate the segment.
	 */
	@Override
	public void translate(Point_2D vec) {
		_pointA.move(vec);
		_pointB.move(vec);
	}

	/**
	 * Creates a deep copy of the segment.
	 *
	 * @return A deep copy of the segment.
	 */
	@Override
	public GeoShape copy() {
		return new Segment_2D(this);
	}

	/**
	 * Scales the segment with respect to a given center and ratio.
	 *
	 * @param center The center of scaling.
	 * @param ratio  The scaling ratio.
	 */
	@Override
	public void scale(Point_2D center, double ratio) {
		_pointA.scale(center, ratio);
		_pointB.scale(center, ratio);
	}

	/**
	 * Rotates the segment around a given center by a specified angle in degrees.
	 *
	 * @param center        The center of rotation.
	 * @param angleDegrees The angle of rotation in degrees.
	 */
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		_pointA.rotate(center, angleDegrees);
		_pointB.rotate(center, angleDegrees);
	}

	/**
	 * Returns a string representation of the segment in the format "x1,y1,x2,y2".
	 *
	 * @return A string representation of the segment.
	 */
	@Override
	public String toString(){
		return _pointA.toString() + "," + _pointB.toString();
	}

	/**
	 * Checks if two Segment_2D objects are equal.
	 * Two segments are considered equal if they share the same end points (in any order).
	 *
	 * @param o The object to compare with the Segment_2D.
	 * @return True if the objects are equal, false otherwise.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Segment_2D segment2D = (Segment_2D) o;
		if(_pointA.close2equals(segment2D.get_p1(), Ex2_Const.EPS) && _pointB.close2equals(segment2D.get_p2(), Ex2_Const.EPS) ||
				_pointB.close2equals(segment2D.get_p1(), Ex2_Const.EPS) && _pointA.close2equals(segment2D.get_p2(), Ex2_Const.EPS)) return true;
		return false;
	}
}