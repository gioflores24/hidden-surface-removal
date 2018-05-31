package com.giovanniflores.utilities;

/**
 * 
 * @author giovanniflores
 * 
 */
public abstract class Matrix {

	public static double[][] multMatrix(double m[][], double v[][]) throws Exception {
		if (m[0].length != v.length)
			throw new Exception("incompatible matrices"); // prevents illegal multiplications

		double[][] c = new double[m.length][v[0].length];
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < v[i].length; j++) {
				c[i][j] = 0;
				for (int k = 0; k < v.length; k++) {
					c[i][j] += m[i][k] * v[k][j];
				}
			}
		}
		return c;
	}

	public static void print(double[][] matrix) // formatted display to three decimal places..
	{
		for (int i = 0; i < matrix.length; i++) {
			System.out.println();
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.printf("%8.3f\t", matrix[i][j]);
			}
		}
		System.out.println();

	}
}
