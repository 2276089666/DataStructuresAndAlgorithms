package greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author ws
 * @Date 2021/4/14 14:04
 * @Version 1.0
 */
public class MeetingSchedule {
    /**
     * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
     * 给你每一个项目开始的时间和结束的时间
     * 你来安排宣讲的日程，要求会议室进行的宣讲的场次最多。
     * 返回最多的宣讲场次。
     */

    public static class Program{
        int start;
        int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int getMaxMeeting(Program[] programs){
        Arrays.sort(programs, new MyEndComparator());
        int timeLine = 0;
        int result = 0;
        for (int i = 0; i < programs.length; i++) {
            if (timeLine <= programs[i].start) {
                result++;
                timeLine = programs[i].end;
            }
        }
        return result;
    }

    public static class MyEndComparator  implements Comparator<Program>{

        @Override
        public int compare(Program o1, Program o2) {
            return o1.end-o2.end;
        }
    }

    public static void main(String[] args) {
        Program[] programs=new Program[]{new Program(1,3),new Program(2,6),new Program(4,8),new Program(3,4)};
        int maxMeeting = getMaxMeeting(programs);
        System.out.println(maxMeeting);
    }

}
