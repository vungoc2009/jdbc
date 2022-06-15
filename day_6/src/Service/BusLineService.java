package Service;

import ConnectData.ConnectData;
import Entity.BusLine;

import Main.MainRun;

import java.sql.*;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BusLineService implements GeneralMethod{
    @Override
    public void createNew() {
        int quantity = 0;
        do{
            try{
                System.out.println("nhập số lượng tuyến đường");
                quantity = new Scanner(System.in).nextInt();
                if(quantity > 0)break;
            }catch (InputMismatchException e){
                System.out.println("số tuyền đường phải là số dương");
            }
        }while (true);
        for (int i = 0; i < quantity; i++) {
            BusLine busLine = new BusLine();
            busLine.inputBusLine();
            MainRun.busLines.add(busLine);
            inserIntoBusLine(busLine);
        }
    }
    public static void inserIntoBusLine(BusLine busLine){
        String sql = "INSERT INTO route( id,distance , numberofstops)"
                + "VALUES(?,?,?)";

        try{

            Connection connection = ConnectData.connection();
            PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1,busLine.getId());
                statement.setDouble(2,busLine.getDistance());
                statement.setInt(3,busLine.getNumberStop());
                statement.executeUpdate();

            connection.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void readata () {
        String sql = "Select * from route";
        try{
            Connection connection = ConnectData.connection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                int id = rs.getInt(1);
                Double Distance = rs.getDouble(2);
                int NumberStop = rs.getInt(3);
                BusLine busLine = new BusLine(id , Distance , NumberStop);
                MainRun.busLines.add(busLine);
            }
            connection.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void show() {
        for (BusLine busLine :MainRun.busLines) {
            System.out.println(busLine);
        }
    }

    @Override
    public boolean check() {
        if (MainRun.busLines.isEmpty()) return true;
        return false;
    }
}
