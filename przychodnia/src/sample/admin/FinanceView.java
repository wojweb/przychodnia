package sample.admin;

public class FinanceView {
    private int year;
    private int income;
    private int costs;
    private int balance;

    public FinanceView(int year, int income, int costs, int balance) {
        this.year = year;
        this.income = income;
        this.costs = costs;
        this.balance = balance;
    }

    public FinanceView(int year, int income, int costs) {
        this.year = year;
        this.income = income;
        this.costs = costs;
        this.balance = income - costs;
    }

    public int getYear() {
        return year;
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
