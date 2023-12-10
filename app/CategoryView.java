package icebase.app;

import java.util.Scanner;

import icebase.icebase.Icebase;

public class CategoryView {
    public static void main(String[] args) {
        Icebase ib = new Icebase();
        Scanner sc = new Scanner(System.in);
        int choice;
        String storeName = "STORE X"; //fetch from icebase
        String categoryName = "CATEGORY X";//fetch from icebase
        String[] categoryList = {"Category A", "Category B", "Category C"}; //fetch from icebase
        int range = categoryList.length;

        while (true){
            System.out.printf("\t\t\t ---------%n\t\t\t| %s |%n\t\t\t ---------%n", storeName);
            MenuTitle.displaySubTitle("[" + categoryName + "]");
            for(int i = 0; i < range; i++){
                System.out.printf(
                                    "\t\t[%d] - %s%n", i+1, categoryList[i]);
            }
            System.out.printf("\t\t[%d] - Return%n", range+1);

            System.out.print("\nChoice: ");
            try{
                choice = Integer.parseInt(sc.nextLine());
                if(choice == range+1) break;
                String category = categoryList[choice-1]; //fetch from icebase
                System.out.println(category);//go to item list
            }catch (Exception e){
                System.out.println("Please choose from the given options...\n");
            }
        }
        
        sc.close();
    }
}
