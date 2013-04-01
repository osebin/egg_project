package jogl_topo;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

class DrawCircle
{	
	public void drawCircles(GL gl, GLU glu, GLUquadric quadric, Double x, Double y, Double z, float angle, float rx, float ry, float rz, float radian, float color_R, float color_G, float color_B)
	{
		gl.glPushMatrix();
		
		
		gl.glEnable(GL.GL_BLEND);
		
		
		
		gl.glColor4f(color_R, color_G, color_B,1.0f);

		gl.glTranslated(x, y, z);
		gl.glRotatef(angle, rx, ry, rz);
		gl.glScalef(3.0f, 0.3f, 3.0f);
		glu.gluSphere(quadric, radian, 5, 5);

		gl.glPopMatrix();

	}
	
	
}