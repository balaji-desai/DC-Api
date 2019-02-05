package dc.jwtutil;

import java.security.Key;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
public interface KeyGenerator {

    Key generateKey(String secretKey);
}
