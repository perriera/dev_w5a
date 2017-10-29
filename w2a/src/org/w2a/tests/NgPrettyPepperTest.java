package org.w2a.tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.ini4j.InvalidFileFormatException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w2a.HTMLEdit;
import org.w2a.W2AIniFile;
import org.w2a.ngcomponent.NgComponentExtractor;
import org.w2a.prettypepper.NgPrettyPepper;

public class NgPrettyPepperTest {

	W2AIniFile iniFile;

	String stripped(String before) {
		String after1 = before.replace("\n", "");
		String after2 = after1.replace("\t", "");
		String after3 = after2.replace(" ", "");
		return after3;
	}

	@Before
	public void setup() throws InvalidFileFormatException, FileNotFoundException, IOException {
		this.iniFile = new W2AIniFile("files/formaltests/sample.00.ini");
	}

	@Test
	public void test_00_input() throws Exception {

		String before = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.00.before.html");
		String after = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.00.after.html");

		NgPrettyPepper pp = new NgPrettyPepper(before, iniFile);
		String test = pp.output();

		Assert.assertTrue(!test.equals(before));
		Assert.assertTrue(!before.equals(after));
		Assert.assertTrue(stripped(test).equals(stripped(after)));

	}

	@Test
	public void test_00b_input() throws Exception {

		String before = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.00b.before.html");
		String after = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.00b.after.html");

		NgPrettyPepper pp = new NgPrettyPepper(before, iniFile);
		String test = pp.output();

		Assert.assertTrue(!test.equals(before));
		Assert.assertTrue(!before.equals(after));
		Assert.assertTrue(stripped(test).equals(stripped(after)));

	}

	@Test
	public void test_00c_input() throws Exception {

		String before = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.00c.before.html");
		String after = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.00c.after.html");

		NgPrettyPepper pp = new NgPrettyPepper(before, iniFile);
		String test = pp.output();

		Assert.assertTrue(!test.equals(before));
		Assert.assertTrue(!before.equals(after));
		Assert.assertTrue(stripped(test).equals(stripped(after)));

	}

	@Test
	public void test_00d_input() throws Exception {

		String before = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.00d.before.html");
		String after = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.00d.after.html");

		NgPrettyPepper pp = new NgPrettyPepper(before, iniFile);
		String test = pp.output();

		Assert.assertTrue(!test.equals(before));
		Assert.assertTrue(!before.equals(after));
		Assert.assertTrue(stripped(test).equals(stripped(after)));

	}

	@Test
	public void test_00e_input() throws Exception {

		String before = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.00e.before.html");
		String after = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.00e.after.html");

		NgPrettyPepper pp = new NgPrettyPepper(before, iniFile);
		String test = pp.output();

		Assert.assertTrue(!test.equals(before));
		Assert.assertTrue(!before.equals(after));
		Assert.assertTrue(stripped(test).equals(stripped(after)));

	}

	@Test
	public void test_00f_input() throws Exception {

		String before = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.00f.before.html");
		String after = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.00f.after.html");

		NgPrettyPepper pp = new NgPrettyPepper(before, iniFile);
		String test = pp.output();

		Assert.assertTrue(!test.equals(before));
		Assert.assertTrue(!before.equals(after));
		Assert.assertTrue(stripped(test).equals(stripped(after)));

	}

	@Test
	public void test_00g_input() throws Exception {

		String before = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.00g.before.html");
		String after = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.00g.after.html");

		NgPrettyPepper pp = new NgPrettyPepper(before, iniFile);
		String test = pp.output();

		Assert.assertTrue(!test.equals(before));
		Assert.assertTrue(!before.equals(after));
		Assert.assertTrue(stripped(test).equals(stripped(after)));

	}

	@Test
	public void test_00h_input() throws Exception {

		String before = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.00h.before.html");
		String after = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.00h.after.html");

		NgPrettyPepper pp = new NgPrettyPepper(before, iniFile);
		String test = pp.output();

		Assert.assertTrue(!test.equals(before));
		Assert.assertTrue(!before.equals(after));
		Assert.assertTrue(stripped(test).equals(stripped(after)));

	}

	@Test
	public void test_00i_input() throws Exception {

		String before = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.00i.before.html");
		String after = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.00i.after.html");

		NgPrettyPepper pp = new NgPrettyPepper(before, iniFile);
		String test = pp.output();

		Assert.assertTrue(!test.equals(before));
		Assert.assertTrue(!before.equals(after));
		Assert.assertTrue(stripped(test).equals(stripped(after)));

	}

	@Test
	public void test_00j_input() throws Exception {

		String before = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.00j.before.html");
		String after = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.00j.after.html");

		NgPrettyPepper pp = new NgPrettyPepper(before, iniFile);
		String test = pp.output();

		Assert.assertTrue(!test.equals(before));
		Assert.assertTrue(!before.equals(after));
		String a = stripped(test);
		String b = stripped(after);
		Assert.assertTrue(a.equals(b));

	}

	@Test
	public void test_00k_input() throws Exception {

		String before = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.00k.before.html");
		String after = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.00k.after.html");

		NgPrettyPepper pp = new NgPrettyPepper(before, iniFile);
		String test = pp.output();

		Assert.assertTrue(!test.equals(before));
		Assert.assertTrue(!before.equals(after));
		String a = stripped(test);
		String b = stripped(after);
		Assert.assertTrue(a.equals(b));

	}

	@Test
	public void test_00l_input() throws Exception {

		String before = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.00l.before.html");
		String after = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.00l.after.html");

		NgPrettyPepper pp = new NgPrettyPepper(before, iniFile);
		String test = pp.output();

		Assert.assertTrue(!test.equals(before));
		Assert.assertTrue(!before.equals(after));
		Assert.assertTrue(test.trim().equals(after.trim()));

	}
	
	public void test_00m_input() throws Exception {

		String before = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.00m.before.html");
		String after = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.00m.after.html");

		NgPrettyPepper pp = new NgPrettyPepper(before, iniFile);
		String test = pp.output();

		String a = stripped(test);
		String b = stripped(before);
		String c = stripped(after);

		Assert.assertTrue(test.equals(before));
		Assert.assertTrue(before.equals(after));
		Assert.assertTrue(a.equals(b));
		Assert.assertTrue(b.equals(c));
		Assert.assertTrue(a.equals(c));

	}
	
	@Test
	public void test_06_formatted() throws Exception {

		String before = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.06.before.html");
		String after = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.06.after.html");

		String test = NgPrettyPepper.formatted(before);

		Assert.assertTrue(!test.equals(before));
		Assert.assertTrue(!before.equals(after));
		Assert.assertTrue(test.equals(after));

	}

	@Test
	public void test_06b_formatted() throws Exception {

		String before = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.06b.before.html");
		String after = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.06b.after.html");

		String test = NgPrettyPepper.formatted(before);

		Assert.assertTrue(!test.equals(before));
		Assert.assertTrue(!before.equals(after));
		Assert.assertTrue(test.equals(after));

	}

	@Test
	public void test_06c_formatted() throws Exception {

		String before = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.06c.before.html");
		String after = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.06c.after.html");

		String test = NgPrettyPepper.formatted(before);

		Assert.assertTrue(!test.equals(before));
		Assert.assertTrue(!before.equals(after));
		Assert.assertTrue(stripped(test).equals(stripped(after)));

	}
 
	@Test
	public void test_07_Formatting() throws Exception {

		String before = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.07.before.html");
		String after = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.07.after.html");

		NgPrettyPepper pp = new NgPrettyPepper(before, iniFile);
		String test = pp.output();

		Assert.assertTrue(!test.equals(before));
		Assert.assertTrue(!before.equals(after));
		Assert.assertTrue(stripped(test).equals(stripped(after)));

	}

	@Test
	public void test_08c_Formatting() throws Exception {

		String before = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.08.before.html");
		String after = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.08.after.html");

		NgPrettyPepper pp = new NgPrettyPepper(before, iniFile);
		String test = pp.output();

		Assert.assertTrue(stripped(test).equals(stripped(before)));
		Assert.assertTrue(before.equals(after));
		Assert.assertTrue(stripped(test).equals(stripped(after)));

	}

	@Test
	public void test_10_OutlineSource() throws Exception {

		String before = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/top-menu-div.before.html");
		String after = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/top-menu-div.after.html");

		NgPrettyPepper pp = new NgPrettyPepper(before, iniFile);
		String test = pp.output();

		Assert.assertTrue(!test.equals(before));
		Assert.assertTrue(!before.equals(after));
		String a = stripped(test);
		String b = stripped(after);
		Assert.assertTrue(a.equals(b));

	}

	@Test
	public void test_11_HandleBR() throws Exception {

		String before = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/handle_br.before.html");
		String after = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/handle_br.after.html");

		NgPrettyPepper pp = new NgPrettyPepper(before, iniFile);
		String test = pp.output();

		Assert.assertTrue(!test.equals(before));
		Assert.assertTrue(!before.equals(after));
		String a = stripped(test);
		String b = stripped(after);
		Assert.assertTrue(a.equals(b));

	}
	
	@Test
	public void test_12_HandleBR() throws Exception {

		String before = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/handle_br2.before.html");
		String after = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/handle_br2.after.html");

		NgPrettyPepper pp = new NgPrettyPepper(before, iniFile);
		String test = pp.output();

		Assert.assertTrue(!test.equals(before));
		Assert.assertTrue(!before.equals(after));
		String a = stripped(test);
		String b = stripped(after);
		Assert.assertTrue(a.equals(b));

	}
	
	@Test
	public void test_13_fixBitchIssue() throws Exception {

		String before = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/handle_br3.before.html");
		String after = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/handle_br3.after.html");

		String test = NgComponentExtractor.fixBitchIssue(before, iniFile);

		Assert.assertTrue(!test.equals(before));
		Assert.assertTrue(!before.equals(after));
		String a = stripped(test);
		String b = stripped(after);
		Assert.assertTrue(a.equals(b));

	}
	
	@Test
	public void test_13_fixMorphIssue() throws Exception {

		String before = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/handle_morph.before.html");
		String after = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/handle_morph.after.html");

		NgPrettyPepper pp = new NgPrettyPepper(before, iniFile);
		String test = pp.output();

		Assert.assertTrue(!test.equals(before));
		Assert.assertTrue(!before.equals(after));
		String a = stripped(test);
		String b = stripped(after);
		Assert.assertTrue(a.equals(b));

	}
	
//	@Test
//	public void test_13_OutlineSource() throws Exception {
//
//		String before = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.13.before.html");
//		String after = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.13.after.html");
//		
//		NgPrettyPepper pp = new NgPrettyPepper(before, iniFile);
//		String test = pp.output();
//
//		Assert.assertTrue(!test.equals(before));
//		Assert.assertTrue(!before.equals(after));
//		String a = stripped(test);
//		String b = stripped(after);
//		Assert.assertTrue(a.equals(b));
//
//	}
//
//	@Test
//	public void test_14_OutlineSource() throws Exception {
//
//		String before = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.14.before.html");
//		String after = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.14.after.html");
//		
//		NgPrettyPepper pp = new NgPrettyPepper(before, iniFile);
//		String test = pp.output();
//
//		Assert.assertTrue(!test.equals(before));
//		Assert.assertTrue(!before.equals(after));
//		String a = stripped(test);
//		String b = stripped(after);
//		Assert.assertTrue(a.equals(b));
//
//	}
//
//	@Test
//	public void test_15_OutlineSource() throws Exception {
//
//		String before = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.15.before.html");
//		String after = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.15.after.html");
//		
//		String test = NgPrettyPepper.adjustInputTag(before);
//
//		Assert.assertTrue(!test.equals(before));
//		Assert.assertTrue(!before.equals(after));
//		String a = stripped(test);
//		String b = stripped(after);
//		Assert.assertTrue(a.equals(b));
//
//	}
}
