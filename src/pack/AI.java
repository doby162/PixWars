package pack;

public class AI {//when applicable, all method arguments are in value, index order
	public int[] vars = new int[200];
	public boolean[] bools = new boolean[100];
	public bullet[] bullets = new bullet[20];
	public ether[] baddies = new ether[20];
	public int w = 87;
	public int a = 65;
	public int s = 83;
	public int d = 68;
	public int up = 38;
	public int left = 37;
	public int down = 40;
	public int right = 39;
	public int shift = 16;
	public int space = 32;
	public int h = 72;
	
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
		public boolean culpretMoved(int index){
			if(Main.bob.culpret == null)return false;
			if(Main.bob.culpret.x != Main.bob.culpret.X || 
					Main.bob.culpret.y != Main.bob.culpret.Y) return true;
			return false;
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
		public boolean baddyMoved(int index){
			if(Main.others.size() == 0 || Main.others.size() < index)return false;
			if(Main.others.get(index).x != Main.others.get(index).X || 
					Main.others.get(index).y != Main.others.get(index).Y) return true;
			return false;
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
		
		public void storeBaddie(int i, int index){
			try{baddies[index] = Main.others.get(i-1);}catch(Exception e){System.out.println("fail");}
		}
		public void storeBullets(int i, int index){
			try{bullets[index] = Main.bullets.get(i);}catch(Exception e){}
		}
		public int storedBaddieX(int index){
			if(baddies[index] != null)return baddies[index].x;
			return -1;
		}
		public int storedBaddieY(int index){
			if(baddies[index] != null)return baddies[index].Y;
			return -1;
		}
		public int storedBaddieHealth(int index){
			if(baddies[index] != null)return baddies[index].health;
			return -1;
		}
		public int storedBaddieDir(int index){
			if(baddies[index] != null)return baddies[index].dir2;
			return -1;
		}
		public boolean storedBaddyMoved(int index){
			if(baddies[index] == null)return false;
			if(baddies[index].x != baddies[index].X || 
					baddies[index].y != baddies[index].Y) return true;
			return false;
		}
		public int storedBulletsX(int index){
			if(bullets[index] != null)return bullets[index].x;
			return -1;
		}
		public int storedBulletsY(int index){
			if(bullets[index] != null)return bullets[index].y;
			return -1;
		}
		public int storedBulletsDir(int index){
			if(bullets[index] != null)return bullets[index].z;
			return -1;
		}
	
}
