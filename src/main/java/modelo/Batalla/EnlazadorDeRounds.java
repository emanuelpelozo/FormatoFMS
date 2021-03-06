package modelo.Batalla;

import modelo.Batalla.Rounds.Round;

import java.util.ArrayList;

public class EnlazadorDeRounds {

    public void enlazarRounds(ArrayList<Round> rounds){
        for(int i =1 ; i < rounds.size(); i++){

            this.enlazarParRounds(rounds.get(i-1), rounds.get(i));
        }
    }

    private void enlazarParRounds(Round round1, Round round2){
        round1.setSiguiente(round2);
        round2.setAnterior(round1);
    }

}
