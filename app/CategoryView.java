package icebase.app;

import java.util.Scanner;

import icebase.icebase.Icebase;

public class CategoryView {
    public static void main(String[] args) {
        Icebase ib = new Icebase();
        Scanner sc = new Scanner(System.in);

        String[] categoryList = {"Category A", "Category B", "Category C"}; //fetch from icebase

        MenuTitle.displayMainTitle();
        MenuTitle.displaySubTitle("[CATEGORIES]");
        for(int i = 0; i < categoryList.length; i++){
            System.out.printf(
                                "\t\t[%d] - %s%n", i+1, categoryList[i]);
        }
        System.out.print("\nChoice: ");
        sc.nextLine();
        sc.close();
    }
}
