package com.swing.frame;



import org.assertj.swing.core.BasicComponentFinder;
import org.assertj.swing.core.ComponentFinder;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.finder.JOptionPaneFinder;
import org.assertj.swing.finder.WindowFinder;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JOptionPaneFixture;
import org.assertj.swing.fixture.JTextComponentFixture;
import org.junit.Test;

import com.swing.frame.admin.AdminHome;
import com.swing.frame.staff.StaffHome;

public class LoginTest extends AbstractUiTest {

	private JTextComponentFixture uname, password;
	private JButtonFixture login;
	ComponentFinder finder;

	@Override
	protected void onSetUp() {
		finder= BasicComponentFinder.finderWithNewAwtHierarchy();
		Login l = GuiActionRunner.execute(() -> new Login());
		this.frame = new FrameFixture(robot(), l);
		this.frame.show();
		this.frame.maximize();

		uname = this.frame.textBox("username");
		password = this.frame.textBox("password");
		login = this.frame.button("login");
	}

	@Test
	public void testLoginAsAdmin() {
		uname.setText("admin");
		password.setText("admin");
		password.requireText("admin");
		login.requireEnabled().click();
		FrameFixture admin = WindowFinder.findFrame(AdminHome.class).using(robot());
		admin.requireVisible();
	}
	
	@Test
	public void testLoginAsStaff() {
		uname.setText("staff");
		password.setText("staff");
		login.requireEnabled().click();
		FrameFixture staff = WindowFinder.findFrame(StaffHome.class).using(robot());
		staff.requireVisible();
	}
	
	@Test
	public void testLoginFail() {
		uname.setText("");
		password.setText("");
		login.requireEnabled().click();
		JOptionPaneFixture option = JOptionPaneFinder.findOptionPane().using(robot());
		option.requireMessage("Wrong username or password.");
	}

}
