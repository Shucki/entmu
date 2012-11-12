package jdraw.std;

import java.awt.Point;

import jdraw.framework.DrawModelEvent;
import jdraw.framework.DrawModelListener;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;
import jdraw.framework.PointConstrainer;

public class SnapGridDragHandle implements PointConstrainer {
	private static final int SNAP = 15;
	private DrawView view;
	private DrawModelListener listener; 
	private Figure figure;
	private Point p0 = new Point();
	private boolean snapped = false;
	
	public SnapGridDragHandle(DrawView view) {
		this.view = view;
		listener = new DrawModelListener() {
			@Override
			public void modelChanged(DrawModelEvent e) {
				if (e.getType() == DrawModelEvent.Type.FIGURE_CHANGED) {
					figure = e.getFigure();
			    } else {
			    	figure = null;
			    }
			}
		};
		view.getModel().addModelChangeListener(listener);
	}
	
	
	@Override
	public Point constrainPoint(Point p) {
		if (snapped) {
		     if (nearBy(p0, p)) { return p0; }
		     else { snapped = false; return p0 = p; }
		}
	    for (Figure f : view.getModel().getFigures()) {
	    	if (!view.getSelection().contains(f) && f != figure) {
	    		for (FigureHandle h : f.getHandles()) {
	    			FigureHandle nearHandle = findNearHandleOf(h);
	    			if (nearHandle != null) { 
	    				snapped = true;
	    				int dx = nearHandle.getLocation().x-h.getLocation().x;
	    				int dy = nearHandle.getLocation().y-h.getLocation().y;
	    				return p0 = new Point(p0.x + dx, p0.y + dy);
	    			}
	    		}
	    	}
	    }
	    return p;
	}
		
	private boolean nearBy(Point p, Point q) {
	    return p.distance(q.x, q.y) < SNAP;
	}
	
	private FigureHandle findNearHandleOf(FigureHandle h) { 
		for (Figure f : view.getModel().getFigures()) {
			if (!view.getSelection().contains(f)) {
				  for (FigureHandle nh : f.getHandles()) {
					  if (nearBy(h.getLocation(), nh.getLocation())) {
						  return nh;
					  }
				  }
			}	  
		}
		return null;
	}

	@Override
	public int getStepX(boolean right) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getStepY(boolean down) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub

	}

}
