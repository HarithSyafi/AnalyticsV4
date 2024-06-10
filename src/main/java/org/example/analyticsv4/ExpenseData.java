package org.example.analyticsv4;

public class ExpenseData {
    private int userId;
    private int month;
    private double balance;
    private double income;
    private double expense;
    private double budget;
    private double savings;

    public ExpenseData(int userId, int month, double balance, double income, double expense, double budget, double savings) {
        this.userId = userId;
        this.month = month;
        this.balance = balance;
        this.income = income;
        this.expense = expense;
        this.budget = budget;
        this.savings = savings;

    }
    public int getUserId() {
        return userId;
    }
    public int getMonth(){
        return month;
    }
    public double getBalance() {
        return balance;
    }
    public double getIncome() {
        return income;
    }
    public double getExpense() {
        return expense;
    }
    public double getBudget() {
        return budget;
    }
    public double getSavings() {
        return savings;
    }
}
