package Implementations;

import Interfaces.Room;

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

    @Override
    public int compareTo(IMRoom obj) {
        if (this.getBuilding().equals(obj.getBuilding()) && this.getRoomNumber().equals(obj.getRoomNumber()))
            return 0;
        else if(this.getRoomNumber().charAt(0) < obj.getRoomNumber().charAt(0))
            return -1;
        else return 1;
    }
}
