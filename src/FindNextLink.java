import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FindNextLink {

    public String getNextLink(String prevFilePath){
        boolean fileExists = checkIfFileExists(prevFilePath);
        if(!fileExists){
            throw new NoSuchElementException("FIle doesn't exist, file path: "+prevFilePath);
        }
        String srtWithLink = extractedLink(prevFilePath);
        String startChar = "href=\"";
        String endChar = "\"";
        if(srtWithLink == null){
            throw new NoSuchElementException("LINK doesn't exist on file: "+prevFilePath);
        }
        return extractContentLink(srtWithLink,startChar,endChar);
    }

    private String extractedLink(String prevFilePath){
        String startChar = "<a";
        String endChar = "</a>";
        List<String> filteredList;
        try {
            String content = readFile(prevFilePath);
            List<String> anchorLinkList = extractContentFromWebsite(content, startChar, endChar);
            String filterChars = "id=\"next_chap\"";
            filteredList = filterStrings(anchorLinkList, filterChars);
            return filteredList.get(0);
        } catch (IOException e) {
            System.out.println("          ERROR: "+e);
            return "ERROR";
        }
    }

    private String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    private List<String> extractContentFromWebsite(String content, String startChar, String endChar) {
        List<String> anchorLinkList = new ArrayList<String>();
        String regex = Pattern.quote(startChar) + "(.*?)" + Pattern.quote(endChar);
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            anchorLinkList.add(matcher.group(1));
        }
        return anchorLinkList;
    }

    private String extractContentLink(String content, String startChar, String endChar) {
        int startIndex = content.indexOf(startChar);
        int endIndex = content.indexOf(endChar, startIndex + startChar.length());
        if (startIndex != -1 && endIndex != -1) {
            return content.substring(startIndex + startChar.length(), endIndex);
        }
        return "Specified characters not found in the file.";
    }

    private List<String> filterStrings(List<String> stringList, String filterChars) {
        return stringList.stream()
                .filter(str -> str.contains(filterChars))
                .collect(Collectors.toList());
    }

    private boolean checkIfFileExists(String filePath) {
        Path path = Paths.get(filePath);
        return Files.exists(path);
    }
}
