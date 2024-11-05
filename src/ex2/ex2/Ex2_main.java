package ex2.ex2;

import ex2.geo.*;
import ex2.gui.Ex2;
import ex2.gui.GUIShape;
import ex2.gui.GUI_Shape;

import java.awt.*;

/**
 * This class is a very simple main for starting Ex2 functionality.
 * @author Neriya Filber
 * I.D: 211377700
 */
public class Ex2_main {

	public static void main(String[] args) {
		simpleShapes();
		loadDemo();
	}

	public static void simpleShapes() {
		Ex2 ex2 = Ex2.getInstance();
		GUI_Shape_Collection shapes = ex2.getShape_Collection();
		ex2.init(shapes);
		ex2.show();
	}



	public static void loadDemo() {
		Ex2 ex2 = Ex2.getInstance();
		GUI_Shape_Collection shapes = ex2.getShape_Collection();
		String file = "C:\\Users\\brhva\\IdeaProjects\\Ex2\\src\\ex2\\ex2\\ex2Layout.txt"; //make sure the file is your working directory.
		shapes.load(file);
		ex2.init(shapes);
		ex2.show();
	}

}
