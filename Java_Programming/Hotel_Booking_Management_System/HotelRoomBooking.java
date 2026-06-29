package Hotel_Booking_Management_System;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class Room{
    private int roomNum;
    private String type;
    private boolean booked;
    private String customerName;

    public Room(int roomNum,String type){
        this.roomNum = roomNum;
        this.type = type;
        this.booked = false;
        this.customerName = "";
    }

    public int getRoomNum(){
        return roomNum;
    }

    public String getType(){
        return type;
    }

    public String getStatus(){
        if(booked){
            return "Booked";
        }
        else{
            return "Available";
        }
    }

    public String getCustomerName(){
        if(booked){
            return customerName;
        }
        else{
            return "";
        }
    }

    public void bookRoom(String customerName){
        booked = true;
        this.customerName = customerName;
    }

    public void cancelRoom(){
        booked = false;
        this.customerName = "";
    }
}

public class HotelRoomBooking {
    static ArrayList<Room> rooms = new ArrayList<>();
    static Scanner obj = new Scanner(System.in);

    public static void main(String[] args) {
        rooms.add(new Room(101, "Double"));
        rooms.add(new Room(102, "Single"));

        while(true){
            System.out.println("***** Hotel Room Booking System *****");
            System.out.println("1. Display All Rooms");
            System.out.println("2. View Available Rooms");
            System.out.println("3. Room Booking");
            System.out.println("4. Room Cancellation");
            System.out.println("5. Exit");
            System.out.println();

            System.out.print("Enter Your Choice : ");
            try{
                int choice = obj.nextInt();

                switch (choice) {
                    case 1: dispRooms(); break;
                    case 2: dispAvailRooms(); break;
                    case 3: bookRoom(); break;
                    case 4: cancelRoom(); break;
                    case 5: return;
                    default: System.out.println("Invalid Option");
                }
            }
            catch(InputMismatchException e){
                System.out.println("Enter a valid number....");
            }
            catch(Exception e){
                System.out.println("Something Went Wrong....");
            }
        }
    }

    public static void dispRooms(){
        if(rooms.isEmpty()){
            System.out.println("No Rooms Available");
            return;
        }
        System.out.println("***** Rooms List *****");
        for(Room room : rooms){
            System.out.println("Room Number : " + room.getRoomNum());
            System.out.println("Room Type : " + room.getType());
            System.out.println("Status : " + room.getStatus());
            if(room.getStatus().equals("Booked")){
                System.out.println("Customer Name : " + room.getCustomerName());
            }
            System.out.println("------------------------------------");
        }
    }

    public static void dispAvailRooms(){
        boolean found = false;

        for(Room room : rooms){
            if(room.getStatus().equals("Available")){
                System.out.println("Room Number : " + room.getRoomNum());
                System.out.println("Room Type : " + room.getType());
                System.out.println("------------------------------------");
                found = true;
            }
        }

        if(!found){
            System.out.println("Room Not Available");
        }
    }

    public static void bookRoom(){
        try {
            System.out.print("Room Number : ");
            int roomNum = obj.nextInt();
            obj.nextLine();

            Room room = findRoom(roomNum);

            if(room == null){
                System.out.println("Room Not Found");
                return;
            }

            if(room.getStatus().equals("Booked")){
                System.out.println("Room Already Booked");
                return;
            }

            System.out.println("Customer Name : ");
            String customerName = obj.nextLine();
            room.bookRoom(customerName);
            System.out.println("Room Booked Successfully");
        } 
        catch (InputMismatchException e) {
            System.out.println("Enter a valid Room Number");
        }
        catch (Exception e){
            System.out.println("Something Went Wrong....");
        }
    }

    public static void cancelRoom(){
        try {
            System.out.println("Room Number : ");
            int roomNum = obj.nextInt();

            Room room = findRoom(roomNum);

            if(room == null){
                System.out.println("Room Not Found");
                return;
            }
            if(room.getStatus().equals("Available")){
                System.out.println("Room is not Booked");
                return;
            }

            room.cancelRoom();;

            System.out.println("Room Cancelled Successfully");
        } 
        catch (InputMismatchException e) {
            System.out.println("Enter a valid Room Number");
        }
        catch(Exception e){
            System.out.println("Something Went Wrong");
        }
    }
    public static Room findRoom(int roomNum){
        for(int i = 0; i < rooms.size(); i++){
            if(rooms.get(i).getRoomNum() == roomNum){
                return rooms.get(i);
            }
        }
        return null;
    }
}
