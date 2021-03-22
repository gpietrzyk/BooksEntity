package pl.grzegorzpietrzyk;


import pl.grzegorzpietrzyk.service.BooksService;

import java.util.List;


public class App {


    public static void main(String[] args) throws Exception {
        BooksService.displayBooks().forEach(System.out::println);
    }



}