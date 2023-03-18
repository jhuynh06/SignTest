package com.example.signtest;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.util.CombinatoricsUtils;
public class SignTest extends HelloController {
    private int pos;
    private int neg;
    private int nTerms;
    private double pValue;
    public void takeSignsTwoSample(ArrayList<Double> d1, ArrayList<Double> d2) {
        nTerms = d1.size();
        for(int i = 0; i < d1.size(); i++ ) {
            double diff = d1.get(i) - d2.get(i);
            if (diff < 0) {
                neg++;
            }
            else if(diff > 0) {
                pos++;
            }
            else {
                nTerms -= 1;
            }

        }
    }
    public void takeSignsOneSample(ArrayList<Double> d1, double median) {
        nTerms = d1.size();
        for (int i = 0; i < d1.size(); i++) {
            double diff = d1.get(i) - median;
            if (diff < 0) {
                neg++;
            }
            else if(diff > 0) {
                pos++;
            }
            else {
                nTerms -= 1;
            }
        }
    }
    public void pValue() {
        if (nTerms >= 20) {
            double zscore = 0.0;
            NormalDistribution nd = new NormalDistribution();
            if (pos < neg) {
                int x = pos;
                if ( x > nTerms/2) {
                    double z = ((x + prob) - (nTerms/2));
                    double y = (0.5*Math.sqrt(nTerms));
                    zscore = (z/y);
                    System.out.println(zscore);
                    pValue = 1 - nd.cumulativeProbability(zscore);
                }
                else {
                    double z = ((x - prob) - (nTerms/2));
                    double y = (0.5*Math.sqrt(nTerms));
                    zscore = (z/y);
                    System.out.println(zscore);
                    pValue = 1 - nd.cumulativeProbability(zscore);
                }
            }
            else {
                int x = neg;
                if ( x > nTerms/2) {
                    double z = ((x + prob) - (nTerms/2));
                    double y = (0.5*Math.sqrt(nTerms));
                    zscore = (z/y);
                    System.out.println(zscore);
                    pValue = 1 - nd.cumulativeProbability(zscore);
                }
                else {
                    double z = ((x - prob) - (nTerms/2));
                    double y = (0.5*Math.sqrt(nTerms));
                    zscore = (z/y);
                    System.out.println(zscore);
                    pValue = 1 - nd.cumulativeProbability(zscore);
                }

            }
        }
        else {
            if (pos < neg) {
                pValue = (double) (CombinatoricsUtils.factorial(nTerms) / (((CombinatoricsUtils.factorial(pos))) * CombinatoricsUtils.factorial(nTerms - pos) )) * (double) Math.pow(prob, pos) * (double) Math.pow((1-prob), (nTerms - pos));
            }
            else {
                pValue = (double) (CombinatoricsUtils.factorial(nTerms) / (((CombinatoricsUtils.factorial(neg))) * CombinatoricsUtils.factorial(nTerms - neg) ) * (double) Math.pow(prob, neg)) * (double) Math.pow((1-prob), (nTerms - neg));
            }
        }
    }
    public String conclusion() {
        if (pValue > sig) {
            alreadyCalculated = true;
            return "Since the p-value of " + pValue + " is greater than the alpha level of " + sig + ", we fail to reject the null hypothesis as stated.";
        }
        else {
            alreadyCalculated = true;
            return "Since the p-value of " + pValue + " is greater than the alpha level of " + sig + ", we reject the null hypothesis and accept the alternative hypothesis as stated.";
        }
    }
    public int getPos() {
        return pos;
    }
    public int getNeg() {
        return  neg;
    }
    public int getnTerms() {
        return nTerms;
    }
    public String getpValue() {
        DecimalFormat f = new DecimalFormat("##.0000");
        return f.format(pValue);
    }



}
