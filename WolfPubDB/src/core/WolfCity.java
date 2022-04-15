import java.util.*;

public class WolfCity{

    private static Scanner sc = new Scanner(System.in);
    private static final String[] AdminOps = {
        "0. Logout",
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
        "30. Monthly Orders Report",
        "31. Monthly Revenues Report",
        "32. Monthly Expenses Report",
        "33. Total Distributor Count Report",
        "34. Report Revenue By City",
        "35. Distributor Revenue Report",
        "36. Location Revenue Report",
        "37. Employee Payments Report",
    };
    private static final String[] PubManagerOps = {
        "0. Logout",
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
    };
    private static final String[] EditorOps = {
        "0. Logout",
        "1. View Publication",
        "2. Add Book Chapter",
        "3. Delete Book Chapter",
        "4. Add Article",
        "5. Delete Article",
    };
    private static final String[] DistrManagerOps = {
        "0. Logout",
        "1. Create Distributor",
        "2. Delete Distributor",
        "3. Update Distributor Details",
        "4. Create Order",
    };
    private static final String[] AnalystOps = {
        "0. Logout",
        "1. Monthly Orders Report",
        "2. Monthly Revenues Report",
        "3. Monthly Expenses Report",
        "4. Total Distributor Count Report",
        "5. Report Revenue By City",
        "6. Distributor Revenue Report",
        "7. Location Revenue Report",
        "8. Employee Payments Report",
    };
    private static final String[][] WolfCityOpsMapping = {
        AdminOps,
        PubManagerOps,
        EditorOps,
        DistrManagerOps,
        AnalystOps,
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
            System.out.println("0. Exit");
            System.out.println("1. Admin");
            System.out.println("2. Publishing Manager");
            System.out.println("3. Editor");
            System.out.println("4. Distribution Manager");
            System.out.println("5. Reporting Analyst");
            System.out.println("");
            System.out.println("");
            System.out.print("Selected User: ");

            boolean positionInvalid = true;
            while (positionInvalid) {
                try {
                    this.position = Integer.parseInt(this.sc.nextLine());
                    if (this.position == 0) {
                        this.sc.close();
                        System.out.print("\033[H\033[2J");  
                        System.out.flush();  
                        System.exit(1);
                    }
                    else if (1 <= this.position && this.position <= WolfCityOpsMapping.length) {
                        this.validOperations = WolfCityOpsMapping[this.position-1];
                        positionInvalid = false;
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
                    if (this.operation == 0) {
                        return;
                    }
                    else if (1 <= this.operation && this.operation <= this.validOperations.length-1) {
                        operationInvalid = false;
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
                    result = CreatePublication.run(this.sc);	
                    break;	
                case 2:	
                    result = UpdatePublication.run(this.sc);
                    break;	
                case 3:	
                    result = AssignEditor.run(this.sc);	
                    break;	
                case 4:	
                    result = ViewPublication.run(this.sc);	
                    break;	
                case 5:	
                    result = AddBookChapter.run(this.sc);	
                    break;	
                case 6:	
                    result = DeleteBookChapter.run(this.sc);	
                    break;	
                case 7:	
                    result = AddArticle.run(this.sc);	
                    break;	
                case 8:	
                    result = DeleteArticle.run(this.sc);	
                    break;	
                case 9:	
                    result = CreateIssue.run(this.sc);	
                    break;	
                case 10:	
                    result = DeleteIssue.run(this.sc);	
                    break;	
                case 11:	
                    result = UpdateIssue.run(this.sc);	
                    break;	
                case 12:	
                    result = CreateBookEdition.run(this.sc);	
                    break;	
                case 13:	
                    result = DeleteBookEdition.run(this.sc);	
                    break;	
                case 14:	
                    result = UpdateBookEdition.run(this.sc);	
                    break;	
                case 15:	
                    result = AddEditionAuthor.run(this.sc);	
                    break;	
                case 16:	
                    result = DeleteEditionAuthor.run(this.sc);	
                    break;	
                case 17:	
                    result = UpdateArticle.run(this.sc);	
                    break;	
                case 18:	
                    result = UpdateArticleText.run(this.sc);	
                    break;	
                case 19:	
                    result = AddArticleAuthor.run(this.sc);	
                    break;	
                case 20:	
                    result = DeleteArticleAuthor.run(this.sc);	
                    break;	
                case 21:	
                    result = UpdateChapter.run(this.sc);	
                    break;	
                case 22:	
                    result = FindBook.run(this.sc);	
                    break;	
                case 23:	
                    result = FindArticle.run(this.sc);	
                    break;	
                case 24:	
                    result = PayAuthorsEditors.run(this.sc);	
                    break;	
                case 25:	
                    result = ViewContributorPayment.run(this.sc);	
                    break;	
                case 26:	
                    result = CreateDistributor.run(this.sc);	
                    break;	
                case 27:	
                    result = DeleteDistributor.run(this.sc);	
                    break;	
                case 28:	
                    result = UpdateDistributor.run(this.sc);	
                    break;	
                case 29:	
                    result = CreateOrder.run(this.sc);	
                    break;	
                case 30:	
                    result = MonthlyOrdersReport.run(this.sc);	
                    break;	
                case 31:	
                    result = MonthlyRevenuesReport.run(this.sc);	
                    break;	
                case 32:	
                    result = MonthlyExpensesReport.run(this.sc);	
                    break;	
                case 33:	
                    result = DistributorCountReport.run(this.sc);	
                    break;	
                case 34:	
                    result = CityRevenueReport.run(this.sc);	
                    break;	
                case 35:	
                    result = DistributorRevenueReport.run(this.sc);	
                    break;	
                case 36:	
                    result = LocationRevenueReport.run(this.sc);	
                    break;	
                case 37:	
                    result = EmployeePaymentsReport.run(this.sc);	
                    break;	
                default:	
                    return;	
            }	
        }
        else if (this.position == 2) {
            switch (this.operation) {
                case 1:	
                    result = CreatePublication.run(this.sc);	
                    break;	
                case 2:	
                    result = UpdatePublication.run(this.sc);
                    break;	
                case 3:	
                    result = AssignEditor.run(this.sc);	
                    break;	
                case 4:	
                    result = ViewPublication.run(this.sc);	
                    break;	
                case 5:	
                    result = AddBookChapter.run(this.sc);	
                    break;	
                case 6:	
                    result = DeleteBookChapter.run(this.sc);	
                    break;	
                case 7:	
                    result = AddArticle.run(this.sc);	
                    break;	
                case 8:	
                    result = DeleteArticle.run(this.sc);	
                    break;
                case 9:
                    result = CreateIssue.run(this.sc);
                    break;
                case 10:
                    result = DeleteIssue.run(this.sc);
                    break;
                case 11:
                    result = UpdateIssue.run(this.sc);
                    break;
                case 12:
                    result = CreateBookEdition.run(this.sc);
                    break;
                case 13:
                    result = DeleteBookEdition.run(this.sc);
                    break;
                case 14:
                    result = UpdateBookEdition.run(this.sc);
                    break;
                case 15:
                    result = AddEditionAuthor.run(this.sc);
                    break;
                case 16:
                    result = DeleteEditionAuthor.run(this.sc);
                    break;
                case 17:
                    result = UpdateArticle.run(this.sc);
                    break;
                case 18:
                    result = UpdateArticleText.run(this.sc);
                    break;
                case 19:
                    result = AddArticleAuthor.run(this.sc);
                    break;
                case 20:
                    result = DeleteArticleAuthor.run(this.sc);
                    break;
                case 21:
                    result = UpdateChapter.run(this.sc);
                    break;
                case 22:
                    result = FindBook.run(this.sc);
                    break;
                case 23:
                    result = FindArticle.run(this.sc);
                    break;
                case 24:
                    result = PayAuthorsEditors.run(this.sc);
                    break;
                case 25:
                    result = ViewContributorPayment.run(this.sc);
                    break;
                default:
                    return;
            }
        }
        else if (this.position == 3) {
            switch (this.operation) {
                case 1:	
                    result = ListEditorPublication.run(this.sc);	
                    break;	
                case 2:	
                    result = AddBookChapter.run(this.sc);	
                    break;	
                case 3:	
                    result = DeleteBookChapter.run(this.sc);	
                    break;	
                case 4:	
                    result = AddArticle.run(this.sc);	
                    break;	
                case 5:	
                    result = DeleteArticle.run(this.sc);	
                    break;
                default:
                    return;
            }
        }
        else if (this.position == 4) {	
            switch (this.operation) {	
                case 1:	
                    result = CreateDistributor.run(this.sc);	
                    break;	
                case 2:	
                    result = DeleteDistributor.run(this.sc);	
                    break;	
                case 3:	
                    result = UpdateDistributor.run(this.sc);	
                    break;	
                case 4:	
                    result = CreateOrder.run(this.sc);	
                    break;	
                default:	
                    return;	
            }	
        }
        else if (this.position == 5) {
            switch (this.operation) { 
                case 1:	
                    result = MonthlyOrdersReport.run(this.sc);	
                    break;	
                case 2:	
                    result = MonthlyRevenuesReport.run(this.sc);	
                    break;	
                case 3:	
                    result = MonthlyExpensesReport.run(this.sc);	
                    break;	
                case 4:	
                    result = DistributorCountReport.run(this.sc);	
                    break;	
                case 5:	
                    result = CityRevenueReport.run(this.sc);	
                    break;	
                case 6:	
                    result = DistributorRevenueReport.run(this.sc);	
                    break;	
                case 7:	
                    result = LocationRevenueReport.run(this.sc);	
                    break;	
                case 8:	
                    result = EmployeePaymentsReport.run(this.sc);	
                    break;
                default:
                    break;
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