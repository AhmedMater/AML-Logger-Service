package com.am.libraries.test.logger.abstractCases;

public abstract class AbstractLogRESTAPIAspect {

    protected abstract void initLogFilePath();
    protected abstract Boolean isRequestIDAutomaticallyGenerated();
}
