package com.brunull.natur.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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
		g.drawString(text, position.getX(), position.getY() + this.getBounds(g).height);
	}
	
	public void drawShadowed(Graphics2D g) {
		g.setFont(font);
		
		g.setColor(Color.BLACK);
		g.drawString(text, position.getX() + 1, (position.getY() + this.getBounds(g).height) + 1);
		
		g.setColor(color);
		g.drawString(text, position.getX(), position.getY() + this.getBounds(g).height);
	}
	
	public void drawBounds(Graphics2D g) {
		g.setColor(Color.RED);
		g.drawRect(position.getX(), position.getY(), (int)getBounds(g).getWidth(), (int)getBounds(g).getHeight());
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
	
	public Vector2<Integer> getPosition() {
		return position;
	}
	
	public Rectangle getBounds(Graphics2D g) {
		g.setFont(font);
		Rectangle r = g.getFontMetrics().getStringBounds(text, ((Graphics)g)).getBounds();
		return new Rectangle(position.getX(), position.getY(), r.width, r.height);
	}
}