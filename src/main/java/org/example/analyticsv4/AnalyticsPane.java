package org.example.analyticsv4;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AnalyticsPane extends VBox{
    private PieChart pieChart;
    private BarChart<String, Number> barChart;
    private List<ExpenseData> expenseDataList;

    public AnalyticsPane() {
        pieChart = new PieChart();

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        barChart = new BarChart<>(xAxis, yAxis);
        xAxis.setLabel("Category");
        yAxis.setLabel("Amount (RM)");


        getChildren().addAll(pieChart, barChart);
        loadData();
        updateChart(1);

    }
    private void loadData(){
        expenseDataList = new ArrayList<>();
        String filename = "expenses.txt";

        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line;
            boolean isFirstLine = true;
            while((line = br.readLine()) != null){
                if(isFirstLine){
                    isFirstLine = false;
                    continue;
                }
                String[] parts = line.split(",");
                if(parts.length == 7){
                    int userId = Integer.parseInt(parts[0]);
                    int month = Integer.parseInt(parts[1]);
                    double balance = Double.parseDouble(parts[2]);
                    double income = Double.parseDouble(parts[3]);
                    double expense = Double.parseDouble(parts[4]);
                    double budget = Double.parseDouble(parts[5]);
                    double savings = Double.parseDouble(parts[6]);
                    expenseDataList.add(new ExpenseData(userId, month, balance, income, expense, budget, savings));
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void updateChart(int month) {
        pieChart.getData().clear();
        barChart.getData().clear();

        XYChart.Series<String, Number> series = new XYChart.Series<>();

        double total = 0;
        for(ExpenseData data : expenseDataList){
            if(data.getMonth() == month){
                total += data.getBalance() + data.getIncome() + data.getExpense() + data.getBudget() + data.getSavings();
            }
        }

        for(ExpenseData data : expenseDataList){
            if(data.getMonth() == month){

                PieChart.Data balanceSlice = new PieChart.Data("Balance", data.getBalance());
                PieChart.Data incomeSlice = new PieChart.Data("Income", data.getIncome());
                PieChart.Data expenseSlice = new PieChart.Data("Expense", data.getExpense());
                PieChart.Data budgetSlice = new PieChart.Data("Budget", data.getBudget());
                PieChart.Data savingsSlice = new PieChart.Data("Savings", data.getSavings());

                pieChart.getData().add(balanceSlice);
                pieChart.getData().add(incomeSlice);
                pieChart.getData().add(expenseSlice);
                pieChart.getData().add(budgetSlice);
                pieChart.getData().add(savingsSlice);

                balanceSlice.setName(String.format("Balance %.1f%%", (data.getBalance() / total) * 100));
                incomeSlice.setName(String.format("Income %.1f%%", (data.getIncome() / total) * 100));
                expenseSlice.setName(String.format("Expense %.1f%%", (data.getExpense() / total) * 100));
                budgetSlice.setName(String.format("Budget %.1f%%", (data.getBudget() / total) * 100));
                savingsSlice.setName(String.format("Savings %.1f%%", (data.getSavings() / total) * 100));


                series.getData().add(new XYChart.Data<>("Balance", data.getBalance()));
                series.getData().add(new XYChart.Data<>("Income", data.getIncome()));
                series.getData().add(new XYChart.Data<>("Expense", data.getExpense()));
                series.getData().add(new XYChart.Data<>("Budget", data.getBudget()));
                series.getData().add(new XYChart.Data<>("Savings", data.getSavings()));
            }
        }
        barChart.getData().add(series);

    }
}
