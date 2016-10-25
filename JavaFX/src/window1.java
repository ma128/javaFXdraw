import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class window1 extends Application {
	// siia kõik muutujad mis peavad jõudma kõikide selle klassi meetoditeni
	Button but1;
	Stage stage2 = new Stage();

	@Override
	public void start(Stage arg0) throws Exception {
		but1 = new Button("TALK to AI");
		but1.setPrefWidth(70);
		but1.setOnAction(e -> code(e));
		HBox hbox = new HBox();
		hbox.getChildren().add(but1);
		hbox.setAlignment(Pos.CENTER);

		Scene scene2 = new Scene(hbox, 200, 200);
		stage2.setTitle("window1");
		stage2.setScene(scene2);

		stage2.show();

	}

	private void code(ActionEvent e) {
		if (e.getSource() == but1) {
			stage2.close();
			
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
