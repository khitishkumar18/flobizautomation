\# 🚀 Test Automation Framework



\## 📝 Overview

This project is an Automation Framework using \*\*TestNG\*\* and \*\*ExtentReports\*\*. It follows the \*\*Page Object Model (POM)\*\* for organizing page classes and reusable utilities.



\## 📂 Project Structure



src/main/java/com/testautomationframework/

├── listeners/ - Listeners for ExtentReports integration

├── pages/ - Page classes with elements and methods

├── utilities/ - Helpers like RetryAnalyzer, TestUtils, ReadConfig, ScreenshotUtil

└── base/ - BaseClass for WebDriver initialization and setup



src/main/java/com/testautomationframework/testcases/

└── TC\_LoginPage - Test case classes

testErrorForEmptyMobileNumber()
➤ Verifies the error shown when the mobile number field is left empty.

testErrorOnInvalidOtpLength()
➤ Validates error message when entering an OTP of invalid length.

testOtpMismatchAlertMessage()
➤ Verifies that an appropriate alert or error message is shown when the entered OTP does not match the expected value.

testAlertForMissingUserDetails()
➤ Checks for alerts shown when required user detail fields are left empty after login.

testSuccessfulLoginRedirection()
➤ Confirms that the user is redirected to the dashboard after successful login.



src/test/resources/

├── config.properties - baseUrl, browser, defaultOtp

├── log4j.properties - Logging configuration

├── logs/ - Logs folder

├── reports/ - Test reports folder

└── screenshots/ - Screenshots on pass/failure





\## 📦 Maven Dependencies (`pom.xml`)

\- 🧪 Selenium WebDriver  

\- ✅ TestNG  

\- 📊 ExtentReports  

\- 📝 Log4j  

\- 🔧 WebDriverManager  



\## ▶️ Test Suite Execution

\- The `testng.xml` file defines the test suite and test cases to run.  (mvn test -DsuiteXmlFile=testng.xml) 

\- Run tests via your IDE or command line using this file.



\## ⚙️ How to Use

1\. Run tests with `testng.xml` in your IDE or command line.  

2\. After tests complete, view reports in the `reports` folder and screenshots in the `screenshots` folder.



\## ✨ Features

\- Organized with Page Object Model  

\- Automatic retry for failed tests  

\- Beautiful, detailed ExtentReports  

\- Screenshots captured on test pass/failure  

\- Configurable via `config.properties`  

\- Detailed logs using Log4j  



\## 📞 Contact

\*\*Khitish Kumar Nayak\*\*  

📱 7008784532

