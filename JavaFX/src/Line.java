import javafx.scene.shape.Circle;

public class Line {
int lineNR; //j�rjekorra nr
int mem = 0; // m�lu arrayl
Circle[] livepoints = new Circle[mem];//joonistuspunktid kuni vasak hiire nupp on all

public Line() {

}

public Line(Circle[] points, int memory, int lineNR) {
		livepoints = points;
		mem = memory;
	}

}
