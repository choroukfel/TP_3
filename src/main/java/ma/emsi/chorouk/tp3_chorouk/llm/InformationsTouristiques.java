package ma.emsi.chorouk.tp3_chorouk.llm;

import java.util.List;

public record InformationsTouristiques(
        String ville_ou_pays,
        List<String> endroits_a_visiter,
        String prix_moyen_repas
) {
}