
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Administrator
 */
public class BankingSystemFinal {
    
    //DECLARATIONS AND INITIALIZATION:
    static String AccountName;
    static int PinNumber ;
    static Integer Balance;
    static Integer AccountNumber;
    static String _username;
    static String _pass;
    static String LoginNumber;
    static String LoginPin;
    static Integer _balance;
    static String fileName;
    //change directory in accordance to storage path.
    static String directory = "D:\\School Related (BSIT -1A)\\1st Semester (week 1-6)\\Introduction to Programming Files\\";
    static FileWriter writer;
    static Scanner x = new Scanner(System.in);
    static Random rand = new Random();
    public static Integer WA = 0;
    static String _temp;
    static String _transac_user;
    //end of declaration:
    
    public static void main(String[] args) throws IOException, Exception{

        //Intro
        try{
        System.out.println("Welcome to Simple Banking System!");
        System.out.println("-----------------------------------------");
        System.out.println("Are you a new depositor or an existing depositor?");
        System.out.println("-----------------------------------------");
        System.out.println("Type '0' if you're a new depositor or");
        System.out.println("Type '1' if you're an existing depositor");
        System.out.println("-----------------------------------------");
        System.out.print("Input Here: ");
        int A = x.nextInt();
        x.nextLine();
        
        //New Depositor
        if (A==0) {
            System.out.println("Please fill the following:");
            System.out.print("Account Name: ");
            AccountName = x.nextLine();
            System.out.print("Pin Number: ");
            PinNumber = x.nextInt();
            System.out.print("Initial Deposit Amount: ");
            Balance = x.nextInt();
            AccountNumber = rand.nextInt(100000,999999);
            System.out.println("You're Account has been made here is your Account Number: " +  AccountNumber);
            
            x.nextLine();
            fileName = AccountNumber + ".txt";
            createFile(fileName);
            createTransactionHistory(fileName);
            saveToTextFile(fileName);
            LogIn(fileName);
            
            
            //Existing Depositor
        } else if (A==1) {
            LogIn(fileName);
        }
        }catch(IOException e){
            System.out.println("Something went wrong! Try Again!");
        }
    }//end of main method    
//----------------------------------------------------------------------------------------------------------//
    //METHOD SECTION:
//----------------------------------------------------------------------------------------------------------// 
    public static void saveToTextFile(String fileName)throws IOException{
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(directory + fileName, true))) {
            bw.write(AccountNumber + ",");
            bw.write(PinNumber + ",");
            bw.write(AccountName + ",");
            bw.write(Balance + ",\n");
        }
        
    }//end of saveToTextFile
//----------------------------------------------------------------------------------------------------------//    
    public static void createFile(String fileName) throws Exception{
       
        File Fname = new File(directory + fileName);
        if(Fname.createNewFile()){ 
        }                          
    }//end of method CreateFile
//----------------------------------------------------------------------------------------------------------//
    public static void LogIn(String fileName) throws IOException, Exception{
            System.out.println("-----------------------------------------");
            System.out.println("Please enter the following:");
            System.out.print("Account Number: ");
            LoginNumber = x.nextLine();
            System.out.print("Pin Number: ");
            LoginPin = x.nextLine();
            _temp = LoginNumber + ".txt";
            
            try (BufferedReader reader = new BufferedReader(new FileReader(directory + _temp))) {
               
                String _tem;
                
                boolean access = false;
                while((_tem = reader.readLine()) != null){
                    String[] account = _tem.split(",");
                    _username = account[0];
                    _pass = account[1];
                    
                    if(LoginNumber.equals(_username) && LoginPin.equals(_pass)){
                        access = true;
                    }
                }
                if (access){
                    System.out.println("Access Granted!");
                    MainUI(_temp);
                }else{
                    System.out.println("Access Denied! Invalid username or password");
                    LogIn(fileName);
                }
            } 
    }//end of LogIn
//----------------------------------------------------------------------------------------------------------//  
    static void modifyFile(Integer Balance,String fileName) throws IOException{
            File fileToBeModified = new File(directory + fileName);
            String oldContent = "";
            accessBalance(fileName);
            
        try (BufferedReader reader = new BufferedReader(new FileReader(fileToBeModified))) {
            String line = reader.readLine();
            while (line != null){
                oldContent = oldContent + line + System.lineSeparator();
                line = reader.readLine();
            }
            String newContent = oldContent.replaceAll(_balance.toString(), Balance.toString());
            writer = new FileWriter(fileToBeModified);
            writer.write(newContent);
        }
                writer.close();
                
    }//end of Modify
//----------------------------------------------------------------------------------------------------------//
    static void modifyFile(Integer NPN, boolean _temp,String fileName) throws IOException{
            File fileToBeModified = new File(directory + fileName);
            String oldContent = "";
            accessPin(fileName);
            
        try (BufferedReader reader = new BufferedReader(new FileReader(fileToBeModified))) {
            String line = reader.readLine();
            while (line != null){
                oldContent = oldContent + line + System.lineSeparator();
                line = reader.readLine();
            }
            String newContent = oldContent.replaceAll(_pass, NPN.toString());
            writer = new FileWriter(fileToBeModified);
            writer.write(newContent);
        }
                writer.close();
                
    }//end of Method
//----------------------------------------------------------------------------------------------------------//        
    static void accessBalance(String fileName) throws IOException{
        try (BufferedReader reader = new BufferedReader(new FileReader(directory + fileName))) {
           String _temp;
           while((_temp = reader.readLine()) != null){
                    String[] bal = _temp.split(",");
                    _username = bal[0];
                    _pass = bal[1];
                    
                    if(LoginNumber.equals(_username) && LoginPin.equals(_pass)){
                        _balance = Integer.valueOf(bal[3]);
                        Balance = _balance;        
                    }     
           }
    }
    }//end of access balance method
//----------------------------------------------------------------------------------------------------------//
    static void accessPin(String fileName) throws IOException{
        try (BufferedReader reader = new BufferedReader(new FileReader(directory + fileName))) {
           String _temp;
           
           while((_temp = reader.readLine()) != null){
                    String[] pin = _temp.split(",");

                    _pass = pin[1];
           }
    }
    }//end of access balance method
//----------------------------------------------------------------------------------------------------------//    
    static void accessUserName(String fileName) throws IOException{
        try (BufferedReader reader = new BufferedReader(new FileReader(directory + fileName + ".txt"))) {
           String _temp;
           
           while((_temp = reader.readLine()) != null){
                    String[] pin = _temp.split(",");
                    _transac_user = pin[2];
           }
    }
    }//end of access balance method    
//----------------------------------------------------------------------------------------------------------//  
    static void transferFund(String fileName, int AT) throws Exception{
        try (BufferedReader reader = new BufferedReader(new FileReader(directory + fileName))) {
            String _temp;
            
            while((_temp = reader.readLine()) != null){
                String[] Acc = _temp.split(",");
                
                _balance = Integer.valueOf(Acc[3]);
                 Integer _tempBal = _balance;
                 _tempBal += AT;
                 modifyFile(fileName,_tempBal.toString());
            }
        }
    }//end of transferFund method
//----------------------------------------------------------------------------------------------------------//    
    static void modifyFile(String fileName, String _tempBal)throws Exception{
        File fileToBeModified = new File(directory + fileName);
            String oldContent = "";
            accessPin(fileName);
            
        try (BufferedReader reader = new BufferedReader(new FileReader(fileToBeModified))) {
            String line = reader.readLine();
            while (line != null){
                oldContent = oldContent + line + System.lineSeparator();
                line = reader.readLine();
            }
            String newContent = oldContent.replaceAll(_balance.toString(), _tempBal);
            writer = new FileWriter(fileToBeModified);
            writer.write(newContent);
        }
                writer.close();
    }
//----------------------------------------------------------------------------------------------------------//  
    static void createTransactionHistory(String fileName) throws IOException{
        File Fname = new File(directory + "history - " +fileName);
        if(Fname.createNewFile()){ 
        }
    }
//----------------------------------------------------------------------------------------------------------//   
    static void saveTransaction(String description,String withdraw,String deposit) throws IOException{ 
        try (BufferedReader reader = new BufferedReader(new FileReader(directory + _temp))) {
           String _temp;
           
           while((_temp = reader.readLine()) != null){
                    String[] user = _temp.split(",");

                    _username = user[0];
           }
    }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(directory + "history - " + _username + ".txt", true))){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
            LocalDateTime now = LocalDateTime.now();  
            
            
            bw.write(dtf.format(now)+",");
            bw.write(description + ",");
            bw.write(withdraw + ",");
            bw.write(deposit + ",");
            bw.write(Balance + ",\n");
            
        }
    }//end of save transaction method
//----------------------------------------------------------------------------------------------------------//   
    static void checkHistory() throws IOException, Exception{
        try (BufferedReader reader = new BufferedReader(new FileReader(directory + _temp))) {
           String _temp;
           while((_temp = reader.readLine()) != null){
                    String[] user = _temp.split(",");
                    _username = user[0];
           }    
        }
        try {
            File file = new File(directory + "history - " + _username + ".txt");
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();

            String str = new String(data, "UTF-8");
            String lines[] = str.split(",\n");
                 System.out.println("-----------------------------------------");
                 System.out.println("          TRANSACTION  HISTORY:          ");
                 System.out.println("-----------------------------------------");
                 System.out.println("Date|Description|Withdraw|Deposit|Balance");
                 System.out.println("-----------------------------------------");
            for (String line : lines) {
                 System.out.println(line);
            }
            _continue();    
                MainUI(_temp);        
        } catch (IOException e) {
            System.out.println("Something went wrong! Try Again!");
            MainUI(_temp);
        }
        
        MainUI(_temp);
    }
 //----------------------------------------------------------------------------------------------------------//
    static void checkHistory(String fileName) throws IOException, Exception{
        try (BufferedReader reader = new BufferedReader(new FileReader(directory + fileName))) {
           String _temp;
           while((_temp = reader.readLine()) != null){
                    String[] user = _temp.split(",");
                    _username = user[0];
           }    
        }
        try {
            File file = new File(directory + "history - " + _username + ".txt");
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();

            String str = new String(data, "UTF-8");
            String lines[] = str.split(",\n");
                 System.out.println("-----------------------------------------");
                 System.out.println("          TRANSACTION  HISTORY:          ");
                 System.out.println("-----------------------------------------");
                 System.out.println("Date|Description|Withdraw|Deposit|Balance");
                 System.out.println("-----------------------------------------");
            for (String line : lines) {
                 System.out.println(line);
            }
            _continue();
                MainUI(_temp);
        } catch (IOException e) {
            System.out.println("Something went wrong! Try Again!");
            MainUI(_temp);
        }
        
        MainUI(_temp);
    }
//----------------------------------------------------------------------------------------------------------//
    static void _continue(){
        System.out.println("-----------------------------------------");
        System.out.println("Continue?        ");
        System.out.println("0 for no         ");
        System.out.println("1 for yes");
        System.out.print("Enter key to continue: ");
            var A = x.nextInt();
                switch (A) {
                    case 1:
                        break;
                    default:
                        System.exit(0);
            }
    }
//----------------------------------------------------------------------------------------------------------//     
    static void MainUI(String fileName) throws Exception {
        String accountName = "User";
        try {
            
            BufferedReader reader = new BufferedReader(new FileReader(directory + fileName));
            String _temp;
            while ((_temp = reader.readLine()) != null) {
                String[] account = _temp.split(",");
                accountName = account[2];
            }
        } catch (IOException e) {
            return;
        }
        try {
            System.out.println("-----------------------------------------");
            System.out.println("Welcome Back " + accountName.toUpperCase());
            accessBalance(fileName);
            System.out.println("Current Balance: " + Balance);
            System.out.println("-----------------------------------------");
            System.out.println("What would you like to do?");
            System.out.println("'0' for Withdraw");
            System.out.println("'1' for Deposit");
            System.out.println("'2' for Pay Bills");
            System.out.println("'3' for Transfer Funds");
            System.out.println("'4' for Search User");
            System.out.println("'5' for Bank Statement");
            System.out.println("'6' for Change Pin");
            System.out.println("'7' for Logout");
            System.out.println("-----------------------------------------");
            System.out.print("Input here: ");
            int UI = x.nextInt();
            System.out.println("-----------------------------------------");
            switch (UI) {
                case 0 -> { //Withdrawal Module
                    System.out.print("Withdraw Amount: ");
                    WA = x.nextInt();
                    accessBalance(fileName);
                    if (WA < 10000 && WA < Balance){
                        System.out.println("-----------------------------------------");
                        System.out.println("Transaction Completed");
                        Balance = Balance - WA;
                        System.out.println("Balance: " + Balance);
                        saveTransaction("Withdraw",WA.toString(),"0");
                        modifyFile(Balance,fileName);
                    } else{
                        System.out.println("-----------------------------------------");
                        System.out.println("Transaction Failed");
                    }
                    _continue();
                    MainUI(fileName);
                }
                case 1 -> { // Deposit Module
                    System.out.print("Deposit Amount: ");
                    Integer AD = x.nextInt();
                    accessBalance(fileName);
                    Balance = Balance + AD;
                    System.out.println("-----------------------------------------");
                    System.out.println("Balance: " + Balance);
                    saveTransaction("Deposit","0",AD.toString());
                    modifyFile(Balance,fileName);
                    _continue();
                    MainUI(fileName);
                }
                case 2 -> { // Pay Bills Module
                    System.out.println("Choose the Utility that you intend to pay for in the available Categories");
                    System.out.println("-----------------------------------------");
                    System.out.println("0 for Electricity");
                    System.out.println("1 for Water Utilities");
                    System.out.println("2 for Cable/Internet");
                    System.out.println("3 for Loans");
                    System.out.println("-----------------------------------------");
                    System.out.print("Input here: ");
                    int TBP = x.nextInt();
                    switch (TBP) {
                        case 0:
                            System.out.println("-----------------------------------------");
                            System.out.println("ELECTRICITY");
                            System.out.println("-----------------------------------------");
                            System.out.print("Amount to be Paid: ");
                            Integer AP = x.nextInt();
                            accessBalance(fileName);
                            if (AP < Balance) {
                                System.out.println("-----------------------------------------");
                                System.out.println("Transaction Complete");
                                Balance -= AP;
                                System.out.println("Balance: " + Balance);
                                saveTransaction("Electricity",AP.toString(),"0");
                                modifyFile(Balance,fileName);
                            } else {
                                System.out.println("-----------------------------------------");
                                System.out.println("Transaction Failed");
                            }
                            break;
                        case 1:
                            System.out.println("-----------------------------------------");
                            System.out.println("WATER UTILITIES");
                            System.out.println("-----------------------------------------");
                            System.out.print("Amount to be Paid: ");
                            AP = x.nextInt();
                            accessBalance(fileName);
                            if (AP < Balance) {
                                System.out.println("-----------------------------------------");
                                System.out.println("Transaction Complete");
                                Balance -= AP;
                                System.out.println("Balance: " + Balance);
                                saveTransaction("Water Utilities",AP.toString(),"0");
                                modifyFile(Balance,fileName);
                            } else {
                                System.out.println("-----------------------------------------");
                                System.out.println("Transaction Failed");
                            }
                            break;
                        case 2:
                            System.out.println("-----------------------------------------");
                            System.out.println("CABLE/INTERNET");
                            System.out.println("-----------------------------------------");
                            System.out.print("Amount to be Paid: ");
                            AP = x.nextInt();
                            accessBalance(fileName);
                            if (AP < Balance) {
                                System.out.println("-----------------------------------------");
                                System.out.println("Transaction Complete");
                                Balance -= AP;
                                System.out.println("Balance: " + Balance);
                                saveTransaction("Cable/Internet",AP.toString(),"0");
                                modifyFile(Balance,fileName);
                            } else {
                                System.out.println("-----------------------------------------");
                                System.out.println("Transaction Failed");
                            }
                            break;
                        case 3:
                            System.out.println("-----------------------------------------");
                            System.out.println("LOANS");
                            System.out.println("-----------------------------------------");
                            System.out.print("Amount to be Paid: ");
                            AP = x.nextInt();
                            accessBalance(fileName);
                            System.out.println("Processing ...");
                            if (AP < Balance) {
                                System.out.println("-----------------------------------------");
                                System.out.println("Transaction Complete");
                                Balance -= AP;
                                System.out.println("Balance: " + Balance);
                                saveTransaction("Loans",AP.toString(),"0");
                                modifyFile(Balance,fileName);                                
                            } else {
                                System.out.println("-----------------------------------------");
                                System.out.println("Transaction Failed");
                                System.out.println();
                            }
                            break;
                        default:
                            System.out.println("-----------------------------------------");
                            System.out.println("Invalid Input");
                            System.out.println();
                    }
                    _continue();
                    MainUI(fileName);
                }
                case 3 -> { // Transfer Funds
                    System.out.print("Account Number: ");
                    Integer TAcc = x.nextInt();
                    accessUserName(TAcc.toString());
                    System.out.println("Username: " + _transac_user);
                    _continue();
                    System.out.println("-----------------------------------------");        
                    System.out.print("Amount to be transferred: ");
                    Integer AT = x.nextInt();
                    accessBalance(fileName);
                    if (AT < Balance) {
                        System.out.println("-----------------------------------------");
                        System.out.println("Transaction Complete");
                        Balance -= AT;
                        System.out.println("Balance: " + Balance);
                        saveTransaction("Transfer Funds",AT.toString(),"0");
                        modifyFile(Balance,fileName);
                        transferFund(TAcc.toString()+".txt",AT);
                    } else {
                        System.out.println("-----------------------------------------");
                        System.out.println("Transaction Failed");
                        System.out.println();
                    }
                    _continue();
                    MainUI(fileName);
                }
                case 4 ->{
                    System.out.println("Search User");
                    System.out.println("-----------------------------------------");
                    System.out.print("Account Number: ");
                    Integer _tempAccNo = x.nextInt();
                    accessUserName(_tempAccNo.toString());
                    System.out.println("-----------------------------------------");
                    System.out.println("Username: "+_transac_user);
                    _continue();
                    MainUI(fileName);
                    
                }
                case 5 ->{ //Check History
                    System.out.println("Access Bank Statement?");
                    System.out.println("-----------------------------------------");
                    System.out.println("0 for NO");
                    System.out.println("1 for YES");
                    System.out.print("Input here: ");
                    int y = x.nextInt();
                        if (y == 0) {
                            MainUI(fileName);
                        }else if( y==1){
                            checkHistory();
                            checkHistory(fileName);
                        }
                }
                case 6 -> { // Change Pin
                    System.out.println("Change Pin:");
                    System.out.println("-----------------------------------------");
                    System.out.print("New PIN: ");
                    int NPN = x.nextInt();
                    try (BufferedReader reader = new BufferedReader(new FileReader(directory + fileName))) {
                        while((_temp = reader.readLine()) != null){
                        String[] account = _temp.split(",");
                        _username = account[0];
                        _pass = account[1];

                        if(LoginNumber.equals(_username) && LoginPin.equals(_pass)){
                            PinNumber = NPN;
                            System.out.println("-----------------------------------------");
                            System.out.println("New Pin Number: " + PinNumber);
                            System.out.println();
                            modifyFile(NPN,true,fileName);
                        }else {
                            System.out.println("-----------------------------------------");
                            System.out.println("Wrong Account Number and/or Pin Number");
                            System.out.println();
                        }
                        _continue();
                        MainUI(fileName);
                        }
                    }
                }//end of case 5   
                case 7 -> { // Logout
                    System.out.println("Are you sure you want to log out?");
                    System.out.println("-----------------------------------------");
                    System.out.println("0 for NO");
                    System.out.println("1 for YES");
                    System.out.print("Input here: ");
                    int y = x.nextInt();
                    if (y == 0) {
                        MainUI(fileName);
                    } else if (y == 1) {
                        System.out.println("-----------------------------------------");
                        System.out.println("Thank you for using Simple Banking System");
                        System.out.println("We hope to see you again. Goodbye!");
                        System.out.println("-----------------------------------------");
                        System.out.println();
                    }
                }
                default -> {
                    System.out.println("Invalid Input. Returning to Main Menu ...");
                    MainUI(fileName);
                }
            }
        }catch (IOException e){
            MainUI(_temp);
        }
    }//end of MainMethod
//----------------------------------------------------------------------------------------------------------//    
}//end of class
