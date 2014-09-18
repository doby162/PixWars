package pack;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Main{
	
	static boolean outOfDate = false;
	
	anim pic;
	
	boolean failedConnection = false;
	char date = '3';
	
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
    
    static keyFrame frame;
    
    
    int mousex;
    int mousey;
    Boolean hasclicked = false;
    
    static mp3 cd;
    static boolean music = true;
    int mus = 0;
    
    Thread game;
    static boolean flag = true;
    
    static String name = "";
    static boolean textFlag = true;
    
    BufferedReader inputBuffer;
    PrintWriter outputBuffer;
    String input = null;
    
    static ArrayList<ether> others = new ArrayList<ether>();  
    static ArrayList<bullet> bullets = new ArrayList<bullet>(); 
    
    static hero bob;
    
    static ether dead = null;
    
    public void loop() {
    	double time = System.currentTimeMillis();
    	double timeElapsed = System.currentTimeMillis();
    	while(true){
    		time = System.currentTimeMillis();
    		bob.move(frame);
    		if(true){
    			try{
    	    		outputBuffer.write(bob.getStatus());
    	    		outputBuffer.flush();
    	    		}catch(Exception e){System.out.println("Connection lost");failedConnection = true;}
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
    		frame.repaint();
    		timeElapsed = System.currentTimeMillis();
    		int a = (int)(timeElapsed - time);
    		try{Thread.sleep(100-a);}catch(Exception e){System.out.println("Ping too high!");}//magic
    		if(failedConnection) break;
    	}
		
	} //magic
    
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
    		if(dead != null){System.out.println("player " + dead.id + " has left the battle");
    			others.remove(dead);
    			dead = null;
    		}
    		
    		if(flag && !bob.id.equals(tokens[i])){
    			ether e = new ether();
    			e.pic = rr;
    			e.id = tokens[i];
    			e.health = new Integer(tokens[i+1]).intValue();
    			e.x = new Integer(tokens[i+2]).intValue();
    			e.y = new Integer(tokens[i+3]).intValue();
    			e.type = new Integer(tokens[i+4]).intValue();
    			System.out.println("Player "+ e.id + "has entered.");
    			others.add(e);
    		}
    	}
    }
    class anim extends JPanel{
    	public void paintComponent(Graphics g){
		 	Graphics2D g2d = (Graphics2D) g;
	     	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	     	g2d.setColor(Color.WHITE);
	     	g2d.fillRect(0, 0, frame.getWidth(), frame.getHeight());
	     
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
    }
    
    public void setUp(String arg, int num){
        frame = new keyFrame();
        frame.setTitle("PixWars V 2.0 (press 'm' to mute)");
        frame.setSize(1280, 720);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(keyFrame.EXIT_ON_CLOSE);
        frame.addMouseListener(frame);
        frame.addKeyListener(frame);
        
        pic = new anim();
        frame.add(pic);
        
        menu(arg, num);
        
    	String IP = new String("107.196.11.5");
    	try{
    	Socket clientSocket = new Socket(IP,1234);
		inputBuffer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		outputBuffer = new PrintWriter(clientSocket.getOutputStream());
		System.out.println("authenticating");
		//date = (char) inputBuffer.read();
		outputBuffer.println(date);
		}
    	catch (Exception h){System.out.println("connection failed, or server might be down");failedConnection = true;}
    	cd = new mp3("Go Cart.mp3");
    	cd.play();
    	
    	if(date != '3'){outOfDate = true; 
    	System.out.println("Authentication failed, please update");}
    	else {System.out.println("Authentification passed, game is up to date");}
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
        
        loop();
    }
    
    public void menu(String arg,int num){
    	/*while(textFlag){
    		try{Thread.sleep(200);}catch(Exception e){}
    		System.out.println("name is" +name);
    	}*/
    	name = arg;
    	bob = new hero(name, num);
    }

    //public void start(String arg, int num)
    	public static void main(String[] args){
    	if(flag){
    		String randomName = "a";
    		flag = false;
    		int a = (int) (Math.random()*35);
    		try{a = new Integer(args[1]).intValue();}catch(Exception butt){}
	        try{new Main().setUp(args[0], a);}catch(Exception dumb){
        	if(a == 0) randomName = "Doge";
        	else if(a == 1) randomName = "Bob";
        	else if(a == 2) randomName = "Bill";
        	else if(a == 3) randomName = "Onion";
        	else if(a == 4) randomName = "Skillet";
        	else if(a == 5) randomName = "USB drive";
        	else if(a == 6) randomName = "Edison";
        	else if(a == 7) randomName = "Macinwash";
        	else if(a == 8) randomName = "Windwools";
        	else if(a == 9) randomName = "Linpux";
        	else if(a == 10) randomName = "Protein";
        	else if(a == 11) randomName = "Carbohydrate";
        	else if(a == 12) randomName = "Fat";
        	else if(a == 13) randomName = "CannonFodder";
        	else if(a == 14) randomName = "Rice";
        	else if(a == 15) randomName = "Spinich";
        	
        	else if(a == 16) randomName = "Tesla";
        	else if(a == 17) randomName = "Gauss";
        	else if(a == 18) randomName = "Newton";
        	else if(a == 19) randomName = "Pi";
        	else if(a == 20) randomName = "Java";
        	else if(a == 21) randomName = "Kepler";
        	else if(a == 22) randomName = "Python";
        	else if(a == 23) randomName = "Lisp";
        	else if(a == 24) randomName = "Einstein";
        	else if(a == 25) randomName = "Hindenburg";
        	
        	else if(a == 26) randomName = "C++";
        	else if(a == 27) randomName = "Assembly";
        	else if(a == 28) randomName = "Pearl";
        	else if(a == 29) randomName = "QWERTY";
        	else if(a == 30) randomName = "Voltaire";
        	else if(a == 31) randomName = "Dalek";
        	else if(a == 32) randomName = "Feinman";
        	else if(a == 33) randomName = "Star Fox";
        	else if(a == 34) randomName = "Princess Peach";
        	else if(a == 35) randomName = "Higgs Boson";
        	
        	System.out.println("entering battle as "+randomName);
	        
	        new Main().setUp(randomName, a);}
    		}
    	}
    }
    
    