package sound.code;

public class Blocks
{	

    private SoundCapture SoundCapture;


    public Blocks(SoundCapture SoundCapture)
    {
        this.SoundCapture = SoundCapture;
    }

    public void cube(float x,float y,float z,int s,float ry,float rx)
	{
		SoundCapture.pushMatrix();
		SoundCapture.translate(x, y, z);
		SoundCapture.rotateY(ry);
		SoundCapture.rotateX(rx);
		SoundCapture.strokeWeight(5);
		SoundCapture.stroke(255);
		SoundCapture.box(s);
		SoundCapture.popMatrix();
	}

	public void incube(float x,float y,float z,float s,float ry,float rx)
	{
		SoundCapture.pushMatrix();
		SoundCapture.translate(x, y, z);
		SoundCapture.rotateY(ry);
		SoundCapture.rotateX(rx);
		SoundCapture.strokeWeight(1);
		SoundCapture.stroke(40);
		SoundCapture.box(s);
		SoundCapture.popMatrix();
    }

    public void formation(int x,int y,int z,int s,float ry,float rx)
	{
		SoundCapture.noFill();

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
    }

    public void viz(int x,int y,int z,int s,float ry,float rx,float[] lerpedBands)
    {

        SoundCapture.stroke(0);

        //1
        if(lerpedBands[0]/10 > s - s/4)
        {
            SoundCapture.fill(64,64,64,100);
            incube(x,y+s - s/2,z,s - s/4, ry, rx);
            incube(x,y+s - s/2,z,s - s/4, -ry, -rx);
        }
        else
        {
            SoundCapture.fill(64,64,64,100);
            incube(x,y+s - s/2,z,lerpedBands[0]/10, ry, rx);
            incube(x,y+s - s/2,z,lerpedBands[0]/10, -ry, -rx);
        }

        //2
        if(lerpedBands[1]/10 > s - s/4)
        {
            SoundCapture.fill(224,224,224,100);
            incube(x, y - s/2, z,s - s/4, ry, rx);
            incube(x, y - s/2, z,s - s/4, -ry, -rx);
        }
        else
        {
            SoundCapture.fill(224,224,224,100);
            incube(x, y - s/2, z,lerpedBands[1]/10, ry, rx);
            incube(x, y - s/2, z,lerpedBands[1]/10, -ry, -rx);
        }

        //3
        if(lerpedBands[3]/10 > s - s/4)
        {
            SoundCapture.fill(255,0,255,100);
            incube(x-s,y+(s/2) - s/2, z,s - s/4, ry, rx);
            incube(x-s,y+(s/2) - s/2, z,s - s/4, -ry, -rx);
        }
        else
        {
            SoundCapture.fill(255,0,255,100);
            incube(x-s,y+(s/2) - s/2, z,lerpedBands[3]/10, ry, rx);
            incube(x-s,y+(s/2) - s/2, z,lerpedBands[3]/10, -ry, -rx);
        }

        //4
        if(lerpedBands[4]/10 > s - s/4)
        {
            SoundCapture.fill(255,0,73,100);
            incube(x-s,y-(s/2) - s/2, z,s - s/4, ry, rx);
            incube(x-s,y-(s/2) - s/2, z,s - s/4, -ry, -rx);
        }
        else
        {
            SoundCapture.fill(255,0,73,100);
            incube(x-s,y-(s/2) - s/2, z,lerpedBands[4]/10, ry, rx);
            incube(x-s,y-(s/2) - s/2, z,lerpedBands[4]/10, -ry, -rx);
        }

        //5
        if(lerpedBands[2]/10 > s - s/4)
        {
            SoundCapture.fill(111,255,0,100);
            incube(x-s,y+(s*1.5f) - s/2, z,s - s/4, ry, rx);
            incube(x-s,y+(s*1.5f) - s/2, z,s - s/4, -ry, -rx);
        }
        else
        {
            SoundCapture.fill(111,255,0,100);
            incube(x-s,y+(s*1.5f) - s/2, z,lerpedBands[2]/10, ry, rx);
            incube(x-s,y+(s*1.5f) - s/2, z,lerpedBands[2]/10, -ry, -rx);
        }

        //6
        if(lerpedBands[5]/10 > s - s/4)
        {
            SoundCapture.fill(209,255,0,100);
            incube(x-2*s,y+(s/2) - s/2, z,s - s/4, ry, rx);
            incube(x-2*s,y+(s/2) - s/2, z,s - s/4, -ry, -rx);
        }
        else
        {
            SoundCapture.fill(209,255,0,100);
            incube(x-2*s,y+(s/2) - s/2, z,lerpedBands[5]/10, ry, rx);
            incube(x-2*s,y+(s/2) - s/2, z,lerpedBands[5]/10, -ry, -rx);
        }

        //7
        if(lerpedBands[7]/10 > s - s/4)
        {
            SoundCapture.fill(0,76,153,100);
            incube(x+s,y+(s/2) - s/2, z,s - s/4, ry, rx);
            incube(x+s,y+(s/2) - s/2, z,s - s/4, -ry, -rx);
        }
        else
        {
            SoundCapture.fill(0,76,153,100);
            incube(x+s,y+(s/2) - s/2, z,lerpedBands[7]/10, ry, rx);
            incube(x+s,y+(s/2) - s/2, z,lerpedBands[7]/10, -ry, -rx);
        }

        //8
        if(lerpedBands[8]/10 > s - s/4)
        {
            SoundCapture.fill(255,116,0,100);
            incube(x+s,y-(s/2) - s/2, z,s - s/4, ry, rx);
            incube(x+s,y-(s/2) - s/2, z,s - s/4, -ry, -rx);
        }
        else
        {
            SoundCapture.fill(255,116,0,100);
            incube(x+s,y-(s/2) - s/2, z,lerpedBands[8]/10, ry, rx);
            incube(x+s,y-(s/2) - s/2, z,lerpedBands[8]/10, -ry, -rx);
        }

        //9
        if(lerpedBands[6]/10 > s - s/4)
        {
            SoundCapture.fill(255,91,153,100);
            incube(x+s,y+(s*1.5f) - s/2, z,s - s/4, ry, rx);
            incube(x+s,y+(s*1.5f) - s/2, z,s - s/4, -ry, -rx);
        }
        else
        {
            SoundCapture.fill(255,91,153,100);
            incube(x+s,y+(s*1.5f) - s/2, z,lerpedBands[6]/10, ry, rx);
            incube(x+s,y+(s*1.5f) - s/2, z,lerpedBands[6]/10, -ry, -rx);
        }

        //10
        if(lerpedBands[9]/10 > s - s/4)
        {
            SoundCapture.fill(153,0,153,100);
            incube(x+2*s,y+(s/2) - s/2, z,s - s/4, ry, rx);
            incube(x+2*s,y+(s/2) - s/2, z,s - s/4, -ry, -rx);
        }
        else
        {
            SoundCapture.fill(153,0,153,100);
            incube(x+2*s,y+(s/2) - s/2, z,lerpedBands[9]/10, ry, rx);
            incube(x+2*s,y+(s/2) - s/2, z,lerpedBands[9]/10, -ry, -rx);
        }
    }
}