package jogl_topo;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Composite;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

import com.sun.opengl.util.FPSAnimator;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;

public class JOGL_Topo_Gradient extends GLCanvas implements GLEventListener,
		MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;
	private GLU glu; // GL units
	private int fps = 60;
	private FPSAnimator animator;

	private int X = 0, Y = 80, Z = 100;
	private float moveX = 0;
	private int oldX = 0;
	private boolean isDragging = false;

	ArrayList<Data> vertex = new ArrayList<Data>();
	ArrayList<TextureInfo> vt = new ArrayList<TextureInfo>();
	ArrayList<NormalVector> vector = new ArrayList<NormalVector>();
	ArrayList<MeshFace> faces = new ArrayList<MeshFace>();
	ArrayList<Map> map = new ArrayList<Map>();
	float time = 0;

	int range = 0;

	private Texture joglTexture;

	private int select = 0;

	public static int channel_data[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 5, 6, 1, 2, 5 };

	int max = 0;

	private float[][] data = { { -20f, 55f, 20f }, { 20f, 55f, 20f },
			{ 20f, 55f, -20f }, { -20f, 55f, -20f } };

	public JOGL_Topo_Gradient(GLCapabilities capabilities, int width, int height) {
		addGLEventListener(this);

		setData("sample5.obj");
	}
	public JOGL_Topo_Gradient() {
		GLCapabilities capabilities = createGLCapabilities();
		JOGL_Topo_Gradient canvas = new JOGL_Topo_Gradient(capabilities, 800, 500);
		final JFrame frame = new JFrame("DEMO");
		frame.getContentPane().add(canvas, BorderLayout.CENTER);
		frame.setSize(602, 750);
		frame.setVisible(true);
		frame.requestFocus();
		frame.addWindowListener(new WindowAdapter() {
			@SuppressWarnings("deprecation")
			public void windowClosing(WindowEvent e){
				frame.dispose();
			}
		});
		addGLEventListener(this);

		setData("sample5.obj");
	}

	private static GLCapabilities createGLCapabilities() {

		GLCapabilities capabilities = new GLCapabilities();
		capabilities.setRedBits(8);
		capabilities.setBlueBits(8);
		capabilities.setGreenBits(8);
		capabilities.setAlphaBits(8);

		return capabilities;

	}

	// ////////////////////////

	double minX = 99;
	double maxX = 0;
	double minZ = 99;
	double maxZ = 0;
	double WX;
	double WZ;

	private void drawMapping(GL gl) {
		// 湲곗� 38.8794 36.3633 12.4635
		// (13.2435, 41.3030,-1.0177)----�대쭏履�
		// (10.0, 39.3633, 18.4635)----洹�そ

		gl.glPushMatrix();
		// gl.glBegin(GL.GL_POINTS);
		gl.glBegin(GL.GL_POLYGON);

		for (int k = 0; k < faces.size(); k++) {
			int v1 = faces.get(k).v1[0] - 1;// vertex
			int v3 = faces.get(k).v1[2] - 1;
			if (vertex.get(v1).y > 30) {

				double xx = (vertex.get(v1).x - minX) / WX;
				double zz = (vertex.get(v1).z - minZ) / WZ;

				gl.glTexCoord2d(xx, zz);
				// System.out.println(vertex.get(v1).x + "   " +
				// vertex.get(v1).z);
				v1 = faces.get(k).v2[0] - 1;
				v3 = faces.get(k).v2[2] - 1;
				gl.glNormal3d(vector.get(v3).x, vector.get(v3).y,
						vector.get(v3).z);
				gl.glVertex3d(vertex.get(v1).x, vertex.get(v1).y,
						vertex.get(v1).z);

				v1 = faces.get(k).v3[0] - 1;
				v3 = faces.get(k).v3[2] - 1;
				gl.glNormal3d(vector.get(v3).x, vector.get(v3).y,
						vector.get(v3).z);
				gl.glVertex3d(vertex.get(v1).x, vertex.get(v1).y,
						vertex.get(v1).z);
				v1 = faces.get(k).v1[0] - 1;
				v3 = faces.get(k).v1[2] - 1;
				gl.glNormal3d(vector.get(v3).x, vector.get(v3).y,
						vector.get(v3).z);
				gl.glVertex3d(vertex.get(v1).x, vertex.get(v1).y,
						vertex.get(v1).z);
				v1 = faces.get(k).v1[0] - 1;
				v3 = faces.get(k).v1[2] - 1;
				gl.glNormal3d(vector.get(v3).x, vector.get(v3).y,
						vector.get(v3).z);
				gl.glVertex3d(vertex.get(v1).x, vertex.get(v1).y,
						vertex.get(v1).z);

			} else {
			}
		}

		gl.glEnd();

		gl.glPopMatrix();

	}

	private void drawPage(GL gl) {
		try {
			joglTexture = TextureIO.newTexture(new File("test.jpeg"), true);
		} catch (IOException e) {
			System.err.println("Unable to load texture jogl.png");
		}
		gl.glTranslatef(0, 1, 0);
		gl.glPushMatrix();
		gl.glTranslatef(0, 10, 0);
		gl.glShadeModel(GL.GL_SMOOTH);
		

		gl.glBegin(GL.GL_POLYGON);
		gl.glNormal3f(0, 1.0f, 0.0f);
		gl.glTexCoord2f(0, 1);
		// gl.glColor3d(1, 0, 0);
		gl.glVertex3f(data[0][0], 65, data[0][2]);
		gl.glTexCoord2f(1, 1);
		// gl.glColor3d(0, 0, 1);
		gl.glVertex3f(data[1][0], 65, data[1][2]);
		gl.glTexCoord2f(1, 0);
		// gl.glColor3d(0, 1, 0);
		gl.glVertex3f(data[2][0], 65, data[2][2]);
		gl.glTexCoord2f(0, 0);
		// gl.glColor3d(1, 1, 0);
		gl.glVertex3f(data[3][0], 65, data[3][2]);
		gl.glEnd();
		gl.glPopMatrix();
	}

	private void drawFace(GL gl) {
		gl.glTranslatef(0, -30, 0);
		gl.glColor3f(1, 1, 1);
		gl.glPushMatrix();
		gl.glBegin(GL.GL_POINTS);
//		gl.glBegin(GL.GL_POLYGON);
//		gl.glBegin(GL.GL_TRIANGLES);
		gl.glColor3d( 0.8f, 0.6f, 0.5f);
		for (int i = 0; i < faces.size(); i++) {
			int v1 = faces.get(i).v1[0] - 1;
			int v3 = faces.get(i).v1[2] - 1;
			if (vertex.get(v1).y <= 32) {
			gl.glNormal3d(vector.get(v3).x, vector.get(v3).y,
					vector.get(v3).z);
			gl.glVertex3d(vertex.get(v1).x, vertex.get(v1).y,
					vertex.get(v1).z);

			v1 = faces.get(i).v2[0] - 1;
			v3 = faces.get(i).v2[2] - 1;
			gl.glNormal3d(vector.get(v3).x, vector.get(v3).y,
					vector.get(v3).z);
			gl.glVertex3d(vertex.get(v1).x, vertex.get(v1).y,
					vertex.get(v1).z);
			v1 = faces.get(i).v3[0] - 1;
			v3 = faces.get(i).v3[2] - 1;
			gl.glNormal3d(vector.get(v3).x, vector.get(v3).y,
					vector.get(v3).z);
			gl.glVertex3d(vertex.get(v1).x, vertex.get(v1).y,
					vertex.get(v1).z);
			}

		}
		gl.glEnd();
		gl.glPopMatrix();
	}

	private void drawBackground(GL gl) {
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		// 諛곌꼍 �곗깋�쇰줈 援먯껜
		// gl.glClearColor(1f, 1f, 1f, 0f);
		// 諛곌꼍 寃�젙�됱쑝濡�援먯껜
		// gl.glClearColor(0f, 0f, 0f, 0f);
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		// channel max value
		max = 0;
		for (int i = 0; i < 14; i++){
			if (max < channel_data[i]) {
				max = channel_data[i];
				select = i;
			}
		}
		
		GL gl = drawable.getGL();
		if (isDragging == false) {
			moveX++;
		}
		time++;
		
		makeFile(select);
		
		
		setCamera(gl, glu, 100);
		drawBackground(gl);
		drawFace(gl);
		
		gl.glBindTexture(GL.GL_TEXTURE_2D, 1);
		drawPage(gl);
		drawMapping(gl);
		gl.glBindTexture(GL.GL_TEXTURE_2D, 0);
	}

	// �붾㈃ �뚯쟾!!
	private void setCamera(GL gl, GLU glu, float distance) {
		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();

		float widthHeightRatio = (float) getWidth() / (float) getHeight();
		glu.gluPerspective(45, widthHeightRatio, 1, 1000);
		glu.gluLookAt(
					X, Y, Z, 
					0, 20, 15, 
					0, 150, 0);

		gl.glRotatef(moveX, 0, distance, 1);

		gl.glMatrixMode(GL.GL_MODELVIEW);
		gl.glLoadIdentity();

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
		
		drawable.addMouseListener(this);
		drawable.addMouseMotionListener(this);
		// TODO Auto-generated method stub
		drawable.setGL(new DebugGL(drawable.getGL()));
		final GL gl = drawable.getGL();
		glu = new GLU();

		gl.glEnable(GL.GL_DEPTH_TEST);
		// /////////////////////////////////////////
		gl.glEnable(GL.GL_LIGHTING);

		// float ambient[] = { 0.8f, 0.6f, 0.5f, 1f };
		float ambient[] = { 1f, 1f, 1f, 1f };
		gl.glLightModelfv(GL.GL_LIGHT_MODEL_AMBIENT, ambient, 0);

		gl.glEnable(GL.GL_COLOR_MATERIAL);
		gl.glColorMaterial(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT_AND_DIFFUSE);

		gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, 10);
		float position[] = { 0, -80, 0, 0.1f };
		gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, position, 0);

		float intensity[] = { 0.8f, 0.6f, 0.5f, 0.1f };
		gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, intensity, 0);
		gl.glEnable(GL.GL_LIGHT0);
		// ////////////////////////////////////占쏙옙占쏙옙2

		float position1[] = { 0, 80, 0, 0.1f };
		gl.glLightfv(GL.GL_LIGHT1, GL.GL_POSITION, position1, 0);

		gl.glLightfv(GL.GL_LIGHT1, GL.GL_DIFFUSE, intensity, 0);
		gl.glEnable(GL.GL_LIGHT1);

		for (int i = 0; i < faces.size(); i++) {
			int v1 = faces.get(i).v1[0] - 1;
			int v3 = faces.get(i).v1[2] - 1;

			if (minX > vertex.get(v1).x)
				minX = vertex.get(v1).x;
			else if (minZ > vertex.get(v1).z)
				minZ = vertex.get(v1).z;
			else if (maxX < vertex.get(v1).x)
				maxX = vertex.get(v1).x;
			else if (maxZ < vertex.get(v1).z)
				maxZ = vertex.get(v1).z;

			WX = maxX - minX;
			WZ = maxZ - minZ;
		}

		

		gl.glPushMatrix();
		gl.glEnable(GL.GL_TEXTURE_2D);
		gl.glMatrixMode(GL.GL_MODELVIEW);
		gl.glLoadIdentity();

		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glShadeModel(GL.GL_SMOOTH);
		gl.glPopMatrix();
		animator = new FPSAnimator(this, fps);
		animator.start();

	}

	public void makeRound(Graphics2D g2, int xx, int yy, int ww, int hh,
			int arc, Color start, Color end) {
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		Composite composite = g2.getComposite();
		g2.setComposite(AlphaComposite
				.getInstance(AlphaComposite.SRC_OVER, .7f));

		Shape vButtonShape = new RoundRectangle2D.Double((double) xx,
				(double) yy, (double) ww, (double) hh, (double) arc,
				(double) arc);

		java.awt.Paint p = new GradientPaint(0.0F, 0.0F, start, 0.0F, 40, end);
		g2.setPaint(p);

		g2.setClip(vButtonShape);
		g2.fillOval(xx, yy, ww, hh);
	}

	public void makeRound(Graphics2D g2, int xx, int yy, int ww, int hh,
			int arc, Color start, Color end, int px, int py) {
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		Composite composite = g2.getComposite();
		g2.setComposite(AlphaComposite
				.getInstance(AlphaComposite.SRC_OVER, .7f));

		Shape vButtonShape = new RoundRectangle2D.Double((double) xx,
				(double) yy, (double) ww, (double) hh, (double) arc,
				(double) arc);
		Color mid = new Color((start.getRed() + end.getRed()) / 2,
				(start.getGreen() + end.getGreen()) / 2,
				(start.getBlue() + end.getBlue()) / 2);

		float[] dist = { 0.3f, 1f };

		Color[] color = { start, end };
		java.awt.Paint p = new RadialGradientPaint(px, py, 30, dist, color);

		g2.setPaint(p);
		g2.setClip(vButtonShape);
		g2.fillOval(xx, yy, ww, hh);

	}

	public void makeFile(int select) {
		int xx = 0;
		int yy = 0;
		int w = 60;
		int h = 60;
		int ww = 50;
		int hh = 50;
		int arc = 10;
		
		Color vStartColor = Color.RED;
		Color vEndColor = Color.BLUE;

		BufferedImage image = new BufferedImage(50, 50,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = image.createGraphics();
		Graphics g = image.getGraphics();
		Graphics2D g2 = (Graphics2D) g;
		//
		// graphics.setColor(Color.white);
		// graphics.fillRect(0, 0, w, h);
//		System.out.println(select);
		// 40~10
		switch (select) {
		// 醫���
		case 0:
			makeRound(g2, xx, yy, ww, hh, arc, vStartColor, vEndColor, 40, 50);// -range);
			break;
		// 醫���
		case 1:
			makeRound(g2, xx, yy, ww, hh, arc, vStartColor, vEndColor, 45, 35);
			break;
		// ����
		case 2:
			makeRound(g2, xx, yy, ww, hh, arc, vStartColor, vEndColor, 27, 40);
			break;
		// ����
		case 3:
			makeRound(g2, xx, yy, ww, hh, arc, vStartColor, vEndColor, 30, 30);
			break;
		case 4:
			makeRound(g2, xx, yy, ww, hh, arc, vStartColor, vEndColor, 50, 25);
			break;
		case 5:
			makeRound(g2, xx, yy, ww, hh, arc, vStartColor, vEndColor, 40, 10);
			break;
		case 6:
			makeRound(g2, xx, yy, ww, hh, arc, vStartColor, vEndColor, 30, 5);
			break;
		case 7:
			makeRound(g2, xx, yy, ww, hh, arc, vStartColor, vEndColor, 20, 5);
			break;
		case 8:
			makeRound(g2, xx, yy, ww, hh, arc, vStartColor, vEndColor, 10, 10);
			break;
		case 9:
			makeRound(g2, xx, yy, ww, hh, arc, vStartColor, vEndColor, 0, 25);
			break;
		case 10:
			makeRound(g2, xx, yy, ww, hh, arc, vStartColor, vEndColor, 20, 30);
			break;
		case 11:
			makeRound(g2, xx, yy, ww, hh, arc, vStartColor, vEndColor, 23, 40);
			break;
		case 12:
			makeRound(g2, xx, yy, ww, hh, arc, vStartColor, vEndColor, 5, 35);
			break;
		case 13:
			makeRound(g2, xx, yy, ww, hh, arc, vStartColor, vEndColor, 10, 50);
			break;

		// makeRound(g2, xx, yy, ww, hh, arc, Color.white, Color.white,60,60);
		}

		try {
			File file = new File("test.jpeg");
			ImageIO.write(image, "jpeg", file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
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

	class TextureInfo {
		double x;
		double y;
		double z;
	}

	class MeshFace {
		int[] v1 = new int[3];
		int[] v2 = new int[3];
		int[] v3 = new int[3];
	}

	class Map {
		double x;
		double y;
		double z;
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
					} else if (ident.equals("vt")) {
						TextureInfo ti = new TextureInfo();
						ti.x = Double.valueOf(tok.nextToken()) - 25;
						ti.y = Double.valueOf(tok.nextToken());
						ti.z = Double.valueOf(tok.nextToken());
						vt.add(ti);
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
		// System.out.println(" -- " + e.getX());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Clicked -> X : " + e.getX() + " / " + "Y : "
				+ e.getY());
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
