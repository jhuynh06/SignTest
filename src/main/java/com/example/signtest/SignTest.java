package com.example.signtest;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
    public void takeSignsOneSample(ArrayList<Integer> d1, int median) {
        nTerms = d1.size();
        for (int i = 0; i < d1.size(); i++) {
            int diff = d1.get(i) - median;
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
        if (pos < neg) {
            pValue = (double) (CombinatoricsUtils.factorial(nTerms) / (((CombinatoricsUtils.factorial(pos))) * CombinatoricsUtils.factorial(nTerms - pos) )) * (double) Math.pow(prob, pos) * (double) Math.pow((1-prob), (nTerms - pos));
        }
        else {
            pValue = (double) (CombinatoricsUtils.factorial(nTerms) / (((CombinatoricsUtils.factorial(neg))) * CombinatoricsUtils.factorial(nTerms - neg) ) * (double) Math.pow(prob, neg)) * (double) Math.pow((1-prob), (nTerms - neg));
        }
    }
    public String conclusion() {
        if (pValue > sig) {
            return "fail to reject null hypothesis";
        }
        else {
            return "reject null hypothesis";
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
        DecimalFormat f = new DecimalFormat("##.00");
        return f.format(pValue);
    }



}
