package sound.code;

public class Main
{	


	public void SoundCapture()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new SoundCapture());
		
	}
	
	public static void main(String[] args)
	{
		Main main = new Main();
		main.SoundCapture();			
	}
}