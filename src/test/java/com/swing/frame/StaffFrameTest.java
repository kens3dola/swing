package com.swing.frame;

import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JButton;

import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.finder.JOptionPaneFinder;
import org.assertj.swing.finder.WindowFinder;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JComboBoxFixture;
import org.assertj.swing.fixture.JLabelFixture;
import org.assertj.swing.fixture.JListFixture;
import org.assertj.swing.fixture.JMenuItemFixture;
import org.assertj.swing.fixture.JTextComponentFixture;
import org.junit.Test;

import com.swing.dao.MovieDao;
import com.swing.frame.staff.ChangeProfile;
import com.swing.frame.staff.StaffHome;
import com.swing.model.Movie;

public class StaffFrameTest extends AbstractUiTest {

	private JMenuItemFixture option;
	private JButtonFixture homeBtn, historyOrderBtn, changeProfileBtn, changePasswordBtn, logoutBtn,
	first, prev, next, last, movieNextBtn;
	private JLabelFixture image, mIdLbl;
	private JTextComponentFixture date;
	private JComboBoxFixture showtimeList;
	
	@Override
	protected void onSetUp() {
		StaffHome window = GuiActionRunner.execute(()->new StaffHome(2));
		this.frame = new FrameFixture(robot(), window);
		this.frame.show();
		this.frame.maximize();
	}

	@Test
	public void testHomeBtn() {
		homeBtn = this.frame.button("Home");
		FrameFixture staff = WindowFinder.findFrame(StaffHome.class).using(robot());
		staff.requireVisible();
	}
	
	@Test
	public void testOptionMenu1() {
		option = this.frame.menuItem("Option");
		option.click();
		historyOrderBtn = this.frame.button("historyOrderBtn");
		historyOrderBtn.requireVisible().click();
		FrameFixture historyOrder = WindowFinder.findFrame(HistoryOrder.class).using(robot());
		historyOrder.requireVisible();
	}
	
	@Test
	public void testOptionMenu2() {
		option = this.frame.menuItem("Option");
		option.click();
		changeProfileBtn = this.frame.button("changeProfileBtn");
		changeProfileBtn.requireVisible().click();
		FrameFixture changeProfile = WindowFinder.findFrame(ChangeProfile.class).using(robot());
		changeProfile.requireVisible();
	}
	@Test
	public void testOptionMenu3() {
		option = this.frame.menuItem("Option");
		option.click();
		changePasswordBtn = this.frame.button("changePasswordBtn");
		changePasswordBtn.requireVisible().click();
		
		JOptionPaneFinder.findOptionPane().using(robot())
		.pressAndReleaseKeys(KeyEvent.VK_T,KeyEvent.VK_E,KeyEvent.VK_S, KeyEvent.VK_T).okButton().click();
		requireOptionPane("Password changed!");
	}
	
	@Test
	public void testOptionMenu4() {
		option = this.frame.menuItem("Option");
		option.click();
		logoutBtn = this.frame.button("logoutBtn");
		logoutBtn.requireVisible().click();
		JOptionPaneFinder.findOptionPane().using(robot()).yesButton();
		FrameFixture login = WindowFinder.findFrame(Login.class).using(robot());
		login.requireVisible();
	}
	
	public void testMovieSlide() {
		MovieDao mDao = new MovieDao();
		List<Movie> movies = mDao.list(mDao.getAllMovies());
		image = this.frame.label("movieLbl");
		first = this.frame.button("first");
		prev = this.frame.button("prev");
		next = this.frame.button("next");
		last = this.frame.button("last");
		mIdLbl = this.frame.label("mIdLbl");
		
		mIdLbl.requireText("id:"+movies.get(0).getId());
		next.click();
		mIdLbl.requireText("id:"+movies.get(1).getId());
		prev.click();
		mIdLbl.requireText("id:"+movies.get(0).getId());
		last.click();
		mIdLbl.requireText("id:"+movies.get(movies.size()-1).getId());
		first.click();
		mIdLbl.requireText("id:"+movies.get(0).getId());
	}
	
	@Test
	public void testMakingOrder() {
		frame.button("dateBtn").click();
		frame.button(new GenericTypeMatcher<JButton>(JButton.class) {
			@Override
			protected boolean isMatching(JButton component) {
				return "13".equals(component.getText());
			}
		}).click();
		showtimeList = this.frame.comboBox("showtimeList");
		showtimeList.selectItem(0);
		movieNextBtn = this.frame.button("movieNextBtn").click();
		
		this.frame.button("S9").click();
		this.frame.button("seatNextBtn").click();
		
		this.frame.spinner("popSpinner").increment(5);
		this.frame.spinner("cocSpinner").increment(5);
		
		this.frame.button("itemNextBtn").click();
		JOptionPaneFinder.findOptionPane().using(robot()).pressAndReleaseKeys(KeyEvent.VK_T,KeyEvent.VK_E,KeyEvent.VK_S, KeyEvent.VK_T).okButton().click();
		JOptionPaneFinder.findOptionPane().using(robot()).requireMessage("Successfully ordered. Do you want to make a new order?").yesButton().click();

		FrameFixture staff = WindowFinder.findFrame(StaffHome.class).using(robot());
		staff.requireVisible();
	}
	
	private void requireOptionPane(String message) {
		JOptionPaneFinder.findOptionPane().using(robot()).requireMessage(message).okButton().click();
	}
}
