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

	private static final String SELECT_ALL_MOVIES = "select * from movie";
	private static final String SELECT_MOVIE_BY_ID = "SELECT id, name, price, image FROM movie WHERE id =?;";
	private static final String UPDATE_MOVIE_SQL = "UPDATE movie SET name=?, price=?, image=? WHERE id = ?;";

	public ResultSet getAllMovies() {
		Connection connection = null;
		try {
			connection = JdbcConnection.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(SELECT_ALL_MOVIES);
			ResultSet rs = preparedStatement.executeQuery();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int add(String name, String image, int price)  {
		String sql = "insert into movie (name, image, price) value(?,?,?)";
		Connection connection;
		try {
			connection = JdbcConnection.getConnection();
			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, name);
			st.setString(2, image);
			st.setInt(3, price);
			return st.executeUpdate();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return 0;
	}

	public Movie getMovieById(int id) {
		Movie movie = null;
		try (Connection connection = JdbcConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MOVIE_BY_ID);) {
			preparedStatement.setInt(1, id);
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

	public int updateMovie(Movie movie) {
		try (Connection connection = JdbcConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_MOVIE_SQL);) {
			statement.setString(1, movie.getName());
			statement.setInt(2, movie.getPrice());
			statement.setString(3, movie.getImage());
			statement.setInt(4, movie.getId());
			return statement.executeUpdate();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return -1;
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
					t = t.getCause();
				}
			}
		}
	}

	public List<Movie> list(ResultSet rs) {
		List<Movie> movies = new ArrayList<>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String image = rs.getString("image");
				int price = rs.getInt("price");
				movies.add(new Movie(id, name, price, image));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movies;
	}

	public int delete(int id) {
		try {
			Connection con = JdbcConnection.getConnection();
			String sql = "delete from movie where id="+id;
			PreparedStatement statement = con.prepareStatement(sql);
			return statement.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
<<<<<<< Updated upstream
=======

	public boolean getOrderMovie(HistoryOrderModel ho, int id) {
		String sql = "select * from movie where id=" + id;
		try {
			Connection con = null;
			try {
				con = JdbcConnection.getConnection();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				ho.setMovie(rs.getString(2));
			}
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
>>>>>>> Stashed changes
}