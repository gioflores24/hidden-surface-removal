package com.giovanniflores.shapehandling;

import com.giovanniflores.utilities.*;

/**
 * 
 * @author giovanniflores
 *
 */
public class AffineTransformations {
	public static double[][] translate(double px, double py, double pz, double[][] sceneGraph) throws Exception {
		double[][] v = { { 1, 0, 0, px }, { 0, 1, 0, py }, { 0, 0, 1, pz }, { 0, 0, 0, 1 } };

		return Matrix.multMatrix(v, sceneGraph);

	}

	public static double[][] scale(double x, double y, double z, double[][] sceneGraph) throws Exception {
		double[][] v = { { x, 0, 0, 0 }, { 0, y, 0, 0 }, { 0, 0, z, 0 }, { 0, 0, 0, 1 } };

		return Matrix.multMatrix(v, sceneGraph);

	}

	public static double[][] rotateX(double angle, double[][] sceneGraph) throws Exception {
		angle = Math.toRadians(angle);
		double[][] v = { { 1, 0, 0, 0 }, { 0, Math.cos(angle), -Math.sin(angle), 0 },
				{ 0, Math.sin(angle), Math.cos(angle), 0 }, { 0, 0, 0, 1 } };
		return Matrix.multMatrix(v, sceneGraph);
	}

	public static double[][] rotateY(double angle, double[][] sceneGraph) throws Exception {
		angle = Math.toRadians(angle);
		double[][] v = { { Math.cos(angle), 0, Math.sin(angle), 0 }, { 0, 1, 0, 0 },
				{ -Math.sin(angle), 0, Math.cos(angle), 0 }, { 0, 0, 0, 1 } };
		return Matrix.multMatrix(v, sceneGraph);
	}

	public static double[][] rotateZ(double angle, double[][] sceneGraph) throws Exception {
		angle = Math.toRadians(angle);
		double[][] v = { { Math.cos(angle), -Math.sin(angle), 0, 0 }, { Math.sin(angle), Math.cos(angle), 0, 0 },
				{ 0, 0, 1, 0 }, { 0, 0, 0, 1 } };

		return Matrix.multMatrix(v, sceneGraph);
	}

	// used to move to the origin and perform fixed point scaling and rotation.
	public static double[][] toOrigin(double cx, double cy, double cz, double[][] sceneGraph) throws Exception {
		double[][] v = { { 1, 0, 0, -cx }, { 0, 1, 0, -cy }, { 0, 0, 1, -cz }, { 0, 0, 0, 1 } };
		return Matrix.multMatrix(v, sceneGraph);
	}

	public static double[][] back(double cx, double cy, double cz, double[][] sceneGraph) throws Exception {

		double[][] v = { { 1, 0, 0, cx }, { 0, 1, 0, cy }, { 0, 0, 1, cz }, { 0, 0, 0, 1 } };
		return Matrix.multMatrix(v, sceneGraph);
	}

	// This code is quite repetitive; I hope to revisit and change it in the future.
	public static double[][] rotateAboutArbitrary(double arbX, double arbY, double arbZ, double[][] sceneGraph,
			double angle, boolean isOrigin) {
		double[][] m = null;
		angle = Math.toRadians(angle); // necessary conversion
		double[][] origin = { { 1, 0, 0, -sceneGraph[0][8] }, { 0, 1, 0, -sceneGraph[1][8] },
				{ 0, 0, 1, -sceneGraph[2][8] }, { 0, 0, 0, 1 } };
		double[][] andBack = { { 1, 0, 0, sceneGraph[0][8] }, { 0, 1, 0, sceneGraph[1][8] },
				{ 0, 0, 1, sceneGraph[2][8] }, { 0, 0, 0, 1 } };
		// this repeat of the rx matrix is for use in case d = 0
		double[][] rx = { { 1, 0, 0, 0 }, { 0, Math.cos(angle), -Math.sin(angle), 0 },
				{ 0, Math.sin(angle), Math.cos(angle), 0 }, { 0, 0, 0, 1 } };
		Point startP = new Point(0, 0, 0); // I have this always be my start because of how I'm rotating. This should
											// work.
		Point endP = new Point(arbX, arbY, arbZ); // text fields stored in a point
		Vector v = new Vector(startP, endP);

		Point units = Vector.getUnitVector(endP, v); // stores unit vector x, y, and z.

		double d = Math.sqrt(Math.pow(units.getY(), 2) + Math.pow(units.getZ(), 2));

		// units.getX() and getY() represent alpha x and y respectively
		if (d == 0) // this means both alpha y and z are zero. Thus, we do our rotation about x
					// axis.
		{
			try {
				m = Matrix.multMatrix(origin, sceneGraph);
				m = Matrix.multMatrix(rx, m);
				m = Matrix.multMatrix(andBack, m);

				return m;

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			double[][] rz = { { Math.cos(angle), -Math.sin(angle), 0, 0 }, { Math.sin(angle), Math.cos(angle), 0, 0 },
					{ 0, 0, 1, 0 }, { 0, 0, 0, 1 } };
			double[][] rxthetax = { { 1, 0, 0, 0 }, { 0, (units.getZ() / d), (-units.getY() / d), 0 },
					{ 0, (units.getY() / d), (units.getZ() / d), 0 }, { 0, 0, 0, 1 } };
			double[][] rythetay = { { d, 0, -units.getX(), 0 }, { 0, 1, 0, 0 }, { units.getX(), 0, d, 0 },
					{ 0, 0, 0, 1 } };
			double[][] rythetayNegative = { { d, 0, units.getX(), 0 }, { 0, 1, 0, 0 }, { -units.getX(), 0, d, 0 },
					{ 0, 0, 0, 1 } };
			double[][] rxthetaxNegative = { { 1, 0, 0, 0 }, { 0, (units.getZ() / d), (units.getY() / d), 0 },
					{ 0, (-units.getY() / d), (units.getZ() / d), 0 }, { 0, 0, 0, 1 } };
			try {
				if (isOrigin) {
					m = Matrix.multMatrix(origin, sceneGraph);
					m = Matrix.multMatrix(rxthetax, m);
					m = Matrix.multMatrix(rythetay, m);
					m = Matrix.multMatrix(rz, m);
					m = Matrix.multMatrix(rythetayNegative, m);
					m = Matrix.multMatrix(rxthetaxNegative, m);
					m = Matrix.multMatrix(andBack, m);
				} else if (!isOrigin) {
					m = Matrix.multMatrix(rxthetax, sceneGraph);
					m = Matrix.multMatrix(rythetay, m);
					m = Matrix.multMatrix(rz, m);
					m = Matrix.multMatrix(rythetayNegative, m);
					m = Matrix.multMatrix(rxthetaxNegative, m);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return m;

	}

}
