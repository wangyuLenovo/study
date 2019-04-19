package com.wy.shop.suanfa;

/**
 * describe:
 *
 * @author wangyu
 * @date 2019/4/11
 * @time 19:26
 */
class ZuiDaLiRun {

    public static void main(String[] args) {
        int[] price={7,1,5,3,6,4};
        ZuiDaLiRun zuiDaLiRun=new ZuiDaLiRun();
        int i = zuiDaLiRun.maxProfit(price);
    }







    public int maxProfit(int[] prices) {
        return calculate(prices, 0);
    }

    public int calculate(int prices[], int s) {
        if (s >= prices.length)
            return 0;
        int max = 0;
        for (int start = s; start < prices.length; start++) {
            int maxprofit = 0;
            for (int i = start + 1; i < prices.length; i++) {
                if (prices[start] < prices[i]) {
                    int profit = calculate(prices, i + 1) + prices[i] - prices[start];
                    if (profit > maxprofit)
                        maxprofit = profit;
                }
            }
            if (maxprofit > max)
                max = maxprofit;
        }
        return max;
    }
}