package ex2.geo;

import ex2.ex2.Ex2_Const;

/**
 * This class represents a 2D Triangle in the plane.
 * Ex2: you should implement this class!
 * @author Neriya Filber
 * I.D: 211377700
 *
 */
public class Triangle_2D implements GeoShape{

	/**
	 * Three corner points of the triangle.
	 */
	private Point_2D _p1, _p2, _p3;

	/**
	 * Constructs a 2D Triangle with three specified points.
	 *
	 * @param p1 The first vertex of the triangle.
	 * @param p2 The second vertex of the triangle.
	 * @param p3 The third vertex of the triangle.
	 */
	public Triangle_2D(Point_2D p1, Point_2D p2, Point_2D p3) {
		this._p1 = p1;
		this._p2 = p2;
		this._p3 = p3;
	}

	/**
	 * Constructs a copy of the given 2D Triangle.
	 *
	 * @param t1 The triangle to copy.
	 */
	public Triangle_2D(Triangle_2D t1){
		Point_2D[] triangle = t1.getAllPoints();
		this._p1 = new Point_2D(triangle[0]);
		this._p2 = new Point_2D(triangle[1]);
		this._p3 = new Point_2D(triangle[2]);
	}

	/**
	 * Constructs a 2D Triangle from a string representation.
	 *
	 * @param s A string containing the coordinates of the three vertices separated by commas.
	 */
	public Triangle_2D (String s){
		String[] a = s.split(",");
		String s1 = a[0] + "," + a[1];
		String s2 = a[2] + "," + a[3];
		String s3 = a[4] + "," + a[5];
		_p1 = new Point_2D(s1);
		_p2 = new Point_2D(s2);
		_p3 = new Point_2D(s3);
	}

	/**
	 * Gets an array of all the vertices of the triangle.
	 *
	 * @return An array of Point_2D objects representing the vertices.
	 */
	public Point_2D[] getAllPoints() {
        return new Point_2D[]{new Point_2D(this._p1), new Point_2D(this._p2), new Point_2D(this._p3)};
	}

	/**
	 * Checks if the given point is inside the triangle.
	 *
	 * @param ot The point to check.
	 * @return True if the point is inside the triangle, false otherwise.
	 */
	@Override
	public boolean contains(Point_2D ot) {
		Triangle_2D t1 = new Triangle_2D(ot,_p1, _p2);
		Triangle_2D t2 = new Triangle_2D(ot,_p2, _p3);
		Triangle_2D t3 = new Triangle_2D(ot,_p1, _p3);
		double arT1 = t1.area();
		double arT2 = t2.area();
		double arT3 = t3.area();
        return arT1 + arT2 + arT3 < this.area() + Ex2_Const.EPS;
    }

	/**
	 * Calculates the area of the triangle using Heron formula.
	 *
	 * @return The area of the triangle.
	 */
	@Override
	public double area() {
		double dist1 = _p1.distance(_p2);
		double dist2 = _p2.distance(_p3);
		double dist3 = _p3.distance(_p1);
		double sNumber = (dist1 + dist2 + dist3) / 2.0;
		return Math.sqrt(sNumber * (sNumber - dist1) * (sNumber - dist2) * (sNumber - dist3));
	}

	/**
	 * Calculates the perimeter of the triangle.
	 *
	 * @return The perimeter of the triangle.
	 */
	@Override
	public double perimeter() {
		double dist1 = _p1.distance(_p2);
		double dist2 = _p2.distance(_p3);
		double dist3 = _p3.distance(_p1);
        return dist1 + dist2 + dist3;
	}

	/**
	 * Translates the triangle by the specified vector.
	 *
	 * @param vec The translation vector.
	 */
	@Override
	public void translate(Point_2D vec) {
		_p1.move(vec);
		_p2.move(vec);
		_p3.move(vec);
	}

	/**
	 * Creates a copy of the triangle.
	 *
	 * @return A new Triangle_2D object that is a copy of this triangle.
	 */
	@Override
	public GeoShape copy() {
		return new Triangle_2D(this);
	}

	/**
	 * Scales the triangle with respect to a center point and a given ratio.
	 *
	 * @param center The center point for scaling.
	 * @param ratio  The scaling ratio.
	 */
	@Override
	public void scale(Point_2D center, double ratio) {
		_p1.scale(center, ratio);
		_p2.scale(center, ratio);
		_p3.scale(center, ratio);
	}

	/**
	 * Rotates the triangle around a center point by a specified angle in degrees.
	 *
	 * @param center         The center point for rotation.
	 * @param angleDegrees  The angle of rotation in degrees.
	 */
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		_p1.rotate(center, angleDegrees);
		_p2.rotate(center, angleDegrees);
		_p3.rotate(center, angleDegrees);
	}

	/**
	 * Returns a string representation of the triangle.
	 *
	 * @return A string containing the coordinates of the three vertices separated by commas.
	 */
	@Override
	public String toString() {
		return 	_p1.toString() +
				"," + _p2.toString() +
				"," + _p3.toString() ;
	}

	/**
	 * Checks if this triangle is equal to another object.
	 * Two triangles are considered equal if their vertices are equal.
	 *
	 * @param o The object to compare with.
	 * @return True if the triangles are equal, false otherwise.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Triangle_2D triangle2D = (Triangle_2D) o;
		Point_2D[] myPoints1 = triangle2D.getAllPoints();
		Point_2D[] myPoints2 = this.getAllPoints();
        for (int i = 0; i < myPoints1.length; i++) {
			boolean insiderFlag = false;
			for (int j = 0; j < myPoints2.length; j++) {
				if (myPoints1[i].close2equals(myPoints2[j], Point_2D.EPS)) {
					insiderFlag = true;
					break;
				}
			}
			if (insiderFlag == false) return false;
		}

		return true;
	}
}
