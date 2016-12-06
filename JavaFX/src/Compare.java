import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Compare {

	ArrayList<Integer> compAngles = new ArrayList<Integer>();
	ArrayList<String> compNames = new ArrayList<String>();
	int count = 0; // txt file line nrs
	String str = "start";

	public Compare() {
		// TODO Auto-generated constructor stub
	}

	// constructor works as reading txt file and compares with incoming angles
	// list, gives
	// probability match then shows it and if below some nr then user can modify
	// text file by adding new definition and angles come automatically
	public Compare(ArrayList<Integer> list) {

		// adding text to comp lists:
		try (BufferedReader reader = new BufferedReader(new FileReader("resources/compare.txt"))) {
			char ch;
			int i = 0;
			int n = 0;
			int g = 0;
			String str2 = "";

			while (str != null) {
				str = reader.readLine();
				if (str == null) {
					break;
				}
				count++;
				int paaris = count - (count / 2) * count;

				if (paaris == 0) {
					compNames.add(str);
				}

				// reading 2 places ints : 00 90 ...
				while (i < str.length() && paaris > 0) {
					ch = str.charAt(i);
					n++;// n = 1,2
					str2 += Character.toString(ch);

					if (n == 2) {
						if (str2.contentEquals("00")) {
							compAngles.add(0);
						} else {
							compAngles.add(g, Integer.valueOf(str2));
						}
						g++;
						n = 0;
						str2 = "";
					}
					i++;
				} // end while2
			} // end while1
		} catch (Exception e) {
			System.out.println("faili compare.txt lugemisel viga: " + e);
		}
		// compares with incoming angles list
		makeCompare(list);
	}

	private void makeCompare(ArrayList<Integer> list) {
		try {
			if (list.size() > 0) {

				//TODO


			}
		} catch (Exception e) {
			System.out.println("makecompare viga: " + e);
		}
	}

	String showResult() {
		String angles = "";
		int i = 0;

		while (i != compAngles.size()) {
			angles += String.valueOf(compAngles.get(i));
			i++;
		}

		return angles;
	}

}
