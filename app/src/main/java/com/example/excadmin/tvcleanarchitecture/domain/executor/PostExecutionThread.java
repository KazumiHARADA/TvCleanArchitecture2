package com.example.excadmin.tvcleanarchitecture.domain.executor;

/**
 * Created by excadmin on 2017/07/11.
 */

public interface PostExecutionThread {
    //Thread abstraction created to change the execution context from any thread to any other thread.
    void post(Runnable runnable);
}
