package com.swing.frame;

import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;

import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.data.TableCell;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.finder.JOptionPaneFinder;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JListFixture;
import org.assertj.swing.fixture.JRadioButtonFixture;
import org.assertj.swing.fixture.JTabbedPaneFixture;
import org.assertj.swing.fixture.JTableFixture;
import org.assertj.swing.fixture.JTextComponentFixture;
import org.junit.Test;

import com.swing.dao.AccountDao;
import com.swing.dao.MovieDao;
import com.swing.dao.ScheduleDao;
import com.swing.dao.ShowtimeDao;
import com.swing.frame.admin.AdminHome;
import com.swing.model.Account;
import com.swing.model.Movie;

public class AdminFrameTest extends AbstractUiTest {

	private JListFixture movieList, scheduleList;
	private JTableFixture showtimeTable, movieTable, accountTable;
	private JTabbedPaneFixture tabbed;
	private JButtonFixture showtimeBtn, movieBtn, movieUpdate, movieDelete, accountBtn, accountUpdate, accountDelete;
	private JTextComponentFixture movieName, movieImage, moviePrice, username, password;
	private JRadioButtonFixture adminRadioBtn, staffRadioBtn;

	@Override
	protected void onSetUp() {
		AdminHome window = GuiActionRunner.execute(() -> new AdminHome(1));
		frame = new FrameFixture(robot(), window);
		frame.show();
		frame.maximize();

		tabbed = frame.tabbedPane("tabbed");

		movieList = frame.list("movieList");
		scheduleList = frame.list("scheduleList");
		showtimeTable = frame.table("showtimeTable");

	}

	@Test
	public void testStartUp() {
		MovieDao mDao = new MovieDao();
		AccountDao aDao = new AccountDao();
		ShowtimeDao sDao = new ShowtimeDao();
		ScheduleDao scheDao = new ScheduleDao();

		movieList.requireItemCount(mDao.list(mDao.getAllMovies()).size());
		scheduleList.requireItemCount(scheDao.list(scheDao.getAllSchedule()).size());
		showtimeTable.requireRowCount(sDao.list(sDao.getAllShowtime()).size());

		tabbed.selectTab("Manage movies");
		movieTable = frame.table("movieTable");
		movieTable.requireRowCount(mDao.list(mDao.getAllMovies()).size());

		tabbed.selectTab("Manage users");
		accountTable = frame.table("accountTable");
		accountTable.requireRowCount(aDao.getAllAccounts().size());

	}

	@Test
	public void testShowtimeTab() {
		showtimeBtn = frame.button("showtimeBtn");

		showtimeBtn.click();
		requireOptionPane("Please choose a movie, a schedule and a date");

		movieList.clickItem(0);
		showtimeBtn.click();
		requireOptionPane("Please choose a movie, a schedule and a date");

		scheduleList.clickItem(0);
		showtimeBtn.click();
		requireOptionPane("Please choose a movie, a schedule and a date");

		JButtonFixture dateBtn = frame.button("dateBtn");
		dateBtn.click();
		JButtonFixture today = frame.button(new GenericTypeMatcher<JButton>(JButton.class) {
			protected boolean isMatching(JButton component) {
				return String.valueOf(Calendar.getInstance().get(Calendar.DATE)).equals(component.getText());
			};
		});
		today.click();
		showtimeBtn.click();
		requireOptionPane("A new show is added");

		showtimeTable.selectRows(0);
		JButtonFixture showDelete = frame.button("showDelete");
		showDelete.click();
		requireOptionPane("One show is deleted");
	}

	private void requireOptionPane(String message) {
		JOptionPaneFinder.findOptionPane().using(robot()).requireMessage(message).okButton().click();
	}

	@Test
	public void testMovieTab() {
		MovieDao mDao = new MovieDao();
		movieName = this.frame.textBox("movieName");
		movieImage = this.frame.textBox("movieImage");
		moviePrice = this.frame.textBox("moviePrice");
		movieBtn = this.frame.button("movieBtn");
		movieUpdate = this.frame.button("movieUpdate");
		movieDelete = this.frame.button("movieDelete");

		movieName.setText("test");
		movieImage.setText("titanic.jpg");
		moviePrice.setText("5");
		movieBtn.requireText("Add").requireEnabled().click();
		requireOptionPane("Added");

		movieTable = this.frame.table("movieTable");
		List<Movie> movies = mDao.list(mDao.getAllMovies());
		movieTable.requireRowCount(movies.size());

		movieTable.selectRows(movies.size() - 1);
		movieUpdate.click();
		movieName.requireText("test");
		movieImage.requireText("titanic.jpg");
		moviePrice.requireText("5");
		movieBtn.requireText("Update");
		movieName.setText("Test");
		movieImage.setText("Titanic.jpg");
		moviePrice.setText("4");
		movieBtn.click();
		requireOptionPane("Updated");

		movieTable.selectRows(movies.size() - 1);
		movieDelete.click();
		requireOptionPane("Deleted");

		movieTable.selectRows(movies.size() - 2);
	}

	@Test
	public void testAccountTab() {
		AccountDao aDao = new AccountDao();
		accountBtn = this.frame.button("accountBtn");
		accountUpdate = this.frame.button("accountUpdate");
		accountDelete = this.frame.button("accountDelete");
		username = this.frame.textBox("username");
		password = this.frame.textBox("password");
		adminRadioBtn = this.frame.radioButton("admin");
		staffRadioBtn = this.frame.radioButton("staff");

		username.setText("test");
		password.setText("test");
		adminRadioBtn.check();
		accountBtn.click();
		requireOptionPane("Added");
		List<Account> accounts = aDao.getAllAccounts();
		accountTable.requireRowCount(accounts.size() - 1);

		accountTable.selectRows(accounts.size() - 1);
		accountUpdate.click();
		username.requireText("test");
		password.requireText("test");
		adminRadioBtn.requireSelected();
		staffRadioBtn.requireNotSelected();

		username.setText("Test");
		password.setText("Test");
		adminRadioBtn.uncheck();
		staffRadioBtn.check();
		accountBtn.requireText("Update").click();
		requireOptionPane("Updated");

		accountTable.requireCellValue(TableCell.row(accounts.size() - 1).column(2), "Test");
		accountTable.selectRows(accounts.size() - 1);
		accountDelete.click();
		requireOptionPane("Deleted");
		accountTable.requireRowCount(accounts.size() - 2);
	}
}
