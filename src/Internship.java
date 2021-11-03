import java.io.*;
import java.util.*;
import java.sql.*;

public class Internship {
	public static void main(String args[])
	{
		try
		{
			Scanner sc=new Scanner(System.in);
			//creation of database
			Class.forName("org.sqlite.JDBC");
			Connection con=DriverManager.getConnection("jdbc:sqlite:movies.db");
			if(con!=null)
			{
				//creation of movies table
				Statement stmt=con.createStatement();
				stmt.executeUpdate("create table movies(movie_name text,lead_actor text,actress text,year_of_release int,director text);");
				//insertion of data into movies table dynamically
				PreparedStatement pstmt=con.prepareStatement("insert into movies values(?,?,?,?,?)");
				System.out.println("Enter no of records:");
				int t=sc.nextInt();
				while(t!=0)
				{
					System.out.println("Enter the Year of release");
					int n=sc.nextInt();
					System.out.println("Enter the Movie name");
					String a=sc.next();
					System.out.println("Enter the Lead Actor");
					String b=sc.next();
					System.out.println("Enter the Actress");
					String c=sc.next();
					System.out.println("Enter the Director");
					String d=sc.next();
					pstmt.setInt(4, n);
					pstmt.setString(1, a);
					pstmt.setString(2, b);
					pstmt.setString(3, c);
					pstmt.setString(5, d);
					t--;
				}
				pstmt.executeUpdate();
				//Query data 
				PreparedStatement pstmt1=con.prepareStatement("select * from movies");
				ResultSet rs=pstmt1.executeQuery();
				System.out.println("Movie Name\tLead Actor\tActress\tYear of release\tDirector Name");
				System.out.println("-----------------------------------------------------------------------------");
				while(rs.next())
				{
					System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getInt(4)+"\t"+rs.getString(5));
				}
			}
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

}
