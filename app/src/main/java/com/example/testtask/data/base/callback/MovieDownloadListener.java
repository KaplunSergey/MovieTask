package com.example.testtask.data.base.callback;

import com.example.testtask.data.base.exception.RepositoryException;
import com.example.testtask.data.base.model.Movie;

public interface MovieDownloadListener {

    void movieDownloaded(Movie movie);

    void error(RepositoryException ex);

}
