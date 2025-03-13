package com.github.dge1992.fish.concurrent.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 自定义锁
 */
public class ElliotLock extends AbstractQueuedSynchronizer {

    /**
     * 独占锁 - 尝试获取资源
     * @param arg the acquire argument. This value is always the one
     *        passed to an acquire method, or is the value saved on entry
     *        to a condition wait.  The value is otherwise uninterpreted
     *        and can represent anything you like.
     * @return boolean
     */
    @Override
    protected boolean tryAcquire(int arg) {
        return super.tryAcquire(arg);
    }

    /**
     * 独占锁 - 尝试释放资源
     * @param arg the release argument. This value is always the one
     *        passed to a release method, or the current state value upon
     *        entry to a condition wait.  The value is otherwise
     *        uninterpreted and can represent anything you like.
     * @return boolean
     */
    @Override
    protected boolean tryRelease(int arg) {
        return super.tryRelease(arg);
    }

    /**
     * 共享锁 - 尝试获取资源
     * @param arg the acquire argument. This value is always the one
     *        passed to an acquire method, or is the value saved on entry
     *        to a condition wait.  The value is otherwise uninterpreted
     *        and can represent anything you like.
     * @return int
     */
    @Override
    protected int tryAcquireShared(int arg) {
        return super.tryAcquireShared(arg);
    }

    /**
     * 共享锁 - 尝试释放资源
     * @param arg the release argument. This value is always the one
     *        passed to a release method, or the current state value upon
     *        entry to a condition wait.  The value is otherwise
     *        uninterpreted and can represent anything you like.
     * @return boolean
     */
    @Override
    protected boolean tryReleaseShared(int arg) {
        return super.tryReleaseShared(arg);
    }

    /**
     * 是否在独占资源
     * @return boolean
     */
    @Override
    protected boolean isHeldExclusively() {
        return super.isHeldExclusively();
    }
}
