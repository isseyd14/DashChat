package Model;

public class FriendShip {

    private String FriendShipID;
    private String ID_1;
    private String ID_2;

    public FriendShip(String FriendShipID, String ID_1, String ID_2){
        this.FriendShipID = FriendShipID;
        this.ID_1 = ID_1;
        this.ID_2 = ID_2;
    }

    public String getFriendShipID() {
        return FriendShipID;
    }

    public void setFriendShipID(String friendShipID) {
        FriendShipID = friendShipID;
    }

    public String getID_1() {
        return ID_1;
    }

    public void setID_1(String id_1) {
        ID_1 = id_1;
    }

    public String getID_2() {
        return ID_2;
    }

    public void setID_2(String id_2) {
        ID_2 = id_2;
    }
}
