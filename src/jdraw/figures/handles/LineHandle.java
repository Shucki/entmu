package jdraw.figures.handles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import jdraw.figures.AbstractFigure;
import jdraw.figures.Line;
import jdraw.framework.DrawView;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

public class LineHandle extends AbstractHandle implements FigureHandle, FigureListener {
	
	private Line.Points _point;
	
	public LineHandle(AbstractFigure figure, Line.Points point) {
		super(figure, "");
		_point = point;
		_center = getLocation();
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(_point == Line.Points.POINT1?Color.RED:Color.WHITE);
		g.fillRect(_center.x - SIZE, _center.y - SIZE, SIZE*2, SIZE*2);
		g.setColor(Color.BLACK);
		g.drawRect(_center.x - SIZE, _center.y - SIZE, SIZE*2, SIZE*2);
	}

	@Override
	public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
		Rectangle rec = _owner.getBounds();

		Rectangle hdlNW = new Rectangle(rec.x - SIZE,           rec.y - SIZE,            SIZE*2, SIZE*2);
		Rectangle hdlNE = new Rectangle(rec.x+rec.width - SIZE, rec.y - SIZE,            SIZE*2, SIZE*2);
		Rectangle hdlSW = new Rectangle(rec.x - SIZE,           rec.y+rec.height - SIZE, SIZE*2, SIZE*2);
		Rectangle hdlSE = new Rectangle(rec.x+rec.width - SIZE, rec.y+rec.height - SIZE, SIZE*2, SIZE*2);
		
		if (hdlNW.contains(x, y))      _anchor = new Point(rec.x + rec.width, rec.y + rec.height);
		else if (hdlNE.contains(x, y)) _anchor = new Point(rec.x,             rec.y + rec.height);
		else if (hdlSW.contains(x, y)) _anchor = new Point(rec.x + rec.width, rec.y);
		else if (hdlSE.contains(x, y)) _anchor = new Point(rec.x,             rec.y);
	}

	@Override
	public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
		_owner.setBounds(_anchor, new Point(x, y));
	}

	@Override
	public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
		_owner.setBounds(_anchor, new Point(x, y));
	}

	@Override
	public Point getLocation() {
		Point pnt;
		Rectangle rec = _owner.getBounds();
		if (_point == Line.Points.POINT1) {
			if (_owner.contains(rec.x, rec.y)) pnt = rec.getLocation();
			else                               pnt = new Point(rec.x + rec.width, rec.y);
		} else {
			if (_owner.contains(rec.x + rec.width, rec.y + rec.height)) pnt = new Point (rec.x + rec.width, rec.y + rec.height);
			else                                                        pnt = new Point(rec.x, rec.y + rec.height);
		}
		return pnt;
	}
}
