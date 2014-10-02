package pack;

import java.awt.Image;
import java.awt.event.KeyEvent;

public class hero {
	int powerLevel = 3;//stored power
	int idleBanter = 0;//this keeps track on the amount of time spent charging, or standing still. The name is 
	//a dragon ball z reference 
	ether culpret = null;
	int health = 1000;
	int x = 5;
	int y = 5;
	int X = 5;
	int Y = 5;
	int XX = 5;
	int YY = 5;
	String id;
	int type = 2;
	boolean canFire = false;
	int coolDown = 0;
	Image pic;
	int dir = 1;
	int dir2 = 1;
	int dir3 = 1;
	boolean moved = false;
	int bultype = 0;
	
	int respawn = 0;
	boolean died = false;
	
	public void move(keyFrame a){
		
		if(died) respawn--;
		if(respawn <= 0 && died) {health = 1000; died = false;}
		if(health < 0 && !died){died = true; respawn = (100 * Main.others.size()); System.out.println("Died!");
		if(respawn > 10000) respawn = 10000; if(respawn < 200) respawn = 200;}
		
		type = 0;
		if(coolDown > 0) coolDown--;
		if(coolDown == 0) canFire = true; 
		XX = X;
		YY = Y;
		X = x;//capital coords are one frame in the past
		Y = y;
		
		moved = false;
		if(a.keys[KeyEvent.VK_D] == true){x = x+4; dir2 = 1;moved = true;}
		if(a.keys[KeyEvent.VK_W] == true){y = y-4; dir2 = 2;moved = true;}
		if(a.keys[KeyEvent.VK_S] == true){y = y+4; dir2 = 4;moved = true;}
		if(a.keys[KeyEvent.VK_A] == true){x = x-4; dir2 = 3;moved = true;}
		
		if(a.keys[KeyEvent.VK_SHIFT] && powerLevel >= 1){powerLevel--;
			switch(dir2){
			case 1: {x = x + 200; break;}
			case 2: {y = y - 200; break;}
			case 3: {x = x - 200; break;}
			case 4: {y = y + 200; break;}
			}
			
		}
		
		if(a.keys[KeyEvent.VK_H] && powerLevel > 0){powerLevel--; health = health+ Math.max(health/10, 200);}
		
		if (X - XX != x - X)Main.frame.flag = true;
		if (Y - YY != y - Y)Main.frame.flag = true;
		
		
		if(dir2 == 1){
			switch (dir){
			case 1: {pic = Main.rr;break;}
			case 2: {pic = Main.ru;break;}
			case 3: {pic = Main.rl;break;}
			case 4: {pic = Main.rd;break;}
			}
		}
			
			else if(dir2 == 2){
				switch (dir){
				case 1: {pic = Main.ur;break;}
				case 2: {pic = Main.uu;break;}
				case 3: {pic = Main.ul;break;}
				case 4: {pic = Main.ud;break;}
			}
			}
			else if(dir2 == 3){
				switch (dir){
				case 1: {pic = Main.lr;break;}
				case 2: {pic = Main.lu;break;}
				case 3: {pic = Main.ll;break;}
				case 4: {pic = Main.ld;break;}
		}
			}
			else if(dir2 == 4){
				switch (dir){
				case 1: {pic = Main.dr;break;}
				case 2: {pic = Main.du;break;}
				case 3: {pic = Main.dl;break;}
				case 4: {pic = Main.dd;break;}
			}
			}
		
		
		if(bulCollide())damage();
		
		if (collide()){//x = X; y = Y;
			if(x > 1280) x = 1280;
			if(x < 0)x = 0;
			if(y > 720)y = 720;
			if(y < 0)y = 0;
			if(x > X) x = x - 5;
			else if(x < X) x = x + 5;
			if(y > Y) y = y - 5;
			else if(y < Y) y = y + 5;
		}
		
		//check for damage, if damage has occured, set canfire to false
		
		if(canFire){
			type = 0;
			if(a.keys[KeyEvent.VK_RIGHT] && a.keys[KeyEvent.VK_SPACE] && powerLevel > 0){powerLevel--; type = 5;}
			else if(a.keys[KeyEvent.VK_UP] && a.keys[KeyEvent.VK_SPACE] && powerLevel > 0){powerLevel--; type = 6;}
			else if(a.keys[KeyEvent.VK_LEFT] && a.keys[KeyEvent.VK_SPACE] && powerLevel > 0){powerLevel--; type = 7;}
			else if(a.keys[KeyEvent.VK_DOWN] && a.keys[KeyEvent.VK_SPACE] && powerLevel > 0){powerLevel--; type = 8;}
			
			else if(a.keys[KeyEvent.VK_RIGHT]) type = 1;
			else if(a.keys[KeyEvent.VK_UP]) type = 2;
			else if(a.keys[KeyEvent.VK_LEFT]) type = 3;
			else if(a.keys[KeyEvent.VK_DOWN]) type = 4;
			if(type > 0 && type <= 8 && health > 0) {
				Main.frame.flag = true;
				dir = type; if (dir > 4)dir = type-4;
				health = health -15;
				canFire = false;
				coolDown = 7;
				bullet bul = new bullet(x, y, type, true, null);
				Main.bullets.add(bul);
			}
		}
		
		if (type != 0 || moved) idleBanter = 0;
		else idleBanter++;
		if(idleBanter > 60){powerLevel++; idleBanter = 0;}
		if(powerLevel > 3){ powerLevel = 3; idleBanter = 25;}//this reduces wait time for replenishment if you 
		//waited longer than required
		
	}
	private void damage(){
		int loss = health/3;
		if(bultype > 4){loss = (int) (health / 1.5); if(loss < 200) loss = 200;}
		if(loss < 100) loss = 100;
		idleBanter = 0;
		Main.outputBuffer.println("~" + culpret.id + "~" + new Integer(loss).toString() + "~");
		health = health - loss;
		Main.frame.flag = true;
	}
	public boolean bulCollide(){
		//check for collision with shot
		for (bullet bul: Main.bullets){
			if(Math.sqrt((Math.pow((bul.x-x),2))+(Math.pow((bul.y-y),2))) < ((Math.sqrt(health)/2)+10) && !bul.home){
				bul.kill = true;
				culpret = bul.q;
				bultype = bul.z;
				return true;
			}
		}
		//destroy shot if it collided
		return false;
	}
	public boolean collide(){
		int i;
		
		for(i = 0; i<Main.others.size(); i++){
			int xd = x-Main.others.get(i).x;
			int yd = y-Main.others.get(i).y;
			int d = (int)Math.sqrt(Math.pow(xd, 2)+Math.pow(yd, 2));
			if(d<((Math.sqrt(health)/2)+(Math.sqrt(Main.others.get(i).health)/2))) return true;
		}
		if(x < 0) return true;
		else if (x > 1280) return true;
		else if (y < 0) return true;
		else if (y > 720) return true;
		return false;
	}
	public String getStatus(){
		//String status = new String("~"+id+"~"+new Integer(health).toString()+"~"+ new Integer(x).toString()+"~"+new Integer(y).toString()
		//		+"~"+new Integer(type).toString()+"\n");
		String status = new String("~"+id+"~"+new Integer(health).toString()+"~"+ new Integer(x).toString()
				+"~"+new Integer(y).toString()+"~"+new Integer(x-X).toString()+"~"+new Integer(y-Y).toString()
				+"~"+new Integer(type).toString()+"~"+new Integer(powerLevel).toString()+"~");
		return status;
	}
	public hero(String z, int num){
		id = z;
		x = 25 * num;
		y = 15 * num;
		pic = Main.uu;
	}
}
