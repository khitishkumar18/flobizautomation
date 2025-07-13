package com.automationframework.testcases;

import com.automationframework.base.BaseClass;
import com.automationframework.pages.getStartedPage;
import com.automationframework.pages.userDetailsPage;
import com.automationframework.utilities.TestUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_LoginPage extends BaseClass {

    private final String mobile_no = TestUtils.generateRandomMobileNumber();
    private final String new_mobile_no = TestUtils.generateRandomMobileNumber();

    public TC_LoginPage() throws IOException {
    }

    @Test(priority = 1, description = "Verify error when mobile number is empty")
    public void testErrorForEmptyMobileNumber() throws InterruptedException, IOException {
        logger.info("Step 1: Clicking on 'Get OTP' without entering mobile number");
        lp.clickGetOtpButton();

        logger.info("Step 2: Verifying error for empty mobile number");
        Assert.assertTrue(lp.isMobileNumberErrorDisplayed(), "Expected error message not displayed");

        su.captureScreenShot(getDriver(), "testErrorForEmptyMobileNumber_passed");
    }

    @Test(priority = 2, description = "Verify error on invalid OTP length")
    public void testErrorOnInvalidOtpLength() throws InterruptedException, IOException {
        logger.info("Step 1: Entering a randomly generated mobile number");
        lp.enterMobileNumber(mobile_no);
        logger.info("Step 2: Clicking on 'Get OTP' button");
        lp.clickGetOtpButton();
        logger.info("Step 3: Entering an invalid OTP with less than 6 digits");
        lp.enterOTP("2222");
        logger.info("Step 4: Clicking on 'Register' button");
        lp.clickRegisterButton();
        logger.info("Step 5: Verifying error message for short OTP");
        Assert.assertTrue(lp.isShortOtpErrorDisplayed(), "Expected error for short OTP not displayed");
        su.captureScreenShot(getDriver(), "testErrorOnInvalidOtpLength_passed");
    }

    @Test(priority = 3, description = "Verify alert for OTP mismatch")
    public void testOtpMismatchAlertMessage() throws InterruptedException, IOException {
        logger.info("Step 1: Entering a randomly generated mobile number");
        lp.enterMobileNumber(mobile_no);
        logger.info("Step 2: Clicking on 'Get OTP' button");
        lp.clickGetOtpButton();
        logger.info("Step 3: Entering an incorrect OTP to simulate mismatch");
        lp.enterOTP("222222");
        logger.info("Step 4: Clicking on 'Register' button");
        lp.clickRegisterButton();
        logger.info("Step 5: Verifying that OTP mismatch alert is displayed");
        Assert.assertTrue(lp.isInvalidOtpAlertDisplayed(), "Expected OTP mismatch alert not displayed");
        su.captureScreenShot(getDriver(), "testOtpMismatchAlertMessage_passed");
    }

    @Test(priority = 4, description = "Check empty field alert on registration")
    public void testRegistrationPageShowsAlertForMissingFields() throws InterruptedException, IOException {
        logger.info("Step 1: Entering a new random mobile number");
        lp.enterMobileNumber(new_mobile_no);

        logger.info("Step 2: Clicking on 'Get OTP' button");
        lp.clickGetOtpButton();

        logger.info("Step 3: Entering default OTP");
        lp.enterOTP(defaultOtp);

        logger.info("Step 4: Clicking on 'Register' button");
        lp.clickRegisterButton();

        getStartedPage sp = new getStartedPage(getDriver());

        logger.info("Step 5: Verifying visibility of 'Create Invoice' button");
        Assert.assertTrue(sp.isCreateInvoiceButtonVisible(), "Create Invoice button not visible");

        logger.info("Step 6: Clicking on 'Create Invoice' button");
        sp.clickCreateInvoiceButton();

        logger.info("Step 7: Handling 'I Don't Have The Bill' option if present");
        sp.clickIDontHaveTheBillIfPresent();

        userDetailsPage udp = new userDetailsPage(getDriver());

        logger.info("Step 8: Verifying visibility of Business Name input field");
        Assert.assertTrue(udp.isBusinessNameInputVisible(), "Business name input not visible");

        logger.info("Step 9: Clicking on Continue without filling mandatory details");
        udp.clickContinueButton();

        logger.info("Step 10: Verifying if alert is shown for empty required fields");
        Assert.assertTrue(udp.isEmptyFieldAlertVisible(), "Empty field alert not visible");

        su.captureScreenShot(getDriver(), "testRegistrationPageShowsAlertForMissingFields_passed");

        logger.info("Step 11: Verifying if specific error message is shown for missing fields");
        Assert.assertTrue(udp.isEmptyFieldErrorMessageVisible(), "Error message for empty field not visible");
    }

    @Test(priority = 5, description = "Valid Mobile & OTP Registration Flow")
    public void testValidMobileNumberAndOtpRegistration() throws InterruptedException, IOException {

        logger.info("Step 1: Entering a valid mobile number");
        lp.enterMobileNumber(mobile_no);

        logger.info("Step 2: Clicking on 'Get OTP' button");
        lp.clickGetOtpButton();

        logger.info("Step 3: Entering the default OTP");
        lp.enterOTP(defaultOtp);

        logger.info("Step 4: Clicking on 'Register' button");
        lp.clickRegisterButton();

        getStartedPage sp = new getStartedPage(getDriver());

        logger.info("Step 5: Verifying visibility of 'Create Invoice' button");
        Assert.assertTrue(sp.isCreateInvoiceButtonVisible(), "Create Invoice button not visible");

        logger.info("Step 6: Clicking on 'Create Invoice' button");
        sp.clickCreateInvoiceButton();

        logger.info("Step 7: Handling 'I Don't Have The Bill' option if present");
        sp.clickIDontHaveTheBillIfPresent();

        userDetailsPage udp = new userDetailsPage(getDriver());
        logger.info("Step 8: Verifying visibility of Business Name input field");
        Assert.assertTrue(udp.isBusinessNameInputVisible(), "Business name input not visible");

        logger.info("Step 9: Entering random business name");
        udp.enterRandomBusinessName();

        logger.info("Step 10: Selecting random Business Registration Type");
        udp.selectRandomBusinessRegistrationType();

        logger.info("Step 11: Selecting random Business Type");
        udp.selectRandomBusinessType();

        logger.info("Step 12: Selecting Industry Type");
        udp.selectIndustryType();

        logger.info("Step 13: Clicking on Continue button (Step 1)");
        udp.clickContinueButton();

        logger.info("Step 14: Selecting random Billing Requirement");
        udp.selectRandomBillingRequirement();

        logger.info("Step 15: Selecting random Business Size");
        udp.selectRandomBusinessSize();

        logger.info("Step 16: Clicking on Continue button (Step 2)");
        udp.clickContinueButton();

        logger.info("Step 17: Selecting random 'How did you find us' option");
        udp.selectRandomHowDidYouFindUsOption();

        logger.info("Step 18: Selecting Language");
        udp.selectLanguage();

        logger.info("Step 19: Clicking on 'Begin Billing' button");
        udp.clickBeginBillingButton();

        logger.info("Step 20: To verify user landed on dashboard page");
        Assert.assertTrue(udp.isUserLandedOnDashboard(), "Dashboard page not loaded");

        su.captureScreenShot(getDriver(), "testValidMobileNumberAndOtpRegistration_passed");

    }

}
