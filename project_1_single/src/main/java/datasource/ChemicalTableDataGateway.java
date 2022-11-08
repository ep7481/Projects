package datasource;

import DTO.ChemicalDTO;
import exceptions.UnableToConnectException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChemicalTableDataGateway {
    //Find every element that makes up this compound
    //Compound: CO2: Key for CO2 -> madeOfTable(CompoundID); madeOfTable(ElementID) -> elements in Data table

    //Method for getting a list of ID's of elements that make up a compound
    //get all rows from table

    //Take in a list of IDs of elements (gotten from madeof); select all from table where the id matches; return a list of elements
//    ResultSet rs;


    /**
     * Selects a group of chemicals based on ids
     *
     * @param ID - list of ids
     */
    public static List<ChemicalDTO> getChemicals(List<Long> ID) throws Exception {
        JDBC jdbc = JDBC.getJDBC();
        List<ChemicalDTO> chemicalList = new ArrayList<ChemicalDTO>();
        String query = "SELECT * FROM Chemical WHERE ID in (";
        PreparedStatement stmt = jdbc.getConnect().prepareStatement(queryBuilder(ID.size(), query));
        for (int i = 0; i < ID.size(); i++) {
            stmt.setLong(i + 1, ID.get(i));
        }

        stmt.execute();
        ResultSet rs = stmt.getResultSet();
        while (rs.next()) {
            long id = rs.getLong("ID");
            String name = rs.getString("name");
            long atomicNumber = rs.getLong("atomicNumber");
            double atomicMass = rs.getDouble("atomicMass");
            long dissolvedBy = rs.getLong("dissolvedBy");
            long solute = rs.getLong("solute");
            Type type = Type.valueOf(rs.getString("type"));
            chemicalList.add(new ChemicalDTO(id, name, atomicNumber, atomicMass, dissolvedBy, solute, type));
        }

        return chemicalList;
    }


    /**
     * builds an sql statement based on number of ids
     *
     * @param numIDs    - number of ids
     * @param beginning - beginning of string before formatting
     * @return
     */
    public static String queryBuilder(int numIDs, String beginning) {

        StringBuilder sb = new StringBuilder(beginning);

        for (int i = 0; i < numIDs; i++) {
            sb.append(" ?");
            if (i != (numIDs - 1)) {
                sb.append(",");
            }
        }
        sb.append(")");

        return sb.toString();
    }

    public static List<ChemicalDTO> getElementsBetweenRange(int start, int end) throws Exception{
        List<ChemicalDTO> elementList = new ArrayList<ChemicalDTO>();
        JDBC jdbc = JDBC.getJDBC();

        PreparedStatement stmt = jdbc.getConnect().prepareStatement("SELECT * FROM Chemical WHERE atomicNumber BETWEEN ? AND ? ");
        stmt.setInt(1, start);
        stmt.setInt(2, end);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            long id = rs.getLong("id");
            String name = rs.getString("name");
            long atomicNumber = rs.getLong("atomicNumber");
            double atomicMass = rs.getDouble("atomicMass");
            elementList.add(new ChemicalDTO(id, name, atomicNumber, atomicMass, 0, 0, Type.ELEMENT));
        }

        return elementList;


    }

    public static void rollback() throws UnableToConnectException {
        try {
            PreparedStatement delete = JDBC.getJDBC().getConnect().prepareStatement("DELETE FROM Chemical");
            delete.execute();
            PreparedStatement reset = JDBC.getJDBC().getConnect().prepareStatement("ALTER TABLE Chemical AUTO_INCREMENT = 1");
            reset.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UnableToConnectException("Unable to rollback database");
        }
    }


    public static List<ChemicalDTO> getAllChemicals() throws UnableToConnectException {
        try {
            List<ChemicalDTO> chemicalList = new ArrayList<ChemicalDTO>();
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement("SELECT * FROM Chemical");
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                long id = rs.getLong("ID");
                String name = rs.getString("name");
                long atomicMass = rs.getLong("atomicMass");
                long atomicNumber = rs.getLong("atomicNumber");
                chemicalList.add(new ChemicalDTO(id, name, atomicNumber, atomicMass, 0, 0, Type.ELEMENT));
            }
            return chemicalList;
        } catch (SQLException e) {
            throw new UnableToConnectException("Unable to get Chemicals");
        }
    }
}
