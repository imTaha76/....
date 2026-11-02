import java.util.*;

public class SimpleChatbot {
    static Map<String, String[]> replies = new LinkedHashMap<>();
    static void add(String key, String... values) { replies.put(key, values); }

    public static void main(String[] args) {
        add("hello", "Hello!", "Hi there!");
        add("hi", "Hello!", "Hey!");
        add("thank you", "You're welcome!", "Anytime!");
        add("thanks", "You're welcome!", "No problem!");
        add("tell me a joke", "Why did the computer get cold? It left its Windows open!");
        add("how are you", "I'm doing great!", "I'm ready to help!");

        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        System.out.println("Chatbot ready. Type bye to exit.");
        while (true) {
            System.out.print("You: ");
            String input = sc.nextLine().toLowerCase()
                    .replaceAll("[^a-zA-Z0-9\\s]", "").trim();
            if (input.equals("bye")) break;
            String answer = null;
            for (String pattern : replies.keySet()) {
                if ((" " + input + " ").contains(" " + pattern + " ")) {
                    String[] options = replies.get(pattern);
                    answer = options[random.nextInt(options.length)];
                    break;
                }
            }
            System.out.println("Bot: " + (answer == null ? "Sorry, I don't understand." : answer));
        }
        sc.close();
    }
}
