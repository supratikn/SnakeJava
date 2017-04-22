
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.Timer;

import javax.swing.JFrame;

public class Snake implements Runnable{
	
	private Model _m;
	private Renderer _r;
	private JFrame _frame;
	private Timer _timer;
	private Dimension _dim;
	
	
	@Override
	public void run(){
		
		 
		Toolkit.getDefaultToolkit();
		_m = new Model();
        _r = new Renderer();
        _r.addObserver(this);
        _m.addObserver(this);
        _m.addRenderer(_r);
        _r.addModel(_m);
        _r.addObserver(this);
        _m.createFood();
        _timer = new Timer(20, new EventHandler(_m));
        _frame=new JFrame("Snake");
        _frame.setVisible(true);
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.setSize(800,800);
        _dim = Toolkit.getDefaultToolkit().getScreenSize();
        _frame.setLocation(_dim.width/2 - _frame.getWidth()/2, _dim.height/2 - _frame.getHeight()/2);
        _frame.setResizable(true);
        _frame.add(_r);
        _frame.addKeyListener(new EventHandler(_m));
        
        _timer.start();
        
	}
public Dimension getDim(){
	return _dim;
}
public Timer getTimer(){
	return _timer;
}
}
