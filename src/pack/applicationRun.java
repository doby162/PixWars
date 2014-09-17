package pack;

import javax.swing.*;

public class applicationRun extends JFrame{
	    public static void main(String[] args){
	    	System.out.println("running in a JFrame");
	        mainClass mc = new mainClass();
	        JFrame f = new JFrame("PixWars V 1.2 (press 'm' to mute)");
	        f.setSize(1280, 720);
	        f.setVisible(true);
	        f.setResizable(false);
	        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
	        try{mc.start(args[0], 37);} catch(Exception e){
	        	int a = (int) (Math.random()*35);
	        	String randomName = "a";
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
	        	
	        	System.out.println(a);
	        	//randomeName = 
	        	mc.start(randomName, a);
	        	
	        }
	        f.add(mc);
	        }
}
