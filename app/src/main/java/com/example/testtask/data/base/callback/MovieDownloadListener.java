package com.example.testtask.data.base.callback;

import com.example.testtask.data.base.Movie;
import com.example.testtask.data.base.exception.RepositoryException;

public interface MovieDownloadListener {

    void movieDownloaded(Movie movie);

    void error(RepositoryException ex);

}
