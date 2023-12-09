package icebase.app;

import java.util.Scanner;

import icebase.icebase.Icebase;

public class ItemList {
    ItemList(){

    }
    public static void main(String[] args){
        Icebase ib = new Icebase();
        Scanner sc = new Scanner(System.in);
        String[] itemList = {"Item A", "Item B", "Item C"}; //fetch from icebase

        MenuTitle.displayMainTitle();
        MenuTitle.displaySubTitle("[CATEGORY X]");
        for(int i = 0; i < itemList.length; i++){
            System.out.printf(
                                "\t\t[%d] - %s%n", i+1, itemList[i]);
        }

        System.out.print("\npress enter to continue...");
        sc.nextLine();
        sc.close();
    }
}
