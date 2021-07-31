package addressbookworkshop;

public class Contacts 
{
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private long zip;
    private String PhoneNumber;
    private String emailId;

    public Contacts(String firstName, String lastName, String address, String city, String state, long zip, String PhoneNumber, String emailId) 
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.PhoneNumber = PhoneNumber;
        this.emailId = emailId;
    }

    
	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public long getZip() {
		return zip;
	}


	public void setZip(int zip) {
		this.zip = zip;
	}


	public String getPhoneNumber() {
		return PhoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	@Override
	public String toString()
	{
		return "Contacts [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", city=" + city
				+ ", state=" + state + ", zip=" + zip + ", PhoneNumber=" + PhoneNumber + ", emailId=" + emailId + "]";
	}

    
}