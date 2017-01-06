package Entity;

import DAO.DAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hicham.az on 09/12/2016.
 */
public class Etakemons extends DAO {

    int id;
int puntos;
    String tipo, name;

/*
    public Etakemons(int id, String tipo, String nombre, String puntos) {
        this.id = id;
        tipo = tipo;
        nombre = nombre;
        puntos = puntos;
    }
*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public Etakemons (){}
/*
    public List<Etakemons> getEtakemons(String nick)
    {
        List<Etakemons> miLista = new ArrayList<Etakemons>();
        Etakemons etk = new Etakemons();
        miLista = etk.selectEtakemonByUser(nick);
        return miLista;
    }*/
/*
    public Etakemons getEtakemon(int id)
    {
        Etakemons etk  = new Etakemons();
        return etk.selectUnEtakemon(id);
    }*/

}
