package datasource;

import DTO.BaseDTO;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BaseGatewayTest extends TestCase{

    // Row Data Gateways
    @Test
    public void testCreate() throws Exception{
        ChemicalRowDataGateway testerChem1 = ChemicalRowDataGateway.createChemicalRowDataGateway("TestChem1");
        ChemicalRowDataGateway testerChem2 = ChemicalRowDataGateway.createChemicalRowDataGateway("TestChem2");
        BaseRowDataGateway tester = new BaseRowDataGateway(testerChem1.getId(), testerChem2.getId());
        assertNotNull(tester);

        testerChem1.delete();
        testerChem2.delete();

    }

    @Test
    public void testFinder() throws Exception{
        ChemicalRowDataGateway testerChem1 = ChemicalRowDataGateway.createChemicalRowDataGateway("TestChem1");
        ChemicalRowDataGateway testerChem2 = ChemicalRowDataGateway.createChemicalRowDataGateway("TestChem2");
        BaseRowDataGateway tester = new BaseRowDataGateway(testerChem1.getId(), testerChem2.getId());
        BaseRowDataGateway finder = new BaseRowDataGateway(tester.getId());

        assertEquals(testerChem1.getId(), finder.getId());
        assertEquals(testerChem2.getId(), finder.getSolute());
        testerChem1.delete();
        testerChem2.delete();

    }

    @Test
    public void testPersist() throws Exception{
        ChemicalRowDataGateway testerChem1 = ChemicalRowDataGateway.createChemicalRowDataGateway("TestChem1");
        ChemicalRowDataGateway testerChem2 = ChemicalRowDataGateway.createChemicalRowDataGateway("TestChem2");
        BaseRowDataGateway tester = new BaseRowDataGateway(testerChem1.getId(), testerChem1.getId());

        tester.setSolute(testerChem2.getId());

        tester.persist();

        assertEquals(testerChem2.getId(), tester.getSolute());

        testerChem1.delete();
        testerChem2.delete();

    }

    //Table Data Gateway tests
    @Test
    public void testGetBases() throws Exception{
        ChemicalRowDataGateway chemical1 = ChemicalRowDataGateway.createChemicalRowDataGateway("chemical1");
        ChemicalRowDataGateway chemical2 = ChemicalRowDataGateway.createChemicalRowDataGateway("chemical2");
        ChemicalRowDataGateway chemical3 = ChemicalRowDataGateway.createChemicalRowDataGateway("chemical3");
        ChemicalRowDataGateway chemical4 = ChemicalRowDataGateway.createChemicalRowDataGateway("chemical4");

        BaseRowDataGateway base1 = new BaseRowDataGateway(chemical1.getId(), chemical2.getId());
        BaseRowDataGateway base2 = new BaseRowDataGateway(chemical3.getId(), chemical4.getId());

        List<Long> listOfIDs = new ArrayList<Long>();
        listOfIDs.add(base1.getId());
        listOfIDs.add(base2.getId());
        List<BaseDTO> baseList = BaseTableDataGateway.getBases(listOfIDs);

        assertEquals(base1.getSolute(), baseList.get(0).getSolute());
        assertEquals(base2.getSolute(), baseList.get(1).getSolute());

        chemical1.delete();
        chemical2.delete();
        chemical3.delete();
        chemical4.delete();
    }
}
