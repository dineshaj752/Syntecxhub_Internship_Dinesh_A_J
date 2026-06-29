package Libray_Management_System;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        Library lib = new Library();

        while(true){
            System.out.println("***** Library Management System *****");
            System.out.println("1. Add Book");
            System.out.println("2. Delete Book");
            System.out.println("3. Display All Books");
            System.out.println("4. Search Book");
            System.out.println("5. Exit");

            System.out.print("Enter Your Choice");

            try{
                int choice = obj.nextInt();
                obj.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("Book Id : ");
                        int id = obj.nextInt();
                        obj.nextLine();

                        System.out.println("Title : ");
                        String title = obj.nextLine();

                        System.out.println("Author : ");
                        String author = obj.nextLine();

                        if(title.isEmpty() || author.isEmpty()){
                            System.out.println("Title and Author cannot be Empty....");
                            break;
                        }
                        lib.addBook(new Book(id, title, author));
                        break;
                    
                    case 2:
                        System.out.println("Book Id : ");
                        int reomveId = obj.nextInt();
                        obj.nextLine();

                        lib.removeBook(reomveId);
                        break;

                    case 3:
                        lib.displayBooks();
                        break;

                    case 4:
                        System.out.println("Keyword : ");
                        String keyword = obj.nextLine();

                        lib.searchBooks(keyword);
                        break;
                    
                    case 5:
                        return;

                    default:
                        System.out.println("Invalid Choice....");
                }
            }
            catch(InputMismatchException e){
                System.out.println("Please enter a valid number....");
            }
            catch(Exception e){
                System.out.println("Something went Wrong....");
            }
        }
    }
}
