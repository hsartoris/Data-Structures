import java.util.Scanner;

public class RunMarkov {
	static String requestInput = "Please enter a word to start a sentence with (or /help): ";

	public static void main(String[] args) {
		Markov markov;
		if (args.length == 1) {
			markov = new Markov(args[0], false);
		} else if (args.length > 1) {
			markov = new Markov();
			for (String s : args) {
				markov.addFile(s);
			}
		} else {
			System.out.println("Please provide at least one argument pointing to a text file.");
			return;
		}
		Scanner reader = new Scanner(System.in);
		while (true) {
			System.out.print(requestInput);
			String tmp = reader.nextLine();
			if (tmp.equals("/halt")) {
				reader.close();
				return;
			}
			if (tmp.equals("/help")) {
				System.out.println("Available commands: /halt; /addfile; /info");
				continue;
			}
			if (tmp.equals("/addfile")) {
				System.out.print("Please enter your additional filename: ");
				tmp = reader.nextLine();
				markov.addFile(tmp);
				continue;
			}
			if (tmp.equals("/info")) {
				System.out.println(markov);
				continue;
			}
			System.out.println("How many words do you want the sentence to be (maximum; may be shorter)?");
			int input = reader.nextInt();
			System.out.println(markov.generateSentence(tmp, input));
			reader.nextLine();
		}
	}
}
