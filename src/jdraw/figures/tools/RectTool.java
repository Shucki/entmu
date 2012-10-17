/*
 * Copyright (c) 2000-2012 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.figures.tools;

import java.awt.Point;

import jdraw.figures.Rect;
import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import jdraw.framework.Figure;

/**
 * This tool defines a mode for drawing rectangles.
 *
 * @see jdraw.framework.Figure
 *
 * @author  Christoph Denzler
 * @version 2.1, 27.09.07
 */
public class RectTool extends AbstractDragDrawTool implements DrawTool {

	/**
	 * Create a new rectangle tool for the given context.
	 * @param context a context to use this tool in.
	 */
	public RectTool(DrawContext context) {
		super(context, "Rectangle", "rectangle.png");
	}

	@Override
	protected Figure createFigure(Point p) {
		return new Rect(p.x, p.y, 0, 0);
	}
}
