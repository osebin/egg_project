package processing;

import processing.core.PApplet;
import processing.core.PVector;

@SuppressWarnings("serial")
public class Circle extends PApplet{

	public static int num = 50;
	public static float size = (float) 100.0;
	
	Ellipse e[] = new Ellipse[num];
	
	public static int P_r = 0;
	public static int P_g = 0;
	public static int P_b = 0;
	
	public void setup(){
	
		size(240,260,P3D);
		for(int i = 0;i<num;i++)
			e[i] = new Ellipse(width/2,height/2,0,i);
	
		noFill();
		noSmooth();
	}
	
	public void draw(){
		background(255);
		
		for(int i = 0;i<num;i++){
			e[i].draw();
		}
	         
	         fill(0, 100, 200, 55);
	        
	         ellipse(width/2 - 90, height/2 - 50, 25, 25);
	         ellipse(width/2 + 90, height/2 - 50, 25, 25);
	
	         ellipse(width/2 - 60, height/2 - 90, 25, 25);
	         ellipse(width/2 + 60, height/2 - 90, 25, 25);
	         
	         ellipse(width/2 - 60, height/2 - 20, 25, 25);
	         ellipse(width/2 + 60, height/2 - 20, 25, 25);
	         
	         ellipse(width/2 - 30, height/2 - 50, 25, 25);         
	         ellipse(width/2 + 30, height/2 - 50, 25, 25);
	         
	         ellipse(width/2 - 100, height/2, 25, 25);
	         ellipse(width/2 + 100, height/2, 25, 25);
	         
	         ellipse(width/2 - 90, height/2 + 40, 25, 25);
	         ellipse(width/2 + 90, height/2 + 40, 25, 25);
	        
	         ellipse(width/2 - 70, height/2 + 75, 25, 25);
	         ellipse(width/2 + 70, height/2 + 75, 25, 25);
	
	         ellipse(width/2 - 30, height/2 + 100, 25, 25);
	         ellipse(width/2 + 30, height/2 + 100, 25, 25);
	         
	         noFill();
	}
	
	class Ellipse{
	
		float x,y,z;
		PVector rot;
		int id;
		float dens = (float) 0.1;
		float r = Circle.size;
	
		float rate = (float) 100.0;
		float speed = (float) 0.002;
	        int segno;
	
		Ellipse(float _x,float _y,float _z,int _id){
			x=_x;
			y=_y;
			z=_z;
			id=_id;
	                segno = (int)random(40);
	
			rot = new PVector(random(-1,1),random(-1,1),random(-1,1));
		}
		
		void follow(){
			x+=(mouseX-x)/(20.0);
			
			y+=(mouseY-y)/(20.0);
		}
	
		void draw(){
			//follow();
			
			rot.add(
			        noise((id+frameCount)/rate)*speed,
			        noise((float) ((id+34.0+frameCount)/rate))*speed,
			        noise((float) ((id+409.0+frameCount)/rate))*speed
			);
	
			pushMatrix();
			translate(x,y,z);
	
			pushMatrix();
			rotateX(rot.x);
			rotateY(rot.y);
			rotateZ(rot.z);
	
	                int cnt = 0;
	                
			beginShape();
			for(float f = -PI;f<PI;f+=dens){
	                        float X = cos(f)*r;
				float Y = sin(f)*r;
				                      
	  
	                        if(abs(cnt-segno)<5){
	
	                          stroke(P_r, P_g, P_b,map(modelZ(X,Y,0),-r,r,1,180-abs(cnt-segno)*30));
	                        
	                        }else{
	                          
	                         stroke(100,map(modelZ(X,Y,0),-r,r,1,60)); 
	                        
	                        }
	                        
				 strokeWeight(map(modelZ(X,Y,0),-r,r,5,(float) 1.8));
				
				vertex(X,Y,0);
	                        cnt++;
			}
			endShape(CLOSE);
	                
	                 if(frameCount%2==0)
	                segno++;
	                if(segno>=cnt){
	                 segno=0; 
	                }
			popMatrix();
			popMatrix();
		}
	}	
}
