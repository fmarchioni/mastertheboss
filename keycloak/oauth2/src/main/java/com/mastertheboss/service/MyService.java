
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.annotation.security.RolesAllowed;
 
import java.util.Map;
import java.util.HashMap;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
@Path("/")
public class MyService {
    @Context
    SecurityContext context;

	@GET
	@Path("hello")
	@RolesAllowed({"customer-manager"})
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap hello() {
		KeycloakPrincipal principal = (KeycloakPrincipal) context.getUserPrincipal();
		
		HashMap map = new HashMap();
		map.put("principal",principal.getName());
		map.put("email", principal.getKeycloakSecurityContext().getToken().getEmail());
		
		Map<String, Object> customClaims = (principal.getKeycloakSecurityContext().getToken().getOtherClaims());
		map.putAll(customClaims);
		return map;				
	 
	}

}