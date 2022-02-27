package luebkeTJ;

import javax.vecmath.Vector3f;

import com.bulletphysics.collision.shapes.ConcaveShape;
import com.bulletphysics.collision.shapes.ConvexHullShape;
import com.bulletphysics.collision.shapes.ConvexShape;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.util.ObjectArrayList;

public class MyTriShape {
	ConvexHullShape tri;
	
	
	
	public static ConvexHullShape makeMesh(Model m, int index, int maxIndex)
	{
		
		
		int pauseFaces = 0;
		ObjectArrayList<Vector3f> vertex = new ObjectArrayList<Vector3f>();
    	while(index < maxIndex)
    	{
    		
	    	Face faces = m.faces.get(index);
	    	index ++;
	    		if(pauseFaces <= 3)
	    		{
	    			pauseFaces ++;
		    		if(faces.normal.x == -1)
		    		{
			    		Vector3f v1 = new Vector3f();
			    		v1.x = m.vertices.get((int) faces.vertex.x).x;
			    		v1.y = m.vertices.get((int) faces.vertex.x).y;
			    		v1.z = m.vertices.get((int) faces.vertex.x).z;
			    		Vector3f v2 = new Vector3f();
			    		v2.x = m.vertices.get((int) faces.vertex.y).x;
			    		v2.y = m.vertices.get((int) faces.vertex.y).y;
			    		v2.z = m.vertices.get((int) faces.vertex.y).z;
			    		Vector3f v3 = new Vector3f();
			    		v3.x = m.vertices.get((int) faces.vertex.z).x;
			    		v3.y = m.vertices.get((int) faces.vertex.z).y;
			    		v3.z = m.vertices.get((int) faces.vertex.z).z;
			    		
			    		vertex.add(v1);
			    		vertex.add(v2);
			    		vertex.add(v3);
		    		}
		    		else
		    		{
		    			Vector3f v1 = new Vector3f();
			    		v1.x = m.vertices.get((int) faces.vertex.x).x;
			    		v1.y = m.vertices.get((int) faces.vertex.x).y;
			    		v1.z = m.vertices.get((int) faces.vertex.x).z;
			    		Vector3f v2 = new Vector3f();
			    		v2.x = m.vertices.get((int) faces.vertex.y).x;
			    		v2.y = m.vertices.get((int) faces.vertex.y).y;
			    		v2.z = m.vertices.get((int) faces.vertex.y).z;
			    		Vector3f v3 = new Vector3f();
			    		v3.x = m.vertices.get((int) faces.vertex.z).x;
			    		v3.y = m.vertices.get((int) faces.vertex.z).y;
			    		v3.z = m.vertices.get((int) faces.vertex.z).z;
			    		
			    		vertex.add(v2);
			    		vertex.add(v1);
			    		vertex.add(v3);
			    		Vector3f v4 = new Vector3f();
			    		v4.x = m.vertices.get((int) faces.normal.x).x;
			    		v4.y = m.vertices.get((int) faces.normal.x).y;
			    		v4.z = m.vertices.get((int) faces.normal.x).z;
			    		//vertex.add(v1);
			    		vertex.add(v4);
			    		//vertex.
			    		
			    		//vertex.add(v3);
		    		}
	    		}
    	}
	    		
    		
    		
        
		ConvexHullShape shape = new ConvexHullShape(vertex);
    	
    	return shape;
    	
	}
}
