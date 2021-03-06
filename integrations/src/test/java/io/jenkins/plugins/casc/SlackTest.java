package io.jenkins.plugins.casc;

import hudson.ExtensionList;
import io.jenkins.plugins.casc.misc.ConfiguredWithReadme;
import io.jenkins.plugins.casc.misc.JenkinsConfiguredWithReadmeRule;
import jenkins.plugins.slack.SlackNotifier;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;
import org.junit.rules.RuleChain;

import static junit.framework.TestCase.assertNotNull;

/**
 * @author v1v (Victor Martinez)
 */
public class SlackTest {

    @Rule
    public RuleChain chain = RuleChain.outerRule(new EnvironmentVariables()
        .set("SLACK_TOKEN", "ADMIN123"))
        .around(new JenkinsConfiguredWithReadmeRule());

    @Test
    @ConfiguredWithReadme("slack/README.md")
    public void configure_slack() throws Exception {
        // Already validated within the plugin itself, so let's run some simple validations
        // https://github.com/jenkinsci/slack-plugin/pull/582
        SlackNotifier.DescriptorImpl slackNotifier = ExtensionList.lookupSingleton(SlackNotifier.DescriptorImpl.class);
        assertNotNull(slackNotifier);
    }
}
