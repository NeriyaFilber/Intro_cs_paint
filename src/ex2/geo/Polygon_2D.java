package ex2.geo;

import ex2.ex2.Ex2_Const;

import java.util.Arrays;

/**
 * This class represents a 2D polygon in the plane, defined by a collection of vertices.
 * It implements the GeoShape interface, providing methods to interact with and manipulate the polygon.
 * Ex2: you should update this class!
 *
 * @author Neriya Filber
 * I.D: 211377700
 */
public class Polygon_2D implements GeoShape {

	/**
	 * Array of Point_2D representing the vertices of the polygon.
	 */
	Point_2D[] _points;

	/**
	 * Constructs an empty Polygon_2D with no vertices.
	 */
	public Polygon_2D() {
		this._points = new Point_2D[0];
	}

	/**
	 * Copy constructor for Polygon_2D.
	 *
	 * @param po The Polygon_2D to copy.
	 */
	public Polygon_2D(Polygon_2D po) {
		this._points = new Point_2D[po._points.length];
		for (int i = 0; i < po._points.length; i++) {
			this._points[i] = new Point_2D(po._points[i]);
		}
	}

	/**
	 * Constructs a Polygon_2D from a string representation.
	 *
	 * @param s The string representation of the polygon in the format "x1,y1,x2,y2,...,xn,yn".
	 */
	public Polygon_2D(String s){
		this(new Polygon_2D());
		String[] a = s.split(",");
		for (int i = 0; i < a.length-1; i= i + 2) {
			String s1 = a[i] + "," + a[i+1];
			this.add(new Point_2D(s1));
		}
	}

	public Polygon_2D(Point_2D[] points) {
		this._points = Arrays.copyOf(points, points.length);
	}

	/**
	 * Returns an array containing copies of all the vertices of the polygon.
	 *
	 * @return An array of Point_2D representing the vertices of the polygon.
	 */
	public Point_2D[] getAllPoints() {
		Point_2D[] co = new Point_2D[this._points.length];
		for (int i = 0; i < this._points.length; i++) {
			co[i] = new Point_2D(_points[i]);
		}
		return co;
	}

	/**
	 * Adds a vertex to the polygon.
	 *
	 * @param p The Point_2D representing the new vertex.
	 */
	public void add(Point_2D p) {
		if (_points != null) {
			boolean flag = true;
            for (Point_2D point : _points) {
                if (point.x() == p.x() && point.y() == p.y()) {
                    flag = false;
                }
            }
			if (flag) {
				_points = Arrays.copyOf(_points, _points.length + 1);
				_points[_points.length - 1] = new Point_2D(p);
			}
		}
	}

	/**
	 * Returns a string representation of the Polygon_2D.
	 *
	 * @return A string representation in the format "x1,y1,x2,y2,...,xn,yn".
	 */
	@Override
	public String toString() {
		Point_2D[] myArr = this.getAllPoints();
		String outPut = "";
		for (int i = 0; i < myArr.length; i++) {
			if (i == myArr.length -1){
				outPut = outPut + myArr[i].toString();
			}
			else {
				outPut = outPut + myArr[i].toString() + ",";
			}
		}
		return outPut;
	}

	/**
	 * Checks if the polygon contains a given point.
	 *
	 * @param ot The point to check for containment.
	 * @return True if the point is inside the polygon, false otherwise.
	 */
	@Override
	public boolean contains(Point_2D ot) {   //test the error by epsilon
			double[] offsets = {0, -Ex2_Const.EPS, Ex2_Const.EPS};
			for (double offsetX : offsets) {
				for (double offsetY : offsets) {
					if (this.isContains(new Point_2D(ot.x() + offsetX, ot.y() + offsetY))) {
						return true;
					}
				}
			}

			return false;
		}

	/**
	 * Calculates the area of the polygon using shoelace formula.
	 *
	 * @return The area of the polygon.
	 */
	@Override
	public double area() {
		double sum1 = 0;
		double sum2 = 0;
		for (int i = 0; i < _points.length -1; i++) {
				sum1 += _points[i].x() * _points[i + 1].y();
			}

		for (int i = 0; i < _points.length -1; i++) {
				sum2 += _points[i].y() * _points[i+1].x();
		}
		sum1 += _points[_points.length -1].x() * _points[0].y();
		sum2 += _points[_points.length -1].y() * _points[0].x();

		return 0.5 * Math.abs(sum1 - sum2);
	}

	/**
	 * Calculates the perimeter of the polygon.
	 *
	 * @return The perimeter of the polygon.
	 */
	@Override
	public double perimeter() {
		double dist = 0;
		for (int i = 0; i < this._points.length -1; i++) {
				dist += _points[i].distance(_points[i+1]);
        }
		dist += _points[_points.length -1].distance(_points[0]);
		return dist;
	}

	/**
	 * Translates the polygon by a given vector.
	 *
	 * @param vec The translation vector.
	 */
	@Override
	public void translate(Point_2D vec) {
		for (int i = 0; i < _points.length; i++){
			_points[i].move(vec);
		}
	}

	/**
	 * Creates a deep copy of the Polygon_2D.
	 *
	 * @return A deep copy of the Polygon_2D.
	 */
	@Override
	public GeoShape copy() {return new Polygon_2D(this);}

	/**
	 * Scales the polygon with respect to a given center and ratio.
	 *
	 * @param center The center point for scaling.
	 * @param ratio  The scaling ratio.
	 */
	@Override
	public void scale(Point_2D center, double ratio) {
		for (int i = 0; i < this._points.length; i++) {
			this._points[i].scale(center, ratio);
		}
	}

	/**
	 * Rotates the polygon around a given center by a specified angle in degrees.
	 *
	 * @param center        The center point for rotation.
	 * @param angleDegrees  The angle of rotation in degrees.
	 */
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		for (int i = 0; i < _points.length; i++) {
			_points[i].rotate(center, angleDegrees);
		}
	}

	/**
	 * Checks if a given point is inside the polygon using the ray casting algorithm.
	 *
	 * @param ot The point to check for containment.
	 * @return True if the point is inside the polygon, false otherwise.
	 */
	private boolean isContains(Point_2D ot){
		boolean inside = false;

		for (int i = 0, j = _points.length - 1; i < _points.length; j = i++) {
			double x1 = _points[i].x();
			double y1 = _points[i].y();
			double x2 = _points[j].x();
			double y2 = _points[j].y();

			if (((y1 <= ot.y() && ot.y() < y2) || (y2 <= ot.y() && ot.y() < y1))
					&& (ot.x() > (x2 - x1) * (ot.y() - y1) / (y2 - y1) + x1 + Ex2_Const.EPS)) {
				inside = !inside;
			}
		}
		return inside;
	}

	/**
	 * Checks if two Polygon_2D objects are equal.
	 * Two polygons are considered equal if they have the same number of vertices,
	 * and each vertex of the calling polygon is close2equals to a vertex in the other polygon.
	 * Additionally, the area and perimeter of the polygons must be equal for them to be considered equal.
	 *
	 * @param o The object to compare with the Polygon_2D.
	 * @return True if the objects are equal, false otherwise.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Polygon_2D polygon2D = (Polygon_2D) o;
		if (polygon2D._points.length !=_points.length) return false;
		for (int i = 0; i < _points.length; i++) {
			boolean insiderFlag = false;
			for (int j = 0; j < polygon2D._points.length; j++) {
				if (_points[i].close2equals(polygon2D._points[j], Point_2D.EPS)) {
					insiderFlag = true;
					break;
				}
			}
			if (insiderFlag == false) return false;
		}
		if(polygon2D.area() == this.area() && polygon2D.perimeter() == this.perimeter()) return true ;
		return false;
	}
}
