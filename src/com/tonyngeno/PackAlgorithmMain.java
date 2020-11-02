package com.tonyngeno;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PackAlgorithmMain {
	public static void main(String[] args) throws IOException {
		// Set up input reader
		Scanner loInput = new Scanner(System.in);

		// Read container dimensions
		int CntWidth = loInput.nextInt();
		int CntLength = loInput.nextInt();
		int CntHeight = loInput.nextInt();
		
		// Create container
		Shape loContainer = new Box(CntWidth, CntLength, CntHeight);

		// Read box count
		int liBoxCount = loInput.nextInt();

		// Compile list of boxes from input
		List<Shape> loBoxes = new ArrayList<Shape>();
		while (liBoxCount-- > 0) {
			int liBoxWidth = loInput.nextInt();
			int liBoxLength = loInput.nextInt();
			int liBoxHeight = loInput.nextInt();
			loBoxes.add(new Box(liBoxWidth, liBoxLength, liBoxHeight));
		}

		// Apply packing algorithm to input
		PackingAlgorithm loAlg = new PackingAlgorithm();
		loAlg.statPack(loContainer, loBoxes);
	}
}
