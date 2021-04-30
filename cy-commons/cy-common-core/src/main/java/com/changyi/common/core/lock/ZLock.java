package com.changyi.common.core.lock;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 锁对象抽象
 *
 * @author YuRuizhi
 * @date 2020/7/28
 */
@AllArgsConstructor
public class ZLock implements AutoCloseable {

    @Getter
    private final Object lock;

    private final DistributedLock locker;

    @Override
    public void close() throws Exception {
        locker.unlock(lock);
    }
}
