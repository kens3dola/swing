package com.swing.dao;

import com.swing.config.JdbcConnection;
import com.swing.model.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDao {

    private static final String SELECT_ALL_MOVIES = "SELECT * FROM Movie;";
    private static final String INSERT_MOVIE_SQL = "INSERT INTO movie (name, price, image) VALUES (?, ?, ?);";
    private static final String SELECT_MOVIE_BY_ID = "SELECT id, name, price, image FROM movie WHERE id =?;";
    private static final String UPDATE_MOVIE_SQL = "UPDATE movie SET name=?, price=?, image=? WHERE id = ?;";

    public List<Movie> getAllMovies() throws SQLException {
        List<Movie> movies = new ArrayList<>();
        try (Connection connection = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MOVIES);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String image = rs.getString("image");
                int price = rs.getInt("price");
                movies.add(new Movie(id, name, price,image));
            }
        } catch (SQLException | ClassNotFoundException e) {
            printSQLException((SQLException) e);
        }
        return movies;
    }


    public void createMovie(Movie movie) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM movie WHERE name = ?";
        Connection connection = JdbcConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, movie.getName());

        try (PreparedStatement checkMovieExists = connection.prepareStatement(sql)) {
            checkMovieExists.setString(1, movie.getName());
            try (ResultSet rs = checkMovieExists.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Account Existed");
                } else {
                    try (PreparedStatement insert = connection.prepareStatement(
                            INSERT_MOVIE_SQL)) {
                        insert.setString(1, movie.getName());
                        insert.setInt(2, movie.getPrice());
                        insert.setString(3, movie.getImage());
                        System.out.println(insert);
                        insert.executeUpdate();
                    } catch (SQLException e) {
                        printSQLException(e);
                    }
                }
            }
        }
    }

    public Movie getMovieById(int id) {
        Movie movie = null;
        try (Connection connection = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MOVIE_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                int price = rs.getInt("price");
                String image = rs.getString("image");

                movie = new Movie(id, name, price, image);
            }
        } catch (SQLException | ClassNotFoundException e) {
            printSQLException((SQLException) e);
        }
        return movie;
    }

    public boolean updateMovie(Movie movie) {
        boolean rowUpdated = false;
        try (Connection connection = JdbcConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_MOVIE_SQL);) {
            statement.setString(1, movie.getName());
            statement.setInt(2, movie.getPrice());
            statement.setString(2, movie.getImage());
            statement.setInt(9, movie.getId());

            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }


    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}