package addressbookworkshop;

import org.junit.Assert;
import org.junit.Test;
import java.util.List;
public class AddressBookServiceTest
{
    @Test
    public void givenAddressBookContactsInDB_WhenRetrieved_ShouldMatchContactsCount() throws AddressBookException {
        AddressBookJDBC addressbookJDBC = new AddressBookJDBC();
        List<Contact> addressbook = addressbookJDBC.readAddressBookData(AddressBookJDBC.IOService.DB_IO);
        Assert.assertSame(3, addressbook);
    }
    @Test
    public void givenAddressBook_WhenUpdate_ShouldSyncWithDB() throws AddressBookException {
        AddressBookJDBC addressBookJDBC = new AddressBookJDBC();
        addressBookJDBC.updateRecord("Nethaji", "GKW Lauout");
        boolean result = addressBookJDBC.checkUpdatedRecordSyncWithDatabase("Nethaji");
        Assert.assertTrue(result);
    }
    @Test
    public void givenAddressBook_WhenRetrieved_ShouldMatchCountInGivenRange() throws AddressBookException {
        AddressBookJDBC addressBookJDBC = new AddressBookJDBC();
        List<Contact> addressBookData = addressBookJDBC.readAddressBookData(AddressBookJDBC.IOService.DB_IO, "2018-05-14", "2020-09-02");
        Assert.assertEquals(0, addressBookData.size());
    }
    @Test
    public void givenAddressBook_WhenRetrieved_ShouldReturnCountOfCity() throws AddressBookException {
        AddressBookJDBC addressBookJDBC = new AddressBookJDBC();
        Assert.assertEquals(2, addressBookJDBC.readAddressBookData("count", "Hyderabad"));
    }
}