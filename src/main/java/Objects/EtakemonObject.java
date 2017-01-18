package Objects;

import Entity.Etakemons;
import logica.Etakemon;

/**
 * Created by hixam on 17/01/17.
 */
public class EtakemonObject {


    int id;
    int puntos;
    String tipo, name;


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

    public EtakemonObject (Etakemons e){
        this.id = e.getId();
        tipo = e.getTipo();
        name = e.getName();
        puntos = e.getPuntos();
    }
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
