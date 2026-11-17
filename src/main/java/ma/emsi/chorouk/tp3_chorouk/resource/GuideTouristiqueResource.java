package ma.emsi.chorouk.tp3_chorouk.resource;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.inject.Inject;
import ma.emsi.chorouk.tp3_chorouk.llm.LlmClient;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.DefaultValue;

@Path("/guide")

public class GuideTouristiqueResource {
    @Inject
    private LlmClient llmClient;
    @GET
    @Path("lieu/{ville_ou_pays}")
    @Produces(MediaType.APPLICATION_JSON)
    public String endroitsDeVisite(
            @PathParam("ville_ou_pays") String villeOuPays,
            @QueryParam("nb") @DefaultValue("2") int nb) {

        return llmClient.envoyerQuestion(
                "Donne exactement " + nb
                        + " endroits à visiter pour : " + villeOuPays
        );
    }
}
