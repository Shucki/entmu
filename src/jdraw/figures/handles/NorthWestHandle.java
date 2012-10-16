package jdraw.figures.handles;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import jdraw.figures.AbstractFigure;
import jdraw.framework.DrawView;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

public class NorthWestHandle extends Handle implements FigureHandle, FigureListener {

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

}
