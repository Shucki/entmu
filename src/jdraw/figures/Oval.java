package jdraw.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

import jdraw.figures.handles.EastHandle;
import jdraw.figures.handles.NorthHandle;
import jdraw.figures.handles.SouthHandle;
import jdraw.figures.handles.WestHandle;
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
		handles.add(new NorthHandle(this));
		handles.add(new EastHandle(this));
		handles.add(new SouthHandle(this));
		handles.add(new WestHandle(this));
		return handles;
	}
	
	@Override
	public Point getHandleLocation(String ident) {
		Rectangle rectangle = oval.getBounds();
		Point pnt;
		switch (ident) {
			case "N":  pnt = new Point(rectangle.x + (rectangle.width/2), rectangle.y); break;
			case "S":  pnt = new Point(rectangle.x + (rectangle.width/2), rectangle.y + rectangle.height); break;
			case "W":  pnt = new Point(rectangle.x,                       rectangle.y + (rectangle.height/2)); break;
			case "E":  pnt = new Point(rectangle.x + rectangle.width,     rectangle.y + (rectangle.height/2)); break;
			default: pnt = new Point();
		}
		return pnt;
	}

}
