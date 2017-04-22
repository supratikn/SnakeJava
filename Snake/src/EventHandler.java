import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EventHandler implements ActionListener, KeyListener{
   private Model _m;
   
   public EventHandler(Model m){
	   _m=m;
   }
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int i = e.getKeyCode();
		if (i==KeyEvent.VK_A ||i==KeyEvent.VK_LEFT){
			_m.leftMove();
		}
		if (i==KeyEvent.VK_S ||i==KeyEvent.VK_DOWN){
			_m.downMove();
		}
		if (i==KeyEvent.VK_D ||i==KeyEvent.VK_RIGHT){
			_m.rightMove();
		}
		if (i==KeyEvent.VK_W ||i==KeyEvent.VK_UP){
			_m.upMove();
		}
		if (i==KeyEvent.VK_SPACE){
			_m.startGame();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(_m!=null){
		_m.stateChanged();
		}
		
	}

}
