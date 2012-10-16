package jdraw.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

import jdraw.framework.FigureHandle;

public class Oval extends AbstractFigure {

	private static final long serialVersionUID = -5693672773466170368L;
	
	private Ellipse2D oval;
	
	public Oval(int x, int y, int w, int h) {
		oval = new Ellipse2D.Double(x, y, w, h);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval((int) oval.getX(), (int) oval.getY(), (int) oval.getWidth(), (int) oval.getHeight());
		g.setColor(Color.BLACK);
		g.drawOval((int) oval.getX(), (int) oval.getY(), (int) oval.getWidth(), (int) oval.getHeight());
	}

	@Override
	public void move(int dx, int dy) {
		if (dx != 0 || dy != 0) {
			oval.setFrame((int) oval.getX() + dx, (int) oval.getY() + dy, oval.getWidth(), oval.getHeight());
			this.updateAllListener();
		}
	}

	@Override
	public boolean contains(int x, int y) {
		return oval.contains(x, y);
	}

	@Override
	public void setBounds(Point origin, Point corner) {
		oval.setFrameFromDiagonal(origin, corner);
		this.updateAllListener();
	}

	@Override
	public Rectangle getBounds() {
		return oval.getBounds();
	}

	@Override
	public List<FigureHandle> getHandles() {
		ArrayList<FigureHandle> handles = new ArrayList<FigureHandle>();
		handles.add(new Handle(this, getHandleLocation("LT"), "LT"));
		handles.add(new Handle(this, getHandleLocation("RT"), "RT"));
		handles.add(new Handle(this, getHandleLocation("LB"), "LB"));
		handles.add(new Handle(this, getHandleLocation("RB"), "RB"));
		return handles;
	}
	
	@Override
	public Point getHandleLocation(String ident) {
		Rectangle rectangle = oval.getBounds();
		Point pnt;
		switch (ident) {
			case "LT": pnt = new Point(rectangle.x,                   rectangle.y); break;
			case "RT": pnt = new Point(rectangle.x + rectangle.width, rectangle.y); break;
			case "LB": pnt = new Point(rectangle.x,                   rectangle.y + rectangle.height); break;
			case "RB": pnt = new Point(rectangle.x + rectangle.width, rectangle.y + rectangle.height); break;
			default: pnt = new Point();
		}
		return pnt;
	}

}
