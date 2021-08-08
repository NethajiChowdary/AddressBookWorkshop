package addressbookworkshop;

import com.google.gson.Gson;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;


public class AddressBookService
{
	private List<Contact> addressBookData;
    private PreparedStatement addressBookPreparedStatement;
    public HashMap<Integer,Contact> contacts = new HashMap<>();

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Contact> contactlist = new ArrayList<>();
    private Map<String, ArrayList<Contact>> AddressBooks = new HashMap<>();
    public static final String FILE_PATH="C:\\Users\\Admin\\Desktop";
    public static String addressBookFile = "AddressBookFile.txt";
    public static final String CSV_FILE="/addressBook.csv";
    public static final String JSON_FILE="/addressBook.json";



    public void addNewContact()
    {
        Contact contact = new Contact();
        System.out.println("Enter First name:");
        contact.setFirstName(scanner.next());

        System.out.println("Enter Last Name:");
        contact.setLastName(scanner.next());
        

        System.out.println("Enter Address:");
        contact.setAddress(scanner.next());

        System.out.println("Enter City:");
        contact.setCity(scanner.next());

        System.out.println("Enter State:");
        contact.setState(scanner.next());

        System.out.println("Enter Zip:");
        contact.setZip(scanner.next());
        
        System.out.println("Enter Phone:");
        contact.setPhoneNumber(scanner.next());

        System.out.println("Enter Email:");
        contact.setEmail(scanner.next());

        System.out.println("Contact Added Successfully");
        System.out.println("Enter Book name to which you have to add contact");
        String bookName  = scanner.next();
        if(AddressBooks.containsKey(bookName))
        {
        	contactlist.stream().filter(value -> value.getFirstName(). equals(contact.getFirstName())).forEach(value -> 
    	    {
    	    	System.out.println("Duplicate Contact");
    	    	addNewContact();
    	    });            contactlist.add(contact);
            AddressBooks.put(bookName,contactlist);
            System.out.println("New Contact Added Successfully");
        }
        else
        {
            contactlist.add(contact);
            AddressBooks.put(bookName,contactlist);
            System.out.println("New book created and added Contact Added Successfully");
        }

    }
    public void writeToFile()
    {
        StringBuffer addressBuffer = new StringBuffer();
        contactlist.forEach(address -> { String addressDataString = address.toString().concat("\n");addressBuffer.append(addressDataString);});
        try
        {
            Files.write(Paths.get(addressBookFile),addressBuffer.toString().getBytes(StandardCharsets.UTF_8));
            System.out.println("Data successfully written to file.");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void readDataFromFile()
    {
        try
        {
            System.out.println("Reading Data From File :");
            Files.lines(new File(addressBookFile).toPath()).map(line -> line.trim()).forEach(line -> System.out.println(line));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
   
    
    public void editContact()
    {
        String enteredFirstName;
        System.out.println("Enter First name of contact to edit it ");
        enteredFirstName = scanner.next();
        for (int i = 0; i < contactlist.size(); i++) 
        {
            if (contactlist.get(i).getFirstName().equals(enteredFirstName))
            {
                System.out.println("Enter the field to edit:\n1.First Name\n2.Last Name\n3.Address\n4.city\n5.State\n6.Zip\n7.Phone\n8.Email");
                int userInput = scanner.nextInt();
                switch (userInput)
                {
                    case 1:
                        System.out.println("Enter new first name");
                        contactlist.get(i).setFirstName(scanner.next());
                        break;
                    case 2:
                        System.out.println("Enter new last name");
                        contactlist.get(i).setLastName(scanner.next());
                        break;
                    case 3:
                        System.out.println("Enter new Address");
                        contactlist.get(i).setAddress(scanner.next());
                        break;
                    case 4:
                        System.out.println("Enter new city");
                        contactlist.get(i).setCity(scanner.next());
                        break;
                    case 5:
                        System.out.println("Enter new state");
                        contactlist.get(i).setState(scanner.next());
                        break;
                    case 6:
                        System.out.println("Enter new zip");
                        contactlist.get(i).setZip(scanner.next());
                        break;
                    case 7:
                        System.out.println("Enter new phone number");
                        contactlist.get(i).setPhoneNumber(scanner.next());
                        break;
                    case 8:
                        System.out.println("Enter new email");
                        contactlist.get(i).setEmail(scanner.next());
                        break;
                    default:
                        System.out.println("Invalid Entry");
                        
                }     
            }
        }
        System.out.println("Contact Edited Successfully");
    }
    
    public void deleteContact(String name)
    {
        for (int i = 0; i < contactlist.size(); i++) 
        {
            if (contactlist.get(i).getFirstName().equals(name)) 
            {
                Contact contact = contactlist.get(i);
                contactlist.remove(contact);
            }
        }
        System.out.println("Contact Deleted Successfully");
    }
    public void searchPersonByCity (String city)
    {
        System.out.println("following are the persons who belongs to :" + city);
        for(String bookName : AddressBooks.keySet())
        {
            AddressBooks.get(bookName);
            contactlist.stream().filter(value -> value.getCity().equals(city)).map(Contact::getFirstName).forEach(System.out::println);
        }
    }
    public void searchPersonByState (String state)
    {
        System.out.println("following are the persons who belongs to :" + state);
        for(String bookName : AddressBooks.keySet())
        {
            AddressBooks.get(bookName);
            contactlist.stream().filter(value -> value.getState().equals(state)).map(Contact::getFirstName).forEach(System.out::println);
        }
    }
    public void viewPersonByCity (String city)
    {
        for(String bookName : AddressBooks.keySet())
        {
            int countPerson = 0;
            AddressBooks.get(bookName);
            contactlist.stream().filter(value -> value.getCity().equals(city)).map(Contact::getFirstName).forEach(System.out::println);
            countPerson++;
            System.out.println("total persons:"+countPerson);
        }
    }
    public void viewPersonByState (String state)
    {
        for(String bookName : AddressBooks.keySet())
        {
            int countPerson = 0;
            AddressBooks.get(bookName);
            contactlist.stream().filter(value -> value.getState().equals(state)).map(Contact::getFirstName).forEach(System.out::println);
            countPerson++;
            System.out.println("total persons:"+countPerson);
        }
    }
    public void sortByFirstName() {
		System.out.println(AddressBooks.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toList()));
	}
    public void sortByCity() {
        AddressBooks.keySet().forEach((String key) -> {
            AddressBooks.get(key).stream()
                    .sorted(Comparator.comparing(Contact::getCity))
                    .collect(Collectors.toList())
                    .forEach(person -> System.out.println(person.toString()));
        });
    }
    public void displayList() 
    {
        for (Contact iterator : contactlist) System.out.println(iterator);
    }
    public void writeToCsv()
    {
        try
        {
            Writer writer = Files.newBufferedWriter(Paths.get(FILE_PATH+CSV_FILE));
            StatefulBeanToCsv<Contact> beanToCsv = new StatefulBeanToCsvBuilder<Contact>(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();
            List<Contact> ContactList = new ArrayList<>();
            AddressBooks.entrySet().stream()
                    .map(books->books.getKey())
                    .map(bookNames->{
                        return AddressBooks.get(bookNames);
                    }).forEach(contacts ->{
                ContactList.addAll(contacts);
            });
            beanToCsv.write(ContactList);
            writer.close();
        }
        catch (CsvDataTypeMismatchException e)
        {
            e.printStackTrace();
        }
        catch (CsvRequiredFieldEmptyException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void readFromCsvFile()
    {
        Reader reader;
        try {
            reader = Files.newBufferedReader(Paths.get(FILE_PATH+CSV_FILE));
            CsvToBean<Contact> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Contact.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            List<Contact> contacts = csvToBean.parse();

            for(Contact contact: contacts) {
                System.out.println("Name : " + contact.getFirstName()+" "+contact.getLastName());
                System.out.println("Email : " + contact.getEmail());
                System.out.println("PhoneNo : " + contact.getPhoneNumber());
                System.out.println("Address : " + contact.getAddress());
                System.out.println("State : " + contact.getState());
                System.out.println("City : " + contact.getCity());
                System.out.println("Zip : " + contact.getZip());
                System.out.println("==========================");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public void writeToJson()
    {
        List<Contact> contacts = getContentOfCsv();
        Gson gson = new Gson();
        String json = gson.toJson(contacts);
        try
        {
            FileWriter writer = new FileWriter(FILE_PATH+JSON_FILE);
            writer.write(json);
            writer.close();
            System.out.println("Written sucessfully");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void readFromJson()
    {
        try
        {
            Gson gson = new Gson();
            BufferedReader br = new BufferedReader(new FileReader(FILE_PATH+JSON_FILE));
            Contact[] contacts = gson.fromJson(br,Contact[].class);
            List<Contact> contactsList = Arrays.asList(contacts);
            for(Contact contact: contactsList) {
                System.out.println("Name : " + contact.getFirstName()+" "+contact.getLastName());
                System.out.println("Email : " + contact.getEmail());
                System.out.println("PhoneNo : " + contact.getPhoneNumber());
                System.out.println("Address : " + contact.getAddress());
                System.out.println("State : " + contact.getState());
                System.out.println("City : " + contact.getCity());
                System.out.println("Zip : " + contact.getZip());
                System.out.println("==========================");
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    private List<Contact> getContentOfCsv()
    {
        try
        {
            Reader reader = Files.newBufferedReader(Paths.get(FILE_PATH+CSV_FILE));
            CsvToBean<Contact> csvToBean = new CsvToBeanBuilder<Contact>(reader)
                    .withType(Contact.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csvToBean.parse();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    private static AddressBookService addressBookDBService;

   AddressBookService() {
    }

    public static AddressBookService getInstance() {
        if (addressBookDBService == null)
            addressBookDBService = new AddressBookService();
        return addressBookDBService;
    }

    public List<Contact> readData() throws AddressBookException {
        String sql = "SELECT * FROM address_book; ";
        return this.getAddressBookDataUsingDB(sql);
    }

    private List<Contact> getAddressBookDataUsingDB(String sql) throws AddressBookException {
        List<Contact> addressBookList = new ArrayList<>();
        try (Connection connection = AddressBookConnection.getConnection();) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            addressBookList = this.getAddressBookData(resultSet);
        } catch (SQLException e) {
            throw new AddressBookException(e.getMessage(), AddressBookException.ExceptionType.DATABASE_EXCEPTION);
        }
        return addressBookList;
    }

    private List<Contact> getAddressBookData(ResultSet resultSet) throws AddressBookException {
        List<Contact> addressBookList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String address = resultSet.getString("Address");
                String city = resultSet.getString("City");
                String state = resultSet.getString("State");
                String zip = resultSet.getString("Zip");
                String phoneNo = resultSet.getString("Phone");
                String email = resultSet.getString("Email");
                addressBookList.add(new Contact());
            }
        } catch (SQLException e) {
            throw new AddressBookException(e.getMessage(), AddressBookException.ExceptionType.DATABASE_EXCEPTION);
        }
        return addressBookList;
    }
    AddressBookConnection addressBookConnection = new AddressBookConnection();
    private void prepareAddressBookStatement() throws AddressBookException {
        try {
            Connection connection = addressBookConnection.getConnection();
            String query = "select * from address_book where FirstName = ?";
            addressBookPreparedStatement = connection.prepareStatement(query);
        } catch (SQLException e) {
            throw new AddressBookException(e.getMessage(), AddressBookException.ExceptionType.DATABASE_EXCEPTION);
        }
    }
    public int updateAddressBookData(String firstname, String address) throws AddressBookException {
        String query = String.format("update address_book set Address = '%s' where FirstName = '%s';", address,
                firstname);
        try (Connection connection = addressBookConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            return preparedStatement.executeUpdate(query);
        } catch (SQLException e) {
            throw new AddressBookException(e.getMessage(), AddressBookException.ExceptionType.CONNECTION_FAILED);
        }
    }

    public List<Contact> getAddressBookData(String firstname) throws AddressBookException {
        if (this.addressBookPreparedStatement == null)
            this.prepareAddressBookStatement();
        try {
            addressBookPreparedStatement.setString(1, firstname);
            ResultSet resultSet = addressBookPreparedStatement.executeQuery();
            addressBookData = this.getAddressBookData(resultSet);
        } catch (SQLException e) {
            throw new AddressBookException(e.getMessage(), AddressBookException.ExceptionType.CONNECTION_FAILED);
        }
        System.out.println(addressBookData);
        return addressBookData;
    }
    public List<Contact> readData(LocalDate start, LocalDate end) throws AddressBookException {
        String query = null;
        if (start != null)
            query = String.format("select * from address_book where Date between '%s' and '%s';", start, end);
        if (start == null)
            query = "select * from address_book";
        List<Contact> addressBookList = new ArrayList<>();
        try (Connection con = addressBookConnection.getConnection()) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            addressBookList = this.getAddressBookData(rs);
        } catch (SQLException e) {
            throw new AddressBookException(e.getMessage(), AddressBookException.ExceptionType.DATABASE_EXCEPTION);
        }
        return addressBookList;
    }
}
