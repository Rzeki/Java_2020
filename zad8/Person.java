public class Person {
    private String Name;
    private String Surname;
    private String PhoneNumber;

    public Person()
    {
        Name = "John";
        Surname = "Doe";
        PhoneNumber = "123456789";
    }

    public Person(String Name,String Surname,String PhoneNumber)
    {
        this.Name = Name;
        this.Surname = Surname;
        this.PhoneNumber = PhoneNumber;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPhoneNumber(String phoneNumber) { //settery
        PhoneNumber = phoneNumber;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getName()
    {
        return Name;
    }

    public String getSurname() //gettery
    {
        return Surname;
    }

    public String getPhoneNumber()
    {
        return PhoneNumber;
    }


}
