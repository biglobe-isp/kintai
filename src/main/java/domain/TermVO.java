package domain;

import service.BreakTimeVO;
import service.EndTimeVO;
import service.StartTimeVO;

public class TermVO {
    private final int workTime;


    public TermVO(StartTimeVO startTimeVO, EndTimeVO endTimeVO, BreakTimeVO breakTimeVO) {
        //(終業ー始業)から、休憩時間を引く
        this.workTime = endTimeVO.getEndTime() - startTimeVO.getStartTime() - breakTimeVO.getBreakTime();
    }


    //就業時間
    public int getWorkTime() {
        //TODO 休憩時間の計算ロジックはどこで記述するか→BrealTimeVO
        return workTime;
    }

    //残業時間
    public int getOverWorTime() {
        return Math.max(workTime - 8 * 60, 0);
    }


}
