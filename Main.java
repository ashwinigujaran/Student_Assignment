package com.company;
import java.sql.*;
import java.util.*;
import java.text.*;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n;
        do{
        System.out.println("1.Insert\n2.Update\n3.Delete\n4.Display\n0.Exit");
        System.out.println("Enter your choice:");
        n = in.nextInt();

        switch (n) {
            case 1:
                insert();
                break;
            case 2:
                update();
                break;
            case 3:
                delete();
                break;
            case 4:
                display();
                break;
        }
    }while(n!=0);

}

    static void insert()
    {
        String url="jdbc:mysql://localhost:3306/student";
        String username="root";
        String password="Spider@#123@";

        Connection connect;
        java.sql.Date sqdob;
        java.sql.Date sqdoj;

        Scanner in=new Scanner(System.in);

        System.out.println("Enter the register number:");
        int no=in.nextInt();
        System.out.println("Enter the Name:");
        in.next();
        String name=in.nextLine();
        System.out.println("Enter the date of birth(dd-mm-yyyy):");
        String dob=in.next();
        System.out.println("Enter the date of join(dd-mm-yyyy):");
        String doj=in.next();

        try {
            connect=DriverManager.getConnection(url,username,password);
            SimpleDateFormat smp=new SimpleDateFormat("dd-mm-yyyy");
            java.util.Date ndob=smp.parse(dob);
            long ms=ndob.getTime();
            sqdob=new java.sql.Date(ms);

            java.util.Date ndoj=smp.parse(doj);
            long ms2=ndoj.getTime();
            sqdoj=new java.sql.Date(ms2);

            PreparedStatement ps=connect.prepareStatement("insert into student_table(Student_No,Student_Name,Student_DOB,Student_DOJ) values(?,?,?,?)");
            ps.setInt(1, no);
            ps.setString(2, name);
            ps.setDate(3, sqdob);
            ps.setDate(4, sqdoj);

            int row=ps.executeUpdate();

            if(row>0) {
                System.out.println("Record inserted successfully");
            }

            ps.close();
            connect.close();
        }
        catch(Exception e)
        {
            System.out.println("Some error occured while Inserting record");
        }
    }

    static void display()
    {
        String url="jdbc:mysql://localhost:3306/student";
        String username="root";
        String password="Spider@#123@";

        Connection connect;
        Scanner in=new Scanner(System.in);
        try {
            connect=DriverManager.getConnection(url,username,password);

            String sql="select * from student_table";
            Statement statement=connect.createStatement();
            ResultSet result=statement.executeQuery(sql);

            System.out.println("Student NO\tStudent NAME\tStudent DOB\t\tStudent DOJ");
            while(result.next()) {
                int u_id=result.getInt("Student_No");
                String name=result.getString("Student_Name");
                String dob=result.getString("Student_DOB");
                String doj=result.getString("Student_DOJ");

                System.out.println(u_id+"\t"+name+"\t"+dob+"\t"+doj);
            }
            connect.close();
        }
        catch(Exception e) {
            System.out.println("Error occurred during retrival of data");
        }

    }

    static void update()
    {
        String url="jdbc:mysql://localhost:3306/student";
        String username="root";
        String password="Spider@#123@";

        Connection connect;
        java.sql.Date sqdob;
        java.sql.Date sqdoj;

        Scanner in=new Scanner(System.in);

        System.out.println("Enter the register number of student whose record has to update:");
        int no=in.nextInt();
        System.out.println("Enter the updated Name:");
        String name=in.next();
        in.nextLine();
        System.out.println("Enter the updated date of birth(dd-mm-yyyy):");
        String dob=in.next();
        System.out.println("Enter the updated date of join(dd-mm-yyyy):");
        String doj=in.next();

        try {
            connect=DriverManager.getConnection(url,username,password);
            SimpleDateFormat smp=new SimpleDateFormat("dd-mm-yyyy");
            java.util.Date ndob=smp.parse(dob);
            long ms=ndob.getTime();
            sqdob=new java.sql.Date(ms);

            java.util.Date ndoj=smp.parse(doj);
            long ms2=ndoj.getTime();
            sqdoj=new java.sql.Date(ms2);

            PreparedStatement ps=connect.prepareStatement("update student_table set Student_Name=?,Student_DOB=?,Student_DOJ=? where Student_No="+no);
            ps.setString(1, name);
            ps.setDate(2, sqdob);
            ps.setDate(3, sqdoj);

            int row=ps.executeUpdate();

            if(row>0) {
                System.out.println("Record has been updated");
            }

            ps.close();
            connect.close();
        }
        catch(Exception e)
        {
            System.out.println("Some error occured while updating the record");
        }
    }

    static void delete() {
        String url="jdbc:mysql://localhost:3306/student";
        String username="root";
        String password="Spider@#123@";

        Connection connect;
        Scanner in=new Scanner(System.in);

        System.out.println("Enter the register number of student whose record has to update:");
        int no=in.nextInt();

        try {
            connect=DriverManager.getConnection(url,username,password);

            String sql="delete from student_table where Student_No="+no;
            Statement statement=connect.createStatement();
            statement.executeUpdate(sql);

            System.out.println("Record has successfully deleted");
            connect.close();
        }
        catch(Exception e) {
            System.out.println("Couldn't delete the record");
        }


    }
}
