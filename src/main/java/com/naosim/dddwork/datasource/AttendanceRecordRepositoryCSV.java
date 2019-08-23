package com.naosim.dddwork.datasource;

import com.naosim.dddwork.domain.AttendanceRecord;
import com.naosim.dddwork.domain.AttendanceRecords;
import com.naosim.dddwork.domain.date.*;
import com.naosim.dddwork.domain.time.Hour;
import com.naosim.dddwork.domain.time.Minute;
import com.naosim.dddwork.domain.time.EntryTime;
import com.naosim.dddwork.domain.time.WorkingDuration;
import com.naosim.dddwork.service.AttendanceRecordRepository;

import java.io.*;
import java.util.Set;
import java.util.TreeMap;

public class AttendanceRecordRepositoryCSV implements AttendanceRecordRepository {

    // data file
    private final String dataFileName = "data.csv";
    // format - 20190403,0900,1900 <- only working date, start time , end time

    static AttendanceRecords attendanceRecords;

    public AttendanceRecordRepositoryCSV()  {
    }

    public AttendanceRecords load()
    {
        return load(null);
    }

    public AttendanceRecords load(YearMonth yearMonth)
    {
        attendanceRecords = new AttendanceRecords();
        // read data file
        File file = new File(dataFileName);

        if(!file.exists())
        {
            return attendanceRecords;
        }

        try{
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null)
            {
                // parse line to attendance record
                String tokens[] = line.split(",");
                if(tokens.length != 3)
                {
                    System.out.println("token size is not 3. wrong format line = " + line);
                    break;
                }
                // check all the tokens are numeric
                for(String s : tokens)
                {
                    if(!isInteger(s))
                    {
                        System.out.println("token " + s + " is not numeric.");
                        break;
                    }
                }
                // check date format
                if(tokens[0].length() != 8)
                {
                    System.out.println("Date format error '" + tokens[0] + "'");
                    break;
                }
                // parse field  and create working date
                Year year = new Year(Integer.parseInt(tokens[0].substring(0,4)));
                Month month = new Month(Integer.parseInt(tokens[0].substring(4,6)));
                Day day = new Day(Integer.parseInt(tokens[0].substring(6,8)));
                WorkingDate workingDate = new WorkingDate(year,month,day);

                // parse start hour and min
                EntryTime startTime = parseRecordedTime(tokens[1]);
                EntryTime endTime = parseRecordedTime(tokens[2]);
                WorkingDuration workingDuration = new WorkingDuration(startTime,endTime);
                AttendanceRecord attendanceRecord = new AttendanceRecord(workingDate,workingDuration);
                if(yearMonth == null) {
                    attendanceRecords.insert(attendanceRecord);
                }
                else if(yearMonth.getValue() == workingDate.getYearMonth() )
                {
                    attendanceRecords.insert(attendanceRecord);
                }

            }
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
            throw new RuntimeException("Can't access data file");
        }
        return attendanceRecords;
    }

    public boolean  save()
    {
        File file = new File(dataFileName);

        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            for(AttendanceRecord attendanceRecord : attendanceRecords.getAttendanceRecords())
            {
                bw.write(attendanceRecord.getWorkingDate().toString() +"," +
                        attendanceRecord.getWorkingDuration().getStartTime().toString() + "," +
                        attendanceRecord.getWorkingDuration().getEndTime().toString() );
                bw.newLine();
            }
            bw.close();
        }
        catch(IOException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public void delete()
    {

        File file = new File(dataFileName);

        if(file.exists())
        {
            file.delete();
        }
    }

    private EntryTime parseRecordedTime(String token)
    {
        if(token.length() != 4)
        {
            System.out.println("Time Format Error = " + token);
            return null;
        }
        Hour hour = new Hour(Integer.parseInt(token.substring(0,2)));
        Minute minute = new Minute(Integer.parseInt(token.substring(2,4)));
        EntryTime entryTime = new EntryTime(hour,minute);
        return entryTime;
    }

    private boolean isInteger(String s)
    {
        try {
            int i = Integer.parseInt(s);
            return true;
        }
        catch(NumberFormatException e)
        {
           return false;
        }
    }




}
