package com.tonyngeno;

import java.util.Arrays;
import java.util.List;

public class Box extends Shape {

	// Fields
	protected int Width, Length, Height; // dimensions

	public Box(int Width, int Length, int Height) {
		super();
		this.Width = Width;
		this.Length = Length;
		this.Height = Height;
	}

	public Box(int Width, int Length, int Height, int X, int Y, int Z) {
		super.setPosition(X, Y, Z);
		this.Width = Width;
		this.Length = Length;
		this.Height = Height;
	}

	@Override
	public int getArea() {
		return this.Width * this.Length;
	}

	@Override
	public int getVolume() {
		return this.Width * this.Length * this.Height;
	}

	@Override
	public void rotateXY() {
		// swap width and length
		int refWidth = this.Width;
		this.Width = this.Length;
		this.Length = refWidth;
	}

	@Override
	public boolean canContain(Shape shape) {
		if (shape instanceof Box)
			return canContain((Box) shape);
		return false;
	}

	public boolean canContain(Box box) {
		return (box.Width <= this.Width
				&& box.Length <= this.Length && box.Height <= this.Height);
	}

	@Override
	public boolean attemptToContain(Shape shape) {
		if (shape instanceof Box)
			return attemptToContain((Box) shape);
		return false;
	}

	public boolean attemptToContain(Box box) {
		if (canContain(box))
			return true;
		rotateXY();
		if (canContain(box))
			return true;
		rotateXY(); // back to default
		return false;
	}

	@Override
	public List<Shape> breakUp(Shape shape) {
		if (shape instanceof Box)
			return breakUp((Box) shape);
		return null;
	}

	public List<Shape> breakUp(Box box) {
		Shape SubBoxX = new Box(box.Width - this.Width, box.Length,
				box.Height, this.X + this.Width, box.Y, box.Z);
		Shape SubBoxY = new Box(this.Width, box.Length - this.Length,
				box.Height, this.X, box.Y + this.Length, box.Z);
		Shape SubBoxZ = new Box(this.Width, this.Length, box.Height
				- this.Height, this.X, box.Y, box.Z + this.Height);
		return Arrays.asList(SubBoxX, SubBoxY, SubBoxZ);
	}
	

	@Override
	public String toFullString() {
		return "Box [Width=" + Width + ", Length=" + Length + ", Height="
				+ Height + ", Position=" + super.toString() + "]";
	}

	@Override
	public String toString() {
		return "Box [Width=" + Width + ", Length=" + Length + ", Height="
				+ Height + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		if (!super.equals(obj))
			return false;
		Box otherBox = (Box) obj;
		if (Width != otherBox.Width || Length != otherBox.Length
				|| Height != otherBox.Height)
			return false;
		return true;
	}
}
