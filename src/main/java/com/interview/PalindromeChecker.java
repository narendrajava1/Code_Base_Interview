package com.interview;

public class PalindromeChecker {

    public boolean isPalindrome(String str){
        if(str==null||str.isEmpty()){
            return false;
        }
        int left=0,right=str.length()-1;
        while(left<right){
            if(str.charAt(left++)!=str.charAt(right--)){
                return false;
            }
        }
        return true;
    }

    // using recursion
    public boolean isPalindromeRecursive(String str,int left,int right){
        if(str==null||str.isEmpty()){
            return false;
        }
        if(str.charAt(left)!=str.charAt(right)){
            return false;
        }
        if(left>=right){
            return true;
        }
        return isPalindromeRecursive(str,left+1,right-1);

    }


    public static void main(String[] args) {
        PalindromeChecker checker = new PalindromeChecker();
        String testString = "racecarwasawracec/ar".replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        boolean result = checker.isPalindrome(testString);
        boolean resultRecursive = checker.isPalindromeRecursive(testString, 0, testString.length() - 1);
        System.out.println("Is \"" + testString + "\" a palindrome? " + result);
        System.out.println("Is \"" + testString + "\" a palindrome (recursive)? " + resultRecursive);
    }
}
