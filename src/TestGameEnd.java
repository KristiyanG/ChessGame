import static org.junit.Assert.*;

import org.junit.Test;

import boardLogic.Knight;

public class TestGameEnd {

	@Test
	public void testWantedPosition() {
		assertTrue(Knight.wantedPosition(2, 2));
	}

}
