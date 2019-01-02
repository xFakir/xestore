package com.xcx.xestore.common.util;

import java.util.Deque;

/**
 * @ClassName : IDPool
 * @Description : ID生成池
 * @Author : xcx
 * @Date : 2018-10-20 10:45
 * @Version : 1.0
 */
public class IDPool {
    private Deque<Long> deque;

    public IDPool(Deque<Long> deque){
        this.deque = deque;
    }

    public synchronized void set(){

    }

    public synchronized Long get(){
        return 0L;
    }




}
