package com.itbuzzpress.chapter8.test;


import static org.jboss.shrinkwrap.api.ShrinkWrap.create;

import java.io.File;
import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.importer.ZipImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

@RunWith(Arquillian.class)

public class CustomerIT {

	@ArquillianResource
	private URL webUrl;
	private WebClient webClient;

	@Deployment(testable = false)
	public static WebArchive createDeployment() {
		return create(ZipImporter.class, "jsf-demo.war")
				.importFrom(new File("target/jsf-demo.war"))
				.as(WebArchive.class);
	}

	@Before
	public void setUp() {
		webClient = new WebClient();
	}

	@After
	public void tearDown() {
		webClient.close();
	}

	@Test
	public void test() {	 
		try {

			// Navigate to the Home page 
			HtmlPage page = webClient.getPage(webUrl + "index.xhtml");

			// Get input text 'name' and set a value 
			HtmlInput name = (HtmlInput) page.getElementById("formpanel:name");
			name.setValueAttribute("john");

			// Get input text 'surname' and set a value 
			HtmlInput surname = (HtmlInput) page.getElementById("formpanel:surname");
			surname.setValueAttribute("smith");

			// Get input text 'email' and set a value 
			HtmlInput email = (HtmlInput) page.getElementById("formpanel:email");
			email.setValueAttribute("john@gmail.com");

			HtmlSubmitInput submit = (HtmlSubmitInput) page.getElementById("formpanel:submit");
			System.out.println(submit);

			// Get a new HtmlPage after submitting
			HtmlPage page2 = submit.click();

			// Iterate on the Table
			final HtmlTable table = page2.getHtmlElementById("datatableform:mytable");
			for (final HtmlTableRow row : table.getRows()) {
				System.out.println("Found row");
				for (final HtmlTableCell cell : row.getCells()) {
					System.out.println("   Found cell: " + cell.asNormalizedText());
				}
			}


		} catch (Exception e) {
			System.out.println(e.getClass());
			e.getMessage();
		}  
	}

	private void log(String string) {
		System.out.println(string);

	}

}
