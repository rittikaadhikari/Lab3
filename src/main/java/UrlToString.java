import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author rittikaadhikari
 *
 */
public class UrlToString {
    /**
     * Retrieve contents from a URL and return them as a string.
     *
     * @param url url to retrieve contents from
     * @return the contents from the url as a string, or an empty string on error
     */
    public static String urlToString(final String url) {
        Scanner urlScanner;
        try {
            urlScanner = new Scanner(new URL(url).openStream(), "UTF-8");
        } catch (IOException e) {
            return "";
        }
        String contents = urlScanner.useDelimiter("\\A").next();
        urlScanner.close();
        return contents;
    }

    /**
     *
     * @param url url to retrieve contents from
     * @return the number of words
     */
    public static int totalWordCount(final String url) {
        String text = urlToString(url);
        String[] words = text.split(" ");
        return words.length;
    }

    /**
     *
     * @param url url to retrieve contents from
     * @param find the number of times one word repeats
     * @return the number of times one word repeats
     */
    public static int getWordCountOfWord(final String url, final String find) {
        String text = urlToString(url);
        text = text.toLowerCase();
        text = text.replaceAll("[^a-z ]", "");
        String copyFind = find.toLowerCase();
        String[] words = text.split(" ");
        int specificWordCount = 0;
        for (String word : words) {
            if (word.equals(copyFind)) {
               specificWordCount++;
            }
        }
        return specificWordCount;
    }

   /**
    *
    * @param url url to retrieve contents from
    * @return number of unique words in url
    */
   public static int getNumUniqueWords(final String url) {
       String text = urlToString(url);
       text = text.toLowerCase();
       text = text.replaceAll("[^a-z ]", "");
       String[] words = text.split(" ");

       ArrayList<String> uniqueWords = new ArrayList<String>();

       int count = 0;
       while (count < words.length) {
           if (!uniqueWords.contains(words[count])) {
              uniqueWords.add(words[count]);
           }
           count++;
       }
       return uniqueWords.size();
   }


    /**
     *
     * @param args arguments
     */
    public static void main(final String[] args) {
        System.out.println("Number of Words: "
    + totalWordCount("http://erdani.com/tdpl/hamlet.txt"));
        System.out.println("Number of Words: "
    + totalWordCount("https://www.bls.gov/tus/charts/chart9.txt"));
        System.out.println("Number of Words: "
    + totalWordCount("http://tgftp.nws.noaa.gov/data/raw/fz/fzus53.klot.srf.lot.txt"));

        System.out.println("Number of Times 'Prince' Appears in Hamlet: "
        + getWordCountOfWord("http://erdani.com/tdpl/hamlet.txt", "Prince"));

        System.out.println("Number of Unique Words: "
        + getNumUniqueWords("http://erdani.com/tdpl/hamlet.txt"));

    }
}


