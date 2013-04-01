package jogl_topo;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;
import javax.swing.JFrame;
import javax.media.opengl.*;





import com.sun.opengl.util.FPSAnimator;

public class JOGL_Topo_Back extends GLCanvas implements GLEventListener,
		MouseListener, MouseMotionListener, GLUquadric {

	private static final long serialVersionUID = 1L;
	private GLU glu; // GL units
	private int fps = 60;
	private FPSAnimator animator;

	private float moveX = 0;
	private float color = 100;
	private float color1 = 200;
	private boolean color_flag = false;
	private int oldX = 0;
	private int oldY = 0;
	private boolean isDragging = false;
	private GLUquadric qua;

	private double[] y = { 42.0455, 36.3633, 44.2696, 39.7706, 30.4779, 23.6797, 
			37.4290, 43.3030 };
	

	private double[] z = {17.1713, 12.4635, 13.9996, 8.0307, 4.1488, 0.0274, 
			-4.0930, -8.0177 };

	
	private double max = 0;

	ArrayList<Data> vertex = new ArrayList<Data>();
	ArrayList<NormalVector> vector = new ArrayList<NormalVector>();
	ArrayList<MeshFace> faces = new ArrayList<MeshFace>();
	float time = 0;

	
	public JOGL_Topo_Back(GLCapabilities capabilities, int width, int height) {
		addGLEventListener(this);

		setData("sample1.obj");
	}

	public static GLCapabilities createGLCapabilities() {

		GLCapabilities capabilities = new GLCapabilities();
		capabilities.setRedBits(8);
		capabilities.setBlueBits(8);
		capabilities.setGreenBits(8);
		capabilities.setAlphaBits(8);

		return capabilities;

	}

	int ball = 1;
	int flag, flag1;
	boolean flag2 = true;

	@Override
	public void display(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		drawable.addMouseListener(this);
		drawable.addMouseMotionListener(this);
		if (isDragging == false) {
			moveX++;
		}
		time++;
		
		final GL gl = drawable.getGL();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

		setCamera(gl, glu, 100);
		
		//////////
		

		
		/////////

		gl.glBegin(gl.GL_LINE_LOOP);//red X
		gl.glColor3f(1.0f, 0.0f, 0.0f);
		gl.glVertex3f(70.0f, 0.0f, 0.0f);
		gl.glVertex3f(-70.0f, 0.0f, 0.0f);
		gl.glEnd();
		gl.glBegin(gl.GL_LINE_LOOP);//green Y
		gl.glColor3f(0.0f, 1.0f, 0.0f);
		gl.glVertex3f(0.0f, 70.0f, 0.0f);
		gl.glVertex3f(0.0f, -70.0f, 0.0f);
		gl.glEnd();
		gl.glBegin(gl.GL_LINE_LOOP);//blue Z
		gl.glColor3f(0.0f, 0.0f, 1.0f);
		gl.glVertex3f(0.0f, 0.0f, 70.0f);
		gl.glVertex3f(0.0f, 0.0f, -70.0f);
	
		gl.glEnd();
	
		
		gl.glBegin(GL.GL_TRIANGLES);
		//////색 입히기
		for (int c = 0; c < faces.size(); c++) {
			int v1 = faces.get(c).v1[0] - 1;
	
			int v3 = faces.get(c).v1[2] - 1;

			gl.glColor4f(0.8f, 0.6f, 0.5f, 0.5f);// 살색
			gl.glNormal3d(vector.get(v3).x, vector.get(v3).y, vector.get(v3).z);
			gl.glVertex3d(vertex.get(v1).x, vertex.get(v1).y, vertex.get(v1).z);

			v1 = faces.get(c).v2[0] - 1;
	
			v3 = faces.get(c).v2[2] - 1;

			gl.glNormal3d(vector.get(v3).x, vector.get(v3).y, vector.get(v3).z);
			gl.glVertex3d(vertex.get(v1).x, vertex.get(v1).y, vertex.get(v1).z);

			v1 = faces.get(c).v3[0] - 1;
	
			v3 = faces.get(c).v3[2] - 1;

			gl.glNormal3d(vector.get(v3).x, vector.get(v3).y, vector.get(v3).z);
			gl.glVertex3d(vertex.get(v1).x, vertex.get(v1).y, vertex.get(v1).z);

		}
		gl.glEnd();
		
	}

	private void setCamera(GL gl,  GLU glu, float distance) {
		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();

		float widthHeightRatio = (float) getWidth() / (float) getHeight();
		glu.gluPerspective(45, widthHeightRatio, 1, 1000);
		glu.gluLookAt(100, 100, 100, 0, 20, 15, 0, 150, 0);

		gl.glRotatef(moveX, 0, 100, 1);
		
		gl.glMatrixMode(GL.GL_MODELVIEW);
		gl.glLoadIdentity();

		//////////////////////////
		float i = 0.5f;
		GLUquadric quadric=glu.gluNewQuadric();
		gl.glLoadIdentity();

			gl.glColor4f(0.0f, 0.0f, 0.0f, 0.1f);
////////////////////////////정면 좌측 아래
			gl.glPushMatrix();
			gl.glTranslated(-4.6084 ,43.0455, 20.8713);
			gl.glRotatef(50.0f,  1.0f, 0.0f, 0.2f);
			gl.glScalef(3.0f, 0.3f, 3.0f);
			glu.gluSphere(quadric, i, 100, 100);
			gl.glPopMatrix();
////////////////////////////정면 우측 아래			
			gl.glPushMatrix();
			gl.glTranslated(3.6084 ,42.8455, 20.8713);
			gl.glRotatef(50.0f, 1.0f, 0.0f, -0.2f);
			gl.glScalef(3.0f, 0.3f, 3.0f);
			glu.gluSphere(quadric, i, 100, 100);
			gl.glPopMatrix();
////////////////////////////정면 우측 두번째 아래		
			gl.glPushMatrix();
			gl.glTranslated(10 ,39.3633, 18.4635);
			gl.glRotatef(70.0f, 1.0f, 0.0f, -1.2f);
			gl.glScalef(3.0f, 0.3f, 3.0f);
			glu.gluSphere(quadric, i, 100, 100);
			gl.glPopMatrix();
//////////////////////////정면 좌측 두번째 아래		
			gl.glPushMatrix();
			gl.glTranslated(-11.5 ,39.3633, 18.4635);
			gl.glRotatef(70.0f, 1.0f, 0.0f, 1.2f);
			gl.glScalef(3.0f, 0.3f, 3.0f);
			glu.gluSphere(quadric, i, 100, 100);
			gl.glPopMatrix();
////////////////////////////////정면 좌측 위
			gl.glPushMatrix();
			gl.glTranslated(-4.6641, 47.5696, 16.4996);
			gl.glRotatef(50.0f, 1.0f, 0.0f, 0.2f);
			gl.glScalef(3.0f, 0.3f, 3.0f);
			glu.gluSphere(quadric, i, 100, 100);
			gl.glPopMatrix();		
///////////////////////////////정면 우측 위			
			gl.glPushMatrix();
			gl.glTranslated(3.2641, 47.3987,16.2996);
			gl.glRotatef(50.0f,1.0f, 0.0f, -0.2f);
			gl.glScalef(3.0f, 0.3f, 3.0f);
			glu.gluSphere(quadric, i, 100, 100);
			gl.glPopMatrix();					
/////////////////////////////정면 두번째 우측 위 			
			gl.glPushMatrix();
			gl.glTranslated(10.5403, 43.7706, 13.0307);
			gl.glRotatef(50.0f,1.0f, 0.0f, -1.5f);
			gl.glScalef(3.0f, 0.3f, 3.0f);
			glu.gluSphere(quadric, i, 100, 100);
			gl.glPopMatrix();		
/////////////////////////////정면 두번쨰 좌측 위		
			gl.glPushMatrix();
			gl.glTranslated(-12.5403, 43.7706, 13.0307);
			gl.glRotatef(50.0f, 1.0f, 0.0f, 1.5f);
			gl.glScalef(3.0f, 0.3f, 3.0f);
			glu.gluSphere(quadric, i, 100, 100);
			gl.glPopMatrix();					
////////////////////////////오른쪽 귀 바로 위			
			gl.glPushMatrix();
			gl.glTranslated(15.6341,  30.4779 , 4.1488);
			gl.glRotatef(90.0f, 0.0f, 0.0f, 2.0f);
			gl.glScalef(3.0f, 0.3f, 3.0f);
			glu.gluSphere(quadric, i, 100, 100);
			gl.glPopMatrix();	
///////////////////////////왼쪽 귀 바로 위		
			gl.glPushMatrix();
			gl.glTranslated(-17.5341,  30.4779 , 4.1488);
			gl.glRotatef(90.0f, 0.0f, 0.0f, 2.0f);
			gl.glScalef(3.0f, 0.3f, 3.0f);
			glu.gluSphere(quadric, i, 100, 100);
			gl.glPopMatrix();
			
//////////////////////////왼쪽 귀 바로 뒤
			gl.glPushMatrix();
			gl.glTranslated(-16.5341,  23.6797 , -1.0274);
			gl.glRotatef(105.0f, 0.0f, 0.0f, 1.5f);
			gl.glScalef(3.0f, 0.3f, 3.0f);
			glu.gluSphere(quadric, i, 100, 100);
			gl.glPopMatrix();
/////////////////////////오른쪽귀 바로 뒤 			
			gl.glPushMatrix();
			gl.glTranslated(14.5341,  23.6797 , -1.0274);
			gl.glRotatef(80.0f, 0.0f, 0.0f, 1.5f);
			gl.glScalef(3.0f, 0.3f, 3.0f);
			glu.gluSphere(quadric, i, 100, 100);
			gl.glPopMatrix();
/////////////////////////오른쪽귀 위에서 두번째 		
			gl.glPushMatrix();
			gl.glTranslated(13.5617,  33.4290 , -6.0930);
			gl.glRotatef(105.0f, 1.0f, -0.6f, 1.0f);
			gl.glScalef(3.0f, 0.3f, 3.0f);
			glu.gluSphere(quadric, i, 100, 100);
			gl.glPopMatrix();			
/////////////////////////왼쪽귀 위에서 두번째			
			gl.glPushMatrix();
			gl.glTranslated(-15.5617,  33.4290 , -6.0930);
			gl.glRotatef(105.0f, 0.5f, -1.2f, 2.5f);
			gl.glScalef(3.0f, 0.3f, 3.0f);
			glu.gluSphere(quadric, i, 100, 100);
			gl.glPopMatrix();	
/////////////////////////오른쪽 귀 맨 위			
			gl.glPushMatrix();
			gl.glTranslated(13.2435,  41.3030 , -1.0177);
			gl.glRotatef(120.0f, 3.0f, -1.5f, 3.0f);
			gl.glScalef(3.0f, 0.3f, 3.0f);
			glu.gluSphere(quadric, i, 100, 100);
			gl.glPopMatrix();			
/////////////////////////왼쪽 귀 맨 위			
			gl.glPushMatrix();
			gl.glTranslated(-15.2435,  41.3030 , -1.0177);
			gl.glRotatef(105.0f, 1.0f, -2.5f, 2.0f);
			gl.glScalef(3.0f, 0.3f, 3.0f);
			glu.gluSphere(quadric, i, 100, 100);
			gl.glPopMatrix();		

	
		
	}

	@Override
	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged,
			boolean deviceChanged) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException(
				"Changing display is not supported");
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		drawable.setGL(new DebugGL(drawable.getGL()));
		final GL gl = drawable.getGL();

		gl.glEnable(GL.GL_DEPTH_TEST);
		// gl.glDepthFunc(GL.GL_LEQUAL);
		// gl.glClearDepth(1.0f);

		//
		// gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		// gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
		// gl.glEnable(GL.GL_BLEND);
		// gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE);

		// /////////////////////////////////////////
		gl.glEnable(GL.GL_LIGHTING);
		
		float ambient[] = { 0.8f, 0.6f, 0.5f, 0.1f };
		gl.glLightModelfv(GL.GL_LIGHT_MODEL_AMBIENT, ambient, 0);
		//

		// ///////////////////////////광원1
		
		
		float position[] = { 0, -80, 0, 0.1f };	
		gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, position, 0);

		float intensity[] = { 0.8f, 0.6f, 0.5f, 0.1f };
		gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, intensity, 0);
		gl.glEnable(GL.GL_LIGHT0);
//////////////////////////////////////광원2
		
		float position1[] = { 0, 80, 0, 0.1f };
		gl.glLightfv(GL.GL_LIGHT1, GL.GL_POSITION, position1, 0);

		gl.glLightfv(GL.GL_LIGHT1, GL.GL_DIFFUSE, intensity, 0);
		gl.glEnable(GL.GL_LIGHT1);
/////////////////////////////////////
	
		

		
		
		gl.glEnable(GL.GL_COLOR_MATERIAL);
		gl.glColorMaterial(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT_AND_DIFFUSE);

		gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, 10);
		gl.glEnable(GL.GL_POINT_SMOOTH);

		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glShadeModel(GL.GL_SMOOTH);

		glu = new GLU();

		animator = new FPSAnimator(this, fps);
		animator.start();

	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		// TODO Auto-generated method stub
		final GL gl = drawable.getGL();
		gl.glViewport(0, 0, width, height);
	}

	// Vertex Data
	class Data {
		double x;
		double y;
		double z;
	}

	class NormalVector {
		double x;
		double y;
		double z;
	}

	class MeshFace {
		int[] v1 = new int[3];
		int[] v2 = new int[3];
		int[] v3 = new int[3];
	}

	private void setData(String filename) {

		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(
					filename)));

			String line = reader.readLine();
			int cnt = 0;
			while (line != null) {
				StringTokenizer tok = new StringTokenizer(line, " ");
				if (tok.hasMoreTokens()) {
					String ident = tok.nextToken();
					if (ident.equals("v")) {
						Data data = new Data();
						data.x = Double.valueOf(tok.nextToken()) - 25;
						data.y = Double.valueOf(tok.nextToken());
						data.z = Double.valueOf(tok.nextToken());
						vertex.add(data);

						cnt++;
					} else if (ident.equals("vn")) {
						NormalVector nv = new NormalVector();
						nv.x = Double.valueOf(tok.nextToken()) - 25;
						nv.y = Double.valueOf(tok.nextToken());
						nv.z = Double.valueOf(tok.nextToken());
						vector.add(nv);
						cnt++;
					} else if (ident.equals("f")) {
						MeshFace f = new MeshFace();
						String[] tmpStr = tok.nextToken().toString().split("/");
						f.v1[0] = Integer.valueOf(tmpStr[0]);
						f.v1[1] = Integer.valueOf(tmpStr[1]);
						f.v1[2] = Integer.valueOf(tmpStr[2]);

						tmpStr = tok.nextToken().toString().split("/");
						f.v2[0] = Integer.valueOf(tmpStr[0]);
						f.v2[1] = Integer.valueOf(tmpStr[1]);
						f.v2[2] = Integer.valueOf(tmpStr[2]);

						tmpStr = tok.nextToken().toString().split("/");
						f.v3[0] = Integer.valueOf(tmpStr[0]);
						f.v3[1] = Integer.valueOf(tmpStr[1]);
						f.v3[2] = Integer.valueOf(tmpStr[2]);

						faces.add(f);
						cnt++;
					}
				}
				line = reader.readLine();
			}
		} catch (Exception e) {
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
	public void mouseMoved(MouseEvent e) {
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
}
