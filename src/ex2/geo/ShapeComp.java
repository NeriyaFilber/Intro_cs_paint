package ex2.geo;

import java.util.Comparator;

import ex2.ex2.Ex2_Const;
import ex2.gui.GUI_Shape;

/**
 * This class represents a Comparator over GUI_Shapes -
 * as a linear order over GUI_Shapes.
 * @author Neriya Filber
 * I.D: 211377700
 */
public class ShapeComp implements Comparator<GUI_Shape>{

	/**
	 * Represents sorting by area flag.
	 */
	public static final ShapeComp CompByArea = new ShapeComp(Ex2_Const.Sort_By_Area);

	/**
	 * Represents sorting by anti-area flag.
	 */
	public static final ShapeComp CompByAntiArea = new ShapeComp(Ex2_Const.Sort_By_Anti_Area);

	/**
	 * Represents sorting by toString flag.
	 */
	public static final ShapeComp CompByToString = new ShapeComp(Ex2_Const.Sort_By_toString);

	/**
	 * Represents sorting by anti-toString flag.
	 */
	public static final ShapeComp CompByToAntiString = new ShapeComp(Ex2_Const.Sort_By_Anti_toString);
	/**
	 * Represents sorting by perimeter flag.
	 */
	public static final ShapeComp CompByPerimeter = new ShapeComp(Ex2_Const.Sort_By_Perimeter);

	/**
	 * Represents sorting by anti-perimeter flag.
	 */
	public static final ShapeComp CompByAntiPerimeter = new ShapeComp(Ex2_Const.Sort_By_Anti_Perimeter);

	/**
	 * Represents sorting by tag flag.
	 */
	public static final ShapeComp CompByTag = new ShapeComp(Ex2_Const.Sort_By_Tag);

	/**
	 * Represents sorting by anti-tag flag.
	 */
	public static final ShapeComp CompByAntiTag = new ShapeComp(Ex2_Const.Sort_By_Anti_Tag);

	/**
	 * Represent the type of comparison.
	 */
	private int _flag;

	/**
	 * Constructs a ShapeComp with a specified flag.
	 * The flag determines the type of comparison to be performed.
	 *
	 * @param flag The comparison flag.
	 */
	public ShapeComp(int flag) {
		_flag = flag;
	}

	/**
	 * Compares two GUI_Shape objects based on the specified comparison flag.
	 *
	 * @param o1 The first object to be compared.
	 * @param o2 The second object to be compared.
	 * @return 1 if o1 is greater than o2, -1 if o1 is smaller than o2, and 0 if o1 and o2 are equal.
	 */
	@Override
	public int compare(GUI_Shape o1, GUI_Shape o2) {
		double a1=-1, a2 = -1;
		GeoShape s1 = o1.getShape(), s2 = o2.getShape();
		int ans =0;
		if(_flag == Ex2_Const.Sort_By_Area) {
			a1 = s1.area();
			a2 = s2.area();
			if(a1>a2) {ans=1;}
			if(a1<a2) {ans=-1;}
		}
		if(_flag == Ex2_Const.Sort_By_Anti_Area){
			a1 = s1.area();
			a2 = s2.area();
			if (a1 < a2){ans=1;}
			if (a1 > a2){ans=-1;}
		}
		if(_flag == Ex2_Const.Sort_By_Perimeter){
			a1 = s1.perimeter();
			a2 = s2.perimeter();
			if (a1 > a2){ans=1;}
			if (a1<a2) {ans = -1;}
		}
		if (_flag == Ex2_Const.Sort_By_Anti_Perimeter){
			a1 = s1.perimeter();
			a2 = s2.perimeter();
			if (a1 < a2){ans=1;}
			if (a1 > a2){ans=-1;}
		}
		if(_flag == Ex2_Const.Sort_By_toString) {
			ans = o1.toString().compareTo(o2.toString());
		}
		if(_flag == Ex2_Const.Sort_By_Anti_toString){
			ans = o2.toString().compareTo(o1.toString());
		}
		if (_flag == Ex2_Const.Sort_By_Tag){
			int t1 = o1.getTag();
			int t2 = o2.getTag();
			if(t1> t2){ans=1;}
			if (t1<t2){ans=-1;}
		}
		if (_flag == Ex2_Const.Sort_By_Anti_Tag){
			int t1 = o1.getTag();
			int t2 = o2.getTag();
			if(t1<t2){ans=1;}
			if (t1>t2){ans=-1;}
		}
		return ans;
	}
}
