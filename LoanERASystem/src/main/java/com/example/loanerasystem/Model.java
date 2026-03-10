/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.loanerasystem;

/**
 *
 * @author Ong
 */
public class Model {
    public static class Borrower {
        public String name;
        public double income;
        public double debt;
        public String employment;
        public double requestedAmount;

        public Borrower(String name, double income, double debt, String employment, double requestedAmount) {
            this.name = name;
            this.income = income;
            this.debt = debt;
            this.employment = employment;
            this.requestedAmount = requestedAmount;
        }
    }
    
    public static class RiskResult {
        public int score;
        public String riskLevel;
        public boolean eligible;

        public RiskResult(int score, String riskLevel, boolean eligible) {
            this.score = score;
            this.riskLevel = riskLevel;
            this.eligible = eligible;
        }
    }
}
