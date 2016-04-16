import java.awt.Image;


public class strongEnemy extends enemy{
	
	public strongEnemy(Image i, int y, int d){
		
		enemyImage = i;
		
		xCoord = 100;
		yCoord = y - 100;
		width = 100;
		height = 100;
		
		health = 100;
		speed = 10;
		creditsOnDeath = 50;
		distanceTraveled = d - 60;
		
		alive = true;
	}
}