package Implementations;

public class IMRoom implements Interfaces.Room{
    private
    String building;
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
}
