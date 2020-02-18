package controlador;

import animatefx.animation.Pulse;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.FormatoFMS;
import vista.AlertaBatalla;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorVistaPrincipal implements Initializable {

    private String pais;


    @FXML private VBox vistaConfiguracion;
    private VBox vistaImagenCompetencia;
    @FXML private Label labelSeleccionPais;
    @FXML private JFXTextField txtFieldMC1;
    @FXML private JFXTextField txtFieldMC2;
    @FXML private ImageView imgFMS;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String prompTextField = "Ingrese un nombre..";
        this.vistaConfiguracion.setVisible(false);
        this.txtFieldMC1.setPromptText(prompTextField);
        this.txtFieldMC2.setPromptText(prompTextField);

    }

    public void nuevaBatallaButtonClicked(){

        this.vistaConfiguracion.setVisible(true);
        this.imgFMS.setVisible(false);
    }

    public void cargarBatallaButtonClicked(){

        this.vistaConfiguracion.setVisible(false);
        this.imgFMS.setVisible(true);
    }

    @FXML
    public void comenzarBatallaButtonClicked(ActionEvent event) throws IOException {
        if(this.pais == null){

            AlertaBatalla alert = new AlertaBatalla(Alert.AlertType.ERROR);

            alert.setTitle("Información");
            alert.setHeaderText("No se puede comenzar la batalla.");
            alert.setContentText("Debe seleccionar un país para continuar.");
            alert.show();
            return;
        }

        this.verificarTxtFieldMC(this.txtFieldMC1,1);
        this.verificarTxtFieldMC(this.txtFieldMC2,2);

        FormatoFMS app = FormatoFMS.getInstance();
        app.iniciarBatallaNuevaEnPais(this.pais, this.txtFieldMC1.getText(), this.txtFieldMC2.getText());

//        Parent vistaVotacionParent = FXMLLoader.load(getClass().getResource("../vista/vistaVotacion.fxml"));
//        Scene vistaVotacionScene = new Scene(vistaVotacionParent);
//        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//        window.setScene(vistaVotacionScene);

        CambioDeEscena cambioDeEscena = new CambioDeEscena("../vista/vistaVotacion.fxml",
                (Node) event.getSource());

        cambioDeEscena.cambiarEscena();

        System.out.println("Pais: " + app.getPais());
        System.out.println("Competidor1: " + app.getCompetidorQueAtaca());
        System.out.println("Competidor2: "+  app.getCompetidorQueResponde());


    }

    private void verificarTxtFieldMC(JFXTextField txtFieldMC, int nroCompetidor) {
        if(txtFieldMC.getText().isEmpty())
           txtFieldMC.setText("MC" + nroCompetidor);
    }

    @FXML
    public void iluminar(MouseEvent mouseEvent){
        vistaImagenCompetencia = (VBox) mouseEvent.getSource();
        vistaImagenCompetencia.setEffect(new Glow());

    }

    @FXML
    public void deshacerIluminacion(MouseEvent mouseEvent){
        vistaImagenCompetencia = (VBox) mouseEvent.getSource();
        vistaImagenCompetencia.setEffect(null);

    }


    @FXML
    private void imagenCompetenciaClicked(MouseEvent mouseEvent){
        vistaImagenCompetencia = (VBox) mouseEvent.getSource();
        new Pulse(vistaImagenCompetencia).play();
        ObservableList<Node> children = vistaImagenCompetencia.getChildren();
        children.forEach(node -> {
            if (node instanceof Label) {

                String paisSeleccionado =  ((Label) node).getText();
                this.pais = paisSeleccionado;
                this.labelSeleccionPais.setText("Pais seleccionado: "+ this.pais);

            }
        });


    }



}
