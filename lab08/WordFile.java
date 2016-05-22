/**
   Permits word by word reading of a file.
*/

import java.io.*;

public class WordFile {

  private String filename;
  private StreamTokenizer fileStream;

  /**
     Open file for reading.
   */
  public WordFile(String infilename) throws FileNotFoundException {
      filename = infilename;
      reset();
  }

  /**
     Opens the file for reading from the beginning.
   */
    public void reset() throws FileNotFoundException {

	FileInputStream f = new FileInputStream(filename);
	InputStreamReader i = new InputStreamReader(f);
	Reader r = new BufferedReader(i);
	
	fileStream = new StreamTokenizer(r);
    }

  /**
     @return true if reading is complete
   */
  public boolean isEOF() {
      return (fileStream.ttype == StreamTokenizer.TT_EOF);
  }


  /**
   @return the next word from the file.
   Returns null if at EOF.
   */
    public Word nextWord() throws IOException {

	if( isEOF() ) return null;
	fileStream.nextToken();
	if( isEOF() ) return null;

	return new Word(fileStream.sval);
  }
}
