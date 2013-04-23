package kapitel_3.misc;
import java.awt.Color;

public class FilledCircle extends Circle {
	private Color color;
	
	FilledCircle(Color color) {
		this.color = color;
	}
	Color getColor() { 
		return color; 
	}
}
