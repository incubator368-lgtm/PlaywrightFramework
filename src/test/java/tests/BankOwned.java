package tests;

import org.testng.annotations.Test;

import baseTest.BaseTest;

public class BankOwned extends BaseTest {
	
	@Test
	public void bankOwnedFlow() {
		  System.out.println("Bank Owned Class running");
		  page.locator("(//select)[1]").selectOption("BANK OWNED");
		  page.locator("(//input[@type='text'])[4]").fill("123");
		  page.locator("(//select)[4]").selectOption("N");
		  String uniqueName = "AD SampleEntry_" + System.currentTimeMillis();
		  page.locator("(//input[@type='text'])[5]").fill(uniqueName);
		  page.locator("(//select)[5]").selectOption("ST");
		  page.locator("(//select)[9]").selectOption("FL");
		  page.locator("(//select)[10]").selectOption("CLAY");
		  page.locator("(//select)[11]").selectOption("ORANGE PARK");
		  page.locator("//input[@value='Check']").click();
		  page.locator("//button[text()='Ok']").click();
		  page.locator("//button[text()='Ok']").click();
		  page.locator("//a[text()='Listing & Contact']").click();
		  page.locator("(//input[@type='text'])[1]").fill("http://192.168.0.5:3002/dashboard");
		  page.locator("//body").click();
		  page.locator("//a[contains(text(),'Trustee')]").click();
		  page.locator("(//input[@type='text'])[1]").fill("ALASKA TRUSTEE LLC");
		  page.locator("(//input[@type='text'])[2]").fill("3000 A STREET SUITE 200");
		  page.locator("(//input[@type='text'])[3]").fill("907-222-4300");
		  page.locator("(//select)[1]").selectOption("AK");
		  page.locator("(//select)[2]").selectOption("ADAK");
		  page.locator("(//input[@type='text'])[6]").fill("A BETTER MORTGAGE COMPANY");
		  page.locator("(//input[@type='text'])[8]").fill("907-226-2384");
		  page.locator("//body").click();
		  page.locator("//a[contains(text(),'Building')]").click();
		  page.locator("(//select)[1]").selectOption("CON");
		  page.locator("//a[contains(text(),'BANK OWNED')]").click();
		  page.locator("(//input[@type='text'])[1]").fill("Charles W Fawcett");
		  page.locator("(//input[@type='text'])[3]").fill("333-234-2345");
		  page.locator("//span[text()='Submit']").click();
		  page.locator("//button[text()='Ok']").click();
		  page.locator("//button[text()='Ok']").click();
			System.out.println("Bankowned entry saved");


	}

}
