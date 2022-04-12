import java.util.*;

public class WolfCity{

    private static Scanner sc = new Scanner(System.in);
    private static final String[] AdminOps = {
        "1. Create Publication",
        "2. Update Publication",
        "3. Assign Editor",
        "4. View Publication",
        "5. Add Book Chapter",
        "6. Delete Book Chapter",
        "7. Add Article",
        "8. Delete Article",
        "9. Add Issue",
        "10. Delete Issue",
        "11. Update Issue",
        "12. Add Edition",
        "13. Delete Edition",
        "14. Update Edition",
        "15. Add Edition Author",
        "16. Remove Edition Author",
        "17. Update Article",
        "18. Update Article Text",
        "19. Add Article Author",
        "20. Remove Article Author",
        "21. Update Chapter",
        "22. Find Books",
        "23. Find Articles",
        "24. Issue Contributor Payment",
        "25. View Contributor Payment",
        "26. Create Distributor",
        "27. Delete Distributor",
        "28. Update Distributor Details",
        "29. Create Order",
        "30. Bill Distributor",
        "31. Update Balance",
        "32. Monthly Orders Report",
        "33. Monthly Revenues Report",
        "34. Monthly Expenses Report",
        "35. Total Distributor Count Report",
        "36. Report Revenue By City",
        "37. Distributor Revenue Report",
        "38. Location Revenue Report",
        "39. Employee Payments Report"
    };
    private static final String[] ContributorOps = {
        "1. View Publication",
        "2. Add Book Chapter",
        "3. Delete Book Chapter",
        "4. Add Article",
        "5. Delete Article"
    };
    private static final String[][] WolfCityOpsMapping = {
        AdminOps,
        ContributorOps
    };

    private int position;
    private int operation;
    private String[] validOperations;

    public WolfCity(){
        this.position = -1;
        this.operation = -1;
        this.validOperations = null;
    }

    public static void main(String[] args){        
        WolfCity wfInterface = new WolfCity();
        wfInterface.select_position();
    }

    public void select_position(){
        while (true){
            System.out.print("\033\143");  

            System.out.println("+--------------------------------------+");
            System.out.println("| Welcome to WolfCity Publishing House |");
            System.out.println("+--------------------------------------+");
            System.out.println("");
            System.out.println("User Options");
            System.out.println("============");
            System.out.println(
                "1. Admin" + "\n" +
                "2. Contributor" + "\n" +
                "3. Exit"
            );
            System.out.println("");
            System.out.println("");
            System.out.print("Selected User: ");

            boolean positionInvalid = true;
            while (positionInvalid) {
                try {
                    this.position = Integer.parseInt(this.sc.nextLine());
                    if (1 <= this.position && this.position <= 2) {
                        this.validOperations = WolfCityOpsMapping[this.position-1];
                        positionInvalid = false;
                    } else if (this.position == 3) {
                        this.sc.close();
                        System.out.print("\033[H\033[2J");  
                        System.out.flush();  
                        System.exit(1);
                    } else {
                        System.out.print("Invalid Option; Please try again: ");
                    }
                } catch(Exception e){
                        System.out.print("Invalid Option; Please try again: ");
                }
            }

            this.select_operation();
        }
    }

    public void select_operation() {
        while (true) {
            System.out.print("\033\143");

            System.out.println("+--------------------------------+");
            System.out.println("| Select an Operation to Execute |");
            System.out.println("+--------------------------------+");
            System.out.println("");
            System.out.println("Available Operations");
            System.out.println("====================");
            for (String s: this.validOperations){
                System.out.println(s);
            }
            System.out.println("");
            System.out.println("");
            System.out.print("Selected Operation: ");

            boolean operationInvalid = true;
            while (operationInvalid) {
                try {
                    this.operation = Integer.parseInt(this.sc.nextLine());
                    if (1 <= this.operation && this.operation <= this.validOperations.length-1) {
                        operationInvalid = false;
                    } else if (this.operation == this.validOperations.length) {
                        return;
                    } else {
                        System.out.print("Invalid Option; Please try again: ");
                    }
                } catch(Exception e){
                        System.out.print("Invalid Option; Please try again: ");
                }
            }

            this.execute_operation();
        }
    }

    public void execute_operation(){
        System.out.print("\033\143"); 

        ExecResult result = null;

        if(this.position == 1){
            switch(this.operation){
                case 1:
                    result = CreatePublication.run(sc);
                    break;

                case 2:
                    break;
                case 3:
                    // System.out.println("Enter the following information:" + "\n" +
                    //     "1. Publication ID" + "\n" +
                    //     "2. Contributor ID"
                    // );

                    // publicationID = sc.nextInt();
                    // sc.nextLine();
                    // int contributorID = sc.nextInt();
                    // sc.nextLine();

                    // Queries.assignEditor(publicationID, contributorID);
                    
                    break;
                case 4:
                    // System.out.println("Enter the following information:" + "\n" +
                    //     "1. Publication ID"
                    // );

                    // publicationID = sc.nextInt();
                    // sc.nextLine();

                    // Queries.viewPublication(publicationID);

                    break;
                case 5:
                    // System.out.println("Enter the following information:" + "\n" +
                    //     "1. Publication ID" + "\n" +
                    //     "2. Chapter Number" + "\n" +
                    //     "3. Title" + "\n" +
                    //     "4. Text"
                    // );

                    // publicationID = sc.nextInt();
                    // sc.nextLine();
                    // int chapterNumber = sc.nextInt();
                    // sc.nextLine();
                    // title = sc.nextLine();
                    // String text = sc.nextLine();

                    // Queries.addBookChapter(publicationID, chapterNumber, title, text);
                    
                    break;
                case 6:
                    // System.out.println("Enter the following information:" + "\n" +
                    //     "1. Publication ID" + "\n" +
                    //     "2. Chapter Number"
                    // );

                    // publicationID = sc.nextInt();
                    // sc.nextLine();
                    // chapterNumber = sc.nextInt();
                    // sc.nextLine();

                    // Queries.deleteBookChapter(publicationID, chapterNumber);

                    break;
                case 7:
                    // System.out.println("Enter the following information:" + "\n" +
                    //     "1. Publication ID" + "\n" +
                    //     "2. Sequence Number" + "\n" +
                    //     "3. Title" + "\n" +
                    //     "4. Creation Date" + "\n" +
                    //     "5. Text"
                    // );

                    // publicationID = sc.nextInt();
                    // sc.nextLine();
                    // int sequenceNumber = sc.nextInt();
                    // sc.nextLine();
                    // title = sc.nextLine();
                    // creationDate = sc.nextLine();
                    // text = sc.nextLine();

                    // Queries.addArticle(publicationID, sequenceNumber, title, creationDate, text);

                    break;
                case 8:
                    // System.out.println("Enter the following information:" + "\n" +
                    //     "1. Publication ID" + "\n" +
                    //     "2. Sequence Number"
                    // );

                    // publicationID = sc.nextInt();
                    // sc.nextLine();
                    // sequenceNumber = sc.nextInt();
                    // sc.nextLine();

                    // Queries.deleteArticle(publicationID, sequenceNumber);
                    
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 11:
                    break;
                case 12:
                    break;
                case 13:
                    break;
                case 14:
                    break;
                case 15:
                    break;
                case 16:
                    break;
                case 17:
                    break;
                case 18:
                    break;
                case 19:
                    break;
                case 20:
                    break;
                case 21:
                    break;
                case 22:
                    break;
                case 23:
                    break;
                case 24:
                    break;
                case 25:
                    break;
                case 26:
                    break;
                case 27:
                    break;
                case 28:
                    break;
                case 29:
                    break;
                case 30:
                    break;
                case 31:
                    break;
                case 32:
                    result = MonthlyOrdersReport.run(this.sc);
                    break;
                case 33:
                    result = MonthlyRevenuesReport.run(this.sc);
                    break;
                case 34:
                    result = MonthlyExpensesReport.run(this.sc);
                    break;
                case 35:
                    result = DistributorCountReport.run(this.sc);
                    break;
                case 36:
                    result = CityRevenueReport.run(this.sc);
                    break;
                case 37:
                    result = DistributorRevenueReport.run(this.sc);
                    break;
                case 38:
                    result = LocationRevenueReport.run(this.sc);
                    break;
                case 39:
                    result = EmployeePaymentsReport.run(this.sc);
                    break;
                default:
                    return;
            }
        } else{
            switch(this.operation){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    return;
            }
        }

        if (result.success) {
            System.out.println("API Status: Success");
        } else {
            System.out.println("API Status: Failure");
            System.out.println("\tError: " + result.errorMessage);
        }
        System.out.println("");

        System.out.print("Press Enter to Continue...");
        try {	
            this.sc.nextLine();
        } catch (NoSuchElementException error) {
        }
    }
}