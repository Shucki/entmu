package jdraw.figures.handles;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import jdraw.figures.AbstractFigure;
import jdraw.framework.DrawView;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

public class SouthHandle extends Handle implements FigureHandle, FigureListener {

	public SouthHandle(AbstractFigure figure) {
		super(figure, "S");
	}

	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR);
	}

	@Override
	public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
		Rectangle rec = _owner.getBounds();
		_anchor = new Point(rec.x, rec.y);
	}
	
	@Override
	public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
		Rectangle rec = _owner.getBounds();
		_owner.setBounds(_anchor, new Point(rec.x + rec.width, y));
	}
	
	@Override
	public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
		Rectangle rec = _owner.getBounds();
		_owner.setBounds(_anchor, new Point(rec.x + rec.width, y));
	}
}
