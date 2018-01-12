package sample.admin;

import javafx.beans.property.SimpleStringProperty;

public class FinancesProperty {
    private SimpleStringProperty year;
    private SimpleStringProperty income;
    private SimpleStringProperty costs;
    private SimpleStringProperty balance;

    FinancesProperty(FinanceView view){
        year = new SimpleStringProperty(Integer.toString(view.getYear()));
        income = new SimpleStringProperty(Integer.toString(view.getIncome()));
        costs = new SimpleStringProperty(Integer.toString(view.getCosts()));
        balance = new SimpleStringProperty(Integer.toString(view.getBalance()));

    }

    public String getYear() {
        return year.get();
    }

    public SimpleStringProperty yearProperty() {
        return year;
    }

    public void setYear(String year) {
        this.year.set(year);
    }

    public String getIncome() {
        return income.get();
    }

    public SimpleStringProperty incomeProperty() {
        return income;
    }

    public void setIncome(String income) {
        this.income.set(income);
    }

    public String getCosts() {
        return costs.get();
    }

    public SimpleStringProperty costsProperty() {
        return costs;
    }

    public void setCosts(String costs) {
        this.costs.set(costs);
    }

    public String getBalance() {
        return balance.get();
    }

    public SimpleStringProperty balanceProperty() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance.set(balance);
    }
}
