package jdraw.figures;

import java.awt.Point;
import java.util.ArrayList;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureListener;

public abstract class AbstractFigure implements Figure {

	private static final long serialVersionUID = 2736707033115687659L;
	
	private ArrayList<FigureListener> _figureListenerList = new ArrayList<FigureListener>();
	
	@Override
	public Object clone() {
		return null;
	}
	
	@Override
	public void addFigureListener(FigureListener listener) {
		_figureListenerList.add(listener);
	}

	@Override
	public void removeFigureListener(FigureListener listener) {
		_figureListenerList.remove(listener);
	}
	
	protected void updateAllListener() {
		FigureListener[] copy = _figureListenerList.toArray(new FigureListener[_figureListenerList.size()]);
		for (FigureListener listener : copy) {
			listener.figureChanged(new FigureEvent(this));
		}
	}
	
	/**
	 * Returns a location of a specific Handle.
	 * 
	 * @param identifyer of the handle
	 * @return location of the handle
	 * @see java.awt.Point
	 */
	public Point getHandleLocation(String ident) { return null; } 
}
