/*
 * Copyright (c) 2000-2012 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.figures.tools;

import java.awt.Point;

import jdraw.figures.Line;
import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import jdraw.framework.Figure;

/**
 * This tool defines a mode for drawing lines.
 *
 * @see jdraw.framework.Figure
 *
 * @author  Jan Faessler
 * @version 2.1, 27.09.07
 */
public class LineTool extends AbstractDragDrawTool implements DrawTool {
	
	/**
	 * Create a new oval tool for the given context.
	 * @param context a context to use this tool in.
	 */
	public LineTool(DrawContext context) {
		super(context, "Line", "line.png");
	}

	@Override
	public Figure createFigure(Point p) {
		return new Line(p.x, p.y, p.x, p.y);
	}
}
