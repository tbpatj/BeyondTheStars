package luebkeTJ;

import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import com.bulletphysics.linearmath.DefaultMotionState;
import com.bulletphysics.linearmath.Transform;

public class ReturnThis {
	Matrix4f max = new Matrix4f();
	Vector3f v = new Vector3f();
	Quat4f q = new Quat4f();
	public void returnMaxWith(Quat4f quat,Vector3f d,float hi)
	{
		max = new Matrix4f(quat,d,hi);
	}
	public void makeV(float x, float y, float z)
	{
		v = new Vector3f(x,y,z);
	}
	public Vector3f ReturnV(float x, float y, float z)
	{
		return new Vector3f(x,y,z);
	}
}
