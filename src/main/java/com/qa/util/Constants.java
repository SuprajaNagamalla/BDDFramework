package com.qa.util;

public interface Constants {

    //**********Declare RAC application details ***********
    //String BASE_URL = "https://rentacenter-development.mobify-storefront.com/";
    //String BASE_URL = "https://qa.www.rentacenter.com/";
//    String BASE_URL = "https://racprod:worryfree123@perf.www.rentacenter.com/";
    String BASE_URL = "https://qa.www.rentacenter.com/";
    //String BASE_URL = "https://uat.www.rentacenter.com/";

    //String SPACE_SUB_STEP_EXTENT_REPORT="&nbsp;&nbsp;&nbsp;&nbsp;";
    String SPACE_SUB_STEP_EXTENT_REPORT = "&emsp;&emsp;&emsp;";
    //String SPACE_SUB_STEP_EXTENT_REPORT="    ";

    // ****** Payments Details *******

    String PAYPAL_EMAIL = "sb-drxgn1329252@personal.example.com";
    String PAYPAL_PASSWORD = "WeYM7A^D";

    // *** VENMO
    String VENMO_EMAIL = "Sandbox-test-user2";
    String VENMO_PASSWORD = "P@yW1thV3nm0";

    // *** CC DETAILS - Discover
    /*
    String CC_NUMBER = "6011000990911111";
    // *** CC DETAILS
    String CC_NUMBER = "4445222299990007";
    String CC_EXP_DATE = "06";
    String CC_EXP_YEAR = "2027";
    String CC_CVV = "111";
    */

    // *** CC DETAILS - VISA
    String CC_NUMBER = "4445222299990007";
    String CC_EXP_DATE = "06";
    String CC_EXP_YEAR = "2027";
    String CC_CVV = "382";

    // *** GPAY DETAILS
    String GPAY_EMAIL = "ecomqa.rac@gmail.com";
    String GPAY_PASSWORD = "Rac@1234";

    // Sign In details
    //String SIGN_IN_EMAIL_ADDRESS="testracsf1+10@gmail.com";
    //String SIGN_IN_PASSWORD ="Test@1234";

    String SIGN_IN_EMAIL_ADDRESS="kicehzta@racit.com";
    String SIGN_IN_PASSWORD ="P@ssword1";

    //String SIGN_IN_EMAIL_ADDRESS="testracsf1+12@gmail.com";
    //String SIGN_IN_PASSWORD ="Test@1234";

    //String SIGN_IN_EMAIL_ADDRESS="pranathi10@yopmail.com";
    //String SIGN_IN_PASSWORD ="Test@1234";

    String SIGN_IN_OTP="111111";

    //Payment gateway ids
    //	testracsf1+12@gmail.com / Test@1234
    // testracsf1+13@gmail.com / Test@2025

    //from Pranathi -payment gw-my account
    //pranathi10@yopmail.com/ Test@1234


   // CC details:
   // Visa #1	4445222299990007 - cvv - 382
    //Mastercard #1	5444009999222205 - cvv - 382
   // American Express #1	341111597242000 - cvv -1234
   // Discover #1	6011000990911111 - cvv - 111

    //CC:6011000990139424
    //cvv : 732

    //ACH:
   // Bank Account I:  Routing Number: 011075150  Account Number: 1099999999
   // Bank Account II: Routing Number: 011075150  Account Number: 1092969901

    //Paypal :
    //sb-drxgn1329252@personal.example.com/WeYM7A^D
   // sb-0ksam1389120@personal.example.com/Rac@1234


}
