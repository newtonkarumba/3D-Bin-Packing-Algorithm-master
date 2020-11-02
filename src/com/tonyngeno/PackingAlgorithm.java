package com.tonyngeno;

import java.util.*;

public class PackingAlgorithm {

	private static boolean ShowProgress = false; // show progress? (slows algorithm)

	public void statPack(Shape shape, List<Shape> aoShapes) {
		System.out.println("Beggining to pack.");

		// Create clone of shapes
		ArrayList ShapesCopy = new ArrayList<Shape>(Arrays.asList(aoShapes
				.toArray(new Shape[0])));
		
		// Start time stamp
		long startTime = System.currentTimeMillis();

		// Run Algorithm
		int numContainers = this.pack(shape, ShapesCopy);
		
		// End time stamp
		long endTime = System.currentTimeMillis();

		// Compute statistics
		long llElapsedTime = endTime - startTime;
		int liNumBoxesPacked = (aoShapes.size() - ShapesCopy.size());
		int liTotalBoxVolume = Shape.getTotalVolume(aoShapes);
		int liTotalContainerVolume = (numContainers * shape.getVolume());
		float lfPackingEfficiency = ((float) liTotalBoxVolume / liTotalContainerVolume);

		// Output results
		System.out.println("Complete.");
		System.out.println("Packed " + liNumBoxesPacked + " of " + aoShapes.size()
				+ " boxes into " + numContainers + " container(s) of "
				+ shape);
		System.out.println("Statistics:");
		System.out.println("Time elapsed: \t\t" + llElapsedTime
				+ " milliseconds");
		System.out.println("Average: \t\t"
				+ ((float) liNumBoxesPacked / numContainers)
				+ " boxes/container");
		System.out.println("Packing efficiency: \t"
				+ (lfPackingEfficiency * 100) + "%");
	}

	public int pack(Shape shape, List<Shape> aoShapes) {
		// Optimize algorithm performance by sorting the boxes (area ascending)
		Collections.sort(aoShapes, new shapeAreaComparator());
		Collections.reverse(aoShapes);
		return this.pack(shape, aoShapes, 0, false);
	}

	private int pack(Shape shape, List<Shape> aoShapes,
			int aiContainerNum, boolean abSubContainer) {

		// Step 1: Find a shape that fits into the container
		Iterator<Shape> loIterator = aoShapes.iterator();
		Shape loCurrentShape = null;
		boolean FoundFit = false;
		while (!FoundFit && loIterator.hasNext()) {
			loCurrentShape = loIterator.next();
			// Can this shape fit inside the container?
			if (shape.attemptToContain(loCurrentShape))
				FoundFit = true;
		}

		if (FoundFit) {
			// Step 2: If found, position shape inside container
			loCurrentShape.matchPositionOf(shape);
			aoShapes.remove(loCurrentShape);
			
			if (ShowProgress)
				System.out.println("Packed " + loCurrentShape.toFullString()
						+ " into container " + aiContainerNum + ".");

			// Step 3: Split the unoccupied region around this shape into sub 
			// containers and run algorithm along each
			List<Shape> loSubContainers = loCurrentShape.breakUp(shape);
			for (Shape loSubContainer : loSubContainers) {
				this.pack(loSubContainer, aoShapes, aiContainerNum, true);
			}
		}

		// Step 4: If there are boxes left to pack, use an additional container
		if (abSubContainer == false && aoShapes.size() > 0) {
			return this.pack(shape, aoShapes, aiContainerNum + 1, false);
		} else {
			// Otherwise, return total number of containers used
			return aiContainerNum + 1;
		}
	}
}
