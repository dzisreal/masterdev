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
public class M4 {
    static boolean isPrime(int n){
        if (n<=1) return false;
        
        for (int i = 2; i <= Math.sqrt(n); i++) 
            if (n%i==0) return false;
        
        return true;
    } 
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap so can kiem tra:");
        int n = sc.nextInt();
        System.out.println(isPrime(n));
    }
}
