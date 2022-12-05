package city.smartb.registry.program.bdd

import io.cucumber.junit.platform.engine.Constants
import org.junit.platform.suite.api.ConfigurationParameter
import org.junit.platform.suite.api.IncludeEngines
import org.junit.platform.suite.api.SelectClasspathResource
import org.junit.platform.suite.api.Suite

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("city.smartb.registry.program.bdd")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "city.smartb.registry.program.bdd")
class RunCucumberTest
