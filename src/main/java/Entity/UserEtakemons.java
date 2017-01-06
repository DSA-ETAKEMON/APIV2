package Entity;

/**
 * Created by hixam on 6/01/17.
 */
public class UserEtakemons extends DAO.DAO{
    int id;
    int iduser;
    int idetakemon;

    public int getIdetakemon() {
        return idetakemon;
    }

    public void setIdetakemon(int idetakemon) {
        this.idetakemon = idetakemon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }
}
