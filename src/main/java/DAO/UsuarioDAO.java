package DAO;

import Entity.User;
import Exception.DAOException;
import java.sql.SQLException;

/**
 * Created by hicham.az on 09/12/2016.
 */
public interface UsuarioDAO {

    User createUser(String name,String apellidos, String nick, String email, String password,int totalPokemons, int punctuacionTotal) throws SQLException,DAOException;
    User updateProfile(String id, String email, String fullname) throws SQLException;
    User getUserById(String id) throws SQLException;
    User getUserByLoginid(String nick) throws SQLException, DAOException;
    boolean deleteUser(String id) throws SQLException;
    boolean checkPassword(String id, String password) throws SQLException;
}
