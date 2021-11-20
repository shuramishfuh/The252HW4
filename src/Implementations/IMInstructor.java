package Implementations;

public class IMInstructor implements Interfaces.Instructor {
    private
    String fName;
    String lName;

    public IMInstructor(String fName, String lName) {
        this.fName = fName;
        this.lName = lName;
    }

    @Override
    public String getFirstName() {
        return this.fName;
    }

    @Override
    public String getLastName() {
        return this.lName;
    }
}
