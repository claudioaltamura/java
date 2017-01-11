package de.claudioaltamura.os.jsonassert;

import static de.claudioaltamura.os.jsonassert.JsonAssertTestUtils.failsWithMessage;
import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsEqual;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;

public class BookTest {

	private Book book;

	@Before
	public void setUp() {
		book = new Book();
		book.setName("The book");
		book.setPages(100);
	}

	//Order is not important

	@Test
	public void nonStrictMode() throws JSONException {
		String result = BookUtils.toJson(book);

		boolean strict = false;
		JSONAssert.assertEquals("{\"name\":\"The book\"}", result, strict);
	}

	@Test
	public void strictMode() throws JSONException {
		String result = BookUtils.toJson(book);

		boolean strict = true;
		JSONAssert.assertEquals("{\"name\":\"The book\",\"pages\":100}", result, strict);
	}

	@Test
	public void mismatchValue() throws JSONException {
        JSONCompareResult result = JSONCompare.compareJSON("{\"name\":\"The book\"}", "{\"name\":\"Test\"}", JSONCompareMode.LENIENT);
        assertThat(result, failsWithMessage(IsEqual.equalTo("id\nExpected: The book\n     got: Test\n")));
	}

}
