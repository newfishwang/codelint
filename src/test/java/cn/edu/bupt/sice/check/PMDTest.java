package cn.edu.bupt.sice.check;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PMDTest {

    public static void main(String[] args) {
        String line;
        StringBuffer sb = new StringBuffer();
        String checkLineP = "cmd$/c$\"E:/open/pmd-bin-6.2.0/bin/pmd.bat -dir D:/projectsForGraduation/zip/" +"39843803-d236-4c1c-a54a-73eb02da84eb" + ".zip"+
                " -f summaryhtml -r E:/open/results/" + "hahaPMD" + ".html -rulesets E:/open/pmd-bin-6.2.0/myrule.xml -encoding UTF-8\"";
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(checkLineP.split("\\$"));
            Process process = processBuilder.start();
            BufferedReader bufferedReader = new BufferedReader
                    (new InputStreamReader(process.getInputStream()));
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line + "\n");
            }
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}