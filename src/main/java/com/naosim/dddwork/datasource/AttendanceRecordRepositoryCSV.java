package com.naosim.dddwork.datasource;

import com.naosim.dddwork.domain.date.Day;
import com.naosim.dddwork.domain.date.Month;
import com.naosim.dddwork.domain.date.WorkingDate;
import com.naosim.dddwork.domain.date.Year;
import com.naosim.dddwork.domain.time.Hour;
import com.naosim.dddwork.domain.time.Minute;
import com.naosim.dddwork.domain.time.RecordedTime;
import com.naosim.dddwork.domain.time.WorkingDuration;

import java.io.*;
import java.util.Set;
import java.util.TreeMap;

public class AttendanceRecordRepositoryCSV {

    // data file
    private final String dataFileName = "data.csv";
    // format - 20190403,0900,1900 <- only working date, start time , end time

    TreeMap<WorkingDate, WorkingDuration> attendanceRecords;

    public AttendanceRecordRepositoryCSV() throws IOException {

        attendanceRecords = new TreeMap<WorkingDate,WorkingDuration>();
    }


    public TreeMap<WorkingDate,WorkingDuration> load()
    {
        attendanceRecords = new TreeMap<WorkingDate,WorkingDuration>();
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
                RecordedTime startTime = parseRecordedTime(tokens[1]);
                RecordedTime endTime = parseRecordedTime(tokens[2]);
                WorkingDuration workingDuration = new WorkingDuration(startTime,endTime);
                attendanceRecords.put(workingDate,workingDuration);
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
            Set<WorkingDate>  keys = attendanceRecords.keySet();
            for(WorkingDate workingDate : keys)
            {
                WorkingDuration workingDuration = attendanceRecords.get(workingDate);
                bw.write(workingDate.toString() +"," +
                            workingDuration.getStartTime().toString() + "," +
                            workingDuration.getEndTime().toString() );



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

    private RecordedTime parseRecordedTime(String token)
    {
        if(token.length() != 4)
        {
            System.out.println("Time Format Error = " + token);
            return null;
        }
        Hour hour = new Hour(Integer.parseInt(token.substring(0,2)));
        Minute minute = new Minute(Integer.parseInt(token.substring(2,4)));
        RecordedTime recordedTime = new RecordedTime(hour,minute);
        return recordedTime;
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
