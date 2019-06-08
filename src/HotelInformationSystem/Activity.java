package HotelInformationSystem;

import Data.DB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Activity {

    private static final String tblName = "information";

    private int id;
    private String Firstname;
    private String Lastname;
    private String Address;
    private String MobileNo;
    private String Nationality;
    private String Gender;
    private String CheckInDate;
    private String DateOfOut;
    private String BedType;
    private String RoomNo;
    private String Roomkey;
    private String Rate;
    

    public Activity(int id, String Firstname, String Lastname, String Address, String MobileNo, String Nationality,
            String Gender, String CheckInDate, String DateOfOut, String BedType, String RoomNo, String Roomkey, String Rate) {

        this.id = id;
        this.Firstname = Firstname;
        this.Lastname = Lastname;
        this.Address = Address;
        this.MobileNo = MobileNo;
        this.Nationality = Nationality;
        this.Gender = Gender;
        this.CheckInDate = CheckInDate;
        this.DateOfOut = DateOfOut;
        this.BedType = BedType;
        this.RoomNo = RoomNo;
        this.Roomkey = Roomkey;
        this.Rate = Rate;

    }

    public Activity(String Firstname, String Lastname, String Address, String MobileNo, String Nationality, String Gender,
            String CheckInDate, String DateOfOut, String BedType, String RoomNo, String Roomkey, String Rate) {

        this(0, Firstname, Lastname, Address, MobileNo, Nationality, Gender, CheckInDate, DateOfOut, BedType, RoomNo, Roomkey, Rate);
    }

    public Activity() {
        this("", "", "", "", "", "", "", "", "", "", "", "");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String Firstname) {
        this.Firstname = Firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String Address) {
        this.Lastname = Lastname;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String MobileNo) {
        this.MobileNo = MobileNo;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String Nationality) {
        this.Nationality = Nationality;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getCheckInDate() {
        return CheckInDate;
    }

    public void setCheckInDate(String CheckInDate) {
        this.CheckInDate = CheckInDate;
    }

    public String getDateOfOut() {
        return DateOfOut;
    }

    public void setDateOfOut(String DateOfOut) {
        this.DateOfOut = DateOfOut;
    }

    public String getBedType() {
        return BedType;
    }

    public void setBedType(String BedType) {
        this.BedType = BedType;
    }

    public String getRoomNo() {
        return RoomNo;
    }

    public void setRoomNo(String RoomNo) {
        this.RoomNo = RoomNo;
    }

    public String getRoomkey() {
        return Roomkey;
    }

    public void setRoomkey(String Roomkey) {
        this.Roomkey = Roomkey;
    }

    public String getRate() {
        return Rate;
    }

    public void setRate(String Rate) {
        this.Rate = Rate;
    }

    public void insertActivity() throws SQLException {
        String q = String.format("INSERT INTO %s VALUES(NULL,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)", tblName, Firstname, Lastname, Address, MobileNo,
                Nationality, Gender, CheckInDate, DateOfOut, BedType, RoomNo, Roomkey, Rate);
        DB.Query(q, true);
    }

    public static Activity getActivityWithId(int id) throws Exception {
        String q = String.format("SELECT * FROM %s WHERE id = %s", tblName, id);
        ResultSet rs = DB.Query(q);
        if (!rs.next()) {
            throw new Exception("Activity with id " + id + " was not found!");
        }

        Activity a = Activity.getActivityFromResultSet(rs);
        return a;
    }

    public static ArrayList<Activity> searchActivityByName(String s) throws SQLException {
        ArrayList<Activity> activties = new ArrayList<>();
        String q = String.format("SELECT * from %s WHERE "
                + "Firstname LIKE \"%%%s%%\"", tblName, s);
               
               

        ResultSet rs = DB.Query(q);

        while (rs.next()) {
            System.out.println("found");
            Activity a = getActivityFromResultSet(rs);
            System.out.println(a);
            activties.add(a);

        }

        return activties;

    }

    public static Activity getActivityFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String FirstName = rs.getString("FirstName");
        String LastName = rs.getString("LastName");
        String Address = rs.getString("Address");
        String MobileNo = rs.getString("MobileNo");
        String Nationality = rs.getString("Nationality");
        String Gender = rs.getString("Gender");
        String CheckInDate = rs.getString("CheckInDate");
        String DateOfOut = rs.getString("DateOfOut");
        String BedType = rs.getString("BedType");
        String RoomNo = rs.getString("RoomNo");
        String Roomkey = rs.getString("Roomkey");
        String Rate = rs.getString("Rate");

        Activity a = new Activity(id, FirstName, LastName, Address, MobileNo,
                Nationality, Gender, CheckInDate, DateOfOut, BedType, RoomNo, Roomkey, Rate);
        return a;
    }

    public String toString() {
        return String.format(
                "==================\n"
                + "id: %d\n"
                + "Firstname: %s\n"
                + "Lastname : %s\n"
                + "Address: %s\n"
                + "MobileNo : %s\n "
                + "Nationality: %s\n"
                + "Gender : %s\n "
                + "CheckInDate: %s\n"
                + "DateOfOut : %s\n"
                + "BedType: %s\n"
                + "RoomNo: %s\n"
                + "Roomkey : %s\n"
                + "Rate: %s\n", id, Firstname, Lastname, Address, MobileNo, Nationality,
                Gender, CheckInDate, DateOfOut, BedType, RoomNo, Roomkey, Rate);
    }

    public static String[][] arraylistTo2dArray(ArrayList<Activity> as) {
        int rowSize = as.size();
        int colSize = 13;
        String[][] s = new String[rowSize][colSize];
        for (int i = 0; i < rowSize; i++) {
            Activity a = as.get(i);
            s[i][0] = a.getId() + "";
            s[i][1] = a.getFirstname();
            s[i][2] = a.getLastname();
            s[i][3] = a.getAddress();
            s[i][4] = a.getMobileNo();
            s[i][5] = a.getNationality();
            s[i][6] = a.getGender();
            s[i][7] = a.getCheckInDate();
            s[i][8] = a.getDateOfOut();
            s[i][9] = a.getBedType();
            s[i][10] = a.getRoomNo();
            s[i][11] = a.getRoomkey();
            s[i][12] = a.getRate();

        }
        return s;
    }

}
