package hhCore.utils.trajectory;

public class Vector {
	private double XComponent;
	private double YComponent;
	private double magnitude;

	/*
	 * create a vector pointing from CoordinatePoint p1 to CoordinatePoint p2
	 */
	public Vector(CoordinatePoint p1, CoordinatePoint p2) {
		XComponent = p2.getX() - p1.getX();
		YComponent = p2.getY() - p1.getY();
		magnitude = p1.distanceTo(p2);
	}

	public Vector(CoordinatePoint p1) {
		XComponent = p1.getX();
		YComponent = p1.getY();
		magnitude = Math.sqrt((XComponent * XComponent) + (YComponent * YComponent));
	}

	public CoordinatePoint toCoordinatePoint() {
		return new CoordinatePoint(this.getXComponent(), this.getYComponent());
	}

	/*
	 * create a vector with components XComponent and YComponent
	 */
	public Vector(double XComponent, double YComponent) {
		this.XComponent = XComponent;
		this.YComponent = YComponent;
		magnitude = Math.sqrt((XComponent * XComponent) + (YComponent * YComponent));
	}

	public double getXComponent() {
		return XComponent;
	}

	public double getYComponent() {
		return YComponent;
	}

	public double getmagnitude() {
		return magnitude;
	}

	public String toString() {
		return ("<" + XComponent + ", " + YComponent + ">");
	}

	/**
	 * @param v1
	 * @return "this vector" crossed with v1
	 */
	public double Cross(Vector v1) {
		return (this.XComponent * v1.getYComponent()) - (this.YComponent * v1.getXComponent());
	}

	/**
	 * @param v1
	 * @return "this vector" dotted with v1
	 */
	public double Dot(Vector v1) {
		return (this.XComponent * v1.getXComponent()) + (this.YComponent * v1.getYComponent());
	}

	/**
	 * @param v1
	 * @return "this vector" added to v1
	 */
	public Vector Add(Vector v1) {
		return new Vector(this.XComponent + v1.getXComponent(), this.YComponent + v1.getYComponent());
	}

	/**
	 * @param v1
	 * @return "this vector" minus v1
	 */
	public Vector Subtract(Vector v1) {
		return new Vector(this.XComponent - v1.getXComponent(), this.YComponent - v1.getYComponent());
	}

	public Vector ScalarMultiply(double s) {
		return new Vector(this.XComponent * s, this.YComponent * s);
	}

	/**
	 * 
	 * @param v1
	 * @return the scalar projection of "this vector" onto v1
	 */
	public double proj(Vector v1) {
		return (this.Dot(v1) / v1.getmagnitude());
	}

}
