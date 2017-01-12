package de.claudioaltamura.os.jsonassert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.FieldComparisonFailure;
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
        assertTrue(result.failed());
        assertTrue(result.isFailureOnField());
        assertEquals(result.getFieldFailures().size(), 1);
        FieldComparisonFailure fieldComparisonFailure = result.getFieldFailures().get(0);
        assertEquals("name", fieldComparisonFailure.getField());
        assertEquals("The book",fieldComparisonFailure.getExpected());
	}

	@Test
	public void missingField() throws JSONException {
        JSONCompareResult result = JSONCompare.compareJSON("{\"name\":\"The book\",\"pages\":100}", "{\"name\":\"The book\"}", JSONCompareMode.LENIENT);
        assertTrue(result.failed());
        assertTrue(result.isMissingOnField());
        assertEquals(result.getFieldMissing().size(), 1);
        FieldComparisonFailure fieldComparisonFailure = result.getFieldMissing().get(0);
        assertEquals("pages",fieldComparisonFailure.getExpected());
	}

	@Test
	public void unexpectedNull() throws JSONException {
        JSONCompareResult result = JSONCompare.compareJSON("{\"name\":\"The book\",\"pages\":100}", "{\"name\":\"The book\",\"pages\":null}", JSONCompareMode.LENIENT);
        assertTrue(result.failed());
        assertTrue(result.isFailureOnField());
        assertEquals(result.getFieldFailures().size(), 1);
        FieldComparisonFailure fieldComparisonFailure = result.getFieldFailures().get(0);
        assertEquals("pages",fieldComparisonFailure.getField());
	}

}
