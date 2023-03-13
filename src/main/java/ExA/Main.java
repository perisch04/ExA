package ExA;
import java.util.Scanner;
public class Main {
    //IsValidDate(String strDate)
    //This function checks if the date is valid and returns a boolean true if it is valid and false if it is not
    private static boolean IsValidDate(String strDate){
        if(strDate.length()!=10)return false;          //check if the length is not equal to 10 this goes first because if it is less than 10 it will throw an error
        String[] strsplit = strDate.split("/"); //split the string by "/" creating an array with three elements [0] = day [1] = month [2] = year
        int day = Integer.parseInt(strsplit[0]);      //convert the strings to integers
        int month = Integer.parseInt(strsplit[1]);
        int year = Integer.parseInt(strsplit[2]);
        boolean isLeap;                       //leap year boolean
        if(strsplit[0].length()<2 ||strsplit[1].length()<2 ||strsplit[2].length()<4||year<0||year>2023||month<0||month>12) { //checks for correct substring length
            return false;
        }
        if((year%4==0)&&(year%100!=0)||(year%400==0)){ //checks if the year is a leap year
            isLeap = true;
        }
        else {
            isLeap = false;
        }
        if(month==1||month==3||month==5||month==7||month==8||month==10||month==12){ //months that have 31 days
            if(day>31){
                return false;
            }
        }else if((month==4||month==6||month==9||month==11)){ //months that have 30 days
            if(day>30) {
                return false;
            }
        }
        else if(month==2){ //february has either 28 or 29 days
            if(isLeap){
                if(day>29){
                    return false;
                }
            }else{
                if(day>28){
                    return false;
                }
            }
        }
        return true; //if all the checks are passed then the date is valid
    }
    //GetDate()
    //This function asks the user for a date and checks if it is valid and if it is not it asks again
    private static String GetDate(){
        Scanner in = new Scanner(System.in);
        String Date;
        do { //do while loop to ask again if the date is not valid
            Date = in.nextLine();
            if(!IsValidDate(Date)){
                System.out.print("Invalid Date Try Again: ");
            }
        } while (!IsValidDate(Date));
        return Date; //returns the date if it is valid
    }
    //HasBirthday(String cDate)
    //This function asks the user for his birthday and checks if it is same with the current date and if it is it prints wishes and the age
    private static void HasBirthday(String cDate){
        System.out.print("Give Your Birthday [DD/MM/YYYY]: ");
        String bDate = GetDate(); //birthday date
        Scanner in = new Scanner(System.in);
        String[] currsplit = cDate.split("/"); //splits the current date
        String[] bdatesplit = bDate.split("/"); //splits the birthday date
        int ageDiff = Integer.parseInt(currsplit[2]) - Integer.parseInt(bdatesplit[2]); //calculates the age difference
        if(currsplit[0].equals(bdatesplit[0])&&currsplit[1].equals(bdatesplit[1])&&ageDiff>=0) { //checks if the day and month are the same and if the age is positive or zero
            System.out.println("Happy Birthday! your current age is " + ageDiff);
        } else if (ageDiff<0) {
            System.out.println("Error: Your birthday is in the future, Try again.");
            HasBirthday(cDate); //if the birthday is in the future the function is called again
        } else{
            System.out.println("Your birthday is not today");
        }
        System.out.print("Do you want to repeat [Y/N]: ");
        String ans = in.nextLine().toLowerCase(); //converts the answer to lower case to avoid errors
        if(ans.equals("y")){
            HasBirthday(cDate); //if the answer is yes then the function is called again
        }

    }
    public static void main(String[] args) {
        System.out.print("Give Current Date [DD/MM/YYYY]: ");
        String CurrentDate = GetDate();
        HasBirthday(CurrentDate);
    }
}