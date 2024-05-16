import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import controller.FXMLPrincipalController;

public class Principal extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("view/FXMLPrincipal.fxml"));
    /*
     * Codigo abaixo possibilita arrastar a aplicacao com o mouse, ja que
     * o comando que deixa o Stage transparente impossibilita tal acao
     */
    root.setOnMousePressed(pressEvent -> {
      root.setOnMouseDragged(dragEvent -> {
        stage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
        stage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
      });
    });
    Scene tela = new Scene(root);
    // O codigo abaixo torna a Scene e o Stage transparentes para que possa ser
    // usada as bordas arredondadas
    tela.setFill(Color.TRANSPARENT);
    stage.initStyle(StageStyle.TRANSPARENT);
    stage.setScene(tela);
    stage.setTitle("Agenda");
    // Como a aplicacao nao esta responsiva, o codigo abaixo nao permite
    // redimensionar a tela
    stage.resizableProperty().setValue(Boolean.FALSE);
    stage.show();
  }
}
