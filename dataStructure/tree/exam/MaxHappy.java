package tree.exam;

import java.util.List;

/**
 * @Author ws
 * @Date 2021/4/11 16:13
 * @Version 1.0
 */
public class MaxHappy {
    public static class Employee{
        int happy;
        List<Employee> subordinates;

        public Employee(int happy, List<Employee> subordinates) {
            this.happy = happy;
            this.subordinates = subordinates;
        }
    }

    /**
     * 派对的最大快乐值
     *  公司的每个员工都符合 Employee 类的描述。整个公司的人员结构可以看作是一棵标准的、 没有环的多叉树。
     *  树的头节点是公司唯一的老板。除老板之外的每个员工都有唯一的直接上级。
     *  叶节点是没有任何下属的基层员工(subordinates列表为空)，除基层员工外，每个员工都有一个或多个直接下级。
     *
     *  派对的最大快乐值
     * 这个公司现在要办party，你可以决定哪些员工来，哪些员工不来，规则：
     * 1.如果某个员工来了，那么这个员工的所有直接下级都不能来
     * 2.派对的整体快乐值是所有到场员工快乐值的累加
     * 3.你的目标是让派对的整体快乐值尽量大
     * 给定一棵多叉树的头节点boss，请返回派对的最大快乐值。
     */

    // 1.分析与X节点有关或者无关的答案: 1.来(本节点的快乐值+直接子树不来的最大快乐值)  2.不来(0+直接子树来与不来二者中的最大快乐值)
    // 2.子树都返回当前节点树来的快乐值,和不来的快乐值

    public static class Info{
        int arrive;
        int nonArrival;

        public Info(int arrive, int nonArrival) {
            this.arrive = arrive;
            this.nonArrival = nonArrival;
        }
    }

    public static int getMaxHappy(Employee boss){
        if (boss==null){
            return 0;
        }
        Info info=getMax(boss);
        return Math.max(info.arrive,info.nonArrival);
    }

    private static Info getMax(Employee employee) {
        // 1.对叶子节点
        if (employee.subordinates.isEmpty()){
            return new Info(employee.happy,0);
        }
        // 2.为自定义返回结果赋初值
        int arrive=employee.happy;
        int nonArrival=0;
        // 3.递归子树拿返回结果,处理,封装好我们需要的结果返回
        for (Employee subordinate : employee.subordinates) {
            Info info = getMax(subordinate);
            arrive+=info.nonArrival;
            nonArrival+=Math.max(info.arrive,info.nonArrival);
        }
        return new Info(arrive,nonArrival);
    }

}
