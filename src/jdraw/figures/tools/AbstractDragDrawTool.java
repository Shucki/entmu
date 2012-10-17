package jdraw.figures.tools;

import java.awt.Point;
import java.awt.event.MouseEvent;

import jdraw.framework.DrawContext;
import jdraw.framework.Figure;

public abstract class AbstractDragDrawTool extends AbstractDrawTool {

	private DrawContext _context;
	private Point _anchor;
	private Figure _figure;
	
	protected AbstractDragDrawTool(DrawContext context, String name, String icon) {
		super(name, icon);
		_context = context;
	}
	
	@Override
	public void activate() {
		_context.showStatusText(getName()+" Mode");
	}

	@Override
	public void deactivate() {
		_context.showStatusText("");
	}
	
	protected abstract Figure createFigure(Point p);
	
	@Override
	public void mouseDown(int x, int y, MouseEvent e) {
	if (_figure != null) throw new IllegalStateException();
	
		_anchor = new Point(x, y);
		_figure = createFigure(_anchor); 
		_context.getModel().addFigure(_figure);
	}
	@Override
	public void mouseDrag(int x, int y, MouseEvent e) {
		_figure.setBounds(_anchor, new Point(x, y));
	}

	@Override
	public void mouseUp(int x, int y, MouseEvent e) {
		java.awt.Rectangle r = _figure.getBounds();
	    if (r.width == 0 && r.height == 0) _context.getModel().removeFigure(_figure);
	     _anchor = null;
	     _figure = null;
	}

}
