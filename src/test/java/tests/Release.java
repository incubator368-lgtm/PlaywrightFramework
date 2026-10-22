package tests;

import org.testng.annotations.Test;

import baseTest.BaseTest;


public class Release extends BaseTest {
	
	@Test
	public void releaseFlow() {
		System.out.println("Release Class Running");
		page.locator("(//select)[1]").selectOption("RELEASE");
		page.locator("(//input[@type='text'])[4]").fill("123");
		page.locator("(//select)[4]").selectOption("N");
		String uniqueName = "AC SampleEntry_" + System.currentTimeMillis();
		page.locator("(//input[@type='text'])[5]").fill(uniqueName);
		page.locator("(//select)[5]").selectOption("DR");
		page.locator("(//select)[9]").selectOption("AK");
		page.locator("(//select)[10]").selectOption("ALEUTIANS EAST");
		page.locator("(//select)[11]").selectOption("ADAK");
		page.locator("//input[@value='Check']").click();
		page.locator("//button[text()='Ok']").click();
		page.locator("//button[text()='Ok']").click();
		page.locator("//a[text()='Listing & Contact']").click();
		page.locator("(//input[@type='text'])[1]").fill("http://192.168.0.5:3002/dashboard");
		page.locator("(//input[@type='text'])[2]").fill("12/08/2025");
		page.locator("(//input[@type='text'])[5]").fill("S56465");
		page.locator("//a[contains(text(),'Trustee')]").click();
		page.locator("(//input[@type='text'])[1]").fill("ALASKA TRUSTEE LLC");
		page.locator("(//input[@type='text'])[2]").fill("3000 A STREET SUITE 200");
		page.locator("(//input[@type='text'])[3]").fill("907-222-4300");
		page.locator("(//select)[1]").selectOption("AK");
		page.locator("(//select)[2]").selectOption("ADAK");
		page.locator("//body").click();
		page.locator("//a[contains(text(),'Building')]").click();
		page.locator("(//select)[1]").selectOption("CON");
		page.locator("//body").click();
		page.locator("//a[contains(text(),'RELEASE')]").click();
		page.locator("(//input[@type='text'])[2]").fill("04/08/2026");
		page.locator("//span[text()='Submit']").click();
		page.locator("//button[text()='Ok']").click();
		page.locator("//button[text()='Ok']").click();
		System.out.println("Release entry saved");

		


	}

}
