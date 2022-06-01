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
public class M3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap so phan tu: ");
        int n = sc.nextInt();
        int nums[] = new int[n];
        System.out.println("Nhap mang so nguyen: ");
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        Arrays.sort(nums);
        System.out.println("Mang sau khi sap xep la: ");
        for(int num:nums){
            System.out.print(num + " ");
        }
    }
}
