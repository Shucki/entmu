package jdraw.figures;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

public abstract class AbstractDecorator implements Figure {
	
	protected Figure inner;
	
	public AbstractDecorator(Figure f) {
		inner = f;
	}
	
	public Figure getDecoratedFigure() {
		  return inner;
	}
	
	@Override
	public void draw(Graphics g) {
		inner.draw(g);
	}
	
	@Override
	public void move(int dx, int dy) {
		inner.move(dx, dy);
	}

	@Override
	public boolean contains(int x, int y) {
		return getBounds().contains(x, y);
	}

	@Override
	public void setBounds(Point origin, Point corner) {
		inner.setBounds(origin, corner);
	}
	
	@Override
	public void addFigureListener(FigureListener listener) {
		inner.addFigureListener(listener);
	}

	@Override
	public void removeFigureListener(FigureListener listener) {
		inner.removeFigureListener(listener);
	}
	
	@Override
	public AbstractDecorator clone() {
		try {
			AbstractDecorator f = (AbstractDecorator) super.clone();
			f.inner = (Figure) f.clone();
			return f;
		} catch (CloneNotSupportedException e){ throw new InternalError(); } 
	}
	
	@Override
	public List<FigureHandle> getHandles() { 
		List<FigureHandle> handles = new ArrayList<FigureHandle>(inner.getHandles().size());
		for (FigureHandle h : inner.getHandles()) handles.add(new HandleDecorator(h));
		return Collections.unmodifiableList(handles);
	}
		
	
	public class HandleDecorator implements FigureHandle {
		
		private final FigureHandle inner;
		
		public HandleDecorator(FigureHandle handle) {
			this.inner = handle;
		}

		@Override
		public Figure getOwner() {
			return AbstractDecorator.this;
		}

		@Override
		public Point getLocation() {
			return inner.getLocation();
		}

		@Override
		public void draw(Graphics g) {
			inner.draw(g);
		}

		@Override
		public Cursor getCursor() {
			return inner.getCursor();
		}

		@Override
		public boolean contains(int x, int y) {
			return inner.contains(x, y);
		}

		@Override
		public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
			inner.startInteraction(x, y, e, v);
		}

		@Override
		public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
			inner.dragInteraction(x, y, e, v);
		}

		@Override
		public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
			inner.stopInteraction(x, y, e, v);
		}
	}

}
