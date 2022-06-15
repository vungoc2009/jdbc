package Entity;

import ConnectData.ConnectData;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver extends People implements Serializable {
    public final static String A = "trình đọ A";
    public final static String B = "trình đọ B";
    public final static String C = "trình đọ C";
    public final static String F = "trình đọ F";

    private int id;
    private String level;

    public Driver(int id){
        this.id = id;
    }
    public Driver(int id, String level) {
        this.id = id;
        this.level = level;
    }

    public Driver( int id,String name, String address, String phone, String level) {
        super(name, address, phone);
        this.id = id;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Driver() {
    }

    public void inputDriver() {
        String sql1 = "Select * from driver";
        int count =0;
        try{
            Connection connection = ConnectData.connection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql1);
            while (rs.next()){
                count ++;
            }
            connection.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        this.id = 10000 + count;
        super.inputPeople();
        System.out.println("Nhập loại trinhg đọ lái xe ");
        System.out.println("1: trình độ A");
        System.out.println("2: trình độ B");
        System.out.println("5: trình độ C");
        System.out.println("4: trình độ F");
        int option = 0;
        do{
            try {
                option = new Scanner(System.in).nextInt();
                if(option >0 && option<5){
                    break;
                }
                System.out.println("nhập từ 1 đến 4");
            }catch (InputMismatchException e){
                System.out.println("nhập từ 1 đến 4");
            }
        }while (true);
        switch (option){
            case 1:
                this.level = Driver.A;
                break;
            case 2:
                this.level = Driver.B;
                break;
            case 3:
                this.level = Driver.C;
                break;
            case 4:
                this.level = Driver.F;
                break;
        }
    }

    @Override
    public String toString() {
        return super.toString()+ "Driver{" +
                "id=" + id +
                ", level='" + level + '\'' +
                '}';
    }
}
