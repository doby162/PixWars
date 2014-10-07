package pack;

public class AI {//when applicable, all method arguments are in value, index order
	int[] vars = new int[100];
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
			if(b < 100){
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
			if(a < 100){
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
		
		
	
}
