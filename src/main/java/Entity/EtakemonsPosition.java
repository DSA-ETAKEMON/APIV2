package Entity;

import DAO.DAO;

import java.math.BigDecimal;

/**
 * Created by OriolGresa on 22/1/17.
 */
public class EtakemonsPosition extends DAO{
    int id, idetakemon;
    float lat,lng;
    String tipoetakemon;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdetakemon() {
        return idetakemon;
    }

    public void setIdetakemon(int idetakemon) {
        this.idetakemon = idetakemon;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public String getTipoetakemon() {
        return tipoetakemon;
    }

    public void setTipoetakemon(String tipoetakemon) {
        this.tipoetakemon = tipoetakemon;
    }



}
