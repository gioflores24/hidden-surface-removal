package com.giovanniflores.utilities;

import java.util.List;

public class Vector {
	private Point vertex;
	private Point endPoint;
	private Point direction;

	public Vector(Point vertex, Point endPoint) {
		this.vertex = vertex;
		this.endPoint = endPoint;
		this.direction = new Point(endPoint.getX() - vertex.getX(), endPoint.getY() - vertex.getY(),
				endPoint.getZ() - vertex.getZ());

	}

	public Point getVertex() {
		return this.vertex;
	}

	public Point getDirection() {
		return direction;
	}

	public Point getEndPoint() {
		return this.endPoint;
	}

	public void setVertex(Point vertex) {
		this.vertex = vertex;
	}

	public void setEndPoint(Point ep) {
		endPoint = ep;
	}

	public static double dotProduct(List<Vector> vs) {
		double prod = 0;
		prod += vs.get(0).getDirection().getX() * vs.get(1).getDirection().getX()
				+ vs.get(0).getDirection().getY() * vs.get(1).getDirection().getY()
				+ vs.get(0).getDirection().getZ() * vs.get(1).getDirection().getZ();

		return prod;

	}

	public static double dotProduct(Vector v1, Vector v2) {
		double prod = 0;
		prod += (v1.getDirection().getX() * v2.getDirection().getX())
				+ (v1.getDirection().getY() * v2.getDirection().getY())
				+ (v1.getDirection().getZ() * v2.getDirection().getZ());

		return prod;
	}

	public static Vector crossProduct(Vector v1, Vector v2) {
		Point p = new Point(0, 0, 0);
		p.setX((v1.getDirection().getY() * v2.getDirection().getZ())
				- (v1.getDirection().getZ() * v2.getDirection().getY()));
		p.setY((v1.getDirection().getZ() * v2.getDirection().getX())
				- (v1.getDirection().getX() * v2.getDirection().getZ()));
		p.setZ((v1.getDirection().getX() * v2.getDirection().getY())
				- (v1.getDirection().getY() * v2.getDirection().getX()));
		Vector v3 = new Vector(new Point(0, 0, 0), p);
		return v3;
	}

	public static double magnitude(Vector v) {
		double mag = Math.sqrt(Math.pow(v.getEndPoint().getX() - v.getVertex().getX(), 2)
				+ Math.pow(v.getEndPoint().getY() - v.getVertex().getY(), 2)
				+ Math.pow(v.getEndPoint().getZ() - v.getVertex().getZ(), 2));

		return mag;
	}

	public static Point getUnitVector(Point p, Vector v) {
		double curMag = Vector.magnitude(v);
		Point units = new Point(p.getX() / curMag, p.getY() / curMag, p.getZ() / curMag);

		return units;
	}

	public String toString() {
		return "Vector:\n" + "\nstart x: " + vertex.getX() + "\nstart y: " + vertex.getY() + "\nstart z: "
				+ vertex.getZ() + "\n" + "\nend x: " + endPoint.getX() + "\nend y: " + endPoint.getY() + "\nend z: "
				+ endPoint.getZ();
	}

}
