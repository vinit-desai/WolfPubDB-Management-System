import java.util.Scanner;
import java.sql.*;

public class ViewContributorPayment {

    public static ExecResult execute() {

        String sql = 
			"SELECT * FROM Wages" + "\n"  + "\n" +
			";" + "\n" + "\n"
		    ;
        
		return WolfPubDB.executeUpdate(sql);
	}



    public static ExecResult run(Scanner reader) {

        System.out.println("+------------------------------------+");
		System.out.println("|         Wage Details         |");
		System.out.println("+------------------------------------+");
		System.out.println("");

        return execute();
	}

}