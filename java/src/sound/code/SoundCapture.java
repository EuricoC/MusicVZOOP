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
	float s1;
	float sc;
	float ry;
	float rx;
	float radius;
	int cond;
	float cx;
	float cy;
	int cz;



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

		x= width/2;
		y=height/2;
		z=0;
		s=width/9;
		s1=width/20;
		ry=0;
		rx=0;
		radius = 300.0f;
		cond=0;
		cx=width/2;
		cy=height/2;
		cz=-1000;

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

	public void cube(float x,float y,float z,int s,float ry,float rx)
	{
		pushMatrix();
		translate(x, y, z);
		rotateY(ry);
		rotateX(rx);
		strokeWeight(5);
		stroke(255);
		box(s);
		popMatrix();
	}

	public void incube(float x,float y,float z,float s,float ry,float rx)
	{
		pushMatrix();
		translate(x, y, z);
		rotateY(ry);
		rotateX(rx);
		strokeWeight(1);
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
				stroke(player.mix.level()*255);
				circle(x,y,(s*6) + j);
			}
			if(player.mix.get(j)>0.7 && player.mix.get(j)<0.9)
			{
				noFill();
				stroke(player.mix.level()*255);
				circle(x,y,(s*6) + j);
			}
			if(player.mix.get(j)>0.4 && player.mix.get(j)<0.6)
			{
				noFill();
				stroke(player.mix.level()*255);
				circle(x,y,(s*6) + j);
			}
			if(player.mix.get(j)>-0.4 && player.mix.get(j)<-0.2)
			{
				noFill();
				stroke(player.mix.level()*255);
				circle(x,y,(s*6) + j);
			}
			if(player.mix.get(j)>0.1 && player.mix.get(j)<0.25)
			{
				noFill();
				stroke(player.mix.level()*255);
				circle(x,y,(s*6) + j);
			}
		}
	}

	public void hexa(float x, float y,float z, float s, float ry, float rx) 
	{
		
		pushMatrix();
		//translate(x, y, z);
		rotateX(rx);
		rotateY(ry); 
		stroke(255);
		noFill();
		beginShape();

		vertex(x,y,z);
		vertex(x+s,y,z);
		vertex(x+(s*1.5f),y+(sqrt(sq(s)-sq(s/2))),z);
		vertex(x+s,y+(sqrt(sq(2*s)-sq(s))),z);
		vertex(x,y+(sqrt(sq(2*s)-sq(s))),z);
		vertex(x-(s/2),y+(sqrt(sq(s)-sq(s/2))),z);
		vertex(x,y,z);

		vertex(x-(s/2),y+(sqrt(sq(s)-sq(s/2))),z+s);
		vertex(x,y+(sqrt(sq(2*s)-sq(s))),z+s);
		vertex(x+s,y+(sqrt(sq(2*s)-sq(s))),z+s);
		vertex(x+(s*1.5f),y+(sqrt(sq(s)-sq(s/2))),z+s);
		vertex(x+s,y,z+s);
		vertex(x,y,z+s);
		vertex(x-(s/2),y+(sqrt(sq(s)-sq(s/2))),z+s);

		vertex(x,y,z);
		vertex(x,y,z+s);
		vertex(x+s,y,z);
		vertex(x+s,y,z+s);
		vertex(x+(s*1.5f),y+(sqrt(sq(s)-sq(s/2))),z);
		vertex(x+(s*1.5f),y+(sqrt(sq(s)-sq(s/2))),z+s);
		vertex(x+s,y+(sqrt(sq(2*s)-sq(s))),z);
		vertex(x+s,y+(sqrt(sq(2*s)-sq(s))),z+s);
		vertex(x,y+(sqrt(sq(2*s)-sq(s))),z);
		vertex(x,y+(sqrt(sq(2*s)-sq(s))),z+s);
		vertex(x-(s/2),y+(sqrt(sq(s)-sq(s/2))),z);
		vertex(x-(s/2),y+(sqrt(sq(s)-sq(s/2))),z+s);

		endShape();
		popMatrix();
		}
		
		public void cam() 
	{

		if(cz<0 && cz>=-1000  && cond==0)
		{
			camera(cx,cy,cz,width/2,height/2,0,0,1,0);
			cz+=2;
			cx=cx + width/2 / 500;
			cy=cy + height/2 /500;
		}

		if(cz>=0 && cz<1000 && cond == 0)
		{
			camera(cx,cy,cz,width/2,height/2,0,0,1,0);
			cz+=2;
			cx=cx - width/2 / 500;
			cy=cy - height/2 / 500;
		}
		else if(cz==1000)
		{
			cond=1;
		}

		if(cz<=1000 && cz>0 && cond==1)
		{
			camera(cx,cy,cz,width/2,height/2,0,0,1,0);
			cz-=2;
			cx=cx - width/2 / 500;
			cy=cy - height/2 /500;
		}

		if(cz<=0 && cz>-1000 && cond==1)
		{
			camera(cx,cy,cz,width/2,height/2,0,0,1,0);
			cz-=2;
			cx=cx + width/2 / 500;
			cy=cy + height/2 /500;
		}
		else if(cz==-1000)
		{
			cond=0;
		}		
	}

	


	public void draw()
	{			
		background(0);

		lights();
		directionalLight(255, 255, 255, 1, 0, 0);
		directionalLight(255, 255, 255, 1, 0, 1);
		directionalLight(255, 255, 255, -1, 0, 0);
		directionalLight(255, 255, 255, 1, 0, 1);

		cam();
		rings();

		fft.window(FFT.HAMMING);
		fft.forward(player.mix);

		getFrequencyBands();
		ry+= 0.02;
		rx+= 0.02;

		noFill();

		
		//hexa(0+s,y,z,s/10,ry,rx);
		cube(x,y+s - s/2,z,s,0,0);
		cube(x,y - s/2,z,s,0,0);
		cube(x-s,y+(s*1.5f) - s/2,z,s,0,0);
		cube(x-s,y+(s/2) - s/2,z,s,0,0);
		cube(x-s,y-(s/2) - s/2,z,s,0,0);

		cube(x-2*s,y+(s/2) - s/2,z,s,0,0);
		cube(x+s,y+(s*1.5f) - s/2,z,s,0,0);
		cube(x+s,y+(s/2) - s/2,z,s,0,0);
		cube(x+s,y-(s/2) - s/2,z,s,0,0);
		cube(x+2*s,y+(s/2) - s/2,z,s,0,0);
		

		stroke(0);
		//1
		fill(64,64,64,100);
		incube(x,y+s - s/2,z,lerpedBands[0]/10, ry, rx);
		incube(x,y+s - s/2,z,lerpedBands[0]/10, -ry, -rx);
		//2
		fill(224,224,224,100);
		incube(x, y - s/2, z,lerpedBands[1]/10, ry, rx);
		incube(x, y - s/2, z,lerpedBands[1]/10, -ry, -rx);
		incube(x+2*s,y+(s/2) - s/2, z,lerpedBands[9]/10, -ry, -rx);
		//3
		fill(255,0,255,100);
		incube(x-s,y+(s/2) - s/2, z,lerpedBands[3]/10, ry, rx);
		incube(x-s,y+(s/2) - s/2, z,lerpedBands[3]/10, -ry, -rx);
		//4
		fill(255,0,73,100);
		incube(x-s,y-(s/2) - s/2, z,lerpedBands[4]/10, ry, rx);
		incube(x-s,y-(s/2) - s/2, z,lerpedBands[4]/10, -ry, -rx);
		//5
		fill(111,255,0,100);
		incube(x-s,y+(s*1.5f) - s/2, z,lerpedBands[2]/10, ry, rx);
		incube(x-s,y+(s*1.5f) - s/2, z,lerpedBands[2]/10, -ry, -rx);
		//6
		fill(209,255,0,100);
		incube(x-2*s,y+(s/2) - s/2, z,lerpedBands[5]/10, ry, rx);
		incube(x-2*s,y+(s/2) - s/2, z,lerpedBands[5]/10, -ry, -rx);	
		//7
		fill(0,76,153,100);
		incube(x+s,y+(s/2) - s/2, z,lerpedBands[7]/10, ry, rx);
		incube(x+s,y+(s/2) - s/2, z,lerpedBands[7]/10, -ry, -rx);
		//8
		fill(255,116,0,100);
		incube(x+s,y-(s/2) - s/2, z,lerpedBands[8]/10, ry, rx);
		incube(x+s,y-(s/2) - s/2, z,lerpedBands[8]/10, -ry, -rx);
		//9
		fill(255,91,153,100);
		incube(x+s,y+(s*1.5f) - s/2, z,lerpedBands[6]/10, ry, rx);
		incube(x+s,y+(s*1.5f) - s/2, z,lerpedBands[6]/10, -ry, -rx);
		//10
		fill(153,0,153,100);
		incube(x+2*s,y+(s/2) - s/2, z,lerpedBands[9]/10, ry, rx);
	}
}