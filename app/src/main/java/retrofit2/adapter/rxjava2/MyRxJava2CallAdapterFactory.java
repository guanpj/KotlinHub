/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package retrofit2.adapter.rxjava2;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.annotation.Nullable;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import retrofit2.CallAdapter;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A {@linkplain CallAdapter.Factory call adapter} which uses RxJava 2 for creating observables.
 * <p>
 * Adding this class to {@link Retrofit} allows you to return an {@link Observable},
 * {@link Flowable}, {@link Single}, {@link Completable} or {@link Maybe} from service methods.
 * <pre><code>
 * interface MyService {
 *   &#64;GET("user/me")
 *   Observable&lt;User&gt; getUser()
 * }
 * </code></pre>
 * There are three configurations supported for the {@code Observable}, {@code Flowable},
 * {@code Single}, {@link Completable} and {@code Maybe} type parameter:
 * <ul>
 * <li>Direct body (e.g., {@code Observable<User>}) calls {@code onNext} with the deserialized body
 * for 2XX responses and calls {@code onError} with {@link HttpException} for non-2XX responses and
 * {@link IOException} for network errors.</li>
 * <li>Response wrapped body (e.g., {@code Observable<Response<User>>}) calls {@code onNext}
 * with a {@link Response} object for all HTTP responses and calls {@code onError} with
 * {@link IOException} for network errors</li>
 * <li>Result wrapped body (e.g., {@code Observable<Result<User>>}) calls {@code onNext} with a
 * {@link Result} object for all HTTP responses and errors.</li>
 * </ul>
 */
public final class MyRxJava2CallAdapterFactory extends CallAdapter.Factory {
    /**
     * Returns an INSTANCE which creates synchronous observables that do not operate on any scheduler
     * by default.
     */
    public static MyRxJava2CallAdapterFactory create() {
        return new MyRxJava2CallAdapterFactory(null, null, false);
    }

    /**
     * Returns an INSTANCE which creates asynchronous observables. Applying
     * {@link Observable#subscribeOn} has no effect on stream types created by this factory.
     */
    public static MyRxJava2CallAdapterFactory createAsync() {
        return new MyRxJava2CallAdapterFactory(null, null, true);
    }

    /**
     * Returns an INSTANCE which creates synchronous observables that
     * {@linkplain Observable#subscribeOn(Scheduler) subscribe on} {@code scheduler} by default.
     */
    @SuppressWarnings("ConstantConditions") // Guarding public API nullability.
    public static MyRxJava2CallAdapterFactory createWithScheduler(Scheduler scheduler) {
        if (scheduler == null) throw new NullPointerException("scheduler == null");
        return new MyRxJava2CallAdapterFactory(scheduler, null, false);
    }

    /**
     * Returns an INSTANCE which creates synchronous observables that
     * {@linkplain Observable#subscribeOn(Scheduler) subscribe on} {@code scheduler} by default.
     */
    @SuppressWarnings("ConstantConditions") // Guarding public API nullability.
    public static MyRxJava2CallAdapterFactory createWithScheduler(Scheduler subscribeOnScheduler, Scheduler observerOnScheduler) {
        if (subscribeOnScheduler == null)
            throw new NullPointerException("subscribeOnScheduler == null");
        if (observerOnScheduler == null)
            throw new NullPointerException("observerOnScheduler == null");
        return new MyRxJava2CallAdapterFactory(subscribeOnScheduler, observerOnScheduler, false);
    }

    private final @Nullable
    Scheduler subscribeOnScheduler;
    private final @Nullable
    Scheduler observerOnScheduler;
    private final boolean isAsync;

    private MyRxJava2CallAdapterFactory(@Nullable Scheduler subscribeOnScheduler, @Nullable Scheduler observerOnScheduler, boolean isAsync) {
        this.subscribeOnScheduler = subscribeOnScheduler;
        this.observerOnScheduler = observerOnScheduler;
        this.isAsync = isAsync;
    }

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        Class<?> rawType = getRawType(returnType);

        if (rawType == Completable.class) {
            // Completable is not parameterized (which is what the rest of this method deals with) so it
            // can only be created with a single configuration.
            return new MyRxJava2CallAdapter(Void.class, subscribeOnScheduler, observerOnScheduler, isAsync, false, true, false, false,
                    false, true);
        }

        boolean isFlowable = rawType == Flowable.class;
        boolean isSingle = rawType == Single.class;
        boolean isMaybe = rawType == Maybe.class;
        if (rawType != Observable.class && !isFlowable && !isSingle && !isMaybe) {
            return null;
        }

        boolean isResult = false;
        boolean isBody = false;
        Type responseType;
        if (!(returnType instanceof ParameterizedType)) {
            String name = isFlowable ? "Flowable"
                    : isSingle ? "Single"
                    : isMaybe ? "Maybe" : "Observable";
            throw new IllegalStateException(name + " return type must be parameterized"
                    + " as " + name + "<Foo> or " + name + "<? extends Foo>");
        }

        Type observableType = getParameterUpperBound(0, (ParameterizedType) returnType);
        Class<?> rawObservableType = getRawType(observableType);
        if (rawObservableType == Response.class) {
            if (!(observableType instanceof ParameterizedType)) {
                throw new IllegalStateException("Response must be parameterized"
                        + " as Response<Foo> or Response<? extends Foo>");
            }
            responseType = getParameterUpperBound(0, (ParameterizedType) observableType);
        } else if (rawObservableType == Result.class) {
            if (!(observableType instanceof ParameterizedType)) {
                throw new IllegalStateException("Result must be parameterized"
                        + " as Result<Foo> or Result<? extends Foo>");
            }
            responseType = getParameterUpperBound(0, (ParameterizedType) observableType);
            isResult = true;
        } else {
            responseType = observableType;
            isBody = true;
        }

        return new MyRxJava2CallAdapter(responseType, subscribeOnScheduler, observerOnScheduler, isAsync, isResult, isBody, isFlowable,
                isSingle, isMaybe, false);
    }
}
