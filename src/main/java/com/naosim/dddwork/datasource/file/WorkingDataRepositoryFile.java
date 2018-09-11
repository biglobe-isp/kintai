//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.naosim.dddwork.datasource.file;

import com.naosim.dddwork.domain.duty.WorkingData;
import com.naosim.dddwork.domain.duty.WorkingDataRepository;
import com.naosim.dddwork.domain.duty.WorkingSummaryData;
import com.naosim.dddwork.domain.word.WorkDate;
import com.naosim.dddwork.domain.word.WorkingMinute;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

@Repository
public class WorkingDataRepositoryFile implements WorkingDataRepository {
    public WorkingDataRepositoryFile() {
    }

    public void write(WorkingData workingData) throws Exception {
        ArrayList<WorkingData> workingDataList = this.read();
        ArrayList<WorkingData> workingDataListNew = new ArrayList();
        if (!workingDataList.isEmpty()) {
            boolean isInputDataSetted = false;

            for (int n = 0; n < workingDataList.size(); ++n) {
                WorkingData workingDataOriginal = (WorkingData) workingDataList.get(n);
                if (workingData.getWorkDate().convertLocalDate().compareTo(workingDataOriginal.getWorkDate().convertLocalDate()) == 0) {
                    if (!isInputDataSetted) {
                        workingDataListNew.add(workingData);
                        isInputDataSetted = true;
                    }
                } else if (workingData.getWorkDate().convertLocalDate().compareTo(workingDataOriginal.getWorkDate().convertLocalDate()) < 0) {
                    if (!isInputDataSetted) {
                        workingDataListNew.add(workingData);
                        isInputDataSetted = true;
                    }

                    workingDataListNew.add(workingDataOriginal);
                } else if (workingData.getWorkDate().convertLocalDate().compareTo(workingDataOriginal.getWorkDate().convertLocalDate()) > 0) {
                    workingDataListNew.add(workingDataOriginal);
                }
            }

            if (!isInputDataSetted) {
                workingDataListNew.add(workingData);
            }
        } else {
            workingDataListNew.add(workingData);
        }

        File file = new File("src/test/resources/csv/data.csv");

        try {
            FileWriter fileWriter = new FileWriter(file, false);
            Throwable var21 = null;

            try {
                if (!workingDataListNew.isEmpty()) {
                    for (int n = 0; n < workingDataListNew.size(); ++n) {
                        fileWriter.write("\"" + ((WorkingData) workingDataListNew.get(n)).getWorkDate().getValue().toString() + "\",");
                        fileWriter.write("\"" + ((WorkingData) workingDataListNew.get(n)).getWorkTimeFrom().getValue().toString() + "\",");
                        fileWriter.write("\"" + ((WorkingData) workingDataListNew.get(n)).getWorkTimeTo().getValue().toString() + "\",");
                        fileWriter.write("\"" + ((WorkingData) workingDataListNew.get(n)).getWorkingMinute().getValue().toString() + "\",");
                        fileWriter.write("\"" + ((WorkingData) workingDataListNew.get(n)).getOverWorkingMinute().getValue().toString() + "\"\n");
                    }
                }
            } catch (Throwable var16) {
                var21 = var16;
                throw var16;
            } finally {
                if (fileWriter != null) {
                    if (var21 != null) {
                        try {
                            fileWriter.close();
                        } catch (Throwable var15) {
                            var21.addSuppressed(var15);
                        }
                    } else {
                        fileWriter.close();
                    }
                }

            }

        } catch (IOException var18) {
            throw new IOException(var18.getMessage());
        }
    }

    public ArrayList<WorkingData> read() throws Exception {
        File file = new File("src/test/resources/csv/data.csv");
        ArrayList<WorkingData> workingDataList = new ArrayList();
        if (file.length() > 0L) {
            try {
                FileReader fr = new FileReader(file);
                Throwable var4 = null;

                try {
                    BufferedReader br = new BufferedReader(fr);
                    Throwable var6 = null;

                    try {
                        String line;
                        try {
                            while ((line = br.readLine()) != null) {
                                workingDataList.add(new WorkingData(line));
                            }
                        } catch (Throwable var31) {
                            var6 = var31;
                            throw var31;
                        }
                    } finally {
                        if (br != null) {
                            if (var6 != null) {
                                try {
                                    br.close();
                                } catch (Throwable var30) {
                                    var6.addSuppressed(var30);
                                }
                            } else {
                                br.close();
                            }
                        }

                    }
                } catch (Throwable var33) {
                    var4 = var33;
                    throw var33;
                } finally {
                    if (fr != null) {
                        if (var4 != null) {
                            try {
                                fr.close();
                            } catch (Throwable var29) {
                                var4.addSuppressed(var29);
                            }
                        } else {
                            fr.close();
                        }
                    }

                }
            } catch (Exception var35) {
                throw new Exception(var35.getMessage());
            }
        }

        return workingDataList;
    }

    public WorkingSummaryData summary(ArrayList<WorkingData> workingDataList) {
        BigDecimal workMinuteSummary = BigDecimal.ZERO.setScale(2, 4);
        BigDecimal overWorkMinuteSummary = BigDecimal.ZERO.setScale(2, 4);
        int startYear = 0;
        int startMonth = 0;
        int startDay = 0;
        int endYear = 0;
        int endMonth = 0;
        int endDay = 0;
        //int CLOSING_DAY = true;
        if (!workingDataList.isEmpty()) {
            for (int n = 0; n < workingDataList.size(); ++n) {
                if (n == 0) {
                    startYear = ((WorkingData) workingDataList.get(n)).getWorkDate().getValue().getYear();
                    startMonth = ((WorkingData) workingDataList.get(n)).getWorkDate().getValue().getMonth().getValue();
                    if (startMonth == 12) {
                        endYear = startYear + 1;
                        endMonth = 1;
                    } else {
                        endYear = startYear;
                        endMonth = startMonth + 1;
                    }

                    startDay = 16;
                    endDay = 15;
                }

                LocalDate start = LocalDate.of(startYear, startMonth, startDay);
                LocalDate end = LocalDate.of(endYear, endMonth, endDay);
                if (((WorkingData) workingDataList.get(n)).getWorkDate().getValue().compareTo(start) >= 0) {
                    if (((WorkingData) workingDataList.get(n)).getWorkDate().getValue().compareTo(end) == 0) {
                        workMinuteSummary = workMinuteSummary.add(((WorkingData) workingDataList.get(n)).getWorkingMinute().getValue());
                        overWorkMinuteSummary = overWorkMinuteSummary.add(((WorkingData) workingDataList.get(n)).getOverWorkingMinute().getValue());
                        break;
                    }

                    workMinuteSummary = workMinuteSummary.add(((WorkingData) workingDataList.get(n)).getWorkingMinute().getValue());
                    overWorkMinuteSummary = overWorkMinuteSummary.add(((WorkingData) workingDataList.get(n)).getOverWorkingMinute().getValue());
                }
            }
        }

        return new WorkingSummaryData(new WorkDate(LocalDate.of(startYear, startMonth, startDay).toString().replaceAll("-", "")), new WorkDate(LocalDate.of(endYear, endMonth, endDay).toString().replaceAll("-", "")), new WorkingMinute(workMinuteSummary), new WorkingMinute(overWorkMinuteSummary));
    }
}
