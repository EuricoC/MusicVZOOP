package sound.code;

import processing.core.PApplet;
import ddf.minim.analysis.*;
import ddf.minim.*;

public class SoundCapture extends PApplet
{	

	Minim minim;
	AudioInput player;
	FFT fft;
	Waves wave;
	Aura aura;
	Blocks block;
	Camera camera;


	int x;
	int y;
	int z;
	int s;
	float ry;
	float rx;
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
		ry=0;
		rx=0;
		cond=0;
		cx=width/2;
		cy=height/2;
		cz=-1000;

		wave = new Waves(this,player,height,width);
		aura = new Aura(this,player,x,y,s);
		block = new Blocks(this);
		camera = new Camera(this, cx, cy, cz, cond, height, width);

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

	public void draw()
	{			
		background(0);

		lights();
		directionalLight(255, 255, 255, 1, 0, 0);
		directionalLight(255, 255, 255, 1, 0, 1);
		directionalLight(255, 255, 255, -1, 0, 0);
		directionalLight(255, 255, 255, 1, 0, 1);

		camera.cam();
		aura.rings();
		wave.hori();

		fft.window(FFT.HAMMING);
		fft.forward(player.mix);

		getFrequencyBands();
		ry+= 0.02;
		rx+= 0.02;

		block.formation(x, y, z, s, ry, rx);
		block.viz(x, y, z, s, ry, rx,lerpedBands);

	}
}