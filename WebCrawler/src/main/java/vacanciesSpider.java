import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class vacanciesSpider {
    public static void main(String[] args) {
        try {
            javax.swing.filechooser.FileSystemView fsv = javax.swing.filechooser.FileSystemView.getFileSystemView();
            String path = fsv.getHomeDirectory().getPath();
            File taipeiJobFile = new File(path + "\\台北職缺.txt");
            taipeiJobFile.createNewFile();
            BufferedWriter taipeiWriter = new BufferedWriter(new FileWriter(taipeiJobFile));
            File taichungFile = new File(path + "\\台中職缺.txt");
            taichungFile.createNewFile();
            BufferedWriter taichungWriter = new BufferedWriter(new FileWriter(taichungFile));
            File kaohsiungFile = new File(path + "\\高雄職缺.txt");
            kaohsiungFile.createNewFile();
            BufferedWriter kaohsiungWriter = new BufferedWriter(new FileWriter(kaohsiungFile));
            int page = 1;
            int taipeiJobCount = 0;
            int taichungJobCount = 0;
            int kaohsiungJobCount = 0;
            while (true){
                Document doc = Jsoup.connect("https://www.cakeresume.com/jobs?ref=navs_jobs&page=" + String.valueOf(page)).get();
                Elements jobInfos = doc.select(".job-item");
                if (!jobInfos.isEmpty()){
                    System.out.printf("正在讀取----第%d頁\n" , page);
                    for (Element jobInfo : jobInfos){
                        Elements jobLocation = jobInfo.select(".job-list-item-tags .info-section .location-section .middot abbr");
                        if (jobLocation.text().contains("台北")){
                            taipeiJobCount = taipeiJobCount + 1;

                        }else if(jobLocation.text().contains("台中")){
                            taichungJobCount = taichungJobCount + 1;

                        }else if(jobLocation.text().contains("高雄")){
                            kaohsiungJobCount = kaohsiungJobCount + 1;

                        }
                    }
                    Thread.sleep(2000);
                }else{
                    System.out.println("所有職缺已寫入文字檔");
                    break;
                }
                System.out.println("讀取完成");
                System.out.println("==================");
                page = page + 1;
            }
            taichungWriter.close();
            taipeiWriter.close();
            kaohsiungWriter.close();
            System.out.println("==================");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}