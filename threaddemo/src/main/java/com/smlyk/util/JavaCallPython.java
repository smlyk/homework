package com.smlyk.util;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author yekai
 */
@Component
public class JavaCallPython {

    public int calculate(){
        Runtime runtime = Runtime.getRuntime();
        String exe = "python";
        String path = "D:\\python_file\\invoke.py";
        ProcessBuilder pBuilder = new ProcessBuilder(exe, path);
        pBuilder.redirectErrorStream(true);
        BufferedReader reader = null;

        try {
            Process process = pBuilder.start();
            reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "utf-8"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (!StringUtils.isEmpty(line)) {
                    System.out.println(line);
                }
            }
            int result = process.waitFor();
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != reader) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

}
