package sound.code;

import ddf.minim.*;

public class Waves
{	

    private SoundCapture SoundCapture;
    private AudioInput player;
    private float height;
    private float width;

    public Waves(SoundCapture SoundCapture, AudioInput player,float height,float width)
    {
        this.SoundCapture = SoundCapture;
        this.player = player;
        this.height = height;
        this.width = width;

    }
    public void hori() 
    {
        SoundCapture.stroke(150);
		SoundCapture.strokeWeight(1);

		for(int j = -7000; j<=7000; j+=400)
		{
			for(int i = 0; i < player.bufferSize() - 1; i++)
			{
                float x1 = SoundCapture.map( i, 0, player.bufferSize(), -width*40, width*40 );
	            float x2 = SoundCapture.map( i+1, 0, player.bufferSize(),-width*40, width*40 );
				SoundCapture.line( x1, height  - height/9 + player.right.get(i)*50,j, x2, height - height/9 + player.right.get(i+1)*50,j);
			}
		}

    }

}
