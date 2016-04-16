import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

public class towerOne extends tower {

	public towerOne(Image i, int baseX, int baseY) {
		
		towerImage = i;
		
		cost = 75;
		damage = 10;
		range = 150;

		coefficientX = baseX;
		coefficientY = baseY;
		centerX = baseX + 50;
		centerY = baseY + 50;
		
		targetX = coefficientX + 50;
		targetY = coefficientY;
		
	}
}