/*
 * Copyright (c) 2000-2012 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import jdraw.figures.handles.EastHandle;
import jdraw.figures.handles.NorthEastHandle;
import jdraw.figures.handles.NorthHandle;
import jdraw.figures.handles.NorthWestHandle;
import jdraw.figures.handles.SouthEastHandle;
import jdraw.figures.handles.SouthHandle;
import jdraw.figures.handles.SouthWestHandle;
import jdraw.figures.handles.WestHandle;
import jdraw.framework.FigureHandle;

/**
 * Represents rectangles in JDraw.
 * 
 * @author Christoph Denzler
 *
 */
public class Rect extends AbstractFigure {

	private static final long serialVersionUID = 670248645815961556L;

	/**
	 * Use the java.awt.Rectangle in order to save/reuse code.
	 */
	private java.awt.Rectangle rectangle;

	/**
	 * Create a new rectangle of the given dimension.
	 * @param x the x-coordinate of the upper left corner of the rectangle
	 * @param y the y-coordinate of the upper left corner of the rectangle
	 * @param w the rectangle's width
	 * @param h the rectangle's height
	 */
	public Rect(int x, int y, int w, int h) {
		rectangle = new java.awt.Rectangle(x, y, w, h);
	}

	/**
	 * Draw the rectangle to the given graphics context.
	 * @param g the graphics context to use for drawing.
	 */
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
		g.setColor(Color.BLACK);
		g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
	}
	
	@Override
	public void setBounds(Point origin, Point corner) {
		rectangle.setFrameFromDiagonal(origin, corner);
		this.updateAllListener();
	}

	@Override
	public void move(int dx, int dy) {
		if (dx != 0 || dy != 0) {
			rectangle.setLocation(rectangle.x + dx, rectangle.y + dy);
			this.updateAllListener();
		}
	}

	@Override
	public boolean contains(int x, int y) {
		return rectangle.contains(x, y);
	}

	@Override
	public Rectangle getBounds() {
		return rectangle.getBounds();
	}
	
	@Override
	public List<FigureHandle> getHandles() {
		ArrayList<FigureHandle> handles = new ArrayList<FigureHandle>();
		handles.add(new NorthHandle(this));
		handles.add(new EastHandle(this));
		handles.add(new SouthHandle(this));
		handles.add(new WestHandle(this));
		handles.add(new NorthWestHandle(this));
		handles.add(new NorthEastHandle(this));
		handles.add(new SouthWestHandle(this));
		handles.add(new SouthEastHandle(this));
		return handles;
	}
	
	@Override
	public Point getHandleLocation(String ident) {
		Point pnt;
		switch (ident) {
			case "N":  pnt = new Point(rectangle.x + (rectangle.width/2), rectangle.y); break;
			case "S":  pnt = new Point(rectangle.x + (rectangle.width/2), rectangle.y + rectangle.height); break;
			case "W":  pnt = new Point(rectangle.x,                       rectangle.y + (rectangle.height/2)); break;
			case "E":  pnt = new Point(rectangle.x + rectangle.width,     rectangle.y + (rectangle.height/2)); break;
			case "NW": pnt = new Point(rectangle.x,                       rectangle.y); break;
			case "NE": pnt = new Point(rectangle.x + rectangle.width,     rectangle.y); break;
			case "SW": pnt = new Point(rectangle.x,                       rectangle.y + rectangle.height); break;
			case "SE": pnt = new Point(rectangle.x + rectangle.width,     rectangle.y + rectangle.height); break;
			default: pnt = new Point();
		}
		return pnt;
	}
}
