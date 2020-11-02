package com.tonyngeno;

import java.util.Comparator;
import java.util.List;


public abstract class Shape {
	
	// Fields
	protected int X, Y, Z; // position

	public final void setPosition(int X, int Y, int Z) {
		this.X = X;
		this.Y = Y;
		this.Z = Z;
	}

	public final void matchPositionOf(Shape shape) {
		this.setPosition(shape.X, shape.Y, shape.Z);
	}

	public abstract int getArea();

	public abstract int getVolume();

	public abstract void rotateXY();

	public abstract boolean canContain(Shape shape);

	public abstract boolean attemptToContain(Shape shape);

	public abstract List<Shape> breakUp(Shape spaceOccupied);

	public abstract String toFullString();

	@Override
	public String toString() {
		return "[X=" + X + ", Y=" + Y + ", Z=" + Z + "]";
	}

	@Override
	public boolean equals(Object obj) {
		Shape other = (Shape) obj;
		if (X != other.X || Y != other.Y || Z != other.Z)
			return false;
		return true;
	}
	
	// Other Members
	public static final int getTotalVolume(List<Shape> shapes) {
		int listTotalVolume = 0;
		for (Shape shape : shapes) {
			listTotalVolume += shape.getVolume();
		}
		return listTotalVolume;
	}
}

// Other Classes
class shapeAreaComparator implements Comparator<Shape> {
	@Override
	public int compare(Shape shapeOne, Shape shapeTwo) {
		return (shapeOne.getArea() - shapeTwo.getArea());
	}
}

class shapeVolumeComparator implements Comparator<Shape> {
	@Override
	public int compare(Shape shapeOne, Shape shapeTwo) {
		return (shapeOne.getVolume() - shapeTwo.getVolume());
	}
}
