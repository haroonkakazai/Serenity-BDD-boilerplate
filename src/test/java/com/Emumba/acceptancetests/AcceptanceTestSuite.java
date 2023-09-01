package com.Emumba.acceptancetests;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/src/test/resources/features/fill_contact_us_form.feature", glue = "com.Emumba" )
public class AcceptanceTestSuite {
}
