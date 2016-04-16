import java.awt.Graphics2D;
import java.awt.Image;

public class img{
	
	Image image;
	int xCoord;
	int yCoord;
	int resizeValX;
	int resizeValY;
	
	boolean entered;
	
	public img(Image i, int x, int y , int resizeX, int resizeY){
		image = i;
		xCoord = x;
		yCoord = y;
		resizeValX = resizeX;
		resizeValY = resizeY;
		
		entered = false;
	}
	public img(Image i, int x, int y){
		image = i;
		xCoord = x;
		yCoord = y;
		resizeValX = 0;
		resizeValY = 0;
		
		entered = false;
	}
	
	public Image getImage(){
		return image;
	}
	public int getXCoord(){
		return xCoord;
	}
	public int getYCoord(){
		return yCoord;
	}
	public int getResizeX(){
		return resizeValX;
	}
	public int getResizeY(){
		return resizeValY;
	}
}
