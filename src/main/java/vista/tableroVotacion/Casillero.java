package vista.tableroVotacion;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import vista.IntField;


public class Casillero extends VBox {

    private IntField casilla;
    private CheckBox bonificacion;
    private Label nombre;

    public Casillero(String nombre) {

        this.nombre = new Label(nombre);
        casilla = new IntField(0, 9, 0);
        this.inicializar();
        this.estilizarCasilla();

    }

    private void inicializar() {
        this.getChildren().addAll(this.nombre, this.casilla);
//        this.getChildren().addAll( this.casilla);
        this.invisibilizarNumeroPatron();
        this.setAlignment(Pos.TOP_CENTER);
//        this.setSpacing(2);
    }

    private void invisibilizarNumeroPatron() {
        if (this.nombre.getText().length() <= 1 ){
            this.nombre.setVisible(false);
        }
    }


    private void estilizarCasilla() {
        this.casilla.getStyleClass().add("casilla-puntaje");
        this.casilla.setAlignment(Pos.CENTER);
//        this.nombre.setFont(Font.font("Verdana", 10));
//        this.getStyleClass().add("casillero");

        this.casilla.setMaxWidth(15);
        this.casilla.setMaxHeight(15);
//        this.casilla.setFont(Font.font("Verdana", 10));

    }

    public void habilitarBonificacion() {
        this.bonificacion = new CheckBox();
        this.getChildren().add(this.bonificacion);
    }

    public int getValue() {
        return this.casilla.getValue();
    }

    public void setOnActionParaBonificacion(EventHandler<ActionEvent> event){
        if(this.bonificacion != null){
            this.bonificacion.setOnAction( event);
        }
    }

    public void setOnKeyReleasedParaCasilla(EventHandler eventHandler) {
        this.casilla.setOnKeyReleased(eventHandler);
        this.casilla.setOnKeyTyped(eventHandler);

    }
}