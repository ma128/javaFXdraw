import java.util.ArrayList;

//for redo memory
public class Line {
	int NR = 0; // järjekorra nr

	ArrayList<Double> lineX = new ArrayList<Double>();// X and
	ArrayList<Double> lineY = new ArrayList<Double>(); // Y towhat class

	public Line() {

	}

	public void AddX(Double point, int nr) {
		lineX.add(point);
		NR += nr;
	}

	public void AddY(Double point) {
		lineY.add(point);
	}

	Double getLineX(int n) {
		return lineX.get(n);
	}

	Double getLineY(int n) {
		return lineY.get(n);
	}

	void clear() {
		lineX.clear();
		lineY.clear();
		NR = 0;
	}

	int getNR() {
		return NR;
	}

}
