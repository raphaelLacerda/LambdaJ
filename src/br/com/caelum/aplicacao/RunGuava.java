package br.com.caelum.aplicacao;

import static com.google.common.collect.Collections2.filter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import org.apache.commons.collections.CollectionUtils;
import br.com.caelum.dominio.Palestrante;
import com.google.common.base.Predicate;

public class RunGuava {

	public static void main(String[] args) {

		List<Palestrante> palestrantes = new ArrayList<Palestrante>();

		for (int i = 1; i < 100; i++) {

			palestrantes.add(new Palestrante(i, "Aleatorio " + i, new Random().nextInt(100)));
		}

		Collection<Palestrante> pm50 = filter(palestrantes, new Predicate<Palestrante>() {

			@Override
			public boolean apply(Palestrante palestrante) {

				return palestrante.getPostsNoGuj() > 50;
			}
		});

		System.out.println(pm50);

		@SuppressWarnings("unchecked")
		Collection<Palestrante> palestrantesMaior = CollectionUtils.select(pm50, new org.apache.commons.collections.Predicate() {

			@Override
			public boolean evaluate(Object arg0) {

				Palestrante palestrante = (Palestrante) arg0;
				return palestrante.getPostsNoGuj() > 50;
			}
		});

		System.out.println(palestrantesMaior);
	}
}
