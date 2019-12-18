package com.task.doctor.util;

import com.task.doctor.entity.Doctor;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.apache.log4j.Logger;

public class FileUtil {
    private static Logger logger = Logger.getLogger(FileUtil.class);

    public static void fileDownload(String urlToDownload, String fileToSaveData) {
        try {
            URL url = new URL(urlToDownload);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedInputStream bufferedInputStream =
                    new BufferedInputStream(connection.getInputStream());
            File file = new File(fileToSaveData);
            FileOutputStream fileWrite = new FileOutputStream(file);
            write(bufferedInputStream, fileWrite);
            logger.info("Done, zip downloaded");
        } catch (IOException ex) {
            logger.error(String.format("Can't download data from url = %s", urlToDownload));
        }
    }

    public static void unZipFile(String zipFile, String unZippedFile) {
        File file = new File(zipFile);
        if (!file.exists() || !file.canRead()) {
            logger.error("Can't read file");
            return;
        }
        try {
            ZipFile zip = new ZipFile(zipFile);
            Enumeration entries = zip.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                if (entry.getName().equals(unZippedFile)) {
                    write(zip.getInputStream(entry),
                            new BufferedOutputStream(new FileOutputStream(
                                    new File(file.getParent(), entry.getName()))));
                }
            }
            zip.close();
            logger.info("Done, file unzipped");
        } catch (IOException e) {
            logger.error(String.format("Error with unzipping file from %S", zipFile));
        }
    }

    public static List<Doctor> readDataFromFile(String fileToRead) {
        BufferedReader reader = null;
        List<Doctor> doctorList = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(fileToRead));
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] splitLine = line.split(",", 22);
                Doctor doctor = new Doctor(Long.valueOf(splitLine[0].replace("\"", "")),
                        splitLine[5].replace("\"", ""),
                        splitLine[6].replace("\"", ""),
                        splitLine[20].replace("\"", ""));
                doctorList.add(doctor);
            }
            return doctorList;
        } catch (IOException e) {
            logger.error("Can't create doctor from db");
        }
        return doctorList;
    }

    private static void write(InputStream inputStream, OutputStream outputStream)
            throws IOException {
        byte[] buffer = new byte[1024];
        int count;
        while ((count = inputStream.read(buffer)) >= 0) {
            outputStream.write(buffer, 0, count);
        }
        outputStream.close();
        inputStream.close();
    }
}
