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
		if(type > 0 && type < 5) {mainClass.bullets.add(new bullet(x, y, type, false, this));
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
			case 1: {pic = mainClass.rr;break;}
			case 2: {pic = mainClass.ru;break;}
			case 3: {pic = mainClass.rl;break;}
			case 4: {pic = mainClass.rd;break;}
			}
		}
			
			else if(dir2 == 2){
				switch (dir){
				case 1: {pic = mainClass.ur;break;}
				case 2: {pic = mainClass.uu;break;}
				case 3: {pic = mainClass.ul;break;}
				case 4: {pic = mainClass.ud;break;}
			}
			}
			else if(dir2 == 3){
				switch (dir){
				case 1: {pic = mainClass.lr;break;}
				case 2: {pic = mainClass.lu;break;}
				case 3: {pic = mainClass.ll;break;}
				case 4: {pic = mainClass.ld;break;}
		}
			}
			else if(dir2 == 4){
				switch (dir){
				case 1: {pic = mainClass.dr;break;}
				case 2: {pic = mainClass.du;break;}
				case 3: {pic = mainClass.dl;break;}
				case 4: {pic = mainClass.dd;break;}
			}
			}
	}

}
