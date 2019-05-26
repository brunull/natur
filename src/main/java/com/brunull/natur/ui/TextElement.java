package com.brunull.natur.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.brunull.natur.math.Vector2;

public class TextElement {
	
	private String text;
	private Font font;
	
	private Vector2<Integer> position;
	private Color color;

	public TextElement(String text, Font font, Color color, Vector2<Integer> position) {
		this.text = text;
		this.font = font;
		this.color = color;
		this.position = position;
	}
	
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.setFont(font);
		g.drawString(text, position.getX(), position.getY());
	}
	
	public void setPosition(int x, int y) {
		position = new Vector2<Integer>(x, y);
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
	public String getText() {
		return text;
	}
	
	public Font getFont() {
		return font;
	}
}