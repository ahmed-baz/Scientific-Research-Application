package oracle.adf.research.model.bc.module.common;

import oracle.jbo.ApplicationModule;
import oracle.jbo.domain.BlobDomain;
import oracle.jbo.domain.Date;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sun Mar 11 22:58:56 EET 2018
// ---------------------------------------------------------------------
public interface SecurityAM extends ApplicationModule {
    boolean checkLogin(String email, String password);


    void register(String firstName, String lastName, String email, String password, String code, String date,
                  String gender, BlobDomain image);

    String activeEmail(String email, String code);

    boolean checkEmail(String email);
}
