import java.awt.Image;


public class bossEnemy extends enemy{
	
	public bossEnemy(Image i, int y, int d){
		
		enemyImage = i;
		
		xCoord = 100;
		yCoord = y+20;
		width = 100;
		height = 100;
		
		health = 2500;
		speed = 10;
		creditsOnDeath = 75;
		distanceTraveled = d;
		
		alive = true;
		
	}
	
}