/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg1_6_2022;
import java.util.*;
/**
 *
 * @author Admin
 */
public class M2 {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Chuoi la: ");
        String str = sc.nextLine();
        
        int counter[] = new int[256];
        int len = str.length();
        
        for (int i = 0; i < len; i++){
            //Dem so lan xuat hien cua moi ki tu
            counter[str.charAt(i)]++;
        }
            
        int maxIndex = 0;
        for (int i = 0; i < len; i++) {
            //Tim vi tri cua ki tu xuat hien nhieu nhat
            maxIndex = counter[str.charAt(i)] > counter[str.charAt(maxIndex)] ? i : maxIndex;
        }
        
        ArrayList<Character> check = new ArrayList<Character>();
        for (int i = 0; i < len; i++) {
            if (counter[str.charAt(i)] == counter[str.charAt(maxIndex)]){
                check.add(str.charAt(i));
            }
        }
        Set<Character> setCheck = new HashSet<Character>(check);
        if (setCheck.size()>1){
            System.out.println("Co nhieu ki tu cung xuat hien nhieu nhat la: " + counter[str.charAt(maxIndex)] + " lan va chung la:");
            for(Object c:setCheck){
                System.out.print(c + " ");
            }
            System.out.println("\n");
        }
        else System.out.println("Ki tu xuat hien nhieu nhat la: " + str.charAt(maxIndex) + " va xuat hien " + counter[str.charAt(maxIndex)] + " lan");
        
    }
}
