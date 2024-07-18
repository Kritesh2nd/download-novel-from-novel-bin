public class Main {

    private int currentChapterNumber = 1090;

    // this link will be used for initial link to download chapter, more chapter will be download after this chapter
    private String chapterLink = "https://fast.novelupdates.net/book/shadow-slave/chapter-1090-tyrant";

    // Example: C:\Users\Steve\Desktop\webnovel
    private String pathToSaveDownloadedChapter = "/home/kritesh-thapa/AllFile8/test/";

    // total chapters you want to download from, 100 means total 100 ch will be downloaded from given chapterLink in line 5
    private int totalChapterToDonwload = 660;

    private DownloadChapter downloadChapter = new DownloadChapter(currentChapterNumber,totalChapterToDonwload,pathToSaveDownloadedChapter,chapterLink);

    public static void main(String[] args) {
        System.out.println("Download Html Pages");
        Main m = new Main();
        System.out.println("------------------------------------------------------------");
        System.out.println("----------------------------START---------------------------");
        System.out.println("------------------------------------------------------------\n");
        m.downloadChapter.handelDownload();
        System.out.println("------------------------------------------------------------");
        System.out.println("-----------------------------STOP---------------------------");
        System.out.println("------------------------------------------------------------");
    }


}