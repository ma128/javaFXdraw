import java.util.ArrayList;

//for get angles and save for later compare
public class ToWhat {
	Double x0 = 0.0;// from arraylist x start
	Double x1 = 0.0;

	Double y0 = 0.0;// from arraylist y start
	Double y1 = 0.0;

	int k = 19; // min line point differnce in pix for measuring correct line
				// angles
	ArrayList<Integer> LineAngles = null;

	ArrayList<Double> listXW = new ArrayList<Double>();
	ArrayList<Double> listYW = new ArrayList<Double>();
	int[] corrArr = new int[50];

	public ToWhat() {
	}

	// lühidalt add paneb kõik punktide vahelised jooned nurkadena arraylisti
	// ehk sama kujundil peaks olema umbes sama list, kontroll ja täpsus siis 15
	// kraadise astmega

	public void add(ArrayList<Double> listX0, ArrayList<Double> listY0) {
		double x2 = 0.0;
		double y2 = 0.0;
		int ki = 1;

		listXW = new ArrayList<Double>(listX0);
		listYW = new ArrayList<Double>(listY0);

		LineAngles = new ArrayList<Integer>();

		for (int i = -1; i < listXW.size() - 1;) {
			i++;
			// for better accuracy we pick points after k pix
			// and x0 and y0 we pick when line starts
			if (listXW.get(i) == 0.0 || x0 == 0.0) {
				if (i + 1 < listXW.size() - 1) {
					i++;
					x0 = listXW.get(i);
					y0 = listYW.get(i);
				} else {
					break;
				}
			}

			// now we pick next points for angles
			while (i < listXW.size() - 1) {
				i++;

				// check if its last point
				if (i + k + 1 > listXW.size() - 1) {
					i = listXW.size() - 1;
					break;
				}

				// for x2 and y2 as next starting points
				if (listXW.get(i) == 0.0) {
					i = i - 1;
					break;
				} else if (ki == k) {
					x2 = listXW.get(i + 1);
					y2 = listYW.get(i + 1);
					break;
				} else {
					ki++;
				}
			} // end while
			
			ki = 1;
			x1 = listXW.get(i);
			y1 = listYW.get(i);
			setLineAngles(x0, x1, y0, y1);
			if (x2 != 0.0) {
				x0 = x2;
				y0 = y2;
			}
		} // end for loop

	}// end add


	String printAngles() {
		String angles = "";
		int i = 0;

		while (i != LineAngles.size()) {
			angles += String.valueOf(LineAngles.get(i)) + "|";
			i++;
		}

		return angles;
	}

	private void setLineAngles(Double x02, Double x12, Double y02, Double y12) {

		double tempIfx = 0.0;
		double tempIfy = 0.0;
		// delta x coordinates
		double tempxx = Math.abs(x02 - x12);
		// delta y coordinates
		double tempyy = Math.abs(y02 - y12);
		// for knowing if line is 0-90 or 90-180
		double temp2 = (x02 - x12) + (y02 - y12);

		// line angles, 0 means horizontal and 90 is vertical
		// 0, 30, 150 kraadi
		if (tempxx > tempyy) {
			if (tempyy == 0) {
				LineAngles.add(0);
			} else {
				tempIfx = 2 * (1 / (tempxx / tempyy));
				if (tempIfx < 1) {
					LineAngles.add(0);
				} else {
					if (tempIfx >= 1 && ((x1-x0) < 0 && (y1 > y0) || (x1 - x0) > 0 && (y1 < y0))) {
						LineAngles.add(150);//
					} else {
						LineAngles.add(30);//
					}
				}
			}
		}

		// 60, 90, 120 kraadi
		else if (tempxx < tempyy) {
			if (tempxx == 0) {
				LineAngles.add(90);
			} else {
				tempIfy = 2 * (1 / (tempyy / tempxx));
				if (tempIfy < 1) {
					LineAngles.add(90);
				} else {
					if (tempIfy >= 1 && ((x1-x0) < 0 && (y1 > y0) || (x1 - x0) > 0 && (y1 < y0))) {
						LineAngles.add(120);//
					} else {
						LineAngles.add(60);//
					}
				}
			}
		}
		}

}// end class
