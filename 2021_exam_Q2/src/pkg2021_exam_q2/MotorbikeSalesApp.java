public class MotorbikeSalesApp {

    public static void main(String[] args) {
        // Q.2.1: Single array for manufacturer names (Brands)
        String[] brands = {"Triumph", "Honda", "Suzuki", "Yamaha", "Ducati"};
        
        // Q.2.1: Two-dimensional array to store three quarter sale amounts for five manufacturers
        // Rows (5): Manufacturers
        // Columns (3): Quarter 1, Quarter 2, Quarter 3
        int[][] sales = {
            // Q1, Q2, Q3
            {500, 100, 500},  // Triumph
            {70, 80, 200},   // Honda
            {100, 100, 200},  // Suzuki
            {100, 70, 50},   // Yamaha
            {300, 100, 500}   // Ducati
        };

        // Array to store the calculated total sales for each manufacturer
        int[] totalSales = new int[brands.length];
        
        // --- Output Formatting ---
        System.out.println("Motorbike Manufacturer Sales Report");
        System.out.println("------------------------------------------------------------------");
        System.out.printf("%-10s | %-10s | %-10s | %-10s | %-8s | %-8s%n", 
                          "BRAND", "QUARTER 1", "QUARTER 2", "QUARTER 3", "TOTAL", "STATUS");
        System.out.println("------------------------------------------------------------------");

        // Loop through each manufacturer (row)
        for (int i = 0; i < brands.length; i++) {
            String brandName = brands[i];
            int currentTotal = 0;
            
            // Q.2.2: Calculate total sales for the current manufacturer
            // Loop through each quarter's sale amount (column)
            for (int j = 0; j < sales[i].length; j++) {
                currentTotal += sales[i][j];
            }
            
            // Store the calculated total
            totalSales[i] = currentTotal;

            // Q.2.3: Determine manufacturer status
            String status;
            // Status is Gold if total sales >= 300, otherwise Silver
            if (currentTotal >= 300) {
                status = "Gold";
            } else {
                status = "Silver";
            }
            
            // Q.2.4: Print out the manufacturer's quarter sales, total, and status
            System.out.printf("%-10s | %-10d | %-10d | %-10d | %-8d | %-8s%n",
                              brandName, 
                              sales[i][0], // Quarter 1
                              sales[i][1], // Quarter 2
                              sales[i][2], // Quarter 3
                              currentTotal,
                              status);
        }
        
        System.out.println("------------------------------------------------------------------");
    }
}