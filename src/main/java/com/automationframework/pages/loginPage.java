package com.automationframework.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class loginPage {
    WebDriver driver;

    public loginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @FindBy(xpath = "//button[contains(@class,'empty-button') and contains(text(),'Login')]")
    WebElement loginBtn;


    @FindBy(id = "mobile-number")
    WebElement mobileField;

    @FindBy(xpath = "//button/span[text()='Get OTP']")
    WebElement getOTPBtn;

    @FindBy(xpath = "//input[@placeholder='OTP']")
    WebElement otpField;

    @FindBy(xpath = "//button[contains(@class,'otp-verified-register')]/span")
    WebElement submitRegisterBtn;

    // Error when mobile number is missing
    @FindBy(xpath = "//*[contains(@class,'input-error') and text()='Valid mobile number required']")
    WebElement mobileNumberErrorMsg;

    // Error when OTP is too short
    @FindBy(xpath = "//*[@class='input-error' and text()='Enter valid OTP']")
    WebElement shortOtpErrorMsg;

    // Alert when OTP is 6 digits but invalid
    @FindBy(xpath = "//*[@role='alert' and @aria-label='Mobile and otp combination is wrong.']")
    WebElement invalidOtpAlert;


    public void openLoginOrRegisterPopup() throws InterruptedException {
        Thread.sleep(2000);
        loginBtn.click();
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    // Utility method to switch to login frame
    public void switchToLoginFrame() {
        driver.switchTo().frame("login_iframe");
    }




    public void enterMobileNumber(String mobile) {
        mobileField.sendKeys(mobile);
    }

    public void clickGetOtpButton() {
        getOTPBtn.click();
    }

    public void enterOTP(String otp) throws InterruptedException {
        Thread.sleep(2000);
        otpField.clear();
        otpField.sendKeys(otp);
    }

    public void clickRegisterButton() {
        submitRegisterBtn.click();
    }

    public boolean isMobileNumberErrorDisplayed() {
        return mobileNumberErrorMsg.isDisplayed();
    }

    public boolean isShortOtpErrorDisplayed() {
        return shortOtpErrorMsg.isDisplayed();
    }

    public boolean isInvalidOtpAlertDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(invalidOtpAlert));
            return invalidOtpAlert.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

}
