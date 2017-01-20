package Objects;

import Entity.Etakemons;
import Entity.EtakemonsDescription;

/**
 * Created by OriolGresa on 20/1/17.
 */
public class EtakemonsDescriptionObject {
    int id,idetakemon;
    String poder;

    public EtakemonsDescriptionObject (EtakemonsDescription e){
        this.id = e.getId();
        this.poder = e.getPoder();

        this.idetakemon= e.getIdetakemon();
    }
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
