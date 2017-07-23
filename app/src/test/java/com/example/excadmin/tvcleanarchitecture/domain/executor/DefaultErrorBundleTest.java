package com.example.excadmin.tvcleanarchitecture.domain.executor;

import com.example.excadmin.tvcleanarchitecture.domain.exception.DefaultErrorBundle;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/**
 * Created by haradakazumi on 2017/07/23.
 */

@RunWith(MockitoJUnitRunner.class)
public class DefaultErrorBundleTest {
    private DefaultErrorBundle defaultErrorBundle;

    @Mock
    private Exception mockException;

    @Before
    public void setUp() {
        defaultErrorBundle = new DefaultErrorBundle(mockException);
    }

    @Test
    public void testGetException() {
        Exception exception = defaultErrorBundle.getException();
        Assert.assertEquals(exception,mockException);
    }

    @Test
    public void testGetErrorMessageInteraction() {
        defaultErrorBundle.getErrorMessage();

        verify(mockException).getMessage();
    }
}

