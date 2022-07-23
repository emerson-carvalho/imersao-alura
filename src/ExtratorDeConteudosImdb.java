import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudosImdb implements ExtratorDeConteudos{

    public List<Conteudo> extraiConteudos(String json){
        // Extraindo só o que necessitamos
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        List<Conteudo> conteudos = new ArrayList<>();

        // popular a lista de conteudos
        for (Map<String, String> atributos : listaDeAtributos) {
            String titulo = atributos.get("title");
            String urlImagem = atributos.get("image").replaceAll("([.][_](.+)[_])","");

            Conteudo conteudo = new Conteudo(titulo, urlImagem);

            conteudos.add(conteudo);
        }
        return conteudos;
    }
}
