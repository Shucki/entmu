/*
 * Copyright (c) 2000-2012 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.std;

import java.util.ArrayList;
import java.util.LinkedList;

import jdraw.framework.DrawCommandHandler;
import jdraw.framework.DrawModel;
import jdraw.framework.DrawModelEvent;
import jdraw.framework.DrawModelListener;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureListener;

/**
 * Provide a standard behavior for the drawing model. This class initially does not implement the methods
 * in a proper way.
 * @author Jan Faessler <jan.faessler@students.fhnw.ch>
 *
 */
public class StdDrawModel implements DrawModel, FigureListener {
	
	LinkedList<Figure> figureList = new LinkedList<Figure>();
	ArrayList<DrawModelListener> listenerList = new ArrayList<DrawModelListener>();

	@Override
	public void addFigure(Figure f) {
		if (!figureList.contains(f)) {
			f.addFigureListener(this);
			figureList.addLast(f);
			this.updateAllListener(new DrawModelEvent(this,f,DrawModelEvent.Type.FIGURE_ADDED));
		}
	}

	@Override
	public Iterable<Figure> getFigures() {
		return figureList;
	}

	@Override
	public void removeFigure(Figure f) {
		if (figureList.contains(f)) {
			f.removeFigureListener(this);
			figureList.remove(f);
			this.updateAllListener(new DrawModelEvent(this,f,DrawModelEvent.Type.FIGURE_REMOVED));
		}
	}

	@Override
	 public void addModelChangeListener(DrawModelListener listener) {
	      if (listener != null && !listenerList.contains(listener)) {
	    	  listenerList.add(listener);
	      }
	}

	@Override
	public void removeModelChangeListener(DrawModelListener listener) {
		listenerList.remove(listener);
	}

	/** The draw command handler. Initialized here with a dummy implementation. */
	// TODO: initialize with your implementation from the assignments.
	private DrawCommandHandler handler = new EmptyDrawCommandHandler();

	/**
	 * Retrieve the draw command handler in use.
	 * @return the draw command handler.
	 */
	public DrawCommandHandler getDrawCommandHandler() {
		return handler;
	}

	@Override
	public void setFigureIndex(Figure f, int index) {
		
		if (!figureList.contains(f))                 throw new IllegalArgumentException();
		if (index < 0 || index >= figureList.size()) throw new IndexOutOfBoundsException();
		
		figureList.remove(f);
		figureList.add(index, f);
		this.updateAllListener(new DrawModelEvent(this,null,DrawModelEvent.Type.DRAWING_CHANGED));
	}

	@Override
	public void removeAllFigures() {
		for (Figure fig : figureList) {
			fig.removeFigureListener(this);
		}
		figureList.removeAll(figureList);
		this.updateAllListener(new DrawModelEvent(this,null,DrawModelEvent.Type.DRAWING_CLEARED));
	}
	
	protected void updateAllListener(DrawModelEvent e) {
		DrawModelListener[] copy = listenerList.toArray(new DrawModelListener[]{});
		for (DrawModelListener listener : copy) {
			listener.modelChanged(e);
		}
	}

	@Override
	public void figureChanged(FigureEvent e) {
		this.updateAllListener(new DrawModelEvent(this,e.getFigure(),DrawModelEvent.Type.FIGURE_CHANGED));
	}
}
