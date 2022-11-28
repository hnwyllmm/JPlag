package de.jplag.reporting.reportobject;

import java.io.File;
import java.nio.file.Path;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import de.jplag.JPlagResult;
import de.jplag.TestBase;
import de.jplag.exceptions.ExitException;
import de.jplag.reporting.reportobject.model.Version;

class ReportObjectFactoryTest extends TestBase {
    private static final String BASECODE = "basecode";
    private static final String BASECODE_BASE = "basecode-base";
    private static final String OUTPUT = "output";
    private static final String SUBMISSIONS = "submissions";

    @Test
    void testVersionLoading() {
        Assertions.assertNotNull(ReportObjectFactory.REPORT_VIEWER_VERSION);
        Assertions.assertNotEquals(Version.DEVELOPMENT, ReportObjectFactory.REPORT_VIEWER_VERSION);
    }

    @Test
    void testCreateAndSaveReportWithBasecode() throws ExitException {
        JPlagResult result = runJPlag(BASECODE, it -> it.withBaseCodeSubmissionDirectory(new File(BASE_PATH, BASECODE_BASE)));
        String path = Path.of(BASE_PATH, OUTPUT, SUBMISSIONS).toString();
        ReportObjectFactory reportObjectFactory = new ReportObjectFactory();
        reportObjectFactory.createAndSaveReport(result, path);
        Assertions.assertNotNull(result);
    }

}