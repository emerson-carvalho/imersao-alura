import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class ApiImdb {

    public static void main(String[] args) throws Exception {

        // Conexão HTTP:
        String url = "https://alura-filmes.herokuapp.com/conteudos";
        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/NASA-APOD-JamesWebbSpaceTelescope.json";

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        // Exibir o que queremos
        /* for (Map<String, String> filme: listaDeConteudos) {
            System.out.println("Título: " + filme.get("title"));
            System.out.println(filme.get("image"));
            int rating = (int)Float.parseFloat(filme.get("imDbRating"));
            char c = '\u2606';
            for (int i = 0; i <= rating; i++){
                System.out.print(c);
            }
            System.out.println("\n");
        }*/

        ExtratorDeConteudos extrator = new ExtratorDeConteudosImdb();
        // var extrator = new ExtratorDeConteudosNasa();
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();

        for (int i = 0; i < 3; i++){

            Conteudo conteudo = conteudos.get(i);
            InputStream imagem = new URL(conteudo.getUrlImagem()).openStream();

            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";

            System.out.println(conteudo.getTitulo());
            geradora.cria(imagem, nomeArquivo);
         }
    }
}