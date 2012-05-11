package br.com.caelum.aplicacao;

import static ch.lambdaj.Lambda.avgFrom;
import static ch.lambdaj.Lambda.by;
import static ch.lambdaj.Lambda.extract;
import static ch.lambdaj.Lambda.forEach;
import static ch.lambdaj.Lambda.group;
import static ch.lambdaj.Lambda.having;
import static ch.lambdaj.Lambda.index;
import static ch.lambdaj.Lambda.on;
import static ch.lambdaj.Lambda.project;
import static ch.lambdaj.Lambda.select;
import static ch.lambdaj.Lambda.selectMin;
import static ch.lambdaj.Lambda.sort;
import static ch.lambdaj.Lambda.sumFrom;
import static ch.lambdaj.collection.LambdaCollections.with;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Random;
import br.com.caelum.dominio.Palestra;
import br.com.caelum.dominio.Palestrante;
import br.com.caelum.infra.PalestraDTO;
import ch.lambdaj.function.matcher.HasArgumentWithValue;
import ch.lambdaj.group.Group;

public class RunLambdaJ {

	public static void main(String[] args) {

		List<Palestrante> palestrantes = new ArrayList<Palestrante>();

		List<Palestra> palestras = new ArrayList<Palestra>();

		for (int i = 1; i < 100; i++) {

			Palestrante palestrante = new Palestrante(i, "Aleatorio " + i, new Random().nextInt(100), new Random().nextInt(100));
			palestrantes.add(palestrante);

			palestras.add(new Palestra("palestra" + i, palestrante, new Random().nextInt(100)));
		}

		int totalDePosts = sumFrom(palestrantes).getPostsNoGuj();
		System.out.println("total de posts: " + totalDePosts);

		int mediaDePosts = avgFrom(palestrantes).getPostsNoGuj();
		System.out.println("media de posts: " + mediaDePosts);

		List<Palestrante> palestrantesComPostsMaioresQue50 = select(palestrantes, comPostsMaiorQue(50));
		
		
		
		
		forEach(palestrantesComPostsMaioresQue50).favoritar();
		for (Palestrante palestrante : palestrantesComPostsMaioresQue50) {
			System.out.println(palestrante);
		}

		List<String> nomesDosPalestrantes = extract(palestrantes, on(Palestrante.class).getName());

		for (String nome : nomesDosPalestrantes) {
			System.out.println(nome);
		}

		Map<Integer, Palestrante> mapaDePalestrantes = index(palestrantes, on(Palestrante.class).getId());

		System.out.println(mapaDePalestrantes.size());

		List<Palestrante> ordenadosPorPost = sort(palestrantes, on(Palestrante.class).getPostsNoGuj());
		List<Palestrante> ordenadosPorNome = sort(palestrantes, on(Palestrante.class).getName());

		System.out.println(ordenadosPorPost);
		System.out.println(ordenadosPorNome);

		Group<Palestrante> agrupadosPorPosts = group(palestrantes, by(on(Palestrante.class).getPostsNoGuj()));

		for (Palestrante palestrante : agrupadosPorPosts.findGroup("20").findAll()) {
			System.out.println(palestrante);
		}

		//DTO
		List<PalestraDTO> palestrantesParaTransferencia = project(palestrantes, PalestraDTO.class, on(Palestrante.class).getId(),
				on(Palestrante.class).getName());

		System.out.println(palestrantesParaTransferencia);

		//Selecionar o menor post
		Palestrante palestrantesComMenosPost = selectMin(palestrantes, on(Palestrante.class).getPostsNoGuj());
		System.out.println(palestrantesComMenosPost);

		//combinacao
		//das palestras
		//quero os que tiveram mais de 50 votos
		//entao eu quero somente uma lista de nome desses palestrantes
		//ordenadas por tempo no guj

		List<Palestrante> nomeDosPalestrantesQuePostaramMaisQue80 = with(palestras).retain(comQuantidadeDeVotosMaiorQue(50)).extract(palestrante())
				.sort(porTempoNoGuj());

		System.out.println(nomeDosPalestrantesQuePostaramMaisQue80);

		List<String> nomes = with(palestrantes).retain(having(on(Palestrante.class).getPostsNoGuj(), greaterThanOrEqualTo(30)))
				.extract(on(Palestrante.class).getName()).sort(on(String.class));
		
		
		System.out.println(nomes);
		
				
		
	}

	private static int porTempoNoGuj() {

		return on(Palestrante.class).getTempoDeGuj();
	}

	private static Palestrante palestrante() {

		return on(Palestra.class).getPalestrante();
	}

	private static HasArgumentWithValue<Object, Integer> comQuantidadeDeVotosMaiorQue(int quantidade) {

		return having(on(Palestra.class).getQuantidadeDeVotos(), greaterThanOrEqualTo(quantidade));
	}

	private static HasArgumentWithValue<Object, Integer> comPostsMaiorQue(int valor) {

		return having(on(Palestrante.class).getPostsNoGuj(), greaterThanOrEqualTo(valor));
	}
	
	
}


