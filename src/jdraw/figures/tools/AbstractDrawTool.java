package jdraw.figures.tools;

import java.awt.Cursor;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import jdraw.framework.DrawTool;

public abstract class AbstractDrawTool implements DrawTool {
	
	/** 
	 * the image resource path. 
	 */
	private static final String IMAGES = "/images/";
	private String _icon;
	private String _name;
	
	protected AbstractDrawTool(String name, String icon) { 
		_name = name; 
		_icon = icon;
	}

	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
	}

	@Override
	public Icon getIcon() {
		if (_icon != null) {
			return new ImageIcon(getClass().getResource(IMAGES+_icon));
	      } else { return null; }
	}
	
	@Override
	public String getName() {
		return _name;
	}
}
