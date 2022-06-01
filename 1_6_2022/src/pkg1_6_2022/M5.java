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
class TriangleEdgesException extends Exception {
    // Ham khoi tao
    public TriangleEdgesException(String str) {
        super(str);
    }
}
        
public class M5 {
    public void checkTriangleEdges(int a, int b, int c) throws TriangleEdgesException {
            if (a + b <= c || a + c <= b || b + c <= a) {
                throw new TriangleEdgesException("Loi: Tam giac khong hop le!");
            } else {
                float p = (a+b+c)/2;
                float s = (float)Math.sqrt(p*(p-a)*(p-b)*(p-c));
                System.out.println("Dien tich tam gia la: " + s);
            }
        }
    
    public static void main(String[] args) {
        M5 check = new M5();
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Nhap vao canh thu nhat: ");
            int a = sc.nextInt();
            System.out.print("Nhap vao canh thu hai: ");
            int b = sc.nextInt();
            System.out.print("Nhap vao canh thu ba: ");
            int c = sc.nextInt();

            try {
                check.checkTriangleEdges(a, b, c);
            } catch (TriangleEdgesException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Sai: khong dung dinh dang!");
        }

        sc.close();
    }
        
}
