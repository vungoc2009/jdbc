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
        try{
            Connection connection = ConnectData.connection();
            PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1,10000+count);
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
        String sql = "Select * from driver";
        try{
            Connection connection = ConnectData.connection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                System.out.println("id " + rs.getInt(1));
                System.out.println("name "+rs.getString(2));
                System.out.println("address "+rs.getString(3));
                System.out.println("phone "+rs.getString(4));
                System.out.println("levele "+rs.getString(5));
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
