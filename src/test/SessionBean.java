package test;

import javax.ejb.Stateless;


@Stateless(name = "SessionEJB")
public class SessionBean implements SessionRemoteBean {


    @Override
    public String testMe(String input) {
        return "tested " + input;
    }
}
