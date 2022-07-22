import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.*;
import java.util.List;
import java.util.Map;

public class ApiImdb {

    public static void main(String[] args) throws Exception {

        // Conexão HTTP:

        String url = "https://alura-filmes.herokuapp.com/conteudos";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // Extraindo só o que necessitamos

        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // Exibir o que queremos
        for (Map<String, String> filme: listaDeFilmes) {
            System.out.println("Título: " + filme.get("title"));
            System.out.println(filme.get("image"));
            int rating = (int)Float.parseFloat(filme.get("imDbRating"));
            char c = '\u2606';
            for (int i = 0; i <= rating; i++){
                System.out.print(c);
            }
            System.out.println("\n");
        }

        String urlImagem= listaDeFilmes.get(0).get("image");
        String urlImagemEditada = urlImagem.replaceAll("([.][_](.+)[_])","");
        InputStream imagem = new URL(urlImagemEditada).openStream();
        var geradora = new GeradoraDeFigurinhas();
        geradora.cria(imagem, "filme2.png");
    }
}