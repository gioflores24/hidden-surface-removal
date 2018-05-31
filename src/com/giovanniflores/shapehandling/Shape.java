package com.giovanniflores.shapehandling;

import java.awt.Color;

import java.awt.Graphics2D;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.giovanniflores.utilities.*;
/**
 * 
 * @author giovanniflores
 *
 *	
 */
public class Shape {
	private double[][] mat; //for storing our vertices
	private ArrayList<ArrayList<Integer>> faces;

	public Shape(File blueprint) throws IOException 
	{
		faces = new ArrayList<ArrayList<Integer>>();
		processFile(blueprint);
	}
	//reads shape based on a .txt file. 
	private void processFile(File f) throws IOException {
		// handles shape construction
		BufferedReader reader = new BufferedReader(new FileReader(f));
		String line = reader.readLine(); // tells us the number of vertices.
		mat = new double[4][Integer.parseInt(line)];
		mat[0][8] = 0;
		mat[1][8] = 0;
		mat[2][8] = 0;
		mat[3][8] = 1;
		line = reader.readLine(); // this reads second line
		line = reader.readLine(); // this reads third line
		int i = 0;

		while (!(line.equals("edges"))) {

			String valuesLine = line;
			String values[] = valuesLine.split(",");

			mat[0][i] = Double.parseDouble(values[0]);
			mat[1][i] = Double.parseDouble(values[1]);
			mat[2][i] = Double.parseDouble(values[2]);
			mat[3][i] = 1;
			i++;
			line = reader.readLine();
		}

		while (!(line.equals("faces"))) {
			// insert code here
			line = reader.readLine();
		}
		line = reader.readLine();
		while (line != null) {
			String valuesLine = line.replaceAll("\\s", ""); // used to trim whitespace between
			String face[] = valuesLine.trim().split(",");
			int index1 = Integer.parseInt(face[0]);
			int index2 = Integer.parseInt(face[1]);
			int index3 = Integer.parseInt(face[2]);
			int index4 = Integer.parseInt(face[3]);
			ArrayList<Integer> nestedList = new ArrayList<>();
			nestedList.add(index1);
			nestedList.add(index2);
			nestedList.add(index3);
			nestedList.add(index4);

			faces.add(nestedList);
			line = reader.readLine();

		}

		reader.close();

	}

	public double[][] getMat() {
		return this.mat;
	}

	public void setMat(double[][] mat) {
		this.mat = mat;
	}

	public void draw(Graphics2D g2d, double[][] mat) {

		for (int i = 0; i < faces.size(); i++) {
			double v0x = mat[0][faces.get(i).get(0)];
			double v0y = mat[1][faces.get(i).get(0)];
			double v0z = mat[2][faces.get(i).get(0)];

			double v1x = mat[0][faces.get(i).get(1)];
			double v1y = mat[1][faces.get(i).get(1)];
			double v1z = mat[2][faces.get(i).get(1)];

			double v2x = mat[0][faces.get(i).get(2)];
			double v2y = mat[1][faces.get(i).get(2)];
			double v2z = mat[2][faces.get(i).get(2)];

			Vector v1 = new Vector(new Point(v1x, v1y, v1z), new Point(v0x, v0y, v0z));

			Vector v2 = new Vector(new Point(v1x, v1y, v1z), new Point(v2x, v2y, v2z));

			int xCoords[] = new int[faces.get(i).size()];

			int yCoords[] = new int[faces.get(i).size()];
			if (isVisible(v1, v2)) {

				// drawing methods
				xCoords[0] = (int) mat[0][faces.get(i).get(0)];

				xCoords[1] = (int) mat[0][faces.get(i).get(1)];
				xCoords[2] = (int) mat[0][faces.get(i).get(2)];
				xCoords[3] = (int) mat[0][faces.get(i).get(3)];

				yCoords[0] = (int) mat[1][faces.get(i).get(0)];
				yCoords[1] = (int) mat[1][faces.get(i).get(1)];
				yCoords[2] = (int) mat[1][faces.get(i).get(2)];
				yCoords[3] = (int) mat[1][faces.get(i).get(3)];

				if (i == 0) {
					g2d.setColor(Color.RED);
				}
				if (i == 1) {
					g2d.setColor(Color.BLUE);
				}
				if (i == 2) {
					g2d.setColor(Color.GREEN);
				}
				if (i == 3) {
					g2d.setColor(Color.MAGENTA);
				}
				if (i == 4) {
					g2d.setColor(Color.YELLOW);
				}
				if (i == 5) {
					g2d.setColor(Color.CYAN);
				}

				g2d.fillPolygon(xCoords, yCoords, 4);
				surfaceNormals(v1, v2, g2d);
			}

		}
	}

	public boolean isVisible(Vector fv, Vector sv) {
		Vector crossProduct = Vector.crossProduct(fv, sv);

		double magnitude = Vector.magnitude(crossProduct);
		Vector unitV = new Vector(new Point(0, 0, 0), new Point(crossProduct.getDirection().getX() / magnitude,
				crossProduct.getDirection().getY() / magnitude, crossProduct.getDirection().getZ() / magnitude));
		double res = Vector.dotProduct(unitV, new Vector(new Point(0, 0, 0), new Point(0, 0, 1)));
		if (res < 0) {
			return false;
		} else {
			return true;
		}
	}

	public void surfaceNormals(Vector fv, Vector sv, Graphics2D g2d) {

		Vector crossProduct = Vector.crossProduct(fv, sv);
		double mag = Vector.magnitude(crossProduct);
		Point units = new Point(-crossProduct.getDirection().getX() / mag, -crossProduct.getDirection().getY() / mag,
				-crossProduct.getDirection().getZ() / mag);
		units.setX(units.getX() * 25);
		units.setY(units.getY() * 25);
		int x1 = (int) ((fv.getEndPoint().getX() + sv.getEndPoint().getX()) / 2);
		int y1 = (int) ((fv.getEndPoint().getY() + sv.getEndPoint().getY()) / 2);
		g2d.setColor(Color.BLACK);
		g2d.drawLine(x1, y1, (int) units.getX() + x1, (int) units.getY() + y1);

	}

}