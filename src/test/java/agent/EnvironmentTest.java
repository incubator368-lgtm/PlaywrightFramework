package agent;

public class EnvironmentTest {


	    public static void main(String[] args) {

	        String apiKey = System.getenv("OPENAI_API_KEY");

	        if (apiKey == null || apiKey.isBlank()) {
	            System.out.println("OPENAI_API_KEY is NOT set");
	        } else {
	            System.out.println("OPENAI_API_KEY is set successfully");
	        }
	    
	}
}
