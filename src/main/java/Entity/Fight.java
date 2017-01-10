package Entity;

import DAO.DAO;

/**
 * Created by hicham.az on 09/12/2016.
 */
public class Fight   extends DAO {

    int id;
    int puntoscontrincantedos;
    int puntoscontrincanteuno;
    int contrincanteuno, contrincantedos;
    String estado1,estado2;
    String juego1,juego2;
    String ganador;

    public Fight() {
    }

    public Fight(int id, int puntosContrincanteDos, int puntosContrincanteUno, int nameContrincanteUno, int nameContrincanteDos) {
        this.id = id;
        puntoscontrincantedos = puntosContrincanteDos;
        puntoscontrincanteuno = puntosContrincanteUno;
        contrincanteuno = nameContrincanteUno;
        contrincantedos = nameContrincanteDos;

    }

    public String getEstado1() {
        return estado1;
    }

    public void setEstado1(String estado1) {
        this.estado1 = estado1;
    }

    public String getEstado2() {
        return estado2;
    }

    public void setEstado2(String estado2) {
        this.estado2 = estado2;
    }

    public String getJuego1() {
        return juego1;
    }

    public void setJuego1(String juego1) {
        this.juego1 = juego1;
    }

    public String getJuego2() {
        return juego2;
    }

    public void setJuego2(String juego2) {
        this.juego2 = juego2;
    }

    public String getGanador() {
        return ganador;
    }

    public void setGanador(String ganador) {
        this.ganador = ganador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPuntoscontrincantedos() {
        return puntoscontrincantedos;
    }

    public void setPuntoscontrincantedos(int puntoscontrincantedos) {
        this.puntoscontrincantedos = puntoscontrincantedos;
    }

    public int getPuntoscontrincanteuno() {
        return puntoscontrincanteuno;
    }

    public void setPuntoscontrincanteuno(int puntoscontrincanteuno) {
        this.puntoscontrincanteuno = puntoscontrincanteuno;
    }

    public int getContrincanteuno() {
        return contrincanteuno;
    }

    public void setContrincanteuno(int contrincanteuno) {
        this.contrincanteuno = contrincanteuno;
    }

    public int getContrincantedos() {
        return contrincantedos;
    }

    public void setContrincantedos(int contrincantedos) {
        this.contrincantedos = contrincantedos;
    }

}
