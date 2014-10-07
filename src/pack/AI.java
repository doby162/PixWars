package pack;

public class AI {//when applicable, all method arguments are in value, index order
	int[] vars = new int[200];
	boolean[] bools = new boolean[100];
	
	public void keyReset(){
		
		for (int i = 0; i < Main.frame.keys.length; i++) {
			Main.frame.keys[i] = false;
		}
	}
	public void setKey(boolean a, int b){
		if(b < 525){
			Main.frame.keys[b] = a;
		}
	}
		
		public void setVar(int a, int b){
			if(b < 200){
				vars[b] = a;
			}
		}
		
		public void setBool(boolean a, int b){
			if(b < 100){
				bools[b] = a;
			}
		}
		
		public boolean getBool(int a){
			if(a < 100){
				return bools[a];
			}
			return false;
		}
		
		public int getVar(int a){
			if(a < 200){
				return vars[a];
			}
			return 0;
		}
		
		public boolean getKey(int a){
		if(a>525)return Main.frame.keys[a];
		return false;
	}
		
		public void print(String a){
			System.out.println(a);
		}
		public void print(int a){
			System.out.println(a);
		}
		public void print(boolean a){
			System.out.println(a);
		}
		
		public void music(){
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
		
		public int getX(){
			return Main.bob.x;
		}
		public int getY(){
			return Main.bob.y;
		}
		public int getHealth(){
			return Main.bob.health;
		}
		public int getPower(){
			return Main.bob.powerLevel;
		}
		public boolean canFire(){
			return Main.bob.canFire;
		}
		public boolean died(){
			return Main.bob.died;
		}
		public int culpretX(){
			if(Main.bob.culpret == null)return -1;
			return Main.bob.culpret.x;
		}
		public int culpretY(){
			if(Main.bob.culpret == null)return -1;
			return Main.bob.culpret.Y;
		}
		public int culpretHealth(){
			if(Main.bob.culpret == null)return -1;
			return Main.bob.culpret.health;
		}
		public int culpretDir(){
			return Main.bob.culpret.dir2;//direction of face
		}
		public int numberOfPlayers(){
			return Main.others.size();
		}
		public int numberOfBullets(){
			return Main.bullets.size();
		}
		public int bulletX(int index){
			if(Main.bullets.size() == 0 || Main.bullets.size() < index)return -1;
			return Main.bullets.get(index-1).x;
		}
		public int bulletY(int index){
			if(Main.bullets.size() == 0 || Main.bullets.size() < index)return -1;
			return Main.bullets.get(index-1).y;
		}
		public int bulletDir(int index){
			if(Main.bullets.size() == 0 || Main.bullets.size() < index)return -1;
			return Main.bullets.get(index-1).z;
		}
		public int baddyX(int index){
			if(Main.others.size() == 0 || Main.others.size() < index)return -1;
			return Main.others.get(index-1).x;
		}
		public int baddyY(int index){
			if(Main.others.size() == 0 || Main.others.size() < index)return -1;
			return Main.others.get(index-1).y;
		}
		public int baddyHealth(int index){
			if(Main.others.size() == 0 || Main.others.size() < index)return -1;
			return Main.others.get(index-1).health;
		}
		public void quit(){
			System.exit(0);
		}
	
}
