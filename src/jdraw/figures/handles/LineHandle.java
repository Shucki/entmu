package jdraw.figures.handles;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import jdraw.figures.AbstractFigure;
import jdraw.framework.DrawView;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

public class LineHandle extends Handle implements FigureHandle, FigureListener {
		
	public LineHandle(AbstractFigure figure, String ident) {
		super(figure, ident);
	}

	@Override
	public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
		Rectangle rec = _owner.getBounds();
		Rectangle handle = new Rectangle(rec.x - SIZE, rec.y - SIZE, SIZE * 2, SIZE * 2);
		if (handle.contains(x,y)) _anchor = new Point(rec.x + rec.width, rec.y + rec.height);
		else                      _anchor = new Point(rec.x, rec.y);
	}

	@Override
	public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
		_owner.setBounds(_anchor, new Point(x, y));
	}

	@Override
	public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
		_owner.setBounds(_anchor, new Point(x, y));
	}
}
