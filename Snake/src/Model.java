import java.util.Random;



import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
public class Model {

	private Snake _s;
	private Renderer _r;
	private Random _rand;
	private ArrayList<Point> _snakeParts;
	private int _ticks, _direction, _score , _tailLength;
	private Point _head, _food;
	private static final int UP=0, DOWN=1, LEFT=2, RIGHT=3;
	private boolean _over, _paused;
	public Model(){
		_rand = new Random();
		_tailLength=2;
		_snakeParts = new ArrayList<Point>();
		_ticks =0;
		_head = new Point(0,0);
		_direction=DOWN;
		_over = false;
		_paused =true;
	}
	
	public void addObserver(Snake s){
		_s=s;
		
	}
	
	public void addRenderer(Renderer r){
		_r=r;
	}
	
	public ArrayList<Point> getSnakeParts(){
		return _snakeParts;
	}
	
	public void stateChanged(){
		if(_r!=null && _s!=null){
			_r.repaint();
			if(!_over && !_paused){
			_ticks++;
			}
			else{
				_ticks+=0;
			}
			if(_ticks%2==0 && _head!=null && !_over &&!_paused){
				_snakeParts.add(new Point(_head.x,_head.y));
				if(_direction==UP){
					if(_head.y-1>=0 ){
					_head=new Point(_head.x, _head.y-1);
					}
					else{
						_over = true;
					}
					}
				if(_direction==DOWN){
					if(_head.y+1<76){
				_head=new Point(_head.x, _head.y+1);
					}
					else{
						_over = true;
					}
				}
				if(_direction==LEFT){
					if(_head.x-1>=0){
					_head=new Point(_head.x-1, _head.y);
					}
					else{
						_over = true;
					}
					}
				if(_direction==RIGHT){
					if(_head.x+1<79){
					_head=new Point(_head.x+1, _head.y);
					}
					else{
						_over = true;
					}
					}
				for(Point p : _snakeParts){
					if(_head.x==p.x && _head.y==p.y){
						_over = true;
					}
				}
				if (_snakeParts.size() > _tailLength)
				{
					_snakeParts.remove(0);

				}
			
			}
		}
		this.eat();
	}
	
	public void eat(){
		if(_food!=null){
			if(_head.equals(_food)){
     			_score+=10;
				_tailLength++;
				int k =_rand.nextInt(78);
				int l= _rand.nextInt(76);
				for(Point p : _snakeParts){
					if(p.x!=k && p.y!=l){
				_food.setLocation(k,l);
					}
					else{
						do{
							k =_rand.nextInt(78);
							l= _rand.nextInt(76);
							_food.setLocation(k,l);
						}while(p.x==k && p.y==l);
						
					}

				}
			}
		}
	

	}
	public void createFood(){
_food = new Point(_rand.nextInt(78),_rand.nextInt(76));
	}

	public int getScore(){
		return _score;
	}
	public void setHead(int x, int y){
		_head = new Point(x,y);
	}
	
	public Point getHead(){
		return _head;
	}
	
	public void leftMove(){
		if(_direction!=RIGHT){
		_direction=LEFT;
		}
	}
	public void rightMove(){
		if(_direction!=LEFT){
		_direction=RIGHT;
		}
	}
	public void upMove(){
		if(_direction!=DOWN){
		_direction=UP;
		}
	}
	public void downMove(){
		if(_direction!=UP){
		_direction=DOWN;
		}
	}
	
	public void startGame(){
		if(_over){
			
			_over=false;
			_head = new Point(0,-1);
			_snakeParts.clear();
			_score=0;
			_tailLength=2;
			_direction=DOWN;
			_ticks=0;
			this.createFood();
			_s.getTimer().start();
		}
		else{
			_paused=!_paused;
		}
	}
	public Point getFood(){
		return _food;
		
	}
	public int getTime(){
		return _ticks;
	}
	public String getHighScore() throws IOException{
		changeHighScore();

		Iterator<String> it1 = new HighScoreReader("HighScore/HighScore.txt");
		String s1 =it1.next();
		return s1+"";

	}
	public void changeHighScore() throws IOException{

		Iterator<String> it1 = new HighScoreReader("HighScore/HighScore.txt");
		String s1 =it1.next();
		int result = Integer.parseInt(s1);


		BufferedReader file = new BufferedReader(new FileReader("HighScore/HighScore.txt"));
		String line;
		String input = "";

		while ((line = file.readLine()) != null)
			input += line + System.lineSeparator();
		if(getScore()>result){
			input = input.replace(s1, getScore()+"");
		}
		FileOutputStream os = new FileOutputStream("HighScore/HighScore.txt");
		os.write(input.getBytes());

		file.close();
		os.close();
	}
	public boolean getPaused(){
		return _paused;
	}
	public boolean getGameOver(){
		return _over;
	}
}
