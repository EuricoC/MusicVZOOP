package sound.code;

public class Camera
{	

    private SoundCapture SoundCapture;
    private float cx;
    private float cy;
    private float cz;
    private int cond;
    private float height;
    private float width;


    public Camera(SoundCapture SoundCapture, float cx, float cy, float cz, int cond, float height, float width)
    {
        this.SoundCapture = SoundCapture;
        this.cx = cx;
        this.cy = cy;
        this.cz = cz;
        this.cond = cond;
        this.height = height;
        this.width = width;
    }

    public void cam()
	{
        if(cz<0 && cz>=-1000  && cond==0)
		{
			SoundCapture.camera(cx,cy,cz,width/2,height/2,0,0,1,0);
			cz+=2;
			cx=cx + width/2 / 500;
			cy=cy + height/2 /500;
		}

		if(cz>=0 && cz<1000 && cond == 0)
		{
			SoundCapture.camera(cx,cy,cz,width/2,height/2,0,0,1,0);
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
			SoundCapture.camera(cx,cy,cz,width/2,height/2,0,0,1,0);
			cz-=2;
			cx=cx - width/2 / 500;
			cy=cy - height/2 /500;
		}

		if(cz<=0 && cz>-1000 && cond==1)
		{
			SoundCapture.camera(cx,cy,cz,width/2,height/2,0,0,1,0);
			cz-=2;
			cx=cx + width/2 / 500;
			cy=cy + height/2 /500;
		}
		else if(cz==-1000)
		{
			cond=0;
		}		
    }
}