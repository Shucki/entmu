package jdraw.std.action;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import jdraw.framework.Figure;
import jdraw.std.StdContext;

public class CutAction extends AbstractAction implements MenuListener {

	private StdContext context;
	
	public CutAction(StdContext context) {
		this.context = context;
		this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control X"));
		this.putValue(Action.NAME, "Cut");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		List<Figure> s = context.getView().getSelection();
	    for (Figure f : s) context.getModel().removeFigure(f);
	    context.clipboard = s;
	}

	@Override
	public void menuSelected(MenuEvent e) {
		setEnabled(context.getView().getSelection().size() > 0);
	}

	@Override
	public void menuDeselected(MenuEvent e) {
		setEnabled(true);
	}

	@Override
	public void menuCanceled(MenuEvent e) {
		setEnabled(true);
	}

}
