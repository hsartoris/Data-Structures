import java.util.Scanner;

public class RunMarkov {

	public static void main(String[] args) {
		Markov markov;
		if (args.length == 1) {
			markov = new Markov(args[0]);
		} else {
			System.out.println("Please provide one argument pointing to a text file.");
			return;
		}
		Scanner reader = new Scanner(System.in);
		while (true) {
			System.out.print("Please enter a word to start a sentence with (or /halt to stop): ");
			String tmp = reader.nextLine();
			if (tmp.equals("/halt")) {
				reader.close();
				return;
			}
			System.out.println("How many words do you want the sentence to be (maximum; may be shorter)?");
			int input = reader.nextInt();
			System.out.println(markov.generateSentence(tmp, input));
			reader.nextLine();
		}
	}
}
