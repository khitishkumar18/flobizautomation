package com.automationframework.pages;

import com.automationframework.base.BaseClass;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class userDetailsPage extends BaseClass {
    WebDriver driver;

    public userDetailsPage(WebDriver driver) throws IOException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    // Business Name Input Field
    @FindBy(xpath = "//input[@formcontrolname='name']")
    WebElement businessNameInput;

    // Business Registration Type Dropdown
    @FindBy(xpath = "//div[contains(@class,'business-registration-type')]//div[@id='dropdownMenuButton']")
    WebElement businessRegistrationTypeDropdown;

    // Business Registration Type Menu Options
    @FindBy(xpath = "//div[contains(@class,'business-registration-type')]//div[@id='dropdownMenuButton']/following-sibling::div//ul/li")
    List<WebElement> businessRegistrationTypeOptions;

    // Business Type Dropdown
    @FindBy(xpath = "//div[contains(text(),'Business type')]/parent::div//div[@id='dropdownMenuButton']")
    WebElement businessTypeDropdown;

    // Business Type Menu Options
    @FindBy(xpath = "//div[contains(text(),'Business type')]/parent::div//div[@id='dropdownMenuButton']/following-sibling::div//ul/li")
    List<WebElement> businessTypeOptions;

    // Industry Type Search Input
    @FindBy(xpath = "//input[@placeholder='Search Industry Types']")
    WebElement industryTypeSearchInput;

    // Industry Type Option - Electronics (1st option)
    @FindBy(xpath = "(//div[contains(@class,'mbb-option')])[1]")
    WebElement industryTypeOptionElectronics;

    // Continue Button
    @FindBy(xpath = "//button[contains(text(),'Continue')]")
    WebElement continueButton;

    // Billing Requirement Radio Options
    @FindBy(xpath = "//*[contains(text(),'Select your billing requirement?')]/parent::div//*[@type='radio']/following-sibling::span")
    List<WebElement> billingRequirementOptions;

    // Size of Your Business Radio Options
    @FindBy(xpath = "//*[contains(text(),'Size of your business?')]/parent::div//*[@type='radio']/following-sibling::span")
    List<WebElement> businessSizeOptions;

    // "How did you find out about myBillBook?" Radio Options
    @FindBy(xpath = "//*[contains(text(),'How did you find out about myBillBook?')]/parent::div//*[@type='radio']/following-sibling::span")
    List<WebElement> howDidYouFindUsOptions;

    // "Which language do you like to talk in?" Dropdown
    @FindBy(xpath = "//div[contains(text(),'Which language do you like to talk in?')]/parent::div//div[@id='dropdownMenuButton']")
    WebElement languageDropdown;

    // English Option under "Which language do you like to talk in?" Dropdown
    @FindBy(xpath = "//div[contains(text(),'Which language do you like to talk in?')]/parent::div//div[@id='dropdownMenuButton']/following-sibling::div//ul/li/a/span[contains(text(),'English')]")
    WebElement englishLanguageOption;

    // "Begin Billing" Button
    @FindBy(xpath = "//button[contains(text(),'Begin Billing')]")
    WebElement beginBillingButton;

    @FindBy(xpath = "//*[@aria-label='Please fix error/s to proceed']")
    WebElement registrationPageEmptyFieldAlert;

    @FindBy(xpath = "//*[contains(@class,'validation-error-message')]")
    WebElement registrationPageEmptyFieldErrorMessage;

    public boolean isBusinessNameInputVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(businessNameInput));
            return businessNameInput.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void enterRandomBusinessName() {
        int randomNum = 1000 + new Random().nextInt(9000); // Generates number from 1000–9999
        String businessName = "AutoBusiness" + randomNum;

        businessNameInput.clear();
        businessNameInput.sendKeys(businessName);
    }

    // Select random Business Registration Type
    public void selectRandomBusinessRegistrationType() {

        businessRegistrationTypeDropdown.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(businessRegistrationTypeOptions));
        int randomIndex = new Random().nextInt(businessRegistrationTypeOptions.size());
        WebElement option = businessRegistrationTypeOptions.get(randomIndex);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
        option.click();
    }

    // Select random Business Type
    public void selectRandomBusinessType() {

        businessTypeDropdown.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(businessTypeOptions));
        int randomIndex = new Random().nextInt(businessTypeOptions.size());
        WebElement option = businessTypeOptions.get(randomIndex);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
        option.click();
        businessTypeDropdown.click();
    }

    // Select industry type
    public void selectIndustryType() {
        industryTypeSearchInput.click();
        wait.until(ExpectedConditions.visibilityOf(industryTypeOptionElectronics));
        industryTypeOptionElectronics.click();
    }

    // Waits for the 'Continue' button to become clickable and then clicks it
    public void clickContinueButton() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        continueButton.click();
    }

    // Select random Billing requirement
    public void selectRandomBillingRequirement() throws InterruptedException {
        Thread.sleep(3000);
        wait.until(ExpectedConditions.visibilityOfAllElements(billingRequirementOptions));
        int randomIndex = new Random().nextInt(billingRequirementOptions.size());
        WebElement option = billingRequirementOptions.get(randomIndex);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
        option.click();
    }

    // Select random size of business
    public void selectRandomBusinessSize() {
        wait.until(ExpectedConditions.visibilityOfAllElements(businessSizeOptions));
        int randomIndex = new Random().nextInt(businessSizeOptions.size());
        WebElement option = businessSizeOptions.get(randomIndex);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
        option.click();
    }

    //Selects a random option from the "How did you find us?" options.
    public void selectRandomHowDidYouFindUsOption() {
        wait.until(ExpectedConditions.visibilityOfAllElements(howDidYouFindUsOptions));
        int randomIndex = new Random().nextInt(howDidYouFindUsOptions.size());
        WebElement option = howDidYouFindUsOptions.get(randomIndex);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
        option.click();
    }

    // Select language
    public void selectLanguage() {
        languageDropdown.click();
        wait.until(ExpectedConditions.visibilityOf(englishLanguageOption));
        englishLanguageOption.click();
    }

    // Waits for the 'Begin billing' button to become clickable and then clicks it
    public void clickBeginBillingButton() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(beginBillingButton));
        beginBillingButton.click();
        Thread.sleep(10000);
    }

    public boolean isUserLandedOnDashboard() {
        String actualUrl = getDriver().getCurrentUrl();
        return actualUrl.equals("https://niobooks.in/app/dashboard")
                || actualUrl.equals("https://niobooks.in/app/e-invoicing-billing-introduction");
    }


    //Checks whether the empty field alert is visible on the registration page
    public boolean isEmptyFieldAlertVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(registrationPageEmptyFieldAlert));
            return registrationPageEmptyFieldAlert.isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    //Checks whether the validation error message is visible on the registration page.
    public boolean isEmptyFieldErrorMessageVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(registrationPageEmptyFieldErrorMessage));
            return registrationPageEmptyFieldErrorMessage.isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }
}


