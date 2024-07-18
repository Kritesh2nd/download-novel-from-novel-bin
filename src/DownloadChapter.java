import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadChapter {

    private int chapterCounter;
    private int totalDownload;
    private final String fileSavingPath;
    private final String startLink;

    private boolean firstDownload = true;
    private String newSavePath = "";
    private final FindNextLink findNextLink = new FindNextLink();

    public DownloadChapter(int chapterCounter, int totalDownload, String fileSavingPath, String startLink) {
        this.chapterCounter = chapterCounter;
        this.totalDownload = totalDownload;
        this.fileSavingPath = fileSavingPath;
        this.startLink = startLink;
    }

    public String[] getLinkAndPath() {
        String downloadLink = startLink;
        String startChar = "chapter";
        String fileName = extractStringFrom(downloadLink, startChar);
        String savePath = fileSavingPath + fileName+".html";

        if (firstDownload) {
            newSavePath = savePath;
            firstDownload = false;
        } else {

            downloadLink = findNextLink.getNextLink(newSavePath);

            startChar = "chapter";
            fileName = extractStringFrom(downloadLink, startChar);

            savePath = fileSavingPath + fileName+".html";
            newSavePath = savePath;
        }
        String[] linkAndPath;
        linkAndPath = new String[]{downloadLink, newSavePath};
        return linkAndPath;
    }

    public void handelDownload(){

        String[] linkAndPath = getLinkAndPath();
        String link = linkAndPath[0];
        String path = linkAndPath[1];



        System.out.println("Chapter Counter: "+chapterCounter);
        System.out.println("Total remaining downloads: "+totalDownload);
        System.out.println("File downloading link: "+link);
        System.out.println("File saving path: "+path);
        downloadChapter(link,path);
        System.out.println("\n------------------------------------------------------------");
        chapterCounter++;

        if(totalDownload > 1){
            totalDownload--;
            handelDownload();
        }
    }

    public void downloadChapter(String url, String path){
        try {
            URL websiteUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) websiteUrl.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();

                File file = new File(path);
                file.getParentFile().mkdirs();
                FileWriter writer = new FileWriter(file);
                writer.write(content.toString());
                writer.close();

                System.out.println("Website downloaded successfully. Saved as " + file);
            } else {
                System.out.println("Failed to download website. HTTP error code: " + responseCode);
            }
        } catch (MalformedURLException e) {
            System.out.println("Invalid URL: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error downloading website: " + e.getMessage());
        }
    }

    private String extractStringFrom(String input, String startChars) {
        int startIndex = input.indexOf(startChars);
        if (startIndex != -1) {
            return input.substring(startIndex);
        } else {
            return "Start characters not found in the input string.";
        }
    }
}