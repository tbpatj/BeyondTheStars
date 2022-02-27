package luebkeTJ;

import static org.lwjgl.util.glu.GLU.gluLookAt;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.ARBFramebufferObject.*;
import static org.lwjgl.opengl.ARBShadowAmbient.GL_TEXTURE_COMPARE_FAIL_VALUE_ARB;
import static org.lwjgl.opengl.EXTFramebufferObject.GL_DEPTH_ATTACHMENT_EXT;
import static org.lwjgl.opengl.EXTFramebufferObject.GL_FRAMEBUFFER_COMPLETE_EXT;
import static org.lwjgl.opengl.EXTFramebufferObject.GL_FRAMEBUFFER_EXT;
import static org.lwjgl.opengl.EXTFramebufferObject.GL_MAX_RENDERBUFFER_SIZE_EXT;
import static org.lwjgl.opengl.EXTFramebufferObject.GL_RENDERBUFFER_EXT;
import static org.lwjgl.opengl.EXTFramebufferObject.glBindFramebufferEXT;
import static org.lwjgl.opengl.EXTFramebufferObject.glBindRenderbufferEXT;
import static org.lwjgl.opengl.EXTFramebufferObject.glCheckFramebufferStatusEXT;
import static org.lwjgl.opengl.EXTFramebufferObject.glFramebufferRenderbufferEXT;
import static org.lwjgl.opengl.EXTFramebufferObject.glGenFramebuffersEXT;
import static org.lwjgl.opengl.EXTFramebufferObject.glGenRenderbuffersEXT;
import static org.lwjgl.opengl.EXTFramebufferObject.glRenderbufferStorageEXT;
import static org.lwjgl.opengl.GL11.GL_ALPHA_TEST;
import static org.lwjgl.opengl.GL11.GL_AMBIENT;
import static org.lwjgl.opengl.GL11.GL_COLOR_MATERIAL;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_COMPONENT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_DIFFUSE;
import static org.lwjgl.opengl.GL11.GL_EYE_LINEAR;
import static org.lwjgl.opengl.GL11.GL_FLAT;
import static org.lwjgl.opengl.GL11.GL_GREATER;
import static org.lwjgl.opengl.GL11.GL_INTENSITY;
import static org.lwjgl.opengl.GL11.GL_LEQUAL;
import static org.lwjgl.opengl.GL11.GL_LIGHT0;
import static org.lwjgl.opengl.GL11.GL_LIGHTING;
import static org.lwjgl.opengl.GL11.GL_LINEAR;
import static org.lwjgl.opengl.GL11.GL_MAX_TEXTURE_SIZE;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW_MATRIX;
import static org.lwjgl.opengl.GL11.GL_NONE;
import static org.lwjgl.opengl.GL11.GL_NORMALIZE;
import static org.lwjgl.opengl.GL11.GL_POLYGON_OFFSET_FILL;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_PROJECTION_MATRIX;
import static org.lwjgl.opengl.GL11.GL_Q;
import static org.lwjgl.opengl.GL11.GL_R;
import static org.lwjgl.opengl.GL11.GL_S;
import static org.lwjgl.opengl.GL11.GL_SMOOTH;
import static org.lwjgl.opengl.GL11.GL_T;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_GEN_MODE;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_GEN_Q;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_GEN_R;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_GEN_S;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_GEN_T;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_S;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_T;
import static org.lwjgl.opengl.GL11.glAlphaFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glColorMask;
import static org.lwjgl.opengl.GL11.glCopyTexImage2D;
import static org.lwjgl.opengl.GL11.glDepthFunc;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glDrawBuffer;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glGetFloat;
import static org.lwjgl.opengl.GL11.glGetInteger;
import static org.lwjgl.opengl.GL11.glLight;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glPolygonOffset;
import static org.lwjgl.opengl.GL11.glReadBuffer;
import static org.lwjgl.opengl.GL11.glShadeModel;
import static org.lwjgl.opengl.GL11.glTexGeni;
import static org.lwjgl.opengl.GL11.glTexParameterf;
import static org.lwjgl.opengl.GL11.glTexParameteri;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;
import static org.lwjgl.opengl.GL14.GL_DEPTH_COMPONENT32;
import static org.lwjgl.opengl.GL14.GL_DEPTH_TEXTURE_MODE;



//import javax.vecmath.Matrix3f;
//import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.PixelFormat;
import org.lwjgl.util.glu.Cylinder;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
//import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.opengl.GLContext;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;



























import com.bulletphysics.collision.broadphase.BroadphaseInterface;
import com.bulletphysics.collision.broadphase.DbvtBroadphase;
import com.bulletphysics.collision.dispatch.CollisionConfiguration;
import com.bulletphysics.collision.dispatch.CollisionDispatcher;
import com.bulletphysics.collision.dispatch.CollisionFlags;
import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.dispatch.DefaultCollisionConfiguration;
import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.collision.shapes.BvhTriangleMeshShape;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.ConeShape;
import com.bulletphysics.collision.shapes.ConvexHullShape;
import com.bulletphysics.collision.shapes.CylinderShape;
import com.bulletphysics.collision.shapes.IndexedMesh;
import com.bulletphysics.collision.shapes.SphereShape;
import com.bulletphysics.collision.shapes.StaticPlaneShape;
import com.bulletphysics.collision.shapes.TriangleIndexVertexArray;
import com.bulletphysics.collision.shapes.TriangleMeshShape;
import com.bulletphysics.collision.shapes.TriangleShape;
import com.bulletphysics.dynamics.DiscreteDynamicsWorld;
import com.bulletphysics.dynamics.DynamicsWorld;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;
import com.bulletphysics.dynamics.constraintsolver.ConstraintSolver;
import com.bulletphysics.dynamics.constraintsolver.SequentialImpulseConstraintSolver;
import com.bulletphysics.linearmath.DefaultMotionState;
import com.bulletphysics.linearmath.MotionState;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.util.ObjectArrayList;

public class Main {
	boolean paused = false;
	int screenWidth, screenHeight;
	int maxTextureSize;
	float FOV = 45;
	float topX,topY,topZ;
	 Matrix4f textureMatrix = new Matrix4f();
	 int shadowWidth = 640;
	  int shadowHeight = 480;
	 boolean useFBO = true;
	 float factor = (float) 4.0;
	 boolean ambientShadowsAvailable = false;
	float renderDistance = 500000000;
	float cameraX,cameraY,cameraZ;
	 private static final FloatBuffer ambientLight = BufferUtils.createFloatBuffer(4);
	    private static final FloatBuffer diffuseLight = BufferUtils.createFloatBuffer(4);
	   // private static final FloatBuffer lightPosition = BufferUtils.createFloatBuffer(4);
	    private static final FloatBuffer cameraPosition = BufferUtils.createFloatBuffer(4);
	    private static final FloatBuffer tempBuffer = BufferUtils.createFloatBuffer(4);
	float cameraXDir,cameraYDir,cameraZDir;
	FloatBuffer lightPosition = BufferUtils.createFloatBuffer(4);
	float lightX,lightY,lightZ;
	int starsAdded = 0;
	List<Star> Stars = new ArrayList<Star>();
	List<Data> KnownAir = new ArrayList<Data>();
	boolean mouseControl = true;
	boolean mouseDown = false;
	int wait = 0;
	boolean escapeKeyPushed = false;
	boolean keySwitch = false;
	int shadowMapWidth = 1024;
	int shadowMapHeight = 1024;
	String userHome = System.getProperty("user.home");
	FloatBuffer textureBuffer = BufferUtils.createFloatBuffer(16);
	Matrix4f depthModelViewProjection = new Matrix4f();
	int frameBuffer = 0;
	int renderBuffer = 0;
	int mode = 0;
	float disRadius = 12;
	float moveStrength = 10;
	int objectDisplayList;
	Vector3f ShipRot = new Vector3f(0,0,0);
	
	//Texture grass;
	//Textures
	public static void main(String[] args) {
		
		
		Main DisplayMain = new Main();
		DisplayMain.start();
		
	}
	
	private static Transform DEFAULT_BALL_TRANSFORM = new Transform(); //new Transform(new Matrix4f(new Quat4f(0,0,0,1), new Vector3f(0,35,0),1.0f));
	private static DynamicsWorld dynamicsWorld;
	private static Set<RigidBody> balls = new HashSet<RigidBody>();
	private static Set<RigidBody> bullets = new HashSet<RigidBody>();
	private static Set<RigidBody> Boxs = new HashSet<RigidBody>();
	private static Set<RigidBody> TriShapes = new HashSet<RigidBody>();
	private static RigidBody controlBall;
	private static Sphere sphere = new Sphere();
	Cylinder cly = new Cylinder();
	int mouseHasDown = 0;
	private static void setUpPhysics()
	{
		ReturnThis r= new ReturnThis();
		r.makeV(0, 35, 0);
		r.returnMaxWith(new Quat4f(0,0,0,1), r.v,1f);
		DEFAULT_BALL_TRANSFORM = new Transform(r.max);
		
		BroadphaseInterface broadphase = new DbvtBroadphase();
		CollisionConfiguration collisionConfiguration = new DefaultCollisionConfiguration();
		CollisionDispatcher dispatcher = new CollisionDispatcher(collisionConfiguration);
		ConstraintSolver solver = new SequentialImpulseConstraintSolver();
		dynamicsWorld = new DiscreteDynamicsWorld(dispatcher,broadphase,solver,collisionConfiguration);
		r.makeV(0, -30, 0);
		dynamicsWorld.setGravity(r.v);
		r.makeV(0, 1, 0);
		CollisionShape groundShape = new StaticPlaneShape(r.v,0.25f);
		CollisionShape ballShape = new BoxShape(r.ReturnV(1, 1, 1));
		r.returnMaxWith(new Quat4f(0,0,0,1), r.ReturnV(0, -30.2f, 0),1f);
		MotionState groundMotionState = new DefaultMotionState(new Transform(r.max));
		RigidBodyConstructionInfo groundBodyConstructionInfo = new RigidBodyConstructionInfo(0,groundMotionState,groundShape,r.ReturnV(0, 0, 0));
		groundBodyConstructionInfo.restitution = 0.1f;
		groundBodyConstructionInfo.friction = 1f;
		RigidBody groundRigidBody = new RigidBody(groundBodyConstructionInfo);
		//dynamicsWorld.addRigidBody(groundRigidBody);
		MotionState ballMotionState = new DefaultMotionState(DEFAULT_BALL_TRANSFORM);
		r.ReturnV(0, 0, 0);
		ballShape.calculateLocalInertia(2.5f, r.v);
		RigidBodyConstructionInfo ballConstructionInfo = new RigidBodyConstructionInfo(2.5f,ballMotionState,ballShape,r.v);
		ballConstructionInfo.restitution = 0f;
		ballConstructionInfo.friction = 1f;
		ballConstructionInfo.mass = 0.9f;
		ballConstructionInfo.angularDamping = 0.1f;
		//ballConstructionInfo.angularDamping = 0.95f;
		controlBall = new RigidBody(ballConstructionInfo);
		controlBall.setActivationState(CollisionObject.DISABLE_DEACTIVATION);
		balls.add(controlBall);
		dynamicsWorld.addRigidBody(controlBall);
		addGround(0,0f,0,0,1,0);
		//addGround(-50,-301f,0,1,0,0);
		//addGround(0,-30f,-50,0,0,1);
		//addGround(0,-30f,50,0,0,-1);
		
		//Transform j = new Transform
		
		
	}
	public static void addGround(float x, float y, float z, float xW, float yW, float zW)
	{
		ReturnThis r = new ReturnThis();
		r.makeV(xW, yW, zW);
		CollisionShape groundShape = new StaticPlaneShape(r.v,0.25f);
		//CollisionShape ballShape = new SphereShape(3.0f);
		r.returnMaxWith(new Quat4f(0,0,0,1), r.ReturnV(x, y, z),1f);
		MotionState groundMotionState = new DefaultMotionState(new Transform(r.max));
		RigidBodyConstructionInfo groundBodyConstructionInfo = new RigidBodyConstructionInfo(0,groundMotionState,groundShape,r.ReturnV(0, 0, 0));
		groundBodyConstructionInfo.restitution = 0f;
		groundBodyConstructionInfo.friction = 1f;
		RigidBody groundRigidBody = new RigidBody(groundBodyConstructionInfo);
		dynamicsWorld.addRigidBody(groundRigidBody);
	}
	public void loadTextures()
	{
		
		
		
	}
	public void addNewCollider(float x, float y, float z)
	{
		
		
		ReturnThis r= new ReturnThis();
		CollisionShape shape = new CylinderShape(r.ReturnV(2, 0.1f, 2));
		r.returnMaxWith(new Quat4f(0,0,0,1), r.ReturnV(x, y, z),1f);
		DefaultMotionState motionState = new DefaultMotionState(new Transform(r.max));//(new Quat4f(0,0,0,1), new Vector3f(x,y,z),1f)));
		r.makeV(0, 0, 0);
		shape.calculateLocalInertia(1.0f, r.v);
		RigidBodyConstructionInfo constructionInfo = new RigidBodyConstructionInfo(1.0f,motionState,shape,r.v);
		constructionInfo.restitution = 0.1f;
		constructionInfo.mass = 0.9f;
		constructionInfo.friction = 0.9f;
		RigidBody body = new RigidBody(constructionInfo);
		dynamicsWorld.addRigidBody(body);
		balls.add(body);
	}
	private void setUpStates()
	{
		int maxRenderbufferSize = glGetInteger(GL_MAX_RENDERBUFFER_SIZE_EXT);

        if (!GLContext.getCapabilities().OpenGL14 && GLContext.getCapabilities().GL_ARB_shadow) {
            System.out.println("Can't create shadows at all. Requires OpenGL 1.4 or the GL_ARB_shadow extension");
            System.exit(0);
        }

        if (GLContext.getCapabilities().GL_ARB_shadow_ambient) {
            ambientShadowsAvailable = true;
        } else {
            System.out.println("GL_ARB_shadow_ambient extension not availible.\n An extra rendering pass will be " +
                    "required.");
        }

        if (GLContext.getCapabilities().OpenGL20 || GLContext.getCapabilities().GL_EXT_framebuffer_object) {
            System.out.println("Higher quality shadows are availible.");
        }

        maxTextureSize = glGetInteger(GL_MAX_TEXTURE_SIZE);

        System.out.println("Maximum texture size: " + maxTextureSize);
        System.out.println("Maximum renderbuffer size: " + maxRenderbufferSize);

        /*
           * Check to see if the maximum texture size is bigger than 2048.
           * Performance drops too much if it much bigger than that.
           */
        if (maxTextureSize > 2048) {
            maxTextureSize = 2048;
            if (maxRenderbufferSize < maxTextureSize) {
                maxTextureSize = maxRenderbufferSize;
            }
        }

        if (useFBO) {
            shadowWidth = maxTextureSize;
            shadowHeight = maxTextureSize;
        }
		glEnable(GL_DEPTH_TEST);
        glDepthFunc(GL_LEQUAL);
       
		glPolygonOffset(factor , 0.0F);

        glShadeModel(GL_SMOOTH);
        glEnable(GL_LIGHTING);
        glEnable(GL_COLOR_MATERIAL);
        glEnable(GL_NORMALIZE);
        glEnable(GL_LIGHT0);

        // Setup some texture states
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_DEPTH_TEXTURE_MODE, GL_INTENSITY);

       
		// If ambient shadows are availible then we can skip a rendering pass.
        if (ambientShadowsAvailable ) {
            glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_COMPARE_FAIL_VALUE_ARB, 0.5F);
        }

        glTexGeni(GL_S, GL_TEXTURE_GEN_MODE, GL_EYE_LINEAR);
        glTexGeni(GL_T, GL_TEXTURE_GEN_MODE, GL_EYE_LINEAR);
        glTexGeni(GL_R, GL_TEXTURE_GEN_MODE, GL_EYE_LINEAR);
        glTexGeni(GL_Q, GL_TEXTURE_GEN_MODE, GL_EYE_LINEAR);

       
		// If we are using a FBO, we need to setup the framebuffer.
        if (useFBO) {
            frameBuffer = glGenFramebuffersEXT();
            glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, frameBuffer);

            renderBuffer = glGenRenderbuffersEXT();
            glBindRenderbufferEXT(GL_RENDERBUFFER_EXT, renderBuffer);

            glRenderbufferStorageEXT(GL_RENDERBUFFER_EXT, GL_DEPTH_COMPONENT32, maxTextureSize, maxTextureSize);

            glFramebufferRenderbufferEXT(GL_FRAMEBUFFER_EXT, GL_DEPTH_ATTACHMENT_EXT, GL_RENDERBUFFER_EXT,
                    renderBuffer);

            glDrawBuffer(GL_NONE);
            glReadBuffer(GL_NONE);

            int FBOStatus = glCheckFramebufferStatusEXT(GL_FRAMEBUFFER_EXT);
            if (FBOStatus != GL_FRAMEBUFFER_COMPLETE_EXT) {
                System.out.println("Framebuffer error!");
            }

            glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, 0);
        }
        drawShadowMap();
		/**
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_LIGHT0);
		GL11.glEnable(GL11.GL_NORMALIZE);
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glPolygonOffset(2.5f, 4f);
		GL11.glClearColor(0, 0.75f, 1, 1);
        GL11.glLight(GL11.GL_LIGHT0,GL11.GL_POSITION,floatBuffer((float)lightX,(float)lightY,(float)lightZ,1));
        GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, floatBuffer((float)(0),(float)0,(float)0,1));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, floatBuffer((float)(0),(float)0,(float)0,1));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, floatBuffer((float)(1.7f),(float)1.7,(float)1.7, 1.0f));
       
        
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL14.GL_DEPTH_TEXTURE_MODE, GL11.GL_INTENSITY);

        //GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        //GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        //GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        //GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0,GL14.GL_DEPTH_COMPONENT16, 1024, 1024, 0,GL11.GL_DEPTH_COMPONENT, GL11.GL_FLOAT, 0);
       // if (GambientShadowAvailable)
        GL11.glTexParameterf(GL11.GL_TEXTURE_2D,GL_TEXTURE_COMPARE_FAIL_VALUE_ARB ,0.5f); 
        GL11.glTexGeni(GL11.GL_S, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_EYE_LINEAR);
        GL11.glTexGeni(GL11.GL_T, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_EYE_LINEAR);
        GL11.glTexGeni(GL11.GL_R, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_EYE_LINEAR);
        GL11.glTexGeni(GL11.GL_Q, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_EYE_LINEAR);
        //GL11.glEnable(GL11.GL_ALPHA_TEST);
        //GL11.glAlphaFunc(GL11.GL_GREATER, 0.1f);
        //GL11.glTexParameterf(GL11.GL_TEXTURE_2D,GL14.GL_TEXTURE_COMPARE_FUNC , 0.1f);
        
        //GL11.glShadeModel(GL11.GL_SMOOTH);
		*/
	}
	public void RenderStars()
	{
		for(int i = 0; i < Stars.size(); i ++)
		{
			Star star = Stars.get(i);
			draw(star);
		}
	}
	public void setUpFrameBufferObject()
	{
		final int MAX_RENDERBUFFER_SIZE = GL11.glGetInteger(GL_MAX_RENDERBUFFER_SIZE);
		final int MAX_TEXTURE_SIZE = GL11.glGetInteger(GL11.GL_MAX_TEXTURE_SIZE);
		if(MAX_TEXTURE_SIZE > 1024)
		{
			if(MAX_RENDERBUFFER_SIZE < MAX_TEXTURE_SIZE)
			{
				shadowMapWidth = shadowMapHeight = MAX_RENDERBUFFER_SIZE;
			}
			else
			{
				shadowMapWidth = shadowMapHeight = 1024;
			}
		}
		else
		{
			shadowMapWidth = shadowMapHeight = MAX_TEXTURE_SIZE;
		}
		shadowMapWidth = 4000;
		shadowMapHeight = 4000;
		frameBuffer = glGenFramebuffers();
		glBindFramebuffer(GL_FRAMEBUFFER, frameBuffer);
		renderBuffer = glGenRenderbuffers();
		glBindRenderbuffer(GL_RENDERBUFFER, renderBuffer);
		glRenderbufferStorage(GL_RENDERBUFFER, GL14.GL_DEPTH_COMPONENT32, shadowMapWidth, shadowMapHeight);
		glFramebufferRenderbuffer(GL_FRAMEBUFFER, GL_DEPTH_ATTACHMENT, GL_RENDERBUFFER, renderBuffer);
		GL11.glDrawBuffer(GL11.GL_NONE);
		GL11.glReadBuffer(GL11.GL_NONE);
		int FBOStatus = glCheckFramebufferStatus(GL_FRAMEBUFFER);
		if(FBOStatus != GL_FRAMEBUFFER_COMPLETE)
		{
			System.out.println("Framebuffer error:" + GLU.gluErrorString(GL11.glGetError()));
		}
		glBindFramebuffer(GL_FRAMEBUFFER, 0);
	}
	public void init()
	{
		try {

			Display.setDisplayMode(new DisplayMode(screenWidth,screenHeight));
			//Display.setFullscreen(true);
			Display.setVSyncEnabled(true);
			int depthBufferBits = 24;
			Display.create(new PixelFormat(0, depthBufferBits,0));
			//Display.create();
		}catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		if (!GLContext.getCapabilities().OpenGL14 && !GLContext.getCapabilities().GL_ARB_shadow)
		{
			System.out.println("Can't create shadows at all, Requires OpenGL 1.4 or the GL_ARB_shadow extension");
			Display.destroy();
			System.exit(1);
		}
		if(!GLContext.getCapabilities().GL_ARB_shadow_ambient)
		{
			System.err.println("GL_ARB_shadow_ambient not working");
		}
		setUpStates();
		setUpFrameBufferObject();
		
		
		
		
	}
	public void addModelBody(Model m, float mass)
	{
			for(int i = 0; i < m.faces.size(); i ++)
			{
				ConvexHullShape tri = MyTriShape.makeMesh(m, i, i + 1);
				ReturnThis r = new ReturnThis();
				r.returnMaxWith(new Quat4f(0,0,0,1), r.ReturnV(0, 0, 0),1f);
				DefaultMotionState motionState = new DefaultMotionState(new Transform(r.max));
				r.makeV(0, 0, 0);
				tri.calculateLocalInertia(1.0f, r.v);
				//System.out.println(inertia.x + " " + inertia.y + " " + inertia.z);
				RigidBodyConstructionInfo constructionInfo = new RigidBodyConstructionInfo(1.0f,motionState,tri,r.v);
				constructionInfo.restitution = 0.1f;
				constructionInfo.mass =0 ;
				constructionInfo.friction = 0.9f;
				RigidBody triB = new RigidBody(constructionInfo);
				//triB.setActivationState(CollisionObject.DISABLE_DEACTIVATION);
				//controlBall.setCollisionFlags(controlBall.getCollisionFlags() | CollisionFlags.KINEMATIC_OBJECT);
				dynamicsWorld.addRigidBody(triB);
				TriShapes.add(triB);
				System.out.println(i);
				
			}
		
			
		
	}
	public void start()
	{
		screenWidth = 1000;
		screenHeight = 800;
		init();
		
		cameraX = 0;
		cameraY = -10;
		Display.setResizable(true);
		Display.setTitle("Beyond The Stars");
		Make3D();
		Mouse.setGrabbed(false);
		Mouse.setCursorPosition(screenWidth / 2, screenHeight / 2);
		//loadTextures();
		lightX = 100;
		lightY = 200;
		setUpPhysics();
		/**
		addNewCollider(-10,10,0);
		addNewCollider(-10,20,0);
		addNewCollider(-10,25,0);
		addNewCollider(-10,30,0);
		addNewCollider(-10,35,0);
		addNewCollider(-10,40,0);
		addNewCollider(-10,45,0);
		addNewCollider(-10,50,0);
		*/
		float y = 1f;
		float x = 4;
		float z = 0;
		
		for(int i = 0; i < 6; i ++)
		{
			for(int t = 0; t < 6; t ++)
			{
				for(int j = 0; j < 6; j ++)
				{
					
					
						//addBox(x,y,z,0.1f);
					
					y = y + 1.1f;
				}
				y = 1f;
				x = x + 1f;
			}
			x = 4;
			z = z + 1.01f;
		}
		//addBox(4,500,0,0.9f);			
			
		//addNewCollider(-10,10,0);
		//addNewCollider(0,10,10);
		//setUpFrameBufferObject();
		lightY = 50;
		lightX = 200;
		lightZ = 100;
		/**
		objectDisplayList = GL11.glGenLists(1);
		Model m = null;
		try{
			m = plyLoader.loadModel(new File(userHome + "/Library/Application Support/BeyondTheStars/Objects/testMap.ply"));
		} catch(FileNotFoundException e)
		{
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		} catch(IOException e)
		{
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		Make3D();
		GL11.glNewList(objectDisplayList, GL11.GL_COMPILE);
		{
			
			
			GL11.glBegin(GL11.GL_TRIANGLES);
			for(Face face: m.faces)
			{
				if(face.normal.x == -1)
				{
					//Normal1
					Vector3f n1 = m.normals.get((int)face.vertex.x);
					GL11.glNormal3f(n1.x, n1.y, n1.z);
					//Color1
					Vector3f c1 = m.colors.get((int)face.vertex.x);
					GL11.glColor3f(c1.x, c1.y, c1.z);
					//Vertex1
					Vector3f v1 = m.vertices.get((int)face.vertex.x);
					GL11.glVertex3f(v1.x, v1.y, v1.z);
					//Normal2
					Vector3f n2 = m.normals.get((int)face.vertex.y);
					GL11.glNormal3f(n2.x, n2.y, n2.z);
					//Color2
					Vector3f c2 = m.colors.get((int)face.vertex.y);
					GL11.glColor3f(c2.x, c2.y, c2.z);
					//Vertex2
					Vector3f v2 = m.vertices.get((int)face.vertex.y);
					GL11.glVertex3f(v2.x, v2.y, v2.z);
					//normal3
					Vector3f n3 = m.normals.get((int) face.vertex.z);
					GL11.glNormal3f(n3.x, n3.y, n3.z);
					//Color3
					Vector3f c3 = m.colors.get((int)face.vertex.z);
					GL11.glColor3f(c3.x, c3.y, c3.z);
					//Vertex3
					Vector3f v3 = m.vertices.get((int)face.vertex.z);
					GL11.glVertex3f(v3.x, v3.y, v3.z);
				}
				//System.out.println("worked");
			}
			GL11.glEnd();
			GL11.glBegin(GL11.GL_QUADS);
			for(Face face: m.faces)
			{
				if(face.normal.x != -1)
				{
					//Normal1
					Vector3f n1 = m.normals.get((int)face.vertex.x);
					GL11.glNormal3f(n1.x, n1.y, n1.z);
					//Color1
					Vector3f c1 = m.colors.get((int)face.vertex.x);
					GL11.glColor3f(c1.x, c1.y, c1.z);
					//Vertex1
					Vector3f v1 = m.vertices.get((int)face.vertex.x);
					GL11.glVertex3f(v1.x, v1.y, v1.z);
					//Normal2
					Vector3f n2 = m.normals.get((int)face.vertex.y);
					GL11.glNormal3f(n2.x, n2.y, n2.z);
					//Color2
					Vector3f c2 = m.colors.get((int)face.vertex.y);
					GL11.glColor3f(c2.x, c2.y, c2.z);
					//Vertex2
					Vector3f v2 = m.vertices.get((int)face.vertex.y);
					GL11.glVertex3f(v2.x, v2.y, v2.z);
					//normal3
					Vector3f n3 = m.normals.get((int) face.vertex.z);
					GL11.glNormal3f(n3.x, n3.y, n3.z);
					//Color3
					Vector3f c3 = m.colors.get((int)face.vertex.z);
					GL11.glColor3f(c3.x, c3.y, c3.z);
					//Vertex3
					Vector3f v3 = m.vertices.get((int)face.vertex.z);
					GL11.glVertex3f(v3.x, v3.y, v3.z);
					//Normal4
					Vector3f n4 = m.normals.get((int) face.normal.x);
					GL11.glNormal3f(n4.x, n4.y, n4.z);
					//Color4
					Vector3f c4 = m.colors.get((int)face.normal.x);
					GL11.glColor3f(c4.x, c4.y, c4.z);
					//Vertex4
					Vector3f v4 = m.vertices.get((int)face.normal.x);
					GL11.glVertex3f(v4.x, v4.y, v4.z);
				}
				//System.out.println("worked");
			}
			GL11.glEnd();
		}
		GL11.glEndList();
		//addModelBody(m,0.1f);
			//BvhTriangleMeshShape triMesh = new BvhTriangleMeshShape();
		*/	
		
		GL11.glPopAttrib();
	    addToStars();
	   // drawShadowMap();
		while(Display.isCloseRequested() == false)
		{
			if(Keyboard.isKeyDown(Keyboard.KEY_G))
			{
				ReturnThis r = new ReturnThis();
				r.v.x = -(float)(Math.cos(Math.toRadians(cameraXDir)) * Math.sin(Math.toRadians(cameraYDir))) * 20;
				r.v.y = (float)(Math.sin(Math.toRadians(cameraXDir))) * 20;
				r.v.z = -(float)(Math.cos(Math.toRadians(cameraXDir)) * Math.cos(Math.toRadians(cameraYDir))) * 20;
				dynamicsWorld.setGravity(r.v);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_H))
			{
				ReturnThis r = new ReturnThis();
				dynamicsWorld.setGravity(r.ReturnV(0, 0, 0));
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_Q))
			{
				lightX = -cameraX;
				lightY = -cameraY;
				lightZ = -cameraZ;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_U))
			{
				wait ++;
				if(wait >= 2)
				{
					wait = 0;
					createBullet(0,20,0);
				}
			}
			if(starsAdded < 10)
			{
				Mouse.setGrabbed(true);
				starsAdded = 20;
			}
			/**
			if(starsAdded < 10000)
			{
				
				//preRender();
				//addToStars();
				//Make3D();
				//goThroughList();
			}
			else
			{
				
				Make3D();
				goThroughList();
			}
			*/
			screenWidth = Display.getWidth();
			screenHeight = Display.getHeight();
			//Make3D();
			if(Mouse.isButtonDown(0))
			{
				if(mouseHasDown == 0)
				{
					mouseHasDown = 1;
				}
				else
				{
					mouseHasDown = 2;
				}
			}
			else
			{
				mouseHasDown = 0;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_CAPITAL) && mouseHasDown != 1)
			{
				
				dynamicsWorld.stepSimulation(1.0f / 420.0f,20);
			}
			else
			{
				
					dynamicsWorld.stepSimulation(1.0f / 60f);
				
			}
			//Make3D();
			//GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			//GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
			
			
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			Make3D();
			//GL11.glEnable(GL11.GL_CULL_FACE);
			
			//GL11.glClearDepth(1);
			
			//setUpStates();
			GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
			{
				//GL11.glEnable(GL11.GL_NORMALIZE);
				 FloatBuffer lowAmbient = BufferUtils.createFloatBuffer(4);
	                lowAmbient.put(new float[]{0.1F, 0.1F, 0.1F, 1.0F});
	                lowAmbient.flip();

	                FloatBuffer lowDiffuse = BufferUtils.createFloatBuffer(4);
	                lowDiffuse.put(new float[]{0.35F, 0.35F, 0.35F, 1.0F});
	                lowDiffuse.flip();

	                glLight(GL_LIGHT0, GL_AMBIENT, lowAmbient);
	                glLight(GL_LIGHT0, GL_DIFFUSE, lowDiffuse);
	                GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
	        		{
	        			GL11.glDisable(GL11.GL_LIGHTING);
	        			GL11.glBegin(GL11.GL_QUADS);
	        			GL11.glColor3f(0.3f, 0.6f, 0.3f);
	        			GL11.glVertex3f(-120, -19, -120);
	        			GL11.glVertex3f(-120, -19, +120);
	        			GL11.glVertex3f(+120, -19, +120);
	        			GL11.glVertex3f(+120, -19, -120);
	        			GL11.glEnd();
	        		}
	        		GL11.glPopAttrib();
	                render();

	                glAlphaFunc(GL_GREATER, 0.9F);
	                glEnable(GL_ALPHA_TEST);
				generateTextureCoordinates();
				GL11.glCullFace(GL11.GL_BACK);
				//GL11.glCullFace(GL11.GL_BACK);
				/**
				GL11.glBegin(GL11.GL_QUADS);
				GL11.glColor4f(0.6f, 0.6f, 0.6f, 1);
				GL11.glVertex3f(-50, -29.9f, -50);
				GL11.glColor4f(0.85f,0.85f,0.85f,1);
				GL11.glVertex3f(-50, -29.9f, 50);
				GL11.glColor4f(0.75f,0.75f,0.75f,1);
				GL11.glVertex3f(50, -29.9f, 50);
				GL11.glColor4f(0.5f,0.5f,0.5f,1);
				GL11.glVertex3f(50, -29.9f, -50);
				GL11.glEnd();
				GL11.glBegin(GL11.GL_QUADS);
				GL11.glColor4f(0.6f, 0.6f, 0.6f, 1);
				GL11.glVertex3f(-50, -29.9f, -50);
				GL11.glColor4f(0.85f,0.85f,0.85f,1);
				GL11.glVertex3f(50, -29.9f, -50);
				GL11.glColor4f(0.75f,0.75f,0.75f,1);
				GL11.glVertex3f(50, 60f, -50);
				GL11.glColor4f(0.5f,0.5f,0.5f,1);
				GL11.glVertex3f(-50, 60f, -50);
				GL11.glEnd();
				GL11.glBegin(GL11.GL_QUADS);
				GL11.glColor4f(0.6f, 0.6f, 0.6f, 1);
				GL11.glVertex3f(50, -29.9f, 50);
				GL11.glColor4f(0.85f,0.85f,0.85f,1);
				GL11.glVertex3f(-50, -29.9f, 50);
				GL11.glColor4f(0.75f,0.75f,0.75f,1);
				GL11.glVertex3f(-50, 60f, 50);
				GL11.glColor4f(0.5f,0.5f,0.5f,1);
				GL11.glVertex3f(50, 60f, 50);
				GL11.glEnd();
				GL11.glBegin(GL11.GL_QUADS);
				GL11.glColor4f(0.6f, 0.6f, 0.6f, 1);
				GL11.glVertex3f(50, -29.9f, -50);
				GL11.glColor4f(0.85f,0.85f,0.85f,1);
				GL11.glVertex3f(50, -29.9f, 50);
				GL11.glColor4f(0.75f,0.75f,0.75f,1);
				GL11.glVertex3f(50, 60f, 50);
				GL11.glColor4f(0.5f,0.5f,0.5f,1);
				GL11.glVertex3f(50, 60f, -50);
				GL11.glEnd();
				GL11.glBegin(GL11.GL_QUADS);
				GL11.glColor4f(0.6f, 0.6f, 0.6f, 1);
				GL11.glVertex3f(-50, -29.9f, 50);
				GL11.glColor4f(0.85f,0.85f,0.85f,1);
				GL11.glVertex3f(-50, -29.9f, -50);
				GL11.glColor4f(0.75f,0.75f,0.75f,1);
				GL11.glVertex3f(-50, 60f, -50);
				GL11.glColor4f(0.5f,0.5f,0.5f,1);
				GL11.glVertex3f(-50, 60f, 50);
				GL11.glEnd();
				*/
				
				RenderStars();
				
	                
	                render();
	                glDisable(GL_ALPHA_TEST);
		            glDisable(GL_TEXTURE_2D);
		            glDisable(GL_TEXTURE_GEN_S);
		            glDisable(GL_TEXTURE_GEN_T);
		            glDisable(GL_TEXTURE_GEN_R);
		            glDisable(GL_TEXTURE_GEN_Q);
				/**
				Transform trans = new Transform();
			    float[] matrix = new float[16];
			
			    trans = controlBall.getMotionState().getWorldTransform(trans);
		        
		        trans.getOpenGLMatrix(matrix);
		        // pass that matrix to OpenGL and render the cube
		        FloatBuffer buffer = ByteBuffer.allocateDirect(4*16).order(ByteOrder.nativeOrder()).asFloatBuffer().put(matrix);
		        buffer.rewind();
		        GL11.glPushMatrix();
		        GL11.glMultMatrix(buffer);
		        //cly.setDrawStyle(GLU.GLU_SILHOUETTE);
				//GL11.glColor4f(0, 1, 1, 1);
					GL11.glTranslatef(0, -2.5f, 0);
					*/
					
				
		        //GL11.glPopMatrix();
				
				GL11.glCullFace(GL11.GL_FRONT);
				//drawShadowMap();
				
				
			}
			GL11.glPopAttrib();
			
			//logic();
			
			
			keyControl();
			
			
			//Make3D();
			//keyControl();
			//render();
			Display.update();
			Display.sync(60);
			if(paused == false)
			{
				MouseControl();
			}
			if(Mouse.isButtonDown(0) == true)
			{
				if(mouseDown == false)
				{
					mouseDown = true;
					createBullet(-cameraX,-cameraY,-cameraZ);
				}
			}
			else
			{
				mouseDown = false;
			}
			
		}
		cleanUp();
		Display.destroy();
		System.exit(0);
		Mouse.setGrabbed(false);
	}
	public void generateTextureCoordinates()
	{
		GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_MODULATE);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL14.GL_TEXTURE_COMPARE_MODE, GL14.GL_COMPARE_R_TO_TEXTURE);
		GL11.glEnable(GL11.GL_TEXTURE_GEN_S);
		GL11.glEnable(GL11.GL_TEXTURE_GEN_T);
		GL11.glEnable(GL11.GL_TEXTURE_GEN_R);
		GL11.glEnable(GL11.GL_TEXTURE_GEN_Q);
		textureBuffer.clear();
		textureBuffer.put(0,depthModelViewProjection.m00);
		textureBuffer.put(1,depthModelViewProjection.m01);
		textureBuffer.put(2,depthModelViewProjection.m02);
		textureBuffer.put(3,depthModelViewProjection.m03);
		GL11.glTexGen(GL11.GL_S, GL11.GL_EYE_PLANE, textureBuffer);
		textureBuffer.put(0,depthModelViewProjection.m10);
		textureBuffer.put(1,depthModelViewProjection.m11);
		textureBuffer.put(2,depthModelViewProjection.m12);
		textureBuffer.put(3,depthModelViewProjection.m13);
		GL11.glTexGen(GL11.GL_T, GL11.GL_EYE_PLANE, textureBuffer);
		textureBuffer.put(0,depthModelViewProjection.m20);
		textureBuffer.put(1,depthModelViewProjection.m21);
		textureBuffer.put(2,depthModelViewProjection.m22);
		textureBuffer.put(3,depthModelViewProjection.m23);
		GL11.glTexGen(GL11.GL_R, GL11.GL_EYE_PLANE, textureBuffer);
		textureBuffer.put(0,depthModelViewProjection.m30);
		textureBuffer.put(1,depthModelViewProjection.m31);
		textureBuffer.put(2,depthModelViewProjection.m32);
		textureBuffer.put(3,depthModelViewProjection.m33);
		GL11.glTexGen(GL11.GL_Q, GL11.GL_EYE_PLANE, textureBuffer);
		
	}
	void setUpBufferValues() {
	        ambientLight.put(new float[]{0.2F, 0.2F, 0.2F, 1.0F});
	        ambientLight.flip();

	        diffuseLight.put(new float[]{0.7F, 0.7F, 0.7F, 1.0F});
	        diffuseLight.flip();

	        cameraPosition.put(new float[]{100.0F, 50.0F, 200.0F, 1.0F});
	        cameraPosition.flip();

	        lightPosition.put(new float[]{100.0F, 300.0F, 100.0F, 1.0F});
	        lightPosition.flip();
	    }
	public void drawShadowMap()
	{
		 float lightToSceneDistance, nearPlane, fieldOfView;
	        FloatBuffer lightModelView = BufferUtils.createFloatBuffer(16);
	        FloatBuffer lightProjection = BufferUtils.createFloatBuffer(16);
	        Matrix4f lightProjectionTemp = new Matrix4f();
	        Matrix4f lightModelViewTemp = new Matrix4f();

	        float sceneBoundingRadius = 95.0F;

	        lightToSceneDistance = (float) Math.sqrt(lightPosition.get(0) * lightPosition.get(0) + lightPosition.get(1) *
	                lightPosition.get(1) + lightPosition.get(2) * lightPosition.get(2));

	        nearPlane = lightToSceneDistance - sceneBoundingRadius;

	        fieldOfView = (float) Math.toDegrees(2.0F * Math.atan(sceneBoundingRadius / lightToSceneDistance));

	        glMatrixMode(GL_PROJECTION);
	        glLoadIdentity();
	        gluPerspective(fieldOfView, 1.0F, nearPlane, nearPlane + (2.0F * sceneBoundingRadius));
	        glGetFloat(GL_PROJECTION_MATRIX, lightProjection);
	        glMatrixMode(GL_MODELVIEW);
	        glLoadIdentity();
	        gluLookAt(lightPosition.get(0), lightPosition.get(1), lightPosition.get(2), 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F);
	        glGetFloat(GL_MODELVIEW_MATRIX, lightModelView);
	        glViewport(0, 0, shadowWidth, shadowHeight);

	        if (useFBO) {
	            glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, frameBuffer);
	        }

	        glClear(GL_DEPTH_BUFFER_BIT);

	        // Set rendering states to the minimum required, for speed.
	        glShadeModel(GL_FLAT);
	        glDisable(GL_LIGHTING);
	        glDisable(GL_COLOR_MATERIAL);
	        glDisable(GL_NORMALIZE);
	        glColorMask(false, false, false, false);

	        glEnable(GL_POLYGON_OFFSET_FILL);

	        render();

	        glCopyTexImage2D(GL_TEXTURE_2D, 0, GL_DEPTH_COMPONENT, 0, 0, shadowWidth, shadowHeight, 0);

	        // Unbind the framebuffer if we are using them.
	        if (useFBO) {
	            glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, 0);
	        }

	        // Setup the rendering states.
	        glShadeModel(GL_SMOOTH);
	        glEnable(GL_LIGHTING);
	        glEnable(GL_COLOR_MATERIAL);
	        glEnable(GL_NORMALIZE);
	        glColorMask(true, true, true, true);
	        glDisable(GL_POLYGON_OFFSET_FILL);

	        lightProjectionTemp.load(lightProjection);
	        lightModelViewTemp.load(lightModelView);
	        lightProjection.flip();
	        lightModelView.flip();

	        Matrix4f tempMatrix = new Matrix4f();
	        tempMatrix.setIdentity();
	        tempMatrix.translate(new Vector3f(0.5F, 0.5F, 0.5F));
	        tempMatrix.scale(new Vector3f(0.5F, 0.5F, 0.5F));
	        Matrix4f.mul(tempMatrix, lightProjectionTemp, textureMatrix);
	        Matrix4f.mul(textureMatrix, lightModelViewTemp, tempMatrix);
	        Matrix4f.transpose(tempMatrix, textureMatrix);
			
		
	}
	public void cleanUp()
	{
		glDeleteFramebuffers(frameBuffer);
		glDeleteRenderbuffers(renderBuffer);
	}
	public void addBox(float x, float y, float z,float mass)
	{
		ReturnThis r =  new ReturnThis();
		
		BoxShape shape = new BoxShape(r.ReturnV(1f, 1f, 1f));
		//CollisionShape tri = new BvhTriangleMeshShape();
		//tri.getAabb(, arg1, arg2);
		r.returnMaxWith(new Quat4f(0,0,0,1), r.ReturnV(x, y, z),1f);
		DefaultMotionState motionState = new DefaultMotionState(new Transform(r.max));
		r.makeV(0, 0, 0);
		shape.calculateLocalInertia(1.0f, r.v);
		//System.out.println(inertia.x + " " + inertia.y + " " + inertia.z);
		RigidBodyConstructionInfo constructionInfo = new RigidBodyConstructionInfo(1.0f,motionState,shape,r.v);
		constructionInfo.restitution = 0.1f;
		constructionInfo.mass = mass;
		constructionInfo.friction = 0.9f;
		RigidBody body = new RigidBody(constructionInfo);
		dynamicsWorld.addRigidBody(body);
		Boxs.add(body);
	}
	public void createBullet(float x, float y, float z)
	{
		ReturnThis r = new ReturnThis();
		CollisionShape shape = new BoxShape(r.ReturnV(1f, 1f, 1f));
		
		r.returnMaxWith(new Quat4f(0,0,0,1), r.ReturnV(x,y,z),1f);
		DefaultMotionState motionState = new DefaultMotionState(new Transform(r.max));
		r.makeV(0,0,0);
		shape.calculateLocalInertia(0.9f, r.v);
		RigidBodyConstructionInfo constructionInfo = new RigidBodyConstructionInfo(1.0f,motionState,shape,r.v);
		constructionInfo.restitution = 0.1f;
		constructionInfo.mass = 0.1f;
		constructionInfo.friction = 0.9f;
		RigidBody body = new RigidBody(constructionInfo);
		//body.setActivationState(CollisionObject.DISABLE_DEACTIVATION);
		float forcex = (float)(Math.cos(Math.toRadians(cameraXDir)) * Math.sin(Math.toRadians(cameraYDir)) * -3000);
		float forcey = (float)(Math.sin(Math.toRadians(cameraXDir)) * 3000);
		float forcez = (float)(Math.cos(Math.toRadians(cameraXDir)) * Math.cos(Math.toRadians(cameraYDir)) * -3000);
		if(Keyboard.isKeyDown(Keyboard.KEY_E))
		{
			forcex = (float)(Math.cos(Math.toRadians(cameraXDir)) * Math.sin(Math.toRadians(cameraYDir)) * -1000);
			forcey = (float)(Math.sin(Math.toRadians(cameraXDir)) * 1000);
			forcez = (float)(Math.cos(Math.toRadians(cameraXDir)) * Math.cos(Math.toRadians(cameraYDir)) * -1000);
			body.applyCentralForce(r.ReturnV(forcex, forcey, forcez));
			r.makeV( (float)(Math.random() * 800) - 400, (float)(Math.random() * 800) - 400,  (float)(Math.random() * 800) - 400);
			body.applyTorque(r.v);
		}
		
		
		dynamicsWorld.addRigidBody(body);
		bullets.add(body);
		
		
		
	}
	private static void logic()
	{
		//GL11.glLoadIdentity();
		
		
		Set<RigidBody> ballsToBeRemoved = new HashSet<RigidBody>();
		
		for (RigidBody body : balls) {
			ReturnThis r = new ReturnThis();
			r.v = body.getMotionState().getWorldTransform(new Transform()).origin;
			if (!body.equals(controlBall) && r.v.x < -50 || r.v.x > 50 || r.v.z < -50 || r.v.z > 50)
			{
				ballsToBeRemoved.add(body);
			}
		}
		
		for (RigidBody body: ballsToBeRemoved)
		{
			balls.remove(body);
			dynamicsWorld.removeRigidBody(body);
		}
		ballsToBeRemoved = new HashSet<RigidBody>();
		for (RigidBody body : bullets) {
			ReturnThis r = new ReturnThis();
			r.v = body.getMotionState().getWorldTransform(new Transform()).origin;
			if (!body.equals(controlBall) && r.v.x < -50 || r.v.x > 50 || r.v.z < -50 || r.v.z > 50)
			{
				ballsToBeRemoved.add(body);
			}
		}
		for (RigidBody body: ballsToBeRemoved)
		{
			bullets.remove(body);
			dynamicsWorld.removeRigidBody(body);
		}
		
	}
	
	public void drawCyl()
	{
	
	}
	public void render()
	{
		
		
		for(RigidBody body : balls)
		{
			
			
			
			Transform trans = new Transform();
		    float[] matrix = new float[16];
		
		    trans = body.getMotionState().getWorldTransform(trans);
		 	ReturnThis r = new ReturnThis();
		 	r.v = body.getWorldTransform(new Transform()).origin;
            trans.getOpenGLMatrix(matrix);
            // pass that matrix to OpenGL and render the cube
            FloatBuffer buffer = ByteBuffer.allocateDirect(4*16).order(ByteOrder.nativeOrder()).asFloatBuffer().put(matrix);
            buffer.rewind();
            GL11.glPushMatrix();
            GL11.glMultMatrix(buffer);
            drawBox(0,0,0,1);
            //GL11.glTranslated(-0.6, 0, 0);
            //sphere.draw(2, 2, 5);
           // cly.draw(0.4f, 0.2f, 2, 10, 10);
            //cly.setDrawStyle(GLU.GLU_SILHOUETTE);
			GL11.glColor4f(1, 0, 0, 1);
			
				
			
            GL11.glPopMatrix();
            /**
           double dis = Distance(topX,topY,topZ,r.v.x,r.v.y,r.v.z);
           
            if(dis >= disRadius && mode == 0)
            {
            	ReturnThis j = new ReturnThis();
            	j.v.x = topX - r.v.x;
            	j.v.y = topY - r.v.y;
            	j.v.z = topZ - r.v.z;
            	j.v.normalize();
            	j.v.x = j.v.x * 100;
            	j.v.y = j.v.y * 100;
            	j.v.z = j.v.z * 100;
            	body.applyCentralForce(j.v);
            }
            */
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_3))
		{
			topX = -cameraX;
			topY = -cameraY;
			topZ = -cameraZ;
		}
		GL11.glPushMatrix();
		GL11.glTranslatef(topX, topY, topZ);
		//cly.draw(2, 2, 2, 20, 2);
		sphere.draw(disRadius,10,10);
		
		GL11.glPopMatrix();
		
	
		for(RigidBody body : bullets)
		{
			Transform trans = new Transform();
		    float[] matrix = new float[16];
		
		    trans = body.getMotionState().getWorldTransform(trans);
            
            trans.getOpenGLMatrix(matrix);
            // pass that matrix to OpenGL and render the cube
            FloatBuffer buffer = ByteBuffer.allocateDirect(4*16).order(ByteOrder.nativeOrder()).asFloatBuffer().put(matrix);
            buffer.rewind();
            GL11.glPushMatrix();
            GL11.glMultMatrix(buffer);
			//GL11.glPushMatrix();
			//Vector3f ballPosition = body.getWorldTransform(new Transform()).origin;
			//GL11.glTranslatef(ballPosition.x,ballPosition.y,ballPosition.z);
			
			
			GL11.glColor4f(0f, 0.7f, 1, 1);
			//sphere.setDrawStyle(GLU.GLU_FILL);
			//GL11.glRotatef(90, 0, 0, 1);
			//cly.draw(2, 2, 2, 20, 2);
			//GL11.glBegin(GL11.GL_QUADS);
			
			
			
			drawBox(0,0,0,1f);
			//sphere.draw(1.5f,2,20);
			GL11.glPopMatrix();
			
			ReturnThis r = new ReturnThis();
		 	r.v = body.getWorldTransform(new Transform()).origin;
			double dis = Distance(0,20,0,r.v.x,r.v.y,r.v.z);
            if(dis >= disRadius && moveStrength >= 0)
            {
            	ReturnThis j = new ReturnThis();
            	j.v.x = 0 - r.v.x;
            	j.v.y = 20 - r.v.y;
            	j.v.z = 0 - r.v.z;
            	j.v.normalize();
            	j.v.x = j.v.x * moveStrength;
            	j.v.y = j.v.y * moveStrength;
            	j.v.z = j.v.z * moveStrength;
            	body.applyCentralForce(j.v);
            }
		}
		//GL11.glDisable(GL11.GL_CULL_FACE);
		//cly.setDrawStyle(GLU.GLU_FILL);
		for(RigidBody body: Boxs)
		{
			
			  Transform trans = new Transform();
			    float[] matrix = new float[16];
			
			
			    trans = body.getMotionState().getWorldTransform(trans);
			 	ReturnThis r = new ReturnThis();
			 	r.v = body.getWorldTransform(new Transform()).origin;
	            trans.getOpenGLMatrix(matrix);
	            // pass that matrix to OpenGL and render the cube
	            FloatBuffer buffer = ByteBuffer.allocateDirect(4*16).order(ByteOrder.nativeOrder()).asFloatBuffer().put(matrix);
	            buffer.rewind();
	           GL11.glPushMatrix();
	            GL11.glMultMatrix(buffer);
	            //sphere.draw(1f,10,10);
	            //GL11.glRotated(45, 0, 0, 1);
	            //sphere.draw(1, 2, 4);
	            GL11.glColor3f(0.4f,0.4f,0.4f);
	            drawBox(0,0,0,1f);
	            //body.setMassProps(mass, inertia);
	            //cly.draw(2, 2, 5, 40, 2);
	            GL11.glPopMatrix();
	            double dis = Distance(0,20,0,r.v.x,r.v.y,r.v.z);
	            if(dis >= disRadius)
	            {
	            	ReturnThis j = new ReturnThis();
	            	j.v.x = 0 - r.v.x;
	            	j.v.y = 20 - r.v.y;
	            	j.v.z = 0 - r.v.z;
	            	j.v.normalize();
	            	j.v.x = j.v.x * moveStrength;
	            	j.v.y = j.v.y * moveStrength;
	            	j.v.z = j.v.z * moveStrength;
	            	body.applyCentralForce(j.v);
	            }
	            
	           
	            
		}
		
        GL11.glPushMatrix();
        //cly.setDrawStyle(GLU.GLU_SILHOUETTE);
		GL11.glColor4f(0, 1, 1, 1);
		
			sphere.setDrawStyle(GLU.GLU_FILL);
			GL11.glTranslatef(0, 20, 0);
			//cly.draw(2, 2, 2, 20, 2);
			sphere.draw(disRadius,10,10);
		
        GL11.glPopMatrix();
		//GL11.glEnable(GL11.GL_CULL_FACE);
		//GL11.glCullFace(GL11.GL_FRONT);
		//drawShadowMap();
		//GL11.glEnable(GL11.GL_CULL_FACE);
		
	}
	public void drawBox(float x, float y, float z, float radius)
	{
		
		//x = 0;
		//y = 0;
		//z = 0;
		
		GL11.glBegin(GL11.GL_QUADS);
			
			
			
	            GL11.glNormal3f(0, 1, 0);
	            GL11.glVertex3f(x + radius, y + radius, z - radius);
	            GL11.glVertex3f(x - radius, y + radius, z - radius);
	            GL11.glVertex3f(x - radius, y + radius, z + radius);
	            GL11.glVertex3f(x + radius, y + radius, z + radius);
			
	            GL11.glNormal3f(0, -1f, 0);
	            GL11.glVertex3f(x - radius, y - radius, z + radius);
	            GL11.glNormal3f(0, -1f, 0);
	            GL11.glVertex3f(x - radius, y - radius, z - radius);
	            GL11.glNormal3f(0, -1f, 0);
	            GL11.glVertex3f(x + radius, y - radius, z - radius);
	            GL11.glNormal3f(0, -1f, 0);
	            GL11.glVertex3f(x + radius, y - radius, z + radius);
	         
            //GL11.glColor3f(5.0f,1.0f,5.0f); // white
	           GL11.glEnd();
	           GL11.glBegin(GL11.GL_QUADS);
				//GL11.glColor3f(1.0f,1.0f,1.0f);
				GL11.glNormal3f(1, 0, 0);
	            GL11.glVertex3f(x + radius, y - radius, z + radius);
	            GL11.glVertex3f(x + radius, y - radius, z - radius);
	            GL11.glVertex3f(x + radius, y + radius, z - radius);
	            GL11.glVertex3f(x + radius, y + radius, z + radius);
	            
	            GL11.glNormal3f(-1f, 0, 0);
	            GL11.glVertex3f(x - radius, y + radius, z - radius);
	            GL11.glNormal3f(-1f, 0, 0);
	            GL11.glVertex3f(x - radius, y - radius, z - radius);
	            GL11.glNormal3f(-1f, 0, 0);
	            GL11.glVertex3f(x - radius, y - radius, z + radius);
	            GL11.glNormal3f(-1f, 0, 0);
	            GL11.glVertex3f(x - radius, y + radius, z + radius);
	            
			
           // GL11.glColor3f(5.0f,1.0f,5.0f); // white
			
	            
	            GL11.glEnd();
	            GL11.glBegin(GL11.GL_QUADS);
	            GL11.glNormal3f(0, 0, 1);
			
           // GL11.glColor3f(5.0f,1.0f,5.0f); // white
			
	            GL11.glVertex3f(x - radius, y + radius, z + radius);
	            GL11.glVertex3f(x - radius, y - radius, z + radius);
	            GL11.glVertex3f(x + radius, y - radius, z + radius);
	            GL11.glVertex3f(x + radius, y + radius, z + radius);
	            GL11.glNormal3f(0, 0, -1);
			
           // GL11.glColor3f(5.0f,1.0f,5.0f); // white
			
	            GL11.glVertex3f(x + radius, y - radius, z - radius);
	            GL11.glVertex3f(x - radius, y - radius, z - radius);
	            GL11.glVertex3f(x - radius, y + radius, z - radius);
	            GL11.glVertex3f(x + radius, y + radius, z - radius);
			
	            GL11.glEnd();
	           
		
		
	}
	
	public double Distance(float x, float y, float z, float x2, float y2, float z2)
	{
		return Math.sqrt(((x - x2) * (x - x2)) + ((y - y2) * (y - y2)) + ((z - z2) * (z - z2)));
	}
	
	public void MouseControl()
	{
		cameraYDir = cameraYDir - ((Mouse.getX() - (screenWidth / 2)) * 0.2f);
		cameraXDir = cameraXDir + ((Mouse.getY() - (screenHeight / 2)) * 0.2f);
		Mouse.setCursorPosition(screenWidth / 2, screenHeight / 2);
		if(cameraXDir > 90)
		{
			cameraXDir = 90;
		}
		if(cameraXDir < -90)
		{
			cameraXDir = -90;
		}
	}
	public void keyControl()
	{
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
		{
			moveStrength = moveStrength + 0.3f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
		{
			moveStrength = moveStrength - 0.3f;
		}
		if(moveStrength < -0.5)
		{
			moveStrength= -0.5f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_UP))
		{
			disRadius = disRadius + 0.1f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
		{
			disRadius = disRadius - 0.1f;
		}
		if(disRadius < 1)
		{
			disRadius = 1;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_R))
		{
			if(keySwitch == false)
			{
				keySwitch = true;
				if(mode == 0)
				{
					mode = 1;
				}
				else
				{
					mode = 0;
				}
			}
		}
		else
		{
			keySwitch = false;
		}
		if(mode == 1)
		{
			//Transform trans = new Transform();
		    //float[] matrix = new float[16];
		
		
		   // trans = controlBall.getMotionState().getWorldTransform(trans);
		 	ReturnThis r = new ReturnThis();
		 	r.v = controlBall.getWorldTransform(new Transform()).origin;
		 	cameraX = -r.v.x;
		 	cameraY = -r.v.y;
		 	cameraZ = -r.v.z;
		}
		float moveSpeed = 0.8f;
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
		{
			moveSpeed = 1.5f;
		}
		ReturnThis t = new ReturnThis();
		t.v.y = 60;
		//r.v.x = 50;
		controlBall.setAngularVelocity(t.v);
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
		{
			if(escapeKeyPushed == false)
			{
				escapeKeyPushed = true;
				if(paused == false)
				{
					paused = true;
					Mouse.setGrabbed(false);
				}
				else
				{
					paused = false;
					Mouse.setCursorPosition(screenWidth / 2, screenHeight / 2);
					Mouse.setGrabbed(true);
				}
			}
		}
		else
		{
			escapeKeyPushed = false;
		}
		if(mode == 0)
		{
			if(Keyboard.isKeyDown(Keyboard.KEY_W))
			{
				cameraX = cameraX + (float)(Math.cos(Math.toRadians(cameraXDir)) * Math.sin(Math.toRadians(cameraYDir)) * moveSpeed);
				cameraY = cameraY - (float)(Math.sin(Math.toRadians(cameraXDir)) * moveSpeed);
				cameraZ = cameraZ + (float)(Math.cos(Math.toRadians(cameraXDir)) * Math.cos(Math.toRadians(cameraYDir)) * moveSpeed);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_S))
			{
				cameraX = cameraX - (float)(Math.cos(Math.toRadians(cameraXDir)) * Math.sin(Math.toRadians(cameraYDir)) * moveSpeed);
				cameraY = cameraY + (float)(Math.sin(Math.toRadians(cameraXDir)) * moveSpeed);
				cameraZ = cameraZ - (float)(Math.cos(Math.toRadians(cameraXDir)) * Math.cos(Math.toRadians(cameraYDir)) * moveSpeed);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_D))
			{
				cameraX = cameraX - (float)(Math.cos(Math.toRadians(cameraXDir)) * Math.sin(Math.toRadians(cameraYDir + 90)) * moveSpeed);
				cameraZ = cameraZ - (float)(Math.cos(Math.toRadians(cameraXDir)) * Math.cos(Math.toRadians(cameraYDir + 90)) * moveSpeed);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_A))
			{
				cameraX = cameraX + (float)(Math.cos(Math.toRadians(cameraXDir)) * Math.sin(Math.toRadians(cameraYDir + 90)) * moveSpeed);
				cameraZ = cameraZ + (float)(Math.cos(Math.toRadians(cameraXDir)) * Math.cos(Math.toRadians(cameraYDir + 90)) * moveSpeed);
			}
		}
		else
		{
			
				
			
			if(Keyboard.isKeyDown(Keyboard.KEY_W))
			{
				ReturnThis r = new ReturnThis();
				r.v.x =  -(float)(Math.sin(Math.toRadians(cameraYDir)) * moveSpeed) * 60;
				//r.v.y =  (float)(Math.sin(Math.toRadians(cameraXDir)) * moveSpeed) * 3000;
				r.v.z =  -(float)(Math.cos(Math.toRadians(cameraYDir)) * moveSpeed) * 60;
				//r.v.x = 50;
				ReturnThis p = new ReturnThis();
				controlBall.applyForce(r.v, p.ReturnV(0, -0.5f, 0));
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_S))
			{
				ReturnThis r = new ReturnThis();
				r.v.x =  (float)(Math.sin(Math.toRadians(cameraYDir)) * moveSpeed) * 60;
				//r.v.y =  (float)(Math.sin(Math.toRadians(cameraXDir)) * moveSpeed) * 3000;
				r.v.z =  (float)(Math.cos(Math.toRadians(cameraYDir)) * moveSpeed) * 60;
				//r.v.x = 50;
				ReturnThis p = new ReturnThis();
				controlBall.applyForce(r.v, p.ReturnV(0, -0.5f, 0));
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_D))
			{
				ReturnThis r = new ReturnThis();
				r.v.x =  (float)(Math.sin(Math.toRadians(cameraYDir + 90)) * moveSpeed) * 60;
				//r.v.y =  (float)(Math.sin(Math.toRadians(cameraXDir)) * moveSpeed) * 3000;
				r.v.z =  (float)(Math.cos(Math.toRadians(cameraYDir + 90)) * moveSpeed) * 60;
				//r.v.x = 50;
				ReturnThis p = new ReturnThis();
				controlBall.applyForce(r.v, p.ReturnV(0, -0.5f, 0));
				 
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_A))
			{
				ReturnThis r = new ReturnThis();
				r.v.x =  -(float)(Math.sin(Math.toRadians(cameraYDir + 90)) * moveSpeed) * 60;
				//r.v.y =  (float)(Math.sin(Math.toRadians(cameraXDir)) * moveSpeed) * 3000;
				r.v.z =  -(float)(Math.cos(Math.toRadians(cameraYDir + 90)) * moveSpeed) * 60;
				//r.v.x = 50;
				ReturnThis p = new ReturnThis();
				controlBall.applyForce(r.v, p.ReturnV(0, -0.5f, 0));
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
			{
				/**
				ReturnThis r = new ReturnThis();
				r.v.y = 300;
				controlBall.applyCentralForce(r.v);
				*/
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_Z))
		{
			
			ReturnThis r = new ReturnThis();
			ReturnThis v = new ReturnThis();
			controlBall.getAngularVelocity(v.v);
			r.makeV(v.v.x * 0.95f, v.v.y * 0.95f, v.v.z * 0.95f);
			controlBall.setAngularVelocity(r.v);
			controlBall.getLinearVelocity(v.v);
			r.makeV(v.v.x * 0.95f, v.v.y * 0.95f, v.v.z * 0.95f);
			controlBall.setLinearVelocity(r.v);
			
		}
		
		//controlBall.getWorldTransform()
		
	}
	
	public void addToStars()
	{
		GL11.glPointSize(2);
		for(int i = 0; i < 1000; i ++)
		{
			Star d = new Star();
			d.x = (float)((Math.random() * 100000) - 50000);
			d.y = (float)((Math.random() * 100000) - 50000);
			d.z = (float)((Math.random() * 100000) - 50000);
			Stars.add(d);
			starsAdded ++;
		}
	}
	public void goThroughList()
	{
		GL11.glPointSize(2);
		for(int i = 0; i < Stars.size(); i ++)
		{
			Star d = Stars.get(i);
			draw(d);
		}
	}
	public void draw(Star d)
	{
		GL11.glBegin(GL11.GL_POINTS);
		GL11.glColor4d(1, 1, 1,1);
		GL11.glVertex3f(d.x,d.y,d.z);
		GL11.glEnd();
	}
	public void Make3D()
	{
			GL11.glEnable(GL11.GL_BLEND);
				GL11.glMatrixMode(GL11.GL_PROJECTION);
				GL11.glLoadIdentity(); // Reset The Projection Matrix
				GL11.glEnable(GL11.GL_CULL_FACE);
				GL11.glCullFace(GL11.GL_BACK);
				
				gluPerspective(FOV, ((float) screenWidth / (float) screenHeight), 0.1f, renderDistance);
				GL11.glViewport(0, 0, screenWidth,screenHeight);
				//GL11.glOrtho(0, right, bottom, top, zNear, zFar);
				GL11.glMatrixMode(GL11.GL_MODELVIEW);
				GL11.glEnable(GL11.GL_DEPTH_TEST);
				GL11.glEnable(GL11.GL_COLOR_MATERIAL);
				FloatBuffer fogColor = BufferUtils.createFloatBuffer(4);
				
						fogColor.put((float)0).put((float)(0)).put((float)0).put(1.0f).flip();
				
						
		        //GL11.glFogi(GL11.GL_FOG_MODE, fogMode[fogfilter]);          // Fog Mode
		        //temp.asFloatBuffer().put(fogColor).flip();
				GL11.glFog(GL11.GL_FOG_COLOR, fogColor);    
		        GL11.glHint(GL11.GL_FOG_HINT, GL11.GL_DONT_CARE);
				GL11.glLoadIdentity();
				GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
				//cameraZDir = cameraZDir + 1;
				
				
				
					//GL11.glRotated(cameraYDir,0 ,-1,0);
					//GL11.glRotated(cameraXDir,1,0,0);
					//GL11.glRotated(cameraZDir,0 ,0,1);
					
					if(mode == 1)
					{
						GL11.glTranslatef(0,0,-30);
					}
					GL11.glRotated(cameraYDir,0 ,-1,0);
					//GL11.glRotated(cameraZDir,(float)( -Math.sin(Math.toRadians(cameraYDir))),0, (Math.cos(Math.toRadians(cameraYDir))));
					GL11.glRotated(cameraXDir,(float)( -Math.cos(Math.toRadians(cameraYDir))),0, (Math.sin(Math.toRadians(cameraYDir))));
					
					
					GL11.glTranslated(cameraX,cameraY,cameraZ);
				
				
				
				
				//GL11.glRotated(cameraZDir,(float)( -Math.sin(Math.toRadians(cameraYDir))),0, (Math.cos(Math.toRadians(cameraYDir))));
				
				
					
					//GL11.glDisable(GL11.GL_CULL_FACE);
					
		        // Fog End Depth
		        //GL11.glEnable(GL11.GL_FOG);
		        GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_LINEAR);
		       
			        GL11.glFogf(GL11.GL_FOG_START, renderDistance / 1.4f);
			        // Fog Start Depth
			        GL11.glFogf(GL11.GL_FOG_END, renderDistance / 1.01f);
		        
		        	GL11.glClearColor(0.0f,0.0f,0f, 1.0f);
		        
		       
		        
				//GL11.glShadeModel(GL11.GL_SMOOTH);
				 	GL11.glEnable(GL11.GL_LIGHTING);
			        GL11.glEnable(GL11.GL_LIGHT0);
			        
			       // GL11.glEnable(GL11.GL_COLOR_MATERIAL);
			        //GL11.glColorMaterial(GL11.GL_FRONT_AND_BACK, GL11.GL_AMBIENT_AND_DIFFUSE);
			        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, floatBuffer((float)(0.6),(float)0.6,(float)0.6, 1.0f));
			        GL11.glLight(GL11.GL_LIGHT0,GL11.GL_POSITION,floatBuffer((float)lightX,(float)lightY,(float)lightZ,1));
			        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, floatBuffer((float)(0.2),(float)0.2,(float)0.2,1));
			       // GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, floatBuffer(0.5f, 0.5f, 0.5f, 1.0f));
			        //GL11.glLight(GL11.GL_LIGHT0, GL11.GL_EMISSION, floatBuffer(1f, 1f, 1f, 1.0f));
			        GL11.glShadeModel(GL11.GL_SMOOTH);
			        //GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, floatBuffer(1.0f, 1.0f, 1.0f, 1.0f));
			        //enable light 1
			        /**
			        GL11.glEnable(GL11.GL_LIGHT1);
			        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
			        GL11.glColorMaterial(GL11.GL_FRONT_AND_BACK, GL11.GL_AMBIENT_AND_DIFFUSE);
			        GL11.glLight(GL11.GL_LIGHT1, GL11.GL_DIFFUSE, floatBuffer(0.0f, 0.0f, 1.0f, 1.0f));
			        GL11.glLight(GL11.GL_LIGHT1,GL11.GL_POSITION,floatBuffer(0,10,0,1));
			        GL11.glLight(GL11.GL_LIGHT1, GL11.GL_SPECULAR, floatBuffer(1.0f, 1.0f, 1.0f, 1.0f));
			        */
			       // GL11.GL_
			        //GL11.glLight(GL11.GL_LIGHT0, GL11.GL_INTENSITY, floatBuffer(0.0f, 0.0f, 0.0f, 0.0f));
			       


	}
	public FloatBuffer floatBuffer(float a, float b, float c, float d)
    {
	    float[] data = new float[]{a,b,c,d};
	    FloatBuffer fb = BufferUtils.createFloatBuffer(data.length);
	    fb.put(data);
	    fb.flip();
	    return fb;
     }
	public FloatBuffer asFloatBuffer(float[] values)
	{
		FloatBuffer buffer = BufferUtils.createFloatBuffer(values.length);
		buffer.put(values);
		buffer.flip();
		return buffer;
	}

}
