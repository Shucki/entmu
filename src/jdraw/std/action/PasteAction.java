package jdraw.std.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import jdraw.framework.Figure;
import jdraw.std.StdContext;

public class PasteAction extends AbstractAction implements MenuListener {
	
	private StdContext context;
	
	public PasteAction(StdContext context) {
		this.context = context;
		this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control V"));
		this.putValue(Action.NAME, "Paste");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (context.clipboard != null) {
			context.getView().clearSelection();
			
			for (Figure f : context.clipboard) {
				f.move(10, 10);
				f = (Figure) f.clone();
				context.getModel().addFigure(f);
				context.getView().addToSelection(f);
			}
		}
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
