package jdraw.figures;

import java.awt.Color;
import java.awt.Graphics;

public class Oval extends AbstractRectFigure {

	private static final long serialVersionUID = -5693672773466170368L;

	public Oval(int x, int y, int w, int h) {
		super(x,y,w,h);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval((int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight());
		g.setColor(Color.BLACK);
		g.drawOval((int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight());
	}
}
