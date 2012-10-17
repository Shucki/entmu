/*
 * Copyright (c) 2000-2012 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import jdraw.figures.handles.LineHandle;

import jdraw.framework.FigureHandle;

/**
 * Represents lines in JDraw.
 * 
 * @author Jan Faessler
 *
 */
public class Line extends AbstractFigure{

	private static final long serialVersionUID = 670248645815961556L;
	
	public enum Points {POINT1, POINT2};

	/**
	 * Use the java.awt.genom.Line2D in order to save/reuse code.
	 */
	private Line2D line;
		
	/**
	 * Create a new line of the given dimension.
	 * @param x the x-coordinate of the first point of the line
	 * @param y the y-coordinate of the first point of the line
	 * @param x the x-coordinate of the second point of the line
	 * @param y the y-coordinate of the second point of the line
	 */
	public Line(int x1, int y1, int x2, int y2) {
		line = new Line2D.Double(x1, y1, x2, y2);
	}

	/**
	 * Draw the line to the given graphics context.
	 * @param g the graphics context to use for drawing.
	 */
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawLine((int) line.getX1(), (int) line.getY1(), (int) line.getX2(), (int) line.getY2());
		
	}
	
	@Override
	public void setBounds(Point origin, Point corner) {
		line.setLine(origin, corner);
		this.updateAllListener();
	}

	@Override
	public void move(int dx, int dy) {
		if (dx != 0 || dy != 0) {
			line.setLine(line.getX1() + dx, line.getY1() + dy, line.getX2() + dx, line.getY2() + dy);
			this.updateAllListener();
		}
	}

	@Override
	public boolean contains(int x, int y) {
		int TOLLERANZ = 5;
		return line.intersects(x-TOLLERANZ, y-TOLLERANZ, TOLLERANZ*2, TOLLERANZ*2);
	}

	@Override
	public Rectangle getBounds() {
		return line.getBounds();
	}

	@Override
	public List<FigureHandle> getHandles() {
		ArrayList<FigureHandle> handles = new ArrayList<FigureHandle>();
		handles.add(new LineHandle(this, Points.POINT1));
		handles.add(new LineHandle(this, Points.POINT2));
		return handles;
	}
}
