package Entity;

import ConnectData.ConnectData;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BusLine  implements Serializable {

    private int id;
    private double distance;
    private int numberStop;

    public BusLine(){

    }
    public BusLine(int id){
        this.id = id;
    }
    public BusLine(int id, double distance, int numberStop) {
        this.id = id;
        this.distance = distance;
        this.numberStop = numberStop;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getNumberStop() {
        return numberStop;
    }

    public void setNumberStop(int numberStop) {
        this.numberStop = numberStop;
    }
    public void inputBusLine(){
        int count =0;
        String sql1 = "Select * from route";
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
        this.id = 100+count;
        do{
            try {
                System.out.print("Nhập thông khoảng cách: ");
                this.distance = new Scanner(System.in).nextDouble();
                if(this.distance > 0)break;
            }catch (Exception e){
                System.out.println("Nhập khoảng cách sai");
            }
        }while (true);
        do{
            try {
                System.out.print("Nhập số điểm dừng: ");
                this.numberStop = new Scanner(System.in).nextInt();
                if (this.numberStop > 0)break;
            }catch (InputMismatchException e){
                System.out.println("nhập sai số điểm dừng");
            }
        }while (true);
    }

    @Override
    public String toString() {
        return "BusLine{" +
                "id=" + id +
                ", distance=" + distance +
                ", numberStop=" + numberStop +
                '}';
    }
}
