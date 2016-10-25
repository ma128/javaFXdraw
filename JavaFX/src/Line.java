import javafx.scene.shape.Circle;

public class Line {
int lineNR; //järjekorra nr
int mem = 0; // mälu arrayl
Circle[] livepoints = new Circle[mem];//joonistuspunktid kuni vasak hiire nupp on all

public Line() {

}

public Line(Circle[] points, int memory, int lineNR) {
		livepoints = points;
		mem = memory;
	}

}
