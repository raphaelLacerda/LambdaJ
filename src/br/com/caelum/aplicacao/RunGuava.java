package br.com.caelum.aplicacao;

import static com.google.common.collect.Collections2.filter;
import static com.google.common.collect.Collections2.transform;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import org.apache.commons.collections.CollectionUtils;
import br.com.caelum.dominio.Palestrante;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Ordering;

public class RunGuava {

	public static <T> void main(String[] args) {

		List<Palestrante> palestrantes = new ArrayList<Palestrante>();

		for (int i = 1; i < 100; i++) {

			palestrantes.add(new Palestrante(i, "Aleatorio " + i, new Random().nextInt(100), new Random().nextInt(100)));
		}

		Collection<Palestrante> pm50 = filter(palestrantes, new Predicate<Palestrante>() {

			@Override
			public boolean apply(Palestrante palestrante) {

				return palestrante.getPostsNoGuj() > 50;
			}
		});

		Function<Palestrante, String> funcaoOrdenacao = new Function<Palestrante, String>() {

			@Override
			public String apply(Palestrante p) {

				return p.getName();
			}
		};

		Ordering<Palestrante> ordernacaPorNome = Ordering.natural().onResultOf(funcaoOrdenacao);

//		System.out.println(ImmutableSortedSet.orderedBy(ordernacaPorNome).addAll(palestrantes).build());

//		System.out.println(pm50);

		Comparator<Palestrante> ordernacaoPorNomeComparator = new Comparator<Palestrante>() {

			@Override
			public int compare(Palestrante o1, Palestrante o2) {

				return o1.getName().compareTo(o2.getName());
			}
		};

		Comparator<Palestrante> posts = new Comparator<Palestrante>() {

			@Override
			public int compare(Palestrante o1, Palestrante o2) {

				return o1.getPostsNoGuj() - o2.getPostsNoGuj();
			}
		};

		List<Palestrante> ordenadosPorNome = Ordering.from(ordernacaoPorNomeComparator).compound(posts).sortedCopy(palestrantes);

		System.out.println(ordenadosPorNome);

		@SuppressWarnings("unchecked")
		Collection<Palestrante> palestrantesMaior = CollectionUtils.select(pm50, new org.apache.commons.collections.Predicate() {

			@Override
			public boolean evaluate(Object arg0) {

				Palestrante palestrante = (Palestrante) arg0;
				return palestrante.getPostsNoGuj() > 50;
			}
		});

//		System.out.println(palestrantesMaior);
	}
}
