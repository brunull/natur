package com.brunull.natur.math;

public class Vector2<T> {

	private T x;
	private T y;
	
	public Vector2(T x, T y) {
		this.x = x;
		this.y = y;
	}
	
	public void setX(T x) {
		this.x = x;
	}
	
	public void setY(T y) {
		this.y = y;
	}
	
	public T getX() {
		return x;
	}

	public T getY() {
		return y;
	}
	
}