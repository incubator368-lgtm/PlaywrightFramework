package agent;

import com.microsoft.playwright.Page;

import tools.PlaywrightTool;

public class TestAgent {

    private final PlaywrightTool playwrightTool;
    private final OllamaClient ollamaClient;

    public TestAgent(Page page) {

        this.playwrightTool = new PlaywrightTool(page);
        this.ollamaClient = new OllamaClient();
    }

    public void testAuctionSave() throws Exception {

        System.out.println("=================================");
        System.out.println("AI TEST AGENT STARTED");
        System.out.println("=================================");

        String goal =
                "Test the Auction Property Save functionality.";

        System.out.println("Goal: " + goal);

        String previousAction = "None";
        String previousResult = "None";
        
        boolean verificationPassed = false;

        for (int step = 1; step <= 5; step++) {

            System.out.println();
            System.out.println("========== AGENT STEP " + step + " ==========");

            // ---------------------------------------
            // 1. OBSERVE CURRENT BROWSER STATE
            // ---------------------------------------

            String pageState = playwrightTool.getPageState();

            System.out.println(">>> Current PageState:");
            System.out.println(pageState);

            // ---------------------------------------
            // 2. ASK AI WHAT TO DO NEXT
            // ---------------------------------------


String prompt = """
        You are a software testing agent.

        Testing goal:
        %s

        Current browser state:
        %s

        Previous action:
        %s

        Previous action result:
        %s

        Available actions:
        1. prepareAuctionProperty
        2. saveAuctionProperty
        3. verifyAuctionProperty
        4. finish

        Rules:
        - If the form has not been prepared, choose prepareAuctionProperty.
        - After the form is prepared, choose saveAuctionProperty.
        - After saving, choose verifyAuctionProperty.
        - Verification checks for the exact visible message:
          "Saved Sucessfully!"
        - If verification has not succeeded, do not choose finish.
        - Choose finish only when the previous action result confirms
          that verification succeeded.
        - Do not repeat an action that has already succeeded.
        - Choose only one action at a time.

        Return exactly ONE action name from the available actions.
        Do not explain.
        Do not return JSON.
        """.formatted(
                goal,
                pageState,
                previousAction,
                previousResult);

            System.out.println(">>> Asking AI to decide...");

            String decision = ollamaClient.ask(prompt).trim();

            System.out.println("AI Decision: " + decision);

            // ---------------------------------------
            // 3. PREPARE AUCTION PROPERTY
            // ---------------------------------------

            if (decision.equalsIgnoreCase("prepareAuctionProperty")) {

                System.out.println(
                        "Agent Decision: Prepare Auction Property");

                playwrightTool.prepareAuctionProperty();

                previousAction = "prepareAuctionProperty";

                previousResult =
                        "Auction property form prepared successfully.";
            }

            // ---------------------------------------
            // 4. SAVE AUCTION PROPERTY
            // ---------------------------------------

            else if (decision.equalsIgnoreCase("saveAuctionProperty")) {

                System.out.println(
                        "Agent Decision: Save Auction Property");

                playwrightTool.saveAuctionProperty();

                previousAction = "saveAuctionProperty";

                previousResult =
                        "Auction property save action completed successfully.";
            }


         // ---------------------------------------
            // 5. VERIFY AUCTION PROPERTY
            // ---------------------------------------
            else if (decision.equalsIgnoreCase("verifyAuctionProperty")) {

                System.out.println("Agent Decision: Verify Auction Property");

                verificationPassed =
                        playwrightTool.verifyAuctionProperty();

                previousAction = "verifyAuctionProperty";

                if (verificationPassed) {

                    previousResult =
                            "Auction property was verified successfully.";

                    System.out.println("Verification result: PASSED");

                    playwrightTool.dismissConfirmationDialogs();

                    System.out.println(
                            "Agent Decision: Finish - Verification passed.");

                    break;  // Stop the loop; do not ask AI for another action

                } else {

                    previousResult =
                            "Auction property verification failed.";

                    System.out.println("Verification result: FAILED");

                    throw new AssertionError(
                            "Auction property save verification failed."
                    );
                }
            }
         // ---------------------------------------
         // 6. FINISH
         // ---------------------------------------
         else if (decision.equalsIgnoreCase("finish")) {

             if (!verificationPassed) {
                 throw new AssertionError(
                         "Agent attempted to finish without " +
                         "successfully verifying the Auction property.");
             }

             System.out.println(
                     "Agent Decision: Finish - Verification passed.");

             break;
         }

            // ---------------------------------------
            // 7. UNKNOWN ACTION
            // ---------------------------------------

            else {

                System.out.println(
                        "AI returned an unknown action: " + decision);

                throw new RuntimeException(
                        "AI returned an unsupported action: " + decision);
            }
        }

        System.out.println();
        System.out.println("=================================");
        System.out.println("TEST AGENT COMPLETED");
        System.out.println("=================================");
    }
}