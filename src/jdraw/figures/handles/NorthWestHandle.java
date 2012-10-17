package jdraw.figures.handles;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import jdraw.figures.AbstractFigure;
import jdraw.framework.DrawView;

public class NorthWestHandle extends AbstractHandle {

	public NorthWestHandle(AbstractFigure figure) {
		super(figure, "NW");
	}

	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
	}

	@Override
	public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
		Rectangle rec = _owner.getBounds();
		_anchor = new Point(rec.x + rec.width, rec.y + rec.height);
	}
	
	@Override
	public Point getLocation() {
		Rectangle rectangle = _owner.getBounds();
		return new Point(rectangle.x, rectangle.y);
	}
}
