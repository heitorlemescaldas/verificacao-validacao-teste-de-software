package br.edu.ifsp.testing.suites;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SelectPackages({"br.edu.ifsp.testing.class04"}) // Tags do not include test classes itself.
@SuiteDisplayName("All unit tests")
@IncludeTags({"UnitTest"}) //it only filters classes selected by @SelectPackages
public class UnitTests { }

