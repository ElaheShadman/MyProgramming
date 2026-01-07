/*
InstantGrat.java
A tiny interactive "instant gratification" CLI for Codespaces.
Features: compliments, jokes, ASCII art, quick countdown/timer, progress bar, spinner, system info.
Single-file: run with `java InstantGrat.java` (Java 11+) or compile then run.
*/
import java.util.Random;
import java.util.Scanner;

public class InstantGrat {
    static final String RESET = "\u001B[0m";
    static final String RED = "\u001B[31m";
    static final String GREEN = "\u001B[32m";
    static final String YELLOW = "\u001B[33m";
    static final String BLUE = "\u001B[34m";
    static final String MAGENTA = "\u001B[35m";
    static final String CYAN = "\u001B[36m";
    static final Random R = new Random();

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        banner();
        while (true) {
            menu();
            System.out.print(CYAN + "Choose an option: " + RESET);
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1": compliment(); break;
                case "2": joke(); break;
                case "3": asciiArt(); break;
                case "4": quickCountdown(sc); break;
                case "5": progressDemo(); break;
                case "6": spinnerDemo(); break;
                case "7": systemInfo(); break;
                case "0": goodbye(); sc.close(); return;
                default: System.out.println(YELLOW + "Unknown option — try again." + RESET);
            }
            System.out.println(); // whitespace between runs
            System.out.print("Press Enter to return to menu...");
            sc.nextLine();
        }
    }

    static void banner() {
        System.out.println(GREEN + "==========================================" + RESET);
        System.out.println(BLUE + "   InstantGrat — little wins for your day   " + RESET);
        System.out.println(GREEN + "==========================================" + RESET);
    }

    static void menu() {
        System.out.println(MAGENTA + "1." + RESET + " Instant compliment");
        System.out.println(MAGENTA + "2." + RESET + " Quick joke");
        System.out.println(MAGENTA + "3." + RESET + " ASCII art (cute)");
        System.out.println(MAGENTA + "4." + RESET + " Quick countdown (pick seconds)");
        System.out.println(MAGENTA + "5." + RESET + " Short progress demo");
        System.out.println(MAGENTA + "6." + RESET + " Spinner surprise");
        System.out.println(MAGENTA + "7." + RESET + " Show system info");
        System.out.println(MAGENTA + "0." + RESET + " Exit");
    }

    static void compliment() {
        String[] comps = {
            "You're doing great — keep going!",
            "Small steps. Big progress. You're proving it.",
            "Your code matters. So do you.",
            "You're the kind of person who finishes things.",
            "You're creative, focused, and capable.",
            "Nice work showing up today. That counts."
        };
        System.out.println(GREEN + "✨ " + comps[R.nextInt(comps.length)] + RESET);
    }

    static void joke() {
        String[] jokes = {
            "Why do programmers prefer dark mode? Because light attracts bugs!",
            "I told my computer I needed a break — it said 'No problem, I'll go to sleep.'",
            "Why do Java developers wear glasses? Because they can't C#.",
            "A SQL query walks into a bar, sees two tables and asks: 'Can I join you?'"
        };
        System.out.println(YELLOW + "😄 " + jokes[R.nextInt(jokes.length)] + RESET);
    }

    static void asciiArt() {
        String[] arts = {
            "  (\\_/)\n ( •_•)\n / >💻   — cute dev bunny says hi!",
            "   .--.\n  |o_o |\n  |:_/ |\n //   \\ \\\n(|     | )\n/'\\_   _/`\\\n\\___)=(___/  — console owl",
            "   _____\n  /     \\\n |  0 0  |\n |   ^   |\n |  '-'  |\n  \\_____/\n   / | \\\n  /  |  \\  — smile!"
        };
        System.out.println(CYAN + arts[R.nextInt(arts.length)] + RESET);
    }

    static void quickCountdown(Scanner sc) {
        try {
            System.out.print("Enter seconds for quick countdown (e.g. 5): ");
            String line = sc.nextLine().trim();
            int s = Math.max(1, Integer.parseInt(line));
            System.out.println(GREEN + "Starting countdown..." + RESET);
            for (int i = s; i >= 1; i--) {
                System.out.print("\r" + RED + "  " + i + " " + RESET + " seconds left  ");
                Thread.sleep(1000);
            }
            System.out.println("\r" + GREEN + "  Done!            " + RESET);
            System.out.println(BLUE + "Take a breath — one small win ✅" + RESET);
        } catch (NumberFormatException e) {
            System.out.println(YELLOW + "That didn't look like a number. Try again." + RESET);
        } catch (InterruptedException ignored) {}
    }

    static void progressDemo() {
        System.out.println("Simulating a short task:");
        int total = 30;
        for (int i = 0; i <= total; i++) {
            int percent = (i * 100) / total;
            int bars = (i * 20) / total;
            StringBuilder sb = new StringBuilder();
            sb.append("\r[");
            for (int j = 0; j < bars; j++) sb.append("=");
            for (int j = bars; j < 20; j++) sb.append(" ");
            sb.append("] ");
            sb.append(String.format("%3d%%", percent));
            System.out.print(BLUE + sb.toString() + RESET);
            try { Thread.sleep(60 + R.nextInt(120)); } catch (InterruptedException ignored) {}
        }
        System.out.println("\n" + GREEN + "Task complete — high five! ✋" + RESET);
    }

    static void spinnerDemo() {
        System.out.print("Giving you a spinner for fun ");
        char[] spin = {'|','/','-','\\'};
        for (int i = 0; i < 30; i++) {
            System.out.print("\r" + "Working " + spin[i % spin.length]);
            try { Thread.sleep(120); } catch (InterruptedException ignored) {}
        }
        System.out.println("\r" + GREEN + " All done!          " + RESET);
    }

    static void systemInfo() {
        String os = System.getProperty("os.name") + " " + System.getProperty("os.version");
        String user = System.getProperty("user.name");
        long free = Runtime.getRuntime().freeMemory() / (1024 * 1024);
        long total = Runtime.getRuntime().totalMemory() / (1024 * 1024);
        System.out.println(MAGENTA + "User: " + RESET + user);
        System.out.println(MAGENTA + "OS:   " + RESET + os);
        System.out.println(MAGENTA + "JVM memory (MB): free=" + free + " total=" + total + RESET);
        System.out.println(CYAN + "Tip: Run this in a Codespace terminal — try resizing the terminal and watch ASCII art wrap!" + RESET);
    }

    static void goodbye() {
        System.out.println(GREEN + "Nice coding session — see you soon! ✨" + RESET);
    }
}
