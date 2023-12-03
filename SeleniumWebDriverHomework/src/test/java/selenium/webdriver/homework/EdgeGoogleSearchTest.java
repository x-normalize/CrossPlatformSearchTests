package selenium.webdriver.homework;

import basesetup.BaseSetupClass;
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

public class EdgeGoogleSearchTest extends BaseSetupClass {

    private static final Logger logger = Logger.getLogger(EdgeGoogleSearchTest.class.getName());

    @Test
    public void verifySearchResultInGoogle_withEdge() {
        initializeDriver("edge");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Navigate to Google.com
        driver.get(GOOGLE_URL);

        // Navigate to Google.com and agree to consent
        WebElement agreeButton = driver.findElement(By.xpath(utils.Constants.GOOGLE_AGREE_BUTTON_ID));
        agreeButton.click();

        // Type text in search field
        WebElement searchField = driver.findElement(By.xpath(GOOGLE_SEARCH_FIELD_NAME));
        searchField.sendKeys(SEARCH_TERM);

        // Click search button
        WebElement searchButton = driver.findElement(By.xpath(GOOGLE_SEARCH_BUTTON_NAME));
        searchButton.submit();

        // Wait for the result and then assert
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement firstResult = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(GOOGLE_FIRST_RESULT_XPATH)));
        String firstResultText = firstResult.getText();

        // Verify if the result matches the expected or reversed expected
        assertTrue(firstResultText.contains(VALIDATE_RESULT) || firstResultText.contains(REVERSED_VALIDATE_RESULT),
                "Expected result not found: " + firstResultText);

        // Print a message after the test passes
        logger.log(Level.INFO, "Test passed! Search result found: " + firstResultText);
    }
}
