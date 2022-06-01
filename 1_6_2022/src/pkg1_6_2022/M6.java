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
public class M6 {
    public static void main(String[] args) {
       // Declare variable for radius and distance
	double distance;
	int rad;
	// Accept data from user
	Scanner sc = new Scanner(System.in);
	System.out.print("Nhap ban kinh: ");
	rad = sc.nextInt();
	System.out.println("Hinh duoc ve nhu sau:\n");
	// Use for loop for row wise
	for (int row = 0; row <= 2 * rad; row++) {
	// Use for loop for col wise
            for (int col = 0; col <= 2 * rad; col++) {
		distance = Math.sqrt((row - rad) * (row - rad) + (col - rad) * (col - rad));

		/**
		 * Check whether distance is in the range of (radius - 0.5) and (radius + 0.5)
		 * or not to print *
		 */
		if (distance > rad - 0.5 && distance < rad + 0.5)
                    System.out.print("*");
		else
                    System.out.print(" ");
            }
            System.out.println();
	}
    }
}
