package com.example.excadmin.tvcleanarchitecture.domain.interactor;

import com.example.excadmin.tvcleanarchitecture.domain.executor.PostExecutionThread;
import com.example.excadmin.tvcleanarchitecture.domain.executor.ThreadExecutor;
import com.example.excadmin.tvcleanarchitecture.domain.repository.VideoRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * Created by haradakazumi on 2017/07/23.
 */
@RunWith(MockitoJUnitRunner.class)
public class GetVideoListTest {

    private GetVideoList getVideoList;

    @Mock private ThreadExecutor mockThreadExecutor;
    @Mock private PostExecutionThread mockPostExecutionThread;
    @Mock private VideoRepository mockVideoRepository;


    @Before
    public void setup() {
        getVideoList = new GetVideoList(mockVideoRepository,mockThreadExecutor,mockPostExecutionThread);
    }

    @Test
    public void testGetVideoListUseCaseObservableHappyCase() {
        getVideoList.buildUseCaseObservable(null);

        verify(mockVideoRepository).videos();
        verifyNoMoreInteractions(mockVideoRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }

}
