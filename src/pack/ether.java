package pack;

import java.awt.Image;
	
public class ether {
	int health;
	int x;
	int y;
	int X = 0;
	int Y = 0;
	String id;
	int type;
	int countdown = 15;
	int dir2;
	int dir;
	Image pic;
	public void testFire(){
		if(type > 0 && type < 5) {Main.bullets.add(new bullet(x, y, type, false, this));
		dir = type;
		}
		if(x > X) dir2 = 1;
		else if(X > x) dir2 = 3;
		else if(y > Y) dir2 = 4;
		else if(Y > y)dir2 = 2;
		X = x;
		Y = y;
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
	}

}
