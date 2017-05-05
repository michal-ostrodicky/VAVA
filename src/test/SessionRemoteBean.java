package test;

import javax.ejb.Remote;


@Remote
public interface SessionRemoteBean {

    String testMe(String input);

}
