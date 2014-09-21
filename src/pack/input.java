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
			try{Thread.sleep(20);}catch(Exception e){}
			try {
				input = in.readLine();
				//System.out.println(input);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}boolean match = false;
			System.out.println(Main.others.size());
			for(ether e: Main.others){
				if(input.contains(e.id)){
					match = true;
					e.flag = true;
					e.message = input;
					System.out.println("match:" + e.message);
				}
			}
			if(!match && !input.contains(Main.name)){
				System.out.println("main name is "+Main.name);
				ether f = new ether();
				f.flag = true;
				String[] tokens = input.split("~");
				f.id = tokens[1];
				System.out.println(tokens[1]);
				f.message = input;
				Main.others.add(f);
				System.out.println("ether added: " + f.id);
			}
			
		}
		
	}
}
