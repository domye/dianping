package com.hmdp.utils;

public interface ILock {

    boolean tryLock(long timeoutSec);

    // 释放锁
    void unlock();
}
