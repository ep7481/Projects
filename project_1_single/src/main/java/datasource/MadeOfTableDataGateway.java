package datasource;

import DTO.MadeOfDTO;
import exceptions.EntryNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import java.util.ArrayList;

public class MadeOfTableDataGateway {
    //Take ID of compound from Chemical, get all ids of elements that make up this compound; return as list
    //Multiple entries for each compound, each containing a different element ID. (Many-to-many)



    private static final String getElementIDs = "SELECT * FROM madeOf WHERE compoundID = ?";
    private static final String getCompoundIDs = "SELECT * FROM madeOf WHERE elementID = ?";
    private static final String createNewCompound = "INSERT INTO madeOf VALUES ( ?, ?)";

    /**
     * finds all of the elements that make up a compound
     *
     * @param CompoundID - id being searched for
     * @throws Exception - throws when compound isnt found
     */
    public static List<String> findElements(long CompoundID) throws EntryNotFoundException{
        try {
            JDBC jdbc = JDBC.getJDBC();
            PreparedStatement stmt = jdbc.getConnect().prepareStatement(getElementIDs);
            stmt.setLong(1, CompoundID);
            ResultSet rs = stmt.executeQuery();

            List<Long> elements = new ArrayList<>();
            while(rs.next()){
                elements.add(rs.getLong("elementID"));
            }

            List<String> names = new ArrayList<>();

            for (int i = 0; i < elements.size(); i++) {
                names.add(ChemicalRowDataGateway.getNameFromID(elements.get(i)));
            }
            return names;

        } catch (SQLException e) {
            throw new EntryNotFoundException("This Compound could not be found");
        }

    }

    /**
     * Finds all of the compounds that contain an element
     *
     * @param elementID - id of element
     * @return - boolean if this is successful or not
     * @throws Exception - throws when element isnt found
     */
    public static List<MadeOfDTO> findCompoundsWithOneElement(long elementID) throws Exception {
        //get list of DTO's (compounds) that have a certain element in it.
        try {
            JDBC jdbc = JDBC.getJDBC();
            PreparedStatement stmt = jdbc.getConnect().prepareStatement(getCompoundIDs);
            stmt.setLong(1, elementID);
            ResultSet rs = stmt.executeQuery();
            List<Long> elementIDs = new ArrayList<Long>();
            List<MadeOfDTO> listOfDTOs = new ArrayList<>();

            while (rs.next()) {
                elementIDs.add(rs.getLong("elementID"));
                MadeOfDTO DTO = new MadeOfDTO(rs.getLong("compoundID"), elementIDs);
                listOfDTOs.add(DTO);
            }
            return listOfDTOs;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntryNotFoundException("This Element could not be found");
        }
    }

    public static List<String> findCompounds(long elementID) throws EntryNotFoundException {
        //get list of DTO's (compounds) that have a certain element in it.
        try {
            JDBC jdbc = JDBC.getJDBC();
            PreparedStatement stmt = jdbc.getConnect().prepareStatement(getCompoundIDs);
            stmt.setLong(1, elementID);
            ResultSet rs = stmt.executeQuery();
            List<Long> compounds = new ArrayList<>();
            while (rs.next()) {
                long compound = rs.getLong("compoundID");
                compounds.add(compound);
            }

            List<String> names = new ArrayList<>();

            for (int i = 0; i < compounds.size(); i++) {
                String name = ChemicalRowDataGateway.getNameFromID(compounds.get(i));
                if (names.contains(name)) {
                    continue;
                }
                names.add(name);
            }
            return names;

        } catch (SQLException e) {
            throw new EntryNotFoundException("Compounds could not be found");
        }
    }

    /**
     * Creates a new compound
     *
     * @param compoundID - id of compound in Chemical table
     * @param elementID -  first element that makes up the compound
     * @throws Exception - throws when breaks foreign key rules
     */
    public static void CreateNewCompound(long compoundID, long elementID) throws SQLException {
        //creates a new compound entry with its elements
        try {
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(createNewCompound);
            stmt.setLong(1, compoundID);
            stmt.setLong(2, elementID);
            stmt.execute();

        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    /**
     * Deletes an element based on a certain compound
     *
     * @param id - id of compound
     * @return - boolean whether successful or not
     * @throws Exception - Throws when entry could not be found
     */
    public static void deleteElements(long id) throws Exception {
        //delete all elements in a certain compound
        //DELETE
        Statement deleteStatement = null;
        try {
            JDBC jdbc = JDBC.getJDBC();
            deleteStatement = jdbc.getConnect().createStatement();
            String deleteString = "DELETE FROM madeOf WHERE elementID = " + Long.toString(id);
            deleteStatement.execute(deleteString);

        } catch (SQLException entryNotFound) {
            entryNotFound.printStackTrace();
            throw new EntryNotFoundException("Could not find entry with this id");
        }
    }

    /**
     * Deletes an element from a compound
     *
     * @param id - id of element being removed
     * @return - boolean whether successful or not
     * @throws Exception - throws when entry for element isn't found
     */
    public static void deleteCompounds(long id) throws Exception {
        Statement deleteStatement = null;
        try {
            JDBC jdbc = JDBC.getJDBC();
            deleteStatement = jdbc.getConnect().createStatement();
            String deleteString = "DELETE FROM madeOf WHERE compoundID = " + Long.toString(id);
            deleteStatement.execute(deleteString);

        } catch (SQLException entryNotFound) {
            entryNotFound.printStackTrace();
            throw new EntryNotFoundException("Could not find entry with this id");
        }
    }






}
