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
public class M1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap so phan tu: ");
        int n = sc.nextInt();
        int nums[] = new int[n];
        int s = 0;
        System.out.println("Nhap cac phan tu: ");
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
            s+=nums[i];
        }
        System.out.println("Tong cac so trong mang tren la: " + s);
    }
}
