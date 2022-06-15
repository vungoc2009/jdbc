package Service;

import ConnectData.ConnectData;
import Entity.Driver;

import Main.MainRun;

import java.sql.*;

import java.util.InputMismatchException;

import java.util.Scanner;

public class DriverService implements GeneralMethod{

    @Override
    public void createNew() {
        System.out.print("nhập số lượng lái xe");
        int quantity = 0;
        do{
            try {
                quantity = new Scanner(System.in).nextInt(  );
                if(quantity >=0){
                    break;
                }
            }catch (InputMismatchException e){
                System.out.println("phải là số");
            }
        }while (true);
        for (int i = 0; i < quantity; i++) {
            Driver driver = new Driver();
            driver.inputDriver();
            MainRun.drivers.add(driver);
            inserIntoDriver(driver);
        }

    }

    public static void inserIntoDriver(Driver driver){
        String sql = "INSERT INTO driver( id,fullName ,address ,phone,levele)"
                + "VALUES(?,?,?,?,?)";
       try{
            Connection connection = ConnectData.connection();
            PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1,driver.getId());
                statement.setString(2,driver.getName());
                statement.setString(3,driver.getAddress());
                statement.setString(4,driver.getPhone());
                statement.setString(5,driver.getLevel());
                statement.executeUpdate();
            connection.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void show() {
        for (Driver driver: MainRun.drivers) {
            System.out.println(driver);
        }
    }
    public void readData() {
        String sql = "Select * from driver";
        try{
            Connection connection = ConnectData.connection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String address = rs.getString(3);
                String phone = rs.getString(4);
                String levele = rs.getString(5);
                Driver driver = new Driver(id , name , address , phone , levele);
                MainRun.drivers.add(driver);
            }
            connection.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean check() {
        if (MainRun.drivers.isEmpty()){
            return true;
        }
        return false;
    }
}
