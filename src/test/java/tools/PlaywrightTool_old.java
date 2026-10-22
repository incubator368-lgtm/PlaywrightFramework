package tools;

import com.microsoft.playwright.Page;

public class PlaywrightTool_old {

    private final Page page;

    public PlaywrightTool_old(Page page) {
        this.page = page;
    }

    public void createAuctionProperty() {

        System.out.println("Agent Tool: Creating Auction property");

        // Property ID
        page.locator("(//input[@type='text'])[4]").fill("123");

        // Property Type
        page.locator("(//select)[4]").selectOption("N");

        // Unique Property Name
        String uniqueName = "AA SampleEntry_" + System.currentTimeMillis();

        page.locator("(//input[@type='text'])[5]").fill(uniqueName);

        // Street Type
        page.locator("(//select)[5]").selectOption("BLVD");

        // State
        page.locator("(//select)[9]").selectOption("AK");

        // County
        page.locator("(//select)[10]").selectOption("ALEUTIANS EAST");

        // City
        page.locator("(//select)[11]").selectOption("ADAK");

        // Check
        page.locator("//input[@value='Check']").click();

        // Handle confirmation dialogs
        page.locator("//button[text()='Ok']").click();

        page.locator("//button[text()='Ok']").click();

        // Listing & Contact
        page.locator("//a[text()='Listing & Contact']").click();

        // Website
        page.locator("(//input[@type='text'])[1]")
                .fill("http://192.168.0.5:3002/dashboard");

        page.locator("//body").click();

        // Trustee
        page.locator("//a[contains(text(),'Trustee')]").click();

        page.locator("(//input[@type='text'])[1]")
                .fill("ALASKA TRUSTEE LLC");

        page.locator("(//input[@type='text'])[2]")
                .fill("3000 A STREET SUITE 200");

        page.locator("(//input[@type='text'])[3]")
                .fill("907-222-4300");

        page.locator("(//select)[1]").selectOption("AK");

        page.locator("(//select)[2]").selectOption("ADAK");

        page.locator("//body").click();

        // Building
        page.locator("//a[contains(text(),'Building')]").click();

        page.locator("(//select)[1]").selectOption("CON");

        page.locator("//body").click();

        // Auction
        page.locator("//a[contains(text(),'AUCTION')]").click();

        page.locator("(//input[@type='text'])[1]")
                .fill("12/08/2026");

        // Submit
        page.locator("//span[text()='Submit']").click();

        page.locator("//button[text()='Ok']").click();

        page.locator("//button[text()='Ok']").click();

        System.out.println("Agent Tool: Auction entry saved");
    }
    
    
    public String getPageContent() {
    	
    return	page.locator("body").innerText();
    	
    }
    
    public String getPageState() {

        String url = page.url();
        String title = page.title();

        StringBuilder state = new StringBuilder();

        state.append("URL: ").append(url).append("\n");
        state.append("Title: ").append(title).append("\n");

        // Current visible page/section
        if (page.getByText("AUCTION", new Page.GetByTextOptions().setExact(true)).count() > 0) {
            state.append("Current Section: AUCTION\n");
        }

        // Important buttons
        if (page.getByText("Next", new Page.GetByTextOptions().setExact(true)).count() > 0) {
            state.append("Next button: visible\n");
        }

        // Check for common success/error messages
        String bodyText = page.locator("body").innerText();

        if (bodyText.contains("saved successfully")) {
            state.append("Result: Save successful\n");
        }

        if (bodyText.contains("error")) {
            state.append("Result: Error message detected\n");
        }

        if (bodyText.contains("required")) {
            state.append("Result: Required field message detected\n");
        }

        // Check important fields
        if (page.locator("input").count() > 0) {
            state.append("Input fields: present\n");
        }

        if (page.locator("select").count() > 0) {
            state.append("Dropdown fields: present\n");
        }

        return state.toString();
    }
}