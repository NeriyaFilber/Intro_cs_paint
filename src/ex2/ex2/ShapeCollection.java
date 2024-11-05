package ex2.ex2;

import ex2.gui.GUIShape;
import ex2.gui.GUI_Shape;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * This class represents a collection of GUI_Shape object.
 * It provides various methods to manipulate and manage a collection of shapes.
 * @author Neriya Filber
 * I.D: 211377700 *
 */


public class ShapeCollection implements GUI_Shape_Collection {

	/**
	 * Internal storage for GUI_Shape objects.
	 */
	private ArrayList<GUI_Shape> _shapes;

	/**
	 * Constructs an empty ShapeCollection.
	 */
	public ShapeCollection() {
		_shapes = new ArrayList<GUI_Shape>();
	}

	/**
	 * Retrieves the GUI_Shape at the specified index.
	 *
	 * @param i Index of the GUI_Shape to retrieve.
	 * @return The GUI_Shape at the specified index.
	 */
	@Override
	public GUI_Shape get(int i) {
		return _shapes.get(i);
	}

	/**
	 * Retrieves the number of elements in the ShapeCollection.
	 *
	 * @return The size of the ShapeCollection.
	 */
	@Override
	public int size() {
		return _shapes.size();
	}

	/**
	 * Removes the GUI_Shape at the specified index from the ShapeCollection.
	 *
	 * @param i Index of the GUI_Shape to remove.
	 * @return The removed GUI_Shape element.
	 */
	@Override
	public GUI_Shape removeElementAt(int i) {
		GUI_Shape removedElement = _shapes.get(i);
		_shapes.remove(i);
		return removedElement;
	}

	/**
	 * Adds a GUI_Shape at the specified index in the ShapeCollection.
	 *
	 * @param s The GUI_Shape to add.
	 * @param i Index at which the GUI_Shape should be added.
	 */
	@Override
	public void addAt(GUI_Shape s, int i) {
		if (s != null && s.getShape() != null) {
			_shapes.add(i, s);
		}
	}

	/**
	 * Adds a GUI_Shape to the end of the ShapeCollection.
	 *
	 * @param s The GUI_Shape to add.
	 */
	@Override
	public void add(GUI_Shape s) {
		if (s != null && s.getShape() != null) {
			_shapes.add(s);
		}
	}

	/**
	 * Creates a deep copy of the ShapeCollection.
	 *
	 * @return A deep copy of the ShapeCollection.
	 */
	@Override
	public GUI_Shape_Collection copy() {
		GUI_Shape_Collection deepCopy = new ShapeCollection();
		for (int i = 0; i < this._shapes.size(); i++) {
			deepCopy.add(this._shapes.get(i).copy());
		}
		return deepCopy;
	}

	/**
	 * Sorts the elements in the ShapeCollection using the provided comparator.
	 *
	 * @param comp Comparator used for sorting the elements.
	 */
	@Override
	public void sort(Comparator<GUI_Shape> comp) {
		_shapes.sort(comp);
	}

	/**
	 * Removes all elements from the ShapeCollection.
	 */
	@Override
	public void removeAll() {
		_shapes.clear();
	}

	/**
	 * Saves the ShapeCollection to a file.
	 *
	 * @param file The file path to save the ShapeCollection.
	 */
	@Override
	public void save(String file) {
		File myFile = new File(file);
		try {
			myFile.createNewFile();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		try {
			FileWriter myWriter = new FileWriter(myFile);
			String data = "";
			for (int i = 0; i < size(); i = i + 1) {
				data += this.get(i) + "\n";
			}
			myWriter.write(data);
			myWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Loads a ShapeCollection from a file.
	 *
	 * @param file The file path from which to load the ShapeCollection.
	 */
	@Override
	public void load(String file){
		try {
			File myObj = new File(file);
			Scanner myReader = new Scanner(myObj);

			String data = "";
			while (myReader.hasNextLine()) {
				data += myReader.nextLine();
			}
			String[] dataSplited = data.split("GUIShape,");
			for (int i = 1; i < dataSplited.length; i++) {
				this.add(new GUIShape(dataSplited[i]));
			}
			myReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		};
	}

	/**
	 * Returns a string representation of the ShapeCollection.
	 *
	 * @return A string containing the string representation of each GUI_Shape in the collection.
	 */
	@Override
	public String toString() {
		String ans = "";
		for(int i=0;i<size();i=i+1) {
			ans += this.get(i) + "\n";
		}
		return ans;
	}

	/**
	 * Checks whether the current ShapeCollection is equal to another Object.
	 * Two ShapeCollections are considered equal if they contain the same set of GUI_Shape objects,
	 * regardless of the order in which the shapes are stored within the collections.
	 *
	 * @param sc The Object to compare with the current ShapeCollection.
	 * @return True if the ShapeCollections are equal, false otherwise.
	 */

	@Override
	public boolean equals(Object sc){
		if(sc == null || !(sc instanceof ShapeCollection)) {return false;}
		ShapeCollection sc2 = (ShapeCollection) sc;
		Boolean innerBool;
		for (int i = 0; i < sc2.size(); i++) {
			innerBool = false;
			for (int j = 0; j < this.size(); j++) {
				if (this.get(j).equals(sc2.get(i))) innerBool = true;
			}
			if (innerBool==false) return false;
		}
		for (int i = 0; i < this.size(); i++) {
			innerBool = false;
			for (int j = 0; j < sc2.size(); j++) {
				if(this.get(i).equals(sc2.get(j))) innerBool = true;
			}
			if (innerBool==false) return false;
		}
		return true;
	}
}
