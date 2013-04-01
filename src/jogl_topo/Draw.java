package jogl_topo;

import java.util.ArrayList;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

public class Draw {
	GL gl;
	GLU glu;
	private static Colorvalues[] color_V = new Colorvalues[16];
	private static DrawCircle[] draw_Circle = new DrawCircle[16];

	//채널 정보
		
		
		void drawLine() {
			// //////////////////////////////////좌표축 그리기
			gl.glBegin(GL.GL_LINE_LOOP);// red X
			gl.glColor3f(1.0f, 0.0f, 0.0f);
			gl.glVertex3f(70.0f, 0.0f, 0.0f);
			gl.glVertex3f(-70.0f, 0.0f, 0.0f);
			gl.glEnd();
			gl.glBegin(GL.GL_LINE_LOOP);// green Y
			gl.glColor3f(0.0f, 1.0f, 0.0f);
			gl.glVertex3f(0.0f, 70.0f, 0.0f);
			gl.glVertex3f(0.0f, -70.0f, 0.0f);
			gl.glEnd();
			gl.glBegin(GL.GL_LINE_LOOP);// blue Z
			gl.glColor3f(0.0f, 0.0f, 1.0f);
			gl.glVertex3f(0.0f, 0.0f, 70.0f);
			gl.glVertex3f(0.0f, 0.0f, -70.0f);
			gl.glEnd();
		}
		public void drawFace(GL gl,ArrayList<Data> vertex,ArrayList<NormalVector> vector,ArrayList<MeshFace> faces)
		{
			gl.glBegin(GL.GL_TRIANGLES);
			
			// ////색 입히기
			for (int i = 0; i < faces.size(); i++) {
				int v1 = faces.get(i).v1[0] - 1;

				int v3 = faces.get(i).v1[2] - 1;

				gl.glColor4f(0.8f, 0.6f, 0.5f, 1.0f);// 살색
				gl.glNormal3d(vector.get(v3).x, vector.get(v3).y, vector.get(v3).z);
				gl.glVertex3d(vertex.get(v1).x, vertex.get(v1).y, vertex.get(v1).z);

				v1 = faces.get(i).v2[0] - 1;

				v3 = faces.get(i).v2[2] - 1;

				gl.glNormal3d(vector.get(v3).x, vector.get(v3).y, vector.get(v3).z);
				gl.glVertex3d(vertex.get(v1).x, vertex.get(v1).y, vertex.get(v1).z);

				v1 = faces.get(i).v3[0] - 1;

				v3 = faces.get(i).v3[2] - 1;

				gl.glNormal3d(vector.get(v3).x, vector.get(v3).y, vector.get(v3).z);
				gl.glVertex3d(vertex.get(v1).x, vertex.get(v1).y, vertex.get(v1).z);

			}
			gl.glEnd();
		}

}
