package jdraw.figures;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

public class Handle implements FigureHandle, FigureListener {
	
	private AbstractFigure _owner;
	private Point _center;
	private Point _anchor;
	private String _ident;
	private int SIZE = 5;
	
	public Handle(AbstractFigure figure, Point point, String ident) {
		_owner = figure;
		_center = point;
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
		Cursor crs;
		switch (_ident) {
			case "LT": crs = Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR); break;
			case "RT": crs = Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR); break;
			case "LB": crs = Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR); break;
			case "RB": crs = Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR); break;
			default: crs = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
		}
		return crs;
	}

	@Override
	public boolean contains(int x, int y) {
		Rectangle rec = new Rectangle(_center.x - SIZE, _center.y - SIZE, _center.x + SIZE, _center.y + SIZE);
		return rec.contains(x, y);
	}

	@Override
	public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
		Rectangle rec = _owner.getBounds();
		Rectangle handle = new Rectangle(rec.x - SIZE, rec.y - SIZE, rec.x + SIZE, rec.y + SIZE);
		
		if (handle.contains(x, y)) {
			_anchor = new Point((int) (rec.getX() + rec.getWidth()), (int) (rec.getY() + rec.getHeight()));
		} else {
			_anchor = new Point((int) rec.getX(), (int) rec.getY());
		}

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
