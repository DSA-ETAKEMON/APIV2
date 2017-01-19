package DAO;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.Properties;

/**
 * Created by hixam on 21/12/16.
 */
@SuppressWarnings("StringBufferReplaceableByString")
public class DAOConnection {

    public Connection getConnection() {
        Connection con = null;
        String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost:3306/projecteDB";
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "hicham");
        properties.setProperty("useSSL", "false");
        properties.setProperty("serverTimezone", "UTC");

        try{
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, properties);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Connected to database");
        return con;
    }

    public String getInsertQuery() {
        StringBuffer query = new StringBuffer("INSERT INTO ");
        query.append(this.getClass().getSimpleName());
        query.append(" (");
        addFieldsInsertQuery(query);
        query.append(")");
        query.append(" VALUES (");
        addInterrogantsInsertQuery(query);
        query.append(")");
        return query.toString();
    }

    public String getSelectQuery(String where, String someThing) {
        StringBuilder query = new StringBuilder("SELECT * FROM ");
        query.append(this.getClass().getSimpleName());
        query.append(" WHERE " + where + " = '" + someThing+"'");
        return query.toString();
    }
    public String getSelectQuery(String where, int someThing) {
        StringBuilder query = new StringBuilder("SELECT * FROM ");
        query.append(this.getClass().getSimpleName());
        query.append(" WHERE " + where + " = "  + someThing);
        return query.toString();
    }

    public String getSelectQuery(String where, int someThing,String where2, String someThing2) {
        StringBuilder query = new StringBuilder("SELECT * FROM ");
        query.append(this.getClass().getSimpleName());
        query.append(" WHERE " + where + " = "  + someThing + " AND " + where2 + " = '" + someThing2+"'");
        return query.toString();
    }

    public String getSelectQueryByNick() {
        StringBuilder query = new StringBuilder("SELECT * FROM ");
        query.append(this.getClass().getSimpleName());
        query.append(" WHERE nick=");
        return query.toString();
    }
    public String getSelectQueryBy(String select) {
        StringBuilder query = new StringBuilder("SELECT * FROM ");
        query.append(this.getClass().getSimpleName());
        query.append(" WHERE "+ select+ " =");
        return query.toString();
    }

    public String getSelectQueryByIDUser(String where,int someThing) {
        StringBuilder query = new StringBuilder("SELECT * FROM ");
        query.append(this.getClass().getSimpleName());
        query.append(" WHERE "+ where + " = " + someThing);
        return query.toString();
    }

    public String getUpdateQuery(String where, String update) {
        StringBuffer query = new StringBuffer("UPDATE ");
        query.append(this.getClass().getSimpleName());
        query.append(" SET ");
        addFieldsAndInterrogantsUpdateQuery(query);
        query.append(" WHERE "+ where + "=" + "'"+update+"'");
        return query.toString();
    }

    public String getUpdateQuery(String update,String dato, String where, String someThing) {
        StringBuffer query = new StringBuffer("UPDATE ");
        query.append(this.getClass().getSimpleName());
        query.append(" SET ");
        query.append(update+ " = '"  +dato+"'");
       // addFieldsAndInterrogantsUpdateQuery(query);
        query.append(" WHERE "+ where + " = '"  +someThing+"'");
        return query.toString();
    }

    public String getDeleteQuery() {
        StringBuilder query = new StringBuilder("DELETE FROM ");
        query.append(this.getClass().getSimpleName());
        query.append(" WHERE id=?");
        return query.toString();
    }

    public String getSelectAllQuery() {
        StringBuilder query = new StringBuilder("SELECT * FROM ");
        query.append(this.getClass().getSimpleName());
        return query.toString();
    }

    public String getSelectAllQueryOrderBy(String orderby) {
        StringBuilder query = new StringBuilder("SELECT * FROM ");
        query.append(this.getClass().getSimpleName());
        query.append(" ORDER BY " + orderby + " DESC");
        return query.toString();
    }

    private void addFieldsInsertQuery(StringBuffer query) {
        for(Field f : this.getClass().getDeclaredFields()) {
            query.append(f.getName()).append(",");
        }
        query.deleteCharAt(query.length() - 1);
    }

    private void addInterrogantsInsertQuery(StringBuffer query) {
        for(Field ignore : this.getClass().getDeclaredFields()) {
            query.append("?,");
        }
        query.deleteCharAt(query.length() - 1);
    }

    public void addClassFieldsParameters(PreparedStatement pstm) {
        int i=1;
        for(Field field : this.getClass().getDeclaredFields()) {
            try {
                Method method = this.getClass().getMethod(getGetterName(field.getName()));
                Object object = method.invoke(this);
                pstm.setObject(i,object);
                i++;
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public String getGetterName(String fieldName) {
        StringBuilder getterName = new StringBuilder("get");
        getterName.append(capitalizeName(fieldName));
        return getterName.toString();
    }

    public String getSetterName(String fieldName) {
        StringBuilder setterName = new StringBuilder("set");
        setterName.append(capitalizeName(fieldName));
        return setterName.toString();
    }

    public String capitalizeName (String name) {
        String capitalizedFieldName;
        capitalizedFieldName = name.substring(0,1).toUpperCase() + name.substring(1);
        return capitalizedFieldName;
    }

    private void addFieldsAndInterrogantsUpdateQuery(StringBuffer query) {
        for(Field f : this.getClass().getDeclaredFields()) {
            query.append(f.getName());
            query.append("=?,");
        }
        query.deleteCharAt(query.length() - 1);
    }

    public void addPrimaryKeyParameter(PreparedStatement pstm, int position, int primaryKey) {
        try {
            pstm.setObject(position, primaryKey);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getPrimaryKeyParameter() {
        Method method;
        int id = 0;
        try {
            method = this.getClass().getMethod(getGetterName("id"));
            Object object = method.invoke(this);
            id = Integer.parseInt(object.toString());
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void setStringField (String resultString, String name, Object object) {
        Method method;
        try {
            method = object.getClass().getMethod(getSetterName(name), resultString.getClass());
            method.invoke(object, resultString);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void setIntField (int resultId, String name, Object object) {
        Method method;
        try {
            Class[] arguments = new Class[1];
            arguments[0] = int.class;
            method = object.getClass().getMethod(getSetterName(name), arguments);
            method.invoke(object, resultId);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void setFieldsFromResultSet(ResultSet resultSet, ResultSetMetaData resultSetMetaData, Object object) {
        try {
            for(int i=1; i <= resultSetMetaData.getColumnCount(); i++) {
                String columnType = resultSetMetaData.getColumnTypeName(i);
                String columnName = resultSetMetaData.getColumnLabel(i);
                switch(columnType) {
                    case "VARCHAR":
                        String resultString = resultSet.getString(i);
                        setStringField(resultString, columnName, object);
                        break;
                    case "INT":
                        int resultInt = resultSet.getInt(i);
                        setIntField(resultInt, columnName, object);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
