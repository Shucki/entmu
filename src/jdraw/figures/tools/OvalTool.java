package jdraw.figures.tools;

import java.awt.Point;

import jdraw.figures.Oval;
import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import jdraw.framework.Figure;

public class OvalTool extends AbstractDragDrawTool implements DrawTool {

		/**
		 * Create a new oval tool for the given context.
		 * @param context a context to use this tool in.
		 */
		public OvalTool(DrawContext context) {
			super(context, "Oval", "oval.png");
		}

		@Override
		protected Figure createFigure(Point p) {
			return new Oval(p.x, p.y, 0, 0);
		}
}
