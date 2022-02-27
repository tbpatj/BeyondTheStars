package luebkeTJ;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.util.vector.Vector3f;

public class plyLoader {
	public static Model loadModel(File f) throws FileNotFoundException, IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(f));
		Model m = new Model();
		String line;
		float vertices = 0;
		float faces = 0;
		boolean start = false;
		while((line = reader.readLine()) != null)
		{
			if(line.startsWith("e") && start == false)
			{
				String type = (line.split(" ")[1]);
				if(type.equalsIgnoreCase("vertex"))
				{
					vertices = Float.valueOf(line.split(" ")[2]);
				}
				if(type.equalsIgnoreCase("face"))
				{
					faces = Float.valueOf(line.split(" ")[2]);
					start = true;
				}
				
			}
			
			if(start == true && !line.startsWith("e") && !line.startsWith("p"))
			{
				if(vertices > 0)
				{
					float x = Float.valueOf(line.split(" ")[0]);
					float y = Float.valueOf(line.split(" ")[1]);
					float z = Float.valueOf(line.split(" ")[2]);
					m.vertices.add(new Vector3f(x,y,z));
					
					float nx = Float.valueOf(line.split(" ")[3]);
					float ny = Float.valueOf(line.split(" ")[4]);
					float nz = Float.valueOf(line.split(" ")[5]);
					m.normals.add(new Vector3f(nx,ny,nz));
					float cx = Float.valueOf(line.split(" ")[6]);
					float cy = Float.valueOf(line.split(" ")[7]);
					float cz = Float.valueOf(line.split(" ")[8]);
					cx = cx / 255;
					cy = cy / 255;
					cz = cz / 255;
					m.colors.add(new Vector3f(cx,cy,cz));
					//System.out.println("x: " + x + " y: " + y + " z: " + z + " normalX: " + nx + " normalY: " + ny + " normalZ: " + nz + " Red: " + cx + " Green: " + cy + " Blue: " + cz);
					vertices --;
				}
				else
				{
					if(line.startsWith("4"))
					{
						Vector3f vertexIndices = new Vector3f(Float.valueOf(line.split(" ")[1]),Float.valueOf(line.split(" ")[2]),Float.valueOf(line.split(" ")[3]));
						Vector3f normalIndices = new Vector3f(Float.valueOf(line.split(" ")[4]),0,0);
						m.faces.add(new Face(vertexIndices,normalIndices));
					}
					if(line.startsWith("3"))
					{
						Vector3f vertexIndices = new Vector3f(Float.valueOf(line.split(" ")[1]),Float.valueOf(line.split(" ")[2]),Float.valueOf(line.split(" ")[3]));
						Vector3f normalIndices = new Vector3f(-1,0,0);
						m.faces.add(new Face(vertexIndices,normalIndices));
					}
					
				}
				/**
				else if(line.startsWith("vn "))
				{
					float x = Float.valueOf(line.split(" ")[1]);
					float y = Float.valueOf(line.split(" ")[2]);
					float z = Float.valueOf(line.split(" ")[3]);
					m.normals.add(new Vector3f(x,y,z));
				}
				else if(line.startsWith("f "))
				{
					Vector3f vertexIndices = new Vector3f(Float.valueOf(line.split(" ")[1].split("/")[0]),Float.valueOf(line.split(" ")[2].split("/")[0]),Float.valueOf(line.split(" ")[3].split("/")[0]));
					Vector3f normalIndices = new Vector3f(Float.valueOf(line.split(" ")[1].split("/")[2]),Float.valueOf(line.split(" ")[2].split("/")[2]),Float.valueOf(line.split(" ")[3].split("/")[2]));
					m.faces.add(new Face(vertexIndices,normalIndices));
				}
				*/
			}
			
			
			
		}
		reader.close();
		return m;
	}
}
