package selenium.webdriver.homework;

import basesetup.BaseSetupClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.Constants.*;

public class FirefoxBingSearchTest extends BaseSetupClass {

    private static final Logger logger = Logger.getLogger(FirefoxBingSearchTest.class.getName());

    @Test
    public void verifySearchResultInBing_withFirefox() {
        initializeDriver("firefox");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Navigate to Bing.com
        driver.get(BING_URL);

        // Navigate to Bing.com and agree to consent
        WebElement agreeButton = driver.findElement(By.xpath(utils.Constants.BING_AGREE_BUTTON_ID));
        agreeButton.click();

        // Type text in search field
        WebElement searchField = driver.findElement(By.xpath(BING_SEARCH_FIELD_ID));
        searchField.sendKeys(SEARCH_TERM);

        // Click search button
        WebElement searchButton = driver.findElement(By.xpath(BING_SEARCH_BUTTON_XPATH));
        searchButton.submit();

        // Wait for the result and then assert
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement firstResult = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BING_FIRST_RESULT_XPATH)));
        String firstResultText = firstResult.getText();

        // Verify if the result matches the expected or reversed expected
        // Verify if the result matches the expected or reversed expected
        assertTrue(firstResultText.contains(VALIDATE_RESULT) || firstResultText.contains(REVERSED_VALIDATE_RESULT),
                "Expected result not found: " + firstResultText);

        // Print a message after the test passes
        logger.log(Level.INFO, "Test passed! Search result found: " + firstResultText);
    }
}
