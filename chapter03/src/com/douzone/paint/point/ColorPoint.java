package com.douzone.paint.point;

import chapter04.Point;

public class ColorPoint extends Point {
	private String color;
	
	public ColorPoint(int x, int y, String color) {
		//super();	//Point에서 생성자 만들어주면 기본 생성자 자동으로 안들어가서 에러남.
		//setX(x);
		//setY(y);
		super(x, y);
		this.color = color;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	@Override
	public void show() {
		
		System.out.println("점[x=" + getX() + ",y=" + getY() +", color= " + color + "]을 그렸습니다.");
	}

}
