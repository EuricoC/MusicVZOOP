package sound.code;

import processing.core.PApplet;
import ddf.minim.analysis.*;
import ddf.minim.*;

public class SoundCapture extends PApplet
{	

	Minim minim;
	AudioInput player;
	FFT fft;

	int x;
	int y;
	int z;
	int s;
	int cam;
	float s1;
	float sc;
	float ry;
	float rx;
	float mr;
	float radius;
	int count;



	float[] bands;
	float[] lerpedBands;

	float log2(float f) 
	{
	return log(f)/log(2.0f);
	}
	

	public void settings()
	{
		fullScreen(P3D,SPAN);
		// size(500,500,P3D);
	}

    public void setup() 
    {
		minim = new Minim(this);
		player = minim.getLineIn(Minim.MONO, 1024);
 		fft = new FFT(1024, 44100);

		
		bands = new float[(int) log2(1024)];
		lerpedBands = new float[bands.length];

		count = 50;
		x= width/2;
		y=height/2;
		z=0;
		s=width/8;
		s1=width/20;
		ry=0;
		rx=0;
		cam= 0;
		mr=width/2;
		radius = 300.0f;
	}

		void getFrequencyBands()
		{        
		  for (int i = 0; i < bands.length; i++)
		  {
			int start = (int)pow(2, i) - 1;
			int w = (int)pow(2, i);
			int end = start + w;
			float average = 0;
			for (int j = start; j < end; j++)
			{
			  average += fft.getBand(j) * (j + 1);
			}
			average /= (float) w;
			bands[i] = average * 5.0f;
			lerpedBands[i] = lerp(lerpedBands[i], bands[i], 0.05f);
		  }
		}

	public void cube(int x,int y,int z,int s,float ry,float rx)
	{
		pushMatrix();
		translate(x, y, z);
		rotateY(ry);
		rotateX(rx);
		stroke(255);
		box(s);
		popMatrix();
	}

	public void incube(int x,int y,float z,float s,float ry,float rx)
	{
		pushMatrix();
		translate(x, y, z);
		rotateY(ry);
		rotateX(rx);
		stroke(40);
		box(s);
		popMatrix();
	}

	public void rings()
	{
		for(int j =0; j<100; j++)
		{
			if(player.mix.get(j)>-0.8 && player.mix.get(j)<-0.5)
			{
				noFill();
				stroke(player.mix.level()*255,0,player.mix.level()*220);
				circle(x,y,(s*5) + j);
			}
			if(player.mix.get(j)>0.7 && player.mix.get(j)<0.9)
			{
				noFill();
				stroke(player.mix.level()*248,player.mix.level()*255,0);
				circle(x,y,(s*5) + j);
			}
			if(player.mix.get(j)>0.4 && player.mix.get(j)<0.6)
			{
				noFill();
				stroke(0,player.mix.level()*247,player.mix.level()*255);
				circle(x,y,(s*5) + j);
			}
			if(player.mix.get(j)>-0.4 && player.mix.get(j)<-0.2)
			{
				noFill();
				stroke(player.mix.level()*120,player.mix.level()*150,player.mix.level()*60);
				circle(x,y,(s*5) + j);
			}
			if(player.mix.get(j)>0.1 && player.mix.get(j)<0.25)
			{
				noFill();
				stroke(player.mix.level()*255,player.mix.level()*63,player.mix.level()*4);
				circle(x,y,(s*5) + j);
			}
		}
	}

	void hexa(float x, float y,float z, float s, float ry, float rx) 
	{
		
		pushMatrix();
		translate(x, y, z);
		rotateX(rx);
		rotateY(ry); 
		stroke(255);
		noFill();
		beginShape();

		vertex(x,y,z);
		vertex(x+s,y,z);
		vertex(x,y+(sqrt(sq(2*s)-sq(s))),z);
		vertex(x+s,y+(sqrt(sq(2*s)-sq(s))),z);
		vertex(x+(s*1.5f),y+(sqrt(sq(s)-sq(s/2))),z);
		vertex(x-(s*0.5f),y+(sqrt(sq(s)-sq(s/2))),z);

		endShape(CLOSE);
		popMatrix();
	  }
	


	public void draw()
	{			
		background(0);
		lights();
		//camera(radius*sin(radians(mr)) + width/2, height/2, radius*cos(radians(mr))+(width/2) / tan(PI/6),width/2,height/2,(width/2) / tan(PI/6),0,1,0);
		mr+= 1.0f;
		rings();

		fft.window(FFT.HAMMING);
		fft.forward(player.mix);

		getFrequencyBands();
		ry+= 0.02;
		rx+= 0.02;

		noFill();
		stroke(255);
		hexa(x,y,z,s,0,0);
		// cube(x,y,z,s,0,0);
		// cube(x+s,y,z,s,0,0);
		// cube(x-s,y,z,s,0,0);
		// cube(x+(2*s),y,z,s,0,0);
		// cube(x-(2*s),y,z,s,0,0);
		// cube(x,y+s,z,s,0,0);
		// cube(x+s,y+s,z,s,0,0);
		// cube(x-s,y+s,z,s,0,0);
		// cube(x+(2*s),y+s,z,s,0,0);
		// cube(x-(2*s),y+s,z,s,0,0);

		stroke(0);
		fill(255,0,0,100);
		incube(x+s, y, z,lerpedBands[0]/10, ry, rx);
		incube(x+s, y, z,lerpedBands[0]/10, -ry, -rx);
		fill(0,255,0,100);
		incube(x, y, z,lerpedBands[1]/10, -ry, -rx);
		incube(x, y, z,lerpedBands[1]/10, ry, rx);
		fill(0,0,255,100);
		incube(x-s, y, z,lerpedBands[2]/10, ry, rx);
		incube(x-s, y, z,lerpedBands[2]/10, -ry, -rx);
		fill(100,220,9,100);
		incube(x-(2*s), y, z,lerpedBands[3]/10, ry, rx);
		incube(x-(2*s), y, z,lerpedBands[3]/10, -ry, -rx);
		fill(60,9,230,100);
		incube(x+(2*s), y, z,lerpedBands[4]/10, ry, rx);
		incube(x+(2*s), y, z,lerpedBands[4]/10, -ry, -rx);

		fill(255,0,0,100);
		incube(x+s, y+s, z,lerpedBands[5]/10, ry, rx);
		incube(x+s, y+s, z,lerpedBands[5]/10, -ry, -rx);
		fill(0,255,0,100);
		incube(x, y+s, z,lerpedBands[6]/10, ry, rx);
		incube(x, y+s, z,lerpedBands[6]/10, -ry, -rx);
		fill(0,0,255,100);
		incube(x-s, y+s, z,lerpedBands[7]/10, ry, rx);
		incube(x-s, y+s, z,lerpedBands[7]/10, -ry, -rx);
		fill(100,220,9,100);
		incube(x-(2*s), y+s, z,lerpedBands[8]/10, ry, rx);
		incube(x-(2*s), y+s, z,lerpedBands[8]/10, -ry, -rx);
		fill(60,9,230,100);
		incube(x+(2*s), y+s, z,lerpedBands[9]/10, ry, rx);
		incube(x+(2*s), y+s, z,lerpedBands[9]/10, -ry, -rx);	
	}
}