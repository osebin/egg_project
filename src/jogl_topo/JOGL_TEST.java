package jogl_topo;

import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.media.opengl.*;
import javax.media.opengl.glu.*;
import javax.swing.*;

import jogl_topo.JOGL_Topo.*;

import com.sun.opengl.util.*;

public class JOGL_TEST extends GLCanvas implements GLEventListener, 
												MouseMotionListener,
												MouseListener{
	
	private static final long serialVersionUID = 1L;
	private GLU glu; // GL unit 
	private int fps = 60;
	private FPSAnimator animator;
	
	private int moveX = 0;
	private int oldX = 0;
	private boolean isDragging = false;
	
	public static ArrayList<Data> vertex = new ArrayList<Data>();
	float time = 0;
	
	public static void main(String args[]){
		GLCapabilities cap = JOGL_TEST.createGLCapabilities3();
		GLCanvas canvas = new JOGL_TEST(cap, 800, 600);
		JFrame frame = new JFrame();
		frame.setBounds(200, 200, 800, 600);
		frame.setVisible(true);
		frame.getContentPane().add(canvas);
	}
	public JOGL_TEST(GLCapabilities capabilities, int width, int height){
		addGLEventListener(this);		
		setData("sample.obj");
	}
	
	public static GLCapabilities createGLCapabilities3(){
		GLCapabilities capabilities = new GLCapabilities();
		capabilities.setRedBits(8);
		capabilities.setBlueBits(8);
		capabilities.setGreenBits(8);
		capabilities.setAlphaBits(8);
	
	return capabilities;
	}
	
	@Override
	public void display(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		if(isDragging == false){
		moveX ++;
	}
	
	final GL gl = drawable.getGL();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		setCamera(gl, glu, 100);
		
		gl.glRotatef(moveX , 0, 100, 1);
		gl.glColor3f(1, 1, 1);
		
		gl.glBegin(GL.GL_POINTS);
		Iterator<Data> elements = vertex.iterator();
		while(elements.hasNext()){
		Data data = elements.next();
		gl.glVertex3d(data.x, data.y, data.z);
		}
		gl.glColor3f(255, 0, 0);
		gl.glVertex3d(0, 25, 0);
	
		gl.glEnd();
	}
	
	private void setCamera(GL gl, GLU glu, float distance){
		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		
		float widthHeightRatio = (float)getWidth() / (float)getHeight();
		glu.gluPerspective(45, widthHeightRatio, 1, 1000);
		glu.gluLookAt(
		0, 25, 100, 
		0, 25, 0, 
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
		// TODO Auto-generated method stub
		drawable.setGL(new DebugGL(drawable.getGL()));
		drawable.addMouseListener(this);
		drawable.addMouseMotionListener(this);
		
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
						if(data.y > 20){
							vertex.add(data);
						}else{
							
						}
						
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
		if(isDragging){
			moveX = e.getX() - oldX;
		}	
	//oldX = e.getX();
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
	// TODO Auto-generated method stub
		System.out.println("Moved -> X : " + e.getX() + " / " + "Y : " + e.getY());
	}
	@Override
	public void mouseClicked(MouseEvent e) {
	// TODO Auto-generated method stub
		System.out.println("Clicked -> X : " + e.getX() + " / " + "Y : " + e.getY());
	}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {
	// TODO Auto-generated method stub
		oldX = e.getX();
		if(isDragging == false){
			isDragging = true;
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub
		if(isDragging == true){
			isDragging = false;
		}
		moveX = 0;
	}	
}
