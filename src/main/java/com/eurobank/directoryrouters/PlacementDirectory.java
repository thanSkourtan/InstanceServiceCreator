package com.eurobank.directoryrouters;

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

// Unfortunately there is not a default location for Services declared in ___.xml Please provide the
// stem(root) of the project name as third argument. For example if you want the several files of you Service to be located
// into the packages BRMEstatements, BRMEStatementsExits, ESBEstatements and ESBEstatementsImpl the write
// java jar .... <nameofxmlfile> <servicename> <Estatements>. Do the same if for any reason you want to override the default location.



}
