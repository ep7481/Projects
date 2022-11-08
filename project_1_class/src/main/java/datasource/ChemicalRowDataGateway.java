package datasource;

import exceptions.AlreadyExistsException;
import exceptions.UnableToConnectException;
import exceptions.UnableToUpdateException;
import exceptions.EntryNotFoundException;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChemicalRowDataGateway {
    long id;
    String name;

    public static final String existsQuery = "SELECT * FROM chemical WHERE name = ?";
    public static final String createQuery = "INSERT INTO chemical (name) VALUES (?)";
    public static final String findQuery = "SELECT * FROM chemical WHERE name = ?";
    public static final String updateQuery = "UPDATE chemical SET name = ? WHERE id = ?";
    public static final String deleteQuery = "DELETE FROM chemical WHERE id = ?";
    public static final String nameFromID = "SELECT * FROM chemical WHERE id = ?";

    public static String getNameFromID(long id) throws SQLException {
        PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(nameFromID, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        stmt.setLong(1, id);
        stmt.execute();
        ResultSet results = stmt.getResultSet();
        results.first();
        return results.getString("name");
    }

    /**
     * Create
     *
     * @param name
     * @return
     * @throws Exception
     */
    public static ChemicalRowDataGateway createChemicalRowDataGateway(String name) throws Exception {
        try {
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(createQuery);
            stmt.setString(1, name);
            stmt.execute();
            return new ChemicalRowDataGateway(name);
        } catch (SQLException UnableToConnect) {
            throw new UnableToConnectException("Unable to create Chemical. Check connection and try again!");
        }
    }

    /**
     * Finder constructor
     *
     * @param name - name of an already created chemical
     * @throws Exception - unable to find the row
     */
    public ChemicalRowDataGateway(String name) throws EntryNotFoundException {
        try {
            PreparedStatement findStatement = null;
            findStatement = JDBC.getJDBC().getConnect().prepareStatement(findQuery, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            findStatement.setString(1, name);
            findStatement.execute();
            ResultSet results = findStatement.getResultSet();
            results.first();
            this.id = results.getLong("id");
            this.name = results.getString("name");
        } catch (SQLException e) {
            throw new EntryNotFoundException("Could not find an entry for this name");
        }

    }

    /**
     * Updates the row in the database
     *
     * @throws Exception - Unable to update the row in the database
     */
    public void persist() throws Exception {
        try {
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(updateQuery);
            stmt.setString(1, name);
            stmt.setLong(2, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new UnableToUpdateException("Unable to update entry!");
        }

    }

    /**
     * Deletes the row in the database
     *
     * @return - true if deletion succeeded
     * @throws Exception - unable to find the row
     */
    public boolean delete() throws EntryNotFoundException {
        try {
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(deleteQuery);
            stmt.setLong(1, id);
            stmt.execute();
        } catch (SQLException entryNotFound) {
            throw new EntryNotFoundException("Could not find entry with this id");
        }
        return true;
    }

    /**
     * Checks if a base with identical parameters already exists
     *
     * @param name - name of the chemical
     * @return - true if it already exists
     * @throws Exception - unable to check the database at all
     */
    private boolean exists(String name) throws UnableToConnectException {
        try {
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(existsQuery);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            return rs.isBeforeFirst();
        } catch (SQLException unableToConnect) {
            throw new UnableToConnectException("Unable to check if Compound exists. Check Connection and try again!");
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
