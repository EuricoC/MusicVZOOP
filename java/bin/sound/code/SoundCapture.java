package sound.code;

import processing.core.PApplet;
import ddf.minim.*;

public class SoundCapture extends PApplet
{	
	Minim minim;
	AudioPlayer player;

	public void settings()
	{
		size(500,500);
	}

    public void setup() 
    {
		minim = new Minim(this);

		player = minim.loadFile("looped.mp3");
	}

	public void draw()
	{			
		background(0);
		stroke(243,16,243);
		

		for(int i = 0; i < player.bufferSize() - 1; i++)
		{
			float x1 = map( i, 0, player.bufferSize(), 0, width );
			float x2 = map( i+1, 0, player.bufferSize(), 0, width );

			line( x1, height/2 + player.mix.get(i)*50, x2, height/2 + player.mix.get(i+1)*50 );
		}

		rectMode(CORNERS);
		rect( 0,height,100,height-player.left.level()*width);
 		rect( 100,height,200,height-player.right.level()*width );

	}

	public void keyPressed()
	{

		if ( player.isPlaying() )
		{
			player.pause();
		}
		

		else if ( player.position() == player.length() )
		{
			player.rewind();
			player.play();
		}
		else
		{
			player.play();
		}
	}

}