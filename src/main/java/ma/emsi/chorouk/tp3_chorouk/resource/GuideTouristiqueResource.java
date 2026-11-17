
package ma.emsi.chorouk.tp3_chorouk.resource;

import dev.langchain4j.model.output.TokenUsage;
import dev.langchain4j.service.Result;
import jakarta.inject.Inject;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import ma.emsi.chorouk.tp3_chorouk.llm.InformationsTouristiques;
import ma.emsi.chorouk.tp3_chorouk.llm.LlmClient;
import ma.emsi.chorouk.tp3_chorouk.llm.ReponseTouristique;

@Path("/guide")
public class GuideTouristiqueResource {

    @Inject
    private LlmClient llmClient;

    /**
     * Retourne les informations touristiques au format JSON.
     */
    @GET
    @Path("lieu/{ville_ou_pays}")
    @Produces(MediaType.APPLICATION_JSON)
    public InformationsTouristiques endroitsDeVisite(
            @PathParam("ville_ou_pays") String villeOuPays,
            @QueryParam("nb") @DefaultValue("2") int nb) {

        Result<InformationsTouristiques> result =
                llmClient.envoyerQuestion(
                        "Donne exactement " + nb
                                + " endroits à visiter pour : " + villeOuPays
                );

        return result.content();
    }

    /**
     * Retourne les informations touristiques et
     * les métadonnées d'utilisation de Gemini.
     */
    @GET
    @Path("lieu/{ville_ou_pays}/details")
    @Produces(MediaType.APPLICATION_JSON)
    public ReponseTouristique endroitsAvecDetails(
            @PathParam("ville_ou_pays") String villeOuPays,
            @QueryParam("nb") @DefaultValue("2") int nb) {

        Result<InformationsTouristiques> result =
                llmClient.envoyerQuestion(
                        "Donne exactement " + nb
                                + " endroits à visiter pour : " + villeOuPays
                );

        TokenUsage usage = result.tokenUsage();

        return new ReponseTouristique(
                result.content(),
                usage != null ? usage.inputTokenCount() : null,
                usage != null ? usage.outputTokenCount() : null,
                usage != null ? usage.totalTokenCount() : null
        );
    }
}
