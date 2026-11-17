package ma.emsi.chorouk.tp3_chorouk.llm;

public record ReponseTouristique(
        InformationsTouristiques informations,
        Integer tokensEntree,
        Integer tokensSortie,
        Integer tokensTotal
) {
}