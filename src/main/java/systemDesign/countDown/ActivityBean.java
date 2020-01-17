package systemDesign.countDown;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author by xinjian.yao
 * @date 2020/1/17 17:24
 */

@Data
public class ActivityBean {

    private Long activityId;

    private String activityName;

    private LocalDateTime startTime;

    /**
     * 距离开始时间有多久 时间戳
     */
    private Long startTimeDistance;

    private LocalDateTime endTime;

    /**
     * 距离结束时间还有对久 时间戳
     */
    private Long endTimeDistance;


}
