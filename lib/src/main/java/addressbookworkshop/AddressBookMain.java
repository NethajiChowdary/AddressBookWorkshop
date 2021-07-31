package addressbookworkshop;

public class AddressBookMain 
{
    public static void main(String[] args)
    {
        System.out.println("Welcome to Address Book System");
        Contacts contact = new Contacts("Nethaji","Ch","Kukatpally","Hyderabad","Telangana",500085,"9454856874","nethaji123@gmail.com");
        System.out.println(contact);
    }
}