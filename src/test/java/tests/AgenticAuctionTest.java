package tests;

import org.testng.annotations.Test;

import agent.TestAgent;
import baseTest.BaseTest;

public class AgenticAuctionTest extends BaseTest {

    @Test
    public void testAuctionSaveUsingAgent() throws Exception {

        System.out.println("Agentic Auction Test Started");

        TestAgent agent = new TestAgent(page);

        agent.testAuctionSave();

        System.out.println("Agentic Auction Test Completed");
    }
}