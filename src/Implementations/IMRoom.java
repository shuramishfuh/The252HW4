package Implementations;

public class IMRoom implements Interfaces.Room, Comparable<IMRoom> {
    private String building;
    String roomNumber;

    public IMRoom(String building, String roomNumber) {
        this.building = building;
        this.roomNumber = roomNumber;
    }

    @Override
    public String getBuilding() {
        return this.building;
    }

    @Override
    public String getRoomNumber() {
        return this.roomNumber;
    }

    //@Override
    public int compareTo(IMRoom obj) {
        // we sort objects on the basis of Student Id
        if(this.roomNumber.charAt(0) < obj.roomNumber.charAt(0))
            return -1;
        else return 1;
        //return (this.roomNumber - obj.roomNumber);
    }
}
