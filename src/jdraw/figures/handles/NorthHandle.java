package jdraw.figures.handles;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import jdraw.figures.AbstractFigure;
import jdraw.framework.DrawView;

public class NorthHandle extends AbstractHandle {

	public NorthHandle(AbstractFigure figure) {
		super(figure, "N");
	}

	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
	}

	@Override
	public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
		Rectangle rec = _owner.getBounds();
		_anchor = new Point(rec.x, rec.y + rec.height);
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
	
	@Override
	public Point getLocation() {
		Rectangle rectangle = _owner.getBounds();
		return new Point(rectangle.x + (rectangle.width/2), rectangle.y);
	}
}
