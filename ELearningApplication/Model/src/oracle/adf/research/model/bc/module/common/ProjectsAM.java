package oracle.adf.research.model.bc.module.common;

import oracle.jbo.ApplicationModule;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Apr 20 17:20:21 EET 2018
// ---------------------------------------------------------------------
public interface ProjectsAM extends ApplicationModule {
    boolean accessProject(String code);

    void filterPrivateProjects();

    void filterPublicProjects();

    String getCode();
}

