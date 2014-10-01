package pack;

public class bullet {
	int x;
	int y;
	int z;
	ether q;
	boolean home = false;
	boolean kill = false;
	public bullet(int a, int b, int c, boolean d, ether p){
		x=a;
		y=b;
		z=c;
		home = d;
		q=p;
	}
	public void move(){
		if (z == 1) x = x + 15;
		else if (z == 2) y = y - 15;
		else if (z == 3) x = x - 15;
		else if (z == 4) y = y + 15;
		
		if(y < 0) kill = true;
		else if(y > 2000) kill = true;
		if(x < 0) kill = true;
		else if(x > 2000) kill = true;
	}
	public boolean collide(){
		// this only handles collisions with ethers
		for (ether e: Main.others){
			int ghg =(int) (Math.sqrt(e.health)/2)+10;
			if(Math.sqrt((Math.pow((e.x-x),2))+(Math.pow((e.y-y),2))) < ghg && q != e) {kill = true;
				//if(home)Main.bob.health = Main.bob.health + Math.max((e.health/5),25); 
			}
		}
		return false;
	}
}
