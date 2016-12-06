
import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
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
	int u = 0; // nr of children or dots drawn +1
	int nr = 1;// redo from Line
	int radius = 3;// point radius
	int c = 0;// bigger whatArr

	double sliderValue = 0; // default
	double slidermaxValue = 2;
	double d1 = 0;// request undos
	double mouseX = 0;
	double mouseY = 0;
	double prefWidth = 350;
	double prefHeight = 42;

	ArrayList<Double> listX = new ArrayList<Double>();// X and
	ArrayList<Double> listY = new ArrayList<Double>(); // Y towhat class

	boolean MousePrimaryButtonDown = false;
	boolean redoSelected = false;
	boolean undoSelected = true;

	Line redoLine = new Line();
	ToWhat towhat = new ToWhat();

	VBox vbox = new VBox();// kogu window scene
	HBox hbox1 = new HBox(10);// ülemine rida , sulgudes: child node spacing
								// horizontally
	Group group2 = new Group(); // alumine grupp
	Rectangle r1 = new Rectangle(500, 400); // läheb group2 sisse
											// joonistus aluseks
	Parent root = null;
	Scene scene = new Scene(vbox);

	Button clearb = new Button("Clear");
	Label labelUndo = new Label("Undo");
	Label labelRedo = new Label("Redo");
	CheckBox undoCheck = new CheckBox();
	CheckBox redoCheck = new CheckBox();
	Slider slider = new Slider(0, slidermaxValue, sliderValue);
	Button whatitis = new Button("What it is?");

	/************ main ***********/
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		primaryStage.setTitle("Drawboard");

		{// nupud
			clearb.setPrefWidth(70);
			labelUndo.setPrefWidth(30);
			undoCheck.setPrefWidth(20);
			labelRedo.setPrefWidth(30);
			redoCheck.setPrefWidth(20);
			slider.setPrefWidth(140);
			whatitis.setPrefWidth(90);
		}

		{// hbox1 sisu ja style
			hbox1.getChildren().add(clearb);
			hbox1.getChildren().add(labelUndo);
			hbox1.getChildren().add(undoCheck);
			hbox1.getChildren().add(labelRedo);
			hbox1.getChildren().add(redoCheck);
			hbox1.getChildren().add(slider);
			hbox1.getChildren().add(whatitis);
			hbox1.setAlignment(Pos.CENTER);
			hbox1.setStyle("-fx-background-color: #FF5733");// oranss
		}

		undoCheck.setSelected(true);
		vbox.getChildren().add(hbox1);
		r1.setFill(Color.BLUE);
		group2.getChildren().add(r1);

		// ----------------- code--------------------

		// clearbutton
		clearb.setOnAction((eventcl) -> {
			redoLine.clear();
			group2.getChildren().clear();
			group2.getChildren().add(r1);
			listX.clear();// deletes all in arraylist
			listY.clear();

		});

		// draw
		group2.addEventHandler(MouseEvent.ANY, me -> {

			if (me.isPrimaryButtonDown()) {
				if(!MousePrimaryButtonDown) {
						listX.add(0.0); // 'to what' means line beginning
				listY.add(0.0);
				MousePrimaryButtonDown = true;

				}
			
				// arvutada koht, mitte scenel aga r1-es!
				if (me.getSceneX() >= r1.getX() + radius && me.getSceneX() <= r1.getX() + r1.getWidth() - radius) {
					mouseX = me.getSceneX();
				}
				if (me.getSceneY() >= r1.getY() + radius + 25
						&& me.getSceneY() <= r1.getY() + r1.getHeight() + 25 - radius) {
					mouseY = me.getSceneY() - 25;
				}
				try {
					// Drawing or adding point to screen
					group2.getChildren().add(new Circle(mouseX, mouseY, radius, Color.BLACK));

					listX.add(mouseX); // to what
					listY.add(mouseY);

				} catch (Exception ex) {
					System.out.println("Draw viga: \n" + ex);
				} finally {
					// redos isnt possible anymore
					redoLine.clear();
					redoCheck.setVisible(false);

					// sliderValue on nähtav koht ekraanil
					// ja getValue annb hiire koha kui draggida sliderboxil
					slider.setValue(listX.size());
					sliderValue = slider.getValue();
				}
			}else{
				MousePrimaryButtonDown = false;
			}
		});

		// checkbox for undo
		undoCheck.setOnAction((eventun) -> {
			undoSelected = undoCheck.isSelected();
			redoCheck.setSelected(false);
			redoSelected = false;
		});
		// checkbox for redo
		redoCheck.setOnAction((eventre) -> {
			redoSelected = undoCheck.isSelected();
			undoCheck.setSelected(false);
			undoSelected = false;
		});

		// undo ja redo slideriga

		slider.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (undoSelected && slider.getValue() < sliderValue) {
				undo();
			}
			if (redoSelected && slider.getValue() < sliderValue) {
				redo();
			}
		});

		// WHAT IT IS button
		whatitis.setOnAction((eventwh) -> {

			towhat.add(listX, listY);

			try {
				// open answer "what it is?" in new win
				Compare comp = new Compare(towhat.LineAngles);

				VBox vbox2 = new VBox();

				// Answer windows shows:
				TextArea textbox = new TextArea(towhat.printAngles() + "\n" + comp.showResult());

				textbox.setPrefSize(prefWidth, prefHeight);
				vbox2.getChildren().add(textbox);

				root = vbox2;
				Stage stage = new Stage();
				stage.setTitle("Answer");
				stage.setScene(new Scene(root, prefWidth, prefHeight));
				stage.show();
			} catch (Exception e) {
				System.out.println("What it is nupust, uue akna laadimise viga: " + e);
			}
		});

		// add and show
		vbox.getChildren().add(group2);
		vbox.setAlignment(Pos.CENTER);
		primaryStage.setScene(scene);

		// primaryStage.setResizable(false);
		primaryStage.show();
	}// end start(), next FFFFFFFFFFFFFFFFF methods FFFFFFFFFFFFFFFFFFFFFF

	private void undo() {
		u = group2.getChildren().size();

		try {
			if (1 < u && slider.getValue() >= 0) {

				group2.getChildren().remove(u - 1);// removes last
				// 0.0 means beginning of line
				if (listX.remove(listX.size() - 2 == 0.0)) {
					redoLine.AddX(listX.get((listX.size() - 1)), 1);// adds
																	// points
					redoLine.AddY(listY.get((listY.size() - 1))); // for redo
					listX.remove(listX.size() - 1);
					listY.remove(listY.size() - 1);
					redoLine.AddX(listX.get((listX.size() - 1)), 1);// adds
																	// points
					redoLine.AddY(listY.get((listY.size() - 1))); // for redo
					listX.remove(listX.size() - 1);
					listY.remove(listY.size() - 1);
				} else {
					redoLine.AddX(listX.get((listX.size() - 1)), 1);// adds
																	// points
					redoLine.AddY(listY.get((listY.size() - 1))); // for redo
					listX.remove(listX.size() - 1);
					listY.remove(listY.size() - 1);
				}
				redoCheck.setVisible(true);
				slider.setValue(group2.getChildren().size());
			}
		} catch (Exception ex2) {
			System.out.println("Undo viga: \n" + ex2);
		}

	}

	private void redo() {
		try {

			if (redoLine.NR > 0 && redoLine.getLineX(redoLine.NR - 1) != 0.0) {
				// to screen only if coordinates != 0.0, marking line beginning:
				group2.getChildren().add(new Circle(redoLine.getLineX(redoLine.NR - 1),
						redoLine.getLineY(redoLine.NR - 1), radius, Color.BLACK));
				// to listX and Y
				listX.add(redoLine.getLineX(redoLine.NR - 1));
				listY.add(redoLine.getLineY(redoLine.NR - 1));
				// and remove from redoline
				redoLine.lineX.remove(redoLine.NR - 1);
				redoLine.lineY.remove(redoLine.NR - 1);
				redoLine.NR--;
			}else if (redoLine.NR > 0 && redoLine.getLineX(redoLine.NR - 1) == 0.0) {
			
				// to listX and Y
				listX.add(redoLine.getLineX(redoLine.NR - 1));
				listY.add(redoLine.getLineY(redoLine.NR - 1));
				// and remove from redoline
				redoLine.lineX.remove(redoLine.NR - 1);
				redoLine.lineY.remove(redoLine.NR - 1);
				redoLine.NR--;
			}
		} catch (Exception ex1) {
			System.out.println("Redo viga: \n" + ex1);
		}
	}

}
