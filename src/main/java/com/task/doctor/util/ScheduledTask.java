package com.task.doctor.util;

import com.task.doctor.entity.Doctor;
import com.task.doctor.service.DoctorService;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.TimerTask;
import org.springframework.beans.factory.annotation.Autowired;

public class ScheduledTask extends TimerTask {
    private static final String PATH = "D:\\";
    private static final String ZIP_NAME = "doctor.zip";
    private final DoctorService doctorService;

    @Autowired
    public ScheduledTask(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @Override
    public void run() {
        LocalDate localDate = LocalDate.now();
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        Long start = dayOfWeek.getValue() + 13L;
        Long end = start - 6L;
        String zipName = String.format(
                "http://download.cms.gov/nppes/NPPES_Data_Dissemination_%s_%s_Weekly.zip",
                localDate.minusDays(start).format(DateTimeFormatter.ofPattern("MMdduu")),
                localDate.minusDays(end).format(DateTimeFormatter.ofPattern("MMdduu")));
        String fileName = String.format("npidata_pfile_%s-%s.csv",
                localDate.minusDays(start).format(DateTimeFormatter.ofPattern("uuuuMMdd")),
                localDate.minusDays(end).format(DateTimeFormatter.ofPattern("uuuuMMdd")));
        FileUtil.fileDownload(zipName, PATH + ZIP_NAME);
        FileUtil.unZipFile(PATH + ZIP_NAME, fileName);
        List<Doctor> doctorList = FileUtil.readDataFromFile(PATH + fileName);
        doctorService.addAll(doctorList);
    }
}
