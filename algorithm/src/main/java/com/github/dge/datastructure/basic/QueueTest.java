package com.github.dge.datastructure.basic;

import com.google.common.collect.EvictingQueue;

import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * @author dge
 * @version 1.0
 * @date 2022-09-15 4:13 PM
 */
public class QueueTest {


    /**
     * 方法 boolean add(E e);
     * Inserts the specified element into this queue if it is possible to do so
     * immediately without violating capacity restrictions, returning
     * {@code true} upon success and throwing an {@code IllegalStateException}
     * if no space is currently available.
     *
     * 在不违反容量限制的情况下，可以很快的将规定的元素插入到队列中
     * 成功后返回{@code true}或者如果当前没有可用空间则抛错{@code IllegalStateException}
     *
     * @param e the element to add
     * @return {@code true} (as specified by {@link Collection#add})
     * @throws IllegalStateException if the element cannot be added at this
     *         time due to capacity restrictions
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this queue
     * @throws NullPointerException if the specified element is null and
     *         this queue does not permit null elements
     * @throws IllegalArgumentException if some property of this element
     *         prevents it from being added to this queue
     */


    /**
     * 方法 boolean offer(E e);
     * Inserts the specified element into this queue if it is possible to do
     * so immediately without violating capacity restrictions.
     * When using a capacity-restricted queue, this method is generally
     * preferable to {@link #add}, which can fail to insert an element only
     * by throwing an exception.
     *
     * 在不违反容量限制的情况下，可以很快得将规定的元素将入到队列中
     * 当我们使用的是有限队列时，该方法通常优于{@link #add}方法，{@link #add}方法方法
     * 在插入元素失败时只能抛异常
     *
     * @param e the element to add
     * @return {@code true} if the element was added to this queue, else
     *         {@code false}
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this queue
     * @throws NullPointerException if the specified element is null and
     *         this queue does not permit null elements
     * @throws IllegalArgumentException if some property of this element
     *         prevents it from being added to this queue
     */


    /**
     * 方法 E remove();
     * Retrieves and removes the head of this queue.  This method differs
     * from {@link #poll poll} only in that it throws an exception if this
     * queue is empty.
     *
     * 返回并删除队列的头
     *
     * @return the head of this queue
     * @throws NoSuchElementException if this queue is empty
     */


    /**
     * 方法 E poll();
     * Retrieves and removes the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * 返回并删除队列的头
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */


    /**
     * 方法 E element();
     * Retrieves, but does not remove, the head of this queue.  This method
     * differs from {@link #peek peek} only in that it throws an exception
     * if this queue is empty.
     *
     * 返回队列的头部元素但不删除
     *
     * @return the head of this queue
     * @throws NoSuchElementException if this queue is empty
     */


    /**
     * 方法 E peek();
     * Retrieves, but does not remove, the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * 返回队列的头部元素但不删除
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */

    public static void main(String[] args) {
        // creat capacity-restricted queue
        EvictingQueue<Integer> evictingQueue = EvictingQueue.create(5);
        for (int i = 0; i < 6; i++) {
            System.out.println(evictingQueue.add(i));
        }
        System.out.println(evictingQueue.size());
    }


}
