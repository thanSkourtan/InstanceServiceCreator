package com.eurobank.routing;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by v-askourtaniotis on 21/5/2018. mailTo: thanskourtan@gmail.com
 */
public class PlacementDirectory {

    private static final Map<String, String> directoriesRoots = new HashMap<>();

    static {
        directoriesRoots.put("EuroBankAltamiraAccounting", "PersonalAccounts");
        directoriesRoots.put("EuroBankAltamiraCardFraud", "CardFraud");
        directoriesRoots.put("EuroBankAltamiraCards", "Cards");
        directoriesRoots.put("EuroBankAltamiraCheques", "Cheques");
        directoriesRoots.put("EuroBankAltamiraCorpTables", "CorpTables");
        directoriesRoots.put("EuroBankAltamiraCustomers", "Customers");
        directoriesRoots.put("EuroBankAltamiraEStatements", "EStatements");
        directoriesRoots.put("EuroBankAltamiraGroups", "Customers");
        directoriesRoots.put("EuroBankAltamiraLoans", "Loans");
        directoriesRoots.put("EuroBankAltamiraCardLoansLimits", "Loans");
        directoriesRoots.put("EuroBankAltamiraPaymentServices", "PersonalAccounts");
        directoriesRoots.put("EuroBankAltamiraPersonalAccounts", "PersonalAccounts");
        directoriesRoots.put("EuroBankAltamiraPOS", "Cards");
        directoriesRoots.put("EuroBankAltamiraSpecialProducts", "SpecialProducts");
        directoriesRoots.put("EuroBankAltamiraStandingOrders", "PersonalAccounts");
        directoriesRoots.put("EuroBankAltamiraTimeDeposits", "PersonalAccounts");
        directoriesRoots.put("EuroBankOther", "PersonalAccounts");
    }

    public static Map<String, String> getDirectoriesRoots() {
        return directoriesRoots;
    }



}
