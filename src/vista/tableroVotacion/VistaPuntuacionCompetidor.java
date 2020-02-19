package vista.tableroVotacion;

import controlador.VistaPuntuacionCompetidorEventHandler;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class VistaPuntuacionCompetidor extends HBox {

    private Label nombreCompetidor;
    private HBox casillasPrincipales;
    private HBox casillasEspeciales;
    private CasilleroAcumulador puntajeAcumulado;

    public VistaPuntuacionCompetidor(String nombreCompetidor){

        this.nombreCompetidor = new Label(nombreCompetidor);
        this.casillasEspeciales = new HBox();
        this.casillasPrincipales = new HBox();
        this.puntajeAcumulado = new CasilleroAcumulador(nombreCompetidor, "Total");
        this.inicializar();
    }

    private void inicializar() {
        this.getChildren().add(nombreCompetidor);
        this.setSpacing(20);
        this.setAlignment(Pos.CENTER);

        this.setOnKeyReleased(new VistaPuntuacionCompetidorEventHandler());

    }

    public void inicializarParaCantRounds(int cantRounds) {
        this.casillasPrincipales.setSpacing(5);
        ObservableList<Node> children = this.casillasPrincipales.getChildren();

        for( int i = 1; i<= cantRounds; i++){

            Casillero casillero = new Casillero(Integer.toString(i),this.nombreCompetidor.getText());
            children.add(casillero);
        }
        this.getChildren().add(this.casillasPrincipales);
        this.inicializarCasillasEspeciales();
    }

    private void inicializarCasillasEspeciales(){
        this.casillasEspeciales.setSpacing(5);
        ObservableList<Node> children = this.casillasEspeciales.getChildren();
        Casillero flow = new Casillero("Flow", this.nombreCompetidor.getText());
        Casillero skill = new Casillero("Skill", this.nombreCompetidor.getText());
        Casillero ptaEscena = new Casillero("P.esc", this.nombreCompetidor.getText());

        this.getChildren().addAll(flow,skill,ptaEscena, this.puntajeAcumulado);
    }

    public String getCompetidor(){
        return this.nombreCompetidor.getText();
    }


    public void setPuntaje(int puntaje) {
        this.puntajeAcumulado.setPuntaje(puntaje);
    }
}