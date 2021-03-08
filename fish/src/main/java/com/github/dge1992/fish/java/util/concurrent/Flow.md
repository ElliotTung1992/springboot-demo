```
/*
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

/*
 *
 *
 *
 *
 *
 * Written by Doug Lea with assistance from members of JCP JSR-166
 * Expert Group and released to the public domain, as explained at
 * http://creativecommons.org/publicdomain/zero/1.0/
 */

package java.util.concurrent;

/**
 * Interrelated interfaces and static methods for establishing
 * flow-controlled components in which {@link Publisher Publishers}
 * produce items consumed by one or more {@link Subscriber
 * Subscribers}, each managed by a {@link Subscription
 * Subscription}.
 * 
 * 用于建立流控制组件的相关接口和静态方法，其中Publisher生产一个或多个Subscriber所使用的项
 * 每个项由Subscription管理
 *
 * <p>These interfaces correspond to the <a
 * href="http://www.reactive-streams.org/"> reactive-streams</a>
 * specification.  They apply in both concurrent and distributed
 * asynchronous settings: All (seven) methods are defined in {@code
 * void} "one-way" message style. Communication relies on a simple form
 * of flow control (method {@link Subscription#request}) that can be
 * used to avoid resource management problems that may otherwise occur
 * in "push" based systems.
 *
 * <p><b>Examples.</b> A {@link Publisher} usually defines its own
 * {@link Subscription} implementation; constructing one in method
 * {@code subscribe} and issuing it to the calling {@link
 * Subscriber}. It publishes items to the subscriber asynchronously,
 * normally using an {@link Executor}.  For example, here is a very
 * simple publisher that only issues (when requested) a single {@code
 * TRUE} item to a single subscriber.  Because the subscriber receives
 * only a single item, this class does not use buffering and ordering
 * control required in most implementations (for example {@link
 * SubmissionPublisher}).
 *
 * <pre> {@code
 * class OneShotPublisher implements Publisher<Boolean> {
 *   private final ExecutorService executor = ForkJoinPool.commonPool(); // daemon-based
 *   private boolean subscribed; // true after first subscribe
 *   public synchronized void subscribe(Subscriber<? super Boolean> subscriber) {
 *     if (subscribed)
 *       subscriber.onError(new IllegalStateException()); // only one allowed
 *     else {
 *       subscribed = true;
 *       subscriber.onSubscribe(new OneShotSubscription(subscriber, executor));
 *     }
 *   }
 *   static class OneShotSubscription implements Subscription {
 *     private final Subscriber<? super Boolean> subscriber;
 *     private final ExecutorService executor;
 *     private Future<?> future; // to allow cancellation
 *     private boolean completed;
 *     OneShotSubscription(Subscriber<? super Boolean> subscriber,
 *                         ExecutorService executor) {
 *       this.subscriber = subscriber;
 *       this.executor = executor;
 *     }
 *     public synchronized void request(long n) {
 *       if (!completed) {
 *         completed = true;
 *         if (n <= 0) {
 *           IllegalArgumentException ex = new IllegalArgumentException();
 *           executor.execute(() -> subscriber.onError(ex));
 *         } else {
 *           future = executor.submit(() -> {
 *             subscriber.onNext(Boolean.TRUE);
 *             subscriber.onComplete();
 *           });
 *         }
 *       }
 *     }
 *     public synchronized void cancel() {
 *       completed = true;
 *       if (future != null) future.cancel(false);
 *     }
 *   }
 * }}</pre>
 *
 * <p>A {@link Subscriber} arranges that items be requested and
 * processed.  Items (invocations of {@link Subscriber#onNext}) are
 * not issued unless requested, but multiple items may be requested.
 * Many Subscriber implementations can arrange this in the style of
 * the following example, where a buffer size of 1 single-steps, and
 * larger sizes usually allow for more efficient overlapped processing
 * with less communication; for example with a value of 64, this keeps
 * total outstanding requests between 32 and 64.
 * Because Subscriber method invocations for a given {@link
 * Subscription} are strictly ordered, there is no need for these
 * methods to use locks or volatiles unless a Subscriber maintains
 * multiple Subscriptions (in which case it is better to instead
 * define multiple Subscribers, each with its own Subscription).
 *
 * <pre> {@code
 * class SampleSubscriber<T> implements Subscriber<T> {
 *   final Consumer<? super T> consumer;
 *   Subscription subscription;
 *   final long bufferSize;
 *   long count;
 *   SampleSubscriber(long bufferSize, Consumer<? super T> consumer) {
 *     this.bufferSize = bufferSize;
 *     this.consumer = consumer;
 *   }
 *   public void onSubscribe(Subscription subscription) {
 *     long initialRequestSize = bufferSize;
 *     count = bufferSize - bufferSize / 2; // re-request when half consumed
 *     (this.subscription = subscription).request(initialRequestSize);
 *   }
 *   public void onNext(T item) {
 *     if (--count <= 0)
 *       subscription.request(count = bufferSize - bufferSize / 2);
 *     consumer.accept(item);
 *   }
 *   public void onError(Throwable ex) { ex.printStackTrace(); }
 *   public void onComplete() {}
 * }}</pre>
 *
 * <p>The default value of {@link #defaultBufferSize} may provide a
 * useful starting point for choosing request sizes and capacities in
 * Flow components based on expected rates, resources, and usages.
 * Or, when flow control is never needed, a subscriber may initially
 * request an effectively unbounded number of items, as in:
 *
 * <pre> {@code
 * class UnboundedSubscriber<T> implements Subscriber<T> {
 *   public void onSubscribe(Subscription subscription) {
 *     subscription.request(Long.MAX_VALUE); // effectively unbounded
 *   }
 *   public void onNext(T item) { use(item); }
 *   public void onError(Throwable ex) { ex.printStackTrace(); }
 *   public void onComplete() {}
 *   void use(T item) { ... }
 * }}</pre>
 *
 * @author Doug Lea
 * @since 9
 */
public final class Flow {

    private Flow() {} // uninstantiable

    /**
     * A producer of items (and related control messages) received by
     * Subscribers.  Each current {@link Subscriber} receives the same
     * items (via method {@code onNext}) in the same order, unless
     * drops or errors are encountered. If a Publisher encounters an
     * error that does not allow items to be issued to a Subscriber,
     * that Subscriber receives {@code onError}, and then receives no
     * further messages.  Otherwise, when it is known that no further
     * messages will be issued to it, a subscriber receives {@code
     * onComplete}.  Publishers ensure that Subscriber method
     * invocations for each subscription are strictly ordered in <a
     * href="package-summary.html#MemoryVisibility"><i>happens-before</i></a>
     * order.
     *
     * <p>Publishers may vary in policy about whether drops (failures
     * to issue an item because of resource limitations) are treated
     * as unrecoverable errors.  Publishers may also vary about
     * whether Subscribers receive items that were produced or
     * available before they subscribed.
     *
     * @param <T> the published item type
     */
    @FunctionalInterface
    public static interface Publisher<T> {
        /**
         * Adds the given Subscriber if possible.  If already
         * subscribed, or the attempt to subscribe fails due to policy
         * violations or errors, the Subscriber's {@code onError}
         * method is invoked with an {@link IllegalStateException}.
         * Otherwise, the Subscriber's {@code onSubscribe} method is
         * invoked with a new {@link Subscription}.  Subscribers may
         * enable receiving items by invoking the {@code request}
         * method of this Subscription, and may unsubscribe by
         * invoking its {@code cancel} method.
         * 如果可行添加指定的订阅者
         * 如果已经订阅或者由于策略违反或错误，订阅失败
         * 订阅服务器的{@code onError}方法通过{@link IllegalStateException}调用
         * 否则，将使用新的{@link Subscription}调用订阅者的{@code onSubscribe}方法
         * 订阅者可以通过调用此订阅的{@code request}方法来启用接收项目
         * 也可以通过调用其{@code cancel}方法来取消订阅。
         *
         * @param subscriber the subscriber
         * @throws NullPointerException if subscriber is null
         */
        public void subscribe(Subscriber<? super T> subscriber);
    }

    /**
     * A receiver of messages.  The methods in this interface are
     * invoked in strict sequential order for each {@link
     * Subscription}.
     * 消息接收者,此接口中的方法是按严格顺序为每个{@link订阅}调用的。
     *
     * @param <T> the subscribed item type
     */
    public static interface Subscriber<T> {
        /**
         * Method invoked prior to invoking any other Subscriber
         * methods for the given Subscription. If this method throws
         * an exception, resulting behavior is not guaranteed, but may
         * cause the Subscription not to be established or to be cancelled.
         * 方法在指定订阅者调用任何其他订阅方法之前调用
         * 如果这个方法抛出异常，无法保证结果行为
         * 但是有可能导致认购不成立或者取消
         *
         * <p>Typically, implementations of this method invoke {@code
         * subscription.request} to enable receiving items.
         * 通常，此方法的调用{@code subscription.request}启用接收项目。
         *
         * @param subscription a new subscription
         */
        public void onSubscribe(Subscription subscription);

        /**
         * Method invoked with a Subscription's next item.  If this
         * method throws an exception, resulting behavior is not
         * guaranteed, but may cause the Subscription to be cancelled.
         * 方法调用订阅操作的下一个项，如果这个方法抛出了一个异常
         * 由此产生的行为不能得到保证
         * 但是可能导致订阅被取消
         *
         * @param item the item
         */
        public void onNext(T item);

        /**
         * Method invoked upon an unrecoverable error encountered by a
         * Publisher or Subscription, after which no other Subscriber
         * methods are invoked by the Subscription.  If this method
         * itself throws an exception, resulting behavior is
         * undefined.
         * 方法在发布服务器或订阅服务器遇到不可恢复的错误时调用
         * 在此错误之后，订阅不会调用其他订阅服务器方法
         * 如果此方法本身抛出异常，则结果行为是未定义的。
         *
         * @param throwable the exception
         */
        public void onError(Throwable throwable);

        /**
         * Method invoked when it is known that no additional
         * Subscriber method invocations will occur for a Subscription
         * that is not already terminated by error, after which no
         * other Subscriber methods are invoked by the Subscription.
         * If this method throws an exception, resulting behavior is
         * undefined.
         * 对尚未因错误终止的订阅时调用此方法，不会发生其他订阅服务器方法调用
         * 在此之后，订阅不会调用其他订阅者方法
         * 如果此方法引发异常，则结果行为未定义
         */
        public void onComplete();
    }

    /**
     * Message control linking a {@link Publisher} and {@link
     * Subscriber}.  Subscribers receive items only when requested,
     * and may cancel at any time. The methods in this interface are
     * intended to be invoked only by their Subscribers; usages in
     * other contexts have undefined effects.
     * 链接Publisher和Subscriber的消息控件，
     * Subscribers只有当请求时才接收项目并且可以在任何时候取消
     * 此接口的方法只能让其订阅者调用
     * 在其他语境中的用法具有未定义的效果。
     * 
     */
    public static interface Subscription {
        /**
         * Adds the given number {@code n} of items to the current
         * unfulfilled demand for this subscription.  If {@code n} is
         * less than or equal to zero, the Subscriber will receive an
         * {@code onError} signal with an {@link
         * IllegalArgumentException} argument.  Otherwise, the
         * Subscriber will receive up to {@code n} additional {@code
         * onNext} invocations (or fewer if terminated).
         * 将给定数量的{@code n}项添加到此订阅操作的当前未满足需求中。
         * 如果{@code n}小于或等于零
         * Subscriber将接收到IllegalArgumentException的信号
         * 否则，订阅服务器将最多接收{@code n}个额外的{@code onNext}调用（如果终止，则接收更少的调用）。
         *
         * @param n the increment of demand; a value of {@code
         * Long.MAX_VALUE} may be considered as effectively unbounded
         * n是需求的增量
         * 
         */
        public void request(long n);

        /**
         * Causes the Subscriber to (eventually) stop receiving
         * messages.  Implementation is best-effort -- additional
         * messages may be received after invoking this method.
         * A cancelled subscription need not ever receive an
         * {@code onComplete} or {@code onError} signal.
         * 使订阅者最终停止接收消息
         * 实现是最大的努力——调用此方法后可能会收到其他消息。
         * 取消订阅不需要接收{@code onComplete}或{@code onError}信号。
         */
        public void cancel();
    }

    /**
     * A component that acts as both a Subscriber and Publisher.
     * 同时作为订阅者和发布者的组件
     *
     * @param <T> the subscribed item type
     * @param <R> the published item type
     */
    public static interface Processor<T,R> extends Subscriber<T>, Publisher<R> {
    }

    static final int DEFAULT_BUFFER_SIZE = 256;

    /**
     * Returns a default value for Publisher or Subscriber buffering,
     * that may be used in the absence of other constraints.
     *
     * @implNote
     * The current value returned is 256.
     *
     * @return the buffer size value
     */
    public static int defaultBufferSize() {
        return DEFAULT_BUFFER_SIZE;
    }

}
```