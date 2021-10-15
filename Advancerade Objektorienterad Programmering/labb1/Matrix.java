package labb1;

public class Matrix {
	private double[][] elements;
	
	public Matrix(int rows, int columns){
		elements = new double[rows][columns];
	}
	public static void main(String[] args) {
		Matrix lol = new Matrix(3,2);
		double[] lmao = new double[9];
		for(int i = 0; i < lmao.length; i++) {
			lmao[i] = i;
		}
		lol.input(lmao);
		lol.print();
		System.out.println();
		
		Matrix lolxd = new Matrix(2,3);
		lolxd.input(lmao);
		lolxd.print();
		System.out.println();
		double[][] rolle=lolxd.get();
		lol.add(rolle);
		lol.print();
		System.out.println();
		rolle = lol.multiply(rolle);
		for(int x = 0; x < rolle.length; x++) {
			for(int y = 0; y < rolle[0].length; y++) {
				System.out.print(rolle[x][y] + " ");
			}
			System.out.println();
		}
		System.out.println("\n" + getNumber(lol, 2, 1));
		System.out.println("\n" + getNumber(lol, 3, 0));
		System.out.println();
		
		lol.setNumber(lol, 1, 1, 35.0);
		lol.print();
	}
	
	public static double getNumber(Matrix a, int row, int column) {
		if(row >= 0 || column >= 0) {
		double[][] copy = a.get();
		if(copy.length-1 < row || copy[0].length-1 < column) {
			return -1; // Basically FAIL
		}else {
			return copy[row][column];
	}
		}else {
			return -1;
		}
	}
	public Matrix setNumber(Matrix b, int column, int row, double number) {
		if((row < 0 || column < 0) || b == null) {
			return null;
		}else {
		double[][] copy = b.get();
		copy[row][column] = number;
		double[] numbers = new double[copy.length*copy[0].length];
		int counter = 0;
		
		for(int i = 0; i < copy.length; i++) {
			for(int j = 0; j < copy[0].length; j++) {
				numbers[counter] = copy[i][j];
				counter++;
			}
		}
		Matrix matrix = new Matrix(copy.length, copy[0].length);
		matrix.input(numbers);
		
		return matrix;
		}
	}
	
	public double[][] get(){
		return elements;
	}
	public void print() {
		for(int x = 0; x < elements.length; x++) {
			for(int y = 0; y < elements[0].length; y++) {
				System.out.print(elements[x][y] + " ");
			}
			System.out.println();
		}
	}
	
	public void input(double[] numbers){
		int length = numbers.length;
		int counter = 0;
		
		for(int i = 0; i < elements.length; i++) {
			for(int j = 0; j < elements[0].length; j++) {
				if(counter >= length) {
				elements[i][j] = 0;
				}
				else {
					elements[i][j] = numbers[counter];
				}
			counter++;
			}
		}
	}
	public void add(double[][] mAdd){
		if(elements.length != mAdd.length || elements[0].length != mAdd[0].length) {
			System.out.println("Not a compatible matrix");
		}else {
		for(int i = 0; i < elements.length; i++) {
			for(int j = 0; j < elements[0].length; j++) {
				elements[i][j] += mAdd[i][j];
				}
			}
		}
	}
	public double[][] multiply(double[][] mMulti){
		double number = 0;
		if(elements[0].length != mMulti.length) {
			System.out.println("Not a compatible matrix");
			return null;
		}else {
		double[][] sumMatrix = new double[elements.length][mMulti[0].length];
		
		for(int i = 0; i < sumMatrix[0].length; i++) {
			for(int j = 0; j < sumMatrix[0].length; j++) {
				
					for(int k =0; k < elements[0].length;k++) {
						number += elements[i][k] * mMulti[k][j];
					}
				sumMatrix[i][j] = number;
				number = 0;
			}
		}
		
		return sumMatrix;
		}
	}
}
