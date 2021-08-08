package addressbookworkshop;

import java.time.LocalDate;
import java.util.List;

public class AddressBookJDBC {
    public enum IOService {
        DB_IO
    }

    private List<Contact> addressBookList;
    private static AddressBookService addressBookDBService;

    public AddressBookJDBC() {
        addressBookDBService = AddressBookService.getInstance();
    }

    public AddressBookJDBC(List<Contact> addresBookList) {
        this();
        this.addressBookList = addressBookList;
    }

    public List<Contact> readAddressBookData(IOService ioservice) throws AddressBookException {
        if (ioservice.equals(IOService.DB_IO))
            this.addressBookList = addressBookDBService.readData();
        return this.addressBookList;
    }
    public void updateRecord(String firstname, String address) throws AddressBookException {
        int result = addressBookDBService.updateAddressBookData(firstname, address);
        if (result == 0)
            return;
        Contact addressBookData = this.getAddressBookData(firstname);
        if (addressBookData != null)
            addressBookData.setAddress(address);
    }

    public boolean checkUpdatedRecordSyncWithDatabase(String firstname) throws AddressBookException {
        try {
            List<Contact> addressBookData = addressBookDBService.getAddressBookData(firstname);
            return addressBookData.get(0).equals(getAddressBookData(firstname));
        } catch (AddressBookException e) {
            throw new AddressBookException(e.getMessage(), AddressBookException.ExceptionType.DATABASE_EXCEPTION);
        }
    }

    private Contact getAddressBookData(String firstname) {
        for (Contact addressBookItem : addressBookList) {
            if (addressBookItem.getFirstName().equals(firstname)) {
                return addressBookItem;
            }
        }
        return null;
    }
    public List<Contact> readAddressBookData(IOService ioService, String start, String end)
            throws AddressBookException {
        try {
            LocalDate startLocalDate = LocalDate.parse(start);
            LocalDate endLocalDate = LocalDate.parse(end);
            if (ioService.equals(IOService.DB_IO))
                return addressBookDBService.readData(startLocalDate, endLocalDate);
            return this.addressBookList;
        } catch (AddressBookException e) {
            throw new AddressBookException(e.getMessage(), AddressBookException.ExceptionType.DATABASE_EXCEPTION);
        }
    }
}