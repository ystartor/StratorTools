package com.ystartor.thread.stopthreads;

/**
 * @desc 最佳实践：在catch语句中调用Thread.currentThread().interrupt()来恢复设置中断状态，以便于在后续的执行中，依然能够检查到刚才发生了中断
 *                  回到刚才RightWayStopThreadInProd补上中断，让他跳出
 */
public class RightWayStopThreadInProd2 implements Runnable{

    @Override
    public void run() {
            while (true){
                if (Thread.currentThread().isInterrupted()){
                    System.out.println("interrupted process end");
                    break;
                }
                System.out.println("-------------------");
                throwInMethod3();
            }
    }

    private void throwInMethod3() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }


    /**
     *
     * @throws InterruptedException
     */
    private void throwInMethod2() throws InterruptedException {
        Thread.sleep(2000);
    }

    /**
     * 这个是错误的方式一
     */
    private void throwInMethod1() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProd2());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }

}
