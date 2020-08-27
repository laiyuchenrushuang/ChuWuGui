package com.seatrend.utilsdk.thread.manyThread;

/**
 * Created by ly on 2020/8/20 15:44
 * <p>
 * Thread 状态：
 * 1. 初始(NEW)：新创建了一个线程对象，但还没有调用start()方法。
 * 2. 运行(RUNNABLE)：Java线程中将就绪（ready）和运行中（running）两种状态笼统的称为“运行”。
 * 线程对象创建后，其他线程(比如main线程）调用了该对象的start()方法。该状态的线程位于可运行线程池中，等待被线程调度选中，获取CPU的使用权，此时处于就绪状态（ready）。就绪状态的线程在获得CPU时间片后变为运行中状态（running）。
 * 3.阻塞(BLOCKED)：表示线程阻塞于锁。
 * 4.等待(WAITING)：进入该状态的线程需要等待其他线程做出一些特定动作（通知或中断）。
 * 5.超时等待(TIMED_WAITING)：该状态不同于WAITING，它可以在指定的时间后自行返回。
 * <p>
 * 6. 终止(TERMINATED)：表示该线程已经执行完毕
 */
public class LyThread extends Thread {

    /**
     * start 线程启动
     * join ,A，B线程  A中执行B的join()  ,  执行顺序B->A      正常情况A->B
     * wait ,A线程中执行wait 是等待，暂停意思 和notify并用  wait后不再执行线程里的代码  如果事件interupt，如果有没执行完的代码会继续执行然后停止
     * stop 线程停止 不安全
     * notify notifyAll  是唤醒等待线程
     * isActive 线程是否活着
     * isDamon 守护线程
     * 关闭线程 在成员变量设置一个flag  线程开关
     */

}
