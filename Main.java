import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        try {
            String salesRepId = "";
            int counter1 = 0;
            int counter2 = 0;
            File salesDb = new File("SalesReps.csv");
            File companyDb = new File("Company.csv");
            Scanner salesInput = new Scanner(salesDb);
            Scanner companyInput = new Scanner(companyDb);
            ArrayList<String[]> salesData = new ArrayList<String[]>();
            while (salesInput.hasNextLine()) {
                String line = salesInput.nextLine();
                String[] splited1Line = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                salesData.add(splited1Line);
            }
            salesInput.close();
            System.out.println("Please enter a sales rep last name: ");
            Scanner sc = new Scanner(System.in);
            String salesRepLastName = sc.nextLine();
            for (String[] entry : salesData) {
                for(int i = 0; i < entry.length; i++) {
                    if (entry[i].equals(salesRepLastName)) {
                        salesRepId = entry[i-2];
                        System.out.println("Sales rep found:" + salesRepLastName + " " + salesRepId);
                        counter1++;
                    }
                }
            }
            if(counter1 == 0)
//could add do while/while validation for this step
                System.out.println("Sales rep not found. Please enter name again: ");

            ArrayList<String[]>companyData = new ArrayList<String[]>();
            while(companyInput.hasNextLine()) {
                String line = companyInput.nextLine();
                String[] splitedLine2 = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                companyData.add(splitedLine2);
            }
            companyInput.close();
                //int n and m are being used where they are for better performance
                for (int i = 0, m = companyData.size(); i < m; i++) {
                    String[] currentArray = companyData.get(i);
                   // for (int j = 0, n = currentArray.length; j < n; j++) {
                       if(currentArray[2].equals(salesRepId)){
                           System.out.println("Organization Id: " + currentArray[1]);
                           System.out.println("Company Name: " + currentArray[3]);
                           System.out.println("Country: " + currentArray[5]);
                           System.out.println("Industry: " + currentArray[8]);
                           System.out.println("Number of employees: " + currentArray[9]);
                           counter2++;
                       }
                    //}
                }
            if(counter2 == 0)
                System.out.println("Sales ID not valid and company not found.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found! ");
            e.printStackTrace();
        }
    }
}