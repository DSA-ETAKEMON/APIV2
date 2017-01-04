package DAO;

import Entity.User;
import Exception.DAOException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Created by hixam on 20/12/16.
 */
public class UsuarioDaoimpl implements UsuarioDAO {

    @Override
    public User createUser(String name, String apellidos, String nick, String email, String password, int totalPokemons, int punctuacionTotal) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        String id = null;
        try {
            User user = getUserByLoginid(nick);
            if (user != null)
                throw new DAOException("usuario ya existe");
            connection = Database.getConnection();
            stmt =  connection.prepareStatement(UserDAOQuery.UUID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                id = rs.getString(1);
            else
                throw new SQLException();
            connection.setAutoCommit(false);
            stmt.close();
            stmt = connection.prepareStatement(UserDAOQuery.CREATE_USER);
            stmt.setString(1, id);
            stmt.setString(2, nick);
            stmt.setString(3, password);
            stmt.setString(4, email);
            stmt.setString(5, name + apellidos);
            stmt.executeUpdate();
            stmt.close();
            stmt = connection.prepareStatement(UserDAOQuery.ASSIGN_ROLE_REGISTERED);
            stmt.setString(1, id);
            stmt.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw e;
        }catch (DAOException e)
        { System.out.println("DAOECEEEEPTION *****"+e.toString());}

        finally {
            if (stmt != null) stmt.close();
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }
        return getUserById(id);    }

    @Override
    public User updateProfile(String id, String email, String fullname) throws SQLException {
        return null;
    }

    @Override
    public User getUserById(String id) throws SQLException {
        return null;
    }

    @Override
    public User getUserByLoginid(String nick) throws SQLException,DAOException {
        return null;
    }

    @Override
    public boolean deleteUser(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean checkPassword(String id, String password) throws SQLException {
        return false;
    }
}
