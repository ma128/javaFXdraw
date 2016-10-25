import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ProovA extends Application {
	TextField txtin;

	Button enter, yes, no, dontknow, tryagain, btnclear, but1;

	Stage stage2 = new Stage();
	Stage stage1 = new Stage();

	Label lblanswer;

	@Override
	public void start(Stage arg0) {
		// make the controls
		txtin = new TextField();// sisestus
		enter = new Button("Enter");
		yes = new Button("yes");
		no = new Button("no");
		dontknow = new Button("I dont know");
		tryagain = new Button("try again");
		btnclear = new Button("Clear");
		lblanswer = new Label("?");// AI vastus
		// esimene window
		but1 = new Button("Talk to AI");
		but1.setPrefWidth(70);
		but1.setOnAction(e -> code(e));
		HBox hbox = new HBox();
		hbox.getChildren().add(but1);
		hbox.setAlignment(Pos.CENTER);

		// center text in label
		lblanswer.setAlignment(Pos.CENTER);
		// apply ccs-like style to label (yes, you can)
		lblanswer.setStyle("-fx-border-color: #000; -fx-padding: 5px;");

		// make container for app
		GridPane root = new GridPane();
		// put container in middle of scene
		root.setAlignment(Pos.CENTER);
		// setspacing between controls in grid
		root.setHgap(10);
		root.setVgap(10);
		// add to grid, cell by cell
		root.add(yes, 0, 0);
		root.add(no, 1, 0);
		root.add(dontknow, 0, 1);
		root.add(tryagain, 1, 1);

		// last 2 rows span across 2 columns
		// col, rol, colspan, rowspan
		root.add(lblanswer, 0, 2, 2, 2);
		root.add(txtin, 0, 4, 2, 2);

		root.add(enter, 0, 6, 2, 1);
		root.add(btnclear, 1, 6, 2, 1);
		// set widths of all controls in separate method
		setWidths();
		// attach buttons to code in separate method
		attachCode();
		// usual stuff

		Scene scene2 = new Scene(hbox, 200, 200);

		stage2.setTitle("window1");
		stage2.setScene(scene2);

		stage2.show();

		Scene scene = new Scene(root, 400, 200);
		stage1.setTitle("AI 1.0");
		stage1.setScene(scene);

	}

	public void setWidths() {
		// set widths of all controls
		txtin.setPrefWidth(380);
		enter.setPrefWidth(100);
		yes.setPrefWidth(100);
		no.setPrefWidth(100);
		dontknow.setPrefWidth(100);
		tryagain.setPrefWidth(100);
		btnclear.setPrefWidth(100);
		lblanswer.setPrefWidth(380);
	}

	public void attachCode() {
		// have each button run BTNCODE when clicked
		enter.setOnAction(e -> btncode(e));
		yes.setOnAction(e -> btncode(e));
		no.setOnAction(e -> btncode(e));
		dontknow.setOnAction(e -> btncode(e));
		tryagain.setOnAction(e -> btncode(e));
		btnclear.setOnAction(e -> btncode(e));
	}

	//window vahetus
	private void code(ActionEvent e) {
		if (e.getSource() == but1) {
			stage2.hide();
			stage1.show();

		}
	}

	//mis nupp mida teeb
	public void btncode(ActionEvent e) {
		int num1, answer;
		char symbol;
		String string = "";

		// e tells us which button was clicked
		if (e.getSource() == btnclear) {
			txtin.setText("");
			lblanswer.setText("AI: im ready...");
			txtin.requestFocus();
			return;
		}

		// read numbers in from textfields
		string = txtin.getText();

		if (e.getSource() == yes) {
			lblanswer.setText("OK!");
		} else if (e.getSource() == enter) {
			lblanswer.setText("You entered: " + string);
		} else if (e.getSource() == no) {
			lblanswer.setText("Roger! ");
		} else if (e.getSource() == dontknow) {
			lblanswer.setText("So you dont know?");
		} else {// try again button
			lblanswer.setText("Do it again... ");

		}
		// display answer
		// lblanswer.setText("" + num1 + symbol + num2 + "=" + answer);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
