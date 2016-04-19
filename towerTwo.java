import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;


public class towerTwo extends tower{
	
	public towerTwo(Image i, int baseX, int baseY) {
		
		towerImage = i;
		
		cost = 200;
		damage = 5;
		range = 250;

		coefficientX = baseX;
		coefficientY = baseY;
		centerX = baseX + 50;
		centerY = baseY + 50;
		
		targetX = coefficientX + 50;
		targetY = coefficientY;
	}
	
}



