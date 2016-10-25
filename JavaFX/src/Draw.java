
import java.util.Iterator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Draw extends Application {
	// deklaratsioonid:
	int n = 0;// to array
	int k = 0; // from array
	int u = 0;
	int uusLineKlassiNR = 0;
	Line[] uusLine = new Line[20]; // array to save class Line

	double mouseX = 0;
	double mouseY = 0;
	int mem = 100;// undo mälu
	int full = 0; // removes points id mem full
	Circle[] points = new Circle[mem];

	// double[][] XY;// for saving and later to text file!!!
	int radius = 3;
	boolean drawing = false;

	Circle point = null;

	VBox vbox = new VBox();// kogu window scene
	HBox hbox1 = new HBox(10);// ülemine rida , sulgudes: child node spacing
								// horizontally
	Group group2 = new Group(); // alumine grupp
	Iterator<Node> i = group2.getChildren().iterator();// To safely remove from
														// a collection while
														// iterating over it you
														// should use an
														// Iterator
	Rectangle r1 = new Rectangle(500, 400); // läheb alumise grupi sisse
											// joonistus aluseks

	Scene scene = new Scene(vbox);

	Button clearb = new Button("Clear");
	Button undob = new Button("Undo");
	Button redob = new Button("Redo");
	Button saveb = new Button("SAVE");

	/************ main ***********/
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		primaryStage.setTitle("Drawboard");

		{// nupud
			clearb.setPrefWidth(70);
			undob.setPrefWidth(70);
			redob.setPrefWidth(70);
			saveb.setPrefWidth(70);
		}

		{// hbox1 sisu ja style
			hbox1.getChildren().add(clearb);
			hbox1.getChildren().add(undob);
			hbox1.getChildren().add(redob);
			hbox1.getChildren().add(saveb);
			hbox1.setAlignment(Pos.TOP_CENTER);
			hbox1.setStyle("-fx-background-color: #FF5733");// oranss
		}
		vbox.getChildren().add(hbox1);
		r1.setFill(Color.BLUE);
		group2.getChildren().add(r1);

		// -----------------hiire code--------------------
		scene.addEventHandler(MouseEvent.MOUSE_DRAGGED, me -> {
			if (me.isPrimaryButtonDown()) {
				// arvutada koht, mitte scenel aga sellel r1-es!
				if (me.getSceneX() >= r1.getX() + radius && me.getSceneX() <= r1.getX() + r1.getWidth() - radius) {
					mouseX = me.getSceneX();
				}
				if (me.getSceneY() >= r1.getY() + radius + 25
						&& me.getSceneY() <= r1.getY() + r1.getHeight() + 25 - radius) {
					mouseY = me.getSceneY() - 25;
				}
				try {
					handle(me);
					// add point to screen
					group2.getChildren().add(getPoint());

				} catch (Exception ex) {
				}
			} 
		});
		//uus joone klass kui hiire nupp ei ole all enam:
		scene.addEventHandler(MouseEvent.MOUSE_RELEASED, me2 -> {
			uusLine[uusLineKlassiNR] = new Line(points, n, uusLineKlassiNR);
			n = 0;
			//uusLineKlassiNR++;
		});
		undob.setOnAction((ActionEvent event) -> {
			undo();
		});

		vbox.getChildren().add(group2);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	// FFFFFFFFFFFFFFFFF meetodid FFFFFFFFFFFFFFFFFFFFFF

	public void handle(MouseEvent me) {
		draw();
		toArrayMem();// vigane veel

	}

	private void undo() {
		u = group2.getChildren().size();

		group2.getChildren().remove(u - 1);
		group2.getChildren().remove(u - 2);
		group2.getChildren().remove(u - 3);
		group2.getChildren().remove(u - 4);
		group2.getChildren().remove(u - 5);
		group2.getChildren().remove(u - 6);
		group2.getChildren().remove(u - 7);
		group2.getChildren().remove(u - 8);
		group2.getChildren().remove(u - 9);
		group2.getChildren().remove(u - 10);

	}

	private Node getPoint() {
		k = n - 1;
		if (k < 0 || k >= points.length) {
			k = 0;
		}

		return points[k];
	}

	private void toArrayMem() {
		points[n] = point;
		n++;
		if (n >= mem) {
			n = 0;
		}

	}

	private void draw() {

		point = new Circle(mouseX, mouseY, radius, Color.BLACK);
	}

}
