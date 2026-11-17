
package ma.emsi.chorouk.tp3_chorouk.llm;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.google.genai.GoogleGenAiChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.enterprise.context.Dependent;

import java.io.Serializable;

@Dependent
public class LlmClient implements Serializable {

    /**
     * Assistant IA créé par LangChain4j.
     */
    private transient GuideTouristique assistant;


    public LlmClient() {
        initialiserAssistant();
    }

    /**
     * Initialise le modèle Gemini et l'assistant.
     */
    private void initialiserAssistant() {

        String apiKey = System.getenv("GEMINI_API_KEY");

        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException(
                    "La variable GEMINI_API_KEY n'est pas configurée."
            );
        }

        ChatModel model = GoogleGenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gemini-3.5-flash-lite")
                .temperature(0.3)
                .build();


        // Création du service IA
        this.assistant = AiServices.builder(GuideTouristique.class)
                .chatModel(model)
                .build();
    }


    /**
     * Envoie une question au LLM et retourne sa réponse.
     */
    public String envoyerQuestion(String question) {

        if (assistant == null) {
            initialiserAssistant();

        }

        return assistant.chat(question);
    }

}