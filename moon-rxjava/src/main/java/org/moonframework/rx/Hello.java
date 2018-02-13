package org.moonframework.rx;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * @author quzile
 * @version 1.0
 * @since 2018/2/9
 */
public class Hello {

    public static void a0() {
        Flowable.just("Hello world").subscribe(System.out::println);
    }

    public static void a1() throws InterruptedException {
        Flowable.fromCallable(() -> {
            Thread.sleep(1000); //  imitate expensive computation
            return "Done";
        })
                .subscribeOn(Schedulers.newThread())
                //.observeOn(Schedulers.single())
                .subscribe(System.out::println, Throwable::printStackTrace);

        System.out.println("finished");
        Thread.sleep(2000); // <--- wait for the flow to finish
    }

    public static void a2() throws InterruptedException {

        //subscribeOn() 它指示Observable在一个指定的调度器上创建（只作用于被观察者创建阶段）。只能指定一次，如果指定多次则以第一次为准
        //observeOn() 指定在事件传递（加工变换）和最终被处理（观察者）的发生在哪一个调度器。可指定多次，每次指定完都在下一步生效。

        Observable
                .<Integer>create(emitter -> {
                    System.out.println("start");
                    emitter.onNext(1);
                    emitter.onNext(2);
                    emitter.onNext(3);
                    emitter.onNext(4);
                    emitter.onComplete();
                    emitter.onNext(5);
                    emitter.onNext(6);
                })
                .map(Math::sqrt)
                // Asynchronously subscribes Observers to this ObservableSource on the specified Scheduler.
                // 在指定的线程调度器中, 异步地使观察者们(Observers)订阅被观察者(ObservableSource)对象。
                // 通常, 我们可以将一些CPU密集型(computations)或IO密集型(blocking IO)的任务放到独立的线程中去执行, 不在当前线程中执行这些耗时任务。
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe(System.out::println);
        System.out.println("end");
        Thread.sleep(2000);
    }

    public static void a3() throws Exception {
        Flowable.range(1, 10)
                .observeOn(Schedulers.computation())
                .map(v -> v * v)
                .blockingSubscribe(System.out::println);
        // Thread.sleep(2000L);
    }

    public static void a4() {
        Flowable.range(1, 10)
                .flatMap(v ->
                        Flowable.just(v)
                                .subscribeOn(Schedulers.computation())
                                .map(w -> w * w)
                )
                .blockingSubscribe(System.out::println);
    }

    public static void a5() {
        Flowable.range(1, 10)
                .parallel()
                .runOn(Schedulers.computation())
                .map(v -> v * v)
                .sequential()
                .blockingSubscribe(System.out::println);
    }

    public static void a6() throws Exception {


        Observable.timer(3, TimeUnit.SECONDS)

                .map(aLong -> {
                    System.out.println(aLong);
                    return aLong;
                })
                .subscribe(System.out::println);

        Thread.sleep(10000);
    }

    public static void a7() {
        Observable
                .error(() -> {
                    return new RuntimeException();
                })
                .subscribe(o -> {
                            System.out.println(o);
                        }, e -> {
                            System.out.println(e);
                        },
                        () -> {
                            System.out.println("finished");
                        }
                );
    }

    public static void a8() throws Exception {
        Observable.interval(1, TimeUnit.SECONDS)
                .blockingSubscribe(System.out::println);

        // Thread.sleep(10000);
    }

    public static void a9() throws Exception {
        Observable
                .range(0, 10)
                .blockingSubscribe(System.out::println);

        // Thread.sleep(10000);
    }

    public static void a10() throws Exception {
        Observable.zip(
                Observable.<String>create(emitter -> {
                    System.out.println("start a");
                    Thread.sleep(5000);
                    emitter.onNext("a");
                    emitter.onNext("a1");
                    emitter.onNext("a2");
                }),
                Observable.<String>create(emitter -> {
                    System.out.println("start b");
                    Thread.sleep(1000);
                    emitter.onNext("b");
                    emitter.onNext("b1");
                }),
                (t1, t2) -> {
                    return t1 + t2;
                }).blockingSubscribe(System.out::println);
    }

    public static void a11() {
        Observable<String> a1 = Observable.fromArray("a0", "a1", "a2");
        Observable<String> b1 = Observable.fromArray("b0", "b1", "b2");
        Observable<String> c1 = Observable.fromArray("c0", "c1", "c2", "c3", "c4", "c5");
        Observable
                .merge(a1, b1, c1)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe(System.out::println);

        sleep();
    }

    public static void a12() {
        Flowable.just(1, 2)
                .parallel()
                .runOn(Schedulers.computation())
                .map(v -> v * v)
                .sequential()
                .blockingSubscribe(System.out::println);
    }

    public static void a13() {
        Flowable<String> a1 = Flowable.<String>create(emitter -> {
            System.out.println("start a");
            Thread.sleep(2000);
            emitter.onNext("a0");
            emitter.onNext("a1");
            emitter.onNext("a2");
            emitter.onComplete();
        }, BackpressureStrategy.DROP)
                .subscribeOn(Schedulers.single())
                .map(o -> {
                    Thread.sleep(5000);
                    return o;
                });

        Flowable<String> b1 = Flowable.<String>create(emitter -> {
            System.out.println("start b");
            Thread.sleep(2000);
            emitter.onNext("b0");
            Thread.sleep(10000);
            emitter.onNext("b1");
            emitter.onNext("b2");
            emitter.onComplete();
        }, BackpressureStrategy.DROP)
                .subscribeOn(Schedulers.single())
                .map(s -> {
                    Thread.sleep(5000);
                    return s;
                });

        Flowable.merge(a1, b1)
                .parallel()
                .runOn(Schedulers.newThread())
                .sequential()
                .blockingSubscribe(System.out::println);

//        Flowable.merge(a1, b1)
//                .flatMap(s -> {
//                    char[] chars = s.toCharArray();
//                    List<Character> list = new ArrayList<>();
//                    for (char aChar : chars) {
//                        list.add(aChar);
//                    }
//                    Flowable.fromIterable(list);
//
//                    return Flowable.fromArray(chars)
//                            .subscribeOn(Schedulers.io())
//                            .map(chars1 -> {
//                                StringBuilder sb = new StringBuilder();
//                                for (char c : chars1) {
//                                    sb.append(c).append(",");
//                                }
//                                return sb;
//                            });
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.single())
//                .subscribe(System.out::println);

        //sleep(20);
    }

    public static void a14() {
        final String[] aStrings = {"A1", "A2", "A3", "A4", "A5", "A6"};
        final String[] bStrings = {"B1", "B2", "B3", "B4", "B5", "B6"};

        final Flowable<String> x = Flowable.fromArray(aStrings);
        final Flowable<String> y = Flowable.fromArray(bStrings);

        Flowable
                .merge(x, y)//使用merge操作符将两个被观察者合并
                .parallel(2)
                .runOn(Schedulers.computation())
                .map(s -> {
                    Thread.sleep(random());
                    return s;
                })
                .sequential()
                .subscribe(System.out::println);

        sleep(50);
    }

    public static void a15() {
//        Flowable.range(0, 100)
//                .flatMap(v ->
//                        Flowable
//                                .just(v)
//                                .subscribeOn(Schedulers.computation())
//                                .map(integer -> {
//                                    random();
//                                    return integer ^ 2;
//                                })
//                ).subscribe(System.out::println);

        System.out.println("---");

        Flowable.range(0, 100)
                .parallel(3)
                .runOn(Schedulers.computation())
                .sequential()
                .subscribe(System.out::println);

        sleep();
    }

    public static void a16() {
        Observable<String> o1 = Observable.<String>create(emitter -> {
            System.out.println("start a");

            emitter.onNext("a1");
            emitter.onNext("a2");
            emitter.onComplete();
        })
                .subscribeOn(Schedulers.io())
                .map(o -> {
                    Thread.sleep(random(2000));
                    Thread.sleep(random(2000));
                    return o;
                });
        Observable<Integer> o2 = Observable.<Integer>create(emitter -> {
            System.out.println("start b");
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onComplete();
        })
                .subscribeOn(Schedulers.io())
                .map(o -> {
                    Thread.sleep(random(2000));
                    Thread.sleep(random(2000));
                    return o;
                });

        Observable.zip(o1, o2, (a, b) -> {
            return new StringBuilder().append(a).append(b);
        }).subscribe(System.out::println);

        sleep(20000);
    }

    // -----------------------------------------------------------------------------------------------------------------

    private static void sleep(long timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void sleep() {
        sleep(5);
    }

    private static long random(int v) {
        return (long) (Math.random() * v);
    }

    private static long random() {
        return random(1000);
    }

    public static void main(String[] args) throws Exception {
        a13();
    }

}
