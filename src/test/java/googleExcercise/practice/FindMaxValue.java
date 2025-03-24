package practice;

public class FindMaxValue {

    public static void main(String args[]){
        // Input prices array
        int[] prices = {7, 1, 5, 3, 6, 4};
        int[] prices2 = {1,2,3,4,5};
        int[] prices3 = {7,6,4,3,1};

        // Calculate the maximum profit
        int maxProfit = maxProfit(prices3);

        // Output the result
        System.out.println("Output: " + maxProfit);
    }

    public static int maxProfit(int[] prices){
        int profit=0;
        for(int i=1; i<prices.length;i++){
            if(prices[i-1]<prices[i]){
                profit+=prices[i]-prices[i-1];
            }
        }
        return profit;
    }
}