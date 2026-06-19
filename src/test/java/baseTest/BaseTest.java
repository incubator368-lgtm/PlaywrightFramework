package baseTest;

	import org.testng.annotations.AfterSuite;
	import org.testng.annotations.BeforeSuite;

	import com.microsoft.playwright.*;

	public class BaseTest {

	    protected static Playwright playwright;
	    protected static Browser browser;
	    protected static BrowserContext context;
	    protected static Page page;

	    @BeforeSuite
	    public void setup() {

	        playwright = Playwright.create();
	        browser = playwright.chromium().launch(
	                new BrowserType.LaunchOptions()
	                        .setHeadless(false));
	        context = browser.newContext(
	                new Browser.NewContextOptions()
	                        .setViewportSize(1920, 1080));

	        page = context.newPage();

	        page.navigate("http://192.168.0.5:7200/auth");

	        System.out.println("Browser Opened");

	        // Login
	        page.locator("input[name='emailId']").fill("test2");

	        page.locator("input[name='password']").fill("test2");

	        page.locator(".bg-sky-400").click();

	        // Handle Alert if present
	        page.onceDialog(dialog -> {
	            System.out.println("Alert detected: " + dialog.message());
	            dialog.accept();
	        });

	        System.out.println("Logged in successfully");
	    }

	    @AfterSuite
	    public void tearDown() {

	        if (browser != null) {
	            browser.close();
	        }

	        if (playwright != null) {
	            playwright.close();
	        }
	    }
	

}
