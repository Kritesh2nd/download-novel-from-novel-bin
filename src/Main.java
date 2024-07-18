public class Main {

    private int currentChapterNumber = 1090;


    // EDIT: change this data according to your novel choice
    // ADD: add the lnk of first chapter you want to download, more chapter will be downloaded after this chapter
    // EXAMPLE: chapterLink = ""
    private String chapterLink = "";

    // EDIT: change this data according to your novel choice
    // ADD: add the path of folder you want to download chapters in your computer
    // EXAMPLE: pathToSaveDownloadedChapter = "C:\\Users\\Steve\\Desktop\\novel\\";
    private String pathToSaveDownloadedChapter = "";

    // EDIT: change this data according to your novel choice
    // ADD: add the total number of chapters you want to download, 100 means total 100 ch will be downloaded from given chapterLink in line 5
    // EXAMPLE: totalChapterToDonwload = 100;
    private int totalChapterToDonwload = 10;


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