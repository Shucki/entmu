package jdraw.figures.handles;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import jdraw.figures.AbstractFigure;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

public class Handle implements FigureHandle, FigureListener {
	
	protected AbstractFigure _owner;
	protected Point _center;
	protected Point _anchor;
	protected String _ident;
	protected int SIZE = 5;
	
	public Handle(AbstractFigure figure, String ident) {
		_owner = figure;
		_center = _owner.getHandleLocation(ident);
		_ident = ident;
		if (_owner != null) _owner.addFigureListener(this);
	}
	public String getIdent() {
		return _ident;
	}

	@Override
	public Figure getOwner() {
		return _owner;
	}

	@Override
	public Point getLocation() {
		return _center;
	}

	@Override
	public void draw(Graphics g) {
		if ("LT".equals(_ident)) g.setColor(Color.RED);
		else                     g.setColor(Color.WHITE);
		g.fillRect(_center.x - SIZE, _center.y - SIZE, SIZE*2, SIZE*2);
		g.setColor(Color.BLACK);
		g.drawRect(_center.x - SIZE, _center.y - SIZE, SIZE*2, SIZE*2);
	}

	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
	}

	@Override
	public boolean contains(int x, int y) {
		Rectangle rec = new Rectangle(_center.x - SIZE, _center.y - SIZE, SIZE * 2, SIZE * 2);
		return rec.contains(x, y);
	}

	@Override
	public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
		Rectangle rec = _owner.getBounds();
		_anchor = new Point(rec.x, rec.y);
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
	public void figureChanged(FigureEvent e) {
		_center = _owner.getHandleLocation(_ident);
	}

}
