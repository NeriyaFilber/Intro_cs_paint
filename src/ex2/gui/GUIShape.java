package ex2.gui;
/**
 * Implements the GUI_Shape interface to represent a shape in the GUI.
 * @author Neriya Filber
 *  * I.D: 211377700
 */
import ex2.geo.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Objects;


public class GUIShape implements GUI_Shape{
	/**
	 * Represent 2D geo shape.
	 */
	private GeoShape _g = null;
	/**
	 * Represent the fill of the geo shape.
	 */
	private boolean _fill;
	/**
	 * Represent the color of the geo shape.
	 */
	private Color _color;
	/**
	 * represent the tag of the geo shape.
	 */
	private int _tag;
	/**
	 * Represent whether the geo shape is selected.
	 */
	private boolean _isSelected;

	/**
	 * Constructs a GUIShape object with the specified geometric shape, fill status, color, and tag.
	 *
	 * @param g  The geometric shape.
	 * @param f  Whether the shape is filled.
	 * @param c  The color of the shape.
	 * @param t  The tag associated with the shape.
	 */
	public GUIShape(GeoShape g, boolean f, Color c, int t) {
		_g = null;
		if (g!=null) {_g = g.copy();}
		_fill= f;
		_color = c;
		_tag = t;
		_isSelected = false;
	}

	/**
	 * Constructs a GUIShape object by copying another GUIShape.
	 *
	 * @param ot The GUIShape to copy.
	 */
	public GUIShape(GUIShape ot) {
		this(ot._g, ot._fill, ot._color, ot._tag);
	}

	/**
	 * Constructs a GUIShape object from a string representation.
	 *
	 * @param s A string containing the details of the GUIShape.
	 */
	public GUIShape(String s) {
		String[] a = s.split(",");
		if(Objects.equals(a[0], "GUIShape")){
			a = Arrays.copyOfRange( a, 1, a.length);
		}
		_color = decodeColor(Integer.parseInt(a[0]));
		_fill = Boolean.parseBoolean(a[1]);
		_tag = Integer.parseInt(a[2]);
		if(Objects.equals(a[3], "Segment_2D")){
			_g = new Segment_2D(a[4] + "," + a[5] + "," + a[6] + "," + a[7]);
		}
		if (Objects.equals(a[3], "Triangle_2D")) {
			_g = new Triangle_2D(a[4]+ "," + a[5]+ "," + a[6] + "," + a[7]+ "," + a[8] + "," + a[9]);
		}
		if (Objects.equals(a[3], "Rect_2D")) {
			_g = new Rect_2D(new Point_2D(a[4] + "," + a[5]), new Point_2D(a[6] + "," + a[7]), new Point_2D(a[8] + "," + a[9]),new Point_2D(a[10] + "," + a[11]));
		}
		if (Objects.equals(a[3], "Circle_2D")) {
			_g = new Circle_2D(a[4] + "," + a[5] + "," + a[6]);
		}
		if (Objects.equals(a[3], "Polygon_2D")) {
			String points = "";
			for (int i = 4; i < a.length; i++) {
				if (i != a.length-1)
				points = points + a[i] + ",";
				else {
					points += a[i];
				}
			}
			_g = new Polygon_2D(points);
		}
	}

	/**
	 * Gets the geometric shape.
	 *
	 * @return The geometric shape.
	 */
	@Override
	public GeoShape getShape() {
		return _g;
	}

	/**
	 * Sets the geometric shape of this GUIShape.
	 *
	 * @param g The new geometric shape.
	 */
	@Override
	public void setShape(GeoShape g) {
		_g = g;
	}

	/**
	 * Checks if the shape is filled.
	 *
	 * @return True if the shape is filled, false otherwise.
	 */
	@Override
	public boolean isFilled() {
		return _fill;
	}

	/**
	 * Sets the fill status of the shape.
	 *
	 * @param filled The new fill status.
	 */
	@Override
	public void setFilled(boolean filled) {
		_fill = filled;
	}

	/**
	 * Gets the color of the shape.
	 *
	 * @return The color of the shape.
	 */
	@Override
	public Color getColor() {
		return _color;
	}

	/**
	 * Sets the color of the shape.
	 *
	 * @param cl The new color.
	 */
	@Override
	public void setColor(Color cl) {
		_color = cl;
	}

	/**
	 * Gets the tag associated with the shape.
	 *
	 * @return The tag.
	 */
	@Override
	public int getTag() {
		return _tag;
	}

	/**
	 * Sets the tag associated with the shape.
	 *
	 * @param tag The new tag.
	 */
	@Override
	public void setTag(int tag) {
		_tag = tag;
		
	}

	/**
	 * Creates a deep copy of this GUIShape.
	 *
	 * @return A deep copy of this GUIShape.
	 */
	@Override
	public GUI_Shape copy() {
        return new GUIShape(this);
	}

	/**
	 * Converts the GUIShape to a string representation.
	 *
	 * @return A string containing the details of the GUIShape.
	 */
	@Override
	public String toString() {
		String ans = ""+this.getClass().getSimpleName()+","+colorEncoding(_color)+","+_fill+","+_tag+","+this._g.getClass().getSimpleName()+","+_g.toString();
		return ans;
	}

	/**
	 * Encodes a color as an integer.
	 *
	 * @param c The color to encode.
	 * @return The encoded integer.
	 */
	public static int colorEncoding(Color c) {
		int r = c.getRed();
		int b = c.getBlue();
		int g = c.getGreen();
		int ce = r * 256 * 256 + g * 256 + b;
		return ce;
	}

	/**
	 * Decodes an integer into a color.
	 *
	 * @param ce The integer to decode.
	 * @return The decoded Color object.
	 */
	public static Color decodeColor(int ce) {
		int r = ce / (256 * 256);
		int g = (ce / 256) % 256;
		int b = ce % 256;
		return new Color(r, g, b);
	}

	/**
	 * Checks if the GUIShape is selected.
	 *
	 * @return True if the GUIShape is selected, false otherwise.
	 */
		@Override
	public boolean isSelected() {
		return this._isSelected;
	}

	/**
	 * Checks if this GUIShape is equal to another object.
	 *
	 * @param o The object to compare with.
	 * @return True if the GUIShape objects are equal, false otherwise.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		GUIShape guiShape = (GUIShape) o;
		return _fill == guiShape._fill && _tag == guiShape._tag && (_isSelected == guiShape._isSelected) && Objects.equals(_g, guiShape._g) && Objects.equals(_color, guiShape._color);
	}

	/**
	 * Sets the selection status of the GUIShape.
	 *
	 * @param s The new selection status.
	 */
	@Override
	public void setSelected(boolean s) {
		this._isSelected = s;
	}
}
