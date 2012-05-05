package br.com.caelum.aplicacao;

import static ch.lambdaj.Lambda.avgFrom;
import static ch.lambdaj.Lambda.by;
import static ch.lambdaj.Lambda.closure;
import static ch.lambdaj.Lambda.extract;
import static ch.lambdaj.Lambda.forEach;
import static ch.lambdaj.Lambda.group;
import static ch.lambdaj.Lambda.having;
import static ch.lambdaj.Lambda.index;
import static ch.lambdaj.Lambda.of;
import static ch.lambdaj.Lambda.on;
import static ch.lambdaj.Lambda.project;
import static ch.lambdaj.Lambda.select;
import static ch.lambdaj.Lambda.sort;
import static ch.lambdaj.Lambda.sumFrom;
import static ch.lambdaj.Lambda.var;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import br.com.caelum.dominio.Palestrante;
import br.com.caelum.infra.PalestraDTO;
import ch.lambdaj.function.closure.Closure;
import ch.lambdaj.function.matcher.HasArgumentWithValue;
import ch.lambdaj.group.Group;

public class RunLambdaJ {

	public static void main(String[] args) {

		List<Palestrante> palestrantes = new ArrayList<Palestrante>();

		for (int i = 1; i < 300; i++) {

			palestrantes.add(new Palestrante(i, "Aleatorio " + i, new Random().nextInt(100)));
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

		
		
		
		
		List<PalestraDTO> palestrantesParaTransferencia = project(palestrantes, PalestraDTO.class, on(Palestrante.class).getId(),
				on(Palestrante.class).getName());

		System.out.println(palestrantesParaTransferencia);

		println.apply("rafa");
	}

	static Closure	println	= closure();
	{
		of(System.out).println(var(String.class));
	}

	
	
	
	private static HasArgumentWithValue<Object, Integer> comPostsMaiorQue(int valor) {

		return having(on(Palestrante.class).getPostsNoGuj(), greaterThanOrEqualTo(valor));
	}
}
