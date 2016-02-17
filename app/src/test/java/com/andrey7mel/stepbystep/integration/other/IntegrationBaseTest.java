package com.andrey7mel.stepbystep.integration.other;

import com.andrey7mel.stepbystep.BuildConfig;
import com.andrey7mel.stepbystep.integration.other.di.IntegrationTestComponent;
import com.andrey7mel.stepbystep.other.App;
import com.andrey7mel.stepbystep.other.TestUtils;
import com.squareup.okhttp.mockwebserver.Dispatcher;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import javax.inject.Inject;

@RunWith(RobolectricGradleTestRunner.class)
@Config(application = IntegrationTestApp.class,
        constants = BuildConfig.class,
        sdk = 21)
@Ignore
public class IntegrationBaseTest {

    public IntegrationTestComponent component;
    public TestUtils testUtils;

    @Inject
    protected MockWebServer mockWebServer;

    @Before
    public void setUp() throws Exception {
        component = (IntegrationTestComponent) App.getComponent();
        testUtils = new TestUtils();
        component.inject(this);
    }

    @After
    public void tearDown() throws Exception {
        mockWebServer.shutdown();
    }

    protected void setErrorAnswerWebServer() {
        mockWebServer.setDispatcher(new Dispatcher() {
            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
                return new MockResponse().setResponseCode(500);
            }
        });
    }
}
