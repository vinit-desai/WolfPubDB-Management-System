import java.util.*;

public class User{

    private static Scanner sc = new Scanner(System.in);
    private static final String[] AdminOps = {"1. Create Publication",
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
        "39. Employee Payments Report",
        "40. Logout"
    };
    private static final String[] ContributorOps = {"1. View Publication",
        "2. Add Book Chapter",
        "3. Delete Book Chapter",
        "4. Add Article",
        "5. Delete Article",
        "6. Logout"
    };  
    private int position;
    private int operation;

    public User(){
        this.position = -1;
        this.operation = -1;        
    }

    public void select_position(){
        
        while (true){
            System.out.println(
                "Log in as?" + "\n" +
                "1. Admin" + "\n" +
                "2. Contributor" + "\n" +
                "3. Exit"
            );

            try{
                this.position = sc.nextInt();
            } catch(Exception e){
                System.out.println(e);
            }

            if(this.position == 1 || this.position == 2){
                this.execute_operation();
            } else if(this.position == 3) {
                System.exit(1);
            } else{
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    public void execute_operation(){

        System.out.println("Select any one of the following operations:");

        if(this.position == 1){

            do{

                for(String s: AdminOps){
                    System.out.println(s);
                }

                try{
                    this.operation = sc.nextInt();
                } catch(Exception e){
                    System.out.println(e);
                }

                switch(this.operation){
                    case 1:
                        System.out.println("Enter the following information:" + "\n" +
                            "1. Publication ID" + "\n" +
                            "2. Type" + "\n" +
                            "3. Publication Date" + "\n" +
                            "4. Topic"
                        );
                        
                        int publicationID = sc.nextInt();
                        sc.nextLine();
                        String type = sc.nextLine();
                        String publicationDate = sc.nextLine(); 
                        String topic = sc.nextLine();

                        if(type.equals("Book")){
                            System.out.println("Additional information:" + "\n" +
                                "1. ISBN" + "\n" +
                                "2. Title" + "\n" +
                                "3. Edition" + "\n" +
                                "4. Creation Date"
                            );
                        }else {
                            System.out.println("Additional information:" + "\n" +
                                "1. Periodical Type" + "\n" +
                                "2. Title" + "\n" +
                                "3. Issue" + "\n" +
                                "4. Issue Date"
                            );
                        }

                        String ISBNType = sc.nextLine();
                        String title = sc.nextLine();
                        int editionIssue = sc.nextInt(); 
                        sc.nextLine();
                        String creationDate = sc.nextLine();

                        Queries.createPublication(publicationID, type, publicationDate, topic, ISBNType, title, editionIssue, creationDate);

                        break;

                    case 2:
                        break;
                    case 3:
                        System.out.println("Enter the following information:" + "\n" +
                            "1. Publication ID" + "\n" +
                            "2. Contributor ID"
                        );

                        publicationID = sc.nextInt();
                        sc.nextLine();
                        int contributorID = sc.nextInt();
                        sc.nextLine();

                        Queries.assignEditor(publicationID, contributorID);
                        
                        break;
                    case 4:
                        System.out.println("Enter the following information:" + "\n" +
                            "1. Publication ID"
                        );

                        publicationID = sc.nextInt();
                        sc.nextLine();

                        Queries.viewPublication(publicationID);

                        break;
                    case 5:
                        System.out.println("Enter the following information:" + "\n" +
                            "1. Publication ID" + "\n" +
                            "2. Chapter Number" + "\n" +
                            "3. Title" + "\n" +
                            "4. Text"
                        );

                        publicationID = sc.nextInt();
                        sc.nextLine();
                        int chapterNumber = sc.nextInt();
                        sc.nextLine();
                        title = sc.nextLine();
                        String text = sc.nextLine();

                        Queries.addBookChapter(publicationID, chapterNumber, title, text);
                        
                        break;
                    case 6:
                        System.out.println("Enter the following information:" + "\n" +
                            "1. Publication ID" + "\n" +
                            "2. Chapter Number"
                        );

                        publicationID = sc.nextInt();
                        sc.nextLine();
                        chapterNumber = sc.nextInt();
                        sc.nextLine();

                        Queries.deleteBookChapter(publicationID, chapterNumber);

                        break;
                    case 7:
                        System.out.println("Enter the following information:" + "\n" +
                            "1. Publication ID" + "\n" +
                            "2. Sequence Number" + "\n" +
                            "3. Title" + "\n" +
                            "4. Creation Date" + "\n" +
                            "5. Text"
                        );

                        publicationID = sc.nextInt();
                        sc.nextLine();
                        int sequenceNumber = sc.nextInt();
                        sc.nextLine();
                        title = sc.nextLine();
                        creationDate = sc.nextLine();
                        text = sc.nextLine();

                        Queries.addArticle(publicationID, sequenceNumber, title, creationDate, text);

                        break;
                    case 8:
                        System.out.println("Enter the following information:" + "\n" +
                            "1. Publication ID" + "\n" +
                            "2. Sequence Number"
                        );

                        publicationID = sc.nextInt();
                        sc.nextLine();
                        sequenceNumber = sc.nextInt();
                        sc.nextLine();

                        Queries.deleteArticle(publicationID, sequenceNumber);
                        
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
                        Queries.monthlyOrdersReport();

                        break;
                    case 33:
                        Queries.monthlyRevenuesReport();

                        break;
                    case 34:
                        Queries.monthlyExpensesReport();

                        break;
                    case 35:
                        Queries.distributorCountReport();

                        break;
                    case 36:
                        break;
                    case 37:
                        Queries.distributorRevenueReport();

                        break;
                    case 38:


                        break;
                    case 39:
                        System.out.println("Enter the following information:" + "\n" +
                            "1. Start Date" + "\n" +
                            "2. End Date"
                        );
                        
                        String startDate = sc.nextLine();
                        String endDate = sc.nextLine();

                        Queries.employeePaymentsReport(startDate, endDate); 

                        break;
                    case 40:
                        break;
                    default:
                        System.out.println("Invalid input. Please try again.");
                }
            } while(this.operation != 40);

        } else{
            for(String s: ContributorOps){
                System.out.println(s);
            }
        }
    }
}