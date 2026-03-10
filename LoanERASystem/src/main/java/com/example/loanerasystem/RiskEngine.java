/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.loanerasystem;
import java.sql.*;
import java.util.*;
/**
 *
 * @author Ong
 */
public class RiskEngine {
    public static Model.RiskResult assess(double income, double debt, String employment) {
        int score = 0;
        double dti = debt / income;

        Map<String, Double> weights = getRiskWeights();
        double dtiWeight = weights.getOrDefault("dti_ratio", 0.5);
        double empWeight = weights.getOrDefault("employment", 0.5);

        int dtiScore;
        if (dti < 0.3) dtiScore = 100;
        else if (dti < 0.6) dtiScore = 70;
        else dtiScore = 30;

        int empScore;
        if (employment.equalsIgnoreCase("Regular")) empScore = 100;
        else if (employment.equalsIgnoreCase("Contractual")) empScore = 70;
        else empScore = 40;

        score = (int) ((dtiScore * dtiWeight) + (empScore * empWeight));

        String risk;
        if (score >= 70) risk = "Low";
        else if (score >= 50) risk = "Medium";
        else risk = "High";

        boolean eligible = score >= 60;

        return new Model.RiskResult(score, risk, eligible);
    }
    
    private static Map<String, Double> getRiskWeights() {
        Map<String, Double> weights = new HashMap<>();
        String sql = "SELECT metric, weight FROM risk_rules";

        try (Connection conn = DB.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                weights.put(rs.getString("metric"), rs.getDouble("weight"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return weights;
    }
}
