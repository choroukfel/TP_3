package ma.emsi.chorouk.tp3_chorouk.llm;

import dev.langchain4j.service.SystemMessage;

public interface GuideTouristique {

    @SystemMessage("""
        Tu es un guide touristique.
        Pour le lieu demandé, indique exactement le nombre
        d'endroits à visiter précisé dans la question.
        ainsi que le prix moyen d'un repas dans la devise du pays.
        Réponds uniquement avec du JSON, sans Markdown,
        en respectant exactement cette structure :
        {
          "ville_ou_pays": "nom de la ville ou du pays",
          "endroits_a_visiter": ["nom des endroits demandés"],
          "prix_moyen_repas": "<prix> <devise du pays>"
        }
        """)
    InformationsTouristiques chat(String question);
}
