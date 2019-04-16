package sound.code;

import ddf.minim.*;

public class Aura
{	

    private SoundCapture SoundCapture;
    private AudioInput player;
    private int x;
    private int y;
    private int s;


    public Aura(SoundCapture SoundCapture, AudioInput player,int x, int y, int s)
    {
        this.SoundCapture = SoundCapture;
        this.player = player;
        this.x = x;
        this.y = y;
        this.s = s;


    }

    public void rings()
	{
		for(int j =0; j<100; j++)
		{
			if(player.mix.get(j)>-0.8 && player.mix.get(j)<-0.5)
			{
				SoundCapture.noFill();
				SoundCapture.stroke(player.mix.level()*255);
				SoundCapture.circle(x,y,(s*6) + j);
			}
			if(player.mix.get(j)>0.7 && player.mix.get(j)<0.9)
			{
				SoundCapture.noFill();
				SoundCapture.stroke(player.mix.level()*255);
				SoundCapture.circle(x,y,(s*6) + j);
			}
			if(player.mix.get(j)>0.4 && player.mix.get(j)<0.6)
			{
				SoundCapture.noFill();
				SoundCapture.stroke(player.mix.level()*255);
				SoundCapture.circle(x,y,(s*6) + j);
			}
			if(player.mix.get(j)>-0.4 && player.mix.get(j)<-0.2)
			{
				SoundCapture.noFill();
				SoundCapture.stroke(player.mix.level()*255);
				SoundCapture.circle(x,y,(s*6) + j);
			}
			if(player.mix.get(j)>0.1 && player.mix.get(j)<0.25)
			{
				SoundCapture.noFill();
				SoundCapture.stroke(player.mix.level()*255);
				SoundCapture.circle(x,y,(s*6) + j);
			}
		}
	}

}