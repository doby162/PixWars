package pack;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.net.URL;

import javax.swing.ImageIcon;

public class mainClass extends Applet implements Runnable, KeyListener, MouseListener{
	
	static boolean outOfDate = false;
	
	private Image dbImage;
    private Graphics dbg;
    
    static Image dd;
    static Image dl;
    static Image dr;
    static Image du;
    static Image ld;
    static Image ll;
    static Image lr;
    static Image lu;
    static Image rd;
    static Image rl;
    static Image rr;
    static Image ru;
    static Image ud;
    static Image ul;
    static Image ur;
    static Image uu;
    static URL url;
    
    
    static boolean[] keys = new boolean[525];
    int mousex;
    int mousey;
    Boolean hasclicked = false;
    
    mp3 cd;
    static boolean music = true;
    int mus = 0;
    
    Thread game;
    boolean flag = true;
    
    String name = "";
    boolean textFlag = true;
    
    BufferedReader inputBuffer;
    PrintWriter outputBuffer;
    String input = null;
    
    static ArrayList<ether> others = new ArrayList<ether>();  
    static ArrayList<bullet> bullets = new ArrayList<bullet>(); 
    
    static hero bob;
    
    static ether dead = null;
    
    @Override
	public void run() {
    	Thread t = Thread.currentThread();
    	double time = System.currentTimeMillis();
    	double timeElapsed = System.currentTimeMillis();
    	while(t == game){
    		time = System.currentTimeMillis();
    		bob.move(this);
    		if(true){
    			try{
    				bob.flag = false;
    	    		outputBuffer.write(bob.getStatus());
    	    		outputBuffer.flush();
    	    		}catch(Exception e){System.out.println("oops"); bob.flag = true; }
    			try{parse(inputBuffer.readLine());}catch (Exception e){}
    		}int i = 100020;
    		int j = 100021;
    		for(bullet bul: bullets){
    			bul.move();
    			if(bul.collide()) j = bullets.indexOf(bul);
    			if(bul.kill){i = bullets.indexOf(bul);
    			}
    		}
    		if(i != 100020)bullets.remove(i);
    		if(j != 100021)bullets.remove(j);
    		mus++;
    		if(mus > 2120 && music){
    			cd.close();
    			cd = new mp3("Go Cart.mp3");
    			cd.play();
    			mus = 0;
    		}
    		repaint();
    		timeElapsed = System.currentTimeMillis();
    		int a = (int)(timeElapsed - time);
    		try{Thread.sleep(100-a);}catch(Exception e){System.out.println("Ping too high!");}
    	}
		
	}
    
    public void parse(String a){
    	String[] tokens = a.split("~");
    	for(int i = 1; i < tokens.length; i = i+5){
    		boolean flag = true;
    		for(int j = 0; j < others.size(); j++){
    			if(others.get(j).id.equals(tokens[i])){
    				flag = false;
    				ether e = others.get(j);
    				e.health = new Integer(tokens[i+1]).intValue();
    				e.x = new Integer(tokens[i+2]).intValue();
    				e.y = new Integer(tokens[i+3]).intValue();
    				e.type = new Integer(tokens[i+4]).intValue();
    				e.testFire();
    				others.get(j).countdown = 15;
    			} else {others.get(j).countdown--;
    			if(others.get(j).countdown < 0) dead = others.get(j);
    			}
    		}
    		if(dead != null){System.out.println("trap triggered");
    			others.remove(dead);
    			dead = null;
    		}
    		
    		if(flag && !bob.id.equals(tokens[i])){
    			System.out.println("new ether");
    			ether e = new ether();
    			e.pic = mainClass.rr;
    			e.id = tokens[i];
    			e.health = new Integer(tokens[i+1]).intValue();
    			e.x = new Integer(tokens[i+2]).intValue();
    			e.y = new Integer(tokens[i+3]).intValue();
    			e.type = new Integer(tokens[i+4]).intValue();
    			others.add(e);
    		}
    	}
    }
    
    public void paint(Graphics g){
		 Graphics2D g2d = (Graphics2D) g;
	     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	     g2d.setColor(Color.WHITE);
	     g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
	     
	     if(outOfDate){
	    	g2d.setColor(Color.black);
	    	g2d.drawString("OUT OF DATE", 400, 400);
	     }
	     
	     g2d.setColor(Color.blue);
	     //g2d.fillRect((int)(bob.x-(Math.sqrt(bob.health)/2)),(int) (bob.y-(Math.sqrt(bob.health)/2)),(int) Math.sqrt(bob.health),(int)Math.sqrt(bob.health));
	     g2d.drawImage(bob.pic,(int) (bob.x-(Math.sqrt(bob.health)/2)), 
	     (int) (bob.y-(Math.sqrt(bob.health)/2)), (int)Math.sqrt(bob.health),(int) Math.sqrt(bob.health), this);
	     
	     g2d.drawString(bob.id, bob.x,(int) (bob.y-(Math.sqrt(bob.health)+10)));
	     
	     g2d.setColor(Color.green);
	     for(int i = 0; i<others.size(); i++){
	    	 g2d.drawImage(others.get(i).pic,(int) (others.get(i).x-(Math.sqrt(others.get(i).health)/2)), 
	    		     (int) (others.get(i).y-(Math.sqrt(others.get(i).health)/2)), (int)Math.sqrt(others.get(i).health),(int) Math.sqrt(others.get(i).health), this);
	    	 g2d.drawString(others.get(i).id, others.get(i).x,(int) (others.get(i).y-(Math.sqrt(others.get(i).health))+10));
	     }
	     g2d.setColor(Color.red);
	     for (bullet bub : bullets){
	    	 g2d.fillRect(bub.x-2, bub.y-2, 4, 4);
	     }
    }
    
    public void setUp(String arg, int num){
    	this.setSize(1280, 720);
    	addMouseListener(this);
        addKeyListener(this);
        
        menu(arg, num);
        
    	String IP = new String("107.196.11.5");
    	char date = '1';
    	try{
    	Socket clientSocket = new Socket(IP,1234);
		inputBuffer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		outputBuffer = new PrintWriter(clientSocket.getOutputStream());
		System.out.println("waiting for date");
		date = (char) inputBuffer.read();
		}
    	catch (Exception h){System.out.println("connection failed");bob = null;}
    	cd = new mp3("Go Cart.mp3");
    	cd.play();
    	
    	if(date != '2')outOfDate = true;
    	System.out.println(outOfDate);
    	url = getClass().getResource("dd.png");
        dd = new ImageIcon(url).getImage();
        url = getClass().getResource("dl.png");
        dl = new ImageIcon(url).getImage();
        url = getClass().getResource("dr.png");
        dr = new ImageIcon(url).getImage();
        url = getClass().getResource("du.png");
        du = new ImageIcon(url).getImage();
        url = getClass().getResource("ld.png");
        ld = new ImageIcon(url).getImage();
        url = getClass().getResource("ll.png");
        ll = new ImageIcon(url).getImage();
        url = getClass().getResource("lr.png");
        lr = new ImageIcon(url).getImage();
        url = getClass().getResource("lu.png");
        lu = new ImageIcon(url).getImage();
        url = getClass().getResource("rd.png");
        rd = new ImageIcon(url).getImage();
        url = getClass().getResource("rl.png");
        rl = new ImageIcon(url).getImage();
        url = getClass().getResource("rr.png");
        rr = new ImageIcon(url).getImage();
        url = getClass().getResource("ru.png");
        ru = new ImageIcon(url).getImage();
        url = getClass().getResource("ud.png");
        ud = new ImageIcon(url).getImage();
        url = getClass().getResource("ul.png");
        ul = new ImageIcon(url).getImage();
        url = getClass().getResource("ur.png");
        ur = new ImageIcon(url).getImage();
        url = getClass().getResource("uu.png");
        uu = new ImageIcon(url).getImage();
        
    }
    
    public void menu(String arg,int num){
    	/*while(textFlag){
    		try{Thread.sleep(200);}catch(Exception e){}
    		System.out.println("name is" +name);
    	}*/
    	name = arg;
    	bob = new hero(name, num);
    }
    
    public void start(String arg, int num){
    	if(flag){
    		flag = false;
	        game = new Thread(this);
	        setUp(arg, num);
	        game.start();
    	}
    }
    
    public void stop(){
    	//this code does not run when the window is closed
    }

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		keys[arg0.getKeyCode()] = true;
		if(textFlag){
			if(arg0.getKeyCode() == KeyEvent.VK_ENTER){ textFlag = false;System.out.println(name);}
			else name = name+arg0.getKeyChar();
		}
		if(keys[KeyEvent.VK_M]){
			if(music){
				music = false;
				cd.close();
			}
			else {
				cd = new mp3("Go Cart.mp3");
				cd.play();
				music = true;
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
	
	// Double Buffering(removes flicker)
    public void update (Graphics g){
        // initialize buffer
        if(dbImage == null){
            dbImage = createImage(this.getSize().width, this.getSize().height);
            dbg = dbImage.getGraphics();
        }

        // clear screen in background
        dbg.setColor(getBackground());
        dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);

        // draw elements in background 
        dbg.setColor(getForeground());
        paint(dbg);

        // draw image on the screen
        g.drawImage (dbImage, 0, 0, this);
    }

}
