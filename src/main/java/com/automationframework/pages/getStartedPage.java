package com.automationframework.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class getStartedPage {
    WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    public getStartedPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@class='option-card']/p[text()='Create Invoice']")
    WebElement createInvoiceBtn;

    @FindBy(xpath = "//div[normalize-space(text())=\"I Don't Have The Bill\"]")
    List<WebElement> iDontHaveTheBillOption;


    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public boolean isCreateInvoiceButtonVisible() {
        switchToDefaultContent();
        try {
            wait.until(ExpectedConditions.visibilityOf(createInvoiceBtn));
            return createInvoiceBtn.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickCreateInvoiceButton() throws InterruptedException {
        Thread.sleep(3000);
        createInvoiceBtn.click();

    }

    public void clickIDontHaveTheBillIfPresent() {
        try {
            // Wait a few seconds to check if the option appears
            wait.until(driver -> !iDontHaveTheBillOption.isEmpty());

            WebElement option = iDontHaveTheBillOption.get(0);
            if (option.isDisplayed()) {
                wait.until(ExpectedConditions.elementToBeClickable(option)).click();
                System.out.println("Clicked on 'I Don't Have The Bill' option.");
            } else {
                System.out.println("'I Don't Have The Bill' option is not visible.");
            }
        } catch (Exception e) {
            System.out.println("'I Don't Have The Bill' option not present, skipping click.");
            // Do not throw — continue test
        }
    }



}
