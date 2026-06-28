package Libray_Management_System;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Library {
    ArrayList<Book> books = new ArrayList<>();
    private static final String FILE_NAME = "books.txt";

    public Library(){
        loadBooks();
    }

    public void addBook(Book book){
        for(int i = 0; i < books.size(); i++){
            if(books.get(i).getId() == book.getId());
            System.out.println("Book Already Exists....");
            return;
        }
        books.add(book);
        saveBooks();
        
        System.out.println("Book Added Successfully....");
    }

    public void removeBook(int id){
        for(int i = 0; i < books.size(); i++){
            if(books.get(i).getId() == id){
                books.remove(i);
                saveBooks();
                System.out.println("Book Deleted Successfully....");
                return;
            }
        }
        System.out.println("Book Not Found....");
    }

    public void displayBooks(){
        if (books.isEmpty()) {
            System.out.println("Library is Empty....");
            return;
        }

        System.out.println("***** BOOKS LIST *****");
        for(int i = 0; i < books.size(); i++){
            Book book = books.get(i);

            System.out.println("Book Id : " + book.getId());
            System.out.println("Title : " + book.getTitle());
            System.out.println("Author : " + book.getAuthor());
            System.out.println("------------------------------");
        }
    }

    public void searchBooks(String keyword){
        boolean found = false;

        for(int i = 0; i < books.size(); i++){
            Book book = books.get(i);

            if(book.getTitle().toLowerCase().contains(keyword.toLowerCase()) || 
            book.getAuthor().toLowerCase().contains(keyword.toLowerCase())){
                System.out.println("Book Id : " + book.getId());
                System.out.println("Title : " + book.getTitle());
                System.out.println("Author : " + book.getAuthor());
                found = true;
            }
        }
        if(!found){
            System.out.println("Book Not Found....");
        }
    }

    private void saveBooks(){
        try{
            PrintWriter writer = new PrintWriter(new File(FILE_NAME));

            for(int i = 0; i < books.size(); i++){
                writer.println(books.get(i));
            }
            writer.close();
        }
        catch(Exception e){
            System.out.println("Error Saving Books....");
        }
    }

    private void loadBooks(){
        File file = new File(FILE_NAME);

        if(!file.exists()){
            return;
        }
        try{
            BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));

            String line;

            while((line = br.readLine()) != null){
                String[] data = line.split(",");

                if(data.length == 3){
                    int id = Integer.parseInt(data[0]);
                    String title = data[1];
                    String author = data[2];
                    books.add(new Book(id, title, author));
                }
            }
            br.close();
        }
        catch(IOException | NumberFormatException e){
            System.out.println("Error Loading Books....");
        }
    }
}
