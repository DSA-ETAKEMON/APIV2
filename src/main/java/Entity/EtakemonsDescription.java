package Entity;

import DAO.DAO;

/**
 * Created by OriolGresa on 20/1/17.
 */
public class EtakemonsDescription extends DAO {
    int id,idetakemon;
    String poder;

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

    public String getPoder() {
        return poder;
    }

    public void setPoder(String poder) {
        this.poder = poder;
    }
}
