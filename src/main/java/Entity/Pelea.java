package Entity;

/**
 * Created by hicham.az on 09/12/2016.
 */
public class Pelea {

    int Id;
    int PuntosContrincanteDos;
    int PuntosContrincanteUno;
    String NameContrincanteUno,NameContrincanteDos;
    Etakemons ContrincanteUno;
    Etakemons ContrincanteDos;

    public Pelea() {
    }

    public Pelea(int id, int puntosContrincanteDos, int puntosContrincanteUno, String nameContrincanteUno, String nameContrincanteDos, Etakemons contrincanteUno, Etakemons contrincanteDos) {
        Id = id;
        PuntosContrincanteDos = puntosContrincanteDos;
        PuntosContrincanteUno = puntosContrincanteUno;
        NameContrincanteUno = nameContrincanteUno;
        NameContrincanteDos = nameContrincanteDos;
        ContrincanteUno = contrincanteUno;
        ContrincanteDos = contrincanteDos;
    }



    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getPuntosContrincanteDos() {
        return PuntosContrincanteDos;
    }

    public void setPuntosContrincanteDos(int puntosContrincanteDos) {
        PuntosContrincanteDos = puntosContrincanteDos;
    }

    public int getPuntosContrincanteUno() {
        return PuntosContrincanteUno;
    }

    public void setPuntosContrincanteUno(int puntosContrincanteUno) {
        PuntosContrincanteUno = puntosContrincanteUno;
    }

    public String getNameContrincanteUno() {
        return NameContrincanteUno;
    }

    public void setNameContrincanteUno(String nameContrincanteUno) {
        NameContrincanteUno = nameContrincanteUno;
    }

    public String getNameContrincanteDos() {
        return NameContrincanteDos;
    }

    public void setNameContrincanteDos(String nameContrincanteDos) {
        NameContrincanteDos = nameContrincanteDos;
    }

    public Etakemons getContrincanteUno() {
        return ContrincanteUno;
    }

    public void setContrincanteUno(Etakemons contrincanteUno) {
        ContrincanteUno = contrincanteUno;
    }

    public Etakemons getContrincanteDos() {
        return ContrincanteDos;
    }

    public void setContrincanteDos(Etakemons contrincanteDos) {
        ContrincanteDos = contrincanteDos;
    }


}
