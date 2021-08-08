package addressbookworkshop;

import java.util.List;

public class AddressBookJDBC {
    public enum IOService {
        DB_IO
    }

    private List<Contact> addressBookList;
    private static AddressBookService  addressBookDBService;

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
}