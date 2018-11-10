package repository;

import datasource.InputCsvDatasource;

public class InputCsvRepositoryImpl implements InputCsvRepository{

    public void InputCsvAdd(
            String[] inputData,
            int workMinutes,
            int overWorkMinutes){
        new InputCsvDatasource(inputData, workMinutes, overWorkMinutes);
    }
}
