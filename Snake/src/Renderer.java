import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.IOException;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Renderer extends JPanel{
	private Snake _s;
	private static Color _green; 
	private Model _m;
	public Renderer(){
		_green = new Color(1666073);
	}
	
	public void addObserver(Snake s){
		_s=s;
	}
	public void addModel(Model m){
		_m=m;
	}
@Override
public void paintComponent(Graphics g){
	super.paintComponent(g);
	g.setColor(_green);
	g.fillRect(0, 0, 800, 800);
	g.setColor(Color.BLUE);
	for(Point p : _m.getSnakeParts()){
		g.fillRect(p.x*10, p.y*10, 10, 10);
	}
	g.fillRect(_m.getHead().x*10, _m.getHead().y*10, 10, 10);
	g.setColor(Color.RED);
	g.fillRect(_m.getFood().x*10, _m.getFood().y*10, 10, 10);
	g.setColor(Color.WHITE);
	String s;
	try {
		s = "Score: " + _m.getScore() + ", Time: " +_m.getTime()/20 +", High Score:  "+ _m.getHighScore();
		g.drawString(s, (int) (getWidth() / 2 - s.length() * 2.5f),10);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

	if (_m.getGameOver()){
		s = "Game Over!";
		g.drawString(s, (int) (getWidth() / 2 - s.length() * 2.5f), (int) _s.getDim().getHeight() / 4);
	}

	

	if (_m.getPaused() && !_m.getGameOver()){
		s = "Paused!";
		g.drawString(s, (int) (getWidth() / 2 - s.length() * 2.5f), (int) _s.getDim().getHeight()/ 4);
	}
}
}


