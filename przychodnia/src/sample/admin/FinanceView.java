package sample.admin;

public class FinanceView {
    private int income;
    private int costs;
    private int balance;

    public FinanceView(int income, int costs, int balance) {
        this.income = income;
        this.costs = costs;
        this.balance = balance;
    }

    public int getIncome() {
        return income;
    }

    public int getCosts() {
        return costs;
    }

    public int getBalance() {
        return balance;
    }
}
