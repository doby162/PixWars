package pack;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class keyFrame extends JFrame implements MouseListener, MouseMotionListener, KeyListener{
	static boolean[] keys = new boolean[525];

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		keys[arg0.getKeyCode()] = true;
		if(Main.textFlag){
			if(arg0.getKeyCode() == KeyEvent.VK_ENTER){ Main.textFlag = false;System.out.println(Main.name);}
			else Main.name = Main.name+arg0.getKeyChar();
		}
		if(keys[KeyEvent.VK_M]){
			if(Main.music){
				Main.music = false;
				Main.cd.close();
			}
			else {
				Main.cd = new mp3("Go Cart.mp3");
				Main.cd.play();
				Main.music = true;
			}
		}


	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		keys[arg0.getKeyCode()] = false;
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
