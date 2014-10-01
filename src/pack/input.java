package pack;

import java.io.BufferedReader;
import java.io.IOException;

public class input implements Runnable{
	BufferedReader in;
	String input = "";
	String formattedInput = "";
	
	public input(BufferedReader a){
		in = a;
	}

	@Override
	public void run() {
		while(true){
			try{Thread.sleep(5);}catch(Exception e){}
			try {
				input = in.readLine();
				if(input.contains(Main.name)){
					String trap = null;
					String[] tokens = input.split("~");
					try{trap = tokens[3];}catch(Exception thisIsActuallySuposedToHappen)
					//error indicates that the message is a short message, aka a damage message, and not ping
					{Main.bob.health = Main.bob.health + new Integer(tokens[2]);}
					//System.out.println(System.currentTimeMillis()-Main.ping);
				}
				//System.out.println(input);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}boolean match = false;
			try{
			for(ether e: Main.others){
				if(input.contains(e.id) && (input.length() - input.replace("~", "").length()) > 5){
					match = true;
					e.flag = true;
					e.countdown = 50;
					e.message = input;
				}
			}}catch(Exception e){}
			if(!match && !input.contains(Main.name)  && (input.length() - input.replace("~", "").length()) > 5){
				ether f = new ether();
				f.flag = true;
				f.countdown = 40;
				String[] tokens = input.split("~");
				f.id = tokens[1];
				f.message = input;
				f.dir = 1;
				f.dir2 = 1;
				Main.others.add(f);
				System.out.println("ether added: " + f.id);
			}
			
		}
		
	}
}
