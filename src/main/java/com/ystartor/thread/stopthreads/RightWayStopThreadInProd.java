package com.ystartor.thread.stopthreads;

/**
 * @desc 最佳实践：catch了InterruptedException之后的优先选择，在方法签名中抛出异常
 *
 *             那么在run()方法中，就会强制try/catch
 */
public class RightWayStopThreadInProd implements Runnable{

    @Override
    public void run() {
        try {
            while (true){
                System.out.println("-------------------");
                    throwInMethod2();
            }
        } catch (InterruptedException e) {
            System.out.println("保存日志，报警");
            e.printStackTrace();
        }
    }

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
        Thread thread = new Thread(new RightWayStopThreadInProd());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }

}
