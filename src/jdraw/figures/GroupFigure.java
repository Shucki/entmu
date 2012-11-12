package jdraw.figures;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import jdraw.framework.Figure;
import jdraw.framework.FigureGroup;
import jdraw.framework.FigureHandle;

public class GroupFigure extends AbstractFigure implements FigureGroup {

	private static final long serialVersionUID = -6738718971698375257L;
	private List<Figure> parts;
	public GroupFigure(List<Figure> selectedFigures) {
		if(selectedFigures == null || (selectedFigures.size() <= 1)) throw new IllegalArgumentException();
	    this.parts = new LinkedList<Figure>(selectedFigures);
	}

	@Override
	public void draw(Graphics g) {
		for (Figure f : parts) f.draw(g);
	}

	@Override
	public void move(int dx, int dy) {
		for (Figure f : parts) f.move(dx, dy);
		//notifyChange();
	}

	@Override
	public boolean contains(int x, int y) {
		return getBounds().contains(x, y);
	}

	@Override
	public void setBounds(Point origin, Point corner) {
		// if setBounds is implemented, then original size
		// relations must be stored
	}

	@Override
	public Rectangle getBounds() {
		Rectangle bounds = null;
		for (Figure f : parts) {
			if (bounds == null) { bounds = f.getBounds(); }
			else { bounds.add(f.getBounds()); }
		}
		return bounds;
	}

	@Override
	public List<FigureHandle> getHandles() {
		// if setBounds is supported then RectangularFigure Handles 
		// may be used;
		// as an alternative the handles may be used to move
		// the group only.
		return null;
	}

	@Override
	public Iterable<Figure> getFigureParts() {
		return Collections.unmodifiableList(parts);
	}

}
