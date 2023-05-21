package cn.kdan.compdfkit.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author tangxiangan
 * file tool class
 */
public class FileUtils {


    /**
     * multipartFileToFile
     * @param file file
     * @return File
     */
    public static File multipartFileToFile(MultipartFile file) {
        File toFile = null;
        try {
        if (file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(file.getOriginalFilename());
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return toFile;
    }

    //get flow file
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
