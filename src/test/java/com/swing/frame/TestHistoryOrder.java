package com.swing.frame;

import java.util.List;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JTableFixture;
import org.junit.Test;

import com.swing.dao.OrderDao;
import com.swing.model.Order;

public class TestHistoryOrder extends AbstractUiTest {

	private JTableFixture table;
	@Override
	protected void onSetUp() {
		HistoryOrder l = GuiActionRunner.execute(() -> new HistoryOrder());
		this.frame = new FrameFixture(robot(), l);
		this.frame.show();
		this.frame.maximize();
		table = this.frame.table("HistoryOrder");
	}
	@Test
	public void testHistoryOrder() {
		OrderDao oDao = new OrderDao();
		List<Order> order = oDao.list(oDao.getAllOrder());
		table.requireRowCount(order.size());
	}
}
