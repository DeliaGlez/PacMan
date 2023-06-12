import java.awt.Color;

public class Pastilla {
    int x=0;
		int y=0;
		int w=0;
		int h=0;
		Color c= Color.black;
		Pastilla(int x,int y, int w, int h, Color c){
			this.x=x;
			this.y=y;
			this.w=w;
			this.h=h;
			this.c=c;
		}
		Pastilla(int x,int y){
			this.x=x;
			this.y=y;
			this.w=10;
			this.h=10;
			this.c=Color.black;
		}
		
		public boolean colision(Pastilla target) {
			
			if(this.x<target.x + target.w &&
					this.x+this.w>target.x &&
					
					this.y<target.y+target.h &&
					this.y+this.h>target.y) {
				return true;
			}
			
			return false;
			
		}
}
