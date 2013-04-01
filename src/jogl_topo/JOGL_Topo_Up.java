package jogl_topo;

import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.media.opengl.*;
import javax.media.opengl.glu.*;

import jogl_topo.JOGL_Topo.*;

import com.sun.opengl.util.*;

public class JOGL_Topo_Up extends GLCanvas implements GLEventListener, MouseListener,
														MouseMotionListener ,MouseWheelListener{

	private boolean Topo_Up = false;
	
	private static final long serialVersionUID = 1L;
	private GLU glu; // GL unit 
	private int fps = 60;
	private FPSAnimator animator;
	
	private float moveX = 0;
	private int oldX = 0;
	
	private boolean isDragging = false;
	public static double [][]Channel_order = new double[14][14];
	
	ArrayList<Data> vertex = new ArrayList<Data>();
	float time = 0;
	
	float Rcolor = 0;
	float Gcolor = 0;
	float Bcolor = 0;

	public static Active_channel active;
	
	public JOGL_Topo_Up(GLCapabilities capabilities, int width, int height){
		addGLEventListener(this);
		setData("Object/sample.obj");
		active = new Active_channel();
	}
	
	public static GLCapabilities createGLCapabilities2(){
		GLCapabilities capabilities = new GLCapabilities();
		capabilities.setRedBits(8);
		capabilities.setBlueBits(8);
		capabilities.setGreenBits(8);
		capabilities.setAlphaBits(8);
		
		return capabilities;
	}
	
	@Override
	public void display(GLAutoDrawable drawable) {

		if (isDragging == false) {
			moveX++;
		}
		
		// TODO Auto-generated method stub
		final GL gl = drawable.getGL();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		setCamera(gl, glu, 120);
		
		gl.glRotatef(moveX/2 , 0, 100, 1);
		gl.glColor3f(1, 1, 1);
		

		gl.glBegin(GL.GL_POINTS);
		Iterator<Data> elements = vertex.iterator();
		while(elements.hasNext()){
			Data data = elements.next();
			gl.glVertex3d(data.x, data.y, data.z);
			
		}
		gl.glColor3f(1, 0, 0);
		gl.glVertex3d(0, 0, 0);
		gl.glEnd();

		boolean color_flag = false;
		
		gl.glBegin(GL.GL_LINE_LOOP);
		gl.glColor3f(Rcolor, Gcolor, Bcolor);
		gl.glColor3f(0, 1, 0);
		set_line(gl); // SET CORRELATION LINE LOOP
		gl.glEnd();
		
	}
	public class Active_channel{
		public boolean channel1;
		public boolean channel2;
		public boolean channel3;
		public boolean channel4;
		public boolean channel5;
		public boolean channel6;
		public boolean channel7;
		public boolean channel8;
		public boolean channel9;
		public boolean channel10;
		public boolean channel11;
		public boolean channel12;
		public boolean channel13;
		public boolean channel14;
		
		public boolean stand1;
		public boolean stand2;
		
		public Active_channel(){
			this.channel1 = false;
			this.channel2 = false;
			this.channel3 = false;
			this.channel4 = false;
			this.channel5 = false;
			this.channel6 = false;
			this.channel7 = false;
			this.channel8 = false;
			this.channel9 = false;
			this.channel10 = false;
			this.channel11 = false;
			this.channel12 = false;
			this.channel13 = false;
			this.channel14 = false;
			
			this.stand1 = true;
			this.stand2 = true;
		}
	}
	
	private void set_color(GL gl, int index){
		if(index < 5)
			gl.glColor3f(0, 1, 0);
		
		if(index > 5 && index < 10)
			gl.glColor3f(1, 1, 0);
		
		if(index > 10)
			gl.glColor3f(1, 0, 0);
		
	}
	private void set_line(GL gl){
		if(active.channel1 == true){
			set_color(gl, (int)Channel_order[0][0]);
			gl.glVertex3d(3.6085, 42.8455, 20.8714);
			gl.glVertex3d(10, 39.3633, 18.4634);
			
			set_color(gl, (int)Channel_order[0][1]);
			gl.glVertex3d(3.6085, 42.8455, 20.8714);
			gl.glVertex3d(3.2641, 47.3987, 16.2995);
			
			set_color(gl, (int)Channel_order[0][2]);
			gl.glVertex3d(3.6085, 42.8455, 20.8714);
			gl.glVertex3d(10.5403, 43.7706, 13.0306);
			
			set_color(gl, (int)Channel_order[0][3]);
			gl.glVertex3d(3.6085, 42.8455, 20.8714);
			gl.glVertex3d(15.6341, 30.4779, 4.1489);
			
			set_color(gl, (int)Channel_order[0][4]);
			gl.glVertex3d(3.6085, 42.8455, 20.8714);
			gl.glVertex3d(13.2435, 41.3030, -1.0178);
			
			set_color(gl, (int)Channel_order[0][5]);
			gl.glVertex3d(3.6085, 42.8455, 20.8714);
			gl.glVertex3d(13.5617, 33.4290, -6.0931);
			
			set_color(gl, (int)Channel_order[0][6]);
			gl.glVertex3d(3.6085, 42.8455, 20.8714);
			gl.glVertex3d(-15.5617, 33.4290, -6.0931);
			
			set_color(gl, (int)Channel_order[0][7]);
			gl.glVertex3d(3.6085, 42.8455, 20.8714);
			gl.glVertex3d(-15.2435, 41.3030, -1.0178);
			
			set_color(gl, (int)Channel_order[0][8]);
			gl.glVertex3d(3.6085, 42.8455, 20.8714);
			gl.glVertex3d(-17.5341, 30.4779, 4.14899);
			
			set_color(gl, (int)Channel_order[0][9]);
			gl.glVertex3d(3.6085, 42.8455, 20.8714);
			gl.glVertex3d(-16.5341, 23.6797, -1.02739);
			
			set_color(gl, (int)Channel_order[0][10]);
			gl.glVertex3d(3.6085, 42.8455, 20.8714);
			gl.glVertex3d(14.5341, 23.6797, -1.02739);
			
			set_color(gl, (int)Channel_order[0][11]);
			gl.glVertex3d(3.6085, 42.8455, 20.8714);
			gl.glVertex3d(-12.5403, 43.7706, 13.03069);
			
			set_color(gl, (int)Channel_order[0][12]);
			gl.glVertex3d(3.6085, 42.8455, 20.8714);
			gl.glVertex3d(-4.6641, 47.5696, 16.499599);
			
			set_color(gl, (int)Channel_order[0][13]);
			gl.glVertex3d(3.6085, 42.8455, 20.8714);
			gl.glVertex3d(-11.5, 39.3633, 18.46349999);
			
			set_color(gl, (int)Channel_order[0][13]);
			gl.glVertex3d(3.6085, 42.8455, 20.8714);
			gl.glVertex3d(-4.6084, 43.0455, 20.87129999);
		}
		//////////////////////////////////////////////
		
		if(active.channel2 == true){
			set_color(gl, (int)Channel_order[1][0]);
			gl.glVertex3d(10, 39.3633, 18.4634);
			gl.glVertex3d(3.6085, 42.8455, 20.8714);
			
			set_color(gl, (int)Channel_order[1][1]);
			gl.glVertex3d(10, 39.3633, 18.4634);
			gl.glVertex3d(3.2641, 47.3987, 16.2995);
			
			set_color(gl, (int)Channel_order[1][2]);
			gl.glVertex3d(10, 39.3633, 18.4634);
			gl.glVertex3d(10.5403, 43.7706, 13.0306);
			
			set_color(gl, (int)Channel_order[1][3]);
			gl.glVertex3d(10, 39.3633, 18.4634);
			gl.glVertex3d(15.6341, 30.4779, 4.1489);
			
			set_color(gl, (int)Channel_order[1][4]);
			gl.glVertex3d(10, 39.3633, 18.4634);
			gl.glVertex3d(13.2435, 41.3030, -1.0178);
			
			set_color(gl, (int)Channel_order[1][5]);
			gl.glVertex3d(10, 39.3633, 18.4634);
			gl.glVertex3d(13.5617, 33.4290, -6.0931);
			
			set_color(gl, (int)Channel_order[1][6]);
			gl.glVertex3d(10, 39.3633, 18.4634);
			gl.glVertex3d(-15.5617, 33.4290, -6.0931);
			
			set_color(gl, (int)Channel_order[1][7]);
			gl.glVertex3d(10, 39.3633, 18.4634);
			gl.glVertex3d(-15.2435, 41.3030, -1.0178);
			
			set_color(gl, (int)Channel_order[1][8]);
			gl.glVertex3d(10, 39.3633, 18.4634);
			gl.glVertex3d(-17.5341, 30.4779, 4.14899);
			
			set_color(gl, (int)Channel_order[1][9]);
			gl.glVertex3d(10, 39.3633, 18.4634);
			gl.glVertex3d(-16.5341, 23.6797, -1.02739);
			
			set_color(gl, (int)Channel_order[1][10]);
			gl.glVertex3d(10, 39.3633, 18.4634);
			gl.glVertex3d(14.5341, 23.6797, -1.02739);
			
			set_color(gl, (int)Channel_order[1][11]);
			gl.glVertex3d(10, 39.3633, 18.4634);
			gl.glVertex3d(-12.5403, 43.7706, 13.03069);
			
			set_color(gl, (int)Channel_order[1][12]);
			gl.glVertex3d(10, 39.3633, 18.4634);
			gl.glVertex3d(-4.6641, 47.5696, 16.499599);
			
			set_color(gl, (int)Channel_order[1][13]);
			gl.glVertex3d(10, 39.3633, 18.4634);
			gl.glVertex3d(-11.5, 39.3633, 18.46349999);
			
			set_color(gl, (int)Channel_order[1][13]);
			gl.glVertex3d(10, 39.3633, 18.4634);
			gl.glVertex3d(-4.6084, 43.0455, 20.87129999);
		}
		///////////////////////////////////////////
		if(active.channel3 == true){
			set_color(gl, (int)Channel_order[2][0]);
			gl.glVertex3d(3.2641, 47.3987, 16.2995);
			gl.glVertex3d(3.6085, 42.8455, 20.8714);
			
			set_color(gl, (int)Channel_order[2][1]);
			gl.glVertex3d(3.2641, 47.3987, 16.2995);
			gl.glVertex3d(10, 39.3633, 18.4634);
			
			set_color(gl, (int)Channel_order[2][2]);
			gl.glVertex3d(3.2641, 47.3987, 16.2995);
			gl.glVertex3d(10.5403, 43.7706, 13.0306);
			
			set_color(gl, (int)Channel_order[2][3]);
			gl.glVertex3d(3.2641, 47.3987, 16.2995);
			gl.glVertex3d(15.6341, 30.4779, 4.1489);
			
			set_color(gl, (int)Channel_order[2][4]);
			gl.glVertex3d(3.2641, 47.3987, 16.2995);
			gl.glVertex3d(13.2435, 41.3030, -1.0178);
			
			set_color(gl, (int)Channel_order[2][5]);
			gl.glVertex3d(3.2641, 47.3987, 16.2995);
			gl.glVertex3d(13.5617, 33.4290, -6.0931);
			
			set_color(gl, (int)Channel_order[2][6]);
			gl.glVertex3d(3.2641, 47.3987, 16.2995);
			gl.glVertex3d(-15.5617, 33.4290, -6.0931);
			
			set_color(gl, (int)Channel_order[2][7]);
			gl.glVertex3d(3.2641, 47.3987, 16.2995);
			gl.glVertex3d(-15.2435, 41.3030, -1.0178);
			
			set_color(gl, (int)Channel_order[2][8]);
			gl.glVertex3d(3.2641, 47.3987, 16.2995);
			gl.glVertex3d(-17.5341, 30.4779, 4.14899);
			
			set_color(gl, (int)Channel_order[2][9]);
			gl.glVertex3d(3.2641, 47.3987, 16.2995);
			gl.glVertex3d(-16.5341, 23.6797, -1.02739);
			
			set_color(gl, (int)Channel_order[2][10]);
			gl.glVertex3d(3.2641, 47.3987, 16.2995);
			gl.glVertex3d(14.5341, 23.6797, -1.02739);
			
			set_color(gl, (int)Channel_order[2][11]);
			gl.glVertex3d(3.2641, 47.3987, 16.2995);
			gl.glVertex3d(-12.5403, 43.7706, 13.03069);
			
			set_color(gl, (int)Channel_order[2][12]);
			gl.glVertex3d(3.2641, 47.3987, 16.2995);
			gl.glVertex3d(-4.6641, 47.5696, 16.499599);
			
			set_color(gl, (int)Channel_order[2][13]);
			gl.glVertex3d(3.2641, 47.3987, 16.2995);
			gl.glVertex3d(-11.5, 39.3633, 18.46349999);
			
			set_color(gl, (int)Channel_order[2][13]);
			gl.glVertex3d(3.2641, 47.3987, 16.2995);
			gl.glVertex3d(-4.6084, 43.0455, 20.87129999);
		}
		//////////////////////////////////////////
		if(active.channel4 == true){
			set_color(gl, (int)Channel_order[3][0]);
			gl.glVertex3d(10.5403, 43.7706, 13.0306);
			gl.glVertex3d(3.6085, 42.8455, 20.8714);
	
			set_color(gl, (int)Channel_order[3][1]);
			gl.glVertex3d(10.5403, 43.7706, 13.0306);
			gl.glVertex3d(10, 39.3633, 18.4634);
			
			set_color(gl, (int)Channel_order[3][2]);
			gl.glVertex3d(10.5403, 43.7706, 13.0306);
			gl.glVertex3d(3.2641, 47.3987, 16.2995);
			
			set_color(gl, (int)Channel_order[3][3]);
			gl.glVertex3d(10.5403, 43.7706, 13.0306);
			gl.glVertex3d(15.6341, 30.4779, 4.1489);
			
			set_color(gl, (int)Channel_order[3][4]);
			gl.glVertex3d(10.5403, 43.7706, 13.0306);
			gl.glVertex3d(13.2435, 41.3030, -1.0178);
			
			set_color(gl, (int)Channel_order[3][5]);
			gl.glVertex3d(10.5403, 43.7706, 13.0306);
			gl.glVertex3d(13.5617, 33.4290, -6.0931);
			
			set_color(gl, (int)Channel_order[3][6]);
			gl.glVertex3d(10.5403, 43.7706, 13.0306);
			gl.glVertex3d(-15.5617, 33.4290, -6.0931);
			
			set_color(gl, (int)Channel_order[3][7]);
			gl.glVertex3d(10.5403, 43.7706, 13.0306);
			gl.glVertex3d(-15.2435, 41.3030, -1.0178);
			
			set_color(gl, (int)Channel_order[3][8]);
			gl.glVertex3d(10.5403, 43.7706, 13.0306);
			gl.glVertex3d(-17.5341, 30.4779, 4.14899);
			
			set_color(gl, (int)Channel_order[3][9]);
			gl.glVertex3d(10.5403, 43.7706, 13.0306);
			gl.glVertex3d(-16.5341, 23.6797, -1.02739);
			
			set_color(gl, (int)Channel_order[3][10]);
			gl.glVertex3d(10.5403, 43.7706, 13.0306);
			gl.glVertex3d(14.5341, 23.6797, -1.02739);
			
			set_color(gl, (int)Channel_order[3][11]);
			gl.glVertex3d(10.5403, 43.7706, 13.0306);
			gl.glVertex3d(-12.5403, 43.7706, 13.03069);
			
			set_color(gl, (int)Channel_order[3][12]);
			gl.glVertex3d(10.5403, 43.7706, 13.0306);
			gl.glVertex3d(-4.6641, 47.5696, 16.499599);
			
			set_color(gl, (int)Channel_order[3][13]);
			gl.glVertex3d(10.5403, 43.7706, 13.0306);
			gl.glVertex3d(-11.5, 39.3633, 18.46349999);
			
			set_color(gl, (int)Channel_order[3][13]);
			gl.glVertex3d(10.5403, 43.7706, 13.0306);
			gl.glVertex3d(-4.6084, 43.0455, 20.87129999);
		}
		////////////////////////////////////////////
		if(active.channel5 == true){
			set_color(gl, (int)Channel_order[4][0]);
			gl.glVertex3d(15.6341, 30.4779, 4.1489);
			gl.glVertex3d(3.6085, 42.8455, 20.8714);

			set_color(gl, (int)Channel_order[4][1]);
			gl.glVertex3d(15.6341, 30.4779, 4.1489);
			gl.glVertex3d(10, 39.3633, 18.4634);
			
			set_color(gl, (int)Channel_order[4][2]);
			gl.glVertex3d(15.6341, 30.4779, 4.1489);
			gl.glVertex3d(3.2641, 47.3987, 16.2995);
			
			set_color(gl, (int)Channel_order[4][3]);
			gl.glVertex3d(15.6341, 30.4779, 4.1489);
			gl.glVertex3d(10.5403, 43.7706, 13.0306);
			
			set_color(gl, (int)Channel_order[4][4]);
			gl.glVertex3d(15.6341, 30.4779, 4.1489);
			gl.glVertex3d(13.2435, 41.3030, -1.0178);
			
			set_color(gl, (int)Channel_order[4][5]);
			gl.glVertex3d(15.6341, 30.4779, 4.1489);
			gl.glVertex3d(13.5617, 33.4290, -6.0931);
			
			set_color(gl, (int)Channel_order[4][6]);
			gl.glVertex3d(15.6341, 30.4779, 4.1489);
			gl.glVertex3d(-15.5617, 33.4290, -6.0931);
			
			set_color(gl, (int)Channel_order[4][7]);
			gl.glVertex3d(15.6341, 30.4779, 4.1489);
			gl.glVertex3d(-15.2435, 41.3030, -1.0178);
			
			set_color(gl, (int)Channel_order[4][8]);
			gl.glVertex3d(15.6341, 30.4779, 4.1489);
			gl.glVertex3d(-17.5341, 30.4779, 4.14899);
			
			set_color(gl, (int)Channel_order[4][9]);
			gl.glVertex3d(15.6341, 30.4779, 4.1489);
			gl.glVertex3d(-16.5341, 23.6797, -1.02739);
			
			set_color(gl, (int)Channel_order[4][10]);
			gl.glVertex3d(15.6341, 30.4779, 4.1489);
			gl.glVertex3d(14.5341, 23.6797, -1.02739);
			
			set_color(gl, (int)Channel_order[4][11]);
			gl.glVertex3d(15.6341, 30.4779, 4.1489);
			gl.glVertex3d(-12.5403, 43.7706, 13.03069);
			
			set_color(gl, (int)Channel_order[4][12]);
			gl.glVertex3d(15.6341, 30.4779, 4.1489);
			gl.glVertex3d(-4.6641, 47.5696, 16.499599);
			
			set_color(gl, (int)Channel_order[4][13]);
			gl.glVertex3d(15.6341, 30.4779, 4.1489);
			gl.glVertex3d(-11.5, 39.3633, 18.46349999);
			
			set_color(gl, (int)Channel_order[4][13]);
			gl.glVertex3d(15.6341, 30.4779, 4.1489);
			
			gl.glVertex3d(-4.6084, 43.0455, 20.87129999);
		}
		/////////////////////////////////////////////
		if(active.channel6 == true){
			set_color(gl, (int)Channel_order[5][0]);
			gl.glVertex3d(13.2435, 41.3030, -1.0178);
			gl.glVertex3d(3.6085, 42.8455, 20.8714);

			set_color(gl, (int)Channel_order[5][1]);
			gl.glVertex3d(13.2435, 41.3030, -1.0178);
			gl.glVertex3d(10, 39.3633, 18.4634);
			
			set_color(gl, (int)Channel_order[5][2]);
			gl.glVertex3d(13.2435, 41.3030, -1.0178);
			gl.glVertex3d(3.2641, 47.3987, 16.2995);
			
			set_color(gl, (int)Channel_order[5][3]);
			gl.glVertex3d(13.2435, 41.3030, -1.0178);
			gl.glVertex3d(10.5403, 43.7706, 13.0306);
		
			set_color(gl, (int)Channel_order[5][4]);
			gl.glVertex3d(13.2435, 41.3030, -1.0178);
			gl.glVertex3d(15.6341, 30.4779, 4.1489);
			
			set_color(gl, (int)Channel_order[5][5]);
			gl.glVertex3d(13.2435, 41.3030, -1.0178);
			gl.glVertex3d(13.5617, 33.4290, -6.0931);
			
			set_color(gl, (int)Channel_order[5][6]);
			gl.glVertex3d(13.2435, 41.3030, -1.0178);
			gl.glVertex3d(-15.5617, 33.4290, -6.0931);
			
			set_color(gl, (int)Channel_order[5][7]);
			gl.glVertex3d(13.2435, 41.3030, -1.0178);
			gl.glVertex3d(-15.2435, 41.3030, -1.0178);
			
			set_color(gl, (int)Channel_order[5][8]);
			gl.glVertex3d(13.2435, 41.3030, -1.0178);
			gl.glVertex3d(-17.5341, 30.4779, 4.14899);
			
			set_color(gl, (int)Channel_order[5][9]);
			gl.glVertex3d(13.2435, 41.3030, -1.0178);
			gl.glVertex3d(-16.5341, 23.6797, -1.02739);
			
			set_color(gl, (int)Channel_order[5][10]);
			gl.glVertex3d(13.2435, 41.3030, -1.0178);
			gl.glVertex3d(14.5341, 23.6797, -1.02739);
			
			set_color(gl, (int)Channel_order[5][11]);
			gl.glVertex3d(13.2435, 41.3030, -1.0178);
			gl.glVertex3d(-12.5403, 43.7706, 13.03069);
			
			set_color(gl, (int)Channel_order[5][12]);
			gl.glVertex3d(13.2435, 41.3030, -1.0178);
			gl.glVertex3d(-4.6641, 47.5696, 16.499599);
			
			set_color(gl, (int)Channel_order[5][13]);
			gl.glVertex3d(13.2435, 41.3030, -1.0178);
			gl.glVertex3d(-11.5, 39.3633, 18.46349999);
			
			set_color(gl, (int)Channel_order[5][13]);
			gl.glVertex3d(13.2435, 41.3030, -1.0178);
			gl.glVertex3d(-4.6084, 43.0455, 20.87129999);
		}
		///////////////////////////////////////////
		if(active.channel7 == true){
			set_color(gl, (int)Channel_order[6][0]);
			gl.glVertex3d(13.5617, 33.4290, -6.0931);
			gl.glVertex3d(3.6085, 42.8455, 20.8714);

			set_color(gl, (int)Channel_order[6][1]);
			gl.glVertex3d(13.5617, 33.4290, -6.0931);
			gl.glVertex3d(10, 39.3633, 18.4634);
			
			set_color(gl, (int)Channel_order[6][2]);
			gl.glVertex3d(13.5617, 33.4290, -6.0931);
			gl.glVertex3d(3.2641, 47.3987, 16.2995);
			
			set_color(gl, (int)Channel_order[6][3]);
			gl.glVertex3d(13.5617, 33.4290, -6.0931);
			gl.glVertex3d(10.5403, 43.7706, 13.0306);
		
			set_color(gl, (int)Channel_order[6][4]);
			gl.glVertex3d(13.5617, 33.4290, -6.0931);
			gl.glVertex3d(15.6341, 30.4779, 4.1489);
			
			set_color(gl, (int)Channel_order[6][5]);
			gl.glVertex3d(13.5617, 33.4290, -6.0931);
			gl.glVertex3d(13.2435, 41.3030, -1.0178);
				
			set_color(gl, (int)Channel_order[6][6]);
			gl.glVertex3d(13.5617, 33.4290, -6.0931);
			gl.glVertex3d(-15.5617, 33.4290, -6.0931);
			
			set_color(gl, (int)Channel_order[6][7]);
			gl.glVertex3d(13.5617, 33.4290, -6.0931);
			gl.glVertex3d(-15.2435, 41.3030, -1.0178);
			
			set_color(gl, (int)Channel_order[6][8]);
			gl.glVertex3d(13.5617, 33.4290, -6.0931);
			gl.glVertex3d(-17.5341, 30.4779, 4.14899);
			
			set_color(gl, (int)Channel_order[6][9]);
			gl.glVertex3d(13.5617, 33.4290, -6.0931);
			gl.glVertex3d(-16.5341, 23.6797, -1.02739);
			
			set_color(gl, (int)Channel_order[6][10]);
			gl.glVertex3d(13.5617, 33.4290, -6.0931);
			gl.glVertex3d(14.5341, 23.6797, -1.02739);
			
			set_color(gl, (int)Channel_order[6][11]);
			gl.glVertex3d(13.5617, 33.4290, -6.0931);
			gl.glVertex3d(-12.5403, 43.7706, 13.03069);
			
			set_color(gl, (int)Channel_order[6][12]);
			gl.glVertex3d(13.5617, 33.4290, -6.0931);
			gl.glVertex3d(-4.6641, 47.5696, 16.499599);
			
			set_color(gl, (int)Channel_order[6][13]);
			gl.glVertex3d(13.5617, 33.4290, -6.0931);
			gl.glVertex3d(-11.5, 39.3633, 18.46349999);
			
			set_color(gl, (int)Channel_order[6][13]);
			gl.glVertex3d(13.5617, 33.4290, -6.0931);
			gl.glVertex3d(-4.6084, 43.0455, 20.87129999);
		}
		////////////////////////////////////////////
		if(active.channel8 == true){
			set_color(gl, (int)Channel_order[7][0]);
			gl.glVertex3d(-15.5617, 33.4290, -6.0931);
			gl.glVertex3d(3.6085, 42.8455, 20.8714);

			set_color(gl, (int)Channel_order[7][1]);
			gl.glVertex3d(-15.5617, 33.4290, -6.0931);
			gl.glVertex3d(10, 39.3633, 18.4634);
			
			set_color(gl, (int)Channel_order[7][2]);
			gl.glVertex3d(-15.5617, 33.4290, -6.0931);
			gl.glVertex3d(3.2641, 47.3987, 16.2995);
			
			set_color(gl, (int)Channel_order[7][3]);
			gl.glVertex3d(-15.5617, 33.4290, -6.0931);
			gl.glVertex3d(10.5403, 43.7706, 13.0306);
		
			set_color(gl, (int)Channel_order[7][4]);
			gl.glVertex3d(-15.5617, 33.4290, -6.0931);
			gl.glVertex3d(15.6341, 30.4779, 4.1489);
			
			set_color(gl, (int)Channel_order[7][5]);
			gl.glVertex3d(-15.5617, 33.4290, -6.0931);
			gl.glVertex3d(13.2435, 41.3030, -1.0178);
		
			set_color(gl, (int)Channel_order[7][6]);
			gl.glVertex3d(-15.5617, 33.4290, -6.0931);
			gl.glVertex3d(13.5617, 33.4290, -6.0931);
			
			set_color(gl, (int)Channel_order[7][7]);
			gl.glVertex3d(-15.5617, 33.4290, -6.0931);
			gl.glVertex3d(-15.2435, 41.3030, -1.0178);
			
			set_color(gl, (int)Channel_order[7][8]);
			gl.glVertex3d(-15.5617, 33.4290, -6.0931);
			gl.glVertex3d(-17.5341, 30.4779, 4.14899);
			
			set_color(gl, (int)Channel_order[7][9]);
			gl.glVertex3d(-15.5617, 33.4290, -6.0931);
			gl.glVertex3d(-16.5341, 23.6797, -1.02739);
			
			set_color(gl, (int)Channel_order[7][10]);
			gl.glVertex3d(-15.5617, 33.4290, -6.0931);
			gl.glVertex3d(14.5341, 23.6797, -1.02739);
			
			set_color(gl, (int)Channel_order[7][11]);
			gl.glVertex3d(-15.5617, 33.4290, -6.0931);
			gl.glVertex3d(-12.5403, 43.7706, 13.03069);
			
			set_color(gl, (int)Channel_order[7][12]);
			gl.glVertex3d(-15.5617, 33.4290, -6.0931);
			gl.glVertex3d(-4.6641, 47.5696, 16.499599);
			
			set_color(gl, (int)Channel_order[7][13]);
			gl.glVertex3d(-15.5617, 33.4290, -6.0931);
			gl.glVertex3d(-11.5, 39.3633, 18.46349999);
			
			set_color(gl, (int)Channel_order[7][13]);
			gl.glVertex3d(-15.5617, 33.4290, -6.0931);
			gl.glVertex3d(-4.6084, 43.0455, 20.87129999);
		}
		////////////////////////////////////////////
		if(active.channel9 == true){
			set_color(gl, (int)Channel_order[8][0]);
			gl.glVertex3d(-15.2435, 41.3030, -1.0178);
			gl.glVertex3d(3.6085, 42.8455, 20.8714);

			set_color(gl, (int)Channel_order[8][1]);
			gl.glVertex3d(-15.2435, 41.3030, -1.0178);
			gl.glVertex3d(10, 39.3633, 18.4634);
			
			set_color(gl, (int)Channel_order[8][2]);
			gl.glVertex3d(-15.2435, 41.3030, -1.0178);
			gl.glVertex3d(3.2641, 47.3987, 16.2995);
			
			set_color(gl, (int)Channel_order[8][3]);
			gl.glVertex3d(-15.2435, 41.3030, -1.0178);
			gl.glVertex3d(10.5403, 43.7706, 13.0306);
		
			set_color(gl, (int)Channel_order[8][4]);
			gl.glVertex3d(-15.2435, 41.3030, -1.0178);
			gl.glVertex3d(15.6341, 30.4779, 4.1489);
			
			set_color(gl, (int)Channel_order[8][5]);
			gl.glVertex3d(-15.2435, 41.3030, -1.0178);
			gl.glVertex3d(13.2435, 41.3030, -1.0178);
		
			set_color(gl, (int)Channel_order[8][6]);
			gl.glVertex3d(-15.2435, 41.3030, -1.0178);
			gl.glVertex3d(13.5617, 33.4290, -6.0931);
			
			set_color(gl, (int)Channel_order[8][7]);
			gl.glVertex3d(-15.2435, 41.3030, -1.0178);
			gl.glVertex3d(-15.5617, 33.4290, -6.0931);
			
			set_color(gl, (int)Channel_order[8][8]);
			gl.glVertex3d(-15.2435, 41.3030, -1.0178);
			gl.glVertex3d(-17.5341, 30.4779, 4.14899);
			
			set_color(gl, (int)Channel_order[8][9]);
			gl.glVertex3d(-15.2435, 41.3030, -1.0178);
			gl.glVertex3d(-16.5341, 23.6797, -1.02739);
			
			set_color(gl, (int)Channel_order[8][10]);
			gl.glVertex3d(-15.2435, 41.3030, -1.0178);
			gl.glVertex3d(14.5341, 23.6797, -1.02739);
			
			set_color(gl, (int)Channel_order[8][11]);
			gl.glVertex3d(-15.2435, 41.3030, -1.0178);
			gl.glVertex3d(-12.5403, 43.7706, 13.03069);
			
			set_color(gl, (int)Channel_order[8][12]);
			gl.glVertex3d(-15.2435, 41.3030, -1.0178);
			gl.glVertex3d(-4.6641, 47.5696, 16.499599);
			
			set_color(gl, (int)Channel_order[8][13]);
			gl.glVertex3d(-15.2435, 41.3030, -1.0178);
			gl.glVertex3d(-11.5, 39.3633, 18.46349999);
			
			set_color(gl, (int)Channel_order[8][13]);
			gl.glVertex3d(-15.2435, 41.3030, -1.0178);
			gl.glVertex3d(-4.6084, 43.0455, 20.87129999);
		}
		////////////////////////////////////////////
		if(active.channel10 == true){
			set_color(gl, (int)Channel_order[9][0]);
			gl.glVertex3d(-17.5341, 30.4779, 4.14899);
			gl.glVertex3d(3.6085, 42.8455, 20.8714);

			set_color(gl, (int)Channel_order[9][1]);
			gl.glVertex3d(-17.5341, 30.4779, 4.14899);
			gl.glVertex3d(10, 39.3633, 18.4634);
			
			set_color(gl, (int)Channel_order[9][2]);
			gl.glVertex3d(-17.5341, 30.4779, 4.14899);
			gl.glVertex3d(3.2641, 47.3987, 16.2995);
			
			set_color(gl, (int)Channel_order[9][3]);
			gl.glVertex3d(-17.5341, 30.4779, 4.14899);
			gl.glVertex3d(10.5403, 43.7706, 13.0306);
		
			set_color(gl, (int)Channel_order[9][4]);
			gl.glVertex3d(-17.5341, 30.4779, 4.14899);
			gl.glVertex3d(15.6341, 30.4779, 4.1489);
			
			set_color(gl, (int)Channel_order[9][5]);
			gl.glVertex3d(-17.5341, 30.4779, 4.14899);
			gl.glVertex3d(13.2435, 41.3030, -1.0178);
		
			set_color(gl, (int)Channel_order[9][6]);
			gl.glVertex3d(-17.5341, 30.4779, 4.14899);
			gl.glVertex3d(13.5617, 33.4290, -6.0931);
			
			set_color(gl, (int)Channel_order[9][7]);
			gl.glVertex3d(-17.5341, 30.4779, 4.14899);
			gl.glVertex3d(-15.5617, 33.4290, -6.0931);
			
			set_color(gl, (int)Channel_order[9][8]);
			gl.glVertex3d(-17.5341, 30.4779, 4.14899);
			gl.glVertex3d(-15.2435, 41.3030, -1.0178);
			
			set_color(gl, (int)Channel_order[9][9]);
			gl.glVertex3d(-17.5341, 30.4779, 4.14899);
			gl.glVertex3d(-16.5341, 23.6797, -1.02739);
			
			set_color(gl, (int)Channel_order[9][10]);
			gl.glVertex3d(-17.5341, 30.4779, 4.14899);
			gl.glVertex3d(14.5341, 23.6797, -1.02739);
			
			set_color(gl, (int)Channel_order[9][11]);
			gl.glVertex3d(-17.5341, 30.4779, 4.14899);
			gl.glVertex3d(-12.5403, 43.7706, 13.03069);
			
			set_color(gl, (int)Channel_order[9][12]);
			gl.glVertex3d(-17.5341, 30.4779, 4.14899);
			gl.glVertex3d(-4.6641, 47.5696, 16.499599);
			
			set_color(gl, (int)Channel_order[9][13]);
			gl.glVertex3d(-17.5341, 30.4779, 4.14899);
			gl.glVertex3d(-11.5, 39.3633, 18.46349999);
			
			set_color(gl, (int)Channel_order[9][13]);
			gl.glVertex3d(-17.5341, 30.4779, 4.14899);
			gl.glVertex3d(-4.6084, 43.0455, 20.87129999);
		}
		/////////////////////////////////////////////
		if(active.stand1 == true){
			gl.glColor3f(1, 1, 1);
			gl.glVertex3d(-16.5341, 23.6797, -1.02739);
			gl.glVertex3d(3.6085, 42.8455, 20.8714);

			gl.glVertex3d(-16.5341, 23.6797, -1.02739);
			gl.glVertex3d(10, 39.3633, 18.4634);
			
			gl.glVertex3d(-16.5341, 23.6797, -1.02739);
			gl.glVertex3d(3.2641, 47.3987, 16.2995);
			
			gl.glVertex3d(-16.5341, 23.6797, -1.02739);
			gl.glVertex3d(10.5403, 43.7706, 13.0306);
		
			gl.glVertex3d(-16.5341, 23.6797, -1.02739);
			gl.glVertex3d(15.6341, 30.4779, 4.1489);
			
			gl.glVertex3d(-16.5341, 23.6797, -1.02739);
			gl.glVertex3d(13.2435, 41.3030, -1.0178);
		
			gl.glVertex3d(-16.5341, 23.6797, -1.02739);
			gl.glVertex3d(13.5617, 33.4290, -6.0931);
			
			gl.glVertex3d(-16.5341, 23.6797, -1.02739);
			gl.glVertex3d(-15.5617, 33.4290, -6.0931);
			
			gl.glVertex3d(-16.5341, 23.6797, -1.02739);
			gl.glVertex3d(-15.2435, 41.3030, -1.0178);
			
			gl.glVertex3d(-16.5341, 23.6797, -1.02739);
			gl.glVertex3d(-17.5341, 30.4779, 4.14899);
			
			gl.glVertex3d(-16.5341, 23.6797, -1.02739);
			gl.glVertex3d(14.5341, 23.6797, -1.02739);
			
			gl.glVertex3d(-16.5341, 23.6797, -1.02739);
			gl.glVertex3d(-12.5403, 43.7706, 13.03069);
			
			gl.glVertex3d(-16.5341, 23.6797, -1.02739);
			gl.glVertex3d(-4.6641, 47.5696, 16.499599);
			
			gl.glVertex3d(-16.5341, 23.6797, -1.02739);
			gl.glVertex3d(-11.5, 39.3633, 18.46349999);
			
			gl.glVertex3d(-16.5341, 23.6797, -1.02739);
			gl.glVertex3d(-4.6084, 43.0455, 20.87129999);
		}
		//////////////////////////////////////////////
		if(active.stand2 == true){
			gl.glColor3f(1, 1, 1);
			gl.glVertex3d(14.5341, 23.6797, -1.02739);
			gl.glVertex3d(3.6085, 42.8455, 20.8714);

			gl.glVertex3d(14.5341, 23.6797, -1.02739);
			gl.glVertex3d(10, 39.3633, 18.4634);
			
			gl.glVertex3d(14.5341, 23.6797, -1.02739);
			gl.glVertex3d(3.2641, 47.3987, 16.2995);
			
			gl.glVertex3d(14.5341, 23.6797, -1.02739);
			gl.glVertex3d(10.5403, 43.7706, 13.0306);
		
			gl.glVertex3d(14.5341, 23.6797, -1.02739);
			gl.glVertex3d(15.6341, 30.4779, 4.1489);
			
			gl.glVertex3d(14.5341, 23.6797, -1.02739);
			gl.glVertex3d(13.2435, 41.3030, -1.0178);
		
			gl.glVertex3d(14.5341, 23.6797, -1.02739);
			gl.glVertex3d(13.5617, 33.4290, -6.0931);
			
			gl.glVertex3d(14.5341, 23.6797, -1.02739);
			gl.glVertex3d(-15.5617, 33.4290, -6.0931);
			
			gl.glVertex3d(14.5341, 23.6797, -1.02739);
			gl.glVertex3d(-15.2435, 41.3030, -1.0178);
			
			gl.glVertex3d(14.5341, 23.6797, -1.02739);
			gl.glVertex3d(-17.5341, 30.4779, 4.14899);
			
			gl.glVertex3d(14.5341, 23.6797, -1.02739);
			gl.glVertex3d(-16.5341, 23.6797, -1.02739);
			
			gl.glVertex3d(14.5341, 23.6797, -1.02739);
			gl.glVertex3d(-12.5403, 43.7706, 13.03069);
			
			gl.glVertex3d(14.5341, 23.6797, -1.02739);
			gl.glVertex3d(-4.6641, 47.5696, 16.499599);
			
			gl.glVertex3d(14.5341, 23.6797, -1.02739);
			gl.glVertex3d(-11.5, 39.3633, 18.46349999);
			
			gl.glVertex3d(14.5341, 23.6797, -1.02739);
			gl.glVertex3d(-4.6084, 43.0455, 20.87129999);
		}
		//////////////////////////////////////////////
		if(active.channel11 == true){
			set_color(gl, (int)Channel_order[10][0]);
			gl.glVertex3d(-12.5403, 43.7706, 13.03069);
			gl.glVertex3d(3.6085, 42.8455, 20.8714);

			set_color(gl, (int)Channel_order[10][1]);
			gl.glVertex3d(-12.5403, 43.7706, 13.03069);
			gl.glVertex3d(10, 39.3633, 18.4634);
			
			set_color(gl, (int)Channel_order[10][2]);
			gl.glVertex3d(-12.5403, 43.7706, 13.03069);
			gl.glVertex3d(3.2641, 47.3987, 16.2995);
			
			set_color(gl, (int)Channel_order[10][3]);
			gl.glVertex3d(-12.5403, 43.7706, 13.03069);
			gl.glVertex3d(10.5403, 43.7706, 13.0306);
		
			set_color(gl, (int)Channel_order[10][4]);
			gl.glVertex3d(-12.5403, 43.7706, 13.03069);
			gl.glVertex3d(15.6341, 30.4779, 4.1489);
			
			set_color(gl, (int)Channel_order[10][5]);
			gl.glVertex3d(-12.5403, 43.7706, 13.03069);
			gl.glVertex3d(13.2435, 41.3030, -1.0178);
		
			set_color(gl, (int)Channel_order[10][6]);
			gl.glVertex3d(-12.5403, 43.7706, 13.03069);
			gl.glVertex3d(13.5617, 33.4290, -6.0931);
			
			set_color(gl, (int)Channel_order[10][7]);
			gl.glVertex3d(-12.5403, 43.7706, 13.03069);
			gl.glVertex3d(-15.5617, 33.4290, -6.0931);
			
			set_color(gl, (int)Channel_order[10][8]);
			gl.glVertex3d(-12.5403, 43.7706, 13.03069);
			gl.glVertex3d(-15.2435, 41.3030, -1.0178);
			
			set_color(gl, (int)Channel_order[10][9]);
			gl.glVertex3d(-12.5403, 43.7706, 13.03069);
			gl.glVertex3d(-17.5341, 30.4779, 4.14899);
			
			set_color(gl, (int)Channel_order[10][10]);
			gl.glVertex3d(-12.5403, 43.7706, 13.03069);
			gl.glVertex3d(-16.5341, 23.6797, -1.02739);
			
			set_color(gl, (int)Channel_order[10][11]);
			gl.glVertex3d(-12.5403, 43.7706, 13.03069);
			gl.glVertex3d(14.5341, 23.6797, -1.02739);
			
			set_color(gl, (int)Channel_order[10][12]);
			gl.glVertex3d(-12.5403, 43.7706, 13.03069);
			gl.glVertex3d(-4.6641, 47.5696, 16.499599);
			
			set_color(gl, (int)Channel_order[10][13]);
			gl.glVertex3d(-12.5403, 43.7706, 13.03069);
			gl.glVertex3d(-11.5, 39.3633, 18.46349999);
			
			set_color(gl, (int)Channel_order[10][13]);
			gl.glVertex3d(-12.5403, 43.7706, 13.03069);
			gl.glVertex3d(-4.6084, 43.0455, 20.87129999);
		}
		//////////////////////////////////////////////
		if(active.channel12 == true){
			set_color(gl, (int)Channel_order[11][0]);
			gl.glVertex3d(-4.6641, 47.5696, 16.499599);
			gl.glVertex3d(3.6085, 42.8455, 20.8714);

			set_color(gl, (int)Channel_order[11][1]);
			gl.glVertex3d(-4.6641, 47.5696, 16.499599);
			gl.glVertex3d(10, 39.3633, 18.4634);
			
			set_color(gl, (int)Channel_order[11][2]);
			gl.glVertex3d(-4.6641, 47.5696, 16.499599);
			gl.glVertex3d(3.2641, 47.3987, 16.2995);
			
			set_color(gl, (int)Channel_order[11][3]);
			gl.glVertex3d(-4.6641, 47.5696, 16.499599);
			gl.glVertex3d(10.5403, 43.7706, 13.0306);
		
			set_color(gl, (int)Channel_order[11][4]);
			gl.glVertex3d(-4.6641, 47.5696, 16.499599);
			gl.glVertex3d(15.6341, 30.4779, 4.1489);
			
			set_color(gl, (int)Channel_order[11][5]);
			gl.glVertex3d(-4.6641, 47.5696, 16.499599);
			gl.glVertex3d(13.2435, 41.3030, -1.0178);
		
			set_color(gl, (int)Channel_order[11][6]);
			gl.glVertex3d(-4.6641, 47.5696, 16.499599);
			gl.glVertex3d(13.5617, 33.4290, -6.0931);
			
			set_color(gl, (int)Channel_order[11][7]);
			gl.glVertex3d(-4.6641, 47.5696, 16.499599);
			gl.glVertex3d(-15.5617, 33.4290, -6.0931);
			
			set_color(gl, (int)Channel_order[11][8]);
			gl.glVertex3d(-4.6641, 47.5696, 16.499599);
			gl.glVertex3d(-15.2435, 41.3030, -1.0178);
			
			set_color(gl, (int)Channel_order[11][9]);
			gl.glVertex3d(-4.6641, 47.5696, 16.499599);
			gl.glVertex3d(-17.5341, 30.4779, 4.14899);
			
			set_color(gl, (int)Channel_order[11][10]);
			gl.glVertex3d(-4.6641, 47.5696, 16.499599);
			gl.glVertex3d(-16.5341, 23.6797, -1.02739);
			
			set_color(gl, (int)Channel_order[11][11]);
			gl.glVertex3d(-4.6641, 47.5696, 16.499599);
			gl.glVertex3d(14.5341, 23.6797, -1.02739);
			
			set_color(gl, (int)Channel_order[11][12]);
			gl.glVertex3d(-4.6641, 47.5696, 16.499599);
			gl.glVertex3d(-12.5403, 43.7706, 13.03069);
			
			set_color(gl, (int)Channel_order[11][13]);
			gl.glVertex3d(-4.6641, 47.5696, 16.499599);
			gl.glVertex3d(-11.5, 39.3633, 18.46349999);
			
			set_color(gl, (int)Channel_order[11][13]);
			gl.glVertex3d(-4.6641, 47.5696, 16.499599);
			gl.glVertex3d(-4.6084, 43.0455, 20.87129999);
		}
		//////////////////////////////////////////////
		if(active.channel13 == true){
			set_color(gl, (int)Channel_order[12][0]);
			gl.glVertex3d(-11.5, 39.3633, 18.46349999);
			gl.glVertex3d(3.6085, 42.8455, 20.8714);
	
			set_color(gl, (int)Channel_order[12][1]);
			gl.glVertex3d(-11.5, 39.3633, 18.46349999);
			gl.glVertex3d(10, 39.3633, 18.4634);
			
			set_color(gl, (int)Channel_order[12][2]);
			gl.glVertex3d(-11.5, 39.3633, 18.46349999);
			gl.glVertex3d(3.2641, 47.3987, 16.2995);
			
			set_color(gl, (int)Channel_order[12][3]);
			gl.glVertex3d(-11.5, 39.3633, 18.46349999);
			gl.glVertex3d(10.5403, 43.7706, 13.0306);
		
			set_color(gl, (int)Channel_order[12][4]);
			gl.glVertex3d(-11.5, 39.3633, 18.46349999);
			gl.glVertex3d(15.6341, 30.4779, 4.1489);
			
			set_color(gl, (int)Channel_order[12][5]);
			gl.glVertex3d(-11.5, 39.3633, 18.46349999);
			gl.glVertex3d(13.2435, 41.3030, -1.0178);
		
			set_color(gl, (int)Channel_order[12][6]);
			gl.glVertex3d(-11.5, 39.3633, 18.46349999);
			gl.glVertex3d(13.5617, 33.4290, -6.0931);
			
			set_color(gl, (int)Channel_order[12][7]);
			gl.glVertex3d(-11.5, 39.3633, 18.46349999);
			gl.glVertex3d(-15.5617, 33.4290, -6.0931);
			
			set_color(gl, (int)Channel_order[12][8]);
			gl.glVertex3d(-11.5, 39.3633, 18.46349999);
			gl.glVertex3d(-15.2435, 41.3030, -1.0178);
			
			set_color(gl, (int)Channel_order[12][9]);
			gl.glVertex3d(-11.5, 39.3633, 18.46349999);
			gl.glVertex3d(-17.5341, 30.4779, 4.14899);
			
			set_color(gl, (int)Channel_order[12][10]);
			gl.glVertex3d(-11.5, 39.3633, 18.46349999);
			gl.glVertex3d(-16.5341, 23.6797, -1.02739);
			
			set_color(gl, (int)Channel_order[12][11]);
			gl.glVertex3d(-11.5, 39.3633, 18.46349999);
			gl.glVertex3d(14.5341, 23.6797, -1.02739);
			
			set_color(gl, (int)Channel_order[12][12]);
			gl.glVertex3d(-11.5, 39.3633, 18.46349999);
			gl.glVertex3d(-12.5403, 43.7706, 13.03069);
			
			set_color(gl, (int)Channel_order[12][13]);
			gl.glVertex3d(-11.5, 39.3633, 18.46349999);
			gl.glVertex3d(-4.6641, 47.5696, 16.499599);
			
			set_color(gl, (int)Channel_order[12][13]);
			gl.glVertex3d(-11.5, 39.3633, 18.46349999);
			gl.glVertex3d(-4.6084, 43.0455, 20.87129999);
		}
		////////////////////////////////////////////////
		if(active.channel14 == true){
			set_color(gl, (int)Channel_order[13][0]);
			gl.glVertex3d(-4.6084, 43.0455, 20.87129999);
			gl.glVertex3d(3.6085, 42.8455, 20.8714);

			set_color(gl, (int)Channel_order[13][1]);
			gl.glVertex3d(-4.6084, 43.0455, 20.87129999);
			gl.glVertex3d(10, 39.3633, 18.4634);
			
			set_color(gl, (int)Channel_order[13][2]);
			gl.glVertex3d(-4.6084, 43.0455, 20.87129999);
			gl.glVertex3d(3.2641, 47.3987, 16.2995);
			
			set_color(gl, (int)Channel_order[13][3]);
			gl.glVertex3d(-4.6084, 43.0455, 20.87129999);
			gl.glVertex3d(10.5403, 43.7706, 13.0306);
		
			set_color(gl, (int)Channel_order[13][4]);
			gl.glVertex3d(-4.6084, 43.0455, 20.87129999);
			gl.glVertex3d(15.6341, 30.4779, 4.1489);
			
			set_color(gl, (int)Channel_order[13][5]);
			gl.glVertex3d(-4.6084, 43.0455, 20.87129999);
			gl.glVertex3d(13.2435, 41.3030, -1.0178);
		
			set_color(gl, (int)Channel_order[13][6]);
			gl.glVertex3d(-4.6084, 43.0455, 20.87129999);
			gl.glVertex3d(13.5617, 33.4290, -6.0931);
			
			set_color(gl, (int)Channel_order[13][7]);
			gl.glVertex3d(-4.6084, 43.0455, 20.87129999);
			gl.glVertex3d(-15.5617, 33.4290, -6.0931);
			
			set_color(gl, (int)Channel_order[13][8]);
			gl.glVertex3d(-4.6084, 43.0455, 20.87129999);
			gl.glVertex3d(-15.2435, 41.3030, -1.0178);
			
			set_color(gl, (int)Channel_order[13][9]);
			gl.glVertex3d(-4.6084, 43.0455, 20.87129999);
			gl.glVertex3d(-17.5341, 30.4779, 4.14899);
			
			set_color(gl, (int)Channel_order[13][10]);
			gl.glVertex3d(-4.6084, 43.0455, 20.87129999);
			gl.glVertex3d(-16.5341, 23.6797, -1.02739);
			
			set_color(gl, (int)Channel_order[13][11]);
			gl.glVertex3d(-4.6084, 43.0455, 20.87129999);
			gl.glVertex3d(14.5341, 23.6797, -1.02739);
			
			set_color(gl, (int)Channel_order[13][12]);
			gl.glVertex3d(-4.6084, 43.0455, 20.87129999);
			gl.glVertex3d(-12.5403, 43.7706, 13.03069);
			
			set_color(gl, (int)Channel_order[13][13]);
			gl.glVertex3d(-4.6084, 43.0455, 20.87129999);
			gl.glVertex3d(-4.6641, 47.5696, 16.499599);
			
			set_color(gl, (int)Channel_order[13][13]);
			gl.glVertex3d(-4.6084, 43.0455, 20.87129999);
			gl.glVertex3d(-11.5, 39.3633, 18.46349999);
		}
	}

	private void setCamera(GL gl, GLU glu, float distance){
		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		
		float widthHeightRatio = (float)getWidth() / (float)getHeight();
		glu.gluPerspective(45, widthHeightRatio, 1, 1000);
		glu.gluLookAt(
				0, 50, 90, 
				0, 20, 0, 
				0, 1, 0);
		
		gl.glMatrixMode(GL.GL_MODELVIEW);
		gl.glLoadIdentity();
	}
	
	@Override
	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Changing display is not supported");
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		drawable.addMouseListener(this);
		drawable.addMouseMotionListener(this);
		drawable.addMouseWheelListener(this);
		
		// TODO Auto-generated method stub
		drawable.setGL(new DebugGL(drawable.getGL()));
	
		
		final GL gl = drawable.getGL();
		
		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glDepthFunc(GL.GL_LEQUAL);
		
		gl.glShadeModel(GL.GL_SMOOTH);
		
		gl.glClearColor(0f, 0f, 0f, 0f);
		
		gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
		
		
		glu = new GLU();
		
		animator = new FPSAnimator(this, fps);
		animator.start();
	
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		final GL gl = drawable.getGL();
		gl.glViewport(0, 0, width, height);
	}
	
	class Data{
		double x;
		double y;
		double z;
	}
	
	private void setData(String filename){
		try{
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			
			String line = reader.readLine();

			while(line != null){
				StringTokenizer tok = new StringTokenizer(line, " ");
				if(tok.hasMoreTokens()){
					String ident = tok.nextToken();
					if(ident.equals("v")){
						Data data = new Data();
						data.x = Double.valueOf(tok.nextToken()) - 25;
						data.y = Double.valueOf(tok.nextToken());
						data.z = Double.valueOf(tok.nextToken());
						vertex.add(data);
					}
				}
				line = reader.readLine();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if (isDragging) {
			moveX = e.getX() - oldX;
		}
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		oldX = e.getX();
		if (isDragging == false) {
			isDragging = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (isDragging == true) {
			isDragging = false;
		}
		moveX = 0;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
	}
}
