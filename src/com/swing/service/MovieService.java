package com.swing.service;

import java.sql.SQLException;
import java.util.List;

import com.swing.dao.MovieDao;
import com.swing.model.Account;
import com.swing.model.Movie;

public class MovieService {
    private MovieDao MovieDao;

    public MovieService() throws ClassNotFoundException, SQLException {
        MovieDao = new MovieDao();
    }

    public List<Movie> getAllMovies() throws SQLException{
        return MovieDao.getAllMovies();
    }

    public void createMovie(Movie movie) throws SQLException{
        try {
            MovieDao.createMovie(movie);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void updateMovie(Movie movie) throws SQLException{
        MovieDao.updateMovie(movie);
    }


    public Movie getMovieById(int id) throws SQLException {
        return MovieDao.getMovieById(id);
    }

}
