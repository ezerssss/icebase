package icebase.app;

import java.util.InputMismatchException;
import java.util.Scanner;

import icebase.icebase.Icebase;

public class ItemList {
    ItemList(){

    }

    public static void buyItem(String item){
        System.out.println(item);
        //display other item attributes
    }
    public static void main(String[] args){
        Icebase ib = new Icebase();
        Scanner sc = new Scanner(System.in);
        int choice;
        String storeName = "STORE X";
        String categoryName = "CATEGORY X";
        String[] itemList = {"Item A", "Item B", "Item C"}; //fetch from icebase
        int range = itemList.length;
        while (true){
            System.out.printf("\t\t\t ---------%n\t\t\t| %s |%n\t\t\t ---------%n", storeName);
            MenuTitle.displaySubTitle("[" + categoryName + "]");
            for(int i = 0; i < range; i++){
                System.out.printf(
                                    "\t\t[%d] - %s%n", i+1, itemList[i]);
            }
            System.out.printf("\t\t[%d] - Return%n", range+1);

            System.out.print("\nChoice: ");
            try{
                choice = Integer.parseInt(sc.nextLine());
                if(choice == range+1) break;
                String item = itemList[choice-1]; //fetch from icebase
                buyItem(item);
            }catch (Exception e){
                System.out.println("Please choose from the given options...\n");
            }
        }
       
        sc.close();
    }
}
