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
            //桌面路徑
            javax.swing.filechooser.FileSystemView fsv = javax.swing.filechooser.FileSystemView.getFileSystemView();
            String path = fsv.getHomeDirectory().getPath();
            //建立新的文字檔
            File taipeiJobFile = new File(path + "\\台北職缺.txt");
            taipeiJobFile.createNewFile();
            BufferedWriter taipeiWriter = new BufferedWriter(new FileWriter(taipeiJobFile));
            File taichungFile = new File(path + "\\台中職缺.txt");
            taichungFile.createNewFile();
            BufferedWriter taichungWriter = new BufferedWriter(new FileWriter(taichungFile));
            File kaohsiungFile = new File(path + "\\高雄職缺.txt");
            kaohsiungFile.createNewFile();
            BufferedWriter kaohsiungWriter = new BufferedWriter(new FileWriter(kaohsiungFile));
            //計算頁碼
            int page = 1;
            //計算職缺總數
            int taipeiJobCount = 0;
            int taichungJobCount = 0;
            int kaohsiungJobCount = 0;
            while (true){
                Document doc = Jsoup.connect("https://www.cakeresume.com/jobs?ref=navs_jobs&page=" + page).get();
                Elements jobInfos = doc.select(".job-item");
                if (!jobInfos.isEmpty()){
                    System.out.printf("正在讀取----第%d頁\n" , page);
                    for (Element jobInfo : jobInfos){
                        Elements jobLocation = jobInfo.select(".job-list-item-tags .info-section .location-section .middot abbr");
                        if (jobLocation.text().contains("台北")){
                            taipeiJobCount = taipeiJobCount + 1;
                            //把文字寫入文字檔
                            taipeiWriter.write("==========台北職缺==========\n");
                            //陳雅蓁
                            //讀取職缺資訊
                            Elements jobContents = jobInfo.select(".job-list-item-content");
                            for (Element jobContent : jobContents){
                                //抓取職缺、公司的名稱與網址的路徑
                                Elements jobs = jobContent.select(".job-title .job-link-wrapper .job-link");
                                Elements companyName = jobContent.select(".page-name a");
                                taipeiWriter.write("職缺：" + jobs.text() + "\n");
                                //抓取並輸出職缺網址
                                for (Element job :jobs){
                                    String jobHref = job.absUrl("href");
                                    taipeiWriter.write("職缺網址:" + jobHref + "\n");
                                }
                                taipeiWriter.write("公司：" + companyName.text() + "\n");
                                //抓取並輸出公司網址
                                for (Element company : companyName){
                                    String companyHref = company.absUrl("href");
                                    taipeiWriter.write("公司網址：" + companyHref + "\n");
                                }
                            }
                            //莊富淇
                            //讀取職缺資料
                            Elements jobTags = jobInfo.select(".job-list-item-tags");
                            for(Element tag : jobTags){
                                //抓取招聘人數、薪資、職缺類別
                                Elements numberOfHires = tag.select(".number-for-hire-section");
                                Elements salary = tag.select(".job-salary");
                                Elements jobLabels = tag.select(".labels a.label.label-default");
                                String number = numberOfHires.text();
                                //判斷是否有該資料並輸出
                                if(jobLabels.hasText()){
                                    taipeiWriter.write("職缺類別: \n" );
                                }
                                else{
                                    taipeiWriter.write("職缺類別 : \n ");
                                }
                                for(Element jobLabel : jobLabels){
                                    taipeiWriter.write("-" + jobLabel.text() + "\n");
                                }
                                if(!number.isEmpty()){
                                    taipeiWriter.write("招聘人數" + numberOfHires.text() + "\n");
                                }
                                else {
                                    taipeiWriter.write("招聘人數 : 沒註明\n ");
                                }
                                if(!salary.isEmpty()){
                                    taipeiWriter.write("薪資 :" + salary.text() + "\n");
                                }else{
                                    taipeiWriter.write("薪資 : 沒註明\n");
                                }
                            }
                        }else if(jobLocation.text().contains("台中")){
                            taichungJobCount = taichungJobCount + 1;
                            taichungWriter.write("==========台中職缺==========\n");
                            Elements jobContents = jobInfo.select(".job-list-item-content");
                            for (Element jobContent : jobContents){
                                Elements jobs = jobContent.select(".job-title .job-link-wrapper .job-link");
                                Elements companyName = jobContent.select(".page-name a");
                                taichungWriter.write("職缺："+ jobs.text() + "\n");
                                for (Element job :jobs){
                                    String jobHref = job.absUrl("href");
                                    taichungWriter.write("職缺網址:" + jobHref + "\n");
                                }
                                taichungWriter.write("公司：" + companyName.text() + "\n");
                                for (Element company : companyName){
                                    String companyHref = company.absUrl("href");
                                    taichungWriter.write("公司網址：" + companyHref + "\n");
                                }
                            }
                            Elements jobTags = jobInfo.select(".job-list-item-tags");
                            for(Element tag : jobTags){
                                Elements numberOfHires = tag.select(".number-for-hire-section");
                                Elements salary = tag.select(".job-salary");
                                Elements jobLabels = tag.select(".labels a.label.label-default");
                                String number = numberOfHires.text();
                                if(jobLabels.hasText()){
                                    taichungWriter.write("職缺類別: \n" );
                                }
                                else{
                                    taichungWriter.write("職缺類別 : \n ");
                                }
                                for(Element jobLabel : jobLabels){
                                    taichungWriter.write("-" + jobLabel.text() + "\n");
                                }
                                if(!number.isEmpty()){
                                    taichungWriter.write("招聘人數" + numberOfHires.text() + "\n");
                                }
                                else {
                                    taichungWriter.write("招聘人數 : 沒註明\n ");
                                }
                                if(!salary.isEmpty()){
                                    taichungWriter.write("薪資 :" + salary.text() + "\n");
                                }else{
                                    taichungWriter.write("薪資 : 沒註明\n");
                                }
                            }

                        }else if(jobLocation.text().contains("高雄")){
                            kaohsiungJobCount = kaohsiungJobCount + 1;
                            kaohsiungWriter.write("==========高雄職缺==========\n");
                            Elements jobContents = jobInfo.select(".job-list-item-content");
                            for (Element jobContent : jobContents){
                                Elements jobs = jobContent.select(".job-title .job-link-wrapper .job-link");
                                Elements companyName = jobContent.select(".page-name a");
                                kaohsiungWriter.write("職缺：" + jobs.text() + "\n");
                                for (Element job :jobs){
                                    String jobHref = job.absUrl("href");
                                    kaohsiungWriter.write("職缺網址:" + jobHref + "\n");
                                }
                                kaohsiungWriter.write("公司：" + companyName.text() + "\n");
                                for (Element company : companyName){
                                    String companyHref = company.absUrl("href");
                                    kaohsiungWriter.write("公司網址：" + companyHref + "\n");
                                }
                            }
                            Elements jobTags = jobInfo.select(".job-list-item-tags");
                            for(Element tag : jobTags){
                                Elements numberOfHires = tag.select(".number-for-hire-section");
                                Elements salary = tag.select(".job-salary");
                                Elements jobLabels = tag.select(".labels a.label.label-default");
                                String number = numberOfHires.text();
                                if(jobLabels.hasText()){
                                    kaohsiungWriter.write("職缺類別: \n" );
                                }
                                else{
                                    kaohsiungWriter.write("職缺類別 : \n ");
                                }
                                for(Element jobLabel : jobLabels){
                                    kaohsiungWriter.write("-" + jobLabel.text() + "\n");
                                }
                                if(!number.isEmpty()){
                                    kaohsiungWriter.write("招聘人數" + numberOfHires.text() + "\n");
                                }
                                else {
                                    kaohsiungWriter.write("招聘人數 : 沒註明\n ");
                                }
                                if(!salary.isEmpty()){
                                    kaohsiungWriter.write("薪資 :" + salary.text() + "\n");
                                }else{
                                    kaohsiungWriter.write("薪資 : 沒註明\n");
                                }
                            }
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
            System.out.println("台北職缺總數：" + taipeiJobCount);
            System.out.println("台中職缺總數：" + taichungJobCount);
            System.out.println("高雄職缺總數：" + kaohsiungJobCount);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
